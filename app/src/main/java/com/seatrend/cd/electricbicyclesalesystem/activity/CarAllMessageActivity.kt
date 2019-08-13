package com.seatrend.cd.electricbicyclesalesystem.activity

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.util.Log
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.entity.CarMessageEntity
import com.seatrend.cd.electricbicyclesalesystem.fragment.CarMsgGgxxFragment
import com.seatrend.cd.electricbicyclesalesystem.fragment.CarMsgJscsFragment
import com.seatrend.cd.electricbicyclesalesystem.persenter.CarMessagePersenter
import kotlinx.android.synthetic.main.activity_car_all_message.*

class CarAllMessageActivity : BaseActivity() {
    var rb_ggxx: RadioButton? = null
    var carmsg_rg: RadioGroup? = null
    var carmsg_fl: FrameLayout? = null

    var mCarMsgJscsFG: CarMsgJscsFragment? = null
    var mCarMsgggxxFG: CarMsgGgxxFragment? = null

    companion object {
        var mCarMessageEntity: CarMessageEntity? = null
    }

    @SuppressLint("ResourceType")
    override fun initView() {
        setPageTitle(getString(R.string.car_query))
        mCarMsgJscsFG = CarMsgJscsFragment()
        mCarMsgggxxFG = CarMsgGgxxFragment()

        rb_ggxx = findViewById(R.id.rb_ggxx)
        carmsg_fl = findViewById(R.id.carmsg_fl)
        carmsg_rg = findViewById(R.id.carmsg_rg)


        bindEvent()
        carmsg_rg!!.check(rb_jscs!!.id)
    }

    private fun bindEvent() {
        carmsg_rg!!.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {

            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                Log.d("lylog"," p1 = "+p1)
                if (p1 == rb_jscs!!.id) {
                    rb_jscs!!.setTextColor(resources.getColor(R.color.theme_color))
                    rb_ggxx!!.setTextColor(resources.getColor(R.color.black))
                    Log.d("lylog"," rb_jscs")
                    switchFragment(mCarMsgJscsFG);
                } else {
                    rb_ggxx!!.setTextColor(resources.getColor(R.color.theme_color))
                    rb_jscs!!.setTextColor(resources.getColor(R.color.black))
                    switchFragment(mCarMsgggxxFG);
                }
            }

        })
        car_message_clcy!!.setOnClickListener {
            startActivity(intent.setClass(this,CarCYActivity::class.java))
        }
    }

    private fun switchFragment(fragment: Fragment?) {
        supportFragmentManager.beginTransaction().replace(R.id.carmsg_fl, fragment).commit();
    }

    override fun getLayout(): Int {
        return R.layout.activity_car_all_message
    }

}
