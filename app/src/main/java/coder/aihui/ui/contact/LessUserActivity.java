package coder.aihui.ui.contact;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import coder.aihui.data.bean.YsrBean;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static coder.aihui.base.Content.USER_ID_LIST;
import static coder.aihui.base.Content.USER_NAME_LIST;
import static coder.aihui.ui.main.DownPresenter.AZYS_DOWN;
import static coder.aihui.ui.main.DownPresenter.INSPECT_PLAN_DOWN;

public class LessUserActivity extends AppActivity {

    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.rv)
    RecyclerView mRv;

    @BindView(R.id.tv_ok)
    TextView mTvOk;
    private List<UserBean> mDatas = new ArrayList();     //对应的是id和name
    private CommonAdapter      mCommonAdapter;
    private ArrayList<Integer> mUserIdList; //已打钩的User集合

    @Override
    protected int getContentViewId() {
        return R.layout.activity_less_user;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }


    private void initXJData() {

        final List<UserBean> list = new ArrayList<>();      //临时数据存储
        Cursor cursor = mDb.rawQuery("select distinct a.INSEE__USER__ID as ID,ic_selected.USER__NAME as NAME from INSPECT__EXT__EXECUTOR a, SYS__USER ic_selected where a.INSEE__USER__ID=ic_selected.USER__ID ", new String[]{});
        cursor.moveToFirst();

        while (cursor.getPosition() != cursor.getCount()) {

            UserBean userBean = new UserBean();
            userBean.name = cursor.getString(cursor.getColumnIndex("NAME"));
            userBean.id = cursor.getInt(cursor.getColumnIndex("ID"));
            userBean.isCheck = false;
            list.add(userBean);
            cursor.moveToNext();
        }

        if (mUserIdList != null && mUserIdList.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                if (mUserIdList.contains(list.get(i).id)) {
                    list.get(i).isCheck = true;         //回显打钩
                }
            }
        }
        mDatas.clear();
        mDatas.addAll(list);
        mCommonAdapter.notifyDataSetChanged();

    }

    @Override
    public void initView() {
        mTvTitle.setText("选择巡检人员");
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mCommonAdapter = new CommonAdapter<UserBean>(this, R.layout.item_text_cb, mDatas) {
            @Override
            protected void convert(final ViewHolder holder, final UserBean bean, int position) {
                String name = bean.name;
                boolean ischeck = bean.isCheck;
                holder.setText(R.id.tv_name, name);


                holder.setChecked(R.id.cb, ischeck);

                holder.setOnClickListener(R.id.cb, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.setChecked(R.id.cb, !bean.getCheck());
                        bean.setCheck(!bean.getCheck());
                    }
                });


            }
        };
        mRv.setAdapter(mCommonAdapter);
        Intent intent = getIntent();
        int type = intent.getIntExtra("type", -1);
        mUserIdList = intent.getIntegerArrayListExtra(USER_ID_LIST);    //已点击的 回显

        switch (type) {
            case AZYS_DOWN:         //安装验收的
                initAZData();
                break;
            case INSPECT_PLAN_DOWN: //巡检
                initXJData();
                break;
            default:
                break;
        }
    }

    private void initAZData() {
        mDaoSession.getYsrBeanDao().queryBuilder().rx().oneByOne().subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Func1<YsrBean, UserBean>() {
                    @Override
                    public UserBean call(YsrBean ysrBean) {
                        UserBean userBean = new UserBean();
                        userBean.isCheck = false;
                        userBean.id = ysrBean.getPurYsrId().intValue();
                        userBean.name = ysrBean.getUserName();
                        return userBean;
                    }
                })
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<UserBean>>() {
                    @Override
                    public void call(List<UserBean> list) {
                        if (mUserIdList != null && mUserIdList.size() != 0) {
                            for (int i = 0; i < list.size(); i++) {
                                if (mUserIdList.contains(list.get(i).id)) {
                                    list.get(i).isCheck = true;         //回显打钩
                                }
                            }
                        }
                        mDatas.clear();
                        mDatas.addAll(list);
                        mCommonAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_ok:
                gotoSave();
                break;
        }


    }

    //去保存
    private void gotoSave() {
        ArrayList<Integer> userChooseIds = new ArrayList<>();
        ArrayList<String> userNameList = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            if (mDatas.get(i).isCheck) {
                userChooseIds.add(mDatas.get(i).id);
                userNameList.add(mDatas.get(i).name);
            }
        }
        Intent intent = new Intent();
        intent.putExtra(USER_ID_LIST, userChooseIds);
        intent.putExtra(USER_NAME_LIST, userNameList);
        setResult(RESULT_OK, intent);
        finish();

    }


    class UserBean {
        String  name;
        Integer id;
        Boolean isCheck;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Boolean getCheck() {
            return isCheck;
        }

        public void setCheck(Boolean check) {
            isCheck = check;
        }
    }


}
