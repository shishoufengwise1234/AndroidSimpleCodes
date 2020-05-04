package com.app.simple.codes.utils

import android.content.res.Resources
import android.util.TypedValue

/**
 * Created by shishoufeng on 2020-05-03.
 * email:shishoufeng1227@126.com
 *
 *
 *
 *
 */
class ScreenUtils {


    companion object {

        fun dp2px(dp: Float): Float {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                Resources.getSystem().displayMetrics
            )
        }

    }
}