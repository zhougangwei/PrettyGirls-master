package coder.aihui.ui.pxgl;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.data.bean.IN_MATERIALS_WZMC;
import coder.aihui.data.bean.PXGL_SAVE;
import coder.aihui.data.bean.PXJL;
import coder.aihui.data.bean.SYS_USER;
import coder.aihui.data.bean.gen.PXGL_SAVEDao;
import coder.aihui.ui.main.DownPresenter;
import coder.aihui.ui.main.DownView;
import coder.aihui.util.GsonUtil;
import coder.aihui.util.ToastUtil;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static coder.aihui.app.MyApplication.daoSession;
import static coder.aihui.ui.main.DownPresenter.PUR_CONTRACT_PLAN_UP;

public class PxglListActivity extends AppActivity implements DownView {

    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.iv_add)
    LinearLayout mIvAdd;
    @BindView(R.id.iv_updown)
    LinearLayout mIvUpdown;
    @BindView(R.id.rv)
    RecyclerView mRv;

    private List<PXGL_SAVE> mDatas = new ArrayList<>();
    private CommonAdapter<PXGL_SAVE> mAdapter;
    private DownPresenter mDownPresenter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_pxgl_list;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }


    @Override
    protected void initView() {

        mTvTitle.setText("培训管理");
        initRecycleView();
        initData();
        mDownPresenter = new DownPresenter(this, mDaoSession);
    }

    private void initData() {
        List<PXGL_SAVE> pxgl_saves = mDaoSession.getPXGL_SAVEDao().loadAll();
        mDatas.addAll(pxgl_saves);
    }

    private void initRecycleView() {
        mAdapter = new CommonAdapter<PXGL_SAVE>(this, R.layout.item_pxgl_list, mDatas) {
            @Override
            protected void convert(ViewHolder holder, final PXGL_SAVE bean, int position) {

                PXJL pxjl = GsonUtil.parseJsonToBean(bean.getPXJL_JSON(), PXJL.class);
                holder.setText(R.id.tv_title, pxjl.getPxzt() == null ? "" : pxjl.getPxzt());      //主题

                String pxsb_json = bean.getPXSB_JSON();     //设备
                List<IN_MATERIALS_WZMC> mcList = GsonUtil.parseJsonToList(pxsb_json, new TypeToken<List<IN_MATERIALS_WZMC>>() {
                }.getType());
                StringBuilder sb = new StringBuilder();
                if (mcList != null) {
                    for (int i = 0; i < mcList.size(); i++) {
                        if (i != mcList.size() - 1) {
                            sb.append(mcList.get(i).getWZMC()).append(",");
                        } else {
                            sb.append(mcList.get(i).getWZMC());
                        }
                    }
                }
                holder.setText(R.id.tv_sb, sb.toString());    //设备
                StringBuilder sbry = new StringBuilder();   //人员
                String pxry_json = bean.getPXRY_JSON();
                String[] split = pxry_json.split(",");
                //解析获得人员
                if (split != null) {
                    for (int i = 0; i < split.length; i++) {
                        SYS_USER sysBean = daoSession.getSYS_USERDao().load(Long.parseLong(split[i]));
                        sbry.append(sysBean.getUSER_NAME());
                    }
                }
                holder.setText(R.id.tv_name, sbry.toString());  //人员
                holder.setText(R.id.tv_time, TimeUtils.date2String(pxjl.getPxrq()));    //日期                    //

                //点击事件

                holder.setOnClickListener(R.id.rl, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PxglListActivity.this, PxglDetailActivity.class);
                        intent.putExtra("id", bean.getID());
                        startActivity(intent);
                    }
                });
            }
        };
        mRv.setAdapter(mAdapter);
    }

    @OnClick({R.id.iv_add, R.id.iv_updown, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add:
                gotoAdd();
                break;
            case R.id.iv_updown:
                gotoUpdata();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    //添加培训
    private void gotoAdd() {
        Intent intent = new Intent(this, PxglDetailActivity.class);
        startActivityForResult(intent, Content.PXGL_DETAIL_REQUEST_CODE);

    }

    //去上传数据
    private void gotoUpdata() {
        HashMap<String, String> map = new HashMap<>();


        mDownPresenter.gotoUp(map,PUR_CONTRACT_PLAN_UP);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //返回哪些添加到培训管理里
                case Content.PXGL_DETAIL_REQUEST_CODE:
                    //long id = data.getLongExtra("id", -1L);           //新增的
                    mDaoSession.getPXGL_SAVEDao().queryBuilder().orderAsc(PXGL_SAVEDao.Properties.ID).rx().list()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<List<PXGL_SAVE>>() {
                                @Override
                                public void call(List<PXGL_SAVE> pxgl_saves) {
                                    mDatas.clear();
                                    mDatas.addAll(pxgl_saves);
                                    mAdapter.notifyDataSetChanged();
                                }
                            });
                    break;
            }
        }

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
