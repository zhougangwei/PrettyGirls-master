package coder.aihui.ui.main;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.BaseFragment;
import coder.aihui.ui.login.LoginActivity;
import coder.aihui.ui.setting.SettingActivity;
import coder.aihui.util.SPUtil;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/3/17 15:31
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/3/17$
 * @ 更新描述  ${TODO}
 */

public class MyFragment extends BaseFragment<MyPresenter> {


    @BindView(R.id.ll_set)
    LinearLayout   mLlSet;
    @BindView(R.id.tv_sum)
    TextView       mTvSum;
    @BindView(R.id.rl_use)
    RelativeLayout mRlUse;
    @BindView(R.id.rl_version)
    RelativeLayout mRlVersion;
    @BindView(R.id.tv_exit)
    TextView       mTvExit;


    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }



    @OnClick(R.id.ll_set)
    public void onClick() {

        startActivity(new Intent(getActivity(), SettingActivity.class));
    }



    @OnClick({R.id.rl_use, R.id.rl_version, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_use:           //使用说明
                break;
            case R.id.rl_version:       //版本信息
                break;
            case R.id.tv_exit:          //退出
                SPUtil.saveBoolean(getActivity(),"isLogin",false);
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
        }
    }
}
