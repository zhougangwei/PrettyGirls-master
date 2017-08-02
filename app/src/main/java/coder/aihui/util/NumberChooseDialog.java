package coder.aihui.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import coder.aihui.data.bean.DialogBean;
import coder.aihui.widget.ListBottomDialog;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/7/31 16:03
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/7/31$
 * @ 更新描述  ${TODO}
 */

public class NumberChooseDialog {

    private static NumberChooseDialog mLoadDataManager = new NumberChooseDialog();

    private NumberChooseDialog() {
    }

    public static NumberChooseDialog getInstance() {
        return mLoadDataManager;
    }


    public void showNum(Activity activity,ListBottomDialog.onBackResult backResult) {

        List<DialogBean> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            DialogBean dialogBean = new DialogBean(i + "", i);
            list.add(dialogBean);
        }
        new ListBottomDialog(activity).showDialog(list,backResult);
    }

}
