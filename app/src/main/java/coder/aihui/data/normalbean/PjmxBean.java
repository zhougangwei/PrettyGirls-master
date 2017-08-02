package coder.aihui.data.normalbean;

/**
 * @ 创建者   zhou
 * @ 创建时间   2016/12/22 16:39
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2016/12/22$
 * @ 更新描述  ${TODO}
 */

public class PjmxBean {
    /**
     * pdBrand : 1
     * pdNum : 1
     * pdCode : 1
     * checkDetailId : 41
     * pdUnit : 1
     * pdBxq : 1
     * pdRemark : 1
     * pdGgxh : 1
     * pdPrice : 1
     * pdPjmc : 1
     */

    private String pdBrand;//品牌
    private Integer    pdNum;   //数量
    private String pdCode;  //生产编号
    private Integer    checkDetailId;
    private String pdUnit;//单位
    private String    pdBxq;//保修期
    private String pdRemark;//评论
    private String pdGgxh;//规格型号
    private Integer    pdPrice; //价格
    private String pdPjmc;  //名称
    private String bxjzrq2;

    public String getBxjzrq2() {
        return bxjzrq2;
    }

    public void setBxjzrq2(String bxjzrq2) {
        this.bxjzrq2 = bxjzrq2;
    }

    public String getPdBrand() {
        return pdBrand;
    }

    public void setPdBrand(String pdBrand) {
        this.pdBrand = pdBrand;
    }

    public Integer getPdNum() {
        return pdNum;
    }

    public void setPdNum(Integer pdNum) {
        this.pdNum = pdNum;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public Integer getCheckDetailId() {
        return checkDetailId;
    }

    public void setCheckDetailId(Integer checkDetailId) {
        this.checkDetailId = checkDetailId;
    }

    public String getPdUnit() {
        return pdUnit;
    }

    public void setPdUnit(String pdUnit) {
        this.pdUnit = pdUnit;
    }

    public String getPdBxq() {
        return pdBxq;
    }

    public void setPdBxq(String pdBxq) {
        this.pdBxq = pdBxq;
    }

    public String getPdRemark() {
        return pdRemark;
    }

    public void setPdRemark(String pdRemark) {
        this.pdRemark = pdRemark;
    }

    public String getPdGgxh() {
        return pdGgxh;
    }

    public void setPdGgxh(String pdGgxh) {
        this.pdGgxh = pdGgxh;
    }

    public Integer getPdPrice() {
        return pdPrice;
    }

    public void setPdPrice(Integer pdPrice) {
        this.pdPrice = pdPrice;
    }

    public String getPdPjmc() {
        return pdPjmc;
    }

    public void setPdPjmc(String pdPjmc) {
        this.pdPjmc = pdPjmc;
    }
}
