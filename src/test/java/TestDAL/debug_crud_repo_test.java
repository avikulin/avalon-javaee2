package TestDAL;
import DAL.DataEntities.Dictionaries.Sex;
import DAL.DataEntities.Registers.Contact;
import DAL.Templates.BaseCrudRepository;

public class debug_crud_repo_test {
    public static void main(String[] args) {
        BaseCrudRepository<Sex, String> sexRepo = new BaseCrudRepository<>(Sex.class);
        Sex s = sexRepo.getById("M");

        BaseCrudRepository<Contact, Integer> contactRepo = new BaseCrudRepository<>();
        Contact contact = new Contact();
        contact.setFirstName("First name");
        contact.setLastName("Last name");
        contact.setMiddleName("Middle name");
        contact.setSex(s);
        contact.setEmailAddress("email@address.ru");
        contact.setPhoneNumber("+7 000 111 22 33");

        contactRepo.create(contact);
        System.out.println(contact.getId());
    }
}
