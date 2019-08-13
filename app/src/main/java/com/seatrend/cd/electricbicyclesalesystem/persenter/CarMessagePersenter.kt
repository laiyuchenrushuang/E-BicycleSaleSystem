package com.seatrend.cd.electricbicyclesalesystem.persenter

import com.seatrend.cd.electricbicyclesalesystem.common.BasePresenter
import com.seatrend.cd.electricbicyclesalesystem.common.BaseView
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.module.CarLoginModule
import com.seatrend.cd.electricbicyclesalesystem.module.CarMessageModule
import com.seatrend.cd.electricbicyclesalesystem.module.LoginModule
import com.seatrend.cd.electricbicyclesalesystem.module.SettingModule
import com.seatrend.cd.electricbicyclesalesystem.view.CarLoginView
import com.seatrend.cd.electricbicyclesalesystem.view.CarMessageView
import com.seatrend.cd.electricbicyclesalesystem.view.LoginView
import com.seatrend.cd.electricbicyclesalesystem.view.SettingView

/**
 * Created by seatrend on 2018/12/27.
 */

class CarMessagePersenter(mView: CarMessageView) : BasePresenter(mView) {

    private var mCarMessageModule: CarMessageModule?=null
    private var mCarMessageView: CarMessageView?=null
    init {
        mCarMessageModule=CarMessageModule(this)
        mCarMessageView=mView;
    }

    override fun doNetworkTask(map: Map<String, String>, url: String) {
        mCarMessageModule!!.doWork(map,url)

    }

    override fun requestResults(commonResponse: CommonResponse, isSuccess: Boolean) {
        if (isSuccess){
            mCarMessageView!!.netWorkTaskSuccess(commonResponse)
        }else {
            mCarMessageView!!.netWorkTaskfailed(commonResponse)
        }

    }
}
