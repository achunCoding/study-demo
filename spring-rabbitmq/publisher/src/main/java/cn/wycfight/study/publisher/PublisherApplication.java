package cn.wycfight.study.publisher;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author by achun
 * @Classname PublisherApplication
 * @Description TODO
 * @Date 2023/10/31 9:39
 */
@SpringBootApplication
public class PublisherApplication {

    public static void main(String[] args) {
        // 这段代码加上try catch 并打印异常信息，可以看到异常信息
        SpringApplication.run(PublisherApplication.class, args);
    }

    @Bean
    public MessageConverter jacksonMessageConverter() {
        Jackson2JsonMessageConverter jjmc = new Jackson2JsonMessageConverter();
        jjmc.setCreateMessageIds(true);
        return jjmc;
    }
}
