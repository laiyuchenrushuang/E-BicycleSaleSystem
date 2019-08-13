package com.seatrend.cd.electricbicyclesalesystem.activity

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.NinePatchDrawable
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.adpater.CarPhotoAdapter
import com.seatrend.cd.electricbicyclesalesystem.adpater.CollectRegisterPhotoAdapter
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import kotlinx.android.synthetic.main.activity_collect_register_photo.*
import kotlinx.android.synthetic.main.activity_collect_register_photo.m_recycler_view
import kotlinx.android.synthetic.main.recyclerview.*
import java.io.File

class CollectRegisterPhotoActivity : BaseActivity(), CarPhotoAdapter.ItemOnclickListener {


    private var imgFile: File? = null
    private val CAMERA_REQUEST_CODE = 20

    private var photoPosition = 0

    var list = ArrayList<HashMap<String, String>>()
    private var mCarPhotoAdapter: CarPhotoAdapter? = null
    private val VIN_CODE = 30

    override fun initView() {
        setPageTitle(getString(R.string.qscdjzlzp))

        m_recycler_view.layoutManager= StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        mCarPhotoAdapter = CarPhotoAdapter(this, list)
        mCarPhotoAdapter!!.setItemClickListener(this)
        m_recycler_view.adapter = mCarPhotoAdapter
        initData()
        bindEvent()
    }

    private fun bindEvent(){
        btn_ok.setOnClickListener {
            intent.setClass(this,InsuranceActivity::class.java)
            startActivity(intent)
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_collect_register_photo
    }

    private fun initData() {
        list.clear()
        var map1 = HashMap<String, String>()
        var map2 = HashMap<String, String>()
        var map3 = HashMap<String, String>()
        var map4 = HashMap<String, String>()
        map1["整车编码"] = ""
        map2["合格证"] = ""
        map3["整车后30°照(带牌照)"] = ""
        map4["前后轮中心距"] = ""
        list.add(map1)
        list.add(map2)
        list.add(map3)
        list.add(map4)
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

    override fun itemOnClick(position: Int) {
        photoPosition = position
        getPicFromCamera()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("lylog", "path = " + imgFile!!.path)
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            list[photoPosition][getKey(list[photoPosition])] = imgFile!!.path
            mCarPhotoAdapter!!.notifyDataSetChanged()
        } else if (requestCode == VIN_CODE && resultCode == Activity.RESULT_OK) {
        }
    }

    private fun getKey(map: HashMap<String, String>): String {
        var type = map.keys
        var key = ""
        for (s: String in type) {
            key = s
        }
        return key
    }
}
