package coder.aihui.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.http.WebServiceCallBackUtil;
import coder.aihui.data.bean.DownLoadBean;
import coder.aihui.ui.main.DownPresenter;
import coder.aihui.ui.main.DownView;
import coder.aihui.util.AndroidUtils;
import coder.aihui.util.SPUtil;
import coder.aihui.util.ToastUtil;
import coder.aihui.util.ViewUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static coder.aihui.ui.main.DownPresenter.SYS_USER_DOWN;
import static coder.aihui.ui.main.DownPresenter.WEB_SERVICE;


public class ConfigActivity extends AppActivity implements DownView {


    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @BindView(R.id.bt_sure)
    Button         mBtSure;
    @BindView(R.id.et_address)
    EditText       mEtAddress;
    @BindView(R.id.donut_progress)
    DonutProgress  mDonutProgress;
    @BindView(R.id.rl_progress)
    RelativeLayout mRlProgress;
    @BindView(R.id.iv_back)
    ImageView      mIvBack;
    @BindView(R.id.rb)
    RadioButton    mRb;

    private DownPresenter mPresenter;

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }


    protected int getContentViewId() {
        return R.layout.activity_config;
    }


    @Override
    protected void initView() {
        //初始化标题
        //initToolbar();
        mTvTitle.setText("新用户配置");
        initData();

        ViewUtils.canCancelRadioButton(mRb, new ViewUtils.onBackResult() {
            @Override
            public void backResult(boolean b) {

            }
        });

    }

    private void initData() {
        String wsAddress = SPUtil.getString(ConfigActivity.this, Content.WS_ADDRESS, "");
        if (!TextUtils.isEmpty(wsAddress)) {
            mEtAddress.setText(wsAddress);      //回显地址
        }
        mPresenter = new DownPresenter(this, mDaoSession);
    }


    @OnClick({R.id.bt_sure, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_sure:
                //测试地址
                testWsAddress();
                break;
            case R.id.iv_back:
                finish();
                break;
        }


    }


    private void testWsAddress() {
        String wsAddress = mEtAddress.getText().toString().trim();
        if (!AndroidUtils.isConnect(ConfigActivity.this)) {
            ToastUtil.showToast("请检查网络!");
        }
        // 加载配置的WS地址
        try {
            final String testUrl;
            if (wsAddress.contains("http://")) {
                int i = wsAddress.indexOf("/", 8);
                testUrl = wsAddress.substring(0, i == -1 ? wsAddress.length() : i) + "/";
            } else {
                testUrl = "http://" + wsAddress + "/";
            }

            // String WSDL =
            // "http://iwell2014.oicp.net:8081/services/pdaws";
            String METHOD = "testConnect";


            WebServiceCallBackUtil ws2 = new WebServiceCallBackUtil(new WebServiceCallBackUtil.OnCallBack() {
                @Override
                public void callBack(String recode) {
                    if (recode.substring(0, 1).equals("0")) {
                        AndroidUtils.showErrorMsg("失败",
                                recode.substring(2, recode.length()), ConfigActivity.this);
                    } else {
                        ToastUtil.showToast("测试成功");
                        SPUtil.saveString(ConfigActivity.this
                                , Content.WS_ADDRESS, testUrl
                        );
                        downSysUser();
                    }
                }
            });
            ws2.execute(testUrl, METHOD);

        } catch (Exception e) {
            e.printStackTrace();
            AndroidUtils.showErrorMsg("失败", e.getMessage(), ConfigActivity.this);

        }
    }


    private void downSysUser() {

        //动画
      /*  ObjectAnimator objectAnimator =ObjectAnimator.ofFloat(mActivityConfig, "alpha", 1.0f, 0.8f, 0.5f);
        objectAnimator.setDuration(300);

        objectAnimator.start();*/
        changeProgress(true);
        DownLoadBean bean = new DownLoadBean();
        bean.setMethods(new String[]{"getHrpUserDataJSON"});
        bean.setEnties(new String[]{"coder.aihui.data.bean.SYS_USER"});
        bean.setWay(WEB_SERVICE);
        bean.setType(SYS_USER_DOWN);
        mPresenter.gotoDown(bean);

    }


    //变更显示的进度
    private void changeProgress(boolean isshow) {
        if (isshow) {
            mDonutProgress.setMax(100);
            // mActivityConfig.setNotouch(true);
            mDonutProgress.setVisibility(View.VISIBLE);
            mRlProgress.setVisibility(View.VISIBLE);
            //mActivityConfig.setBackgroundResource(R.color.bgGrey);

        } else {
            // mActivityConfig.setNotouch(false);
            mDonutProgress.setVisibility(View.GONE);
            mRlProgress.setVisibility(View.GONE);
            //mActivityConfig.setBackgroundResource(R.color.bgBlue);
        }
    }

    @Override
    public void showSuccess(int type) {
        changeProgress(false);
        ToastUtil.showToast("下载成功");
        finish();
    }

    @Override
    public void showFault(int type, String wrong) {
        changeProgress(false);
        AndroidUtils.showErrorMsg("下载失败", wrong, ConfigActivity.this);
    }

    @Override
    public void showProgress(int num, int type) {

        Observable.just(num).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        mDonutProgress.setProgress(integer);
                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }



 /*   @Override
    public void showLoading(int type) {

    }

    @Override
    public void closeLoading(int type) {

    }*/
}
