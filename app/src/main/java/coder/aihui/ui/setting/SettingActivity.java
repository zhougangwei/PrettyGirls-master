package coder.aihui.ui.setting;

import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/3/17 15:31
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/3/17$
 * @ 更新描述  ${TODO}
 */

public class SettingActivity extends AppActivity {


    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.tb)
    LinearLayout mTb;
    @BindView(R.id.ll_set)
    LinearLayout mLlSet;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }







   /*


    @Override
    protected void initView() {


    }

    @Override
    protected int getLayoutId() {
        return R.include_tab.fragment_setting;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public boolean onBackPressed() {
        removeFragment();
        return false;
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        onBackPressed();
    }*/
}
