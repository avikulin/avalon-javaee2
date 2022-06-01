package DAL.DataEntities.Registers;

import Common.AuditableEntity;
import Common.IpAddress;
import DAL.DataEntities.Dictionaries.Model;

import javax.persistence.*;

@Entity
@Table(name = "REG_EQUIPMENT_UNITS")
public class Equipment  extends AuditableEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "UNIT_CODE", nullable = false, length = 50)
    private String code;

    @OneToOne
    @JoinColumn(name = "MODEL_ID", nullable = false)
    private Model model;

    @OneToOne
    @JoinColumn(name = "LOCATION_ID", nullable = false)
    private Location location;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ipAddressValue", column = @Column(name = "IP_ADDR", nullable = false))
    })
    private IpAddress ipAddress;

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public IpAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IpAddress ipAddress) {
        this.ipAddress = ipAddress;
    }
}
