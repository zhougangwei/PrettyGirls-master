package coder.aihui.ui.azys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.data.bean.LoadingBean;
import coder.aihui.data.bean.PUR_CONTRACT_PLAN;
import coder.aihui.data.bean.PUR_CONTRACT_PLAN_DETAIL;
import coder.aihui.data.bean.gen.PUR_CONTRACT_PLANDao;
import coder.aihui.data.bean.gen.PUR_CONTRACT_PLAN_DETAILDao;
import coder.aihui.ui.inspectxj.InspectPagerAdapter;
import coder.aihui.ui.main.DownPresenter;
import coder.aihui.ui.main.DownView;
import coder.aihui.util.GsonUtil;
import coder.aihui.util.ToastUtil;
import coder.aihui.widget.MyArrayAdapter;
import coder.aihui.widget.contact.LessUserActivity;
import coder.aihui.widget.popwindow.MenuPopup;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class AzysActivity extends AppActivity implements DownView, TabLayout.OnTabSelectedListener {

    @BindView(R.id.iv_updown)
    LinearLayout mIvUpdown;
    @BindView(R.id.iv_config)
    LinearLayout mIvConfig;
    @BindView(R.id.iv_people)
    LinearLayout mIvPeople;
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
    @BindView(R.id.tb)
    TabLayout    mTb;

    @BindView(R.id.back_top)
    FloatingActionButton mBackTop;
    @BindView(R.id.vp)
    ViewPager            mVp;

    private List<String> mUpDownList = new ArrayList<>();//上传下载按钮的数据填充
    private MenuPopup mUpdownPopup;
    private List<String> mPzList = new ArrayList<>();//配置按钮的数据填充
    private MenuPopup mPzPopup;

    //列表的数据

    private CommonAdapter<PUR_CONTRACT_PLAN> mAllAdapter;
    private CommonAdapter<PUR_CONTRACT_PLAN> mCheckedAdapter;
    private CommonAdapter<PUR_CONTRACT_PLAN> mUnCheckedAdapter;

    private final static int ALL       = 1;
    private final static int CHECKED   = 2;
    private final static int UNCHECKED = 3;
    private DownPresenter          mDownPresenter;
    private MyArrayAdapter<String> mSearchAdapter;

    private List<String>       mTitleList = new ArrayList<>();          //标题集合
    private List<RecyclerView> mViewList  = new ArrayList<>();          //视图集合
    private InspectPagerAdapter mPagerAdapter;
    private int                 CurrentWhich;                   //当前选择的是哪个
    private List<LoadingBean>       mLoadingList = new ArrayList<>();   //装载了数据和是否在加载
    private List<PUR_CONTRACT_PLAN> mAllList     = new ArrayList<>();
    private List<PUR_CONTRACT_PLAN> mCheckList   = new ArrayList<>();
    private List<PUR_CONTRACT_PLAN> mUnCheckList = new ArrayList<>();
    private String mSearchTypeText;        //搜索的类型

    @Override
    protected int getContentViewId() {
        return R.layout.activity_azys;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }


    @Override
    protected void initView() {
        mTvTitle.setText("安装验收");
        mUpDownList.add("上传数据");
        mSearchTypeText = AzysActivity.this.getResources().getStringArray(R.array.azys)[0];

        mLoadingList.add(new LoadingBean(mAllList, false));
        mLoadingList.add(new LoadingBean(mCheckList, false));
        mLoadingList.add(new LoadingBean(mUnCheckList, false));


        mPagerAdapter = new InspectPagerAdapter(mTitleList, mViewList, this);
        mVp.setAdapter(mPagerAdapter);

        mTb.setupWithViewPager(mVp);
        mTb.addOnTabSelectedListener(this);


        initRecycleView();

        initSpinner();


    }

    private void initSpinner() {
        mDownPresenter = new DownPresenter(this, mDaoSession);
        mSearchAdapter = new MyArrayAdapter<String>(this, R.layout.mysimple_spinner_item, getResources().getStringArray(R.array.azys));
        mSearchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpSearch.setAdapter(mSearchAdapter);
        mSpSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSearchTypeText = AzysActivity.this.getResources().getStringArray(R.array.azys)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSearchTypeText = AzysActivity.this.getResources().getStringArray(R.array.azys)[0];
            }
        });
    }


    private void initRecycleView() {
        RecyclerView viewAll = (RecyclerView) View.inflate(this, R.layout.recycleview, null);
        RecyclerView viewChecked = (RecyclerView) View.inflate(this, R.layout.recycleview, null);
        RecyclerView viewUnchecked = (RecyclerView) View.inflate(this, R.layout.recycleview, null);

        mViewList.add(viewAll);
        mViewList.add(viewChecked);
        mViewList.add(viewUnchecked);

        mTitleList.add("全部");
        mTitleList.add("已验");
        mTitleList.add("未验");

        mPagerAdapter.notifyDataSetChanged();
        setScrollListener(mViewList, mLoadingList);

        mAllAdapter = new CommonAdapter<PUR_CONTRACT_PLAN>(this, R.layout.item_azys_plan, mAllList) {
            @Override
            protected void convert(ViewHolder holder, final PUR_CONTRACT_PLAN bean, int position) {
                //显示视图渲染
                showView(holder, bean);
            }
        };

        mCheckedAdapter = new CommonAdapter<PUR_CONTRACT_PLAN>(this, R.layout.item_azys_plan, mCheckList) {
            @Override
            protected void convert(ViewHolder holder, final PUR_CONTRACT_PLAN bean, int position) {
                //显示视图渲染
                showView(holder, bean);
            }
        };
        mUnCheckedAdapter = new CommonAdapter<PUR_CONTRACT_PLAN>(this, R.layout.item_azys_plan, mUnCheckList) {
            @Override
            protected void convert(ViewHolder holder, final PUR_CONTRACT_PLAN bean, int position) {
                //显示视图渲染
                showView(holder, bean);
            }
        };

        viewAll.setAdapter(mAllAdapter);
        viewChecked.setAdapter(mCheckedAdapter);
        viewUnchecked.setAdapter(mUnCheckedAdapter);


        for (int i = 0; i < mLoadingList.size(); i++) {
            showSearch(mSearchTypeText, i, mLoadingList.get(i).list);
        }


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
                            showSearch(mSearchTypeText, CurrentWhich, dataList.get(finalI).list);
                            dataList.get(finalI).isLoading = false;
                        }
                    }
                }
            });
        }
    }


    private void showView(ViewHolder holder, final PUR_CONTRACT_PLAN bean) {
        holder.setOnClickListener(R.id.ll, new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AzysActivity.this, AzysListActivity.class);
                intent.putExtra("htmxId", bean.getHTMX_ID());
                startActivity(intent);
            }
        });

        holder.setText(R.id.tv_name, bean.getWZMC());
        holder.setText(R.id.tv_htbh, bean.getCONTRACT_NUM());
        holder.setText(R.id.tv_gysmc, bean.getGYSMC());
        holder.setText(R.id.tv_dept, bean.getDEPT_NAME());

        int yssl = bean.getYSSL() == null ? 0 : bean.getYSSL();
        int check_sl = bean.getCHECK_SL() == null ? 0 : bean.getCHECK_SL();
        int uncheck_sl = yssl - check_sl;
        holder.setText(R.id.tv_all, "总数(" + yssl + ")");
        holder.setText(R.id.tv_check, "已验(" + check_sl + ")");
        holder.setText(R.id.tv_uncheck, "未验(" + uncheck_sl + ")");

    }

    @OnClick({R.id.iv_updown, R.id.iv_config, R.id.iv_people, R.id.iv_back, R.id.tv_search, R.id.back_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_updown:
                gotoUpdown();           //上传下载
                break;
            case R.id.iv_config:
                gotoConfig();           //配置
                break;
            case R.id.iv_people:
                gotoChoosePeople();     //选择安装验收人
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search:
                doTextSearch();         //文字搜索
                break;
            case R.id.back_top:
                backToTop();
                break;
        }
    }

    //返回到顶端
    private void backToTop() {
        mViewList.get(CurrentWhich).scrollToPosition(0);
    }


    //文字搜索
    private void doTextSearch() {
        showSearch(mSearchTypeText, CurrentWhich, mLoadingList.get(CurrentWhich).list);
    }


    private void showSearch(String searchId, int type, List searchList) {

        String s = mEtSearch.getText().toString();
        PUR_CONTRACT_PLANDao pur_contract_planDao = mDaoSession.getPUR_CONTRACT_PLANDao();
        QueryBuilder<PUR_CONTRACT_PLAN> qb = pur_contract_planDao.queryBuilder();
        List<PUR_CONTRACT_PLAN> pur_contract_plen = pur_contract_planDao.loadAll();
        Log.d("AzysActivity", "pur_contract_plen.size():" + pur_contract_plen.size());
        List<PUR_CONTRACT_PLAN> list = new ArrayList<PUR_CONTRACT_PLAN>();

        Property propertyName = null;
        switch (searchId) {
            case "科室":
                propertyName = PUR_CONTRACT_PLANDao.Properties.DEPT_NAME;
                break;
            case "供应商名称":
                propertyName = PUR_CONTRACT_PLANDao.Properties.GYSMC;
                break;
            case "名称":
                propertyName = PUR_CONTRACT_PLANDao.Properties.WZMC;
                break;
            case "合同号":
                propertyName = PUR_CONTRACT_PLANDao.Properties.CONTRACT_NUM;
                break;
        }


        WhereCondition whereCondition = null;
        switch (type) {
            case 0:
                whereCondition = qb.or(PUR_CONTRACT_PLANDao.Properties.CHECK_STATUS.in(1, 2), PUR_CONTRACT_PLANDao.Properties.CHECK_STATUS.isNull());
                break;
            case 1:
                whereCondition = PUR_CONTRACT_PLANDao.Properties.CHECK_STATUS.in(1);
                break;
            case 2:
                whereCondition = qb.or(PUR_CONTRACT_PLANDao.Properties.CHECK_STATUS.in(2), PUR_CONTRACT_PLANDao.Properties.CHECK_STATUS.isNull());
                break;

        }

        list = qb.where(whereCondition)
                .where(propertyName.like("%" + s + "%"))
                .list();

        searchList.clear();
        searchList.addAll(list);
        switch (type) {
            case 0:
                mAllAdapter.notifyDataSetChanged();
                break;
            case 1:
                mCheckedAdapter.notifyDataSetChanged();
                break;
            case 2:
                mUnCheckedAdapter.notifyDataSetChanged();
                break;
        }


    }


    //选择人
    private void gotoChoosePeople() {
        Intent intent = new Intent(this, LessUserActivity.class);
        startActivity(intent);
    }

    //配置
    private void gotoConfig() {
        Intent intent = new Intent(this, AzysConfigActivity.class);
        intent.putExtra("where", Content.AZYS_CONFIG);
        startActivity(intent);
    }


    private void gotoUpdown() {
        if (mUpdownPopup == null) {
            mUpdownPopup = new MenuPopup(this, mUpDownList, new MenuPopup.BackReslut() {
                @Override
                public void onBackResult(String string) {
                    if ("上传数据".equals(string)) {
                        gotoUpdata();
                    }
                }
            });
        }
        mUpdownPopup.showPopupWindow(mIvUpdown);

    }

    //去上传数据
    private void gotoUpdata() {
        HashMap<String, String> map = new HashMap<>();
        String json = getupJson();
        map.put("dataJson", json);
        mDownPresenter.gotoUp(map);

    }

    private String getupJson() {
        List<PUR_CONTRACT_PLAN_DETAIL> ll = mDaoSession.getPUR_CONTRACT_PLAN_DETAILDao().queryBuilder()
                .where((PUR_CONTRACT_PLAN_DETAILDao.Properties.CHECK_STATUS.eq(1)))
                .where(PUR_CONTRACT_PLAN_DETAILDao.Properties.IS_UP.eq(2))
                .orderAsc(PUR_CONTRACT_PLAN_DETAILDao.Properties.CONTRACT_ID)
                .list();


        if (ll == null || ll.size() == 0) {
            ToastUtil.showToast("没有数据需要上传!");
        }

        TreeMap<String, String> treeMap = new TreeMap<String, String>();

        for (int i = 0; i < ll.size(); i++) {

            //一个单号对应几个验收人
            ll.get(i).setYSSJ(new Date());

            mDaoSession.update(ll.get(i));
            treeMap.put(ll.get(i).getDH_ID(), ll.get(i).getYSR_IDS());


        }


        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();


        Iterator ite = treeMap.keySet().iterator();
        while (ite.hasNext()) {
            String string = (String) ite.next();
            sb1.append(string).append(",");
            sb2.append(treeMap.get(string)).append("|");
        }

        String string = sb1.toString();
        //切掉最后一个
        String substring = string.substring(0, string.length() - 1);        //单号

        String string2 = sb2.toString();
        //切掉最后一个
        String substring2 = string2.substring(0, string2.length() - 1);        //验收人


        //String url = "";


        String json = GsonUtil.parseListToJson(ll);

        String substring1 = json.substring(1);

        StringBuilder sb = new StringBuilder();


        // [{"TYPE":"81-1"},{"ALQJ":1,"CCBH":"1993-22-11","CHECK_ISCHECKED":false,"CHECK_STATUS":1,"CMZP_URL":"/storage/emulated/0/DCIM/IMG-675398161.jpg","CONTRACT_ID":81,"CONTRACT_NUM":"H0000081","DEPT_ID":1,"DEPT_NAME":"所有科室","DH_ID":"81-1","DQAQJC":1,"GGXH":"Thinkpad T420","GYSMC":"北京健峰生物技术有限公司","HTMX_ID":81,"HWQD":0,"IS_UP":2,"JDSQ_FILE_ID":1,"JLZM":1,"JQZP_FILE_ID":1,"JYSYNX":1.5,"MCGGID":585,"PLAN_ID":31,"PUR_YSR_ID":-1,"QSDQSJ":"1994-10-11 00:00:00","WGJC":0,"WZMC":"笔记本电脑","YQGZQK":0,"YSSJ":"2016-12-20 09:49:27","YSSL":1,"ZCZH":"1993-22-11","ZMZP_URL":"/storage/emulated/0/DCIM/100MEDIA/IMAG0484.jpg","_ID":3},{"ALQJ":1,"CCBH":"1993-22-11","CHECK_ISCHECKED":false,"CHECK_STATUS":1,"CMZP_URL":"/storage/emulated/0/DCIM/IMG-675398161.jpg","CONTRACT_ID":81,"CONTRACT_NUM":"H0000081","DEPT_ID":1,"DEPT_NAME":"所有科室","DH_ID":"81-1","DQAQJC":1,"GGXH":"Thinkpad T420","GYSMC":"北京健峰生物技术有限公司","HTMX_ID":81,"HWQD":0,"IS_UP":2,"JDSQ_FILE_ID":1,"JLZM":1,"JQZP_FILE_ID":1,"JYSYNX":1.5,"MCGGID":585,"PLAN_ID":31,"PUR_YSR_ID":-1,"QSDQSJ":"1994-10-11 00:00:00","WGJC":0,"WZMC":"笔记本电脑","YQGZQK":0,"YSSJ":"2016-12-20 09:49:27","YSSL":1,"ZCZH":"1993-22-11","ZMZP_URL":"/storage/emulated/0/DCIM/100MEDIA/IMAG0484.jpg","_ID":4},{"ALQJ":1,"CCBH":"1993-22-11","CHECK_ISCHECKED":false,"CHECK_STATUS":1,"CMZP_URL":"/storage/emulated/0/DCIM/IMG-675398161.jpg","CONTRACT_ID":81,"CONTRACT_NUM":"H0000081","DEPT_ID":1,"DEPT_NAME":"所有科室","DH_ID":"81-1","DQAQJC":1,"GGXH":"Thinkpad T420","GYSMC":"北京健峰生物技术有限公司","HTMX_ID":81,"HWQD":0,"IS_UP":2,"JDSQ_FILE_ID":1,"JLZM":1,"JQZP_FILE_ID":1,"JYSYNX":1.5,"MCGGID":585,"PLAN_ID":31,"PUR_YSR_ID":-1,"QSDQSJ":"1994-10-11 00:00:00","WGJC":0,"WZMC":"笔记本电脑","YQGZQK":0,"YSSJ":"2016-12-20 09:49:27","YSSL":1,"ZCZH":"1993-22-11","ZMZP_URL":"/storage/emulated/0/DCIM/100MEDIA/IMAG0484.jpg","_ID":5},{"ALQJ":1,"CCBH":"1993-22-11","CHECK_ISCHECKED":false,"CHECK_STATUS":1,"CMZP_URL":"/storage/emulated/0/DCIM/IMG-675398161.jpg","CONTRACT_ID":81,"CONTRACT_NUM":"H0000081","DEPT_ID":1,"DEPT_NAME":"所有科室","DH_ID":"81-1","DQAQJC":1,"GGXH":"Thinkpad T420","GYSMC":"北京健峰生物技术有限公司","HTMX_ID":81,"HWQD":0,"IS_UP":2,"JDSQ_FILE_ID":1,"JLZM":1,"JQZP_FILE_ID":1,"JYSYNX":1.5,"MCGGID":585,"PLAN_ID":31,"PUR_YSR_ID":-1,"QSDQSJ":"1994-10-11 00:00:00","WGJC":0,"WZMC":"笔记本电脑","YQGZQK":0,"YSSJ":"2016-12-20 09:49:27","YSSL":1,"ZCZH":"1993-22-11","ZMZP_URL":"/storage/emulated/0/DCIM/100MEDIA/IMAG0484.jpg","_ID":6},{"ALQJ":1,"CCBH":"1993-22-11","CHECK_ISCHECKED":false,"CHECK_STATUS":1,"CMZP_URL":"/storage/emulated/0/DCIM/IMG-675398161.jpg","CONTRACT_ID":81,"CONTRACT_NUM":"H0000081","DEPT_ID":1,"DEPT_NAME":"所有科室","DH_ID":"81-1","DQAQJC":1,"GGXH":"Thinkpad T420","GYSMC":"北京健峰生物技术有限公司","HTMX_ID":81,"HWQD":0,"IS_UP":2,"JDSQ_FILE_ID":1,"JLZM":1,"JQZP_FILE_ID":1,"JYSYNX":1.5,"MCGGID":585,"PLAN_ID":31,"PUR_YSR_ID":-1,"QSDQSJ":"1994-10-11 00:00:00","WGJC":0,"WZMC":"笔记本电脑","YQGZQK":0,"YSSJ":"2016-12-20 09:49:27","YSSL":1,"ZCZH":"1993-22-11","ZMZP_URL":"/storage/emulated/0/DCIM/100MEDIA/IMAG0484.jpg","_ID":7}]
        // 231231
        String finaljson = sb.append("[{\"TYPE\":\"")
                .append(substring)                      //单号
                .append("\"},")
                .append("{\"YSR_IDS\":\"")
                .append(substring2)
                .append("\"},")
                .append(substring1).toString();//大的json数据
        return finaljson;
    }

    //配置
    private void gotoPz() {


/*        if (mPzPopup == null) {
            mPzPopup = new MenuPopup(this, mPzList);
        }
        mPzPopup.showPopupWindow(mIvPz);*/

    }

    public Long getAccount(int type) {
        QueryBuilder<PUR_CONTRACT_PLAN> qb = mDaoSession.getPUR_CONTRACT_PLANDao().queryBuilder();
        switch (type) {
            case ALL:
                return qb.where(qb.or(PUR_CONTRACT_PLANDao.Properties.CHECK_STATUS.eq(1), PUR_CONTRACT_PLANDao.Properties.CHECK_STATUS.isNull()))
                        .orderAsc(PUR_CONTRACT_PLANDao.Properties.CONTRACT_ID)
                        .count();

            case CHECKED:
                return qb.where(PUR_CONTRACT_PLANDao.Properties.CHECK_STATUS.eq(1))
                        .orderAsc(PUR_CONTRACT_PLANDao.Properties.CONTRACT_ID)
                        .count();

            case UNCHECKED:
                return qb.where(PUR_CONTRACT_PLANDao.Properties.CHECK_STATUS.isNull())
                        .orderAsc(PUR_CONTRACT_PLANDao.Properties.CONTRACT_ID)
                        .count();

        }
        return 0L;
    }

    @Override
    public void showSuccess(int type) {
        Observable.just(type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        ToastUtil.showToast("上传成功!");
                    }
                });
    }

    @Override
    public void showFault(int type, String wrong) {
        Observable.just(type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        ToastUtil.showToast("上传失败!");
                    }
                });
    }

    @Override
    public void showProgress(int num, int type) {
        Observable.just(num)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        //进度显示多少

                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
