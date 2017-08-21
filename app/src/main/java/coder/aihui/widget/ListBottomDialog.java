package coder.aihui.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import coder.aihui.R;
import coder.aihui.data.bean.DialogBean;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/7/6 15:46
 * @ 描述    ${底部弹出的dialog}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/7/6$
 * @ 更新描述  ${TODO}
 */

public class ListBottomDialog extends Dialog {

     Context mContext;

    public ListBottomDialog(Context context) {
        super(context, R.style.bottom_dialog);
        this.mContext = context;

    }

    public ListBottomDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    public ListBottomDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    private  int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }



   public interface onBackResult {
        void backResult(DialogBean bean);
    }


    public void showDialog(List<DialogBean> mDatas, final onBackResult result) {

        final ListBottomDialog dialog = new ListBottomDialog(mContext, R.style.bottom_dialog);
        RecyclerView mRv = new RecyclerView(mContext);

        mRv.setBackgroundColor(Color.WHITE);

        mRv.setLayoutManager(new LinearLayoutManager(mContext));

        if(mDatas.size()>8){
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mRv.setLayoutParams(layoutParams);
        }else{
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mRv.setLayoutParams(layoutParams);
        }





        mRv.setAdapter(new CommonAdapter<DialogBean>(mContext,R.layout.item_text_mid,mDatas) {
            @Override
            protected void convert(ViewHolder holder, final DialogBean dialogBean, int position) {

                holder.setText(R.id.tv_name,dialogBean.getName());
                holder.setOnClickListener(R.id.tv_name, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        result.backResult(dialogBean);
                    }
                });

            }
        });

        mRv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));

       // View contentView = LayoutInflater.from(context).inflate(ResId, null);
        dialog.setContentView(mRv);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;

        if(mDatas.size()>8){
            params.height =dip2px(mContext, 770/2);
        }else{
            params.height =WindowManager.LayoutParams.WRAP_CONTENT;
        }

        window.setAttributes(params);
        window.setGravity(Gravity.BOTTOM);

        dialog.show();
    }
}
