package coder.aihui.ui.main;

import coder.aihui.base.RxBusPresenter;
import rx.functions.Action1;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/8/7 16:00
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/8/7$
 * @ 更新描述  ${TODO}
 */

public class UpPresenter implements RxBusPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {
        unregisterRxBus();
    }

    @Override
    public <T> void registerRxBus(Class<T> eventType, Action1<T> action) {

    }

    @Override
    public void unregisterRxBus() {
        mRxBus.unSubscribe(this);
    }


}
