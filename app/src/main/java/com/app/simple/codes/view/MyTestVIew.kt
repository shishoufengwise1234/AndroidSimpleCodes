package com.app.simple.codes.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.app.simple.codes.utils.ScreenUtils

/**
 * Created by shishoufeng on 2020-05-03.
 * email:shishoufeng1227@126.com
 *
 *
 *
 *
 */
class MyTestVIew(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    private val TAG = "MyTestVIew"

    private var paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var path = Path()

    private var pathMeasure = PathMeasure()

    init {

        paint.color = Color.BLACK
        paint.strokeWidth = ScreenUtils.dp2px(3.0f)
        paint.style = Paint.Style.FILL_AND_STROKE



    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawLine(50.0f,50.0f,260.0f,260.0f,paint)

        paint.color = Color.RED
//        canvas?.drawCircle(width / 2.0f,height /2.0f,ScreenUtils.dp2px(100.0f),paint)
//
        path.fillType = Path.FillType.EVEN_ODD

        canvas?.drawPath(path,paint)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        path.reset()

        path.addRect(width /2.0f - 150,height / 2.0f - 150,
            width / 2.0f + 150,height / 2.0f + 150,Path.Direction.CCW)

        path.addCircle(width /2.0f ,height /2.0f,150.0f,Path.Direction.CCW)

    }

}