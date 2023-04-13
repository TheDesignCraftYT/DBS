package de.thedesigncraft.discord.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GlobalThreadPool {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    private static final ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;

    private GlobalThreadPool() {
    }

    public static ExecutorService getExecutorService() {
        return executorService;
    }

    public static void shutdown() {
        executorService.shutdown();
    }

    public static void execute(Runnable task) {
        executorService.execute(task);
    }

    public static void execute(Thread thread) {
        executorService.execute(thread);
    }

    public static StatusType getStatus() {
        return threadPoolExecutor.isShutdown() ? StatusType.SHUTDOWN : threadPoolExecutor.isTerminated() ? StatusType.TERMINATED : StatusType.RUNNING;
    }

    public static int getPoolSize() {
        return threadPoolExecutor.getPoolSize();
    }

    public static int getActiveCount() {
        return threadPoolExecutor.getActiveCount();
    }

    public static int getLargestPoolSize() {
        return threadPoolExecutor.getLargestPoolSize();
    }

    public static int getMaximumPoolSize() {
        return threadPoolExecutor.getMaximumPoolSize();
    }

    public static int getQueueSize() {
        return threadPoolExecutor.getQueue().size();
    }

    public static int getCompletedTaskCount() {
        return (int) threadPoolExecutor.getCompletedTaskCount();
    }

    public static int getTaskCount() {
        return (int) threadPoolExecutor.getTaskCount();
    }

    public static int getCorePoolSize() {
        return threadPoolExecutor.getCorePoolSize();
    }

    public static int getKeepAliveTime() {
        return (int) threadPoolExecutor.getKeepAliveTime(TimeUnit.MINUTES);
    }

    public static int getQueueRemainingCapacity() {
        return threadPoolExecutor.getQueue().remainingCapacity();
    }

    public static int getQueueCapacity() {
        return threadPoolExecutor.getQueue().size() + threadPoolExecutor.getQueue().remainingCapacity();
    }

    public static int getQueueUsedCapacity() {
        return threadPoolExecutor.getQueue().size();
    }

    public static int getQueueFreeCapacity() {
        return threadPoolExecutor.getQueue().remainingCapacity();
    }

    public static int getQueueUsedPercentage() {
        return (int) (((double) threadPoolExecutor.getQueue().size() / (double) (threadPoolExecutor.getQueue().size() + threadPoolExecutor.getQueue().remainingCapacity())) * 100);
    }

    public static int getQueueFreePercentage() {
        return (int) (((double) threadPoolExecutor.getQueue().remainingCapacity() / (double) (threadPoolExecutor.getQueue().size() + threadPoolExecutor.getQueue().remainingCapacity())) * 100);
    }

    public static int getQueueUsedCapacityInMB() {
        return (int) (((double) threadPoolExecutor.getQueue().size() / (double) (threadPoolExecutor.getQueue().size() + threadPoolExecutor.getQueue().remainingCapacity())) * 100);
    }

    public static int getQueueFreeCapacityInMB() {
        return (int) (((double) threadPoolExecutor.getQueue().remainingCapacity() / (double) (threadPoolExecutor.getQueue().size() + threadPoolExecutor.getQueue().remainingCapacity())) * 100);
    }

    public enum StatusType {
        RUNNING,
        SHUTDOWN,
        TERMINATED
    }

}
