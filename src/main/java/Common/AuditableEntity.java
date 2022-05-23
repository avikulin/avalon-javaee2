package Common;

import DAL.DataEntities.Registers.User;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import java.sql.Date;

public class AuditableEntity {
    @OneToOne
    @Column(name = "CREATED_BY")
    private User createdBy;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @OneToOne
    @Column(name = "EDITED_BY")
    private User editedBy;

    @Column(name = "EDITED_DATE")
    private Date editedDate;

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(User editedBy) {
        this.editedBy = editedBy;
    }

    public Date getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(Date editedDate) {
        this.editedDate = editedDate;
    }
}
