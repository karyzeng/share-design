package com.zzp.scheduled.task.listeners;

import java.util.List;

/**
 * @Description 定时子任务基类
 * @Author karyzeng
 * @since 2020.09.03
 **/
public abstract class BaseTaskListener<T> implements TaskListener{

    @Override
    public void countAndSave() {
        this.save(this.count());
    }

    public abstract void save(List<T> list);

    public abstract List<T> count();
}
