package coder.aihui.util;

import android.widget.EditText;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/8/9 16:18
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/8/9$
 * @ 更新描述  ${TODO}
 */

public class EdittextUtil {

    public interface OnSearch {
        void doSearch();
    }

    public static void addSearch(final EditText edittext, final OnSearch onSearch){
       /* edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                *//*判断是否是“GO”键*//*
                if ((event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) || actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE)

                    InputMethodManager imm = (InputMethodManager) edittext
                            .getContext().getSystemService(
                                    Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(
                            v.getApplicationWindowToken(), 0);
                }
                    return onSearch.doSearch();
                return false;
           }
        });*/
    }
}
