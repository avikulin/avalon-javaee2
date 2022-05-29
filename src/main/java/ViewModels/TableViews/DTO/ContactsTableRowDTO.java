package ViewModels.TableViews.DTO;

import DAL.Annotations.*;

@SourceEntity(entityName = "Contact")
public class ContactsTableRowDTO {

    @SourceField(fieldSource = "Contact", fieldName = "id")
    @CtorParam(position = 1)
    private final Long id;

    @Filterable
    @UserCaption(caption = "Фамилия")
    @SourceField(fieldSource = "Contact", fieldName = "lastName")
    @CtorParam(position = 2)
    private final String lastName;

    @Filterable
    @UserCaption(caption = "Имя")
    @SourceField(fieldSource = "Contact", fieldName = "firstName")
    @CtorParam(position = 3)
    private final String firstName;

    @Filterable
    @UserCaption(caption = "Отчество")
    @SourceField(fieldSource = "Contact", fieldName = "middleName")
    @CtorParam(position = 4)
    private final String middleName;

    @Filterable
    @UserCaption(caption = "Телефон")
    @SourceField(fieldSource = "Contact", fieldName = "phoneNumber")
    @CtorParam(position = 5)
    private final String phoneNumber;

    @Filterable
    @UserCaption(caption = "Эл. почта")
    @SourceField(fieldSource = "Contact", fieldName = "emailAddress")
    @CtorParam(position = 6)
    private final String emailAddress;

    public ContactsTableRowDTO(Long id, String lastName, String firstName, String middleName, String phoneNumber, String emailAddress) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getFullName() {
        return this.lastName + ", " + this.firstName + " " + this.middleName;
    }

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "ContactsTableRowDTO{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
