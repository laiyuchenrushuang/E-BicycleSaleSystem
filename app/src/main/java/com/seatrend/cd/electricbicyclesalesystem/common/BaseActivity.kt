package com.seatrend.cd.electricbicyclesalesystem.common

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ComponentName
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.widget.*

import com.seatrend.cd.electricbicyclesalesystem.activity.MainActivity
import com.seatrend.cd.electricbicyclesalesystem.R

import butterknife.ButterKnife
import com.seatrend.cd.electricbicyclesalesystem.entity.MessageEntity
import com.seatrend.cd.electricbicyclesalesystem.util.GsonUtils

/**
 * Created by seatrend on 2018/8/20.
 */

abstract class BaseActivity : AppCompatActivity(), BaseView {

    var ivBack: ImageView? = null
    var ivRight: ImageView?=null;
    var tvPageTitle: TextView? = null
    var tvRight: TextView? = null
    var rlParent: RelativeLayout?=null;
    private var noDataView: View? = null
    val ID_CARD_READ_CODE=10;





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN); //全屏
        setContentView(getLayout())
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// 横屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT//竖屏
        ActivityCollector.addActivity(this)
        //ButterKnife.bind(this)
        initCommonTitle()
        initView()


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            setStatusBarColor(resources.getColor(R.color.theme_color))
        }

    }


    @SuppressLint("WrongViewCast")
    fun initCommonTitle() {
        ivBack = findViewById(R.id.iv_back)
        ivRight = findViewById(R.id.iv_right)
        tvPageTitle = findViewById(R.id.tv_titile)
        tvRight = findViewById(R.id.tv_right)
        rlParent = findViewById(R.id.rl_parent)

        if (ivBack != null) {
            ivBack!!.setOnClickListener { finish() }
        }

    }

    fun hideBackIcon() {
        if (ivBack != null) {
            ivBack!!.visibility = View.GONE
        }
    }

    fun goMainActivity() {
        /*Intent intent = new Intent(BaseActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
        ActivityCollector.finishToOne(MainActivity::class.java)
    }

    fun setPageTitle(pageTitle: String) {
        if (tvPageTitle != null) {
            tvPageTitle!!.text = pageTitle
        }
    }

    fun setRightTitle(rightTitle: String) {
        //tvRight
        if (tvRight != null) {
            tvRight!!.text = rightTitle
        }
    }

    /*private void showNetWorkError(){
        if(rlParent==null){
            return;
        }
        View view = LayoutInflater.from(this).inflate(R.layout.popupwindow_network_erroe, null);
        final PopupWindow popupWindow=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.popmenu_animation);
        try {
            popupWindow.showAsDropDown(rlParent, 0, 0);
        }catch (Exception e){
           e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        popupWindow.dismiss();
                    }
                });
            }
        }).start();
    }*/


    protected fun hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            val v = this.window.decorView
            v.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            val decorView = window.decorView
            val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN)
            decorView.systemUiVisibility = uiOptions
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showToast(resId: Int) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show()
    }

    public override fun showErrorDialog(msg: String) {

        var tipsMsg=""
        try {
            tipsMsg= GsonUtils.gson(msg,MessageEntity::class.java).message
        }catch (e:java.lang.Exception){
            tipsMsg=msg
        }

        try {
            val mDialog = Dialog(this)
            mDialog.setContentView(R.layout.dialog_error)
            mDialog.setCanceledOnTouchOutside(true)
            val tvMsg = mDialog.findViewById<TextView>(R.id.tv_msg)
            val btnOk = mDialog.findViewById<Button>(R.id.btn_ok)
            tvMsg.text = tipsMsg
            btnOk.setOnClickListener { mDialog.dismiss() }
            mDialog.show()
        } catch (e: Exception) {
            showToast("showErrorDialog has Exception")

        }

    }

    fun showWarningMsg(msg: String, title: String) {
        /*final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_warning);
        TextView tvTitle=dialog.findViewById(R.id.tv_title);
        TextView tvMsg=dialog.findViewById(R.id.tv_msg);
        Button tvOk=dialog.findViewById(R.id.tv_ok);
        tvTitle.setText(StringUtils.isNull(title));
        tvMsg.setText(StringUtils.isNull(msg));
        dialog.show();
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });*/

    }

    abstract fun initView()
    abstract fun getLayout():Int

    override fun onDestroy() {
        super.onDestroy()
        // backHomeThread.interrupt();
        ActivityCollector.removeActivity(this)

    }

    override fun onResume() {
        super.onResume()

        /*if(!NetUtils.isNetworkAvailable(this)){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    networkStatus(0);
                }
            }, 1000);
        }*/

    }

    override fun onPause() {
        super.onPause()


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun setStatusBarColor(statusColor: Int) {
        val window = window
        //取消状态栏透明
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //设置状态栏颜色
        window.statusBarColor = statusColor
        //设置系统状态栏处于可见状态
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        //让view不根据系统窗口来调整自己的布局
        val mContentView = window.findViewById<View>(Window.ID_ANDROID_CONTENT) as ViewGroup
        val mChildView = mContentView.getChildAt(0)
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false)
            ViewCompat.requestApplyInsets(mChildView)
        }
    }

    fun showNoDataView(msg: String) {

        noDataView = LayoutInflater.from(this).inflate(R.layout.common_no_data, null)
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        val tvMsg = noDataView!!.findViewById<TextView>(R.id.tv_msg)
        tvMsg.setText(msg)
        addContentView(noDataView, params)


    }

    fun hideNoDataView() {
        if (noDataView != null) {
            (noDataView!!.getParent() as ViewGroup).removeView(noDataView)
            noDataView = null
        }

    }

    fun startRotateAlphaAcaleAnimation() {
        overridePendingTransition(R.anim.publish_life_in, R.anim.out_to_left)
    }

    fun exitRotateAlphaAcaleAnimation() {
        overridePendingTransition(R.anim.finish_in, R.anim.publish_life_out)
    }

    fun goNfcReadPlugin() {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.component = ComponentName("com.seatrend.cd.nfcread", "com.seatrend.cd.nfcread.IdCardReadActivity")
            startActivityForResult(intent, ID_CARD_READ_CODE)
        } catch (e: java.lang.Exception) {
            showToast("未找到NFC身份证读取插件，请先安装插件")

        }

    }
    protected fun showLog(s: String) {
        Log.d("lylog", s)
    }

}
