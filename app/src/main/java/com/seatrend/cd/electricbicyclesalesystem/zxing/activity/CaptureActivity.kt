package com.seatrend.cd.electricbicyclesalesystem.zxing.activity

import android.Manifest
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.AssetFileDescriptor
import android.graphics.Bitmap
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.Vibrator
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.SurfaceHolder
import android.view.SurfaceHolder.Callback
import android.view.SurfaceView
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast

import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.activity.CarAllMessageActivity
import com.seatrend.cd.electricbicyclesalesystem.activity.CarCodeCYActivity
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.CarMessageEntity
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.persenter.CarLoginPersenter
import com.seatrend.cd.electricbicyclesalesystem.util.GsonUtils
import com.seatrend.cd.electricbicyclesalesystem.util.LoadingDialog
import com.seatrend.cd.electricbicyclesalesystem.view.CarLoginView
import com.seatrend.cd.electricbicyclesalesystem.zxing.camera.CameraManager
import com.seatrend.cd.electricbicyclesalesystem.zxing.decoding.CaptureActivityHandler
import com.seatrend.cd.electricbicyclesalesystem.zxing.decoding.InactivityTimer
import com.seatrend.cd.electricbicyclesalesystem.zxing.view.ViewfinderView

import java.io.IOException
import java.util.HashMap
import java.util.Vector

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.util.regex.Pattern


/**
 * Initial the camera
 *
 * @author Ryan.Tang
 */
class CaptureActivity : BaseActivity(), Callback, CarLoginView {

    private var handler: CaptureActivityHandler? = null
    var viewfinderView: ViewfinderView? = null
        private set
    private var back: ImageView? = null
    private var hasSurface: Boolean = false
    private var decodeFormats: Vector<BarcodeFormat>? = null
    private var characterSet: String? = null
    private var inactivityTimer: InactivityTimer? = null
    private var mediaPlayer: MediaPlayer? = null
    private var playBeep: Boolean = false
    private var vibrate: Boolean = false
    private var rl_hgzcy_next: RelativeLayout? = null//合格证查验下一步
    private var mCarLoginPersenter: CarLoginPersenter? = null

    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                View.VISIBLE -> {
                }
                View.INVISIBLE -> {
                }
            }
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private val beepListener = OnCompletionListener { mediaPlayer -> mediaPlayer.seekTo(0) }
    var surfaceHolder :SurfaceHolder?=null
    override fun onResume() {
        super.onResume()
        val surfaceView = findViewById<View>(R.id.scanner_view) as SurfaceView
        surfaceHolder = surfaceView.holder
        if (hasSurface) {
            initCamera(surfaceHolder)
        } else {
            surfaceHolder!!.addCallback(this)
            surfaceHolder!!.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
        }
        decodeFormats = null
        characterSet = null

        playBeep = true
        val audioService = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        if (audioService.ringerMode != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false
        }
        initBeepSound()
        vibrate = true
    }

    override fun onPause() {
        super.onPause()
        if (handler != null) {
            handler!!.quitSynchronously()
            handler = null
        }
        CameraManager.get().closeDriver()
    }

    override fun onDestroy() {
        inactivityTimer!!.shutdown()
        super.onDestroy()
    }

    /**
     * Handler scan result
     *
     * @param result
     * @param barcode
     */
    fun handleDecode(result: Result, barcode: Bitmap) {
        inactivityTimer!!.onActivity()
        playBeepSoundAndVibrate()
        val resultString = result.text
        val msg = Message()
        msg.what = View.VISIBLE
        mHandler.sendMessage(msg)

        //FIXME
        if (TextUtils.isEmpty(resultString)) {
            initCamera(surfaceHolder)
            return
        } else {
            getDataSync(resultString)
        }
    }

    var resultStringZcbm = ""
    private fun getDataSync(result: String) {
        var regEx = "[0-9]{15}";
		var p = Pattern.compile(regEx);
		var m = p.matcher(result);
		if( m.find()){
            resultStringZcbm = m.group(0)
            val map = HashMap<String, String>()
            map["zcbm"] = resultStringZcbm
            LoadingDialog.getInstance().showLoadDialog(this)
            mCarLoginPersenter!!.doNetworkTask(map, Constants.GET_CAR_MSG)
        }else{
            initCamera(surfaceHolder)
            return
        }

    }

    private fun initCamera(surfaceHolder: SurfaceHolder?) {
        try {
            CameraManager.get().openDriver(surfaceHolder)
        } catch (ioe: IOException) {
            return
        } catch (e: RuntimeException) {
            return
        }

//        if (handler == null) {
            handler = CaptureActivityHandler(this, decodeFormats,
                    characterSet)
//        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int,
                                height: Int) {

    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        if (!hasSurface) {
            hasSurface = true
            initCamera(holder)
        }

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        hasSurface = false

    }

    fun getHandler(): Handler? {
        return handler
    }

    fun drawViewfinder() {
        viewfinderView!!.drawViewfinder()

    }

    private fun initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            volumeControlStream = AudioManager.STREAM_MUSIC
            mediaPlayer = MediaPlayer()
            mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
            mediaPlayer!!.setOnCompletionListener(beepListener)

            val file = resources.openRawResourceFd(
                    R.raw.beep)
            try {
                mediaPlayer!!.setDataSource(file.fileDescriptor,
                        file.startOffset, file.length)
                file.close()
                mediaPlayer!!.setVolume(BEEP_VOLUME, BEEP_VOLUME)
                mediaPlayer!!.prepare()
            } catch (e: IOException) {
                mediaPlayer = null
            }

        }
    }

    private fun playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer!!.start()
        }
        if (vibrate) {
            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(VIBRATE_DURATION)
        }
    }

    override fun initView() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //申请权限，CEMERA_OK是自定义的常量
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA),
                    1)
        }
        setPageTitle(getString(R.string.hgzjc))
        mCarLoginPersenter = CarLoginPersenter(this)
        CameraManager.init(application)
        viewfinderView = findViewById<View>(R.id.viewfinder_content) as ViewfinderView
        rl_hgzcy_next = findViewById(R.id.rl_hgzcy_next)
        rl_hgzcy_next!!.setOnClickListener {
            val intent = Intent(this@CaptureActivity, CarCodeCYActivity::class.java)
            intent.putExtra("task", "1")
            startActivity(intent)
            finish()
        }
        back = findViewById<View>(R.id.iv_back) as ImageView
        back!!.setOnClickListener {
            finish()
        }
        hasSurface = false
        inactivityTimer = InactivityTimer(this)

    }

    override fun getLayout(): Int {
        return R.layout.activity_scanner
    }

    override fun netWorkTaskSuccess(commonResponse: CommonResponse) {
        CarAllMessageActivity.mCarMessageEntity = GsonUtils.gson(commonResponse.getResponseString(), CarMessageEntity::class.java)
        intent.setClass(this, CarAllMessageActivity::class.java)
        intent.putExtra("zcbm", resultStringZcbm)
        intent.putExtra("task", "1")   //业务类型
        intent.putExtra("JKLX", "1")  //查询接口入口
        startActivity(intent)
        LoadingDialog.getInstance().dismissLoadDialog()
    }

    override fun netWorkTaskfailed(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()
        showErrorDialog(commonResponse.getResponseString())
        resultStringZcbm = ""
    }

    companion object {

        private val REQUEST_CODE_SCAN_GALLERY = 100
        private val BEEP_VOLUME = 0.10f

        private val VIBRATE_DURATION = 200L
    }
}