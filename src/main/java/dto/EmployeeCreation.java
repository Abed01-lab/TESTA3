package dto;

import java.sql.Date;

public class EmployeeCreation {
    public final String firstName, lastName;

    public EmployeeCreation(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
