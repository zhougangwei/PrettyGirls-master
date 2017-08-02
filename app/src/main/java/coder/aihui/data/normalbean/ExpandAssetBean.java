package coder.aihui.data.normalbean;


import java.util.List;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/6/12 20:51
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/6/12$
 * @ 更新描述  ${TODO}
 */

public class ExpandAssetBean {

    private String    name;
    private List<ExpandSonBean> sonBean;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExpandSonBean> getSonBean() {
        return sonBean;
    }

    public void setSonBean(List<ExpandSonBean> sonBean) {
        this.sonBean = sonBean;
    }
}
