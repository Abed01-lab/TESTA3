package servicelayer.employee;

import datalayer.employee.EmployeeStorage;
import dto.Employee;
import dto.EmployeeCreation;

import java.sql.Date;
import java.sql.SQLException;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeStorage employeeStorage;

    public EmployeeServiceImpl(EmployeeStorage employeeStorage) {
        this.employeeStorage = employeeStorage;
    }


    @Override
    public int createEmployee(String firstName, String lastName) throws EmployeeServiceException {
        try {
            return employeeStorage.createEmployee(new EmployeeCreation(firstName, lastName));
        } catch (SQLException e) {
            throw new EmployeeServiceException(e.getMessage());
        }
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws EmployeeServiceException {
        try {
            return employeeStorage.getEmployeeWithId(employeeId);
        } catch (SQLException e) {
            throw new EmployeeServiceException(e.getMessage());
        }
    }
}
