package coder.aihui.base;

import coder.aihui.rxbus.RxBus;
import rx.functions.Action1;

/**
 * Created by long on 2016/9/2.
 * RxBus Presenter
 */
public interface RxBusPresenter extends IBasePresenter {

    public RxBus mRxBus = RxBus.getInstance();

    /**
     * 注册
     * @param eventType
     * @param <T>
     */




    <T> void registerRxBus(Class<T> eventType, Action1<T> action);

    /**
     * 注销
     */
    void unregisterRxBus();


}
