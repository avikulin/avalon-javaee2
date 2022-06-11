package DAL.DataFactories;

import DAL.Contracts.DataFactory.CrudDataFactory;
import DAL.Contracts.Repository.CrudRepository;
import DAL.DataEntities.Dictionaries.*;
import DAL.DataEntities.Registers.*;
import DAL.Repositories.BaseCrudRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class CrudDataFactoryImpl implements CrudDataFactory {
    private CrudRepository<Country, String> countryRepo;
    private CrudRepository<Model,String> modelRepo;
    private CrudRepository<OrgType,String> orgTypeRepo;
    private CrudRepository<Sex,String> sexRepo;
    private CrudRepository<Vendor,Long> vendorRepo;
    private CrudRepository<Contact,Long> contactRepo;
    private CrudRepository<Equipment,Long> equipmentRepo;
    private CrudRepository<Location, Long> locationRepo;
    private CrudRepository<Organization, Long> organizationRepo;
    private CrudRepository<User, String> userRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    private void init(){
        this.countryRepo = new BaseCrudRepository<>(Country.class, entityManager);
        this.modelRepo = new BaseCrudRepository<>(Model.class, entityManager);
        this.orgTypeRepo = new BaseCrudRepository<>(OrgType.class, entityManager);
        this.sexRepo = new BaseCrudRepository<>(Sex.class, entityManager);
        this.vendorRepo = new BaseCrudRepository<>(Vendor.class, entityManager);
        this.contactRepo = new BaseCrudRepository<>(Contact.class, entityManager);
        this.equipmentRepo = new BaseCrudRepository<>(Equipment.class, entityManager);
        this.locationRepo = new BaseCrudRepository<>(Location.class, entityManager);
        this.organizationRepo = new BaseCrudRepository<>(Organization.class, entityManager);
        this.userRepo = new BaseCrudRepository<>(User.class, entityManager);
    }

    @Override
    public CrudRepository<Country, String> getCountryRepo(){
        return this.countryRepo;
    }

    @Override
    public CrudRepository<Model,String> getModelRepo(){
        return this.modelRepo;
    }

    @Override
    public CrudRepository<OrgType,String> getOrgTypeRepo(){
        return this.orgTypeRepo;
    }

    @Override
    public CrudRepository<Sex,String> getSexRepo(){
        return this.sexRepo;
    }

    @Override
    public CrudRepository<Vendor,Long> getVendorRepo(){
        return this.vendorRepo;
    }

    @Override
    public CrudRepository<Contact,Long> getContactRepo(){
        return this.contactRepo;
    }

    @Override
    public CrudRepository<Equipment,Long> getEquipmentRepo(){
        return this.equipmentRepo;
    }

    @Override
    public CrudRepository<Location, Long> getLocationRepo(){
        return this.locationRepo;
    }

    @Override
    public CrudRepository<Organization, Long> getOrganizationRepo(){
        return this.organizationRepo;
    }

    @Override
    public CrudRepository<User, String> getUserRepo(){
        return this.userRepo;
    }
}
