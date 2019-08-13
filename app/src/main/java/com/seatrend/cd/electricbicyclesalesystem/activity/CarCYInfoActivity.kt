package com.seatrend.cd.electricbicyclesalesystem.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.RadioGroup
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.adpater.CarInfoPagerAdapter
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.fragment.*
import java.util.ArrayList

/**
 * Created by ly on 2019/7/2 17:00
 *
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
class CarCYInfoActivity : BaseActivity() {
    var carcy_info_pg: ViewPager? = null
    var carinfo_rg: RadioGroup? = null
    private val fragmentList = ArrayList<Fragment>()
    @SuppressLint("ResourceType")
    override fun initView() {
        setPageTitle(getString(R.string.carcy_info))
        carcy_info_pg = findViewById(R.id.carcy_info_pg)
        carinfo_rg = findViewById(R.id.carinfo_rg)

        fragmentList.add(CarMsgJscsFragment())
        fragmentList.add(CarCYFragment())
        fragmentList.add(CarPhotoFragment())
        carcy_info_pg!!.adapter = CarInfoPagerAdapter(supportFragmentManager,fragmentList)
        carinfo_rg!!.check(1)
        bindEvent()
    }

    private fun bindEvent() {
        carcy_info_pg!!.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                carinfo_rg!!.check(position+1)
            }

        })
        carinfo_rg!!.setOnCheckedChangeListener(object:RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                carcy_info_pg!!.setCurrentItem(p1-1)
            }

        })
    }

    override fun getLayout(): Int {
        return R.layout.activity_carinfo
    }
}