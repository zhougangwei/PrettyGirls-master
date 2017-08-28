package coder.aihui.ui.contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.data.bean.PUB_COMPANY;
import coder.aihui.data.bean.gen.PUB_COMPANYDao;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static coder.aihui.base.Content.COMPANY_GHDW;
import static coder.aihui.base.Content.COMPANY_ID;
import static coder.aihui.base.Content.COMPANY_SCCJ;
import static coder.aihui.base.Content.COMPANY_TYPE;


//仿联系人的 选择人 或者供货单位之类的
public class ContactActivity extends AppActivity {


    @BindView(R.id.rv)
    RecyclerView   mRv;
    /**
     * 右侧边栏导航区域
     */
    @BindView(R.id.indexBar)
    IndexBar       mIndexBar;
    /**
     * 显示指示器DialogText
     */
    @BindView(R.id.tvSideBarHint)
    TextView       mTvSideBarHint;
    @BindView(R.id.iv_back)
    ImageView      mIvBack;
    @BindView(R.id.tv_title)
    TextView       mTvTitle;
    @BindView(R.id.search)
    RelativeLayout mSearch;


    private SuspensionDecoration mDecoration;

    private List<PUB_COMPANY> mDatas = new ArrayList<>();
    private LinearLayoutManager mManager;
    private CommonAdapter       mCommonAdapter;
    private int                 mCompanyType;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_contact;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

    @Override
    public void initView() {

        initGetIntent();

        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));

        mCommonAdapter = new CommonAdapter<PUB_COMPANY>(this, R.layout.item_text_pd20, mDatas) {

            @Override
            protected void convert(ViewHolder holder, final PUB_COMPANY bean, int position) {
                holder.setText(R.id.tv_name, bean.getMC());
                holder.setOnClickListener(R.id.tv_name, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra(COMPANY_ID, bean.getID());
                        intent.putExtra(COMPANY_TYPE, mCompanyType);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            }
        };

        mRv.setAdapter(mCommonAdapter);


        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(this, mDatas));
        mRv.setItemAnimator(new DefaultItemAnimator());
        //如果add两个，那么按照先后顺序，依次渲染。
        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //使用indexBar
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView


        //indexbar初始化
        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager

        //模拟线上加载数据
        initDatas();


    }

    private void initGetIntent() {

        Intent intent = getIntent();
        mCompanyType = intent.getIntExtra(COMPANY_TYPE, -1);
        switch (mCompanyType) {
            case COMPANY_GHDW:
                mTvTitle.setText("供货单位");
                break;
            case COMPANY_SCCJ:
                mTvTitle.setText("生产厂家");
                break;
        }

    }


    private void initDatas() {
        mDaoSession.getPUB_COMPANYDao().queryBuilder()
                .where(PUB_COMPANYDao.Properties.LX.eq(mCompanyType)
                ).orderAsc(PUB_COMPANYDao.Properties.MC).rx().list()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<PUB_COMPANY>>() {
                    @Override
                    public void call(List<PUB_COMPANY> sys_users) {
                        mDatas.clear();
                        mDatas.addAll(sys_users);
                        mCommonAdapter.notifyDataSetChanged();
                        mIndexBar.setmSourceDatas(mDatas)//设置数据
                                .invalidate();
                        mDecoration.setmDatas(mDatas);
                    }
                });
    }

    public void updateDatas() {
        mIndexBar.setmSourceDatas(mDatas)
                .invalidate();
        mCommonAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}

