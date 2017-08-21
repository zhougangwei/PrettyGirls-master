package coder.aihui.ui.main;


import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.WhereCondition;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/4/10 15:18
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/4/10$
 * @ 更新描述  ${TODO}
 */

public class UpBean {


    private String           enties;    //对象
    private String           methods;   //方法
    private String[]         pars;        //参数
    private Integer          type;       //下载的类别
    private Integer          way;       //下载的方式 http还是webservice
    private String           name;        //名称
    private Integer          count;      //数目
    private String           bigType;      //主体是哪个模块的 如资产清点 巡检...
    private WhereCondition[] whereconditions;   //数据上传条件
    private Property[]         propertie;   //排序条件

    public String getEnties() {
        return enties;
    }

    public void setEnties(String enties) {
        this.enties = enties;
    }

    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }

    public String[] getPars() {
        return pars;
    }

    public void setPars(String[] pars) {
        this.pars = pars;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getWay() {
        return way;
    }

    public void setWay(Integer way) {
        this.way = way;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getBigType() {
        return bigType;
    }

    public void setBigType(String bigType) {
        this.bigType = bigType;
    }

    public WhereCondition[] getWhereconditions() {
        return whereconditions;
    }

    public void setWhereconditions(WhereCondition[] whereconditions) {
        this.whereconditions = whereconditions;
    }

    public Property[] getPropertie() {
        return propertie;
    }

    public void setPropertie(Property[] propertie) {
        this.propertie = propertie;
    }
}
