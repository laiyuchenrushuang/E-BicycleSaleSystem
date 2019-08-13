package com.seatrend.cd.electricbicyclesalesystem.module

import com.seatrend.cd.electricbicyclesalesystem.common.BaseModule
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.http.HttpService
import com.seatrend.cd.electricbicyclesalesystem.persenter.YwxxPresenter

/**
 * Created by ly on 2019/8/8 18:57
 *
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
  class YwxxModule : BaseModule {
    private var mYwxxPresenter:YwxxPresenter?=null
    constructor(ywxxPresenter: YwxxPresenter){
        this.mYwxxPresenter = ywxxPresenter
    }
    override fun doWork(map: Map<String, String>?, url: String?) {
        HttpService.getInstance().getDataFromServer(map,url,Constants.GET,this)
    }

    override fun doWorkResults(commonResponse: CommonResponse?, isSuccess: Boolean) {
        mYwxxPresenter!!.requestResults(commonResponse!!,isSuccess)
    }

}