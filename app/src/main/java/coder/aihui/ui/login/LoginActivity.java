package coder.aihui.ui.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.data.bean.SYS_USER;
import coder.aihui.data.bean.gen.SYS_USERDao;
import coder.aihui.ui.main.MainActivity;
import coder.aihui.util.MD5Util;
import coder.aihui.util.SPUtil;
import coder.aihui.util.ToastUtil;

public class LoginActivity extends AppActivity {


    @BindView(R.id.et_user)
    EditText     mEtUser;
    @BindView(R.id.et_password)
    EditText     mEtPassword;
    @BindView(R.id.tv_config)
    TextView     mTvConfig;
    @BindView(R.id.button)
    Button       mButton;
    @BindView(R.id.login_progress)
    ProgressBar  mProgressView;
    @BindView(R.id.login_form)
    LinearLayout mLoginFormView;

    private UserLoginTask mAuthTask = null;
    private boolean       isLogin   = false;


    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

    @Override
    protected void initView() {


        isLogin = SPUtil.getBoolean(this, "isLogin", false);
        //如果是已经登录的 就直接进去了
        if (isLogin) {
            startActivity(new Intent(LoginActivity.this
                    , MainActivity.class
            ));
            finish();
        }

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }


    @OnClick({R.id.tv_config, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_config:
                //跳转到配置界面
                startActivity(new Intent(this, ConfigActivity.class));
                break;
            case R.id.button:
                attemptLogin();
                break;
        }
    }


    //对比本地数据库 然后查询
    private void attemptLogin() {


        mEtUser.setError(null);
        mEtPassword.setError(null);
        // Store values at the time of the login attempt.
        String userId = mEtUser.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid userId address.
        if (TextUtils.isEmpty(userId)) {

            SPUtil.saveString(this, "userName", userId);

            //mEtUser.setError(getString(R.string.error_field_required));
            focusView = mEtUser;
            cancel = true;
        } else if (!isUserValid(userId)) {
            //mEtUser.setError(getString(R.string.error_user));
            focusView = mEtUser;
            cancel = true;
        }
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            //mEtPassword.setError(getString(R.string.error_invalid_password));
            focusView = mEtPassword;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
            //mEtPassword.setError(getString(R.string.action_sign_in_short));
            focusView = mEtPassword;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(userId, password);
            mAuthTask.execute((Void) null);
        }

    }


    //可以写入密码的规范  比如说 几位数 只能是数字等的正则
    private boolean isPasswordValid(String password) {

        return true;
    }

    //同上
    private boolean isUserValid(String userId) {
        return true;
    }


    //显示进度条
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply showDialog
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String userId;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            userId = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return false;
            }
            String pwdMd5 = MD5Util.getMD5(mPassword).toLowerCase();

            SYS_USERDao sys_dao = mDaoSession.getSYS_USERDao();
            List<SYS_USER> list = sys_dao.queryBuilder().where(SYS_USERDao.Properties.USER_ACCOUNT.eq(userId), SYS_USERDao.Properties.USER_PSWD.eq(pwdMd5)).list();
            int count = list.size();
            if (count > 0) {
                SYS_USER ss = list.get(0);

                if (SPUtil.getBoolean(LoginActivity.this, Content.IS_REMEMBER_PW, false)) {
                    SPUtil.saveString(LoginActivity.this, Content.PASSWORD, mPassword);
                    SPUtil.saveString(LoginActivity.this, Content.USER_ACCOUNT, userId);
                }
                SPUtil.saveString(LoginActivity.this, "userName", ss.getUSER_NAME());
                SPUtil.saveString(LoginActivity.this, "userID", ss.getUSER_ID() + "");
                SPUtil.saveString(LoginActivity.this, "hospitalName", ss.getHOSPITAL_NAME());//登陆后放入医院
                SPUtil.saveString(LoginActivity.this, "userFuncs", ss.getUSER_FUNCS() != null ? ss.getUSER_FUNCS() : "");//登陆后放入用户权限
                SPUtil.saveBoolean(LoginActivity.this, "isLogin", true);


                //					activity.startActivityForResult(intent, IntentContent.INDEXHOME_INTENT);
                return true;
            } else {
                return false;
            }

        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                ToastUtil.showToast("登录成功");
                finish();
                startActivity(new Intent(LoginActivity.this
                        , MainActivity.class
                ));
            } else {
                // mEtPassword.setError(getString(R.string.error_incorrect_password));
                ToastUtil.showToast("密码错误");
                mEtPassword.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }

}
