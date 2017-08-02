package coder.aihui.ui.splash;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jaeger.library.StatusBarUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import coder.aihui.R;
import coder.aihui.ui.main.MainActivity;
import coder.aihui.util.SPUtil;

/**
 * Created by xx on 2016/10/21.
 */
public class SplashActivity extends RxAppCompatActivity implements View.OnClickListener {

    private MyVideoView mVideoView;
    private Button      mButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //设置沉浸式状态栏
        StatusBarUtil.setColor(this, Color.GRAY);
        initView();
        initData();
    }

    private void initData() {
        mVideoView.setVideoURI(Uri.parse("android.resource://"+this.getPackageName()+"/"+R.raw.aaa));
        //当视频播放完毕，继续播放
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mVideoView.start();
            }
        });
        mVideoView.start();
    }

    private void initView() {
        mVideoView = (MyVideoView) findViewById(R.id.videoview);
        mButton = (Button) findViewById(R.id.bt);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt:
                //判断当前是否是第一次进入，如果是先进入引导界面，否则直接进入首页
                boolean first = SPUtil.getBoolean(SplashActivity.this, "first", true);
                if (first) {
                    //进入引导界面
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    //记录下第一次进入操作
                    SPUtil.saveBoolean(SplashActivity.this,"first",false);
                } else {
                    //进入首页
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                }

                //停止播放视频，并销毁界面
                if (mVideoView != null && mVideoView.isPlaying()) {
                    mVideoView.stopPlayback();
                }

                finish();
                break;
        }
    }
}
