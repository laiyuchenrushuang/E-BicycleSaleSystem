package com.seatrend.cd.electricbicyclesalesystem.activity

import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import kotlinx.android.synthetic.main.activity_car_basedata.view.*

/**
 * Created by ly on 2019/7/3 15:55
 *
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
class CarSmallInfoActivity :BaseActivity(){
    override fun initView() {
        setPageTitle(getString(R.string.clxx))
    }

    override fun getLayout(): Int {
       return R.layout.activity_small_carinfo
    }

}