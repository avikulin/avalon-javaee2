package TestDAL;

import Common.IpAddress;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

class ResultDto{
    private final String code;
    private final String modelCode;
    private final int numPorts;
    private final String organization;
    private final String location;
    private final IpAddress ip;

    public ResultDto(String code, String modelCode, int numPorts, String organization, String location, IpAddress ip) {
        this.code = code;
        this.modelCode = modelCode;
        this.numPorts = numPorts;
        this.organization = organization;
        this.location = location;
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "ResultDto{" +
                "code='" + code + '\'' +
                ", modelCode='" + modelCode + '\'' +
                ", numPorts=" + numPorts +
                ", organization='" + organization + '\'' +
                ", location='" + location + '\'' +
                ", ip='" + ip.toString() + '\'' +
                '}';
    }
}

public class debug_runner_query_data {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab1");
        EntityManager em = emf.createEntityManager();
        TypedQuery<ResultDto> q = em.createQuery(
                                            "select new " +
                                                    "TestDAL.ResultDto(" +
                                                    "eu.code, " +
                                                    "eu.model.modelCode," +
                                                    "eu.model.numPoEPorts," +
                                                    "eu.location.organization.name," +
                                                    "eu.location.locName, " +
                                                    "eu.ipAddress) " +
                                                "from Equipment eu", ResultDto.class);
        List<ResultDto> r = q.getResultList();
        for(ResultDto dto: r){
            System.out.println(dto);
        }
    }



}
