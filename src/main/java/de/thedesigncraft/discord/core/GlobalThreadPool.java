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

    /**
     * @return the executorService
     */
    public static ExecutorService getExecutorService() {
        return executorService;
    }

    /**
     * Shutdowns the thread pool
     */
    public static void shutdown() {
        executorService.shutdown();
    }

    /**
     * Executes a task
     * @param task The task to execute
     */
    public static void execute(Runnable task) {
        executorService.execute(task);
    }

    /**
     * Executes a thread
     * @param thread The thread to execute
     */
    public static void execute(Thread thread) {
        executorService.execute(thread);
    }

    /**
     * Returns the status of the thread pool
     * @return The status of the thread pool
     */
    public static StatusType getStatus() {
        return threadPoolExecutor.isShutdown() ? StatusType.SHUTDOWN : threadPoolExecutor.isTerminated() ? StatusType.TERMINATED : StatusType.RUNNING;
    }

    /**
     * @return The pool size
     */
    public static int getPoolSize() {
        return threadPoolExecutor.getPoolSize();
    }

    /**
     * @return The number of threads that are currently active
     */
    public static int getActiveCount() {
        return threadPoolExecutor.getActiveCount();
    }

    /**
     * @return The largest number of threads that have ever simultaneously been in the pool
     */
    public static int getLargestPoolSize() {
        return threadPoolExecutor.getLargestPoolSize();
    }

    /**
     * @return The maximum allowed number of threads
     */
    public static int getMaximumPoolSize() {
        return threadPoolExecutor.getMaximumPoolSize();
    }

    /**
     * @return The size of the queue
     */
    public static int getQueueSize() {
        return threadPoolExecutor.getQueue().size();
    }

    /**
     * @return The number of tasks that have completed execution
     */
    public static int getCompletedTaskCount() {
        return (int) threadPoolExecutor.getCompletedTaskCount();
    }

    /**
     * @return The number of tasks that have been scheduled for one-time execution
     */
    public static int getTaskCount() {
        return (int) threadPoolExecutor.getTaskCount();
    }

    /**
     * @return The number of threads that are currently idle
     */
    public static int getCorePoolSize() {
        return threadPoolExecutor.getCorePoolSize();
    }

    /**
     * Returns the thread keep-alive time, which is the amount of time
     * that threads may remain idle before being terminated.
     * Threads that wait this amount of time without processing a
     * task will be terminated if there are more than the core
     * number of threads currently in the pool, or if this pool
     * {@linkplain ThreadPoolExecutor#allowsCoreThreadTimeOut() allows core thread timeout}.
     *
     * @return The time limit
     * @see ThreadPoolExecutor#setKeepAliveTime(long, TimeUnit)
     */
    public static int getKeepAliveTime() {
        return (int) threadPoolExecutor.getKeepAliveTime(TimeUnit.MINUTES);
    }

    /**
     * Returns the number of additional elements that this queue can ideally
     * (in the absence of memory or resource constraints) accept without
     * blocking, or {@code Integer.MAX_VALUE} if there is no intrinsic
     * limit.
     *
     * <p>Note that you <em>cannot</em> always tell if an attempt to insert
     * an element will succeed by inspecting {@code remainingCapacity}
     * because it may be the case that another thread is about to
     * insert or remove an element.
     *
     * @return the remaining capacity
     */
    public static int getQueueRemainingCapacity() {
        return threadPoolExecutor.getQueue().remainingCapacity();
    }

    /**
     * Returns the number of additional elements that this queue can ideally
     * (in the absence of memory or resource constraints) accept without
     * blocking, or {@code Integer.MAX_VALUE} if there is no intrinsic
     * limit.
     *
     * <p>Note that you <em>cannot</em> always tell if an attempt to insert
     * an element will succeed by inspecting {@code remainingCapacity}
     * because it may be the case that another thread is about to
     * insert or remove an element.
     *
     * @return the remaining capacity
     */
    public static int getQueueCapacity() {
        return threadPoolExecutor.getQueue().size() + threadPoolExecutor.getQueue().remainingCapacity();
    }

    /**
     * Returns the number of additional elements that this queue can ideally
     * (in the absence of memory or resource constraints) accept without
     * blocking, or {@code Integer.MAX_VALUE} if there is no intrinsic
     * limit.
     *
     * <p>Note that you <em>cannot</em> always tell if an attempt to insert
     * an element will succeed by inspecting {@code remainingCapacity}
     * because it may be the case that another thread is about to
     * insert or remove an element.
     *
     * @return the remaining capacity
     */
    public static int getQueueFreeCapacity() {
        return threadPoolExecutor.getQueue().remainingCapacity();
    }

    /**
     * Status types of the thread pool
     */
    public enum StatusType {
        RUNNING,
        SHUTDOWN,
        TERMINATED
    }

}
