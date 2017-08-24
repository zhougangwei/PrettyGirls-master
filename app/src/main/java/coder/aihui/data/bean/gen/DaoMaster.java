package coder.aihui.data.bean.gen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.identityscope.IdentityScopeType;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/**
 * Master of DAO (schema version 18): knows all DAOs.
 */
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 18;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(Database db, boolean ifNotExists) {
        AZYS_MXDao.createTable(db, ifNotExists);
        DHBeanDao.createTable(db, ifNotExists);
        DqxqOutBeanDao.createTable(db, ifNotExists);
        UserDao.createTable(db, ifNotExists);
        InspectTempletItemDao.createTable(db, ifNotExists);
        INSPECT_EXTDao.createTable(db, ifNotExists);
        INSPECT_EXT_EXECUTORDao.createTable(db, ifNotExists);
        INSPECT_GROUPDao.createTable(db, ifNotExists);
        INSPECT_PLANDao.createTable(db, ifNotExists);
        INSPECT_REPDao.createTable(db, ifNotExists);
        INSPECT_REPSDao.createTable(db, ifNotExists);
        INSPECT_REP_PICDao.createTable(db, ifNotExists);
        IN_ASSETDao.createTable(db, ifNotExists);
        IN_MATERIALS_PPMCDao.createTable(db, ifNotExists);
        IN_MATERIALS_WZMCDao.createTable(db, ifNotExists);
        IN_STORE_QCDao.createTable(db, ifNotExists);
        PDA_ASSET_CHECKDao.createTable(db, ifNotExists);
        PUB_COMPANYDao.createTable(db, ifNotExists);
        PUB_DICTIONARY_ITEMDao.createTable(db, ifNotExists);
        PUR_CONTRACT_PLANDao.createTable(db, ifNotExists);
        PUR_CONTRACT_PLAN_DETAILDao.createTable(db, ifNotExists);
        PXGL_SAVEDao.createTable(db, ifNotExists);
        REPAIR_PLACEDao.createTable(db, ifNotExists);
        SYS_DEPTDao.createTable(db, ifNotExists);
        SYS_PARAMDao.createTable(db, ifNotExists);
        SYS_USERDao.createTable(db, ifNotExists);
        CORRECT_ASSETDao.createTable(db, ifNotExists);
        YsrBeanDao.createTable(db, ifNotExists);
    }

    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(Database db, boolean ifExists) {
        AZYS_MXDao.dropTable(db, ifExists);
        DHBeanDao.dropTable(db, ifExists);
        DqxqOutBeanDao.dropTable(db, ifExists);
        UserDao.dropTable(db, ifExists);
        InspectTempletItemDao.dropTable(db, ifExists);
        INSPECT_EXTDao.dropTable(db, ifExists);
        INSPECT_EXT_EXECUTORDao.dropTable(db, ifExists);
        INSPECT_GROUPDao.dropTable(db, ifExists);
        INSPECT_PLANDao.dropTable(db, ifExists);
        INSPECT_REPDao.dropTable(db, ifExists);
        INSPECT_REPSDao.dropTable(db, ifExists);
        INSPECT_REP_PICDao.dropTable(db, ifExists);
        IN_ASSETDao.dropTable(db, ifExists);
        IN_MATERIALS_PPMCDao.dropTable(db, ifExists);
        IN_MATERIALS_WZMCDao.dropTable(db, ifExists);
        IN_STORE_QCDao.dropTable(db, ifExists);
        PDA_ASSET_CHECKDao.dropTable(db, ifExists);
        PUB_COMPANYDao.dropTable(db, ifExists);
        PUB_DICTIONARY_ITEMDao.dropTable(db, ifExists);
        PUR_CONTRACT_PLANDao.dropTable(db, ifExists);
        PUR_CONTRACT_PLAN_DETAILDao.dropTable(db, ifExists);
        PXGL_SAVEDao.dropTable(db, ifExists);
        REPAIR_PLACEDao.dropTable(db, ifExists);
        SYS_DEPTDao.dropTable(db, ifExists);
        SYS_PARAMDao.dropTable(db, ifExists);
        SYS_USERDao.dropTable(db, ifExists);
        CORRECT_ASSETDao.dropTable(db, ifExists);
        YsrBeanDao.dropTable(db, ifExists);
    }

    /**
     * WARNING: Drops all table on Upgrade! Use only during development.
     * Convenience method using a {@link DevOpenHelper}.
     */
    public static DaoSession newDevSession(Context context, String name) {
        Database db = new DevOpenHelper(context, name).getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }

    public DaoMaster(SQLiteDatabase db) {
        this(new StandardDatabase(db));
    }

    public DaoMaster(Database db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(AZYS_MXDao.class);
        registerDaoClass(DHBeanDao.class);
        registerDaoClass(DqxqOutBeanDao.class);
        registerDaoClass(UserDao.class);
        registerDaoClass(InspectTempletItemDao.class);
        registerDaoClass(INSPECT_EXTDao.class);
        registerDaoClass(INSPECT_EXT_EXECUTORDao.class);
        registerDaoClass(INSPECT_GROUPDao.class);
        registerDaoClass(INSPECT_PLANDao.class);
        registerDaoClass(INSPECT_REPDao.class);
        registerDaoClass(INSPECT_REPSDao.class);
        registerDaoClass(INSPECT_REP_PICDao.class);
        registerDaoClass(IN_ASSETDao.class);
        registerDaoClass(IN_MATERIALS_PPMCDao.class);
        registerDaoClass(IN_MATERIALS_WZMCDao.class);
        registerDaoClass(IN_STORE_QCDao.class);
        registerDaoClass(PDA_ASSET_CHECKDao.class);
        registerDaoClass(PUB_COMPANYDao.class);
        registerDaoClass(PUB_DICTIONARY_ITEMDao.class);
        registerDaoClass(PUR_CONTRACT_PLANDao.class);
        registerDaoClass(PUR_CONTRACT_PLAN_DETAILDao.class);
        registerDaoClass(PXGL_SAVEDao.class);
        registerDaoClass(REPAIR_PLACEDao.class);
        registerDaoClass(SYS_DEPTDao.class);
        registerDaoClass(SYS_PARAMDao.class);
        registerDaoClass(SYS_USERDao.class);
        registerDaoClass(CORRECT_ASSETDao.class);
        registerDaoClass(YsrBeanDao.class);
    }

    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }

    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }

    /**
     * Calls {@link #createAllTables(Database, boolean)} in {@link #onCreate(Database)} -
     */
    public static abstract class OpenHelper extends DatabaseOpenHelper {
        public OpenHelper(Context context, String name) {
            super(context, name, SCHEMA_VERSION);
        }

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(Database db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }

    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name) {
            super(context, name);
        }

        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

}
