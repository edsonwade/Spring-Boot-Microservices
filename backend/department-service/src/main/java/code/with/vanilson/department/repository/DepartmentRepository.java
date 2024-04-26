package code.with.vanilson.department.repository;

import code.with.vanilson.department.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("SELECT d FROM Department d WHERE d.departmentCode = :departmentCode")
    Department findByDepartmentCode(@Param("departmentCode") String departmentCode);

}
