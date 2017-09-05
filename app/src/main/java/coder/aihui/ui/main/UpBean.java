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
    private Property[]       propertie;   //排序条件

    public String getEnties() {
        return enties;
    }

    public void setEnties(String enties) {
        this.enties = enties;
    }

    public String getMethods() {
        return methods;
    }

    /**
     * @param methods 上传的方法 例如"uploadPdaRepDataJSON"
     */
    public void setMethods(String methods) {
        this.methods = methods;
    }

    public String[] getPars() {
        return pars;
    }


    /**
     * @param pars 上传的参数
     */
    public void setPars(String[] pars) {
        this.pars = pars;
    }

    public Integer getType() {
        return type;
    }

    /**
     * @param type 上传的类型 对应的是之类
     *             ASSET_DOWN   = 1;         //台账
     *             INIT_DOWN    = 0;         //初始化
     *             COMPANY_DOWN = 2;         //下载公司
     *             INSPECT_PLAN_DOWN        = 3;         //下载巡检计划
     *             INSPECT_INIT_DOWN        = 4;         //下载巡检初始化
     *             INSPECT_TEMPLETITEM_DOWN = 5;         //下载巡检模板
     *             AZYS_DOWN                = 6;         //下载安装验收
     *             PXGL_SB_DOWN             = 7;         //下载培训管理设备
     *             PUR_CONTRACT_PLAN_UP = 8;         //上传安装验收
     *             INSPECT_UP           = 9;         //上传巡检数据
     *             INSPECT_PM_PLAN_DOWN        = 10;         //下载PM计划
     *             INSPECT_PM_INIT_DOWN        = 11;         //下载PM初始化
     *             INSPECT_PM_TEMPLETITEM_DOWN = 12;         //下载PM模板
     *             ASSET_CORRECT_UP            = 13;         //上传修改台账的数据
     *             PXGL_UP                     = 14;         //培训管理上传
     */
    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getWay() {
        return way;
    }

    /**
     * @param way 上传走HTTP还是webService
     *            <p>
     *            public static final int WEB_SERVICE = 1;        //用webservie下载
     *            public static final int HTTP        = 2;               //用http下载
     */
    public void setWay(Integer way) {
        this.way = way;
    }

    public String getName() {
        return name;
    }

    /**
     * 上传时进度条显示的文字
     *  @param name
     */
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

    /**
     * @param bigType 上传的大类型 主要用于downfragment
     */
    public void setBigType(String bigType) {
        this.bigType = bigType;
    }

    public WhereCondition[] getWhereconditions() {
        return whereconditions;
    }

    /**
     * @param whereconditions  上传数据的过滤条件 因为需要到数据库取数据
     */
    public void setWhereconditions(WhereCondition[] whereconditions) {
        this.whereconditions = whereconditions;
    }

    public Property[] getPropertie() {
        return propertie;
    }

    /**
     * @param propertie 上传数据的排序条件 默认升序 以后可以再改进
     */
    public void setOrderPropertie(Property[] propertie) {
        this.propertie = propertie;
    }
}
