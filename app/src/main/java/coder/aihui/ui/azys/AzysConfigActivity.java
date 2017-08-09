package coder.aihui.ui.azys;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.ui.bd.FormActivity;
import coder.aihui.ui.pxgl.PxglListActivity;

public class AzysConfigActivity extends AppActivity {

    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.ll_bd)
    LinearLayout mLlBd;
    @BindView(R.id.pxgl)
    LinearLayout mPxgl;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_azys_config;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }


    @Override
    protected void initView() {
        mTvTitle.setText("安装验收配置");
    }


    @OnClick({R.id.iv_back, R.id.tv_title, R.id.ll_bd, R.id.pxgl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:
                mTvTitle.setText("安装验收配置");
                break;
            case R.id.ll_bd:
                gotoActivity(FormActivity.class);
                break;
            case R.id.pxgl:
                gotoActivity(PxglListActivity.class);
                break;
        }
    }
}
