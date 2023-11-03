package cn.wycfight.study.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author by achun
 * @Classname FanoutConfiguration
 * @Description TODO
 * @Date 2023/11/1 9:15
 */
@Configuration
public class FanoutConfiguration {

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("hello.fanout2");
    }

    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout.queue3");
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout.queue4");
    }

    @Bean
    public Binding bindingFanoutQueue1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingFanoutQueue2(Queue fanoutQueue2,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }

}
