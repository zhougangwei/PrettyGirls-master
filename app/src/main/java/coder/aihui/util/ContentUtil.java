package coder.aihui.util;

import android.util.Log;

import static coder.aihui.app.MyApplication.mContext;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/3/23 11:58
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/3/23$
 * @ 更新描述  ${TODO}
 */

public class ContentUtil {
    //读取配置文件中的wsdl地址
    public static String getWsAddress() {
        String wsAddress = SPUtil.getString(mContext, "wsAddress", "");
        Log.d("AndroidUtils", wsAddress);
        //http://192.168.1.192:8080/service
        // 192.168.1.192:8080
        if (wsAddress == null || "".equals(wsAddress) || wsAddress.length() < 10) {
            return null;
        }
        try {
            if (wsAddress.contains("http://")) {
                return wsAddress.substring(0, wsAddress.indexOf("/", 8));
            } else {
                return "http://" + wsAddress;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String getUserId() {
        return SPUtil.getString(mContext, "userId", "");
    }
    public static String getUserName() {
        return SPUtil.getString(mContext, "UserName", "");
    }
}
