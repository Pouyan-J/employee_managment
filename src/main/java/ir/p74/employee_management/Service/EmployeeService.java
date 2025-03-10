package ir.p74.employee_management.Service;

import ir.p74.employee_management.Models.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee addEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
    List<Employee> getEmployeesBySalaryRange(BigDecimal minSalary, BigDecimal maxSalary);
    List<Employee> getEmployeesByName(String firstName, String lastName);
}