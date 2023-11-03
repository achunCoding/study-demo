package cn.wycfight.study.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author by achun
 * @Classname MqListener
 * @Description Mq消息监听
 * @Date 2023/10/31 10:48
 */
@Component
@Slf4j
public class MqListener {

    // 接受队列simple.queue的消息，并打印消息
    @RabbitListener(queues = "simple.queue")
    public void simpleQueueListener(String msg) {
        log.info("simple.queue接受到的消息是：{}", msg);
    }


    @RabbitListener(queues = "work.queue")
    public void workQueueListener1(String msg) {
        log.info("work.queue 消费者1接受到的消息是：{}", msg);
    }


    @RabbitListener(queues = "work.queue")
    public void workQueueListener2(String msg) {
        log.error("work.queue 消费者2接受到的消息是.....：{}", msg);
    }



    @RabbitListener(queues = "fanout.queue1")
    public void fanoutQueueListener1(String msg) {
        log.error("fanout.queue1 消费者1接受到的消息是.....：{}", msg);
    }


    @RabbitListener(queues = "fanout.queue2")
    public void fanoutQueueListener2(String msg) {
        log.error("fanout.queue2 消费者2接受到的消息是.....：{}", msg);
    }



    @RabbitListener(queues = "direct.queue1")
    public void directQueueListener1(String msg) {
        log.info("direct.queue1 消费者1接受到的消息是.....：{}", msg);
    }


    @RabbitListener(queues = "direct.queue2")
    public void directQueueListener2(String msg) {
        log.info("direct.queue2 消费者2接受到的消息是.....：{}", msg);
    }


    @RabbitListener(queues = "topic.queue1")
    public void topicQueueListener1(String msg) {
        log.info("topic.queue1 消费者1接受到的消息是.....：{}", msg);
    }


    @RabbitListener(queues = "topic.queue2")
    public void topicQueueListener2(String msg) {
        log.info("topic.queue2 消费者2接受到的消息是.....：{}", msg);
    }


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "direct.queue3", durable = "true"),
                    exchange = @Exchange(value = "hello.direct1", type = ExchangeTypes.DIRECT),
                    key = {"red", "blue"}
            )
    })
    public void ListenerBindingQueue3(String msg) {
        log.info("topic.queue2 消费者2接受到的消息是.....：{}", msg);
    }


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "direct.queue4", durable = "true"),
                    exchange = @Exchange(value = "hello.direct1", type = ExchangeTypes.DIRECT),
                    key = {"red", "yellow"}
            )
    })
    public void ListenerBindingQueue4(String msg) {
        log.info("topic.queue2 消费者2接受到的消息是.....：{}", msg);
    }


    @RabbitListener(queues = "object.queue")
    public void ListenerObjectQueue(Map<String, Object> msg) {
        log.info("topic.queue2 消费者2接受到的消息是.....：{}", msg);
    }



    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "delay.queue", durable = "true"),
    exchange = @Exchange(value = "delay.direct", delayed = "true"), key = "delay"))
    public void listenerDelayQueue(String msg) {
        log.info("接收到delay.queue的消息:{}", msg);
    }
}
