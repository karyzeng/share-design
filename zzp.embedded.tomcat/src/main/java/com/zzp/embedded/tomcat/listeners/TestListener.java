package com.zzp.embedded.tomcat.listeners;

import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;

/**
 * @Description 测试监听器
 * @Author karyzeng
 * @since 2020.08.07
 **/
public class TestListener implements LifecycleListener {

    @Override
    public void lifecycleEvent(LifecycleEvent event) {
        System.out.println("进入事件监听器");
    }
}
