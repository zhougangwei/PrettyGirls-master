package coder.aihui.data.normalbean;

import coder.aihui.widget.jdaddressselector.ISelectAble;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/6/22 13:51
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/6/22$
 * @ 更新描述  ${TODO}
 */

public class DeptBean implements ISelectAble {

    public String name;
    public int id;
    public  String arg;



    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getArg() {
        return arg;
    }
}
