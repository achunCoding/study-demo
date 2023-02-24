package cn.wycfight.study.mapper;

import static org.junit.Assert.assertEquals;

import cn.wycfight.study.dto.CarDTO;
import cn.wycfight.study.entity.Car;
import org.junit.Test;

public class CarMapperUnitTest {

    @Test
    public void givenCarEntitytoCar_whenMaps_thenCorrect() {
        
        Car entity  = new Car();
        entity.setId(1);
        entity.setName("Toyota");
        
        CarDTO carDto = CarMapper.INSTANCE.carToCarDTO(entity);

        assertEquals(carDto.getId(), entity.getId());
        assertEquals(carDto.getName(), entity.getName());
    }
}
