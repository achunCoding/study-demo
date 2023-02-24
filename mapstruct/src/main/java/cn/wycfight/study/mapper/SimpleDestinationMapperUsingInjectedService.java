package cn.wycfight.study.mapper;


import cn.wycfight.study.dto.SimpleSource;
import cn.wycfight.study.entity.SimpleDestination;
import cn.wycfight.study.service.SimpleService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 这种方式可以注入 bean 这个Bean不能设置为私有， 因为MapStruct必须访问生成实现类中的对象
 */
@Mapper(componentModel = "spring")
public abstract class SimpleDestinationMapperUsingInjectedService {

    @Autowired
    protected SimpleService simpleService;

    @Mapping(target = "name", expression = "java(simpleService.enrichName(source.getName()))")
    public abstract SimpleDestination sourceToDestination(SimpleSource source);

    public abstract SimpleSource destinationToSource(SimpleDestination destination);


}
