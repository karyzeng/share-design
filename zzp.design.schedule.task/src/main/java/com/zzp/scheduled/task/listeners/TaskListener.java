package com.zzp.scheduled.task.listeners;

import java.util.List;

/**
 * @Description 定时子任务抽象接口
 * @Author Garyzeng
 * @since 2020.07.17
 **/
public interface TaskListener<T> {

    void save(List<T> list);

    List<T> count();

}
