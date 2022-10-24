package unit.servicelayer.employee;

import datalayer.employee.EmployeeStorage;
import org.junit.jupiter.api.*;
import servicelayer.employee.EmployeeService;
import servicelayer.employee.EmployeeServiceException;
import servicelayer.employee.EmployeeServiceImpl;

import java.sql.Date;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class CreateEmployeeTest {
    private EmployeeService employeeService;
    private EmployeeStorage storageMock;

    @BeforeAll
    public void beforeAll(){
        storageMock = mock(EmployeeStorage.class);
        employeeService = new EmployeeServiceImpl(storageMock);
    }


    @Test
    public void mustCallStorageWhenCreatingEmployee () throws EmployeeServiceException, SQLException {
        var firstName = "a";
        var lastName = "b";
        var birthdate = Date.valueOf("1997-07-14");

        employeeService.createEmployee(firstName, lastName, birthdate);

        verify(storageMock)
                .createEmployee(
                        argThat(x ->
                                x.firstName.equals(firstName) &&
                                x.lastName.equals(lastName) &&
                                x.birthdate.equals(birthdate))
                );
    }

    @Test
    public void mustCallStorageWhenGettingEmployeeWithId() throws EmployeeServiceException, SQLException {
        var id = 23;
        employeeService.getEmployeeById(id);
        verify(storageMock).getEmployeeWithId(anyInt());

    }
}
