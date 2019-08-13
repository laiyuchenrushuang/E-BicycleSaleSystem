package com.seatrend.cd.electricbicyclesalesystem.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity

/**
 * 档案归档
 */
class DagdActivity : BaseActivity() {


    override fun initView() {
        setPageTitle(getString(R.string.dagd))
    }

    override fun getLayout(): Int {

        return R.layout.activity_dagd
    }


}
