package coder.aihui.app;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.util.concurrent.TimeUnit;

import coder.aihui.app.exception.LocalFileHandler;
import coder.aihui.data.bean.MySQLiteOpenHelper;
import coder.aihui.data.bean.gen.DaoMaster;
import coder.aihui.data.bean.gen.DaoSession;
import coder.aihui.util.LogUtil;
import okhttp3.OkHttpClient;


/**
 * Created by oracleen on 2016/6/28.
 */
public class MyApplication extends Application /*implements ReactApplication */{

    private static MyApplication mApplication;
    public static MyApplication mContext;      //可以用作全局上下文
    public static String currentGirl = "http://ww2.sinaimg.cn/large/610dc034jw1f5k1k4azguj20u00u0421.jpg";
    private RefWatcher mRefWatcher;//leakCanary观察器

    /**
     * A flag to showDialog how easily you can switch from standard SQLite to the encrypted SQLCipher.
     */
    public static final boolean ENCRYPTED = true;
    public static DaoSession     daoSession;
    private SQLiteDatabase mDb;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;
        mContext = this;

        //配置是否显示log
        LogUtil.isDebug = true;

        mRefWatcher = LeakCanary.install(this);


        //配置程序异常退出处理
        Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(this));

        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(this, "aihuimobile.db", null);
        mDb = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(mDb);
         daoSession = daoMaster.newSession();
        //RxBus的
    }

    public static OkHttpClient defaultOkHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        return client;
    }

    public static MyApplication getIntstance() {
        return mApplication;
    }

    //获取到daosession
    public DaoSession getDaoSession() {
        return daoSession;
    }
    public SQLiteDatabase getDatabase() {
        return mDb;
    }





    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.mRefWatcher;
    }

   /* @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }


    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {


        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage()
                    //将我们创建的包管理器给添加进来

            );
        }
    };*/
}
