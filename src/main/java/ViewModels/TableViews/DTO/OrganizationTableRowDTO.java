package ViewModels.TableViews.DTO;

import Common.Annotations.UserCaption;
import DAL.Annotations.*;

import java.util.StringJoiner;

@SourceEntity(entityName = "Organization")
public class OrganizationTableRowDTO {
    @UserCaption(value = "Идентификатор")
    @SourceField(fieldSource = "Organization", fieldName = "id")
    @CtorParam(position = 1)
    private final Long id;

    @Filterable
    @UserCaption(value = "Наименование организации")
    @SourceField(fieldSource = "Organization", fieldName = "fullName")
    @CtorParam(position = 2)
    private final String fullName;

    @Filterable
    @UserCaption(value = "Город")
    @SourceField(fieldSource = "Location", fieldName = "locCity", fieldProjectionPath = "mainOfficeLocation.locCity")
    @CtorParam(position = 3)
    private final String locCity;

    @Filterable
    @UserCaption(value = "Улица")
    @SourceField(fieldSource = "Location", fieldName = "locStreet", fieldProjectionPath = "mainOfficeLocation.locStreet")
    @CtorParam(position = 4)
    private final String locStreet;

    @Filterable
    @UserCaption(value = "Номер дома")
    @SourceField(fieldSource = "Location", fieldName = "locHouseNumber", fieldProjectionPath = "mainOfficeLocation.locHouseNumber")
    @CtorParam(position = 5)
    private final int locHouseNumber;

    @Filterable
    @UserCaption(value = "Номер строения")
    @SourceField(fieldSource = "Location", fieldName = "locBuilding", fieldProjectionPath = "mainOfficeLocation.locBuilding")
    @CtorParam(position = 6)
    private final int locBuilding;

    @Filterable
    @UserCaption(value = "Номер квартиры")
    @SourceField(fieldSource = "Location", fieldName = "locApartmentNumber", fieldProjectionPath = "mainOfficeLocation.locApartmentNumber")
    @CtorParam(position = 7)
    private final int locApartmentNumber;

    @Filterable
    @UserCaption(value = "Фамилия")
    @SourceField(fieldSource = "Contact", fieldName = "lastName", fieldProjectionPath = "mainContact.lastName")
    @CtorParam(position = 8)
    private final String lastName;

    @Filterable
    @UserCaption(value = "Имя")
    @SourceField(fieldSource = "Contact", fieldName = "firstName", fieldProjectionPath = "mainContact.firstName")
    @CtorParam(position = 9)
    private final String firstName;

    @Filterable
    @UserCaption(value = "Отчество")
    @SourceField(fieldSource = "Contact", fieldName = "middleName", fieldProjectionPath = "mainContact.middleName")
    @CtorParam(position = 10)
    private final String middleName;

    @Filterable
    @UserCaption(value = "Отчество")
    @SourceField(fieldSource = "Contact", fieldName = "phoneNumber", fieldProjectionPath = "mainContact.phoneNumber")
    @CtorParam(position = 11)
    private final String phoneNumber;

    @Filterable
    @UserCaption(value = "Отчество")
    @SourceField(fieldSource = "Contact", fieldName = "emailAddress", fieldProjectionPath = "mainContact.emailAddress")
    @CtorParam(position = 12)
    private final String emailAddress;

    public OrganizationTableRowDTO(Long id, String fullName, String locCity, String locStreet, int locHouseNumber,
                                   int locBuilding, int locApartmentNumber, String lastName, String firstName,
                                   String middleName, String phoneNumber, String emailAddress) {
        this.id = id;
        this.fullName = fullName;
        this.locCity = locCity;
        this.locStreet = locStreet;
        this.locHouseNumber = locHouseNumber;
        this.locBuilding = locBuilding;
        this.locApartmentNumber = locApartmentNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getFullAddress() {
        StringJoiner res = new StringJoiner(", ");
        res.add(this.locCity);
        res.add(this.locStreet);
        res.add("д. " + this.locHouseNumber);
        res.add("корп. " + this.locBuilding);
        res.add("кв. " + this.locApartmentNumber);
        return res.toString();
    }

    public String getMainContact(){
        StringBuilder res = new StringBuilder();
        res.append(this.lastName);
        res.append(" ");
        res.append(this.firstName.toUpperCase().substring(0,1));
        res.append(". ");
        res.append(this.middleName.toUpperCase().substring(0,1));
        res.append(".");
        return res.toString();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "OrganizationTableRowDTO{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", locCity='" + locCity + '\'' +
                ", locStreet='" + locStreet + '\'' +
                ", locHouseNumber=" + locHouseNumber +
                ", locBuilding=" + locBuilding +
                ", locApartmentNumber=" + locApartmentNumber +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
