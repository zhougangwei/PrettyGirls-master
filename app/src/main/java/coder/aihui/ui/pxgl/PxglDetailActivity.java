package coder.aihui.ui.pxgl;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.data.bean.DialogBean;
import coder.aihui.data.bean.PUB_COMPANY;
import coder.aihui.data.bean.PUB_DICTIONARY_ITEM;
import coder.aihui.data.bean.gen.PUB_DICTIONARY_ITEMDao;
import coder.aihui.util.ListUtils;
import coder.aihui.widget.ListBottomDialog;
import coder.aihui.widget.contact.ContactActivity;
import coder.aihui.widget.contact.SysUserActivity;
import coder.aihui.widget.contact.WzActivity;
import coder.aihui.widget.popwindow.MenuPopup;
import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static coder.aihui.R.id.ll_jc;
import static coder.aihui.base.Content.PXGL_WZ_REQUEST_CODE;

public class PxglDetailActivity extends AppActivity {

    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.iv_updown)
    LinearLayout mIvUpdown;
    @BindView(R.id.ll_pxry)
    LinearLayout mLlPxry;
    @BindView(R.id.ll_pxsb)
    LinearLayout mLlPxsb;
    @BindView(R.id.et_zt)
    EditText     mEtZt;
    @BindView(R.id.et_zjr)
    EditText     mEtZjr;
    @BindView(R.id.et_jlr)
    EditText     mEtJlr;
    @BindView(R.id.tv_pxlx)
    TextView     mTvPxlx;
    @BindView(R.id.ll_pxlx)
    LinearLayout mLlPxlx;
    @BindView(R.id.tv_pxf)
    TextView     mTvPxf;
    @BindView(R.id.ll_pxf)
    LinearLayout mLlPxf;
    @BindView(R.id.textView6)
    TextView     mTextView6;
    @BindView(R.id.tv_pxrq)
    TextView     mTvPxrq;
    @BindView(R.id.ll_pxrq)
    LinearLayout mLlPxrq;

    @BindView(R.id.ll_time)
    LinearLayout mLlTime;
    @BindView(R.id.tv_scrq)
    TextView     mTvScrq;

    @BindView(R.id.tv_pxry)
    TextView mTvPxry;
    @BindView(R.id.tv_pxsb)
    TextView mTvPxsb;


    @BindView(R.id.ll_fj)
    RelativeLayout mLlFj;
    @BindView(ll_jc)
    RelativeLayout mLlJc;

    @BindView(R.id.title)
    TextView mTvTitle;
    private List<DialogBean> mPxlxList = new
            ArrayList();
    private List<String>     mSbIdList = new ArrayList<>();         //已选设备的Id集合
    private ArrayList<String> docPaths = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_pxgl_detail;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }


    @Override
    protected void initView() {


        mUpDownList.add("巡检初始");
        initGetIntent();
        initData();

        //附件
        TextView mTvJc = (TextView)mLlJc.findViewById(R.id.tv_word);
        mTvJc.setText("培训教材");
        //教材
        TextView mTvFj = (TextView)mLlFj.findViewById(R.id.tv_word);
        mTvFj.setText("培训附件");
        //
        mTvTitle.setText("培训详情");
    }

    private void initGetIntent() {
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(Content.SB_IDS);
        String sbNames = intent.getStringExtra(Content.SB_NAMES);
        if (!TextUtils.isEmpty(stringExtra)) {
            //显示设备
            mSbIdList = ListUtils.StringsTolist(stringExtra);
        }
        if (!TextUtils.isEmpty(sbNames)) {
            mTvPxsb.setText(sbNames);
        }
    }

    private void initData() {
        //培训分类
        initPxfl();
    }


    //培训分类
    private void initPxfl() {
        Observable.from(mDaoSession.getPUB_DICTIONARY_ITEMDao().queryBuilder().where(PUB_DICTIONARY_ITEMDao.Properties.DICT_ID.eq("007083"))
                .list()).map(new Func1<PUB_DICTIONARY_ITEM, DialogBean>() {
            @Override
            public DialogBean call(PUB_DICTIONARY_ITEM bean) {
                return new DialogBean(bean.getITEM_NAME(), bean);
            }
        }).toList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<DialogBean>>() {
            @Override
            public void call(List<DialogBean> pub_dictionary_items) {
                mPxlxList.addAll(pub_dictionary_items);
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.iv_updown, R.id.ll_pxry, R.id.ll_pxsb, R.id.ll_pxlx, R.id.ll_pxf, R.id.ll_pxrq,
            R.id.ll_time,
            R.id.ll_fj,
            R.id.ll_jc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_updown:
                //上传下载
                gotoUpDown();
                break;
            case R.id.ll_pxry:
                //培训人员
                gotoPxry();
                break;
            case R.id.ll_pxsb:
                //培训设备
                gotoPxsb();
                break;
            case R.id.ll_pxlx:
                //培训类型
                gotoPxlx();
                break;
            case R.id.ll_pxf:
                //培训方
                gotoPxf();
                break;
            case R.id.ll_pxrq:
                //培训日期
                gotoPxrq();
                break;
            case R.id.ll_time:
                gotoPxsj();
                //培训时长
            case R.id.ll_fj:
                gotoAddFj();
                break;
            case R.id.ll_jc:
                gotoAddFj();
                break;
        }
    }

    private void gotoAddFj() {
        FilePickerBuilder.getInstance().setMaxCount(10)
                .setSelectedFiles(docPaths)
                .setActivityTheme(R.style.AppTheme)
                .pickFile(this);


    }

    //选择培训日期
    private void gotoPxrq() {

        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCurrentMillseconds(TimeUtils.string2Milliseconds(mTvPxrq.getText().toString() == null ? TimeUtils.getCurTimeString(format) : mTvPxrq.getText().toString(), format))
                .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(15)
                .setCallBack(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        mTvPxrq.setText(com.blankj.utilcode.utils.TimeUtils.milliseconds2String(millseconds, format));
                    }
                })
                .build();
        //这个"1"只是一个tag 随便写
        mDialogAll.show(getSupportFragmentManager(), "1");
    }


    //选择培训时长
    private void gotoPxsj() {
        final SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCurrentMillseconds(TimeUtils.string2Milliseconds(mTvScrq.getText().toString() == null ? TimeUtils.getCurTimeString(format) : mTvScrq.getText().toString(), format))
                .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg))
                .setType(Type.HOURS_MINS)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(12)
                .setCallBack(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        mTvScrq.setText(TimeUtils.milliseconds2String(millseconds, format));
                    }
                })
                .build();
        //这个"1"只是一个tag 随便写
        mDialogAll.show(getSupportFragmentManager(), "1");
    }

    //培训方
    private void gotoPxf() {
        Intent intent = new Intent(this, ContactActivity.class);
        intent.putExtra(Content.COMPANY_TYPE, Content.COMPANY_GHDW);
        startActivityForResult(intent, Content.PXGL_PXF_REQUEST_CODE);
    }

    //培训类型
    private void gotoPxlx() {
        new ListBottomDialog(this).showDialog(mPxlxList, new ListBottomDialog.onBackResult() {
            @Override
            public void backResult(DialogBean bean) {
                mTvPxlx.setText(bean.getName());
            }
        });
    }

    //添加培训设备
    private void gotoPxsb() {
        Intent intent = new Intent(this, WzActivity.class);
        intent.putExtra(Content.IS_MULTISELECT, true);
        intent.putExtra(Content.SB_IDS, ListUtils.listToStrings(mSbIdList));           //用于回显
        startActivityForResult(intent, PXGL_WZ_REQUEST_CODE);
    }

    //添加培训人员
    private void gotoPxry() {
        Intent intent = new Intent(this, SysUserActivity.class);
        intent.putExtra(Content.IS_MULTISELECT, true);      //是否多选
        startActivityForResult(intent, Content.PXGL_PXRY_REQUEST_CODE);
    }

    private MenuPopup mUpdownPopup;
    private List<String> mUpDownList = new ArrayList<>();

    //上传下载数据
    private void gotoUpDown() {
        if (mUpdownPopup == null) {
            mUpdownPopup = new MenuPopup(this, mUpDownList);
        }
        mUpdownPopup.showPopupWindow(mIvUpdown);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //培训方
                case Content.PXGL_PXF_REQUEST_CODE:
                    long companyId = data.getLongExtra(Content.COMPANY_ID, -1);
                    mDaoSession.getPUB_COMPANYDao().rx().load(companyId).filter(new Func1<PUB_COMPANY, Boolean>() {
                        @Override
                        public Boolean call(PUB_COMPANY pub_company) {
                            return pub_company != null;
                        }
                    }).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<PUB_COMPANY>() {
                        @Override
                        public void call(PUB_COMPANY pub_company) {
                            mTvPxf.setText(pub_company.getMC());
                        }
                    });
                    break;
                //培训人员
                case Content.PXGL_PXRY_REQUEST_CODE:
                    ArrayList<String> idList = data.getStringArrayListExtra(Content.CHECKED_USER_IDS);
                    ArrayList<String> nameList = data.getStringArrayListExtra(Content.CHECKED_USER_NAMES);
                    if (idList != null && idList.size() != 0) {
                        mTvPxry.setText(ListUtils.listToStrings(nameList));
                    }
                    break;

                //培训设备
                case PXGL_WZ_REQUEST_CODE:
                    mSbIdList = data.getStringArrayListExtra(Content.CHECKED_SB_IDS);
                    ArrayList<String> sbNameList = data.getStringArrayListExtra(Content.CHECKED_SB_NAMES);

                    if (mSbIdList != null && mSbIdList.size() != 0) {
                        mTvPxsb.setText(ListUtils.listToStrings(sbNameList));
                    }
                    break;
                //附件和教材
                case FilePickerConst.REQUEST_CODE_DOC:
                    if(resultCode== Activity.RESULT_OK && data!=null)
                    {
                        docPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS));
                    }
                    break;
            }
        }

    }
}
