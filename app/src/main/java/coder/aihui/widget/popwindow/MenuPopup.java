package coder.aihui.widget.popwindow;

import android.animation.Animator;
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
 * Created by 大灯泡 on 2016/1/22.
 * 菜单。
 */
public class MenuPopup extends BasePopupWindow {


    private List<String> mDataList;

    public MenuPopup(Activity context, List dataList, final BackReslut backReslut) {
        super(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setBackPressEnable(false);
        setDismissWhenTouchOuside(true);
        mDataList = dataList;

        ListView listView = (ListView) findViewById(R.id.popup_list);
        listView.setAdapter(new InnerPopupAdapter(context, mDataList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), mDataList.get(position), Toast.LENGTH_LONG).show();
                backReslut.onBackResult(mDataList.get(position));
            }
        });


    }

    public interface BackReslut {
      void  onBackResult(String string);
    }


    @Override
    protected Animation initShowAnimation() {
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new DecelerateInterpolator());
        set.addAnimation(getScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0));
        set.addAnimation(getDefaultAlphaAnimation());
        return set;
        //return null;
    }

    @Override
    public Animator initShowAnimator() {
       /* AnimatorSet set=new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(mAnimaView,"scaleX",0.0f,1.0f).setDuration(300),
                ObjectAnimator.ofFloat(mAnimaView,"scaleY",0.0f,1.0f).setDuration(300),
                ObjectAnimator.ofFloat(mAnimaView,"alpha",0.0f,1.0f).setDuration(300*3/2));*/
        return null;
    }

    @Override
    public void showPopupWindow(View v) {
        setOffsetX(-(getWidth() - v.getWidth() / 2));
        setOffsetY(-v.getHeight() / 2);
        super.showPopupWindow(v);
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.popup_menu);
    }

    @Override
    public View initAnimaView() {
        return getPopupWindowView().findViewById(R.id.popup_contianer);
    }


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
                convertView = View.inflate(mContext, R.layout.item_popup_list, null);
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
