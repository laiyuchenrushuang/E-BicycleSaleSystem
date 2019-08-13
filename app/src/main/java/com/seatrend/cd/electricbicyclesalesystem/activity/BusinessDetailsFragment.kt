package com.seatrend.cd.electricbicyclesalesystem.activity

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.BaseFragment
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.BusinessEntity
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.entity.UserInfo
import com.seatrend.cd.electricbicyclesalesystem.persenter.BusinessDetailsPersenter
import com.seatrend.cd.electricbicyclesalesystem.util.GsonUtils
import com.seatrend.cd.electricbicyclesalesystem.util.LoadingDialog
import com.seatrend.cd.electricbicyclesalesystem.util.SharedPreferencesUtils
import com.seatrend.cd.electricbicyclesalesystem.util.StringUtils
import com.seatrend.cd.electricbicyclesalesystem.view.BusinessDetailsView
import kotlinx.android.synthetic.main.activity_business_details.*
import kotlinx.android.synthetic.main.fragment_dw_xxsj.*
import java.lang.Exception

class BusinessDetailsFragment : BaseFragment(), BusinessDetailsView {

    private var isViewCreated=false
    private var isUIVisible=false
    private var isFirst=true


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser){
            isUIVisible=true
            lazyLoad()
        }else{
            isUIVisible=false
        }


    }
    private fun lazyLoad(){

        if (isUIVisible && isViewCreated && isFirst){
            isUIVisible=false
            isViewCreated=false
            isFirst=false
            getData()
        }

    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        isViewCreated=true
        lazyLoad()
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun getLayoutView(inflater: LayoutInflater?, container: ViewGroup?): View {
        return inflater!!.inflate(R.layout.activity_business_details, container, false)
    }


    override fun netWorkTaskSuccess(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()
       try {
           val businessEntity = GsonUtils.gson(commonResponse.getResponseString(), BusinessEntity::class.java)

           tv_ywzs.text=businessEntity.data.total.toString()
           tv_tgsl.text=businessEntity.data.pass.toString()
           tv_wtgsl.text=businessEntity.data.fail.toString()

       }catch (e:Exception){
           showToast(e.message.toString())
       }

    }

    override fun netWorkTaskfailed(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()
        showErrorDialog(commonResponse.getResponseString())
    }

    private var mBusinessDetailsPersenter: BusinessDetailsPersenter?=null


    override fun initView() {
        mBusinessDetailsPersenter= BusinessDetailsPersenter(this)
        tv_name.text=getString(R.string.who_hello,UserInfo.XM)
        tv_tjqssj.text=SharedPreferencesUtils.getTjqssj()
        tv_tjzzsj.text=SharedPreferencesUtils.getTjzzsj()
        bindEvent()

    }

    override fun onStart() {
        super.onStart()

    }

    private fun bindEvent() {
        tv_tjqssj.setOnClickListener {
            showTimeDialog(0)
        }

        tv_tjzzsj.setOnClickListener {
            showTimeDialog(1)
        }
    }
    private fun showTimeDialog(tag: Int) {
        val dialog = Dialog(context)
        // MAlertDialog dialog=new MAlertDialog(this);
        dialog.setContentView(R.layout.dialog_date_picker)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
        val btnCancel = dialog.findViewById<Button>(R.id.btn_cancel)
        val btnOk = dialog.findViewById<Button>(R.id.btn_ok)
        val mDatePicker = dialog.findViewById<DatePicker>(R.id.m_date_picker)

        btnCancel.setOnClickListener { dialog.dismiss() }

        btnOk.setOnClickListener {
            val year = mDatePicker.year
            val month = mDatePicker.month + 1
            val dayOfMonth = mDatePicker.dayOfMonth
            val time = year.toString() + "-" + month + "-" + dayOfMonth

            if (tag == 0) {
                    tv_tjqssj.text=time
                val startTime = StringUtils.dateToStamp(time)
                val endTime = StringUtils.dateToStamp(tv_tjzzsj.text.toString())
                if (startTime>endTime){
                    showToast("起始时间不能大于终止时间")
                }else{
                    tv_tjqssj.text=time
                    SharedPreferencesUtils.setTjqssj(time)
                    dialog.dismiss()
                    getData()
                }

                } else if (tag == 1) {


                val startTime = StringUtils.dateToStamp(tv_tjqssj.text.toString())
                val endTime = StringUtils.dateToStamp(time)
                if (startTime>endTime){
                    showToast("起始时间不能大于终止时间")
                }else{
                    tv_tjzzsj.text=time
                    SharedPreferencesUtils.setTjzzsj(time)
                    dialog.dismiss()
                    getData()
                }

                }


        }

        dialog.setOnDismissListener {
           // getData()
        }


    }

    private fun getData(){
        val map=HashMap<String,String>()
        map.put("glbm",UserInfo.GLBM)
        map.put("startTime",tv_tjqssj.text.toString())
        map.put("endTime ",tv_tjzzsj.text.toString())

        LoadingDialog.getInstance().showLoadDialog(context)
        mBusinessDetailsPersenter!!.doNetworkTask(map,Constants.BUSINESS_MESSAGE)
    }

}
