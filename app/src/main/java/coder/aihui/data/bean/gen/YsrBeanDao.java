package coder.aihui.data.bean.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import coder.aihui.data.bean.YsrBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "YSR_BEAN".
*/
public class YsrBeanDao extends AbstractDao<YsrBean, Long> {

    public static final String TABLENAME = "YSR_BEAN";

    /**
     * Properties of entity YsrBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property PurYsrId = new Property(0, Long.class, "purYsrId", true, "_id");
        public final static Property UserId = new Property(1, Integer.class, "userId", false, "USER_ID");
        public final static Property UserName = new Property(2, String.class, "userName", false, "USER_NAME");
    }


    public YsrBeanDao(DaoConfig config) {
        super(config);
    }
    
    public YsrBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"YSR_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: purYsrId
                "\"USER_ID\" INTEGER," + // 1: userId
                "\"USER_NAME\" TEXT);"); // 2: userName
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"YSR_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, YsrBean entity) {
        stmt.clearBindings();
 
        Long purYsrId = entity.getPurYsrId();
        if (purYsrId != null) {
            stmt.bindLong(1, purYsrId);
        }
 
        Integer userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(2, userId);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(3, userName);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, YsrBean entity) {
        stmt.clearBindings();
 
        Long purYsrId = entity.getPurYsrId();
        if (purYsrId != null) {
            stmt.bindLong(1, purYsrId);
        }
 
        Integer userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(2, userId);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(3, userName);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public YsrBean readEntity(Cursor cursor, int offset) {
        YsrBean entity = new YsrBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // purYsrId
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // userId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // userName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, YsrBean entity, int offset) {
        entity.setPurYsrId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserId(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setUserName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(YsrBean entity, long rowId) {
        entity.setPurYsrId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(YsrBean entity) {
        if(entity != null) {
            return entity.getPurYsrId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(YsrBean entity) {
        return entity.getPurYsrId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
