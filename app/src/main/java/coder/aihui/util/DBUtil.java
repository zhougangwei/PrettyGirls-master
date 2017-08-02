package coder.aihui.util;

import android.content.Context;

import coder.aihui.data.bean.gen.DaoMaster;


/**
 * 获取数据库 单例模式
 */
public class DBUtil {

    private static final String DATABASE_NAME = "aihuimobile.db";


    private static DaoMaster.DevOpenHelper mInstance = null;

    public synchronized static DaoMaster.DevOpenHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DaoMaster.DevOpenHelper(context, DATABASE_NAME, null);
        }
        return mInstance;
    }


}
