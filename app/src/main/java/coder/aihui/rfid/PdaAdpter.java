package coder.aihui.rfid;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/9/6 9:50
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/9/6$
 * @ 更新描述  ${TODO}
 */

public interface PdaAdpter {
    void close();

    void stop();

    void pause();

    void start();

    void onCreate();
}
