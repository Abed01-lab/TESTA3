package servicelayer.employee;

import dto.Employee;

import java.sql.Date;

public interface EmployeeService {
    public int createEmployee(String firstName, String lastName, Date date) throws EmployeeServiceException;
    public Employee getEmployeeById(int employeeId) throws EmployeeServiceException;
}
