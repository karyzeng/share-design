package com.zzp.gps.handler;

import com.zzp.gps.domain.GpsApiConfig;
import com.zzp.gps.domain.Vehicle;

import java.util.List;

/**
 * @Description GPS信息处理handler基类
 * @Author karyzeng
 * @since 2020.08.28
 **/
public abstract class BaseGpsHandler implements GpsHandler{

    public List<Vehicle> listGpsInfos(String vehicles, GpsApiConfig config) {
        return this.internalListGpsInfos(vehicles, config);
    }

    public abstract List<Vehicle> internalListGpsInfos(String vehicles, GpsApiConfig config);
}
