package com.seatrend.cd.electricbicyclesalesystem.common

import android.os.Environment

/**
 * Created by seatrend on 2018/8/20.
 */

class Constants {

    companion object {

        var UPDATA_TIME = "更新时间:2019-04-12"
        //http://11.1.1.73:18080
        //接口
        var GET = "GET"
        var POST = "POST"///vio/getVioByCar
        var IMAGE_FILE = "BicycleImage"//文件夹
        var USE_FILE = "BicycleFile"//文件夹
        var IMAGE_PATH = Environment.getExternalStorageDirectory().path + "/" + IMAGE_FILE//照片的路径
        var FILE_PATH = Environment.getExternalStorageDirectory().path + "/" + USE_FILE//保存文件的路径


        var GET_CODE = "/seaCode/getSeaCodeByXtlbAndDmlb"
        var GET_CODE_QH = "/seaCode/getSeaCodeForQH"
        var USER_LOGIN = "/seaSysUser/login"
        var GET_CAR_MSG = "/remote/getBikeCccInfo"   //远程接口
        var FACTOTY_GET_CAR_MSG = "/BusinessHandling/getVehFlowMainByZcbm"  //业务接口
        var UPDATE_CAR_MSG = "/vehicleTemp/updateVehicleTemp"
        var ADD_CAR_MSG = "/vehicleTemp/addVehicleTemp"
        var SAVE_PHOTO = "/photo/saveFilePhoto"
        var GET_INSURANCEP_MES = "/insurance/getVehInsurance"
        var SAVE_INSURANCEP_MES = "/insurance/saveVehInsurance"
        var GET_COMPANY_NAME = "/insurance/getInsuranceCompanys"
        var DECRYPT_INSURANCE_MSG = "/insurance/decryptVehInsurance"
        var GET_INSURANCE_DETAILS = "/insurance/finishInsurance"
        var WARNING_MESSAGE = "/warning/getVehFlowMainByGlbmAndShbj"
        var BUSINESS_MESSAGE = "/statistics/getCountOfVehFlowMainByGlbmAndTime"
        var DOWNLOAD_PHOTO = "/photo/downloadPhoto"
        var VERIFY = "/validation/validationStatusOfBuyCar"
        var EXIT_LOGIN = "/seaSysUser/logout"
        var GET_QR_CODE_BY_LSH = "/vehicleTemp/getQrCodeByLshAndXh"
        var GET_TREE_BY_GLBM = "/admin/department/getTreeByGlbm"
        var GET_PHOTO_TYPE = "/Configuration/getConfigList"
        var SAVE_CAR_CY_URL = "/vehicleTemp/addVehicleAndFlow"
        var GET_CAR_LLZM_BY_HDFS = "/Configuration/geParticulartConfigList"
        var PSOT_CAR_YWXX = "/BusinessHandling/updateOwnerMessage" //业务类接口
        var POST_HPBF = "/BusinessHandling/licensePlate"


        //相关的key值
        var SETTING = "setting"
        var IP_K = "ip"
        var PORT_K = "port"
        var NAME = "name"
        var SEX = "sex"
        var NUMBER = "number"
        var ADDRESS = "address"
        var ADMAIN = "admain"
        var PHOTO = "photo"
        var SFZMMC = "2019" //身份证明名称系统类别
        var CLLX = "1007" //车辆类型系统类别
        var SYXZ = "1003"   //使用性质
        var CSYS = "1008"   //车身颜色
        var HDFS = "0001"   //获得方式
        var YWLX = "0060"   //业务类型
        var LLZM = "1002"   //来历证明
        var CLYT = "1006"  //车辆用途
        var XSQY = "0033"  //行驶区域
        var SYN_CODE = "syncode"
        var IS_FIRST = "is_first"
        var CLSBDH_ENTITY = "clsbdh_entity"
        var RESULT = "result"
        var BH = "cjxxbh"
        var QAUTH = "Authorization"
        var CJXXBH = "cjxxbh"
        var DATA = "data"
        var MSG = "msg"
        var TITLE = "title"
        var PATH = "path"
        var ZPLX = "zplx"
        var TYPE = "type"
        var TAG = "tag"

        var TJQSSJ = "tjqssj"
        var TJZZSJ = "tjzzsj"

        var POSITION = "position"
        var TO_VIN = "tovin"
        var LSH = "lsh"
        var XH = "xh"

        var TASK = "task"
        var LX = "JKLX"  // 接口类型 1  外网， 2  业务内网


        var APK_NAME = "zizhuzhongduan.apk"
        var CHECK_UPDATE = "http://11.1.1.73:18082/download/version.jason"

        val CAR_VIN: String = "0" //车辆VIN
        val CAR_CY: String = "1" //车辆查验
        val CAR_ZC: String = "2" //车辆注册
        val CAR_BG: String = "3" //车辆变更
        val CAR_ZY: String = "4" //车辆转移
        val CAR_BH: String = "5" //车辆补换
        val CAR_ZX: String = "6" //车辆注销
        val CAR_GD: String = "7" //车辆档案归档

        //车辆查验的KEY （CarCYActivity）
        val CY_CLLX: String = "cllx"  //车身类型 1 标准  0超标
        val CY_YWLX: String = "ywlx"  //A 注册登记  D 变更登记  B 转移登记 K 补换登记
        val CY_CSYS1: String = "csys1"  //颜色1
        val CY_CSYS2: String = "csys2"  //颜色2
        val CY_BJ_CSYS: String = "csysbj"  //车身颜色标记( 0-不合格 1-合格)
        val CY_WKC: String = "wkc" //外壳长
        val CY_WKK: String = "wkk"//外壳宽
        val CY_WKG: String = "wkg"//外壳高
        val CY_BJ_CKG: String = "ckgbj" // 长宽高标记( 0-不合格 1-合格)
        val CY_SCZL: String = "sczbzl" //实测质量
        val CY_BJ_SCZL: String = "zczlbj" //实测质量标记( 0-不合格 1-合格)
        val CY_SJSS: String = "sjss" //实际时速
        val CY_BJ_SJSS: String = "sjssbj" //实际时速标记( 0-不合格 1-合格)
        val CY_YWYY = "vehFlowMain.djjy"  //业务原因
        val CY_BHGYY = "vehFlowMain.ywyy"  //不合格原因  (说明:在app端业务原因在数据库对应的字段是djjy,不合格原因字段是ywyy)

//        val CY_ZCDJ = "注册登记"
//        val CY_ZYDJ = "转移登记"
//        val CY_BGDJ = "变更登记"
//        val CY_BHPZ = "补/换牌证"
//        val CY_BZ = "标准电动自行车"
//        val CY_CBZ = "超标准电动自行车"
//        val CY_YWYY_CSYS = "变更车身颜色"
//        val CY_YWYY_ZCBM = "变更整车编码"
//        val CY_YWYY_SFZ = "变更身份证号码"
    }


}
