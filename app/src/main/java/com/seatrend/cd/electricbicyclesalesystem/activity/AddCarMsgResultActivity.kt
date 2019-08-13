package com.seatrend.cd.electricbicyclesalesystem.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import kotlinx.android.synthetic.main.activity_add_car_msg_result.*
import kotlin.concurrent.thread

class AddCarMsgResultActivity : BaseActivity() {

    private val TIME_CODE=100

    override fun initView() {
        setPageTitle("提交成功")

        btn_ywdj.setOnClickListener {
           // startActivity(Intent(this@AddCarMsgResultActivity,CarDetailsActivity::class.java))

            showToast("业务登记")
        }

        btn_back_home.setOnClickListener {
            val intent= Intent(this@AddCarMsgResultActivity,MainActivity::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }

        thread {
            var time=10
            for (i in 0..10){
                val msg=Message.obtain()
                msg.what=TIME_CODE
                msg.obj=time
                mHandler.sendMessage(msg)
                time--
                Thread.sleep(1000)
            }
        }

    }

    override fun getLayout(): Int {
        return R.layout.activity_add_car_msg_result
    }


    @SuppressLint("HandlerLeak")
    private val mHandler= object :Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            val  time=msg!!.obj as Int
            tv_tips_msg.text = getString(R.string.tips_time,time)
            if (time==0){
                btn_ywdj.performClick()
            }
        }
    }


}
