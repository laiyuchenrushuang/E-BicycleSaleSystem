package com.seatrend.cd.electricbicyclesalesystem.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.database.CodeTableSQLiteUtils
import com.seatrend.cd.electricbicyclesalesystem.entity.CodeEntity
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.persenter.SettingPersenter
import com.seatrend.cd.electricbicyclesalesystem.util.GsonUtils
import com.seatrend.cd.electricbicyclesalesystem.util.LoadingDialog
import com.seatrend.cd.electricbicyclesalesystem.util.SharedPreferencesUtils
import com.seatrend.cd.electricbicyclesalesystem.view.SettingView
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : BaseActivity() , SettingView {

    private var mSettingPersenter: SettingPersenter?=null

    private var count=0;


    override fun netWorkTaskSuccess(commonResponse: CommonResponse) {

        try {
            if (commonResponse.getUrl().equals(Constants.GET_CODE) || commonResponse.getUrl().equals(Constants.GET_CODE_QH)){
                val codeEntity = GsonUtils.gson(commonResponse.getResponseString(), CodeEntity::class.java)
                if(count==0){
                    CodeTableSQLiteUtils.deleteAll()
                }
                CodeTableSQLiteUtils.insert(codeEntity.data)

                count++
                if (count==7){
                    showToast("同步成功")
                    SharedPreferencesUtils.setIsFirst(false)
                    LoadingDialog.getInstance().dismissLoadDialog()
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun netWorkTaskfailed(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()
        if (count==0){
            showErrorDialog(commonResponse.getResponseString())
        }
        count++

    }


    override fun initView() {
        setPageTitle(getString(R.string.setting))
        tv_update_time.text=Constants.UPDATA_TIME
        mSettingPersenter= SettingPersenter(this)
        btn_server_setting.setOnClickListener {
            startActivity(Intent(this,ServiceSettingActivity::class.java))
        }
        btn_code_syn.setOnClickListener {
            count=0
            LoadingDialog.getInstance().showLoadDialog(this)
            getDataCode("00",Constants.CSYS)//颜色值
            getDataCode("00",Constants.SYXZ)//使用性质
            getDataCode("01",Constants.HDFS)//获得方式
            getDataCode("50",Constants.SFZMMC)//身份证明名称
            getDataCode("01",Constants.YWLX)//业务类型
            getDataCode("00",Constants.LLZM)//来历证明
            getDataCode("00",Constants.CLYT)//车辆用途
            getDataQhCode()//行驶区域
        }
    }

    private fun getDataCode(xtlb: String,dmlb: String){
        var map= HashMap<String,String>()
        map.put("xtlb",xtlb)
        map.put("dmlb",dmlb)
        mSettingPersenter!!.doNetworkTask(map,Constants.GET_CODE)
    }

    private fun getDataQhCode(){
        mSettingPersenter!!.doNetworkTask(HashMap<String,String>(),Constants.GET_CODE_QH)

    }
    override fun getLayout(): Int {
        return R.layout.activity_setting
    }


}
