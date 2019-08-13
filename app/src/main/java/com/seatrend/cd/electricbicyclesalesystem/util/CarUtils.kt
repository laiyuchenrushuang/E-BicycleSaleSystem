package com.seatrend.cd.electricbicyclesalesystem.util

import com.seatrend.cd.electricbicyclesalesystem.R
import com.seatrend.cd.electricbicyclesalesystem.common.Constants
import com.seatrend.cd.electricbicyclesalesystem.common.MyApplication
import com.seatrend.cd.electricbicyclesalesystem.database.CodeTableSQLiteUtils

import java.util.HashMap

/**
 * Created by ly on 2019/8/1 17:59
 *
 *
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
class CarUtils {

   companion object{
       val CY_ZCDJ = MyApplication.myApplicationContext!!.getString(R.string.zcdj)
       val CY_ZYDJ = MyApplication.myApplicationContext!!.getString(R.string.zydj)
       val CY_BGDJ = MyApplication.myApplicationContext!!.getString(R.string.bgdj)
       val CY_BHPZ = MyApplication.myApplicationContext!!.getString(R.string.bhpz)
       val CY_XSDJ = MyApplication.myApplicationContext!!.getString(R.string.xsdj)
       val Car_LX_BZ = MyApplication.myApplicationContext!!.getString(R.string.bz_car)
       val Car_LX_CBZ = MyApplication.myApplicationContext!!.getString(R.string.cbz_car)
       val ZC_QY_OFF = MyApplication.myApplicationContext!!.getString(R.string.zc_qy_off)
       val ZC_QY_ON = MyApplication.myApplicationContext!!.getString(R.string.zc_qy_on)
       //业务类型码表对应
       val ywlxMap: HashMap<String, String>
           get() {
               val ywlxMap = HashMap<String, String>()
               ywlxMap[CY_ZCDJ] = "A"
               ywlxMap[CY_ZYDJ] = "B"
               ywlxMap[CY_BGDJ] = "D"
               ywlxMap[CY_BHPZ] = "K"
               ywlxMap[CY_XSDJ] = "E"
               return ywlxMap
           }

        //车身颜色码表对应(颜色->码)
       val csysMap: HashMap<String, String>
           get() {
               val csysMap = HashMap<String, String>()
               val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.CSYS)
               for (db in sfzmmcList) {
                   val dmz = db.getDmz()
                   val dmsm1 = db.getDmsm1()
                   csysMap.put(dmsm1,dmz)
               }
               return csysMap
           }

       //车身颜色码表对应  (码->颜色)
       val csysGetMap: HashMap<String, String>
           get() {
               val csysMap = HashMap<String, String>()
               val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(Constants.CSYS)
               for (db in sfzmmcList) {
                   val dmz = db.getDmz()
                   val dmsm1 = db.getDmsm1()
                   csysMap.put(dmz,dmsm1)
               }
               return csysMap
           }

       // 车辆类型码表对应
       val cllxMap:HashMap<String,String>
           get() {
               val cllxMap = HashMap<String,String>()
               cllxMap[Car_LX_BZ] = "1"
               cllxMap[Car_LX_CBZ] = "2"
               return cllxMap
           }

       // 行驶区域码表对应
       val xsqyMap:HashMap<String,String>
           get() {
               val xsqyMap = HashMap<String,String>()
               xsqyMap[ZC_QY_OFF] = "1"
               xsqyMap[ZC_QY_ON] = "2"
               return xsqyMap
           }
       //其他获取代码值的接口 注意没有来历证明
       fun getQTDMZ(cxxh:String ):HashMap<String,String>{
           val QTMap = HashMap<String, String>()
           val sfzmmcList = CodeTableSQLiteUtils.queryByDMLB(cxxh)
           for (db in sfzmmcList) {
               val dmz = db.getDmz().trim()
               val dmsm1 = db.getDmsm1().trim()
               QTMap.put(dmsm1,dmz)
           }
           return QTMap
       }
       var llzmMap = HashMap<String,String>() //数据来自YwxxFragment

       //获取车辆类型码表
       val taskLXMap:HashMap<String,String>
       get() {
           val taskLXMap = HashMap<String,String>()
           taskLXMap["2"] = "A"
           taskLXMap["4"] = "B"
           taskLXMap["3"] = "D"
           taskLXMap["5"] = "K"
           return taskLXMap
       }
        //获取车辆类型名称
       val getTaskLXMap:HashMap<String,String>
           get() {
               val getTaskLXMap = HashMap<String,String>()
               getTaskLXMap["A"] = CY_ZCDJ
               getTaskLXMap["B"] = CY_ZYDJ
               getTaskLXMap["D"] = CY_BGDJ
               getTaskLXMap["K"] = CY_BHPZ
               return getTaskLXMap
           }
       //获取车辆类型
       val getCllxMap:HashMap<String,String>
           get() {
               val getCllxMap = HashMap<String,String>()
               getCllxMap["1"] = Car_LX_BZ
               getCllxMap["2"] = Car_LX_CBZ
               return getCllxMap
           }
       var sfzmcMap = HashMap<String,String>()//数据来自YwxxFragment
       var szqyMap = HashMap<String,String>()//数据来自YwxxFragment
   }

}
