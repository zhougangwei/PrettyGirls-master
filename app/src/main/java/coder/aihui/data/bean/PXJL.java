package coder.aihui.data.bean;

import java.util.Date;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/1/10 15:29
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/1/10$
 * @ 更新描述  ${TODO}
 */

public class PXJL {


    private String  pxzt;            //培训主题
    private Date    pxrq;                //培训日期
    private String  pxf;                //培训方
    private Integer pxxs;            //培训时间(小时)
    private Integer pxfz;            //培训时间(分钟)
    private String  zjr;                //主讲人
    private String  zynr;            //主要内容
    private String  jlr;                //记录人
    private String  pxlx;            //培训类型


    public String getPxzt() {
        return this.pxzt;
    }
    public void setPxzt(String pxzt) {
        this.pxzt = pxzt;
    }
    public Date getPxrq() {
        return this.pxrq;
    }
    public void setPxrq(Date pxrq) {
        this.pxrq = pxrq;
    }
    public String getPxf() {
        return this.pxf;
    }
    public void setPxf(String pxf) {
        this.pxf = pxf;
    }
    public Integer getPxxs() {
        return this.pxxs;
    }
    public void setPxxs(Integer pxxs) {
        this.pxxs = pxxs;
    }
    public Integer getPxfz() {
        return this.pxfz;
    }
    public void setPxfz(Integer pxfz) {
        this.pxfz = pxfz;
    }
    public String getZjr() {
        return this.zjr;
    }
    public void setZjr(String zjr) {
        this.zjr = zjr;
    }
    public String getZynr() {
        return this.zynr;
    }
    public void setZynr(String zynr) {
        this.zynr = zynr;
    }
    public String getJlr() {
        return this.jlr;
    }
    public void setJlr(String jlr) {
        this.jlr = jlr;
    }
    public String getPxlx() {
        return this.pxlx;
    }
    public void setPxlx(String pxlx) {
        this.pxlx = pxlx;
    }


}
