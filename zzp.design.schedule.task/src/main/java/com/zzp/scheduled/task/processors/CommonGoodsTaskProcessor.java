package com.zzp.scheduled.task.processors;

import com.zzp.scheduled.task.listeners.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Description 公共商品库定时任务
 * @Author karyzeng
 * @since 2020.09.04
 **/
@Component
@PropertySource(value={"classpath:jobs.properties"})
public class CommonGoodsTaskProcessor extends BaseTaskProcessor{

    /**
     * 使用@Qualifier注解可以指定注入bean的名称
     */
    @Autowired
    @Qualifier("commonGoodsAAdapter")
    private TaskListener aAdapter;

    @Autowired
    @Qualifier("commonGoodsBAdapter")
    private TaskListener bAdapter;

    @Value("${jobs.schedule.commonGoods.enable}")
    private Boolean enable = true;

    @Override
    public void beforeRun() {
        System.out.println("公共商品库定时任务开始");
    }

    @Override
    public void afterRun() {
        System.out.println("公共商品库定时任务结束");
    }

    @Override
    @Scheduled(cron = "${jobs.schedule.commonGoods}")
    public void start() {
        if (!enable) {
            System.out.println("公共商品库定时任务未开启");
            return;
        }

        this.run();
    }

    /**
     * bean初始化完之后调用的方法
     */
    @PostConstruct
    public void initMethod() {
        this.addListener(aAdapter);
        this.addListener(bAdapter);
    }

    /**
     * bean销毁之前调用的方法
     */
    @PreDestroy
    public void destoryMethod() {
        System.out.println("公共商品库定时任务销毁了");
    }
}
