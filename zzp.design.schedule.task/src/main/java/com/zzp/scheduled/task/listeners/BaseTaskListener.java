package com.zzp.scheduled.task.listeners;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @Description 定时子任务基类
 * @Author Garyzeng
 * @since 2020.09.03
 **/
public abstract class BaseTaskListener<T> implements TaskListener<T>{

    @Override
    public void save(List<T> list) {
        this.internalSave(list);
    }

    @Override
    public List<T> count() {
        return this.internalCount();
    }

    public void countAndSave() {
        this.save(this.count());
    }

    public abstract void internalSave(List<T> list);

    public abstract List<T> internalCount();
}
