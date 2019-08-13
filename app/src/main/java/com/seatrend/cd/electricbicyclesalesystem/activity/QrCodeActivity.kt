package com.seatrend.cd.electricbicyclesalesystem.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.util.StringUtils
import kotlinx.android.synthetic.main.activity_qr_code.*

class QrCodeActivity : BaseActivity() {


    override fun initView() {
        setPageTitle("扫描二维码")
       var url=intent.getStringExtra("url")

       var bitmap=StringUtils.createQRCode(url)
        iv_qrcode.setImageBitmap(bitmap)

    }

    override fun getLayout(): Int {

        return R.layout.activity_qr_code;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
