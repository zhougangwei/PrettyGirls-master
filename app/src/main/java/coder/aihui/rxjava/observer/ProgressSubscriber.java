package coder.aihui.rxjava.observer;

import android.content.Context;
import android.widget.Toast;

import rx.Subscriber;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/3/22 11:44
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/3/22$
 * @ 更新描述  ${TODO}
 */

public class ProgressSubscriber<K> extends Subscriber<K> {


    private SubscriberOnNextListener mSubscriberOnNextListener;
    private Context                  context;

    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onCompleted() {
        Toast.makeText(context, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(context, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNext(K t) {
        mSubscriberOnNextListener.onNext(t);
    }
}

interface SubscriberOnNextListener<T> {
    void onNext(T t);
}
