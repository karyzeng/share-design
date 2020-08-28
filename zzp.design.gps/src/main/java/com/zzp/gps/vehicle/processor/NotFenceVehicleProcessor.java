package com.zzp.gps.vehicle.processor;

/**
 * @Description 无电子围栏的车辆Processor
 * @Author karyzeng
 * @since 2020.08.28
 **/
public class NotFenceVehicleProcessor extends BaseVehicleProcessor{

    @Override
    public void doRun() {
        this.getHandler().listGpsInfos(null, null);
        System.out.println("NotFenceVehicleProcessor -> doRun");
    }
}
