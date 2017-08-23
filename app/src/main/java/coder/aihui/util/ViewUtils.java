package coder.aihui.util;

import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/8/1 10:13
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/8/1$
 * @ 更新描述  ${TODO}
 */

public class ViewUtils {
    //可以取消的RadioButton
    public static void canCancelRadioButton(final RadioButton rbJlzmYes) {
        rbJlzmYes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (rbJlzmYes.isChecked()) {
                        rbJlzmYes.setChecked(false);
                    } else {
                        rbJlzmYes.setChecked(true);
                    }
                }
                return true;
            }
        });
    }

    public static void canCancelRadioButton(final RadioButton rbJlzmYes, final onBackResult backResult) {
        rbJlzmYes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (rbJlzmYes.isChecked()) {
                        rbJlzmYes.setChecked(false);
                        if (backResult != null) {
                            backResult.backResult(false);
                        }
                    } else {
                        rbJlzmYes.setChecked(true);
                        if (backResult != null) {
                            backResult.backResult(true);
                        }
                    }
                }
                return true;
            }
        });
    }

    public interface onBackResult {
        void backResult(boolean b);
    }
}
