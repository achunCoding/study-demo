package cn.wycfight.study.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmployeeDTO {

    private int employeeId;
    private String employeeName;

    private DivisionDTO division;

    private String employeeStartDt;
}
