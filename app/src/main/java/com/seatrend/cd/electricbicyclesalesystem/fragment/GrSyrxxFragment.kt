package com.seatrend.cd.electricbicyclesalesystem.fragment


import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.activity.CollectBicycleActivity
import com.seatrend.cd.electricbicyclesalesystem.common.BaseFragment
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.database.CodeTableSQLiteUtils
import com.seatrend.cd.electricbicyclesalesystem.util.OtherUtils
import com.seatrend.cd.electricbicyclesalesystem.util.StringUtils
import kotlinx.android.synthetic.main.fragment_gr_syrxx.*
import java.io.File


/**
 * A simple [Fragment] subclass.
 */
class GrSyrxxFragment : BaseFragment() {


    private var imgFile: File? = null
    private val CAMERA_REQUEST_CODE = 10
    private val FACE_RECOGNITION_CODE = 1002
    private var photoTag = 0 //拍照标记 按界面依次 标记

    public val photoMap = HashMap<String, String>()

    private val deteleTagArray = IntArray(2) //删除标记 0是原始状态 是原始状态则 拍照

    /**
     * 11 车辆标准照（厂商）  12 车架号照片（厂商） 13 其他  14 前后轮距照  15 整车后45°照  16 铭牌照   17 车架号  18 车辆标准照
     * 19 发票原件   20 合格证原件  21  身份证正面（国徽面） 22 身份证背面（人像面）  23 营业执照（原件）
     */
    private val sfzzmKey="21"
    private val sfzfmKey="22"

    private var veritySfzmhm=false //是否有资格 购买车辆

    private var  isFirst=true;
    private var syrHeadPhoto:ByteArray?=null
    private var rlsbjg="0"; //人脸识别标记




    override fun getLayoutView(inflater: LayoutInflater?, container: ViewGroup?): View {
        return inflater!!.inflate(R.layout.fragment_gr_syrxx, container, false)
    }

    override fun initView() {


        setSpinnerAdapter(sp_zsxzqh)
        setSpinnerAdapter(sp_lxzsxzqh)
        bindEvent()

        try {
            initData()
        }catch (e:java.lang.Exception){
            showToast(e.message.toString())
        }


    }

    private fun initData() {
        val xh=CollectBicycleActivity.mCarMessageEntity!!.data.xh
        val lsh=CollectBicycleActivity.mCarMessageEntity!!.data.lsh
        Glide.with(this).load(StringUtils.getGlideUrl(lsh,xh,"21")).centerCrop().placeholder(R.drawable.error_image).error(R.drawable.take_photo).listener(object : RequestListener<String, GlideDrawable> {
            override fun onException(e: Exception?, model: String, target: Target<GlideDrawable>, isFirstResource: Boolean): Boolean {
                return false
            }

            override fun onResourceReady(resource: GlideDrawable, model: String, target: Target<GlideDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                isFirst=false;
                return false
            }
        }).into(iv_sfzzm)
        Glide.with(this).load(StringUtils.getGlideUrl(lsh,xh,"22")).centerCrop().placeholder(R.drawable.error_image).error(R.drawable.take_photo).listener(object : RequestListener<String, GlideDrawable> {
            override fun onException(e: Exception?, model: String, target: Target<GlideDrawable>, isFirstResource: Boolean): Boolean {
                return false
            }

            override fun onResourceReady(resource: GlideDrawable, model: String, target: Target<GlideDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                isFirst=false;
                return false
            }
        }).into(iv_sfzfm)

        iv_sfzzm_delete.visibility=View.VISIBLE
        iv_sfzfm_delete.visibility=View.VISIBLE
        deteleTagArray[0]=0
        deteleTagArray[1]=0


        tv_sfzh.text=CollectBicycleActivity.mCarMessageEntity!!.data.czzjhm
        tv_xm.text=CollectBicycleActivity.mCarMessageEntity!!.data.czxm
        et_zz.setText(CollectBicycleActivity.mCarMessageEntity!!.data.czzsdz)
        OtherUtils.setSpinnerToDmz(CollectBicycleActivity.mCarMessageEntity!!.data.czzsqh,sp_zsxzqh)
        OtherUtils.setSpinnerToDmz(CollectBicycleActivity.mCarMessageEntity!!.data.czzzqh,sp_lxzsxzqh)
        et_jzzhm.setText(CollectBicycleActivity.mCarMessageEntity!!.data.jzzhm)
        et_lxdz.setText(CollectBicycleActivity.mCarMessageEntity!!.data.czzzdz)
        et_lxdh.setText(CollectBicycleActivity.mCarMessageEntity!!.data.czlxdh)

        if (!TextUtils.isEmpty(tv_sfzh.text.toString()) && !TextUtils.isEmpty(tv_xm.text.toString())){
            //身份证号不为空  验证下是否有 购车资格

            (activity as CollectBicycleActivity).verfity(tv_sfzh.text.toString(),tv_xm.text.toString())

        }



    }

    fun setVeritySfzmhm(b: Boolean){
        this.veritySfzmhm=b;
    }

    private fun bindEvent() {

        iv_sfzh_scan.setOnClickListener {
            goNfcReadPlugin()
        }

        iv_sfzzm.setOnClickListener {
            if (deteleTagArray[0]==0){
                photoTag=0;
                getPicFromCamera()
            }

        }
        iv_sfzzm_delete.setOnClickListener {
            deteleTagArray[0]=0;
            photoMap.remove(sfzzmKey)
            iv_sfzzm_delete.visibility=View.GONE
            iv_sfzzm.setImageResource(R.drawable.take_photo)
        }
        iv_sfzfm.setOnClickListener {
            if (deteleTagArray[1]==0){
                photoTag=1;
                getPicFromCamera()
            }

        }
        iv_sfzfm_delete.setOnClickListener {
            deteleTagArray[1]=0;
            photoMap.remove(sfzfmKey)
            iv_sfzfm_delete.visibility=View.GONE
            iv_sfzfm.setImageResource(R.drawable.take_photo)
        }

        btn_next.setOnClickListener {
            commitData()

        }
        btn_back.setOnClickListener {
            (activity as CollectBicycleActivity).rbXxsjPerformOnClick()
        }

        iv_rlsb_scan.setOnClickListener {
            if (syrHeadPhoto==null){
                showToast("请先读取身份证")
            }else{
                OtherUtils.goFaceComparePlugin(activity,syrHeadPhoto,FACE_RECOGNITION_CODE)
            }
        }

    }

    private fun setSpinnerAdapter(spinner: Spinner) {
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(R.layout.item_spinner__down_common)
        when (spinner.id) {
            R.id.sp_zsxzqh,R.id.sp_lxzsxzqh-> {
                val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.XSQY)
                for (db in sfzmmcList) {
                    val dmz = db.getDmz()
                    val dmsm1 = db.getDmsm1()
                    adapter.add(dmz + ":" + dmsm1)
                }

                spinner.adapter = adapter
            }

            R.id.sp_syxz-> {
                val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.SYXZ)
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

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            deteleTagArray[photoTag] = 1

            when (photoTag) {

                0 -> {
                    photoMap.put(sfzzmKey, imgFile!!.path)
                    Glide.with(this).load(imgFile).centerCrop().error(R.drawable.error_image).into(iv_sfzzm)
                    iv_sfzzm_delete.visibility = View.VISIBLE
                }
                1 -> {
                    photoMap.put(sfzfmKey, imgFile!!.path)
                    Glide.with(this).load(imgFile).centerCrop().error(R.drawable.error_image).into(iv_sfzfm)
                    iv_sfzfm_delete.visibility = View.VISIBLE
                }


            }

        } else if (requestCode == ID_CARD_READ_CODE && resultCode == RESULT_OK && data != null) {

            if (data.getStringExtra(Constants.NUMBER)!=tv_sfzh.text){
                //更换身份证，置为初始状态
                rlsbjg="0"
                tv_rlsbjg.text=getString(R.string.unpass)
                tv_rlsbjg.setTextColor(resources.getColor(R.color.red))
            }

            tv_sfzh.text =  data.getStringExtra(Constants.NUMBER)
            tv_xm.text =data.getStringExtra(Constants.NAME)
            syrHeadPhoto=data.getByteArrayExtra(Constants.PHOTO)
            et_zz!!.setText(data.getStringExtra(Constants.ADDRESS))
            showToast("身份证已读取")
            (activity as CollectBicycleActivity).verfity(data.getStringExtra(Constants.NUMBER),data.getStringExtra(Constants.NAME))

        }else if(requestCode==FACE_RECOGNITION_CODE && resultCode==RESULT_OK && data != null){

            // String ret = data.getStringExtra("data")
            rlsbjg="1"
            tv_rlsbjg.text=getString(R.string.passed)
            tv_rlsbjg.setTextColor(resources.getColor(R.color.green))
            showToast("人脸识别已通过")


        }
    }

    private fun commitData(){

        if (checkPhoto()){
            if (TextUtils.isEmpty(tv_sfzh.text)){
                showToast("请读取身份证")
                return
            }
            if (!veritySfzmhm){
                showToast(getString(R.string.you_can_not_buy_car))
                return
            }

            if (TextUtils.isEmpty(et_zz.text)){
                showToast("请填写住址")
                return
            }
            if (TextUtils.isEmpty(et_jzzhm.text)){
                showToast("请填写居住证号码")
                return
            }
            if (TextUtils.isEmpty(et_lxdz.text)){
                showToast("请填写联系地址")
                return
            }
            if (TextUtils.isEmpty(et_lxdh.text.toString())){
                showToast("请填写联系电话")
                return
            }
            if(!StringUtils.isPhoneNumber(et_lxdh.text.toString())){
                showToast("电话号码格式不对")
                return
            }
            if(rlsbjg == "0"){
                showToast("请先进行人脸识别")
                return
            }
            val zsxzqh = sp_zsxzqh.selectedItem.toString().split(":")[0]
            val lxzsxzqh = sp_lxzsxzqh.selectedItem.toString().split(":")[0]

            val map=HashMap<String,String>()
            map.put("czxm",tv_xm.text.toString())
            map.put("czzjhm",tv_sfzh.text.toString())
            map.put("czzsdz",et_zz.text.toString())
            map.put("czzzdz",et_lxdz.text.toString())
            map.put("czlxdh",et_lxdh.text.toString())
            map.put("jzzhm",et_jzzhm.text.toString())
            map.put("czzsqh",zsxzqh)
            map.put("czzzqh",lxzsxzqh)
            (activity as CollectBicycleActivity).updateCarMessage(map,"3")
        }

    }
    private fun checkPhoto():Boolean{
         //流程状态(1：已外检，2:已注册，3：已制证，E：归档完结，4：简阳居住证审核)
        //如果已提交过  则不需要判定强制项
        if(!isFirst){
            return true
        }
        if (TextUtils.isEmpty(photoMap.get(sfzzmKey))){
            showToast("请拍摄【身份证正面】照片")
            return false
        }
        if (TextUtils.isEmpty(photoMap.get(sfzfmKey))){
            showToast("请拍摄【身份证反面】照片")
            return false
        }
        return true
    }

    public fun commitPhoto() {
        (activity as CollectBicycleActivity).commitPhoto(photoMap,"3")
    }
}
