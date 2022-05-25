package TestDAL;
import DAL.DataEntities.Dictionaries.Sex;
import DAL.DataEntities.Registers.Contact;
import DAL.Repositories.BaseCrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class debug_crud_repo_test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab1");
        EntityManager em = emf.createEntityManager();

        BaseCrudRepository<Sex, String> sexRepo = new BaseCrudRepository<>(Sex.class, em);
        Sex s = sexRepo.getById("M");

        BaseCrudRepository<Contact, Long> contactRepo = new BaseCrudRepository<>(Contact.class, em);
        Contact contact = new Contact();
        contact.setFirstName("First name");
        contact.setLastName("Last name");
        contact.setMiddleName("Middle name");
        contact.setSex(s);
        contact.setEmailAddress("email@address.ru");
        contact.setPhoneNumber("+7 000 111 22 33");

        contactRepo.create(contact);
        Long id = contact.getId();
        System.out.println("ContactID: "+ id);

        contact = null;
        System.out.println("Object data:");
        Contact new_contact = contactRepo.getById(id);
        StringBuilder builder = new StringBuilder();
        builder.append(new_contact.getLastName());
        builder.append(" ");
        builder.append(new_contact.getFirstName());
        builder.append(" ");
        builder.append(new_contact.getMiddleName());
        builder.append(" ");
        builder.append(new_contact.getEmailAddress());
        builder.append(" ");
        builder.append(new_contact.getPhoneNumber());
        System.out.println(builder.toString());

        new_contact.setLastName("VIKULIN");
        new_contact.setFirstName("ANDREY");
        new_contact.setMiddleName("SERGEEVICH");
        new_contact.setEmailAddress("EMAIL@ADDR.RU");
        new_contact.setPhoneNumber("+7 000 000-00-00");

        contactRepo.update(new_contact);
        System.out.println("Item updated");

        contactRepo.delete(new_contact.getId());

        System.out.println("Item deleted");
    }
}
