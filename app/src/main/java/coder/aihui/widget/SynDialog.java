package coder.aihui.widget;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/2/25 13:58
 * @ 描述    ${会阻塞线程的Dialog}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/2/25$
 * @ 更新描述  ${TODO}
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public abstract class SynDialog extends Dialog {

    private Handler mHandler;
    protected Object result;

    public SynDialog(Context context){
        super(context);
        onCreate();
    }

    public abstract void onCreate();

    /**
     * 结束对话框，将触发返回result对象
     */
    public void finishDialog(){
        dismiss();
        mHandler.sendEmptyMessage(0);
    }

    static class SynHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            throw new RuntimeException();
        }
    }

    /**
     * 显示同步对话框
     * @return 返回result对象
     */
    public Object showDialog() {
        super.show();
        try {
            Looper.getMainLooper();
            mHandler = new SynHandler();
            Looper.loop();
        } catch (Exception e) {
        }
        return result;
    }
}
