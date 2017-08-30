package coder.aihui.ui.pxgl;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import coder.aihui.data.normalbean.FjBean;
import coder.aihui.data.normalbean.UpPicBean;
import coder.aihui.ui.main.DownPresenter;
import coder.aihui.ui.main.DownView;
import coder.aihui.util.AndroidUtils;
import coder.aihui.util.GsonUtil;
import coder.aihui.util.ListUtils;
import coder.aihui.util.ToastUtil;
import coder.aihui.util.UpPicClient;
import coder.aihui.util.viewutil.ProcessDialogUtil;
import coder.aihui.widget.MyProgressDialog;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static coder.aihui.app.MyApplication.daoSession;
import static coder.aihui.ui.main.DownPresenter.PXGL_UP;

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
    private DownPresenter            mDownPresenter;
    private List<PXGL_SAVE>          mUpList;
    private MyProgressDialog         mProgressDialog;

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


        mProgressDialog = ProcessDialogUtil.createNoCancelDialog("上传安装验收数据", this);


        //先得上传照片

        mUpList = mDaoSession.getPXGL_SAVEDao().queryBuilder()
                .where(PXGL_SAVEDao.Properties.IS_UP.eq(0))
                .orderAsc(PXGL_SAVEDao.Properties.IS_UP)
                .list();

        List<String> getPXFJ_json = ListUtils.ListFiled2list(mUpList, "getPXFJ_JSON", PXGL_SAVE.class); //获取所有的附件

        List<String> fjUrlList = new ArrayList<>();            //装文件地址的集合
        //获取所有的文件
        for (String s : getPXFJ_json) {
            List<FjBean> flList = GsonUtil.parseJsonToList(s, new TypeToken<List<FjBean>>() {
            }.getType());
            for (FjBean fjBean : flList) {
                if (!TextUtils.isEmpty(fjBean.getPXJL_FILE_PATH())) {
                    fjUrlList.add(fjBean.getPXJL_FILE_PATH());
                }
            }
        }
        new UpPicClient.Builder()
                .setImgUrls(fjUrlList)
                .setMessage("上传附件")
                .setProgressDialog(mProgressDialog)
                .setOnDefaultBackResult(new UpPicClient.OnDefaultResult() {
                    @Override
                    public void gotoSuccess(List<UpPicBean> recode) {
                        super.gotoSuccess(recode);
                        solvePicData(recode);
                    }
                }).build().uploadImg("");

    }

    private void solvePicData(List<UpPicBean> recode) {

        List<FjBean> fjUrlList = new ArrayList<>();            //装文件对象的集合
        //获取所有的文件
        for (int i = 0; i < mUpList.size(); i++) {
            PXGL_SAVE pxgl_save = mUpList.get(i);
            String pxfj_json = pxgl_save.getPXFJ_JSON();
            List<FjBean> flList = GsonUtil.parseJsonToList(pxfj_json, new TypeToken<List<FjBean>>() {
            }.getType());
            for (FjBean fjBean : flList) {
                fjBean.setPxglId(pxgl_save.getID());
                fjUrlList.add(fjBean);
            }
        }
        //将Id给他
        for (FjBean fjBean : fjUrlList) {
            for (UpPicBean upPicBean : recode) {
                if (upPicBean.getOldFileName().equals(fjBean.getPXJL_FILE_PATH())) {
                    fjBean.setPXJL_FILE_ID(upPicBean.getFileId());
                }
            }
        }
        for (int j = 0; j < mUpList.size(); j++) {
            PXGL_SAVE pxgl_save = mUpList.get(j);
            ArrayList<FjBean> fjBeanList = new ArrayList<>();       //重新要用来覆盖原FjBean的集合
            for (int i = 0; i < fjUrlList.size(); i++) {
                FjBean fjBean = fjUrlList.get(i);
                if (fjBean.getPxglId() == pxgl_save.getID()) {
                    fjBeanList.add(fjBean);
                }
            }
            if (fjBeanList.size() != 0) {
                pxgl_save.setPXFJ_JSON(GsonUtil.parseListToJson(fjBeanList));
            }
        }


        //上传Json
        gotoUpJson();


    }


    //上传Json
    private void gotoUpJson() {
        Observable.from(mUpList).observeOn(Schedulers.io())
                .subscribe(new Action1<PXGL_SAVE>() {
                    @Override
                    public void call(PXGL_SAVE pxgl_save) {
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("pxjl_json", pxgl_save.getPXJL_JSON());         //记录
                        hashMap.put("pxfj_json", pxgl_save.getPXFJ_JSON());         //附件
                        hashMap.put("pxry_json", pxgl_save.getPXRY_JSON());         //人员
                        hashMap.put("pxsb_json", pxgl_save.getPXSB_JSON());        //设备
                        mDownPresenter.gotoUp(hashMap, PXGL_UP);
                    }
                });
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
    public void showFault(int type, final String wrong) {
        Observable.just(type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        ToastUtil.showToast("上传失败!");
                        AndroidUtils.showErrorMsg("下载失败", wrong,PxglListActivity.this);
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
                        mProgressDialog.setProgress(integer);
                    }
                });

    }
}
