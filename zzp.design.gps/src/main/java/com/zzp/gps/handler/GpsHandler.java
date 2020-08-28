package com.zzp.gps.handler;

import com.zzp.gps.domain.GpsApiConfig;
import com.zzp.gps.domain.Vehicle;

import java.util.List;

/**
 * @Description GPS信息处理handler
 * @author zzp
 * @since 2020.08.28
 */
public interface GpsHandler {

    /**
     * 根据车牌信息和GPS API配置查询车辆GPS信息
     * @param vehicles 车牌信息，格式如“粤AD00001,粤HF00002”
     * @param config
     * @return
     */
    List<Vehicle> listGpsInfos(String vehicles, GpsApiConfig config);

}
