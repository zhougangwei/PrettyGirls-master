package coder.aihui.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import com.squareup.leakcanary.RefWatcher;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import coder.aihui.app.MyApplication;
import coder.aihui.data.bean.INSPECT_GROUP;
import coder.aihui.data.bean.IN_ASSET;
import coder.aihui.data.bean.PUB_DICTIONARY_ITEM;
import coder.aihui.data.bean.REPAIR_PLACE;
import coder.aihui.data.bean.SYS_DEPT;
import coder.aihui.data.bean.gen.DaoSession;
import coder.aihui.data.bean.gen.INSPECT_GROUPDao;
import coder.aihui.data.bean.gen.IN_ASSETDao;
import coder.aihui.data.bean.gen.PUB_DICTIONARY_ITEMDao;
import coder.aihui.data.bean.gen.REPAIR_PLACEDao;
import coder.aihui.data.bean.gen.SYS_DEPTDao;
import coder.aihui.data.normalbean.DeptBean;
import coder.aihui.util.ToastUtil;
import coder.aihui.widget.jdaddressselector.BottomDialog;
import coder.aihui.widget.jdaddressselector.DataProvider;
import coder.aihui.widget.jdaddressselector.ISelectAble;
import coder.aihui.widget.jdaddressselector.SelectedListener;
import coder.aihui.widget.jdaddressselector.Selector;
import coder.aihui.zxing.decoding.MipcaActivityCapture;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by renlei on 2016/5/23.
 */
public abstract class AppActivity extends BaseActivity implements DlwzView {

    private static final String  LAYOUT_LINEARLAYOUT   = "LinearLayout";
    private static final String  LAYOUT_FRAMELAYOUT    = "FrameLayout";
    private static final String  LAYOUT_RELATIVELAYOUT = "RelativeLayout";
    public static final  Integer BAR_CODE              = 1;
    Unbinder mUnbinder;
    private BottomDialog   mDeptDialog;           //底部弹窗 科室或者地理位子的
    private BottomDialog   mLocationDialog;           //底部弹窗 科室或者地理位子的
    public  DaoSession     mDaoSession;
    public  SQLiteDatabase mDb;

    //获取第一个fragment
    protected abstract BaseFragment getFirstFragment();

    //获取Intent
    protected void handleIntent(Intent intent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        mUnbinder = ButterKnife.bind(this);

     /*   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }*/


        if (null != getIntent()) {
            handleIntent(getIntent());
        }
        //避免重复添加Fragment
        if (null == getSupportFragmentManager().getFragments()) {
            BaseFragment firstFragment = getFirstFragment();
            if (null != firstFragment) {
                addFragment(firstFragment);
            }
        }
        addColorStatus();
        //setImmersionStatus();
        mDaoSession = MyApplication.getIntstance().getDaoSession();
        mDb = MyApplication.getIntstance().getDatabase();


        ActivityManager.getInstance().addActivity(this);

        initView();


    }

    protected abstract void initView();

    //沉浸式
    private void setImmersionStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            //			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }


    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mUnbinder = null;
        RefWatcher refWatcher = MyApplication.getRefWatcher(this);
        refWatcher.watch(this);
        ActivityManager.getInstance().finishActivity(this);


    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        }

        if (name.equals(LAYOUT_LINEARLAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        }

        if (name.equals(LAYOUT_RELATIVELAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }

        if (view != null)
            return view;

        return super.onCreateView(name, context, attrs);
    }


    //获得地理位子
    public void getDept(SelectedListener selectedListener) {

        if (mDeptDialog == null) {
            mDeptDialog = new BottomDialog(AppActivity.this);

            Selector selector = new Selector(this, 3);

            final List<ISelectAble> Dept1 = new ArrayList<ISelectAble>();       //科室
            final List<SYS_DEPT> list = mDaoSession.getSYS_DEPTDao().queryBuilder().where(SYS_DEPTDao.Properties.DEPT_PARENT_ID.eq(0L)).list();

            if (list == null && list.size() == 0) {
                ToastUtil.showToast("请先进行数据初始化!");
                return;
            }

            for (int i = 0; i < list.size(); i++) {
                SYS_DEPT sys_dept = list.get(i);
                DeptBean deptBean = new DeptBean();
                deptBean.id = Integer.parseInt(sys_dept.getDEPT_ID() + "");
                deptBean.name = sys_dept.getDEPT_NAME();
                deptBean.arg = "dept";
                Dept1.add(deptBean);
            }

            if (Dept1.size() == 0) {
                ToastUtil.showToast("当前无数据!");
                return;
            }

            //输入自己的数据
            selector.setDataProvider(new DataProvider() {
                @Override
                public void provideData(int currentDeep, int preId, final DataReceiver receiver) {
                    if (currentDeep == 0) {
                        receiver.send(Dept1);
                    } else if (currentDeep == 1 || currentDeep == 2) {

                        Observable.just(preId).compose(AppActivity.this.<Integer>bindToLife())
                                .subscribeOn(Schedulers.io())
                                .map(new Func1<Integer, List<SYS_DEPT>>() {
                                    @Override
                                    public List<SYS_DEPT> call(Integer integer) {
                                        return mDaoSession.getSYS_DEPTDao().queryBuilder().where(SYS_DEPTDao.Properties.DEPT_PARENT_ID.eq(integer)).list();
                                    }
                                }).map(new Func1<List<SYS_DEPT>, List<ISelectAble>>() {
                            @Override
                            public List<ISelectAble> call(List<SYS_DEPT> sys_depts) {
                                return changeDeptToCity(sys_depts);
                            }
                        })
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Action1<List<ISelectAble>>() {
                                    @Override
                                    public void call(List<ISelectAble> counties) {
                                        receiver.send(counties);
                                    }
                                }, new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {
                                        //给一个空值 进去 触发提前结束
                                        receiver.send(new ArrayList<ISelectAble>());
                                    }
                                });
                    }
                }
            });
            selector.setSelectedListener(selectedListener);
            mDeptDialog.init(this, selector);
            mDeptDialog.setOnAddressSelectedListener(selectedListener);
            mDeptDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    ToastUtil.showToast("111");
                }
            });
        }

        mDeptDialog.show();
    }


    //获得地理位子
    public void getLocation(SelectedListener selectedListener) {

        if (mLocationDialog == null) {
            mLocationDialog = new BottomDialog(AppActivity.this);
            Selector selector = new Selector(this, 3);

            final List<ISelectAble> locationList = new ArrayList<ISelectAble>();       //地理位子

            List<PUB_DICTIONARY_ITEM> diclist = mDaoSession.getPUB_DICTIONARY_ITEMDao().queryBuilder()
                    .where(PUB_DICTIONARY_ITEMDao.Properties.DICT_ID.eq("007114"), PUB_DICTIONARY_ITEMDao.Properties.PARENT_ID.eq(0L)
                    ).list();

            if (diclist == null && diclist.size() == 0) {
                ToastUtil.showToast("请先进行数据初始化!");
                return;
            }

            for (int i = 0; i < diclist.size(); i++) {
                PUB_DICTIONARY_ITEM dlwz = diclist.get(i);
                DeptBean deptBean = new DeptBean();
                deptBean.id = Integer.parseInt(dlwz.getITEM_ID() + "");
                deptBean.name = dlwz.getITEM_NAME();
                deptBean.arg = "location";
                locationList.add(deptBean);
            }

            //输入自己的数据
            selector.setDataProvider(new DataProvider() {
                @Override
                public void provideData(int currentDeep, int preId, final DataReceiver receiver) {
                    if (currentDeep == 0) {
                        receiver.send(locationList);
                    } else if (currentDeep == 1 || currentDeep == 2) {
                        Observable.just(preId).compose(AppActivity.this.<Integer>bindToLife())
                                .subscribeOn(Schedulers.io())
                                .map(new Func1<Integer, List<PUB_DICTIONARY_ITEM>>() {
                                    @Override
                                    public List<PUB_DICTIONARY_ITEM> call(Integer integer) {
                                        return mDaoSession.getPUB_DICTIONARY_ITEMDao().queryBuilder()
                                                .where(PUB_DICTIONARY_ITEMDao.Properties.DICT_ID.eq("007114"), PUB_DICTIONARY_ITEMDao.Properties.PARENT_ID.eq(integer)
                                                ).list();
                                    }
                                }).map(new Func1<List<PUB_DICTIONARY_ITEM>, List<ISelectAble>>() {
                            @Override
                            public List<ISelectAble> call(List<PUB_DICTIONARY_ITEM> locationList) {
                                return changeLocationToCity(locationList);
                            }
                        }).observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Action1<List<ISelectAble>>() {
                                    @Override
                                    public void call(List<ISelectAble> counties) {
                                        receiver.send(counties);

                                    }
                                }, new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {
                                        //给一个空值 进去 触发提前结束
                                        receiver.send(new ArrayList<ISelectAble>());

                                    }
                                });
                    }
                }
            });

            mLocationDialog.init(this, selector);
            mLocationDialog.setOnAddressSelectedListener(selectedListener);
        }

        mLocationDialog.show();
    }


    //转换
    private List<ISelectAble> changeDeptToCity(List<SYS_DEPT> sys_depts) {
        List<ISelectAble> list = new ArrayList<>();

        for (int i = 0; i < sys_depts.size(); i++) {
            SYS_DEPT sys_dept = sys_depts.get(i);
            DeptBean city = new DeptBean();
            city.arg = "dept";
            city.id = Integer.parseInt(sys_dept.getDEPT_ID() + "");
            city.name = sys_dept.getDEPT_NAME();
            list.add(city);
        }
        return list;
    }

    //转换
    private List<ISelectAble> changeLocationToCity(List<PUB_DICTIONARY_ITEM> locatioList) {
        List<ISelectAble> list = new ArrayList<>();

        for (int i = 0; i < locatioList.size(); i++) {
            PUB_DICTIONARY_ITEM sys_dept = locatioList.get(i);
            DeptBean city = new DeptBean();
            city.arg = "location";
            city.id = Integer.parseInt(sys_dept.getITEM_ID() + "");
            city.name = sys_dept.getITEM_NAME();
            list.add(city);
        }
        return list;
    }


    //打开相机
    public void open2Scan() {

        Intent intent = new Intent();
        intent.setClass(this, MipcaActivityCapture.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, Content.SCANNIN_GREQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //从弹出树返回
        //从摄像头回来
        if (requestCode == Content.SCANNIN_GREQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                //显示扫描到的内容
                HashSet<String> epcSet = new HashSet<>();
                epcSet.add(bundle.getString("result"));
                doSaveListData(epcSet, BAR_CODE);
                //处理结束清空
                epcSet.clear();
            }
        }
    }


    //转换下
    public void doSaveListData(Set<String> set, int type) {

        //二维码转换成rfidCode
        HashSet<String> loopSet = new HashSet<>();
        for (String bar : set) {
            if (bar.indexOf("D") > -1) {
                try {
                    PUB_DICTIONARY_ITEM entity = mDaoSession.getPUB_DICTIONARY_ITEMDao().queryBuilder().where(PUB_DICTIONARY_ITEMDao.Properties.DICT_ID.eq("007114"), PUB_DICTIONARY_ITEMDao.Properties.BAR_CODE.like(bar)).unique();
                    if (entity != null) {
                        loopSet.add(entity.getITEM_CODE());
                    }
                } catch (Exception e) {
                    ToastUtil.showToast("该标签可能存在重复的问题");
                }
            } else if (bar.indexOf("B") > -1) {
                List<INSPECT_GROUP> entities = mDaoSession.getINSPECT_GROUPDao().queryBuilder().where(INSPECT_GROUPDao.Properties.INSG_RFID.eq(bar)).list();
                if (entities != null && entities.size() > 0) {
                    loopSet.add(entities.get(0).getINSG_RFID());
                }
            } else {
                REPAIR_PLACE entity1 = mDaoSession.getREPAIR_PLACEDao().queryBuilder().where(REPAIR_PLACEDao.Properties.PLACE_BAR_CODE.eq(bar)).unique();
                if (entity1 != null) {
                    loopSet.add(entity1.getPLACE_RFID_CODE());
                    continue;
                }
                IN_ASSET entity2 = mDaoSession.getIN_ASSETDao().queryBuilder().where(IN_ASSETDao.Properties.BAR_CODE.eq(bar)).unique();
                if (entity2 != null) {
                    loopSet.add(entity2.getRFID_CODE());
                    continue;
                }
            }
        }

        if (loopSet.size() == 0) {
            ToastUtil.showToast("没有查询到相关信息，请初始化台账、巡检点数据");
            return;
        }
        doSaveListData(loopSet);
    }

    public void doSaveListData(Set<String> set) {

    }


    public void closeDiaLog() {
        if (mLocationDialog != null) {
            mLocationDialog.dismiss();
        }
        if (mDeptDialog != null) {
            mDeptDialog.dismiss();
        }
    }


}
