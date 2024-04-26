package code.with.vanilson.employee.api.client;

import code.with.vanilson.employee.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {
    // Build get department rest api
    @GetMapping("/api/departments/code/{department-code}")
    DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);
}
