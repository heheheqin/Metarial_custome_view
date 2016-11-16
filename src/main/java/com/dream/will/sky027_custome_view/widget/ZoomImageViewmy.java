package com.dream.will.sky027_custome_view.widget;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Author：Will on 2016/11/16 14:08
 * Mail：heheheqin.will@gmail.com
 * <p>
 * 按矩阵移动缩放图片
 */

public class ZoomImageViewmy extends ImageView {
    PointF startPF; //按下时保存的点
    //当前矩阵
    Matrix currentMatrix;
    //移动后点矩阵
    Matrix matrix;
    boolean isMore;

    public ZoomImageViewmy(Context context) {
        super(context);
        init();
    }

    public ZoomImageViewmy(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setScaleType(ScaleType.MATRIX);
        startPF = new PointF();
        currentMatrix = new Matrix();
        matrix = new Matrix();
    }

    //多点按下时距离
    float startDis = 0;
    //重写onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()&MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_POINTER_DOWN:{
                //多点触控
            }
            break;
            case MotionEvent.ACTION_POINTER_UP:{
                //抬起时 释放
            }
            break;
            case MotionEvent.ACTION_DOWN: {
                //按下计算当前位置
            }
            break;
            case MotionEvent.ACTION_MOVE: {
                //

            }
            break;
        }

        return true;
    }

    private  float getDistence(MotionEvent event){
        float x =  (event.getX(1) - event.getX(0));
        float y =  (event.getY(1) - event.getY(0));
        return (float) Math.sqrt(x*x+y*y);
    }


}
