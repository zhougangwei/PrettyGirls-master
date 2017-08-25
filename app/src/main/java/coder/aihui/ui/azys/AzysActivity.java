package coder.aihui.ui.azys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import coder.aihui.data.normalbean.UpPicBean;
import coder.aihui.ui.inspectxj.InspectPagerAdapter;
import coder.aihui.ui.main.DownPresenter;
import coder.aihui.ui.main.DownView;
import coder.aihui.util.GsonUtil;
import coder.aihui.util.ListUtils;
import coder.aihui.util.ToastUtil;
import coder.aihui.util.UpPicClient;
import coder.aihui.util.viewutil.ProcessDialogUtil;
import coder.aihui.widget.MyArrayAdapter;
import coder.aihui.widget.MyProgressDialog;
import coder.aihui.widget.contact.LessUserActivity;
import coder.aihui.widget.popwindow.MenuPopup;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

import static coder.aihui.R.id.ll;
import static coder.aihui.app.MyApplication.daoSession;
import static coder.aihui.base.Content.AZYS_LIST_REQUEST_CODE;
import static coder.aihui.base.Content.AZYS_YSR_REQUEST_CODE;
import static coder.aihui.base.Content.USER_ID_LIST;
import static coder.aihui.base.Content.USER_NAME_LIST;
import static coder.aihui.ui.azys.AzysDetailActivity.CMZ;
import static coder.aihui.ui.azys.AzysDetailActivity.MPZ;
import static coder.aihui.ui.azys.AzysDetailActivity.QMZ;
import static coder.aihui.ui.azys.AzysDetailActivity.ZMZ;
import static coder.aihui.ui.azys.AzysDetailActivity.ZP_STRINGS;
import static coder.aihui.ui.main.DownPresenter.AZYS_DOWN;
import static coder.aihui.ui.main.DownPresenter.PUR_CONTRACT_PLAN_UP;


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
    /**
     * <string-array name="azys">
     * <item>科室</item>
     * <item>供应商名称</item>
     * <item>合同号</item>
     * <item>名称</item>
     * </string-array>
     */
    private String           mSearchTypeString;        //搜索的类型
    private MyProgressDialog mProgressDialog;
    private ArrayList<Integer> mUserIdList = new ArrayList<>();         //选择的验收人
    private ArrayList<String>              mUserNameList;
    private List<PUR_CONTRACT_PLAN_DETAIL> mUpList;             //待上传的数据
    private List<String>                   mUpPicList;          //待上传的照片


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

        mProgressDialog = ProcessDialogUtil.createNoCancelDialog("上传安装验收数据", this);

        mTvTitle.setText("安装验收");
        mUpDownList.add("上传数据");
        mUpDownList.add("清空数据");
        mSearchTypeString = AzysActivity.this.getResources().getStringArray(R.array.azys)[0];

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

    /**
     * 初始化选择器
     */
    private void initSpinner() {
        mDownPresenter = new DownPresenter(this, mDaoSession);
        mSearchAdapter = new MyArrayAdapter<String>(this, R.layout.mysimple_spinner_item, getResources().getStringArray(R.array.azys));
        mSearchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpSearch.setAdapter(mSearchAdapter);
        mSpSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSearchTypeString = AzysActivity.this.getResources().getStringArray(R.array.azys)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSearchTypeString = AzysActivity.this.getResources().getStringArray(R.array.azys)[0];
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

        refreshAllData();


    }


    //刷新所有的数据
    private void refreshAllData() {
        for (int i = 0; i < mLoadingList.size(); i++) {
            showSearch(mSearchTypeString, i, mLoadingList.get(i).list);
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
                    if (lastVisibleItem >= totalItemCount - 4 && dy > 0 && lastVisibleItem != totalItemCount - 1) {
                        if (!dataList.get(finalI).isLoading) {
                            dataList.get(finalI).isLoading = true;
                            showSearch(mSearchTypeString, CurrentWhich, dataList.get(finalI).list);
                            dataList.get(finalI).isLoading = false;
                        }
                    }
                }
            });
        }
    }


    private void showView(ViewHolder holder, final PUR_CONTRACT_PLAN bean) {
        //跳转到下一个页面
        holder.setOnClickListener(ll, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserIdList == null || mUserIdList.size() == 0) {
                    ToastUtil.showToast("请选择验收人!");
                    return;
                }
                Intent intent = new Intent(AzysActivity.this, AzysListActivity.class);
                intent.putExtra("htmxId", bean.getHTMX_ID());
                intent.putExtra(USER_ID_LIST, mUserIdList);
                intent.putExtra(USER_NAME_LIST, mUserNameList);
                startActivityForResult(intent, AZYS_LIST_REQUEST_CODE);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //列表
                case AZYS_LIST_REQUEST_CODE:
                    //刷新视图
                    refreshAllData();
                    break;
                case AZYS_YSR_REQUEST_CODE:
                    //已点击的 回显
                    mUserIdList = data.getIntegerArrayListExtra(USER_ID_LIST);
                    mUserNameList = data.getStringArrayListExtra(USER_NAME_LIST);
                    break;
            }
        }


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
        showSearch(mSearchTypeString, CurrentWhich, mLoadingList.get(CurrentWhich).list);
    }


    /**
     * @param searchId
     * @param type
     * @param searchList
     */
    private synchronized void showSearch(String searchId, final int type, final List searchList) {

        String s = mEtSearch.getText().toString();
        PUR_CONTRACT_PLANDao pur_contract_planDao = mDaoSession.getPUR_CONTRACT_PLANDao();
        QueryBuilder<PUR_CONTRACT_PLAN> qb = pur_contract_planDao.queryBuilder();


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
        qb.where(whereCondition)
                .where(propertyName.like("%" + s + "%"))
                .rx().list().doOnSubscribe(new Action0() {
            @Override
            public void call() {
                searchList.clear();
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<PUR_CONTRACT_PLAN>>() {
                    @Override
                    public void call(List<PUR_CONTRACT_PLAN> pur_contract_plen) {
                        searchList.addAll(pur_contract_plen);
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
                });
    }


    //选择人
    private void gotoChoosePeople() {
        Intent intent = new Intent(this, LessUserActivity.class);
        intent.putExtra("type", AZYS_DOWN);
        intent.putExtra(USER_ID_LIST, mUserIdList);
        startActivityForResult(intent, AZYS_YSR_REQUEST_CODE);
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

                    switch (string) {
                        case "清空数据":
                            clearAll();
                            break;
                        case "上传数据":
                            mProgressDialog.setMyMessage("上传数据");
                            mProgressDialog.show();
                            gotoUpdata();
                            break;
                        default:
                            break;
                    }
                    mUpdownPopup.dismiss();

                }
            });
        }
        mUpdownPopup.showPopupWindow(mIvUpdown);

    }

    //去上传数据
    private void gotoUpdata() {
        mUpList = mDaoSession.getPUR_CONTRACT_PLAN_DETAILDao().queryBuilder()
                .where((PUR_CONTRACT_PLAN_DETAILDao.Properties.CHECK_STATUS.eq(1)))
                .where(PUR_CONTRACT_PLAN_DETAILDao.Properties.IS_UP.eq(2))
                .orderAsc(PUR_CONTRACT_PLAN_DETAILDao.Properties.CONTRACT_ID)
                .list();
        gotoUpPic(mUpList);
    }

    /**
     * @param list 上传数据对象的集合
     */
    private void gotoUpPic(final List<PUR_CONTRACT_PLAN_DETAIL> list) {

        Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                for (int i = 0; i < ZP_STRINGS.length; i++) {
                    subscriber.onNext(getZp(list, ZP_STRINGS[1]));
                }
                subscriber.onCompleted();
            }
        }).filter(new Func1<List<String>, Boolean>() {
            @Override
            public Boolean call(List<String> strings) {
                return strings != null && strings.size() != 0;
            }
        }).doOnNext(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        mUpPicList.addAll(strings);
                    }
                })
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mProgressDialog.setMyMessage("上传图片");
                    }
                }).map(new Func1<List<String>, List<String>>() {
            @Override
            public List call(List<String> strings) {
                return mUpPicList;
            }
        }).filter(new Func1<List<String>, Boolean>() {
            @Override
            public Boolean call(List<String> strings) {
                return strings != null && strings.size() != 0;
            }
        }).distinctUntilChanged()                                   //不传重复的图片
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> s) {
                        upFile(s);
                    }
                });
    }


    private void upFile(List<String> s) {
        new UpPicClient.Builder()
                .setImgUrls(s)
                .setMessage("上传图片").setOnBackResult(new UpPicClient.OnBackResult() {
            @Override
            public void gotoSuccess(List<UpPicBean> recode) {
                //处理返回的照片的数据
                solvePicData(recode);
            }
            @Override
            public void gotoFinish() {
                ToastUtil.showToast("上传图片成功!");
                mProgressDialog.dismiss();
                gotoUpJson();
            }

            @Override
            public void goToFail(String str) {
                mProgressDialog.dismiss();
                ToastUtil.showToast(str + "");
            }

            @Override
            public void goToProgress(long totalBytes, long l) {
                mProgressDialog.setProgress((int) (totalBytes / l));
            }
        }).build().uploadImg("azys");
    }


    /**
     * 上传完成的Json
     */
    private void gotoUpJson() {
        HashMap<String, String> map = new HashMap<>();
        String json = getupJson();
        map.put("dataJson", json);
        mDownPresenter.gotoUp(map, PUR_CONTRACT_PLAN_UP);
    }

    /**
     * 处理返回码
     *
     * @param recode
     */
    private void solvePicData(List<UpPicBean> recode) {
        //有空改这个循环
        for (PUR_CONTRACT_PLAN_DETAIL detailBean : mUpList) {
            for (UpPicBean upPicBean : recode) {
                //正面照片
                if (upPicBean.getOldFileName().equals(detailBean.getZMZP_URL())) {
                    detailBean.setJQZP_FILE_ID(upPicBean.getFileId());
                }
                //侧面照片
                if (upPicBean.getOldFileName().equals(detailBean.getCMZP_URL())) {
                    detailBean.setJDSQ_FILE_ID(upPicBean.getFileId());
                }
                //铭牌照片
                if (upPicBean.getOldFileName().equals(detailBean.getMPZ_URL())) {
                    detailBean.setMPZ_FILE_ID(upPicBean.getFileId());
                }
                //签名
                if (upPicBean.getOldFileName().equals(detailBean.getKSQSR_FILE_URL())) {
                    detailBean.setKSQSR_FILE_ID(upPicBean.getFileId());
                }
            }
        }
    }

    private void clearAll() {
        //清空数据
        daoSession.getPUR_CONTRACT_PLAN_DETAILDao().deleteAll();
        daoSession.getPUR_CONTRACT_PLANDao().deleteAll();
        daoSession.getAZYS_MXDao().deleteAll();
        daoSession.getDHBeanDao().deleteAll();
        refreshAllData();
        ToastUtil.showToast("清空完成");
    }


    private List<String> getZp(List<PUR_CONTRACT_PLAN_DETAIL> list, final String type) {
        switch (type) {
            case ZMZ:
                return ListUtils.ListFiled2list(list, "getZMZP_URL", PUR_CONTRACT_PLAN_DETAIL.class);
            case CMZ:
                return ListUtils.ListFiled2list(list, "getCMZP_URL", PUR_CONTRACT_PLAN_DETAIL.class);
            case MPZ:
                return ListUtils.ListFiled2list(list, "getMPZ_URL", PUR_CONTRACT_PLAN_DETAIL.class);
            case QMZ:
                return ListUtils.ListFiled2list(list, "getKSQSR_FILE_URL", PUR_CONTRACT_PLAN_DETAIL.class);
            default:
                return null;
        }
    }


    private String getupJson() {
        if (mUpList == null || mUpList.size() == 0) {
            ToastUtil.showToast("没有数据需要上传!");
        }
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        for (int i = 0; i < mUpList.size(); i++) {
            //一个单号对应几个验收人
            mUpList.get(i).setYSSJ(new Date());
            mDaoSession.update(mUpList.get(i));
            treeMap.put(mUpList.get(i).getDH_ID(), mUpList.get(i).getYSR_IDS());
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

        String json = GsonUtil.parseListToJson(mUpList);

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


    /**
     * 获取数目
     *
     * @param type
     * @return
     */
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
                        mProgressDialog.dismiss();
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
                        mProgressDialog.dismiss();
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
                        mProgressDialog.setProgress(integer);
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
