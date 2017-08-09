package coder.aihui.ui.main;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.List;
import java.util.Map;

import coder.aihui.base.RxBusPresenter;
import coder.aihui.data.bean.gen.DaoSession;
import coder.aihui.data.source.MyDataSource;
import coder.aihui.data.source.remote.RemoteMyDataSource;
import coder.aihui.rxbus.RxBus;
import coder.aihui.rxbus.event.MainEvent;
import coder.aihui.util.LogUtil;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/3/20 16:47
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/3/20$
 * @ 更新描述  ${TODO}
 */


public class DownPresenter implements RxBusPresenter {
    private       CompositeSubscription mCompositeSubscription;
    private       DownView              mView;
    private final RemoteMyDataSource    mRemoteMyDataSource;
    private       DaoSession            mDaossion;

    public static final int ASSET_DOWN               = 1;         //台账
    public static final int INIT_DOWN                = 0;         //初始化
    public static final int COMPANY_DOWN             = 2;         //下载公司
    public static final int INSPECT_PLAN_DOWN        = 3;         //下载巡检计划
    public static final int INSPECT_INIT_DOWN        = 4;         //下载巡检初始化
    public static final int INSPECT_TEMPLETITEM_DOWN = 5;         //下载巡检模板
    public static final int PUR_CONTRACT_PLAN_DOWN   = 6;         //下载安装验收
    public static final int PXGL_SB_DOWN             = 7;         //下载培训管理设备
    public static final int PUR_CONTRACT_PLAN_UP     = 8;         //上传安装验收

    public static final int SYS_USER_DOWN = 1001;         //下载人员


    public static final int WEB_SERVICE = 1;        //用webservie下载
    public static final int HTTP        = 2;               //用http下载


    //下载成功或者失败的信号
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            int type = data.getInt("Type");
            String wrong = data.getString("wrong");
            switch (msg.what) {
                case 1: //失败
                    mView.showFault(type, wrong);
                    // mView.dismissProgress();
                    break;
                case 2://结束
                    mView.showSuccess(type);
                    //mView.dismissProgress();
            }

        }
    };

    public DownPresenter(DownView view, DaoSession mDaossion) {
        mView = view;
        mRemoteMyDataSource = new RemoteMyDataSource(mView, mDaossion);
        this.mDaossion = mDaossion;
    }


    @Override
    public void onStart() {
        RxBus.getInstance().post(new MainEvent());
    }

    @Override
    public void onDestroy() {
        unregisterRxBus();
    }

    @Override
    public <T> void registerRxBus(Class<T> eventType, Action1<T> action) {
        Subscription subscription = mRxBus.doSubscribe(eventType, action, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtil.e(throwable.toString());
            }
        });
        //用于注销
        RxBus.getInstance().addSubscription(this, subscription);
    }

    @Override
    public void unregisterRxBus() {
        mRxBus.unSubscribe(this);
    }


    //下载初始化


    /**
     * @param <></>   泛型
     * @param methods webservice下载的方法
     * @param enties  下载的类是哪个
     * @param type    是哪种下载 台账下载初始化下载...
     * @param way     哪种方式 http和webservice
     */
    //下载初始化
    public void gotoDown(String[] methods, String[] enties, List<String[]> pars, final Integer type, final Integer way) {
        if (way == WEB_SERVICE) {
            mRemoteMyDataSource.saveDatas(mView, enties, methods, pars, new MyLoadDatasCallback(type) {
                @Override
                public void onDatasLoadedProgress(int index) {
                    Observable.just(index)
                            .compose(mView.<Integer>bindToLife())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<Integer>() {
                                @Override
                                public void call(Integer integer) {
                                    mView.showProgress(integer, type);
                                }
                            });
                }
            });
        } else if (way == HTTP) {
         /*   mRemoteMyDataSource.saveHttpDatas(getRetrofitObserbe(type), new MyLoadDatasCallback(type) {
                @Override
                public void onDatasLoadedProgress(int index) {
                    Observable.just(index).compose(mView.<Integer>bindToLife())
                            .compose(mView.<Integer>bindToLife())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<Integer>() {
                                @Override
                                public void call(Integer integer) {
                                    mView.showProgress(integer, type);
                                }
                            });
                }
            });*/
        }
    }

    public void gotoDown(final Integer type) {

        mRemoteMyDataSource.saveHttpDatas(type, new MyLoadDatasCallback(type) {
            @Override
            public void onDatasLoadedProgress(int index) {
                Observable.just(index).compose(mView.<Integer>bindToLife())
                        .compose(mView.<Integer>bindToLife())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                mView.showProgress(integer, type);
                            }
                        });
            }
        });
    }


    //清空台账数据
    public void clearData(Class entie) {
        mDaossion.getDao(entie).deleteAll();
    }

    //上传数据
    public void gotoUp(Map map) {
        mRemoteMyDataSource.gotoUpJson(PUR_CONTRACT_PLAN_UP, map, new MyLoadDatasCallback(PUR_CONTRACT_PLAN_UP) {
            @Override
            public void onDatasLoadedProgress(int index) {

            }
        });
    }

    //简单抽取失败了的和结束了的
    public abstract class MyLoadDatasCallback implements MyDataSource.LoadDatasCallback {

        private int mType;

        public MyLoadDatasCallback(int type) {
            mType = type;
        }

        @Override
        public void onDataNotAvailable(String wrong) {
            Message message = mHandler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putInt("Type", mType);
            bundle.putString("wrong", wrong);
            message.setData(bundle);
            message.what = 1;
            mHandler.sendMessage(message);
        }

        @Override
        public void onDataFinished() {
            Message message = mHandler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putInt("Type", mType);
            message.setData(bundle);
            message.what = 2;
            mHandler.sendMessage(message);
        }

        @Override
        public void onGetData() {
            //mView.closeLoading(mType);
        }

        @Override
        public void onLoadingData() {
            //mView.showLoading(mType);
        }

    }
}
