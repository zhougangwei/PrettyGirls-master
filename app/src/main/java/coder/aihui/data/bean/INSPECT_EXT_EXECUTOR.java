package coder.aihui.data.bean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Entity mapped to table INSPECT__EXT__EXECUTOR.
 */
@Entity
public class INSPECT_EXT_EXECUTOR {
    @Id
    @Property(nameInDb = "INSEE_ID")
    private Long    INSEE_ID;
    private Integer INSEE_USER_ID;
    private String  INSEE_FK_ID;
    private String  INSEE_TYPE;
    @Generated(hash = 1027220314)
    public INSPECT_EXT_EXECUTOR(Long INSEE_ID, Integer INSEE_USER_ID, String INSEE_FK_ID,
            String INSEE_TYPE) {
        this.INSEE_ID = INSEE_ID;
        this.INSEE_USER_ID = INSEE_USER_ID;
        this.INSEE_FK_ID = INSEE_FK_ID;
        this.INSEE_TYPE = INSEE_TYPE;
    }
    @Generated(hash = 776538220)
    public INSPECT_EXT_EXECUTOR() {
    }
    public Long getINSEE_ID() {
        return this.INSEE_ID;
    }
    public void setINSEE_ID(Long INSEE_ID) {
        this.INSEE_ID = INSEE_ID;
    }
    public Integer getINSEE_USER_ID() {
        return this.INSEE_USER_ID;
    }
    public void setINSEE_USER_ID(Integer INSEE_USER_ID) {
        this.INSEE_USER_ID = INSEE_USER_ID;
    }
    public String getINSEE_FK_ID() {
        return this.INSEE_FK_ID;
    }
    public void setINSEE_FK_ID(String INSEE_FK_ID) {
        this.INSEE_FK_ID = INSEE_FK_ID;
    }
    public String getINSEE_TYPE() {
        return this.INSEE_TYPE;
    }
    public void setINSEE_TYPE(String INSEE_TYPE) {
        this.INSEE_TYPE = INSEE_TYPE;
    }


}
