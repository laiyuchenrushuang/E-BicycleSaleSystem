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
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.seatrend.cd.electricbicyclesalesystem.BuildConfig

import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.activity.CollectBicycleActivity
import com.seatrend.cd.electricbicyclesalesystem.common.BaseFragment
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.database.CodeTableSQLiteUtils
import com.seatrend.cd.electricbicyclesalesystem.util.DeviceScanUtils
import com.seatrend.cd.electricbicyclesalesystem.util.StringUtils
import kotlinx.android.synthetic.main.activity_company_buy_bicycle.*
import kotlinx.android.synthetic.main.fragment_dw_bdxx.*
import org.json.JSONObject
import java.lang.Exception


/**
 * A simple [Fragment] subclass.
 */
class DwBdxxFragment : BaseFragment() {



    private var mDeviceScanUtils: DeviceScanUtils?=null
    private var readyed=false


    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val msgString = msg.obj as String
            when (msg.what) {
                DeviceScanUtils.OTHER_CODE -> if (!TextUtils.isEmpty(msgString)) {

                    Log.i("logtestooo","-  "+msgString)
                    (activity as CollectBicycleActivity).analysisInsuranceData(msgString)

                }

            }
        }
    }
    override fun getLayoutView(inflater: LayoutInflater?, container: ViewGroup?): View {
        return inflater!!.inflate(R.layout.fragment_dw_bdxx, container, false)
    }

    override fun initView() {



        bindEvent()
        (activity as CollectBicycleActivity).getCompanyName()
        onHiddenChanged(false)

    }

    private fun bindEvent(){
        tv_scan.setOnClickListener {
            if (mDeviceScanUtils==null){
                mDeviceScanUtils= DeviceScanUtils(activity,mHandler)
            }
            mDeviceScanUtils!!.startScan()
        }


        btn_next.setOnClickListener {
            if (readyed){
                commitData()
            } else {
                if (BuildConfig.DEBUG) {
                    (activity as CollectBicycleActivity).rb_wc.performClick()
                } else {
                    showToast(getString(R.string.please_scan_insurance_rqcode))
                }

                showToast(getString(R.string.please_scan_insurance_rqcode))

            }

        }

        btn_back.setOnClickListener {
            (activity as CollectBicycleActivity).BdxxLastPerformOnClick()
        }

        sp_bxmc.onItemSelectedListener=object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                (activity as CollectBicycleActivity).getInsuranceMsg(sp_bxmc.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)


    }
    private fun setSpinnerAdapter(spinner: Spinner,list: MutableList<String>) {
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(R.layout.item_spinner__down_common)
        when (spinner.id) {
            R.id.sp_bxmc-> {
                for (s in list) {
                    adapter.add(s)
                }

                spinner.adapter = adapter
            }


        }

    }
    private fun commitData() {

        val map=HashMap<String,String>()
        map.put("bxd",tv_bdh.text.toString())  //保险单
        map.put("bxgsmc",tv_bxgsmc.text.toString())  //保险公司名称
        map.put("bxksrq",tv_qbrq.text.toString())   //保险开始时间
        map.put("bxjsrq",tv_zbrq.text.toString())    //保险结束时间
        map.put("bf",tv_bf.text.toString())      //保费
        map.put("bxcpbm",tv_cpbm.text.toString())  //保险产品编码

        (activity as CollectBicycleActivity).saveInsuranceMsg(map)
    }

    fun setQrImage(msg: String){
        iv_qrcode.setImageBitmap(StringUtils.createQRCode(msg))
        tv_bdh.text=getString(R.string.placeholder)
        tv_qbrq.text=getString(R.string.placeholder)
        tv_zbrq.text=getString(R.string.placeholder)
        tv_bf.text=getString(R.string.placeholder)
        tv_bxrmc.text=getString(R.string.placeholder)
        tv_bxrsfzh.text=getString(R.string.placeholder)
        tv_cph.text=getString(R.string.placeholder)
        tv_cpbm.text=getString(R.string.placeholder)
        tv_bxgsmc.text=getString(R.string.placeholder)
        tv_lxdh.text=getString(R.string.placeholder)
        readyed=false;



    }
    fun setCompanyName(list: MutableList<String>){
        setSpinnerAdapter(sp_bxmc,list)

    }
    fun setInsuranceMessage(msg: String){

        try {
            val jb = JSONObject(msg)
            tv_bdh.text = jb.optString("BDH")
            tv_qbrq.text = jb.optString("BXKSRQ")
            tv_zbrq.text = jb.optString("BXJSRQ")
            tv_bf.text = jb.optString("BF") + "元"
            tv_bxrmc.text = jb.optString("SYR")
            tv_bxrsfzh.text = jb.optString("SFZMHM")
            tv_cph.text = jb.optString("PZH")
            tv_cpbm.text = jb.optString("BXCPDM")
            tv_bxgsmc.text = jb.optString("BXGSMC")
            tv_lxdh.text = jb.optString("LXDH")
            readyed = true;
        } catch (e: Exception) {
            readyed = false;
        }




    }
    override fun onDestroy() {
        super.onDestroy()
        if (mDeviceScanUtils!=null){
            mDeviceScanUtils!!.releaseDeviceScan()
        }
    }




}
