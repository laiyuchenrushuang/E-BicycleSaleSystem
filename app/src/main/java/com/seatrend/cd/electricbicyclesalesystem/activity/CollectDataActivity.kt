package com.seatrend.cd.electricbicyclesalesystem.activity

import android.support.v4.app.Fragment
import android.util.Log
import android.widget.RadioGroup
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.entity.CarBean
import com.seatrend.cd.electricbicyclesalesystem.entity.UserInfo
import com.seatrend.cd.electricbicyclesalesystem.fragment.DlrxxFragment
import com.seatrend.cd.electricbicyclesalesystem.fragment.SyrxxFragment
import com.seatrend.cd.electricbicyclesalesystem.fragment.YwxxFragment
import com.seatrend.cd.electricbicyclesalesystem.util.LoadingDialog
import kotlinx.android.synthetic.main.activity_collect_data.*

class CollectDataActivity : BaseActivity() {

    companion object {
        var mCarZcMsgData = CarBean()
    }

    var mYwxxFragment = YwxxFragment()
    var mSyrxxFragment = SyrxxFragment()
    var mDlrxxFragment = DlrxxFragment()

    private var lastFragment: Fragment? = null


    override fun initView() {
        setPageTitle(getString(R.string.qtxywxx))
        rb_ywxx.isChecked = true
        view1.isSelected = true
        view2.isSelected = false
        view3.isSelected = false
        switchFrament(lastFragment, mYwxxFragment)
        bindEvent()
    }

    private fun bindEvent() {
        m_radio_group.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.rb_ywxx -> {
                        switchFrament(lastFragment, mYwxxFragment)
                        view1.isSelected = true
                        view2.isSelected = false
                        view3.isSelected = false
                    }

                    R.id.rb_syrxx -> {
                        switchFrament(lastFragment, mSyrxxFragment)
                        view1.isSelected = false
                        view2.isSelected = true
                        view3.isSelected = false
                    }
                    R.id.rb_dlrxx -> {
                        switchFrament(lastFragment, mDlrxxFragment)
                        view1.isSelected = false
                        view2.isSelected = false
                        view3.isSelected = true
                    }

                }

            }
        })
    }

    override fun getLayout(): Int {
        return R.layout.activity_collect_data
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

    public fun updateCarMessage(map: HashMap<String, String>, taskType: String) {
        map.put("lsh", CheckDataActivity.mCarMessageEntity!!.data.lsh)
        map.put("xh", CheckDataActivity.mCarMessageEntity!!.data.xh)
        map.put("xss", UserInfo.SFZMHM)
        // this.taskType=taskType
        LoadingDialog.getInstance().showLoadDialog(this)
        // mCollectBicyclePersenter!!.doNetworkTask(map,Constants.UPDATE_CAR_MSG)
    }

    fun donext() {
        rb_syrxx.isChecked = true

    }

    fun doDLRnext() {
        rb_dlrxx.isChecked = true
        mYwxxFragment.getYWXXCommitData()
    }

    fun doYWXXnext() {
        rb_ywxx.isChecked = true
    }

    //各个页面的所有信息传递
    fun getAllData() {
        mYwxxFragment.getYWXXCommitData()
        mSyrxxFragment.getSYRXXCommitData()
        mDlrxxFragment.getDLRXXCommitData()
    }
}
