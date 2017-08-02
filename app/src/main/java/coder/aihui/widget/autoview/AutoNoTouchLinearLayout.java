package coder.aihui.widget.autoview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zhy.autolayout.AutoLayoutInfo;
import com.zhy.autolayout.utils.AutoLayoutHelper;

/**
 * 此Template用于生成AutoLayout需要的的Auto系列View,如需要使ScrollView适配,使用此Template输入ScrollView,即可生成
 * AutoScrollView,使用此View即可自适应
 * Created by jess on 16/4/14.
 */
public class AutoNoTouchLinearLayout extends LinearLayout {
    private AutoLayoutHelper mHelper = new AutoLayoutHelper(this);
    private boolean noTouch;
    public AutoNoTouchLinearLayout(Context context) {
        super(context);
    }

    public AutoNoTouchLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoNoTouchLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode())
            mHelper.adjustChildren();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }


    public static class LayoutParams extends LinearLayout.LayoutParams
            implements AutoLayoutHelper.AutoLayoutParams {
        private AutoLayoutInfo mAutoLayoutInfo;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            mAutoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(c, attrs);
        }

        @Override
        public AutoLayoutInfo getAutoLayoutInfo() {
            return mAutoLayoutInfo;
        }


        public LayoutParams(int width, int height) {
            super(width, height);
        }


        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if(noTouch){
            return false;
        }else{
            return super.dispatchTouchEvent(ev);
        }

    }

    public void setNotouch(boolean isnotouch){
        noTouch=isnotouch;
    }
}