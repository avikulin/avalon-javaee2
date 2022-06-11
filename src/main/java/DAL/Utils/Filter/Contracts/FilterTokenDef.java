package DAL.Utils.Filter.Contracts;

import DAL.Utils.Filter.Enums.CriteriaType;

public interface FilterTokenDef {
    String getFieldName();
    Class<?> getFieldClass();
    String getEntity();
    String getUserDescription();
    CriteriaType getCriteriaType();
}
