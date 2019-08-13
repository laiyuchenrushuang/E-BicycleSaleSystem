package com.seatrend.cd.electricbicyclesalesystem.persenter

import com.seatrend.cd.electricbicyclesalesystem.common.BasePresenter
import com.seatrend.cd.electricbicyclesalesystem.common.BaseView
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.module.CarLoginModule
import com.seatrend.cd.electricbicyclesalesystem.module.CollectBicycleModule
import com.seatrend.cd.electricbicyclesalesystem.module.LoginModule
import com.seatrend.cd.electricbicyclesalesystem.module.SettingModule
import com.seatrend.cd.electricbicyclesalesystem.view.CarLoginView
import com.seatrend.cd.electricbicyclesalesystem.view.CollectBicycleView
import com.seatrend.cd.electricbicyclesalesystem.view.LoginView
import com.seatrend.cd.electricbicyclesalesystem.view.SettingView
import java.io.File

/**
 * Created by seatrend on 2018/12/27.
 */

class CollectBicyclePersenter(mView: CollectBicycleView) : BasePresenter(mView) {

    private var mCollectBicycleModule: CollectBicycleModule?=null
    private var mCollectBicycleView: CollectBicycleView?=null
    init {
        mCollectBicycleModule=CollectBicycleModule(this)
        mCollectBicycleView=mView;
    }

    override fun doNetworkTask(map: Map<String, String>, url: String) {
        mCollectBicycleModule!!.doWork(map,url)

    }

    override fun requestResults(commonResponse: CommonResponse, isSuccess: Boolean) {
        if (isSuccess){
            mCollectBicycleView!!.netWorkTaskSuccess(commonResponse)
        }else {
            mCollectBicycleView!!.netWorkTaskfailed(commonResponse)
        }

    }
    fun uploadFile(file: File, map: Map<String, String>, url: String){
        mCollectBicycleModule!!.uploadFile(file,url,map)
    }
}
