package coder.aihui.data.bean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Entity mapped to table PXGL__SAVE.
 */

@Entity
public class PXGL_SAVE {

    @Id
    private Long ID;
    private Long PXJL_ID;     //培训记录的主键id
    private String PXJL_JSON;
    private String PXFJ_JSON;
    private String PXRY_JSON;
    private String PXSB_JSON;
    private String ITEM_STRING1;
    private String ITEM_STRING2;
    private String ITEM_STRING3;
    private String ITEM_STRG4;
    private Integer IS_UP;
    @Generated(hash = 670224980)
    public PXGL_SAVE(Long ID, Long PXJL_ID, String PXJL_JSON, String PXFJ_JSON, String PXRY_JSON,
            String PXSB_JSON, String ITEM_STRING1, String ITEM_STRING2, String ITEM_STRING3,
            String ITEM_STRG4, Integer IS_UP) {
        this.ID = ID;
        this.PXJL_ID = PXJL_ID;
        this.PXJL_JSON = PXJL_JSON;
        this.PXFJ_JSON = PXFJ_JSON;
        this.PXRY_JSON = PXRY_JSON;
        this.PXSB_JSON = PXSB_JSON;
        this.ITEM_STRING1 = ITEM_STRING1;
        this.ITEM_STRING2 = ITEM_STRING2;
        this.ITEM_STRING3 = ITEM_STRING3;
        this.ITEM_STRG4 = ITEM_STRG4;
        this.IS_UP = IS_UP;
    }
    @Generated(hash = 319003152)
    public PXGL_SAVE() {
    }
    public Long getID() {
        return this.ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public Long getPXJL_ID() {
        return this.PXJL_ID;
    }
    public void setPXJL_ID(Long PXJL_ID) {
        this.PXJL_ID = PXJL_ID;
    }
    public String getPXJL_JSON() {
        return this.PXJL_JSON;
    }
    public void setPXJL_JSON(String PXJL_JSON) {
        this.PXJL_JSON = PXJL_JSON;
    }
    public String getPXFJ_JSON() {
        return this.PXFJ_JSON;
    }
    public void setPXFJ_JSON(String PXFJ_JSON) {
        this.PXFJ_JSON = PXFJ_JSON;
    }
    public String getPXRY_JSON() {
        return this.PXRY_JSON;
    }
    public void setPXRY_JSON(String PXRY_JSON) {
        this.PXRY_JSON = PXRY_JSON;
    }
    public String getPXSB_JSON() {
        return this.PXSB_JSON;
    }
    public void setPXSB_JSON(String PXSB_JSON) {
        this.PXSB_JSON = PXSB_JSON;
    }
    public String getITEM_STRING1() {
        return this.ITEM_STRING1;
    }
    public void setITEM_STRING1(String ITEM_STRING1) {
        this.ITEM_STRING1 = ITEM_STRING1;
    }
    public String getITEM_STRING2() {
        return this.ITEM_STRING2;
    }
    public void setITEM_STRING2(String ITEM_STRING2) {
        this.ITEM_STRING2 = ITEM_STRING2;
    }
    public String getITEM_STRING3() {
        return this.ITEM_STRING3;
    }
    public void setITEM_STRING3(String ITEM_STRING3) {
        this.ITEM_STRING3 = ITEM_STRING3;
    }
    public String getITEM_STRG4() {
        return this.ITEM_STRG4;
    }
    public void setITEM_STRG4(String ITEM_STRG4) {
        this.ITEM_STRG4 = ITEM_STRG4;
    }
    public Integer getIS_UP() {
        return this.IS_UP;
    }
    public void setIS_UP(Integer IS_UP) {
        this.IS_UP = IS_UP;
    }



}
