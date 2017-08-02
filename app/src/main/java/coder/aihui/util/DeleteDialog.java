package coder.aihui.util;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/7/31 13:23
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/7/31$
 * @ 更新描述  ${TODO}
 */

public class DeleteDialog {
    private static DeleteDialog mLoadDataManager = new DeleteDialog();

    private DeleteDialog() {
    }

    public static DeleteDialog getInstance() {
        return mLoadDataManager;
    }

    public interface OnBackResult{
        void delete();
    }

    /**
     * @param activity 因为是dialog 所以一定要是activity
     * @param onBackResult  回调
     */
    public void showDilaog(final Activity activity, final OnBackResult onBackResult){
        //长按删除


        TextView textView = new TextView(activity);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        textView.setLayoutParams(layoutParams);
        textView.setText("删除");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackResult.delete();
            }
        });




    }

}
