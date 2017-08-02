package coder.aihui.ui.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.http.WebServiceUtil;
import coder.aihui.ui.main.DownPresenter;
import coder.aihui.ui.main.DownView;
import coder.aihui.util.AndroidUtils;
import coder.aihui.util.SPUtil;
import coder.aihui.util.ToastUtil;

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
        initData();
    }

    private void initData() {

        String wsAddress = SPUtil.getString(ConfigActivity.this, Content.WS_ADDRESS, "");
        if (!TextUtils.isEmpty(wsAddress)) {
            mEtAddress.setText(wsAddress);      //回显地址
        }
        mPresenter = new DownPresenter(this, mDaoSession);
    }


    @OnClick(R.id.bt_sure)
    public void onViewClicked() {

        //测试地址
        testWsAddress();
    }


    private void testWsAddress() {
        String wsAddress = mEtAddress.getText().toString().trim();
        if (AndroidUtils.isConnect(ConfigActivity.this)) {
            ToastUtil.showToast("请检查网络!");
        }
        // 加载配置的WS地址

        String recode;
        try {
            String testUrl;
            if (wsAddress.contains("http://")) {
                int i = wsAddress.indexOf("/", 8);
                testUrl = wsAddress.substring(0, i==-1?wsAddress.length():i);
            } else {
                testUrl = "http://" + wsAddress;
            }
            WebServiceUtil ws = new WebServiceUtil();
            // String WSDL =
            // "http://iwell2014.oicp.net:8081/services/pdaws";
            String METHOD = "testConnect";
            recode = ws.execute(testUrl, METHOD).get();
            if (recode.substring(0, 1).equals("0")) {
                AndroidUtils.showErrorMsg("失败",
                        recode.substring(2, recode.length()), ConfigActivity.this);
            } else {
                ToastUtil.showToast("测试成功");
                //下载联系人
                //保存地址
                SPUtil.saveString(ConfigActivity.this
                        , Content.WS_ADDRESS, testUrl
                );
                downSysUser();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            AndroidUtils.showErrorMsg("失败", e.getMessage(), ConfigActivity.this);
        } catch (ExecutionException e) {
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
        mPresenter.gotoDown(new String[]{"getHrpUserDataJSON"}, new String[]{"coder.aihui.data.bean.SYS_USER"}, null, SYS_USER_DOWN, WEB_SERVICE);

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
        AndroidUtils.showErrorMsg("下载失败",wrong,ConfigActivity.this);
    }

    @Override
    public void showProgress(int num, int type) {
        mDonutProgress.setProgress(num);
    }

 /*   @Override
    public void showLoading(int type) {

    }

    @Override
    public void closeLoading(int type) {

    }*/
}
