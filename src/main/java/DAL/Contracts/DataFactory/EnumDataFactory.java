package DAL.Contracts.DataFactory;

import DAL.DataEntities.Enums.DeviceType;
import DAL.DataEntities.Enums.OsiLayer;
import DAL.Repositories.BaseEnumRepository;

//@Local
public interface EnumDataFactory {
//    @Produces
    BaseEnumRepository<DeviceType> getDeviceTypeRepo();

//    @Produces
    BaseEnumRepository<OsiLayer> getOsiLayerRepo();
}
