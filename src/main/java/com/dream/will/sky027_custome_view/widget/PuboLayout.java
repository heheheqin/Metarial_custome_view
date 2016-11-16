package com.dream.will.sky027_custome_view.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Will on 2016/11/16 16:02
 * Mail：heheheqin.will@gmail.com
 */

public class PuboLayout extends ScrollView {
    public PuboLayout(Context context) {
        super(context);
        init();
    }

    public PuboLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //三个垂直方向线性布局。用来存放ImageView
    List<LinearLayout> childLayout;

    private void init() {
        //初始化集合
        childLayout = new ArrayList<>();
        //添加水平线性布局
        LinearLayout hLinearLayout = new LinearLayout(getContext());
        //设置水平方向
        hLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        //添加垂直线性布局到 水平布局
        //垂直线性布局到布局属性
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 2;
        for (int i = 0; i < 3; i++) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            //添加三个垂直线性布局到 水平中去
            hLinearLayout.addView(linearLayout,params);
            //添加到集合
            childLayout.add(linearLayout);
        }
        //把水平线性布局添加到scrollview
        addView(hLinearLayout);
    }

    //同级当前有多少个ImageView
    int countImg = 0;

    public  void addImage(Bitmap bitmap){
        //每添加一张图片就创建一个行到ImageView
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(bitmap);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(5,5,5,5);
        //并添加到指定到垂直线性布局
        //子控件索引
        int index = countImg%childLayout.size();
        childLayout.get(index).addView(imageView,params);
        Log.i("TAG", "addImage: childLayout---------" + childLayout.get(index).getChildCount());
        //计数添加
        countImg++;
    }

}
