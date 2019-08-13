package com.seatrend.cd.electricbicyclesalesystem.persenter

import com.seatrend.cd.electricbicyclesalesystem.common.BasePresenter
import com.seatrend.cd.electricbicyclesalesystem.common.BaseView
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.module.CarLoginModule
import com.seatrend.cd.electricbicyclesalesystem.module.LoginModule
import com.seatrend.cd.electricbicyclesalesystem.module.SettingModule
import com.seatrend.cd.electricbicyclesalesystem.module.WarningMessageModule
import com.seatrend.cd.electricbicyclesalesystem.view.CarLoginView
import com.seatrend.cd.electricbicyclesalesystem.view.LoginView
import com.seatrend.cd.electricbicyclesalesystem.view.SettingView
import com.seatrend.cd.electricbicyclesalesystem.view.WarningMessageView

/**
 * Created by seatrend on 2018/12/27.
 */

class WarningMessagePersenter(mView: WarningMessageView) : BasePresenter(mView) {

    private var mWarningMessageModule: WarningMessageModule?=null
    private var mWarningMessageView: WarningMessageView?=null
    init {
        mWarningMessageModule=WarningMessageModule(this)
        mWarningMessageView=mView;
    }

    override fun doNetworkTask(map: Map<String, String>, url: String) {
        mWarningMessageModule!!.doWork(map,url)

    }

    override fun requestResults(commonResponse: CommonResponse, isSuccess: Boolean) {
        if (isSuccess){
            mWarningMessageView!!.netWorkTaskSuccess(commonResponse)
        }else {
            mWarningMessageView!!.netWorkTaskfailed(commonResponse)
        }

    }
}
