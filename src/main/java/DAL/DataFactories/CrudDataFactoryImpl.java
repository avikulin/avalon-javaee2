package DAL.DataFactories;

import DAL.Contracts.DataFactory.CrudDataFactory;
import DAL.Contracts.Repository.CrudRepository;
import DAL.DataEntities.Dictionaries.*;
import DAL.DataEntities.Registers.*;
import DAL.Repositories.BaseCrudRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CrudDataFactoryImpl implements CrudDataFactory {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CrudRepository<Country, String> getCountryRepo(){
        return new BaseCrudRepository<>(Country.class, entityManager);
    }

    @Override
    public CrudRepository<Model,String> getModelRepo(){
        return new BaseCrudRepository<>(Model.class, entityManager);
    }

    @Override
    public CrudRepository<OrgType,String> getOrgTypeRepo(){
        return new BaseCrudRepository<>(OrgType.class, entityManager);
    }

    @Override
    public CrudRepository<Sex,String> getSexRepo(){
        return new BaseCrudRepository<>(Sex.class, entityManager);
    }

    @Override
    public CrudRepository<Vendor,Long> getVendorRepo(){
        return new BaseCrudRepository<>(Vendor.class, entityManager);
    }

    @Override
    public CrudRepository<Contact,Long> getContactRepo(){
        return new BaseCrudRepository<>(Contact.class, entityManager);
    }

    @Override
    public CrudRepository<Equipment,Long> getEquipmentRepo(){
        return new BaseCrudRepository<>(Equipment.class, entityManager);
    }

    @Override
    public CrudRepository<Location, Long> getLocationRepo(){
        return new BaseCrudRepository<>(Location.class, entityManager);
    }

    @Override
    public CrudRepository<Organization, Long> getOrganizationRepo(){
        return new BaseCrudRepository<>(Organization.class, entityManager);
    }

    @Override
    public CrudRepository<User, String> getUserRepo(){
        return new BaseCrudRepository<>(User.class, entityManager);
    }
}
