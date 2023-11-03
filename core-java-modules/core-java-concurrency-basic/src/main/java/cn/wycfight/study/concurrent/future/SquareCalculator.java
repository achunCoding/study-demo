package cn.wycfight.study.concurrent.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class SquareCalculator {

    public SquareCalculator() {
    }

    public SquareCalculator(ExecutorService executor) {
        this.executor = executor;
    }

    private ExecutorService executor;

    public Future<Integer> calculate(Integer input) {
        return executor.submit(()-> {
            try {
                Thread.sleep(1000L);
                return input * input;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public ExecutorService getExecutor() {
        return executor;
    }
}
