package com.seatrend.cd.electricbicyclesalesystem.common

import android.app.Activity
import android.app.Application
import android.os.Bundle


/**
 * Created by seatrend on 2018/8/20.
 */

class MyApplication : Application(), Application.ActivityLifecycleCallbacks {

    override fun onCreate() {
        super.onCreate()
        myApplicationContext = this
        //CrashHandler.getInstance().init(this);
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle) {
        //currentActivity=activity;
    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }

    companion object {


        var myApplicationContext: MyApplication? = null

    }
}
