package servicelayer.customer;

import dto.Customer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

public interface CustomerService {
    int createCustomer(String firstName, String lastName, Date birthdate) throws CustomerServiceException;
    Customer getCustomerById(int id) throws SQLException;
    Collection<Customer> getCustomersByFirstName(String firstName);
}
