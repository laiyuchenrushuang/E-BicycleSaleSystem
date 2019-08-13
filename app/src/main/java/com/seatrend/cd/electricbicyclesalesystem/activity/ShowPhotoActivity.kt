package com.seatrend.cd.electricbicyclesalesystem.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Window
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import kotlinx.android.synthetic.main.activity_show_photo.*
import uk.co.senab.photoview.PhotoView

class ShowPhotoActivity : BaseActivity() {


    override fun initView() {
        val mPhotoView=findViewById<PhotoView>(R.id.iv_photo)
        Glide.with(this).load(intent.getStringExtra(Constants.PATH)).placeholder(R.drawable.image_loading)
                .error(R.drawable.error_image).into(mPhotoView)

    }

    override fun getLayout(): Int {

        return R.layout.activity_show_photo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
         getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            finish()
            exitRotateAlphaAcaleAnimation()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


}
