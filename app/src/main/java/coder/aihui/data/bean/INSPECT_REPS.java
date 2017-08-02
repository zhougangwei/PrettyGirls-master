package coder.aihui.data.bean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Entity mapped to table INSPECT__REPS.
 */
@Entity
public class INSPECT_REPS {

    @Id
    @Property(nameInDb = "INSPR__ID")
    private Long    INSPR_ID;
    private Integer INSPR_REP_ID;


    private String INSPR_PNAME;

    private String INSPR_PCONTENT;

    private String INSPR_RE_VALUE;          //参考值


    private String INSPR_EXAM_VALUE;
    private String INSPR_EXAM_RESULT;

    private String INSPR_COMMENTS;

    private String INSPR_VAL_TYPE;

    private String  INSPR_SEL_VAL;
    private Integer INSPR_WX_NEED;
    private String  INSPR_UNIT;
    private String  INSPR_CYCLE;

    private String  INSPR_IS_FILL_IN;
    private String  INSPR_BZ;
    private Date    SYNC_DATE;
    private Integer SYNC_FLAG;
    private String  UUID;
    private String  PUUID;//关联父类的UUID

    private String INSPR_HG_VAL;
    private String FLAG;        //标识 是否选中 0是没选中 1是选中

    private String INSPR_WRONG_VAL;      //记录错误的数据

    private Long PLAN_ID;
    private Date SAVE_DATE;
    private Long NET_INSPRID;

    private String INSR_TYPE;       //类型


    private Long INSPR_METER_ID;           //主记录的Id
    private Long METER_PLAN_ID;            //计划ID


    private Integer Default;       //是否使用默认值 不存储到数据库 临时数据  1是使用默认值


    @Generated(hash = 1225058003)
    public INSPECT_REPS(Long INSPR_ID, Integer INSPR_REP_ID, String INSPR_PNAME, String INSPR_PCONTENT,
            String INSPR_RE_VALUE, String INSPR_EXAM_VALUE, String INSPR_EXAM_RESULT,
            String INSPR_COMMENTS, String INSPR_VAL_TYPE, String INSPR_SEL_VAL, Integer INSPR_WX_NEED,
            String INSPR_UNIT, String INSPR_CYCLE, String INSPR_IS_FILL_IN, String INSPR_BZ,
            Date SYNC_DATE, Integer SYNC_FLAG, String UUID, String PUUID, String INSPR_HG_VAL,
            String FLAG, String INSPR_WRONG_VAL, Long PLAN_ID, Date SAVE_DATE, Long NET_INSPRID,
            String INSR_TYPE, Long INSPR_METER_ID, Long METER_PLAN_ID, Integer Default) {
        this.INSPR_ID = INSPR_ID;
        this.INSPR_REP_ID = INSPR_REP_ID;
        this.INSPR_PNAME = INSPR_PNAME;
        this.INSPR_PCONTENT = INSPR_PCONTENT;
        this.INSPR_RE_VALUE = INSPR_RE_VALUE;
        this.INSPR_EXAM_VALUE = INSPR_EXAM_VALUE;
        this.INSPR_EXAM_RESULT = INSPR_EXAM_RESULT;
        this.INSPR_COMMENTS = INSPR_COMMENTS;
        this.INSPR_VAL_TYPE = INSPR_VAL_TYPE;
        this.INSPR_SEL_VAL = INSPR_SEL_VAL;
        this.INSPR_WX_NEED = INSPR_WX_NEED;
        this.INSPR_UNIT = INSPR_UNIT;
        this.INSPR_CYCLE = INSPR_CYCLE;
        this.INSPR_IS_FILL_IN = INSPR_IS_FILL_IN;
        this.INSPR_BZ = INSPR_BZ;
        this.SYNC_DATE = SYNC_DATE;
        this.SYNC_FLAG = SYNC_FLAG;
        this.UUID = UUID;
        this.PUUID = PUUID;
        this.INSPR_HG_VAL = INSPR_HG_VAL;
        this.FLAG = FLAG;
        this.INSPR_WRONG_VAL = INSPR_WRONG_VAL;
        this.PLAN_ID = PLAN_ID;
        this.SAVE_DATE = SAVE_DATE;
        this.NET_INSPRID = NET_INSPRID;
        this.INSR_TYPE = INSR_TYPE;
        this.INSPR_METER_ID = INSPR_METER_ID;
        this.METER_PLAN_ID = METER_PLAN_ID;
        this.Default = Default;
    }


    @Generated(hash = 164296842)
    public INSPECT_REPS() {
    }


    public Long getINSPR_ID() {
        return this.INSPR_ID;
    }


    public void setINSPR_ID(Long INSPR_ID) {
        this.INSPR_ID = INSPR_ID;
    }


    public Integer getINSPR_REP_ID() {
        return this.INSPR_REP_ID;
    }


    public void setINSPR_REP_ID(Integer INSPR_REP_ID) {
        this.INSPR_REP_ID = INSPR_REP_ID;
    }


    public String getINSPR_PNAME() {
        return this.INSPR_PNAME;
    }


    public void setINSPR_PNAME(String INSPR_PNAME) {
        this.INSPR_PNAME = INSPR_PNAME;
    }


    public String getINSPR_PCONTENT() {
        return this.INSPR_PCONTENT;
    }


    public void setINSPR_PCONTENT(String INSPR_PCONTENT) {
        this.INSPR_PCONTENT = INSPR_PCONTENT;
    }


    public String getINSPR_RE_VALUE() {
        return this.INSPR_RE_VALUE;
    }


    public void setINSPR_RE_VALUE(String INSPR_RE_VALUE) {
        this.INSPR_RE_VALUE = INSPR_RE_VALUE;
    }


    public String getINSPR_EXAM_VALUE() {
        return this.INSPR_EXAM_VALUE;
    }


    public void setINSPR_EXAM_VALUE(String INSPR_EXAM_VALUE) {
        this.INSPR_EXAM_VALUE = INSPR_EXAM_VALUE;
    }


    public String getINSPR_EXAM_RESULT() {
        return this.INSPR_EXAM_RESULT;
    }


    public void setINSPR_EXAM_RESULT(String INSPR_EXAM_RESULT) {
        this.INSPR_EXAM_RESULT = INSPR_EXAM_RESULT;
    }


    public String getINSPR_COMMENTS() {
        return this.INSPR_COMMENTS;
    }


    public void setINSPR_COMMENTS(String INSPR_COMMENTS) {
        this.INSPR_COMMENTS = INSPR_COMMENTS;
    }


    public String getINSPR_VAL_TYPE() {
        return this.INSPR_VAL_TYPE;
    }


    public void setINSPR_VAL_TYPE(String INSPR_VAL_TYPE) {
        this.INSPR_VAL_TYPE = INSPR_VAL_TYPE;
    }


    public String getINSPR_SEL_VAL() {
        return this.INSPR_SEL_VAL;
    }


    public void setINSPR_SEL_VAL(String INSPR_SEL_VAL) {
        this.INSPR_SEL_VAL = INSPR_SEL_VAL;
    }


    public Integer getINSPR_WX_NEED() {
        return this.INSPR_WX_NEED;
    }


    public void setINSPR_WX_NEED(Integer INSPR_WX_NEED) {
        this.INSPR_WX_NEED = INSPR_WX_NEED;
    }


    public String getINSPR_UNIT() {
        return this.INSPR_UNIT;
    }


    public void setINSPR_UNIT(String INSPR_UNIT) {
        this.INSPR_UNIT = INSPR_UNIT;
    }


    public String getINSPR_CYCLE() {
        return this.INSPR_CYCLE;
    }


    public void setINSPR_CYCLE(String INSPR_CYCLE) {
        this.INSPR_CYCLE = INSPR_CYCLE;
    }


    public String getINSPR_IS_FILL_IN() {
        return this.INSPR_IS_FILL_IN;
    }


    public void setINSPR_IS_FILL_IN(String INSPR_IS_FILL_IN) {
        this.INSPR_IS_FILL_IN = INSPR_IS_FILL_IN;
    }


    public String getINSPR_BZ() {
        return this.INSPR_BZ;
    }


    public void setINSPR_BZ(String INSPR_BZ) {
        this.INSPR_BZ = INSPR_BZ;
    }


    public Date getSYNC_DATE() {
        return this.SYNC_DATE;
    }


    public void setSYNC_DATE(Date SYNC_DATE) {
        this.SYNC_DATE = SYNC_DATE;
    }


    public Integer getSYNC_FLAG() {
        return this.SYNC_FLAG;
    }


    public void setSYNC_FLAG(Integer SYNC_FLAG) {
        this.SYNC_FLAG = SYNC_FLAG;
    }


    public String getUUID() {
        return this.UUID;
    }


    public void setUUID(String UUID) {
        this.UUID = UUID;
    }


    public String getPUUID() {
        return this.PUUID;
    }


    public void setPUUID(String PUUID) {
        this.PUUID = PUUID;
    }


    public String getINSPR_HG_VAL() {
        return this.INSPR_HG_VAL;
    }


    public void setINSPR_HG_VAL(String INSPR_HG_VAL) {
        this.INSPR_HG_VAL = INSPR_HG_VAL;
    }


    public String getFLAG() {
        return this.FLAG;
    }


    public void setFLAG(String FLAG) {
        this.FLAG = FLAG;
    }


    public String getINSPR_WRONG_VAL() {
        return this.INSPR_WRONG_VAL;
    }


    public void setINSPR_WRONG_VAL(String INSPR_WRONG_VAL) {
        this.INSPR_WRONG_VAL = INSPR_WRONG_VAL;
    }


    public Long getPLAN_ID() {
        return this.PLAN_ID;
    }


    public void setPLAN_ID(Long PLAN_ID) {
        this.PLAN_ID = PLAN_ID;
    }


    public Date getSAVE_DATE() {
        return this.SAVE_DATE;
    }


    public void setSAVE_DATE(Date SAVE_DATE) {
        this.SAVE_DATE = SAVE_DATE;
    }


    public Long getNET_INSPRID() {
        return this.NET_INSPRID;
    }


    public void setNET_INSPRID(Long NET_INSPRID) {
        this.NET_INSPRID = NET_INSPRID;
    }


    public String getINSR_TYPE() {
        return this.INSR_TYPE;
    }


    public void setINSR_TYPE(String INSR_TYPE) {
        this.INSR_TYPE = INSR_TYPE;
    }


    public Long getINSPR_METER_ID() {
        return this.INSPR_METER_ID;
    }


    public void setINSPR_METER_ID(Long INSPR_METER_ID) {
        this.INSPR_METER_ID = INSPR_METER_ID;
    }


    public Long getMETER_PLAN_ID() {
        return this.METER_PLAN_ID;
    }


    public void setMETER_PLAN_ID(Long METER_PLAN_ID) {
        this.METER_PLAN_ID = METER_PLAN_ID;
    }


    public Integer getDefault() {
        return this.Default;
    }


    public void setDefault(Integer Default) {
        this.Default = Default;
    }


}