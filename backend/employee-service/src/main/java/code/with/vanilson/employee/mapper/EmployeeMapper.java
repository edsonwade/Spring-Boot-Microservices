package code.with.vanilson.employee.mapper;

import code.with.vanilson.employee.dto.EmployeeDto;
import code.with.vanilson.employee.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper {

    private final ModelMapper modelMapper;

    public EmployeeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EmployeeDto mapToEmployeeDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public List<EmployeeDto> mapEmployeeListToDto(List<Employee> employees) {
        return employees.stream()
                .map(this::mapToEmployeeDto)
                .toList();
    }

    public Employee mapToEmployeeEntity(EmployeeDto employeeDto) {
        return modelMapper.map(employeeDto, Employee.class);
    }
}
