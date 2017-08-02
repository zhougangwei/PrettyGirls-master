package coder.aihui.widget;

import android.widget.ExpandableListView;

/**
 * @ 创建者   zhou
 * @ 创建时间   2016/12/28 13:22
 * @ 描述    ${能在ScrollView中正常显示的ListView}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2016/12/28$
 * @ 更新描述  ${TODO}
 */

public class ScrollViewWithExpandListView extends ExpandableListView {

    public ScrollViewWithExpandListView(android.content.Context context,
                                        android.util.AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Integer.MAX_VALUE >> 2,如果不设置，系统默认设置是显示两条
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }

}


