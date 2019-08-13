package com.seatrend.cd.electricbicyclesalesystem.persenter

import com.seatrend.cd.electricbicyclesalesystem.common.BasePresenter
import com.seatrend.cd.electricbicyclesalesystem.common.BaseView
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.module.LoginModule
import com.seatrend.cd.electricbicyclesalesystem.module.SettingModule
import com.seatrend.cd.electricbicyclesalesystem.view.LoginView
import com.seatrend.cd.electricbicyclesalesystem.view.SettingView

/**
 * Created by seatrend on 2018/12/27.
 */

class LoginPersenter(mView: LoginView) : BasePresenter(mView) {

    private var mLoginModule: LoginModule?=null
    private var mLoginView:LoginView?=null
    init {
        mLoginModule=LoginModule(this)
        mLoginView=mView;
    }

    override fun doNetworkTask(map: Map<String, String>, url: String) {
        mLoginModule!!.doWork(map,url)

    }

    override fun requestResults(commonResponse: CommonResponse, isSuccess: Boolean) {
        if (isSuccess){
            mLoginView!!.netWorkTaskSuccess(commonResponse)
        }else {
            mLoginView!!.netWorkTaskfailed(commonResponse)
        }

    }
}
