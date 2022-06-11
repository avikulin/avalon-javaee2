package DAL.Utils.Filter;

import DAL.Utils.Filter.Contracts.FilterTokenDef;
import DAL.Utils.Filter.Enums.CriteriaType;

public class FilterTokenDefImpl implements FilterTokenDef {
private final String entityName;            // из какой сущности тянуть список для выбора
    private final String fieldName;         // на какое поле накладывать фильтр
    private final Class<?> fieldClass;      // какой тип предиката нужен
    private final String fieldCaption;      // заголовок для поля фильтрации
    private final CriteriaType criteriaType;// правила фильтрации (тип предиката)

    public FilterTokenDefImpl(String entityName, String fieldName, Class<?> clazz, String fieldCaption, CriteriaType criteriaType) {
        this.entityName = entityName;
        this.fieldName = fieldName;
        this.fieldClass = clazz;
        this.fieldCaption = fieldCaption;
        this.criteriaType = criteriaType;
    }

    @Override
    public String getFieldName() {
        return this.fieldName;
    }

    @Override
    public Class<?> getFieldClass() {
        return fieldClass;
    }

    @Override
    public String getEntity() {
        return this.entityName;
    }

    @Override
    public String getUserDescription() {
        return this.fieldCaption;
    }

    @Override
    public CriteriaType getCriteriaType() {
        return this.criteriaType;
    }
}
