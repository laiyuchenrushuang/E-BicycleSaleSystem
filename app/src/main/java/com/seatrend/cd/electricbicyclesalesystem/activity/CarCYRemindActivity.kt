package com.seatrend.cd.electricbicyclesalesystem.activity

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.entity.CarMessageEntity
import kotlinx.android.synthetic.main.activity_carcy_remind.*

/**
 * Created by ly on 2019/7/2 19:08
 *
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
class CarCYRemindActivity : BaseActivity() {
    var carcy_hpbf_bt: Button? = null
    var carcy_info_bt: Button? = null
    var ll_sencond: LinearLayout? = null
    var ll_first: LinearLayout? = null
    var tv_remind_back_home: TextView? = null

    companion object {
        var showFlag = false
    }

    override fun initView() {
        setPageTitle(getString(R.string.carcy_remind))
        carcy_hpbf_bt = findViewById(R.id.carcy_hpbf_bt)
        carcy_info_bt = findViewById(R.id.carcy_info_bt)
        ll_sencond = findViewById(R.id.ll_sencond)
        ll_first = findViewById(R.id.ll_first)
        tv_remind_back_home = findViewById(R.id.tv_remind_back_home)

        bindEvent()
    }

    private fun bindEvent() {
        carcy_info_bt!!.setOnClickListener {
            startActivity(intent.setClass(this, CarCYInfoActivity::class.java))
        }
        carcy_hpbf_bt!!.setOnClickListener {
            startActivity(intent.setClass(this, HpbfActivity::class.java))
        }
        tv_remind_back_home !!. setOnClickListener {
            showFlag = false
            finish()
        }

    }

    override fun getLayout(): Int {
        return R.layout.activity_carcy_remind
    }

    override fun onResume() {
        super.onResume()
        if (!showFlag) {
            ll_sencond!!.setVisibility(View.GONE)
            ll_first!!.setVisibility(View.VISIBLE)
        } else {
            ll_first!!.setVisibility(View.GONE)
            ll_sencond!!.setVisibility(View.VISIBLE)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showFlag = false
    }

}