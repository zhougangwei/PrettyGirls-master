package coder.aihui.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/8/7 18:15
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/8/7$
 * @ 更新描述  ${单号}
 */

@Entity
public class DHBean {


    @Id
    Long id;            //主键
    String  dh;          //单号
    String  wzmc;        //物资名称
    Integer num;        //数量
    String  contractId;   //对应plan表的 标识


    @Generated(hash = 607489257)
    public DHBean(Long id, String dh, String wzmc, Integer num, String contractId) {
        this.id = id;
        this.dh = dh;
        this.wzmc = wzmc;
        this.num = num;
        this.contractId = contractId;
    }

    @Generated(hash = 259480127)
    public DHBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDh() {
        return this.dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public String getWzmc() {
        return this.wzmc;
    }

    public void setWzmc(String wzmc) {
        this.wzmc = wzmc;
    }

    public Integer getNum() {
        return this.num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getContractId() {
        return this.contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }


}
