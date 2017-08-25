package coder.aihui.http;

import com.google.gson.annotations.SerializedName;

import coder.aihui.base.Content;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/8/25 15:00
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/8/25$
 * @ 更新描述  ${TODO}
 */

public class HttpStatus {
    @SerializedName("code")
    private int mCode;
    @SerializedName("message")
    private String mMessage;

    public int getCode() {
        return mCode;
    }

    public String getMessage() {
        return mMessage;
    }

    /**
     * API是否请求失败
     *
     * @return 失败返回true, 成功返回false
     */
    public boolean isCodeInvalid() {
        return mCode != Content.WEB_RESP_CODE_SUCCESS;
    }
}


