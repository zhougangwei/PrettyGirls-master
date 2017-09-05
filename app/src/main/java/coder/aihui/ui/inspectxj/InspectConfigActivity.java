package coder.aihui.ui.inspectxj;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.DeptDecotor;
import coder.aihui.base.DeptView;
import coder.aihui.base.DlwzDecotor;
import coder.aihui.base.DlwzView;
import coder.aihui.manager.DeptLocationManager;
import coder.aihui.util.AndroidUtils;
import coder.aihui.widget.jdaddressselector.ISelectAble;

public class InspectConfigActivity extends AppActivity implements DeptView, DlwzView, AdapterView.OnItemSelectedListener {

    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.tv_save)
    TextView     mTvSave;
    @BindView(R.id.ll_location)
    LinearLayout mLlLocation;
    @BindView(R.id.ll_dept)
    LinearLayout mLlDept;
    @BindView(R.id.ll_cxrq)
    LinearLayout mLlCxrq;
    @BindView(R.id.ll_ksrq)
    LinearLayout mLlKsrq;
    @BindView(R.id.ll_jsrq)
    LinearLayout mLlJsrq;
    @BindView(R.id.tv_dlwz)
    TextView     mTvDlwz;
    @BindView(R.id.tv_dept)
    TextView     mTvDept;
    @BindView(R.id.sp_cxzq)
    Spinner      mSpCxzq;
    @BindView(R.id.tv_ksrq)
    TextView     mTvKsrq;
    @BindView(R.id.tv_jsrq)
    TextView     mTvJsrq;

    private DeptDecotor mDeptDecotor;

    private DlwzDecotor mDlwzDecotor;
    private String      mAllDeptIds;
    private String      mAllDlwzIds;
    private String      mDlwzIds;
    private String      mDeptIds;

    private Date     mStartDate;//查询开始日期00:00
    private Date     mEndDate;//查询结束日期23:59
    private TextView mTimeView;

    private int mSearchCycle = 1;//查询周期  1 今天，2本周 3 本月 4当天向前15天 5当天向前三十天 6当天向前45天 7一个周期 目前是两个月
    private String[] mStringArray;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_inspect_config;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

    @Override
    protected void initView() {
        mDeptDecotor = new DeptDecotor(this, this);
        mDlwzDecotor = new DlwzDecotor(this, this);

        initGetIntent();

        mTvTitle.setText("巡检配置");


        mStringArray = getResources().getStringArray(R.array.recycleConfig);
        mSpCxzq.setOnItemSelectedListener(this);
    }

    private void initGetIntent() {

        Intent intent = getIntent();
        mStartDate = (Date) intent.getSerializableExtra("mStartDate");
        mEndDate = (Date) intent.getSerializableExtra("mEndDate");
        String insrType = intent.getStringExtra("insrType");
        switch (insrType) {
            case "XJ":
                mTvTitle.setText("巡检配置");
                break;
            case "PM":
                mTvTitle.setText("PM配置");
                break;
        }
        setTime();
    }

    private void setTime() {
        mTvKsrq.setText(TimeUtils.date2String(mStartDate, mFormat));
        mTvJsrq.setText(TimeUtils.date2String(mEndDate, mFormat));
    }


    @OnClick({R.id.iv_back, R.id.tv_save, R.id.ll_location, R.id.ll_dept, R.id.ll_ksrq, R.id.ll_jsrq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_save:
                gotoSave();
                break;
            case R.id.ll_location:
                getDlwz();
                break;
            case R.id.ll_dept:
                getDept();
                break;

            case R.id.ll_ksrq:
                gotoChooseTime(2);
                break;
            case R.id.ll_jsrq:
                gotoChooseTime(3);
                break;
        }
    }

    private void changeSearchCycle(int searchCycle) {
        this.mSearchCycle = searchCycle;
        Date[] dateArry = AndroidUtils.getTimeCycle(mSearchCycle);
        mStartDate = dateArry[0];
        mEndDate = dateArry[1];
        //设置时间
        setTime();

    }

    private void gotoChooseTime(final int type) {
        switch (type) {
            case 1:
                break;
            case 2:
                mTimeView = mTvKsrq;
                break;
            case 3:
                mTimeView = mTvJsrq;
                break;
        }

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCurrentMillseconds(TimeUtils.string2Milliseconds(mTimeView.getText().toString() == null ? TimeUtils.getCurTimeString(mFormat) : mTimeView.getText().toString(), mFormat))
                .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(15)
                .setCallBack(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        String time = TimeUtils.milliseconds2String(millseconds, mFormat);
                        mTimeView.setText(time);
                        switch (type) {
                            case 1:

                                break;
                            case 2:
                                mStartDate = TimeUtils.string2Date(time + " 00:00:00");
                                break;
                            case 3:
                                mEndDate = TimeUtils.string2Date(time + " 23:59:59");
                                break;
                        }
                    }
                })
                .build();

        mDialogAll.show(getSupportFragmentManager(), "1");

    }

    private void gotoSave() {
        Intent intent = new Intent();
        //intent.putExtra("mDeptName", mDeptName);
        intent.putExtra("mDeptIds", mDeptIds);
        //intent.putExtra("mDlwzName", mDlwzName);
        intent.putExtra("mDlwzIds", mDlwzIds);
        //intent.putExtra("mAllDeptName", mAllDeptName);
        intent.putExtra("mAllDeptIds", mAllDeptIds);
        intent.putExtra("mAllDlwzIds", mAllDlwzIds);
        intent.putExtra("mStartDate", mStartDate);
        intent.putExtra("mEndDate", mEndDate);
        intent.putExtra("mAllDlwzIds", mAllDlwzIds);
        //intent.putExtra("mAllDlwzName", mAllDlwzName);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onAddressSelected(ArrayList<ISelectAble> selectAbles) {
        DeptLocationManager manager = new DeptLocationManager();
        manager.solveDatas(selectAbles);
        String allDeptName = manager.getAllDeptName();
        String allDlwzName = manager.getAllDlwzName();
        mAllDeptIds = manager.getAllDeptIds();
        mAllDlwzIds = manager.getAllDlwzIds();
        mDlwzIds = manager.getDlwzIds();
        mDeptIds = manager.getDeptIds();
        mTvDept.setText(allDeptName);           //所有科室的
        mTvDlwz.setText(allDlwzName);           //所有地理的
    }

    @Override
    public void closeDiaLog() {
        mDeptDecotor.closeDiaLog();
        mDlwzDecotor.closeDiaLog();
    }

    @Override
    public void getDept() {
        mDeptDecotor.getDept();
    }


    @Override
    public void getDlwz() {
        mDlwzDecotor.getDlwz();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String SelectChoose = (String) mSpCxzq.getSelectedItem();
        switch (SelectChoose) {
            case "本期":
                mSearchCycle = 7;
                break;
            case "本月":
                mSearchCycle = 3;
                break;
            case "今天":
                mSearchCycle = 1;
                break;
            case "本周":
                mSearchCycle = 2;
                break;
            case "向前15天":
                mSearchCycle = 4;
                break;
            case "向前30天":
                mSearchCycle = 5;
                break;
            case "向前45天":
                mSearchCycle = 6;
                break;
            default:
                break;
        }

        changeSearchCycle(mSearchCycle);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mSearchCycle = 7;
    }
}
