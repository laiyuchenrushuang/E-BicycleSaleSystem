package com.seatrend.cd.electricbicyclesalesystem.activity

import android.util.Log
import android.view.View
import android.widget.*
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.database.CodeTableSQLiteUtils

/**
 * Created by ly on 2019/7/3 11:54
 *
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
class CarRegisterDataActivity : BaseActivity(), AdapterView.OnItemSelectedListener {

    var carbd_sp_ywlx: Spinner? = null
    var carbd_sp_bgnr: Spinner? = null
    var carbd_ll_bglr: LinearLayout? = null
    var view: View? = null
    override fun initView() {
        setPageTitle(getString(R.string.car_register))
        carbd_sp_ywlx = findViewById(R.id.carbd_sp_ywlx)
        carbd_sp_bgnr = findViewById(R.id.carbd_sp_bgnr)
        carbd_ll_bglr = findViewById(R.id.carbd_ll_bglr)
        view = findViewById(R.id.view)
        setSpinerData()
    }

    private fun setSpinerData() {
        setSpinnerAdapter(carbd_sp_ywlx)
        setSpinnerAdapter(carbd_sp_bgnr)
    }

    private fun setSpinnerAdapter(spinner: Spinner?) {
        when (spinner) {
            carbd_sp_ywlx -> {
                val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item)
                adapter.setDropDownViewResource(R.layout.item_spinner__down_common)
                val ywlx = ArrayList<String>()
                ywlx!!.add("注册登记")
                ywlx!!.add("变更登记")
                ywlx!!.add("转移登记")
                ywlx!!.add("补/换牌证")
                for (db: String in ywlx) {
                    adapter.add(db)
                }
                spinner!!.adapter = adapter
                spinner.setOnItemSelectedListener(this);
            }
            carbd_sp_bgnr -> {
                val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item)
                adapter.setDropDownViewResource(R.layout.item_spinner__down_common)
                val ywlx = ArrayList<String>()
                ywlx!!.add("车辆品牌")
                ywlx!!.add("车辆型号")
                ywlx!!.add("整车编码")
                ywlx!!.add("电机号")
                ywlx!!.add("整备质量")
                ywlx!!.add("车身颜色")
                ywlx!!.add("实测质量")
                ywlx!!.add("实际时速")
                for (db: String in ywlx) {
                    adapter.add(db)
                }
                spinner!!.adapter = adapter
                spinner.setOnItemSelectedListener(this);
            }

        }

    }

    override fun getLayout(): Int {
        return R.layout.activity_car_basedata
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Log.d("lylog", " p0 =" + p0)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when(p0!!.id){
            R.id.carbd_sp_ywlx ->{
                if (p2 == 1) {
                    carbd_ll_bglr!!.visibility = View.VISIBLE
                    view!!.visibility = View.VISIBLE
                } else {
                    carbd_ll_bglr!!.visibility = View.GONE
                    view!!.visibility = View.GONE
                }
            }
        }
    }
}
