package coder.aihui.widget.contact;

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
    private CommonAdapter mCommonAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_less_user;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }


    private void initData() {

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


        Intent intent = getIntent();
        //已打钩的User集合
        ArrayList<Integer> userIdList = intent.getIntegerArrayListExtra("userIdList");

        if (userIdList != null && userIdList.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                if (userIdList.contains(list.get(i).id)) {
                    list.get(i).isCheck = true;         //回显打钩
                }
            }
        }

        mDatas.addAll(list);
        mCommonAdapter.notifyDataSetChanged();

    }

    @Override
    public void initView() {
        mTvTitle.setText("选择巡检人员");
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mCommonAdapter = new CommonAdapter<UserBean>(this, R.layout.item_text_cb, mDatas) {
            @Override
            protected void convert(ViewHolder holder, UserBean bean, int position) {

                String name = bean.name;
                Boolean ischeck = bean.isCheck;

                holder.setText(R.id.tv_name, name);
                holder.setChecked(R.id.cb, ischeck);
            }
        };
        mRv.setAdapter(mCommonAdapter);
        initData();
        getIntent();

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
        intent.putExtra("userIdList", userChooseIds);
        intent.putExtra("userNameList", userNameList);
        setResult(RESULT_OK, intent);
        finish();

    }


    class UserBean {
        String  name;
        Integer id;
        Boolean isCheck;
    }


}
