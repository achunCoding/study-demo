package cn.wycfight.study.mapper;

import cn.wycfight.study.dto.DivisionDTO;
import cn.wycfight.study.dto.EmployeeDTO;
import cn.wycfight.study.entity.Division;
import cn.wycfight.study.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "employeeId", source = "entity.id")
    @Mapping(target = "employeeName", source = "entity.name")
    @Mapping(target = "employeeStartDt", source = "entity.startDt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    EmployeeDTO employeeToEmployeeDTO(Employee entity);

    @Mapping(target = "id", source = "dto.employeeId")
    @Mapping(target = "name", source = "dto.employeeName")
    @Mapping(target = "startDt", source = "dto.employeeStartDt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    Employee employeeDTOtoEmployee(EmployeeDTO dto);

    /**
     * Employee员工属性中对象参数传递
     * @param entity
     * @return
     */
    DivisionDTO divisionToDivisionDTO(Division entity);

    Division divisionDTOtoDivision(DivisionDTO dto);


}
