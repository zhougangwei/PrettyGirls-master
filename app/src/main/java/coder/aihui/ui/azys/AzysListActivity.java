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
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

import static coder.aihui.base.Content.AZYS_DETAIL_IDS;
import static coder.aihui.base.Content.AZYS_DETAIL_REQUEST_CODE;
import static coder.aihui.base.Content.SB_IDS;
import static coder.aihui.base.Content.USER_ID_LIST;
import static coder.aihui.base.Content.USER_NAME_LIST;


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
    @BindView(R.id.tv_ys)
    TextView         mTvYs;

    @BindView(R.id.iv_pxgl)
    ImageView mIvPxgl;
    @BindView(R.id.tv_ryname)
    TextView  mTvRyname;

    ArrayList<PUR_CONTRACT_PLAN_DETAIL> mDatas = new ArrayList();
    //boolean                             IsFirstTime = true;         //是否是第一次进来 决定了是否需要新建单号表
    private PUR_CONTRACT_PLAN mFatherBean;                          //主对象
    private List<PUR_CONTRACT_PLAN_DETAIL> mList = new ArrayList<>();
    private Long mHtmxId;


    //哪些设备是需要跳往下一个页面的
    ArrayList<String> mSonBeanIdList = new ArrayList();
    private CommonAdapter<PUR_CONTRACT_PLAN_DETAIL> mAdapter;
    private AlertDialog                             mDialog;
    private List<DHBean> mDHList = new ArrayList<>();
    private CommonAdapter<DHBean> mDhAdapter;
    private ArrayList<String>     mUserIdList;


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
        mTvTitle.setText("设备验收");
        initGetIntent();
        initRecycleView();
        refreshDatas();
    }

    private void initGetIntent() {
        Intent intent = getIntent();
        mHtmxId = intent.getLongExtra("htmxId", -1L);
        ArrayList<String> mUserNameList = intent.getStringArrayListExtra(USER_NAME_LIST);   //验收人名字的集合 方便显示
        mUserIdList = intent.getStringArrayListExtra(USER_ID_LIST);             //验收人

        mTvRyname.setText(ListUtils.listToStrings(mUserNameList));
        mFatherBean = mDaoSession.getPUR_CONTRACT_PLANDao().load(mHtmxId);      //主对象
        mFatherBean.setYSR_IDS(ListUtils.listToStrings(mUserIdList));           //动态变化 随时变

        mTvName.setText(mFatherBean.getWZMC());
        mTvGgxh.setText(mFatherBean.getGGXH());
        mTvDept.setText(mFatherBean.getDEPT_NAME());

    }


    //区分对待是否是第一次进入
    private void refreshDatas() {
        List<PUR_CONTRACT_PLAN_DETAIL> list = mDaoSession.getPUR_CONTRACT_PLAN_DETAILDao().queryBuilder().
                where(PUR_CONTRACT_PLAN_DETAILDao.Properties.HTMX_ID.eq(mHtmxId)).list();
        updateDh();
        //没数据说明是第一次进来
        if (list != null && list.size() != 0) {     //有数据
            mDatas.clear();
            mDatas.addAll(list);
            //IsFirstTime = false;
        } else {                                    //没数据
            //IsFirstTime = true;
            initFirstData();                        //新建数据
        }
        for (PUR_CONTRACT_PLAN_DETAIL data : mDatas) {
            if (data.getIS_UP() != 1) {
                data.setYSR_IDS(mFatherBean.getYSR_IDS()); //已经上传过的数据就不给改了
            }
        }
        mAdapter.notifyDataSetChanged();
    }


    //初始化RecycleView
    private void initRecycleView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7);

        mRv.setLayoutManager(gridLayoutManager);


        mAdapter = new CommonAdapter<PUR_CONTRACT_PLAN_DETAIL>(this, R.layout.item_text_num_point, mDatas) {
            @Override
            protected void convert(final ViewHolder holder, final PUR_CONTRACT_PLAN_DETAIL bean, final int position) {
                holder.setText(R.id.num, (position + 1) + "");
                //显示是否是已点击了
                if (bean.getCHECK_STATUS() == 1) {
                    holder.setVisible(R.id.iv_point, true);
                } else {
                    holder.setVisible(R.id.iv_point, false);
                }
                holder.setOnClickListener(R.id.rv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //已检的直接跳,未检的打上勾
                        if (bean.getCHECK_STATUS() == 1) {
                            Intent intent = new Intent(AzysListActivity.this
                                    , AzysDetailActivity.class);
                            String ids = bean.getID() + "";
                            intent.putExtra(AZYS_DETAIL_IDS, ids);
                            intent.putExtra("isFirstTime", false);
                            intent.putExtra("dh",bean.getDH_ID());
                            startActivityForResult(intent, AZYS_DETAIL_REQUEST_CODE);
                        } else {
                            //有的增加没的删除
                            if (!mSonBeanIdList.contains(bean.getID() + "")) {
                                holder.setBackgroundRes(R.id.rv, R.color.red);
                                mSonBeanIdList.add(bean.getID() + "");
                            } else {
                                holder.setBackgroundRes(R.id.rv, R.color.white);
                                mSonBeanIdList.remove(bean.getID() + "");
                            }
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

        Integer mcggid = mDaoSession.getPUR_CONTRACT_PLAN_DETAILDao().load(Long.valueOf(mDatas.get(0).getID()))
                .getMCGGID();
        Intent intent = new Intent(this, PxglDetailActivity.class);
        intent.putExtra(SB_IDS, mcggid + "");
        startActivityForResult(intent, Content.AZYS_DETAIL_REQUEST_CODE);


    }

    private void gotoChooseForm() {
        if (mSonBeanIdList.size() == 0) {
            ToastUtil.showToast("请选择设备!");
            return;
        }
        gotoChooseList();

    }

    //跳转到验收页面
    private void gotoYs(Long dh) {
        Intent intent = new Intent(this, AzysDetailActivity.class);
        String ids = ListUtils.listToStrings(mSonBeanIdList);
        intent.putExtra(AZYS_DETAIL_IDS, ids);
        intent.putExtra("isFirstTime", true);
        intent.putExtra("dh", dh);
        startActivityForResult(intent, Content.AZYS_DETAIL_REQUEST_CODE);
    }


    //选择框的条目选择
    private void gotoChooseList() {

        //跳窗的dialog
        View view = View.inflate(this, R.layout.installlist_numlist, null);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv);
        TextView add = (TextView) view.findViewById(R.id.add);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);

        rv.setLayoutManager(new LinearLayoutManager(this));

        //添加新合同
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer contract_id = mFatherBean.getCONTRACT_ID();
                DHBean dhBean = new DHBean();
                dhBean.setDh(contract_id + "-" + (mDHList.size() + 1));
                dhBean.setWzmc(mFatherBean.getWZMC());
                dhBean.setContractId(mFatherBean.getCONTRACT_ID() + "");
                dhBean.setHtmxId(mFatherBean.getHTMX_ID());
                mDaoSession.insertOrReplace(dhBean);
                updateDh();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDhAdapter = new CommonAdapter<DHBean>(this, R.layout.item_text_pd30, mDHList) {
            @Override
            protected void convert(ViewHolder holder, final DHBean dhBean, int position) {
                holder.setText(R.id.tv_name, dhBean.getDh());
                holder.setOnClickListener(R.id.tv_name, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                        //合同号相同的 则合并
                        //查询当前dhBean的 dhId 内的数据 是否有htmxid一样的
                        List<DHBean> list = mDaoSession.getDHBeanDao().queryBuilder().where(
                                DHBeanDao.Properties.Dh.eq(dhBean.getDh())
                        ).list();
                        List<String> getHtmxId = ListUtils.ListFiled2list(list, "getHtmxId", DHBean.class);

                        if (!getHtmxId.contains(mFatherBean.getHTMX_ID() + "")) {
                            //新建一个Danhaobean  是为了 单号表渲染的时候 是一个个对象  不用拆分不同设备
                            DHBean newDhBean = new DHBean();
                            //除了主键id不能一样 其余的都一样
                            newDhBean.setContractId(mFatherBean.getCONTRACT_ID() + "");
                            newDhBean.setWzmc(mFatherBean.getWZMC());
                            newDhBean.setHtmxId(mFatherBean.getHTMX_ID());
                            newDhBean.setDh(dhBean.getDh());
                            long newDhBeanId = mDaoSession.insertOrReplace(newDhBean);
                            gotoYs(newDhBeanId);
                        }else{
                            gotoYs(dhBean.getId());
                        }
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
            dhBean.setHtmxId(mFatherBean.getHTMX_ID());
            dhBean.setWzmc(mFatherBean.getWZMC());
            dhBean.setContractId(mFatherBean.getCONTRACT_ID() + "");
            mDaoSession.insertOrReplace(dhBean);
            updateDh();
            mDhAdapter.notifyDataSetChanged();
        }

    }

    private void updateDh() {

        //只要单号相同的数据
        mDaoSession.getDHBeanDao().queryBuilder().where(DHBeanDao.Properties.ContractId.eq(mFatherBean.getCONTRACT_ID() + ""))
                .orderAsc(DHBeanDao.Properties.Dh)                  //更具单号升序
                .rx().oneByOne().distinctUntilChanged(new Func1<DHBean, String>() {
            @Override
            public String call(DHBean dhBean) {
                return dhBean.getDh();
            }
        }).toList().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<DHBean>>() {
            @Override
            public void call(List<DHBean> dhBeen) {
                mDHList.clear();
                mDHList.addAll(dhBeen);
                if (mDhAdapter != null) {
                    mDhAdapter.notifyDataSetChanged();
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Content.AZYS_DETAIL_REQUEST_CODE:


                    int num = data.getIntExtra("num", 0);       //验收了几台
                    mFatherBean.setCHECK_SL(num);
                    mDaoSession.insertOrReplace(mFatherBean);    //变更
                    mSonBeanIdList.clear();     //清空已选的数据
                    refreshDatas();            //刷新下数据
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

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }
}
