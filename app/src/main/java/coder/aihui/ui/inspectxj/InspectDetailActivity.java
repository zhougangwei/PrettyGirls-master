package coder.aihui.ui.inspectxj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.TimeUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.data.bean.INSPECT_GROUP;
import coder.aihui.data.bean.INSPECT_PLAN;
import coder.aihui.data.bean.INSPECT_REP;
import coder.aihui.data.bean.INSPECT_REPS;
import coder.aihui.data.bean.INSPECT_REP_PIC;
import coder.aihui.data.bean.IN_ASSET;
import coder.aihui.data.bean.InspectTempletItem;
import coder.aihui.data.bean.REPAIR_PLACE;
import coder.aihui.data.bean.SYS_PARAM;
import coder.aihui.data.bean.gen.INSPECT_GROUPDao;
import coder.aihui.data.bean.gen.INSPECT_PLANDao;
import coder.aihui.data.bean.gen.INSPECT_REPDao;
import coder.aihui.data.bean.gen.INSPECT_REPSDao;
import coder.aihui.data.bean.gen.INSPECT_REP_PICDao;
import coder.aihui.data.bean.gen.IN_ASSETDao;
import coder.aihui.data.bean.gen.InspectTempletItemDao;
import coder.aihui.data.bean.gen.REPAIR_PLACEDao;
import coder.aihui.data.bean.gen.SYS_PARAMDao;
import coder.aihui.util.AndroidUtils;
import coder.aihui.widget.FullyLineLayoutManager;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static coder.aihui.app.MyApplication.daoSession;

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
    private static final int     FINISH_PART = 2;   //完成部分\
    private              String  mINSP_fk_id = "";           //区分台账还是巡检点
    private              Integer isPlan      = 0;//是否计划  0:不是   1:计划表存在，过期or还没到 2:是正常的计划 ,3有模版
    private              Long    planId      = 0L;//巡检计划ID, 或者保存本地的ID
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

        initRecycleView();
        initGetIntent();
        initData();


    }

    private void initData() {


        if (isNew == 1) {
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
                INSPECT_PLAN plan = pDao.load(planId);
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
                List<INSPECT_GROUP> insgList = daoSession.getINSPECT_GROUPDao().queryBuilder().where(INSPECT_GROUPDao.Properties.INSG_RFID.eq(groupRfid)).list();
                mTvKpbh.setText("总数量：" + insgList.size() + " 条");
                mTvName.setText("分组编号：" + groupRfid);
                mTvType.setText("全部分组:");
            }

            //从字典表取，会传字典ID过来
            InspectTempletItemDao dicDao = daoSession.getInspectTempletItemDao();
            List<InspectTempletItem> dicParList = dicDao.queryBuilder().where(InspectTempletItemDao.Properties.ITEM_PARENT_ID.eq(dicId))
                    .where(InspectTempletItemDao.Properties.ITEM_TYPE.eq("PM".equals(insrType) ? 2 : 1))
                    .orderAsc(InspectTempletItemDao.Properties.ITEM_SORT)
                    .orderAsc(InspectTempletItemDao.Properties.ITEM_ID).list();

            for (InspectTempletItem model : dicParList) {
                List<InspectTempletItem> dicSubList = dicDao.queryBuilder().where(InspectTempletItemDao.Properties.ITEM_PARENT_ID.eq(model.getITEM_ID()))
                        .where(InspectTempletItemDao.Properties.ITEM_TYPE.eq("PM".equals(insrType) ? 2 : 1))
                        .orderAsc(InspectTempletItemDao.Properties.ITEM_SORT)
                        .orderAsc(InspectTempletItemDao.Properties.ITEM_ID)
                        .list();
            }


        } else {
            //重新打开


            INSPECT_REP inspectRep;


            if ("PM".equals(insrType)) {
                List<INSPECT_REP> list = daoSession.getINSPECT_REPDao().queryBuilder().
                        where(INSPECT_REPDao.Properties.INSR_ID.eq(planId))
                        .list();

                if (list != null && list.size() != 0) {
                    inspectRep = list.get(0);
                    correctRepId = inspectRep.getINSR_ID();

                } else {
                    inspectRep = daoSession.getINSPECT_REPDao().load(planId);
                }
            } else {
                inspectRep = daoSession.getINSPECT_REPDao().load(planId);
            }


            if (inspectRep == null) {
                Log.v("MYTAG", "空了");
                return;
            }
            JSONObject jsonPlan = new JSONObject();

            String INSR_NID = inspectRep.getINSR_NID();

            if (INSR_NID.indexOf("TZ") > -1) {
                mTvType.setText("台账:");
            } else if (INSR_NID.indexOf("RP") > -1) {
                mTvType.setText("维修点:");
            }


            mTvPlanTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(plan.getINSP_EXEC_DATE()));
            mTvCurrentTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            mTvName.setText(inspectRep.getWZMC());
            mTvKpbh.setText(inspectRep.getKPBH());
            mTvDlwz.setText(inspectRep.getDDMC());

            mTvCurrentTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
                    inspectRep.getINSR_UPDATE_DATE() == null ? inspectRep.getINSR_CREATE_DATE() : inspectRep.getINSR_UPDATE_DATE()));//此处需要改成打开的时间
            jsonPlan.put("jhlx", inspectRep.getINSR_FK_ID() > 0 ? 2 : 0);
            //如果存在计划ID，就去查询一次计划的时间
            if (inspectRep.getINSR_FK_ID() > 0) {
                INSPECT_PLAN plan = daoSession.getINSPECT_PLANDao().load(Long.valueOf(inspectRep.getINSR_FK_ID() + ""));
                if (plan != null) {
                    mTvPlanTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(plan.getINSP_EXEC_DATE()));
                }
            }

            List<INSPECT_REPS> repsList = daoSession.getINSPECT_REPSDao().queryBuilder()
                    .where(INSPECT_REPSDao.Properties.INSPR_REP_ID.eq(Integer.parseInt(planId + "")))
                    .orderAsc(INSPECT_REPSDao.Properties.INSPR_ID)
                    .list();

            ArrayList<INSPECT_REPS> uniqueList2 = new ArrayList();
            if ("XJ".equals(insrType)) {
                uniqueList2.addAll(repsList);
            } else {
                for (int j = 0; j < repsList.size(); j++) {
                    INSPECT_REPS model = repsList.get(j);
                    if (uniqueList2.size() == 0) {
                        uniqueList2.add(model);
                    } else {
                        boolean isRepeat = false;
                        for (int i = 0; i < uniqueList2.size(); i++) {
                            if (uniqueList2.get(i).getINSPR_PNAME().equals(model.getINSPR_PNAME())
                                    && uniqueList2.get(i).getINSPR_PCONTENT().equals(model.getINSPR_PCONTENT())
                                    ) {
                                //用来显示最近的数据
                                if (uniqueList2.get(i).getINSPR_ID() < (model.getINSPR_ID())) {
                                    uniqueList2.set(i, model);
                                    isRepeat = true;
                                    break;
                                }
                            }
                        }
                        if (!isRepeat) {
                            uniqueList2.add(model);
                        }
                    }
                }
            }
            for (int i = 0; i < uniqueList2.size(); i++) {
                INSPECT_REPS inspect_reps = uniqueList2.get(i);
                JSONObject jjob = inspect_reps.getJson();
                models.put(jjob);
            }
        }
        if (models == null || models.length() == 0) {
            Toast.makeText(InspectDetailActivity.this, "请确认是否已经下载巡检模板?", Toast.LENGTH_LONG);
        }
        JSONArray modelsNew = new JSONArray();
        List<String> historyNameList = new ArrayList<String>();
        //此处要重新循环模板，有相同名字的放一起


        List<INSPECT_REP_PIC> picList = daoSession.getINSPECT_REP_PICDao().queryBuilder().where(INSPECT_REP_PICDao.Properties.INSPR_REP_ID.eq(planId)).list();
        JSONArray picArr = new JSONArray();
        for (INSPECT_REP_PIC pic : picList) {
            picArr.put(pic.getURL());
        }


        Observable.from(mDaoSession.getINSPECT_REPSDao().queryBuilder().where(INSPECT_REPSDao.Properties.INSPR_REP_ID.eq(planId))
                .orderAsc(INSPECT_REPSDao.Properties.INSPR_ID).list())
                .subscribeOn(Schedulers.io())
                .filter(new Func1<INSPECT_REPS, Boolean>() {
                    @Override
                    public Boolean call(INSPECT_REPS inspect_reps) {                //不存空的
                        return inspect_reps != null;
                    }
                }).distinctUntilChanged(new Func1<INSPECT_REPS, String>() {           //不存重复的
            @Override
            public String call(INSPECT_REPS inspect_reps) {
                return inspect_reps.getINSPR_PNAME();
            }
        }).toList().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<INSPECT_REPS>>() {
                    @Override
                    public void call(List<INSPECT_REPS> inspect_repses) {
                        mFatherDatas.addAll(inspect_repses);
                    }
                });


    }

    private void initRecycleView() {

        FullyLineLayoutManager lineLayoutManager = new FullyLineLayoutManager(this);
        mRv.setLayoutManager(lineLayoutManager);
        mAdapter = new CommonAdapter<INSPECT_REPS>(this, R.layout.item_inspect_detail, mFatherDatas) {
            @Override
            protected void convert(ViewHolder holder, INSPECT_REPS bean, int position) {
                holder.setText(R.id.rb, bean.getINSPR_PNAME());
                holder.setOnClickListener(R.id.rb, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(InspectDetailActivity.this, InspectDetail2Activity.class);
                        startActivity(intent);

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


    @OnClick({R.id.iv_back, R.id.rb_all, R.id.tv_yzk, R.id.tv_lsyd, R.id.tv_add_pic, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rb_all:
                gotoChooseAll();
                break;
            case R.id.tv_yzk:
                gotoMr();
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

    //添加图片
    private void gotoAddPic() {


    }

    //保存
    private void gotoSave(int i) {
        switch (i) {
            case FINISH_ALL:


                break;
            case FINISH_PART:


                break;
        }


    }

    //默认
    private void gotoMr() {

    }


    //选择所有
    private void gotoChooseAll() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
