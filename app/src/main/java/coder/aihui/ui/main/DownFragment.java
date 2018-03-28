package coder.aihui.ui.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import coder.aihui.R;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.data.bean.DownLoadBean;
import coder.aihui.data.bean.gen.INSPECT_PLANDao;
import coder.aihui.rxbus.RxBus;
import coder.aihui.rxbus.event.DownEvent;
import coder.aihui.rxbus.event.UIEvent;
import coder.aihui.util.AndroidUtils;
import coder.aihui.util.ColorsUtil;
import coder.aihui.util.ListUtils;
import coder.aihui.util.ListViewUtil;
import coder.aihui.util.SPUtil;
import coder.aihui.util.ToastUtil;
import coder.aihui.widget.MyDecoration;
import coder.aihui.widget.MyProgressButton;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static coder.aihui.app.MyApplication.mContext;
import static coder.aihui.data.bean.DownLoadBean.ON_FINISH;
import static coder.aihui.ui.main.DownPresenter.ASSET_DOWN;
import static coder.aihui.ui.main.DownPresenter.AZYS_DOWN;
import static coder.aihui.ui.main.DownPresenter.COMPANY_DOWN;
import static coder.aihui.ui.main.DownPresenter.HTTP;
import static coder.aihui.ui.main.DownPresenter.INIT_DOWN;
import static coder.aihui.ui.main.DownPresenter.INSPECT_INIT_DOWN;
import static coder.aihui.ui.main.DownPresenter.INSPECT_PLAN_DOWN;
import static coder.aihui.ui.main.DownPresenter.INSPECT_PM_INIT_DOWN;
import static coder.aihui.ui.main.DownPresenter.INSPECT_PM_PLAN_DOWN;
import static coder.aihui.ui.main.DownPresenter.INSPECT_PM_TEMPLETITEM_DOWN;
import static coder.aihui.ui.main.DownPresenter.INSPECT_TEMPLETITEM_DOWN;
import static coder.aihui.ui.main.DownPresenter.PXGL_SB_DOWN;
import static coder.aihui.ui.main.DownPresenter.WEB_SERVICE;
import static coder.aihui.ui.main.MainActivity.DOWN_TAB;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/3/17 15:31
 * @ 描述    ${增加下载  改变两个方法 一个initTextName(),一个getRetrofitObserbe http专用 () 然后配置一个常量就可以了}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/3/17$
 * @ 更新描述  ${TODO}
 */

public class DownFragment extends BaseFragment<DownPresenter> implements DownView {


    @BindView(R.id.rv)
    RecyclerView mRv;

    //初始化页面

    //下载的标题
    ArrayList<DownLoadBean> mDatas = new ArrayList<DownLoadBean>() {
    };
    private CommonAdapter<DownLoadBean> mMainAdapter;

    public static final String[]     mBigType     = new String[]{"台账", "巡检", "PM", "安装验收"};
    private             List<String> mBigTypeList = new ArrayList<>();
    private List<String> mTypeList;
    HashSet<Integer> DownSet = new HashSet<>();         //已下载的类型的集合 因为一个下载按钮会对应多个下载表 所以区别对待

    @Override
    public void onDestroy() {
        mPresenter.unregisterRxBus();
        super.onDestroy();
    }

    @Override
    protected void initView() {
        mPresenter = new DownPresenter(this, mDaoSession);
        //初始进度条
        MyProgressButton.initStatusString(new String[]{"下载", "暂停", "完成", "错误", "删除", "更新", " 下载中"});
        //初始话列表的下载
        initRecycle();
        //初始化名字 以后要加就加这里
        initTextName();

        if (SPUtil.getInt(mContext, Content.UnDownDatas, 0) == 0) {
            SPUtil.saveInt(mContext, Content.UnDownDatas, mDatas.size());   //显示几个小红点
        }
        //更新显示 数目未下载数目
        RxBus.getInstance().post(new DownEvent(DOWN_TAB));
    }

    private void initTextName() {


        mBigTypeList = Arrays.asList(mBigType);

        //查询台账的已有数目
        mDatas.clear();

        //下载台账初始化
        DownLoadBean downLoadBean = new DownLoadBean();
        downLoadBean.name = "初始化";
        downLoadBean.count = mDaoSession.getSYS_DEPTDao().count() == 0L ? 0 : Integer.parseInt(mDaoSession.getSYS_DEPTDao().count() + "");
        downLoadBean.enties = new String[]{"coder.aihui.data.bean.PUB_DICTIONARY_ITEM", "coder.aihui.data.bean.SYS_DEPT", "coder.aihui.data.bean.IN_STORE_QC", "coder.aihui.data.bean.PUB_DICTIONARY_ITEM"};
        downLoadBean.methods = new String[]{"getHrpPubDictDataJSON2", "getHrpDeptDataJSON", "getHrpInStoreQcDataJSON", "getHrpPubDictDataJSON"};
        downLoadBean.way = WEB_SERVICE;
        downLoadBean.type = INIT_DOWN;
        downLoadBean.bigType = mBigType[0];
        mDatas.add(downLoadBean);


        DownLoadBean assetBean = new DownLoadBean();


        //下载台账
        assetBean.name = "台账下载";
        assetBean.count = mDaoSession.getIN_ASSETDao().count() == 0L ? 0 : Integer.parseInt(mDaoSession.getIN_ASSETDao().count() + "");
        assetBean.enties = new String[]{"coder.aihui.data.bean.IN_ASSET"};
        assetBean.methods = new String[]{"getHrpInAssetDataJSON3"};
        assetBean.way = WEB_SERVICE;
        assetBean.type = ASSET_DOWN;
        assetBean.bigType = mBigType[0];
        mDatas.add(assetBean);

        //下载公司
        DownLoadBean companyBean = new DownLoadBean();
        companyBean.name = "公司下载";
        companyBean.count = mDaoSession.getIN_ASSETDao().count() == 0L ? 0 : Integer.parseInt(mDaoSession.getPUB_COMPANYDao().count() + "");
        companyBean.way = HTTP;
        companyBean.type = COMPANY_DOWN;
        companyBean.bigType = mBigType[0];
        mDatas.add(companyBean);


        //下载巡检计划
        DownLoadBean planBean = new DownLoadBean();

        long xj = mDaoSession.getINSPECT_PLANDao().queryBuilder().where(INSPECT_PLANDao.Properties.INSP_TYPE.eq("XJ")).count();
        planBean.count = (xj == 0L ? 0 : Integer.parseInt(xj + ""));
        planBean.enties = new String[]{"coder.aihui.data.bean.INSPECT_PLAN"};
        planBean.methods = new String[]{"getHrpInspectPlanDataJSON4"};
        planBean.way = WEB_SERVICE;
        planBean.type = INSPECT_PLAN_DOWN;
        planBean.name = "巡检下载";
        planBean.bigType = mBigType[1];
        mDatas.add(planBean);


        //下载巡检初始化
        DownLoadBean inspectInitBean = new DownLoadBean();


        inspectInitBean.name = "巡检初始下载";
        inspectInitBean.count = mDaoSession.getINSPECT_EXT_EXECUTORDao().count() == 0L ? 0 : Integer.parseInt(mDaoSession.getINSPECT_EXT_EXECUTORDao().count() + "");
        inspectInitBean.enties = new String[]{"coder.aihui.data.bean.PUB_DICTIONARY_ITEM", "coder.aihui.data.bean.REPAIR_PLACE", "coder.aihui.data.bean.INSPECT_EXT", "coder.aihui.data.bean.INSPECT_EXT_EXECUTOR", "coder.aihui.data.bean.SYS_PARAM", "coder.aihui.data.bean.INSPECT_GROUP"};
        inspectInitBean.methods = new String[]{"getHrpPubDictDataJSON2", "getHrpRePlaceDataJSON", "getHrpInspectExtDataJSON", "getHrpInspectExtExecDataJSON", "getHrpParamDataJSON", "getHrpInspectGroupDataJSON"};
        inspectInitBean.type = INSPECT_INIT_DOWN;
        inspectInitBean.way = WEB_SERVICE;

        inspectInitBean.bigType = mBigType[1];
        mDatas.add(inspectInitBean);
        //下载巡检模板
        DownLoadBean inspectTempletItemBean = new DownLoadBean();

        inspectTempletItemBean.name = "巡检模板下载";
        inspectTempletItemBean.count = mDaoSession.getInspectTempletItemDao().count() == 0L ? 0 : Integer.parseInt(mDaoSession.getInspectTempletItemDao().count() + "");
        inspectTempletItemBean.enties = new String[]{"coder.aihui.data.bean.InspectTempletItem"};
        inspectTempletItemBean.methods = new String[]{"getHrpInspectTempletItemDataJSON"};
        inspectTempletItemBean.type = INSPECT_TEMPLETITEM_DOWN;
        inspectTempletItemBean.way = WEB_SERVICE;
        inspectTempletItemBean.bigType = mBigType[1];


        mDatas.add(inspectTempletItemBean);


        //下载PM计划
        DownLoadBean planPmBean = new DownLoadBean();

        planPmBean.name = "PM下载";

        long pm = mDaoSession.getINSPECT_PLANDao().queryBuilder().where(INSPECT_PLANDao.Properties.INSP_TYPE.eq("PM")).count();
        planPmBean.count = (pm == 0L ? 0 : Integer.parseInt(pm + ""));
        planPmBean.enties = new String[]{"coder.aihui.data.bean.INSPECT_PLAN"};
        planPmBean.methods = new String[]{"getHrpInspectPlanDataJSON4"};
        planPmBean.way = WEB_SERVICE;
        planPmBean.type = INSPECT_PM_PLAN_DOWN;

        planPmBean.bigType = mBigType[2];
        mDatas.add(planPmBean);


        //下载PM初始化

        DownLoadBean pmInitBean = (DownLoadBean) inspectInitBean.deepClone();
        pmInitBean.type = INSPECT_PM_INIT_DOWN;
        pmInitBean.bigType = mBigType[2];
        pmInitBean.name = "PM初始下载";
        mDatas.add(pmInitBean);

        //下载巡检模板
        DownLoadBean pmTempletItemBean = new DownLoadBean();

        pmTempletItemBean.name = "PM模板下载";
        pmTempletItemBean.count = mDaoSession.getInspectTempletItemDao().count() == 0L ? 0 : Integer.parseInt(mDaoSession.getInspectTempletItemDao().count() + "");
        pmTempletItemBean.enties = new String[]{"coder.aihui.data.bean.InspectTempletItem"};
        pmTempletItemBean.methods = new String[]{"HrpInspectTempletItemDataJSON"};
        pmTempletItemBean.type = INSPECT_PM_TEMPLETITEM_DOWN;
        pmTempletItemBean.way = WEB_SERVICE;

        pmTempletItemBean.bigType = mBigType[2];
        mDatas.add(pmTempletItemBean);


        //下载安装验收
        DownLoadBean pubContractPlanBean = new DownLoadBean();
        pubContractPlanBean.name = "安装验收下载";
        pubContractPlanBean.count = mDaoSession.getPUR_CONTRACT_PLANDao().count() == 0L ? 0 : Integer.parseInt(mDaoSession.getPUR_CONTRACT_PLANDao().count() + "");
        pubContractPlanBean.type = AZYS_DOWN;
        pubContractPlanBean.way = HTTP;
        pubContractPlanBean.bigType = mBigType[3];
        mDatas.add(pubContractPlanBean);

        //下载培训管理
        DownLoadBean pxsbBean = new DownLoadBean();
        pxsbBean.name = "培训管理下载";
        pxsbBean.count = mDaoSession.getIN_MATERIALS_WZMCDao().count() == 0L ? 0 : Integer.parseInt(mDaoSession.getIN_MATERIALS_WZMCDao().count() + "");
        pxsbBean.type = PXGL_SB_DOWN;
        pxsbBean.way = HTTP;
        pxsbBean.bigType = mBigType[3];
        mDatas.add(pxsbBean);

        mTypeList = ListUtils.ListFiled2list(mDatas, "getType", DownLoadBean.class);

        mMainAdapter.notifyDataSetChanged();


    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }


    //显示错误页面
    public synchronized void showError(int type, final String wrong) {
        Observable.just(type)
                .compose(this.<Integer>bindToLife())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {

                int i = mTypeList.indexOf(integer + "");
                View view = ListViewUtil.getViewByPosition(i, mRv);
                if (view == null) {             //说明当前页面被回收了
                    mDatas.get(i).setState(DownLoadBean.MISTAKES);
                    mMainAdapter.notifyDataSetChanged();
                } else {
                    MyProgressButton mcp = (MyProgressButton) view.findViewById(R.id.CP_down);
                    //"下载","暂停","完成","错误","删除","更新"
                    mcp.setMorphingCircle(false);
                    mcp.setMorphingNormal(false);
                    mcp.normal(3);
                }

                //弹框显示错误原因
                AndroidUtils.showErrorMsg("下载失败", wrong, getActivity());
            }

        });


    }


    //下载成功的
    public synchronized void showNormal(int type) {
        Observable.just(type)
                .compose(this.<Integer>bindToLife())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer type) {                        //根据Type来进行显示哪个
                int i = mTypeList.indexOf(type + "");
                View view = ListViewUtil.getViewByPosition(i, mRv);

                //页面滑动的时候 是无法通过上述方法定位的
                if (view != null) {
                    MyProgressButton mcp = (MyProgressButton) view.findViewById(R.id.CP_down);
                    //"下载","暂停","完成","错误","删除","更新"
                    mcp.setMorphingCircle(false);
                    mcp.setMorphingNormal(false);

                    int mulTimes = mcp.getMulTimes();
                    int currentTimes = mcp.getCurrentTimes();

                    if(mulTimes!=0&&mulTimes!=1&&currentTimes<mulTimes){
                        mcp.setCurrentTimes(currentTimes+1);
                    } else  {
                        mcp.normal(2);
                        mcp.setCurrentTimes(1);
                    }
                    if (!DownSet.contains(type)) {
                        Integer anInt = SPUtil.getInt(mContext, Content.UnDownDatas, mDatas.size());
                        DownSet.add(type);
                        SPUtil.saveInt(mContext, Content.UnDownDatas, anInt - 1);
                    }
                    //更新主页面上的下载几个显示
                    RxBus.getInstance().post(new DownEvent(DOWN_TAB));
                    //更新视图
                    if (type == ASSET_DOWN) {
                        RxBus.getInstance().post(new UIEvent(ASSET_DOWN));
                    }
                    ToastUtil.showToast("下载完成");
                } else {
                    mDatas.get(i).setState(ON_FINISH);
                    mMainAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    //显示下载完成的
    private void showComplete(Button bt_down, NumberProgressBar mNumberProgressBar, TextView mTvTitle) {

        //bt_down.setText("下载");
        mTvTitle.setText("已下载");
        mNumberProgressBar.setReachedBarColor(ColorsUtil.GREEN_DARK_TRANSLUCENT);
        mNumberProgressBar.setMax(1);
        mNumberProgressBar.setProgress(1);

        Log.d("DownFragment", "www");
    }

    //显示成功的
    @Override
    public void showSuccess(int type) {
        showNormal(type);
    }

    //显示错误的
    @Override
    public void showFault(int type, String wrong) {
        showError(type, wrong);
    }

    //显示进度条
    @Override
    public  void showProgress(int num, final int type) {
        synchronized (this){
            Log.d("showProgress", "index+mBean.getType():" + (num + ":" + type) + "(" + Thread.currentThread().getName());
            int[] ints = {num, type};
            Observable.just(ints)
                    .compose(this.<int[]>bindToLife())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new Action1<int[]>() {
                                @Override
                                public void call(int[] ints) {
                                    int num = ints[0];
                                    int type = ints[1];
                                    int i = mTypeList.indexOf(type + "");
                                    //保证是对的控件在渲染数据
                                    View view = ListViewUtil.getViewByPosition(i, mRv);
                                    if (view == null) {
                                        mDatas.get(i).setState(DownLoadBean.ON_PROGRESS);
                                    } else {
                                        MyProgressButton mcp = (MyProgressButton) view.findViewById(R.id.CP_down);
                                        //"下载","暂停","完成","错误","删除","更新"
                                        mcp.download(num);
                                    }
                                }
                            }
                    );
        }
    }


    //关键下载逻辑入口


    private MyDecoration mDecoration;

    private void initRecycle() {
        mRv.setLayoutManager(new LinearLayoutManager(mActivity));

        mRv.addItemDecoration(mDecoration = new MyDecoration(getActivity(), new MyDecoration.DecorationCallback() {
            @Override
            public long getGroupId(int position) {
                return mBigTypeList.indexOf(mDatas.get(position).bigType);
            }

            @Override
            public String getGroupFirstLine(int position) {
                return mDatas.get(position).bigType;
            }
        }));

        //下载
        mMainAdapter = new CommonAdapter<DownLoadBean>(mActivity, R.layout.item_down_main_ios, mDatas) {
            @Override
            protected void convert(final ViewHolder holder, final DownLoadBean bean, int position) {
                final MyProgressButton cb = holder.getView(R.id.CP_down);
                //(new String[]{"下载","暂停","完成","错误","删除","更新"};


                cb.setMulTimes(bean.methods==null?0:bean.methods.length);

                if (bean.count == 0) {
                    cb.normal(0); //max value is String[].length - 1;  call anytime;
                } else {
                    if (bean.getState() != DownLoadBean.ON_PROGRESS) {              //数据持有者状态
                        if (MyProgressButton.STATE.PROGRESS.equals(cb.getState())) {    //控件状态 因为被复用 所以可能会不一样
                            cb.setMorphingCircle(false);                //不加这两行可能胡出错
                            cb.setMorphingNormal(false);                //
                            cb.normal(bean.getState(),false);                 //更新为最新的状态
                        } else {
                            cb.setMorphingCircle(false);                //不加这两行可能胡出错
                            cb.setMorphingNormal(false);
                            cb.normal(bean.getState(), false);
                        }
                    } else {
                        if (MyProgressButton.STATE.PROGRESS.equals(cb.getState())) {     //保证控件是对的
                            cb.startDownLoad();
                            cb.download(bean.getProgressNum());         //设置为进度
                        }
                    }
                }
                holder.setText(R.id.tv_title, bean.name);
                //下载
                cb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (MyProgressButton.STATE.PROGRESS.equals(cb.getState())) {
                            ToastUtil.showToast("当前不支持暂停下载!");
                            cb.normal(3);
                            return;
                        }
                        if ("下载".equals(cb.getText()) || "更新".equals(cb.getText()) || "错误".equals(cb.getText()) ||
                                "完成".equals(cb.getText())
                                ) {
                            //视图

                            cb.startDownLoad();
                            //下载之前先清空
                            try {
                                if (bean.enties != null && bean.enties.length != 0) {
                                    for (int i = 0; i < bean.enties.length; i++) {
                                        mPresenter.clearData(Class.forName(bean.enties[i]));
                                    }
                                }
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            if (bean.way == WEB_SERVICE) {
                                mPresenter.gotoDown(bean);
                            } else if (bean.way == HTTP) {
                                mPresenter.gotoDown(bean.type);
                            }
                        } else if ("删除".equals(cb.getText())) {
                            try {
                                if (bean.enties != null && bean.enties.length != 0) {
                                    for (int i = 0; i < bean.enties.length; i++) {
                                        mPresenter.clearData(Class.forName(bean.enties[i]));
                                    }
                                }
                                cb.normal(0);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        };
        mRv.setAdapter(mMainAdapter);
    }

    /**
     * @param bt_down
     * @param bt_clear
     * @param IsClose  是否关闭下载
     */
    //改变按钮颜色
    private void changeButtton(Button bt_down, Button bt_clear, boolean IsClose) {
        if (IsClose) {
            bt_down.setClickable(false);                 //下载不可点击
            // bt_down.setText("正在下载");
            bt_down.setTextColor(getResources().getColor(R.color.colorGrey));
            bt_clear.setClickable(false);                 //下载不可点击
            bt_clear.setTextColor(getResources().getColor(R.color.colorGrey));
        } else {
            bt_down.setClickable(true);                 //下载可点击
            // bt_down.setText("下载");
            bt_down.setTextColor(getResources().getColor(R.color.black));
            bt_clear.setClickable(true);                 //下载可点击
            bt_clear.setTextColor(getResources().getColor(R.color.black));
        }
    }


    @Override
    public boolean onBackPressed() {
        return false;
    }
}
