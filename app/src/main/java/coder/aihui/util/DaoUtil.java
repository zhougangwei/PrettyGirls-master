package coder.aihui.util;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import coder.aihui.data.bean.IN_MATERIALS_WZMC;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/8/7 9:20
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/8/7$
 * @ 更新描述  ${TODO}
 */

public class DaoUtil {


    /**
     * @param pxsb_json 培训管理pxgl_save的设备json
     * @return  //设备名字的一个用","隔开的string
     */
    public static String getWzString(String pxsb_json) {
        //设备
        List<IN_MATERIALS_WZMC> mcList = GsonUtil.parseJsonToList(pxsb_json, new TypeToken<List<IN_MATERIALS_WZMC>>() {
        }.getType());
        StringBuilder sb = new StringBuilder();
        if (mcList != null) {
            for (int i = 0; i < mcList.size(); i++) {
                if (i != mcList.size() - 1) {
                    sb.append(mcList.get(i).getWZMC()).append(",");
                } else {
                    sb.append(mcList.get(i).getWZMC());
                }
            }
        }
        return sb.toString();

    }

}
