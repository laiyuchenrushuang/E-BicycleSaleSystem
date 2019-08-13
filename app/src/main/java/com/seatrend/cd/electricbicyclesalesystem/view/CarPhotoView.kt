package com.seatrend.cd.electricbicyclesalesystem.view

import com.seatrend.cd.electricbicyclesalesystem.common.BaseView
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse

/**
 * Created by seatrend on 2018/12/27.
 */
interface CarPhotoView:BaseView{
     fun netWorkTaskSuccess(commonResponse: CommonResponse)
     fun netWorkTaskfailed(commonResponse: CommonResponse)
}