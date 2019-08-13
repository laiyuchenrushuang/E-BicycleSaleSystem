package com.seatrend.cd.electricbicyclesalesystem.fragment


import android.content.Intent
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.activity.*
import com.seatrend.cd.electricbicyclesalesystem.common.BaseFragment
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.common.Constants.Companion.CAR_BG
import com.seatrend.cd.electricbicyclesalesystem.common.Constants.Companion.CAR_BH
import com.seatrend.cd.electricbicyclesalesystem.common.Constants.Companion.CAR_CY
import com.seatrend.cd.electricbicyclesalesystem.common.Constants.Companion.CAR_GD
import com.seatrend.cd.electricbicyclesalesystem.common.Constants.Companion.CAR_ZC
import com.seatrend.cd.electricbicyclesalesystem.common.Constants.Companion.CAR_ZX
import com.seatrend.cd.electricbicyclesalesystem.common.Constants.Companion.CAR_ZY
import com.seatrend.cd.electricbicyclesalesystem.util.LoadingDialog
import com.seatrend.cd.electricbicyclesalesystem.zxing.activity.CaptureActivity
import kotlinx.android.synthetic.main.fragment_business.*


/**
 * A simple [Fragment] subclass.
 */
class BusinessFragment : BaseFragment() {



    override fun getLayoutView(inflater: LayoutInflater?, container: ViewGroup?): View {
        return inflater!!.inflate(R.layout.fragment_business, container, false)
    }

    override fun initView() {


        rl_clcy.setOnClickListener {

//            startActivity(Intent(context, FactorFunctionListActivity::class.java))
            val intent = Intent(context, CaptureActivity::class.java)
            intent.putExtra(Constants.TASK,CAR_CY)
            intent.putExtra(Constants.LX,"1")
            startActivity(intent)
        }
        rl_zcdj.setOnClickListener {
            val intent= Intent(context,CarCodeCYActivity::class.java)
            intent.putExtra(Constants.TASK, CAR_ZC)
            intent.putExtra(Constants.LX, "2")
            startActivity(intent)
        }
        rl_bgdj.setOnClickListener {
            val intent= Intent(context,CarCodeCYActivity::class.java)
            intent.putExtra(Constants.TASK, CAR_BG)
            intent.putExtra(Constants.LX, "2")
            startActivity(intent)
        }
        rl_zydj.setOnClickListener {
            val intent= Intent(context,CarCodeCYActivity::class.java)
            intent.putExtra(Constants.TASK,CAR_ZY)
            intent.putExtra(Constants.LX, "2")
            startActivity(intent)
        }
        rl_bhpz.setOnClickListener {
            val intent= Intent(context,CarCodeCYActivity::class.java)
            intent.putExtra(Constants.TASK,CAR_BH)
            intent.putExtra(Constants.LX, "2")
            startActivity(intent)
        }
        rl_zxdj.setOnClickListener {
            val intent= Intent(context,CarCodeCYActivity::class.java)
            intent.putExtra(Constants.TASK,CAR_ZX)
            intent.putExtra(Constants.LX, "2")
            startActivity(intent)
        }
        rl_dagd.setOnClickListener {
            val intent= Intent(context,ArchiveFileActivity::class.java)
            intent.putExtra(Constants.TASK,CAR_GD)
            startActivity(intent)
        }

    }




}
