package coder.aihui.ui.azys;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.data.bean.DHBean;
import coder.aihui.data.bean.PUR_CONTRACT_PLAN;
import coder.aihui.data.bean.PUR_CONTRACT_PLAN_DETAIL;
import coder.aihui.data.bean.gen.DHBeanDao;
import coder.aihui.data.bean.gen.PUR_CONTRACT_PLAN_DETAILDao;
import coder.aihui.data.normalbean.PartsBean;
import coder.aihui.data.normalbean.PjmxBean;
import coder.aihui.ui.pxgl.PxglDetailActivity;
import coder.aihui.util.GsonUtil;
import coder.aihui.util.ListUtils;
import coder.aihui.util.ToastUtil;
import coder.aihui.widget.autoview.AutoRecyclerView;

import static coder.aihui.R.id.tv_ys;
import static coder.aihui.base.Content.AZYS_DETAIL_IDS;
import static coder.aihui.base.Content.SB_IDS;


public class AzysListActivity extends AppActivity {

    @BindView(R.id.iv_back)
    ImageView        mIvBack;
    @BindView(R.id.tv_title)
    TextView         mTvTitle;
    @BindView(R.id.tv_name)
    TextView         mTvName;
    @BindView(R.id.tv_ggxh)
    TextView         mTvGgxh;
    @BindView(R.id.tv_dept)
    TextView         mTvDept;
    @BindView(R.id.ll_names)
    LinearLayout     mLlNames;
    @BindView(R.id.rv)
    AutoRecyclerView mRv;
    @BindView(tv_ys)
    TextView         mTvYs;

    @BindView(R.id.iv_pxgl)
    ImageView mIvPxgl;


    ArrayList<PUR_CONTRACT_PLAN_DETAIL> mDatas      = new ArrayList();
    boolean                             IsFirstTime = true;
    private PUR_CONTRACT_PLAN mFatherBean;
    private List<PUR_CONTRACT_PLAN_DETAIL> mList = new ArrayList<>();
    private Long mHtmxId;


    //哪些设备是需要跳往下一个页面的
    ArrayList<String> sonBeanIdList = new ArrayList();
    private CommonAdapter<PUR_CONTRACT_PLAN_DETAIL> mAdapter;
    private AlertDialog                             mDialog;
    private List<DHBean> mDHList = new ArrayList<>();
    private CommonAdapter<DHBean> mDhAdapter;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_azyslist;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }


    @Override
    protected void initView() {
        initGetIntent();
        initRecycleView();
        initDatas();
    }

    private void initGetIntent() {
        Intent intent = getIntent();

        mHtmxId = intent.getLongExtra("htmxId", -1L);

        mFatherBean = mDaoSession.getPUR_CONTRACT_PLANDao().load(mHtmxId);

        mTvName.setText(mFatherBean.getWZMC());
        mTvGgxh.setText(mFatherBean.getGGXH());
        mTvDept.setText(mFatherBean.getDEPT_NAME());

    }


    //区分对待是否是第一次进入
    private void initDatas() {
        List<PUR_CONTRACT_PLAN_DETAIL> list = mDaoSession.getPUR_CONTRACT_PLAN_DETAILDao().queryBuilder().
                where(PUR_CONTRACT_PLAN_DETAILDao.Properties.HTMX_ID.eq(mHtmxId)).list();

        updateDh();

        if (list != null && list.size() != 0) {
            mDatas.addAll(list);
            IsFirstTime = false;
        } else {
            IsFirstTime = true;
            initFirstData();
        }
    }


    //初始化RecycleView
    private void initRecycleView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7);

        mRv.setLayoutManager(gridLayoutManager);


        mAdapter = new CommonAdapter<PUR_CONTRACT_PLAN_DETAIL>(this, R.layout.item_text_num_point, mDatas) {
            @Override
            protected void convert(final ViewHolder holder, final PUR_CONTRACT_PLAN_DETAIL o, final int position) {
                holder.setText(R.id.num, (position + 1) + "");
                holder.setOnClickListener(R.id.rv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //有的增加没的删除
                        if (!sonBeanIdList.contains(o.getID() + "")) {
                            holder.setBackgroundRes(R.id.rv, R.color.chooseGrey);
                            sonBeanIdList.add(o.getID() + "");
                        } else {
                            holder.setBackgroundRes(R.id.rv, R.color.white);
                            sonBeanIdList.remove(o.getID() + "");
                        }
                    }
                });
            }
        };

        mRv.setAdapter(mAdapter);
    }

    //第一次进来获取数据
    private void initFirstData() {
        mList.clear();
        Integer mItemNum = mFatherBean.getYSSL();
        for (int j = 0; j < mItemNum; j++) {
            //状态1为  子的已检查 数量为1其余和父亲都一样
            //纯体力活
            PUR_CONTRACT_PLAN_DETAIL sonBean = plan2Detail();
            mList.add(sonBean);
            mDaoSession.insertOrReplace(sonBean);
        }

        mDatas.addAll(mList);
    }

    @OnClick({R.id.iv_back, R.id.tv_ys, R.id.iv_pxgl})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_ys:
                gotoChooseForm();
                break;
            case R.id.iv_pxgl:
                gotoPxgl();
        }
    }

    private void gotoPxgl() {

        if (sonBeanIdList.size() == 0) {
            return;
        }
        Integer mcggid = mDaoSession.getPUR_CONTRACT_PLAN_DETAILDao().load(Long.valueOf(sonBeanIdList.get(0)))
                .getMCGGID();
        Intent intent = new Intent(this, PxglDetailActivity.class);
        intent.putExtra(SB_IDS, mcggid + "");

        startActivity(intent);


    }

    private void gotoChooseForm() {

        if (sonBeanIdList.size() == 0) {
            ToastUtil.showToast("请选择设备!");
            return;
        }
        if (!IsFirstTime) {
            gotoChooseList();
        } else {
            gotoYs();
        }


    }

    //跳转到验收页面
    private void gotoYs() {
        Intent intent = new Intent(this, AzysDetailActivity.class);
        String ids = ListUtils.listToStrings(sonBeanIdList);
        intent.putExtra(AZYS_DETAIL_IDS, ids);
        intent.putExtra("isFirstTime", IsFirstTime);
        startActivityForResult(intent, Content.AZYS_LIST_REQUEST_CODE);
    }


    //选择框的条目选择
    private void gotoChooseList() {

        //跳窗的dialog
        View view = View.inflate(this, R.layout.installlist_numlist, null);


        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv);
        TextView add = (TextView) view.findViewById(R.id.add);

        rv.setLayoutManager(new LinearLayoutManager(this));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer contract_id = mFatherBean.getCONTRACT_ID();
                DHBean dhBean = new DHBean();
                dhBean.setDh(contract_id + "-" + (mDHList.size() + 1));
                dhBean.setWzmc(mFatherBean.getWZMC());
                dhBean.setContractId(mFatherBean.getCONTRACT_ID() + "");
                mDaoSession.insertOrReplace(dhBean);
                updateDh();
                mDhAdapter.notifyDataSetChanged();

            }
        });

        mDhAdapter = new CommonAdapter<DHBean>(this, R.layout.item_text_pd30, mDHList) {
            @Override
            protected void convert(ViewHolder holder, DHBean dhBean, int position) {
                holder.setText(R.id.tv_name, dhBean.getDh());
                holder.setOnClickListener(R.id.tv_name, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gotoYs();
                    }
                });
            }
        };
        rv.setAdapter(mDhAdapter);

        if (mDHList.size() != 0) {
            //只要不是空 则跳出
            mDialog = new AlertDialog.Builder(this)
                    .setView(view).create();
            mDialog.show();
        } else {
            Integer con_id = mFatherBean.getCONTRACT_ID();
            DHBean dhBean = new DHBean();
            dhBean.setDh(con_id + "-1");
            dhBean.setWzmc(mFatherBean.getWZMC());
            dhBean.setContractId(mFatherBean.getCONTRACT_ID() + "");
            mDaoSession.insertOrReplace(dhBean);
            updateDh();
            mDhAdapter.notifyDataSetChanged();
        }

    }

    private void updateDh() {
        mDHList.clear();
        //单号的
        List<DHBean> dhBeanList = mDaoSession.getDHBeanDao().queryBuilder().where(DHBeanDao.Properties.ContractId.eq(mFatherBean.getCONTRACT_ID() + ""))
                .list();
        mDHList.addAll(dhBeanList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Content.AZYS_LIST_REQUEST_CODE:
                    int num = data.getIntExtra("num", 0);       //验收了几台
                    mFatherBean.setCHECK_SL(num);
                    break;
            }
        }


    }

    //将plan转换增多生成detail
    private PUR_CONTRACT_PLAN_DETAIL plan2Detail() {
        PUR_CONTRACT_PLAN_DETAIL sonBean = new PUR_CONTRACT_PLAN_DETAIL();
        sonBean.setZCZH(mFatherBean.getZCZH());

        if (!TextUtils.isEmpty(mFatherBean.getQSDQSJ())) {
            sonBean.setQSDQSJ(TimeUtils.string2Date(mFatherBean.getQSDQSJ(), new SimpleDateFormat("yyyy-MM-dd")));
        }
        sonBean.setBXQ(mFatherBean.getBXQ());
        sonBean.setGCJK(mFatherBean.getGCJK());
        sonBean.setBRAND(mFatherBean.getBRAND());


        if (!TextUtils.isEmpty(mFatherBean.getPARTS_STRING())) {
            //配件
            List<PartsBean> parts = GsonUtil.parseJsonToList(mFatherBean.getPARTS_STRING(), new TypeToken<PUR_CONTRACT_PLAN>() {
            }.getType());
            ArrayList<PjmxBean> al = new ArrayList<>();
            for (int i = 0; i < parts.size(); i++) {
                PartsBean partsBean = parts.get(i);
                //将计划里的配件 转成配件明细
                PjmxBean pjmxBean = pjBean2pjmx(partsBean);
                al.add(pjmxBean);
            }
            sonBean.setPARTS(GsonUtil.parseListToJson(al));
        }


        sonBean.setCHECK_STATUS(2);
        sonBean.setHTMX_ID(mFatherBean.getHTMX_ID());
        sonBean.setGYSMC(mFatherBean.getGYSMC());
        sonBean.setDEPT_NAME(mFatherBean.getDEPT_NAME());
        sonBean.setDEPT_ID(mFatherBean.getDEPT_ID());
        sonBean.setCHECK_ISCHECKED(false);

        //sonBean.setAZGCS(mFatherBean.getAZGCS());

        sonBean.setCONTRACT_ID(mFatherBean.getCONTRACT_ID());
        sonBean.setCONTRACT_NUM(mFatherBean.getCONTRACT_NUM());
        sonBean.setWZMC(mFatherBean.getWZMC());

        sonBean.setWZMC(mFatherBean.getWZMC());
        sonBean.setMCGGID(mFatherBean.getMCGGID());
        sonBean.setGGXH(mFatherBean.getGGXH());
        sonBean.setPLAN_ID(mFatherBean.getPLAN_ID());
        sonBean.setYSSL(1);
        sonBean.setIS_UP(2);
        sonBean.setCHECK_ISCHECKED(false);

        return sonBean;
    }

    /**
     * @param partsBean plan类的配件
     * @return
     */
    @NonNull
    private PjmxBean pjBean2pjmx(PartsBean partsBean) {
        PjmxBean pjmxBean = new PjmxBean();
        pjmxBean.setPdUnit(partsBean.getPD_UNIT());
        //pjmxBean.setPdBxq(partsBean.getBXJZRQ());
        pjmxBean.setBxjzrq2(partsBean.getBXJZRQ());
        pjmxBean.setPdBrand(partsBean.getPD_BRAND());
        pjmxBean.setPdCode(partsBean.getPD_CODE());
        pjmxBean.setPdGgxh(partsBean.getPD_GGXH());
        pjmxBean.setPdNum(partsBean.getPD_NUM());
        pjmxBean.setPdPjmc(partsBean.getPD_PJMC());
        pjmxBean.setPdPrice(partsBean.getPD_PRICE());
        pjmxBean.setPdRemark(partsBean.getPD_REMARK());
        pjmxBean.setPdUnit(partsBean.getPD_UNIT());
        return pjmxBean;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
