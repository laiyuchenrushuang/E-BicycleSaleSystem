package com.seatrend.cd.electricbicyclesalesystem.persenter

import com.seatrend.cd.electricbicyclesalesystem.common.BasePresenter
import com.seatrend.cd.electricbicyclesalesystem.common.BaseView
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.module.BusinessDetailsModule
import com.seatrend.cd.electricbicyclesalesystem.module.LoginModule
import com.seatrend.cd.electricbicyclesalesystem.module.SettingModule
import com.seatrend.cd.electricbicyclesalesystem.view.BusinessDetailsView
import com.seatrend.cd.electricbicyclesalesystem.view.LoginView
import com.seatrend.cd.electricbicyclesalesystem.view.SettingView

/**
 * Created by seatrend on 2018/12/27.
 */

class BusinessDetailsPersenter(mView: BusinessDetailsView) : BasePresenter(mView) {

    private var mBusinessDetailsModule: BusinessDetailsModule?=null
    private var mBusinessDetailsView: BusinessDetailsView?=null
    init {
        mBusinessDetailsModule=BusinessDetailsModule(this)
        mBusinessDetailsView=mView;
    }

    override fun doNetworkTask(map: Map<String, String>, url: String) {
        mBusinessDetailsModule!!.doWork(map,url)

    }

    override fun requestResults(commonResponse: CommonResponse, isSuccess: Boolean) {
        if (isSuccess){
            mBusinessDetailsView!!.netWorkTaskSuccess(commonResponse)
        }else {
            mBusinessDetailsView!!.netWorkTaskfailed(commonResponse)
        }

    }
}
