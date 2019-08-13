package com.seatrend.cd.electricbicyclesalesystem.module

import com.seatrend.cd.electricbicyclesalesystem.common.BaseModule
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.http.HttpService
import com.seatrend.cd.electricbicyclesalesystem.persenter.CarLoginPersenter
import com.seatrend.cd.electricbicyclesalesystem.persenter.LoginPersenter
import com.seatrend.cd.electricbicyclesalesystem.persenter.SettingPersenter

/**
 * Created by seatrend on 2018/12/27.
 */
class CarLoginModule:BaseModule{

    private var mCarLoginPersenter: CarLoginPersenter?=null

    constructor(mCarLoginPersenter: CarLoginPersenter?) : super() {
        this.mCarLoginPersenter = mCarLoginPersenter
    }


    override fun doWork(map: Map<String, String>?, url: String?) {
       HttpService.getInstance().getDataFromServer(map,url,Constants.GET,this)
    }

    override fun doWorkResults(commonResponse: CommonResponse?, isSuccess: Boolean) {
        mCarLoginPersenter!!.requestResults(commonResponse!!,isSuccess)
    }


}