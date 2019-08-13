package com.seatrend.cd.electricbicyclesalesystem.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.common.Constants.Companion.CAR_BG
import com.seatrend.cd.electricbicyclesalesystem.common.Constants.Companion.CAR_BH
import com.seatrend.cd.electricbicyclesalesystem.common.Constants.Companion.CAR_CY
import com.seatrend.cd.electricbicyclesalesystem.common.Constants.Companion.CAR_ZC
import com.seatrend.cd.electricbicyclesalesystem.common.Constants.Companion.CAR_ZX
import com.seatrend.cd.electricbicyclesalesystem.entity.CarBean
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.entity.UserInfo
import com.seatrend.cd.electricbicyclesalesystem.fragment.YwxxFragment
import com.seatrend.cd.electricbicyclesalesystem.persenter.CarPhotoPersenter
import com.seatrend.cd.electricbicyclesalesystem.util.CarHphmUtils
import com.seatrend.cd.electricbicyclesalesystem.util.LoadingDialog
import com.seatrend.cd.electricbicyclesalesystem.view.CarPhotoView
import kotlinx.android.synthetic.main.activity_hpbf.*

class HpbfActivity : BaseActivity(), CarPhotoView {

    private var mCarPhotoPersenter: CarPhotoPersenter? = null

    override fun netWorkTaskSuccess(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()
        if(commonResponse.getUrl() == Constants.SAVE_CAR_CY_URL){   //查验的销售登记
            intent = Intent(this, RemindHPBFactivity::class.java)
            startActivity(intent)
        }else if(commonResponse.getUrl() == Constants.POST_HPBF && intent.getStringExtra(Constants.TASK) == CAR_CY){  // 注册登记
            intent = Intent(this, RemindDZPZactivity::class.java)
            startActivity(intent)
        }else if(commonResponse.getUrl() == Constants.POST_HPBF && intent.getStringExtra(Constants.TASK) == CAR_BH){  //补换号牌
            intent = Intent(this, RemindHPBFactivity::class.java)
            startActivity(intent)
        }

    }

    override fun netWorkTaskfailed(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()
        showErrorDialog(commonResponse.getResponseString())
    }

    val CAR_DZPZ: Boolean = true //无电子凭证
    val ZCBM: String = "zcbm" //整车编码or车牌

    override fun initView() {
        setPageTitle(getString(R.string.hpbf))
        mCarPhotoPersenter = CarPhotoPersenter(this)
        tv_zcbm.setText(intent.getStringExtra(ZCBM))
        et_hphm.transformationMethod = CarHphmUtils.TransInformation()
        bindEvent()
    }

    private fun bindEvent() {
        btn_ok.setOnClickListener {
            if (et_hphm.text == null || et_hphm.text.toString() == null || !CarHphmUtils.macherString(et_hphm.text.toString().toUpperCase())) {
                showErrorDialog("号牌号码格式正确，请正确输入号牌号码")
                return@setOnClickListener
            }
            when (intent.getStringExtra(Constants.TASK)) {

                CAR_CY -> {
                    CarPhotoActivity.mClcyCommitData.hphm = et_hphm.text.toString().toUpperCase()
                    var gson = Gson()
                    var result = gson.toJson(CarPhotoActivity.mClcyCommitData)
                    LoadingDialog.getInstance().showLoadDialog(this)
                    Log.d("lylog", "  [hphm]result = " + result)
                    mCarPhotoPersenter!!.uploadFileAndData(result, Constants.SAVE_CAR_CY_URL)
                }
                CAR_ZC -> {
                    var map = HashMap<String,String>()
                    map["xh"] = YwxxFragment.mYwSearchBean.data.vehicleTemp.xh
                    map["hphm"] = et_hphm.text.toString()
                    map["username"] = UserInfo.SFZMHM
                    map["ywzl"] = "0" //注册登记
                    mCarPhotoPersenter!!.doNetworkTask(map, Constants.POST_HPBF)  //业务类的接口
                }
                CAR_BH ->{
                    var map = HashMap<String,String>()
                    map["xh"] = YwxxFragment.mYwSearchBean.data.vehicleTemp.xh
                    map["hphm"] = et_hphm.text.toString()
                    map["username"] = UserInfo.SFZMHM
                    map["ywzl"] = "1" //补换号牌登记
                    mCarPhotoPersenter!!.doNetworkTask(map, Constants.POST_HPBF)  //业务类的接口
                }
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_hpbf
    }


}
