package com.seatrend.cd.electricbicyclesalesystem.fragment


import android.content.Context
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.activity.CheckDataActivity
import com.seatrend.cd.electricbicyclesalesystem.activity.CollectDataActivity
import com.seatrend.cd.electricbicyclesalesystem.activity.CollectDataActivity.Companion.mCarZcMsgData
import com.seatrend.cd.electricbicyclesalesystem.common.BaseFragment
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.database.CodeTableSQLiteUtils
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.entity.LlzmBean
import com.seatrend.cd.electricbicyclesalesystem.entity.YwSearchBean
import com.seatrend.cd.electricbicyclesalesystem.persenter.YwxxPresenter
import com.seatrend.cd.electricbicyclesalesystem.util.*
import com.seatrend.cd.electricbicyclesalesystem.view.YwxxView
import kotlinx.android.synthetic.main.fragment_yexx.*
import kotlinx.android.synthetic.main.fragment_yexx.btn_next
import kotlinx.android.synthetic.main.fragment_yexx.tv_cllx
import kotlinx.android.synthetic.main.fragment_yexx.tv_ywlx


/**
 * A simple [Fragment] subclass.
 */
class YwxxFragment : BaseFragment(), YwxxView {
    companion object {
        var mYwSearchBean = YwSearchBean() //zcbm查询获取的bean
    }

    var mLlzmList = ArrayList<String>()
    var mActivity: CollectDataActivity? = null
    private var mYwxxPresenter: YwxxPresenter? = null
    override fun getLayoutView(inflater: LayoutInflater?, container: ViewGroup?): View {
        return inflater!!.inflate(R.layout.fragment_yexx, container, false)
    }

    override fun initView() {
        mYwxxPresenter = YwxxPresenter(this)
        setSpinnerAdapter(sp_csys1)
        setSpinnerAdapter(sp_csys2)
        setSpinnerAdapter(sp_hdfs)
        setSpinnerAdapter(sp_syxz)
        setSpinnerAdapter(sp_clyt)
        setSpinnerAdapter(sp_xsqy)
        bindevent()
        initdata()
    }

    private fun initdata() {
        tv_zcbm.setText(mYwSearchBean.data.vehicleTemp.zcbm)//整车编码
        tv_cllx.setText(CarUtils.getCllxMap[mYwSearchBean.data.vehicleTemp.cllx])//车辆类型
        tv_ywlx.setText(CarUtils.getTaskLXMap[mYwSearchBean.data.vehFlowMain.ywlx])//业务类型
        tv_hgzh.setText(mYwSearchBean.data.vehicleTemp.cphgzbh) //合格证编号
        tv_clhp.setText(mYwSearchBean.data.vehicleTemp.hphm)//号牌号码
        tv_time.setText(StringUtils.longToStringDataNoHour(mYwSearchBean.data.vehicleTemp.hdrq.toLong()))//获得日期
    }

    private fun bindevent() {
        btn_next.setOnClickListener {
            mActivity!!.donext()
        }
//        ll_hqrq.setOnClickListener {
//            showTimeDialog(tv_time)
//        }

        sp_hdfs.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                LoadingDialog.getInstance().showLoadDialog(context)
                var map = HashMap<String, String>()
                map["pzlx"] = CarUtils.getQTDMZ(Constants.HDFS)[sp_hdfs.selectedItem.toString()].toString()
                mYwxxPresenter!!.doNetworkTask(map, Constants.GET_CAR_LLZM_BY_HDFS)

            }

        }
    }

    override fun netWorkTaskSuccess(commonResponse: CommonResponse) {
        if (commonResponse.getUrl() == Constants.GET_CAR_LLZM_BY_HDFS) {
            mLlzmList.clear()
            var llzmbean = GsonUtils.gson(commonResponse.getResponseString(), LlzmBean::class.java)
            for (index in 0 until llzmbean.data.size) {
                mLlzmList.add(llzmbean.data.get(index).zmmc)
                CarUtils.llzmMap.put(llzmbean.data.get(index).zmmc, llzmbean.data.get(index).zplx)
            }
            sp_llzm.postInvalidate()
            setSpinnerAdapter(sp_llzm)
            getYWXXCommitData()
        }
        LoadingDialog.getInstance().dismissLoadDialog()
    }

    override fun netWorkTaskfailed(commonResponse: CommonResponse) {
        showErrorDialog(commonResponse.getResponseString())
        LoadingDialog.getInstance().dismissLoadDialog()
    }

    private fun setSpinnerAdapter(spinner: Spinner) {
        val adapter = ArrayAdapter<String>(activity, R.layout.my_simple_spinner_item)
        adapter.setDropDownViewResource(R.layout.item_spinner__down_common)
        when (spinner.id) {
            R.id.sp_csys1, R.id.sp_csys2 -> {
                val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.CSYS)
                for (db in sfzmmcList) {
                    val dmz = db.getDmz()
                    val dmsm1 = db.getDmsm1()
                    adapter.add(dmsm1)
                }

                spinner.adapter = adapter
            }
            R.id.sp_ywlx -> {
                val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.YWLX)

                for (db in sfzmmcList) {
                    val dmz = db.getDmz()
                    val dmsm1 = db.getDmsm1()
                    adapter.add(dmsm1)
                }

                spinner.adapter = adapter

            }

            R.id.sp_hdfs -> {
                val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.HDFS)

                for (db in sfzmmcList) {
                    val dmz = db.getDmz()
                    val dmsm1 = db.getDmsm1().trim()
                    adapter.add(dmsm1)
                }

                spinner.adapter = adapter

            }
            R.id.sp_llzm -> {

                adapter.addAll(mLlzmList)
                spinner.adapter = adapter

            }
            R.id.sp_syxz -> {
                val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.SYXZ)

                for (db in sfzmmcList) {
                    val dmz = db.getDmz()
                    val dmsm1 = db.getDmsm1()
                    adapter.add(dmsm1)
                }

                spinner.adapter = adapter

            }
            R.id.sp_clyt -> {
                val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.CLYT)

                for (db in sfzmmcList) {
                    val dmz = db.getDmz()
                    val dmsm1 = db.getDmsm1()
                    adapter.add(dmsm1)
                }

                spinner.adapter = adapter

            }
            R.id.sp_xsqy -> {
                val list = ArrayList<String>()
                list.add(getString(R.string.zc_qy_off))
                list.add(getString(R.string.zc_qy_on))
                adapter.addAll(list)
                spinner.adapter = adapter
            }
        }
    }

    fun getYWXXCommitData() {
        val map = HashMap<String, String>()

        val zcbm = tv_zcbm.text.toString()
        val cllx = tv_cllx.text.toString()//车辆类型
        val hgzh = tv_hgzh.text.toString()//合格证编号
        val clhp = tv_clhp.text.toString()//车辆号牌

        val csys1 = sp_csys1.selectedItem.toString()
        val csys2 = sp_csys2.selectedItem.toString()
        val hdfs = sp_hdfs.selectedItem.toString()
        val llzm = sp_llzm.selectedItem.toString()
        val syxz = sp_syxz.selectedItem.toString()
        val clyt = sp_clyt.selectedItem.toString()
        val xsqy = sp_xsqy.selectedItem.toString()

        //注册登记业务信息
        mCarZcMsgData.lsh  = mYwSearchBean.data.vehFlowMain.lsh   //流水号
        mCarZcMsgData.zcbm  = mYwSearchBean.data.vehFlowMain.zcbm //整车编码
        mCarZcMsgData.xh  = mYwSearchBean.data.vehFlowMain.xh  //序号

        mCarZcMsgData.csys1 = CarUtils.csysMap[csys1]//车辆颜色1
        mCarZcMsgData.csys2 = CarUtils.csysMap[csys2]//车辆颜色2
        mCarZcMsgData.hdfs = CarUtils.getQTDMZ(Constants.HDFS)[hdfs]//获得方式
        mCarZcMsgData.llzm = CarUtils.llzmMap[llzm]//来历证明
        mCarZcMsgData.syxz = CarUtils.getQTDMZ(Constants.SYXZ)[syxz]//使用性质
        mCarZcMsgData.clyt = CarUtils.getQTDMZ(Constants.CLYT)[clyt]//车辆用途
        mCarZcMsgData.xsqy = CarUtils.xsqyMap[xsqy]//行驶区域
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as CollectDataActivity
    }
}
