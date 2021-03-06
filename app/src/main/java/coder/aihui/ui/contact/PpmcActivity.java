package coder.aihui.ui.contact;

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;

import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.data.bean.IN_MATERIALS_PPMC;
import coder.aihui.data.bean.gen.DaoSession;
import coder.aihui.data.bean.gen.IN_MATERIALS_PPMCDao;
import coder.aihui.util.ListUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static coder.aihui.base.Content.PPMC_IDS;
import static coder.aihui.base.Content.PPMC_NAMES;


//仿联系人的 选择人 或者供货单位之类的
public class PpmcActivity extends AppActivity {


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

    @BindView(R.id.et_search)
    EditText  mEtSearch;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView  mTvTitle;


    private SuspensionDecoration mDecoration;

    private List<IN_MATERIALS_PPMC> mDatas = new ArrayList<>();
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


        mTvTitle.setText("设备选择");
        initGetIntent();

        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));

        if (IsMultiselect) {
            //是否是多选
            mCommonAdapter = new CommonAdapter<IN_MATERIALS_PPMC>(this, R.layout.item_text_cb, mDatas) {
                @Override
                protected void convert(final ViewHolder holder, final IN_MATERIALS_PPMC bean, int position) {
                    holder.setText(R.id.tv_name, bean.getMC());
                }
            };
        } else {
            mTvOk.setVisibility(View.GONE);
            mCommonAdapter = new CommonAdapter<IN_MATERIALS_PPMC>(this, R.layout.item_text_pd20, mDatas) {
                @Override
                protected void convert(ViewHolder holder, final IN_MATERIALS_PPMC bean, int position) {
                    holder.setText(R.id.tv_name, bean.getMC());
                    holder.setOnClickListener(R.id.tv_name, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.putExtra(PPMC_IDS, bean.getID());
                            intent.putExtra(PPMC_NAMES, bean.getMC());
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


        mTvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> idList = new ArrayList();
                ArrayList<String> nameList = new ArrayList();
                for (IN_MATERIALS_PPMC data : mDatas) {
                    if (data.getIS_CHECKED()) {
                        idList.add(data.getID() + "");
                        nameList.add(data.getMC() + "");
                    }
                }
                Intent intent = new Intent();
                intent.putStringArrayListExtra(PPMC_IDS, idList);
                intent.putStringArrayListExtra(PPMC_NAMES, nameList);
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
            daoSession.getIN_MATERIALS_PPMCDao().queryBuilder().where(IN_MATERIALS_PPMCDao.Properties.MC.like(text.toString() == null ? "" : "%" + text.toString() + "%"))
                    .rx().list().filter(new Func1<List<IN_MATERIALS_PPMC>, Boolean>() {
                @Override
                public Boolean call(List<IN_MATERIALS_PPMC> in_materials_wzmcs) {
                    return in_materials_wzmcs != null && in_materials_wzmcs.size() != 0;
                }

            }).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<List<IN_MATERIALS_PPMC>>() {
                        @Override
                        public void call(List<IN_MATERIALS_PPMC> in_materials_wzmcs) {
                            mDatas.clear();
                            mDatas.addAll(in_materials_wzmcs);
                            updateDatas();
                        }
                    });
        } else {
            return true;
        }
        return false;

    }

    private void initGetIntent() {
        Intent intent = getIntent();
        IsMultiselect = intent.getBooleanExtra(Content.IS_MULTISELECT, false);
        String stringExtra = intent.getStringExtra(PPMC_IDS);
        List<String> list = ListUtils.StringsTolist(stringExtra);
        for (IN_MATERIALS_PPMC data : mDatas) {
            if (list.contains(data.getID() + "")) {
                data.setIS_CHECKED(true);
            }
        }
    }


    private void initDatas() {
        mDaoSession.getIN_MATERIALS_PPMCDao().queryBuilder()
                .orderAsc(IN_MATERIALS_PPMCDao.Properties.MC).rx().list()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<IN_MATERIALS_PPMC>>() {
                    @Override
                    public void call(List<IN_MATERIALS_PPMC> sys_users) {
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



    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}

