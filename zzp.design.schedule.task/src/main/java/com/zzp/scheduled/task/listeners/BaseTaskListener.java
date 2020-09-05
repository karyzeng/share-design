package com.zzp.scheduled.task.listeners;

import java.util.List;

/**
 * @Description 定时子任务基类
 * @Author karyzeng
 * @since 2020.09.03
 **/
public abstract class BaseTaskListener<T> implements TaskListener{

    @Override
    public void handle() {
        this.save(this.convert(this.count()));
    }

    public abstract List<T> count();

    public abstract List<T> convert(List<T> list);

    public abstract void save(List<T> list);
}
