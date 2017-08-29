package coder.aihui.data.bean.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import coder.aihui.data.bean.CORRECT_ASSET;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CORRECT__ASSET".
*/
public class CORRECT_ASSETDao extends AbstractDao<CORRECT_ASSET, Long> {

    public static final String TABLENAME = "CORRECT__ASSET";

    /**
     * Properties of entity CORRECT_ASSET.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property ID = new Property(0, Long.class, "ID", true, "ID");
        public final static Property ASSET_ID = new Property(1, Long.class, "ASSET_ID", false, "ASSET__ID");
        public final static Property DDID = new Property(2, String.class, "DDID", false, "DDID");
        public final static Property DDMC = new Property(3, String.class, "DDMC", false, "DDMC");
        public final static Property GHDWID = new Property(4, String.class, "GHDWID", false, "GHDWID");
        public final static Property GHDWMC = new Property(5, String.class, "GHDWMC", false, "GHDWMC");
        public final static Property PPID = new Property(6, String.class, "PPID", false, "PPID");
        public final static Property PPMC = new Property(7, String.class, "PPMC", false, "PPMC");
        public final static Property SCCJID = new Property(8, String.class, "SCCJID", false, "SCCJID");
        public final static Property SCCJMC = new Property(9, String.class, "SCCJMC", false, "SCCJMC");
        public final static Property GGXH = new Property(10, String.class, "GGXH", false, "GGXH");
        public final static Property SCBH = new Property(11, String.class, "SCBH", false, "SCBH");
        public final static Property PP_FLAG = new Property(12, Integer.class, "PP_FLAG", false, "PP__FLAG");
        public final static Property GHDW_FLAG = new Property(13, Integer.class, "GHDW_FLAG", false, "GHDW__FLAG");
        public final static Property SCCJ_FLAG = new Property(14, Integer.class, "SCCJ_FLAG", false, "SCCJ__FLAG");
        public final static Property DD_FLAG = new Property(15, Integer.class, "DD_FLAG", false, "DD__FLAG");
        public final static Property USERID = new Property(16, String.class, "USERID", false, "USERID");
        public final static Property USERNAME = new Property(17, String.class, "USERNAME", false, "USERNAME");
        public final static Property IS_UP = new Property(18, Integer.class, "IS_UP", false, "IS__UP");
        public final static Property SCRQ = new Property(19, java.util.Date.class, "SCRQ", false, "SCRQ");
        public final static Property WZMC = new Property(20, String.class, "WZMC", false, "WZMC");
        public final static Property FRONT_PIC = new Property(21, String.class, "FRONT_PIC", false, "FRONT__PIC");
        public final static Property SIDE_PIC = new Property(22, String.class, "SIDE_PIC", false, "SIDE__PIC");
        public final static Property BIG_FRONT_PIC = new Property(23, String.class, "BIG_FRONT_PIC", false, "BIG__FRONT__PIC");
        public final static Property BIG_SIDE_PIC = new Property(24, String.class, "BIG_SIDE_PIC", false, "BIG__SIDE__PIC");
        public final static Property ZMZ_FILE_ID = new Property(25, Integer.class, "ZMZ_FILE_ID", false, "ZMZ__FILE__ID");
        public final static Property CMZ_FILE_ID = new Property(26, Integer.class, "CMZ_FILE_ID", false, "CMZ__FILE__ID");
        public final static Property QD_FLAG = new Property(27, String.class, "QD_FLAG", false, "QD__FLAG");
        public final static Property BGRID = new Property(28, String.class, "BGRID", false, "BGRID");
        public final static Property BGRXM = new Property(29, String.class, "BGRXM", false, "BGRXM");
        public final static Property BGKSID = new Property(30, String.class, "BGKSID", false, "BGKSID");
        public final static Property BGKSMC = new Property(31, String.class, "BGKSMC", false, "BGKSMC");
        public final static Property BIG_MP_PIC = new Property(32, String.class, "BIG_MP_PIC", false, "BIG__MP__PIC");
        public final static Property MP_PIC = new Property(33, String.class, "MP_PIC", false, "MP__PIC");
        public final static Property BQLX = new Property(34, String.class, "BQLX", false, "BQLX");
        public final static Property QRBZ = new Property(35, Integer.class, "QRBZ", false, "QRBZ");
        public final static Property BQYCZT = new Property(36, String.class, "BQYCZT", false, "BQYCZT");
        public final static Property BQZT = new Property(37, String.class, "BQZT", false, "BQZT");
    }


    public CORRECT_ASSETDao(DaoConfig config) {
        super(config);
    }
    
    public CORRECT_ASSETDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CORRECT__ASSET\" (" + //
                "\"ID\" INTEGER PRIMARY KEY ," + // 0: ID
                "\"ASSET__ID\" INTEGER," + // 1: ASSET_ID
                "\"DDID\" TEXT," + // 2: DDID
                "\"DDMC\" TEXT," + // 3: DDMC
                "\"GHDWID\" TEXT," + // 4: GHDWID
                "\"GHDWMC\" TEXT," + // 5: GHDWMC
                "\"PPID\" TEXT," + // 6: PPID
                "\"PPMC\" TEXT," + // 7: PPMC
                "\"SCCJID\" TEXT," + // 8: SCCJID
                "\"SCCJMC\" TEXT," + // 9: SCCJMC
                "\"GGXH\" TEXT," + // 10: GGXH
                "\"SCBH\" TEXT," + // 11: SCBH
                "\"PP__FLAG\" INTEGER," + // 12: PP_FLAG
                "\"GHDW__FLAG\" INTEGER," + // 13: GHDW_FLAG
                "\"SCCJ__FLAG\" INTEGER," + // 14: SCCJ_FLAG
                "\"DD__FLAG\" INTEGER," + // 15: DD_FLAG
                "\"USERID\" TEXT," + // 16: USERID
                "\"USERNAME\" TEXT," + // 17: USERNAME
                "\"IS__UP\" INTEGER," + // 18: IS_UP
                "\"SCRQ\" INTEGER," + // 19: SCRQ
                "\"WZMC\" TEXT," + // 20: WZMC
                "\"FRONT__PIC\" TEXT," + // 21: FRONT_PIC
                "\"SIDE__PIC\" TEXT," + // 22: SIDE_PIC
                "\"BIG__FRONT__PIC\" TEXT," + // 23: BIG_FRONT_PIC
                "\"BIG__SIDE__PIC\" TEXT," + // 24: BIG_SIDE_PIC
                "\"ZMZ__FILE__ID\" INTEGER," + // 25: ZMZ_FILE_ID
                "\"CMZ__FILE__ID\" INTEGER," + // 26: CMZ_FILE_ID
                "\"QD__FLAG\" TEXT," + // 27: QD_FLAG
                "\"BGRID\" TEXT," + // 28: BGRID
                "\"BGRXM\" TEXT," + // 29: BGRXM
                "\"BGKSID\" TEXT," + // 30: BGKSID
                "\"BGKSMC\" TEXT," + // 31: BGKSMC
                "\"BIG__MP__PIC\" TEXT," + // 32: BIG_MP_PIC
                "\"MP__PIC\" TEXT," + // 33: MP_PIC
                "\"BQLX\" TEXT," + // 34: BQLX
                "\"QRBZ\" INTEGER," + // 35: QRBZ
                "\"BQYCZT\" TEXT," + // 36: BQYCZT
                "\"BQZT\" TEXT);"); // 37: BQZT
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CORRECT__ASSET\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CORRECT_ASSET entity) {
        stmt.clearBindings();
 
        Long ID = entity.getID();
        if (ID != null) {
            stmt.bindLong(1, ID);
        }
 
        Long ASSET_ID = entity.getASSET_ID();
        if (ASSET_ID != null) {
            stmt.bindLong(2, ASSET_ID);
        }
 
        String DDID = entity.getDDID();
        if (DDID != null) {
            stmt.bindString(3, DDID);
        }
 
        String DDMC = entity.getDDMC();
        if (DDMC != null) {
            stmt.bindString(4, DDMC);
        }
 
        String GHDWID = entity.getGHDWID();
        if (GHDWID != null) {
            stmt.bindString(5, GHDWID);
        }
 
        String GHDWMC = entity.getGHDWMC();
        if (GHDWMC != null) {
            stmt.bindString(6, GHDWMC);
        }
 
        String PPID = entity.getPPID();
        if (PPID != null) {
            stmt.bindString(7, PPID);
        }
 
        String PPMC = entity.getPPMC();
        if (PPMC != null) {
            stmt.bindString(8, PPMC);
        }
 
        String SCCJID = entity.getSCCJID();
        if (SCCJID != null) {
            stmt.bindString(9, SCCJID);
        }
 
        String SCCJMC = entity.getSCCJMC();
        if (SCCJMC != null) {
            stmt.bindString(10, SCCJMC);
        }
 
        String GGXH = entity.getGGXH();
        if (GGXH != null) {
            stmt.bindString(11, GGXH);
        }
 
        String SCBH = entity.getSCBH();
        if (SCBH != null) {
            stmt.bindString(12, SCBH);
        }
 
        Integer PP_FLAG = entity.getPP_FLAG();
        if (PP_FLAG != null) {
            stmt.bindLong(13, PP_FLAG);
        }
 
        Integer GHDW_FLAG = entity.getGHDW_FLAG();
        if (GHDW_FLAG != null) {
            stmt.bindLong(14, GHDW_FLAG);
        }
 
        Integer SCCJ_FLAG = entity.getSCCJ_FLAG();
        if (SCCJ_FLAG != null) {
            stmt.bindLong(15, SCCJ_FLAG);
        }
 
        Integer DD_FLAG = entity.getDD_FLAG();
        if (DD_FLAG != null) {
            stmt.bindLong(16, DD_FLAG);
        }
 
        String USERID = entity.getUSERID();
        if (USERID != null) {
            stmt.bindString(17, USERID);
        }
 
        String USERNAME = entity.getUSERNAME();
        if (USERNAME != null) {
            stmt.bindString(18, USERNAME);
        }
 
        Integer IS_UP = entity.getIS_UP();
        if (IS_UP != null) {
            stmt.bindLong(19, IS_UP);
        }
 
        java.util.Date SCRQ = entity.getSCRQ();
        if (SCRQ != null) {
            stmt.bindLong(20, SCRQ.getTime());
        }
 
        String WZMC = entity.getWZMC();
        if (WZMC != null) {
            stmt.bindString(21, WZMC);
        }
 
        String FRONT_PIC = entity.getFRONT_PIC();
        if (FRONT_PIC != null) {
            stmt.bindString(22, FRONT_PIC);
        }
 
        String SIDE_PIC = entity.getSIDE_PIC();
        if (SIDE_PIC != null) {
            stmt.bindString(23, SIDE_PIC);
        }
 
        String BIG_FRONT_PIC = entity.getBIG_FRONT_PIC();
        if (BIG_FRONT_PIC != null) {
            stmt.bindString(24, BIG_FRONT_PIC);
        }
 
        String BIG_SIDE_PIC = entity.getBIG_SIDE_PIC();
        if (BIG_SIDE_PIC != null) {
            stmt.bindString(25, BIG_SIDE_PIC);
        }
 
        Integer ZMZ_FILE_ID = entity.getZMZ_FILE_ID();
        if (ZMZ_FILE_ID != null) {
            stmt.bindLong(26, ZMZ_FILE_ID);
        }
 
        Integer CMZ_FILE_ID = entity.getCMZ_FILE_ID();
        if (CMZ_FILE_ID != null) {
            stmt.bindLong(27, CMZ_FILE_ID);
        }
 
        String QD_FLAG = entity.getQD_FLAG();
        if (QD_FLAG != null) {
            stmt.bindString(28, QD_FLAG);
        }
 
        String BGRID = entity.getBGRID();
        if (BGRID != null) {
            stmt.bindString(29, BGRID);
        }
 
        String BGRXM = entity.getBGRXM();
        if (BGRXM != null) {
            stmt.bindString(30, BGRXM);
        }
 
        String BGKSID = entity.getBGKSID();
        if (BGKSID != null) {
            stmt.bindString(31, BGKSID);
        }
 
        String BGKSMC = entity.getBGKSMC();
        if (BGKSMC != null) {
            stmt.bindString(32, BGKSMC);
        }
 
        String BIG_MP_PIC = entity.getBIG_MP_PIC();
        if (BIG_MP_PIC != null) {
            stmt.bindString(33, BIG_MP_PIC);
        }
 
        String MP_PIC = entity.getMP_PIC();
        if (MP_PIC != null) {
            stmt.bindString(34, MP_PIC);
        }
 
        String BQLX = entity.getBQLX();
        if (BQLX != null) {
            stmt.bindString(35, BQLX);
        }
 
        Integer QRBZ = entity.getQRBZ();
        if (QRBZ != null) {
            stmt.bindLong(36, QRBZ);
        }
 
        String BQYCZT = entity.getBQYCZT();
        if (BQYCZT != null) {
            stmt.bindString(37, BQYCZT);
        }
 
        String BQZT = entity.getBQZT();
        if (BQZT != null) {
            stmt.bindString(38, BQZT);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CORRECT_ASSET entity) {
        stmt.clearBindings();
 
        Long ID = entity.getID();
        if (ID != null) {
            stmt.bindLong(1, ID);
        }
 
        Long ASSET_ID = entity.getASSET_ID();
        if (ASSET_ID != null) {
            stmt.bindLong(2, ASSET_ID);
        }
 
        String DDID = entity.getDDID();
        if (DDID != null) {
            stmt.bindString(3, DDID);
        }
 
        String DDMC = entity.getDDMC();
        if (DDMC != null) {
            stmt.bindString(4, DDMC);
        }
 
        String GHDWID = entity.getGHDWID();
        if (GHDWID != null) {
            stmt.bindString(5, GHDWID);
        }
 
        String GHDWMC = entity.getGHDWMC();
        if (GHDWMC != null) {
            stmt.bindString(6, GHDWMC);
        }
 
        String PPID = entity.getPPID();
        if (PPID != null) {
            stmt.bindString(7, PPID);
        }
 
        String PPMC = entity.getPPMC();
        if (PPMC != null) {
            stmt.bindString(8, PPMC);
        }
 
        String SCCJID = entity.getSCCJID();
        if (SCCJID != null) {
            stmt.bindString(9, SCCJID);
        }
 
        String SCCJMC = entity.getSCCJMC();
        if (SCCJMC != null) {
            stmt.bindString(10, SCCJMC);
        }
 
        String GGXH = entity.getGGXH();
        if (GGXH != null) {
            stmt.bindString(11, GGXH);
        }
 
        String SCBH = entity.getSCBH();
        if (SCBH != null) {
            stmt.bindString(12, SCBH);
        }
 
        Integer PP_FLAG = entity.getPP_FLAG();
        if (PP_FLAG != null) {
            stmt.bindLong(13, PP_FLAG);
        }
 
        Integer GHDW_FLAG = entity.getGHDW_FLAG();
        if (GHDW_FLAG != null) {
            stmt.bindLong(14, GHDW_FLAG);
        }
 
        Integer SCCJ_FLAG = entity.getSCCJ_FLAG();
        if (SCCJ_FLAG != null) {
            stmt.bindLong(15, SCCJ_FLAG);
        }
 
        Integer DD_FLAG = entity.getDD_FLAG();
        if (DD_FLAG != null) {
            stmt.bindLong(16, DD_FLAG);
        }
 
        String USERID = entity.getUSERID();
        if (USERID != null) {
            stmt.bindString(17, USERID);
        }
 
        String USERNAME = entity.getUSERNAME();
        if (USERNAME != null) {
            stmt.bindString(18, USERNAME);
        }
 
        Integer IS_UP = entity.getIS_UP();
        if (IS_UP != null) {
            stmt.bindLong(19, IS_UP);
        }
 
        java.util.Date SCRQ = entity.getSCRQ();
        if (SCRQ != null) {
            stmt.bindLong(20, SCRQ.getTime());
        }
 
        String WZMC = entity.getWZMC();
        if (WZMC != null) {
            stmt.bindString(21, WZMC);
        }
 
        String FRONT_PIC = entity.getFRONT_PIC();
        if (FRONT_PIC != null) {
            stmt.bindString(22, FRONT_PIC);
        }
 
        String SIDE_PIC = entity.getSIDE_PIC();
        if (SIDE_PIC != null) {
            stmt.bindString(23, SIDE_PIC);
        }
 
        String BIG_FRONT_PIC = entity.getBIG_FRONT_PIC();
        if (BIG_FRONT_PIC != null) {
            stmt.bindString(24, BIG_FRONT_PIC);
        }
 
        String BIG_SIDE_PIC = entity.getBIG_SIDE_PIC();
        if (BIG_SIDE_PIC != null) {
            stmt.bindString(25, BIG_SIDE_PIC);
        }
 
        Integer ZMZ_FILE_ID = entity.getZMZ_FILE_ID();
        if (ZMZ_FILE_ID != null) {
            stmt.bindLong(26, ZMZ_FILE_ID);
        }
 
        Integer CMZ_FILE_ID = entity.getCMZ_FILE_ID();
        if (CMZ_FILE_ID != null) {
            stmt.bindLong(27, CMZ_FILE_ID);
        }
 
        String QD_FLAG = entity.getQD_FLAG();
        if (QD_FLAG != null) {
            stmt.bindString(28, QD_FLAG);
        }
 
        String BGRID = entity.getBGRID();
        if (BGRID != null) {
            stmt.bindString(29, BGRID);
        }
 
        String BGRXM = entity.getBGRXM();
        if (BGRXM != null) {
            stmt.bindString(30, BGRXM);
        }
 
        String BGKSID = entity.getBGKSID();
        if (BGKSID != null) {
            stmt.bindString(31, BGKSID);
        }
 
        String BGKSMC = entity.getBGKSMC();
        if (BGKSMC != null) {
            stmt.bindString(32, BGKSMC);
        }
 
        String BIG_MP_PIC = entity.getBIG_MP_PIC();
        if (BIG_MP_PIC != null) {
            stmt.bindString(33, BIG_MP_PIC);
        }
 
        String MP_PIC = entity.getMP_PIC();
        if (MP_PIC != null) {
            stmt.bindString(34, MP_PIC);
        }
 
        String BQLX = entity.getBQLX();
        if (BQLX != null) {
            stmt.bindString(35, BQLX);
        }
 
        Integer QRBZ = entity.getQRBZ();
        if (QRBZ != null) {
            stmt.bindLong(36, QRBZ);
        }
 
        String BQYCZT = entity.getBQYCZT();
        if (BQYCZT != null) {
            stmt.bindString(37, BQYCZT);
        }
 
        String BQZT = entity.getBQZT();
        if (BQZT != null) {
            stmt.bindString(38, BQZT);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CORRECT_ASSET readEntity(Cursor cursor, int offset) {
        CORRECT_ASSET entity = new CORRECT_ASSET( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // ID
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // ASSET_ID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // DDID
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // DDMC
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // GHDWID
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // GHDWMC
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // PPID
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // PPMC
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // SCCJID
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // SCCJMC
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // GGXH
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // SCBH
            cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12), // PP_FLAG
            cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13), // GHDW_FLAG
            cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14), // SCCJ_FLAG
            cursor.isNull(offset + 15) ? null : cursor.getInt(offset + 15), // DD_FLAG
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // USERID
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // USERNAME
            cursor.isNull(offset + 18) ? null : cursor.getInt(offset + 18), // IS_UP
            cursor.isNull(offset + 19) ? null : new java.util.Date(cursor.getLong(offset + 19)), // SCRQ
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // WZMC
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // FRONT_PIC
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // SIDE_PIC
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // BIG_FRONT_PIC
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // BIG_SIDE_PIC
            cursor.isNull(offset + 25) ? null : cursor.getInt(offset + 25), // ZMZ_FILE_ID
            cursor.isNull(offset + 26) ? null : cursor.getInt(offset + 26), // CMZ_FILE_ID
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // QD_FLAG
            cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28), // BGRID
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // BGRXM
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // BGKSID
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // BGKSMC
            cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32), // BIG_MP_PIC
            cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33), // MP_PIC
            cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34), // BQLX
            cursor.isNull(offset + 35) ? null : cursor.getInt(offset + 35), // QRBZ
            cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36), // BQYCZT
            cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37) // BQZT
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CORRECT_ASSET entity, int offset) {
        entity.setID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setASSET_ID(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setDDID(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDDMC(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setGHDWID(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setGHDWMC(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPPID(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setPPMC(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setSCCJID(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setSCCJMC(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setGGXH(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setSCBH(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setPP_FLAG(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
        entity.setGHDW_FLAG(cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13));
        entity.setSCCJ_FLAG(cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14));
        entity.setDD_FLAG(cursor.isNull(offset + 15) ? null : cursor.getInt(offset + 15));
        entity.setUSERID(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setUSERNAME(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setIS_UP(cursor.isNull(offset + 18) ? null : cursor.getInt(offset + 18));
        entity.setSCRQ(cursor.isNull(offset + 19) ? null : new java.util.Date(cursor.getLong(offset + 19)));
        entity.setWZMC(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setFRONT_PIC(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setSIDE_PIC(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setBIG_FRONT_PIC(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setBIG_SIDE_PIC(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setZMZ_FILE_ID(cursor.isNull(offset + 25) ? null : cursor.getInt(offset + 25));
        entity.setCMZ_FILE_ID(cursor.isNull(offset + 26) ? null : cursor.getInt(offset + 26));
        entity.setQD_FLAG(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setBGRID(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
        entity.setBGRXM(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setBGKSID(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setBGKSMC(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setBIG_MP_PIC(cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32));
        entity.setMP_PIC(cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33));
        entity.setBQLX(cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34));
        entity.setQRBZ(cursor.isNull(offset + 35) ? null : cursor.getInt(offset + 35));
        entity.setBQYCZT(cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36));
        entity.setBQZT(cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CORRECT_ASSET entity, long rowId) {
        entity.setID(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CORRECT_ASSET entity) {
        if(entity != null) {
            return entity.getID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CORRECT_ASSET entity) {
        return entity.getID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}