package com.seatrend.cd.electricbicyclesalesystem.fragment


import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Message
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.activity.CollectBicycleActivity
import com.seatrend.cd.electricbicyclesalesystem.common.BaseFragment
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.database.CodeTableSQLiteUtils
import com.seatrend.cd.electricbicyclesalesystem.util.DeviceScanUtils
import com.seatrend.cd.electricbicyclesalesystem.util.OtherUtils
import com.seatrend.cd.electricbicyclesalesystem.util.StringUtils
import kotlinx.android.synthetic.main.fragment_dw_syrxx.*
import java.io.File
import java.lang.Exception


/**
 * A simple [Fragment] subclass.
 */
class DwSyrxxFragment : BaseFragment() {

    private var imgFile: File? = null
    private val CAMERA_REQUEST_CODE = 20


    public val photoMap = HashMap<String, String>()
    private var deteleTagArray=0;
    /**
     * 11 车辆标准照（厂商）  12 车架号照片（厂商） 13 其他  14 前后轮距照  15 整车后45°照  16 铭牌照   17 车架号  18 车辆标准照
     * 19 发票原件   20 合格证原件  21  身份证正面（国徽面） 22 身份证背面（人像面）  23 营业执照（原件）
     */
    private val yyzzKey="23"

    public var mDeviceScanUtils:DeviceScanUtils?=null

    private var isFirst=true

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val msgString = msg.obj as String
            when (msg.what) {
                DeviceScanUtils.YYZZ_CODE -> if (!TextUtils.isEmpty(msgString)) {
                    val split = msgString.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    var tyshxydm: String? = null //统一社会信用代码
                    var mc: String? = null //名称
                    var djjg: String? = null //地址
                    for (i in split.indices) {
                        val split1 = split[i].split("：".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        if ("统一社会信用代码" == split1[0]) {
                            tyshxydm = split1[1]
                        } else if ("名称" == split1[0]) {
                            mc = split1[1]
                        } else if ("登记机关" == split1[0]) {
                            djjg = split1[1]
                        }
                    }

                    if (!TextUtils.isEmpty(tyshxydm)) {
                        tv_yyzz.setText(tyshxydm)
                    }
                    if (!TextUtils.isEmpty(mc)) {

                        tv_gsmc.setText(mc)
                    }
                }

            }
        }
    }


    override fun getLayoutView(inflater: LayoutInflater?, container: ViewGroup?): View {
        return inflater!!.inflate(R.layout.fragment_dw_syrxx, container, false)
    }

    override fun initView() {
        setSpinnerAdapter(sp_gsxzqh)
        setSpinnerAdapter(sp_dzxzqh)

        bindEvent()
        try {
            initData()
        }catch (e:Exception){
            showToast(e.message.toString())
        }

    }

    private fun initData() {

        val xh=CollectBicycleActivity.mCarMessageEntity!!.data.xh
        val lsh=CollectBicycleActivity.mCarMessageEntity!!.data.lsh
        Glide.with(this).load(StringUtils.getGlideUrl(lsh,xh,"23")).centerCrop().placeholder(R.drawable.error_image).error(R.drawable.take_photo).listener(object : RequestListener<String, GlideDrawable> {
            override fun onException(e: Exception?, model: String, target: Target<GlideDrawable>, isFirstResource: Boolean): Boolean {
                return false
            }

            override fun onResourceReady(resource: GlideDrawable, model: String, target: Target<GlideDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                isFirst=false;
                return false
            }
        }).into(iv_yyzz)

        tv_yyzz.setText(CollectBicycleActivity.mCarMessageEntity!!.data.czzjhm)
        tv_gsmc.setText(CollectBicycleActivity.mCarMessageEntity!!.data.czxm)

        OtherUtils.setSpinnerToDmz(CollectBicycleActivity.mCarMessageEntity!!.data.czzzqh,sp_gsxzqh)
        OtherUtils.setSpinnerToDmz(CollectBicycleActivity.mCarMessageEntity!!.data.czzsqh,sp_dzxzqh)
        et_lxdz.setText(CollectBicycleActivity.mCarMessageEntity!!.data.czzsdz)
        et_lxdh.setText(CollectBicycleActivity.mCarMessageEntity!!.data.czlxdh)
    }

    private fun bindEvent() {

        iv_yyzz.setOnClickListener {
            if (deteleTagArray==0){
                getPicFromCamera()
            }
        }
        iv_yyzz_delete.setOnClickListener {
            photoMap.remove(yyzzKey)
            iv_yyzz.setImageResource(R.drawable.take_photo)
            iv_yyzz_delete.visibility=View.GONE
        }
        iv_yyzz_scan.setOnClickListener {
            if (mDeviceScanUtils==null){
                mDeviceScanUtils=DeviceScanUtils(activity,mHandler)
            }
            mDeviceScanUtils!!.startScan()
        }

        btn_next.setOnClickListener {
            commitData()
        }
        btn_back.setOnClickListener {
            (activity as CollectBicycleActivity).rbXxsjPerformOnClick()
        }
    }

    private fun setSpinnerAdapter(spinner: Spinner) {
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(R.layout.item_spinner__down_common)
        when (spinner.id) {
            R.id.sp_gsxzqh, R.id.sp_dzxzqh -> {
                val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.XSQY)
                for (db in sfzmmcList) {
                    val dmz = db.getDmz()
                    val dmsm1 = db.getDmsm1()
                    adapter.add(dmz + ":" + dmsm1)
                }

                spinner.adapter = adapter
            }


        }

    }
    private fun getPicFromCamera() {
        //用于保存调用相机拍照后所生成的文件
        val tempFile = File(Constants.IMAGE_PATH)//,
        val imageUri: Uri
        if (!tempFile.exists()) {
            tempFile.mkdirs()
        }
        imgFile = File(tempFile, System.currentTimeMillis().toString() + ".jpg")
        if (Build.VERSION.SDK_INT >= 24) {//判断版本
            imageUri = FileProvider.getUriForFile(context, getString(R.string.authority), imgFile)
        } else {
            imageUri = Uri.fromFile(imgFile)
        }
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==CAMERA_REQUEST_CODE && resultCode==RESULT_OK){
            photoMap.put(yyzzKey,imgFile!!.path)
            deteleTagArray=1;
        Glide.with(this).load(imgFile).centerCrop().error(R.drawable.error_image).into(iv_yyzz)
            iv_yyzz_delete.visibility=View.VISIBLE
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        if (mDeviceScanUtils!=null){
            mDeviceScanUtils!!.releaseDeviceScan()
        }
    }

    private fun commitData(){

        //流程状态(1：已外检，2:已注册，3：已制证，E：归档完结，4：简阳居住证审核)
        //如果已提交过  则不需要判定强制项
        if (TextUtils.isEmpty(photoMap.get(yyzzKey)) && isFirst){
            showToast("请拍摄【营业执照原件】照片")
            return
        }
        if (TextUtils.isEmpty(tv_yyzz.text.toString())){
            showToast("请扫描营业执照")
            return
        }
        if (TextUtils.isEmpty(et_lxdz.text.toString())){
            showToast("请输入联系地址")
            return
        }
        if (TextUtils.isEmpty(et_lxdh.text.toString())){
            showToast("请输入联系电话")
            return
        }
        if (!StringUtils.isPhoneNumber(et_lxdh.text.toString())){
            showToast("电话号码格式不对")
            return
        }

        val gsxzqh = sp_gsxzqh.selectedItem.toString().split(":")[0]
        val dzxzqh = sp_dzxzqh.selectedItem.toString().split(":")[0]
        val  map=HashMap<String,String>()
        map.put("czzjhm",tv_yyzz.text.toString())
        map.put("czxm",tv_gsmc.text.toString())
        map.put("czzjlx","N")
        map.put("czlxdh",et_lxdh.text.toString())
        map.put("czzzdz",et_lxdz.text.toString())
        map.put("czzsdz",gsxzqh)
        map.put("czzzqh",dzxzqh)
        (activity as CollectBicycleActivity).updateCarMessage(map,"3")


    }

     fun commitPhoto() {
        (activity as CollectBicycleActivity).commitPhoto(photoMap,"3")
    }

}
