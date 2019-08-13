package com.seatrend.cd.electricbicyclesalesystem.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.activity.CarAllMessageActivity
import com.seatrend.cd.electricbicyclesalesystem.common.BaseFragment
import com.seatrend.cd.electricbicyclesalesystem.entity.CarMessageEntity
import kotlinx.android.synthetic.main.fragment_jscs.*

/**
 * Created by ly on 2019/7/2 10:03
 *
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
class CarMsgJscsFragment : BaseFragment() {

    var mCarMsgFragmentEntity: CarMessageEntity? = null


    override fun getLayoutView(inflater: LayoutInflater?, container: ViewGroup?): View {
        return inflater!!.inflate(R.layout.fragment_jscs, container, false)
    }

    override fun initView() {
        mCarMsgFragmentEntity = CarAllMessageActivity.mCarMessageEntity
//        tv_zcbm!!.setText(mCarMsgFragmentEntity!!.data.zcbm)
    }
}