package coder.aihui.rfid;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/9/5 16:26
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/9/5$
 * @ 更新描述  ${TODO}
 */

public class PdaPresenterFactory {

    public static HandHeldPdaPresenter mHandHeldPdaPresenter;

    public static PdaMachine provideRfidSupport(PdaView pdaView,String machineName) throws Exception {

        if (machineName.equals("汉德霍尔清点机")) {
            if (mHandHeldPdaPresenter == null) {
                return new HandHeldPdaPresenter(pdaView);
            }
        } else if (machineName.equals("汉德霍尔清点机")) {         //存着
            if (mHandHeldPdaPresenter == null) {
                return new HandHeldPdaPresenter(pdaView);
            }
        } else if (machineName.equals("汉德霍尔清点机")) {         //存着
            if (mHandHeldPdaPresenter == null) {
                return new HandHeldPdaPresenter(pdaView);
            }
        } else {
            throw new Exception("没有该产品");
        }
        return null;
    }



}
