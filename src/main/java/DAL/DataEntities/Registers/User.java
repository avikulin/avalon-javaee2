package DAL.DataEntities.Registers;

import Common.AuditableEntity;

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

    @Transient
    private List<String> roles;

    @Transient
    private List<String> privileges;
}
