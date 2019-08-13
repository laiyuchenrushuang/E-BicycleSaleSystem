package com.seatrend.cd.electricbicyclesalesystem.activity

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.*
import com.google.gson.Gson
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.database.CodeTableSQLiteUtils
import com.seatrend.cd.electricbicyclesalesystem.entity.CarBean
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.entity.UserInfo
import com.seatrend.cd.electricbicyclesalesystem.persenter.CarCYPresenter
import com.seatrend.cd.electricbicyclesalesystem.util.CarUtils
import com.seatrend.cd.electricbicyclesalesystem.util.LoadingDialog
import com.seatrend.cd.electricbicyclesalesystem.view.CarCYView
import kotlinx.android.synthetic.main.activity_carcy.*

class CarCYActivity : BaseActivity() {
    val TAG_OK = 1; //合格tag
    val TAG_FAILED = 0; //不合格tag
    var listdata_ywlx = ArrayList<String>() //业务类型数据会随车身类型变化而变化
    var context: Context? = null


    companion object {
        var csysData = HashMap<String, String>()
        var ywlxData = HashMap<String, String>()
    }

    override fun initView() {
        setPageTitle(getString(R.string.carmsg_clcy))
        context = this
        ywlxData = CarUtils.ywlxMap
        csysData = CarUtils.csysMap
        //默认都不合格
        iv_sczl.setBackgroundResource(R.mipmap.check_bt_fail)
        iv_sjss.setBackgroundResource(R.mipmap.check_bt_fail)
        iv_ckg.setBackgroundResource(R.mipmap.check_bt_fail)
        iv_csys.setBackgroundResource(R.mipmap.check_bt_fail)

        //设置tag
        iv_sczl.tag = TAG_FAILED
        iv_sjss.tag = TAG_FAILED
        iv_ckg.tag = TAG_FAILED
        iv_csys.tag = TAG_FAILED
        initspiner()
        bindEvent()
    }

    private fun initspiner() {
        setAdapterSp(clcy_cllx_sp)
        setAdapterSp(clcy_ywlx_sp)
        setAdapterSp(clcy_ywyy_sp)
        setAdapterSp(clcy_csys1_sp)
        setAdapterSp(clcy_csys2_sp)
    }

    private fun setAdapterSp(sp: Spinner?) {
        var adapter = ArrayAdapter<String>(context, R.layout.my_simple_spinner_item)
        adapter!!.setDropDownViewResource(R.layout.item_spinner__down_common)

        when (sp) {
            clcy_cllx_sp -> {
                var listdata = ArrayList<String>()
                listdata!!.add(getString(R.string.bz_car))
                listdata!!.add(getString(R.string.cbz_car))
                adapter!!.addAll(listdata)
                sp!!.adapter = adapter
            }
            clcy_ywlx_sp -> {
                listdata_ywlx!!.add(getString(R.string.zcdj))
                listdata_ywlx!!.add(getString(R.string.zydj))
                listdata_ywlx!!.add(getString(R.string.bgdj))
                listdata_ywlx!!.add(getString(R.string.bhpz))
                adapter!!.addAll(listdata_ywlx)
                sp!!.adapter = adapter
            }
            clcy_ywyy_sp -> {
                var listdata = ArrayList<String>()
                listdata!!.add(getString(R.string.bg_csys))
                listdata!!.add(getString(R.string.bg_zcbm))
                listdata!!.add(getString(R.string.bg_sfz))
                adapter!!.addAll(listdata)
                sp!!.adapter = adapter
            }
            clcy_csys1_sp -> {
                val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.CSYS)
                for (db in sfzmmcList) {
                    val dmz = db.getDmz()
                    val dmsm1 = db.getDmsm1()
                    adapter.add(dmsm1)
                }
                sp!!.adapter = adapter
            }
            clcy_csys2_sp -> {
                val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.CSYS)
                for (db in sfzmmcList) {
                    val dmz = db.getDmz()
                    val dmsm1 = db.getDmsm1()
                    adapter.add(dmsm1)
                }
                sp!!.adapter = adapter
            }
        }
    }

    private fun bindEvent() {
        LoadingDialog.getInstance().dialogShowing()
        clcy_next!!.setOnClickListener {
            var vehFlowMain = CarBean.vehFlowMain()
            CarPhotoActivity.mClcyCommitData.cllx = if (clcy_cllx_sp.selectedItem.toString().equals(getString(R.string.bz_car))) "1" else "2"     //车辆类型 "1" 标准  "0" 超标
            vehFlowMain.cllx = if (clcy_cllx_sp.selectedItem.toString().equals(getString(R.string.bz_car))) "1" else "2"     //车辆类型 "1" 标准  "0" 超标
            vehFlowMain.ywlx = ywlxData.get(clcy_ywlx_sp.selectedItem).toString()//业务类型
            if (vehFlowMain.ywlx.equals("D")) { // 选择了变更登记
                vehFlowMain.djjy = (clcy_ywyy_sp.selectedItemId + 1).toString()  //1 代表车身颜色  2 整车编码 3 身份证号码
            }
            CarPhotoActivity.mClcyCommitData.csys1 = csysData.get(clcy_csys1_sp.selectedItem).toString()
            CarPhotoActivity.mClcyCommitData.csys2 = csysData.get(clcy_csys2_sp.selectedItem).toString()
            CarPhotoActivity.mClcyCommitData.csysbj = iv_csys.tag.toString()

            CarPhotoActivity.mClcyCommitData.wkc = ed_c.text.toString()
            CarPhotoActivity.mClcyCommitData.wkk = ed_k.text.toString()
            CarPhotoActivity.mClcyCommitData.wkg = ed_g.text.toString()
            CarPhotoActivity.mClcyCommitData.ckgbj = iv_ckg.tag.toString()

            CarPhotoActivity.mClcyCommitData.sczbzl = ed_sczl.text.toString()
            CarPhotoActivity.mClcyCommitData.zczlbj = iv_sczl.tag.toString()

            CarPhotoActivity.mClcyCommitData.sjss = ed_sjss.text.toString()
            CarPhotoActivity.mClcyCommitData.zgssbj = iv_sjss.tag.toString()
            CarPhotoActivity.mClcyCommitData.ywyy = ed_bhgyy.text.toString()
            CarPhotoActivity.mClcyCommitData.setVehFlowMain(vehFlowMain)

            CarPhotoActivity.mClcyCommitData.djr =  UserInfo.XM
            CarPhotoActivity.mClcyCommitData.jyr =  UserInfo.XM
            CarPhotoActivity.mClcyCommitData.djdw = UserInfo.GLBM
            CarPhotoActivity.mClcyCommitData.jydw = UserInfo.GLBM

            CarPhotoActivity.mClcyCommitData.zcbm = intent.getStringExtra("zcbm")
            CarPhotoActivity.mClcyCommitData.vehFlowMain.zcbm = intent.getStringExtra("zcbm")
            CarPhotoActivity.mClcyCommitData.clsbdm = intent.getStringExtra("zcbm")
            CarPhotoActivity.mClcyCommitData.vehFlowMain.clsbdh = intent.getStringExtra("zcbm")
            startActivity(intent.setClass(this, CarPhotoActivity::class.java))
        }
        clcy_ywlx_sp!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                var name: String = parent!!.selectedItem as String
                if (name != null && name.equals(getString(R.string.bgdj))) {
                    clcy_yeyy_ll.visibility = View.VISIBLE
                    clcy_yeyy_v.visibility = View.VISIBLE
                } else {
                    clcy_yeyy_ll.visibility = View.GONE
                    clcy_yeyy_v.visibility = View.GONE
                }
            }
        }
        clcy_cllx_sp!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var adapter = ArrayAdapter<String>(context, R.layout.my_simple_spinner_item)
                adapter!!.setDropDownViewResource(R.layout.item_spinner__down_common)

                var name: String = parent!!.selectedItem as String
                if (name != null && name.equals(getString(R.string.cbz_car))) {
                    listdata_ywlx.clear()
                    listdata_ywlx!!.add(getString(R.string.zcdj))
                    listdata_ywlx!!.add(getString(R.string.bgdj))
                    listdata_ywlx!!.add(getString(R.string.xsdj))
                    listdata_ywlx!!.add(getString(R.string.bhpz))
                    adapter.addAll(listdata_ywlx)
                    adapter.notifyDataSetChanged()
                    clcy_ywlx_sp.adapter = adapter
                } else {
                    listdata_ywlx.clear()
                    listdata_ywlx!!.add(getString(R.string.zcdj))
                    listdata_ywlx!!.add(getString(R.string.zydj))
                    listdata_ywlx!!.add(getString(R.string.bgdj))
                    listdata_ywlx!!.add(getString(R.string.xsdj))
                    listdata_ywlx!!.add(getString(R.string.bhpz))
                    adapter.addAll(listdata_ywlx)
                    adapter.notifyDataSetChanged()
                    clcy_ywlx_sp.adapter = adapter
                }
            }
        }

        iv_sczl.setOnClickListener {
            changeState(iv_sczl)
        }
        iv_sjss.setOnClickListener {
            changeState(iv_sjss)
        }
        iv_ckg.setOnClickListener {
            changeState(iv_ckg)
        }
        iv_csys.setOnClickListener {
            changeState(iv_csys)
        }
    }

    private fun changeState(iv: ImageView?) {
        if (TAG_OK != iv!!.tag) {
            iv!!.setBackgroundResource(R.mipmap.check_bt_ok) //获取第一个 合格
            iv.tag = TAG_OK
        } else {
            iv!!.setBackgroundResource(R.mipmap.check_bt_fail)//获取第二个 不合格
            iv.tag = TAG_FAILED
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_carcy
    }

}

