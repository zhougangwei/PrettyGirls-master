package coder.aihui.data.bean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Entity mapped to table IN__MATERIALS__WZMC.
 */
@Entity
public class IN_MATERIALS_WZMC extends BaseIndexPinyinBean {

    @Id
    private Long ID;
    private String MCID;
    private String WZMC;
    private String GGXH;
    private String ZXDW;
    private Double CKDJ;
    private String PPMC;
    private Long PPID;

    @Transient
    private boolean IS_CHECKED;

    @Generated(hash = 1080167745)
    public IN_MATERIALS_WZMC(Long ID, String MCID, String WZMC, String GGXH, String ZXDW, Double CKDJ,
            String PPMC, Long PPID) {
        this.ID = ID;
        this.MCID = MCID;
        this.WZMC = WZMC;
        this.GGXH = GGXH;
        this.ZXDW = ZXDW;
        this.CKDJ = CKDJ;
        this.PPMC = PPMC;
        this.PPID = PPID;
    }
    @Generated(hash = 1438808694)
    public IN_MATERIALS_WZMC() {
    }

    @Override
    public String getTarget() {
        return getWZMC();
    }

    public Long getID() {
        return this.ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public String getMCID() {
        return this.MCID;
    }
    public void setMCID(String MCID) {
        this.MCID = MCID;
    }
    public String getWZMC() {
        return this.WZMC;
    }
    public void setWZMC(String WZMC) {
        this.WZMC = WZMC;
    }
    public String getGGXH() {
        return this.GGXH;
    }
    public void setGGXH(String GGXH) {
        this.GGXH = GGXH;
    }
    public String getZXDW() {
        return this.ZXDW;
    }
    public void setZXDW(String ZXDW) {
        this.ZXDW = ZXDW;
    }
    public Double getCKDJ() {
        return this.CKDJ;
    }
    public void setCKDJ(Double CKDJ) {
        this.CKDJ = CKDJ;
    }
    public String getPPMC() {
        return this.PPMC;
    }
    public void setPPMC(String PPMC) {
        this.PPMC = PPMC;
    }
    public Long getPPID() {
        return this.PPID;
    }
    public void setPPID(Long PPID) {
        this.PPID = PPID;
    }

    public boolean getIS_CHECKED() {
        return IS_CHECKED;
    }

    public void setIS_CHECKED(boolean IS_CHECKED) {
        this.IS_CHECKED = IS_CHECKED;
    }
}
