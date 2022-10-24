package main;

import datalayer.employee.EmployeeStorageImpl;
import dto.Customer;
import datalayer.customer.CustomerStorageImpl;
import dto.CustomerCreation;
import dto.Employee;
import dto.EmployeeCreation;
import servicelayer.employee.EmployeeServiceException;
import servicelayer.employee.EmployeeServiceImpl;

import java.sql.Date;
import java.sql.SQLException;

public class Main {

    private static final String conStr = "jdbc:mysql://localhost:3307/DemoApplication";
    private static final String user = "root";
    private static final String pass = "example";

    public static void main(String[] args) throws SQLException, EmployeeServiceException {
        CustomerStorageImpl storage = new CustomerStorageImpl(conStr, user, pass);
        EmployeeStorageImpl employeeStorage = new EmployeeStorageImpl(conStr, user, pass);
        EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl(new EmployeeStorageImpl(conStr, user, pass));
        var id = employeeServiceImpl.createEmployee("abed", "hariri");
        System.out.println(employeeServiceImpl.getEmployeeById(id).getFirstName());

        storage.createCustomer(new CustomerCreation("Abed", "Hariri", Date.valueOf("1997-07-14")));



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
