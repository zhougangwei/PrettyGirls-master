package coder.aihui.rfid;

import android.support.annotation.Nullable;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/9/5 16:36
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/9/5$
 * @ 更新描述  ${TODO}
 */

public class HandHeldPdaPresenter implements PdaMachine {


    PdaView            mPdaView;
    KeyListener        mKeyListener;
    PdaAdpter          mPdaAdpter;



    @Nullable
    public void setAdapter( PdaAdpter pdaAdpter){
        mPdaAdpter =pdaAdpter;
   }

    public HandHeldPdaPresenter (PdaView pdaView){
        mPdaView=pdaView;
    }






    public interface KeyListener {
        void keyUp();

        void keyDown();
    }






}
