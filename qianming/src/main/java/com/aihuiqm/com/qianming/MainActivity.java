package com.aihuiqm.com.qianming;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int MY_PERMISSION_REQUEST_CODE = 10000;
    private ImageView mIvQm;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initView();
    }

    private void initView() {
        mTv = (TextView)findViewById(R.id.tv);
        mIvQm = (ImageView)findViewById(R.id.iv_qm);
        mTv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv:

                if (Build.VERSION.SDK_INT >= 23) {
                    int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST_CODE);
                        return;
                    }else{
                        gotoQianMing();
                    }
                } else {
                    gotoQianMing();
                }



                break;
        }



    }

    private QianMingUtils mQianMingUtils;
    private String qmUrl        = new String();
    private void gotoQianMing() {



        mQianMingUtils = new QianMingUtils(this);
        mQianMingUtils.showQianming(this, new QianMingUtils.OnSure() {
            @Override
            public void backResult(String url) {
                if (url != null) {

                    Glide.with(MainActivity.this).load(url).into(mIvQm);
                }
            }
        });
    }



}
