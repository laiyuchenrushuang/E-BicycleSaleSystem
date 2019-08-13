package com.seatrend.cd.electricbicyclesalesystem.persenter

import com.seatrend.cd.electricbicyclesalesystem.common.BasePresenter
import com.seatrend.cd.electricbicyclesalesystem.common.BaseView
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.module.SettingModule
import com.seatrend.cd.electricbicyclesalesystem.view.SettingView

/**
 * Created by seatrend on 2018/12/27.
 */

class SettingPersenter(mView: SettingView) : BasePresenter(mView) {

    private var mSettingModule: SettingModule?=null
    private var mSettingView:SettingView?=null
    init {
        mSettingModule=SettingModule(this)
        mSettingView=mView;
    }

    override fun doNetworkTask(map: Map<String, String>, url: String) {
        mSettingModule!!.doWork(map,url)

    }

    override fun requestResults(commonResponse: CommonResponse, isSuccess: Boolean) {
        if (isSuccess){
            mSettingView!!.netWorkTaskSuccess(commonResponse)
        }else {
            mSettingView!!.netWorkTaskfailed(commonResponse)
        }

    }
}
