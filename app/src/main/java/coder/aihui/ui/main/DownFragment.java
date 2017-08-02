package coder.aihui.ui.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import coder.aihui.R;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.rxbus.event.MainEvent;
import coder.aihui.util.AndroidUtils;
import coder.aihui.util.ColorsUtil;
import coder.aihui.util.SPUtil;
import coder.aihui.util.ToastUtil;
import coder.aihui.util.viewutil.TextViewUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static coder.aihui.app.MyApplication.mContext;
import static coder.aihui.ui.main.DownPresenter.ASSET_DOWN;
import static coder.aihui.ui.main.DownPresenter.COMPANY_DOWN;
import static coder.aihui.ui.main.DownPresenter.HTTP;
import static coder.aihui.ui.main.DownPresenter.INIT_DOWN;
import static coder.aihui.ui.main.DownPresenter.INSPECT_INIT_DOWN;
import static coder.aihui.ui.main.DownPresenter.INSPECT_PLAN_DOWN;
import static coder.aihui.ui.main.DownPresenter.INSPECT_TEMPLETITEM_DOWN;
import static coder.aihui.ui.main.DownPresenter.PUR_CONTRACT_PLAN_DOWN;
import static coder.aihui.ui.main.DownPresenter.PXGL_SB_DOWN;
import static coder.aihui.ui.main.DownPresenter.WEB_SERVICE;

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
    private List<String> mTextNameList = new ArrayList();     //文本名字


    @Override
    protected void initView() {

        //初始化名字 以后要加就加这里
        initTextName();

        SPUtil.saveInt(mContext, Content.UnDownDatas, mDatas.size());   //显示几个小红点
        //更新显示 数目未下载数目
        ((MainActivity) mActivity).updateUnreadCount();

        mPresenter = new DownPresenter(this, mDaoSession);
        mPresenter.registerRxBus(MainEvent.class, new Action1<MainEvent>() {
            @Override
            public void call(MainEvent mainEvent) {
            }
        });

        //初始话列表的下载
        initRecycle();

    }

    private void initTextName() {
        mTextNameList.clear();
        mTextNameList.add("初始化");
        mTextNameList.add("台账下载");
        mTextNameList.add("公司下载");
        mTextNameList.add("巡检下载");
        mTextNameList.add("巡检初始下载");
        mTextNameList.add("巡检模板下载");
        mTextNameList.add("安装验收下载");
        mTextNameList.add("培训管理下载");

        //查询台账的已有数目
        mDatas.clear();


        //下载台账初始化
        DownLoadBean downLoadBean = new DownLoadBean();
        downLoadBean.name = mTextNameList.get(0);
        downLoadBean.count = mDaoSession.getSYS_DEPTDao().count() == 0 ? 0 : Integer.parseInt(mDaoSession.getSYS_DEPTDao().count() + "");
        downLoadBean.enties = new String[]{"coder.aihui.data.bean.PUB_DICTIONARY_ITEM", "coder.aihui.data.bean.SYS_DEPT", "coder.aihui.data.bean.IN_STORE_QC", "coder.aihui.data.bean.PUB_DICTIONARY_ITEM"};
        downLoadBean.methods = new String[]{"getHrpPubDictDataJSON2", "getHrpDeptDataJSON", "getHrpInStoreQcDataJSON", "getHrpPubDictDataJSON"};
        downLoadBean.way = WEB_SERVICE;
        downLoadBean.type = INIT_DOWN;

        final String[] pars0 = new String[]{"007083"};
        final List<String[]> pars0List = new ArrayList<>();
        pars0List.add(pars0);
        downLoadBean.pars = pars0List;
        mDatas.add(downLoadBean);


        DownLoadBean assetBean = new DownLoadBean();

        String userId = SPUtil.getString(mContext, "userId", "1");
        final String[] pars = new String[]{userId};
        final List<String[]> parss = new ArrayList<>();
        parss.add(pars);

        //下载台账
        assetBean.name = mTextNameList.get(1);
        assetBean.count = mDaoSession.getIN_ASSETDao().count() == 0 ? 0 : Integer.parseInt(mDaoSession.getIN_ASSETDao().count() + "");
        assetBean.enties = new String[]{"coder.aihui.data.bean.IN_ASSET"};
        assetBean.methods = new String[]{"getHrpInAssetDataJSON3"};
        assetBean.way = WEB_SERVICE;
        assetBean.type = ASSET_DOWN;
        assetBean.pars = parss;
        mDatas.add(assetBean);


        //下载公司
        DownLoadBean companyBean = new DownLoadBean();
        companyBean.name = mTextNameList.get(2);
        companyBean.count = mDaoSession.getIN_ASSETDao().count() == 0 ? 0 : Integer.parseInt(mDaoSession.getPUB_COMPANYDao().count() + "");
        companyBean.way = HTTP;
        companyBean.type = COMPANY_DOWN;
        mDatas.add(companyBean);

        //下载巡检计划
        DownLoadBean planBean = new DownLoadBean();
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"XJ", AndroidUtils.getPlanPras("XJ", "1", mDaoSession)});
        planBean.name = mTextNameList.get(3);
        planBean.count = mDaoSession.getIN_ASSETDao().count() == 0 ? 0 : Integer.parseInt(mDaoSession.getINSPECT_PLANDao().count() + "");
        planBean.enties = new String[]{"coder.aihui.data.bean.INSPECT_PLAN"};
        planBean.methods = new String[]{"getHrpInspectPlanDataJSON4"};
        planBean.way = WEB_SERVICE;
        planBean.type = INSPECT_PLAN_DOWN;
        planBean.pars = list;
        mDatas.add(planBean);


        //下载巡检初始化
        DownLoadBean inspectInitBean = new DownLoadBean();

        List<String[]> list2 = new ArrayList<>();
        String[] pars2 = new String[]{"007119,007120"};
        list2.add(pars2);

        inspectInitBean.name = mTextNameList.get(4);
        inspectInitBean.count = mDaoSession.getINSPECT_EXT_EXECUTORDao().count() == 0 ? 0 : Integer.parseInt(mDaoSession.getINSPECT_EXT_EXECUTORDao().count() + "");
        inspectInitBean.enties = new String[]{"coder.aihui.data.bean.PUB_DICTIONARY_ITEM", "coder.aihui.data.bean.REPAIR_PLACE", "coder.aihui.data.bean.INSPECT_EXT", "coder.aihui.data.bean.INSPECT_EXT_EXECUTOR", "coder.aihui.data.bean.SYS_PARAM", "coder.aihui.data.bean.INSPECT_GROUP"};
        inspectInitBean.methods = new String[]{"getHrpPubDictDataJSON2", "getHrpRePlaceDataJSON", "getHrpInspectExtDataJSON", "getHrpInspectExtExecDataJSON", "getHrpParamDataJSON", "getHrpInspectGroupDataJSON"};
        inspectInitBean.type = INSPECT_INIT_DOWN;
        inspectInitBean.way = WEB_SERVICE;
        inspectInitBean.pars = list2;
        mDatas.add(inspectInitBean);


        //下载巡检模板
        DownLoadBean inspectTempletItemBean = new DownLoadBean();
        List<String[]> list3 = new ArrayList<>();
        String[] pars3 = {"XJ"};
        list3.add(pars3);
        inspectTempletItemBean.name = mTextNameList.get(5);
        inspectTempletItemBean.count = mDaoSession.getInspectTempletItemDao().count() == 0 ? 0 : Integer.parseInt(mDaoSession.getInspectTempletItemDao().count() + "");
        inspectTempletItemBean.enties = new String[]{"coder.aihui.data.bean.InspectTempletItem"};
        inspectTempletItemBean.methods = new String[]{"getHrpInspectTempletItemDataJSON"};
        inspectTempletItemBean.type = INSPECT_TEMPLETITEM_DOWN;
        inspectTempletItemBean.way = WEB_SERVICE;
        inspectTempletItemBean.pars = list2;
        mDatas.add(inspectTempletItemBean);

        //下载安装验收
        DownLoadBean pubContractPlanBean = new DownLoadBean();
        pubContractPlanBean.name = mTextNameList.get(6);
        pubContractPlanBean.count = mDaoSession.getPUR_CONTRACT_PLANDao().count() == 0 ? 0 : Integer.parseInt(mDaoSession.getPUR_CONTRACT_PLANDao().count() + "");
        pubContractPlanBean.type = PUR_CONTRACT_PLAN_DOWN;
        pubContractPlanBean.way = HTTP;
        mDatas.add(pubContractPlanBean);

        //下载培训管理
        DownLoadBean pxsbBean = new DownLoadBean();
        pxsbBean.name = mTextNameList.get(7);
        pxsbBean.count = mDaoSession.getIN_MATERIALS_WZMCDao().count() == 0 ? 0 : Integer.parseInt(mDaoSession.getIN_MATERIALS_WZMCDao().count() + "");
        pxsbBean.type = PXGL_SB_DOWN;
        pxsbBean.way = HTTP;
        mDatas.add(pxsbBean);

    }





    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }


    //显示错误页面
    public void showError(int type, final String wrong) {
        Observable.just(type)
                .compose(this.<Integer>bindToLife())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                View view = mRv.getChildAt(integer);//与前面添加集合对应
                TextView mTvTitle = (TextView) view.findViewById(R.id.tv_detail);
                Button bt_down = (Button) view.findViewById(R.id.bt_down);
                Button bt_clear = (Button) view.findViewById(R.id.bt_clear);
                changeButtton(bt_down, bt_clear, false);
                NumberProgressBar mNumberProgressBar = (NumberProgressBar) view.findViewById(R.id.number_progress_bar);
                mTvTitle.setText("下载(失败)");
                mNumberProgressBar.setReachedBarColor(ColorsUtil.RED);
                mNumberProgressBar.setMax(1);
                mNumberProgressBar.setProgress(0);
                TextViewUtils.changeTextColorRed(mTvTitle, 0, mTvTitle.getText().toString().length());

                //弹框显示错误原因
                AndroidUtils.showErrorMsg("下载失败", wrong, getActivity());

            }
        });


    }


    //下载成功的
    public void showNormal(int type) {

        Observable.just(type)
                .compose(this.<Integer>bindToLife())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer type) {                        //根据Type来进行显示哪个
                View view = mRv.getChildAt(type);//与前面添加集合对应
                view.findViewById(R.id.ll_detail).setVisibility(View.GONE);             //收缩下载

                Button bt_down = (Button) view.findViewById(R.id.bt_down);
                Button bt_clear = (Button) view.findViewById(R.id.bt_clear);
                //按钮颜色
                changeButtton(bt_down, bt_clear, false);
                TextView mTvTitle = (TextView) view.findViewById(R.id.tv_detail);

                //下载完成的
                NumberProgressBar mNumberProgressBar = (NumberProgressBar) view.findViewById(R.id.number_progress_bar);
                showComplete(bt_down, mNumberProgressBar, mTvTitle);

                Integer anInt = SPUtil.getInt(mContext, Content.UnDownDatas, 10);
                SPUtil.saveInt(mContext, Content.UnDownDatas, anInt - 1);
                //更新主页面上的下载几个显示
                ((MainActivity) mActivity).updateUnreadCount();
                ToastUtil.showToast("下载完成");
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

    }

    //显示成功的
    @Override
    public void showSuccess(int type) {
        showNormal(type);
    }

    //显示错误的
    @Override
    public void showFault(int type, String wrong) {
        showError(type,wrong);
    }

    //显示进度条
    @Override
    public void showProgress(int num, final int type) {
        int[] ints = {num, type};
        Observable.just(ints)
                .compose(this.<int[]>bindToLife())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Action1<int[]>() {
                            @Override
                            public void call(int[] ints) {
                                int num = ints[0];
                                int type = ints[1];
                                View view = mRv.getChildAt(type);//与前面添加集合对应
                                NumberProgressBar mNumberProgressBar0 = (NumberProgressBar) view.findViewById(R.id.number_progress_bar);

                                mNumberProgressBar0.setProgress(num);
                            }
                        }
                );
    }


    //关键下载逻辑入口

    private void initRecycle() {
        mRv.setLayoutManager(new LinearLayoutManager(mActivity));

        //下载
        mMainAdapter = new CommonAdapter<DownLoadBean>(mActivity, R.layout.item_down_main, mDatas) {
            @Override
            protected void convert(final ViewHolder holder, final DownLoadBean bean, int position) {

                if (bean.count != null || bean.count != 0) {
                    holder.setText(R.id.tv_detail, "已下载");
                    //显示完成的
                    showComplete((Button) holder.getView(R.id.bt_down), (NumberProgressBar) holder.getView(R.id.number_progress_bar)
                            , (TextView) holder.getView(R.id.tv_detail));
                } else {
                    holder.setText(R.id.tv_detail, "未下载");
                }
                holder.setText(R.id.tv_title, bean.name);


                //显示/隐藏
                holder.setOnClickListener(R.id.im_detail, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.getView(R.id.ll_detail).isShown()) {
                            holder.getView(R.id.ll_detail).setVisibility(View.GONE);
                        } else {
                            holder.getView(R.id.ll_detail).setVisibility(View.VISIBLE);
                        }
                    }
                });

                //下载
                holder.setOnClickListener(R.id.bt_down, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.setText(R.id.tv_detail, "下载中");
                        ((NumberProgressBar) holder.getView(R.id.number_progress_bar)).setProgress(0);
                        ((NumberProgressBar) holder.getView(R.id.number_progress_bar)).setMax(100);
                        changeButtton((Button) holder.getView(R.id.bt_down), (Button) holder.getView(R.id.bt_clear), true);
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
                            mPresenter.gotoDown(bean.methods, bean.enties, bean.pars, bean.type, bean.way);
                        } else if (bean.way == HTTP) {
                            //for (int i = 0; i < ; i++) {
                            mPresenter.gotoDown( bean.type);
                            //}
                        }
                    }
                });

                //清空
                holder.setOnClickListener(R.id.bt_clear, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.setText(R.id.tv_detail, "未下载");
                        ((NumberProgressBar) holder.getView(R.id.number_progress_bar)).setProgress(0);
                        try {
                            for (int i = 0; i < bean.enties.length; i++) {
                                mPresenter.clearData(Class.forName(bean.enties[i]));
                            }

                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
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


    //显示正在下载

   /* @Override
    public void showLoading(int type) {
        Observable.just(type)
                .compose(this.<Integer>bindToLife())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                switch (integer) {
                    case INIT_DOWN:

                        break;

                    case ASSET_DOWN:
                        View view = mRv.getChildAt(1);//与前面添加集合对应
                        NumberProgressBar mNumberProgressBar = (NumberProgressBar) view.findViewById(R.id.number_progress_bar);
                        break;

                }
            }
        });
    }

    //关闭正在下载
    @Override
    public void closeLoading(int type) {
        Observable.just(type)
                .compose(this.<Integer>bindToLife())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                switch (integer) {
                    case ASSET_DOWN:
                        View view = mRv.getChildAt(1);//与前面添加集合对应
                        break;
                }
            }
        });

    }
*/

    @Override
    public void onDestroy() {
        mPresenter.unregisterRxBus();
        super.onDestroy();
    }


}
