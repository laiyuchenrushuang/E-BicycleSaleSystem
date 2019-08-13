package com.seatrend.cd.electricbicyclesalesystem.activity

import android.content.Intent
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.view.LoginView
import android.widget.EditText
import android.widget.RelativeLayout
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.LoginEntity
import com.seatrend.cd.electricbicyclesalesystem.entity.UserInfo
import com.seatrend.cd.electricbicyclesalesystem.persenter.LoginPersenter
import com.seatrend.cd.electricbicyclesalesystem.util.GsonUtils
import com.seatrend.cd.electricbicyclesalesystem.util.LoadingDialog


/**
 * Created by ly on 2019/7/1 13:45
 *
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
@Suppress("PLUGIN_WARNING")
class SystemLoginActivity : BaseActivity(), LoginView {
    var ed_sfz: EditText? = null
    var rl_system_next: RelativeLayout? = null
    private var mLoginPersenter: LoginPersenter? = null

    override fun initView() {
        setPageTitle(getString(R.string.system_login_title))
        ed_sfz = findViewById(R.id.ed_sfzhm)
        rl_system_next = findViewById(R.id.rl_system_next)
        mLoginPersenter= LoginPersenter(this)
        rl_system_next!!.setOnClickListener{
            if (ed_sfz != null && !"".equals(ed_sfz!!.getText())) {
                login();
            } else {
                showToast("请输入身份证号码");
            }
        }
    }

    private fun login() {
        LoadingDialog.getInstance().showLoadDialog(this)
        val map = HashMap<String, String>()
        val sfzhm = ed_sfz!!.getText().toString().trim()
        map.put("sfzmhm", sfzhm)
        mLoginPersenter!!.doNetworkTask(map, Constants.USER_LOGIN)
    }

    override fun getLayout(): Int {
        return R.layout.system_login
    }

    override fun netWorkTaskSuccess(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()

        if (commonResponse.getUrl().equals(Constants.GET_TREE_BY_GLBM)) {
            val result = commonResponse.getResponseString()
            val parse = JsonParser()
            val json = parse.parse(result) as JsonObject
            val data = json.get("data").asJsonObject
            var bmqc = data.get("bmqc").toString()
            UserInfo.BMQC = bmqc
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

    private fun getBMQC() {

        var map = HashMap<String, String>()
        map.put("glbm", UserInfo.GLBM + "")
        mLoginPersenter!!.doNetworkTask(map, Constants.GET_TREE_BY_GLBM)

    }

    override fun netWorkTaskfailed(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()
        showErrorDialog(commonResponse.getResponseString())
    }

}