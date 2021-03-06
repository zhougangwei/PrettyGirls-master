package coder.aihui.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

/**
 * @ 创建者   zhou
 * @ 创建时间   2016/12/12 17:14
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2016/12/12$
 * @ 更新描述  ${TODO}
 */

public class ListViewUtil {

    //获得ListView中的View
    public static View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;
        if (pos < firstListItemPosition || pos > lastListItemPosition) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }


    //获得ListView中的View
    public static View getViewByPosition(int pos, RecyclerView mRecyclerView) {

       int firstVisibleItems=  ((LinearLayoutManager)mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        // 真实Position就是position - firstVisibleItems[0]
        View childAt       = mRecyclerView.getChildAt(pos - firstVisibleItems);
        return childAt;
    }
}
