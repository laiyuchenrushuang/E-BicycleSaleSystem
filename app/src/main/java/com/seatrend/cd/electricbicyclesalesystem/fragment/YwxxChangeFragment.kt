package com.seatrend.cd.electricbicyclesalesystem.fragment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.activity.CarChangeRegisterActivity
import com.seatrend.cd.electricbicyclesalesystem.activity.CarChangeRegisterActivity.Companion.mCarZcMsgData
import com.seatrend.cd.electricbicyclesalesystem.activity.CarChangeRegisterActivity.Companion.mYwSearchBean
import com.seatrend.cd.electricbicyclesalesystem.common.BaseFragment
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.common.Constants.Companion.CAR_BG
import com.seatrend.cd.electricbicyclesalesystem.common.Constants.Companion.CAR_ZX
import com.seatrend.cd.electricbicyclesalesystem.common.Constants.Companion.CAR_ZY
import com.seatrend.cd.electricbicyclesalesystem.database.CodeTableSQLiteUtils
import com.seatrend.cd.electricbicyclesalesystem.util.CarUtils
import com.seatrend.cd.electricbicyclesalesystem.util.StringUtils
import kotlinx.android.synthetic.main.fragment_change_ywxx.*
import kotlinx.android.synthetic.main.fragment_change_ywxx.sp_csys1
import kotlinx.android.synthetic.main.fragment_change_ywxx.sp_csys2
import kotlinx.android.synthetic.main.fragment_change_ywxx.tv_cllx
import kotlinx.android.synthetic.main.fragment_change_ywxx.btn_next
import kotlinx.android.synthetic.main.fragment_yexx.*

import kotlinx.android.synthetic.main.fragment_change_ywxx.tv_ywlx as tv_ywlx1

@Suppress("DEPRECATION")
class YwxxChangeFragment : BaseFragment() {
    var mActivity: CarChangeRegisterActivity? = null

    val CAR_BG_SFZ = 0 //身份证
    val CAR_BG_ZCBM = 1 //整车编码
    val CAR_BG_CSYS = 2 //车身颜色

    var ywlx: String? = null

    override fun getLayoutView(inflater: LayoutInflater?, container: ViewGroup?): View {
        return inflater!!.inflate(R.layout.fragment_change_ywxx, container, false)
    }

    override fun initView() {
        setAdapterSpinnerData()
        ywlx = mActivity!!.intent.getStringExtra(Constants.TASK)
        dispatcherYwlx(ywlx)
        getSyncData()  //获取服务器信息
        bindEvent()
    }

    private fun getSyncData() {
        ed_zcbm.setText(CarChangeRegisterActivity.mYwSearchBean.data.vehicleTemp.zcbm)
        tv_cllx.setText(CarUtils.getCllxMap[CarChangeRegisterActivity.mYwSearchBean.data.vehicleTemp.cllx])
        tv_ywlx.setText(CarUtils.getTaskLXMap[CarChangeRegisterActivity.mYwSearchBean.data.vehFlowMain.ywlx])
        et_hgzh.setText(CarChangeRegisterActivity.mYwSearchBean.data.vehicleTemp.cphgzbh)
        et_hqfs.setText(CarChangeRegisterActivity.mYwSearchBean.data.vehicleTemp.hdfs)
        tv_cshm.setText(CarChangeRegisterActivity.mYwSearchBean.data.vehicleTemp.hphm)
        tv_csys1.setText(CarUtils.csysGetMap[CarChangeRegisterActivity.mYwSearchBean.data.vehicleTemp.csys1])
        tv_csys2.setText(CarUtils.csysGetMap[CarChangeRegisterActivity.mYwSearchBean.data.vehicleTemp.csys2])
        tv_llzm.setText(CarChangeRegisterActivity.mYwSearchBean.data.vehicleTemp.llzm)
        tv_syxz.setText(CarChangeRegisterActivity.mYwSearchBean.data.vehicleTemp.syxz)
        tv_clyt.setText(CarChangeRegisterActivity.mYwSearchBean.data.vehicleTemp.clyt)
        tv_xsqy.setText(CarChangeRegisterActivity.mYwSearchBean.data.vehicleTemp.xsqy)
        tv_hqrq.setText(StringUtils.longToStringDataNoHour(CarChangeRegisterActivity.mYwSearchBean.data.vehicleTemp.hdrq.toLong()))
    }

    private fun dispatcherYwlx(ywlx: String?) {
        //初始化
        ll_bgnr.visibility = View.GONE //变更内容 spiner
        ll_zxyy.visibility = View.GONE //注销原因 spiner

        when (ywlx) {
            CAR_BG -> {
                tv_ywlx.text ="变更登记"
                ll_bgnr.visibility = View.VISIBLE
            }
            CAR_ZY -> {
                tv_ywlx.text ="转移登记"
            }
            CAR_ZX -> {
                tv_ywlx.text ="注销登记"
                ll_zxyy.visibility = View.VISIBLE
            }
        }
    }

    private fun bindEvent() {

        sp_bgnr!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                ed_zcbm.setText(CarChangeRegisterActivity.mYwSearchBean.data.vehicleTemp.zcbm) //保持整车编码归位
                sp_csys1.visibility = View.GONE
                sp_csys2.visibility = View.GONE
                tv_csys1.visibility = View.VISIBLE
                tv_csys2.visibility = View.VISIBLE
                ed_zcbm.setTextColor(resources.getColor(R.color.black))
                ed_zcbm.isFocusableInTouchMode = false
                ed_zcbm.isFocusable = false
                SyrxxChangeFragment.isChange = false
                if (position == CAR_BG_CSYS) {
                    sp_csys1.visibility = View.VISIBLE
                    sp_csys2.visibility = View.VISIBLE
                    tv_csys1.visibility = View.GONE
                    tv_csys2.visibility = View.GONE
                }
                if (position == CAR_BG_ZCBM) {
                    ed_zcbm.setTextColor(resources.getColor(R.color.theme_color))
                    ed_zcbm.isFocusableInTouchMode = true
                    ed_zcbm.isFocusable = true
                    ed_zcbm.requestFocus()
                }
                if (position == CAR_BG_SFZ) {
                    SyrxxChangeFragment.isChange = true
                }
            }
        }

        btn_next.setOnClickListener {
            mActivity!!.donext()
        }
    }

    private fun setAdapterSpinnerData() {
        setAdapterSp(sp_bgnr)
        setAdapterSp(sp_zxyy)
        setAdapterSp(sp_csys1)
        setAdapterSp(sp_csys2)
    }

    private fun setAdapterSp(sp: Spinner?) {
        var adapter = ArrayAdapter<String>(context, R.layout.my_simple_spinner_item)
        adapter!!.setDropDownViewResource(R.layout.item_spinner__down_common)

        when (sp) {
            sp_bgnr -> {
                var listdata = ArrayList<String>()
                listdata!!.add("身份证")
                listdata!!.add("整车编码")
                listdata!!.add("车身颜色")

                adapter!!.addAll(listdata)
                sp!!.adapter = adapter
            }
            sp_zxyy -> {
                var listdata = ArrayList<String>()
                listdata!!.add("自行报废")
                adapter!!.addAll(listdata)
                sp!!.adapter = adapter
            }
            sp_csys1 -> {
                var listdata = CodeTableSQLiteUtils.queryByDMLB(Constants.CSYS)
                for (db in listdata) {
                    val dmz = db.getDmz()
                    val dmsm1 = db.getDmsm1()
                    adapter.add( dmsm1)
                }
                sp!!.adapter = adapter
            }
            sp_csys2 -> {
                var listdata = CodeTableSQLiteUtils.queryByDMLB(Constants.CSYS)
                for (db in listdata) {
                    val dmz = db.getDmz()
                    val dmsm1 = db.getDmsm1()
                    adapter.add( dmsm1)
                }
                sp!!.adapter = adapter
            }
        }
    }

    //获取当前界面数据
    fun getYWXXCommitData(){
        mCarZcMsgData.lsh = mYwSearchBean.data.vehicleTemp.lsh
        mCarZcMsgData.xh = mYwSearchBean.data.vehicleTemp.xh
        if (ywlx == CAR_BG){
            mCarZcMsgData.zcbm = mYwSearchBean.data.vehicleTemp.zcbm
            mCarZcMsgData.csys1 = mYwSearchBean.data.vehicleTemp.csys1
            mCarZcMsgData.csys2 = mYwSearchBean.data.vehicleTemp.csys2

            when(sp_bgnr.selectedItemPosition){
                CAR_BG_ZCBM ->{
                    var zcbm = ed_zcbm.text.toString()  //整车编码
                    mCarZcMsgData.zcbm = zcbm
                }
                CAR_BG_CSYS ->{
                    var csys1 = CarUtils.csysMap[sp_csys1.selectedItem.toString()] //颜色1
                    var csys2 = CarUtils.csysMap[sp_csys2.selectedItem.toString()] //颜色2
                    mCarZcMsgData.csys1 = csys1
                    mCarZcMsgData.csys2 = csys2
                }
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as CarChangeRegisterActivity
    }
}