package DAL.Utils.Filter;

import ApiUtils.Helpers.DataHelper;
import ApiUtils.Helpers.DataValidator;
import Common.Classes.IpAddress;
import Common.Exceptions.DataValidationException;
import DAL.Utils.Filter.Contracts.FilterExpression;
import DAL.Utils.Filter.Contracts.FilterTokenDef;
import DAL.Utils.Filter.Enums.CriteriaType;
import DAL.Utils.Filter.Enums.PredicateType;

import java.util.*;
import java.util.regex.Pattern;

public class FilterBuilderImpl implements FilterBuilder {
    private static final String TOKEN_SEPARATOR_EXPR = "[\\s]+|([\\s]*[,][\\s]*)";
    private static final Pattern tokenSeparationPattern = Pattern.compile(TOKEN_SEPARATOR_EXPR);

    private final Map<String, FilterTokenDef> fields;
    private List<String> filterableScalars;
    private final List<Class<?>> filterableScalarTypes;
    private final List<String> filterableRefs;
    private int tokenOffset;
    private boolean areClassesTheSame;

    public FilterBuilderImpl(List<FilterTokenDef> fieldDefs) throws DataValidationException {
        Objects.requireNonNull(fieldDefs, "Filterable fields definitions must be set");
        this.tokenOffset = 0;
        this.fields = new HashMap<>();
        this.filterableScalars = new ArrayList<>();
        this.filterableScalarTypes = new ArrayList<>();
        this.filterableRefs = new ArrayList<>();

        for (FilterTokenDef f : fieldDefs) {
            String name = f.getFieldName();
            Class<?> clazz;
            try {
                clazz = DataHelper.cast(f.getFieldClass());
            } catch (DataValidationException dve) {
                throw new DataValidationException("Неподдерживаемые метаданные полей. " + dve.getMessage());
            }

            CriteriaType type = f.getCriteriaType();
            if (type == CriteriaType.ENTITY_REF && !f.getFieldClass().equals(IpAddress.class)) {
                this.filterableRefs.add(name);
            } else {
                this.filterableScalars.add(name);
                this.filterableScalarTypes.add(clazz);
            }
            this.fields.put(name, f);
        }
        this.areClassesTheSame = true;
        Class<?> unigueClazz = this.filterableScalarTypes.get(0);
        for (int i = 1; i < this.filterableScalars.size(); i++) {
            if (!unigueClazz.equals(this.filterableScalarTypes.get(i))) {
                this.areClassesTheSame = false;
            }
        }
    }

    @Override
    public void setScalarCriteriaOrder(List<String> scalarFieldNames) throws DataValidationException {
        Objects.requireNonNull(scalarFieldNames, "Field names sequence must be not-null");
        this.filterableScalars.clear();
        this.filterableScalarTypes.clear();
        for (String s : scalarFieldNames) {
            FilterTokenDef fd = this.fields.get(s);
            if (fd == null) {
                String msg = String.format("Поле \"%s\" не отмечено, как фильтруемое, в параметрах " +
                        "класса представления", s);
                throw new DataValidationException(msg);
            }
            Class<?> clazz = fd.getFieldClass();
            Class<?> uniClazz = DataHelper.cast(clazz);
            this.filterableScalars.add(s);
            this.filterableScalarTypes.add(uniClazz);
        }
    }

    private String findNearestScalar(Class<?> clazz) throws DataValidationException {
        if (this.areClassesTheSame) {
            int offset = this.tokenOffset;
            this.tokenOffset++;
            return this.filterableScalars.get(offset);
        }

        Class<?> unifiedClazz;
        try {
            unifiedClazz = DataHelper.cast(clazz);
        } catch (DataValidationException dve) {
            throw new DataValidationException("Невозможно обработать поиск по типу значения. " + dve.getMessage());
        }

        for (int i = this.tokenOffset; i < this.filterableScalarTypes.size(); i++) {
            if (this.filterableScalarTypes.get(i).equals(unifiedClazz)) {
                this.tokenOffset = i;
                return this.filterableScalars.get(i);
            }
        }
        String msg = String.format("Несоответствие типа параметра (ожидается \"%s\")", clazz.getSimpleName());
        throw new DataValidationException(msg);
    }

    @Override
    public FilterExpression[] build(Map<String, String> refValues, String filterStr) throws DataValidationException {
        List<FilterExpression> expr = new ArrayList<>();
        this.tokenOffset = 0;
        if (DataValidator.isNotEmpty(filterStr)) {
            String[] rawTokens = tokenSeparationPattern.split(filterStr.trim());

            for (int i = 0; i < rawTokens.length; i++) {
                String rawToken = rawTokens[i];
                if (DataValidator.isNotEmpty(rawToken)) {
                    rawToken = rawToken.trim();
                    try {
                        Class<?> clazz = DataHelper.detectClass(rawToken);
                        String fieldName = findNearestScalar(clazz);
                        FilterTokenDef ftd = this.fields.get(fieldName);
                        Class<?> fieldClass = DataHelper.cast(ftd.getFieldClass());
                        clazz = (!clazz.equals(fieldClass)) ? fieldClass : clazz;
                        PredicateType predicate = (clazz.equals(String.class)) ? null : PredicateType.EQ;
                        Object value = DataHelper.extract(rawToken, clazz);
                        expr.add(new FilterExpressionImpl(ftd, predicate, value));
                    } catch (DataValidationException e) {
                        String msg = String.format("Значение \"%s\" не может быть добавлено в фильтр. %s",
                                rawToken,
                                e.getMessage());
                        throw new DataValidationException(msg);
                    }
                } else {
                    this.tokenOffset++;
                }
            }
        }

        if (refValues != null && refValues.size() > 0) {
            for (Map.Entry<String, String> rv : refValues.entrySet()) {
                if (DataValidator.isNotEmpty(rv.getValue())) {
                    FilterTokenDef ftd = this.fields.get(rv.getKey());
                    if (ftd == null) {
                        String msg = String.format("Inconsistent filterable fields definition. Field \"%s\" not found",
                                rv.getKey());
                        throw new DataValidationException(msg);
                    }
                    PredicateType predicate = PredicateType.EQ;
                    Object value = DataHelper.extract(rv.getValue(), ftd.getFieldClass());
                    expr.add(new FilterExpressionImpl(ftd, predicate, value));
                }
            }
        }

        FilterExpression[] res = new FilterExpression[expr.size()];
        return expr.toArray(res);
    }
}
