package coder.aihui.ui.pxgl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;
import com.google.gson.reflect.TypeToken;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.leon.lfilepickerlibrary.LFilePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.data.bean.DialogBean;
import coder.aihui.data.bean.IN_MATERIALS_WZMC;
import coder.aihui.data.bean.PUB_COMPANY;
import coder.aihui.data.bean.PUB_DICTIONARY_ITEM;
import coder.aihui.data.bean.PXGL_SAVE;
import coder.aihui.data.bean.PXJL;
import coder.aihui.data.bean.SYS_USER;
import coder.aihui.data.bean.gen.PUB_DICTIONARY_ITEMDao;
import coder.aihui.data.bean.gen.SYS_USERDao;
import coder.aihui.data.normalbean.FjBean;
import coder.aihui.util.DaoUtil;
import coder.aihui.util.GsonUtil;
import coder.aihui.util.ListUtils;
import coder.aihui.util.ToastUtil;
import coder.aihui.widget.ListBottomDialog;
import coder.aihui.widget.contact.ContactActivity;
import coder.aihui.widget.contact.SysUserActivity;
import coder.aihui.widget.contact.WzActivity;
import coder.aihui.widget.popwindow.MenuPopup;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static coder.aihui.base.Content.FILE_PICK_REQUEST_CODE;
import static coder.aihui.base.Content.PXGL_WZ_REQUEST_CODE;

public class PxglDetailActivity extends AppActivity {


    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.iv_updown)
    LinearLayout mIvUpdown;
    @BindView(R.id.tv_pxry)
    TextView     mTvPxry;
    @BindView(R.id.ll_pxry)
    LinearLayout mLlPxry;
    @BindView(R.id.tv_pxsb)
    TextView     mTvPxsb;
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
    @BindView(R.id.tv_pxsc)
    TextView     mTvPxsc;
    @BindView(R.id.ll_time)
    LinearLayout mLlTime;
    @BindView(R.id.et_content)
    EditText     mEtContent;

    @BindView(R.id.rl_fj)
    RelativeLayout mRlFj;
    @BindView(R.id.rl_jc)
    RelativeLayout mRlJc;


    private List<DialogBean> mPxlxList = new                    //培训类型
            ArrayList();
    private List<String>     mSbIdList = new ArrayList<>();         //已选设备的Id集合

    private List<String> mPxryIdList = new ArrayList<>();      //培训人员的集合
    private Integer mDocType;            //是教材还是附件
    private static final Integer JC = 1;            //附件
    private static final Integer FJ = 2;            //教材

    private ArrayList<FjBean> mDocList = new ArrayList<>();//文件的集合包含附件和教材

    private HashMap<Integer, ArrayList> mDocMap = new HashMap<>();//用于回显文件选择


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
        TextView mTvJc = (TextView) (mRlJc.findViewById(R.id.tv_word));
        mTvJc.setText("培训教材");
        //教材
        TextView mTvFj = (TextView) mRlFj.findViewById(R.id.tv_word);
        mTvFj.setText("培训附件");
        //
        mTvTitle.setText("培训详情");
    }

    private void initGetIntent() {

        //回显跳过来

        Intent intent = getIntent();
        long id = intent.getLongExtra("id", -1L);

        if (id != -1L) {
            PXGL_SAVE load = mDaoSession.getPXGL_SAVEDao().load(id);
            if (load != null) {
                String pxry_json = load.getPXRY_JSON();//人员
                if (!TextUtils.isEmpty(pxry_json)) {
                    mPxryIdList = ListUtils.StringsTolist(pxry_json);
                }
                Observable.from(mDaoSession.getSYS_USERDao()
                        .queryBuilder().where(SYS_USERDao.Properties.USER_ID.in(mPxryIdList))
                        .list())
                        .subscribeOn(Schedulers.io())
                        .map(new Func1<SYS_USER, String>() {
                            @Override
                            public String call(SYS_USER sys_users) {
                                return sys_users.getUSER_NAME();
                            }
                        }).toList().map(new Func1<List<String>, String>() {
                    @Override
                    public String call(List<String> lists) {
                        return ListUtils.listToStrings(lists);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                mTvPxry.setText(s);
                            }
                        });
                //设备数据回显处理
                String pxsb_json = load.getPXSB_JSON();//设备
                String wzString = DaoUtil.getWzString(pxsb_json);
                if (!TextUtils.isEmpty(pxsb_json)) {
                    List<IN_MATERIALS_WZMC> mcList = GsonUtil.parseJsonToList(pxsb_json, new TypeToken<List<IN_MATERIALS_WZMC>>() {
                    }.getType());
                    for (int i = 0; i < mcList.size(); i++) {
                        mSbIdList.add(mcList.get(i) + "");
                    }
                }
                if (!TextUtils.isEmpty(wzString)) {
                    mTvPxsb.setText(wzString);
                }
            }
        } else {
            //安装验收跳过来
            String sbIds = intent.getStringExtra(Content.SB_IDS);
            if (!TextUtils.isEmpty(sbIds)) {
                //显示设备
                mSbIdList = ListUtils.StringsTolist(sbIds);
            }
            IN_MATERIALS_WZMC load = mDaoSession.getIN_MATERIALS_WZMCDao().load(Long.valueOf(sbIds));
            if (load != null) {
                String wzmc = load.getWZMC();
                if (!TextUtils.isEmpty(wzmc)) {
                    mTvPxsb.setText(wzmc);
                }
            }
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
            R.id.rl_fj,
            R.id.rl_jc,
            R.id.tv_ok

    })
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
                break;
            case R.id.rl_fj:
                gotoAddFj(FJ);
                break;
            case R.id.rl_jc:
                gotoAddFj(JC);
                break;
            case R.id.tv_ok:
                gotoSave();

        }
    }

    private void gotoSave() {
        //上传数据
        String pxzt = mEtZt.getText().toString().trim();    //主题
        if (TextUtils.isEmpty(pxzt)) {
            ToastUtil.showToast("请填写主题");
            return;
        }
        String jlr = mEtJlr.getText().toString().trim();        //记录人
        String zjr = mEtZjr.getText().toString().trim();        //主讲人
        String pxrq = mTvPxrq.getText().toString().trim();      //培训日期
        String zynr = mEtContent.getText().toString().trim();   //培训内容
        String pxlx = mTvPxlx.getText().toString().trim();   //培训类型
        String pxf = mTvPxf.getText().toString().trim();//培训方
        String pxsc = mTvPxsc.getText().toString().trim();//培训时长


        PXJL pxjlBean = new PXJL();         //培训记录的Bean
        pxjlBean.setPxzt(pxzt);             //培训主题
        pxjlBean.setJlr(jlr);                //记录人
        pxjlBean.setZjr(zjr);               //主讲人
        pxjlBean.setZynr(zynr);             //主要内容
        pxjlBean.setPxlx(pxlx);             //培训类型
        pxjlBean.setPxf(pxf);             //培训方

        if (!TextUtils.isEmpty(pxsc)) {
            String[] split = pxsc.split(":");
            pxjlBean.setPxxs(Integer.parseInt(split[0]));             //培训时间(小时)
            pxjlBean.setPxfz(Integer.parseInt(split[1]));              //培训分钟
        }


        //培训日期
        if (!TextUtils.isEmpty(pxrq)) {
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(pxrq);
                pxjlBean.setPxrq(date);
            } catch (ParseException e) {
                ToastUtil.showToast("时间输入格式不对,请确认");
                e.printStackTrace();
                return;
            }
        } else {
            ToastUtil.showToast("请选择培训时间");
            return;
        }
        final PXGL_SAVE saveBean = new PXGL_SAVE();
        //主Bean的数据存储
        saveBean.setPXJL_JSON(GsonUtil.parseObjectToJson(pxjlBean));        //主培训记录
        saveBean.setPXRY_JSON(ListUtils.listToStrings(mPxryIdList));        //培训人员
        //保存培训设备
        Observable.from(mSbIdList).map(new Func1<String, IN_MATERIALS_WZMC>() {
            @Override
            public IN_MATERIALS_WZMC call(String s) {
                return mDaoSession.getIN_MATERIALS_WZMCDao().load(Long.valueOf(s));
            }
        }).toList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<List<IN_MATERIALS_WZMC>, String>() {
                    @Override
                    public String call(List<IN_MATERIALS_WZMC> in_materials_wzmcs) {
                        return GsonUtil.parseListToJson(in_materials_wzmcs);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String json) {
                        saveBean.setPXSB_JSON(json);
                    }
                });
        saveBean.setIS_UP(0);           //是否上传
        saveBean.setPXFJ_JSON(GsonUtil.parseListToJson(mDocList));        //附件

        long id = mDaoSession.insertOrReplace(saveBean);
        Intent intent = new Intent();
        intent.putExtra("id",id);                                   //将存的实体类的id返回去
        setResult(RESULT_OK);
    }

    private void gotoAddFj(Integer docType) {
        mDocType = docType;
  /*      FilePickerBuilder.getInstance().setMaxCount(10)
                .setSelectedFiles(mDocMap.get(mDocType))
                .setActivityTheme(R.style.blueTheme)
                .pickFile(this);*/
        new LFilePicker()
                .withActivity(this)
                .withRequestCode(FILE_PICK_REQUEST_CODE)
                .start();

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
                        mTvPxrq.setText(TimeUtils.milliseconds2String(millseconds, format));
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
                .setCurrentMillseconds(TimeUtils.string2Milliseconds(mTvPxsc.getText().toString() == null ? TimeUtils.getCurTimeString(format) : mTvPxsc.getText().toString(), format))
                .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg))
                .setType(Type.HOURS_MINS)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(12)
                .setCallBack(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        mTvPxsc.setText(TimeUtils.milliseconds2String(millseconds, format));
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
        intent.putExtra(Content.AZYS_DETAIL_IDS, ListUtils.listToStrings(mSbIdList));           //用于回显
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
            mUpdownPopup = new MenuPopup(this, mUpDownList, new MenuPopup.BackReslut() {
                @Override
                public void onBackResult(String string) {

                }
            });
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
                    mPxryIdList = data.getStringArrayListExtra(Content.CHECKED_USER_IDS);
                    ArrayList<String> nameList = data.getStringArrayListExtra(Content.CHECKED_USER_NAMES);
                    if (mPxryIdList != null && mPxryIdList.size() != 0) {
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
                case FILE_PICK_REQUEST_CODE:
                    if (resultCode == Activity.RESULT_OK && data != null) {
                        ArrayList<String> stringList = data.getStringArrayListExtra(Content.FJ_PATH);
                        mDocMap.put(mDocType, stringList);
                        for (int i = 0; i < stringList.size(); i++) {
                            FjBean fjBean = new FjBean();
                            fjBean.setFILE_TYPE(mDocType);
                            fjBean.setPXJL_FILE_PATH(stringList.get(i));
                            mDocList.add(fjBean);
                        }
                    }
                    gotoBackShow();
                    break;
            }
        }
    }

    //回显文件名
    private void gotoBackShow() {
        TextView mTvJc = (TextView) mRlJc.findViewById(R.id.tv_word);
        mTvJc.setText(ListUtils.ListFiled2String(mDocList, "getPXJL_FILE_PATH", FjBean.class));
        //教材
        TextView mTvFj = (TextView) mRlFj.findViewById(R.id.tv_word);
        mTvFj.setText("培训附件");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
