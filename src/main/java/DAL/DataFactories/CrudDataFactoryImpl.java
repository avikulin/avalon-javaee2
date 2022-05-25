package DAL.DataFactories;

import DAL.Contracts.DataFactory.CrudDataFactory;
import DAL.Contracts.Repository.CrudRepository;
import DAL.DataEntities.Dictionaries.*;
import DAL.DataEntities.Registers.*;
import DAL.Repositories.BaseCrudRepository;

import javax.ejb.Stateful;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

@Stateful
public class CrudDataFactoryImpl implements CrudDataFactory {
    @PersistenceContext
    private EntityManager entityManager;

    public CrudDataFactoryImpl() {}

    public CrudDataFactoryImpl(EntityManager entityManager) {
        Objects.requireNonNull(entityManager, "Entity manager reference must be not null");
        this.entityManager = entityManager;
    }

    @Override
    @Produces
    public CrudRepository<Country, String> getCountryRepo(){
        return new BaseCrudRepository<>(Country.class, entityManager);
    }

    @Override
    @Produces
    public CrudRepository<Model,String> getModelRepo(){
        return new BaseCrudRepository<>(Model.class, entityManager);
    }

    @Override
    @Produces
    public CrudRepository<OrgType,String> getOrgTypeRepo(){
        return new BaseCrudRepository<>(OrgType.class, entityManager);
    }

    @Override
    @Produces
    public CrudRepository<Sex,String> getSexRepo(){
        return new BaseCrudRepository<>(Sex.class, entityManager);
    }

    @Override
    @Produces
    public CrudRepository<Vendor,Long> getVendorRepo(){
        return new BaseCrudRepository<>(Vendor.class, entityManager);
    }

    @Override
    @Produces
    public CrudRepository<Contact,Long> getContactRepo(){
        return new BaseCrudRepository<>(Contact.class, entityManager);
    }

    @Override
    @Produces
    public CrudRepository<Equipment,Long> getEquipmentRepo(){
        return new BaseCrudRepository<>(Equipment.class, entityManager);
    }

    @Override
    @Produces
    public CrudRepository<Location, Long> getLocationRepo(){
        return new BaseCrudRepository<>(Location.class, entityManager);
    }

    @Override
    @Produces
    public CrudRepository<Organization, Long> getOrganizationRepo(){
        return new BaseCrudRepository<>(Organization.class, entityManager);
    }

    @Override
    @Produces
    public CrudRepository<User, String> getUserRepo(){
        return new BaseCrudRepository<>(User.class, entityManager);
    }
}
