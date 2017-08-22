package coder.aihui.ui.assetcheck;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.DeptDecotor;
import coder.aihui.base.DeptView;
import coder.aihui.data.bean.IN_ASSET;
import coder.aihui.data.bean.PDA_ASSET_CHECK;
import coder.aihui.data.bean.gen.PDA_ASSET_CHECKDao;
import coder.aihui.data.normalbean.YzkBean;
import coder.aihui.data.normalbean.YzkSonBean;
import coder.aihui.manager.DeptLocationManager;
import coder.aihui.util.AndroidUtils;
import coder.aihui.util.SPUtil;
import coder.aihui.util.ToastUtil;
import coder.aihui.widget.ScrollViewWithExpandListView;
import coder.aihui.widget.jdaddressselector.ISelectAble;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class YzkActivity extends AppActivity implements DeptView {

    @BindView(R.id.tv_title)
    TextView  mTvTitle;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_num)
    TextView  mTvNum;

    @BindView(R.id.tv_ok)
    TextView                     mTvOk;
    @BindView(R.id.tv_changeDept)
    TextView                     mTvChangeDept;
    @BindView(R.id.el)
    ScrollViewWithExpandListView mEl;

    @BindView(R.id.ll_dept)
    LinearLayout mLlDept;
    private List fatherList = new ArrayList();
    private YzkAdapter        mYzkAdapter;
    private ArrayList<String> mIds;

    String changeDeptName;  //更改的科室名称
    String changeDeptId;    //更改的科室id
    private DeptDecotor mDeptDecotor;
    private DeptLocationManager mDeptLocationManager;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_yzk;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }


    @Override
    protected void initView() {
        mDeptLocationManager = new DeptLocationManager();
        mDeptDecotor = new DeptDecotor(this, this);
        mTvTitle.setText("预转科");
        mYzkAdapter = new YzkAdapter(this, fatherList);
        mEl.setAdapter(mYzkAdapter);
        initGetIntent();
    }

    private void initGetIntent() {

        Intent intent = getIntent();
        //获取到哪些要预转科
        mIds = intent.getStringArrayListExtra("ids");
        if (mIds == null || mIds.size() == 0) {
            ToastUtil.showToast("预转科数据为空!");
            finish();
        }

        //显示数目为几个
        mTvNum.setText(mIds.size() + "");

        YzkBean expandYzkBean = new YzkBean();
        expandYzkBean.setName("位置变更前");

        List<YzkSonBean> list = new ArrayList<>();
        for (int i = 0; i < mIds.size(); i++) {
            IN_ASSET load = mDaoSession.getIN_ASSETDao()
                    .load(Long.parseLong(mIds.get(i)));
            if (load != null) {
                YzkSonBean sonBean = new YzkSonBean(load.getWZMC(), load);
                list.add(sonBean);
            }
        }
        expandYzkBean.setSonBean(list);
        fatherList.add(expandYzkBean);
        mYzkAdapter.notifyDataSetChanged();

    }


    @OnClick({R.id.iv_back, R.id.tv_ok, R.id.ll_dept})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_ok:
                gotoSave();
                break;
            case R.id.ll_dept:
                getDept();
                break;
        }
    }


    //保存
    private void gotoSave() {
        final PDA_ASSET_CHECKDao assetCheckDao = mDaoSession.getPDA_ASSET_CHECKDao();
        Observable.from(assetCheckDao.queryBuilder().where(PDA_ASSET_CHECKDao.Properties.ASSET_ID.in(mIds))
                .where(PDA_ASSET_CHECKDao.Properties.QCPC.eq(AndroidUtils.getMaxPcid(mDaoSession)))
                .list()
        ).subscribeOn(Schedulers.io())
                .filter(new Func1<PDA_ASSET_CHECK, Boolean>() {
                    @Override
                    public Boolean call(PDA_ASSET_CHECK pda_asset_check) {
                        return pda_asset_check != null;
                    }
                }).doOnNext(new Action1<PDA_ASSET_CHECK>() {
            @Override
            public void call(PDA_ASSET_CHECK pda_asset_check) {
                //转科的逻辑
                pda_asset_check.setIS_CHANGE(1);
                pda_asset_check.setCHANGE_DEPT(changeDeptId);
                pda_asset_check.setASSET_CHECK_DATE(new Date());
                pda_asset_check.setASSET_CHECK_ID(SPUtil.getUserId(YzkActivity.this));
                pda_asset_check.setASSET_CHECK_NAME(SPUtil.getUserName(YzkActivity.this));
                pda_asset_check.setASSET_SYNC_DATE(null);
                pda_asset_check.setASSET_SYNC_FLAG(0);//设置为未同步状态
                assetCheckDao.update(pda_asset_check);
            }
        }).observeOn(Schedulers.io())
                .map(new Func1<PDA_ASSET_CHECK, Long>() {
                    @Override
                    public Long call(PDA_ASSET_CHECK pda_asset_check) {
                        return pda_asset_check.getCHECK_ID();
                    }
                }).toList()
                .map(new Func1<List<Long>, Long[]>() {
                    @Override
                    public Long[] call(List<Long> strings) {
                        return  strings.toArray(new Long[strings.size()]);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long[]>() {
                    @Override
                    public void call(Long[] longs) {
                        Intent intent = new Intent();
                        //万一要用  先传过去吧
                        intent.putExtra("changeDepts", longs);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
    }


    @Override
    public void onAddressSelected(ArrayList<ISelectAble> selectAbles) {

        mDeptLocationManager.solveDatas(selectAbles);
        changeDeptId = mDeptLocationManager.getDeptIds();
        changeDeptName = mDeptLocationManager.getDeptName();
        mTvChangeDept.setText(changeDeptName + "");
    }

    @Override
    public void closeDiaLog() {
        mDeptDecotor.closeDiaLog();
    }
    @Override
    public void getDept() {
        mDeptDecotor.getDept();
    }
}
