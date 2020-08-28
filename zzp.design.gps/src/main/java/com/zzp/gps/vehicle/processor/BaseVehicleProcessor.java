package com.zzp.gps.vehicle.processor;

import com.zzp.gps.handler.GpsHandler;

/**
 * @Description 车辆信息Processor基类
 * @Author karyzeng
 * @since 2020.08.28
 **/
public abstract class BaseVehicleProcessor implements Runnable{

    private GpsHandler handler;

    public void run() {
        this.doRun();
    }

    public abstract void doRun();

    public GpsHandler getHandler() {
        return handler;
    }

    public void setHandler(GpsHandler handler) {
        this.handler = handler;
    }
}
