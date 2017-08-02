package coder.aihui.ui.inspectxj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.data.bean.INSPECT_REPS;
import coder.aihui.data.bean.gen.INSPECT_REPSDao;
import coder.aihui.widget.FullyLineLayoutManager;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class InspectDetailActivity extends AppActivity {


    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.tv_name)
    TextView     mTvName;
    @BindView(R.id.tv_ggxh)
    TextView     mTvGgxh;
    @BindView(R.id.tv_dept)
    TextView     mTvDept;
    @BindView(R.id.ll_names)
    LinearLayout mLlNames;
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

    private static final int FINISH_ALL  = 1;    //全部完成
    private static final int FINISH_PART = 2;   //完成部分\

    private Integer isPlan   = 0;//是否计划  0:不是   1:计划表存在，过期or还没到 2:是正常的计划 ,3有模版
    private Long    planId   = 0L;//巡检计划ID, 或者保存本地的ID
    private Integer isNew    = 0;//新增还是重新打开，新增1
    private Long    dicId    = -1L;//字典模板表ID
    private String  rfidType = "";//用来判断查看巡检表还是台账表
    private Integer canM     = 0;//1不能修改
    private String  insrType = "XJ";//XJ还是PM
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
        mTvMr.setText("暂存");

        initRecycleView();
        initGetIntent();
        initData();


    }

    private void initData() {

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
                        Intent intent = new Intent(InspectDetailActivity.this,InspectDetail2Activity.class);
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
