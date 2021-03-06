package coder.aihui.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

/**
 * Entity mapped to table SYS__DEPT.
 */

@Entity
public class SYS_DEPT  {
    @Id
    @Property(nameInDb = "DEPT__ID")
    private Long DEPT_ID;
    private String DEPT_NAME;
    private Long DEPT_PARENT_ID;
    private String DEPT_CODE;
    private String DEPT_ADDRESS;
    private String DEPT_SEARCH_CODE;
    private Integer DEPT_GRADE;
    private Integer DEPT_TYPE;
    private String DEPT_PY;
    private String DEPT_WB;
    private Integer DEPT_ISDEL;
    private String DEPT_BARCODE;
    private Integer DEPT_MATERIALS;
    private String DEPT_OA_ID;
    private String DEPT_HIS_ID;
    @Generated(hash = 945906290)
    public SYS_DEPT(Long DEPT_ID, String DEPT_NAME, Long DEPT_PARENT_ID, String DEPT_CODE,
            String DEPT_ADDRESS, String DEPT_SEARCH_CODE, Integer DEPT_GRADE, Integer DEPT_TYPE,
            String DEPT_PY, String DEPT_WB, Integer DEPT_ISDEL, String DEPT_BARCODE,
            Integer DEPT_MATERIALS, String DEPT_OA_ID, String DEPT_HIS_ID) {
        this.DEPT_ID = DEPT_ID;
        this.DEPT_NAME = DEPT_NAME;
        this.DEPT_PARENT_ID = DEPT_PARENT_ID;
        this.DEPT_CODE = DEPT_CODE;
        this.DEPT_ADDRESS = DEPT_ADDRESS;
        this.DEPT_SEARCH_CODE = DEPT_SEARCH_CODE;
        this.DEPT_GRADE = DEPT_GRADE;
        this.DEPT_TYPE = DEPT_TYPE;
        this.DEPT_PY = DEPT_PY;
        this.DEPT_WB = DEPT_WB;
        this.DEPT_ISDEL = DEPT_ISDEL;
        this.DEPT_BARCODE = DEPT_BARCODE;
        this.DEPT_MATERIALS = DEPT_MATERIALS;
        this.DEPT_OA_ID = DEPT_OA_ID;
        this.DEPT_HIS_ID = DEPT_HIS_ID;
    }
    @Generated(hash = 140425538)
    public SYS_DEPT() {
    }
    public Long getDEPT_ID() {
        return this.DEPT_ID;
    }
    public void setDEPT_ID(Long DEPT_ID) {
        this.DEPT_ID = DEPT_ID;
    }
    public String getDEPT_NAME() {
        return this.DEPT_NAME;
    }
    public void setDEPT_NAME(String DEPT_NAME) {
        this.DEPT_NAME = DEPT_NAME;
    }
    public Long getDEPT_PARENT_ID() {
        return this.DEPT_PARENT_ID;
    }
    public void setDEPT_PARENT_ID(Long DEPT_PARENT_ID) {
        this.DEPT_PARENT_ID = DEPT_PARENT_ID;
    }
    public String getDEPT_CODE() {
        return this.DEPT_CODE;
    }
    public void setDEPT_CODE(String DEPT_CODE) {
        this.DEPT_CODE = DEPT_CODE;
    }
    public String getDEPT_ADDRESS() {
        return this.DEPT_ADDRESS;
    }
    public void setDEPT_ADDRESS(String DEPT_ADDRESS) {
        this.DEPT_ADDRESS = DEPT_ADDRESS;
    }
    public String getDEPT_SEARCH_CODE() {
        return this.DEPT_SEARCH_CODE;
    }
    public void setDEPT_SEARCH_CODE(String DEPT_SEARCH_CODE) {
        this.DEPT_SEARCH_CODE = DEPT_SEARCH_CODE;
    }
    public Integer getDEPT_GRADE() {
        return this.DEPT_GRADE;
    }
    public void setDEPT_GRADE(Integer DEPT_GRADE) {
        this.DEPT_GRADE = DEPT_GRADE;
    }
    public Integer getDEPT_TYPE() {
        return this.DEPT_TYPE;
    }
    public void setDEPT_TYPE(Integer DEPT_TYPE) {
        this.DEPT_TYPE = DEPT_TYPE;
    }
    public String getDEPT_PY() {
        return this.DEPT_PY;
    }
    public void setDEPT_PY(String DEPT_PY) {
        this.DEPT_PY = DEPT_PY;
    }
    public String getDEPT_WB() {
        return this.DEPT_WB;
    }
    public void setDEPT_WB(String DEPT_WB) {
        this.DEPT_WB = DEPT_WB;
    }
    public Integer getDEPT_ISDEL() {
        return this.DEPT_ISDEL;
    }
    public void setDEPT_ISDEL(Integer DEPT_ISDEL) {
        this.DEPT_ISDEL = DEPT_ISDEL;
    }
    public String getDEPT_BARCODE() {
        return this.DEPT_BARCODE;
    }
    public void setDEPT_BARCODE(String DEPT_BARCODE) {
        this.DEPT_BARCODE = DEPT_BARCODE;
    }
    public Integer getDEPT_MATERIALS() {
        return this.DEPT_MATERIALS;
    }
    public void setDEPT_MATERIALS(Integer DEPT_MATERIALS) {
        this.DEPT_MATERIALS = DEPT_MATERIALS;
    }
    public String getDEPT_OA_ID() {
        return this.DEPT_OA_ID;
    }
    public void setDEPT_OA_ID(String DEPT_OA_ID) {
        this.DEPT_OA_ID = DEPT_OA_ID;
    }
    public String getDEPT_HIS_ID() {
        return this.DEPT_HIS_ID;
    }
    public void setDEPT_HIS_ID(String DEPT_HIS_ID) {
        this.DEPT_HIS_ID = DEPT_HIS_ID;
    }

    

}
