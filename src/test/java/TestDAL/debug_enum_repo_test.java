package TestDAL;

import DAL.DataEntities.Enums.OsiLayer;
import DAL.Repositories.BaseEnumRepository;

import java.util.Map;

public class debug_enum_repo_test {
    public static void main(String[] args) {
        BaseEnumRepository<OsiLayer> enumRepository = new BaseEnumRepository<>(OsiLayer.class);
        for (Map.Entry<String, String> s : enumRepository.getAll().entrySet()){
            System.out.println(s.getKey() + " : "+s.getValue());
        }
    }
}
