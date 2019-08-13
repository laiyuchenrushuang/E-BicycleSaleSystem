package com.seatrend.cd.electricbicyclesalesystem.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.gson.JsonObject
import com.google.gson.JsonParser

import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.activity.CollectBicycleActivity
import com.seatrend.cd.electricbicyclesalesystem.common.BaseFragment
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.database.CodeTableSQLiteUtils
import com.seatrend.cd.electricbicyclesalesystem.util.DeviceScanUtils
import com.seatrend.cd.electricbicyclesalesystem.util.OtherUtils
import kotlinx.android.synthetic.main.activity_car_login.*
import kotlinx.android.synthetic.main.fragment_jcsj.*

/**
 * A simple [Fragment] subclass.
 */
class DwJcsjFragment : BaseFragment() {

    public var mDeviceScanUtils: DeviceScanUtils? = null

    var cccbh: String? = null
    var cpbh: String? = null;
    var clzzs: String? = null;
    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val msgString = msg.obj as String
            Log.d("lylog", "msgString = " + msgString);
            if (!TextUtils.isEmpty(msgString)) {
                Log.d("lylog", "begin ");
                var result :JsonObject ?=null;
                try {
                    val parser = JsonParser()
                    val jsObject = parser.parse(msgString) as JsonObject
                    result = jsObject.asJsonObject
                }catch (e:Exception){

                }

                if (result != null && result.get("CCC证书编号") != null &&result.get("产品合格证证书编号") != null &&
                        result.get("制造商") != null && result.get("制造商").getAsString() != null &&
                        result.get("CCC证书编号").getAsString() != null && result.get("产品合格证证书编号").getAsString() != null

                        ) {
                    cccbh = result.get("CCC证书编号").getAsString()
                    cpbh = result.get("产品合格证证书编号").getAsString()
                    clzzs = result.get("制造商").getAsString()
                    tv_cpzsbh!!.setText(cccbh)
                    tv_ccczsbh!!.setText(cpbh)
                    tv_clzzs!!.setText(clzzs)
                } else {
                    tv_cpzsbh!!.setText("空")
                    tv_ccczsbh!!.setText("空")
                    tv_clzzs!!.setText("空")
                }
            }
        }
    }

    override fun getLayoutView(inflater: LayoutInflater?, container: ViewGroup?): View {

        return inflater!!.inflate(R.layout.fragment_jcsj, container, false)
    }

    override fun initView() {

         setSpinnerAdapter(sp_csys1)
         setSpinnerAdapter(sp_csys2)


        initdata()
        btn_next.setOnClickListener {
            commitData()
        }
    }

    private fun initdata() {

        var carMessageData=CollectBicycleActivity.mCarMessageEntity!!.data

        tv_zcbm.text = carMessageData.zcbm
//        tv_cjh.text=carMessageData.clsbdm
        tv_djh.text = carMessageData.djh
        tv_pzhm.text = carMessageData.hphm
        tv_zbzl.text = carMessageData.zbzl + "kg"
        tv_sczl.text = carMessageData.sczbzl + "kg"
        tv_clxh.text = carMessageData.clxh
        tv_clpp.text = carMessageData.clpp

        OtherUtils.setSpinnerToDmz(carMessageData.csys1, sp_csys1)
        OtherUtils.setSpinnerToDmz(carMessageData.csys2, sp_csys2)

        iv_smcx.setOnClickListener {
            if (mDeviceScanUtils == null) {
                mDeviceScanUtils = DeviceScanUtils(context, mHandler)
            }
            mDeviceScanUtils!!.startScan()
        }

    }


    private fun setSpinnerAdapter(spinner: Spinner) {
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(R.layout.item_spinner__down_common)
        when (spinner.id) {
            R.id.sp_csys1, R.id.sp_csys2 -> {
                val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.CSYS)
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

        val csys1 = sp_csys1.selectedItem.toString().split(":")[0]
        val csys2 = sp_csys2.selectedItem.toString().split(":")[0]
        val map = HashMap<String, String>()
        map.put("csys1", csys1)
        map.put("csys2", csys2)
        map.put("cccbh", cccbh.toString())
        map.put("cphgzbh", cpbh.toString())
        map.put("clzzs", clzzs.toString())
        (activity as CollectBicycleActivity).updateCarMessage(map, "1")
    }




}
