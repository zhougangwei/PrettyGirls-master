package coder.aihui.ui.assetcheck;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.data.bean.INSPECT_GROUP;
import coder.aihui.data.bean.IN_ASSET;
import coder.aihui.data.bean.PDA_ASSET_CHECK;
import coder.aihui.data.bean.PUB_DICTIONARY_ITEM;
import coder.aihui.data.bean.REPAIR_PLACE;
import coder.aihui.data.bean.gen.INSPECT_GROUPDao;
import coder.aihui.data.bean.gen.IN_ASSETDao;
import coder.aihui.data.bean.gen.PDA_ASSET_CHECKDao;
import coder.aihui.data.bean.gen.PUB_DICTIONARY_ITEMDao;
import coder.aihui.data.bean.gen.REPAIR_PLACEDao;
import coder.aihui.manager.DataUtil;
import coder.aihui.util.AndroidUtils;
import coder.aihui.util.SPUtil;
import coder.aihui.util.ToastUtil;
import coder.aihui.util.Utils;
import coder.aihui.widget.AlertListDialogUtil;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static coder.aihui.R.id.iv_back;


public class AssetCheckActivity extends AppActivity implements AssetQueryView {


    @BindView(R.id.linearLayout)
    LinearLayout mLinearLayout;
    @BindView(R.id.iv_scan)
    LinearLayout mIvScan;
    @BindView(R.id.iv_search)
    LinearLayout mIvSearch;
    @BindView(R.id.iv_config)
    LinearLayout mIvConfig;
    @BindView(iv_back)
    ImageView    mIvBack;
    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.tv_dlwz)
    TextView     mTvDlwz;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.rb_all)
    RadioButton  mRbAll;
    @BindView(R.id.pb)
    ProgressBar  mPb;
    @BindView(R.id.tv_yd)
    TextView     mTvYd;
    @BindView(R.id.tv_wd)
    TextView     mTvWd;
    @BindView(R.id.tv_bd)
    TextView     mTvBd;
    @BindView(R.id.tv_zk)
    TextView     mTvZk;
    @BindView(R.id.linearLayout2)
    LinearLayout mLinearLayout2;
    @BindView(R.id.iv_devide)
    ImageView    mIvDevide;
    @BindView(R.id.tv_all)
    TextView     mTvAll;
    @BindView(R.id.tv_yqd)
    TextView     mTvYqd;
    @BindView(R.id.tv_wqd)
    TextView     mTvWqd;

    @BindView(R.id.tv_yzk)
    TextView mTvYzk;

    @BindView(R.id.tv_lsyd)
    TextView mTvLsyd;


    private String mDeptName;
    private String mDeptIds;
    private String mDlwzName;
    private String mDlwzIds;       //相当于All

    private String mAllDlwzIds = "";//所有的子地理位置

    private String mAllDeptIds = "";

    private String mAllDeptName = "";
    private String mAllDlwzName = "";

    private CommonAdapter<IN_ASSET> mMainAdapter;

    private String searchState = "234";//默认

    ArrayList<String> searchList = new ArrayList();

    private String qcid;           //期次Id
    private Integer pdaType = 1;        //1是rfid 2是红外 6是摄像头
    boolean IsCheckAll;         //全选
    //下载的标题
    ArrayList<IN_ASSET> mDatas       = new ArrayList<IN_ASSET>() {
    };
    ArrayList<IN_ASSET> mLinshiDatas = new ArrayList<IN_ASSET>() {
    };

    private Map<String, TextView> mTvMap = new HashMap<>();
    private LinearLayoutManager mLayoutManager;
    private boolean             isLoadingMore;
    private long                mLastTime;
    private boolean                 loadFlag    = true;       //加载数据的时候不刷新界面
    private HashMap<Long, IN_ASSET> mChooseData = new HashMap<>();  //所有被选中的物资


    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return (R.layout.activity_asset_check);
    }


    @Override
    protected void initView() {

        qcid = AndroidUtils.getMaxPcid(mDaoSession);     //初始化获取最大期次

        initTextView();

        //初始化列表视图
        initRecyCycleView();
        //初始化数据
        initData();
        //查询头信息
        queryAssetInfo();
        //先给初始信息
    }

    private void initTextView() {
        //将四个放进去同意管理
        mTvMap.put("1", mTvYd);
        mTvMap.put("2", mTvWd);
        mTvMap.put("3", mTvBd);
        mTvMap.put("4", mTvZk);

    }

    //获取数据
    private void initData() {
        searchList.add("1");
        searchList.add("2");
        searchList.add("3");
        searchList.add("4");

        for (int i = 0; i < searchList.size(); i++) {
            showCheckType(searchList.get(i));
        }
        changeShow("1");
    }


    //初始化列表
    private void initRecyCycleView() {
        canCancelRadioButton(mRbAll);
        mLayoutManager = new LinearLayoutManager(this);
        mRvList.setLayoutManager(mLayoutManager);
        mMainAdapter = new CommonAdapter<IN_ASSET>(this, R.layout.item_assetquery, mDatas) {
            @Override
            protected void convert(ViewHolder holder, final IN_ASSET bean, int position) {

                //  holder.setText(R.id.iv_asset)
                holder.setText(R.id.tv_name, bean.getWZMC());
                holder.setText(R.id.tv_brand, bean.getPPMC());
                holder.setText(R.id.tv_code, bean.getKPBH());
                holder.setText(R.id.tv_old_code, bean.getKPBH_OLD());

                holder.setText(R.id.rb, bean.getKSMC()); //科室
                //  holder.setChecked(R.id.rb, bean.getIsCheck());

                //未清点的才显示手动
                if (bean.getWhichType() != null && bean.getWhichType() != 2) {
                    holder.setVisible(R.id.tv_hand, false);
                }

                RadioButton mRb = (RadioButton) holder.getView(R.id.rb);
                mRb.setChecked(bean.isFlagChoose());

                //radiobutton 的状态保持
                Utils.canCancelRadioButton(mRb, bean, new Utils.onBackResult() {
                    @Override
                    public void backResult(IN_ASSET bean, boolean isCheck) {
                        if (isCheck) {
                            mChooseData.put(bean.getID(), bean);
                        } else {
                            //将已有的删除
                            if (mChooseData.keySet().contains(bean.getID())) {
                                mChooseData.remove(bean.getID());
                            }
                        }
                    }
                });

                //跳转到编辑界面
                holder.setOnClickListener(R.id.ll_main, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(AssetCheckActivity.this, AssetDetailEditActivity.class);
                        intent.putExtra("assetId", bean.getID());
                        startActivity(intent);
                    }
                });

                holder.setText(R.id.tv_location_before, bean.getDDMC());
                holder.setText(R.id.tv_location_later, bean.getChange_dept() == null ? "" : bean.getChange_dept());

                //手动清点
                holder.setOnClickListener(R.id.tv_hand, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(AssetCheckActivity.this).setMessage("确定手动清点吗?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                HashSet<String> set = new HashSet<>();
                                set.add(bean.getRFID_CODE());
                                doSaveListData(set);
                            }
                        }).create().show();

                    }
                });
            }
        };

        mRvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
                int totalItemCount = mLayoutManager.getItemCount();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                // dy>0 表示向下滑动
                if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
                    if (isLoadingMore) {
                        //不做操作
                    } else {
                        isLoadingMore = true;
                        //加载更多数据
                        loadMeinv(mDatas.size(), 10);//这里多线程也要手动控制isLoadingMore
                        queryAssetInfo();
                    }
                }
            }
        });
        mRvList.setAdapter(mMainAdapter);
    }


    //返回的科室在这


    @OnClick({R.id.iv_back, R.id.iv_scan, R.id.iv_search, R.id.iv_config, R.id.tv_yd, R.id.tv_wd, R.id.tv_bd, R.id.tv_zk,
            R.id.tv_yzk, R.id.tv_lsyd
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_scan:
                pdaType = 6;
                open2Scan();       //打开二维码
                break;
            case R.id.iv_search:
                gotoSearch();       //打开搜索框  手动输入编号搜索
                break;
            case R.id.iv_config:
                gotoConfig();
                break;
            case R.id.tv_yd:        //已点
                changeShow("1");
                break;
            case R.id.tv_wd: //未点
                changeShow("2");
                break;
            case R.id.tv_bd://变动
                changeShow("3");
                break;
            case R.id.tv_zk://转科
                changeShow("4");
                break;
            case R.id.tv_yzk:   //转科逻辑
                changeDept();
                break;
            case R.id.tv_lsyd:  //临时移动的逻辑
                changeDdid();
                break;

        }
    }


    //转科
    public void changeDept() {

        if (mChooseData.size() == 0) {
            ToastUtil.showToast("没有物资被选中!");
        }
        Set<Long> checkedSet = mChooseData.keySet();
        // 取最大的盘点期次
        PDA_ASSET_CHECKDao pdaDao = mDaoSession.getPDA_ASSET_CHECKDao();

        ArrayList<String> checkList = new ArrayList();

        for (Long c_id : checkedSet) {
            PDA_ASSET_CHECK pdaList = pdaDao.queryBuilder().where(PDA_ASSET_CHECKDao.Properties.ASSET_ID.eq(c_id),
                    PDA_ASSET_CHECKDao.Properties.QCPC.eq(qcid)).unique();
            // 不存在.该物资没有盘点进去,加载界面时就做操作
            if (pdaList == null || pdaList.getCHECK_ID() == null) {
                ToastUtil.showToast("存在没有盘点的数据!");
                return;
            } else {
                checkList.add(pdaList.getASSET_ID() + "");
            }
        }
        Intent intent = new Intent(this, YzkActivity.class);
        intent.putExtra("ids", checkList);
        startActivityForResult(intent, Content.YZK_REQUEST_CODE);
    }

    //临时移动
    public void changeDdid() {

        if (mChooseData.size() == 0) {
            ToastUtil.showToast("没有物资被选中!");
        }
        Set<Long> checkedSet = mChooseData.keySet();

        // 取最大的盘点期次
        final PDA_ASSET_CHECKDao pdaDao = mDaoSession
                .getPDA_ASSET_CHECKDao();
        // 对所有选中的物资循环

        Observable.from(checkedSet)
                .compose(this.<Long>bindToLife())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Func1<Long, PDA_ASSET_CHECK>() {
                    @Override
                    public PDA_ASSET_CHECK call(Long aLong) {
                        return pdaDao
                                .queryBuilder()
                                .where(PDA_ASSET_CHECKDao.Properties.ASSET_ID
                                                .eq(aLong),
                                        PDA_ASSET_CHECKDao.Properties.QCPC
                                                .eq(qcid)).unique();
                    }
                })
                .filter(new Func1<PDA_ASSET_CHECK, Boolean>() {
                    @Override
                    public Boolean call(PDA_ASSET_CHECK pdaBean) {
                        return pdaBean != null && pdaBean.getCHECK_ID() != null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PDA_ASSET_CHECK>() {
                    @Override
                    public void onCompleted() {
                        ToastUtil.showToast("临时移动成功!");
                        queryAssetInfo();
                        loadMeinv(0, 10);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(PDA_ASSET_CHECK pdaBean) {
                        //设置临时移动的数据
                        setLsydData(pdaBean, pdaDao);
                    }
                });
    }

    /**
     * @param pdaBean
     * @param pdaDao
     */
    private void setLsydData(PDA_ASSET_CHECK pdaBean, PDA_ASSET_CHECKDao pdaDao) {
        pdaBean.setIS_CHANGE_DD(null);//临时变动改为正常状态
        pdaBean.setCHANGE_DDID(null);
        pdaBean.setASSET_CHECK_DATE(new Date());
        pdaBean.setASSET_SYNC_DATE(null);
        pdaBean.setASSET_SYNC_FLAG(0);
        String userName = SPUtil.getString(AssetCheckActivity.this, "userName", "");
        String userId = SPUtil.getString(AssetCheckActivity.this, "userID", "");
        pdaBean.setASSET_CHECK_ID(userId);
        pdaBean.setASSET_CHECK_NAME(userName);
        pdaDao.update(pdaBean);
    }


    //改变颜色的逻辑
    private void changeShow(String type) {
        TextView tv = mTvMap.get(type);
        if (searchList.contains(type)) {
            searchList.remove(type);
            tv.setTextColor(getResources().getColor(R.color.textGrey));
            tv.setBackgroundResource(R.drawable.btn_normal);
        } else {
            searchList.add(type);
            tv.setBackgroundResource(R.drawable.shape_blue_normal);
            tv.setTextColor(getResources().getColor(R.color.tv_tv_blue));
        }
        //根据不同的按钮  显示不同的数据
        Collections.sort(searchList);
        StringBuilder sb = new StringBuilder();
        for (String str : searchList) {
            sb.append(str);
        }
        searchState = sb.toString();
        //查询数据
        mDatas.clear();
        mMainAdapter.notifyDataSetChanged();

        if (loadFlag) {
            loadFlag = false;
            queryAssetInfo();
            loadMeinv(0, 10);
        }


    }

    //改变选中的颜色状态
    private void showCheckType(String type) {
        TextView tv = mTvMap.get(type);
        tv.setBackgroundResource(R.drawable.shape_blue_normal);
        tv.setTextColor(getResources().getColor(R.color.tv_tv_blue));
    }


    //配置
    private void gotoConfig() {
        Intent intent = new Intent(this, AssetQueryConfigActivity.class);
        intent.putExtra("mAllDeptIds", mAllDeptIds);
        intent.putExtra("mAllDeptName", mAllDeptName);

        intent.putExtra("mAllDlwzIds", mAllDlwzIds);
        intent.putExtra("mAllDlwzName", mAllDlwzName);

        startActivityForResult(intent, Content.REQUEST_CONFIG);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                //配置
                case Content.REQUEST_CONFIG:
                    mDeptName = data.getStringExtra("mDeptName");
                    mDeptIds = data.getStringExtra("mDeptIds");
                    mDlwzName = data.getStringExtra("mDlwzName");
                    mDlwzIds = data.getStringExtra("mDlwzIds");
                    mAllDeptName = data.getStringExtra("mAllDeptName");
                    mAllDeptIds = data.getStringExtra("mAllDeptIds");
                    mAllDlwzIds = data.getStringExtra("mAllDlwzIds");
                    mAllDlwzName = data.getStringExtra("mAllDlwzName");
                    mTvDlwz.setText(mAllDlwzName);

                    //预转科
                case Content.YZK_REQUEST_CODE:
                    //转科成功的数据
                    // long[] changeDeptses = data.getLongArrayExtra("changeDepts");
                    queryAssetInfo();
                    loadMeinv(0, 10);

            }
        }
    }


    //可以取消的RadioButton
    private void canCancelRadioButton(final RadioButton rbJlzmYes) {
        rbJlzmYes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (IsCheckAll) {
                        IsCheckAll = false;
                        rbJlzmYes.setChecked(false);
                    } else {
                        IsCheckAll = true;
                        rbJlzmYes.setChecked(true);
                    }
                    gotoCheckAll(IsCheckAll);
                }
                return true;
            }
        });
    }

    private void gotoCheckAll(boolean isCheckAll) {


        for (int i = 0; i < mDatas.size(); i++) {
            mDatas.get(i).setFlagChoose(isCheckAll);
        }
        mMainAdapter.notifyDataSetChanged();


    }


    public void loadMeinv(final int start, final int count) {
        if ((mDlwzIds == null || "".equals(mDlwzIds)) && (mDeptIds == null || "".equals(mDeptIds))) {
        }
        // 取最大的盘点期次,查询一次即可


        //增加侧边条件判断

        String where = "";
        String whereb = "";
        StringBuffer whereAll = new StringBuffer("");
        if (!TextUtils.isEmpty(mDeptIds) && !TextUtils.isEmpty(mDlwzIds)) {
            where = " where ( a.KSID in " + mAllDeptIds + " or ( a.KSID is null or a.KSID = ''))" + " and ( a.DDID in " + mAllDlwzIds + " or ( a.DDID is null or a.DDID = '')) and ((a.DDID is  not null and a.DDID <> '' ) or ( a.KSID is not null and a.KSID <> '')) ";
            whereb = " and (ic_selected.DQKSID in " + mAllDeptIds + " or ( ic_selected.DQKSID is null or ic_selected.DQKSID = '')) and ( ic_selected.DQDDID in " + mAllDlwzIds + " or ( ic_selected.DQDDID is null or ic_selected.DQDDID = '')) and ((ic_selected.DQKSID is  not null and ic_selected.DQKSID <> '' ) or ( ic_selected.DQDDID is not null and ic_selected.DQDDID <> '')) ";
        } else if (!TextUtils.isEmpty(mDeptIds)) {
            where = " where a.KSID in " + mAllDeptIds;
            whereb = " and ic_selected.DQKSID in " + mAllDeptIds;
        } else if (!TextUtils.isEmpty(mDlwzIds)) {
            where = " where a.DDID in " + mAllDlwzIds;
            whereb = " and ic_selected.DQDDID in " + mAllDlwzIds;
        }
        Log.d("AssetCheckJsFunction2", where);

        //查不到异常数据，还需要单独查一条，已PDA__ASSET__CHECK为主表，现在是left
        //把当前的查出来，根据地理位置去判断？
        //1已点绿色 green，2未点蓝色blue，3变动红色red，4转科橙色orange, 5的话两个都是
        StringBuffer sql = new StringBuffer();
        sql.append("select a.ID as ID, a.WZMC as WZMC, a.GGXH as GGXH, a.PPMC as PPMC, a.SCBH as SCBH,a.KPBH as KPBH, a.KPBH__OLD as KPBH_OLD, a.RFID__CODE as RFID, a.BAR__CODE as BAR, a.KSMC as KSMC, a.DDMC as DDMC,ic_selected.DQDDMC as DQDDMC,ic_selected.IS__CHANGE as IS__CHANGE ,ic_selected.CHANGE__DEPT as CHANGE__DEPT");
        sql.append(",case when ic_selected.ASSET__ID is not null and (ic_selected.IS__CHANGE__DD != 1 or ic_selected.IS__CHANGE__DD is null) and ic_selected.IS__CHANGE=1 then 4 " +
                "when ic_selected.ASSET__ID is not null and (ic_selected.IS__CHANGE != 1 or ic_selected.IS__CHANGE is null) and ic_selected.IS__CHANGE__DD=1 then 3 " +
                "when ic_selected.ASSET__ID is not null and ic_selected.IS__CHANGE=1 and ic_selected.IS__CHANGE__DD=1 then 5 " +
                "when ic_selected.ASSET__ID is not null then 1 " +
                "else 2 " +
                "end as PDAID");
        sql.append(",ic_selected.ASSET__CHECK__DATE as CDATE ");
        sql.append(" from IN__ASSET a left join PDA__ASSET__CHECK ic_selected on a.ID=ic_selected.ASSET__ID and ic_selected.QCPC=" + qcid);

        //异常的SQL，上面是查不出异常的
        StringBuffer sql_yc = new StringBuffer();
        sql_yc.append("select a.ID as ID, a.WZMC as WZMC, a.GGXH as GGXH, a.PPMC as PPMC, a.SCBH as SCBH,a.KPBH as KPBH, a.KPBH__OLD as KPBH_OLD, a.RFID__CODE as RFID, a.BAR__CODE as BAR, a.KSMC as KSMC, a.DDMC as DDMC,ic_selected.DQDDMC as DQDDMC,ic_selected.IS__CHANGE as IS__CHANGE ,ic_selected.CHANGE__DEPT as CHANGE__DEPT");
        sql_yc.append(",case when ic_selected.ASSET__ID is not null and (ic_selected.IS__CHANGE__DD != 1 or ic_selected.IS__CHANGE__DD is null) and ic_selected.IS__CHANGE=1 then 4 " +
                "when ic_selected.ASSET__ID is not null and (ic_selected.IS__CHANGE != 1 or ic_selected.IS__CHANGE is null) and ic_selected.IS__CHANGE__DD=1 then 3 " +
                "when ic_selected.ASSET__ID is not null and ic_selected.IS__CHANGE=1 and ic_selected.IS__CHANGE__DD=1 then 5 " +
                "when ic_selected.ASSET__ID is not null then 1 " +
                "else 2 " +
                "end as PDAID");
        sql_yc.append(",ic_selected.ASSET__CHECK__DATE as CDATE ");

        sql_yc.append(" from IN__ASSET a, PDA__ASSET__CHECK ic_selected where a.ID=ic_selected.ASSET__ID and ic_selected.QCPC=" + qcid);

        //针对复选框的判断
        if ("1".equals(searchState)) {
            //已点的正常数据
            whereAll.append(" (ic_selected.IS__CHANGE != 1 or ic_selected.IS__CHANGE is null) and (ic_selected.IS__CHANGE__DD != 1 or ic_selected.IS__CHANGE__DD is null) and ic_selected.ASSET__ID is not null ");
        } else if ("2".equals(searchState)) {
            //未点
            whereAll.append(" ic_selected.ASSET__ID is null ");
        } else if ("3".equals(searchState)) {
            //变动
            whereAll.append(" ic_selected.IS__CHANGE__DD=1 ");
        } else if ("4".equals(searchState)) {
            //转科
            whereAll.append(" ic_selected.IS__CHANGE=1 ");
        } else if ("12".equals(searchState)) {
            //已点 + 未点（不包括异常数据）
            whereAll.append(" (ic_selected.IS__CHANGE != 1 or ic_selected.IS__CHANGE is null) and (ic_selected.IS__CHANGE__DD != 1 or ic_selected.IS__CHANGE__DD is null) ");
        } else if ("13".equals(searchState)) {
            //已点+变动,因为只有转科的数据也已经盘点,既转科又变动的数据也要显示,所以比较麻烦
            whereAll.append("(ic_selected.IS__CHANGE__DD=1 or (ic_selected.IS__CHANGE__DD=1 and ic_selected.IS__CHANGE=1) or (ic_selected.ASSET__ID is not null and (ic_selected.IS__CHANGE != 1 or ic_selected.IS__CHANGE is null) and (ic_selected.IS__CHANGE__DD != 1 or ic_selected.IS__CHANGE__DD is null)))");
            //whereAll.append(" ic_selected.ASSET__ID is not null and (ic_selected.IS__CHANGE__DD=1 or (ic_selected.IS__CHANGE__DD=1 and ic_selected.IS__CHANGE=1) or (ic_selected.IS__CHANGE != 1 or ic_selected.IS__CHANGE is null) or (ic_selected.IS__CHANGE__DD != 1 or ic_selected.IS__CHANGE__DD is null))");
        } else if ("14".equals(searchState)) {
            //已点+转科
            whereAll.append("(ic_selected.IS__CHANGE=1 or (ic_selected.IS__CHANGE__DD=1 and ic_selected.IS__CHANGE=1) or (ic_selected.ASSET__ID is not null and (ic_selected.IS__CHANGE != 1 or ic_selected.IS__CHANGE is null) and (ic_selected.IS__CHANGE__DD != 1 or ic_selected.IS__CHANGE__DD is null)))");
            //whereAll.append(" ic_selected.ASSET__ID is not null and (ic_selected.IS__CHANGE=1 or (ic_selected.IS__CHANGE__DD=1 and ic_selected.IS__CHANGE=1) or (ic_selected.IS__CHANGE != 1 or ic_selected.IS__CHANGE is null) or (ic_selected.IS__CHANGE__DD != 1 or ic_selected.IS__CHANGE__DD is null))");
        } else if ("23".equals(searchState)) {
            //未点+变动
            whereAll.append(" (ic_selected.ASSET__ID is null or ic_selected.IS__CHANGE__DD=1) ");
        } else if ("24".equals(searchState)) {
            //未点+转科
            whereAll.append(" (ic_selected.ASSET__ID is null or ic_selected.IS__CHANGE=1) ");
        } else if ("34".equals(searchState)) {
            //变动+转科
            whereAll.append(" (ic_selected.IS__CHANGE__DD=1 or ic_selected.IS__CHANGE=1) ");
        } else if ("123".equals(searchState)) {
            //已点+未点+变动
            whereAll.append(" (ic_selected.IS__CHANGE != 1 or ic_selected.IS__CHANGE is null or ic_selected.IS__CHANGE__DD=1)");
        } else if ("124".equals(searchState)) {
            //已点+未点+转科
            whereAll.append(" (ic_selected.IS__CHANGE__DD != 1 or ic_selected.IS__CHANGE__DD is null or ic_selected.IS__CHANGE=1) ");
        } else if ("134".equals(searchState)) {
            //已点+变动+转科
            whereAll.append(" ic_selected.ASSET__ID is not null ");
        } else if ("234".equals(searchState)) {
            //未点+变动+转科
            whereAll.append(" (ic_selected.ASSET__ID is null or ic_selected.IS__CHANGE__DD=1 or ic_selected.IS__CHANGE=1) ");
        } else if ("1234".equals(searchState)) {

        } else {
            //什么都没选，直接返回空

        }
        sql.append(where);
        if (!"".equals(whereAll.toString())) {
            if (!"".equals(where.toString())) {
                sql.append(" and ");
            } else {
                sql.append(" where ");
            }
        }
        sql.append(whereAll.toString());

        sql_yc.append(whereb);
        if (!"".equals(whereAll.toString())) {
            sql_yc.append(" and ");
            sql_yc.append(whereAll.toString());
        }
        sql_yc.append(" and (ic_selected.DQDDID<>ic_selected.DDID or ic_selected.DQKSID<>ic_selected.KSID)");//如果是正常数据，全部都保存物资本身的数据


        String sql_all = "select CDATE as CDATE, PDAID as PDAID, ID as ID, WZMC as WZMC, GGXH as GGXH, PPMC as PPMC, SCBH as SCBH, RFID as RFID, BAR as BAR, KSMC as KSMC, DDMC as DDMC,DQDDMC as DQDDMC, KPBH as KPBH, KPBH_OLD as KPBH_OLD , IS__CHANGE as IS__CHANGE,CHANGE__DEPT as CHANGE__DEPT" +
                " from (" + sql.toString() +
                " union " + sql_yc.toString() + ")" +
                " order by PDAID desc, CDATE desc ";

        //获得所有数据 用于选择框的逻辑
        //getAllDatas(sql_all);

        String sql_all_limit = sql_all +
                " limit ? offset ? ";
        List<String> parList = new ArrayList<String>();

        parList.add(count + "");
        parList.add(start + "");

        StringBuffer sql_count = new StringBuffer();
        if (start == 0) {
            sql_count.append("select count(*) from (");
            sql_count.append(sql_all_limit.substring(0, sql_all_limit.indexOf("limit")));
            sql_count.append(")");
            long row_count = getRowCount(sql_count.toString(), new String[]{});
            //获得所又的数据
            //getAllDatas(sql_all);

            //mTvAll.setText(row_count + "");
        }
        //查询对应的数据 进行渲染
        querySummaryInAsset(sql_all_limit, (String[]) parList.toArray(new String[parList.size()]));
    }

    //获得所有的数据 为了
    private void getAllDatas(String sql_all) {
        DataUtil.getDatas(mDaoSession, sql_all, null).compose(this.bindToLife())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribe(new Action1<List<IN_ASSET>>() {
                    @Override
                    public void call(List<IN_ASSET> list) {
                        mChooseData.clear();
                        for (IN_ASSET in_asset : list) {
                            mChooseData.put(in_asset.getID(), in_asset);
                        }
                    }
                });
    }


    @Override
    public void doSaveListData(Set set) {
        //
        if ((TextUtils.isEmpty(mDlwzIds) || TextUtils.isEmpty(mDeptIds))) {
            ToastUtil.showToast("请先选择地理位置或科室");
            return;
        }
        final Set<String> loopset_tmp = new HashSet<String>();
        if (set == null || set.size() == 0) {
            return;
        }
        Iterator<String> ite_rfid = set.iterator();

        while (ite_rfid.hasNext()) {
            String tag = ite_rfid.next();
            if (TextUtils.isEmpty(tag)) {
                ToastUtil.showToast("旧编号设备不支持当前方式清点");
                return;
            }
            loopset_tmp.add(tag);
        }
        Observable.from(loopset_tmp).subscribeOn(Schedulers.io())
                .concatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return changeRfid2Use(s);
                    }
                })
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return insertPdaAsset(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        mDatas.clear();
                        mMainAdapter.notifyDataSetChanged();
                        loadMeinv(0, 10);
                        queryAssetInfo();
                        ToastUtil.showToast("清点完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showToast("出现错误" + e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {

                    }
                });


    }


    //插入PdaAsset
    private boolean insertPdaAsset(String data) {
        IN_ASSETDao assetDao = mDaoSession.getIN_ASSETDao();
        PDA_ASSET_CHECKDao pdaDao = mDaoSession.getPDA_ASSET_CHECKDao();
        // 取最大的盘点期次
        String userName = SPUtil.getString(this, "userName", "");
        String userId = SPUtil.getString(this, "userID", "");
        String tagid = data.trim();//去掉空格后的RFID，已经去过一次了。
        IN_ASSET asset = null;
        if (pdaType == 1) {
            asset = assetDao.queryBuilder().where(IN_ASSETDao.Properties.RFID_CODE.eq(tagid)).unique();
        } else {
            asset = assetDao.queryBuilder().where(IN_ASSETDao.Properties.BAR_CODE.eq(tagid)).unique();
        }
        if (asset == null) {//找不到
            return false;
        }
        PDA_ASSET_CHECK pdaAsset = pdaDao.queryBuilder().where(PDA_ASSET_CHECKDao.Properties.ASSET_ID.eq(asset.getID()),
                PDA_ASSET_CHECKDao.Properties.QCPC.eq(qcid)).unique();

        //此处对于已经存在的数据，进行部分数据更新
        if (pdaAsset == null || pdaAsset.getCHECK_ID() == null) {
            pdaAsset = new PDA_ASSET_CHECK();
        }

        String dlwz_l = "'" + asset.getDDID() + "'";
        //


        if (!TextUtils.isEmpty(mDlwzIds) && (TextUtils.isEmpty(asset.getDDID()) || mAllDlwzIds.indexOf(dlwz_l) < 0)) {//说明地理位置不对，默认变动,如果说ddid是空的话，那么也是变动
            pdaAsset.setIS_CHANGE_DD(1);
            pdaAsset.setCHANGE_DDID(mDlwzIds);
            pdaAsset.setDQDDID(mDlwzIds);//当前的地理位置
            pdaAsset.setDQDDMC(mDlwzName);
        } else {
            pdaAsset.setIS_CHANGE_DD(null);
            pdaAsset.setCHANGE_DDID(null);
            pdaAsset.setDQDDID(asset.getDDID());//当前的地理位置，保存物资自己的科室，防止是子科室
            pdaAsset.setDQDDMC(asset.getDDMC());
        }
        pdaAsset.setASSET_ID(asset.getID());
        pdaAsset.setASSET_CHECK_DATE(new Date());
        pdaAsset.setASSET_CHECK_ID(userId);
        pdaAsset.setASSET_CHECK_NAME(userName);
        pdaAsset.setQCPC(qcid);
        pdaAsset.setCHECK_TYPE("pda");
        pdaAsset.setPDA_ID(AndroidUtils.getImei(this));//设置每台机器的编号
        pdaAsset.setASSET_SYNC_FLAG(0);//设置同步标记为0,防止同步过的资产再次盘点到不能同步。
        pdaAsset.setASSET_SYNC_DATE(null);
        pdaAsset.setDDID(asset.getDDID());//物资原来的地理位置
        pdaAsset.setKSID(asset.getKSID());

        pdaAsset.setUUID(AndroidUtils.createUUID());

        String ksdm_l = "'" + asset.getKSID() + "'";
        //不存在科室，就存物资本身的科室
        if (TextUtils.isEmpty(mDeptIds)) {
            pdaAsset.setDQKSID(asset.getKSID());
        } else {
            if (mAllDeptIds.indexOf(ksdm_l) < 0) {//不在子科室列表
                pdaAsset.setDQKSID(mDeptIds);
            } else {
                pdaAsset.setDQKSID(asset.getKSID());//在子科室列表，就存自己
            }
        }
        pdaDao.insertOrReplace(pdaAsset);

        return true;
    }

    //获取数据数目

    public long getRowCount(String sql, String[] par) {
        long count = 0;
        Cursor cursor = mDb.rawQuery(sql, par);
        cursor.moveToFirst();
        while (cursor.getPosition() != cursor.getCount()) {
            count = Long.parseLong(cursor.getString(0));
            cursor.moveToNext();
        }
        return count;
    }


    public void querySummaryInAsset(final String sql, final String[] par) {
        DataUtil.getDatas(mDaoSession, sql, par)
                .compose(this.bindToLife())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<IN_ASSET>() {
                    @Override
                    public void onCompleted() {
                        Log.d("AssetQueryActivity2", "mLinshiDatas.size():" + mLinshiDatas.toString());
                        // mDatas.clear();
                        mDatas.addAll(mLinshiDatas);
                        mMainAdapter.notifyDataSetChanged();
                        mLinshiDatas.clear();
                        loadFlag = true;
                        isLoadingMore = false;
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(IN_ASSET in_asset) {
                        //加载出来的数据 要将他弄成已打钩的
                        //如果是全选了 那么加载出来的数据 默认全选 这么做是因为 数据是动态加载的  之后的全选之后的操作另作计算
                        if (IsCheckAll) {
                            in_asset.setFlagChoose(true);
                        }
                        mLinshiDatas.add(in_asset);
                        request(1);
                    }

                    @Override
                    public void onStart() {
                        request(1);
                    }
                });
    }


    //显示按钮数目的
    public void queryAssetInfo() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        // 取最大的盘点期次

        if (TextUtils.isEmpty(qcid)) {
            AndroidUtils.showErrorMsg("错误", "盘点期次未下载", this);
            return;
        }

        // 盘点ID，最近日期，开始日期
        long sysl = 0l;
        long pdsl = 0l;


        //增加侧边条件判断
        List<String> parList = new ArrayList<String>();
        parList.add(qcid);
        String where = "";
        if (!TextUtils.isEmpty(mDeptIds) && !TextUtils.isEmpty(mDlwzIds)) {
            where = " where ( a.KSID in " + mAllDeptIds + " or ( a.KSID is null or a.KSID = ''))" + " and ( a.DDID in " + mAllDlwzIds + " or ( a.DDID is null or a.DDID = '')) and ((a.DDID is  not null and a.DDID <> '' ) or ( a.KSID is not null and a.KSID <> '' ))";

        } else if (!TextUtils.isEmpty(mDeptIds)) {
            where = " where a.KSID in " + mAllDeptIds;
        } else if (!TextUtils.isEmpty(mDlwzIds)) {
            where = " where a.DDID in " + mAllDlwzIds;
        }

        StringBuffer sql = new StringBuffer();
        sql.append("select count(ic_selected.ASSET__ID) as YPSL, max(ic_selected.ASSET__CHECK__DATE) as ZJRQ, min(ic_selected.ASSET__CHECK__DATE) as KSRQ ,count(a.ID) as WPSL");
        sql.append(" from IN__ASSET a left join PDA__ASSET__CHECK ic_selected on a.ID=ic_selected.ASSET__ID and ic_selected.QCPC=?");
        sql.append(where);

        Log.d("AssetCheckJsFunction22", sql.toString());
        Cursor cursor2 = mDb.rawQuery(sql.toString(), (String[]) parList.toArray(new String[parList.size()]));
        cursor2.moveToFirst();
        while (cursor2.getPosition() != cursor2.getCount()) {
            pdsl = cursor2.getLong(0);
            long l_zjrq = cursor2.getLong(1);
            long l_ksrq = cursor2.getLong(2);
            sysl = cursor2.getLong(3);
            cursor2.moveToNext();
        }

        long noCheck = sysl - pdsl;
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("ZCTS", sysl);          //总数
            jsonObj.put("YQDS", pdsl);          //已清点
            jsonObj.put("WQDS", noCheck);       //未清点

            mTvAll.setText(sysl + "");
            mTvYqd.setText(pdsl + "");
            mTvWqd.setText(noCheck + "");

        } catch (Exception e) {

            e.printStackTrace();
            AndroidUtils.showErrorMsg("错误", e.getMessage(), AssetCheckActivity.this);
        }

    }

    //将Rfid 转成对应的 地理位子 卡片编号等
    public Observable<String> changeRfid2Use(final String str) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (pdaType == 1) {
                    subscriber.onNext(judgeDB(str));
                } else if (pdaType == 2 || pdaType == 6) {
                    subscriber.onNext(str);
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());
    }

    //判断是否是地理位子
    private String judgeDB(String str) {
        if (str.indexOf("D") > -1) {
            PUB_DICTIONARY_ITEM entity = mDaoSession.getPUB_DICTIONARY_ITEMDao().queryBuilder().where(PUB_DICTIONARY_ITEMDao.Properties.DICT_ID.eq("007114"), PUB_DICTIONARY_ITEMDao.Properties.BAR_CODE.like(str)).unique();
            if (entity != null) {
                return entity.getITEM_CODE();
            }
        } else if (str.indexOf("B") > -1) {
            List<INSPECT_GROUP> entities = mDaoSession.getINSPECT_GROUPDao().queryBuilder().where(INSPECT_GROUPDao.Properties.INSG_RFID.eq(str)).list();
            if (entities != null && entities.size() > 0) {
                return (entities.get(0).getINSG_RFID());
            }
        } else {
            REPAIR_PLACE entity1 = mDaoSession.getREPAIR_PLACEDao().queryBuilder().where(REPAIR_PLACEDao.Properties.PLACE_BAR_CODE.eq(str)).unique();
            if (entity1 != null) {
                return entity1.getPLACE_RFID_CODE();
            }
            IN_ASSET entity2 = mDaoSession.getIN_ASSETDao().queryBuilder().where(IN_ASSETDao.Properties.BAR_CODE.eq(str)).unique();
            if (entity2 != null) {
                return entity2.getRFID_CODE();
            }
        }
        return str;
    }


}
