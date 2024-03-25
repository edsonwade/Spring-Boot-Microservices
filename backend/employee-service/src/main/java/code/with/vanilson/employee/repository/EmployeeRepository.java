package code.with.vanilson.employee.repository;

import code.with.vanilson.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.email = :email")
    Employee findEmployeeByEmail(String email);

    @Query("SELECT e FROM Employee e WHERE e.departmentCode = :departmentCode")
    List<Employee> findByDepartmentCode(String departmentCode);

    boolean existsByEmail(String email);
}
