package coder.aihui.ui.inspectxj;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;
import com.bumptech.glide.Glide;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.greendao.query.QueryBuilder;
import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.data.bean.INSPECT_EXT;
import coder.aihui.data.bean.INSPECT_GROUP;
import coder.aihui.data.bean.INSPECT_PLAN;
import coder.aihui.data.bean.INSPECT_REP;
import coder.aihui.data.bean.INSPECT_REPS;
import coder.aihui.data.bean.INSPECT_REP_PIC;
import coder.aihui.data.bean.IN_ASSET;
import coder.aihui.data.bean.InspectTempletItem;
import coder.aihui.data.bean.REPAIR_PLACE;
import coder.aihui.data.bean.gen.INSPECT_EXTDao;
import coder.aihui.data.bean.gen.INSPECT_GROUPDao;
import coder.aihui.data.bean.gen.INSPECT_PLANDao;
import coder.aihui.data.bean.gen.INSPECT_REPDao;
import coder.aihui.data.bean.gen.INSPECT_REPSDao;
import coder.aihui.data.bean.gen.INSPECT_REP_PICDao;
import coder.aihui.data.bean.gen.IN_ASSETDao;
import coder.aihui.data.bean.gen.InspectTempletItemDao;
import coder.aihui.data.bean.gen.REPAIR_PLACEDao;
import coder.aihui.ui.assetcheck.GifSizeFilter;
import coder.aihui.util.AndroidUtils;
import coder.aihui.util.SPUtil;
import coder.aihui.util.ToastUtil;
import coder.aihui.widget.FullyLineLayoutManager;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static coder.aihui.app.MyApplication.daoSession;
import static coder.aihui.base.Content.INSPECT_DETAIL2_REQUESET_CODE;

;

public class InspectDetailActivity extends AppActivity {


    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.tv_name)
    TextView     mTvName;
    @BindView(R.id.tv_ggxh)         //卡片编号
    TextView     mTvKpbh;
    @BindView(R.id.tv_dept)         //台账还是巡检点
    TextView     mTvType;
    @BindView(R.id.ll_names)
    LinearLayout mLlNames;
    @BindView(R.id.tv_ryname)
    TextView     mTvDlwz;
    @BindView(R.id.tv_planTime)
    TextView     mTvPlanTime;
    @BindView(R.id.tv_currentTime)
    TextView     mTvCurrentTime;
    @BindView(R.id.rb_all)
    RadioButton  mRbAll;
    @BindView(R.id.tv_yzk)          //代表的是默认
    TextView     mTvMr;
    @BindView(R.id.tv_lsyd)         //代表的是暂存
    TextView     mTvZc;
    @BindView(R.id.linearLayout)
    LinearLayout mLinearLayout;
    @BindView(R.id.tv_add_pic)
    TextView     mTvAddPic;
    @BindView(R.id.tv_ok)
    TextView     mTvOk;

    @BindView(R.id.rv)
    RecyclerView mRv;

    private static final int     FINISH_ALL  = 1;    //全部完成
    private static final int     FINISH_PART = 3;   //完成部分\
    private              String  mINSP_fk_id = "";           //区分台账还是巡检点
    private              Integer isPlan      = 0;//是否计划  0:不是   1:计划表存在，过期or还没到 2:是正常的计划 ,3有模版
    private              Long    planId      = 0L;//巡检计划ID, 或者保存本地的ID  或者是台账,巡检点id
    private              Integer isNew       = 0;//新增还是重新打开，新增1
    private              Long    dicId       = -1L;//字典模板表ID
    private              String  rfidType    = "";//用来判断查看巡检表还是台账表
    private              Integer canM        = 0;//1不能修改
    private              String  insrType    = "XJ";//XJ还是PM
    private Long correctRepId;             //修改reps的时候用到的repid
    private String groupRfid = "";//分组的rfid

    private String allUserName = "";
    private String allUserId   = "";
    private CommonAdapter<INSPECT_REPS> mAdapter;

    //一级目录的数据
    private List<INSPECT_REPS> mFatherDatas = new ArrayList<>();
    //二级目录的数据
    private List<INSPECT_REPS> mSonDatas    = new ArrayList<>();
    private List<String>       mPicDatas    = new ArrayList<>();
    private AlertDialog           mPicDialog;
    private CommonAdapter<String> mPicAdapter;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_inspect_detail;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }


    @Override
    protected void initView() {
        mTvTitle.setText("巡检详情");
        mTvMr.setText("默认");
        mTvZc.setText("暂存");
        mRbAll.setVisibility(View.GONE);
        mPicDatas.add("");          //加条空的当默认页

        initRecycleView();
        initGetIntent();
        initData();                 //初始化数据


    }

    private void initData() {

        if (isNew == 1) {
            newDataShow();              //新旧显示逻辑点上是不同的 注意 一个是pname(父)一个是content(自己)
        } else {
            oldDataBackShow();
        }
    }


    //旧数据的回显
    private void oldDataBackShow() {
        //重新打开


        INSPECT_REP inspectRep = daoSession.getINSPECT_REPDao().queryBuilder().
                where(INSPECT_REPDao.Properties.INSR_FK_ID.eq(planId))
                .unique();
        if (inspectRep == null) {
            Log.v("MYTAG", "空了");
            return;
        }
        String INSR_NID = inspectRep.getINSR_NID();

        if (INSR_NID.indexOf("TZ") > -1) {
            mTvType.setText("台账:");
        } else if (INSR_NID.indexOf("RP") > -1) {
            mTvType.setText("维修点:");
        }

        if (inspectRep.getINSR_FK_ID() > 0) {
            INSPECT_PLAN plan = daoSession.getINSPECT_PLANDao().load(Long.valueOf(inspectRep.getINSR_FK_ID() + ""));
            if (plan != null) {
                mTvPlanTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(plan.getINSP_EXEC_DATE()));
            }
        }
        mTvCurrentTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        mTvName.setText(inspectRep.getWZMC());
        mTvKpbh.setText(inspectRep.getKPBH());
        mTvDlwz.setText(inspectRep.getDDMC());

        mTvCurrentTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
                inspectRep.getINSR_UPDATE_DATE() == null ? inspectRep.getINSR_CREATE_DATE() : inspectRep.getINSR_UPDATE_DATE()));//此处需要改成打开的时间

        //如果存在计划ID，就去查询一次计划的时间
        if (inspectRep.getINSR_FK_ID() > 0) {
            INSPECT_PLAN plan = daoSession.getINSPECT_PLANDao().load(Long.valueOf(inspectRep.getINSR_FK_ID() + ""));
            if (plan != null) {
                mTvPlanTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(plan.getINSP_EXEC_DATE()));
            }
        }
        //此处要重新循环模板，有相同名字的放一起
        List<INSPECT_REP_PIC> picList = daoSession.getINSPECT_REP_PICDao().queryBuilder().where(INSPECT_REP_PICDao.Properties.INSPR_REP_ID.eq(planId)).list();
        JSONArray picArr = new JSONArray();
        for (INSPECT_REP_PIC pic : picList) {
            picArr.put(pic.getURL());
        }

        mSonDatas = mDaoSession.getINSPECT_REPSDao().queryBuilder().where(INSPECT_REPSDao.Properties.INSPR_REP_ID.eq(planId)).orderAsc(INSPECT_REPSDao.Properties.INSPR_ID).list();

        Observable.from(mSonDatas)
                .subscribeOn(Schedulers.io())
                .filter(new Func1<INSPECT_REPS, Boolean>() {
                    @Override
                    public Boolean call(INSPECT_REPS inspect_reps) {                //不存空的
                        return inspect_reps != null;
                    }
                }).distinctUntilChanged(new Func1<INSPECT_REPS, String>() {           //不存重复的
            @Override
            public String call(INSPECT_REPS inspect_reps) {
                return inspect_reps.getINSPR_PCONTENT();
            }
        }).doOnNext(new Action1<INSPECT_REPS>() {
            @Override
            public void call(INSPECT_REPS inspect_reps) {
                List<INSPECT_REPS> list = mDaoSession.getINSPECT_REPSDao().queryBuilder().where(INSPECT_REPSDao.Properties.INSPR_REP_ID.eq(planId)
                ).where(INSPECT_REPSDao.Properties.INSPR_PCONTENT.eq(inspect_reps.getINSPR_PCONTENT()))
                        .list();
                for (int i = 0; i < list.size(); i++) {
                    if ("BHG".equals(list.get(i).getINSPR_HG_VAL())) {
                        inspect_reps.setINSPR_HG_VAL("BHG");
                        break;
                    }
                }
            }
        }).observeOn(Schedulers.io())
                .toList().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<INSPECT_REPS>>() {
                    @Override
                    public void call(List<INSPECT_REPS> inspect_repses) {
                        mFatherDatas.addAll(inspect_repses);
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }


    //新数据的显示
    private void newDataShow() {
        if (isPlan == 3) {
            if ("TZ".equals(rfidType)) {
                IN_ASSETDao assetDao = daoSession.getIN_ASSETDao();
                IN_ASSET asset = assetDao.load(planId);//此处需要展示旧编号
                mTvKpbh.setText(asset.getKPBH());        //卡片编号
                mTvDlwz.setText(asset.getDDMC());          //地点名称
                mTvType.setText("台账");
                mTvName.setText(asset.getWZMC());
            } else {
                REPAIR_PLACEDao placeDao = daoSession.getREPAIR_PLACEDao();
                REPAIR_PLACE place = placeDao.load(planId);
                mTvKpbh.setText(place.getPLACE_KPBH());        //卡片编号
                mTvDlwz.setText(place.getITEM_DD_NAME());          //地点名称
                mTvType.setText("巡检点");                     //巡检点
                mINSP_fk_id = "RP" + planId;
                mTvName.setText(place.getPLACE_NAME());
            }
            mTvPlanTime.setText("");
            mTvCurrentTime.setText(TimeUtils.getCurTimeString(new SimpleDateFormat("yyyy-MM-dd")));

        } else {
            //从计划表去取模板
            INSPECT_PLANDao pDao = daoSession.getINSPECT_PLANDao();
            INSPECT_PLAN plan = pDao.queryBuilder().where(INSPECT_PLANDao.Properties.INSP_ID.eq(planId)).unique();
            if (plan == null) {
                return;
            }
            mINSP_fk_id = plan.getINSP_FK_ID();
            if (mINSP_fk_id.indexOf("TZ") > -1) {
                mTvType.setText("台账");
            } else if (mINSP_fk_id.indexOf("RP") > -1) {
                mTvType.setText("巡检点");                     //巡检点
            }
            mTvKpbh.setText(plan.getKPBH());
            mTvDlwz.setText(plan.getDDMC());
            mTvPlanTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(plan.getINSP_EXEC_DATE()));
            mTvCurrentTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
        //如果有分组标签，那么头部显示分组标签
        if (groupRfid != null && !"".equals(groupRfid)) {
            List<INSPECT_GROUP> insgList = mDaoSession.getINSPECT_GROUPDao().queryBuilder().where(INSPECT_GROUPDao.Properties.INSG_RFID.eq(groupRfid)).list();
            mTvKpbh.setText("总数量：" + insgList.size() + " 条");
            mTvName.setText("分组编号：" + groupRfid);
            mTvType.setText("全部分组:");
        }

        //从字典表取，会传字典ID过来
        InspectTempletItemDao dicDao = mDaoSession.getInspectTempletItemDao();
        List<InspectTempletItem> dicParList = dicDao.queryBuilder().where(InspectTempletItemDao.Properties.ITEM_PARENT_ID.eq(dicId))
                .where(InspectTempletItemDao.Properties.ITEM_TYPE.eq("PM".equals(insrType) ? 2 : 1))
                .orderAsc(InspectTempletItemDao.Properties.ITEM_SORT)
                .orderAsc(InspectTempletItemDao.Properties.ITEM_ID).list();

        Observable.from(dicParList)
                .subscribeOn(Schedulers.io())
                .filter(new Func1<InspectTempletItem, Boolean>() {
                    @Override
                    public Boolean call(InspectTempletItem inspectTemplet) {                //不存空的
                        return inspectTemplet != null;
                    }
                })
                .map(new Func1<InspectTempletItem, INSPECT_REPS>() {                    //数据转化 将模板转换成reps 统一
                    @Override
                    public INSPECT_REPS call(InspectTempletItem inspectTempletItem) {
                        INSPECT_REPS inspect_reps = AndroidUtils.changIti2Reps(inspectTempletItem);
                        return inspect_reps;
                    }
                })
                .doOnNext(new Action1<INSPECT_REPS>() {                             //通过父节点生成所有的子节点
                    @Override
                    public void call(INSPECT_REPS inspect_reps) {
                        createSonData(inspect_reps.getModelId());
                    }
                })
                .subscribeOn(Schedulers.io())
                .distinctUntilChanged(new Func1<INSPECT_REPS, String>() {           //不存重复的,这是父节点所以数据是pcontetnt
                    @Override
                    public String call(INSPECT_REPS inspect_reps) {
                        return inspect_reps.getINSPR_PCONTENT();
                    }
                }).toList().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<INSPECT_REPS>>() {
                    @Override
                    public void call(List<INSPECT_REPS> inspect_repses) {
                        mFatherDatas.addAll(inspect_repses);
                        mAdapter.notifyDataSetChanged();
                    }
                });


    }


    /**
     * @param modelId 模板的父id 通过父id获得子id
     */
    private void createSonData(final Long modelId) {
        Observable.from(mDaoSession.getInspectTempletItemDao().queryBuilder().where(InspectTempletItemDao.Properties.ITEM_PARENT_ID.eq(modelId))
                .list())
                .subscribeOn(Schedulers.io())
                .filter(new Func1<InspectTempletItem, Boolean>() {
                    @Override
                    public Boolean call(InspectTempletItem inspectTempletItem) {
                        return inspectTempletItem != null;
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<InspectTempletItem, INSPECT_REPS>() {
                    @Override
                    public INSPECT_REPS call(InspectTempletItem inspectTempletItem) {
                        INSPECT_REPS inspect_reps = AndroidUtils.changIti2Reps(inspectTempletItem);
                        //子类的时候需要将父类的名字写上去
                        inspect_reps.setINSPR_PNAME(mDaoSession.getInspectTempletItemDao().load(modelId).getITEM_NAME());
                        return inspect_reps;
                    }
                }).toList()
                .observeOn(Schedulers.io())
                .subscribe(new Action1<List<INSPECT_REPS>>() {
                    @Override
                    public void call(List<INSPECT_REPS> inspect_repses) {
                        mDaoSession.getINSPECT_REPSDao().insertInTx(inspect_repses);            //存储数据
                        mSonDatas.addAll(inspect_repses);

                    }
                });
    }

    private void initRecycleView() {
        FullyLineLayoutManager lineLayoutManager = new FullyLineLayoutManager(this);
        mRv.setLayoutManager(lineLayoutManager);
        mAdapter = new CommonAdapter<INSPECT_REPS>(this, R.layout.item_inspect_detail, mFatherDatas) {
            @Override
            protected void convert(ViewHolder holder, final INSPECT_REPS bean, int position) {
                holder.setText(R.id.tv, bean.getINSPR_PCONTENT());
                TextView mTv = (TextView) holder.getView(R.id.tv);


                //动态设置的合格不合格
                if ("BHG".equals(bean.getINSPR_HG_VAL())) {
                    Drawable drawable = getResources().getDrawable(R.mipmap.ic_bhg);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    mTv.setCompoundDrawables(null, null, drawable, null);
                } else {
                    mTv.setCompoundDrawables(null, null, null, null);
                }
                holder.setOnClickListener(R.id.tv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(InspectDetailActivity.this, InspectDetail2Activity.class);
                        intent.putExtra(Content.INSPECT_PNAME, bean.getINSPR_PCONTENT());       //父类,所以传的是父类的pcontetnt
                        intent.putExtra("repId", bean.getINSPR_REP_ID().intValue());
                        startActivityForResult(intent, INSPECT_DETAIL2_REQUESET_CODE);
                    }
                });
            }
        };
        mRv.setAdapter(mAdapter);
    }


    private void initGetIntent() {

        Intent intent = getIntent();
        planId = intent.getLongExtra("planId", 0L);
        isPlan = intent.getIntExtra("isPlan", 0);
        isNew = intent.getIntExtra("isNew", 0);
        dicId = intent.getLongExtra("dicId", -1L);
        rfidType = intent.getStringExtra("rfidType");
        canM = intent.getIntExtra("canM", 0);
        insrType = intent.getStringExtra("TYPE");
        groupRfid = intent.getStringExtra("groupRfid");
        allUserName = intent.getStringExtra("allUserName");
        allUserId = intent.getStringExtra("allUserId");
    }


    @OnClick({R.id.iv_back, R.id.tv_yzk, R.id.tv_lsyd, R.id.tv_add_pic, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_yzk:
                setDefault();
                break;
            case R.id.tv_lsyd:
                gotoSave(FINISH_PART);
                break;
            case R.id.tv_add_pic:
                gotoAddPic();
                break;
            case R.id.tv_ok:
                gotoSave(FINISH_ALL);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Content.REQUEST_PIC_CHOOSE:
                    List<Uri> uris = Matisse.obtainResult(data);
                    mPicDatas.clear();
                    for (int i = 0; i < uris.size(); i++) {
                        mPicDatas.add(uris.get(i).toString());
                    }
                    mPicAdapter.notifyDataSetChanged();
                    break;
                case INSPECT_DETAIL2_REQUESET_CODE:
                    //暂时不做处理
                    String ishg = data.getStringExtra("ISHG");//是否合格
                    String stringExtra = data.getStringExtra(Content.INSPECT_PNAME);

                    for (int i = 0; i < mFatherDatas.size(); i++) {
                        if (mFatherDatas.get(i).getINSPR_PCONTENT().equals(stringExtra)) {
                            mFatherDatas.get(i).setINSPR_HG_VAL(ishg);
                        }
                    }
                    mAdapter.notifyDataSetChanged();

                    break;

            }
        }
    }

    //保存
    private void gotoSave(Integer savetype) {
        INSPECT_PLAN planBean = new INSPECT_PLAN();
        INSPECT_REP inspectRep = new INSPECT_REP();
        INSPECT_PLANDao pDao = mDaoSession.getINSPECT_PLANDao();
        //先保存大的
        if (isNew == 1) {
            //过期或者没到
            if (isPlan == 3) {
                //不是计划
                String INSE_FK_ID = "";
                if ("TZ".equals(rfidType)) {
                    IN_ASSETDao assetDao = daoSession.getIN_ASSETDao();
                    IN_ASSET asset = assetDao.load(planId);
                    inspectRep.setKSID(Long.valueOf(asset.getKSID()));
                    inspectRep.setKSMC(asset.getKSMC());

                    inspectRep.setWZMC(asset.getWZMC());
                    inspectRep.setKPBH(asset.getKPBH());
                    inspectRep.setDDMC(asset.getDDMC());

                    inspectRep.setSCBH(asset.getSCBH());
                    inspectRep.setGGXH(asset.getGGXH());
                    inspectRep.setPPMC(asset.getPPMC());

                    if (!TextUtils.isEmpty(asset.getDDID())) {
                        inspectRep.setDDID(Long.parseLong(asset.getDDID()));
                    }
                    //									inspectRep.setXJFL_MC("");
                    INSE_FK_ID = "TZ" + planId;
                    inspectRep.setINSR_NID(INSE_FK_ID);//ID感觉应该加RP/TZ
                    inspectRep.setRFID_CODE(asset.getRFID_CODE());
                    inspectRep.setBAR_CODE(asset.getBAR_CODE());
                    inspectRep.setKPBH_OLD(asset.getKPBH_OLD());

                } else {
                    REPAIR_PLACEDao placeDao = daoSession.getREPAIR_PLACEDao();
                    REPAIR_PLACE place = placeDao.load(planId);
                    inspectRep.setWZMC(place.getPLACE_NAME());
                    inspectRep.setKPBH(place.getPLACE_KPBH());
                    inspectRep.setDDMC(place.getITEM_DD_NAME());
                    inspectRep.setDDID(Long.parseLong(place.getPLACE_DDID()));
                    INSE_FK_ID = "RP" + planId;
                    inspectRep.setINSR_NID(INSE_FK_ID);//ID感觉应该加RP/TZ
                    inspectRep.setRFID_CODE(place.getPLACE_RFID_CODE());
                    inspectRep.setBAR_CODE(place.getPLACE_BAR_CODE());
                    if (place.getPLACE_XJID() != null && !"".equals(place.getPLACE_XJID())) {
                        inspectRep.setXJFL(Long.parseLong(place.getPLACE_XJID()));
                    }
                    inspectRep.setXJFL_MC(place.getITEM_XJ_NAME());
                }

                INSPECT_EXTDao extDao = daoSession.getINSPECT_EXTDao();
                List<INSPECT_EXT> extList = extDao.queryBuilder()
                        .where(INSPECT_EXTDao.Properties.INSE_FK_ID.eq(INSE_FK_ID))
                        .list();
                INSPECT_EXT ectExt = extList.get(0);
                inspectRep.setZQLX(ectExt.getINSE_CYCLE_TYPE());
                inspectRep.setINSE_CYCLE(ectExt.getINSE_CYCLE() + "");

                inspectRep.setINSR_FK_ID(0);//关联计划外键ID,无计划的记录值写0

                //需要另外插入一个新的plan 作为临时数据渲染

                planBean.setIS_TEMPORARY(true);
                planBean.setWZMC(inspectRep.getWZMC());
                planBean.setKPBH(inspectRep.getKPBH());
                planBean.setKPBH_OLD(inspectRep.getKPBH_OLD());
                planBean.setSCBH(inspectRep.getSCBH());
                planBean.setGGXH(inspectRep.getGGXH());
                planBean.setPPMC(inspectRep.getPPMC());
                planBean.setKSMC(inspectRep.getKSMC());
                planBean.setDDMC(inspectRep.getDDMC());

                planBean.setISCHECK(1);
                planBean.setRESULT(1);
                planBean.setINSP_FK_ID(INSE_FK_ID);
                planBean.setINSP_ID(-1L);               //是临时的标志

            } else {

                 planBean = pDao.queryBuilder().where(INSPECT_PLANDao.Properties.INSP_ID.eq(planId)).unique();
                inspectRep.setKSID(planBean.getKSID());//新增科室ID
                inspectRep.setKSMC(planBean.getKSMC());
                inspectRep.setWZMC(planBean.getWZMC());
                inspectRep.setKPBH(planBean.getKPBH());
                inspectRep.setDDMC(planBean.getDDMC());
                inspectRep.setDDID(planBean.getDDID());

                inspectRep.setSCBH(planBean.getSCBH());
                inspectRep.setGGXH(planBean.getGGXH());
                inspectRep.setPPMC(planBean.getPPMC());

                if (isPlan == 2) {
                    inspectRep.setINSR_FK_ID(Integer.parseInt(planId + ""));//关联计划外键ID,无计划的记录值写0
                    //更新计划表
                    planBean = daoSession.getINSPECT_PLANDao().queryBuilder().where
                            (INSPECT_PLANDao.Properties.INSP_ID.eq(planId)).unique();
                    planBean.setISCHECK(1);
                    planBean.setRESULT(1);//1是合格
                } else {
                    inspectRep.setINSR_FK_ID(0);//关联计划外键ID,无计划的记录值写0
                }
                inspectRep.setINSR_NID(planBean.getINSP_FK_ID());//ID感觉应该加RP/TZ
                inspectRep.setBAR_CODE(planBean.getBAR_CODE());
                inspectRep.setRFID_CODE(planBean.getRFID_CODE());
                inspectRep.setXJFL(planBean.getXJFL());
                inspectRep.setXJFL_MC(planBean.getXJFL_MC());
                inspectRep.setZQLX(planBean.getZQLX());
                inspectRep.setINSE_CYCLE(planBean.getINSE_CYCLE());

                inspectRep.setKPBH_OLD(planBean.getKPBH_OLD());

            }
            inspectRep.setINSR_TYPE(insrType);
            inspectRep.setINSR_EXEC_DATE(new Date());
            inspectRep.setINSR_RESULT("HG");
            String userID = SPUtil.getUserId(this);
            if (userID == null || userID.equals("")) {
                inspectRep.setINSR_USER_ID(0);
            } else {
                inspectRep.setINSR_USER_ID(Integer.parseInt(userID));
            }
            inspectRep.setINSR_CREATE_DATE(new Date());
            inspectRep.setINSR_CREATE_USER(Integer.parseInt(userID));

            inspectRep.setINSR_UPDATE_DATE(new Date());
            inspectRep.setINSR_UPDATE_USER(Integer.parseInt(userID));

        } else {
            //重新打开，做更新操作
            String userID = SPUtil.getUserId(this);
            inspectRep = daoSession.getINSPECT_REPDao().load(planId);
            inspectRep.setINSR_UPDATE_DATE(new Date());
            inspectRep.setINSR_UPDATE_USER(Integer.parseInt(userID));
            inspectRep.setINSR_ID(planId);
            planBean = pDao.queryBuilder().where(INSPECT_PLANDao.Properties.INSP_ID.eq(planId)).unique();
            Log.v("MYTAG", planId + "修改的ID");
        }
        inspectRep.setISCHECK(1);
        inspectRep.setPDAID(AndroidUtils.getImei());



        //这个因为关系到Pm所以一定要有计划的 才能改变ISCHECK 的状态
        if (inspectRep.getINSR_FK_ID() != null && inspectRep.getINSR_FK_ID() > 0) {
                planBean.setISCHECK(savetype);
                inspectRep.setISCHECK(savetype);
        }
        planBean.setRESULT(1);//1是合格


        inspectRep.setINSR_WX_NEED(0);          //是否维修
        inspectRep.setINSR_RESULT("HG");        //是否合格
        long repId = mDaoSession.insertOrReplace(inspectRep);

        for (int i = 0; i < mSonDatas.size(); i++) {
            INSPECT_REPS reps = mSonDatas.get(i);
            if ("BHG".equals(reps.getINSPR_HG_VAL())) {
                inspectRep.setINSR_RESULT("BHG");
                planBean.setRESULT(2);
            }
            if (1 == reps.getINSPR_WX_NEED()) {
                inspectRep.setINSR_WX_NEED(1);
            }
            reps.setINSPR_REP_ID(Integer.parseInt(repId + ""));
        }
        if (planBean.getINSP_ID() == -1L) {
            planBean.setINSP_ID(-repId);
        }
        mDaoSession.insertOrReplace(planBean);
        mDaoSession.getINSPECT_REPSDao().insertOrReplaceInTx(mSonDatas);
        setResult(RESULT_OK);
        finish();
    }

    //默认
    private void setDefault() {
        for (INSPECT_REPS sonData : mSonDatas) {
            setDefaultData(sonData);
        }
        ToastUtil.showToast("设置默认成功!");
    }

    //通过数据类型 设置默认值
    public void setDefaultData(final INSPECT_REPS inspect_reps) {

        final String defValue = inspect_reps.getINSPR_RE_VALUE();
        String type = inspect_reps.getINSPR_VAL_TYPE();
        if (TextUtils.isEmpty(type) || type.equals("Text")) {
            type = "Number";
        }
        switch (type) {
            case "Number":
                //如果有默认的就变成默认的
                inspect_reps.setINSPR_EXAM_RESULT("HG");            //默认的就得是合格
                if (!TextUtils.isEmpty(defValue) && (!"无".equals(defValue))) {
                    inspect_reps.setINSPR_EXAM_VALUE(defValue);
                } else {

                }
                break;
            case "Select":
                //如果有默认的就变成默认的
                String[] split = inspect_reps.getINSPR_SEL_VAL().split("\\|");

                ArrayList<String> countDatas = new ArrayList<String>();
                countDatas.add("");
                if (split != null && split.length != 0) {
                    for (int i = 0; i < split.length; i++) {
                        countDatas.add(split[i]);
                    }
                }
                //这里默认值给的是 代号 defValue==0
                if (!TextUtils.isEmpty(defValue)) {
                    for (int i = 0; i < countDatas.size(); i++) {
                        if (defValue.equals(countDatas.get(i).split("-")[0])) {
                            inspect_reps.setINSPR_EXAM_VALUE(countDatas.get(i).split("-")[0]);
                        }
                    }
                }
                break;
            case "Date":
                if (defValue != null) {
                    inspect_reps.setINSPR_EXAM_VALUE(defValue);
                }
                break;
        }


    }


    //添加图片
    private void gotoAddPic() {

        if (mPicDialog == null) {
            View view = View.inflate(this, R.layout.layout_dilaog_pic, null);
            RecyclerViewPager mRvPic = (RecyclerViewPager) view.findViewById(R.id.rv_pic);
            mRvPic.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false));
            mPicAdapter = new CommonAdapter<String>(this, R.layout.recycle_pic_inspect, mPicDatas) {
                @Override
                protected void convert(ViewHolder holder, final String urlString, int position) {
                    //使用glide 填充图片就好
                    if (!TextUtils.isEmpty(urlString)) {
                        Glide.with(InspectDetailActivity.this).load(urlString).into((ImageView) holder.getView(R.id.iv_pic));
                    }
                    //删除
                    holder.setOnClickListener(R.id.iv_delete, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!TextUtils.isEmpty(urlString)) {
                                mPicDatas.remove(urlString);
                                mPicAdapter.notifyDataSetChanged();
                            }

                        }
                    });
                    //添加图片
                    holder.setOnClickListener(R.id.tv_add_pic, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            gotoChoosePic();
                        }
                    });
                }
            };
            mRvPic.setAdapter(mPicAdapter);
            mPicDialog = new AlertDialog.Builder(this).setView(view)
                    .create();
            mPicDialog.show();
        } else {
            mPicAdapter.notifyDataSetChanged();
            mPicDialog.show();
        }
    }

    private void gotoChoosePic() {
        Matisse.from(this)
                .choose(MimeType.allOf())
                .countable(true)
                .capture(true)
                .captureStrategy(
                        new CaptureStrategy(true, "coder.aihui.ui.assetquery.provider"))
                .maxSelectable(5)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                // .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(Content.REQUEST_PIC_CHOOSE);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("确认不保存数据退出吗?")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (isNew == 1) {
                            INSPECT_REPSDao repsDao = mDaoSession.getINSPECT_REPSDao();
                            QueryBuilder<INSPECT_REPS> qb = repsDao.queryBuilder();
                            qb.where(qb.or(INSPECT_REPSDao.Properties.INSPR_REP_ID.isNull(),
                                    INSPECT_REPSDao.Properties.INSPR_REP_ID.eq(-1))
                            ).buildDelete().executeDeleteWithoutDetachingEntities();
                        }
                        finish();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();

    }


    //显示误差
    private void gotoShowWc(INSPECT_REPS inspect_reps, EditText et_input, TextView mTv_wc) {


        String insts_sel_val = inspect_reps.getINSPR_SEL_VAL();
        String s = inspect_reps.getINSPR_EXAM_VALUE();

        if (!TextUtils.isEmpty(s) && s.toString().matches("^[0-9\\.]*$") && !TextUtils.isEmpty(insts_sel_val)) {
            Float inputNum = Float.parseFloat(s.toString());
            if (insts_sel_val.indexOf(",") > -1) {
                String[] split = insts_sel_val.split("-");
                if (split == null || split.length == 0) {
                    return;
                }
                Float mAmin = Float.parseFloat(split[0]);
                Float amax = Float.parseFloat(split[1]);
                if (mAmin != null && amax != null) {
                    if (inputNum >= mAmin && inputNum <= amax) {
                        et_input.setBackgroundColor(Color.WHITE);
                        inspect_reps.setINSPR_EXAM_RESULT("HG");

                        mTv_wc.setText("");
                    } else if (inputNum < mAmin) {
                        inspect_reps.setINSPR_EXAM_RESULT("BHG");

                        et_input.setBackgroundColor(Color.RED);

                        mTv_wc.setText((inputNum - mAmin) + "");
                    } else if (inputNum > amax) {
                        inspect_reps.setINSPR_EXAM_RESULT("BHG");
                        et_input.setBackgroundColor(Color.RED);
                        mTv_wc.setText((amax - inputNum) + "");
                    }
                } else {
                    et_input.setBackgroundColor(Color.WHITE);

                    mTv_wc.setText("");
                }
            } else if (insts_sel_val.indexOf("-") > -1) {

                Float amax = null;
                Float mAmin = null;

                if (!TextUtils.isEmpty(insts_sel_val)) {
                    String[] split = insts_sel_val.split("-");
                    mAmin = Float.parseFloat(split[0]);
                    amax = Float.parseFloat(split[1]);
                } else {
                    return;
                }


                if (mAmin != null && amax != null) {
                    if (inputNum >= mAmin && inputNum <= amax) {
                        inspect_reps.setINSPR_EXAM_RESULT("HG");

                        mTv_wc.setText("");
                        et_input.setBackgroundColor(Color.WHITE);
                    } else if (inputNum < mAmin) {
                        inspect_reps.setINSPR_EXAM_RESULT("BHG");


                        mTv_wc.setText((inputNum - mAmin) + "");
                        et_input.setBackgroundColor(Color.RED);

                    } else if (inputNum > amax) {
                        inspect_reps.setINSPR_EXAM_RESULT("BHG");

                        mTv_wc.setText((amax - inputNum) + "");
                        et_input.setBackgroundColor(Color.RED);
                    }
                } else {

                    inspect_reps.setINSPR_EXAM_RESULT("HG");
                    et_input.setBackgroundColor(Color.WHITE);
                    mTv_wc.setText("");
                }
            } else if (insts_sel_val.indexOf(">=") > -1) {
                float v = Float.parseFloat(insts_sel_val.substring(2));
                if (!(inputNum >= v)) {
                    //说明是错误的
                    mTv_wc.setText("" + (v - inputNum));

                    et_input.setBackgroundColor(Color.RED);

                    inspect_reps.setINSPR_EXAM_RESULT("BHG");

                    //设置不合格

                } else {

                    inspect_reps.setINSPR_EXAM_RESULT("HG");
                    et_input.setBackgroundColor(Color.WHITE);
                    mTv_wc.setText("");
                }
            } else if (insts_sel_val.indexOf("<=") > -1) {
                float v = Float.parseFloat(insts_sel_val.substring(2));
                if (!(inputNum <= v)) {
                    //说明是错误的
                    mTv_wc.setText("" + (v - inputNum));
                    et_input.setBackgroundColor(Color.RED);

                    inspect_reps.setINSPR_EXAM_RESULT("BHG");
                    //设置不合格

                    // TODO: 2017/6/14
                    //还要设置一个条目标红

                } else {

                    inspect_reps.setINSPR_EXAM_RESULT("HG");
                    et_input.setBackgroundColor(Color.WHITE);

                    mTv_wc.setText("");
                }
            } else if (insts_sel_val.indexOf(">") > -1) {
                float v = Float.parseFloat(insts_sel_val.substring(1));
                if (!(inputNum > v)) {
                    //说明是错误的
                    mTv_wc.setText("" + (v - inputNum));
                    et_input.setBackgroundColor(Color.RED);
                    inspect_reps.setINSPR_EXAM_RESULT("BHG");

                    //设置不合格
                } else {
                    inspect_reps.setINSPR_EXAM_RESULT("HG");
                    et_input.setBackgroundColor(Color.WHITE);
                    mTv_wc.setText("");
                }
            } else if (insts_sel_val.indexOf("<") > -1) {
                float v = Float.parseFloat(insts_sel_val.substring(1));
                if (!(inputNum < v)) {
                    //说明是错误的
                    mTv_wc.setText("" + (v - inputNum));
                    et_input.setBackgroundColor(Color.RED);
                    inspect_reps.setINSPR_EXAM_RESULT("BHG");

                    //设置不合格
                } else {
                    inspect_reps.setINSPR_EXAM_RESULT("HG");
                    et_input.setBackgroundColor(Color.WHITE);
                    mTv_wc.setText("");
                }
            }

        } else {
            //如果没值同时
            if (TextUtils.isEmpty(s) && !"HG".equals(inspect_reps.getINSPR_EXAM_RESULT())) {
                inspect_reps.setINSPR_EXAM_RESULT("HG");
            }
            et_input.setBackgroundColor(Color.WHITE);
            mTv_wc.setText("");
            //默认设置成合格
        }
    }


}
