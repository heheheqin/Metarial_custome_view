package com.dream.will.sky027_custome_view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Will on 2016/11/15 15:54
 * Mail：heheheqin.will@gmail.com
 */

public class MyLinear  extends ViewGroup{

    List<List<View>>  lines;
    List<Integer> heights;



    public MyLinear(Context context) {
        super(context);
        init();
    }

    public MyLinear(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        lines = new ArrayList<>();
        heights = new ArrayList<>();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量所有子控件大小
        //获取子空间的数量
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            //测量每个子控件
            measureChild(childAt,widthMeasureSpec,heightMeasureSpec);
        }
    }

    /*    *//*
    * 返回子控件的layoutParams属性
    * xml中，layout_xxx
    * *//*
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }*/

    //布局子控件时  调用
    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

        Log.i("TAG", "onLayout: ----heheheh-----" );
//        //清控件集合
//        lines.clear();
//        heights.clear();

        //分析 总共有多少行，每一行的子控件集合
        //每一行宽度
        int childWidth = 0;
        int childHeight = 0;
        //计算当前控件宽度
        int measuredWidth = getMeasuredWidth();
        //子控件的个数
        int childCount = getChildCount();
        //循环获取每个子控件，得到每行的宽度
        ArrayList<View> childList = new ArrayList<>();
        for (int j = 0; j < childCount; j++) {
            View childAt = getChildAt(j);
            //获取子控件layout属性
             MarginLayoutParams params = (MarginLayoutParams)childAt.getLayoutParams();
//             LayoutParams params = (LayoutParams)childAt.getLayoutParams();
            //当前子控件宽度
            int measuredWidth1 = childAt.getMeasuredWidth()+params.leftMargin +params.rightMargin;
            int measuredHeight = childAt.getMeasuredHeight() +params.topMargin +params.bottomMargin;
            //判断当前宽度总长  是否换行
            if (measuredWidth1+childWidth>measuredWidth) {
                //帮当前高度保存到集合
                heights.add(childHeight);
                lines.add(childList);
                //换行 子集合中心负值
                childList = new ArrayList<>();
                //清零 宽度 累加值
                childWidth = 0;
                //高度清零
                childHeight = 0;
            }
            //不换行 把当前子控件添加到子集合
            childList.add(childAt);
            //每行宽度相加
            childWidth = childWidth +measuredWidth1;
            //保存当前最高值
            childHeight = Math.max(childHeight,measuredHeight);
        }
        //把最后一行的子控件集合以及高度添加到集合中
        lines.add(childList);
        heights.add(childHeight);
        Log.i("TAG", "onLayout: -heights--------" + heights);

        //开始布局， 总共行数
        int lineCount = lines.size();
        //每行高度累加
        int sumtop = 0;
        //每行布局
        for (int i4 = 0; i4 < lineCount; i4++) {
            //取出当前行的所有子控件
            List<View> currentLine = lines.get(i4);
            //左边距
            int sumleft = 0;
            for (int i5 = 0; i5 < currentLine.size(); i5++) {
                //取出每个子控件
                View view = currentLine.get(i5);
                //获取子控件layout属性
                MarginLayoutParams params = (MarginLayoutParams)view.getLayoutParams();
                int left = sumleft +params.leftMargin;
                int top = sumtop + params.topMargin;
                int right = left +view.getMeasuredWidth() ;
                int buttom = top + view.getMeasuredHeight();
                Log.i("TAG", "onLayout: top---------" + top);
                //布局
                view.layout(left,top,right,buttom);
                //当前布局完，准备下一个控件布局
                sumleft += view.getMeasuredWidth() + params.leftMargin +params.rightMargin;
            }
            sumtop += heights.get(i4);
//            Log.i("TAG", "onLayout: sumtop---"+i4+"------" + sumtop);
        }

    }

    /**
     * 返回子控件   layout属性
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
}
