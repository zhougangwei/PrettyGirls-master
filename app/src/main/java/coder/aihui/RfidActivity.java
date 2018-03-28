package coder.aihui;

import android.content.Context;
import android.support.annotation.CallSuper;

import coder.aihui.base.AppActivity;
import coder.aihui.rfid.HandHeldAdapter;
import coder.aihui.rfid.PdaAdpter;
import coder.aihui.rfid.PdaView;
import coder.aihui.util.ToastUtil;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/9/6 9:40
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/9/6$
 * @ 更新描述  ${TODO}
 */

public abstract class RfidActivity extends AppActivity implements PdaView {


    private PdaAdpter mPdaAdpter;

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        try {
            int a=1;
            if(a==1){
                mPdaAdpter = new HandHeldAdapter(this);
            }else if(a==2){
                mPdaAdpter =new HandHeldAdapter(this);
            }else{
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @CallSuper
    protected void onPause() {
        super.onPause();
        mPdaAdpter.pause();
    }

    @Override
    @CallSuper
    protected void onStop() {
        mPdaAdpter.stop();
        super.onStop();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        mPdaAdpter.close();
        super.onDestroy();
    }


    @Override
    protected void initView() {

    }

    @Override
    public Context getPdaContext(){
        return  this;
    }


    //protected abstract void initPdaPresenter(HandHeldPdaPresenter mHandHeldPdaPresenter);

    /**
     * 如果想强制自定义的话  就弄成抽象好了
     */
    @Override
    public void pdaStartSearch() {
        ToastUtil.showToast("当前RFID模式开启中.");
    }

    /**
     *  如果想强制自定义的话  就弄成抽象好了
     */
    @Override
    public void pdaStopSearch() {
        ToastUtil.showToast("当前RFID已关闭!");
    }

    @Override
    public abstract void sendMessage(String needMsg);
}
