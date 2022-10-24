package dto;

import java.sql.Date;

public class CustomerCreation {
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public final Date birthdate;

    public final String firstname, lastname;

    public CustomerCreation(String firstname, String lastname, Date birthdate) {
        this.birthdate = birthdate;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
