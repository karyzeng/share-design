package com.zzp.gps.client;

import com.zzp.gps.domain.GpsApiConfig;
import com.zzp.gps.handler.GpsHandler;
import com.zzp.gps.vehicle.processor.BaseVehicleProcessor;
import com.zzp.gps.vehicle.processor.FenceVehicleProcessor;
import com.zzp.gps.vehicle.processor.NotFenceVehicleProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description gps client
 * @Author karyzeng
 * @since 2020.08.28
 **/
public class Client {

    public static void main(String[] args) {
        Client client = new Client();
        List<GpsApiConfig> gpsApiConfigs = client.listConfigs();
        ExecutorService executor = client.newGpsThreadPoolExecutor();

        for (int i = 0; i < gpsApiConfigs.size(); i++) {
            GpsApiConfig gpsApiConfig = gpsApiConfigs.get(i);
            GpsHandler handler = client.findGpsHandler(gpsApiConfig.getHandlerClass());

            // 测试，如果是偶数则使用有电子围栏的processor，奇数则使用无电子围栏的processor
            // 实际情况则需要根据具体业务来判断，以下只是测试
            BaseVehicleProcessor vehicleProcessor = null;
            if (i % 2 == 0) {
                vehicleProcessor = new FenceVehicleProcessor();
            } else {
                vehicleProcessor = new NotFenceVehicleProcessor();
            }
            vehicleProcessor.setHandler(handler);

            // 将vehicleProcess添加到线程池中
            executor.execute(vehicleProcessor);
        }

        // 关闭线程池
        executor.shutdown();

    }

    private List<GpsApiConfig> listConfigs(){
        List<GpsApiConfig> gpsApiConfigs = new ArrayList<GpsApiConfig>();
        GpsApiConfig ylConfig = new GpsApiConfig();
        ylConfig.setId(1);
        ylConfig.setSystem("yl");
        ylConfig.setUrl("http://www.yl.com/test/gps");
        ylConfig.setHandlerClass("com.zzp.gps.handler.YlGpsHandler");
        gpsApiConfigs.add(ylConfig);

        GpsApiConfig ytadConfig = new GpsApiConfig();
        ytadConfig.setId(2);
        ytadConfig.setSystem("ytad");
        ytadConfig.setUrl("http://www.ytad.com/test/gps");
        ytadConfig.setHandlerClass("com.zzp.gps.handler.YtadGpsHandler");
        gpsApiConfigs.add(ytadConfig);

        return gpsApiConfigs;
    }

    private ExecutorService newGpsThreadPoolExecutor() {
        int corePoolSize = 5;
        int maxPoolSize = 10;
        long keepAliveTime = 60L;
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(100);
        return new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, queue, new ThreadFactory() {
            // 自定义ThreadFactory，重新命名线程池名称以及线程池的线程名称
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "gps-thread-pool-" + r.hashCode());
                return thread;
            }
        });
    }

    private GpsHandler findGpsHandler(String handlerClass) {
        GpsHandler handler = null;
        try {
            Class<?> clazz = Class.forName(handlerClass);
            handler = (GpsHandler) clazz.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return handler;
    }

}
