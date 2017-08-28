package coder.aihui.base;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import com.squareup.leakcanary.RefWatcher;
import com.taobao.sophix.SophixManager;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import coder.aihui.R;
import coder.aihui.app.MyApplication;
import coder.aihui.data.bean.INSPECT_GROUP;
import coder.aihui.data.bean.IN_ASSET;
import coder.aihui.data.bean.PUB_DICTIONARY_ITEM;
import coder.aihui.data.bean.REPAIR_PLACE;
import coder.aihui.data.bean.gen.DaoSession;
import coder.aihui.data.bean.gen.INSPECT_GROUPDao;
import coder.aihui.data.bean.gen.IN_ASSETDao;
import coder.aihui.data.bean.gen.PUB_DICTIONARY_ITEMDao;
import coder.aihui.data.bean.gen.REPAIR_PLACEDao;
import coder.aihui.util.ToastUtil;
import coder.aihui.zxing.decoding.MipcaActivityCapture;

import static coder.aihui.app.MyApplication.IsNeedRestart;
import static coder.aihui.app.MyApplication.isActive;

/**
 * Created by renlei on 2016/5/23.
 */
public abstract class AppActivity extends BaseActivity {

    private static final String  LAYOUT_LINEARLAYOUT   = "LinearLayout";
    private static final String  LAYOUT_FRAMELAYOUT    = "FrameLayout";
    private static final String  LAYOUT_RELATIVELAYOUT = "RelativeLayout";
    public static final  Integer BAR_CODE              = 1;
    Unbinder mUnbinder;
    //  private BottomDialog   mDeptDialog;           //底部弹窗 科室或者地理位子的

    public static final SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");

    public DaoSession     mDaoSession;
    public SQLiteDatabase mDb;


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

    @Override
    protected void onResume() {
        super.onResume();
        if (!isActive) {
            //app 从后台唤醒，进入前台
            isActive = true;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!isAppOnForeground()) {
            //app 进入后台
            isActive = false;
            //全局变量isActive = false 记录当前已经进入后台
            if (IsNeedRestart) {
                SophixManager.getInstance().killProcessSafely();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mUnbinder = null;
        RefWatcher refWatcher = MyApplication.getRefWatcher(this);
        refWatcher.watch(this);
        ActivityManager.getInstance().finishActivity(this);
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);

    }

    //获取第一个fragment
    protected abstract BaseFragment getFirstFragment();

    //获取Intent
    protected void handleIntent(Intent intent) {

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


    public boolean isAppOnForeground() {
        // Returns a list of application processes that are running on the
        // device

        android.app.ActivityManager activityManager = (android.app.ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();

        List<RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
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


    public void gotoActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }


}
