package coder.aihui.manager;

import android.text.TextUtils;

import java.util.ArrayList;

import coder.aihui.widget.jdaddressselector.ISelectAble;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/8/1 14:05
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/8/1$
 * @ 更新描述  ${TODO}
 */

public class DeptLocationManager implements ISelectAbleManager{


    public ArrayList<ISelectAble> selectAbles;
    String mDlwzName;
    String mDlwzIds;
    String mDeptName;
    String mDeptIds;

    String mAllDlwzIds;
    String mAllDeptIds;
    String mAllDeptName;
    String mAllDlwzName;

    //数据转化分析
    public DeptLocationManager() {
    }

    public void solveDatas(ArrayList<ISelectAble> selectAbles) {
        String result = "";
        StringBuilder sbids = new StringBuilder();
        String arg = "";
        sbids.append("(");

        for (int i = 0; i < selectAbles.size(); i++) {

            ISelectAble selectAble = selectAbles.get(i);
            if (i != selectAbles.size() - 1) {
                arg = selectAble.getArg();
                String name = selectAble.getName();
                int id = selectAble.getId();
                result += name + "-";
                sbids.append(id).append(",");
            } else {
                arg = selectAble.getArg();
                String name = selectAble.getName();
                int id = selectAble.getId();
                result += name;
                sbids.append(id);
            }
        }
        sbids.append(")");
        if (selectAbles != null && selectAbles.size() > 0) {
            ISelectAble iSelectAble = selectAbles.get(selectAbles.size() - 1);

            if ("dept".equals(arg)) {
                mDeptName = iSelectAble.getName();
                mDeptIds = iSelectAble.getId() + "";
            } else if ("location".equals(arg)) {
                mDlwzName = iSelectAble.getName();
                mDlwzIds = iSelectAble.getId() + "";
            }

        }

        if (!TextUtils.isEmpty(result)) {
            if ("dept".equals(arg)) {
                mAllDeptName = result.substring(0, result.length() );
            } else if ("location".equals(arg)) {
                mAllDlwzName = result.substring(0, result.length() );
            }
        }
        String ids = sbids.toString();
        if (ids.length() != 2) {              //说明只有()
            if ("dept".equals(arg)) {
                mAllDeptIds = ids;
            } else if ("location".equals(arg)) {
                mAllDlwzIds = ids;
            }
        }
    }

    public ArrayList<ISelectAble> getSelectAbles() {
        return selectAbles;
    }


    public String getDlwzName() {
        return mDlwzName;
    }


    public String getDlwzIds() {
        return mDlwzIds;
    }


    public String getDeptName() {
        return mDeptName;
    }


    public String getDeptIds() {
        return mDeptIds;
    }


    public String getAllDlwzIds() {
        return mAllDlwzIds;
    }


    public String getAllDeptIds() {
        return mAllDeptIds;
    }


    public String getAllDeptName() {
        return mAllDeptName;
    }


    public String getAllDlwzName() {
        return mAllDlwzName;
    }

    public DeptLocationManager setAllDlwzName(String allDlwzName) {
        mAllDlwzName = allDlwzName;
        return this;
    }


}
