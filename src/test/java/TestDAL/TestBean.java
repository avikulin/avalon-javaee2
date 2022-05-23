package TestDAL;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Local
@Stateless
public class TestBean {
    @PersistenceContext(unitName = "lab1")
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
