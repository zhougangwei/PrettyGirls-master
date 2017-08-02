package coder.aihui.data.bean.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import coder.aihui.data.bean.INSPECT_GROUP;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "INSPECT__GROUP".
*/
public class INSPECT_GROUPDao extends AbstractDao<INSPECT_GROUP, Long> {

    public static final String TABLENAME = "INSPECT__GROUP";

    /**
     * Properties of entity INSPECT_GROUP.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property ID = new Property(0, Long.class, "ID", true, "_id");
        public final static Property INSG_ID = new Property(1, Long.class, "INSG_ID", false, "INSG__ID");
        public final static Property INSG_KPBH = new Property(2, String.class, "INSG_KPBH", false, "INSG__KPBH");
        public final static Property INSG_RFID = new Property(3, String.class, "INSG_RFID", false, "INSG__RFID");
        public final static Property INSG_BAR_CODE = new Property(4, String.class, "INSG_BAR_CODE", false, "INSG__BAR__CODE");
        public final static Property INSG_DDID = new Property(5, String.class, "INSG_DDID", false, "INSG__DDID");
        public final static Property INSGR_PNID = new Property(6, String.class, "INSGR_PNID", false, "INSGR__PNID");
        public final static Property WZMC = new Property(7, String.class, "WZMC", false, "WZMC");
        public final static Property KPBH = new Property(8, String.class, "KPBH", false, "KPBH");
        public final static Property RFID_CODE = new Property(9, String.class, "RFID_CODE", false, "RFID__CODE");
        public final static Property BAR_CODE = new Property(10, String.class, "BAR_CODE", false, "BAR__CODE");
    }


    public INSPECT_GROUPDao(DaoConfig config) {
        super(config);
    }
    
    public INSPECT_GROUPDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"INSPECT__GROUP\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: ID
                "\"INSG__ID\" INTEGER," + // 1: INSG_ID
                "\"INSG__KPBH\" TEXT," + // 2: INSG_KPBH
                "\"INSG__RFID\" TEXT," + // 3: INSG_RFID
                "\"INSG__BAR__CODE\" TEXT," + // 4: INSG_BAR_CODE
                "\"INSG__DDID\" TEXT," + // 5: INSG_DDID
                "\"INSGR__PNID\" TEXT," + // 6: INSGR_PNID
                "\"WZMC\" TEXT," + // 7: WZMC
                "\"KPBH\" TEXT," + // 8: KPBH
                "\"RFID__CODE\" TEXT," + // 9: RFID_CODE
                "\"BAR__CODE\" TEXT);"); // 10: BAR_CODE
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"INSPECT__GROUP\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, INSPECT_GROUP entity) {
        stmt.clearBindings();
 
        Long ID = entity.getID();
        if (ID != null) {
            stmt.bindLong(1, ID);
        }
 
        Long INSG_ID = entity.getINSG_ID();
        if (INSG_ID != null) {
            stmt.bindLong(2, INSG_ID);
        }
 
        String INSG_KPBH = entity.getINSG_KPBH();
        if (INSG_KPBH != null) {
            stmt.bindString(3, INSG_KPBH);
        }
 
        String INSG_RFID = entity.getINSG_RFID();
        if (INSG_RFID != null) {
            stmt.bindString(4, INSG_RFID);
        }
 
        String INSG_BAR_CODE = entity.getINSG_BAR_CODE();
        if (INSG_BAR_CODE != null) {
            stmt.bindString(5, INSG_BAR_CODE);
        }
 
        String INSG_DDID = entity.getINSG_DDID();
        if (INSG_DDID != null) {
            stmt.bindString(6, INSG_DDID);
        }
 
        String INSGR_PNID = entity.getINSGR_PNID();
        if (INSGR_PNID != null) {
            stmt.bindString(7, INSGR_PNID);
        }
 
        String WZMC = entity.getWZMC();
        if (WZMC != null) {
            stmt.bindString(8, WZMC);
        }
 
        String KPBH = entity.getKPBH();
        if (KPBH != null) {
            stmt.bindString(9, KPBH);
        }
 
        String RFID_CODE = entity.getRFID_CODE();
        if (RFID_CODE != null) {
            stmt.bindString(10, RFID_CODE);
        }
 
        String BAR_CODE = entity.getBAR_CODE();
        if (BAR_CODE != null) {
            stmt.bindString(11, BAR_CODE);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, INSPECT_GROUP entity) {
        stmt.clearBindings();
 
        Long ID = entity.getID();
        if (ID != null) {
            stmt.bindLong(1, ID);
        }
 
        Long INSG_ID = entity.getINSG_ID();
        if (INSG_ID != null) {
            stmt.bindLong(2, INSG_ID);
        }
 
        String INSG_KPBH = entity.getINSG_KPBH();
        if (INSG_KPBH != null) {
            stmt.bindString(3, INSG_KPBH);
        }
 
        String INSG_RFID = entity.getINSG_RFID();
        if (INSG_RFID != null) {
            stmt.bindString(4, INSG_RFID);
        }
 
        String INSG_BAR_CODE = entity.getINSG_BAR_CODE();
        if (INSG_BAR_CODE != null) {
            stmt.bindString(5, INSG_BAR_CODE);
        }
 
        String INSG_DDID = entity.getINSG_DDID();
        if (INSG_DDID != null) {
            stmt.bindString(6, INSG_DDID);
        }
 
        String INSGR_PNID = entity.getINSGR_PNID();
        if (INSGR_PNID != null) {
            stmt.bindString(7, INSGR_PNID);
        }
 
        String WZMC = entity.getWZMC();
        if (WZMC != null) {
            stmt.bindString(8, WZMC);
        }
 
        String KPBH = entity.getKPBH();
        if (KPBH != null) {
            stmt.bindString(9, KPBH);
        }
 
        String RFID_CODE = entity.getRFID_CODE();
        if (RFID_CODE != null) {
            stmt.bindString(10, RFID_CODE);
        }
 
        String BAR_CODE = entity.getBAR_CODE();
        if (BAR_CODE != null) {
            stmt.bindString(11, BAR_CODE);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public INSPECT_GROUP readEntity(Cursor cursor, int offset) {
        INSPECT_GROUP entity = new INSPECT_GROUP( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // ID
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // INSG_ID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // INSG_KPBH
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // INSG_RFID
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // INSG_BAR_CODE
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // INSG_DDID
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // INSGR_PNID
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // WZMC
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // KPBH
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // RFID_CODE
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10) // BAR_CODE
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, INSPECT_GROUP entity, int offset) {
        entity.setID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setINSG_ID(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setINSG_KPBH(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setINSG_RFID(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setINSG_BAR_CODE(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setINSG_DDID(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setINSGR_PNID(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setWZMC(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setKPBH(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setRFID_CODE(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setBAR_CODE(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(INSPECT_GROUP entity, long rowId) {
        entity.setID(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(INSPECT_GROUP entity) {
        if(entity != null) {
            return entity.getID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(INSPECT_GROUP entity) {
        return entity.getID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
