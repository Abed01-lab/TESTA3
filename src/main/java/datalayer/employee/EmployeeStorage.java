package datalayer.employee;

import dto.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeStorage {
    public int createEmployee(Employee employee) throws SQLException;
    public List<Employee> getEmployees () throws SQLException;
    /*
    public Employee getEmployeeWithId (int employeeId) throws SQLException;*/

}
