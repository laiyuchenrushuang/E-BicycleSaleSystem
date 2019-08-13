package com.seatrend.cd.electricbicyclesalesystem.persenter

import com.seatrend.cd.electricbicyclesalesystem.common.BasePresenter
import com.seatrend.cd.electricbicyclesalesystem.common.BaseView
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.module.CarPhotoModule
import com.seatrend.cd.electricbicyclesalesystem.module.LoginModule
import com.seatrend.cd.electricbicyclesalesystem.module.SettingModule
import com.seatrend.cd.electricbicyclesalesystem.view.CarPhotoView
import com.seatrend.cd.electricbicyclesalesystem.view.LoginView
import com.seatrend.cd.electricbicyclesalesystem.view.SettingView
import java.io.File

/**
 * Created by seatrend on 2018/12/27.
 */

class CarPhotoPersenter(mView: CarPhotoView) : BasePresenter(mView) {

    private var mCarPhotoModule: CarPhotoModule?=null
    private var mCarPhotoView: CarPhotoView?=null
    init {
        mCarPhotoModule=CarPhotoModule(this)
        mCarPhotoView=mView;
    }

    override fun doNetworkTask(map: Map<String, String>, url: String) {
        mCarPhotoModule!!.doWork(map,url)

    }

    override fun requestResults(commonResponse: CommonResponse, isSuccess: Boolean) {
        if (isSuccess){
            mCarPhotoView!!.netWorkTaskSuccess(commonResponse)
        }else {
            mCarPhotoView!!.netWorkTaskfailed(commonResponse)
        }

    }

    fun uploadFileAndData( jsonString: String, url: String){
        mCarPhotoModule!!.uploadFileAndData(jsonString,url)
    }
}
