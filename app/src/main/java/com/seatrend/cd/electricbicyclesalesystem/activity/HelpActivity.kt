package com.seatrend.cd.electricbicyclesalesystem.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import kotlinx.android.synthetic.main.activity_help.*

class HelpActivity : BaseActivity() {



    override fun initView() {

        setPageTitle("帮助")

        ll_exit_login.setOnClickListener {

            startActivity(Intent(this,ExitLoginActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return  R.layout.activity_help
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
