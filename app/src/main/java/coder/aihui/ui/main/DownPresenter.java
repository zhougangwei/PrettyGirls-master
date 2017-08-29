package coder.aihui.ui.main;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import coder.aihui.base.RxBusPresenter;
import coder.aihui.data.bean.DownLoadBean;
import coder.aihui.data.bean.gen.DaoSession;
import coder.aihui.data.source.MyDataSource;
import coder.aihui.data.source.remote.RemoteMyDataSource;
import coder.aihui.http.WebService2000Util;
import coder.aihui.rxbus.RxBus;
import coder.aihui.util.AndroidUtils;
import coder.aihui.util.LogUtil;
import coder.aihui.util.SPUtil;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static coder.aihui.app.MyApplication.mContext;

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
    private       DaoSession            mDaoSession;

    public static final int ASSET_DOWN   = 1;         //台账
    public static final int INIT_DOWN    = 0;         //初始化
    public static final int COMPANY_DOWN = 2;         //下载公司

    public static final int INSPECT_PLAN_DOWN        = 3;         //下载巡检计划
    public static final int INSPECT_INIT_DOWN        = 4;         //下载巡检初始化
    public static final int INSPECT_TEMPLETITEM_DOWN = 5;         //下载巡检模板
    public static final int AZYS_DOWN                = 6;         //下载安装验收
    public static final int PXGL_SB_DOWN             = 7;         //下载培训管理设备

    public static final int PUR_CONTRACT_PLAN_UP = 8;         //上传安装验收
    public static final int INSPECT_UP           = 9;         //上传巡检数据

    public static final int INSPECT_PM_PLAN_DOWN        = 10;         //下载PM计划
    public static final int INSPECT_PM_INIT_DOWN        = 11;         //下载PM初始化
    public static final int INSPECT_PM_TEMPLETITEM_DOWN = 12;         //下载PM模板


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
        this.mDaoSession = mDaossion;
    }


    @Override
    public void onStart() {

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
        if (mRxBus != null) {
            mRxBus.unSubscribe(this);
        }

    }


    //下载初始化


    /**
     * 参数在这添加 因为获取参数很多时候是耗时操作
     *
     * @param bean 下载封装对象
     */
    //下载初始化
    public void gotoDown(final DownLoadBean bean) {


        Observable.create(new Observable.OnSubscribe<DownLoadBean>() {
            @Override
            public void call(final Subscriber<? super DownLoadBean> subscriber) {
                getPars(bean).map(new Func1<DownLoadBean, DownLoadBean>() {
                    @Override
                    public DownLoadBean call(DownLoadBean downLoadBean) {

                        for (int i = 0; i < downLoadBean.getEnties().length; i++) {
                            String s = downLoadBean.getEnties()[i];
                            String methods = downLoadBean.getMethods()[i];

                            List<String[]> pars = downLoadBean.getPars();
                            List<String[]> parsNext = new ArrayList<String[]>();

                            if (pars != null && pars.size() > i && pars.get(i) != null && pars.get(i).length > 0) {
                                String[] strings = downLoadBean.getPars().get(i);
                                parsNext.add(strings);
                            }
                            DownLoadBean downLoadBean1 = new DownLoadBean();
                            downLoadBean1.setEnties(new String[]{s});
                            downLoadBean1.setType(downLoadBean.getType());
                            downLoadBean1.setBigType(downLoadBean.getBigType());
                            downLoadBean1.setMethods(new String[]{methods});
                            downLoadBean1.setPars(parsNext);
                            subscriber.onNext(downLoadBean1);
                        }
                        subscriber.onCompleted();
                        return null;
                    }
                }).subscribe();

            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Action1<DownLoadBean>() {
                    @Override
                    public void call(final DownLoadBean mBean) {
                        mRemoteMyDataSource.saveDatas(mBean, new MyLoadDatasCallback(mBean.getType()) {
                            @Override
                            public void onDatasLoadedProgress(int index, String enties) {

                                //需要一个记录上一个 传的 只有在上一个传到100后才变身!
                                if (TextUtils.isEmpty(mLastEntie)) {
                                    mLastEntie = enties;
                                } else if (index == 100) {
                                    mLastEntie = "";
                                } else if (!mLastEntie.equals(enties)) {
                                    return;
                                }
                                Log.d("DownPresenter", "index+mBean.getType():" + (index + ":" + enties) + "(" + Thread.currentThread().getName());
                                mView.showProgress(index, mBean.getType());

                            }
                        });
                    }
                });
    }

    String mLastEntie = "";


    /**
     * 根据不同的获得参数
     *
     * @param bean 封装的Bean 数据有点多 以后有时间改成builder 看看有没有必要
     * @return
     */
    private Observable<DownLoadBean> getPars(final DownLoadBean bean) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                //先获取库房id
                String userId = SPUtil.getUserId(mContext);
                String wsAddress = SPUtil.getWsAddress(mContext);
                List list1 = new ArrayList();
                list1.add(userId);
                WebService2000Util ws2 = new WebService2000Util(new WebService2000Util.OnCallBack() {
                    @Override
                    public void callBack(String result) {
                        subscriber.onNext(result);
                        subscriber.onCompleted();
                    }
                });
                ws2.execute(wsAddress, "getHrpModuleIdByUserIs", list1);
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String kfid) {
                        boolean b = TextUtils.isEmpty(kfid) || kfid.startsWith("0$");
                        if (b) {
                            mView.showFault(bean.type, "没有网络");
                        }
                        return !b;
                    }
                }).observeOn(Schedulers.io())
                .map(new Func1<String, DownLoadBean>() {
                    @Override
                    public DownLoadBean call(String kfid) {
                        List<String[]> pars0List = new ArrayList<>();
                        switch (bean.type) {
                            case INIT_DOWN:
                                final String[] pars0 = new String[]{"007083"};
                                pars0List.add(pars0);
                                break;
                            case ASSET_DOWN:
                                String userId = SPUtil.getString(mContext, "userId", "1");
                                final String[] pars = new String[]{userId};
                                pars0List.add(pars);
                                break;
                            case INSPECT_PLAN_DOWN:
                                pars0List.add(new String[]{"XJ", AndroidUtils.getPlanPras("XJ", kfid, mDaoSession)});
                                break;
                            case INSPECT_INIT_DOWN:
                                String[] pars2 = new String[]{"007119,007120"};
                                pars0List.add(pars2);
                                break;
                            case INSPECT_TEMPLETITEM_DOWN:
                                //参数
                                pars0List.add(new String[]{"XJ", AndroidUtils.getInspectTemPars("XJ", kfid, mDaoSession)});
                                break;
                            case INSPECT_PM_PLAN_DOWN:
                                pars0List.add(new String[]{"PM", AndroidUtils.getPlanPras("PM", kfid, mDaoSession)});
                                break;
                            case INSPECT_PM_TEMPLETITEM_DOWN:
                                pars0List.add(new String[]{"PM", AndroidUtils.getInspectTemPars("PM", kfid, mDaoSession)});
                                break;
                            case INSPECT_PM_INIT_DOWN:
                                String[] pars3 = new String[]{"007119,007120"};
                                pars0List.add(pars3);
                                break;
                        }
                        bean.pars = pars0List;
                        return bean;
                    }
                });

    }


    public void gotoDown(final Integer type) {

        mRemoteMyDataSource.saveHttpDatas(type, new MyLoadDatasCallback(type) {
            @Override
            public void onDatasLoadedProgress(int index, String enties) {
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
        mDaoSession.getDao(entie).deleteAll();
    }

    //上传数据http
    public void gotoUp(Map map, final Integer type) {
        mRemoteMyDataSource.gotoUpJson(type, map, new MyLoadDatasCallback(PUR_CONTRACT_PLAN_UP) {
            @Override
            public void onDatasLoadedProgress(int index, String enties) {
                mView.showProgress(index, type);
            }
        });
    }

    public void gotoUp(List<UpBean> list, final Integer type) {
        mRemoteMyDataSource.gotoUp(list, new MyLoadDatasCallback(type) {
            @Override
            public void onDatasLoadedProgress(int index, String enties) {
                mView.showProgress(index, type);
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
