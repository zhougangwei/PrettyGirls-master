package coder.aihui.base;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.github.ikidou.fragmentBackHandler.FragmentBackHandler;
import com.squareup.leakcanary.RefWatcher;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import coder.aihui.app.BaseView;
import coder.aihui.app.MyApplication;
import coder.aihui.data.bean.gen.DaoSession;


/**
 * Created by renlei on 2016/5/23.
 */
public abstract class BaseFragment<P extends IBasePresenter> extends RxFragment implements BaseView , FragmentBackHandler {

    protected P mPresenter;

    protected BaseActivity   mActivity;
    private   View           mView;
    public    DaoSession     mDaoSession;
    public   SQLiteDatabase mDb;

    protected abstract void initView();

    //获取fragment布局文件ID
    protected abstract int getLayoutId();

    //获取宿主Activity
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }


    private Unbinder mUnbinder;

    private ViewStub mNetworkErrorLayout;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    //添加fragment
    public void addFragment(BaseFragment fragment) {
        if (null != fragment) {
            getHoldingActivity().addFragment(fragment);
        }
    }

    //移除fragment
    protected void removeFragment() {
        getHoldingActivity().removeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        mDaoSession = MyApplication.getIntstance().getDaoSession();
        mDb = MyApplication.getIntstance().getDatabase();
        mUnbinder = ButterKnife.bind(this, mView);
        mActivity = (BaseActivity) getActivity();

        initView();
        return mView;
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
        }


        if (mPresenter != null)
            mPresenter.onDestroy();//释放资源
        this.mPresenter = null;
        this.mActivity = null;
        this.mView = null;
        this.mUnbinder = null;
        RefWatcher refWatcher = MyApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

    //生命周期
    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }


}
