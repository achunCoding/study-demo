package cn.wycfight.study.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Employee {

    private int id;
    private String name;
    private Division division;

    private Date startDt;
}
