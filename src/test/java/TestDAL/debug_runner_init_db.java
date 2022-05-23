package TestDAL;

import Common.IpAddress;
import DAL.DataEntities.Dictionaries.*;
import DAL.DataEntities.Enums.OsiLayer;
import DAL.DataEntities.Registers.Contact;
import DAL.DataEntities.Registers.Equipment;
import DAL.DataEntities.Registers.Location;
import DAL.DataEntities.Registers.Organization;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class debug_runner_init_db {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("lab1");
        em = emf.createEntityManager();
        tx = em.getTransaction();
        tx.begin();

        Country country = new Country();
        country.setId("US");
        country.setCode("US");
        country.setShortName("United States");
        country.setFullName("United States of America");
        em.persist(country);

        OrgType orgType = new OrgType();
        orgType.setType_id("LTD");
        orgType.setDescription("Limited liability partnership");
        em.persist(orgType);

        Sex sex = new Sex();
        sex.setId("M");
        sex.setName("Male");
        em.persist(sex);



        Organization organization = new Organization();
        organization.setName("Рога и копыта");
        organization.setType(orgType);
        em.persist(organization);

        Location location = new Location();
        location.setLocName("Main office");
        location.setLocAddress("143520, Moscow area, city distr. Istra, Svetlogorye village, 14a");
        location.setOrganization(organization);
        em.persist(location);

        Contact contact = new Contact();
        contact.setLastName("Vikulin");
        contact.setFirstName("Andrey");
        contact.setMiddleName("S.");
        contact.setSex(sex);
        contact.setPhoneNumber("+7 962 981-00-13");
        contact.setEmailAddress("asvikulin@mail.ru");
        em.persist(contact);

        Vendor vendor = new Vendor();
        vendor.setName("Zyxel Corp.");
        vendor.setCountryOfOrigin(country);
        vendor.setWebSite("https://www.zyxel.com/ru/ru/homepage.shtml");
        em.persist(vendor);

        Model model1 = new Model();
        model1.setModelCode("XGS3700-24");
        model1.setLayerNum(OsiLayer.LEVEL3);
        model1.setModelDescription("24-портовый гигабитный L3 коммутатор с 10G аплинком");
        model1.setVendor(vendor);
        model1.setNum100MbpsPorts(24);
        model1.setNum10GbpsPorts(4);
        model1.setNumPoEPorts(0);
        model1.setImageUrl("https://www.zyxel.com/library/assets/products/xgs3700/img_xgs3700-24_p_1000.jpg");
        em.persist(model1);

        Model model2 = new Model();
        model2.setModelCode("XGS3700-24HP");
        model2.setLayerNum(OsiLayer.LEVEL3);
        model2.setModelDescription("24-портовый гигабитный L3 PoE коммутатор с 10G аплинком");
        model2.setVendor(vendor);
        model2.setNum100MbpsPorts(24);
        model2.setNum10GbpsPorts(4);
        model2.setNumPoEPorts(24);
        model2.setImageUrl("https://www.zyxel.com/library/assets/products/xgs3700/img_xgs3700-24hp_p_1000.jpg");
        em.persist(model2);

        Model model3 = new Model();
        model3.setModelCode("XGS3700-48");
        model3.setLayerNum(OsiLayer.LEVEL3);
        model3.setModelDescription("48-портовый гигабитный L3 коммутатор с 10G аплинком");
        model3.setVendor(vendor);
        model3.setNum100MbpsPorts(48);
        model3.setNum10GbpsPorts(4);
        model3.setNumPoEPorts(0);
        model3.setImageUrl("https://www.zyxel.com/library/assets/products/xgs3700/img_xgs3700-48_p_1000.jpg");
        em.persist(model3);

        Model model4 = new Model();
        model4.setModelCode("XGS3700-48HP");
        model4.setLayerNum(OsiLayer.LEVEL3);
        model4.setModelDescription("48-портовый гигабитный L3 PoE коммутатор с 10G аплинком");
        model4.setVendor(vendor);
        model4.setNum100MbpsPorts(48);
        model4.setNum10GbpsPorts(4);
        model4.setNumPoEPorts(48);
        model4.setImageUrl("https://www.zyxel.com/library/assets/products/xgs3700/img_xgs3700-48hp_p_1000.jpg");
        em.persist(model4);

        Equipment equipment1 = new Equipment();
        equipment1.setCode("00-01-223495");
        equipment1.setLocation(location);
        equipment1.setModel(model1);
        equipment1.setIpAddress(new IpAddress(192, 168, 0,1));
        em.persist(equipment1);

        Equipment equipment2 = new Equipment();
        equipment2.setCode("00-01-223483");
        equipment2.setLocation(location);
        equipment2.setModel(model1);
        equipment2.setIpAddress(new IpAddress(192, 168, 0,2));
        em.persist(equipment2);

        Equipment equipment3 = new Equipment();
        equipment3.setCode("00-01-220831");
        equipment3.setLocation(location);
        equipment3.setModel(model1);
        equipment3.setIpAddress(new IpAddress(192, 168, 0,3));
        em.persist(equipment3);

        Equipment equipment4 = new Equipment();
        equipment4.setCode("00-01-229140");
        equipment4.setLocation(location);
        equipment4.setModel(model2);
        equipment4.setIpAddress(new IpAddress(192, 168, 10,100));
        em.persist(equipment4);

        Equipment equipment5 = new Equipment();
        equipment5.setCode("00-01-226846");
        equipment5.setLocation(location);
        equipment5.setModel(model2);
        equipment5.setIpAddress(new IpAddress(192, 168, 10,101));
        em.persist(equipment5);

        Equipment equipment6 = new Equipment();
        equipment6.setCode("00-01-225493");
        equipment6.setLocation(location);
        equipment6.setModel(model2);
        equipment6.setIpAddress(new IpAddress(192, 168, 10,102));
        em.persist(equipment6);

        Equipment equipment7 = new Equipment();
        equipment7.setCode("00-01-228754");
        equipment7.setLocation(location);
        equipment7.setModel(model3);
        equipment7.setIpAddress(new IpAddress(192, 168, 20,10));
        em.persist(equipment7);

        Equipment equipment8 = new Equipment();
        equipment8.setCode("00-01-226544");
        equipment8.setLocation(location);
        equipment8.setModel(model3);
        equipment8.setIpAddress(new IpAddress(192, 168, 20,11));
        em.persist(equipment8);

        Equipment equipment9 = new Equipment();
        equipment9.setCode("00-01-228413");
        equipment9.setLocation(location);
        equipment9.setModel(model3);
        equipment9.setIpAddress(new IpAddress(192, 168, 20,12));
        em.persist(equipment9);

        Equipment equipment10 = new Equipment();
        equipment10.setCode("00-01-227328");
        equipment10.setLocation(location);
        equipment10.setModel(model3);
        equipment10.setIpAddress(new IpAddress(192, 168, 20,13));
        em.persist(equipment10);
        tx.commit();

        if (em != null) em.close();
        if (emf != null) emf.close();
    }
}
