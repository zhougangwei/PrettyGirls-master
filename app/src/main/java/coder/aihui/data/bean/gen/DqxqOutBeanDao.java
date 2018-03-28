package coder.aihui.data.bean.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import coder.aihui.data.bean.DqxqOutBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DQXQ_OUT_BEAN".
*/
public class DqxqOutBeanDao extends AbstractDao<DqxqOutBean, Long> {

    public static final String TABLENAME = "DQXQ_OUT_BEAN";

    /**
     * Properties of entity DqxqOutBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property ID = new Property(0, Long.class, "ID", true, "_id");
        public final static Property GGXH = new Property(1, String.class, "GGXH", false, "GGXH");
        public final static Property TEMPLET_NAME = new Property(2, String.class, "TEMPLET_NAME", false, "TEMPLET_NAME");
        public final static Property JCYQ = new Property(3, String.class, "JCYQ", false, "JCYQ");
        public final static Property SCCJ = new Property(4, String.class, "SCCJ", false, "SCCJ");
        public final static Property REMARK = new Property(5, String.class, "REMARK", false, "REMARK");
        public final static Property ZFBZ = new Property(6, Integer.class, "ZFBZ", false, "ZFBZ");
    }


    public DqxqOutBeanDao(DaoConfig config) {
        super(config);
    }
    
    public DqxqOutBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DQXQ_OUT_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: ID
                "\"GGXH\" TEXT," + // 1: GGXH
                "\"TEMPLET_NAME\" TEXT," + // 2: TEMPLET_NAME
                "\"JCYQ\" TEXT," + // 3: JCYQ
                "\"SCCJ\" TEXT," + // 4: SCCJ
                "\"REMARK\" TEXT," + // 5: REMARK
                "\"ZFBZ\" INTEGER);"); // 6: ZFBZ
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DQXQ_OUT_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DqxqOutBean entity) {
        stmt.clearBindings();
 
        Long ID = entity.getID();
        if (ID != null) {
            stmt.bindLong(1, ID);
        }
 
        String GGXH = entity.getGGXH();
        if (GGXH != null) {
            stmt.bindString(2, GGXH);
        }
 
        String TEMPLET_NAME = entity.getTEMPLET_NAME();
        if (TEMPLET_NAME != null) {
            stmt.bindString(3, TEMPLET_NAME);
        }
 
        String JCYQ = entity.getJCYQ();
        if (JCYQ != null) {
            stmt.bindString(4, JCYQ);
        }
 
        String SCCJ = entity.getSCCJ();
        if (SCCJ != null) {
            stmt.bindString(5, SCCJ);
        }
 
        String REMARK = entity.getREMARK();
        if (REMARK != null) {
            stmt.bindString(6, REMARK);
        }
 
        Integer ZFBZ = entity.getZFBZ();
        if (ZFBZ != null) {
            stmt.bindLong(7, ZFBZ);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DqxqOutBean entity) {
        stmt.clearBindings();
 
        Long ID = entity.getID();
        if (ID != null) {
            stmt.bindLong(1, ID);
        }
 
        String GGXH = entity.getGGXH();
        if (GGXH != null) {
            stmt.bindString(2, GGXH);
        }
 
        String TEMPLET_NAME = entity.getTEMPLET_NAME();
        if (TEMPLET_NAME != null) {
            stmt.bindString(3, TEMPLET_NAME);
        }
 
        String JCYQ = entity.getJCYQ();
        if (JCYQ != null) {
            stmt.bindString(4, JCYQ);
        }
 
        String SCCJ = entity.getSCCJ();
        if (SCCJ != null) {
            stmt.bindString(5, SCCJ);
        }
 
        String REMARK = entity.getREMARK();
        if (REMARK != null) {
            stmt.bindString(6, REMARK);
        }
 
        Integer ZFBZ = entity.getZFBZ();
        if (ZFBZ != null) {
            stmt.bindLong(7, ZFBZ);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DqxqOutBean readEntity(Cursor cursor, int offset) {
        DqxqOutBean entity = new DqxqOutBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // ID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // GGXH
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // TEMPLET_NAME
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // JCYQ
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // SCCJ
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // REMARK
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6) // ZFBZ
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DqxqOutBean entity, int offset) {
        entity.setID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setGGXH(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTEMPLET_NAME(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setJCYQ(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSCCJ(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setREMARK(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setZFBZ(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DqxqOutBean entity, long rowId) {
        entity.setID(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DqxqOutBean entity) {
        if(entity != null) {
            return entity.getID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DqxqOutBean entity) {
        return entity.getID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
