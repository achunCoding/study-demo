package cn.wycfight.study.mapper;

import cn.wycfight.study.dto.SimpleSource;
import cn.wycfight.study.entity.SimpleDestination;
import cn.wycfight.study.service.SimpleService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface SimpleSourceDestinationMapper {


    SimpleDestination sourceToDestination(SimpleSource simpleSource);

    SimpleSource destinationToSource(SimpleDestination simpleDestination);
}
