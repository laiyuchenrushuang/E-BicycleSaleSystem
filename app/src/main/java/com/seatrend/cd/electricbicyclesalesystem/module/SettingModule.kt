package com.seatrend.cd.electricbicyclesalesystem.module

import com.seatrend.cd.electricbicyclesalesystem.common.BaseModule
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.http.HttpService
import com.seatrend.cd.electricbicyclesalesystem.persenter.SettingPersenter

/**
 * Created by seatrend on 2018/12/27.
 */
class SettingModule:BaseModule{

    private var mSettingPersenter: SettingPersenter?=null

    constructor(mSettingPersenter: SettingPersenter?) : super() {
        this.mSettingPersenter = mSettingPersenter
    }

    override fun doWork(map: Map<String, String>?, url: String?) {
       HttpService.getInstance().getDataFromServer(map,url,Constants.GET,this)
    }

    override fun doWorkResults(commonResponse: CommonResponse?, isSuccess: Boolean) {
        mSettingPersenter!!.requestResults(commonResponse!!,isSuccess)
    }


}