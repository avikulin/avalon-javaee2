package DAL.Contracts.DataFactory;

import DAL.DataEntities.Enums.DeviceType;
import DAL.DataEntities.Enums.OsiLayer;
import DAL.Repositories.BaseEnumRepository;

import javax.ejb.Local;

@Local
public interface EnumDataFactory {
    BaseEnumRepository<DeviceType> getDeviceTypeRepo();
    BaseEnumRepository<OsiLayer> getOsiLayerRepo();
}
