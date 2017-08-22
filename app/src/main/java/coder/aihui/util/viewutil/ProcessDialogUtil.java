
package coder.aihui.util.viewutil;

import android.app.Activity;
import android.app.ProgressDialog;

import coder.aihui.widget.MyProgressDialog;


/**
 * @ 创建者   zhou
 * @ 创建时间   2016/12/8 11:00
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2016/12/8$
 * @ 更新描述  ${TODO}
 */


public class ProcessDialogUtil {

    public static MyProgressDialog createNoCancelDialog(String message, Activity activity) {
        MyProgressDialog  mProgressDialog = new MyProgressDialog(activity);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setMessage(message);
        mProgressDialog.setProgress(0);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setCancelable(false);
        return mProgressDialog;

    }

}

