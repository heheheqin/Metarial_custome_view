package com.dream.will.sky027_custome_view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TimeUtils;
import android.view.View;

/**
 * Author：Will on 2016/11/15 11:08
 * Mail：heheheqin.will@gmail.com
 * 实现进度提示
 * 设置进度
 * 获取进度值
 * <p>
 * 继承View
 * 重写 onDraw
 */

public class MyprogressBar extends View {
    Paint paint;
    /**
     * 半径
     */
    int cr = 100;
    //
    int px = 0;

    int py = 0;
    //扇形区域
    RectF rf;

    int progress = 0;

    /**
     * zaixml布局中写控件
     *
     * @param context
     */
    public MyprogressBar(Context context) {
        super(context);
        init();
    }

    /**
     * java中动态创建
     *
     * @param context
     * @param attrs
     */
    public MyprogressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //初始化
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
        int kongxi = 20;

        rf = new RectF(px - cr + kongxi, py - cr + kongxi, px + cr - kongxi, py + cr - kongxi);

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
                if (type==1) {

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

    /**
     * 绘制调用
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("TAG", "onDraw:---------");
        //设置画笔 描边
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setStrokeMiter(5);
        canvas.drawCircle(px, py, cr, paint);
        paint.setStyle(Paint.Style.FILL);
        int tmp = progress * 360 / 100;
        canvas.drawArc(rf, 0, tmp, true, paint);
    }

    //向外提供设置进度方法
    public void setProgress(int progress) {
        this.progress = progress;
        //重绘界面
        invalidate();
    }
}
