package coder.aihui.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * @ 创建者   zhou
 * @ 创建时间   2016/11/24 11:20
 * @ 描述    ${自定义的一个ProgressDialog  实现可以在子线程中直接更换message}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2016/11/24$
 * @ 更新描述  ${TODO}
 */

public class MyProgressDialog extends ProgressDialog{

    private Handler mHandler =new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setMessage(msg.getData().getString("Message"));

        }
    };
    public MyProgressDialog(Context context) {
        super(context);
        setIndeterminate(false);
        setCancelable(false);
        setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

    }

    public void setMyMessage(String str){
        Message message = mHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putString("Message",str);
        message.setData(bundle);
        mHandler.sendMessage(message);
    }


}
