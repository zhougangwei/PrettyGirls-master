package coder.aihui.ui.inspectxj;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.data.bean.INSPECT_PLAN;
import coder.aihui.data.bean.LoadingBean;
import coder.aihui.data.bean.gen.INSPECT_PLANDao;
import coder.aihui.data.bean.gen.INSPECT_REPDao;
import coder.aihui.data.bean.gen.INSPECT_REPSDao;
import coder.aihui.ui.main.DownPresenter;
import coder.aihui.ui.main.DownView;
import coder.aihui.ui.main.UpBean;
import coder.aihui.util.AndroidUtils;
import coder.aihui.util.SPUtil;
import coder.aihui.widget.popwindow.MenuPopup;
import coder.aihui.widget.popwindow.SlideFromTopPopup;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static coder.aihui.base.Content.INSPECT_START_REQUESET_CODE;
import static coder.aihui.ui.main.DownFragment.mBigType;
import static coder.aihui.ui.main.DownPresenter.HTTP;
import static coder.aihui.ui.main.DownPresenter.INSPECT_UP;

public class InspectXJActivity extends AppActivity implements TabLayout.OnTabSelectedListener, DownView {


    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.iv_config)
    LinearLayout mIvConfig;
    @BindView(R.id.iv_updown)
    LinearLayout mIvUpdown;
    @BindView(R.id.iv_pz)
    LinearLayout mIvPz;
    @BindView(R.id.sp_search)
    Spinner      mSpSearch;
    @BindView(R.id.et_search)
    EditText     mEtSearch;
    @BindView(R.id.tv_search)
    TextView     mTvSearch;
    @BindView(R.id.tb)
    TabLayout    mTb;

    @BindView(R.id.vp)
    ViewPager            mVp;
    @BindView(R.id.fab_xj)
    ImageButton          mFabXj;


    private List<String>       mTitleList = new ArrayList<>();
    private List<RecyclerView> mViewList  = new ArrayList<>();


    private List<INSPECT_PLAN> mCheckList   = new ArrayList<>();
    private List<INSPECT_PLAN> mUnCheckList = new ArrayList<>();
    private List<INSPECT_PLAN> mOverDueList = new ArrayList<>();


    public static final int CHECK_PLAN     = 0;//已检
    public static final int UNCHECK_PLAN   = 1;//未检
    public static final int OVERCHECK_PLAN = 2;//过期


    //保存所有数据以及是否需要加载数据
    private List<LoadingBean> mDataList = new ArrayList<>();
    private InspectPagerAdapter mPagerAdapter;
    private CommonAdapter       mCheckAdapter;
    private CommonAdapter       mUncheckAdapter;
    private CommonAdapter       mOverDueAdapter;

    private String mDeptName;
    private String mDeptIds;
    private String mDlwzName;
    private String mDlwzIds;       //相当于All
    private String mAllDeptName = "";
    private String mAllDlwzName = "";
    private String mAllDlwzIds  = "";//所有的子地理位置
    private String mAllDeptIds  = "";//所有科室


    private Integer modelState = 1;//1 巡检模式，2 计划查询模式 3.查询已检 4.查询未检

    private Date mStartDate;//查询开始日期00:00
    private Date mEndDate;//查询结束日期23:59
    private String searchText = "";//内容，优先级最高

    private String insrType = "XJ";//默认巡检   新增JL

    private String       dicType     = "";//字典ID 字典类别,巡检分类 0 查询全部
    private int          jhls        = 0;//0 全部，1计划，2临时
    private int          searchCycle = 1;//查询周期  1 今天，2本周 3 本月 4当天向前15天 5当天向前三十天 6当天向前45天 7一个周期 目前是两个月
    private int          searchType  = 1;//"1">RFID "2">二维码 "3">名称  默认1  "6">摄像头
    private List<String> mUpDownList = new ArrayList<>();
    private List<String> mPzList     = new ArrayList<>();
    private MenuPopup mPzPopup;
    private MenuPopup mUpdownPopup;

    private View mYjTabView;    //已检的tabView
    private View mWjTabView;    //未检的TabVIew
    private View mGqTabView;    //过期的TabView
    private List<View> mTabViewList = new ArrayList<>();
    private DownPresenter mDownPresenter;
    private int mWhichSelect = 0;               //当前选中的是哪个

    @Override
    protected int getContentViewId() {
        return R.layout.activity_inspect_xj;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

    @Override
    protected void initView() {

        mDownPresenter = new DownPresenter(this, mDaoSession);
        mPagerAdapter = new InspectPagerAdapter(mTitleList, mViewList, this);
        mVp.setAdapter(mPagerAdapter);

        mTb.setupWithViewPager(mVp);

        mTb.addOnTabSelectedListener(this);

        mDataList.add(new LoadingBean(mCheckList, false));
        mDataList.add(new LoadingBean(mUnCheckList, false));
        mDataList.add(new LoadingBean(mOverDueList, false));

        //区分巡检还是pm
        initgetIntent();


        //第一次进来请求的时间
        getFirstTime();
        initRecycleView();

        //变更tab显示
        changeTextOfTab();
        initdata();
    }

    private void initgetIntent() {
        Intent intent = getIntent();
        insrType = intent.getStringExtra("type");       //类型
    }

    private void changeTextOfTab() {

        for (int i = 0; i < mTitleList.size(); i++) {
            TabLayout.Tab tab = mTb.getTabAt(i);
            int position = tab.getPosition();
            //还没数据的 去加载数据 有数据的 就直接显示

            if (position == 0) {
                mYjTabView = View.inflate(this, R.layout.tab_view0, null);
                mTabViewList.add(mYjTabView);
            } else if (position == 1) {
                mWjTabView = View.inflate(this, R.layout.tab_view1, null);
                mTabViewList.add(mWjTabView);
            } else if (position == 2) {
                mGqTabView = View.inflate(this, R.layout.tab_view2, null);
                mTabViewList.add(mGqTabView);
            }
            tab.setCustomView(mTabViewList.get(i));

            if (position == 0) {
                mYjTabView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        if (mTb.getSelectedTabPosition() == 0) {
                            showWhichType(v);
                        }
                        return false;
                    }
                });
            }
            //查询数据 渲染
            queryAllCount(position, 0, 10);
        }


    }


    //是全部还是计划还是临时的数据
    private void showWhichType(View v) {
        ArrayList<String> showDialogs = new ArrayList<>();
        showDialogs.add("全部");
        showDialogs.add("计划");
        showDialogs.add("临时");
        SlideFromTopPopup popWindow = new SlideFromTopPopup(this, showDialogs, new SlideFromTopPopup.onBackResult() {
            @Override
            public void backResult(String string) {
                switch (string) {
                    case "全部":

                        break;
                    case "计划":

                        break;
                    case "临时":

                        break;

                    default:
                        break;

                }
            }
        });
        popWindow.showPopupWindow(v);
    }


    //第一次进来请求的时间
    private void getFirstTime() {
        try {
            Date[] dateArry = AndroidUtils.getTimeCycle(7);
            mStartDate = dateArry[0];
            mEndDate = dateArry[1];
            if ("PM".equals(insrType) && searchCycle == 7) {
                mStartDate = new Date();
                mEndDate = new Date();
            }
            searchCycle = 7;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //初始化数据
    private void initdata() {
        mPzList.add("全部");
        mPzList.add("医疗设备巡检");
        mPzList.add("总务巡检");
        mPzList.add("消防巡检");
        mPzList.add("机房巡检");
        mPzList.add("电工巡检");


        mUpDownList.add("上传巡检数据");
        mUpDownList.add("巡检初始");
        mUpDownList.add("下载模板");
        mUpDownList.add("下载模板数据");
        mUpDownList.add("数据修复");
        mUpDownList.add("数据清空");


    }

    private void initRecycleView() {

        RecyclerView viewChecked = (RecyclerView) View.inflate(this, R.layout.recycleview, null);
        RecyclerView viewUnchecked = (RecyclerView) View.inflate(this, R.layout.recycleview, null);
        RecyclerView viewOverdue = (RecyclerView) View.inflate(this, R.layout.recycleview, null);

        mViewList.add(viewChecked);
        mViewList.add(viewUnchecked);
        mViewList.add(viewOverdue);

        mTitleList.add("已检");
        mTitleList.add("未检");
        mTitleList.add("过期");

        mPagerAdapter.notifyDataSetChanged();


        //设置滚动加载监听,同时加载数据
        setScrollListener(mViewList, mDataList);


        mCheckAdapter = new CommonAdapter<INSPECT_PLAN>(this, R.layout.item_inspect, mCheckList) {
            @Override
            protected void convert(ViewHolder holder, INSPECT_PLAN o, int position) {
                showItemView(holder, o, CHECK_PLAN);
            }
        };


        mUncheckAdapter = new CommonAdapter<INSPECT_PLAN>(this, R.layout.item_inspect, mUnCheckList) {
            @Override
            protected void convert(ViewHolder holder, INSPECT_PLAN o, int position) {
                showItemView(holder, o, UNCHECK_PLAN);
            }
        };
        mOverDueAdapter = new CommonAdapter<INSPECT_PLAN>(this, R.layout.item_inspect, mOverDueList) {
            @Override
            protected void convert(ViewHolder holder, INSPECT_PLAN o, int position) {
                showItemView(holder, o, OVERCHECK_PLAN);
            }
        };
        viewChecked.setAdapter(mCheckAdapter);
        viewUnchecked.setAdapter(mUncheckAdapter);
        viewOverdue.setAdapter(mOverDueAdapter);


    }


    //显示明细的页面
    private void showItemView(ViewHolder holder, INSPECT_PLAN planBean, int type) {

        //说明是临时的 任务 更改视图

        if (type == CHECK_PLAN) {
            holder.setVisible(R.id.tv_label, true);
            holder.setVisible(R.id.iv_label, true);
            if (planBean.getIS_TEMPORARY() != null && planBean.getIS_TEMPORARY()) {
                holder.setBackgroundRes(R.id.tv_label, R.drawable.shape_button_linshi);
                holder.setBackgroundColor(R.id.iv_label, R.color.linshiGreen);
            } else {
                holder.setBackgroundRes(R.id.tv_label, R.drawable.shape_button_plan);
                holder.setBackgroundColor(R.id.iv_label, R.color.planRed);
            }
        }

        holder.setText(R.id.tv_wzmc, planBean.getWZMC());
        holder.setText(R.id.tv_kpbh, planBean.getKPBH());
        holder.setText(R.id.tv_oldkpbh, planBean.getKPBH_OLD());
        holder.setText(R.id.tv_sn, planBean.getSCBH());
        holder.setText(R.id.tv_ggxh, planBean.getGGXH());
        holder.setText(R.id.tv_ppmc, planBean.getPPMC());
        holder.setText(R.id.tv_kssj, TimeUtils.date2String(planBean.getINSP_EXEC_START_DATE(), new SimpleDateFormat("yyyy-MM-dd")));
        holder.setText(R.id.tv_jssj, TimeUtils.date2String(planBean.getINSP_EXEC_END_DATE(), new SimpleDateFormat("yyyy-MM-dd")));
        holder.setText(R.id.tv_dept, planBean.getKSMC());
        holder.setText(R.id.tv_location, planBean.getDDMC());
    }


    /**
     * @param viewList 图的列表
     * @param dataList 数据的列表
     */
    private void setScrollListener(List<RecyclerView> viewList, final List<LoadingBean> dataList) {
        for (int i = 0; i < viewList.size(); i++) {
            RecyclerView recyclerView = viewList.get(i);
            final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            final int finalI = i;
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int lastVisibleItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
                    int totalItemCount = mLayoutManager.getItemCount();
                    //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                    // dy>0 表示向下滑动
                    if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
                        if (!dataList.get(finalI).isLoading) {
                            dataList.get(finalI).isLoading = true;
                            queryAllCount(finalI, dataList.get(finalI).list.size(), 10);
                            dataList.get(finalI).isLoading = false;
                        }
                    }
                }
            });
        }
    }

    @OnClick({R.id.iv_back, R.id.iv_config, R.id.iv_updown, R.id.iv_pz, R.id.tv_search, R.id.fab_xj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_config:
                gotoConfig();
                break;
            case R.id.iv_updown:
                gotoUpdown();
                break;
            case R.id.iv_pz:
                gotoPz();
                break;
            case R.id.tv_search:
                gotoSearch();
                break;
            case R.id.fab_xj:
                Intent intent = new Intent(this, InspectStartActivity.class);
                intent.putExtra("insrType", insrType);
                intent.putExtra("mStartDate", mStartDate);
                intent.putExtra("mEndDate", mEndDate);
                startActivityForResult(intent, INSPECT_START_REQUESET_CODE);
                break;

        }
    }

    //搜索
    private void gotoSearch() {

    }

    //配置
    private void gotoPz() {
        if (mPzPopup == null) {
            mPzPopup = new MenuPopup(this, mPzList, new MenuPopup.BackReslut() {
                @Override
                public void onBackResult(String string) {

                }
            });
        }
        mPzPopup.showPopupWindow(mIvPz);

    }

    //设置 上传下载
    private void gotoUpdown() {
        if (mUpdownPopup == null) {
            mUpdownPopup = new MenuPopup(this, mUpDownList, new MenuPopup.BackReslut() {
                @Override
                public void onBackResult(String string) {
                    switch (string) {
                        case "上传巡检数据":
                            gotoUpData();
                            break;
                        case "巡检初始":
                            break;
                        case "下载模板":
                            break;
                        case "下载模板数据":
                            break;
                        case "数据修复":
                            break;
                        case "数据清空":
                            break;

                    }
                }
            });
        }
        mUpdownPopup.showPopupWindow(mIvUpdown);
    }

    private void gotoUpData() {
        ArrayList<UpBean> list = new ArrayList<>();
        UpBean upBean = new UpBean();
        upBean.setEnties("coder.aihui.data.bean.INSPECT_REP");
        upBean.setMethods("uploadPdaRepDataJSON");
        upBean.setPars(null);
        upBean.setType(INSPECT_UP);
        upBean.setWay(HTTP);
        upBean.setName("主表");
        upBean.setCount(0);
        upBean.setBigType(mBigType[2]);
        upBean.setPropertie(new Property[]{INSPECT_REPDao.Properties.SYNC_FLAG});
        upBean.setWhereconditions(new WhereCondition[]{INSPECT_REPDao.Properties.INSR_TYPE.eq("XJ")});


        UpBean upBean2 = new UpBean();
        upBean2.setEnties("coder.aihui.data.bean.INSPECT_REPS");
        upBean2.setMethods("uploadPdaRepsDataJSON");
        upBean2.setPars(null);
        upBean2.setType(INSPECT_UP);
        upBean2.setWay(HTTP);
        upBean2.setName("明细表");
        upBean2.setCount(0);
        upBean2.setBigType(mBigType[2]);
        upBean2.setPropertie(new Property[]{INSPECT_REPSDao.Properties.SYNC_FLAG});
        upBean2.setWhereconditions(new WhereCondition[]{INSPECT_REPSDao.Properties.INSR_TYPE.eq("XJ")});

        list.add(upBean);
        list.add(upBean2);
        mDownPresenter.gotoUp(list, INSPECT_UP);
    }

    //配置
    private void gotoConfig() {
        Intent intent = new Intent(this, InspectConfigActivity.class);
        intent.putExtra("mEndDate", mEndDate);
        intent.putExtra("mAllDlwzIds", mAllDlwzIds);
        startActivityForResult(intent, Content.INSPECT_CONFIG_REQUESET_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //配置回来
                case Content.INSPECT_CONFIG_REQUESET_CODE:

                    mDeptIds = data.getStringExtra("mDeptIds");
                    mDlwzIds = data.getStringExtra("mDlwzIds");
                    mAllDeptIds = data.getStringExtra("mAllDeptIds");
                    mAllDlwzIds = data.getStringExtra("mAllDlwzIds");
                    mStartDate = (Date) data.getSerializableExtra("mStartDate");
                    mEndDate = (Date) data.getSerializableExtra("mEndDate");
                    mAllDlwzIds = data.getStringExtra("mAllDlwzIds");
                    break;
                //开始巡检回来
                case INSPECT_START_REQUESET_CODE:
                    for (int i = 0; i < mTitleList.size(); i++) {
                        queryAllCount(i, 0, 10);
                    }
                    break;
            }
        }
    }

    public void queryAllCount(final int which, int i_start, int i_count) {

        searchText = mEtSearch.getText().toString();
        try {
            INSPECT_PLANDao planDao = mDaoSession.getINSPECT_PLANDao();
            QueryBuilder<INSPECT_PLAN> qb2 = planDao.queryBuilder();
            //用户ID
            String userID = SPUtil.getString(this, "userID", "");
            qb2.where(new WhereCondition.StringCondition("INSP__FK__ID IN " + "(SELECT INSEE__FK__ID FROM INSPECT__EXT__EXECUTOR WHERE INSEE__TYPE='" + insrType + "' AND INSEE__USER__ID = " + userID + ")"));
            if (!"".equals(dicType)) {
                qb2.where(INSPECT_PLANDao.Properties.XJFL.eq(dicType));
            }
            if (!"".equals(mAllDlwzIds)) {
                String allDlwzNo = mAllDlwzIds.substring(1, mAllDlwzIds.length() - 1);
                String[] dlwzArr = allDlwzNo.split(",");
                List<String> dlwzList = Arrays.asList(dlwzArr);
                qb2.where(INSPECT_PLANDao.Properties.DDID.in(dlwzList));
            }
            if (!"".equals(mAllDeptIds)) {
                String allKsdm = mAllDeptIds.substring(1, mAllDlwzIds.length() - 1);
                String[] ksArr = allKsdm.split(",");
                List<String> ksList = Arrays.asList(ksArr);
                qb2.where(INSPECT_PLANDao.Properties.KSID.in(ksList));
            }
            if (searchCycle == 7) {
                qb2.where(INSPECT_PLANDao.Properties.INSP_EXEC_START_DATE.le(new Date()),
                        INSPECT_PLANDao.Properties.INSP_EXEC_END_DATE.ge(new Date()));
            } else {
                qb2.where(INSPECT_PLANDao.Properties.INSP_EXEC_DATE.ge(mStartDate),
                        INSPECT_PLANDao.Properties.INSP_EXEC_DATE.le(mEndDate));
            }
            qb2.where(INSPECT_PLANDao.Properties.INSP_TYPE.eq(insrType));
            if (!"".equals(searchText) && searchType == 3) {
                qb2.where(INSPECT_PLANDao.Properties.WZMC
                        .like("%" + searchText + "%"));
            }
            long num = -1;

            switch (which) {

                case 0:
                    //已清点的
                    qb2.where(INSPECT_PLANDao.Properties.ISCHECK.isNotNull(), INSPECT_PLANDao.Properties.INSP_EXEC_END_DATE.ge(new Date()));
                    num = qb2.count();
                    break;
                case 1:
                    //未清点的
                    qb2.where(INSPECT_PLANDao.Properties.ISCHECK.isNull(), INSPECT_PLANDao.Properties.INSP_EXEC_END_DATE.ge(new Date()));
                    num = qb2.count();
                    break;
                case 2:
                    //过期的
                    qb2.where(INSPECT_PLANDao.Properties.ISCHECK.isNull(), INSPECT_PLANDao.Properties.INSP_EXEC_END_DATE.lt(new Date()));
                    num = qb2.count();
                    break;
            }
            //以上是数字
            final long finalNum = num;
            //如果数目都没变的话 肯定是不对的
            if (num == -1) {
                return;
            }

            qb2.limit(i_count).offset(i_start).rx().list().subscribeOn(Schedulers.io()).compose(this.<List<INSPECT_PLAN>>bindToLife()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<INSPECT_PLAN>>() {
                @Override
                public void call(List<INSPECT_PLAN> inspect_plen) {
                    switch (which) {
                        case 0:
                            ((TextView) mTabViewList.get(which).findViewById(R.id.tv_name))
                                    .setText("已检(" + finalNum + ")");

                            mCheckList.addAll(inspect_plen);
                            mCheckAdapter.notifyDataSetChanged();
                            break;
                        case 1:
                            ((TextView) mTabViewList.get(which).findViewById(R.id.tv_name))
                                    .setText("未检(" + finalNum + ")");


                            mUnCheckList.addAll(inspect_plen);
                            mUncheckAdapter.notifyDataSetChanged();
                            break;
                        case 2:
                            ((TextView) mTabViewList.get(which).findViewById(R.id.tv_name))
                                    .setText("过期(" + finalNum + ")");

                            mOverDueList.addAll(inspect_plen);
                            mOverDueAdapter.notifyDataSetChanged();
                            break;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mWhichSelect = tab.getPosition();
        //还没数据的 去加载数据 有数据的 就直接显示
    /*    if (mDataList.get(mWhichSelect).list.size() == 0) {
            queryAllCount(mWhichSelect, 0, 10);
        }*/

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
   /*     int position = tab.getPosition();
        if (position == 1) {
            tab.setCustomView(R.include_tab.tab_view1);
        }
        if (position == 2) {
            tab.setCustomView(R.include_tab.tab_view2);
        }*/
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void showSuccess(int type) {

    }

    @Override
    public void showFault(int type, String wrong) {

    }

    @Override
    public void showProgress(int num, int type) {

    }
}
