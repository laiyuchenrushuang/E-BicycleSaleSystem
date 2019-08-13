package com.seatrend.cd.electricbicyclesalesystem.activity

import android.content.Intent
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import kotlinx.android.synthetic.main.activity_hpbf_remind.*

class RemindHPBFactivity :BaseActivity(){
    override fun initView() {
       setPageTitle("提示信息")
        btn_back_home.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_hpbf_remind
    }

}
