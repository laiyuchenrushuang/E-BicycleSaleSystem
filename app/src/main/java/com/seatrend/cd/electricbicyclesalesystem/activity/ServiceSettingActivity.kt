package com.seatrend.cd.electricbicyclesalesystem.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.util.SharedPreferencesUtils
import kotlinx.android.synthetic.main.activity_service_setting.*

class ServiceSettingActivity : BaseActivity() {



    override fun initView() {
        setPageTitle(getString(R.string.server_setting))



        et_ip.setText(SharedPreferencesUtils.getIpAddress())
        et_port.setText(SharedPreferencesUtils.getPort())

        btn_ok.setOnClickListener {

            if (TextUtils.isEmpty(et_ip.text.toString())) {
                showToast("请输入端ip地址")

                return@setOnClickListener

            }
            if (TextUtils.isEmpty(et_port.text.toString())) {
                showToast("请输入端口号")
                return@setOnClickListener
            }
            SharedPreferencesUtils.setIpAddress(et_ip.text.toString())
            SharedPreferencesUtils.setPort(et_port.text.toString())
            finish()

        }




    }

    override fun getLayout(): Int {

        return R.layout.activity_service_setting;

    }


}
