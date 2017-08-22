package coder.aihui.widget.contact;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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
import coder.aihui.base.Content;
import coder.aihui.data.bean.SYS_USER;
import coder.aihui.data.bean.gen.DaoSession;
import coder.aihui.data.bean.gen.SYS_USERDao;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
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
    TextView       mTvOk;
    @BindView(R.id.iv_back)
    ImageView      mIvBack;
    @BindView(R.id.tv_title)
    TextView       mTvTitle;
    @BindView(R.id.et_search)
    EditText       mEtSearch;
    @BindView(R.id.search)
    RelativeLayout mSearch;


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
                            intent.putExtra("userName", bean.getUSER_NAME());
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
                finish();
            }
        });


        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                /*判断是否是“GO”键*/
                if ((event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) || actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE)
                    return doSearch(v, mEtSearch, mDaoSession);
                return false;
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

    //更新搜索数据
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

    private boolean doSearch(TextView v, EditText mEtSearch, DaoSession daoSession) {
/*隐藏软键盘*/
        InputMethodManager imm = (InputMethodManager) v
                .getContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(
                    v.getApplicationWindowToken(), 0);
        }
        Editable text = mEtSearch.getText();
        if (!TextUtils.isEmpty(text)) {
            daoSession.getSYS_USERDao().queryBuilder().where(SYS_USERDao.Properties.USER_NAME.like(text.toString() == null ? "" : "%" + text.toString() + "%"))

                    .rx().list().filter(new Func1<List<SYS_USER>, Boolean>() {
                @Override
                public Boolean call(List<SYS_USER> user) {
                    return user != null && user.size() != 0;
                }
            }).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<List<SYS_USER>>() {
                        @Override
                        public void call(List<SYS_USER> users) {
                            mDatas.clear();
                            mDatas.addAll(users);
                            updateDatas();

                        }
                    });

        } else {
            return true;
        }
        return false;
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;



        }

    }
}

