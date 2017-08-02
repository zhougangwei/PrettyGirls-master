package coder.aihui.util;

import android.widget.Toast;

import static coder.aihui.app.MyApplication.mContext;


public class ToastTool {

    private static Toast toast;
    //  弹吐司的  因为只有一个对象 可以不断的弹


    public static void showToast(String text) {
        if (toast == null) {
            toast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);//如果不为空，则直接改变当前toast的文本
        }
        toast.show();
    }
}
