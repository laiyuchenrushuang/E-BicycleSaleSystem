package com.seatrend.cd.electricbicyclesalesystem.activity

import android.app.Dialog
import android.os.Build
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.adpater.ArchiveFileAdaper
import com.seatrend.cd.electricbicyclesalesystem.common.BaseActivity
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.entity.CommonResponse
import com.seatrend.cd.electricbicyclesalesystem.persenter.ArchiveFilePresenter
import com.seatrend.cd.electricbicyclesalesystem.view.ArchiveFileView
import kotlinx.android.synthetic.main.activity_archive_file.*
import kotlinx.android.synthetic.main.activity_archive_file.iv_btn_search
import kotlinx.android.synthetic.main.activity_dagd.*

/**
 * Created by ly on 2019/7/8 14:33
 *
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
class ArchiveFileActivity : BaseActivity(), ArchiveFileAdaper.SearchViewState, ArchiveFileView {
    override fun netWorkTaskSuccess(commonResponse: CommonResponse) {

    }

    override fun netWorkTaskfailed(commonResponse: CommonResponse) {
        showErrorDialog(commonResponse.getResponseString())
    }

    private var count: Int = 10
    private var ll: LinearLayoutManager? = null
    var adapter: ArchiveFileAdaper? = null
    var mData = ArrayList<HashMap<String, String>>()
    var mArchiveFilePresenter: ArchiveFilePresenter? = null

    override fun initView() {
        setPageTitle(getString(R.string.archive_title))
        initData()
        initRecycleView()
        initEvent()
    }

    override fun getLayout(): Int {
        return R.layout.activity_archive_file
    }

    private fun initData() {
        mArchiveFilePresenter = ArchiveFilePresenter(this)
        for (index in 0 until count) {
            var map = HashMap<String, String>()
            map["zcbm"] = "No." + index
            map["clhm"] = "川A " + index
            map["shzt"] = "" + if ((index % 2) == 1) 1 else 0
            mData.add(map)
        }
    }

    private fun initRecycleView() {
        ll = LinearLayoutManager(this)
        m_recycler_view!!.layoutManager = ll
        adapter = ArchiveFileAdaper(this, mData)
        m_recycler_view.adapter = adapter
        adapter!!.registerSetSearchViewListener(this)
    }

    private fun initEvent() {
        shuaxin.isEnableRefresh = false //取消下拉刷新

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            header.tooltipText = ""
        }
        var searchString: String? = null

        //搜索框不自动缩小为一个搜索图标，而是match_parent
        searchview.setIconifiedByDefault(false)
        //显示搜索按钮
        searchview.isSubmitButtonEnabled = false
        //设置提示hint
        searchview.queryHint = "查询车辆号牌或整车编码"
        searchview!!.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(pSubmit: String?): Boolean {
                Log.d("lylog", "pSubmit =" + pSubmit)
                return false
            }

            override fun onQueryTextChange(pChange: String?): Boolean {
                searchString = pChange
                return false
            }

        })
        iv_btn_search.setOnClickListener {
            if (TextUtils.isEmpty(searchString)) {
                showToast("搜索的内容为空")
                return@setOnClickListener
            }
            var map = HashMap<String, String>()
            map.put("zcbm", searchString + "")
//            mArchiveFilePresenter!!.doNetworkTask(map,Constants.USER_LOGIN)
        }

        shuaxin.setOnLoadmoreListener(object : OnLoadmoreListener {
            override fun onLoadmore(refreshlayout: RefreshLayout?) {
                for (index in count until count + 10) {
                    var map = HashMap<String, String>()
                    map["zcbm"] = "No." + index
                    map["clhm"] = "川A " + index
                    map["shzt"] = "" + if ((index % 2) == 1) 1 else 0
                    mData.add(map)
                }
                count += 10
                adapter!!.notifyDataSetChanged()
                refreshlayout!!.finishLoadmore()
            }

        })
        all_select!!.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, check: Boolean) {
                adapter!!.setAllselectListener(check)
            }

        })
        all_commit!!.setOnClickListener {
            if (all_select.isChecked && mData.size == adapter!!.getPositionList().size) {
                val mDialog = Dialog(this)
                mDialog.setContentView(R.layout.dialog_error)
                mDialog.setCanceledOnTouchOutside(true)
                val tvMsg = mDialog.findViewById<TextView>(R.id.tv_msg)
                val btnOk = mDialog.findViewById<Button>(R.id.btn_ok)
                tvMsg.text = "当前已经勾选全选选项，确定需要全部归档？"
                btnOk.setOnClickListener {
                    mDialog.dismiss()
                    startActivity(intent.setClass(this, RemindHPBFactivity::class.java))
                }
                mDialog.show()
            } else if (0 == adapter!!.getPositionList().size) {
                showErrorDialog("当前没有勾选需要归档条目，请勾选归档选项！")
                return@setOnClickListener
            } else {
                startActivity(intent.setClass(this, RemindHPBFactivity::class.java))
            }
            showLog("result = " + adapter!!.getPositionList().toString())

        }
    }

    override fun searchViewChangeListener(change: Boolean) {
        if (change) {
            all_select!!.isChecked = false
        }
    }
}