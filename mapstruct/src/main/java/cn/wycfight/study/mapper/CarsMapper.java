package cn.wycfight.study.mapper;

import cn.wycfight.study.dto.CarDTO;
import cn.wycfight.study.dto.FuelType;
import cn.wycfight.study.entity.BioDieselCar;
import cn.wycfight.study.entity.Car;
import cn.wycfight.study.entity.ElectricCar;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class CarsMapper {
    /**
     * 映射之前进行操作
     * @param car
     * @param carDto
     */
    @BeforeMapping
    protected void enrichDTOWithFuelType(Car car, @MappingTarget CarDTO carDto) {
        if (car instanceof ElectricCar)
            carDto.setFuelType(FuelType.ELECTRIC);
        if (car instanceof BioDieselCar)
            carDto.setFuelType(FuelType.BIO_DIESEL);
    }

    /**
     * 映射之后进行操作
     * @param carDto
     */
    @AfterMapping
    protected void convertNameToUpperCase(@MappingTarget CarDTO carDto) {
        carDto.setName(carDto.getName().toUpperCase());
    }

    public abstract CarDTO toCarDto(Car car);

}
