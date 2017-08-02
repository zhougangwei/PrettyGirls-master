package coder.aihui.ui.assetcheck;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import coder.aihui.R;
import coder.aihui.data.normalbean.ExpandAssetBean;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/6/12 20:33
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/6/12$
 * @ 更新描述  ${TODO}
 */

public class AssetDetailAdapter extends BaseExpandableListAdapter {

    public Context context;

    List<ExpandAssetBean> profather;
    // private int isDef = 0;              //1是使用默认的数据


    static class ListItemson {
        /*   public ImageView sonpic;
           public ImageView arrow;*/
        public TextView sontName;
        public TextView sonValue;


    }

    static class ListItemparent {
        public TextView  parentName;
        public ImageView icon;

    }


    public AssetDetailAdapter(Activity moreSortNewActivity,
                              List profather) {
        this.context = moreSortNewActivity;
        this.profather = profather;
    }


    @Override
    public int getGroupCount() {

        // 一级分类的数量
        return profather.size();// 一级分类的数量
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // 二级分类的数量
        return profather.get(groupPosition).getSonBean()==null?0:profather.get(groupPosition).getSonBean().size();// 二级分类的数量
    }

    @Override
    public Object getGroup(int groupPosition) {
        return profather.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (childPosition == 0) {
            return profather.get(groupPosition);
        } else {

            return profather.get(groupPosition).getSonBean()
                    .get(childPosition);

        }
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ListItemparent listfather;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.newinsepect_detail_father, null);
            listfather = new ListItemparent();
            listfather.parentName = (TextView) convertView
                    .findViewById(R.id.tv_father_name);
            listfather.icon = (ImageView) convertView
                    .findViewById(R.id.iv_arrow);

            convertView.setTag(listfather);
        } else {
            listfather = (ListItemparent) convertView.getTag();
        }
        listfather.parentName.setText(profather.get(groupPosition).getName());




        //自定义箭头

        if (isExpanded){
            listfather.icon.setImageResource(R.mipmap.ic_arrow_down);
         //   down
        }else{
            //up
            listfather.icon.setImageResource(R.mipmap.ic_arrow_up);
        }






        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ListItemson listSon;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.newinsepect_detail_son, null);
            listSon = new ListItemson();
            listSon.sontName = (TextView) convertView
                    .findViewById(R.id.tv_sonname);
            listSon.sonValue = (TextView) convertView
                    .findViewById(R.id.tv_sonvalue);


            convertView.setTag(listSon);
        } else {
            listSon = (ListItemson) convertView.getTag();
        }
        listSon.sontName.setText(profather.get(groupPosition).getSonBean().get(childPosition).name);
        listSon.sonValue.setText(profather.get(groupPosition).getSonBean().get(childPosition).bean+"");


        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


}
