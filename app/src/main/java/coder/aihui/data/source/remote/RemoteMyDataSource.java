package coder.aihui.data.source.remote;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.blankj.utilcode.utils.TimeUtils;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import coder.aihui.app.BaseView;
import coder.aihui.base.Content;
import coder.aihui.data.bean.DqxqOutBean;
import coder.aihui.data.bean.gen.DaoSession;
import coder.aihui.data.source.MyDataSource;
import coder.aihui.http.AiHuiLoginServices;
import coder.aihui.http.MyRetrofit;
import coder.aihui.http.WebServiceUtil;
import coder.aihui.ui.main.DownLoadBean;
import coder.aihui.ui.main.DownView;
import coder.aihui.ui.main.UpBean;
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

import static coder.aihui.app.MyApplication.daoSession;
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
     * @param bean     封装的下载对象
     * @param callback 回调显示视图
     */
    //保存台账的
    public void saveDatas(DownLoadBean bean, final LoadDatasCallback callback) {
        for (int k = 0; k < bean.getEnties().length; k++) {
            final String[] entitys = bean.getEnties();
            final List<String[]> pars = bean.getPars();
            final String[] methods = bean.getMethods();
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
                            if (mTotals == 0) {
                                callback.onDataFinished();
                                return;
                            }
                            for (int j = 0; j < datas.size(); j++) {
                                mDaossion.insertOrReplace(datas.get(j));
                                callback.onDatasLoadedProgress(100 * (mDownNum + j + 1) / mTotals);
                            }
                            mDownNum += datas.size();

                            mCountHashMap.put(entitys[finalK], mDownNum);
                            if (mDownNum >= mTotals) {
                                callback.onDataFinished();
                            }

                        }
                    });
        }

    }


    /**
     * @param upDatas  //封装的集合
     * @param callback 接口回调渲染视图
     */
    public void gotoUp(List<UpBean> upDatas, final LoadDatasCallback callback) {
        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        final String imei = telephonyManager.getDeviceId();//机器的IMEI

        final String wsAddress = SPUtil.getWsAddress(mContext);
        //找到所有未上传的数据
        Observable.from(upDatas)
                .concatMap(new Func1<UpBean, Observable<StringListBean>>() {
                    @Override
                    public Observable<StringListBean> call(UpBean upBean) {
                        return getUpdatas(upBean);          //获取未上传的数据
                    }
                }).filter(new Func1<StringListBean, Boolean>() {
            @Override
            public Boolean call(StringListBean bean) {
                return bean.datas != null && bean.datas.size() != 0;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<StringListBean>() {
                    @Override
                    public void call(StringListBean bean) {
                        int updateSize = 20;
                        List list = bean.datas;
                        try {
                            if (list.size() != 0) {
                                for (int i = 0; i < list.size(); i = i + updateSize) {
                                    List list1 = list.subList(i, (list.size()) > updateSize ? updateSize : (list.size()));
                                    WebServiceUtil ws = new WebServiceUtil();
                                    String recode = ws.execute(wsAddress, bean.method, list1).get();
                                    JSONObject resJson = new JSONObject(recode);

                                    if (resJson.getLong("recode") != 0) {// 返回码不等于
                                        callback.onDatasLoadedProgress(i);
                                    }
                                }
                                gotoChangeFlag(bean.upBean, list);
                                callback.onDataFinished();
                            } else {
                                callback.onDataFinished();
                            }
                        } catch (Exception e) {
                            callback.onDataNotAvailable(e.getMessage());
                        }
                    }
                });
    }

    private void gotoChangeFlag(UpBean upBean, List list) {

        try {
            String enties = upBean.getEnties();
            Class clazz = Class.forName(enties);
            Method m2 = clazz.getDeclaredMethod("setSYNC_DATE", Date.class);
            Method m3 = clazz.getDeclaredMethod("setSYNC_FLAG", Integer.class);
            for (int i = 0; i < list.size(); i++) {
                m2.invoke(list.get(i), new Date());
                m3.invoke(list.get(i), 1);
            }
            mDaossion.getDao(clazz).insertOrReplaceInTx(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Nullable
    private synchronized Observable<StringListBean> getUpdatas(final UpBean upBean) {
        return Observable.create(new Observable.OnSubscribe<StringListBean>() {
            @Override
            public void call(Subscriber<? super StringListBean> subscriber) {
                try {
                    String entity = upBean.getEnties();
                    String method = upBean.getMethods();
                    WhereCondition[] whereconditions1 = upBean.getWhereconditions();
                    Property[] propertie = upBean.getPropertie();
                    Class cls = Class.forName(entity);
                    QueryBuilder qb = daoSession.queryBuilder(cls);
                    if (whereconditions1 != null) {
                        for (WhereCondition whereCondition : whereconditions1) {
                            qb.where(whereCondition);
                        }
                    }
                    if (propertie != null && propertie.length != 0) {
                        qb.orderAsc(propertie);
                    }
                    List list = qb.list();
                    StringListBean stringListBean = new StringListBean();
                    stringListBean.method = method;
                    stringListBean.datas = list;
                    stringListBean.upBean = upBean;
                    subscriber.onNext(stringListBean);
                } catch (Exception e) {
                    subscriber.onNext(null);
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());

    }


    class StringListBean {
        String method;
        List   datas;
        UpBean upBean;
    }


    /**
     * @param view     视图
     * @param entite   实体类
     * @param method   方法
     * @param callback 回传
     */

  /*  public void saveDatas(DownView view, String[] entite, String[] method, final LoadDatasCallback callback) {
        this.saveDatas(view, entite, method, null, callback);
    }
*/

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

    public void gotoUpJson(Integer type, Map<String, String> jsonMap, final LoadDatasCallback callback) {

        MyRetrofit.getRetrofit()
                .create(AiHuiLoginServices.class)
                .upLoadPurPlan(jsonMap)
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        callback.onDataFinished();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDataNotAvailable(e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("RemoteMyDataSource", s);
                    }
                });


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
                        .getAzysDatas(getMaxAzystime(), 1), MyRetrofit.getRetrofit()
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


    //获取本地数据库最大时间
    private String getMaxAzystime() {

        long createDate = 0;
        Database db = mDaossion.getDatabase();
        String sql = "select max(CREATE__TIME) as CREATE__TIME from PUR__CONTRACT__PLAN ";
        Cursor cursor = db.rawQuery(sql, new String[]{});
        cursor.moveToFirst();
        while (cursor.getPosition() != cursor.getCount()) {
            createDate = cursor.getLong(0);
            cursor.moveToNext();
        }
        if (createDate != 0) {
            return TimeUtils.milliseconds2String(createDate);
        }else{
            return "2010-10-25 12:00:00";
        }
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
