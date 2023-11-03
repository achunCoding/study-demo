package cn.wycfight.study.publisher.entity;

import lombok.Data;

import java.util.List;

/**
 * @author by achun
 * @Classname MultiDelayMessage
 * @Description 多次发送消息 实体类
 * @Date 2023/11/3 11:25
 */
@Data
public class MultiDelayMessage<T> {

    /**
     * 消息体
     */
    private T data;
    /**
     * 记录延迟消息时间的集合
     */
    private List<Integer> delayMillis;

    // 构造参数
    public MultiDelayMessage(T data, List<Integer> delayMillis) {
        this.data = data;
        this.delayMillis = delayMillis;
    }

    // 创建of方法 包含data 和 delayMillis
    public static <T> MultiDelayMessage<T> of(T data, List<Integer> delayMillis) {
        return new MultiDelayMessage<>(data, delayMillis);
    }

    // 获取并移除第一个延迟时间
    public Integer getDelayTime() {
        return delayMillis.remove(0);
    }

    // 判断是否还有延迟时间
    public boolean hasDelayTime() {
        return !delayMillis.isEmpty();
    }
}
