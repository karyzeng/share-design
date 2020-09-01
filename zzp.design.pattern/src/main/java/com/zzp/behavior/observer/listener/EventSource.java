package com.zzp.behavior.observer.listener;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description 事件源
 * @Author Garyzeng
 * @since 2020.07.30
 **/
public class EventSource {

    private List<Listener> listeners;

    private ExecutorService executor;

    public EventSource() {
        listeners = new LinkedList<Listener>();
        executor = this.newThreadPoolExecutor();
    }

    public void addListener(Listener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public void fireEvent(Event e) {
        notify(e);
    }

    public void notify(Event e) {
        for (Listener listener : listeners) {
            // 使用线程池的方案来通知监听器，异步实现
            executor.execute(() -> {
                listener.eventHandler(e);
            });
        }
    }

    private ExecutorService newThreadPoolExecutor() {
        int corePoolSize = 5;
        int maxPoolSize = 10;
        long keepAliveTime = 60L;
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(100);
        return new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, queue, new ThreadFactory() {
            // 自定义ThreadFactory，重新命名线程池名称以及线程池的线程名称
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "thread-pool-" + r.hashCode());
                return thread;
            }
        });
    }

}
