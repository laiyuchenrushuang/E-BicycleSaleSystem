package com.seatrend.cd.electricbicyclesalesystem.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.adpater.CarPhotoAdapter
import com.seatrend.cd.electricbicyclesalesystem.common.BaseFragment
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import kotlinx.android.synthetic.main.recyclerview.*
import java.io.File

class CarPhotoFragment : BaseFragment(), CarPhotoAdapter.ItemOnclickListener {

    private var imgFile: File? = null
    private val CAMERA_REQUEST_CODE = 20

    private var photoPosition = 0

    var list = ArrayList<HashMap<String, String>>()
    private var mCarPhotoAdapter: CarPhotoAdapter? = null
    private val VIN_CODE = 30


    override fun getLayoutView(inflater: LayoutInflater?, container: ViewGroup?): View {
        initData()
        return inflater!!.inflate(R.layout.fragment_car_photo, container, false)
    }

    override fun initView() {
        m_recycler_view.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        mCarPhotoAdapter = CarPhotoAdapter(context, list)
        mCarPhotoAdapter!!.setItemClickListener(this)
        m_recycler_view.adapter = mCarPhotoAdapter
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
            imageUri = FileProvider.getUriForFile(context, getString(R.string.authority), imgFile)
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
