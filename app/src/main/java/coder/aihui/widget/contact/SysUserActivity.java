package coder.aihui.widget.contact;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.data.bean.SYS_USER;
import coder.aihui.data.bean.gen.SYS_USERDao;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static coder.aihui.base.Content.CHECKED_USER_IDS;
import static coder.aihui.base.Content.CHECKED_USER_NAMES;


//仿联系人的 选择人 或者供货单位之类的
public class SysUserActivity extends AppActivity {


    @BindView(R.id.rv)
    RecyclerView mRv;
    /**
     * 右侧边栏导航区域
     */
    @BindView(R.id.indexBar)
    IndexBar     mIndexBar;
    /**
     * 显示指示器DialogText
     */
    @BindView(R.id.tvSideBarHint)
    TextView     mTvSideBarHint;

    @BindView(R.id.tv_ok)
    TextView mTvOk;


    private SuspensionDecoration mDecoration;

    private List<SYS_USER> mDatas = new ArrayList<>();
    private LinearLayoutManager mManager;
    private CommonAdapter       mCommonAdapter;
    private boolean IsMultiselect = false;       //是否是多选 根据是否多选 渲染不同的界面


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

        if (IsMultiselect) {
            //是否是多选
            mCommonAdapter = new CommonAdapter<SYS_USER>(this, R.layout.item_text_cb, mDatas) {
                @Override
                protected void convert(final ViewHolder holder, final SYS_USER bean, int position) {
                    holder.setText(R.id.tv_name, bean.getUSER_NAME());
                    holder.setChecked(R.id.cb, bean.IS_CHECKED());
                    holder.setOnClickListener(R.id.cb, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (bean.IS_CHECKED()) {
                                holder.setChecked(R.id.cb, !bean.IS_CHECKED());
                                bean.setIS_CHECKED(!bean.IS_CHECKED());
                            } else {
                                holder.setChecked(R.id.cb, !bean.IS_CHECKED());
                            }

                        }
                    });
                }
            };
        } else {
            mTvOk.setVisibility(View.GONE);
            mCommonAdapter = new CommonAdapter<SYS_USER>(this, R.layout.item_text_pd30, mDatas) {
                @Override
                protected void convert(ViewHolder holder, final SYS_USER bean, int position) {
                    holder.setText(R.id.tv_name, bean.getUSER_NAME());
                    holder.setOnClickListener(R.id.tv_name, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.putExtra("userId", bean.getUSER_ID());
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    });
                }
            };
        }


        mRv.setAdapter(mCommonAdapter);


        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(this, mDatas));
        mRv.setItemAnimator(new DefaultItemAnimator());
        //如果add两个，那么按照先后顺序，依次渲染。
        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //使用indexBar
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar

        //indexbar初始化
        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager

        //模拟线上加载数据


        mTvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> idList = new ArrayList();
                ArrayList<String> nameList = new ArrayList();
                for (SYS_USER data : mDatas) {
                    if (data.IS_CHECKED()) {
                        idList.add(data.getUSER_ID() + "");
                        nameList.add(data.getUSER_NAME() + "");
                    }
                }
                Intent intent = new Intent();
                intent.putStringArrayListExtra(CHECKED_USER_IDS, idList);
                intent.putStringArrayListExtra(CHECKED_USER_NAMES, nameList);
                setResult(RESULT_OK, intent);
            }
        });


        initDatas();


    }

    private void initGetIntent() {
        Intent intent = getIntent();
        IsMultiselect = intent.getBooleanExtra(Content.IS_MULTISELECT, false);

    }


    private void initDatas() {
        mDaoSession.getSYS_USERDao().queryBuilder()
                .orderAsc(SYS_USERDao.Properties.USER_NAME).rx().list()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<SYS_USER>>() {
                    @Override
                    public void call(List<SYS_USER> sys_users) {
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
}

