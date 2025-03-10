package ir.p74.employee_management.Repositories;

import ir.p74.employee_management.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE " +
            "(:minSalary IS NULL OR e.salary >= :minSalary) AND " +
            "(:maxSalary IS NULL OR e.salary <= :maxSalary)")
    List<Employee> findEmployeesBySalaryRange(@Param("minSalary") BigDecimal minSalary,
                                     @Param("maxSalary") BigDecimal maxSalary);


    @Query("SELECT e FROM Employee e WHERE " +
            "(:firstName IS NULL OR e.firstName = :firstName) AND " +
            "(:lastName IS NULL OR e.lastName = :lastName)")
    List<Employee> findEmployeesByName(@Param("firstName") String firstName,
                               @Param("lastName") String lastName);
}
