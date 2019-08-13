package com.seatrend.cd.electricbicyclesalesystem.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import butterknife.OnClick
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.database.CodeTableSQLiteUtils
import com.seatrend.cd.electricbicyclesalesystem.entity.BaseEntity
import com.seatrend.cd.electricbicyclesalesystem.entity.CarMessageEntity
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.entity.UserInfo
import com.seatrend.cd.electricbicyclesalesystem.persenter.CarMessagePersenter
import com.seatrend.cd.electricbicyclesalesystem.util.GsonUtils
import com.seatrend.cd.electricbicyclesalesystem.util.LoadingDialog
import com.seatrend.cd.electricbicyclesalesystem.util.OtherUtils
import com.seatrend.cd.electricbicyclesalesystem.view.CarMessageView
import kotlinx.android.synthetic.main.activity_car_message.*
import kotlinx.android.synthetic.main.activity_car_message.view.*
import kotlinx.android.synthetic.main.fragment_jcsj.view.*
import java.lang.Exception

class CarMessageActivity : BaseActivity(),CarMessageView {




    private var mCarMessagePersenter: CarMessagePersenter?=null
    companion object {
        var mCarMessageEntity: CarMessageEntity?=null
    }


    override fun initView() {
        setPageTitle("车辆基础数据")
        mCarMessagePersenter= CarMessagePersenter(this)
        bindEvent();
        setSpinnerAdapter(sp_ywlx)
        setSpinnerAdapter(sp_csys1)
        setSpinnerAdapter(sp_csys2)
        initData()
    }

    private fun initData() {
        val dataBean = mCarMessageEntity!!.data;
        tv_clpp.text=dataBean.clpp
        tv_clxh.text=dataBean.clxh
        tv_zcbm.text=dataBean.zcbm
        tv_cjh.text=dataBean.clsbdm
        tv_djh.text=dataBean.djh
        tv_pzhm.text=dataBean.hphm
        tv_zbzl.text=dataBean.zbzl+"kg"
        tv_sczl.text=dataBean.sczbzl+"kg"
        tv_sjss.text=dataBean.sjss+"km/h"
        et_hphm.setText(dataBean.hphm)
        OtherUtils.setSpinnerToDmz(dataBean.csys1,sp_csys1)
        OtherUtils.setSpinnerToDmz(dataBean.csys2,sp_csys2)

    }

    override fun getLayout(): Int {

        return R.layout.activity_car_message
    }

    private fun bindEvent(){
        btn_next.setOnClickListener {
           // startActivity(Intent(this,CarPhotoActivity::class.java))
            commitData()
        }
    }

    private fun setSpinnerAdapter(spinner: Spinner) {
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(R.layout.item_spinner__down_common)
        when (spinner.id) {
            R.id.sp_csys1, R.id.sp_csys2-> {
                val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.CSYS)
                for (db in sfzmmcList) {
                    val dmz = db.getDmz()
                    val dmsm1 = db.getDmsm1()
                    adapter.add(dmz + ":" + dmsm1)
                }

                spinner.adapter = adapter
            }
            R.id.sp_ywlx -> {
                val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.YWLX)

                for (db in sfzmmcList) {
                    val dmz = db.getDmz()
                    val dmsm1 = db.getDmsm1()
                    adapter.add(dmz + ":" + dmsm1)
                }

                spinner.adapter = adapter



            }


        }

    }

    private fun commitData(){




       val ywlxText=sp_ywlx.selectedItem.toString()
       val spCsysText1=sp_csys1.selectedItem.toString()
       val spCsysText2=sp_csys2.selectedItem.toString()
        val ywlx=ywlxText.split(":")[0]
        val csys1=spCsysText1.split(":")[0]
        val csys2=spCsysText2.split(":")[0]

        //val  map=HashMap<String,String>()

        val map = OtherUtils.objcetToMap(mCarMessageEntity!!.data)
        map["ywlx"] = ywlx
        map["csys1"] = csys1
        map["csys2"] = csys2
        map["hphm"] = et_hphm.text.toString()
        map["sfzmhm"] = UserInfo.SFZMHM
        LoadingDialog.getInstance().showLoadDialog(this)
        mCarMessagePersenter!!.doNetworkTask(map,Constants.ADD_CAR_MSG)
    }

    override fun netWorkTaskSuccess(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()
        try {
            val carDetailsActivity = GsonUtils.gson(commonResponse.getResponseString(), CarMessageEntity::class.java)
            mCarMessageEntity!!.data.lsh=carDetailsActivity.data.lsh
            mCarMessageEntity!!.data.xh=carDetailsActivity.data.xh
            showToast(carDetailsActivity.getMessage())
            startActivity(Intent(this,CarPhotoActivity::class.java))

        }catch (e:Exception){
            showToast(e.message.toString())
        }

    }

    override fun netWorkTaskfailed(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()
        showErrorDialog(commonResponse.getResponseString())
    }
}
