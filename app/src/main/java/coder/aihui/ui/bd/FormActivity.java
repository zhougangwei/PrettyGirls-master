package coder.aihui.ui.bd;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.data.bean.DHBean;

public class FormActivity extends AppActivity {

    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.sp_search)
    Spinner      mSpSearch;
    @BindView(R.id.et_search)
    EditText     mEtSearch;
    @BindView(R.id.tv_search)
    TextView     mTvSearch;
    @BindView(R.id.rv)
    RecyclerView mRv;
    private List<DHBean> mDatas = new ArrayList<>();
    private CommonAdapter<DHBean> mAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_form;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }


    @Override
    protected void initView() {
        initRecycleView();
    }

    private void initRecycleView() {
        mAdapter = new CommonAdapter<DHBean>(this
                , R.layout.item_form, mDatas) {
            @Override
            protected void convert(ViewHolder holder, DHBean bean, int position) {
                holder.setText(R.id.tv_dh, bean.getDh());
                holder.setText(R.id.tv_mc, bean.getWzmc());
                holder.setText(R.id.tv_num, bean.getNum() + "");
            }
        };
        mRv.setAdapter(mAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
