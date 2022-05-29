package DAL.DataFactories;

import DAL.Contracts.DataFactory.EnumDataFactory;
import DAL.DataEntities.Enums.DeviceType;
import DAL.DataEntities.Enums.OsiLayer;
import DAL.Repositories.BaseEnumRepository;

//@Stateless
public class EnumDataFactoryImpl implements EnumDataFactory {
//    @Produces
    public BaseEnumRepository<DeviceType> getDeviceTypeRepo(){
        return new BaseEnumRepository<>(DeviceType.class);
    }

//    @Produces
    public BaseEnumRepository<OsiLayer> getOsiLayerRepo(){
        return new BaseEnumRepository<>(OsiLayer.class);
    }
}
