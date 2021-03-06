package coder.aihui.data.bean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Entity mapped to table INSPECT__EXT.
 */

@Entity
public class INSPECT_EXT {
    @Id
    @Property(nameInDb = "INSE__ID")
    private Long    INSE_ID;
    private String  INSE_FK_ID;
    private String  INSE_TYPE;
    private String  INSE_TEMPLATE_ID;
    private Integer INSE_CYCLE;
    private String  INSE_CYCLE_TYPE;

    @Generated(hash = 1063400222)
    public INSPECT_EXT(Long INSE_ID, String INSE_FK_ID, String INSE_TYPE, String INSE_TEMPLATE_ID,
            Integer INSE_CYCLE, String INSE_CYCLE_TYPE) {
        this.INSE_ID = INSE_ID;
        this.INSE_FK_ID = INSE_FK_ID;
        this.INSE_TYPE = INSE_TYPE;
        this.INSE_TEMPLATE_ID = INSE_TEMPLATE_ID;
        this.INSE_CYCLE = INSE_CYCLE;
        this.INSE_CYCLE_TYPE = INSE_CYCLE_TYPE;
    }
    @Generated(hash = 905237702)
    public INSPECT_EXT() {
    }
    public Long getINSE_ID() {
        return this.INSE_ID;
    }
    public void setINSE_ID(Long INSE_ID) {
        this.INSE_ID = INSE_ID;
    }
    public String getINSE_FK_ID() {
        return this.INSE_FK_ID;
    }
    public void setINSE_FK_ID(String INSE_FK_ID) {
        this.INSE_FK_ID = INSE_FK_ID;
    }
    public String getINSE_TYPE() {
        return this.INSE_TYPE;
    }
    public void setINSE_TYPE(String INSE_TYPE) {
        this.INSE_TYPE = INSE_TYPE;
    }
    public String getINSE_TEMPLATE_ID() {
        return this.INSE_TEMPLATE_ID;
    }
    public void setINSE_TEMPLATE_ID(String INSE_TEMPLATE_ID) {
        this.INSE_TEMPLATE_ID = INSE_TEMPLATE_ID;
    }
    public Integer getINSE_CYCLE() {
        return this.INSE_CYCLE;
    }
    public void setINSE_CYCLE(Integer INSE_CYCLE) {
        this.INSE_CYCLE = INSE_CYCLE;
    }
    public String getINSE_CYCLE_TYPE() {
        return this.INSE_CYCLE_TYPE;
    }
    public void setINSE_CYCLE_TYPE(String INSE_CYCLE_TYPE) {
        this.INSE_CYCLE_TYPE = INSE_CYCLE_TYPE;
    }


}
