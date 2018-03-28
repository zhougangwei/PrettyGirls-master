package coder.aihui.rfid;

import android.content.Context;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/9/5 17:11
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/9/5$
 * @ 更新描述  ${TODO}
 */

public interface PdaView {


    /**
     * 当前pda已开启
     */
    void pdaStartSearch();

    /**
     * 当前pda已关闭,开始走数据
     */
    void pdaStopSearch();

    /**
     * @param needMsg 获得的Rfid
      */
    void sendMessage(String needMsg);


    /**
     * 因为必须要有Context 所以就写这儿
     */
    Context getPdaContext();

}
