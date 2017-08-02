package coder.aihui.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * @ 创建者   zhou
 * @ 创建时间   2016/12/28 9:58
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2016/12/28$
 * @ 更新描述  ${TODO}
 */
@Entity
public class DqxqOutBean {

    @Id
    private Integer ID;                //id

    @Property(nameInDb = "GGXH")
    private String  GGXH;

    @Property(nameInDb = "TEMPLET_NAME")
    private String  TEMPLET_NAME;       //模板名

    @Property(nameInDb = "JCYQ")
    private String  JCYQ;            //检测仪器
    @Property(nameInDb = "SCCJ")
    private String  SCCJ;                //生产厂家
    @Property(nameInDb = "REMARK")
    private String  REMARK;            //备注
    @Property(nameInDb = "ZFBZ")
    private Integer ZFBZ;               //作废标志
    @Generated(hash = 1129004542)
    public DqxqOutBean(Integer ID, String GGXH, String TEMPLET_NAME, String JCYQ,
            String SCCJ, String REMARK, Integer ZFBZ) {
        this.ID = ID;
        this.GGXH = GGXH;
        this.TEMPLET_NAME = TEMPLET_NAME;
        this.JCYQ = JCYQ;
        this.SCCJ = SCCJ;
        this.REMARK = REMARK;
        this.ZFBZ = ZFBZ;
    }

    @Generated(hash = 1603351983)
    public DqxqOutBean() {
    }
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public Integer getZFBZ() {
        return ZFBZ;
    }

    public void setZFBZ(Integer ZFBZ) {
        this.ZFBZ = ZFBZ;
    }

    /**
     * GGXH :
     * TEMPLET_NAME : 医疗设备电气安全质量检测原始记录
     * JCYQ : 电气安全检测仪
     * SCCJ : Fluke

     */


    public String getGGXH() {
        return GGXH;
    }

    public void setGGXH(String GGXH) {
        this.GGXH = GGXH;
    }

    public String getTEMPLET_NAME() {
        return TEMPLET_NAME;
    }

    public void setTEMPLET_NAME(String TEMPLET_NAME) {
        this.TEMPLET_NAME = TEMPLET_NAME;
    }

    public String getJCYQ() {
        return JCYQ;
    }

    public void setJCYQ(String JCYQ) {
        this.JCYQ = JCYQ;
    }

    public String getSCCJ() {
        return SCCJ;
    }

    public void setSCCJ(String SCCJ) {
        this.SCCJ = SCCJ;
    }

    @Override
    public String toString() {
        return "DqxqOutBean{" +
                "ID=" + ID +
                ", GGXH='" + GGXH + '\'' +
                ", TEMPLET_NAME='" + TEMPLET_NAME + '\'' +
                ", JCYQ='" + JCYQ + '\'' +
                ", SCCJ='" + SCCJ + '\'' +
                ", REMARK='" + REMARK + '\'' +
                ", ZFBZ=" + ZFBZ +
                '}';
    }
}
