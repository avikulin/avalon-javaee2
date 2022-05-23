package DAL.DataEntities.Dictionaries;

import Common.AuditableEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DICT_ORGANIZATION_TYPES")
public class OrgType extends AuditableEntity {
    @Id
    @Column(name = "TYPE_ID", length = 5)
    private String type_id;

    @Column(name = "TYPE_DESCRIPTION", nullable = false, length = 200)
    private String description;

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
