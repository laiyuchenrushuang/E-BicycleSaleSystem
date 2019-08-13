package com.seatrend.cd.electricbicyclesalesystem.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner

import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.activity.CarPhotoActivity
import com.seatrend.cd.electricbicyclesalesystem.activity.CollectDataActivity
import com.seatrend.cd.electricbicyclesalesystem.activity.CollectDataActivity.Companion.mCarZcMsgData
import com.seatrend.cd.electricbicyclesalesystem.common.BaseFragment
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.database.CodeTableSQLiteUtils
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.persenter.CarPhotoPersenter
import com.seatrend.cd.electricbicyclesalesystem.util.CarUtils
import com.seatrend.cd.electricbicyclesalesystem.util.GsonUtils
import com.seatrend.cd.electricbicyclesalesystem.util.LoadingDialog
import com.seatrend.cd.electricbicyclesalesystem.view.CarPhotoView
import kotlinx.android.synthetic.main.fragment_change_syrxx.*
import kotlinx.android.synthetic.main.fragment_dlrxx.*
import kotlinx.android.synthetic.main.fragment_dlrxx.btn_last
import kotlinx.android.synthetic.main.fragment_dlrxx.btn_next
import kotlinx.android.synthetic.main.fragment_dlrxx. iv_sfzh_scan
import kotlinx.android.synthetic.main.fragment_dlrxx.et_lxdh
import kotlinx.android.synthetic.main.fragment_dlrxx.et_sfzmhm
import kotlinx.android.synthetic.main.fragment_dlrxx.et_xm
import kotlinx.android.synthetic.main.fragment_dlrxx.et_xxdz
import kotlinx.android.synthetic.main.fragment_dlrxx.sp_sfzmmc
import kotlinx.android.synthetic.main.fragment_dlrxx.sp_szdq
import kotlinx.android.synthetic.main.fragment_syrxx.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DlrxxFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DlrxxFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DlrxxFragment : BaseFragment(), CarPhotoView {
    override fun netWorkTaskSuccess(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()
        mActivity!!.intent.setClass(context, CarPhotoActivity::class.java)
        startActivity(mActivity!!.intent)
    }

    override fun netWorkTaskfailed(commonResponse: CommonResponse) {
        LoadingDialog.getInstance().dismissLoadDialog()
        showErrorDialog(commonResponse.getResponseString())
    }

    var mCarPhotoPersenter: CarPhotoPersenter? = null
    var mActivity: CollectDataActivity? = null

    override fun getLayoutView(inflater: LayoutInflater?, container: ViewGroup?): View {
        return inflater!!.inflate(R.layout.fragment_dlrxx, container, false)
    }

    override fun initView() {
        mCarPhotoPersenter = CarPhotoPersenter(this)
        setAdapterData(sp_sfzmmc)
        setAdapterData(sp_szdq)
        bindEvent()

    }

    private fun bindEvent() {
        btn_next.setOnClickListener {
            //判断所有人信息的完善
            if (!mActivity!!.mSyrxxFragment.isAdded ||
                    TextUtils.isEmpty(mActivity!!.mSyrxxFragment.et_sfzmhm.text.toString()) ||
                    TextUtils.isEmpty(mActivity!!.mSyrxxFragment.et_xm.text.toString()) ||
                    TextUtils.isEmpty(mActivity!!.mSyrxxFragment.et_lxdh.text.toString()) ||
                    TextUtils.isEmpty(mActivity!!.mSyrxxFragment.et_xxdz.text.toString())
            ) {
                showToast("请完善所有人信息  ")
                return@setOnClickListener
            }

            mActivity!!.getAllData() //数据传递
            LoadingDialog.getInstance().showLoadDialog(context)
            var result = GsonUtils.toJson(mCarZcMsgData)
            mCarPhotoPersenter!!.uploadFileAndData(result,Constants.PSOT_CAR_YWXX)
        }
        btn_last.setOnClickListener {
            mActivity!!.donext()
        }
        iv_sfzh_scan.setOnClickListener {
            goNfcReadPlugin()
        }

    }

    private fun setAdapterData(sp: Spinner) {
        val adapter = ArrayAdapter<String>(activity, R.layout.my_simple_spinner_item)
        adapter.setDropDownViewResource(R.layout.item_spinner__down_common)
        when (sp) {
            sp_sfzmmc -> {
                val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.SFZMMC)

                for (db in sfzmmcList) {
                    val dmz = db.getDmz()
                    val dmsm1 = db.getDmsm1()
                    adapter.add(dmsm1)
                }
                sp.adapter = adapter
            }
            sp_szdq -> {
                val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.XSQY)

                for (db in sfzmmcList) {
                    val dmz = db.getDmz()
                    val dmsm1 = db.getDmsm1()
                    adapter.add(dmsm1)
                }
                sp.adapter = adapter
            }
        }
    }

    fun getDLRXXCommitData() {

        var sfzmmc = sp_sfzmmc.selectedItem.toString()
        var szqy = sp_szdq.selectedItem.toString()
        val sfzmhm = et_sfzmhm.text.toString()
        val xm = et_xm.text.toString()
        val dh = et_lxdh.text.toString()
        val xxdz = et_xxdz.text.toString()

        //  d_ = 代理人
        mCarZcMsgData.dlrzjlx = CarUtils.sfzmcMap[sfzmmc]//身份证明名称
        mCarZcMsgData.dlrzjhm = sfzmhm//身份证明号码
        mCarZcMsgData.dlrxm = xm//姓名
        mCarZcMsgData.dlrlxdh = dh//联系电话
        mCarZcMsgData.dlrzsqu = CarUtils.szqyMap[szqy]//所在地区
        mCarZcMsgData.dlrzsdz = xxdz//详细地址
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==ID_CARD_READ_CODE && resultCode== Activity.RESULT_OK && data!=null){
            showToast("身份证已读取")
            et_sfzmhm.setText(data.getStringExtra(Constants.NUMBER))

        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as CollectDataActivity
    }
}
