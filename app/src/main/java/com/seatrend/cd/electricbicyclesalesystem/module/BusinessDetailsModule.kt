package com.seatrend.cd.electricbicyclesalesystem.module

import com.seatrend.cd.electricbicyclesalesystem.common.BaseModule
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.http.HttpService
import com.seatrend.cd.electricbicyclesalesystem.persenter.BusinessDetailsPersenter
import com.seatrend.cd.electricbicyclesalesystem.persenter.LoginPersenter
import com.seatrend.cd.electricbicyclesalesystem.persenter.SettingPersenter

/**
 * Created by seatrend on 2018/12/27.
 */
class BusinessDetailsModule:BaseModule{

    private var mBusinessDetailsPersenter: BusinessDetailsPersenter?=null

    constructor(mBusinessDetailsPersenter: BusinessDetailsPersenter?) : super() {
        this.mBusinessDetailsPersenter = mBusinessDetailsPersenter
    }


    override fun doWork(map: Map<String, String>?, url: String?) {
       HttpService.getInstance().getDataFromServer(map,url,Constants.GET,this)
    }

    override fun doWorkResults(commonResponse: CommonResponse?, isSuccess: Boolean) {
        mBusinessDetailsPersenter!!.requestResults(commonResponse!!,isSuccess)
    }


}