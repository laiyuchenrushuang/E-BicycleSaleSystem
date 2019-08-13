package com.seatrend.cd.electricbicyclesalesystem.persenter

import com.seatrend.cd.electricbicyclesalesystem.common.BasePresenter
import com.seatrend.cd.electricbicyclesalesystem.common.BaseView
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.module.YwxxModule
import com.seatrend.cd.electricbicyclesalesystem.view.YwxxView

/**
 * Created by ly on 2019/8/8 18:55
 *
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
class YwxxPresenter(mView: YwxxView) : BasePresenter(mView) {
    private var mYwxxView: YwxxView? = null
    private var mYwxxModule: YwxxModule? = null

    init {
        mYwxxView = mView
        mYwxxModule = YwxxModule(this)
    }

    override fun doNetworkTask(map: Map<String, String>, url: String) {
        mYwxxModule!!.doWork(map, url)
    }

    override fun requestResults(commonResponse: CommonResponse, isSuccess: Boolean) {
        if (isSuccess) {
            mYwxxView!!.netWorkTaskSuccess(commonResponse)
        } else {
            mYwxxView!!.netWorkTaskfailed(commonResponse)
        }
    }

}