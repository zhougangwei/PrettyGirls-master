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
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.data.bean.PUR_CONTRACT_PLAN;
import coder.aihui.data.bean.gen.PUR_CONTRACT_PLANDao;
import coder.aihui.widget.contact.LessUserActivity;
import coder.aihui.widget.popwindow.MenuPopup;

public class AzysActivity extends AppActivity {

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
    @BindView(R.id.line)
    LinearLayout         mLine;
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
        initRecycleView();
        mTvTitle.setText("安装验收");

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
            mUpdownPopup = new MenuPopup(this, mUpDownList);
        }
        mUpdownPopup.showPopupWindow(mIvUpdown);

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

}
