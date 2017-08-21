package coder.aihui.data.bean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.greenrobot.greendao.database.Database;

import coder.aihui.data.bean.gen.DaoMaster;
import coder.aihui.data.bean.gen.INSPECT_PLANDao;
import coder.aihui.data.bean.gen.INSPECT_REPSDao;
import coder.aihui.data.bean.gen.InspectTempletItemDao;
import coder.aihui.data.bean.gen.MigrationHelper;
import coder.aihui.data.bean.gen.PUR_CONTRACT_PLANDao;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/7/14 10:36
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/7/14$
 * @ 更新描述  ${TODO}
 */


public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
    public MySQLiteOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {

        Log.d("数据库升级", "oldVersion:" + oldVersion + "newVersion" + newVersion);
        MigrationHelper.getInstance().migrate(db, INSPECT_PLANDao.class, InspectTempletItemDao.class, PUR_CONTRACT_PLANDao.class
        ,INSPECT_REPSDao.class
        );

    }

}
