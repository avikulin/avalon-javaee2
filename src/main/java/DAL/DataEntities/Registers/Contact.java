package DAL.DataEntities.Registers;

import Common.AuditableEntity;
import DAL.DataEntities.Dictionaries.Sex;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "REG_ORG_CONTACTS",
        uniqueConstraints = {
        @UniqueConstraint(name = "FIO_UC",
                          columnNames = {"FIRST_NAME", "MIDDLE_NAME", "LAST_NAME", "SEX_ID"})
})
public class Contact extends AuditableEntity {
    @Id
    @GeneratedValue
    @Column(name = "CONTACT_ID")
    private Long id;

    @ManyToMany
    @JoinTable(name = "LNK_LOC_CONTACTS",
               joinColumns = @JoinColumn(name = "LOC_ID"),
               inverseJoinColumns = @JoinColumn(name = "CONTACT_ID"))
    private List<Location> locations;

    @Column(name = "LAST_NAME", nullable = false, length = 100)
    private String lastName;

    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    private String firstName;

    @Column(name = "MIDDLE_NAME", nullable = false, length = 100)
    private String middleName;

    @OneToOne
    @JoinColumn(name = "SEX_ID", nullable = false)
    private Sex sex;

    @Column(name = "PHONE_NUM", nullable = true, length = 50)
    private String phoneNumber;

    @Column(name = "EMAIL", nullable = true, length = 50)
    private String emailAddress;

    public Long getId() {
        return id;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
