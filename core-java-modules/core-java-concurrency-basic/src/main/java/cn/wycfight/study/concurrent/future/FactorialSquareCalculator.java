package cn.wycfight.study.concurrent.future;

import java.util.concurrent.RecursiveTask;

public class FactorialSquareCalculator extends RecursiveTask<Integer> {

    private Integer n;


    public FactorialSquareCalculator(Integer n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }
        FactorialSquareCalculator factorialSquareCalculator = new FactorialSquareCalculator(n - 1);
        // fork 是启动新线程进行执行
        factorialSquareCalculator.fork();
        // join是将子任务结算结果进行汇总
        return n * n + factorialSquareCalculator.join();
    }
}
