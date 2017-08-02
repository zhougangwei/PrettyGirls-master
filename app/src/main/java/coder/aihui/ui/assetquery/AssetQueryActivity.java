package coder.aihui.ui.assetquery;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.ui.assetcheck.AssetQueryConfigActivity;
import coder.aihui.widget.AlertListDialogUtil;

public class AssetQueryActivity extends AppActivity {

    @BindView(R.id.iv_scan)
    LinearLayout mIvScan;
    @BindView(R.id.iv_search)
    LinearLayout mIvSearch;
    @BindView(R.id.iv_config)
    LinearLayout mIvConfig;
    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.sp_search)
    Spinner      mSpSearch;
    @BindView(R.id.et_search)
    EditText     mEtSearch;
    @BindView(R.id.tv_search)
    TextView     mTvSearch;
    @BindView(R.id.tb)
    TabLayout    mTb;
    @BindView(R.id.vp)
    ViewPager    mVp;

    private String mDeptName;
    private String mDeptIds;
    private String mDlwzName;
    private String mDlwzIds;       //相当于All

    private String mAllDlwzIds = "";//所有的子地理位置

    private String mAllDeptIds = "";

    private String            mAllDeptName  = "";
    private String            mAllDlwzName  = "";
    private Integer           pdaType       = 1;        //1是rfid 2是红外 6是摄像头
    private ArrayList<String> mTitleList    = new ArrayList<>();
    private List<Fragment>    mFragmentList = new ArrayList<>();
    private AssetQueryFragment mAssetQueryFragment; //

    @Override
    protected int getContentViewId() {
        return R.layout.activity_asset_query;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

    @Override
    protected void initView() {

        mTvTitle.setText("台账管理");
        mTitleList.add("全部");
        mTitleList.add("已修改");

        mAssetQueryFragment = new AssetQueryFragment();
        AssetQueryFragment assetQueryFragment2 = new AssetQueryFragment();
        mFragmentList.add(mAssetQueryFragment);
        mFragmentList.add(assetQueryFragment2);

        mVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mTitleList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitleList.get(position);
            }
        });
        mTb.setupWithViewPager(mVp);

    }

    @OnClick({R.id.iv_scan, R.id.iv_search, R.id.iv_config, R.id.iv_back, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                pdaType = 6;
                open2Scan();            //打开二维码
                break;
            case R.id.iv_search:
                gotoSearch();           //搜索
                break;
            case R.id.iv_config:
                openConfig();           //配置
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search:
                gotoTextSearch();       //文字搜索
                break;
        }
    }

    private void gotoTextSearch() {
        switch (mTb.getSelectedTabPosition()) {
            case 0: //说明当前选中的是全部
                Editable text = mEtSearch.getText();
                if (!TextUtils.isEmpty(text)) {
                    mAssetQueryFragment.setSeachText(text.toString());
                    //这里需要正在搜索
                    mAssetQueryFragment.loadMeinv(0, 10);
                }
                break;
            case 1://说明当前选中的是已清点
                break;
        }

    }

    //用来弹框的
    private void gotoSearch() {
        AlertListDialogUtil.getInstance().showDialog("", mDaoSession, R.array.kpbh2, this, new AlertListDialogUtil.onGetResult() {
            @Override
            public void getResult(String result) {
                HashSet<String> set = new HashSet<>();
                set.add(result);
                doSaveListData(set);
            }
        });

    }

    private void openConfig() {

        Intent intent = new Intent(this, AssetQueryConfigActivity.class);
        startActivityForResult(intent, Content.REQUEST_CONFIG);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Content.REQUEST_CONFIG) {
            if (resultCode == RESULT_OK) {
                mDeptName = data.getStringExtra("mDeptName");
                mDeptIds = data.getStringExtra("mDeptIds");
                mDlwzName = data.getStringExtra("mDlwzName");
                mDlwzIds = data.getStringExtra("mDlwzIds");

                mAllDeptName = data.getStringExtra("mAllDeptName");
                mAllDeptIds = data.getStringExtra("mAllDeptIds");
                mAllDlwzIds = data.getStringExtra("mAllDlwzIds");
                mAllDlwzName = data.getStringExtra("mAllDlwzName");

            }
        }


    }

}
