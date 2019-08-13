package com.seatrend.cd.electricbicyclesalesystem.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.activity.LoginActivity
import com.seatrend.cd.electricbicyclesalesystem.common.*
import com.seatrend.cd.electricbicyclesalesystem.entity.BaseEntity
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.entity.UserInfo
import com.seatrend.cd.electricbicyclesalesystem.http.HttpService
import com.seatrend.cd.electricbicyclesalesystem.util.GsonUtils
import com.seatrend.cd.electricbicyclesalesystem.util.LoadingDialog
import kotlinx.android.synthetic.main.activity_user_info.*
import java.lang.Exception

class UserInfoFragment : BaseFragment() {



    override fun getLayoutView(inflater: LayoutInflater?, container: ViewGroup?): View {
        return inflater!!.inflate(R.layout.activity_user_info, container, false)
    }


    override fun initView() {


        if ("0".equals(UserInfo.YHLX)){
            tv_mc.text="厂商名称"
        }else{
            tv_mc.text="销售点名称"
        }


        tv_yhdh.text=UserInfo.YHDH
        tv_xsdmc.text=UserInfo.XSDMC
        tv_name.text=UserInfo.XM

        btn_exit.setOnClickListener {
            exitLogin()

        }

    }



    private fun exitLogin(){
        LoadingDialog.getInstance().showLoadDialog(context)
        val map=HashMap<String,String>()
        map.put("sfzmhm",UserInfo.SFZMHM)
        HttpService.getInstance().getDataFromServer(map,Constants.EXIT_LOGIN,Constants.GET,object : BaseModule() {
            override fun doWork(map: MutableMap<String, String>?, url: String?) {

            }

            override fun doWorkResults(commonResponse: CommonResponse?, isSuccess: Boolean) {

                try {
                    LoadingDialog.getInstance().dismissLoadDialog()
                   val entity= GsonUtils.gson(commonResponse!!.getResponseString(),BaseEntity::class.java)
                    showToast(entity.getMessage())
                    ActivityCollector.finishAll()
                    startActivity(Intent(context, LoginActivity::class.java))
                    activity.finish()
                }catch (e:Exception){
                    e.printStackTrace()
                }

            }
        })
    }


}
