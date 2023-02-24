package cn.wycfight.study.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
public class CarDTO {
    private int id;
    private String name;
    private FuelType fuelType;
}
