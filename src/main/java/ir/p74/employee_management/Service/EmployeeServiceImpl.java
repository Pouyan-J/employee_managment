package ir.p74.employee_management.Service;

import ir.p74.employee_management.Models.Employee;
import ir.p74.employee_management.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            Employee empToUpdate = existingEmployee.get();
            empToUpdate.setFirstName(employee.getFirstName());
            empToUpdate.setLastName(employee.getLastName());
            empToUpdate.setBirthYear(employee.getBirthYear());
            empToUpdate.setEmail(employee.getEmail());
            empToUpdate.setSalary(employee.getSalary());
            return employeeRepository.save(empToUpdate);
        } else {
            throw new RuntimeException("Employee with id " + id + " not found");
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getEmployeesBySalaryRange(BigDecimal minSalary, BigDecimal maxSalary) {
        return employeeRepository.findEmployeesBySalaryRange(minSalary, maxSalary);
    }

    @Override
    public List<Employee> getEmployeesByName(String firstName, String lastName) {
        return employeeRepository.findEmployeesByName(firstName, lastName);
    }
}
