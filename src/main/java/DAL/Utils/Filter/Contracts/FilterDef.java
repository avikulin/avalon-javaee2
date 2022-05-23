package DAL.Utils.Filter.Contracts;

import DAL.Utils.Filter.Enums.CriteriaType;

public interface FilterDef {
    String getFieldName();
    String getEntity();
    CriteriaType getType();
}
