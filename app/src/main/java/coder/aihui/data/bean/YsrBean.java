package coder.aihui.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @ 创建者   zhou
 * @ 创建时间   2016/12/21 11:20
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2016/12/21$
 * @ 更新描述  ${TODO}
 */
@Entity
public class YsrBean {
    /**
     * purYsrId : 1
     * userId : 1
     * userName : admin
     */
    @Id
    private Long purYsrId;
    private Integer userId;
    private String  userName;
    @Generated(hash = 609210918)
    public YsrBean(Long purYsrId, Integer userId, String userName) {
        this.purYsrId = purYsrId;
        this.userId = userId;
        this.userName = userName;
    }
    @Generated(hash = 941557736)
    public YsrBean() {
    }
    public Long getPurYsrId() {
        return this.purYsrId;
    }
    public void setPurYsrId(Long purYsrId) {
        this.purYsrId = purYsrId;
    }
    public Integer getUserId() {
        return this.userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }


}
