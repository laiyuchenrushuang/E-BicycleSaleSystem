package com.seatrend.cd.electricbicyclesalesystem.persenter

import com.seatrend.cd.electricbicyclesalesystem.common.BasePresenter
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.module.ArchiveFileMoudule
import com.seatrend.cd.electricbicyclesalesystem.module.BusinessDetailsModule
import com.seatrend.cd.electricbicyclesalesystem.view.ArchiveFileView
import com.seatrend.cd.electricbicyclesalesystem.view.BusinessDetailsView

/**
 * Created by ly on 2019/7/18 16:30
 *
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
class ArchiveFilePresenter(mView: ArchiveFileView) : BasePresenter(mView) {

    private var mArchiveFileMoudule: ArchiveFileMoudule?=null
    private var mArchiveFileView: ArchiveFileView?=null
    init {
        mArchiveFileMoudule= ArchiveFileMoudule(this)
        mArchiveFileView=mView;
    }

    override fun doNetworkTask(map: Map<String, String>, url: String) {
        mArchiveFileMoudule!!.doWork(map,url)

    }

    override fun requestResults(commonResponse: CommonResponse, isSuccess: Boolean) {
        if (isSuccess){
            mArchiveFileView!!.netWorkTaskSuccess(commonResponse)
        }else {
            mArchiveFileView!!.netWorkTaskfailed(commonResponse)
        }

    }
}