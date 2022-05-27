package DAL.Utils.Filter;

import DAL.Utils.Filter.Contracts.FilterDef;
import DAL.Utils.Filter.Enums.CriteriaType;

public class FilterDefImpl implements FilterDef {
    private final String entityName;
    private final String fieldName;
    private final String fieldCaption;
    private final CriteriaType criteriaType;

    public FilterDefImpl(String entityName, String fieldName, String fieldCaption, CriteriaType criteriaType) {
        this.entityName = entityName;
        this.fieldName = fieldName;
        this.fieldCaption = fieldCaption;
        this.criteriaType = criteriaType;
    }

    @Override
    public String getFieldName() {
        return this.fieldName;
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
    public CriteriaType getType() {
        return this.criteriaType;
    }
}
