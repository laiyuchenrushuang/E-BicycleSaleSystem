package com.seatrend.cd.electricbicyclesalesystem.fragment


import android.app.Activity.RESULT_OK
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_dw_dlrxx.*
import java.io.File
import kotlin.collections.HashMap


/**
 * A simple [Fragment] subclass.
 */
class DwDlrxxFragment : BaseFragment() {

    private var imgFile: File? = null
    private val CAMERA_REQUEST_CODE = 20
    private val FACE_RECOGNITION_CODE = 40

    private var photoTag = 0 //拍照标记 按界面依次 标记

    public val photoMap = HashMap<String, String>()

    private val deteleTagArray = IntArray(2) //删除标记 0是原始状态 是原始状态则 拍照

    /**
     *照片类型（11-车辆标准照(厂商),12-车架号照片(厂商),14-前后轮距照,17-整车后45°照,16-铭牌照(厂商),19-发票原件(销售商),
     * 21-身份证正面照（国徽面）,22-身份证背面照(人像面),24-铭牌照(销售商),25-电池照(厂商),18-车辆标准照(销售商)）
     */
    private val sfzzmKey="21"
    private val sfzfmKey="22"
    private var rlsbjg="0"

    private var dlrHeadPhoto: ByteArray? = null//代理人头像照片

    private var isFirst=true


    override fun getLayoutView(inflater: LayoutInflater?, container: ViewGroup?): View {
        return inflater!!.inflate(R.layout.fragment_dw_dlrxx, container, false)
    }

    override fun initView() {



        setSpinnerAdapter(sp_sfzmmc)

        bindEvent()

        initData()

    }

    private fun initData() {

        val xh=CollectBicycleActivity.mCarMessageEntity!!.data.xh
        val lsh=CollectBicycleActivity.mCarMessageEntity!!.data.lsh
        Glide.with(this).load(StringUtils.getGlideUrl(lsh,xh,sfzzmKey)).centerCrop().placeholder(R.drawable.error_image).error(R.drawable.take_photo).listener(object : RequestListener<String, GlideDrawable> {
            override fun onException(e:Exception?, model: String, target: Target<GlideDrawable>, isFirstResource: Boolean): Boolean {
                return false
            }

            override fun onResourceReady(resource: GlideDrawable, model: String, target: Target<GlideDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                isFirst=false;
                return false
            }
        }).into(iv_sfzzm)
        Glide.with(this).load(StringUtils.getGlideUrl(lsh,xh,sfzfmKey)).centerCrop().placeholder(R.drawable.error_image).error(R.drawable.take_photo).listener(object : RequestListener<String, GlideDrawable> {
            override fun onException(e:Exception?, model: String, target: Target<GlideDrawable>, isFirstResource: Boolean): Boolean {
                return false
            }

            override fun onResourceReady(resource: GlideDrawable, model: String, target: Target<GlideDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                isFirst=false;
                return false
            }
        }).into(iv_sfzfm)

        OtherUtils.setSpinnerToDmz(CollectBicycleActivity.mCarMessageEntity!!.data.dlrzjlx,sp_sfzmmc)
        tv_zjhm.text=CollectBicycleActivity.mCarMessageEntity!!.data.dlrzjhm
        tv_xm.text=CollectBicycleActivity.mCarMessageEntity!!.data.dlrxm
        tv_rlbd.text=if ("0".equals(CollectBicycleActivity.mCarMessageEntity!!.data.rlsbjg))getString(R.string.unpass) else getString(R.string.passed)
        if (tv_rlbd.text.equals(getString(R.string.passed))){
            tv_rlbd.setTextColor(resources.getColor(R.color.green))
        }
        et_zz.setText(CollectBicycleActivity.mCarMessageEntity!!.data.dlrzsdz)

    }

    private fun bindEvent() {
        iv_sfzzm.setOnClickListener {

            if (deteleTagArray[0]==0){
                photoTag=0;
                getPicFromCamera()
            }

        }
        iv_sfzzm_delete.setOnClickListener {
            photoMap.remove(sfzzmKey)
            iv_sfzzm_delete.visibility=View.GONE
            deteleTagArray[0]=0
            iv_sfzzm.setImageResource(R.drawable.take_photo)

        }
        iv_sfzfm.setOnClickListener {
            if (deteleTagArray[1]==0){
                photoTag=1;
                getPicFromCamera()
            }
        }
        iv_sfzfm_delete.setOnClickListener {
            photoMap.remove(sfzfmKey)
            iv_sfzfm_delete.visibility=View.GONE
            deteleTagArray[1]=0
            iv_sfzfm.setImageResource(R.drawable.take_photo)
        }
        iv_zjhm_scan.setOnClickListener {
            goNfcReadPlugin()
        }
        iv_rlbd_scan.setOnClickListener {
            if (dlrHeadPhoto==null){
                showToast("请先读取身份证")
            }else{
                OtherUtils.goFaceComparePlugin(activity,dlrHeadPhoto,FACE_RECOGNITION_CODE)
            }
        }

        btn_next.setOnClickListener {
            commitData()
        }

        btn_back.setOnClickListener {
            (activity as CollectBicycleActivity).rbSyrPerformOnClick()
        }

    }

    private fun setSpinnerAdapter(spinner: Spinner) {
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(R.layout.item_spinner__down_common)
        when (spinner.id) {
            R.id.sp_sfzmmc -> {
                val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.SFZMMC)
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
            deteleTagArray[photoTag]=1
            when(photoTag){
                0->{
                    photoMap.put(sfzzmKey,imgFile!!.path)
                    Glide.with(this).load(imgFile).centerCrop().error(R.drawable.error_image).into(iv_sfzzm)
                    iv_sfzzm_delete.visibility=View.VISIBLE
                }

                1->{
                    photoMap.put(sfzfmKey,imgFile!!.path)
                    Glide.with(this).load(imgFile).centerCrop().error(R.drawable.error_image).into(iv_sfzfm)
                    iv_sfzfm_delete.visibility=View.VISIBLE
                }
            }


        }else if(requestCode == ID_CARD_READ_CODE && resultCode == RESULT_OK && data != null){
            if(!TextUtils.isEmpty(data.getStringExtra(Constants.NUMBER))&&data.getStringExtra(Constants.NUMBER)!=tv_zjhm.text){
                //更换身份证 将置为初始状态，防止识别后更换身份证的读取
                rlsbjg="0";
                tv_rlbd.text=getString(R.string.unpass);
                tv_rlbd.setTextColor(resources.getColor(R.color.red));
            }
            tv_zjhm.text=data.getStringExtra(Constants.NAME);
            tv_xm.text=data.getStringExtra(Constants.NUMBER);
            et_zz!!.setText(data.getStringExtra(Constants.ADDRESS))
            dlrHeadPhoto=data.getByteArrayExtra(Constants.PHOTO);

            showToast("身份证已读取");
        }else if(requestCode==FACE_RECOGNITION_CODE && resultCode==RESULT_OK && data != null){

            // String ret = data.getStringExtra("data");
            rlsbjg="1";
            tv_rlbd.text=getString(R.string.passed)
            tv_rlbd.setTextColor(resources.getColor(R.color.green))
            showToast("人脸识别已通过")


        }


    }

     fun commitPhoto(){
         (activity as CollectBicycleActivity).commitPhoto(photoMap,"4")
    }

    private fun commitData(){
        if (checkPhoto()){
            if (TextUtils.isEmpty(tv_zjhm.text.toString())){
                showToast("请先读取身份证")
                return
            }
            if (TextUtils.isEmpty(et_zz.text.toString())){
                showToast("请填写住址")
                return
            }
            if (rlsbjg=="0"){
                showToast("请先进行人脸识别")
                return
            }

            val map=HashMap<String,String>()
            map.put("dlrzjhm",tv_zjhm.text.toString())
            map.put("dlrzjlx",sp_sfzmmc.selectedItem.toString().split(":")[0])
            map.put("dlrxm",tv_xm.text.toString())
            map.put("dlrzsdz",et_zz.text.toString())
            map.put("rlsbjg",rlsbjg)
            (activity as CollectBicycleActivity).updateCarMessage(map,"4")
        }

    }

    private fun checkPhoto():Boolean{

        if (!isFirst){
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



}
