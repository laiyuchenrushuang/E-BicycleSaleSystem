package com.seatrend.cd.electricbicyclesalesystem.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.adpater.CheckDataPhotoAdapter
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.database.CodeTableSQLiteUtils
import com.seatrend.cd.electricbicyclesalesystem.entity.CarMessageEntity
import com.seatrend.cd.electricbicyclesalesystem.util.OtherUtils
import kotlinx.android.synthetic.main.activity_check_data.*



class CheckDataActivity : BaseActivity() {


    companion object {
        var mCarMessageEntity: CarMessageEntity?=null
    }
    override fun initView() {
        setPageTitle(getString(R.string.qfhcysj))
        m_recycler_view.layoutManager= StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        m_recycler_view.adapter=CheckDataPhotoAdapter(this)
        setSpinnerAdapter(sp_ywlx)
        setSpinnerAdapter(sp_csys1)
        setSpinnerAdapter(sp_csys2)
        bindEvent()
        initData()
    }

    private fun bindEvent(){
        btn_ok.setOnClickListener {
            startActivity(Intent(this,CollectDataActivity::class.java))
        }
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
        return R.layout.activity_check_data
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
}
