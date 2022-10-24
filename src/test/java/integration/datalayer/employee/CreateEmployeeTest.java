package integration.datalayer.employee;

import com.github.javafaker.Faker;
import datalayer.employee.EmployeeStorage;
import datalayer.employee.EmployeeStorageImpl;
import dto.Employee;
import dto.EmployeeCreation;
import integration.ContainerizedDbIntegrationTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
class CreateEmployeeTest extends ContainerizedDbIntegrationTest {
    private EmployeeStorage employeeStorage;

    /* changed code */

    @BeforeAll
    public void Setup() throws SQLException {
        runMigration(2);

        employeeStorage = new EmployeeStorageImpl(getConnectionString(), "root", getDbPassword());

        var numCustomers = employeeStorage.getEmployees().size();
        if (numCustomers < 100) {
            addFakeCustomers(100 - numCustomers);
        }

    }

    private void addFakeCustomers(int numCustomers) throws SQLException {
        Faker faker = new Faker();
        for (int i = 0; i < numCustomers; i++) {
            EmployeeCreation c = new EmployeeCreation(faker.name().firstName(), faker.name().lastName(), Date.valueOf("2000-12-30"));
            employeeStorage.createEmployee(c);
        }

    }

    @Test
    public void createEmployeeTest() throws SQLException {
        // Arrange
        // Act
        employeeStorage.createEmployee(new EmployeeCreation("John","Carlssonn", Date.valueOf("1997-07-14")));

        // Assert
        var employees = employeeStorage.getEmployees();
        assertTrue(
                employees.stream().anyMatch(x ->
                        x.getFirstName().equals("John") &&
                        x.getLastName().equals("Carlssonn") &&
                        x.getBirthdate().equals(Date.valueOf("1997-07-14"))));
    }

    @Test
    public void getEmployeeWithIdTest() throws SQLException {
        int id = employeeStorage.createEmployee(new EmployeeCreation("Carl", "Johnson", Date.valueOf("1997-07-14")));
        Employee e = employeeStorage.getEmployeeWithId(id);
        assertTrue(e.getFirstName().equals("Carl") && e.getLastName().equals("Johnson") && e.getBirthdate().equals(Date.valueOf("1997-07-14")));
    }

}
