package com.seatrend.cd.electricbicyclesalesystem.module

import com.seatrend.cd.electricbicyclesalesystem.common.BaseModule
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.http.HttpService
import com.seatrend.cd.electricbicyclesalesystem.persenter.CarPhotoPersenter
import com.seatrend.cd.electricbicyclesalesystem.persenter.LoginPersenter
import com.seatrend.cd.electricbicyclesalesystem.persenter.SettingPersenter
import java.io.File

/**
 * Created by seatrend on 2018/12/27.
 */
class CarPhotoModule:BaseModule{

    private var mCarPhotoPersenter: CarPhotoPersenter?=null

    constructor(mCarPhotoPersenter: CarPhotoPersenter?) : super() {
        this.mCarPhotoPersenter = mCarPhotoPersenter
    }


    override fun doWork(map: Map<String, String>?, url: String?) {
        if(url == Constants.PSOT_CAR_YWXX){
            HttpService.getInstance().getDataFromServer(map,url,Constants.POST,this)
        }else{
            HttpService.getInstance().getDataFromServer(map,url,Constants.GET,this)
        }

    }

    override fun doWorkResults(commonResponse: CommonResponse?, isSuccess: Boolean) {
        mCarPhotoPersenter!!.requestResults(commonResponse!!,isSuccess)
    }
    fun uploadFileAndData(jsonString: String,url: String){
        HttpService.getInstance().getDataFromServerByJson(jsonString,url,this)
    }

}