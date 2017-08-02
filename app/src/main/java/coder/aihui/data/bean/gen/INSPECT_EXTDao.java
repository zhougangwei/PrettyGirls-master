package coder.aihui.data.bean.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import coder.aihui.data.bean.INSPECT_EXT;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "INSPECT__EXT".
*/
public class INSPECT_EXTDao extends AbstractDao<INSPECT_EXT, Long> {

    public static final String TABLENAME = "INSPECT__EXT";

    /**
     * Properties of entity INSPECT_EXT.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property INSE_ID = new Property(0, Long.class, "INSE_ID", true, "INSE__ID");
        public final static Property INSE_FK_ID = new Property(1, String.class, "INSE_FK_ID", false, "INSE__FK__ID");
        public final static Property INSE_TYPE = new Property(2, String.class, "INSE_TYPE", false, "INSE__TYPE");
        public final static Property INSE_TEMPLATE_ID = new Property(3, String.class, "INSE_TEMPLATE_ID", false, "INSE__TEMPLATE__ID");
        public final static Property INSE_CYCLE = new Property(4, Integer.class, "INSE_CYCLE", false, "INSE__CYCLE");
        public final static Property INSE_CYCLE_TYPE = new Property(5, String.class, "INSE_CYCLE_TYPE", false, "INSE__CYCLE__TYPE");
    }


    public INSPECT_EXTDao(DaoConfig config) {
        super(config);
    }
    
    public INSPECT_EXTDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"INSPECT__EXT\" (" + //
                "\"INSE__ID\" INTEGER PRIMARY KEY ," + // 0: INSE_ID
                "\"INSE__FK__ID\" TEXT," + // 1: INSE_FK_ID
                "\"INSE__TYPE\" TEXT," + // 2: INSE_TYPE
                "\"INSE__TEMPLATE__ID\" TEXT," + // 3: INSE_TEMPLATE_ID
                "\"INSE__CYCLE\" INTEGER," + // 4: INSE_CYCLE
                "\"INSE__CYCLE__TYPE\" TEXT);"); // 5: INSE_CYCLE_TYPE
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"INSPECT__EXT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, INSPECT_EXT entity) {
        stmt.clearBindings();
 
        Long INSE_ID = entity.getINSE_ID();
        if (INSE_ID != null) {
            stmt.bindLong(1, INSE_ID);
        }
 
        String INSE_FK_ID = entity.getINSE_FK_ID();
        if (INSE_FK_ID != null) {
            stmt.bindString(2, INSE_FK_ID);
        }
 
        String INSE_TYPE = entity.getINSE_TYPE();
        if (INSE_TYPE != null) {
            stmt.bindString(3, INSE_TYPE);
        }
 
        String INSE_TEMPLATE_ID = entity.getINSE_TEMPLATE_ID();
        if (INSE_TEMPLATE_ID != null) {
            stmt.bindString(4, INSE_TEMPLATE_ID);
        }
 
        Integer INSE_CYCLE = entity.getINSE_CYCLE();
        if (INSE_CYCLE != null) {
            stmt.bindLong(5, INSE_CYCLE);
        }
 
        String INSE_CYCLE_TYPE = entity.getINSE_CYCLE_TYPE();
        if (INSE_CYCLE_TYPE != null) {
            stmt.bindString(6, INSE_CYCLE_TYPE);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, INSPECT_EXT entity) {
        stmt.clearBindings();
 
        Long INSE_ID = entity.getINSE_ID();
        if (INSE_ID != null) {
            stmt.bindLong(1, INSE_ID);
        }
 
        String INSE_FK_ID = entity.getINSE_FK_ID();
        if (INSE_FK_ID != null) {
            stmt.bindString(2, INSE_FK_ID);
        }
 
        String INSE_TYPE = entity.getINSE_TYPE();
        if (INSE_TYPE != null) {
            stmt.bindString(3, INSE_TYPE);
        }
 
        String INSE_TEMPLATE_ID = entity.getINSE_TEMPLATE_ID();
        if (INSE_TEMPLATE_ID != null) {
            stmt.bindString(4, INSE_TEMPLATE_ID);
        }
 
        Integer INSE_CYCLE = entity.getINSE_CYCLE();
        if (INSE_CYCLE != null) {
            stmt.bindLong(5, INSE_CYCLE);
        }
 
        String INSE_CYCLE_TYPE = entity.getINSE_CYCLE_TYPE();
        if (INSE_CYCLE_TYPE != null) {
            stmt.bindString(6, INSE_CYCLE_TYPE);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public INSPECT_EXT readEntity(Cursor cursor, int offset) {
        INSPECT_EXT entity = new INSPECT_EXT( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // INSE_ID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // INSE_FK_ID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // INSE_TYPE
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // INSE_TEMPLATE_ID
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // INSE_CYCLE
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // INSE_CYCLE_TYPE
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, INSPECT_EXT entity, int offset) {
        entity.setINSE_ID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setINSE_FK_ID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setINSE_TYPE(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setINSE_TEMPLATE_ID(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setINSE_CYCLE(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setINSE_CYCLE_TYPE(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(INSPECT_EXT entity, long rowId) {
        entity.setINSE_ID(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(INSPECT_EXT entity) {
        if(entity != null) {
            return entity.getINSE_ID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(INSPECT_EXT entity) {
        return entity.getINSE_ID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
