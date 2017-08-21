package coder.aihui.ui.assetquery;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.data.bean.IN_ASSET;
import coder.aihui.data.bean.LoadingBean;
import coder.aihui.manager.DataUtil;
import coder.aihui.ui.assetcheck.AssetDetailActivity;
import coder.aihui.ui.assetcheck.AssetDetailEditActivity;
import coder.aihui.ui.assetcheck.AssetQueryConfigActivity;
import coder.aihui.ui.inspectxj.InspectPagerAdapter;
import coder.aihui.util.AndroidUtils;
import coder.aihui.util.Utils;
import coder.aihui.widget.AlertListDialogUtil;
import coder.aihui.widget.MyArrayAdapter;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static coder.aihui.app.MyApplication.daoSession;

public class AssetQueryActivity extends AppActivity implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.iv_scan)
    LinearLayout mIvScan;
    @BindView(R.id.iv_search)
    LinearLayout mIvSearch;
    @BindView(R.id.iv_config)
    LinearLayout mIvConfig;
    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.tv_title)
    TextView     mTvTitle;
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

    private String mDeptName;
    private String mDeptIds;
    private String mDlwzName;
    private String mDlwzIds;       //相当于All

    private String mAllDlwzIds = "";//所有的子地理位置

    private String mAllDeptIds = "";

    private String            mAllDeptName  = "";
    private String            mAllDlwzName  = "";
    private Integer           pdaType       = 1;        //1是rfid 2是红外 6是摄像头
    private ArrayList<String> mTitleList    = new ArrayList<>();
    private List<Fragment>    mFragmentList = new ArrayList<>();
    private InspectPagerAdapter mPagerAdapter;

    private int    mSearchType = 9;//"1">名称 "2">出厂 "3">规格 "4">RFID "5">二维码设备扫描 "6">二维码摄像头扫描"7">老资产编号"8">新资产编号 9是品牌名称规格的后搜索
    private String searchText  = "";

    private List<RecyclerView> mViewList    = new ArrayList<>();
    private List<IN_ASSET>     mAllList     = new ArrayList<>();
    private List<IN_ASSET>     mCorrectList = new ArrayList<>();
    private List<LoadingBean>  mDataList    = new ArrayList<>();
    private CommonAdapter<IN_ASSET> mAllAdapter;
    private CommonAdapter<IN_ASSET> mCorrectAdapter;
    private boolean isSearchSon = true;                     //查询子集

    private String whichType = "";  //搜索类型 分为总数 已清点 未清点 未确认      对应1,2,3,4
    private ArrayList              mLinshiDatas;
    private MyArrayAdapter<String> mSearchAdapter;
    /**
     * <string-array name="assetquerycheck">
     * <item>品牌|名称|规格</item>
     * <item>名称</item>
     * <item>新资产编号</item>
     * <item>老资产编号</item>
     * </string-array>
     */
    private String                 mSearchTypeString;
    private int                    CurrentWhich; //当前选择的是哪个


    @Override
    protected int getContentViewId() {
        return R.layout.activity_asset_query;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

    @Override
    protected void initView() {

        mTvTitle.setText("台账管理");
        mTitleList.add("全部");
        mTitleList.add("已修改");


        //默认第一个
        mSearchTypeString = AssetQueryActivity.this.getResources().getStringArray(R.array.assetquerycheck)[0];

        mDataList.add(new LoadingBean(mAllList, false));
        mDataList.add(new LoadingBean(mCorrectList, false));


        initSpinner();
        mPagerAdapter = new InspectPagerAdapter(mTitleList, mViewList, this);
        initRecycleView();
        mVp.setAdapter(mPagerAdapter);
        mTb.setupWithViewPager(mVp);
        mTb.addOnTabSelectedListener(this);

        initData();

    }

    private void initData() {
        for (int i = 0; i <mDataList.size() ; i++) {
            loadMeinv(i, mDataList.get(i).list.size(), 10);
        }
    }


    /**
     * 初始化Spinner数据
     */
    private void initSpinner() {
        mSearchAdapter = new MyArrayAdapter<String>(this, R.layout.mysimple_spinner_item, getResources().getStringArray(R.array.assetquerycheck));
        mSearchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpSearch.setAdapter(mSearchAdapter);

        mSpSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSearchTypeString = AssetQueryActivity.this.getResources().getStringArray(R.array.assetquerycheck)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSearchTypeString = AssetQueryActivity.this.getResources().getStringArray(R.array.assetquerycheck)[0];
            }
        });
    }


    /**
     * 初始化RecycleView
     */
    private void initRecycleView() {

        RecyclerView viewAll = (RecyclerView) View.inflate(this, R.layout.recycleview, null);
        RecyclerView viewCorrect = (RecyclerView) View.inflate(this, R.layout.recycleview, null);


        mViewList.add(viewAll);
        mViewList.add(viewCorrect);

        mTitleList.add("总计");
        mTitleList.add("计划");
        mTitleList.add("过期");

        mPagerAdapter.notifyDataSetChanged();
        //设置滚动加载监听,同时加载数据
        setScrollListener(mViewList, mDataList);
        mAllAdapter = new CommonAdapter<IN_ASSET>(this, R.layout.item_inspect, mAllList) {
            @Override
            protected void convert(ViewHolder holder, IN_ASSET o, int position) {
                showItemView(holder, o);
            }
        };
        mCorrectAdapter = new CommonAdapter<IN_ASSET>(this, R.layout.item_inspect, mCorrectList) {
            @Override
            protected void convert(ViewHolder holder, IN_ASSET o, int position) {
                showItemView(holder, o);
            }
        };
        viewAll.setAdapter(mAllAdapter);
        viewCorrect.setAdapter(mCorrectAdapter);

    }

    /**
     * @param holder 视图持有者
     * @param bean   数据持有者
     */
    private void showItemView(ViewHolder holder, final IN_ASSET bean) {
        holder.setText(R.id.tv_name, bean.getWZMC());               //物资名称
        holder.setText(R.id.tv_brand, bean.getPPMC());              //品牌名称
        holder.setText(R.id.tv_code, bean.getKPBH());               //卡片编号
        holder.setText(R.id.tv_old_code, bean.getKPBH_OLD());       //旧编号

        holder.setText(R.id.rb, bean.getKSMC());                    //科室
        //  holder.setChecked(R.id.rb, bean.getIsCheck());

        RadioButton mRb = holder.getView(R.id.rb);
        mRb.setChecked(bean.isFlagChoose());

        Utils.canCancelRadioButton(mRb, bean, null);
        //radiobutton 的状态保持

        //跳转到台账详情界面
        holder.setOnClickListener(R.id.ll_main, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssetQueryActivity.this, AssetDetailActivity.class);
                intent.putExtra("assetId", bean.getID());
                startActivity(intent);
            }
        });

        holder.setOnClickListener(R.id.tv_correct, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssetQueryActivity.this, AssetDetailEditActivity.class);
                intent.putExtra("assetId", bean.getID());
                startActivity(intent);
            }
        });

        holder.setText(R.id.tv_dept, bean.getKSMC());
        holder.setText(R.id.tv_location, bean.getDDMC());
    }

    @OnClick({R.id.iv_scan, R.id.iv_search, R.id.iv_config, R.id.iv_back, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                pdaType = 6;
                open2Scan();            //打开二维码
                break;
            case R.id.iv_search:
                gotoSearch();           //搜索
                break;
            case R.id.iv_config:
                openConfig();           //配置
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search:
                gotoTextSearch();       //文字搜索
                break;
        }
    }

    private void gotoTextSearch() {
        loadMeinv(CurrentWhich, mDataList.get(CurrentWhich).list.size(), 10);
    }


    //用来弹框的
    private void gotoSearch() {
        AlertListDialogUtil.getInstance().showDialog("", mDaoSession, R.array.kpbh2, this, new AlertListDialogUtil.onGetResult() {
            @Override
            public void getResult(String result) {
                HashSet<String> set = new HashSet<>();
                set.add(result);
                doSaveListData(set);
            }
        });
    }

    private void openConfig() {
        Intent intent = new Intent(this, AssetQueryConfigActivity.class);
        startActivityForResult(intent, Content.REQUEST_CONFIG);
    }

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
                    if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
                        if (!dataList.get(finalI).isLoading) {
                            dataList.get(finalI).isLoading = true;
                            loadMeinv(finalI, dataList.get(finalI).list.size(), 10);//这里多线程也要手动控制isLoadingMore
                            dataList.get(finalI).isLoading = false;
                        }
                    }
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Content.REQUEST_CONFIG) {
            if (resultCode == RESULT_OK) {
                mDeptName = data.getStringExtra("mDeptName");
                mDeptIds = data.getStringExtra("mDeptIds");
                mDlwzName = data.getStringExtra("mDlwzName");
                mDlwzIds = data.getStringExtra("mDlwzIds");

                mAllDeptName = data.getStringExtra("mAllDeptName");
                mAllDeptIds = data.getStringExtra("mAllDeptIds");
                mAllDlwzIds = data.getStringExtra("mAllDlwzIds");
                mAllDlwzName = data.getStringExtra("mAllDlwzName");
            }
        }
    }


    /**
     * @param which 类别 是搜索全部还是已修改的数据
     * @param sql   sql语句
     * @param par   参数
     */
    private synchronized void querySummaryInAsset(final int which, String sql, String[] par) {

        DataUtil.getDatas(mDaoSession, sql, par)
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribe(new Action1<List<IN_ASSET>>() {
                    @Override
                    public void call(List<IN_ASSET> listDatas) {
                        mDataList.get(which).list.addAll(listDatas);
                        switch (which) {
                            case 0:
                                mAllAdapter.notifyDataSetChanged();
                                break;
                            case 1:
                                mCorrectAdapter.notifyDataSetChanged();
                                break;
                        }
                    }
                });
    }


    /**
     * @param which 是搜索全部还是已修改的数据
     * @param start 起始位子
     * @param count 数量 默认一次十个
     */
    public void loadMeinv(final int which, final int start, final int count) {

        //   mSearchType = 9;//"1">名称 "2">出厂 "3">规格 "4">RFID "5">二维码设备扫描 "6">二维码摄像头扫描"7">老资产编号"8">新资产编号 9是品牌名称规格的后搜索

        switch (mSearchTypeString) {
            case "品牌\\|名称\\|规格":
                mSearchType = 9;
                break;
            case "名称":
                mSearchType = 1;
                break;
            case "新资产编号":
                mSearchType = 8;
                break;
            case "老资产编号":
                mSearchType = 7;
                break;
        }

        int i_start = start;
        int i_count = count;

        //增加侧边条件判断
        String qcid = AndroidUtils.getMaxPcid(daoSession);// 取最大的盘点期次，这个只要取一次就好，不用每次数据库。
        List<String> parList = new ArrayList<String>();

        String where = "";
        if (mSearchType != 4 && mSearchType != 5 && mSearchType != 6) {
            if (!TextUtils.isEmpty(mDeptIds) && !TextUtils.isEmpty(mDlwzIds)) {
                if (isSearchSon) {
                    where = " where ( a.KSID in " + mAllDeptIds + " or ( a.KSID is null or a.KSID = ''))" + " and ( a.DDID in " + mAllDlwzIds + " or ( a.DDID is null or a.DDID = '')) and ((a.DDID is  not null and a.DDID <> '' ) or ( a.KSID is not null and a.KSID <> '')) ";
                } else {
                    where = " where a.KSID in " + mAllDeptIds + " and a.DDID in " + mAllDlwzIds;
                }
            } else if (!TextUtils.isEmpty(mDeptIds) && TextUtils.isEmpty(mDlwzIds)) {

                if (isSearchSon) {
                    where = " where a.KSID in " + mAllDeptIds + " and ( a.DDID IS NULL or  a.DDID = '' ) ";
                } else {
                    where = " where a.KSID in " + mAllDeptIds;
                }
            } else if (!TextUtils.isEmpty(mDlwzIds) && TextUtils.isEmpty(mDeptIds)) {
                //看是否查询子集
                if (isSearchSon) {
                    where = " where a.DDID in " + mAllDlwzIds;
                } else {
                    where = " where a.DDID in " + mAllDlwzIds + " and ( a.KSID IS NULL  or  a.KSID = '' ) ";
                }
            } else if (TextUtils.isEmpty(mDlwzIds) && TextUtils.isEmpty(mDeptIds) && !isSearchSon) {
                where = " where ( a.DDID IS NULL  a.DDID = '' )   and ( a.KSID IS NULL or a.KSID = '' ) ";
            }
        }
        StringBuffer sql = new StringBuffer();
        sql.append("select a.ID as ID, a.WZMC as WZMC, a.GGXH as GGXH, a.PPMC as PPMC, a.SCBH as SCBH, a.KPBH as KPBH,a.KPBH__OLD as KPBH_OLD, a.BAR__CODE as BAR, a.KSMC as KSMC, a.DDMC as DDMC,ic_selected.DQDDMC as DQDDMC");
        sql.append(",case when ic_selected.ASSET__ID is not null and (ic_selected.IS__CHANGE__DD != 1 or ic_selected.IS__CHANGE__DD is null) and ic_selected.IS__CHANGE=1 then 4 " +
                "when ic_selected.ASSET__ID is not null and (ic_selected.IS__CHANGE != 1 or ic_selected.IS__CHANGE is null) and ic_selected.IS__CHANGE__DD=1 then 3 " +
                "when ic_selected.ASSET__ID is not null and ic_selected.IS__CHANGE=1 and ic_selected.IS__CHANGE__DD=1 then 5 " +
                "when ic_selected.ASSET__ID is not null then 1 " +
                "else 2 " +
                "end as PDAID");
        String aTab = "IN__ASSET a ";           //a表
        String bTab = "PDA__ASSET__CHECK ic_selected ";           //b表

        StringBuffer sql_count = new StringBuffer();

        //如果是跳转查询的话 就交换顺序
        if (!TextUtils.isEmpty(whichType)) {
            if ("2".equals(whichType)) {
                String tabc;
                tabc = aTab;
                aTab = bTab;
                bTab = tabc;
            }
        }

        sql.append(" from " + aTab + " left join " + bTab + "  on a.ID=ic_selected.ASSET__ID and ic_selected.QCPC=" + qcid);
        sql_count.append("select count(*) from " + aTab + " left join " + bTab + " on a.ID=ic_selected.ASSET__ID and ic_selected.QCPC=" + qcid);
        if (which == 1) {               //全部
            sql.append(" inner join CORRECT__ASSET c on a.ID = c.ID ");
            sql_count.append(" inner join CORRECT__ASSET c on a.ID = c.ID ");
        } else if (which == 0) {        //修改
        }

        //如果是RFID扫描，则跳过科室地理位置条件
        if (mSearchType == 4 || mSearchType == 5 || mSearchType == 6) {
            where = "";
        } else {
            sql.append(where);
            sql_count.append(where);
        }

        if (searchText != null && !"".equals(searchText)) {
            if (!"".equals(where)) {
                sql.append(" and ");
                sql_count.append(" and ");
            } else {
                sql.append(" where ");
                sql_count.append(" where ");
            }
            //"1">名称 "2">出厂 "3">规格 "4">RFID
            if (mSearchType == 1) {
                sql.append(" a.WZMC like ?");
                sql_count.append(" a.WZMC like ?");
                parList.add("%" + searchText + "%");
            } else if (mSearchType == 2) {
                sql.append(" a.SCBH like ?");
                sql_count.append(" a.SCBH like ?");
                parList.add("%" + searchText + "%");
            } else if (mSearchType == 7) {        //老资产编号
                sql.append(" a.KPBH__OLD like ?");
                sql_count.append(" a.KPBH__OLD like ?");
                parList.add("%" + searchText + "%");
            } else if (mSearchType == 8) {    //新编号
                sql.append(" a.RFID__CODE like ?");
                sql_count.append(" a.RFID__CODE like ?");
                parList.add("%" + searchText + "%");
            } else if (mSearchType == 3) {
                sql.append(" a.GGXH like ?");
                sql_count.append(" a.GGXH like ?");
                parList.add("%" + searchText + "%");
            } else if (mSearchType == 4) {
                sql.append(" a.RFID__CODE in( " + searchText + " )");
                sql_count.append(" a.RFID__CODE in( " + searchText + " )");
            } else if (mSearchType == 5) {
                sql.append(" a.BAR__CODE in( " + searchText + " )");
                sql_count.append(" a.BAR__CODE in( " + searchText + " )");
            } else if (mSearchType == 6) {
                sql.append(" a.BAR__CODE in( " + searchText + " )");
                sql_count.append(" a.BAR__CODE in( " + searchText + " )");
            } else if (mSearchType == 9) {
                sql.append(" a.WZMC like ? or a.GGXH like ? or a.PPMC like ? ");
                sql_count.append(" a.WZMC like ? or a.GGXH like ? or a.PPMC like ? ");
                parList.add("%" + searchText + "%");
                parList.add("%" + searchText + "%");
                parList.add("%" + searchText + "%");
            }
        }

        if (!TextUtils.isEmpty(whichType)) {
            if ("3".equals(whichType)) {
                sql.append(" and a.QRBZ =  1 ");
                sql_count.append(" and a.QRBZ =  1 ");

            } else if ("4".equals(whichType)) {
                //and ( a.ID not in (select ASSET__ID from PDA__ASSET__CHECK))
                sql.append(" and ( a.QRBZ =  0  or a.QRBZ is null ) and ( a.ID not in (select ASSET__ID from PDA__ASSET__CHECK)) ");
                sql_count.append(" and ( a.QRBZ =  0 or a.QRBZ is null ) and ( a.ID not in (select ASSET__ID from PDA__ASSET__CHECK ))  ");
            }
        }
        sql.append(" order by PDAID desc,ID");
        sql.append(" limit ? offset ?");
        parList.add(i_count + "");
       parList.add(i_start + "");


        //搜素数据的方法  以上就是拼sql
        querySummaryInAsset(which, sql.toString(), (String[]) parList.toArray(new String[parList.size()]));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        CurrentWhich = tab.getPosition();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
