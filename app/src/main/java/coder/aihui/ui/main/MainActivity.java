package coder.aihui.ui.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import butterknife.BindView;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.rxbus.RxBus;
import coder.aihui.rxbus.event.DownEvent;
import coder.aihui.rxbus.event.UIEvent;
import coder.aihui.util.FragmentFactory;
import coder.aihui.util.PermissionUtils;
import coder.aihui.util.SPUtil;
import coder.aihui.util.ToastUtil;
import rx.functions.Action1;

import static coder.aihui.app.MyApplication.mContext;
import static coder.aihui.ui.main.DownPresenter.ASSET_DOWN;


public class MainActivity extends AppActivity implements BottomNavigationBar.OnTabSelectedListener {


    @BindView(R.id.tv_title)
    TextView            mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar             mToolbar;
    @BindView(R.id.fl_content)
    FrameLayout         mFlContent;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
    private int[] titleIds = {R.string.main, R.string.down, R.string.my};
    private BadgeItem mBadgeItem;
    private long exitTime = 0;

    public static final int MAIN_TAB = 0;
    public static final int DOWN_TAB = 1;
    public static final int MY_TAB   = 2;


    @Override
    protected void initView() {
        //初始化标题
        initToolbar();
        //初始化底部导航栏
        initBottomNavigation();


        initRxBus();

        requstPermisson();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Content.HOME_REQUEST_CODE:
                RxBus.getInstance().post(new UIEvent(ASSET_DOWN));
                break;
        }
    }

    //事件处理
    private void initRxBus() {
        RxBus.getInstance().toObservable(DownEvent.class)
                .subscribe(new Action1<DownEvent>() {
                    @Override
                    public void call(DownEvent downEvent) {
                        switch (downEvent.getId()) {
                            case MAIN_TAB:
                                break;
                            case DOWN_TAB:
                                updateUnreadCount();
                                break;
                            case MY_TAB:
                                break;
                        }

                    }
                });
    }

    private void requstPermisson() {
        PermissionUtils.requestMultiPermissions(this, mPermissionGrant);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.fl_content;
    }


    //初始化Toolbar
    private void initToolbar() {
        mTvTitle.setText(titleIds[0]);
    }


    @Override
    protected BaseFragment getFirstFragment() {
        BaseFragment fragment = FragmentFactory.getFragment(0);
        return fragment;
    }

    //初始化底边栏
    private void initBottomNavigation() {

        //底边栏主页
        BottomNavigationItem MainItem = new BottomNavigationItem(R.mipmap.sy_pre, titleIds[0]);

             /* MainItem.setActiveColorResource(R.color.btn_normal);//选中的颜色
                MainItem.setActiveColorResource();//没选中的颜色*/
        mBottomNavigationBar.addItem(MainItem);



        //底边栏下载
        BottomNavigationItem DownItem = new BottomNavigationItem(R.mipmap.xz_pre, titleIds[1]);
        //        DownItem.setActiveColor(getResources().getColor(R.color.btn_normal));//选中的颜色
        //        DownItem.setInActiveColor(getResources().getColor(R.color.inActive));//没选中的颜色
        mBadgeItem = new BadgeItem();
        mBadgeItem.setGravity(Gravity.RIGHT);
        mBadgeItem.setTextColorResource(R.color.white);
        mBadgeItem.setBackgroundColor(R.color.red);
        updateUnreadCount();            //用来显示 数据的
        DownItem.setBadgeItem(mBadgeItem);
        mBottomNavigationBar.addItem(DownItem);


        BottomNavigationItem pluginItem = new BottomNavigationItem(R.mipmap.yh_pre, titleIds[2]);
        //        pluginItem.setActiveColor(getResources().getColor(R.color.btn_normal));//选中的颜色
        //        pluginItem.setInActiveColor(getResources().getColor(R.color.inActive));//没选中的颜色
        mBottomNavigationBar.addItem(pluginItem);

        mBottomNavigationBar.setActiveColor(R.color.Active);
        mBottomNavigationBar.setInActiveColor(R.color.inActive);

        mBottomNavigationBar.initialise();

        mBottomNavigationBar.setTabSelectedListener(this);
    }


    public void updateUnreadCount() {
        //获取所有没下载的数据
        int unreadMsgsCount = SPUtil.getInt(mContext, Content.UnDownDatas, 10);
        if (unreadMsgsCount > 99) {
            mBadgeItem.setText("99+");
            mBadgeItem.show(true);
        } else if (unreadMsgsCount > 0) {
            mBadgeItem.setText(unreadMsgsCount + "");
            mBadgeItem.show(true);
        } else {
            mBadgeItem.hide(true);
        }

    }


    //点击了哪个fragment
    @Override
    public void onTabSelected(int position) {
        /**
         * 先判断当前Fragment是否被添加到了MainActivity中
         * 如果添加了则直接显示即可
         * 如果没有添加则添加，然后显示
         */
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        BaseFragment fragment = FragmentFactory.getFragment(position);
        if (!fragment.isAdded()) {
            // addFragment(getFragmentContentId(),fragment,""+position);
            transaction.add(getFragmentContentId(), fragment, "" + position);
        }
        transaction.show(fragment).commit();

        mTvTitle.setText(titleIds[position]);
    }

    @Override
    public void onTabUnselected(int position) {
        getSupportFragmentManager().beginTransaction().hide(FragmentFactory.getFragment(position)).commit();
    }

    @Override
    public void onTabReselected(int position) {

    }

    private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtils.CODE_RECORD_AUDIO:
                    Toast.makeText(MainActivity.this, "Result Permission Grant CODE_RECORD_AUDIO", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_GET_ACCOUNTS:
                    Toast.makeText(MainActivity.this, "Result Permission Grant CODE_GET_ACCOUNTS", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_READ_PHONE_STATE:
                    Toast.makeText(MainActivity.this, "Result Permission Grant CODE_READ_PHONE_STATE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_CALL_PHONE:
                    Toast.makeText(MainActivity.this, "Result Permission Grant CODE_CALL_PHONE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_CAMERA:
                    Toast.makeText(MainActivity.this, "Result Permission Grant CODE_CAMERA", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_ACCESS_FINE_LOCATION:
                    Toast.makeText(MainActivity.this, "Result Permission Grant CODE_ACCESS_FINE_LOCATION", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_ACCESS_COARSE_LOCATION:
                    Toast.makeText(MainActivity.this, "Result Permission Grant CODE_ACCESS_COARSE_LOCATION", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_READ_EXTERNAL_STORAGE:
                    Toast.makeText(MainActivity.this, "Result Permission Grant CODE_READ_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE:
                    Toast.makeText(MainActivity.this, "Result Permission Grant CODE_WRITE_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, mPermissionGrant);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.showToast("再按一次退出程序!");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }

    }
}
