package com.seatrend.cd.electricbicyclesalesystem.common;

import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse;

import java.util.Map;

/**
 * Created by seatrend on 2018/8/20.
 */

public abstract class BaseModule {

    public abstract void doWork(Map<String,String> map ,String url);
    public abstract void doWorkResults(CommonResponse commonResponse, boolean isSuccess);

}
