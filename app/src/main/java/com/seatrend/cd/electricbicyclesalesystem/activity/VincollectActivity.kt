package com.seatrend.cd.electricbicyclesalesystem.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.BaseModule
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.BaseEntity
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.http.HttpService
import com.seatrend.cd.electricbicyclesalesystem.util.GsonUtils
import com.seatrend.cd.electricbicyclesalesystem.util.OtherUtils
import kotlinx.android.synthetic.main.activity_vincollect.*
import java.io.File
import java.util.HashMap

class VincollectActivity : BaseActivity() {

    private val GO_VIN_CODE = 1000

    private var vinPhotoPath: String? = null

    override fun initView() {
        setPageTitle(getString(R.string.vin_collect))
        btn_collect_vin.setOnClickListener {
            OtherUtils.toVin(this,GO_VIN_CODE,intent.getStringExtra(Constants.LSH))

        }
        btn_save.setOnClickListener {
            uploadVin()

        }
        iv_vin.setOnClickListener {

        }

    }

    override fun getLayout(): Int {
        return R.layout.activity_vincollect;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun uploadVin() {
        val lsh = intent.getStringExtra(Constants.LSH)
        val xh = intent.getStringExtra(Constants.XH)
        if (!TextUtils.isEmpty(lsh) && !TextUtils.isEmpty(xh)) {
            val map = HashMap<String, String>()
            map[Constants.LSH] = lsh
            map[Constants.XH] = lsh
            map[Constants.ZPLX] = "19"
            HttpService.getInstance().uploadFileToServer(Constants.SAVE_PHOTO, File(vinPhotoPath), map, mBaseModule)
        } else {
            showToast("抱歉，未获取到流水号或序号，无法提交，请联系相关人员")
        }

    }

    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GO_VIN_CODE && resultCode == RESULT_OK && data != null) {
            vinPhotoPath = data.getStringExtra("zp")
            Glide.with(this).load(vinPhotoPath).listener(object : RequestListener<String, GlideDrawable> {
                override fun onException(e:Exception?, model: String, target: Target<GlideDrawable>, isFirstResource: Boolean): Boolean {
                    return false
                }

                override fun onResourceReady(resource: GlideDrawable, model: String, target: Target<GlideDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                    btn_save.isEnabled=true;
                    return false
                }
            }).into(iv_vin)
        }
    }

    private val mBaseModule = object : BaseModule() {
        override  fun doWork(map: Map<String, String>, url: String) {

        }

      override  fun doWorkResults(commonResponse: CommonResponse, isSuccess: Boolean) {
            if (isSuccess) {
                try {
                    val baseEntity = GsonUtils.gson(commonResponse.getResponseString(), BaseEntity::class.java)
                    if (baseEntity.getCode() === 0 && baseEntity.isStatus()) {
                        showToast(baseEntity.getMessage())
                        finish()
                    } else {
                        showToast(baseEntity.getMessage())
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    showToast(e.message+"")
                }

            }

        }
    }
}
