package com.seatrend.cd.electricbicyclesalesystem.activity

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.*
import com.seatrend.cd.electricbicyclesalesystem.persenter.CollectBicyclePersenter
import com.seatrend.cd.electricbicyclesalesystem.util.DeviceScanUtils
import com.seatrend.cd.electricbicyclesalesystem.util.GsonUtils
import com.seatrend.cd.electricbicyclesalesystem.util.LoadingDialog
import com.seatrend.cd.electricbicyclesalesystem.util.StringUtils
import com.seatrend.cd.electricbicyclesalesystem.view.CollectBicycleView
import kotlinx.android.synthetic.main.activity_insurance.*
import kotlinx.android.synthetic.main.activity_insurance.iv_qrcode
import kotlinx.android.synthetic.main.activity_insurance.sp_bxmc
import kotlinx.android.synthetic.main.activity_insurance.tv_bdh
import kotlinx.android.synthetic.main.activity_insurance.tv_bf
import kotlinx.android.synthetic.main.activity_insurance.tv_bxgsmc
import kotlinx.android.synthetic.main.activity_insurance.tv_bxrmc
import kotlinx.android.synthetic.main.activity_insurance.tv_bxrsfzh
import kotlinx.android.synthetic.main.activity_insurance.tv_cpbm
import kotlinx.android.synthetic.main.activity_insurance.tv_cph
import kotlinx.android.synthetic.main.activity_insurance.tv_lxdh
import kotlinx.android.synthetic.main.activity_insurance.tv_qbrq
import kotlinx.android.synthetic.main.activity_insurance.tv_scan
import kotlinx.android.synthetic.main.activity_insurance.tv_zbrq
import kotlinx.android.synthetic.main.fragment_dw_bdxx.*
import org.json.JSONObject
import java.lang.Exception

class InsuranceActivity : BaseActivity(), CollectBicycleView {
    public var mDeviceScanUtils: DeviceScanUtils?=null
    var xh = CollectDataActivity.mCarZcMsgData.xh
    var lsh = CollectDataActivity.mCarZcMsgData.lsh

    override fun netWorkTaskSuccess(commonResponse: CommonResponse) {
        when(commonResponse.getUrl()){
            Constants.GET_COMPANY_NAME ->{
                val nameEntity = GsonUtils.gson(commonResponse.getResponseString(), CompanyNameEntity::class.java)
                setSpinnerAdapter(nameEntity.data.insuranceCompanys)
                getCodeImage() //spiner有数据后才去获取qccode
            }
            Constants.DECRYPT_INSURANCE_MSG ->{
                LoadingDialog.getInstance().dismissLoadDialog()
                val qrMessageEntity = GsonUtils.gson(commonResponse.getResponseString(), InsuranceQrMessageEntity::class.java)
                setInsuranceMessage(qrMessageEntity.data)
            }
            Constants.GET_INSURANCEP_MES->{
                LoadingDialog.getInstance().dismissLoadDialog()
                val messageEntity = GsonUtils.gson(commonResponse.getResponseString(), DataStringMessageEntity::class.java)
                iv_qrcode.setImageBitmap(StringUtils.createQRCode(messageEntity.data))
            }
            Constants.SAVE_INSURANCEP_MES->{
                getInsuranceDetails()
            }
            Constants.GET_INSURANCE_DETAILS->{
                getDZPZImage()
            }
            Constants.GET_QR_CODE_BY_LSH ->{
                LoadingDialog.getInstance().dismissLoadDialog();
                val entity = GsonUtils.gson(commonResponse.getResponseString(), DataStringMessageEntity::class.java)
                intent.setClass(this,HpbfActivity::class.java)
                intent.putExtra("url",entity.data)
                startActivity(intent)
            }
        }
    }


    override fun netWorkTaskfailed(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()
        showErrorDialog(commonResponse.getResponseString())
    }
    /**
     * 注册完成获取电子凭证
     */
    private fun getDZPZImage() {
        var map = HashMap<String,String>()
        map.put("xh",xh)
        map.put("lsh",lsh)
        LoadingDialog.getInstance().showLoadDialog(this)
        mCollectBicyclePersenter!!.doNetworkTask(map,Constants.GET_QR_CODE_BY_LSH)
    }

    /**
     * 保险数据详情
     */
    fun getInsuranceDetails(){
        val map= HashMap<String, String>()
        map.put("xh ",xh)
        map.put("lsh",lsh)
        mCollectBicyclePersenter!!.doNetworkTask(map,Constants.GET_INSURANCE_DETAILS)
    }

    private fun setInsuranceMessage(data: String?) {
        try {
            val jb = JSONObject(data)
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
        } catch (e: Exception) {
            showErrorDialog(e.toString())
        }

    }

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val msgString = msg.obj as String
            when (msg.what) {
                DeviceScanUtils.OTHER_CODE -> if (!TextUtils.isEmpty(msgString)) {
                    analysisInsuranceData(msgString)
                }
            }
        }
    }

    /**
     * 解析保险数据
     */
    fun analysisInsuranceData(qrcode: String){

        val map= HashMap<String, String>()
        map.put("code",qrcode)
        LoadingDialog.getInstance().showLoadDialog(this)
        mCollectBicyclePersenter!!.doNetworkTask(map,Constants.DECRYPT_INSURANCE_MSG)
    }

    private fun setSpinnerAdapter(list: List<String>) {
        val adapter = ArrayAdapter<String>(this, R.layout.my_simple_spinner_item)
        adapter.setDropDownViewResource(R.layout.item_spinner__down_common)
        adapter.addAll(list)
        sp_bxmc.adapter = adapter
    }

    private var mCollectBicyclePersenter: CollectBicyclePersenter?=null
    override fun initView() {
        setPageTitle(getString(R.string.bxxx))
        mCollectBicyclePersenter=CollectBicyclePersenter(this)
        bindEvent()
        btn_gm.isChecked = true
        getBDType()
    }

    /**
     * 获取二维码
     */
    private fun getCodeImage() {
        val map=HashMap<String,String>()
        map.put("xh ",xh)
        map.put("lsh",lsh)
        map.put("companyName",sp_bxmc.selectedItem.toString())
        mCollectBicyclePersenter!!.doNetworkTask(map,Constants.GET_INSURANCEP_MES)
    }

    /**
     * 获取保险机构类型
     */
    private fun getBDType() {
        LoadingDialog.getInstance().showLoadDialog(this)
        mCollectBicyclePersenter!!.doNetworkTask(HashMap<String,String>(),Constants.GET_COMPANY_NAME)
    }

    private fun bindEvent() {
        btn_ok.setOnClickListener {
            if(rb_bgm.isChecked){
                getDZPZImage()
            }else{
                commitData()
            }

        }
        rb_buy.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: RadioGroup?, position: Int) {
                when (position) {
                    R.id.rb_bgm -> {
                        sv_bdxx.visibility = View.INVISIBLE
                    }
                    R.id.btn_gm -> {
                        sv_bdxx.visibility = View.VISIBLE
                    }
                }
            }

        })
        tv_scan.setOnClickListener {
            if (mDeviceScanUtils==null){
                mDeviceScanUtils= DeviceScanUtils(this,mHandler)
            }
            mDeviceScanUtils!!.startScan()
        }

        sp_bxmc.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                getCodeImage()
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

        map.put("xh",xh)
        map.put("lsh",lsh)
        map.put("sfzmhm", UserInfo.SFZMHM)
        LoadingDialog.getInstance().showLoadDialog(this)
        mCollectBicyclePersenter!!.doNetworkTask(map,Constants.SAVE_INSURANCEP_MES)
    }

    override fun getLayout(): Int {
        return R.layout.activity_insurance
    }
}
