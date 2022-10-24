package dto;

import java.sql.Date;

public class EmployeeCreation {
    public final String firstName, lastName;
    public final Date birthdate;

    public EmployeeCreation(String firstName, String lastName, Date birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }
}
