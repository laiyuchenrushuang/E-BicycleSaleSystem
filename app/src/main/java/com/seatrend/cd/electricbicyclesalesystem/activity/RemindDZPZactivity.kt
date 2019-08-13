package com.seatrend.cd.electricbicyclesalesystem.activity

import android.content.Intent
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.util.StringUtils
import kotlinx.android.synthetic.main.activity_dzpz.*
import kotlinx.android.synthetic.main.activity_qr_code.*


class RemindDZPZactivity:BaseActivity() {
    override fun initView() {
        setPageTitle("扫码领凭证")
        var url=intent.getStringExtra("url")

        var bitmap= StringUtils.createQRCode(url)
        iv_ewm.setImageBitmap(bitmap)

        btn_back_home.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_dzpz
    }

}
