package DAL.Contracts.DataFactory;

import DAL.Contracts.Repository.CrudRepository;
import DAL.DataEntities.Dictionaries.*;
import DAL.DataEntities.Registers.*;

import javax.ejb.Local;

@Local
public interface CrudDataFactory {
    CrudRepository<Country, String> getCountryRepo();

    CrudRepository<Model,String> getModelRepo();

    CrudRepository<OrgType,String> getOrgTypeRepo();

    CrudRepository<Sex,String> getSexRepo();

    CrudRepository<Vendor,Long> getVendorRepo();

    CrudRepository<Contact,Long> getContactRepo();

    CrudRepository<Equipment,Long> getEquipmentRepo();

    CrudRepository<Location, Long> getLocationRepo();

    CrudRepository<Organization, Long> getOrganizationRepo();

    CrudRepository<User, String> getUserRepo();
}
