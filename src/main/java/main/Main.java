package main;

import datalayer.employee.EmployeeStorageImpl;
import dto.Customer;
import datalayer.customer.CustomerStorageImpl;
import dto.Employee;

import java.sql.Date;
import java.sql.SQLException;

public class Main {

    private static final String conStr = "jdbc:mysql://localhost:3307/DemoApplication";
    private static final String user = "root";
    private static final String pass = "example";

    public static void main(String[] args) throws SQLException {
        CustomerStorageImpl storage = new CustomerStorageImpl(conStr, user, pass);
        EmployeeStorageImpl employeeStorage = new EmployeeStorageImpl(conStr, user, pass);

        employeeStorage.createEmployee(new Employee("Abed", "Hariri", Date.valueOf("1997-07-14")));
        /*
        System.out.println("Got customers: ");
        for(Customer c : storage.getCustomers()) {
            System.out.println(toString(c));
        }
         */
        System.out.println("The end.");
    }

    public static String toString(Customer c) {
        return "{" + c.getId() + ", " + c.getFirstname() + ", " + c.getLastname() + "}";
    }
}
