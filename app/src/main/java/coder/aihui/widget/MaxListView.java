package coder.aihui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @ 创建者   zhou
 * @ 创建时间   2016/12/30 16:57
 * @ 描述    ${可以设置最大高度的ListView}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2016/12/30$
 * @ 更新描述  ${TODO}
 */

public class MaxListView extends ListView {
    /**
     * listview高度
     */
    private int listViewHeight;

    public int getListViewHeight() {
        return listViewHeight;
    }

    public void setListViewHeight(int listViewHeight) {
        this.listViewHeight = listViewHeight;
    }

    public MaxListView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public MaxListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public MaxListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        if (listViewHeight > -1) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(listViewHeight,
                    MeasureSpec.AT_MOST);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}

