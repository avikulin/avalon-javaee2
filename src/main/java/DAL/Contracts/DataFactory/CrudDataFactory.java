package DAL.Contracts.DataFactory;

import DAL.Contracts.Repository.CrudRepository;
import DAL.DataEntities.Dictionaries.*;
import DAL.DataEntities.Registers.*;

import javax.ejb.Local;
import javax.enterprise.inject.Produces;

@Local
public interface CrudDataFactory {
    @Produces
    CrudRepository<Country, String> getCountryRepo();

    @Produces
    CrudRepository<Model,String> getModelRepo();

    @Produces
    CrudRepository<OrgType,String> getOrgTypeRepo();

    @Produces
    CrudRepository<Sex,String> getSexRepo();

    @Produces
    CrudRepository<Vendor,Long> getVendorRepo();

    @Produces
    CrudRepository<Contact,Long> getContactRepo();

    @Produces
    CrudRepository<Equipment,Long> getEquipmentRepo();

    @Produces
    CrudRepository<Location, Long> getLocationRepo();

    @Produces
    CrudRepository<Organization, Long> getOrganizationRepo();

    @Produces
    CrudRepository<User, String> getUserRepo();
}
