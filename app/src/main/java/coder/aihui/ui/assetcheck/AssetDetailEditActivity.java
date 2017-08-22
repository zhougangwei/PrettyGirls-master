package coder.aihui.ui.assetcheck;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;
import com.bumptech.glide.Glide;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.base.DeptDecotor;
import coder.aihui.base.DeptView;
import coder.aihui.base.DlwzDecotor;
import coder.aihui.base.DlwzView;
import coder.aihui.data.bean.CORRECT_ASSET;
import coder.aihui.data.bean.DialogBean;
import coder.aihui.data.bean.IN_ASSET;
import coder.aihui.data.bean.PUB_COMPANY;
import coder.aihui.data.bean.PUB_DICTIONARY_ITEM;
import coder.aihui.data.bean.SYS_USER;
import coder.aihui.data.bean.gen.CORRECT_ASSETDao;
import coder.aihui.data.bean.gen.PUB_DICTIONARY_ITEMDao;
import coder.aihui.manager.DeptLocationManager;
import coder.aihui.util.AndroidUtils;
import coder.aihui.util.SPUtil;
import coder.aihui.util.ToastUtil;
import coder.aihui.util.viewutil.TextViewUtils;
import coder.aihui.widget.ListBottomDialog;
import coder.aihui.widget.contact.ContactActivity;
import coder.aihui.widget.contact.PpmcActivity;
import coder.aihui.widget.contact.SysUserActivity;
import coder.aihui.widget.jdaddressselector.ISelectAble;

import static coder.aihui.base.Content.COMPANY_GHDW;
import static coder.aihui.base.Content.COMPANY_ID;
import static coder.aihui.base.Content.COMPANY_REQUEST_CODE;
import static coder.aihui.base.Content.COMPANY_SCCJ;
import static coder.aihui.base.Content.COMPANY_TYPE;
import static coder.aihui.base.Content.PPMC_IDS;
import static coder.aihui.base.Content.PPMC_NAMES;
import static coder.aihui.base.Content.PPMC_REQUEST_CODE;
import static coder.aihui.base.Content.SYSUSER_REQUEST_CODE;


public class AssetDetailEditActivity extends AppActivity implements DeptView, DlwzView {


    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.et_wzmc)
    EditText     mEtWzmc;
    @BindView(R.id.tv_brand)
    TextView     mTvBrand;
    @BindView(R.id.et_ggxh)
    EditText     mEtGgxh;
    @BindView(R.id.et_scbh)
    EditText     mEtScbh;
    @BindView(R.id.tv_scrq)
    TextView     mTvScrq;
    @BindView(R.id.ll_scrq)
    LinearLayout mLlScrq;
    @BindView(R.id.tv_location)
    TextView     mTvLocation;
    @BindView(R.id.ll_location)
    LinearLayout mLlLocation;
    @BindView(R.id.tv_ghdw)
    TextView     mTvGhdw;
    @BindView(R.id.ll_ghdw)
    LinearLayout mLlGhdw;
    @BindView(R.id.tv_sccj)
    TextView     mTvSccj;
    @BindView(R.id.ll_sccj)
    LinearLayout mLlSccj;
    @BindView(R.id.tv_bgks)
    TextView     mTvBgks;
    @BindView(R.id.ll_bgks)
    LinearLayout mLlBgks;
    @BindView(R.id.tv_bgr)
    TextView     mTvBgr;
    @BindView(R.id.ll_bgr)
    LinearLayout mLlBgr;
    @BindView(R.id.tv_bqlx)
    TextView     mTvBqlx;
    @BindView(R.id.ll_bqlx)
    LinearLayout mLlBqlx;
    @BindView(R.id.tv_scqr)
    TextView     mTvScqr;
    @BindView(R.id.ll_scqr)
    LinearLayout mLlScqr;
    @BindView(R.id.tv_save)
    TextView     mTvSave;

    @BindView(R.id.rl_zmz)
    RelativeLayout mRlZmz;
    @BindView(R.id.rl_cmz)
    RelativeLayout mRlCmz;
    @BindView(R.id.rl_mpz)
    RelativeLayout mRlMpz;
    @BindView(R.id.ll_wzmc)
    LinearLayout   mLlWzmc;
    @BindView(R.id.ll_ppmc)
    LinearLayout   mLlPpmc;
    @BindView(R.id.ll_ggxh)
    LinearLayout   mLlGgxh;
    @BindView(R.id.ll_scbh)
    LinearLayout   mLlScbh;
    @BindView(R.id.tv_bqyw)
    TextView       mTvBqyw;
    @BindView(R.id.ll_bqyw)
    LinearLayout   mLlBqyw;

    private List<DialogBean> mBqlxList = new ArrayList<>();     //标签类型的集合
    private List<DialogBean> mScqrList = new ArrayList<>(); //首次确认
    private List<DialogBean> mBqywList = new ArrayList<>(); //有无标签
    private int              whichPic  = -1;                //哪张图片
    private List<ImageView>  PicList   = new ArrayList<>(); //ImageView的集合
    private DeptDecotor         mDeptDecotor;
    private DlwzDecotor         mDlwzDecotor;
    private DeptLocationManager mDeptLocationManager;

    private CORRECT_ASSET       mCorrect_asset;


    HashMap<Integer, String> mPicMap = new HashMap<>();
    private long   mSccjId = -1;       //生产厂家Id
    private long   mGhdwId = -1;       //供货单位Id
    private String mPpId   = "";       //品牌Id
    private String mAllDlwzName;
    private String mAllDeptIds;
    private String mAllDlwzIds;
    private String mAllDeptName;
    private String mDeptIds;
    private String mDeptName;
    private String mDlwzIds;
    private String mDlwzName;
    private long mBgrId = -1;//保管人id
    private String mBgrXm;//保管人姓名

    @Override
    protected int getContentViewId() {
        return R.layout.activity_asset_detail_edit;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

    @Override
    protected void initView() {

        mDeptDecotor = new DeptDecotor(this, this);
        mDlwzDecotor = new DlwzDecotor(this, this);
        mDeptLocationManager = new DeptLocationManager();
        mTvTitle.setText("资产编辑");
        TextView tv_Zmz = (TextView) mRlZmz.findViewById(R.id.tv_word);
        TextView tv_Cmz = (TextView) mRlCmz.findViewById(R.id.tv_word);
        TextView tv_Mpz = (TextView) mRlMpz.findViewById(R.id.tv_word);
        tv_Zmz.setText("正面照");
        tv_Cmz.setText("侧面照");
        tv_Mpz.setText("铭牌照");


        ImageView iv_Zmz = (ImageView) mRlZmz.findViewById(R.id.iv_pic);
        ImageView iv_Cmz = (ImageView) mRlCmz.findViewById(R.id.iv_pic);
        ImageView iv_Mpz = (ImageView) mRlMpz.findViewById(R.id.iv_pic);

        PicList.add(iv_Zmz);
        PicList.add(iv_Cmz);
        PicList.add(iv_Mpz);

        //一个渲染的是主要的数据 一个渲染的是 从其他表获得的动态数据
        initGetIntent();
        initData();

    }

    private void initGetIntent() {
        Intent intent = getIntent();
        long assetId = intent.getLongExtra("assetId", 0L);

        mCorrect_asset = mDaoSession.getCORRECT_ASSETDao().
                queryBuilder().where(CORRECT_ASSETDao.Properties.ASSET_ID.eq(assetId))
                .unique();
        if (mCorrect_asset != null) {
            //使用旧的
        } else {
            //创建新的
            mCorrect_asset = createNew(assetId);
        }
        showView(mCorrect_asset);
    }

    private CORRECT_ASSET createNew(long assetId) {
        IN_ASSET load = mDaoSession.getIN_ASSETDao().load(assetId);
        mCorrect_asset = new CORRECT_ASSET();
        mCorrect_asset.setID(-1L);
        mCorrect_asset.setASSET_ID(load.getID());
        mCorrect_asset.setDDID(load.getDDID());
        mCorrect_asset.setDDMC(load.getDDMC());
        mCorrect_asset.setGHDWID(load.getGHDWID());
        mCorrect_asset.setGHDWMC(load.getGHDWMC());
        mCorrect_asset.setPPID(load.getPPID());
        mCorrect_asset.setPPMC(load.getPPMC());
        mCorrect_asset.setSCCJID(load.getSCCJID());
        mCorrect_asset.setSCCJMC(load.getSCCJMC());
        mCorrect_asset.setGGXH(load.getGGXH());
        mCorrect_asset.setSCBH(load.getSCBH());
        mCorrect_asset.setSCRQ(load.getSCRQ());
        mCorrect_asset.setWZMC(load.getWZMC());
        mCorrect_asset.setBGRID(load.getBGRID());
        mCorrect_asset.setBGKSID(load.getBGKSID());
        mCorrect_asset.setBGKSMC(load.getBGKSMC());
        return mCorrect_asset;
    }

    /**
     * 使用旧的来渲染数据
     *
     * @param load 对象
     */
    private void showView(CORRECT_ASSET load) {

        mEtWzmc.setText(load.getWZMC());        //物资名称
        mTvBrand.setText(load.getPPMC());       //品牌名称
        mEtGgxh.setText(load.getGGXH());       //规格型号
        mEtScbh.setText(load.getSCBH());       //生产编号

        if (load.getSCRQ() != null) {
            mTvScrq.setText(TimeUtils.date2String(load.getSCRQ(), new SimpleDateFormat("yyyy-MM-dd")));//生产日期
        }
        mTvLocation.setText(load.getDDMC());      //地理位子
        mTvGhdw.setText(load.getGHDWMC());  //供货单位
        mTvSccj.setText(load.getSCCJMC());  //生产厂家
        mTvBgks.setText(load.getBGKSMC());  //保管科室
        mTvBgr.setText(load.getBGRXM());  //保管人员

        //标签有无
        mTvBqyw.setText("0".equals(load.getBQZT()) ? "无" : ("1".equals(load.getBQZT()) ? "有" : ""));
        //确认标志
        mTvScqr.setText("0".equals(load.getQRBZ()) ? "否" : ("1".equals(load.getBQZT()) ? "是" : ""));

        //生产日期
        if (load.getSCRQ() != null) {
            mTvScrq.setText(TimeUtils.date2String(load.getSCRQ(), mFormat));
        }
        //标签类型
        String bqlx = load.getBQLX();
        if (!TextUtils.isEmpty(bqlx)) {
            PUB_DICTIONARY_ITEM bean = mDaoSession.getPUB_DICTIONARY_ITEMDao().load(Long.valueOf(bqlx));
            if (bean != null) {
                mTvBqlx.setText(bean.getITEM_NAME());
            }
        }
    }

    //获取数据
    private void initData() {

        //获得标签类型
        List<PUB_DICTIONARY_ITEM> pub_dictionary_items = mDaoSession.getPUB_DICTIONARY_ITEMDao().
                queryBuilder().
                where(PUB_DICTIONARY_ITEMDao.Properties.DICT_ID.eq("007309")
                ).list();
        for (PUB_DICTIONARY_ITEM pub_dictionary_item : pub_dictionary_items) {
            DialogBean dialogBean = new DialogBean();
            dialogBean.setObject(pub_dictionary_item);
            dialogBean.setName(pub_dictionary_item.getITEM_NAME());
            mBqlxList.add(dialogBean);
        }
        //获得

        DialogBean dialogBean = new DialogBean();
        dialogBean.setName("是");
        DialogBean dialogBean2 = new DialogBean();
        dialogBean2.setName("否");
        mScqrList.add(dialogBean);
        mScqrList.add(dialogBean2);


        DialogBean dialogBean3 = new DialogBean();
        dialogBean3.setName("有");
        DialogBean dialogBean4 = new DialogBean();
        dialogBean4.setName("无");

        mBqywList.add(dialogBean3);
        mBqywList.add(dialogBean4);


    }


    @OnClick({R.id.iv_back, R.id.tv_save, R.id.ll_scrq, R.id.ll_location, R.id.ll_ghdw, R.id.ll_sccj
            , R.id.ll_bgks, R.id.ll_bgr, R.id.ll_bqlx, R.id.ll_scqr, R.id.rl_zmz, R.id.rl_cmz
            , R.id.rl_mpz, R.id.ll_ppmc, R.id.ll_bqyw
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_save:
                gotoJudgeSave();
                break;

            case R.id.ll_scrq:              //生产日期
                getScrq();
                break;
            case R.id.ll_location:          //地理位子
                getDlwz();
                break;
            case R.id.ll_ghdw:              //供货单位
                gotoChooseSccj(COMPANY_GHDW);
                break;
            case R.id.ll_sccj:              //生产厂家
                gotoChooseSccj(COMPANY_SCCJ);
                break;
            case R.id.ll_bgks:              //保管科室
                getDept();
                break;
            case R.id.ll_bgr:                  //保管人
                gotoGetBgr();
                break;
            case R.id.ll_bqlx:              //标签类型
                getBqLx();
                break;
            case R.id.ll_scqr:              //首次确认
                getScqr();
                break;

            case R.id.rl_zmz:               //正面照
                gotoChoosePic(0);
                break;
            case R.id.rl_cmz:               //侧面照
                gotoChoosePic(1);
                break;
            case R.id.rl_mpz:               //铭牌照
                gotoChoosePic(2);
                break;

            case R.id.ll_ppmc:               //品牌名称
                gotoInputPpmc();
                break;
            case R.id.ll_bqyw:              //有无标签
                gotoChooseBqyw();
                break;
            default:
                break;

        }
    }

    private void gotoChooseBqyw() {

        new ListBottomDialog(this).showDialog(mScqrList, new ListBottomDialog.onBackResult() {
            @Override
            public void backResult(DialogBean bean) {
                mTvBqyw.setText(bean.getName());
            }
        });
    }


    //输入品牌名称
    private void gotoInputPpmc() {
        Intent intent = new Intent(this, PpmcActivity.class);
        startActivityForResult(intent, PPMC_REQUEST_CODE);
    }


    //选取保管人
    private void gotoGetBgr() {
        Intent intent = new Intent(this, SysUserActivity.class);
        intent.putExtra(Content.IS_MULTISELECT, false);      //是否多选
        startActivityForResult(intent, SYSUSER_REQUEST_CODE);

    }

    //选择生产厂家
    private void gotoChooseSccj(int companyType) {
        Intent intent = new Intent(this, ContactActivity.class);
        intent.putExtra(COMPANY_TYPE, companyType);                   //查询的是生产厂家还是保修单位之类的
        startActivityForResult(intent, COMPANY_REQUEST_CODE);

    }

    //选取图片
    private void gotoChoosePic(int type) {
        whichPic = type;
        Matisse.from(this)
                .choose(MimeType.allOf())
                .countable(true)
                .capture(true)
                .captureStrategy(
                        new CaptureStrategy(true, "coder.aihui.ui.assetquery.provider"))
                .maxSelectable(1)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                // .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(Content.REQUEST_PIC_CHOOSE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //拍照回来的
                case Content.REQUEST_PIC_CHOOSE:
                    List<Uri> uris = Matisse.obtainResult(data);
                    if (whichPic != -1) {
                        ImageView imageView = PicList.get(whichPic);
                        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                        imageView.setLayoutParams(layoutParams);

                        Glide.with(this).load(uris.get(0)).into(imageView);
                        mPicMap.put(whichPic, uris.get(0).toString());
                    }
                    break;
                //选择公司表回来的
                case COMPANY_REQUEST_CODE:
                    long companyId = data.getLongExtra(COMPANY_ID, -1L);
                    int companyType = data.getIntExtra(COMPANY_TYPE, -1);
                    PUB_COMPANY load = mDaoSession.getPUB_COMPANYDao().load(companyId);
                    if (load == null) {
                        return;
                    }
                    switch (companyType) {
                        case COMPANY_GHDW:
                            mGhdwId = companyId;        //供货单位的Id
                            mTvGhdw.setText(load.getMC());
                            break;
                        case COMPANY_SCCJ:
                            mSccjId = companyId;        //生产厂家的Id
                            mTvSccj.setText(load.getMC());
                            break;
                    }
                    break;


                //保管人回来的
                case SYSUSER_REQUEST_CODE:
                    mBgrId = data.getLongExtra("userId", -1L);
                    mBgrXm = data.getStringExtra("userName");
                    SYS_USER user = mDaoSession.getSYS_USERDao().load(mBgrId);
                    if (user != null) {
                        mTvBgr.setText(user.getUSER_NAME());
                    }
                    break;
                case PPMC_REQUEST_CODE:
                    mPpId = data.getStringExtra(PPMC_IDS);
                    String nameExtra = data.getStringExtra(PPMC_NAMES);
                    if (!TextUtils.isEmpty(nameExtra)) {
                        mTvBrand.setText(nameExtra);
                    }


            }
        }
    }

    //保存数据
    private void gotoJudgeSave() {
        if (TextViewUtils.isEmpty(mEtWzmc)) {
            ToastUtil.showToast("名称不能为空!");
        }
        if (TextViewUtils.isEmpty(mTvLocation)) {
            ToastUtil.showToast("地理位子不能为空!");
        }
        gotoSave();

    }


    private void gotoSave() {
        try {
            //标签类型
            String bqlx = mTvBqlx.getText().toString();
            for (int i = 0; i < mBqlxList.size(); i++) {
                PUB_DICTIONARY_ITEM bean = (PUB_DICTIONARY_ITEM) mBqlxList.get(i).getObject();
                if (bean.getITEM_NAME().equals(bqlx)) {
                    mCorrect_asset.setBQLX(bean.getITEM_ID() + "");
                }
            }
            String s = mTvScqr.getText().toString();
            //1是已确认 0是未确认
            if ("是".equals(s)) {
                mCorrect_asset.setQRBZ(1);
            } else if ("否".equals(s)) {
                mCorrect_asset.setQRBZ(0);
            }
            //标签有无
            String bqyw = mTvBqyw.getText().toString();
            //1是有标签 0是无标签
            if ("有".equals(bqyw)) {
                mCorrect_asset.setQRBZ(1);
            } else if ("无".equals(bqyw)) {
                mCorrect_asset.setQRBZ(0);
            }
            //生产日期
            String scrq = mTvScrq.getText().toString();
            if (!TextUtils.isEmpty(scrq)) {
                mCorrect_asset.setSCRQ(TimeUtils.string2Date(scrq, mFormat));
            }
            //图片
            mCorrect_asset.setFRONT_PIC(mPicMap.get(0));
            mCorrect_asset.setSIDE_PIC(mPicMap.get(1));
            mCorrect_asset.setMP_PIC(mPicMap.get(2));
            //原图的地址存起来

            //地点名称
            mCorrect_asset.setDDMC(mDlwzName);
            mCorrect_asset.setDDID(mDlwzIds);

            //保管科室
            mCorrect_asset.setBGKSMC(mDeptName);
            mCorrect_asset.setBGKSID(mDeptIds);

            //保管人
            mCorrect_asset.setBGRID(mBgrId == -1 ? null : mBgrId + "");
            mCorrect_asset.setBGRXM(mBgrXm);

            if (!TextViewUtils.isEmpty(mTvBrand)) {
                mCorrect_asset.setPPMC(mTvBrand.getText().toString());//供货单位
                //品牌id
                mCorrect_asset.setPPID(TextUtils.isEmpty(mPpId) ? null : (mPpId + ""));
            }
            if (!TextViewUtils.isEmpty(mEtGgxh)) {
                mCorrect_asset.setGGXH(mEtGgxh.getText().toString()); //型号
            }
            if (!TextViewUtils.isEmpty(mEtWzmc)) {
                mCorrect_asset.setWZMC(mEtWzmc.getText().toString()); //物资名称
            }
            if (!TextViewUtils.isEmpty(mEtScbh)) {
                mCorrect_asset.setSCBH(mEtScbh.getText().toString());//生产编号
            }

            if (!TextViewUtils.isEmpty(mTvGhdw)) {
                mCorrect_asset.setGHDWMC(mTvGhdw.getText().toString());//供货单位
                //供货单位Id
                mCorrect_asset.setGHDWID(mGhdwId == -1 ? null : (mGhdwId + ""));
            }
            if (!TextViewUtils.isEmpty(mTvSccj)) {
                mCorrect_asset.setSCCJMC(mTvSccj.getText().toString()); //生产厂家
                mCorrect_asset.setSCCJID(mSccjId == -1 ? null : (mSccjId + ""));
            }

            mCorrect_asset.setUSERID(SPUtil.getUserId(this));
            mCorrect_asset.setUSERNAME(SPUtil.getUserName(this));
            //变化了 就修改状态为没有上传过
            mCorrect_asset.setIS_UP(0);
            if (-1L == mCorrect_asset.getID()) {
                mCorrect_asset.setID(null);
            }
            mDaoSession.insertOrReplace(mCorrect_asset);
            mDaoSession.clear();
            ToastUtil.showToast("保存成功!");
            setResult(RESULT_OK);

            finish();
        } catch (Exception e) {
            e.printStackTrace();
            AndroidUtils.showErrorMsg("错误1", e.getMessage(), this);
        }

    }


    //获取保管类型
    private void getBqLx() {
        new ListBottomDialog(this).showDialog(mBqlxList, new ListBottomDialog.onBackResult() {
            @Override
            public void backResult(DialogBean bean) {
                mTvBqlx.setText(bean.getName());
            }
        });
    }


    //获取首次确认
    private void getScqr() {
        new ListBottomDialog(this).showDialog(mScqrList, new ListBottomDialog.onBackResult() {
            @Override
            public void backResult(DialogBean bean) {
                mTvScqr.setText(bean.getName());
            }
        });
    }

    //获取生产时间
    private void getScrq() {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        TimePickerDialog build = new TimePickerDialog.Builder()
                .setCurrentMillseconds(TimeUtils.string2Milliseconds(mTvScrq.getText().toString() == null ? TimeUtils.getCurTimeString(format) : mTvScrq.getText().toString(), format))
                .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(15)
                .setCallBack(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        mTvScrq.setText(TimeUtils.milliseconds2String(millseconds, format));
                    }
                })
                .build();
        build.show(getSupportFragmentManager(), "1");
    }

    @Override
    public void onAddressSelected(ArrayList<ISelectAble> selectAbles) {

        mDeptLocationManager.solveDatas(selectAbles);
        mAllDeptIds = mDeptLocationManager.getAllDeptIds();
        mAllDlwzIds = mDeptLocationManager.getAllDlwzIds();
        mAllDeptName = mDeptLocationManager.getAllDeptName();
        mAllDlwzName = mDeptLocationManager.getAllDlwzName();
        mDeptIds = mDeptLocationManager.getDeptIds();
        mDeptName = mDeptLocationManager.getDeptName();
        mDlwzIds = mDeptLocationManager.getDlwzIds();
        mDlwzName = mDeptLocationManager.getDlwzName();


        mTvBgks.setText(mAllDeptName);
        mTvLocation.setText(mAllDlwzName);


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
