package coder.aihui.ui.azys;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;
import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.adapter.InstallDetailListAdapter;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.data.bean.AZYS_MX;
import coder.aihui.data.bean.DHBean;
import coder.aihui.data.bean.DialogBean;
import coder.aihui.data.bean.PUR_CONTRACT_PLAN_DETAIL;
import coder.aihui.data.bean.YsrBean;
import coder.aihui.data.bean.gen.AZYS_MXDao;
import coder.aihui.data.bean.gen.YsrBeanDao;
import coder.aihui.data.normalbean.PjmxBean;
import coder.aihui.ui.assetcheck.GifSizeFilter;
import coder.aihui.ui.bz.BzActivity;
import coder.aihui.util.GsonUtil;
import coder.aihui.util.ListUtils;
import coder.aihui.util.NumberChooseDialog;
import coder.aihui.util.ThreadUtils;
import coder.aihui.util.ToastUtil;
import coder.aihui.util.Utils;
import coder.aihui.util.viewutil.QianMingUtils;
import coder.aihui.widget.ListBottomDialog;
import coder.aihui.widget.ScrollViewWithListView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static coder.aihui.R.id.tv_zczyxq;
import static coder.aihui.base.Content.AZYS_DETAIL_IDS;
import static coder.aihui.base.Content.BZ_REQUEST_CODE;
import static coder.aihui.base.Content.PJMX_REQUEST_CODE;

public class AzysDetailActivity extends AppActivity {


    @BindView(R.id.tv_title)
    TextView               mTvTitle;
    @BindView(R.id.iv_back)
    ImageView              mIvBack;
    @BindView(R.id.tv_wzmc)
    TextView               mTvWzmc;
    @BindView(R.id.textView2)
    TextView               mTextView2;
    @BindView(R.id.tv_dept)
    TextView               mTvDept;
    @BindView(R.id.tv_ggxh)
    TextView               mTvGgxh;
    @BindView(R.id.tv_ysr)
    TextView               mTvYsr;
    @BindView(R.id.et_ccbh)
    EditText               mEtCcbh;
    @BindView(R.id.et_zczh)
    EditText               mEtZczh;
    @BindView(R.id.ll_zczh)
    LinearLayout           mLlZczh;
    @BindView(tv_zczyxq)
    TextView               mTvZczyxq;
    @BindView(R.id.ll_zczyxq)
    LinearLayout           mLlZczyxq;
    @BindView(R.id.tv_jysynx)
    TextView               mTvJysynx;
    @BindView(R.id.et_azgcs)
    EditText               mEtAzgcs;
    @BindView(R.id.ll_azgcs)
    LinearLayout           mLlAzgcs;
    @BindView(R.id.et_gcsdh)
    EditText               mEtGcsdh;
    @BindView(R.id.ll_gcsdh)
    LinearLayout           mLlGcsdh;
    @BindView(R.id.et_bxdw)
    EditText               mEtBxdw;
    @BindView(R.id.ll_bxdw)
    LinearLayout           mLlBxdw;
    @BindView(R.id.et_bxlxr)
    EditText               mEtBxlxr;
    @BindView(R.id.ll_bxlxr)
    LinearLayout           mLlBxlxr;
    @BindView(R.id.et_bxdh)
    EditText               mEtBxdh;
    @BindView(R.id.ll_tv_bxdh)
    LinearLayout           mLlTvBxdh;
    @BindView(R.id.sl_dt)
    ScrollViewWithListView mSlDt;
    @BindView(R.id.ll_bz)
    LinearLayout           mLlBz;

    @BindView(R.id.rl_zmz)
    RelativeLayout mRlZmz;
    @BindView(R.id.rl_cmz)
    RelativeLayout mRlCmz;
    @BindView(R.id.rl_mpz)
    RelativeLayout mRlMpz;

    @BindView(R.id.iv_pic)
    ImageView    mIvPic;
    @BindView(R.id.tv_word)
    TextView     mTvWord;
    @BindView(R.id.tv_pjmx)
    TextView     mTvPjmx;
    @BindView(R.id.ll_pjmx)
    LinearLayout mLlPjmx;
    @BindView(R.id.et_inputId)
    EditText     mEtInputId;

    @BindView(R.id.iv_qianming)
    ImageView mIvQianming;
    private PUR_CONTRACT_PLAN_DETAIL bean;   //回显用 detailBean
    private List<AZYS_MX> mMXList = new ArrayList<>();
    private String whichPic;                //哪张图片
    private Map<String, ImageView> ImageMap = new HashMap<>(); //ImageView的集合

    final static String   ZMZ        = "frontUrl";//正面照
    final static String   CMZ        = "sideUrl";//侧面照
    final static String   MPZ        = "mpUrl";//铭牌照
    final static String   QMZ        = "qmUrl";//签名照
    final static String[] ZP_STRINGS = {ZMZ, CMZ, MPZ};

    //图片所在地址
    HashMap<String, String> mPicMap = new HashMap<>();
    private List<String> mIdList = new ArrayList<>();


    private String                   mBz; //备注
    private InstallDetailListAdapter mInstallDetailListAdapter;
    private String                   mParts;      //配件的返回
    private long                     mDhId;          //单号
    private Boolean                  mIsFullCome;//区分是批量进来还是单次进来回显


    @Override
    protected void initView() {
        mInstallDetailListAdapter = new InstallDetailListAdapter(mMXList, AzysDetailActivity.this);
        mSlDt.setAdapter(mInstallDetailListAdapter);
        initPhoto();
        initData();
        initGetIntent();
        backShow();
    }

    //初始化照片
    private void initPhoto() {
        ImageView iv_Zmz = (ImageView) mRlZmz.findViewById(R.id.iv_pic);
        ImageView iv_Cmz = (ImageView) mRlCmz.findViewById(R.id.iv_pic);
        ImageView iv_Mpz = (ImageView) mRlMpz.findViewById(R.id.iv_pic);

        mTvTitle.setText("设备验收详情");
        TextView tv_Zmz = (TextView) mRlZmz.findViewById(R.id.tv_word);
        TextView tv_Cmz = (TextView) mRlCmz.findViewById(R.id.tv_word);
        TextView tv_Mpz = (TextView) mRlMpz.findViewById(R.id.tv_word);
        tv_Zmz.setText("正面照");
        tv_Cmz.setText("侧面照");
        tv_Mpz.setText("铭牌照");

        ImageMap.put(ZMZ, iv_Zmz);
        ImageMap.put(CMZ, iv_Cmz);
        ImageMap.put(MPZ, iv_Mpz);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        List<AZYS_MX> list = mDaoSession.getAZYS_MXDao().queryBuilder()
                .orderAsc(AZYS_MXDao.Properties.ITEM_ID)
                .list();
        if (list == null || list.size() == 0) {
            ThreadUtils.runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.showToast("请确定是否没有明细数据...");
                }
            });
            return;
        }
        //配件个数
        mMXList.addAll(list);
        mInstallDetailListAdapter.notifyDataSetChanged();

    }


    /**
     * 初始化传递数据
     */
    private void initGetIntent() {
        Intent intent = getIntent();
        String ids = intent.getStringExtra(AZYS_DETAIL_IDS);
        //区分是批量进来还是单次进来回显
        mIsFullCome = intent.getBooleanExtra("isFirstTime", true);
        mDhId = intent.getLongExtra("dh", -1L);

        String[] split = ids.split(",");
        mIdList = Arrays.asList(split);
        //第一次进来
        if (mIsFullCome && split.length > 0) {
            bean = mDaoSession.getPUR_CONTRACT_PLAN_DETAILDao().load(Long.parseLong(mIdList.get(0)));
        } else if (!mIsFullCome && split.length == 1) {
            bean = mDaoSession.getPUR_CONTRACT_PLAN_DETAILDao().load(Long.parseLong(ids));
        }
        //配件
        mParts = bean.getPARTS();
    }


    //回显
    private void backShow() {


        showCurrentYsr();


        if (!TextUtils.isEmpty(bean.getCD_AZR())) {     //安装工程师
            mEtAzgcs.setText(bean.getCD_AZR());
        }

        mEtGcsdh.setText(bean.getCD_AZRDH());      //安装工程师的电话
        //备注
        //.setText(bean.getCD_REMARK());         //备注

        mTvWzmc.setText(bean.getWZMC());
        mTvDept.setText(bean.getDEPT_NAME());
        mTvGgxh.setText(bean.getGGXH());
        mEtCcbh.setText(bean.getCCBH());
        mEtZczh.setText(bean.getZCZH());

        Date qsdqsj = bean.getQSDQSJ();
        if (qsdqsj != null) {
            //证书到期时间
            String zsdqsj = new SimpleDateFormat("yyyy-MM-dd").format(qsdqsj);
            mTvZczyxq.setText(zsdqsj);
        }
        //建议使用年限
        if (bean.getJYSYNX() != null) {
            mTvJysynx.setText(bean.getJYSYNX() + "");
        }

        String check_items = bean.getCHECK_ITEMS();
        if (check_items != null) {
            if (!TextUtils.isEmpty(check_items)) {
                String[] split = check_items.split(",");
                for (int i = 0; i < mMXList.size(); i++) {
                    for (int j = 0; j < split.length; j++) {
                        if (mMXList.get(i).getITEM_ID().toString().equals(split[j])) {
                            mMXList.get(i).setIsChecked(true);
                        }
                    }
                }
                mInstallDetailListAdapter.notifyDataSetChanged();
            }
        }


        //保修单位
        if (bean.getBXDW_NAME() != null) {
            mEtBxdw.setText(bean.getBXDW_NAME());
        }

        //联系人
        if (bean.getLXRMC() != null) {
            mEtBxlxr.setText(bean.getLXRMC());
        }
        //联系方式
        if (bean.getLXFS() != null) {
            mEtBxdh.setText(bean.getLXFS().toString());
        }
        //验收签名那里的
        if (bean.getQMID() != null) {
            mEtInputId.setText(bean.getQMID());
        }

        //配件
        setPjsl();

        //照片的
        String frontUrl = bean.getZMZP_URL();
        String sideUrl = bean.getCMZP_URL();
        String mpUrl = bean.getMPZ_URL();
        String qmUrl = bean.getKSQSR_FILE_URL();

        mPicMap.put(ZMZ, frontUrl);
        mPicMap.put(CMZ, sideUrl);
        mPicMap.put(MPZ, mpUrl);
        mPicMap.put(QMZ, qmUrl);

        for (String s : mPicMap.keySet()) {
            String url = mPicMap.get(s);
            if (!TextUtils.isEmpty(url)) {
                Glide.with(this).load(url).into(ImageMap.get(s));
            }
        }


    }

    @OnClick({R.id.iv_back, R.id.tv_ok, R.id.ll_pjmx, R.id.ll_bz, R.id.rl_zmz,
            R.id.rl_cmz,
            R.id.rl_mpz,
            R.id.ll_zczyxq,
            R.id.ll_jysynx,
            R.id.iv_qianming
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_ok:
                gotoSave();
                break;
            case R.id.ll_pjmx:
                Intent intent = new Intent(this, PjmxActivity.class);
                intent.putExtra("parts", bean.getPARTS());
                startActivityForResult(intent, PJMX_REQUEST_CODE);
                break;
            case R.id.ll_bz:
                gotoBz();
                break;
            case R.id.rl_zmz:
                gotoChoosePic(ZMZ);
                break;
            case R.id.rl_cmz:
                gotoChoosePic(CMZ);
                break;
            case R.id.rl_mpz:
                gotoChoosePic(MPZ);
                break;
            case R.id.ll_zczyxq:   // 注册证有效期
                gotoChooseYxq();
                break;
            case R.id.ll_jysynx:    //建议使用年限
                gotoChooseNx();
                break;
            case R.id.iv_qianming:  //签名
                new QianMingUtils().showQianming(this, new QianMingUtils.OnSure() {
                    @Override
                    public void backResult(String url) {
                        if (!url.startsWith("错误")) {
                            Glide.with(AzysDetailActivity.this).load(url)
                                    .into(mIvQianming);
                            mPicMap.put(QMZ, url);
                        } else {
                            ToastUtil.showToast(url);
                        }
                    }
                });
                break;
        }
    }


    private void gotoChooseNx() {
        NumberChooseDialog.getInstance().showNum(this, new ListBottomDialog.onBackResult() {
            @Override
            public void backResult(DialogBean bean) {
                mTvJysynx.setText(bean.getName());
            }
        });
    }

    private void gotoChooseYxq() {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        TimePickerDialog build = new TimePickerDialog.Builder()
                .setCurrentMillseconds(TimeUtils.string2Milliseconds(mTvZczyxq.getText().toString() == null ? TimeUtils.getCurTimeString(format) : mTvZczyxq.getText().toString(), format))
                .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(15)
                .setCallBack(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        mTvZczyxq.setText(TimeUtils.milliseconds2String(millseconds, format));
                    }
                })
                .build();
        build.show(getSupportFragmentManager(), "1");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //拍照回来的
                case Content.REQUEST_PIC_CHOOSE:
                    List<Uri> uris = Matisse.obtainResult(data);
                    ImageView imageView = ImageMap.get(whichPic);
                    if (!TextUtils.isEmpty(whichPic)) {
                        Glide.with(this).load(uris.get(0)).into(imageView);
                        mPicMap.put(whichPic, Utils.getRealPathFromUri(uris.get(0)));
                    }
                    break;

                //备注回来的
                case BZ_REQUEST_CODE:
                    mBz = data.getStringExtra("data");
                    break;
                //配件回来的
                case PJMX_REQUEST_CODE:
                    mParts = data.getStringExtra("parts");
                    //变更数目
                    int partsnum = data.getIntExtra("partsnum", -1);
                    bean.setPARTS(mParts);
                    mTvPjmx.setText(partsnum == -1 ? "" : partsnum + "");
                    break;

            }
        }
    }


    /**
     * @param type 选的是哪个照片
     */
    private void gotoChoosePic(String type) {

        whichPic = type;
        Matisse.from(this)
                .choose(MimeType.allOf())
                .countable(true)
                .capture(true)
                .captureStrategy(
                        new CaptureStrategy(true, "coder.aihui.ui.assetquery.provider"))
                .maxSelectable(9)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                // .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(Content.REQUEST_PIC_CHOOSE);

    }

    //去备注
    private void gotoBz() {

        Intent intent = new Intent(this, BzActivity.class);
        startActivityForResult(intent, BZ_REQUEST_CODE);

    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_azys_detail;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }


    //显示当前验收人
    private void showCurrentYsr() {
        String ysr_ids = bean.getYSR_IDS();
        Object[] split = ysr_ids.split(",");
        mDaoSession.getYsrBeanDao().queryBuilder().where(YsrBeanDao.Properties.PurYsrId.in(split)).rx()
                .list()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<YsrBean>>() {
                    @Override
                    public void call(List<YsrBean> ysrLists) {
                        StringBuilder sb = new StringBuilder();
                        for (int j = 0; j < ysrLists.size(); j++) {
                            sb.append(ysrLists.get(j).getUserName());
                        }
                        mTvYsr.setText(sb.toString());
                    }
                });
    }


    //显示配件的数量
    private void setPjsl() {
        if (!TextUtils.isEmpty(mParts)) {
            List<PjmxBean> list = GsonUtil.parseJsonToList(mParts, new TypeToken<List<PjmxBean>>() {
            }.getType());
            mTvPjmx.setText(list.size() + "");
        }
    }


    //去保存
    private void gotoSave() {


        //安装工程师
        bean.setCD_AZR(mEtAzgcs.getText().toString());
        bean.setCD_AZRDH(mEtGcsdh.getText().toString());

        //备注
        bean.setCD_REMARK(mBz);

        // 出厂编号
        String num = mEtCcbh.getText().toString();
        //注册证号
        String zczh = mEtZczh.getText().toString();
        //注册证有效期
        String zsdqsj = mTvZczyxq.getText().toString();//TODO 这个得改成date格式的
        //建议使用年限
        String jysynx = mTvJysynx.getText().toString();
        //验收人签名
        String ysrQmId = mEtInputId.getText().toString().trim();


  /*      if (!jysynx.matches("^[1-9]\\d*([.][1-9])?$")) {
            ToastUtil.showToast("请输入正确的建议使用年限(整数或者一位小数)");
            return;
        }*/

        if (!TextUtils.isEmpty(ysrQmId)) {
            bean.setQMID(ysrQmId);
        }
        if (!TextUtils.isEmpty(zsdqsj)) {
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(zsdqsj);
                bean.setQSDQSJ(date);
            } catch (ParseException e) {
                ToastUtil.showToast("时间输入格式不对,请确认");
                e.printStackTrace();
                return;
            }
        } else {
            bean.setQSDQSJ(null);
        }

        if (TextUtils.isEmpty(num)) {
            ToastUtil.showToast("请填写出厂编号");
            return;
        }
        //文本的

        if (!TextUtils.isEmpty(num)) {
            bean.setCCBH(num);
        }
        if (!TextUtils.isEmpty(zczh)) {
            bean.setZCZH(zczh);
        }


        if (!TextUtils.isEmpty(jysynx)) {
            bean.setJYSYNX(Float.parseFloat(jysynx));
        }

        //图片的
        bean.setZMZP_URL(mPicMap.get(ZMZ));
        bean.setCMZP_URL(mPicMap.get(CMZ));
        bean.setMPZ_URL(mPicMap.get(MPZ));
        //签名的
        bean.setKSQSR_FILE_URL(mPicMap.get(QMZ));

        //检查状态
        bean.setCHECK_STATUS(1);


        StringBuilder sb = new StringBuilder();
        sb.append("");
        for (int i = 0; i < mMXList.size(); i++) {
            if (mMXList.get(i).getIsChecked()) {
                if (sb.toString().equals("")) {
                    sb.append(mMXList.get(i).getITEM_ID());
                } else {
                    sb.append(",").append(mMXList.get(i).getITEM_ID());
                }
            }
        }
        bean.setCHECK_ITEMS(sb.toString());
        for (int i = 0; i < mIdList.size(); i++) {
            bean.setID(Long.parseLong(mIdList.get(i)));
            mDaoSession.insertOrReplace(bean);
        }

        //单点进来的 单号数目不变
        if (mIsFullCome) {
            //单号
            DHBean dhBean = mDaoSession.getDHBeanDao().load(mDhId);
            bean.setDH_ID(dhBean.getDh());
            dhBean.setNum((dhBean.getNum() == null ? 0 : dhBean.getNum()) + mIdList.size());
            mDaoSession.insertOrReplace(dhBean);      //订单号数目的变更
        }

        Intent intent = new Intent();
        intent.putExtra("num", mIdList.size());
        intent.putExtra(AZYS_DETAIL_IDS, ListUtils.listToStrings(mIdList));

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
