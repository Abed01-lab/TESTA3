package integration.datalayer.employee;

import com.github.javafaker.Faker;
import datalayer.customer.CustomerStorage;
import datalayer.customer.CustomerStorageImpl;
import datalayer.employee.EmployeeStorage;
import datalayer.employee.EmployeeStorageImpl;
import dto.CustomerCreation;
import dto.Employee;
import integration.ContainerizedDbIntegrationTest;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
class CreateCustomerTest extends ContainerizedDbIntegrationTest {
    private EmployeeStorage employeeStorage;

    /* changed code */

    @BeforeAll
    public void Setup() throws SQLException {
        runMigration(2);

        employeeStorage = new EmployeeStorageImpl(getConnectionString(), "root", getDbPassword());
        /*
        var numCustomers = employeeStorage.getEmployees().size();
        if (numCustomers < 100) {
            addFakeCustomers(100 - numCustomers);
        }
         */
    }

    private void addFakeCustomers(int numCustomers) throws SQLException {
        Faker faker = new Faker();
        for (int i = 0; i < numCustomers; i++) {
            Employee c = new Employee(faker.name().firstName(), faker.name().lastName(), Date.valueOf("2000-14-30"));
            employeeStorage.createEmployee(c);
        }

    }

    @Test
    public void createEmployeeTest() throws SQLException {
        // Arrange
        // Act
        employeeStorage.createEmployee(new Employee("John","Carlssonn", Date.valueOf("1997-07-14")));

        // Assert
        var employees = employeeStorage.getEmployees();
        assertTrue(
                employees.stream().anyMatch(x ->
                        x.getFirstName().equals("John") &&
                        x.getLastName().equals("Carlssonn") &&
                        x.getBirthdate().equals(Date.valueOf("1997-07-14"))));
    }

}
