package com.seatrend.cd.electricbicyclesalesystem.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_carcy.*

class CarCYFragment : BaseFragment() {
    override fun getLayoutView(inflater: LayoutInflater?, container: ViewGroup?): View {
        return inflater!!.inflate(R.layout.fragment_carcy, container, false)
    }

    override fun initView() {
        bindEvent()

    }

    private fun bindEvent() {
        tv_sjss!!.setOnClickListener {
            tv_sjss!!.setText(getString(R.string.carcy_ok))
            tv_sjss!!.setBackground(resources!!.getDrawable(R.drawable.clcy_button_state_ok))
        }
    }

}
