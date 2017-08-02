package coder.aihui.widget.tree;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/4/10 11:45
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/4/10$
 * @ 更新描述  ${TODO}
 */

public class MyTreeListAdapter extends TreeListViewAdapter {
    /**
     * @param mTree
     * @param context
     * @param datas
     * @param defaultExpandLevel 默认展开几级树
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public MyTreeListAdapter(ListView mTree, Context context, List datas, int defaultExpandLevel) throws IllegalArgumentException, IllegalAccessException {
        super(mTree, context, datas, defaultExpandLevel);
    }

    @Override
    public void setDefault(int i) {

    }

    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
        return null;
    }
}
