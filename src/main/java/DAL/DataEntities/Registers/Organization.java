package DAL.DataEntities.Registers;

import Common.AuditableEntity;
import DAL.DataEntities.Dictionaries.OrgType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "REG_ORGANIZATIONS")
public class Organization extends AuditableEntity {
    @Id
    @GeneratedValue
    @Column(name = "ORG_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name="ORG_TYPE", nullable = false)
    private OrgType type;

    @Column(name = "ORG_NAME", nullable = false, unique = true, length = 200)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
    private List<Location> locations = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public OrgType getType() {
        return type;
    }

    public void setType(OrgType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Location> getLocations() {
        return locations;
    }
}


