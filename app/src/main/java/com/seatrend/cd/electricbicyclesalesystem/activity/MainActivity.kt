package com.seatrend.cd.electricbicyclesalesystem.activity

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.TableLayout
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.adpater.ViewPagerAdapter
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.BaseModule
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.BaseEntity
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.fragment.BusinessFragment
import com.seatrend.cd.electricbicyclesalesystem.fragment.UserInfoFragment
import com.seatrend.cd.electricbicyclesalesystem.fragment.WarningMessageFragment
import com.seatrend.cd.electricbicyclesalesystem.http.HttpService
import com.seatrend.cd.electricbicyclesalesystem.util.GsonUtils
import com.seatrend.cd.electricbicyclesalesystem.util.LoadingDialog
import kotlinx.android.synthetic.main.activity_exit_login.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.ArrayList

@Suppress("PLUGIN_WARNING")
class MainActivity : BaseActivity() {

    companion object {
        var username: String = ""
    }

    private val fragmentList = ArrayList<Fragment>()
    private val pagerTitle = ArrayList<String>()


    override fun getLayout(): Int {
        return R.layout.activity_main
    }


    override fun initView() {
        setPageTitle(getString(R.string.electric_bicycle_register))
        ivBack!!.visibility = View.GONE

        fragmentList.add(BusinessFragment())
        fragmentList.add(WarningMessageFragment())
        fragmentList.add(BusinessDetailsFragment())
        fragmentList.add(UserInfoFragment())

        pagerTitle.add("业务")
        pagerTitle.add("预警")
        pagerTitle.add("统计")
        pagerTitle.add("我的")
        view_pager.adapter = ViewPagerAdapter(supportFragmentManager, fragmentList, pagerTitle)
        tableLayout.setupWithViewPager(view_pager)
        tableLayout.getTabAt(0)!!.setIcon(R.drawable.yewu_iv_seletor)
        tableLayout.getTabAt(1)!!.setIcon(R.drawable.jg_iv_seletor)
        tableLayout.getTabAt(2)!!.setIcon(R.drawable.tj_iv_seletor)
        tableLayout.getTabAt(3)!!.setIcon(R.drawable.wd_iv_seletor)

        view_pager.offscreenPageLimit = 4

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> setPageTitle(getString(R.string.electric_bicycle_register))
                    1 -> setPageTitle(getString(R.string.yjxx))
                    2 -> setPageTitle(getString(R.string.ywtj))
                    3 -> setPageTitle(getString(R.string.mine))

                    else -> setPageTitle("")

                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })











        ivRight!!.setOnClickListener {

            //  startActivity(Intent(this, UserInfoFragment::class.java))
        }


    }


    private var firstPressedTime: Long = 0
    private var secondPressedTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            secondPressedTime = System.currentTimeMillis()
            if (secondPressedTime - firstPressedTime < 2000) {
                finish()
                return true
            } else {
                firstPressedTime = secondPressedTime
                showToast(getString(R.string.exit_press_again))
                return false
            }

        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        exitLogin(username)
    }

    private fun exitLogin(username: String) {
        LoadingDialog.getInstance().showLoadDialog(this)
        val map=HashMap<String,String>()
        map.put("sfzmhm", username)
        HttpService.getInstance().getDataFromServer(map, Constants.EXIT_LOGIN, Constants.GET,object : BaseModule() {
            override fun doWork(map: MutableMap<String, String>?, url: String?) {

            }

            override fun doWorkResults(commonResponse: CommonResponse?, isSuccess: Boolean) {

                try {
                    LoadingDialog.getInstance().dismissLoadDialog()
                    val entity= GsonUtils.gson(commonResponse!!.getResponseString(), BaseEntity::class.java)
                    showToast(entity.getMessage())
                }catch (e: Exception){
                    e.printStackTrace()
                }

            }
        })
    }
}
