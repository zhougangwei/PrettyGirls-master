package coder.aihui.adapter;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import butterknife.ButterKnife;
import coder.aihui.app.MyApplication;
import coder.aihui.data.bean.gen.DaoMaster;
import coder.aihui.data.bean.gen.DaoSession;
import coder.aihui.util.DBUtil;


public abstract class BaseHolder<T> {


    View view;
    public SQLiteDatabase db;
    public DaoMaster      daoMaster;
    public DaoSession     daoSession;

    public Activity mActivity;


    public BaseHolder() {

        // LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false)

        view =createItemView();
        //绑定view
        ButterKnife.bind(this, view);
        //领证
        view.setTag(this);

        DaoMaster.DevOpenHelper helper = DBUtil.getInstance(MyApplication.mContext);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

    }

    public BaseHolder(Activity activity) {

        // LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false)
        view =createItemView();
        //绑定view
        ButterKnife.bind(this, view);
        //领证
        view.setTag(this);

        this.mActivity = activity;

        DaoMaster.DevOpenHelper helper = DBUtil.getInstance(MyApplication.mContext);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

    }


    //创建条目,创建一个view
    protected abstract View createItemView();

    //返回一个view
    public View getView() {
        return view;
    }

    //更新view
    public abstract void
    bindView(T t,int position);



}
