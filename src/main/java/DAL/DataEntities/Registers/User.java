package DAL.DataEntities.Registers;

import Common.Classes.AuditableEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "REG_USERS")
public class User  extends AuditableEntity {
    @Id
    @Column(name = "LOGIN")
    private String login;

    @Column(name = "FULL_NAME", nullable = false, length = 100)
    private String fullName;

    @Column(name = "DESCRIPTION", nullable = false, length = 200)
    private String description;

    @Column(name = "LOGO_URL", nullable = false, length = 200)
    private String logoUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
