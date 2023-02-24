package cn.wycfight.study.mapper;

import static org.junit.Assert.assertEquals;

import cn.wycfight.study.dto.CarDTO;
import cn.wycfight.study.dto.FuelType;
import cn.wycfight.study.entity.BioDieselCar;
import cn.wycfight.study.entity.Car;
import cn.wycfight.study.entity.ElectricCar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CarsMapperUnitTest {

    @Autowired
    private CarsMapper carsMapper;

    @Test
    public void testGivenSubTypeElectric_mapsModifiedFieldsToSuperTypeDto_whenBeforeAndAfterMappingMethodscarCalled() {
        Car car = new ElectricCar();
        car.setId(12);
        car.setName("Tesla_Model_C");

        CarDTO carDto = carsMapper.toCarDto(car);

        assertEquals("TESLA_MODEL_C", carDto.getName());
        assertEquals(FuelType.ELECTRIC, carDto.getFuelType());
    }

    @Test
    public void testGivenSubTypeBioDiesel_mapsModifiedFieldsToSuperTypeDto_whenBeforeAndAfterMappingMethodscarCalled() {
        Car car = new BioDieselCar();
        car.setId(11);
        car.setName("Tesla_Model_X");

        CarDTO carDto = carsMapper.toCarDto(car);

        assertEquals("TESLA_MODEL_X", carDto.getName());
        assertEquals(FuelType.BIO_DIESEL, carDto.getFuelType());
    }

}
