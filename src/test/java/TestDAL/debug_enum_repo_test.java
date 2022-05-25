package TestDAL;

import DAL.DataEntities.Enums.OsiLayer;
import DAL.Repositories.BaseEnumRepository;

public class debug_enum_repo_test {
    public static void main(String[] args) {
        BaseEnumRepository<OsiLayer> enumRepository = new BaseEnumRepository<>(OsiLayer.class);
        for (String s : enumRepository.getAll()){
            System.out.println(s);
        }
    }
}
