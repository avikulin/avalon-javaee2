package DAL.DataEntities.Dictionaries;

import Common.AuditableEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DICT_SEX")
public class Sex extends AuditableEntity {
    @Id
    @Column(name = "SEX_ID", length = 1)
    private String id;

    @Column(name = "SEX_DESCRIPTION", nullable = false, length = 100)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
