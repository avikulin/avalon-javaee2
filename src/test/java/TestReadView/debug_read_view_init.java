package TestReadView;

import DAL.Repositories.BaseReadViewRepository;
import DAL.Utils.Filter.Enums.PredicateType;
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

        System.out.println("---WITHOUT FILTERS---");
        System.out.println();
        System.out.println("query results:");
        List<ModelTableRowDTO> res = modelReadViewRepository.getAll();
        for(ModelTableRowDTO mtr: res){
            System.out.println(mtr.toString());
        }
        System.out.println("prepared query: ");
        System.out.println(modelReadViewRepository.getQuery());
        System.out.println();
        System.out.println();
        //------------------------
        System.out.println("---ADD FILTERS---");
        System.out.println("Filterable fields:");
        System.out.println(modelReadViewRepository.getFilterableFields());
        System.out.println();

        System.out.println("1) Add filter: code LIKE 'S37'");
        modelReadViewRepository.addFilter("code","S37");
        res = modelReadViewRepository.getAll();
        for(ModelTableRowDTO mtr: res){
            System.out.println(mtr.toString());
        }
        System.out.println("prepared query: ");
        System.out.println(modelReadViewRepository.getQuery());
        modelReadViewRepository.clearFilters();
        System.out.println();
        System.out.println();
        //---
        System.out.println("2) Add filter: vendor LIKE 'Packard'");
        modelReadViewRepository.addFilter("vendor","Packard");
        res = modelReadViewRepository.getAll();
        for(ModelTableRowDTO mtr: res){
            System.out.println(mtr.toString());
        }
        System.out.println("prepared query: ");
        System.out.println(modelReadViewRepository.getQuery());
        modelReadViewRepository.clearFilters();
        System.out.println();
        System.out.println();
        //---
        System.out.println("3) Add filter: country = 'Соединенные Штаты'");
        modelReadViewRepository.addFilter("country", PredicateType.EQ, "Соединенные Штаты");
        res = modelReadViewRepository.getAll();
        for(ModelTableRowDTO mtr: res){
            System.out.println(mtr.toString());
        }
        System.out.println("prepared query: ");
        System.out.println(modelReadViewRepository.getQuery());
        modelReadViewRepository.clearFilters();
        System.out.println();
        System.out.println();
        //---
        System.out.println("4) Add filter: numPoEPorts >= 12 AND numPoEPorts <= 24");
        modelReadViewRepository.addFilter("numPoEPorts", PredicateType.GTE, 12);
        modelReadViewRepository.addFilter("numPoEPorts", PredicateType.LTE, 24);
        res = modelReadViewRepository.getAll();
        for(ModelTableRowDTO mtr: res){
            System.out.println(mtr.toString());
        }
        System.out.println("prepared query: ");
        System.out.println(modelReadViewRepository.getQuery());
        modelReadViewRepository.clearFilters();

    }
}
