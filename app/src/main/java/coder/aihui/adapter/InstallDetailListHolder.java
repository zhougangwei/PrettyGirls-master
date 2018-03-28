package coder.aihui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.data.bean.AZYS_MX;
import coder.aihui.data.bean.DialogBean;
import coder.aihui.widget.ListBottomDialog;

import static coder.aihui.app.MyApplication.mContext;

/**
 * @ 创建者   zhou
 * @ 创建时间   2016/12/2 10:33
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2016/12/2$
 * @ 更新描述  ${TODO}
 */

public class InstallDetailListHolder extends BaseHolder<AZYS_MX> {


    @BindView(R.id.tv_name)
    TextView     mTvName;
    @BindView(R.id.ll_choose)
    LinearLayout mLlChoose;
    @BindView(R.id.tv_result)
    TextView     mTvResult;


    public InstallDetailListHolder(Activity activity) {
        super(activity);
    }

    @Override
    protected View createItemView() {
        //动态的明细项目

        View view = View.inflate(mContext, R.layout.item_azys_detail_dt, null);
        return view;
    }

    @Override
    public void bindView(final AZYS_MX bean, final int position) {

        mTvName.setText(bean.getITEM_NAME());


        final List mDatas = new ArrayList();
        DialogBean dialogBean = new DialogBean("是", true);
        DialogBean dialogBean2 = new DialogBean("否", false);
        mDatas.add(dialogBean);
        mDatas.add(dialogBean2);
        mLlChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //选择是否
                new ListBottomDialog(mActivity).showDialog(mDatas, new ListBottomDialog.onBackResult() {
                    @Override
                    public void backResult(DialogBean backBean) {
                        bean.setIsChecked((Boolean) backBean.getObject());
                        mTvResult.setText(backBean.getName());
                    }
                });
            }
        });


    }


    @OnClick(R.id.ll_choose)
    public void onViewClicked() {
    }


}
