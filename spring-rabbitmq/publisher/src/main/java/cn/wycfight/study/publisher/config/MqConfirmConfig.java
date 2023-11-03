package cn.wycfight.study.publisher.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author by achun
 * @Classname MqConfirmConfig
 * @Description TODO
 * @Date 2023/11/1 13:54
 */
@Slf4j
@Configuration
public class MqConfirmConfig implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            // 打印消息
            log.debug("收到的return callback, message :{} , exchange :{}, replyCode :{}, replyText :{}, routingKey :{}",
                    returnedMessage.getMessage(), returnedMessage.getExchange(),
                    returnedMessage.getReplyCode(), returnedMessage.getReplyText(), returnedMessage.getRoutingKey());
        });
    }
}
