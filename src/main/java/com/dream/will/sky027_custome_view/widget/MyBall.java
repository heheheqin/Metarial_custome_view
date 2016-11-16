package com.dream.will.sky027_custome_view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author：Will on 2016/11/16 10:52
 * Mail：heheheqin.will@gmail.com
 * 随手移动的圆
 */

public class MyBall extends View {
    int px;
    int py;
    int cr;
    RectF rectF;
    Paint paint;
    boolean isMore;

    public MyBall(Context context) {
        super(context);
        init();
    }

    public MyBall(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        px = 0;
        py = 0;
        cr = 200;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#aacccccc"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(px, py, cr, paint);
    }

    /**
     * 手指触摸  回调
     *
     * @param event 包装了触摸事件
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //如果是手指移动，计算圆心重绘界面
        switch (event.getAction() & MotionEvent.ACTION_MASK) {


            case MotionEvent.ACTION_DOWN: {
                Log.i("TAG", "onTouchEvent: ACTION_DOWN---------");
                isMore = false;
            }
            break;
            case MotionEvent.ACTION_POINTER_DOWN:
            {
                isMore =true;
                Log.i("TAG", "onTouchEvent: ACTION_POINTER_DOWN---------");
                px = (int) event.getX(0);
                py = (int) event.getY(0);
                cr = getCr(event);
                invalidate();
            }
            break;
            case MotionEvent.ACTION_POINTER_UP:{
                isMore = false;
            }break;
            case MotionEvent.ACTION_MOVE: {
                Log.i("TAG", "onTouchEvent: ACTION_MOVE---------");
                px = (int) event.getX();
                py = (int) event.getY();
                if (isMore) {
                    cr = getCr(event);
                }
                invalidate();
            }
            break;
            case MotionEvent.ACTION_UP: {
                isMore = false;
                Log.i("TAG", "onTouchEvent: ACTION_UP---------");
            }
            break;
        }
//        return super.onTouchEvent(event);
        return true;
    }

    private int getCr(MotionEvent event) {
        int x = (int) (event.getX(1) - event.getX(0));
        int y = (int) (event.getY(1) - event.getY(0));
        return (int) Math.sqrt(x*x+y*y);
    }

    /**
     * 测量控件得到 控件测量大小
     * 还没有绘制的时候可以知道控件大小
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置宽高  不使用super
        setMeasuredDimension(getMeasureWH(widthMeasureSpec, 1), getMeasureWH(heightMeasureSpec, 2));

    }

    /**
     * 参数一：widthMeasureSpec或  heightMeasureSpec
     * 参数二：表示测量师宽还是高  1宽 2 高
     */
    private int getMeasureWH(int widthMeasure, int type) {
        //获取设置模式  推荐大小
        int mode = MeasureSpec.getMode(widthMeasure);
        //推荐大小
        int size = MeasureSpec.getSize(widthMeasure);
        //根据模式计算大小
        switch (mode) {
            case MeasureSpec.EXACTLY: {
                //精确值 在xml中设置控件大小为固定值，或match——parent
                //直接返回推荐大小

                return size;
            }
            case MeasureSpec.AT_MOST: {
                //在范围内。在xml中，设置控件大小为wrap——content
                //根据内容，计算大小再返回
//                if(type == 1){  //宽
//                    return (int) paint.measureText(str)+getPaddingLeft()+getPaddingRight();
//                }else {  //高
//                    return (int) (paint.descent()-paint.ascent())+getPaddingTop()+getPaddingBottom();
//                }
                return size;
            }
            case MeasureSpec.UNSPECIFIED: {
                //想要多大就有多大，一半为Listview 或scrollview 子控件大小
                //
                return size;
            }
        }
        return size;
    }
}
