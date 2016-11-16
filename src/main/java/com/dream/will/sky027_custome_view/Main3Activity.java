package com.dream.will.sky027_custome_view;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dream.will.sky027_custome_view.widget.PuboLayout;

import java.io.IOException;
import java.io.InputStream;

public class Main3Activity extends AppCompatActivity {

    private PuboLayout pobu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypubo);
        initView();
        AssetManager assets = getAssets();
        try {
            String[] assetses = assets.list("image");
            Log.i("TAG", "onCreate: assetses---------" + assetses[0]);
            for (int i = 0; i < assetses.length; i++) {
                //把文件转成Bitmap
                InputStream open = assets.open("image/" + assetses[i]);
                Bitmap bitmap = BitmapFactory.decodeStream(open);
                pobu.addImage(bitmap);
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.i("TAG", "onCreate: ddddddeeeee---------" + "fffffffff");
        }
    }

    private void initView() {
        pobu = (PuboLayout) findViewById(R.id.pobu);
    }
}
