package com.seatrend.cd.electricbicyclesalesystem.fragment


import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import butterknife.BindBool
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target

import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.activity.CollectBicycleActivity
import com.seatrend.cd.electricbicyclesalesystem.common.BaseFragment
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.database.CodeTableSQLiteUtils
import com.seatrend.cd.electricbicyclesalesystem.util.OtherUtils
import com.seatrend.cd.electricbicyclesalesystem.util.StringUtils
import kotlinx.android.synthetic.main.fragment_dw_xxsj.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap


/**
 * A simple [Fragment] subclass.
 */
class DwXxsjFragment : BaseFragment() {

    private var imgFile: File? = null
    private val CAMERA_REQUEST_CODE = 10
    private var photoTag = 0 //拍照标记 按界面依次 标记

    public val photoMap = HashMap<String, String>()

    private val deteleTagArray = IntArray(5) //删除标记 0是原始状态 是原始状态则 拍照

    /**
     * 照片类型（11-车辆标准照(厂商),12-车架号照片(厂商),14-前后轮距照,17-整车后45°照,16-铭牌照(厂商)
     * ,19-发票原件(销售商),21-身份证正面照（国徽面）,22-身份证背面照(人像面),24-铭牌照(销售商),25-电池照(厂商),18-车辆标准照(销售商)）
     */
    private val mpzKey="24"
    private val clbzzKey="18"
    private val fpyjKey="19"
    private val hgzKey="20"

    private var isFirst=true

    override fun getLayoutView(inflater: LayoutInflater?, container: ViewGroup?): View {
        return inflater!!.inflate(R.layout.fragment_dw_xxsj, container, false)
    }

    override fun initView() {
        bindEvent()

            initData()


    }

    private fun initData() {

        val xh=CollectBicycleActivity.mCarMessageEntity!!.data.xh
        val lsh=CollectBicycleActivity.mCarMessageEntity!!.data.lsh



            Glide.with(this).load(StringUtils.getGlideUrl(lsh,xh,mpzKey)).centerCrop().placeholder(R.drawable.error_image).error(R.drawable.take_photo).listener(mRequestListener).into(iv_mpz)
            Glide.with(this).load(StringUtils.getGlideUrl(lsh,xh,clbzzKey)).centerCrop().placeholder(R.drawable.error_image).error(R.drawable.take_photo).listener(mRequestListener).into(iv_clbzz)
            Glide.with(this).load(StringUtils.getGlideUrl(lsh,xh,fpyjKey)).centerCrop().placeholder(R.drawable.error_image).error(R.drawable.take_photo).listener(mRequestListener).into(iv_fpyj)
            Glide.with(this).load(StringUtils.getGlideUrl(lsh,xh,hgzKey)).centerCrop().placeholder(R.drawable.error_image).error(R.drawable.take_photo).listener(mRequestListener).into(iv_hgzyj)



        if (CollectBicycleActivity.mCarMessageEntity!!.data.hdrq>0){
            tv_hqsj.text=StringUtils.longToStringDataNoHour(CollectBicycleActivity.mCarMessageEntity!!.data.hdrq)
        }else{
            tv_hqsj.text=getString(R.string.dian_ji_xz)
        }
        if (CollectBicycleActivity.mCarMessageEntity!!.data.djrq>0){
            tv_djrq.text=StringUtils.longToStringDataNoHour(CollectBicycleActivity.mCarMessageEntity!!.data.djrq)
        }else{
            tv_djrq.text=getString(R.string.dian_ji_xz)
        }


        OtherUtils.setSpinnerToDmz(CollectBicycleActivity.mCarMessageEntity!!.data.hdfs,sp_hqfs)
        OtherUtils.setSpinnerToDmz(CollectBicycleActivity.mCarMessageEntity!!.data.syxz,sp_syxz)


    }

   val mRequestListener= object : RequestListener<String, GlideDrawable> {

        override fun onException(e: Exception?, model: String, target: Target<GlideDrawable>, isFirstResource: Boolean): Boolean {
            return false
        }

        override fun onResourceReady(resource: GlideDrawable, model: String, target: Target<GlideDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {

            isFirst=false;
            return false
        }
    }


    private fun bindEvent(){
        tv_hqsj.setOnClickListener { showTimeDialog(0) }
        tv_djrq.setOnClickListener { showTimeDialog(1) }
        setSpinnerAdapter(sp_hqfs)
        setSpinnerAdapter(sp_syxz)

        iv_mpz.setOnClickListener {
            if (deteleTagArray[0]==0){
                photoTag=0
                getPicFromCamera() }
            }

        iv_mpz_delete.setOnClickListener {
            photoMap.remove(mpzKey)
            deteleTagArray[0]=0
            iv_mpz.setImageResource(R.drawable.take_photo)
            iv_mpz_delete.visibility=View.GONE
        }

        iv_clbzz.setOnClickListener {
            if (deteleTagArray[1]==0){
                photoTag=1
                getPicFromCamera()
            }
             }
        iv_clbzz_delete.setOnClickListener {
            photoMap.remove(clbzzKey)
            deteleTagArray[1]=0
            iv_clbzz.setImageResource(R.drawable.take_photo)
            iv_clbzz_delete.visibility=View.GONE
        }

        iv_fpyj.setOnClickListener {
            if (deteleTagArray[2]==0){
                photoTag=2
                getPicFromCamera()
            }
             }
        iv_fpyj_delete.setOnClickListener {
            photoMap.remove(fpyjKey)
            deteleTagArray[2]=0
            iv_fpyj.setImageResource(R.drawable.take_photo)
            iv_fpyj_delete.visibility=View.GONE
        }

        iv_hgzyj.setOnClickListener {
            if (deteleTagArray[3]==0){
                photoTag=3
                getPicFromCamera()
            }
             }
        iv_hgzyj_delete.setOnClickListener {
            photoMap.remove(hgzKey)
            deteleTagArray[3]=0
            iv_hgzyj.setImageResource(R.drawable.take_photo)
            iv_hgzyj_delete.visibility=View.GONE
        }
        btn_next.setOnClickListener {
            commitData()
        }
        btn_back.setOnClickListener {
            (activity as CollectBicycleActivity).rbJcsjPerformOnClick()
        }

    }

    private fun setSpinnerAdapter(spinner: Spinner) {
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(R.layout.item_spinner__down_common)
        when (spinner.id) {
            R.id.sp_hqfs-> {
                val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.HDFS)
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
            val dateToStamp = dateToStamp(time)
            if (dateToStamp - System.currentTimeMillis() > 0 && tag == 0) {
                Toast.makeText(context, "日期不能超过当前日期", Toast.LENGTH_SHORT).show()
            } else {
                if (tag == 0) {
                    tv_hqsj.text=time

                } else if (tag == 1) {
                    tv_djrq.text=time
                }
                dialog.dismiss()
            }
        }


    }

    override fun dateToStamp(s: String): Long {

        try {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
            val date = simpleDateFormat.parse(s)
            return date.time
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return 0
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
            deteleTagArray[photoTag]=1;
            when(photoTag){

                0 ->{
                    photoMap.put(mpzKey,imgFile!!.path)
                    Glide.with(this).load(imgFile).centerCrop().error(R.drawable.error_image).into(iv_mpz)
                    iv_mpz_delete.visibility=View.VISIBLE
                }
                1 ->{
                    photoMap.put(clbzzKey,imgFile!!.path)
                    Glide.with(this).load(imgFile).centerCrop().error(R.drawable.error_image).into(iv_clbzz)
                    iv_clbzz_delete.visibility=View.VISIBLE
                }
                2 ->{
                    photoMap.put(fpyjKey,imgFile!!.path)
                    Glide.with(this).load(imgFile).centerCrop().error(R.drawable.error_image).into(iv_fpyj)
                    iv_fpyj_delete.visibility=View.VISIBLE
                }
                3 ->{
                    photoMap.put(hgzKey,imgFile!!.path)
                    Glide.with(this).load(imgFile).centerCrop().error(R.drawable.error_image).into(iv_hgzyj)
                    iv_hgzyj_delete.visibility=View.VISIBLE
                }


            }

        }


    }

    private fun commitData(){
        if (checkPhoto()){
            if (getString(R.string.dian_ji_xz).equals(tv_hqsj.text)){
                showToast("请选择获取日期")
                return
            }
            if (getString(R.string.dian_ji_xz).equals(tv_djrq.text)){
                showToast("请选择登记日期")
                return
            }

            val hqfs = sp_hqfs.selectedItem.toString().split(":")[0]
            val syxz = sp_syxz.selectedItem.toString().split(":")[0]

            val map=HashMap<String,String>()
            map.put("hdrq",tv_hqsj.text.toString())
            map.put("djrq",tv_djrq.text.toString())
            map.put("hdfs",hqfs)
            map.put("clyt",syxz)

            (activity as CollectBicycleActivity).updateCarMessage(map,"2")
        }
    }

    public fun commitPhoto(){
        (activity as CollectBicycleActivity).commitPhoto(photoMap,"2")
    }

    private fun checkPhoto():Boolean{
        //流程状态(1：已外检，2:已注册，3：已制证，E：归档完结，4：简阳居住证审核)
        //如果已提交过  则不需要判定强制项
        if(!isFirst){
            return true
        }

        if (TextUtils.isEmpty(photoMap.get(mpzKey))){
            showToast("请拍摄【铭牌照】照片")
            return false
        }
        if (TextUtils.isEmpty(photoMap.get(clbzzKey))){
            showToast("请拍摄【车辆标准照】照片")
            return false
        }
        if (TextUtils.isEmpty(photoMap.get(fpyjKey))){
            showToast("请拍摄【发票原件】照片")
            return false
        }

        /*if (TextUtils.isEmpty(photoMap.get(hgzKey))){
            showToast("请拍摄【合格证原件】照片")
            return false
        }*/
        return true
    }

}
