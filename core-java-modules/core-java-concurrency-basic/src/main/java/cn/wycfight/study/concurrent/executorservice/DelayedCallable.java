package cn.wycfight.study.concurrent.executorservice;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class DelayedCallable implements Callable<String> {
    /**
     * 姓名
     */
    private String name;
    /**
     * 睡眠时间
     */
    private long period;
    private CountDownLatch latch;

    public DelayedCallable() {
    }

    public DelayedCallable(String name, long period, CountDownLatch latch) {
        this.name = name;
        this.period = period;
        this.latch = latch;
    }

    public DelayedCallable(String name, long period) {
        this.name = name;
        this.period = period;
    }

    @Override
    public String call() {
        try {
            Thread.sleep(period);
            if (latch != null) {
                latch.countDown();
            }
        } catch (InterruptedException e) {
            // handle exception
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return name;
    }
}
