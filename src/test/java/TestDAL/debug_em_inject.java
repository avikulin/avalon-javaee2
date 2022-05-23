package TestDAL;

import javax.ejb.EJB;

class Runner{
    @EJB
    public TestBean bean;

}

public class debug_em_inject {

    public static void main(String[] args) {
        Runner runner = new Runner();
        System.out.println(runner.bean.getEntityManager().getClass().getName());
    }
}
