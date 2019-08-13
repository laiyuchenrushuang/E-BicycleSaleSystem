package com.seatrend.cd.electricbicyclesalesystem.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.fragment.UserInfoFragment
import kotlinx.android.synthetic.main.activity_factor_function_list.*

class FactorFunctionListActivity : BaseActivity() {



    override fun initView() {
        setPageTitle(getString(R.string.xzksxm))
        ivRight!!.setImageResource(R.drawable.search_icon)
        ivRight!!.setOnClickListener {
            startActivity(Intent(this, UserInfoFragment::class.java))
        }
        cv_vin.setOnClickListener {
            val intent=Intent(this,CarLoginActivity::class.java)
            intent.putExtra(Constants.POSITION,Constants.TO_VIN)
            startActivity(intent)
        }
        cv_input_data.setOnClickListener {
            val intent= Intent(this,CarLoginActivity::class.java)
            intent.putExtra(Constants.TASK,"0")
            startActivity(intent)
        }

    }

    override fun getLayout(): Int {

        return  R.layout.activity_factor_function_list;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
