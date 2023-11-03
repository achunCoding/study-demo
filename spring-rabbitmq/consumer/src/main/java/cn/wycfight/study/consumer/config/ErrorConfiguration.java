package cn.wycfight.study.consumer.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author by achun
 * @Classname ErrorConfiguration
 * @Description TODO
 * @Date 2023/11/2 10:43
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.rabbitmq.listener.simple.retry", name = "enabled", havingValue = "true")
public class ErrorConfiguration {

    @Bean
    public DirectExchange errorDirectChange() {
        return new DirectExchange("error.direct");
    }


    @Bean
    public Queue errorQueue1() {
        return new Queue("error.queue");
    }
    @Bean
    public Binding bindingFanoutQueue1() {
        return BindingBuilder.bind(errorQueue1()).to(errorDirectChange()).with("error");
    }

    @Bean
    public MessageRecoverer messageRecoverer(RabbitTemplate rabbitTemplate) {
        return new RepublishMessageRecoverer(rabbitTemplate, "error.direct", "error");
    }
}
