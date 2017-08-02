package coder.aihui.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;



import java.util.ArrayList;
import java.util.List;


public abstract class BasicAdapter<T> extends android.widget.BaseAdapter {


    //抽取就把里面特定的东西抽到外面来

    //集合
    public List<T> mShowItems = new ArrayList<>();
    public boolean isChecked;

    public Activity mActivity;

    private BaseHolder<T> mViewHolder;

    public BasicAdapter(List<T> showItems) {
        mShowItems = showItems;
    }


    public BasicAdapter(List<T> showItems, Activity activity) {
        mShowItems = showItems;
        this.mActivity = activity;
    }


    @Override
    public int getCount() {
        return mShowItems.size();
    }

    @Override
    public T getItem(int position) {
        return mShowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        //holder不一样
        mViewHolder = null;

        if (convertView == null) {
            //布局不末端

            //谁要实现 adapter谁给我一个holder就可以
            mViewHolder = createViewHolder(position);
            //convertView.setTag(viewHolder);
        } else {
            //获取到
            mViewHolder = (BaseHolder<T>) convertView.getTag();
        }

        //执行完以上的步骤,我们就已经有了viewholder对象

        //接下来更新数据,赋值

        mViewHolder.bindView(mShowItems.get(position), position);
        View itemView = mViewHolder.getView();

        return itemView;
    }

    public void setCheck(boolean check) {
        isChecked = check;
    }


    protected abstract BaseHolder<T> createViewHolder(int position);


}
