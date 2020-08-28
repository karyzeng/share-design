package com.zzp.gps.handler;

import com.zzp.gps.domain.GpsApiConfig;
import com.zzp.gps.domain.Vehicle;

import java.util.List;

/**
 * @Description YL GPS信息处理handler
 * @Author karyzeng
 * @since 2020.08.28
 **/
public class YlGpsHandler extends BaseGpsHandler{

    @Override
    public List<Vehicle> internalListGpsInfos(String vehicles, GpsApiConfig config) {
        System.out.println("YlGpsHandler -> internalListGpsInfos");
        return null;
    }
}
