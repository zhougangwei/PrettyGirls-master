package coder.aihui.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import coder.aihui.R;
import coder.aihui.data.bean.IN_ASSET;
import coder.aihui.data.bean.REPAIR_PLACE;
import coder.aihui.data.bean.gen.DaoSession;
import coder.aihui.data.bean.gen.IN_ASSETDao;
import coder.aihui.data.bean.gen.REPAIR_PLACEDao;


/**
 * @ 创建者   zhou
 * @ 创建时间   2016/12/17 14:02
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2016/12/17$
 * @ 更新描述  ${TODO}
 */

public class AlertListDialogUtil {

    private static AlertListDialogUtil mAlertListDialogUtil = new AlertListDialogUtil();
    public AlertDialog mDialog;


    private MyListAdapter mMyListAdapter;

    private ListView       sLv;
    private ArrayList      mList;
    private String         checkResult;
    private onGetResult    mOnGetResult;
    private MyBaseAdapter  mMyBaseAdapter;
    private List<IN_ASSET> mDatas;


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                try {

                    mMLoadVIew.setVisibility(View.INVISIBLE);
                    mSuccess.setVisibility(View.VISIBLE);
                    Thread.currentThread();
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mMyBaseAdapter.notifyDataSetChanged();
            }
        }
    };


    private View mMLoadVIew;
    private View mSuccess;
    private String mSearchId = "新资产编号";


    private AlertListDialogUtil() {
    }


    public static AlertListDialogUtil getInstance() {
        return mAlertListDialogUtil;
    }


    public interface onGetResult {
        public void getResult(String result);
    }


    /**
     * @param title      标题
     * @param daoSession 数据库
     * @param Rid        Rid是数据所在的数组
     * @param activity
     * @param listener   回调
     */

    public void showDialog(String title, final DaoSession daoSession, final int Rid, final Activity activity, onGetResult listener) {


        try {
            mOnGetResult = listener;

            View view = View.inflate(activity, R.layout.dialogutil_search, null);

            final Spinner mSpinner = (Spinner) view.findViewById(R.id.spinner);


            final TextView mTvSearch = (TextView) view.findViewById(R.id.tv_search);
            //搜索框
            final EditText et_search = (EditText) view.findViewById(R.id.et_search);

            // android:entries="@array/kpbh"

            String[] mdatas = activity.getResources().getStringArray(Rid);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(activity, R.layout.simple_spinner_item, mdatas);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mSpinner.setAdapter(arrayAdapter);

            mTvSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doSearch(et_search,et_search,daoSession);
                }
            });


            FrameLayout fl = (FrameLayout) view.findViewById(R.id.fl);
            //分别是 加载页面和 成功页面
            mMLoadVIew = View.inflate(activity, R.layout.dialog_loading, null);
            mSuccess = View.inflate(activity, R.layout.dialog_page_success, null);


            //显示条目
            ListView lv = (ListView) mSuccess.findViewById(R.id.lv);


            fl.addView(mMLoadVIew);
            fl.addView(mSuccess);

            mMLoadVIew.setVisibility(View.INVISIBLE);
            mSuccess.setVisibility(View.INVISIBLE);



            mDatas = new ArrayList();
            mMyBaseAdapter = new MyBaseAdapter(activity, mDatas);

            lv.setAdapter(mMyBaseAdapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mOnGetResult.getResult(mDatas.get(position).getRFID_CODE());
                    mDialog.dismiss();
                }
            });

            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mSearchId = activity.getResources().getStringArray(Rid)[position];

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    //什么都没选默认是第一个
                    mSearchId = activity.getResources().getStringArray(Rid)[0];
                }
            });


            et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                /*判断是否是“GO”键*/
                    if ((event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) || actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE)
                        return doSearch(v, et_search, daoSession);
                    return false;
                }
            });
            mDialog = new AlertDialog.Builder(activity).setView(view).create();
            mDialog.setTitle(title);
            mDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param v
     * @param et_search
     * @param daoSession
     * @return
     */
    private boolean doSearch(TextView v, EditText et_search, DaoSession daoSession) {
    /*隐藏软键盘*/

        InputMethodManager imm = (InputMethodManager) v
                .getContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(
                    v.getApplicationWindowToken(), 0);
        }

        final Editable s = et_search.getText();
        if (TextUtils.isEmpty(s)) {
            return true;
        }
        mMLoadVIew.setVisibility(View.VISIBLE);

        if ("新资产编号".equals(mSearchId)) {
            List<IN_ASSET> assetList = daoSession.getIN_ASSETDao()
                    .queryBuilder().where(IN_ASSETDao.Properties.KPBH.like("%" + s.toString() + "%")).list();
            List<REPAIR_PLACE> repairList = daoSession.getREPAIR_PLACEDao()
                    .queryBuilder().where(REPAIR_PLACEDao.Properties.PLACE_KPBH.like("%" + s.toString() + "%")).list();

            ArrayList<IN_ASSET> al2 = new ArrayList();    //
            for (REPAIR_PLACE bean : repairList) {
                IN_ASSET in_asset = new IN_ASSET();
                in_asset.setRFID_CODE(bean.getPLACE_RFID_CODE());
                in_asset.setWZMC(bean.getPLACE_NAME());
                al2.add(in_asset);
            }
            mDatas.clear();
            mDatas.addAll(assetList);
            mDatas.addAll(al2);
            mMLoadVIew.setVisibility(View.INVISIBLE);
            mSuccess.setVisibility(View.VISIBLE);
            mMyBaseAdapter.notifyDataSetChanged();


        } else if ("老资产编号".equals(mSearchId)) {
            List<IN_ASSET> assetList = daoSession.getIN_ASSETDao()
                    .queryBuilder().where(IN_ASSETDao.Properties.KPBH_OLD.like("%" + s.toString() + "%")).list();
            mDatas.clear();
            mDatas.addAll(assetList);

            mMLoadVIew.setVisibility(View.INVISIBLE);
            mSuccess.setVisibility(View.VISIBLE);
            mMyBaseAdapter.notifyDataSetChanged();
        }

        return true;
    }

    public void destory() {
        mDialog.dismiss();
    }



/*   //手动修正用的
    public void showDialog(String title, onGetResult listener,Activity activity){
        mOnGetResult = listener;

        View view = View.inflate(activity, R.include_tab.dialogutil_search, null);

        final Spinner mSpinner = (Spinner) view.findViewById(R.id.spinner);

        // android:entries="@array/kpbh"

        String[] mdatas = activity.getResources().getStringArray(R.array.kpbh2);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(activity, android.R.include_tab.simple_spinner_item, mdatas);
        arrayAdapter.setDropDownViewResource(android.R.include_tab.simple_spinner_dropdown_item);
        mSpinner.setAdapter(arrayAdapter);
    }*/


    /**
     * @param title    标题
     * @param datas    数据
     * @param activity 上下文
     * @param listener 回调
     */
    public void showDialog(String title, List<Map<String, String>> datas, Activity activity, onGetResult listener) {


        mOnGetResult = listener;
        mList = new ArrayList();
        for (Map<String, String> m : datas) {
            mList.addAll(new ArrayList<Map.Entry<String, String>>(m.entrySet()));
        }

        Collections.sort(mList, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1,
                               Map.Entry<String, String> o2) {
                int flag = o1.getKey().compareTo(o2.getKey());
                return flag;
            }
        });

        //跳窗的dialog
        View view = View.inflate(activity, R.layout.dialogutil, null);

        sLv = (ListView) view.findViewById(R.id.lv);

        MyListAdapter mMyListAdapter = new MyListAdapter(activity, mList);

        sLv.setAdapter(mMyListAdapter);

        mDialog = new AlertDialog.Builder(activity).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gototrue(mList, sLv);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mOnGetResult.getResult(null);
                mDialog.dismiss();
            }
        })
                .setView(view).create();


        mDialog.setTitle(title);

        mDialog.show();


    }


    private View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;
        if (pos < firstListItemPosition || pos > lastListItemPosition) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    private void gototrue(List<Map.Entry<String, String>> mdatas, ListView mLv) {

        int checked = 0;
        List<Map.Entry<String, String>> checkedList = new ArrayList();

        for (int i = 0; i < mdatas.size(); i++) {
            //i就是当前的位子
            View view = getViewByPosition(i, mLv);
            CheckBox cb = (CheckBox) view.findViewById(R.id.cb);
            if (cb.isChecked()) {
                checked++;
                checkedList.add(mdatas.get(i));
            }
        }

        if (checkedList == null || checkedList.size() == 0) {
            mOnGetResult.getResult(null);
            return;
        }

        StringBuilder sb
                = new StringBuilder();


        for (int i = 0; i < checkedList.size(); i++) {

            if (i != checkedList.size() - 1) {
                sb.append(checkedList.get(i).getKey() + ",");
            } else {
                sb.append(checkedList.get(i).getKey());
            }
        }
        checkResult = sb.toString();

        mOnGetResult.getResult(checkResult);
        //Log.d("AlertListDialogUtil", sb.toString());
    }

    //这个是 有checkbox  条目比较少的时候的适配器
    private class MyListAdapter extends BaseAdapter {
        Activity                        mActivity;
        List<Map.Entry<String, String>> mList;

        public MyListAdapter(Activity activity, List<Map.Entry<String, String>> list) {
            this.mActivity = activity;
            this.mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mActivity, R.layout.alert_list_dialog_item, null);
            CheckBox cb = (CheckBox) view.findViewById(R.id.cb);
            TextView tv = (TextView) view.findViewById(R.id.tv);

            tv.setText(mList.get(position).getValue());
            return view;
        }
    }

    //这个是 有checkbox  条目比较少的时候的适配器
    private class MyBaseAdapter extends BaseAdapter {
        Activity       mActivity;
        List<IN_ASSET> mList;

        public MyBaseAdapter(Activity activity, List<IN_ASSET> list) {
            this.mActivity = activity;
            this.mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            try {
                if (convertView == null) {
                    convertView = View.inflate(mActivity, R.layout.inspect_item, null);
                }
                TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                TextView tv_rfid = (TextView) convertView.findViewById(R.id.tv_rfid);

                if ("新资产编号".equals(mSearchId)) {
                    tv_rfid.setText(mList.get(position).getRFID_CODE());
                } else if ("老资产编号".equals(mSearchId)) {
                    tv_rfid.setText(mList.get(position).getKPBH_OLD());
                }
                tv_name.setText(mList.get(position).getWZMC());

            } catch (Exception e) {
            }

            return convertView;
        }
    }

    //
}
