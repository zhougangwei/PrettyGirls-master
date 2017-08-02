package coder.aihui.data.bean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

@Entity
public class PUR_CONTRACT_PLAN_DETAIL implements Serializable, Comparable<PUR_CONTRACT_PLAN_DETAIL>{

    private static final long serialVersionUID = -697140845867174347L;
    @Id
    private Long    ID;
    private Long    HTMX_ID;
    private Integer CONTRACT_ID;
    private String  CONTRACT_NUM;
    private Integer PLAN_ID;
    private Integer DEPT_ID;
    private String  DEPT_NAME;
    private String  WZMC;
    private Integer YSSL;
    private Integer MCGGID;
    private String  GYSMC;
    private String  GGXH;
    private Integer CHECK_SL;
    private Integer CHECK_STATUS;       //1是已检 2是未检
    private String  DH_ID;
    private Boolean CHECK_ISCHECKED;
    private Integer ALQJ;
    private Integer JLZM;
    private Integer WGJC;
    private Integer HWQD;
    private Integer YQGZQK;
    private Integer DQAQJC;
    private String CCBH;
    private java.util.Date YSSJ;
    private Integer PUR_YSR_ID;
    private Integer JQZP_FILE_ID;
    private Integer JDSQ_FILE_ID;
    private String ZMZP_URL;
    private String CMZP_URL;
    private Integer IS_UP;
    private Float JYSYNX;
    private String ZCZH;
    private java.util.Date QSDQSJ;
    private String YSR_IDS;
    private String PARTS;
    private String KSQSR_FILE_URL;
    private Integer KSQSR_FILE_ID;
    private String QMID;
    private String ESC_RECORD;
    private String ESC_RECORDS;
    private Long KSQSR_ID;
    private Long BXDW_ID;
    private String BXDW_NAME;
    private Long LXR_ID;
    private String LXRMC;
    private String LXFS;
    private Integer NEW_FLAG;
    private String CHECK_ITEMS;
    private String AZGCS;
    private Integer BXQ;
    private String GCJK;
    private String BRAND;
    private String BZ;
    private String CD_AZR;
    private String CD_AZRDH;
    private String CD_REMARK;
    private String BIG_FRONT_PIC;
    private String BIG_SIDE_PIC;
    private String BIG_MPZ_PIC;
    private Integer MPZ_FILE_ID;
    private String MPZ_URL;



    @Generated(hash = 1008746047)
    public PUR_CONTRACT_PLAN_DETAIL(Long ID, Long HTMX_ID, Integer CONTRACT_ID, String CONTRACT_NUM,
            Integer PLAN_ID, Integer DEPT_ID, String DEPT_NAME, String WZMC, Integer YSSL,
            Integer MCGGID, String GYSMC, String GGXH, Integer CHECK_SL, Integer CHECK_STATUS,
            String DH_ID, Boolean CHECK_ISCHECKED, Integer ALQJ, Integer JLZM, Integer WGJC,
            Integer HWQD, Integer YQGZQK, Integer DQAQJC, String CCBH, java.util.Date YSSJ,
            Integer PUR_YSR_ID, Integer JQZP_FILE_ID, Integer JDSQ_FILE_ID, String ZMZP_URL,
            String CMZP_URL, Integer IS_UP, Float JYSYNX, String ZCZH, java.util.Date QSDQSJ,
            String YSR_IDS, String PARTS, String KSQSR_FILE_URL, Integer KSQSR_FILE_ID, String QMID,
            String ESC_RECORD, String ESC_RECORDS, Long KSQSR_ID, Long BXDW_ID, String BXDW_NAME,
            Long LXR_ID, String LXRMC, String LXFS, Integer NEW_FLAG, String CHECK_ITEMS, String AZGCS,
            Integer BXQ, String GCJK, String BRAND, String BZ, String CD_AZR, String CD_AZRDH,
            String CD_REMARK, String BIG_FRONT_PIC, String BIG_SIDE_PIC, String BIG_MPZ_PIC,
            Integer MPZ_FILE_ID, String MPZ_URL) {
        this.ID = ID;
        this.HTMX_ID = HTMX_ID;
        this.CONTRACT_ID = CONTRACT_ID;
        this.CONTRACT_NUM = CONTRACT_NUM;
        this.PLAN_ID = PLAN_ID;
        this.DEPT_ID = DEPT_ID;
        this.DEPT_NAME = DEPT_NAME;
        this.WZMC = WZMC;
        this.YSSL = YSSL;
        this.MCGGID = MCGGID;
        this.GYSMC = GYSMC;
        this.GGXH = GGXH;
        this.CHECK_SL = CHECK_SL;
        this.CHECK_STATUS = CHECK_STATUS;
        this.DH_ID = DH_ID;
        this.CHECK_ISCHECKED = CHECK_ISCHECKED;
        this.ALQJ = ALQJ;
        this.JLZM = JLZM;
        this.WGJC = WGJC;
        this.HWQD = HWQD;
        this.YQGZQK = YQGZQK;
        this.DQAQJC = DQAQJC;
        this.CCBH = CCBH;
        this.YSSJ = YSSJ;
        this.PUR_YSR_ID = PUR_YSR_ID;
        this.JQZP_FILE_ID = JQZP_FILE_ID;
        this.JDSQ_FILE_ID = JDSQ_FILE_ID;
        this.ZMZP_URL = ZMZP_URL;
        this.CMZP_URL = CMZP_URL;
        this.IS_UP = IS_UP;
        this.JYSYNX = JYSYNX;
        this.ZCZH = ZCZH;
        this.QSDQSJ = QSDQSJ;
        this.YSR_IDS = YSR_IDS;
        this.PARTS = PARTS;
        this.KSQSR_FILE_URL = KSQSR_FILE_URL;
        this.KSQSR_FILE_ID = KSQSR_FILE_ID;
        this.QMID = QMID;
        this.ESC_RECORD = ESC_RECORD;
        this.ESC_RECORDS = ESC_RECORDS;
        this.KSQSR_ID = KSQSR_ID;
        this.BXDW_ID = BXDW_ID;
        this.BXDW_NAME = BXDW_NAME;
        this.LXR_ID = LXR_ID;
        this.LXRMC = LXRMC;
        this.LXFS = LXFS;
        this.NEW_FLAG = NEW_FLAG;
        this.CHECK_ITEMS = CHECK_ITEMS;
        this.AZGCS = AZGCS;
        this.BXQ = BXQ;
        this.GCJK = GCJK;
        this.BRAND = BRAND;
        this.BZ = BZ;
        this.CD_AZR = CD_AZR;
        this.CD_AZRDH = CD_AZRDH;
        this.CD_REMARK = CD_REMARK;
        this.BIG_FRONT_PIC = BIG_FRONT_PIC;
        this.BIG_SIDE_PIC = BIG_SIDE_PIC;
        this.BIG_MPZ_PIC = BIG_MPZ_PIC;
        this.MPZ_FILE_ID = MPZ_FILE_ID;
        this.MPZ_URL = MPZ_URL;
    }



    @Generated(hash = 1809080090)
    public PUR_CONTRACT_PLAN_DETAIL() {
    }



    @Override
    public int compareTo(PUR_CONTRACT_PLAN_DETAIL another) {

        Integer s10 = Integer.parseInt(this.getDH_ID().split("-")[0]);
        Integer s11 = Integer.parseInt(this.getDH_ID().split("-")[1]);
        Integer s20 = Integer.parseInt(another.getDH_ID().split("-")[0]);
        Integer s21 = Integer.parseInt(another.getDH_ID().split("-")[1]);
        return s10 - s20 == 0 ? s11 - s21 : s10 - s20;


    }



    public Long getID() {
        return this.ID;
    }



    public void setID(Long ID) {
        this.ID = ID;
    }



    public Long getHTMX_ID() {
        return this.HTMX_ID;
    }



    public void setHTMX_ID(Long HTMX_ID) {
        this.HTMX_ID = HTMX_ID;
    }



    public Integer getCONTRACT_ID() {
        return this.CONTRACT_ID;
    }



    public void setCONTRACT_ID(Integer CONTRACT_ID) {
        this.CONTRACT_ID = CONTRACT_ID;
    }



    public String getCONTRACT_NUM() {
        return this.CONTRACT_NUM;
    }



    public void setCONTRACT_NUM(String CONTRACT_NUM) {
        this.CONTRACT_NUM = CONTRACT_NUM;
    }



    public Integer getPLAN_ID() {
        return this.PLAN_ID;
    }



    public void setPLAN_ID(Integer PLAN_ID) {
        this.PLAN_ID = PLAN_ID;
    }



    public Integer getDEPT_ID() {
        return this.DEPT_ID;
    }



    public void setDEPT_ID(Integer DEPT_ID) {
        this.DEPT_ID = DEPT_ID;
    }



    public String getDEPT_NAME() {
        return this.DEPT_NAME;
    }



    public void setDEPT_NAME(String DEPT_NAME) {
        this.DEPT_NAME = DEPT_NAME;
    }



    public String getWZMC() {
        return this.WZMC;
    }



    public void setWZMC(String WZMC) {
        this.WZMC = WZMC;
    }



    public Integer getYSSL() {
        return this.YSSL;
    }



    public void setYSSL(Integer YSSL) {
        this.YSSL = YSSL;
    }



    public Integer getMCGGID() {
        return this.MCGGID;
    }



    public void setMCGGID(Integer MCGGID) {
        this.MCGGID = MCGGID;
    }



    public String getGYSMC() {
        return this.GYSMC;
    }



    public void setGYSMC(String GYSMC) {
        this.GYSMC = GYSMC;
    }



    public String getGGXH() {
        return this.GGXH;
    }



    public void setGGXH(String GGXH) {
        this.GGXH = GGXH;
    }



    public Integer getCHECK_SL() {
        return this.CHECK_SL;
    }



    public void setCHECK_SL(Integer CHECK_SL) {
        this.CHECK_SL = CHECK_SL;
    }



    public Integer getCHECK_STATUS() {
        return this.CHECK_STATUS;
    }



    public void setCHECK_STATUS(Integer CHECK_STATUS) {
        this.CHECK_STATUS = CHECK_STATUS;
    }



    public String getDH_ID() {
        return this.DH_ID;
    }



    public void setDH_ID(String DH_ID) {
        this.DH_ID = DH_ID;
    }



    public Boolean getCHECK_ISCHECKED() {
        return this.CHECK_ISCHECKED;
    }



    public void setCHECK_ISCHECKED(Boolean CHECK_ISCHECKED) {
        this.CHECK_ISCHECKED = CHECK_ISCHECKED;
    }



    public Integer getALQJ() {
        return this.ALQJ;
    }



    public void setALQJ(Integer ALQJ) {
        this.ALQJ = ALQJ;
    }



    public Integer getJLZM() {
        return this.JLZM;
    }



    public void setJLZM(Integer JLZM) {
        this.JLZM = JLZM;
    }



    public Integer getWGJC() {
        return this.WGJC;
    }



    public void setWGJC(Integer WGJC) {
        this.WGJC = WGJC;
    }



    public Integer getHWQD() {
        return this.HWQD;
    }



    public void setHWQD(Integer HWQD) {
        this.HWQD = HWQD;
    }



    public Integer getYQGZQK() {
        return this.YQGZQK;
    }



    public void setYQGZQK(Integer YQGZQK) {
        this.YQGZQK = YQGZQK;
    }



    public Integer getDQAQJC() {
        return this.DQAQJC;
    }



    public void setDQAQJC(Integer DQAQJC) {
        this.DQAQJC = DQAQJC;
    }



    public String getCCBH() {
        return this.CCBH;
    }



    public void setCCBH(String CCBH) {
        this.CCBH = CCBH;
    }



    public java.util.Date getYSSJ() {
        return this.YSSJ;
    }



    public void setYSSJ(java.util.Date YSSJ) {
        this.YSSJ = YSSJ;
    }



    public Integer getPUR_YSR_ID() {
        return this.PUR_YSR_ID;
    }



    public void setPUR_YSR_ID(Integer PUR_YSR_ID) {
        this.PUR_YSR_ID = PUR_YSR_ID;
    }



    public Integer getJQZP_FILE_ID() {
        return this.JQZP_FILE_ID;
    }



    public void setJQZP_FILE_ID(Integer JQZP_FILE_ID) {
        this.JQZP_FILE_ID = JQZP_FILE_ID;
    }



    public Integer getJDSQ_FILE_ID() {
        return this.JDSQ_FILE_ID;
    }



    public void setJDSQ_FILE_ID(Integer JDSQ_FILE_ID) {
        this.JDSQ_FILE_ID = JDSQ_FILE_ID;
    }



    public String getZMZP_URL() {
        return this.ZMZP_URL;
    }



    public void setZMZP_URL(String ZMZP_URL) {
        this.ZMZP_URL = ZMZP_URL;
    }



    public String getCMZP_URL() {
        return this.CMZP_URL;
    }



    public void setCMZP_URL(String CMZP_URL) {
        this.CMZP_URL = CMZP_URL;
    }



    public Integer getIS_UP() {
        return this.IS_UP;
    }



    public void setIS_UP(Integer IS_UP) {
        this.IS_UP = IS_UP;
    }



    public Float getJYSYNX() {
        return this.JYSYNX;
    }



    public void setJYSYNX(Float JYSYNX) {
        this.JYSYNX = JYSYNX;
    }



    public String getZCZH() {
        return this.ZCZH;
    }



    public void setZCZH(String ZCZH) {
        this.ZCZH = ZCZH;
    }



    public java.util.Date getQSDQSJ() {
        return this.QSDQSJ;
    }



    public void setQSDQSJ(java.util.Date QSDQSJ) {
        this.QSDQSJ = QSDQSJ;
    }



    public String getYSR_IDS() {
        return this.YSR_IDS;
    }



    public void setYSR_IDS(String YSR_IDS) {
        this.YSR_IDS = YSR_IDS;
    }



    public String getPARTS() {
        return this.PARTS;
    }



    public void setPARTS(String PARTS) {
        this.PARTS = PARTS;
    }



    public String getKSQSR_FILE_URL() {
        return this.KSQSR_FILE_URL;
    }



    public void setKSQSR_FILE_URL(String KSQSR_FILE_URL) {
        this.KSQSR_FILE_URL = KSQSR_FILE_URL;
    }



    public Integer getKSQSR_FILE_ID() {
        return this.KSQSR_FILE_ID;
    }



    public void setKSQSR_FILE_ID(Integer KSQSR_FILE_ID) {
        this.KSQSR_FILE_ID = KSQSR_FILE_ID;
    }



    public String getQMID() {
        return this.QMID;
    }



    public void setQMID(String QMID) {
        this.QMID = QMID;
    }



    public String getESC_RECORD() {
        return this.ESC_RECORD;
    }



    public void setESC_RECORD(String ESC_RECORD) {
        this.ESC_RECORD = ESC_RECORD;
    }



    public String getESC_RECORDS() {
        return this.ESC_RECORDS;
    }



    public void setESC_RECORDS(String ESC_RECORDS) {
        this.ESC_RECORDS = ESC_RECORDS;
    }



    public Long getKSQSR_ID() {
        return this.KSQSR_ID;
    }



    public void setKSQSR_ID(Long KSQSR_ID) {
        this.KSQSR_ID = KSQSR_ID;
    }



    public Long getBXDW_ID() {
        return this.BXDW_ID;
    }



    public void setBXDW_ID(Long BXDW_ID) {
        this.BXDW_ID = BXDW_ID;
    }



    public String getBXDW_NAME() {
        return this.BXDW_NAME;
    }



    public void setBXDW_NAME(String BXDW_NAME) {
        this.BXDW_NAME = BXDW_NAME;
    }



    public Long getLXR_ID() {
        return this.LXR_ID;
    }



    public void setLXR_ID(Long LXR_ID) {
        this.LXR_ID = LXR_ID;
    }



    public String getLXRMC() {
        return this.LXRMC;
    }



    public void setLXRMC(String LXRMC) {
        this.LXRMC = LXRMC;
    }



    public String getLXFS() {
        return this.LXFS;
    }



    public void setLXFS(String LXFS) {
        this.LXFS = LXFS;
    }



    public Integer getNEW_FLAG() {
        return this.NEW_FLAG;
    }



    public void setNEW_FLAG(Integer NEW_FLAG) {
        this.NEW_FLAG = NEW_FLAG;
    }



    public String getCHECK_ITEMS() {
        return this.CHECK_ITEMS;
    }



    public void setCHECK_ITEMS(String CHECK_ITEMS) {
        this.CHECK_ITEMS = CHECK_ITEMS;
    }



    public String getAZGCS() {
        return this.AZGCS;
    }



    public void setAZGCS(String AZGCS) {
        this.AZGCS = AZGCS;
    }



    public Integer getBXQ() {
        return this.BXQ;
    }



    public void setBXQ(Integer BXQ) {
        this.BXQ = BXQ;
    }



    public String getGCJK() {
        return this.GCJK;
    }



    public void setGCJK(String GCJK) {
        this.GCJK = GCJK;
    }



    public String getBRAND() {
        return this.BRAND;
    }



    public void setBRAND(String BRAND) {
        this.BRAND = BRAND;
    }



    public String getBZ() {
        return this.BZ;
    }



    public void setBZ(String BZ) {
        this.BZ = BZ;
    }



    public String getCD_AZR() {
        return this.CD_AZR;
    }



    public void setCD_AZR(String CD_AZR) {
        this.CD_AZR = CD_AZR;
    }



    public String getCD_AZRDH() {
        return this.CD_AZRDH;
    }



    public void setCD_AZRDH(String CD_AZRDH) {
        this.CD_AZRDH = CD_AZRDH;
    }



    public String getCD_REMARK() {
        return this.CD_REMARK;
    }



    public void setCD_REMARK(String CD_REMARK) {
        this.CD_REMARK = CD_REMARK;
    }



    public String getBIG_FRONT_PIC() {
        return this.BIG_FRONT_PIC;
    }



    public void setBIG_FRONT_PIC(String BIG_FRONT_PIC) {
        this.BIG_FRONT_PIC = BIG_FRONT_PIC;
    }



    public String getBIG_SIDE_PIC() {
        return this.BIG_SIDE_PIC;
    }



    public void setBIG_SIDE_PIC(String BIG_SIDE_PIC) {
        this.BIG_SIDE_PIC = BIG_SIDE_PIC;
    }



    public String getBIG_MPZ_PIC() {
        return this.BIG_MPZ_PIC;
    }



    public void setBIG_MPZ_PIC(String BIG_MPZ_PIC) {
        this.BIG_MPZ_PIC = BIG_MPZ_PIC;
    }



    public Integer getMPZ_FILE_ID() {
        return this.MPZ_FILE_ID;
    }



    public void setMPZ_FILE_ID(Integer MPZ_FILE_ID) {
        this.MPZ_FILE_ID = MPZ_FILE_ID;
    }



    public String getMPZ_URL() {
        return this.MPZ_URL;
    }



    public void setMPZ_URL(String MPZ_URL) {
        this.MPZ_URL = MPZ_URL;
    }

}
