package com.seatrend.cd.electricbicyclesalesystem.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.activity.CarChangeRegisterActivity
import com.seatrend.cd.electricbicyclesalesystem.activity.CarChangeRegisterActivity.Companion.mCarZcMsgData
import com.seatrend.cd.electricbicyclesalesystem.activity.CarChangeRegisterActivity.Companion.mYwSearchBean
import com.seatrend.cd.electricbicyclesalesystem.common.BaseFragment
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.database.CodeTableSQLiteUtils
import com.seatrend.cd.electricbicyclesalesystem.util.CarUtils
import kotlinx.android.synthetic.main.fragemnt_common_syrxx.*
import kotlinx.android.synthetic.main.fragemnt_common_syrxx.sp_szdq

class SyrxxChangeFragment : BaseFragment() {
    companion object{
        var isChange = false
    }
    var mActivity: CarChangeRegisterActivity? = null

    override fun getLayoutView(inflater: LayoutInflater?, container: ViewGroup?): View {
        return inflater!!.inflate(R.layout.fragemnt_common_syrxx,container,false)
    }

    override fun initView() {
        syncUI()
        getSyncData()
        bindEvent()
        setAdapterData(sp_szdq)
    }

    private fun getSyncData() {
        tv_sfzlx.setText(mYwSearchBean.data.vehicleTemp.czzjlx)
        tv_sfzhm.setText(mYwSearchBean.data.vehicleTemp.czzjhm)
        tv_xm.setText(mYwSearchBean.data.vehicleTemp.czxm)
        tv_lxdh.setText(mYwSearchBean.data.vehicleTemp.czlxdh)
        tv_szqy.setText(mYwSearchBean.data.vehicleTemp.czzsqh)
        tv_xxdz.setText(mYwSearchBean.data.vehicleTemp.czzsdz)
    }

    private fun syncUI() {
        if(isChange){
            tv_lxdh.visibility = View.GONE
            tv_szqy.visibility = View.GONE
            tv_xxdz.visibility = View.GONE

            et_lxdh.visibility = View.VISIBLE
            sp_szdq.visibility = View.VISIBLE
            et_xxdz.visibility = View.VISIBLE
        }else{
            tv_lxdh.visibility = View.VISIBLE
            tv_szqy.visibility = View.VISIBLE
            tv_xxdz.visibility = View.VISIBLE

            et_lxdh.visibility = View.GONE
            sp_szdq.visibility = View.GONE
            et_xxdz.visibility = View.GONE
        }
    }

    private fun setAdapterData(sp: Spinner) {
        val adapter = ArrayAdapter<String>(activity, R.layout.my_simple_spinner_item)
        adapter.setDropDownViewResource(R.layout.item_spinner__down_common)
        when (sp) {
            sp_szdq -> {
                val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.XSQY)

                for (db in sfzmmcList) {
                    val dmz = db.getDmz()
                    val dmsm1 = db.getDmsm1()
                    adapter.add(dmsm1)
                }
                sp.adapter = adapter
            }
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        syncUI()
    }

    private fun bindEvent() {
        btn_next.setOnClickListener {
            mActivity!!.doDLRnext()
        }
        btn_last.setOnClickListener {
            mActivity!!.doYWXXnext()
        }
    }
    //获取当前界面数据  isChange 需要改变的时候传更改的内容，不更改的话传获取的内容，防止内容错误
    override fun getSYRXXCommitData(){
        if(isChange){
            mCarZcMsgData.czlxdh = et_lxdh.text.toString()
            mCarZcMsgData.czzsqh = CarUtils.szqyMap[sp_szdq.selectedItem.toString()]
            mCarZcMsgData.czzsdz = et_xxdz.text.toString()
        }else{
            mCarZcMsgData.czlxdh = mYwSearchBean.data.vehicleTemp.czlxdh
            mCarZcMsgData.czzsqh = mYwSearchBean.data.vehicleTemp.czzsqh
            mCarZcMsgData.czzsdz = mYwSearchBean.data.vehicleTemp.czzsdz
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as CarChangeRegisterActivity
    }

    override fun onDestroy() {
        super.onDestroy()
        isChange = false //入口多 归位
    }
}
