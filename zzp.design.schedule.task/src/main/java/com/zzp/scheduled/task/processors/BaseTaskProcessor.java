package com.zzp.scheduled.task.processors;

import com.zzp.scheduled.task.listeners.TaskListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 定时任务Processor基类
 * @Author karyzeng
 * @since 2020.09.04
 **/
public abstract class BaseTaskProcessor {

    private List<TaskListener> listeners;

    public BaseTaskProcessor() {
        listeners = new ArrayList<TaskListener>();
    }

    public void addListener(TaskListener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    public abstract void beforeRun();

    public abstract void afterRun();

    public abstract void start();

    public void run() {
        this.beforeRun();

        for (TaskListener listener : listeners) {
            listener.handle();
        }

        this.afterRun();
    }

}
