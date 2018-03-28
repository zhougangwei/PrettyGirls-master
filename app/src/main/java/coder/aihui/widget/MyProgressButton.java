package coder.aihui.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.jiang.android.pbutton.CProgressDrawable;
import com.jiang.android.pbutton.ProgressListener;
import com.jiang.android.pbutton.R.color;
import com.jiang.android.pbutton.R.styleable;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/8/9 15:13
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/8/9$
 * @ 更新描述  ${TODO}
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class MyProgressButton extends Button implements ProgressListener {
    private Drawable               mBackground;
    private CProgressDrawable      mProgressDrawable;
    private int                    mWidth;
    private int                    mHeight;
    private MyProgressButton.STATE mState;
    private boolean                morphingCircle;          //变成圆的动画效果状态
    private boolean                morphingNormal;           //变成普通的的动画效果状态
    private float                  mFromCornerRadius;
    private float                  mToCornerRadius;
    private long                   mDuration;
    private int                    mProgress;
    private int                    mMaxProgress;
    private int                    mStrokeColor;
    private int                    mStokeWidth;
    private int                    mStokeWidthOut;
    private static String[] statusString = new String[]{"download", "pause", "complete", "error", "delete"};
    private String resultString;
    private static final String TAG = "CProgressButton";
    private int mulTimes;                                   //几次
    private int currentTimes=1;

    public MyProgressButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public MyProgressButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mState = MyProgressButton.STATE.NORMAL;
        this.mFromCornerRadius = 40.0F;
        this.mToCornerRadius = 90.0F;
        this.mDuration = 500L;
        this.mMaxProgress = 100;
        this.mStokeWidth = 0;
        this.mStokeWidthOut = 0;
        TypedArray a = this.getContext().getTheme().obtainStyledAttributes(attrs, styleable.CProgressButton, 0, 0);

        try {
            this.mStrokeColor = a.getInteger(styleable.CProgressButton_color, -1);
            this.mBackground = a.getDrawable(styleable.CProgressButton_drawable_xml);
            this.mStokeWidthOut = (int) a.getDimension(styleable.CProgressButton_stroke_width, -1.0F);
            this.mFromCornerRadius = (float) ((int) a.getDimension(styleable.CProgressButton_radius, -1.0F));
        } finally {
            a.recycle();
        }

        if (this.mStrokeColor == -1) {
            this.mStrokeColor = this.getResources().getColor(color.black);
        }

        if (this.mBackground == null) {
            throw new NullPointerException("drawable_xml can not be null");
        } else {
            if (this.mStokeWidthOut == -1) {
                this.mStokeWidthOut = dip2px(this.getContext(), 1.0F);
            }

            if (this.mFromCornerRadius == -1.0F) {
                throw new NullPointerException("radius must can not be null");
            } else {
                this.mStokeWidth = this.mStokeWidthOut * 3;
                this.normal(0);
            }
        }
    }

    //设置为可以多次下载模式
    public int getMulTimes() {
        return mulTimes;
    }

    public void setMulTimes(int mulTimes) {
        this.mulTimes = mulTimes;
    }

    public int getCurrentTimes() {
        return currentTimes;
    }

    public void setCurrentTimes(int currentTimes) {
        this.currentTimes = currentTimes;
    }

    public static void initStatusString(String[] status) {
        if (status != null && status.length > 0) {
            statusString = status;
        }

    }

    public MyProgressButton.STATE getState() {
        return this.mState;
    }

    private void setState(MyProgressButton.STATE state, boolean anim) {
        if (state == this.mState) {
            if (state == MyProgressButton.STATE.NORMAL) {
                this.setText(this.resultString);
            }
        } else if (this.getWidth() != 0 && !this.morphingCircle && !this.morphingNormal) {
            this.mState = state;
            if (anim) {
                if (this.mState == MyProgressButton.STATE.PROGRESS) {
                    this.morph2Circle();
                } else if (this.mState == MyProgressButton.STATE.NORMAL) {
                    this.morph2Normal();
                }
            } else {
                this.morphingCircle = this.morphingNormal = false;
                if (this.mState == MyProgressButton.STATE.PROGRESS) {
                    this.setText("");
                } else if (this.mState == MyProgressButton.STATE.NORMAL) {
                    this.setText(this.resultString);
                }
                this.setBound(0);
            }

        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mWidth = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
        this.mHeight = this.getHeight() - this.getPaddingTop() - this.getPaddingRight();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w - this.getPaddingLeft() - this.getPaddingRight();
        this.mHeight = h - this.getPaddingTop() - this.getPaddingRight();
        if (this.mState != MyProgressButton.STATE.NORMAL && (this.mState != MyProgressButton.STATE.PROGRESS || !this.morphingCircle)) {
            this.invalidate();
        } else {
            this.setBound(0);
        }

    }

    private void setBound(int padding) {
        if (this.mWidth == 0) {
            this.mWidth = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
        }

        if (this.mHeight == 0) {
            this.mHeight = this.getHeight() - this.getPaddingTop() - this.getPaddingRight();
        }

        this.mBackground.setBounds(this.getPaddingLeft() + padding, this.getPaddingTop(), this.getPaddingLeft() + this.mWidth - padding, this.getPaddingTop() + this.mHeight);
        this.invalidate();
    }

    private void setProgress(int progress) {
        this.mProgress = progress;

        //无变化
        if (!this.morphingCircle && !this.morphingNormal) {
            if (this.mState != MyProgressButton.STATE.PROGRESS) {
                this.mState = MyProgressButton.STATE.PROGRESS;
                this.setText("");
            }

            if (this.mProgress >= this.mMaxProgress) {
                this.mProgress = this.mMaxProgress;
            }

            if (this.mProgress <= 0) {
                this.mProgress = 0;
            }

            this.setBound(0);
            this.invalidate();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mState == MyProgressButton.STATE.NORMAL || this.mState == MyProgressButton.STATE.PROGRESS && this.morphingCircle) {
            this.mBackground.draw(canvas);
        } else if (this.mState == MyProgressButton.STATE.PROGRESS && !this.morphingCircle) {
            if (this.mProgressDrawable == null) {
                int sweepAngle = (this.mWidth - this.mHeight) / 2 + this.getPaddingLeft();
                int size = this.mHeight;
                this.mProgressDrawable = new CProgressDrawable(this.getContext(), size, this.mStokeWidth, this.mStokeWidthOut, this.mStrokeColor);
                this.mProgressDrawable.setBounds(sweepAngle, this.getPaddingTop(), sweepAngle + this.mHeight, this.getPaddingTop() + this.mHeight);
            }

            float sweepAngle1 = 360.0F / (float) this.mMaxProgress * (float) this.mProgress;
            this.mProgressDrawable.setSweepAngle(sweepAngle1);
            this.mProgressDrawable.draw(canvas);
        }

    }

    private void morph2Normal() {
        ObjectAnimator cornerAnimation = ObjectAnimator.ofFloat(this.mBackground, "cornerRadius", new float[]{this.mToCornerRadius, this.mFromCornerRadius});
        int start = (this.mWidth - this.mHeight) / 2;
        ValueAnimator animator = ValueAnimator.ofInt(new int[]{start, 0});
        animator.setDuration(this.mDuration).addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = ((Integer) animation.getAnimatedValue()).intValue();
                MyProgressButton.this.setBound(value);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(this.mDuration);
        animatorSet.playTogether(new Animator[]{animator, cornerAnimation});
        animatorSet.addListener(new AnimatorListener() {
            public void onAnimationStart(Animator animation) {
                MyProgressButton.this.morphingNormal = true;
            }

            public void onAnimationEnd(Animator animation) {
                MyProgressButton.this.morphingNormal = false;
                MyProgressButton.this.setText(MyProgressButton.this.resultString);


            }

            public void onAnimationCancel(Animator animation) {
                MyProgressButton.this.morphingNormal = false;
            }

            public void onAnimationRepeat(Animator animation) {
            }
        });
        animatorSet.start();
    }

    private void morph2Circle() {
        ObjectAnimator cornerAnimation = ObjectAnimator.ofFloat(this.mBackground, "cornerRadius", new float[]{this.mFromCornerRadius, this.mToCornerRadius});
        int end = (this.mWidth - this.mHeight) / 2;
        ValueAnimator animator = ValueAnimator.ofInt(new int[]{0, end});
        animator.setDuration(this.mDuration).addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = ((Integer) animation.getAnimatedValue()).intValue();
                MyProgressButton.this.setBound(value);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(this.mDuration);
        animatorSet.playTogether(new Animator[]{animator, cornerAnimation});
        animatorSet.addListener(new AnimatorListener() {
            public void onAnimationStart(Animator animation) {
                MyProgressButton.this.setText("");
                MyProgressButton.this.morphingCircle = true;
            }

            public void onAnimationEnd(Animator animation) {
                MyProgressButton.this.setText("");
                MyProgressButton.this.morphingCircle = false;
            }

            public void onAnimationCancel(Animator animation) {
                MyProgressButton.this.morphingCircle = false;
            }

            public void onAnimationRepeat(Animator animation) {
            }
        });
        animatorSet.start();
    }

    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        MyProgressButton.SavedState savedState = new MyProgressButton.SavedState(superState);
        savedState.mProgress = this.mProgress;
        savedState.morphingNormal = this.morphingNormal;
        savedState.morphingCircle = this.morphingCircle;
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof MyProgressButton.SavedState) {
            MyProgressButton.SavedState savedState = (MyProgressButton.SavedState) state;
            this.mProgress = savedState.mProgress;
            this.morphingNormal = savedState.morphingNormal;
            this.morphingCircle = savedState.morphingCircle;
            super.onRestoreInstanceState(savedState.getSuperState());
            this.setProgress(this.mProgress);
        } else {
            super.onRestoreInstanceState(state);
        }

    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }

    public void download(int progress) {
        this.setProgress(progress);
    }

    public void normal(int status) {
        this.normal(status, true);
    }

    public void normal(int status, boolean anim) {
        this.resultString = statusString[status];
        this.setState(MyProgressButton.STATE.NORMAL, anim);
    }

    public void startDownLoad() {
        setCurrentTimes(1);
        this.resultString = "";
        this.setState(MyProgressButton.STATE.PROGRESS, true);
    }

     class SavedState extends View.BaseSavedState {
        private boolean morphingNormal;
        private boolean morphingCircle;
        private int     mProgress;
        public  final Creator<MyProgressButton.SavedState> CREATOR = new Creator() {
            public MyProgressButton.SavedState createFromParcel(Parcel in) {
                return new MyProgressButton.SavedState(in);
            }
            public MyProgressButton.SavedState[] newArray(int size) {
                return new MyProgressButton.SavedState[size];
            }
        };

        public SavedState(Parcelable parcel) {
            super(parcel);
        }

        private SavedState(Parcel in) {
            super(in);
            this.mProgress = in.readInt();
            this.morphingCircle = in.readInt() == 1;
            this.morphingNormal = in.readInt() == 1;
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.mProgress);
            out.writeInt(this.morphingNormal ? 1 : 0);
            out.writeInt(this.morphingCircle ? 1 : 0);
        }
    }


    public boolean isMorphingCircle() {
        return morphingCircle;
    }

    public void setMorphingCircle(boolean morphingCircle) {
        this.morphingCircle = morphingCircle;
    }

    public boolean isMorphingNormal() {
        return morphingNormal;
    }

    public void setMorphingNormal(boolean morphingNormal) {
        this.morphingNormal = morphingNormal;
    }



    public  enum STATE {
        PROGRESS,
        NORMAL;

        private STATE() {
        }
    }

   /*

   static class SavedState extends View.BaseSavedState {
        private boolean morphingNormal;
        private boolean morphingCircle;
        private int     mProgress;
        public static final Creator<MyProgressButton.SavedState> CREATOR = new Creator() {
            public MyProgressButton.SavedState createFromParcel(Parcel in) {
                return new MyProgressButton.SavedState(in);
            }

            public MyProgressButton.SavedState[] newArray(int size) {
                return new MyProgressButton.SavedState[size];
            }
        };

        public SavedState(Parcelable parcel) {
            super(parcel);
        }

        private SavedState(Parcel in) {
            super(in);
            this.mProgress = in.readInt();
            this.morphingCircle = in.readInt() == 1;
            this.morphingNormal = in.readInt() == 1;
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.mProgress);
            out.writeInt(this.morphingNormal ? 1 : 0);
            out.writeInt(this.morphingCircle ? 1 : 0);
        }
    }


    public boolean isMorphingCircle() {
        return morphingCircle;
    }

    public void setMorphingCircle(boolean morphingCircle) {
        this.morphingCircle = morphingCircle;
    }

    public boolean isMorphingNormal() {
        return morphingNormal;
    }

    public void setMorphingNormal(boolean morphingNormal) {
        this.morphingNormal = morphingNormal;
    }



    public  enum STATE {
        PROGRESS,
        NORMAL;

        private STATE() {
        }
    }

    原代码
    public static enum STATE {
        PROGRESS,
        NORMAL;

        private STATE() {
        }
    }*/
}


