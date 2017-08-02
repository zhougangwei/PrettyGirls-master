package coder.aihui.ui.azys;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.data.normalbean.PjmxBean;
import coder.aihui.util.DeleteDialog;
import coder.aihui.util.GsonUtil;

public class PjmxActivity extends AppActivity {

    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.iv_add)
    LinearLayout mIvAdd;
    @BindView(R.id.rv)
    RecyclerView mRv;
    private List<PjmxBean> mDatas = new ArrayList<>();
    private CommonAdapter<PjmxBean> mAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_pjmx;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }


    int pjmxId = 101;

    @Override
    protected void initView() {

        mRv.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new CommonAdapter<PjmxBean>(this, R.layout.item_pjmx, mDatas) {
            @Override
            protected void convert(ViewHolder holder, final PjmxBean bean, int position) {

                holder.setText(R.id.tv_mcpp, bean.getPdPjmc() + "(" + bean.getPdBrand() + ")");
                holder.setText(R.id.tv_ggxh, bean.getPdGgxh());
                holder.setText(R.id.tv_scbh, bean.getPdCode());
                holder.setText(R.id.tv_bxsj, bean.getBxjzrq2());
                holder.setText(R.id.tv_pjdh, (pjmxId + position) + "");
                holder.setText(R.id.tv_num, bean.getPdNum() + bean.getPdUnit());
                holder.setText(R.id.tv_prize, "¥" + bean.getPdPrice() + "/" + bean.getPdUnit());
                holder.setOnLongClickListener(R.id.rl, new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        DeleteDialog.getInstance().showDilaog(PjmxActivity.this, new DeleteDialog.OnBackResult() {
                            @Override
                            public void delete() {
                                mDatas.remove(bean);
                                mAdapter.notifyDataSetChanged();
                            }
                        });
                        return false;
                    }
                });

                holder.setOnClickListener(R.id.rl, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转到配件
                    }
                });

            }
        };

        mRv.setAdapter(mAdapter);

        mTvTitle.setText("配件明细");
        initGetIntent();

    }

    private void initGetIntent() {
        Intent intent = getIntent();
        String parts = intent.getStringExtra("parts");
        showPjmx(parts);
    }

    //显示配件明细
    private void showPjmx(String pjmxJson) {
        if (!TextUtils.isEmpty(pjmxJson)) {
            List<PjmxBean> list = GsonUtil.parseJsonToList(pjmxJson, new TypeToken<List<PjmxBean>>() {
            }.getType());
            mDatas.clear();
            mDatas.addAll(list);
        }
    }

    @OnClick({R.id.iv_back, R.id.iv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                backSaveData();
                break;
            case R.id.iv_add:
                gotoAddPj();
                break;

        }
    }


    //返回保存数据
    private void backSaveData() {
        Intent intent = new Intent();
        intent.putExtra("parts", GsonUtil.parseListToJson(mDatas));
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    public void onBackPressed() {
        backSaveData();
    }

    //去动态添加配件
    private void gotoAddPj() {
        MyAlertDialogUtil.getInstance().showDialog(pjmxId, null, this, new MyAlertDialogUtil.onGetResult() {
            @Override
            public void getResult(PjmxBean bean, Integer pxId) {
                pjmxId = pxId;
                if (bean != null) {
                    mDatas.add(bean);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
