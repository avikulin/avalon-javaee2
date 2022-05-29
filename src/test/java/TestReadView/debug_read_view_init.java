package TestReadView;

import DAL.Repositories.BaseReadViewRepository;
import ViewModels.TableViews.DTO.ModelTableRowDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class debug_read_view_init {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab1");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        BaseReadViewRepository<ModelTableRowDTO> modelReadViewRepository = new BaseReadViewRepository<>(entityManager, ModelTableRowDTO.class);

        System.out.println("prepared query: ");
        System.out.println(modelReadViewRepository.getQuery());
        System.out.println();
        System.out.println("Filterable fields:");
        System.out.println(modelReadViewRepository.getFilterableFields());

        System.out.println();
        System.out.println("query results:");
        List<ModelTableRowDTO> res = modelReadViewRepository.getAll(null);
        for(ModelTableRowDTO mtr: res){
            System.out.println(mtr.toString());
        }
    }
}
