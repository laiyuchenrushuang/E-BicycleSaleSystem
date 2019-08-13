package com.seatrend.cd.electricbicyclesalesystem.activity

import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.widget.EditText
import android.widget.RelativeLayout
import com.seatrend.cd.electricbicyclesalesystem.BuildConfig
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.CarMessageEntity
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.entity.YwSearchBean
import com.seatrend.cd.electricbicyclesalesystem.fragment.DlrxxFragment
import com.seatrend.cd.electricbicyclesalesystem.fragment.YwxxFragment
import com.seatrend.cd.electricbicyclesalesystem.persenter.CarLoginPersenter
import com.seatrend.cd.electricbicyclesalesystem.util.CarUtils
import com.seatrend.cd.electricbicyclesalesystem.util.GsonUtils
import com.seatrend.cd.electricbicyclesalesystem.util.LoadingDialog
import com.seatrend.cd.electricbicyclesalesystem.view.CarLoginView

/**
 * Created by ly on 2019/7/1 16:46
 *
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
class CarCodeCYActivity : BaseActivity(), CarLoginView {
    private var mCarLoginPersenter: CarLoginPersenter? = null

    val ZCBM:String = "zcbm" //整车编码or车牌



    override fun netWorkTaskSuccess(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()
        try {
            if(commonResponse.getUrl() == Constants.GET_CAR_MSG){
                CarAllMessageActivity.mCarMessageEntity= GsonUtils.gson(commonResponse.getResponseString(), CarMessageEntity::class.java)
                intent.setClass(this,CarAllMessageActivity::class.java)
                intent.putExtra(ZCBM,ed_zcbm!!.getText().toString().trim())
                startActivity(intent)
            }else{
                val carMessageEntity = GsonUtils.gson(commonResponse.getResponseString(), YwSearchBean::class.java)
                if (carMessageEntity.data.vehFlowMain.ywlx != CarUtils.taskLXMap[intent.getStringExtra(Constants.TASK)]){
                    showLog(" yelx1 = "+carMessageEntity.data.vehFlowMain.ywlx)
                    showLog(" yelx2 = "+CarUtils.taskLXMap[intent.getStringExtra(Constants.TASK)])
                    showLog(" yelx3 = "+(carMessageEntity.data.vehFlowMain.ywlx != CarUtils.taskLXMap[intent.getStringExtra(Constants.TASK)]))
                    showLog(" yelx3 = "+(carMessageEntity.data.vehFlowMain.ywlx.equals(CarUtils.taskLXMap[intent.getStringExtra(Constants.TASK)]) ))

                    showErrorDialog("该车辆不支持此业务,此车业务是："+CarUtils.getTaskLXMap[carMessageEntity.data.vehFlowMain.ywlx])
                }
                when(intent.getStringExtra(Constants.TASK)){
//                Constants.CAR_VIN->{
//                    if (!TextUtils.isEmpty(intent.getStringExtra(Constants.POSITION)) && Constants.TO_VIN.equals(intent.getStringExtra(Constants.POSITION))) {
//                        val intent = Intent(this, VincollectActivity::class.java)
//                        intent.putExtra(Constants.LSH, carMessageEntity.data.vehicleTemp.lsh)
//                        intent.putExtra(Constants.XH, carMessageEntity.data.vehicleTemp.xh)
//                        startActivity(intent)
//                    }else{
//                        CarMessageActivity.mCarMessageEntity=carMessageEntity
//                        intent.setClass(this,CarMessageActivity::class.java)
//                        startActivity(intent)
//                    }
//                }
                    Constants.CAR_ZC->{
                        YwxxFragment.mYwSearchBean = carMessageEntity  //因为在代理人界面提交 所以数据传过去
                        sendActivityEvent(CollectDataActivity::class.java)
                    }
                    Constants.CAR_BG->{
                        CarChangeRegisterActivity.mYwSearchBean = carMessageEntity
                        sendActivityEvent(CarChangeRegisterActivity::class.java)
                    }
                    Constants.CAR_ZY->{
                        CarChangeRegisterActivity.mYwSearchBean = carMessageEntity
                        sendActivityEvent(CarChangeRegisterActivity::class.java)
                    }
                    Constants.CAR_BH->{
                        sendActivityEvent(HpbfActivity::class.java)
                    }
                    Constants.CAR_ZX->{
                        CarChangeRegisterActivity.mYwSearchBean = carMessageEntity
                        sendActivityEvent(CarChangeRegisterActivity::class.java)
                    }
                }
            }
        }catch (e:Exception){
            Log.d("lylog","ssssss")
            showToast(e.message.toString())
        }
    }

    private fun sendActivityEvent(activity: Class<*>) {
        intent.setClass(this,activity)
        intent.putExtra(ZCBM,ed_zcbm!!.getText().toString().trim())
        startActivity(intent)
    }

    override fun netWorkTaskfailed(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()
        showErrorDialog(commonResponse.getResponseString())
    }

    var ed_zcbm: EditText? = null
    var rl_zcbm_cy_next: RelativeLayout? = null
    override fun initView() {
        setPageTitle("业务查询")
        mCarLoginPersenter = CarLoginPersenter(this)
        ed_zcbm = findViewById(R.id.ed_zcbm)
        rl_zcbm_cy_next = findViewById(R.id.rl_zcbm_cy_next)
        rl_zcbm_cy_next!!.setOnClickListener {
            if (null != ed_zcbm && !"".equals(ed_zcbm!!.getText())) {
                searchByzcbm(ed_zcbm!!.getText().toString().trim());
            } else {
                showToast(getString(R.string.please_enter_zcbm))
            }
        }
        if (BuildConfig.DEBUG){
            ed_zcbm!!.setText("117321900000204") //117321900000170
        }
    }

    private fun searchByzcbm(zcbm: String) {
        val map = HashMap<String, String>()

        LoadingDialog.getInstance().showLoadDialog(this)

        if ("2" == intent.getStringExtra(Constants.LX)) {
            map.put("zcbm", zcbm)
            mCarLoginPersenter!!.doNetworkTask(map, Constants.FACTOTY_GET_CAR_MSG)
        } else {
            map.put("vehicleCode", zcbm)
            mCarLoginPersenter!!.doNetworkTask(map, Constants.GET_CAR_MSG)
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_zcbm_cy
    }
}