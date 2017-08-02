package coder.aihui.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/3/29 9:06
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/3/29$
 * @ 更新描述  ${TODO}
 */

public class DownLoadService extends Service {

    private final IBinder binder = new MyBinder();

    public class MyBinder extends Binder {
        public DownLoadService GetService() {
            return DownLoadService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
