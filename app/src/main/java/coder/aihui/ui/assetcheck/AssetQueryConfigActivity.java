package coder.aihui.ui.assetcheck;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.base.DlwzView;
import coder.aihui.widget.jdaddressselector.ISelectAble;
import coder.aihui.widget.jdaddressselector.SelectedListener;


public class AssetQueryConfigActivity extends AppActivity implements DlwzView, SelectedListener {


    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.tv_location)
    TextView     mTvLocation;
    @BindView(R.id.ll_location)
    LinearLayout mLlLocation;
    @BindView(R.id.tv_dept)
    TextView     mTvDept;
    @BindView(R.id.ll_dept)
    LinearLayout mLlDept;
    @BindView(R.id.tv_way)
    TextView     mTvWay;
    @BindView(R.id.ll_way)
    LinearLayout mLlWay;
    @BindView(R.id.tv_save)
    TextView     mTvSave;


    String mDlwzName;
    String mDlwzIds;
    String mDeptName;
    String mDeptIds;

    String mAllDlwzIds;
    String mAllDeptIds;
    String mAllDeptName;
    String mAllDlwzName;


    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }


    @Override
    protected int getContentViewId() {
        return (R.layout.activity_asset_check_config);
    }


    @Override
    protected void initView() {
        mTvTitle.setText("资产清点配置");
        initIntent();

    }


    private void initIntent() {
        Intent intent = getIntent();
        int where = intent.getIntExtra("where", -1);
        switch (where){
            case Content.ASSET_QUERY:
                mTvTitle.setText("台账查询配置");
                break;
            case Content.AZYS_CONFIG:
                mTvTitle.setText("安装验收配置");
                break;
            case Content.ASSET_CHECK:
                mTvTitle.setText("资产清点配置");
        }

         mAllDeptIds =  intent.getStringExtra("mAllDeptIds");
         mAllDeptName =  intent.getStringExtra("mAllDeptName");
         mAllDlwzIds = intent.getStringExtra("mAllDlwzIds");
         mAllDlwzName = intent.getStringExtra("mAllDlwzName");

        //回显
        mTvDept.setText(mAllDeptName);
        mTvLocation.setText(mAllDlwzName);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.ll_location, R.id.ll_dept, R.id.ll_way, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                backAssetquery();
                finish();           //返回
                break;
            case R.id.ll_location:
                getLocation(this);
                break;
            case R.id.ll_dept:
                getDept(this);  //弹出地址选择框
                break;
            case R.id.ll_way:
                break;
            case R.id.tv_save:
                backAssetquery();
                finish();
                break;
        }
    }


    //保存数据 滚回 assetQuery
    private void backAssetquery() {
        Intent intent = new Intent();
        intent.putExtra("mDeptName", mDeptName);
        intent.putExtra("mDeptIds", mDeptIds);
        intent.putExtra("mDlwzName", mDlwzName);
        intent.putExtra("mDlwzIds", mDlwzIds);
        intent.putExtra("mAllDeptName", mAllDeptName);
        intent.putExtra("mAllDeptIds", mAllDeptIds);
        intent.putExtra("mAllDlwzIds", mAllDlwzIds);
        intent.putExtra("mAllDlwzName", mAllDlwzName);
        setResult(RESULT_OK, intent);
    }


    @Override
    public void onAddressSelected(ArrayList<ISelectAble> selectAbles) {

        String result = "";
        StringBuilder sbids = new StringBuilder();
        String arg = "";
        sbids.append("(");

        for (int i = 0; i < selectAbles.size(); i++) {

            ISelectAble selectAble = selectAbles.get(i);
            if (i != selectAbles.size() - 1) {
                arg = selectAble.getArg();
                String name = selectAble.getName();
                int id = selectAble.getId();
                result += name + "-";
                sbids.append(id).append(",");
            } else {
                arg = selectAble.getArg();
                String name = selectAble.getName();
                int id = selectAble.getId();
                result += name;
                sbids.append(id);
            }
        }
        sbids.append(")");
        if (selectAbles != null && selectAbles.size() > 0) {
            ISelectAble iSelectAble = selectAbles.get(selectAbles.size() - 1);

            if ("dept".equals(arg)) {
                mDeptName = iSelectAble.getName();
                mDeptIds = iSelectAble.getId() + "";
            } else if ("location".equals(arg)) {
                mDlwzName = iSelectAble.getName();
                mDlwzIds = iSelectAble.getId() + "";
            }

        }

        if (!TextUtils.isEmpty(result)) {
            if ("dept".equals(arg)) {
                mAllDeptName = result.substring(0, result.length() - 1);
                mTvDept.setText(mAllDeptName);
            } else if ("location".equals(arg)) {
                mAllDlwzName = result.substring(0, result.length() - 1);
                mTvLocation.setText(mAllDlwzName);
            }
        }


        String ids = sbids.toString();
        if (ids.length() != 2) {              //说明只有()
            if ("dept".equals(arg)) {
                mAllDeptIds = ids;
            } else if ("location".equals(arg)) {
                mAllDlwzIds = ids;
            }
        }

    }

    @Override
    public void onBackPressed() {
        backAssetquery();

        super.onBackPressed();

    }
}
