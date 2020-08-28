package com.zzp.gps.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 车辆信息
 * @Author karyzeng
 * @since 2020.08.28
 **/
public class Vehicle {

    /**
     * id
     */
    private String id;

    /**
     * 车牌号码
     */
    private String vehicle;

    /**
     * 区域
     */
    private String placeName;

    /**
     * 道路
     */
    private String roadName;

    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 经度
     */
    private BigDecimal lon;

    /**
     * GPS定位时间
     */
    private Date gpsTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 来源系统
     */
    private String system;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public Date getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(Date gpsTime) {
        this.gpsTime = gpsTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }
}
