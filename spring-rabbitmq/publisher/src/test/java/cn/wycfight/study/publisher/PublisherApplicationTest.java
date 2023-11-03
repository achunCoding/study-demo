package cn.wycfight.study.publisher;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author by achun
 * @Classname PublisherApplicationTest
 * @Description TODO
 * @Date 2023/10/31 9:48
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class PublisherApplicationTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    public void publisher() {
        String queueName = "simple.queue";
        String msg = "hello, amqp!";
        rabbitTemplate.convertAndSend(queueName, msg);
    }


    @Test
    public void publisherMoreMessage() {
        String queueName = "work.queue";
        for (int i = 0; i < 50; i++) {
            String msg = "hello work queue message_" + i;
            rabbitTemplate.convertAndSend(queueName, msg);
        }
    }


    @Test
    public void publisherFanoutMessage() {
        String fanoutName = "hello.fanout";
        rabbitTemplate.convertAndSend(fanoutName, "", "hello fanout message");
    }


    @Test
    public void publisherDirectMessage() {
        String directName = "hello.direct";
        rabbitTemplate.convertAndSend(directName, "yellow", "hello red message");
    }

    @Test
    public void publisherTopicMessage() {
        String directName = "hello.topic";
        rabbitTemplate.convertAndSend(directName, "china.weather", "hello this is china news message");
    }


    @Test
    public void publisherObjectMessage() {
        String queueName = "object.queue";
        Map<String, Object> map = new HashMap<>(2);
        map.put("chun","chun");
        rabbitTemplate.convertAndSend(queueName, map);
    }


    @Test
    public void testConfirmCallback() throws InterruptedException {
        CorrelationData cd = new CorrelationData(UUID.randomUUID().toString());
        cd.getFuture().addCallback(new ListenableFutureCallback<CorrelationData.Confirm>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("消息发送失败，原因：", throwable);
            }

            @Override
            public void onSuccess(CorrelationData.Confirm confirm) {
                log.info("消息发送成功，correlationData：{}", confirm);
                if (confirm.isAck()) {
                    log.info("消息发送成功，correlationData：{}", confirm);
                } else {
                    log.error("消息发送失败，原因：{}", confirm);
                }
            }
        });
        String queueName = "hello.direct";
        String msg = "hello confirm callback";
        rabbitTemplate.convertAndSend(queueName, "abc", msg, cd);
        Thread.sleep(2000);
    }


    @Test
    public void publisherDelayMessage() {
        String exchangeName = "delay.direct";
        rabbitTemplate.convertAndSend(exchangeName, "delay", "hello.delay", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setDelay(10000);
                return message;
            }
        });
    }
}
