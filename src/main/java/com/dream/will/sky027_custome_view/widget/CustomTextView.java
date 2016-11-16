package com.dream.will.sky027_custome_view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.dream.will.sky027_custome_view.R;

import java.util.Random;

/**
 * Author：Will on 2016/11/16 09:19
 * Mail：heheheqin.will@gmail.com
 */

public class CustomTextView extends View implements View.OnClickListener{

    String str;
    Paint paint;
    int color;
    float dimension;


    public CustomTextView(Context context) {
        super(context);
        init();
    }

    /**
     *
     * @param context 上下文环境
     * @param attrs  获取属性
     */
    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //1。获取属性数组    参数一：attrs xml中属性值  参数二 ：自定义属性名
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.myTextView);
        //获取属性名在数组中的位置：R.styleable.styleableName_标签名称

        str = typedArray.getString(R.styleable.myTextView_MyText);
        color = typedArray.getColor(R.styleable.myTextView_MyColor, Color.RED);

        dimension = typedArray.getDimension(R.styleable.myTextView_MySize, 50);

        //释放属性数组
        typedArray.recycle();
        init();

    }

    private void init() {
        paint = new Paint();
        paint.setTextSize(dimension);
        paint.setColor(color);
        paint.setAntiAlias(true);
        //设置点击监听
        this.setOnClickListener(this);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(str,getPaddingLeft(),-paint.ascent()+getPaddingTop(),paint);
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
                if(type == 1){  //宽
                    return (int) paint.measureText(str)+getPaddingLeft()+getPaddingRight();
                }else {  //高
                    return (int) (paint.descent()-paint.ascent())+getPaddingTop()+getPaddingBottom();
                }
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
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        //生成四位随机数
        Random random = new Random();
        int i = random.nextInt(9999) % (9000) + 1000;
        //设置text
        str = i +"";
        //重绘界面
        this.requestLayout();
        invalidate();
    }
}
