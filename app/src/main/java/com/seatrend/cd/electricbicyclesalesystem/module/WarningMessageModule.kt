package com.seatrend.cd.electricbicyclesalesystem.module

import com.seatrend.cd.electricbicyclesalesystem.common.BaseModule
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.http.HttpService
import com.seatrend.cd.electricbicyclesalesystem.persenter.CarLoginPersenter
import com.seatrend.cd.electricbicyclesalesystem.persenter.LoginPersenter
import com.seatrend.cd.electricbicyclesalesystem.persenter.SettingPersenter
import com.seatrend.cd.electricbicyclesalesystem.persenter.WarningMessagePersenter

/**
 * Created by seatrend on 2018/12/27.
 */
class WarningMessageModule:BaseModule{

    private var mWarningMessagePersenter: WarningMessagePersenter?=null

    constructor(mWarningMessagePersenter: WarningMessagePersenter?) : super() {
        this.mWarningMessagePersenter = mWarningMessagePersenter
    }


    override fun doWork(map: Map<String, String>?, url: String?) {
       HttpService.getInstance().getDataFromServer(map,url,Constants.GET,this)
    }

    override fun doWorkResults(commonResponse: CommonResponse?, isSuccess: Boolean) {
        mWarningMessagePersenter!!.requestResults(commonResponse!!,isSuccess)
    }


}