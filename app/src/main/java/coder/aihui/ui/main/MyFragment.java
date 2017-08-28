package coder.aihui.ui.main;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.BaseFragment;
import coder.aihui.rxbus.RxBus;
import coder.aihui.rxbus.event.UIEvent;
import coder.aihui.ui.login.LoginActivity;
import coder.aihui.util.AndroidUtils;
import coder.aihui.util.LogUtil;
import coder.aihui.util.SPUtil;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static coder.aihui.ui.main.DownPresenter.ASSET_DOWN;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/3/17 15:31
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/3/17$
 * @ 更新描述  ${TODO}
 */

public class MyFragment extends BaseFragment<MyPresenter> {
    @BindView(R.id.tv_sum)
    TextView       mTvSum;
    @BindView(R.id.rl_use)
    RelativeLayout mRlUse;
    @BindView(R.id.rl_version)
    RelativeLayout mRlVersion;
    @BindView(R.id.tv_exit)
    TextView       mTvExit;
    @BindView(R.id.tv_check)
    TextView       mTvCheck;
    @BindView(R.id.tv_uncheck)
    TextView       mTvUncheck;
    @BindView(R.id.tv_version)
    TextView       mTvVersion;


    @Override
    protected void initView() {

        //版本号
        try {
            PackageInfo packageInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
            mTvVersion.setText(packageInfo.versionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //初始化rxBus
        initRxBus();

        queryAssetInfo();
    }

    private void initRxBus() {
        RxBus.getInstance().toObservable(UIEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UIEvent>() {
                    @Override
                    public void call(UIEvent uiEvent) {
                        switch (uiEvent.getId()) {
                            //说明是台账下载过来,更新台账数目
                            case ASSET_DOWN:
                                queryAssetInfo();
                                break;
                        }

                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }


    @OnClick({R.id.rl_use, R.id.rl_version, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_use:           //使用说明
                break;
            case R.id.rl_version:       //版本信息
                break;
            case R.id.tv_exit:          //退出
                SPUtil.saveBoolean(getActivity(), "isLogin", false);
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
        }
    }


    //显示按钮数目的
    public void queryAssetInfo() {

        String qcid = AndroidUtils.getMaxPcid(mDaoSession);     //初始化获取最大期次
        // 取最大的盘点期次
        if (TextUtils.isEmpty(qcid)) {
            AndroidUtils.showErrorMsg("错误", "盘点期次未下载", getActivity());
            return;
        }
        // 盘点ID，最近日期，开始日期
        long sysl = 0l;
        long pdsl = 0l;
        //增加侧边条件判断
        List<String> parList = new ArrayList<String>();
        parList.add(qcid);
        StringBuffer sql = new StringBuffer();
        sql.append("select count(ic_selected.ASSET__ID) as YPSL, max(ic_selected.ASSET__CHECK__DATE) as ZJRQ, min(ic_selected.ASSET__CHECK__DATE) as KSRQ ,count(a.ID) as WPSL");
        sql.append(" from IN__ASSET a left join PDA__ASSET__CHECK ic_selected on a.ID=ic_selected.ASSET__ID and ic_selected.QCPC=?");

        LogUtil.d("Myfragment", sql.toString());
        Cursor cursor2 = mDb.rawQuery(sql.toString(), (String[]) parList.toArray(new String[parList.size()]));
        cursor2.moveToFirst();
        while (cursor2.getPosition() != cursor2.getCount()) {
            pdsl = cursor2.getLong(0);
            sysl = cursor2.getLong(3);
            cursor2.moveToNext();
        }

        long noCheck = sysl - pdsl;
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("ZCTS", sysl);          //总数
            jsonObj.put("YQDS", pdsl);          //已清点
            jsonObj.put("WQDS", noCheck);       //未清点

            if (mTvSum != null) {
                mTvSum.setText(sysl + "");
            }
            if (mTvCheck != null) {
                mTvCheck.setText(pdsl + "");
            }
            if (mTvUncheck != null) {
                mTvUncheck.setText(noCheck + "");
            }

        } catch (Exception e) {
            e.printStackTrace();
            AndroidUtils.showErrorMsg("错误", e.getMessage(), getActivity());
        }

    }
}
