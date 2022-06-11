package ViewModels.FormViewModels.Location;

import ApiUtils.Helpers.DataHelper;
import ApiUtils.Helpers.DataValidator;
import Common.Exceptions.DataValidationException;
import DAL.Contracts.DataFactory.CrudDataFactory;
import DAL.Contracts.DataFactory.ListDataFactory;
import DAL.Contracts.DataFactory.ReadViewDataFactory;
import DAL.Contracts.Repository.CrudRepository;
import DAL.Contracts.Repository.ListRepository;
import DAL.DataEntities.Registers.Location;
import DAL.DataEntities.Registers.Organization;
import ViewModels.FormViewModels.Location.DTO.LocationFormDTO;
import ViewModels.TableViewModels.DTO.OrganizationTableRowDTO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Map;

@Stateless
public class LocationFormViewModelImpl implements LocationFormViewModel {
    @EJB
    CrudDataFactory dataFactory;

    @EJB
    ListDataFactory listDataFactory;

    @EJB
    private ReadViewDataFactory readViewDataFactory;

    private CrudRepository<Location, Long> locRepo;
    private CrudRepository<Organization, Long> orgRepo;
    private ListRepository<OrganizationTableRowDTO, Long> orgListRepo;

    @PostConstruct
    private void init(){
        this.locRepo = dataFactory.getLocationRepo();
        this.orgRepo = dataFactory.getOrganizationRepo();
        this.orgListRepo = listDataFactory.getOrganizationsListRepo(readViewDataFactory.getOrganizationsReadViewRepo());
    }

    @Override
    public Map<Long, String> getOrganizationsList() {
        return orgListRepo.getItems();
    }

    @Override
    public LocationFormDTO getData(Long id) throws DataValidationException {
        try {
            Location locEntity = locRepo.getById(id);
            return new LocationFormDTO(id.toString(),
                    locEntity.getLocName(),
                    locEntity.getOrganization().getId().toString(),
                    locEntity.getLocationCity(),
                    locEntity.getLocationStreet(),
                    Integer.toString(locEntity.getHouseNumber()),
                    Integer.toString(locEntity.getBuilding()),
                    Integer.toString(locEntity.getApartmentNumber()),
                    locEntity.getInfo());
        }catch (Exception e){
            String msg = String.format("Ошибка чтения данных из БД по идентификатору \"%d\"", id);
            throw new DataValidationException(msg);
        }
    }

    @Override
    public void putData(LocationFormDTO item) throws DataValidationException {
        Location loc = new Location();
        if (!DataValidator.isEmpty(item.getId())){
            try {
                loc = new Location(Long.parseLong(item.getId()));
            }catch (NumberFormatException nfe){
                throw new DataValidationException("Передан неверный идентификатор сущности");
            }
        }

        loc.setLocName(item.getLocationName());
        String orgID = item.getOrganizationId();
        if (!DataValidator.isLong(orgID)){
            String msg = String.format("Передан неверный идентификатор организации (%s)", item.getOrganizationId());
            throw new DataValidationException(msg);
        }
        Organization org = orgRepo.getById(DataHelper.getLong(orgID));
        if (org == null){
            String msg = String.format("Организация с идентификатором \"%s\" не найдена в БД", item.getOrganizationId());
            throw new DataValidationException(msg);
        }
        loc.setOrganization(org);
        loc.setLocationCity(item.getCityAddress());
        loc.setStreet(item.getStreetAddress());
        loc.setHouseNumber(DataHelper.getInt(item.getHouseNumberAddress()));
        loc.setBuilding(DataHelper.getInt(item.getBuildingAddress()));
        loc.setApartmentNumber(DataHelper.getInt(item.getApartmentAddress()));
        loc.setInfo(item.getAdditionalAddress());
        try {
            if (item.getId() == null){
                locRepo.create(loc);
            } else {
                locRepo.update(loc);
            }
            locRepo.flush();
        }catch (Exception e){
            throw new DataValidationException("Ошибка сохранения данных в БД");
        }
    }

    @Override
    public void deleteItem(Long id) throws DataValidationException {
        try {
            locRepo.delete(id);
            locRepo.flush();
        }catch (Exception e){
            throw new DataValidationException("Передан неверный идентификатор сущности");
        }
    }
}
