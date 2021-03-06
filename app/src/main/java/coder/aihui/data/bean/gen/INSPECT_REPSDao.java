package coder.aihui.data.bean.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import coder.aihui.data.bean.INSPECT_REPS;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "INSPECT__REPS".
*/
public class INSPECT_REPSDao extends AbstractDao<INSPECT_REPS, Long> {

    public static final String TABLENAME = "INSPECT__REPS";

    /**
     * Properties of entity INSPECT_REPS.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property INSPR_ID = new Property(0, Long.class, "INSPR_ID", true, "INSPR__ID");
        public final static Property INSPR_REP_ID = new Property(1, Integer.class, "INSPR_REP_ID", false, "INSPR__REP__ID");
        public final static Property INSPR_PNAME = new Property(2, String.class, "INSPR_PNAME", false, "INSPR__PNAME");
        public final static Property INSPR_PCONTENT = new Property(3, String.class, "INSPR_PCONTENT", false, "INSPR__PCONTENT");
        public final static Property INSPR_RE_VALUE = new Property(4, String.class, "INSPR_RE_VALUE", false, "INSPR__RE__VALUE");
        public final static Property INSPR_EXAM_VALUE = new Property(5, String.class, "INSPR_EXAM_VALUE", false, "INSPR__EXAM__VALUE");
        public final static Property INSPR_EXAM_RESULT = new Property(6, String.class, "INSPR_EXAM_RESULT", false, "INSPR__EXAM__RESULT");
        public final static Property INSPR_COMMENTS = new Property(7, String.class, "INSPR_COMMENTS", false, "INSPR__COMMENTS");
        public final static Property INSPR_VAL_TYPE = new Property(8, String.class, "INSPR_VAL_TYPE", false, "INSPR__VAL__TYPE");
        public final static Property INSPR_SEL_VAL = new Property(9, String.class, "INSPR_SEL_VAL", false, "INSPR__SEL__VAL");
        public final static Property INSPR_WX_NEED = new Property(10, Integer.class, "INSPR_WX_NEED", false, "INSPR__WX__NEED");
        public final static Property INSPR_UNIT = new Property(11, String.class, "INSPR_UNIT", false, "INSPR__UNIT");
        public final static Property INSPR_CYCLE = new Property(12, String.class, "INSPR_CYCLE", false, "INSPR__CYCLE");
        public final static Property INSPR_IS_FILL_IN = new Property(13, String.class, "INSPR_IS_FILL_IN", false, "INSPR__IS__FILL__IN");
        public final static Property INSPR_BZ = new Property(14, String.class, "INSPR_BZ", false, "INSPR__BZ");
        public final static Property SYNC_DATE = new Property(15, java.util.Date.class, "SYNC_DATE", false, "SYNC__DATE");
        public final static Property SYNC_FLAG = new Property(16, Integer.class, "SYNC_FLAG", false, "SYNC__FLAG");
        public final static Property UUID = new Property(17, String.class, "UUID", false, "UUID");
        public final static Property PUUID = new Property(18, String.class, "PUUID", false, "PUUID");
        public final static Property INSPR_HG_VAL = new Property(19, String.class, "INSPR_HG_VAL", false, "INSPR__HG__VAL");
        public final static Property FLAG = new Property(20, String.class, "FLAG", false, "FLAG");
        public final static Property INSPR_WRONG_VAL = new Property(21, String.class, "INSPR_WRONG_VAL", false, "INSPR__WRONG__VAL");
        public final static Property PLAN_ID = new Property(22, Long.class, "PLAN_ID", false, "PLAN__ID");
        public final static Property SAVE_DATE = new Property(23, java.util.Date.class, "SAVE_DATE", false, "SAVE__DATE");
        public final static Property NET_INSPRID = new Property(24, Long.class, "NET_INSPRID", false, "NET__INSPRID");
        public final static Property INSR_TYPE = new Property(25, String.class, "INSR_TYPE", false, "INSR__TYPE");
        public final static Property INSPR_METER_ID = new Property(26, Long.class, "INSPR_METER_ID", false, "INSPR__METER__ID");
        public final static Property METER_PLAN_ID = new Property(27, Long.class, "METER_PLAN_ID", false, "METER__PLAN__ID");
        public final static Property Default = new Property(28, Integer.class, "Default", false, "DEFAULT");
        public final static Property ModelId = new Property(29, Long.class, "modelId", false, "MODEL_ID");
        public final static Property PDAID = new Property(30, String.class, "PDAID", false, "PDAID");
    }


    public INSPECT_REPSDao(DaoConfig config) {
        super(config);
    }
    
    public INSPECT_REPSDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"INSPECT__REPS\" (" + //
                "\"INSPR__ID\" INTEGER PRIMARY KEY ," + // 0: INSPR_ID
                "\"INSPR__REP__ID\" INTEGER," + // 1: INSPR_REP_ID
                "\"INSPR__PNAME\" TEXT," + // 2: INSPR_PNAME
                "\"INSPR__PCONTENT\" TEXT," + // 3: INSPR_PCONTENT
                "\"INSPR__RE__VALUE\" TEXT," + // 4: INSPR_RE_VALUE
                "\"INSPR__EXAM__VALUE\" TEXT," + // 5: INSPR_EXAM_VALUE
                "\"INSPR__EXAM__RESULT\" TEXT," + // 6: INSPR_EXAM_RESULT
                "\"INSPR__COMMENTS\" TEXT," + // 7: INSPR_COMMENTS
                "\"INSPR__VAL__TYPE\" TEXT," + // 8: INSPR_VAL_TYPE
                "\"INSPR__SEL__VAL\" TEXT," + // 9: INSPR_SEL_VAL
                "\"INSPR__WX__NEED\" INTEGER," + // 10: INSPR_WX_NEED
                "\"INSPR__UNIT\" TEXT," + // 11: INSPR_UNIT
                "\"INSPR__CYCLE\" TEXT," + // 12: INSPR_CYCLE
                "\"INSPR__IS__FILL__IN\" TEXT," + // 13: INSPR_IS_FILL_IN
                "\"INSPR__BZ\" TEXT," + // 14: INSPR_BZ
                "\"SYNC__DATE\" INTEGER," + // 15: SYNC_DATE
                "\"SYNC__FLAG\" INTEGER," + // 16: SYNC_FLAG
                "\"UUID\" TEXT," + // 17: UUID
                "\"PUUID\" TEXT," + // 18: PUUID
                "\"INSPR__HG__VAL\" TEXT," + // 19: INSPR_HG_VAL
                "\"FLAG\" TEXT," + // 20: FLAG
                "\"INSPR__WRONG__VAL\" TEXT," + // 21: INSPR_WRONG_VAL
                "\"PLAN__ID\" INTEGER," + // 22: PLAN_ID
                "\"SAVE__DATE\" INTEGER," + // 23: SAVE_DATE
                "\"NET__INSPRID\" INTEGER," + // 24: NET_INSPRID
                "\"INSR__TYPE\" TEXT," + // 25: INSR_TYPE
                "\"INSPR__METER__ID\" INTEGER," + // 26: INSPR_METER_ID
                "\"METER__PLAN__ID\" INTEGER," + // 27: METER_PLAN_ID
                "\"DEFAULT\" INTEGER," + // 28: Default
                "\"MODEL_ID\" INTEGER," + // 29: modelId
                "\"PDAID\" TEXT);"); // 30: PDAID
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"INSPECT__REPS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, INSPECT_REPS entity) {
        stmt.clearBindings();
 
        Long INSPR_ID = entity.getINSPR_ID();
        if (INSPR_ID != null) {
            stmt.bindLong(1, INSPR_ID);
        }
 
        Integer INSPR_REP_ID = entity.getINSPR_REP_ID();
        if (INSPR_REP_ID != null) {
            stmt.bindLong(2, INSPR_REP_ID);
        }
 
        String INSPR_PNAME = entity.getINSPR_PNAME();
        if (INSPR_PNAME != null) {
            stmt.bindString(3, INSPR_PNAME);
        }
 
        String INSPR_PCONTENT = entity.getINSPR_PCONTENT();
        if (INSPR_PCONTENT != null) {
            stmt.bindString(4, INSPR_PCONTENT);
        }
 
        String INSPR_RE_VALUE = entity.getINSPR_RE_VALUE();
        if (INSPR_RE_VALUE != null) {
            stmt.bindString(5, INSPR_RE_VALUE);
        }
 
        String INSPR_EXAM_VALUE = entity.getINSPR_EXAM_VALUE();
        if (INSPR_EXAM_VALUE != null) {
            stmt.bindString(6, INSPR_EXAM_VALUE);
        }
 
        String INSPR_EXAM_RESULT = entity.getINSPR_EXAM_RESULT();
        if (INSPR_EXAM_RESULT != null) {
            stmt.bindString(7, INSPR_EXAM_RESULT);
        }
 
        String INSPR_COMMENTS = entity.getINSPR_COMMENTS();
        if (INSPR_COMMENTS != null) {
            stmt.bindString(8, INSPR_COMMENTS);
        }
 
        String INSPR_VAL_TYPE = entity.getINSPR_VAL_TYPE();
        if (INSPR_VAL_TYPE != null) {
            stmt.bindString(9, INSPR_VAL_TYPE);
        }
 
        String INSPR_SEL_VAL = entity.getINSPR_SEL_VAL();
        if (INSPR_SEL_VAL != null) {
            stmt.bindString(10, INSPR_SEL_VAL);
        }
 
        Integer INSPR_WX_NEED = entity.getINSPR_WX_NEED();
        if (INSPR_WX_NEED != null) {
            stmt.bindLong(11, INSPR_WX_NEED);
        }
 
        String INSPR_UNIT = entity.getINSPR_UNIT();
        if (INSPR_UNIT != null) {
            stmt.bindString(12, INSPR_UNIT);
        }
 
        String INSPR_CYCLE = entity.getINSPR_CYCLE();
        if (INSPR_CYCLE != null) {
            stmt.bindString(13, INSPR_CYCLE);
        }
 
        String INSPR_IS_FILL_IN = entity.getINSPR_IS_FILL_IN();
        if (INSPR_IS_FILL_IN != null) {
            stmt.bindString(14, INSPR_IS_FILL_IN);
        }
 
        String INSPR_BZ = entity.getINSPR_BZ();
        if (INSPR_BZ != null) {
            stmt.bindString(15, INSPR_BZ);
        }
 
        java.util.Date SYNC_DATE = entity.getSYNC_DATE();
        if (SYNC_DATE != null) {
            stmt.bindLong(16, SYNC_DATE.getTime());
        }
 
        Integer SYNC_FLAG = entity.getSYNC_FLAG();
        if (SYNC_FLAG != null) {
            stmt.bindLong(17, SYNC_FLAG);
        }
 
        String UUID = entity.getUUID();
        if (UUID != null) {
            stmt.bindString(18, UUID);
        }
 
        String PUUID = entity.getPUUID();
        if (PUUID != null) {
            stmt.bindString(19, PUUID);
        }
 
        String INSPR_HG_VAL = entity.getINSPR_HG_VAL();
        if (INSPR_HG_VAL != null) {
            stmt.bindString(20, INSPR_HG_VAL);
        }
 
        String FLAG = entity.getFLAG();
        if (FLAG != null) {
            stmt.bindString(21, FLAG);
        }
 
        String INSPR_WRONG_VAL = entity.getINSPR_WRONG_VAL();
        if (INSPR_WRONG_VAL != null) {
            stmt.bindString(22, INSPR_WRONG_VAL);
        }
 
        Long PLAN_ID = entity.getPLAN_ID();
        if (PLAN_ID != null) {
            stmt.bindLong(23, PLAN_ID);
        }
 
        java.util.Date SAVE_DATE = entity.getSAVE_DATE();
        if (SAVE_DATE != null) {
            stmt.bindLong(24, SAVE_DATE.getTime());
        }
 
        Long NET_INSPRID = entity.getNET_INSPRID();
        if (NET_INSPRID != null) {
            stmt.bindLong(25, NET_INSPRID);
        }
 
        String INSR_TYPE = entity.getINSR_TYPE();
        if (INSR_TYPE != null) {
            stmt.bindString(26, INSR_TYPE);
        }
 
        Long INSPR_METER_ID = entity.getINSPR_METER_ID();
        if (INSPR_METER_ID != null) {
            stmt.bindLong(27, INSPR_METER_ID);
        }
 
        Long METER_PLAN_ID = entity.getMETER_PLAN_ID();
        if (METER_PLAN_ID != null) {
            stmt.bindLong(28, METER_PLAN_ID);
        }
 
        Integer Default = entity.getDefault();
        if (Default != null) {
            stmt.bindLong(29, Default);
        }
 
        Long modelId = entity.getModelId();
        if (modelId != null) {
            stmt.bindLong(30, modelId);
        }
 
        String PDAID = entity.getPDAID();
        if (PDAID != null) {
            stmt.bindString(31, PDAID);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, INSPECT_REPS entity) {
        stmt.clearBindings();
 
        Long INSPR_ID = entity.getINSPR_ID();
        if (INSPR_ID != null) {
            stmt.bindLong(1, INSPR_ID);
        }
 
        Integer INSPR_REP_ID = entity.getINSPR_REP_ID();
        if (INSPR_REP_ID != null) {
            stmt.bindLong(2, INSPR_REP_ID);
        }
 
        String INSPR_PNAME = entity.getINSPR_PNAME();
        if (INSPR_PNAME != null) {
            stmt.bindString(3, INSPR_PNAME);
        }
 
        String INSPR_PCONTENT = entity.getINSPR_PCONTENT();
        if (INSPR_PCONTENT != null) {
            stmt.bindString(4, INSPR_PCONTENT);
        }
 
        String INSPR_RE_VALUE = entity.getINSPR_RE_VALUE();
        if (INSPR_RE_VALUE != null) {
            stmt.bindString(5, INSPR_RE_VALUE);
        }
 
        String INSPR_EXAM_VALUE = entity.getINSPR_EXAM_VALUE();
        if (INSPR_EXAM_VALUE != null) {
            stmt.bindString(6, INSPR_EXAM_VALUE);
        }
 
        String INSPR_EXAM_RESULT = entity.getINSPR_EXAM_RESULT();
        if (INSPR_EXAM_RESULT != null) {
            stmt.bindString(7, INSPR_EXAM_RESULT);
        }
 
        String INSPR_COMMENTS = entity.getINSPR_COMMENTS();
        if (INSPR_COMMENTS != null) {
            stmt.bindString(8, INSPR_COMMENTS);
        }
 
        String INSPR_VAL_TYPE = entity.getINSPR_VAL_TYPE();
        if (INSPR_VAL_TYPE != null) {
            stmt.bindString(9, INSPR_VAL_TYPE);
        }
 
        String INSPR_SEL_VAL = entity.getINSPR_SEL_VAL();
        if (INSPR_SEL_VAL != null) {
            stmt.bindString(10, INSPR_SEL_VAL);
        }
 
        Integer INSPR_WX_NEED = entity.getINSPR_WX_NEED();
        if (INSPR_WX_NEED != null) {
            stmt.bindLong(11, INSPR_WX_NEED);
        }
 
        String INSPR_UNIT = entity.getINSPR_UNIT();
        if (INSPR_UNIT != null) {
            stmt.bindString(12, INSPR_UNIT);
        }
 
        String INSPR_CYCLE = entity.getINSPR_CYCLE();
        if (INSPR_CYCLE != null) {
            stmt.bindString(13, INSPR_CYCLE);
        }
 
        String INSPR_IS_FILL_IN = entity.getINSPR_IS_FILL_IN();
        if (INSPR_IS_FILL_IN != null) {
            stmt.bindString(14, INSPR_IS_FILL_IN);
        }
 
        String INSPR_BZ = entity.getINSPR_BZ();
        if (INSPR_BZ != null) {
            stmt.bindString(15, INSPR_BZ);
        }
 
        java.util.Date SYNC_DATE = entity.getSYNC_DATE();
        if (SYNC_DATE != null) {
            stmt.bindLong(16, SYNC_DATE.getTime());
        }
 
        Integer SYNC_FLAG = entity.getSYNC_FLAG();
        if (SYNC_FLAG != null) {
            stmt.bindLong(17, SYNC_FLAG);
        }
 
        String UUID = entity.getUUID();
        if (UUID != null) {
            stmt.bindString(18, UUID);
        }
 
        String PUUID = entity.getPUUID();
        if (PUUID != null) {
            stmt.bindString(19, PUUID);
        }
 
        String INSPR_HG_VAL = entity.getINSPR_HG_VAL();
        if (INSPR_HG_VAL != null) {
            stmt.bindString(20, INSPR_HG_VAL);
        }
 
        String FLAG = entity.getFLAG();
        if (FLAG != null) {
            stmt.bindString(21, FLAG);
        }
 
        String INSPR_WRONG_VAL = entity.getINSPR_WRONG_VAL();
        if (INSPR_WRONG_VAL != null) {
            stmt.bindString(22, INSPR_WRONG_VAL);
        }
 
        Long PLAN_ID = entity.getPLAN_ID();
        if (PLAN_ID != null) {
            stmt.bindLong(23, PLAN_ID);
        }
 
        java.util.Date SAVE_DATE = entity.getSAVE_DATE();
        if (SAVE_DATE != null) {
            stmt.bindLong(24, SAVE_DATE.getTime());
        }
 
        Long NET_INSPRID = entity.getNET_INSPRID();
        if (NET_INSPRID != null) {
            stmt.bindLong(25, NET_INSPRID);
        }
 
        String INSR_TYPE = entity.getINSR_TYPE();
        if (INSR_TYPE != null) {
            stmt.bindString(26, INSR_TYPE);
        }
 
        Long INSPR_METER_ID = entity.getINSPR_METER_ID();
        if (INSPR_METER_ID != null) {
            stmt.bindLong(27, INSPR_METER_ID);
        }
 
        Long METER_PLAN_ID = entity.getMETER_PLAN_ID();
        if (METER_PLAN_ID != null) {
            stmt.bindLong(28, METER_PLAN_ID);
        }
 
        Integer Default = entity.getDefault();
        if (Default != null) {
            stmt.bindLong(29, Default);
        }
 
        Long modelId = entity.getModelId();
        if (modelId != null) {
            stmt.bindLong(30, modelId);
        }
 
        String PDAID = entity.getPDAID();
        if (PDAID != null) {
            stmt.bindString(31, PDAID);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public INSPECT_REPS readEntity(Cursor cursor, int offset) {
        INSPECT_REPS entity = new INSPECT_REPS( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // INSPR_ID
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // INSPR_REP_ID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // INSPR_PNAME
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // INSPR_PCONTENT
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // INSPR_RE_VALUE
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // INSPR_EXAM_VALUE
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // INSPR_EXAM_RESULT
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // INSPR_COMMENTS
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // INSPR_VAL_TYPE
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // INSPR_SEL_VAL
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10), // INSPR_WX_NEED
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // INSPR_UNIT
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // INSPR_CYCLE
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // INSPR_IS_FILL_IN
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // INSPR_BZ
            cursor.isNull(offset + 15) ? null : new java.util.Date(cursor.getLong(offset + 15)), // SYNC_DATE
            cursor.isNull(offset + 16) ? null : cursor.getInt(offset + 16), // SYNC_FLAG
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // UUID
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // PUUID
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // INSPR_HG_VAL
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // FLAG
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // INSPR_WRONG_VAL
            cursor.isNull(offset + 22) ? null : cursor.getLong(offset + 22), // PLAN_ID
            cursor.isNull(offset + 23) ? null : new java.util.Date(cursor.getLong(offset + 23)), // SAVE_DATE
            cursor.isNull(offset + 24) ? null : cursor.getLong(offset + 24), // NET_INSPRID
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // INSR_TYPE
            cursor.isNull(offset + 26) ? null : cursor.getLong(offset + 26), // INSPR_METER_ID
            cursor.isNull(offset + 27) ? null : cursor.getLong(offset + 27), // METER_PLAN_ID
            cursor.isNull(offset + 28) ? null : cursor.getInt(offset + 28), // Default
            cursor.isNull(offset + 29) ? null : cursor.getLong(offset + 29), // modelId
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30) // PDAID
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, INSPECT_REPS entity, int offset) {
        entity.setINSPR_ID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setINSPR_REP_ID(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setINSPR_PNAME(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setINSPR_PCONTENT(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setINSPR_RE_VALUE(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setINSPR_EXAM_VALUE(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setINSPR_EXAM_RESULT(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setINSPR_COMMENTS(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setINSPR_VAL_TYPE(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setINSPR_SEL_VAL(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setINSPR_WX_NEED(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
        entity.setINSPR_UNIT(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setINSPR_CYCLE(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setINSPR_IS_FILL_IN(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setINSPR_BZ(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setSYNC_DATE(cursor.isNull(offset + 15) ? null : new java.util.Date(cursor.getLong(offset + 15)));
        entity.setSYNC_FLAG(cursor.isNull(offset + 16) ? null : cursor.getInt(offset + 16));
        entity.setUUID(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setPUUID(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setINSPR_HG_VAL(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setFLAG(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setINSPR_WRONG_VAL(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setPLAN_ID(cursor.isNull(offset + 22) ? null : cursor.getLong(offset + 22));
        entity.setSAVE_DATE(cursor.isNull(offset + 23) ? null : new java.util.Date(cursor.getLong(offset + 23)));
        entity.setNET_INSPRID(cursor.isNull(offset + 24) ? null : cursor.getLong(offset + 24));
        entity.setINSR_TYPE(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setINSPR_METER_ID(cursor.isNull(offset + 26) ? null : cursor.getLong(offset + 26));
        entity.setMETER_PLAN_ID(cursor.isNull(offset + 27) ? null : cursor.getLong(offset + 27));
        entity.setDefault(cursor.isNull(offset + 28) ? null : cursor.getInt(offset + 28));
        entity.setModelId(cursor.isNull(offset + 29) ? null : cursor.getLong(offset + 29));
        entity.setPDAID(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(INSPECT_REPS entity, long rowId) {
        entity.setINSPR_ID(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(INSPECT_REPS entity) {
        if(entity != null) {
            return entity.getINSPR_ID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(INSPECT_REPS entity) {
        return entity.getINSPR_ID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
