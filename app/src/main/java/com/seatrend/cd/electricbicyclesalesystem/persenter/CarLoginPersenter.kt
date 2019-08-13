package com.seatrend.cd.electricbicyclesalesystem.persenter

import com.seatrend.cd.electricbicyclesalesystem.common.BasePresenter
import com.seatrend.cd.electricbicyclesalesystem.common.BaseView
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.module.CarLoginModule
import com.seatrend.cd.electricbicyclesalesystem.module.LoginModule
import com.seatrend.cd.electricbicyclesalesystem.module.SettingModule
import com.seatrend.cd.electricbicyclesalesystem.view.CarLoginView
import com.seatrend.cd.electricbicyclesalesystem.view.LoginView
import com.seatrend.cd.electricbicyclesalesystem.view.SettingView

/**
 * Created by seatrend on 2018/12/27.
 */

class CarLoginPersenter(mView: CarLoginView) : BasePresenter(mView) {

    private var mCarLoginModule: CarLoginModule?=null
    private var mCarLoginView: CarLoginView?=null
    init {
        mCarLoginModule=CarLoginModule(this)
        mCarLoginView=mView;
    }

    override fun doNetworkTask(map: Map<String, String>, url: String) {
        mCarLoginModule!!.doWork(map,url)

    }

    override fun requestResults(commonResponse: CommonResponse, isSuccess: Boolean) {
        if (isSuccess){
            mCarLoginView!!.netWorkTaskSuccess(commonResponse)
        }else {
            mCarLoginView!!.netWorkTaskfailed(commonResponse)
        }

    }
}
