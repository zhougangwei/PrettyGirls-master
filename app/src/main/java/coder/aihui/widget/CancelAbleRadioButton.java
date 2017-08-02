package coder.aihui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/5/2 19:24
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/5/2$
 * @ 更新描述  ${TODO}
 */

public class CancelAbleRadioButton extends RadioButton {


    public CancelAbleRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (((CancelAbleRadioButton) v).isChecked()) {
                    ((CancelAbleRadioButton) v).setChecked(false);
                }
                return false;
            }
        });
    }


}
