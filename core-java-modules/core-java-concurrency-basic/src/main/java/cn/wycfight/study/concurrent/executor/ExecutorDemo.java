package cn.wycfight.study.concurrent.executor;

public class ExecutorDemo {
    /**
     * 如果执行器不能接受任务执行，则抛出相应异常 RejectedExecutionException
     */
    public void execute() {
        Invoker invoker = new Invoker();
        invoker.execute(() -> {
            // task to be performed
        });
    }
}
