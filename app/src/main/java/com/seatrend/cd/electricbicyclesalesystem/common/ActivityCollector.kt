package com.seatrend.cd.electricbicyclesalesystem.common

import android.app.Activity

import java.util.ArrayList

/**
 * Created by Administrator on 2016/12/22.
 */

object ActivityCollector {

    var activities: MutableList<Activity> = ArrayList()

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    fun finishAll() {
        for (i in activities.indices) {
            val activity = activities[i]
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
        /*for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }*/
    }

    fun getcurrentActivity(): Activity? {
        return if (activities.size > 0) {
            activities[activities.size - 1]
        } else null
    }

    //除了指定一个activity 其他都关闭
    fun finishToOne(cls: Class<*>) {
        for (i in activities.indices) {
            val activity = activities[i]
            if (!activity.isFinishing) {

                if (activity.javaClass == cls) {
                    continue
                } else {
                    activity.finish()
                }

            }
        }
    }


    //关闭当前activity
    fun finishCurrentActivity() {
        if (activities.size > 0) {
            val activity = activities[activities.size - 1]
            if (!activity.isFinishing) {
                activity.finish()
            }
        }

    }
}
