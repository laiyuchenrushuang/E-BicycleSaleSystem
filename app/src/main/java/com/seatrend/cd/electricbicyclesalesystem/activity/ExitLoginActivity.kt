package com.seatrend.cd.electricbicyclesalesystem.activity

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.ActivityCollector
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.BaseModule
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.BaseEntity
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.entity.UserInfo
import com.seatrend.cd.electricbicyclesalesystem.http.HttpService
import com.seatrend.cd.electricbicyclesalesystem.util.GsonUtils
import com.seatrend.cd.electricbicyclesalesystem.util.LoadingDialog
import com.seatrend.cd.electricbicyclesalesystem.util.OtherUtils
import kotlinx.android.synthetic.main.activity_exit_login.*
import java.lang.Exception

class ExitLoginActivity : BaseActivity() {


    override fun initView() {
        setPageTitle("退出登录")

        iv_read.setOnClickListener {
            goNfcReadPlugin()
        }
        btn_exit.setOnClickListener {
            if (!TextUtils.isEmpty(et_sfzhm.text.toString()) && et_sfzhm.text.toString().trim().length>0){
                exitLogin();
            }else{
                showToast("身份证号码不可为空")
            }

        }
    }

    override fun getLayout(): Int {

        return R.layout.activity_exit_login;
    }
    private fun exitLogin(){
        LoadingDialog.getInstance().showLoadDialog(this)
        val map=HashMap<String,String>()
        map.put("sfzmhm", et_sfzhm.text.toString())
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==ID_CARD_READ_CODE && resultCode== Activity.RESULT_OK && data!=null){
            showToast("身份证已读取")
            et_sfzhm.setText(data.getStringExtra(Constants.NUMBER))

        }
    }
}
