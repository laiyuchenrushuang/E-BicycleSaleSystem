package com.seatrend.cd.electricbicyclesalesystem.activity

import android.Manifest
import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.text.TextUtils
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.entity.LoginEntity
import com.seatrend.cd.electricbicyclesalesystem.entity.UserInfo
import com.seatrend.cd.electricbicyclesalesystem.persenter.LoginPersenter
import com.seatrend.cd.electricbicyclesalesystem.util.*
import com.seatrend.cd.electricbicyclesalesystem.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import java.util.ArrayList
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import android.nfc.NfcManager
import android.content.Context

@Suppress("PLUGIN_WARNING")
class LoginActivity : BaseActivity(), LoginView {


    private var mLoginPersenter: LoginPersenter? = null
    private var sfzhm = "";
    private var FACE_COMPARE_CODE: Int = 11
    private var headPhoto: ByteArray? = null//头像照片
    override fun netWorkTaskSuccess(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()

        if (commonResponse.getUrl().equals(Constants.GET_TREE_BY_GLBM)) {
            val result = commonResponse.getResponseString()
            val parse = JsonParser()
            val json = parse.parse(result) as JsonObject
            val data = json.get("data").asJsonObject
            var bmqc = data.get("bmqc").toString()
            UserInfo.BMQC = bmqc
            MainActivity.username = ed_username.text.toString().trim()
            finish()

        } else if (commonResponse.getUrl().equals(Constants.USER_LOGIN)) {
            try {
                val loginEntity = GsonUtils.gson(commonResponse.getResponseString(), LoginEntity::class.java)
                UserInfo.XM = loginEntity.data.xm
                UserInfo.SFZMHM = loginEntity.data.sfzmhm
                UserInfo.XSDMC = loginEntity.data.xsdmc
                UserInfo.GLBM = loginEntity.data.glbm
                UserInfo.YHLX = loginEntity.data.yhlx
                UserInfo.XSDDM = loginEntity.data.xsddm
                UserInfo.YHDH = loginEntity.data.yhdh

                getBMQC()
                if ("0".equals(UserInfo.YHLX)) {
                    val intent = Intent(this, FactorFunctionListActivity::class.java)
                    startActivity(intent)
                } else {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun netWorkTaskfailed(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()
        showErrorDialog(commonResponse.getResponseString())
    }


    private fun getBMQC() {

        var map = HashMap<String, String>()
        map.put("glbm", UserInfo.GLBM + "")
        mLoginPersenter!!.doNetworkTask(map, Constants.GET_TREE_BY_GLBM)

    }


    override fun initView() {
        intNFC()
        setPageTitle(getString(R.string.electric_bicycle_register))
        mLoginPersenter = LoginPersenter(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AppRequestPermissions()
        }
        btn_next.setOnClickListener {


            if (!SharedPreferencesUtils.getIsFirst()) {
                if (ed_username != null && !TextUtils.isEmpty(ed_username.text.toString())) {
                    doLogin(ed_username.text.toString())
                } else {
                    showToast("输入账号为空，请重新输入")
                }
                //销售商 511502199909099999  510107198208024236
            } else {
                showToast("请先同步代码")
            }

        }
        btn_setting.setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }

        tvRight!!.setText("帮助")
        tvRight!!.setOnClickListener {
            startActivity(Intent(this, HelpActivity::class.java))
        }
        tv_version.text = getString(R.string.cur_version, AppUtils.getVersionName(this))
        btn_system!!.setOnClickListener {
            goNfcReadPlugin()
        }

    }

    private fun intNFC() {

        if ("HC" != AppUtils.getSystemProperty()){
            btn_system.setBackgroundColor(resources.getColor(R.color.black_50))
            btn_system.isEnabled = false
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_login
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun AppRequestPermissions() {


        val permission = ArrayList<String>()
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) !== PackageManager.PERMISSION_GRANTED) {
            permission.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (checkSelfPermission(Manifest.permission.CAMERA) !== PackageManager.PERMISSION_GRANTED) {
            permission.add(Manifest.permission.CAMERA)
        }

        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) !== PackageManager.PERMISSION_GRANTED) {
            permission.add(Manifest.permission.READ_PHONE_STATE)
        }
        if (permission.size > 0) {
            // permissionCount = permission.size

            ActivityCompat.requestPermissions(this@LoginActivity, permission.toTypedArray(), 1)

        }


    }

    private fun doLogin(sfzhm: String) {


        LoadingDialog.getInstance().showLoadDialog(this)
        val map = HashMap<String, String>()
        map.put("sfzmhm", sfzhm)
        mLoginPersenter!!.doNetworkTask(map, Constants.USER_LOGIN)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ID_CARD_READ_CODE && resultCode == Activity.RESULT_OK && data != null) {
            showToast("身份证已读取")
//            data.getStringExtra(Constants.NAME)
            sfzhm = data.getStringExtra(Constants.NUMBER)
//            data.getStringExtra(Constants.ADDRESS)
//            data.getStringExtra(Constants.ADDRESS)
            headPhoto = data.getByteArrayExtra(Constants.PHOTO)

            OtherUtils.goFaceComparePlugin(this, headPhoto, FACE_COMPARE_CODE)
        } else if (requestCode == FACE_COMPARE_CODE && resultCode == Activity.RESULT_OK) {
            doLogin(sfzhm)
        }
    }

}
