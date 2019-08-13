package com.seatrend.cd.electricbicyclesalesystem.fragment


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner

import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.activity.CollectDataActivity
import com.seatrend.cd.electricbicyclesalesystem.activity.CollectDataActivity.Companion.mCarZcMsgData
import com.seatrend.cd.electricbicyclesalesystem.common.BaseFragment
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.database.CodeTableSQLiteUtils
import com.seatrend.cd.electricbicyclesalesystem.util.CarUtils
import kotlinx.android.synthetic.main.activity_exit_login.*
import kotlinx.android.synthetic.main.fragment_syrxx.*
import kotlinx.android.synthetic.main.fragment_syrxx.btn_next
import kotlinx.android.synthetic.main.fragment_yexx.*


/**
 * A simple [Fragment] subclass.
 */
class SyrxxFragment : BaseFragment() {
    var mActivity: CollectDataActivity? = null


    override fun getLayoutView(inflater: LayoutInflater?, container: ViewGroup?): View {
        return inflater!!.inflate(R.layout.fragment_syrxx, container, false)
    }

    override fun initView() {
        setAdapterData(sp_sfzmmc)
        setAdapterData(sp_szdq)
        bindEvent()
    }

    private fun setAdapterData(sp: Spinner) {
        val adapter = ArrayAdapter<String>(activity, R.layout.my_simple_spinner_item)
        adapter.setDropDownViewResource(R.layout.item_spinner__down_common)
        when (sp) {
            sp_sfzmmc -> {
                CarUtils.sfzmcMap.clear() //清除缓存
                val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.SFZMMC)

                for (db in sfzmmcList) {
                    val dmz = db.getDmz()
                    val dmsm1 = db.getDmsm1()
                    CarUtils.sfzmcMap.put(dmsm1,dmz)
                    adapter.add(dmsm1)
                }
                sp.adapter = adapter
            }
            sp_szdq -> {
                CarUtils.szqyMap.clear() //清除缓存
                val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.XSQY)

                for (db in sfzmmcList) {
                    val dmz = db.getDmz()
                    val dmsm1 = db.getDmsm1()
                    CarUtils.szqyMap.put(dmsm1,dmz)
                    adapter.add(dmsm1)
                }
                sp.adapter = adapter
            }
        }
    }

    private fun bindEvent() {
        btn_next.setOnClickListener {
            mActivity!!.doDLRnext()
        }
        btn_last.setOnClickListener {
            mActivity!!.doYWXXnext()
        }
        iv_sfzh_scan.setOnClickListener {
            goNfcReadPlugin()
        }
    }

    override fun getSYRXXCommitData() {

        var sfzmmc = sp_sfzmmc.selectedItem.toString()
        var szqy = sp_szdq.selectedItem.toString()
        val sfzmhm = et_sfzmhm.text.toString()
        val xm =  et_xm.text.toString()
        val dh =  et_lxdh.text.toString()
        val xxdz =  et_xxdz.text.toString()

        //所有人信息
        mCarZcMsgData.czzjlx = CarUtils.sfzmcMap[sfzmmc]//身份证明名称
        mCarZcMsgData.czzjhm = sfzmhm//身份证明号码
        mCarZcMsgData.czxm = xm//姓名
        mCarZcMsgData.czlxdh = dh//联系电话
        mCarZcMsgData.czzsqh = CarUtils.szqyMap[szqy]//所在地区
        mCarZcMsgData.czzsdz = xxdz//详细地址

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==ID_CARD_READ_CODE && resultCode== Activity.RESULT_OK && data!=null){
            showToast("身份证已读取")
            et_sfzmhm.setText(data.getStringExtra(Constants.NUMBER))

        }
    }
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as CollectDataActivity
    }
}
