package com.seatrend.cd.electricbicyclesalesystem.activity

import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import java.util.logging.Handler as Handler1
import android.os.Handler
import android.content.Intent
import android.view.WindowManager


/**
 * Created by ly on 2019/7/29 17:49
 *
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
class LoginLoadingActivity:BaseActivity(){
    val  time :Long = 2000;
    override fun initView() {
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN)
        startLoginActivity()
    }

    private fun startLoginActivity() {
        val handler = Handler()
        handler.postDelayed(Runnable {
            startActivity(Intent(this@LoginLoadingActivity, LoginActivity::class.java))
            finish()
        }, time)
    }

    override fun getLayout(): Int {
       return R.layout.activity_login_loading
    }

}