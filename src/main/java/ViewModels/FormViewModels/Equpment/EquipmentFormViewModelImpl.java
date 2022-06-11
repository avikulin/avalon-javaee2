package ViewModels.FormViewModels.Equpment;

import ApiUtils.Helpers.DataHelper;
import Common.Classes.IpAddress;
import Common.Exceptions.DataValidationException;
import DAL.Contracts.DataFactory.CrudDataFactory;
import DAL.Contracts.DataFactory.ListDataFactory;
import DAL.Contracts.DataFactory.ReadViewDataFactory;
import DAL.Contracts.Repository.CrudRepository;
import DAL.Contracts.Repository.ListRepository;
import DAL.Contracts.Repository.ReadViewRepository;
import DAL.DataEntities.Dictionaries.Model;
import DAL.DataEntities.Registers.Equipment;
import DAL.DataEntities.Registers.Location;
import ViewModels.FormViewModels.Equpment.DTO.EquipmentFormDTO;
import ViewModels.TableViewModels.DTO.ModelTableRowDTO;
import ViewModels.TableViewModels.Location.DTO.LocationTableRowDTO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Map;
import java.util.Objects;

@Stateless
public class EquipmentFormViewModelImpl implements EquipmentFormViewModel{
    @EJB
    CrudDataFactory dataFactory;

    @EJB
    ListDataFactory listDataFactory;

    @EJB
    ReadViewDataFactory readViewDataFactory;

    private CrudRepository<Model, String> modelRepo;
    private CrudRepository<Location, Long> locationRepo;
    private CrudRepository<Equipment, Long> equipmentUnitRepo;
    private ListRepository<ModelTableRowDTO, String> modelsListRepo;
    private ListRepository<LocationTableRowDTO, Long> locationsListRepo;

    @PostConstruct
    private void init(){
        this.modelRepo = dataFactory.getModelRepo();
        this.locationRepo = dataFactory.getLocationRepo();
        this.equipmentUnitRepo = dataFactory.getEquipmentRepo();

        ReadViewRepository<ModelTableRowDTO> modelsTableRepo = readViewDataFactory.getModelsReadViewRepo();
        this.modelsListRepo = listDataFactory.getModelListRepo(modelsTableRepo);

        ReadViewRepository<LocationTableRowDTO> locationTableRepo = readViewDataFactory.getLocationsReadViewRepo();
        this.locationsListRepo = listDataFactory.getLocationsListRepo(locationTableRepo);
    }

    @Override
    public Map<String, String> getModelsList() {
        return this.modelsListRepo.getItems();
    }

    @Override
    public Map<Long, String> getLocationsList() {
        return this.locationsListRepo.getItems();
    }

    @Override
    public EquipmentFormDTO getData(Long id) throws DataValidationException {
        Objects.requireNonNull(id, "Передан пустой идентификатор объекта");
        Equipment eq = equipmentUnitRepo.getById(id);
        if (eq == null){
            throw new DataValidationException("Запрошенный объект не найден в системе");
        }
        EquipmentFormDTO eqDTO = new EquipmentFormDTO();
        eqDTO.setId(id.toString());
        eqDTO.setCode(eq.getCode());
        eqDTO.setDescription(eq.getDescription());
        eqDTO.setModel(eq.getModel().getId());
        eqDTO.setLocation(eq.getLocation().getId().toString());
        eqDTO.setIpAddress(eq.getIpAddress().toString());
        return eqDTO;
    }

    @Override
    public void putData(EquipmentFormDTO item) throws DataValidationException {
        if (item == null) {
            throw new DataValidationException("Передана пустая ссылка на объект данных");
        }
        Equipment eq;
        if (item.getId() == null || item.getId().isEmpty()) {
            eq = new Equipment();
        } else {
            eq = this.equipmentUnitRepo.getById(DataHelper.getLong(item.getId()));
        }

        eq.setCode(item.getCode());
        eq.setDescription(item.getDescription());
        Model model = this.modelRepo.getById(item.getModel());
        if (model == null){
            String msg = String.format("Модель с идентификатором \"%s\" не найдена в БД", item.getModel());
            throw new DataValidationException(msg);
        }
        eq.setModel(model);

        Location location = this.locationRepo.getById(DataHelper.getLong(item.getLocation()));
        if (location == null){
            String msg = String.format("Местоположение с идентификатором \"%s\" не найдено в БД", item.getLocation());
            throw new DataValidationException(msg);
        }
        eq.setLocation(location);
        eq.setIpAddress(new IpAddress(item.getIpAddress()));

        if (item.getId() == null || item.getId().isEmpty()){
            this.equipmentUnitRepo.create(eq);
        } else {
            this.equipmentUnitRepo.update(eq);
        }
        this.equipmentUnitRepo.flush();
    }

    @Override
    public void deleteItem(Long id) throws DataValidationException {
        if (id == null){
            throw new DataValidationException("Передан пустой идентификатор объекта");
        }
        try {
            this.equipmentUnitRepo.delete(id);
        }catch (Exception e){
            String msg = String.format("Удаление объекта по переданному ИД \"%d\" невозможно", id);
            throw new DataValidationException(msg);
        }
    }
}
