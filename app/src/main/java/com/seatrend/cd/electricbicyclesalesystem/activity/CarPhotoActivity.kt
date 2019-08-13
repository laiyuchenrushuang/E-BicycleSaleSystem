package com.seatrend.cd.electricbicyclesalesystem.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.ContactsContract
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.widget.StaggeredGridLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.adpater.CheckDataPhotoAdapter
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.CarBean
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.entity.PhotoTypeEntity
import com.seatrend.cd.electricbicyclesalesystem.persenter.CarPhotoPersenter
import com.seatrend.cd.electricbicyclesalesystem.util.*
import com.seatrend.cd.electricbicyclesalesystem.view.CarPhotoView
import kotlinx.android.synthetic.main.activity_car_photo.*
import kotlinx.android.synthetic.main.recyclerview.*
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.lang.Exception
import java.util.*
import kotlin.collections.HashMap

class CarPhotoActivity : BaseActivity(), CarPhotoView, CheckDataPhotoAdapter.itemOnClickListener, CheckDataPhotoAdapter.itemDeleteClickListener {
    companion object {
        var mClcyCommitData = CarBean()
        private var enterFlag: String? = null//由哪个入口进来的判断获取不同类型的图片
    }

    private var deletePosition = 0
    private var imgFile: File? = null
    private val CAMERA_REQUEST_CODE = 20

    private var photoPosition = 0

    private var mCarPhotoPersenter: CarPhotoPersenter? = null

    private var uploadPhotoFileadNumber = 0;//失败总数
    private var uploadPhotoSuccessNumber = 0;//成功总数
    private val VIN_CODE = 30
    private var mCheckDataPhotoAdapter: CheckDataPhotoAdapter? = null
    var allPhoto = ArrayList<PhotoTypeEntity.DataBean.ConfigBean>() //所有照片类型信息（服务器）
    var zpList = ArrayList<CarBean.Zllist>() //方便传post的数据集合 查验使用  (界面)

    override fun initView() {
        setPageTitle(getString(R.string.carcy_next))
        mCarPhotoPersenter = CarPhotoPersenter(this)
        enterFlag = intent.getStringExtra(Constants.TASK)
        m_recycler_view.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        mCheckDataPhotoAdapter = CheckDataPhotoAdapter(this)
        mCheckDataPhotoAdapter!!.setItemOnClick(this)
        mCheckDataPhotoAdapter!!.setItemdeleteClick(this)
        m_recycler_view.adapter = mCheckDataPhotoAdapter
        getPhotoType()
        bindEvent()
    }


    fun bindEvent() {
        btn_commit.setOnClickListener {
            commitPhoto()
        }
    }

    private fun getPhotoType() {
        LoadingDialog.getInstance().showLoadDialog(this)
        zpList.clear()//清除缓存
        mCarPhotoPersenter!!.doNetworkTask(HashMap<String, String>(), Constants.GET_PHOTO_TYPE)
    }

    override fun getLayout(): Int {

        return R.layout.activity_car_photo
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
            imageUri = FileProvider.getUriForFile(this, getString(R.string.authority), imgFile)
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

            mCheckDataPhotoAdapter!!.setPhoto(photoPosition, imgFile!!.path)
            var zplx = allPhoto.get(photoPosition).zplx
            var zpStr = BitmapUtils.imageToBase64(imgFile!!.path)
            var carPhotoBean = CarBean.Zllist()
            carPhotoBean.zplx = zplx
            carPhotoBean.zpstr = zpStr
            //查验是一次全部上传，其他可以一张一张上传
            when (enterFlag) {
                Constants.CAR_CY -> {
                    //类型去重
                    if (zpList.size > 0) {
                        for (index in 0..zpList.size - 1) {
                            if (zplx.equals(zpList[index].zplx)) {
                                zpList.removeAt(index)
                                break
                            }
                        }
                    }
                    zpList.add(carPhotoBean)
                }
                Constants.CAR_ZC -> {
                    carPhotoBean.lsh = CollectDataActivity.mCarZcMsgData.lsh
                    carPhotoBean.xh = CollectDataActivity.mCarZcMsgData.xh
                    var result = GsonUtils.toJson(carPhotoBean)
                    mCarPhotoPersenter!!.uploadFileAndData(result, Constants.PSOT_CAR_YWXX)
                }

                Constants.CAR_BG -> {
                    carPhotoBean.lsh = CarChangeRegisterActivity.mCarZcMsgData.lsh
                    carPhotoBean.xh = CarChangeRegisterActivity.mCarZcMsgData.xh
                    var result = GsonUtils.toJson(carPhotoBean)
                    mCarPhotoPersenter!!.uploadFileAndData(result, Constants.PSOT_CAR_YWXX)
                }
            }
        } else if (requestCode == VIN_CODE && resultCode == Activity.RESULT_OK) {
        }
    }

    private fun commitPhoto() {
        val dataList = mCheckDataPhotoAdapter!!.getDataList()
        if (checkPhoto(dataList)) {
            when (enterFlag) {
                //查验
                Constants.CAR_CY -> {
                    mClcyCommitData.setZllist(zpList)
                    //查验流程 只有销售 需要传号牌编发
                    if (mClcyCommitData.vehFlowMain.ywlx.equals(CarUtils.ywlxMap[getString(R.string.xsdj)])) {
                        intent.setClass(this, HpbfActivity::class.java)
                        startActivity(intent)
                    } else {
                        var result = GsonUtils.toJson(mClcyCommitData)
                        LoadingDialog.getInstance().showLoadDialog(this)
                        mCarPhotoPersenter!!.uploadFileAndData(result, Constants.SAVE_CAR_CY_URL)
                    }
                }
                //注册
                Constants.CAR_ZC -> {
                    sendActivityEvent(InsuranceActivity::class.java)
                }
                //变更
                Constants.CAR_BG -> {
                    sendActivityEvent(RemindHPBFactivity::class.java)
                }
                //转移
                Constants.CAR_ZY -> {
                    sendActivityEvent(RemindHPBFactivity::class.java)
                }
                //注销
                Constants.CAR_ZX -> {
                    sendActivityEvent(RemindHPBFactivity::class.java)
                }
            }
        }
    }

    private fun sendActivityEvent(activity: Class<*>) {
        if ((uploadPhotoFileadNumber + uploadPhotoSuccessNumber) == allPhoto.size) {
            showToast("成功上传"+uploadPhotoSuccessNumber+"张，失败"+uploadPhotoFileadNumber+"张")
            intent.setClass(this,activity)
            startActivity(intent)
        }else{
            showToast("成功上传"+uploadPhotoSuccessNumber+"张,"+"请稍等")
        }
    }

    private fun checkPhoto(data: ArrayList<PhotoTypeEntity.DataBean.ConfigBean>): Boolean {
        for (cb in data) {
            if (cb.zplj == null || cb.zplj.isEmpty()) {
                showToast("请拍摄【" + cb.zmmc + "】照片")
                return false
            }
        }
        return true
    }

    override fun netWorkTaskSuccess(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()
        if (commonResponse.getUrl() == Constants.GET_PHOTO_TYPE) {
            LoadingDialog.getInstance().dismissLoadDialog()
            val photoTypeEntity = GsonUtils.gson(commonResponse.getResponseString(), PhotoTypeEntity::class.java)
            for (value in (photoTypeEntity.data.config)) {
                when (enterFlag) {
                    Constants.CAR_CY -> {
                        if (value.ywxh != null && value.ywxh.equals("1")) {       //1 代表查验照片
                            allPhoto.add(value)
                        }
                    }
                    Constants.CAR_ZC -> {
                        if (value.ywxh != null && value.ywxh.equals("2")) {       //2 代表注册照片
                            allPhoto.add(value)
                        }
                    }
                    Constants.CAR_BG -> {
                        if (value.ywxh != null && value.ywxh.equals("3")) {       //3 代表变更照片
                            allPhoto.add(value)
                        }
                    }
                    Constants.CAR_ZY -> {
                        if (value.ywxh != null && value.ywxh.equals("4")) {       //4 代表转移照片
                            allPhoto.add(value)
                        }
                    }
                    Constants.CAR_ZX -> {
                        if (value.ywxh != null && value.ywxh.equals("5")) {       //5 代表注销照片
                            allPhoto.add(value)
                        }
                    }
                }
            }
            mCheckDataPhotoAdapter!!.setPhotoType(allPhoto)
            Log.i("lylog", " [CY] photo type size = " + allPhoto.size)

        } else if (commonResponse.getUrl() == Constants.SAVE_CAR_CY_URL) {
            intent = Intent(this, RemindHPBFactivity::class.java)
            startActivity(intent)
        } else if (commonResponse.getUrl() == Constants.PSOT_CAR_YWXX) {
            uploadPhotoSuccessNumber++
            showLog("" + uploadPhotoSuccessNumber)
        }
    }

    override fun netWorkTaskfailed(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()
        if (commonResponse.getUrl() == Constants.SAVE_CAR_CY_URL || commonResponse.getUrl() == Constants.GET_PHOTO_TYPE) {
            showErrorDialog(commonResponse.getResponseString())
        } else if (commonResponse.getUrl() == Constants.PSOT_CAR_YWXX) {
            showErrorDialog(commonResponse.getResponseString())
            uploadPhotoFileadNumber++
            showLog("" + uploadPhotoFileadNumber)
        }
    }

    override fun itemOnClick(position: Int) {
        photoPosition = position
        getPicFromCamera()
    }

    override fun itemdelete(position: Int) {
        deletePosition = position
        if (enterFlag == Constants.CAR_CY) {
            zpList.removeAt(deletePosition)
        }
        uploadPhotoSuccessNumber--
    }

    override fun onDestroy() {
        super.onDestroy()
        if (enterFlag == Constants.CAR_CY) {
            zpList.clear() //清除
        }
    }
}
