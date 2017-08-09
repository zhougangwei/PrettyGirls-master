package coder.aihui.data.normalbean;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/1/10 15:30
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/1/10$
 * @ 更新描述  ${TODO}
 */

public class FjBean {
    private String  PXJL_FILE_PATH;		    //附件地址
    private Integer FILE_TYPE;				//附件类型(1培训附件 2培训教材)
    private Integer PXJL_FILE_ID;				//上传到服务器返回的id

    public String getPXJL_FILE_PATH() {
        return PXJL_FILE_PATH;
    }

    public void setPXJL_FILE_PATH(String PXJL_FILE_PATH) {
        this.PXJL_FILE_PATH = PXJL_FILE_PATH;
    }

    public Integer getFILE_TYPE() {
        return FILE_TYPE;
    }

    public void setFILE_TYPE(Integer FILE_TYPE) {
        this.FILE_TYPE = FILE_TYPE;
    }

    public Integer getPXJL_FILE_ID() {
        return PXJL_FILE_ID;
    }

    public void setPXJL_FILE_ID(Integer PXJL_FILE_ID) {
        this.PXJL_FILE_ID = PXJL_FILE_ID;
    }
}
