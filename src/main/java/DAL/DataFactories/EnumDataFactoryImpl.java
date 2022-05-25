package DAL.DataFactories;

import DAL.DataEntities.Enums.DeviceType;
import DAL.DataEntities.Enums.OsiLayer;
import DAL.Contracts.DataFactory.EnumDataFactory;
import DAL.Repositories.BaseEnumRepository;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;

@Stateless
public class EnumDataFactoryImpl implements EnumDataFactory {
    @Produces
    public BaseEnumRepository<DeviceType> getDeviceTypeRepo(){
        return new BaseEnumRepository<>(DeviceType.class);
    }

    @Produces
    public BaseEnumRepository<OsiLayer> getOsiLayerRepo(){
        return new BaseEnumRepository<>(OsiLayer.class);
    }
}
