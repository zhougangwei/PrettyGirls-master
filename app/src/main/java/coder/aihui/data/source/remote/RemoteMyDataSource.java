package coder.aihui.data.source.remote;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import coder.aihui.app.BaseView;
import coder.aihui.base.Content;
import coder.aihui.data.bean.DqxqOutBean;
import coder.aihui.data.bean.gen.DaoSession;
import coder.aihui.data.source.MyDataSource;
import coder.aihui.http.AiHuiLoginServices;
import coder.aihui.http.MyRetrofit;
import coder.aihui.http.WebServiceUtil;
import coder.aihui.ui.main.DownView;
import coder.aihui.util.GsonUtil;
import coder.aihui.util.SPUtil;
import coder.aihui.util.ToastUtil;
import coder.aihui.widget.MyProgressDialog;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static coder.aihui.app.MyApplication.mContext;
import static coder.aihui.ui.main.DownPresenter.COMPANY_DOWN;
import static coder.aihui.ui.main.DownPresenter.PUR_CONTRACT_PLAN_DOWN;
import static coder.aihui.ui.main.DownPresenter.PXGL_SB_DOWN;

/**
 * Created by oracleen on 2016/6/29.
 */
public class RemoteMyDataSource implements MyDataSource {
    private MyProgressDialog  mProgressDialog;
    private LoadDatasCallback mCallBack;

    private DaoSession mDaossion;
    private BaseView   mView;

    private HashMap<String, Integer> mCountHashMap  = new HashMap();
    private HashMap<String, Integer> mTotalsHashMap = new HashMap();


    public RemoteMyDataSource(DownView view, DaoSession mDaossion) {

        this.mDaossion = mDaossion;
        mView = view;

    }

    public RemoteMyDataSource() {
    }

    /**
     * 保存电气的数据
     *
     * @param callback
     */
    public void saveDqDatas(final LoadDatasCallback callback) {
        mCallBack = callback;
        MyRetrofit.getRetrofit()
                .create(AiHuiLoginServices.class)
                .getDatas()
                .compose(mView.<DqxqOutBean>bindToLife())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //  mProgressDialog = ProcessDialogUtil.createNoCancelDialog("正在下载", mActivity);
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObsever<DqxqOutBean>() {
                    @Override
                    public void onNext(DqxqOutBean bean) {
                        mDaossion.getDqxqOutBeanDao().insertOrReplace(bean);
                        // mCallBack.onDatasLoadedProgress(bean);
                    }
                });

    }


    /**
     * @param view     视图层
     * @param entitys  类
     * @param methods  方法
     * @param pars     参数
     * @param callback 回传
     */
    //保存台账的
    public void saveDatas(final BaseView view, final String entitys[], final String methods[], final List<String[]> pars, final LoadDatasCallback callback) {

        for (int k = 0; k < entitys.length; k++) {
            final String url = SPUtil.getString(mContext, Content.WS_ADDRESS, "");
            mCountHashMap.remove(entitys[k]);
            mTotalsHashMap.remove(entitys[k]);

            final ArrayList list = new ArrayList();

            list.add(1);// 第一页
            list.add(1);            //几个
            list.add("");           //pdaId
            if (pars != null && pars.size() > k && pars.get(k) != null && pars.get(k).length > 0) {
                for (String s : pars.get(k)) {
                    list.add(s);
                }
            }
            callback.onLoadingData();  //显示进度圈
            try {
                WebServiceUtil ws = new WebServiceUtil();
                String recode = ws.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url, methods[k], list).get();

                Log.d("RemoteMyDataSource", recode);

                if (recode.startsWith("0")) {
                    ToastUtil.showToast("" + recode);
                    callback.onDataNotAvailable(1 + recode);
                    return;
                }
                JSONObject jsonObject = new JSONObject(recode);
                JSONArray results = jsonObject.getJSONArray("results");
                int mTotals = jsonObject.getInt("totals");

                if (results == null && mTotals == 0) {
                    return;
                }
                mTotalsHashMap.put(entitys[k], mTotals);
            } catch (Exception e) {
                e.printStackTrace();
                callback.onDataNotAvailable(2 + e.getMessage());
            }

            final int finalK = k;
            Observable.create(new Observable.OnSubscribe<JSONArray>() {
                @Override
                public void call(Subscriber<? super JSONArray> subscriber) {
                    try {

                        int mTotals = mTotalsHashMap.get(entitys[finalK]);

                        int downSize = 500;        //每次下载几条
                        int loopSize = mTotals % downSize == 0 ? mTotals
                                / downSize : mTotals / downSize + 1;
                        for (int i = 0; i < loopSize; i++) {
                            WebServiceUtil ws2 = new WebServiceUtil();
                            ArrayList list2 = new ArrayList();
                            list2.add(i + 1);// 第一页
                            list2.add(downSize);            //几个
                            list2.add("");           //pdaId

                            if (pars != null && pars.size() > finalK && pars.get(finalK) != null && pars.get(finalK).length > 0) {
                                for (String s : pars.get(finalK)) {
                                    list2.add(s);
                                }
                            }

                            String recode2 = ws2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url, methods[finalK], list2).get();
                            Log.d("RemoteMyDataSource2", recode2);

                            JSONObject jsonObject = new JSONObject(recode2);
                            JSONArray results = jsonObject.getJSONArray("results");
                            subscriber.onNext(results);
                        }
                        subscriber.onCompleted();
                    } catch (Exception e) {
                        e.printStackTrace();
                        callback.onDataNotAvailable("错误3" + e.getMessage());
                    }
                }
            })
                    .compose(view.<JSONArray>bindToLife())
                    .subscribeOn(Schedulers.io())
                    .filter(new Func1<JSONArray, Boolean>() {
                        @Override
                        public Boolean call(JSONArray jsonArray) {

                            if (jsonArray == null) {
                                callback.onDataNotAvailable("错误4,数组为空");
                            }

                            return jsonArray != null;
                        }
                    })
                    .observeOn(Schedulers.io())
                    .map(new Func1<JSONArray, List>() {
                        @Override
                        public List call(JSONArray jsonArray) {
                            List list = new ArrayList();
                            try {
                                Class entity = Class.forName(entitys[finalK]);
                                list = GsonUtil.parseJsonArray(jsonArray.toString(), entity);
                            } catch (Exception e) {
                                e.printStackTrace();
                                callback.onDataNotAvailable(5 + e.getMessage());
                            }
                            return list;
                        }
                    })
                    .observeOn(Schedulers.io())
                    .filter(new Func1<List, Boolean>() {
                        @Override
                        public Boolean call(List ts) {
                            boolean a = (list != null && list.size() != 0);
                            if (!a) {
                                callback.onDataNotAvailable("错误6,集合为空");
                            }
                            return a;
                        }
                    })
                    .subscribe(new Action1<List>() {
                        @Override
                        public void call(List datas) {
                            //多线程下载保持 每一个 下载的数目 总数都是独立的
                            int mDownNum = mCountHashMap.get(entitys[finalK]) == null ? 0 : mCountHashMap.get(entitys[finalK]);
                            int mTotals = mTotalsHashMap.get(entitys[finalK]) == null ? 0 : mTotalsHashMap.get(entitys[finalK]);

                            callback.onGetData();       //显示获取到数据

                            for (int j = 0; j < datas.size(); j++) {
                                mDaossion.insertOrReplace(datas.get(j));
                                callback.onDatasLoadedProgress(100 * (mDownNum + j) / mTotals);
                            }
                            mDownNum += datas.size();

                            mCountHashMap.put(entitys[finalK], mDownNum);
                            if (mDownNum == mTotals) {
                                callback.onDataFinished();
                            }
                        }
                    });

        }

    }


    /**
     * @param view     视图
     * @param entite   实体类
     * @param method   方法
     * @param callback 回传
     */
    public void saveDatas(DownView view, String[] entite, String[] method, final LoadDatasCallback callback) {
        this.saveDatas(view, entite, method, null, callback);
    }


    /**
     * @param type     下载类型
     * @param callback
     */
    public void saveHttpDatas(Integer type, final LoadDatasCallback callback) {
        getRetrofitObserbe(type).compose(mView.<List>bindToLife())
                .subscribeOn(Schedulers.io())
                .filter(new Func1<List, Boolean>() {
                    @Override
                    public Boolean call(List list) {
                        if (list == null || list.size() == 0) {
                            callback.onDataNotAvailable("下载错误4:数据为空");
                        }
                        return list != null && list.size() != 0;
                    }
                }).observeOn(Schedulers.io())
                .subscribe(new Subscriber<List>() {
                               @Override
                               public void onCompleted() {
                                   callback.onDataFinished();
                               }

                               @Override
                               public void onError(Throwable e) {
                                   e.printStackTrace();
                                   callback.onDataNotAvailable("下载错误3,网络错误" + e.getMessage());
                               }

                               @Override
                               public void onNext(List list) {
                                   try {
                                       for (int i = 0; i < list.size(); i++) {
                                           Log.d("RemoteMyDataSource", "i:" + list.get(i).getClass().getName() + Thread.currentThread());
                                           mDaossion.insertOrReplace(list.get(i));
                                           callback.onDatasLoadedProgress((100 * i / list.size()));
                                       }
                                   } catch (Exception e) {
                                       callback.onDataNotAvailable("下载错误26" + e.getMessage());
                                       e.printStackTrace();
                                   }
                               }
                           }
                );

    }


    Observable getRetrofitObserbe(int type) {
        switch (type) {
            case COMPANY_DOWN:
                return MyRetrofit.getRetrofit()
                        .create(AiHuiLoginServices.class)
                        .getComPanies(4);
            case PUR_CONTRACT_PLAN_DOWN:
                return Observable.mergeDelayError(MyRetrofit.getRetrofit()
                                .create(AiHuiLoginServices.class)
                                .getAzysDatas("2010-10-25", 1), MyRetrofit.getRetrofit()
                                .create(AiHuiLoginServices.class)
                                .getAzysMx());


            case PXGL_SB_DOWN:
                return Observable.mergeDelayError(MyRetrofit.getRetrofit()
                        .create(AiHuiLoginServices.class)
                        .getPpmc(), MyRetrofit.getRetrofit()
                        .create(AiHuiLoginServices.class)
                        .getWzmc());
        }
        return null;

    }


    //抽取
    abstract class BaseObsever<T> implements Observer<T> {

        @Override
        public void onCompleted() {
            //如果进度圈在转就关了
           /* if (mCallBack != null) {
                mCallBack.onDataFinished();
            }
*/
        }

        @Override
        public void onError(Throwable e) {
            if (mCallBack != null) {
                mCallBack.onDataNotAvailable(9 + e.getMessage());
            }
        }

        @Override
        public abstract void onNext(T t);
    }


}
