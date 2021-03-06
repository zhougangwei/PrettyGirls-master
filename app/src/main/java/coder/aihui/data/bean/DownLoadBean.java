package coder.aihui.data.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/4/10 15:18
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/4/10$
 * @ 更新描述  ${TODO}
 */

public class DownLoadBean implements Cloneable, Serializable {


    public String[]       enties;    //对象
    public String[]       methods;   //方法
    public List<String[]> pars;        //参数
    public Integer        type;       //下载的类别
    public Integer        way;       //下载的方式 http还是webservice
    public String         name;        //名称
    public Integer        count;      //数目
    public String         bigType;      //主体是哪个模块的 如资产清点 巡检...


    public static final int ON_FINISH   = 2;
    public static final int ON_PROGRESS = 6;
    public static final int MISTAKES    = 3;
    public static final int DOWN        = 0;


    /**
     * "下载","暂停","完成","错误","删除","更新","下载中"
     * <p>
     * 0-down
     * 1-onStop
     * 2-finish
     * 3-mistakes
     * 6-onprogress
     */
    public int state = ON_FINISH;             //当前进度条状态 默认完成
    public int progressNum;               //当前进度条数目


    public int getState() {
        return state;
    }

    /**
     * "下载","暂停","完成",,"错误","删除","更新","下载中"
     * 0-down
     * 1-onStop
     * 2-finish
     * 3-mistakes
     * 6-onprogress
     *
     * @param state
     */
    public void setState(int state) {
        this.state = state;
    }

    public int getProgressNum() {
        return progressNum;
    }

    public void setProgressNum(int progressNum) {
        this.progressNum = progressNum;
    }


    public String[] getEnties() {
        return enties;
    }

    public void setEnties(String[] enties) {
        this.enties = enties;
    }

    public String[] getMethods() {
        return methods;
    }

    public void setMethods(String[] methods) {
        this.methods = methods;
    }

    public List<String[]> getPars() {
        return pars;
    }

    public void setPars(List<String[]> pars) {
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

    public Object deepClone() {

        try {
            //将对象写到流里
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            //从流里读回来
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public Object clone() {
        DownLoadBean husband = null;
        try {
            husband = (DownLoadBean) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } finally {
            return husband;
        }
    }

}
