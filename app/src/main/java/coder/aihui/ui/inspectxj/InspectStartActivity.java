package coder.aihui.ui.inspectxj;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.data.bean.INSPECT_EXT;
import coder.aihui.data.bean.INSPECT_EXT_EXECUTOR;
import coder.aihui.data.bean.INSPECT_PLAN;
import coder.aihui.data.bean.INSPECT_REP;
import coder.aihui.data.bean.IN_ASSET;
import coder.aihui.data.bean.InspectTempletItem;
import coder.aihui.data.bean.LoadingBean;
import coder.aihui.data.bean.REPAIR_PLACE;
import coder.aihui.data.bean.gen.INSPECT_EXTDao;
import coder.aihui.data.bean.gen.INSPECT_EXT_EXECUTORDao;
import coder.aihui.data.bean.gen.INSPECT_PLANDao;
import coder.aihui.data.bean.gen.INSPECT_REPDao;
import coder.aihui.data.bean.gen.IN_ASSETDao;
import coder.aihui.data.bean.gen.InspectTempletItemDao;
import coder.aihui.data.bean.gen.REPAIR_PLACEDao;
import coder.aihui.util.AndroidUtils;
import coder.aihui.util.ListUtils;
import coder.aihui.util.SPUtil;
import coder.aihui.util.ToastUtil;
import coder.aihui.widget.AlertListDialogUtil;
import coder.aihui.widget.MyArrayAdapter;
import coder.aihui.ui.contact.LessUserActivity;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static coder.aihui.R.array.inspect;
import static coder.aihui.base.Content.INSPECT_DETAIL_REQUESET_CODE;
import static coder.aihui.base.Content.USER_ID_LIST;
import static coder.aihui.base.Content.USER_NAME_LIST;
import static java.lang.Long.parseLong;

public class InspectStartActivity extends AppActivity implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.iv_people)
    LinearLayout mIvPeople;
    @BindView(R.id.iv_scan)
    LinearLayout mIvScan;
    @BindView(R.id.iv_search)
    LinearLayout mIvSearch;
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

    @BindView(R.id.fab_backToTop)
    FloatingActionButton mFabBackToTop;
    @BindView(R.id.tv_title)
    TextView             mTvTitle;
    @BindView(R.id.tv_start)
    TextView             mTvStart;
    @BindView(R.id.tv_end)
    TextView             mTvEnd;

    private String mDeptName;
    private String mDeptIds;
    private String mDlwzName;
    private String mDlwzIds;       //相当于All
    private String mAllDeptName = "";
    private String mAllDlwzName = "";
    private String mAllDlwzIds  = "";//所有的子地理位置
    private String mAllDeptIds  = "";//所有科室


    private             List<String>       mTitleList  = new ArrayList<>();
    private             List<RecyclerView> mViewList   = new ArrayList<>();
    public static final int                REP_ALL     = 0;//全部
    public static final int                REP_CHECKED = 1;//
    public static final int                REP_NOPLAN  = 2;//过期

    private InspectPagerAdapter mPagerAdapter;
    private CommonAdapter       mAllAdapter;
    private CommonAdapter       mPlanAdapter;
    private CommonAdapter       mNoPlanAdapter;

    private List<INSPECT_PLAN> mAllList    = new ArrayList<>();
    private List<INSPECT_PLAN> mPlanList   = new ArrayList<>();
    private List<INSPECT_PLAN> mNoPlanList = new ArrayList<>();

    private View mALLTabView;    //全部tabView
    private View mPlanView;    //计划内TabVIew
    private View mNoplanTabView;    //无计划TabView
    private List<View> mTabViewList = new ArrayList<>();

    private String insrType    = "XJ";//默认巡检   新增JL
    private String dicType     = "";//字典ID 字典类别,巡检分类 0 查询全部
    private int    searchCycle = 1;//查询周期  1 今天，2本周 3 本月 4当天向前15天 5当天向前三十天 6当天向前45天 7一个周期 目前是两个月


    private int CurrentWhich = 0;   //当前选中的是哪个


    private Date mStartDate;//查询开始日期00:00
    private Date mEndDate;//查询结束日期23:59
    private String  mSearchText = "";//内容，优先级最高
    private Integer pdaType     = 1;//PDA的状态，1、RFID，2、激光，3，摄像头

    private List<LoadingBean> mDataList = new ArrayList<>();
    private int               modeType  = 0;           //普通巡检还是自动巡检

    //全部选择的人
    private ArrayList<Integer> userChooseIds   = new ArrayList<>();
    private ArrayList<String>  userChooseNames = new ArrayList<>();
    private MyArrayAdapter<String> mSearchAdapter;


    /**
     * <item>品牌|名称|规格</item>
     * <item>名称</item>
     * <item>新资产编号</item>
     * <item>老资产编号</item>
     */
    private String mSearchType;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_inspect_start;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }


    @Override
    protected void initView() {
        mSearchType = getResources().getStringArray(inspect)[0];
        mPagerAdapter = new InspectPagerAdapter(mTitleList, mViewList, this);
        mVp.setAdapter(mPagerAdapter);

        mTb.setupWithViewPager(mVp);
        mTb.addOnTabSelectedListener(this);

        mDataList.add(new LoadingBean(mAllList, false));
        mDataList.add(new LoadingBean(mPlanList, false));
        mDataList.add(new LoadingBean(mNoPlanList, false));



        initGetIntent();

        //初始化Spinner数据
        initSpinner();
        //初始化列表视图
        initRecycleView();
        //变更tab显示
        changeTextOfTab();
        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                /*判断是否是“GO”键*/
                if ((event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) || actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                    doSearch();
                }
                return true;

            }
        });


    }

    private void initSpinner() {
        mSearchAdapter = new MyArrayAdapter<String>(this, R.layout.mysimple_spinner_item, getResources().getStringArray(inspect));
        mSearchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpSearch.setAdapter(mSearchAdapter);

        mSpSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSearchType = InspectStartActivity.this.getResources().getStringArray(inspect)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSearchType = InspectStartActivity.this.getResources().getStringArray(inspect)[0];
            }
        });
    }

    private void initGetIntent() {

        Intent intent = getIntent();
        mStartDate = (Date) intent.getSerializableExtra("mStartDate");
        mEndDate = (Date) intent.getSerializableExtra("mEndDate");
        insrType =  intent.getStringExtra("insrType");
        setTime();
        switch (insrType) {
            case "XJ":
                mTvTitle.setText("开始巡检");
                break;
            case "PM":
                mTvTitle.setText("开始PM");
                break;
        }
    }


    private void setTime() {

        mTvStart.setText("周期开始时间:" + TimeUtils.date2String(mStartDate, mFormat));
        mTvEnd.setText("周期结束时间:" + TimeUtils.date2String(mEndDate, mFormat));
    }

    private void initRecycleView() {

        RecyclerView viewChecked = (RecyclerView) View.inflate(this, R.layout.recycleview, null);
        RecyclerView viewUnchecked = (RecyclerView) View.inflate(this, R.layout.recycleview, null);
        RecyclerView viewOverdue = (RecyclerView) View.inflate(this, R.layout.recycleview, null);

        mViewList.add(viewChecked);
        mViewList.add(viewUnchecked);
        mViewList.add(viewOverdue);

        mTitleList.add("总计");
        mTitleList.add("计划");
        mTitleList.add("过期");

        mPagerAdapter.notifyDataSetChanged();


        //设置滚动加载监听,同时加载数据
        setScrollListener(mViewList, mDataList);
        mAllAdapter = new CommonAdapter<INSPECT_PLAN>(this, R.layout.item_inspect, mAllList) {
            @Override
            protected void convert(ViewHolder holder, INSPECT_PLAN o, int position) {


                showItemView(holder, o, REP_ALL);
            }
        };
        mPlanAdapter = new CommonAdapter<INSPECT_PLAN>(this, R.layout.item_inspect, mPlanList) {
            @Override
            protected void convert(ViewHolder holder, INSPECT_PLAN o, int position) {
                showItemView(holder, o, REP_CHECKED);
            }
        };
        mNoPlanAdapter = new CommonAdapter<INSPECT_PLAN>(this, R.layout.item_inspect, mNoPlanList) {
            @Override
            protected void convert(ViewHolder holder, INSPECT_PLAN o, int position) {
                showItemView(holder, o, REP_NOPLAN);
            }
        };
        viewChecked.setAdapter(mAllAdapter);
        viewUnchecked.setAdapter(mPlanAdapter);
        viewOverdue.setAdapter(mNoPlanAdapter);


    }


    //变更tabView的显示
    private void changeTextOfTab() {

        for (int i = 0; i < mTitleList.size(); i++) {
            TabLayout.Tab tab = mTb.getTabAt(i);
            int position = tab.getPosition();
            //还没数据的 去加载数据 有数据的 就直接显示
            if (position == 0) {
                mALLTabView = View.inflate(this, R.layout.tab_view0, null);
                View viewById = mALLTabView.findViewById(R.id.iv_showmore);
                viewById.setVisibility(View.GONE);
                mTabViewList.add(mALLTabView);
            } else if (position == 1) {
                mPlanView = View.inflate(this, R.layout.tab_view1, null);
                mTabViewList.add(mPlanView);
            } else if (position == 2) {
                mNoplanTabView = View.inflate(this, R.layout.tab_view2, null);
                mTabViewList.add(mNoplanTabView);
            }
            tab.setCustomView(mTabViewList.get(i));
            //查询数据 渲染
            queryAllCount(position, 0, 10);

        }


    }


    /**
     * @param holder   视图持有者
     * @param planBean 数据持有者
     * @param type
     */
    //显示明细的页面
    private void showItemView(ViewHolder holder, final INSPECT_PLAN planBean, int type) {

        //说明是临时的 任务 更改视图

        if (type == REP_ALL) {
            holder.setVisible(R.id.tv_label, true);
            holder.setVisible(R.id.iv_label, true);
            if (planBean.getIS_TEMPORARY() != null) {                                           //临时计划
                holder.setBackgroundRes(R.id.tv_label, R.drawable.shape_button_linshi);
                holder.setBackgroundColor(R.id.iv_label, R.color.linshiGreen);
            } else {
                holder.setBackgroundRes(R.id.tv_label, R.drawable.shape_button_plan);
                holder.setBackgroundColor(R.id.iv_label, R.color.planRed);
            }
        }
        holder.setOnClickListener(R.id.ll, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InspectStartActivity.this, InspectDetailActivity.class);
                intent.putExtra("isNew", 1);
                intent.putExtra("planId", planBean.getINSP_ID());//如果isPlan是3，就是设备或者巡检的ID(对应的是台账或者Rp的Id)
                startActivityForResult(intent, INSPECT_DETAIL_REQUESET_CODE);
            }
        });

        holder.setText(R.id.tv_wzmc, planBean.getWZMC());
        holder.setText(R.id.tv_kpbh, planBean.getKPBH());
        holder.setText(R.id.tv_oldkpbh, planBean.getKPBH_OLD());
        holder.setText(R.id.tv_sn, planBean.getSCBH());
        holder.setText(R.id.tv_ggxh, planBean.getGGXH());
        holder.setText(R.id.tv_ppmc, planBean.getPPMC());
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
                    int lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
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
    public void queryAllCount(final int which, int i_start, int i_count) {
        mSearchText = mEtSearch.getText().toString();
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


            qb2.where(INSPECT_PLANDao.Properties.INSP_TYPE.eq(insrType));
            if (!TextUtils.isEmpty(mSearchText)) {
                if ("名称".equals(mSearchType)) {
                    qb2.where(INSPECT_PLANDao.Properties.WZMC
                            .like("%" + mSearchText + "%"));
                } else if ("新资产编号".equals(mSearchType)) {
                    qb2.where(INSPECT_PLANDao.Properties.KPBH
                            .like("%" + mSearchText + "%"));
                }
            }
            long num = -1;

            switch (which) {
                case 0:
                    //1是已检
                    num = qb2.where(INSPECT_PLANDao.Properties.ISCHECK.eq(1)).count();
                    break;
                case 1:
                    num = qb2.where(INSPECT_PLANDao.Properties.ISCHECK.eq(1))
                            .where(INSPECT_PLANDao.Properties.INSP_ID.notEq(-1L))
                            .count();
                    break;
                case 2:
                    num = qb2.where(INSPECT_PLANDao.Properties.ISCHECK.eq(1))
                            .where(INSPECT_PLANDao.Properties.INSP_ID.eq(-1L))
                            .where(INSPECT_PLANDao.Properties.IS_TEMPORARY.eq(1)).count();
                    break;
            }
            //以上是数字
            final long finalNum = num;
            //如果数目都没变的话 肯定是不对的
            if (num == -1) {
                return;
            }
            qb2.limit(i_count).offset(i_start).rx().list().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<INSPECT_PLAN>>() {
                @Override
                public void call(List<INSPECT_PLAN> inspect_plen) {
                    switch (which) {
                        case 0:
                            ((TextView) mTabViewList.get(which).findViewById(R.id.tv_name))
                                    .setText("全部(" + finalNum + ")");
                            mAllList.addAll(inspect_plen);
                            mAllAdapter.notifyDataSetChanged();
                            break;
                        case 1:
                            ((TextView) mTabViewList.get(which).findViewById(R.id.tv_name))
                                    .setText("计划(" + finalNum + ")");
                            mPlanList.addAll(inspect_plen);
                            mPlanAdapter.notifyDataSetChanged();
                            break;
                        case 2:
                            ((TextView) mTabViewList.get(which).findViewById(R.id.tv_name))
                                    .setText("临时(" + finalNum + ")");
                            mNoPlanList.addAll(inspect_plen);
                            mNoPlanAdapter.notifyDataSetChanged();
                            break;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        CurrentWhich = tab.getPosition();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }


    public void doSaveListData(Set<String> set) {


        if (set == null || set.size() == 0) {
            return;
        }

        for (String str1 : set) {
            //从分组编号选择全部过来的标签
            String groupRfid = "";
            if (str1.length() > 25) {
                groupRfid = str1.substring(0, 17);//长度太长，拿出分组信息
                str1 = str1.substring(17, str1.length());
            }
            //此处增加分组标签（B开头）及地理位置（D开头）
            if (str1.indexOf("D") == 0 || str1.indexOf("B") == 0) {
                //先要查出ddid，


                //// TODO: 2017/8/10 bug
                List<INSPECT_PLAN> list = AndroidUtils.getInspPlanByRfid(InspectStartActivity.this, mDaoSession, str1);
                AlertDialog.Builder builder = new AlertDialog.Builder(InspectStartActivity.this);
                builder.setTitle("选择一个项目");
                builder.show();
            }
            //查询计划表
            INSPECT_PLANDao planDao = mDaoSession.getINSPECT_PLANDao();
            //按照计划时间顺序排序
            //此处增加了PM/XJ
            List<INSPECT_PLAN> list = planDao
                    .queryBuilder()
                    .where(INSPECT_PLANDao.Properties.RFID_CODE.eq(str1))
                    .where(INSPECT_PLANDao.Properties.INSP_TYPE.eq(insrType))
                    .orderAsc(INSPECT_PLANDao.Properties.INSP_EXEC_DATE)
                    .list();

            //计划表存在
            Integer isPlan = 0;//是否计划 0:不是 1:计划表存在，过期or还没到 2:是正常的计划,3有模版
            Long planId = 0L;//计划ID
            String rfidType = "";
            String INSP_FK_ID = "";//RP,TZ ,id
            Long INSE_TEMPLATE_ID = -1L;        //模板表对应的Id  取自计划Plan


            //有计划的进入这个循环和判断 看代码 是其实只取了最后一个
            if (list != null && list.size() > 0) {
                //判断是否计划 or 临时(重复)
                //inspTimeFloat：针对小时的巡检， 巡检小时浮动 0表示不限制,找上一个或者下一个要巡检的计划，优先上一个
                //inspDayFloat: 针对天的巡检， 巡检天浮动 0表示不限制,找上一个或者下一个要巡检的计划，优先上一个
                for (INSPECT_PLAN plan : list) {
                    //已经巡检过了
                    isPlan = 1;
                    INSP_FK_ID = plan.getINSP_FK_ID();
                    planId = plan.getINSP_ID();
                    //已检的跳过
                    INSE_TEMPLATE_ID = plan.getINSE_TEMPLATE_ID() == null ? -1L : plan.getINSE_TEMPLATE_ID();
                    if (plan.getISCHECK() != null && plan.getISCHECK() == 1) {
                        continue;
                    }
                    //如果在周期内的话，就是计划
                    if (plan.getINSP_EXEC_START_DATE() != null && plan.getINSP_EXEC_END_DATE() != null && (new Date()).getTime() >= plan.getINSP_EXEC_START_DATE().getTime() && (new Date()).getTime() <= plan.getINSP_EXEC_END_DATE().getTime()) {
                        isPlan = 2;
                        break;
                    }
                }
            }
            //						AndroidUtils.showErrorMsg("提示", "巡检类型(1、2、计划，0、无)：isPlan="+ isPlan + ";扫到的RFID="+str1 , InspectStartActivity.this);
            String userID = SPUtil.getString(this, "userID", "");
            if (isPlan == 0) {//以后修改为，如果没有计划，改为查询任务
                //无计划，模板取字典表
                //判断表，是否有模板
                Long dicId = -1L;
                if (str1.startsWith("A")) {//A开头的话是巡检点
                    REPAIR_PLACEDao placeDao = mDaoSession.getREPAIR_PLACEDao();
                    List<REPAIR_PLACE> placeList = placeDao.queryBuilder()
                            .where(REPAIR_PLACEDao.Properties.PLACE_RFID_CODE.eq(str1))
                            .list();
                    if (placeList == null || placeList.size() == 0) {
                        continue;
                    } else {
                        INSP_FK_ID = "RP" + placeList.get(0).getPLACE_ID();
                    }
                } else {//台账
                    IN_ASSETDao assetDao = mDaoSession.getIN_ASSETDao();
                    QueryBuilder<IN_ASSET> queryBuilder = assetDao.queryBuilder();
                    List<IN_ASSET> assetList = queryBuilder
                            .where(IN_ASSETDao.Properties.RFID_CODE.eq(str1))
                            .list();
                    if (assetList == null || assetList.size() == 0) {
                        //ToastUtil.showToast("当条数据不在台账内!");
                        continue;
                    } else {
                        INSP_FK_ID = "TZ" + assetList.get(0).getID();
                    }
                }
                //增加了PM/XJ
                INSPECT_EXTDao extDao = mDaoSession.getINSPECT_EXTDao();
                List<INSPECT_EXT> extList = extDao.queryBuilder()
                        .where(INSPECT_EXTDao.Properties.INSE_FK_ID.eq(INSP_FK_ID))
                        .where(INSPECT_EXTDao.Properties.INSE_TYPE.eq(insrType))
                        .list();
                if (extList == null || extList.size() == 0) {
                    // ToastUtil.showToast("当条数据没模板");
                    continue;
                } else {
                    //关联Id 关联的是模板表
                    String inse_template_id = extList.get(0).getINSE_TEMPLATE_ID();
                    if (TextUtils.isEmpty(inse_template_id)) {
                        // ToastUtil.showToast("当条数据无模板!");
                        continue;
                    }
                    try {
                        dicId = parseLong(inse_template_id);
                        if (dicId == 0L) {
                            ToastUtil.showToast("当条数据有问题!");
                            continue;
                        }
                    } catch (Exception e) {
                        ToastUtil.showToast("当条数据有问题1!");
                        continue;
                    }
                }
                InspectTempletItemDao dicDao = mDaoSession.getInspectTempletItemDao();
                List<InspectTempletItem> dicList = dicDao.queryBuilder()
                        .where(InspectTempletItemDao.Properties.ITEM_TYPE.eq("PM".equals(insrType) ? 2 : 1))
                        .where(InspectTempletItemDao.Properties.ITEM_PARENT_ID.eq(dicId + ""))
                        .orderAsc(InspectTempletItemDao.Properties.ITEM_SORT)
                        .orderAsc(InspectTempletItemDao.Properties.ITEM_ID)
                        .list();
                if (dicList == null || dicList.size() == 0) {
                    //ToastUtil.showToast("当前设备没有模板,请重新进行初始化或者检查后台数据是否正确");
                    continue;
                }
                INSE_TEMPLATE_ID = dicId;           //其实都是 INSE_TEMPLATE_ID
                isPlan = 3;//等于3为纯任务的巡检
                try {
                    planId = parseLong(INSP_FK_ID.substring(2));//如果plan是3，需要查询一些基本信息，需要这个东西
                } catch (Exception e) {
                    ToastUtil.showToast("当条数据有问题");
                    continue;
                }
                rfidType = INSP_FK_ID.indexOf("TZ") > -1 ? "TZ" : "RP";
            } else {
                if (isPlan == 2) {
                    isPlan = 1;
                    // 判断巡检人, 不是责任人的话，就是一条临时记录
                    INSPECT_EXT_EXECUTORDao inseeDao = mDaoSession.getINSPECT_EXT_EXECUTORDao();
                    List<INSPECT_EXT_EXECUTOR> inseeList = inseeDao.queryBuilder().where(INSPECT_EXT_EXECUTORDao.Properties.INSEE_FK_ID.eq(INSP_FK_ID)).list();
                    for (INSPECT_EXT_EXECUTOR ext : inseeList) {
                        if (userID.equals(String.valueOf(ext.getINSEE_USER_ID()))) {
                            isPlan = 2;
                            break;
                        }
                    }
                }
            }
            if (isPlan > 0) {

                if (modeType == 0) {            //普通巡检
                    //有计划
                    //计划表存在的时候，要判断这条计划
                    Intent intent = new Intent(this, InspectDetailActivity.class);
                    intent.putExtra("dicId", INSE_TEMPLATE_ID);//有用 了
                    intent.putExtra("rfidType", rfidType);//设备还是巡检点
                    intent.putExtra("isPlan", isPlan);
                    intent.putExtra("TYPE", insrType);//XJ还是PM
                    if ("PM".equals(insrType)) {
                        //看看计划Id
                        List<INSPECT_REP> list1 = mDaoSession.getINSPECT_REPDao().queryBuilder()
                                .where(INSPECT_REPDao.Properties.INSR_TYPE.eq("PM"))
                                .where(INSPECT_REPDao.Properties.INSR_FK_ID.eq(planId))
                                .list();
                        if (list1 != null && list1.size() != 0) {
                            intent.putExtra("isNew", 0);
                            intent.putExtra("planId", list1.get(0).getINSR_ID());       //相当于重新打开 planid是rep的主键Id
                        } else {
                            intent.putExtra("isNew", 1);
                            intent.putExtra("planId", planId);//如果isPlan是3，就是设备或者巡检的ID(对应的是台账或者Rp的Id)
                        }
                    } else {
                        intent.putExtra("isNew", 1);//新的巡检，不是重新打开
                        intent.putExtra("planId", planId);//如果isPlan是3，就是设备或者巡检的ID(对应的是台账或者Rp的Id)
                    }
                    intent.putExtra("result", str1);

                    intent.putExtra("mInspFkId", INSP_FK_ID);
                    intent.putExtra("allUserName", ListUtils.listToStrings(userChooseNames));
                    intent.putExtra("allUserId", ListUtils.listToStrings(userChooseIds));
                    intent.putExtra("where", "InspectStart");

                    if (groupRfid != null && !"".equals(groupRfid)) {
                        intent.putExtra("groupRfid", groupRfid);//巡检的是整个分组
                    }
                    startActivityForResult(intent,INSPECT_DETAIL_REQUESET_CODE);
                    break;

                } else if (modeType == 1) {                 //自动巡检
                    INSPECT_REP inspectRep = new INSPECT_REP();
                    Long dicid = -1L;
                    //此处自动巡检需要区分是计划(临时，正常)还是任务
                    if (isPlan == 2 || isPlan == 1) {
                        //Todo 要更改
                        INSPECT_PLANDao pDao = mDaoSession.getINSPECT_PLANDao();

                        INSPECT_PLAN plan = pDao.queryBuilder().where(INSPECT_PLANDao.Properties.INSP_ID.eq(planId)).unique();
                        INSPECT_PLAN inspectPlan = null;

                        dicid = plan.getINSE_TEMPLATE_ID();

                        inspectRep.setWZMC(plan.getWZMC());
                        inspectRep.setKPBH(plan.getKPBH());
                        inspectRep.setDDMC(plan.getDDMC());
                        inspectRep.setDDID(plan.getDDID());

                        inspectRep.setINSR_NID(plan.getINSP_FK_ID());//ID感觉应该加RP/TZ
                        inspectRep.setBAR_CODE(plan.getBAR_CODE());
                        inspectRep.setRFID_CODE(plan.getRFID_CODE());
                        inspectRep.setXJFL(plan.getXJFL());
                        inspectRep.setXJFL_MC(plan.getXJFL_MC());
                        inspectRep.setZQLX(plan.getZQLX());
                        inspectRep.setINSE_CYCLE(plan.getINSE_CYCLE());


                        inspectRep.setKPBH_OLD(plan.getKPBH_OLD());//显示旧编号
                        inspectRep.setKSID(plan.getKSID());//加入科室
                        inspectRep.setKSMC(plan.getKSMC());

                        //新加的三个
                        inspectRep.setGGXH(plan.getGGXH());
                        inspectRep.setSCBH(plan.getSCBH());
                        inspectRep.setPPMC(plan.getPPMC());

                        //更新计划表
                        if (isPlan == 2) {
                            inspectRep.setINSR_FK_ID(Integer.parseInt(planId + ""));//关联计划外键ID,无计划的记录值写0
                            inspectPlan = mDaoSession.getINSPECT_PLANDao().queryBuilder().where(INSPECT_PLANDao.Properties.INSP_ID.eq(planId)).unique();
                            inspectPlan.setISCHECK(1);
                            inspectPlan.setRESULT(1);//1是合格
                            mDaoSession.getINSPECT_PLANDao().insertOrReplace(inspectPlan);//更新计划表
                        } else {
                            inspectRep.setINSR_FK_ID(0);//关联计划外键ID,无计划的记录值写0
                        }

                    } else {
                        //需要通过ID去查询到相关的数据
                        inspectRep.setINSR_FK_ID(0);//关联计划外键ID,无计划的记录值写0
                        if ("TZ".equals(rfidType)) {
                            IN_ASSETDao assetDao = mDaoSession.getIN_ASSETDao();


                            IN_ASSET asset = assetDao.load(planId);

                            //这里的planId是台账的主键 前面切割过了


                            inspectRep.setWZMC(asset.getWZMC());
                            inspectRep.setKPBH(asset.getKPBH());
                            inspectRep.setDDMC(asset.getDDMC());

                            try {
                                long l = parseLong(asset.getDDID());
                                inspectRep.setDDID(l);
                            } catch (Exception e) {

                            }
                            //									inspectRep.setXJFL_MC("");
                            inspectRep.setINSR_NID(INSP_FK_ID);//ID感觉应该加RP/TZ
                            inspectRep.setRFID_CODE(asset.getRFID_CODE());
                            inspectRep.setBAR_CODE(asset.getBAR_CODE());

                            inspectRep.setKPBH_OLD(asset.getKPBH_OLD());//显示旧编号


                            if (TextUtils.isEmpty(asset.getKSID())) {
                                Long aLong = Long.valueOf(asset.getKSID());
                                inspectRep.setKSID(aLong);//加入科室
                            }
                            inspectRep.setKSMC(asset.getKSMC());


                            //新加的三个
                            inspectRep.setGGXH(asset.getGGXH());
                            inspectRep.setSCBH(asset.getSCBH());
                            inspectRep.setPPMC(asset.getPPMC());

                        } else {
                            REPAIR_PLACEDao placeDao = mDaoSession.getREPAIR_PLACEDao();
                            REPAIR_PLACE place = placeDao.load(planId);


                            inspectRep.setWZMC(place.getPLACE_NAME());
                            inspectRep.setKPBH(place.getPLACE_KPBH());
                            inspectRep.setDDMC(place.getITEM_DD_NAME());
                            String place_ddid = place.getPLACE_DDID();
                            if (!TextUtils.isEmpty(place_ddid)) {
                                inspectRep.setDDID(parseLong(place_ddid));
                            }

                            inspectRep.setINSR_NID(INSP_FK_ID);//ID感觉应该加RP/TZ
                            inspectRep.setRFID_CODE(place.getPLACE_RFID_CODE());
                            inspectRep.setBAR_CODE(place.getPLACE_BAR_CODE());
                            if (place.getPLACE_XJID() != null && !"".equals(place.getPLACE_XJID())) {
                                inspectRep.setXJFL(parseLong(place.getPLACE_XJID()));
                            }
                            inspectRep.setXJFL_MC(place.getITEM_XJ_NAME());


                        }
                        INSPECT_EXTDao extDao = mDaoSession.getINSPECT_EXTDao();
                        List<INSPECT_EXT> extList = extDao.queryBuilder()
                                .where(INSPECT_EXTDao.Properties.INSE_FK_ID.eq(INSP_FK_ID))
                                .list();
                        INSPECT_EXT ectExt = extList.get(0);
                        if (extList != null && extList.size() != 0) {
                            dicid = TextUtils.isEmpty(ectExt.getINSE_TEMPLATE_ID()) ? -1L : parseLong(extList.get(0).getINSE_TEMPLATE_ID());
                        }


                        inspectRep.setZQLX(ectExt.getINSE_CYCLE_TYPE());
                        inspectRep.setINSE_CYCLE(ectExt.getINSE_CYCLE() + "");

                    }
                    //TODO 重要
                    inspectRep.setINSR_TYPE(insrType);
                    inspectRep.setINSR_EXEC_DATE(new Date());
                    inspectRep.setINSR_RESULT("HG");


                    if (userID == null || userID.equals("")) {
                        inspectRep.setINSR_USER_ID(0);
                    } else {
                        inspectRep.setINSR_USER_ID(Integer.parseInt(userID));
                    }
                    inspectRep.setINSR_CREATE_DATE(new Date());
                    inspectRep.setINSR_CREATE_USER(Integer.parseInt(userID));

                    inspectRep.setINSR_UPDATE_DATE(new Date());
                    inspectRep.setINSR_UPDATE_USER(Integer.parseInt(userID));

                    inspectRep.setINSR_WX_NEED(0);

                    //保存巡检记录
                    String puuid = AndroidUtils.createUUID();
                    inspectRep.setUUID(puuid);//设置UUID
                    //放入巡检人
                    inspectRep.setREP_NAMES(ListUtils.listToStrings(userChooseNames));
                    inspectRep.setREP_IDS(ListUtils.listToStrings(userChooseIds));
                    inspectRep.setISCHECK(1);

                    Long repId = mDaoSession.getINSPECT_REPDao().insertOrReplace(inspectRep);
                    inspectRep.setINSR_ID(repId);

                    //从字典表取，会传字典ID过来
                    InspectTempletItemDao dicDao = mDaoSession.getInspectTempletItemDao();
                    List<InspectTempletItem> dicParList = dicDao.queryBuilder().where(InspectTempletItemDao.Properties.ITEM_PARENT_ID.eq(dicid))
                            .where(InspectTempletItemDao.Properties.ITEM_TYPE.eq("PM".equals(insrType) ? 2 : 1))
                            .orderAsc(InspectTempletItemDao.Properties.ITEM_SORT)
                            .orderAsc(InspectTempletItemDao.Properties.ITEM_ID).list();
                    JSONArray models = new JSONArray();
                    for (InspectTempletItem model : dicParList) {

                        List<InspectTempletItem> dicSubList = dicDao.queryBuilder().where(InspectTempletItemDao.Properties.ITEM_PARENT_ID.eq(model.getITEM_ID()))
                                .where(InspectTempletItemDao.Properties.ITEM_TYPE.eq("PM".equals(insrType) ? 2 : 1))
                                .orderAsc(InspectTempletItemDao.Properties.ITEM_SORT)
                                .orderAsc(InspectTempletItemDao.Properties.ITEM_ID)
                                .list();
                    }
                }
            }
        }
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

    @OnClick({R.id.iv_back, R.id.iv_people, R.id.iv_scan, R.id.iv_search, R.id.tv_search, R.id.fab_backToTop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_people:
                gotoAddPeople();
                break;
            case R.id.iv_scan:
                open2Scan();
                break;
            case R.id.iv_search:
                doSearch();
                break;
            case R.id.tv_search:
                doTextSearch();
                break;
            case R.id.fab_backToTop:
                backToTop();
                break;
        }
    }


    //添加巡检人员
    private void gotoAddPeople() {
        Intent intent = new Intent(this, LessUserActivity.class);
        intent.putExtra(USER_ID_LIST, userChooseIds);
        intent.putExtra("type", inspect);
        startActivityForResult(intent, Content.XJ_USER_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //对应的是巡检的巡检人员
                case Content.XJ_USER_REQUEST_CODE:
                    userChooseIds = data.getIntegerArrayListExtra(USER_ID_LIST);
                    userChooseNames = data.getStringArrayListExtra(USER_NAME_LIST);
                    break;
                case INSPECT_DETAIL_REQUESET_CODE:
                    for (int i = 0; i < mTitleList.size(); i++) {
                        queryAllCount(i, 0, 10);
                    }
                    break;


            }
        }


    }

    //返回到最上方
    private void backToTop() {
        mViewList.get(CurrentWhich).scrollToPosition(0);
    }

    //文字搜索
    private void doTextSearch() {
        queryAllCount(CurrentWhich, 0, 10);
    }

    //弹框搜索
    private void doSearch() {
        AlertListDialogUtil.getInstance().showDialog("", mDaoSession, R.array.kpbh2, this, new AlertListDialogUtil.onGetResult() {
            @Override
            public void getResult(String result) {
                HashSet<String> set = new HashSet<>();
                set.add(result);
                doSaveListData(set);
            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }
}
