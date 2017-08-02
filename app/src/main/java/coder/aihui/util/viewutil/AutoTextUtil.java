package coder.aihui.util.viewutil;

import android.app.Activity;
import android.view.View;
import android.widget.AutoCompleteTextView;

import coder.aihui.widget.MyArrayAdapter;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/3/17 9:31
 * @ 描述    ${实现自动模糊搜索}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/3/17$
 * @ 更新描述  ${TODO}
 */

public class AutoTextUtil {
    public static void createAutoText(final AutoCompleteTextView autoCompleteTextView, Activity activity , MyArrayAdapter adapter) {
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteTextView.showDropDown();
            }
        });
    }
}

