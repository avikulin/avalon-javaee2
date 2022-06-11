package DAL.DataFactories;

import DAL.Contracts.DataFactory.EnumDataFactory;
import DAL.DataEntities.Enums.DeviceType;
import DAL.DataEntities.Enums.OsiLayer;
import DAL.Repositories.BaseEnumRepository;

import javax.ejb.Stateless;

@Stateless
public class EnumDataFactoryImpl implements EnumDataFactory {
    public BaseEnumRepository<DeviceType> getDeviceTypeRepo(){
        return new BaseEnumRepository<>(DeviceType.class);
    }
    public BaseEnumRepository<OsiLayer> getOsiLayerRepo(){
        return new BaseEnumRepository<>(OsiLayer.class);
    }
}
