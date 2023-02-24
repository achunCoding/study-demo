package cn.wycfight.study.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import cn.wycfight.study.dto.PersonDTO;
import cn.wycfight.study.entity.Person;
import org.junit.Test;

public class PersonMapperUnitTest {
    /**
     * defaultExpression  默认为null 自动填充值
     */
    @Test
    public void givenPersonEntitytoPersonWithExpression_whenMaps_thenCorrect() {
        
        Person entity  = new Person();
        entity.setName("Micheal");
        
        PersonDTO personDto = PersonMapper.INSTANCE.personToPersonDTO(entity);

        assertNull(entity.getId());
        assertNotNull(personDto.getId());
        assertEquals(personDto.getName(), entity.getName());
    }
}