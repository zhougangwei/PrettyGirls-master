package coder.aihui.widget.popwindow;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import coder.aihui.R;
import razerdp.basepopup.BasePopupWindow;


/**
 * Created by 大灯泡 on 2016/12/6.
 * <p>
 * 从顶部下滑的Poup
 */

public class ListPopup extends BasePopupWindow {

    private List<String> mDataList;


    public ListPopup(Activity context, List dataList) {
        super(context);
        setBackPressEnable(false);
        setDismissWhenTouchOuside(true);
        mDataList = dataList;

        ListView listView = (ListView) findViewById(R.id.popup_list);
        listView.setAdapter(new InnerPopupAdapter(context, mDataList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), mDataList.get(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected Animation initShowAnimation() {
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new DecelerateInterpolator());
        set.addAnimation(getScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0));
        set.addAnimation(getDefaultAlphaAnimation());
        return set;
    }

    /*@Override
    protected Animation initExitAnimation() {
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new DecelerateInterpolator());
        set.addAnimation(getScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0));
        set.addAnimation(getDefaultAlphaAnimation());
        return set;
    }*/
  /*  @Override
    public void showPopupWindow(View v) {
        setOffsetX(-(getWidth() - v.getWidth() / 2));
        setOffsetY(-v.getHeight() / 2);
        super.showPopupWindow(v);
    }*/


    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.popup_list);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }

    //=============================================================adapter
    private static class InnerPopupAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private Context        mContext;
        private List<String>   mItemList;

        public InnerPopupAdapter(Context context, @NonNull List<String> itemList) {
            mContext = context;
            mItemList = itemList;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mItemList.size();
        }

        @Override
        public String getItem(int position) {
            return mItemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            InnerPopupAdapter.ViewHolder vh = null;
            if (convertView == null) {
                vh = new InnerPopupAdapter.ViewHolder();
                //convertView = mInflater.inflate(R.include_tab.item_popup_list, parent, false);
                convertView = View.inflate(mContext,R.layout.item_popup_list, null);
                vh.mTextView = (TextView) convertView.findViewById(R.id.item_tx);
                convertView.setTag(vh);
            } else {
                vh = (InnerPopupAdapter.ViewHolder) convertView.getTag();
            }
            vh.mTextView.setText(getItem(position));
            return convertView;
        }

        class ViewHolder {
            public TextView mTextView;
        }
    }





}
