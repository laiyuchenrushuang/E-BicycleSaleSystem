package com.seatrend.cd.electricbicyclesalesystem.activity

import android.app.Dialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.*
import com.seatrend.cd.electricbicyclesalesystem.fragment.*
import com.seatrend.cd.electricbicyclesalesystem.persenter.CollectBicyclePersenter
import com.seatrend.cd.electricbicyclesalesystem.util.GsonUtils
import com.seatrend.cd.electricbicyclesalesystem.util.LoadingDialog
import com.seatrend.cd.electricbicyclesalesystem.util.OtherUtils
import com.seatrend.cd.electricbicyclesalesystem.view.CollectBicycleView
import kotlinx.android.synthetic.main.activity_company_buy_bicycle.*
import java.io.File
import kotlin.Exception

class CollectBicycleActivity : BaseActivity(), CollectBicycleView {




    var lastFragment:Fragment?=null

    private val mDwJcsjFragment=DwJcsjFragment();
    private val mDwXxsjFragment=DwXxsjFragment();
    private val mDwSyrxxFragment=DwSyrxxFragment();
    private val mDwDlrxxFragment=DwDlrxxFragment();
    private val mDwBdxxFragment=DwBdxxFragment();
    private val mDwWcFragment=DwWcFragment();


    private val mGrSyrxxFragment=GrSyrxxFragment();

    private var type:String?=null
    private var taskType:String?=null //提交业务的类型

    private var mCollectBicyclePersenter: CollectBicyclePersenter?=null

    public var uploadPhotoSuccessNumber=0
    public var uploadPhotoFileadNumber=0
    public var uploadPhotoNumber=0

    public var mInsuranceDetailsEntity:InsuranceDetailsEntity?=null


    companion object {
         var mCarMessageEntity: CarMessageEntity?=null
    }

    override fun initView() {

        if ("0".equals(type)){
            setPageTitle(getString(R.string.dwgc))
        }else{
            setPageTitle(getString(R.string.grgc))
        }
        switchFrament(lastFragment, mDwJcsjFragment)
        bindEvent();
        mCollectBicyclePersenter=CollectBicyclePersenter(this)

        //流程状态(1：已外检，2:已注册，3：已制证，E：归档完结，4：简阳居住证审核)
        //如果已提交过  则不需要判定强制项
        if("2".equals(mCarMessageEntity!!.data.lczt)){

            if ("0".equals(type)){
                rb_jcsj.isEnabled=true
                rb_xxsj.isEnabled=true
                rb_syrxx.isEnabled=true
                rb_dlrxx.isEnabled=true
                rb_bdxx.isEnabled=true
                // rb_wc.isEnabled=true

                view_bg1.visibility=View.VISIBLE
                view_bg2.visibility=View.VISIBLE
                view_bg3.visibility=View.VISIBLE
                view_bg4.visibility=View.VISIBLE
                // view_bg5.visibility=View.VISIBLE
            }else{
                rb_jcsj.isEnabled=true
                rb_xxsj.isEnabled=true
                rb_syrxx.isEnabled=true
                rb_bdxx.isEnabled=true
                // rb_wc.isEnabled=true

                view_bg1.visibility=View.VISIBLE
                view_bg2.visibility=View.VISIBLE
                view_bg3.visibility=View.VISIBLE
                // view_bg5.visibility=View.VISIBLE
            }

        }

    }

    override fun getLayout(): Int {
        // 0 是公司   1是个人
         type=intent.getStringExtra(Constants.TYPE)
        if ("0".equals(type)){
            return R.layout.activity_company_buy_bicycle
        }else{
            return R.layout.activity_gr_buy_bicycle
        }
    }

     fun bindEvent(){
         m_radio_group.setOnCheckedChangeListener(object :RadioGroup.OnCheckedChangeListener{
             override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                 when (checkedId) {
                     R.id.rb_jcsj -> switchFrament(lastFragment, mDwJcsjFragment)
                     R.id.rb_xxsj -> switchFrament(lastFragment, mDwXxsjFragment)
                     R.id.rb_syrxx ->
                     if ("0".equals(type)){
                         switchFrament(lastFragment, mDwSyrxxFragment)
                     }else{
                         switchFrament(lastFragment, mGrSyrxxFragment)
                     }
                     R.id.rb_dlrxx -> switchFrament(lastFragment, mDwDlrxxFragment)
                     R.id.rb_bdxx -> switchFrament(lastFragment, mDwBdxxFragment)
                     R.id.rb_wc -> switchFrament(lastFragment, mDwWcFragment)
                 }
             }
         })

         ivBack!!.setOnClickListener(View.OnClickListener {
             showTipsDialog()
         })

     }

     fun switchFrament(from: Fragment?, to: Fragment?) {
         if (from !== to) {
             lastFragment = to
             val fm = supportFragmentManager
             val ft = fm.beginTransaction()
             if (!to!!.isAdded) {
                 if (from != null) {
                     ft.hide(from)
                 }
                 if (to != null) {
                     ft.add(R.id.framelayout, to).commit()
                 }
             } else {
                 if (from != null) {
                     ft.hide(from)
                 }
                 if (to != null) {
                     ft.show(to).commit()
                 }
             }
         }

     }

    override fun netWorkTaskSuccess(commonResponse: CommonResponse) {

        try {
            if (commonResponse.getUrl().equals(Constants.UPDATE_CAR_MSG) && "1".equals(taskType)){
                LoadingDialog.getInstance().dismissLoadDialog()
                val baseEntity = GsonUtils.gson(commonResponse.getResponseString(), BaseEntity::class.java)
                showToast(baseEntity.getMessage())
                rb_xxsj.isEnabled=true;
                rb_xxsj.performClick()
                view_bg1.visibility=View.VISIBLE
            }else if (commonResponse.getUrl().equals(Constants.UPDATE_CAR_MSG) && "2".equals(taskType)){
                if (mDwXxsjFragment.photoMap.size>0){
                    mDwXxsjFragment.commitPhoto()
                }else{
                    LoadingDialog.getInstance().dismissLoadDialog()
                    rb_syrxx.isEnabled=true;
                    rb_syrxx.performClick()
                    view_bg2.visibility=View.VISIBLE
                }

            }else if (commonResponse.getUrl().equals(Constants.SAVE_PHOTO) && "2".equals(taskType)){

                uploadPhotoSuccessNumber++;
                if (uploadPhotoNumber==(uploadPhotoSuccessNumber+uploadPhotoFileadNumber)){
                    LoadingDialog.getInstance().dismissLoadDialog()
                    showToast("成功上传"+uploadPhotoSuccessNumber+"张，失败"+uploadPhotoFileadNumber+"张")
                    rb_syrxx.isEnabled=true;
                    rb_syrxx.performClick()
                    view_bg2.visibility=View.VISIBLE
                }

            }else if (commonResponse.getUrl().equals(Constants.UPDATE_CAR_MSG) && "3".equals(taskType)){
                // 0 是公司   1是个人
                if ("0".equals(type)){

                    if (mDwSyrxxFragment.photoMap.size>0){
                        mDwSyrxxFragment.commitPhoto()
                    }else{
                        LoadingDialog.getInstance().dismissLoadDialog()
                        rb_dlrxx.isEnabled=true;
                        rb_dlrxx.performClick()
                        view_bg3.visibility=View.VISIBLE
                    }
                }else{
                    if (mGrSyrxxFragment.photoMap.size>0){
                        mGrSyrxxFragment.commitPhoto()
                    }else{
                        LoadingDialog.getInstance().dismissLoadDialog()
                        rb_bdxx.isEnabled=true;
                        rb_bdxx.performClick()
                        view_bg3.visibility=View.VISIBLE
                    }

                }

            }else if (commonResponse.getUrl().equals(Constants.SAVE_PHOTO) && "3".equals(taskType)){

                uploadPhotoSuccessNumber++;
                if (uploadPhotoNumber==(uploadPhotoSuccessNumber+uploadPhotoFileadNumber)){
                    LoadingDialog.getInstance().dismissLoadDialog()
                    showToast("成功上传"+uploadPhotoSuccessNumber+"张，失败"+uploadPhotoFileadNumber+"张")
                    // 0 是公司   1是个人
                    if ("0".equals(type)){
                        rb_dlrxx.isEnabled=true;
                        rb_dlrxx.performClick()
                        view_bg3.visibility=View.VISIBLE
                    }else{
                        rb_bdxx.isEnabled=true;
                        rb_bdxx.performClick()
                        view_bg3.visibility=View.VISIBLE
                    }

                }

            }else if (commonResponse.getUrl().equals(Constants.UPDATE_CAR_MSG) && "4".equals(taskType)){
                // 0 是公司   1是个人
                if ("0".equals(type)){

                    if (mDwDlrxxFragment.photoMap.size>0){
                        mDwDlrxxFragment.commitPhoto()
                    }else{
                        rb_bdxx.isEnabled=true;
                        rb_bdxx.performClick()
                        view_bg4.visibility=View.VISIBLE
                    }


                }else{
                   // mGrSyrxxFragment.commitPhoto()
                }

            }else if (commonResponse.getUrl().equals(Constants.SAVE_PHOTO) && "4".equals(taskType)){

                uploadPhotoSuccessNumber++;
                if (uploadPhotoNumber==(uploadPhotoSuccessNumber+uploadPhotoFileadNumber)){
                    LoadingDialog.getInstance().dismissLoadDialog()
                    showToast("成功上传"+uploadPhotoSuccessNumber+"张，失败"+uploadPhotoFileadNumber+"张")
                    // 0 是公司   1是个人
                    if ("0".equals(type)){
                        rb_bdxx.isEnabled=true;
                        rb_bdxx.performClick()
                        view_bg4.visibility=View.VISIBLE
                    }else{
                       // rb_bdxx.performClick()
                    }

                }

            }else if (commonResponse.getUrl().equals(Constants.GET_INSURANCEP_MES)){
                LoadingDialog.getInstance().dismissLoadDialog()

                val messageEntity = GsonUtils.gson(commonResponse.getResponseString(), DataStringMessageEntity::class.java)
                mDwBdxxFragment.setQrImage(messageEntity.data)

            }else if (commonResponse.getUrl().equals(Constants.GET_COMPANY_NAME)){
                LoadingDialog.getInstance().dismissLoadDialog()

                val nameEntity = GsonUtils.gson(commonResponse.getResponseString(), CompanyNameEntity::class.java)
                mDwBdxxFragment.setCompanyName(nameEntity.data.insuranceCompanys)

            }else if (commonResponse.getUrl().equals(Constants.DECRYPT_INSURANCE_MSG)){
                LoadingDialog.getInstance().dismissLoadDialog()
                val qrMessageEntity = GsonUtils.gson(commonResponse.getResponseString(), InsuranceQrMessageEntity::class.java)
                mDwBdxxFragment.setInsuranceMessage(qrMessageEntity.data)

            }else if (commonResponse.getUrl().equals(Constants.SAVE_INSURANCEP_MES)){
                //LoadingDialog.getInstance().dismissLoadDialog()
               // val baseEntity = GsonUtils.gson(commonResponse.getResponseString(), BaseEntity::class.java)
               // showToast(baseEntity.getMessage())
                //rb_wc.performClick()
                getInsuranceDetails()

            }else if (commonResponse.getUrl().equals(Constants.GET_INSURANCE_DETAILS)){
                LoadingDialog.getInstance().dismissLoadDialog()
                mInsuranceDetailsEntity = GsonUtils.gson(commonResponse.getResponseString(), InsuranceDetailsEntity::class.java)
                rb_wc.isEnabled=true;
                rb_wc.performClick()
                // 0 是公司   1是个人
                if ("0".equals(type)){
                    view_bg5.visibility=View.VISIBLE
                }else{
                    view_bg4.visibility=View.VISIBLE
                }

            }else if (commonResponse.getUrl().equals(Constants.VERIFY)){
                val vertiyEntity = GsonUtils.gson(commonResponse.getResponseString(), VertiyEntity::class.java)
                if (vertiyEntity.data.codeX==1){
                    //有资格
                    mGrSyrxxFragment.setVeritySfzmhm(true)
                }else{
                    showErrorDialog("抱歉，当前用户暂无资格购买车辆，原因: "+vertiyEntity.data.messageX)
                }


            }else if (commonResponse.getUrl().equals(Constants.GET_QR_CODE_BY_LSH)){
                LoadingDialog.getInstance().dismissLoadDialog();
                val entity = GsonUtils.gson(commonResponse.getResponseString(), DataStringMessageEntity::class.java)
                mDwWcFragment.startActivity(entity.data+"")

            }
        }catch (e:Exception){
            showToast(e.message.toString())

        }

    }

    override fun netWorkTaskfailed(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()

        if (uploadPhotoFileadNumber==0){
            showErrorDialog(commonResponse.getResponseString())
        }

        if (commonResponse.getUrl().equals(Constants.SAVE_PHOTO)){
            uploadPhotoFileadNumber++
        }

    }

     fun updateCarMessage(map: HashMap<String,String>,taskType: String){
         map.put("lsh", mCarMessageEntity!!.data.lsh)
         map.put("xh",mCarMessageEntity!!.data.xh)
         map.put("xss",UserInfo.SFZMHM)
        this.taskType=taskType
        LoadingDialog.getInstance().showLoadDialog(this)
        mCollectBicyclePersenter!!.doNetworkTask(map,Constants.UPDATE_CAR_MSG)
    }
     fun commitPhoto(photoMap: HashMap<String, String>,taskType: String){

        this.taskType=taskType;
            uploadPhotoSuccessNumber=0;
            uploadPhotoFileadNumber=0;
            uploadPhotoNumber=photoMap.size
            val lsh=mCarMessageEntity!!.data.lsh
            val xh=mCarMessageEntity!!.data.xh
            for (entity in photoMap){
                val map= HashMap<String, String>()
                map.put("zplx",entity.key)
                map.put("lsh",lsh)
                map.put("xh",xh)
                mCollectBicyclePersenter!!.uploadFile(File(entity.value),map,Constants.SAVE_PHOTO)

            }

    }

    /**
     * 获取 保险信息
     */
    fun getInsuranceMsg(gsmc: String){
        val lsh=mCarMessageEntity!!.data.lsh
        val xh=mCarMessageEntity!!.data.xh

        val map=HashMap<String,String>()
        map.put("xh ",xh)
        map.put("lsh",lsh)
        map.put("companyName",gsmc)
        map.put("xsdmc",UserInfo.XSDMC+"")
        map.put("xsddm",UserInfo.XSDDM+"")
        map.put("yhdh ",UserInfo.YHDH+"")
        map.put("xm","1")
        map.put("sfzmhm","1")
        LoadingDialog.getInstance().showLoadDialog(this)
        mCollectBicyclePersenter!!.doNetworkTask(map,Constants.GET_INSURANCEP_MES)
    }


    /**
     * 保存 保险信息
     */
    fun saveInsuranceMsg(map: HashMap<String,String>){
        val lsh=mCarMessageEntity!!.data.lsh
        val xh=mCarMessageEntity!!.data.xh
        map.put("xh ",xh)
        map.put("lsh",lsh)
        map.put("sfzmhm",UserInfo.SFZMHM)
        LoadingDialog.getInstance().showLoadDialog(this)
        mCollectBicyclePersenter!!.doNetworkTask(map,Constants.SAVE_INSURANCEP_MES)
    }

    /**
     * 获取保险公司名称
     */
    fun getCompanyName(){
        mCollectBicyclePersenter!!.doNetworkTask(HashMap<String,String>(),Constants.GET_COMPANY_NAME)
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

    /**
     * 保险数据详情
     */
    fun getInsuranceDetails(){
        val lsh=mCarMessageEntity!!.data.lsh
        val xh=mCarMessageEntity!!.data.xh
        val map= HashMap<String, String>()
        map.put("xh ",xh)
        map.put("lsh",lsh)
        mCollectBicyclePersenter!!.doNetworkTask(map,Constants.GET_INSURANCE_DETAILS)
    }

    /**
     * 是否有资格购车
     */
    fun verfity(sfzhm: String,xm: String){
        val map= HashMap<String, String>()
        map.put("sfzmhm",sfzhm)
        map.put("xm",xm)
        mCollectBicyclePersenter!!.doNetworkTask(map,Constants.VERIFY)
    }
    /**
     * 获取电子凭证
     */
    fun getQrCodeByLsh(){
        val lsh=mCarMessageEntity!!.data.lsh
        val xh=mCarMessageEntity!!.data.xh
        val map= HashMap<String, String>()
        map.put("xh",xh)
        map.put("lsh",lsh)
        LoadingDialog.getInstance().showLoadDialog(this)
        mCollectBicyclePersenter!!.doNetworkTask(map,Constants.GET_QR_CODE_BY_LSH)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (mDwJcsjFragment.isAdded){
            mDwJcsjFragment.onActivityResult(requestCode,resultCode,data)
        }
        if (mDwXxsjFragment.isAdded){
            mDwXxsjFragment.onActivityResult(requestCode,resultCode,data)
        }
        if (mDwSyrxxFragment.isAdded){
            mDwSyrxxFragment.onActivityResult(requestCode,resultCode,data)
        }
        if (mDwDlrxxFragment.isAdded){
            mDwDlrxxFragment.onActivityResult(requestCode,resultCode,data)
        }
        if (mDwBdxxFragment.isAdded){
            mDwBdxxFragment.onActivityResult(requestCode,resultCode,data)
        }
        if (mDwWcFragment.isAdded){
            mDwWcFragment.onActivityResult(requestCode,resultCode,data)
        }
        if (mGrSyrxxFragment.isAdded){
            mGrSyrxxFragment.onActivityResult(requestCode,resultCode,data)
        }
    }

    private fun showTipsDialog() {
        try {
            val mDialog = Dialog(this)
            mDialog.setContentView(R.layout.dialog_tips)
            mDialog.setCanceledOnTouchOutside(true)
            val tvMsg = mDialog.findViewById<TextView>(R.id.tv_tips_msg)
            val btnOk = mDialog.findViewById<Button>(R.id.btn_ok)
            val btnCancel = mDialog.findViewById<Button>(R.id.btn_cancel)
            tvMsg.setText(getString(R.string.exit_collect_data_tips))
            tvMsg.setLineSpacing(6f, 1f)
            btnOk.setOnClickListener(View.OnClickListener { finish() })
            btnCancel.setOnClickListener(View.OnClickListener { mDialog.dismiss() })
            mDialog.show()
        } catch (e: Exception) {
            showToast("showErrorDialog has Exception")

        }

    }


    fun rbJcsjPerformOnClick(){
        rb_jcsj.performClick()
    }
    fun rbXxsjPerformOnClick(){
        rb_xxsj.performClick()
    }
    fun rbSyrPerformOnClick(){
        rb_syrxx.performClick()
    }
    fun rbDlrPerformOnClick(){
        rb_dlrxx.performClick()
    }

    // 保单信息上一步
    fun BdxxLastPerformOnClick(){
        // 存在 个人 和 公司两种情况

        // 0 是公司   1是个人
        if ("0".equals(type)){
            rbDlrPerformOnClick()
        }else{
            rbSyrPerformOnClick()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            showTipsDialog()
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            OtherUtils.deleteFileChild(Constants.IMAGE_PATH)
        }catch (e:Exception){

        }
    }
}
