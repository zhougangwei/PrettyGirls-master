package coder.aihui.ui.splash;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;


public class MyVideoView extends VideoView {
    public MyVideoView(Context context) {
        super(context);
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //为了让视频整屏播放发，重写测量方法

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(0, widthMeasureSpec);//按照当前控件的有多大的位置就显示多大
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width,height);
    }
}
