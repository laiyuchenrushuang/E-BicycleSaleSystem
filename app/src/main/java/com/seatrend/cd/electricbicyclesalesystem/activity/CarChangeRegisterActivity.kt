package com.seatrend.cd.electricbicyclesalesystem.activity

import android.support.v4.app.Fragment
import android.util.Log
import android.widget.RadioGroup
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.BaseFragment
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.CarBean
import com.seatrend.cd.electricbicyclesalesystem.entity.YwSearchBean
import com.seatrend.cd.electricbicyclesalesystem.fragment.*
import kotlinx.android.synthetic.main.activity_change_register.*
import kotlinx.android.synthetic.main.activity_change_register.m_radio_group
import kotlinx.android.synthetic.main.activity_change_register.rb_dlrxx
import kotlinx.android.synthetic.main.activity_change_register.rb_syrxx
import kotlinx.android.synthetic.main.activity_change_register.rb_ywxx
import kotlinx.android.synthetic.main.activity_collect_data.*
import kotlinx.android.synthetic.main.fragment_yexx.*

/**
 * Created by ly on 2019/7/3 17:31
 *
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
class CarChangeRegisterActivity : BaseActivity() {

    companion object{
        var mYwSearchBean = YwSearchBean() //zcbm查询获取的bean
        var mCarZcMsgData = CarBean()  //更改数据提交的bean
    }
    private val mYexxFragment = YwxxChangeFragment()
    private var mSyrxxFragment :BaseFragment ?=null
    private val mDlrxxFragment = DlrxxChangeFragment()
    private var lastFragment: Fragment? = null

    override fun initView() {
        dispatcherTitleYwlx(intent.getStringExtra(Constants.TASK))

        rb_ywxx.isChecked = true
        switchFrament(lastFragment,mYexxFragment)
        bindEvent()
    }

    private fun dispatcherTitleYwlx(ywlx: Any) {
        when (ywlx) {
            Constants.CAR_BG -> {
                setPageTitle("变更登记")
                mSyrxxFragment = SyrxxChangeFragment()
            }
            Constants.CAR_ZY -> {
                setPageTitle("转移登记")
                mSyrxxFragment = CommonChangeFragment()
            }
            Constants.CAR_ZX -> {
                setPageTitle("注销登记")
                mSyrxxFragment = SyrxxChangeFragment() //注销登记不更改用户姓名身份证
            }
        }
    }

    private fun bindEvent() {
        m_radio_group!!.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                Log.d("lylog"," p1 = "+p1)
                when (p1) {
                    R.id.rb_ywxx -> {
                        switchFrament(lastFragment, mYexxFragment)
                    }
                    R.id.rb_syrxx -> {
                        switchFrament(lastFragment, mSyrxxFragment)
                    }
                    R.id.rb_dlrxx -> {
                        switchFrament(lastFragment, mDlrxxFragment)
                    }
                }
            }

        })
    }

    private fun switchFrament(from: Fragment?, to: Fragment?) {
        if (from !== to) {
            lastFragment = to
            val fm = supportFragmentManager
            val ft = fm.beginTransaction()
            if (!to!!.isAdded) {
                if (from != null) {
                    ft.hide(from)
                }
                if (to != null) {
                    ft.add(R.id.framelayout, to).commit()
                }
            } else {
                if (from != null) {
                    ft.hide(from)
                }
                if (to != null) {
                    ft.show(to).commit()
                }
            }
        }

    }

    override fun getLayout(): Int {
        return R.layout.activity_change_register
    }

    fun donext() {
        rb_syrxx.isChecked = true
    }

    fun doDLRnext() {
        rb_dlrxx.isChecked = true
    }

    fun doYWXXnext() {
        rb_ywxx.isChecked = true
    }


    //各个页面的所有信息传递
    fun getAllData() {
        mYexxFragment.getYWXXCommitData()
        mSyrxxFragment!!.getSYRXXCommitData()
        mDlrxxFragment.getDLRXXCommitData()
    }
}

