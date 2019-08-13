package com.seatrend.cd.electricbicyclesalesystem.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import com.seatrend.cd.electricbicyclesalesystem.BuildConfig
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.CarMessageEntity
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.entity.UserInfo
import com.seatrend.cd.electricbicyclesalesystem.persenter.CarLoginPersenter
import com.seatrend.cd.electricbicyclesalesystem.util.DeviceScanUtils
import com.seatrend.cd.electricbicyclesalesystem.util.GsonUtils
import com.seatrend.cd.electricbicyclesalesystem.util.LoadingDialog
import com.seatrend.cd.electricbicyclesalesystem.view.CarLoginView
import kotlinx.android.synthetic.main.activity_car_login.*

class CarLoginActivity : BaseActivity(),CarLoginView {


    private var type:String?=null
    private var mCarLoginPersenter: CarLoginPersenter?=null


    public var mDeviceScanUtils: DeviceScanUtils?=null


    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val msgString = msg.obj as String
            when (msg.what) {
                DeviceScanUtils.OTHER_CODE -> if (!TextUtils.isEmpty(msgString)) {
                    queryCarMessageByTmh(msgString)
                }
            }
        }
    }
    override fun initView() {
        setPageTitle(getString(R.string.car_query))

        mCarLoginPersenter= CarLoginPersenter(this)
        // 0 是公司   1是个人
        type=intent.getStringExtra(Constants.TYPE)

        btn_scan_bm.setOnClickListener {
            if (mDeviceScanUtils==null){
                mDeviceScanUtils= DeviceScanUtils(this,mHandler)
            }
            mDeviceScanUtils!!.startScan()
        }

        btn_query.setOnClickListener {

            val zcbm=et_bm.text.toString()
            if(TextUtils.isEmpty(zcbm)){
                showToast(getString(R.string.please_enter_zcbm))

            }else{
                queryCarMessageByZcbm(zcbm)

                //startActivity(Intent(this,CheckDataActivity::class.java))
            }

        }

        if (BuildConfig.DEBUG){
            et_bm!!.setText("117321900000204") //117321900000170
        }



    }

    override fun getLayout(): Int {
        return R.layout.activity_car_login
    }

    private fun queryCarMessageByZcbm(zcbm: String){

        val map=HashMap<String,String>()
         map.put("zcbm", zcbm)
        LoadingDialog.getInstance().showLoadDialog(this)

        //0是 厂商数据录入登录   1是销售商数据录入登录
        if("0"== intent.getStringExtra(Constants.TASK)){
            mCarLoginPersenter!!.doNetworkTask(map,Constants.FACTOTY_GET_CAR_MSG)
        }else{
            mCarLoginPersenter!!.doNetworkTask(map,Constants.GET_CAR_MSG)
        }

    }
    private fun queryCarMessageByTmh(tmh: String){

        val map=HashMap<String,String>()
        map.put("tmh", tmh)
        LoadingDialog.getInstance().showLoadDialog(this)

        //0是 厂商数据录入登录   1是销售商数据录入登录
        if("0" == intent.getStringExtra(Constants.TASK)){
            mCarLoginPersenter!!.doNetworkTask(map,Constants.FACTOTY_GET_CAR_MSG)
        }else{
            mCarLoginPersenter!!.doNetworkTask(map,Constants.GET_CAR_MSG)
        }

    }
    override fun netWorkTaskSuccess(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()
        try {
            val carMessageEntity = GsonUtils.gson(commonResponse.getResponseString(), CarMessageEntity::class.java)
            if ("0"== intent.getStringExtra(Constants.TASK)){
                if (!TextUtils.isEmpty(intent.getStringExtra(Constants.POSITION)) && Constants.TO_VIN.equals(intent.getStringExtra(Constants.POSITION))) {
                    val intent = Intent(this, VincollectActivity::class.java)
                    intent.putExtra(Constants.LSH, carMessageEntity.data.lsh)
                    intent.putExtra(Constants.XH, carMessageEntity.data.xh)
                    startActivity(intent)
                }else{
                    CarMessageActivity.mCarMessageEntity=carMessageEntity
                    intent.setClass(this,CarMessageActivity::class.java)
                    startActivity(intent)
                }


            }else{
                CheckDataActivity.mCarMessageEntity=GsonUtils.gson(commonResponse.getResponseString(), CarMessageEntity::class.java)
                intent.setClass(this,CheckDataActivity::class.java)
                startActivity(intent)
            }

        }catch (e:Exception){
            showToast(e.message.toString())
        }

    }

    override fun netWorkTaskfailed(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()
        showErrorDialog(commonResponse.getResponseString())
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mDeviceScanUtils!=null){
            mDeviceScanUtils!!.releaseDeviceScan()
            mDeviceScanUtils=null;
        }
    }

    override fun onPause() {
        super.onPause()

    }


    private var firstPressedTime: Long = 0
    private var secondPressedTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {

        return super.onKeyDown(keyCode, event)
    }

}
