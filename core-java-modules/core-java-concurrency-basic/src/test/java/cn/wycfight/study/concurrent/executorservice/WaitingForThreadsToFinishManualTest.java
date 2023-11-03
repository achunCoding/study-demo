package cn.wycfight.study.concurrent.executorservice;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WaitingForThreadsToFinishManualTest {


    private static final Logger logger = LoggerFactory.getLogger(WaitingForThreadsToFinishManualTest.class);


    private static final ExecutorService executor = Executors.newFixedThreadPool(10);


    /**
     * 尝试终止线程池里任务  shutdown 终止线程 但不影响已经正在执行的任务  shutdownNow 尝试终止 包括正在执行的任务
     * awaitTermination 也可以设置超时时间 检测线程池是否已经关闭    interrupted  测试当前线程已经关闭
     */
    public void awaitTerminationAfterShutDown(ExecutorService executor) {
        executor.shutdown();
        try {
            // 如果60秒内没有关闭  则尝试立即停止所有正在运行的线程
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        logger.info("awaitTerminationAfterShutDown success!");
    }

    /**
     * 使用countDownLatch方法
     */
    @Test
    public void givenMultipleThreads_whenUsingCountDownLatch_thenMainShoudWaitForAllToFinish() {
        long startTime = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        for (int i = 0; i < 2; i++) {
            // 这里启动多线程
            executor.submit(() -> {
                try {
                    Thread.sleep(1000L);
                    // 这个表示递减
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            });
        }
        try {
            // await 表示阻塞在这里
            countDownLatch.await();
            long processTime = System.currentTimeMillis() - startTime;
            // 这里肯定会返回true  因为必定是 >= 1000 如果改成小于1000 则返回false
            assertTrue(processTime <= 1100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        awaitTerminationAfterShutDown(executor);
    }

    /**
     * 使用ExecutorService中 invokeAll 方法
     */
    @Test
    public void givenMultipleThreads_whenInvokeAll_thenMainThreadShouldWaitForAllToFinish() {
        List<DelayedCallable> delayedCallables = Arrays.asList(new DelayedCallable("fast thread", 100),
                new DelayedCallable("slow thread", 3000));
        try {
            long startTime = System.currentTimeMillis();
            List<Future<String>> futures = executor.invokeAll(delayedCallables);
            awaitTerminationAfterShutDown(executor);
            try {
                executor.submit((Callable<String>)() -> {
                    Thread.sleep(1000000L);
                    return null;
                });
            } catch (RejectedExecutionException ex) {
                // 再次开启被拒绝
                ex.printStackTrace();
            }
            long totalProcessingTime = System.currentTimeMillis() - startTime;
            assertTrue(totalProcessingTime <= 3000);
            String firstThreadResponse = futures.get(0)
                    .get();
            assertTrue("First response should be from the fast thread", "fast thread".equals(firstThreadResponse));

            String secondThreadResponse = futures.get(1)
                    .get();
            assertTrue("Last response should be from the slow thread", "slow thread".equals(secondThreadResponse));


        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void givenMultipleThreads_whenUsingCompletionService_thenMainThreadShouldWaitForAllToFinish() {
        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);
        List<DelayedCallable> delayedCallables = Arrays.asList(new DelayedCallable("fast thread", 100),
                new DelayedCallable("slow thread", 3000));
        for (DelayedCallable delayedCallable : delayedCallables) {
            completionService.submit(delayedCallable);
        }
        try {

            long startProcessingTime = System.currentTimeMillis();
            // take方法 从内部阻塞队列中获取并移除第一个执行完成的任务， 如果没有该任务则阻塞
            // poll方法 从内部阻塞队列中获取并移除第一个执行完成的任务， 获取不到则返回null， 不阻塞
            Future<String> future = completionService.take();
            String firstThreadResponse = future.get();
            long totalProcessingTime = System.currentTimeMillis() - startProcessingTime;

            assertEquals("First response should be from the fast thread", "fast thread", firstThreadResponse);
            assertTrue(totalProcessingTime >= 100 && totalProcessingTime < 1000);
            logger.debug("Thread finished after: " + totalProcessingTime + " milliseconds");

            future = completionService.take();
            // get 会阻塞住
            String secondThreadResponse = future.get();
            totalProcessingTime = System.currentTimeMillis() - startProcessingTime;

            assertEquals("Last response should be from the slow thread", "slow thread", secondThreadResponse);
            assertTrue(totalProcessingTime >= 3000 && totalProcessingTime < 4000);
            logger.debug("Thread finished after: " + totalProcessingTime + " milliseconds");

        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            awaitTerminationAfterShutDown(executor);
        }


    }





}
