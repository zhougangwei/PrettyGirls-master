package coder.aihui.ui.azys;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.data.bean.PUR_CONTRACT_PLAN;
import coder.aihui.data.bean.PUR_CONTRACT_PLAN_DETAIL;
import coder.aihui.data.bean.gen.PUR_CONTRACT_PLANDao;
import coder.aihui.data.bean.gen.PUR_CONTRACT_PLAN_DETAILDao;
import coder.aihui.ui.main.DownPresenter;
import coder.aihui.ui.main.DownView;
import coder.aihui.util.GsonUtil;
import coder.aihui.util.ToastUtil;
import coder.aihui.widget.contact.LessUserActivity;
import coder.aihui.widget.popwindow.MenuPopup;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static coder.aihui.app.MyApplication.daoSession;

public class AzysActivity extends AppActivity implements DownView {

    @BindView(R.id.iv_updown)
    LinearLayout         mIvUpdown;
    @BindView(R.id.iv_config)
    LinearLayout         mIvConfig;
    @BindView(R.id.iv_people)
    LinearLayout         mIvPeople;
    @BindView(R.id.tv_title)
    TextView             mTvTitle;
    @BindView(R.id.iv_back)
    ImageView            mIvBack;
    @BindView(R.id.sp_search)
    Spinner              mSpSearch;
    @BindView(R.id.et_search)
    EditText             mEtSearch;
    @BindView(R.id.tv_search)
    TextView             mTvSearch;
    @BindView(R.id.tb)
    TabLayout            mTb;

    @BindView(R.id.rv)
    RecyclerView         mRv;
    @BindView(R.id.back_top)
    FloatingActionButton mBackTop;

    private List<String> mUpDownList = new ArrayList<>();//上传下载按钮的数据填充
    private MenuPopup mUpdownPopup;
    private List<String> mPzList = new ArrayList<>();//配置按钮的数据填充
    private MenuPopup mPzPopup;

    //列表的数据
    private List<PUR_CONTRACT_PLAN> mDatas = new ArrayList<>();
    private CommonAdapter<PUR_CONTRACT_PLAN> mCommonAdapter;

    private final static int ALL       = 1;
    private final static int CHECKED   = 2;
    private final static int UNCHECKED = 3;
    private DownPresenter mDownPresenter;


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
        initRecycleView();

        mDownPresenter = new DownPresenter(this, mDaoSession);

        initData();



    }

    private void initData() {
        List<PUR_CONTRACT_PLAN> pub_companies = mDaoSession.getPUR_CONTRACT_PLANDao().loadAll();
        mDatas.addAll(pub_companies);
        mCommonAdapter.notifyDataSetChanged();

    }

    private void initRecycleView() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        mRv.setLayoutManager(linearLayout);
        mCommonAdapter = new CommonAdapter<PUR_CONTRACT_PLAN>(this, R.layout.item_azys_plan, mDatas) {
            @Override
            protected void convert(ViewHolder holder, final PUR_CONTRACT_PLAN bean, int position) {


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
        };

        mRv.setAdapter(mCommonAdapter);


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
        mRv.scrollToPosition(0);
    }


    //文字搜索
    private void doTextSearch() {


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
        List<PUR_CONTRACT_PLAN_DETAIL> ll = daoSession.getPUR_CONTRACT_PLAN_DETAILDao().queryBuilder()
                .where((PUR_CONTRACT_PLAN_DETAILDao.Properties.CHECK_STATUS.eq(1)))
                .where(PUR_CONTRACT_PLAN_DETAILDao.Properties.IS_UP.eq(2))
                .orderAsc(PUR_CONTRACT_PLAN_DETAILDao.Properties.CONTRACT_ID)
                .list();

        TreeMap<String, String> treeMap = new TreeMap<String, String>();

        for (int i = 0; i < ll.size(); i++) {

            //一个单号对应几个验收人
            ll.get(i).setYSSJ(new Date());

            daoSession.update(ll.get(i));
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
}
