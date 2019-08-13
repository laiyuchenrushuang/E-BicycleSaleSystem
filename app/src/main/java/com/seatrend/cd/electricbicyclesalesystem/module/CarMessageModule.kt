package com.seatrend.cd.electricbicyclesalesystem.module

import com.seatrend.cd.electricbicyclesalesystem.common.BaseModule
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.http.HttpService
import com.seatrend.cd.electricbicyclesalesystem.persenter.CarLoginPersenter
import com.seatrend.cd.electricbicyclesalesystem.persenter.CarMessagePersenter
import com.seatrend.cd.electricbicyclesalesystem.persenter.LoginPersenter
import com.seatrend.cd.electricbicyclesalesystem.persenter.SettingPersenter

/**
 * Created by seatrend on 2018/12/27.
 */
class CarMessageModule:BaseModule{

    private var mCarMessagePersenter: CarMessagePersenter?=null

    constructor(mCarMessagePersenter: CarMessagePersenter?) : super() {
        this.mCarMessagePersenter = mCarMessagePersenter
    }


    override fun doWork(map: Map<String, String>?, url: String?) {
       HttpService.getInstance().getDataFromServer(map,url,Constants.GET,this)
    }

    override fun doWorkResults(commonResponse: CommonResponse?, isSuccess: Boolean) {
        mCarMessagePersenter!!.requestResults(commonResponse!!,isSuccess)
    }


}