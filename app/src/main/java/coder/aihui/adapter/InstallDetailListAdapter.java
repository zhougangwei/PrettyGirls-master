package coder.aihui.adapter;

import android.app.Activity;

import java.util.List;

/**
 * @ 创建者   zhou
 * @ 创建时间   2016/12/2 10:32
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2016/12/2$
 * @ 更新描述  ${TODO}
 */

public class InstallDetailListAdapter extends BasicAdapter {

    private InstallDetailListHolder mInstallDetailListHolder;



    public InstallDetailListAdapter(List showItems) {
        super(showItems);
    }

    public InstallDetailListAdapter(List showItems, Activity mActivity) {
        super(showItems,mActivity);
    }


    @Override
    protected BaseHolder createViewHolder(int position) {
        mInstallDetailListHolder = new InstallDetailListHolder(mActivity);
        return mInstallDetailListHolder;
    }


}
