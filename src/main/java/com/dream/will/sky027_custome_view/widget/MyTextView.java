package com.dream.will.sky027_custome_view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author：Will on 2016/11/15 14:48
 * Mail：heheheqin.will@gmail.com
 */

public class MyTextView extends View {
    Paint paint;
    /**
     * 半径
     */
    int cr = 100;
    //
    int px = 0;

    int py = 0;

    //
    public MyTextView(Context context) {
        super(context);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        //设置抗锯齿
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#bbcccccc"));

//不能这里获取宽高  界面还没有绘制出来
//        px = getWidth()/2;
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


        //经过super.onMeasure 后得到测量大小
        //计算圆中心 and  半径
        px = getMeasuredWidth() / 2;
        py = getMeasuredHeight() / 2;
        //
        cr = px < py ? px - 5 : py - 5;


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
                if (type == 1) {

                }
                return 200;
            }
            case MeasureSpec.UNSPECIFIED: {
                //想要多大就有多大，一半为Listview 或scrollview 子控件大小
                //
                return size;
            }
        }
        return size;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆
        canvas.drawCircle(cr, px, py, paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        //宽度
        float with = paint.measureText("%");
        //高度
        float hi = paint.ascent() + paint.descent();
        //写字
        canvas.drawText("%", px - with / 2, py - hi/2 - paint.descent(), paint);
    }
}
