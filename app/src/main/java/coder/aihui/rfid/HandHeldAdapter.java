package coder.aihui.rfid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.KeyEvent;

import com.BRMicro.Tools;
import com.handheld.uhfr.UHFRManager;
import com.uhf.api.cls.Reader;

import java.util.List;

import coder.aihui.util.AndroidUtils;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/9/6 10:03
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/9/6$
 * @ 更新描述  ${TODO}
 */

public class HandHeldAdapter implements PdaAdpter {
    private UHFRManager mUhfrManager;
    Context mContext;
    PdaView mPdaView;
    private long    startTime  = 0;
    private boolean keyUpFalg  = true;
    private boolean keyControl = true;
    private boolean isRunning  = false;
    private boolean isStart    = false;      // 汉德清点机
    private long    lastTime   = 0L;

    public HandHeldAdapter(PdaView pdaView) {
        mPdaView = pdaView;
        mContext = pdaView.getPdaContext();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.rfid.FUN_KEY");
        mContext.registerReceiver(keyReceiver, filter);
    }

    public HandHeldAdapter registerDefultKeyReciver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.rfid.FUN_KEY");
        mContext.registerReceiver(keyReceiver, filter);
        return this;
    }

    /**
     * @param receiver 自定义按键的广播接受者
     */
    public HandHeldAdapter registerKeyReciver(BroadcastReceiver receiver) {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.rfid.FUN_KEY");
        mContext.registerReceiver(receiver, filter);

        return this;
    }

    /**
     * 谁来显示视图
     */
  /*  public HandHeldAdapter setUiView(PdaView view) {
        mPdaView = view;
        return this;
    }*/


    private BroadcastReceiver keyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int keyCode = intent.getIntExtra("keyCode", 0);
            if (keyCode == 0) {//H941
                keyCode = intent.getIntExtra("keycode", 0);
            }
            boolean keyDown = intent.getBooleanExtra("keydown", false);
            if (keyUpFalg && keyDown && System.currentTimeMillis() - startTime > 500) {
                startTime = System.currentTimeMillis();
                keyUpFalg = false;

                if ((keyCode == KeyEvent.KEYCODE_F1 || keyCode == KeyEvent.KEYCODE_F2
                        || keyCode == KeyEvent.KEYCODE_F3 || keyCode == KeyEvent.KEYCODE_F4 ||
                        keyCode == KeyEvent.KEYCODE_F5)) {
                    runInventory();
                    return;
                } else if (keyDown) {
                    startTime = System.currentTimeMillis();
                } else {
                    keyUpFalg = true;
                }
            }
        }

    };

    public void runInventory() {
        if (keyControl) {
            keyControl = false;
            if (!isStart) {
                isRunning = true;
                mUhfrManager.setCancleFastMode();
                new Thread(inventoryTask).start();
                isStart = true;
            } else {
                mPdaView.pdaStopSearch();
                mUhfrManager.stopTagInventory();
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                isRunning = false;
                isStart = false;
            }
            keyControl = true;
        }
    }

    private Runnable inventoryTask = new Runnable() {
        @Override
        public void run() {
            while (isRunning) {
                List<Reader.TAGINFO> mList1 = mUhfrManager.tagInventoryByTimer((short) 50);
                mPdaView.pdaStartSearch();
                if (mList1 != null && mList1.size() > 0) {
                    if (System.currentTimeMillis() - lastTime > 100) {
                        lastTime = System.currentTimeMillis();
                        Util.play(1, 0);
                    }
                    for (Reader.TAGINFO tfs : mList1) {
                        byte[] epcdata = tfs.EpcId;
                        //将字节转成String
                        String epc = Tools.Bytes2HexString(epcdata, epcdata.length);
                        String needTag = AndroidUtils.getStringNoBlank(epc);//先得到没有空格的17位
                        String needMsg = "";
                        needMsg = AndroidUtils.getRfidString(needTag);
                        mPdaView.sendMessage(needMsg);
                    }
                }
            }
        }
    };

    @Override
    public void close() {
        mUhfrManager.close();
        mUhfrManager = null;
    }

    @Override
    public void stop() {
        mContext.unregisterReceiver(keyReceiver);
    }

    @Override
    public void pause() {
        if (mUhfrManager != null) {//close uhf module
            mUhfrManager.close();
            mUhfrManager = null;
        }
    }

    @Override
    public void start() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.rfid.FUN_KEY");
        mContext.registerReceiver(keyReceiver, filter);

    }

    @Override
    public void onCreate() {
        mUhfrManager = UHFRManager.getIntance();
        if (mUhfrManager != null) {
            mUhfrManager.setPower(30, 10);
            mUhfrManager.setRegion(Reader.Region_Conf.valueOf(1));
            //showToast("Init uhf success！");
        } else {
            //showToast("Init uhf onFail！");
        }
    }
}
