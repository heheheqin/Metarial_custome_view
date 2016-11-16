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

public class ZoomImageView extends ImageView {
    PointF startPF; //按下时保存的点
    //当前矩阵
    Matrix currentMatrix;
    //移动后点矩阵
    Matrix matrix;
    boolean isMore;

    public ZoomImageView(Context context) {
        super(context);
        init();
    }

    public ZoomImageView(Context context, AttributeSet attrs) {
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
                isMore = true;
                //记录当前点坐标
                startPF.set(event.getX(), event.getY());
                //记录当前矩阵
                currentMatrix.set(getImageMatrix());
                startDis = getDistence(event);
            }
            break;
            case MotionEvent.ACTION_POINTER_UP:{
                isMore = false;
            }
            break;
            case MotionEvent.ACTION_DOWN: {
                //记录当前点坐标
                startPF.set(event.getX(), event.getY());
                //记录当前矩阵
                currentMatrix.set(getImageMatrix());
            }
            break;
            case MotionEvent.ACTION_MOVE: {
                if (isMore) {
                    //计算sx  x方向缩放比例
                    //计算sy  y方向缩放比例
                    float sx = getDistence(event)/startDis;
                    matrix.set(currentMatrix);
                    matrix.postScale(sx,sx,startPF.x,startPF.y);
                    setImageMatrix(matrix);
                }else {

                    //移动时，计算x,y点移动距离
                    float x = event.getX() - startPF.x;
                    float y = event.getY() - startPF.y;
                    //参考矩阵  以按下时的矩阵作为参考点
                    matrix.set(currentMatrix);
                    //设置移动矩阵
                    matrix.postTranslate(x,y);
                    //吧matrix设置为图拍呢矩阵
                    setImageMatrix(matrix);


                }

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
