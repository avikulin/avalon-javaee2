package DAL.DataEntities.Registers;

import Common.AuditableEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "REG_ORG_LOCATIONS", uniqueConstraints = {
        @UniqueConstraint(name = "ORG_LOCATIONS_UC",columnNames = {"ORGANIZATION_ID", "LOC_NAME"})
})
public class Location extends AuditableEntity {
    @Id
    @GeneratedValue
    @Column(name = "LOC_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID")
    private Organization organization;

    @Column(name = "LOC_NAME", nullable = false, length = 50)
    private String locName;

    @Column(name = "LOC_ADDRESS", nullable = false, length = 250)
    private String locAddress;

    @ManyToMany(mappedBy = "locations")
    private List<Contact> contacts;

    public Long getId() {
        return id;
    }

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }

    public String getLocAddress() {
        return locAddress;
    }

    public void setLocAddress(String locAddress) {
        this.locAddress = locAddress;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Organization getOrganization() {
        return organization;
    }
}
