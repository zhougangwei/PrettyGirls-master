package coder.aihui.ui.assetcheck;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;
import com.bumptech.glide.Glide;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
import coder.aihui.util.ToastUtil;
import coder.aihui.util.viewutil.TextViewUtils;
import coder.aihui.widget.ListBottomDialog;
import coder.aihui.widget.contact.ContactActivity;
import coder.aihui.widget.contact.SysUserActivity;
import coder.aihui.widget.jdaddressselector.ISelectAble;

import static coder.aihui.base.Content.COMPANY_GHDW;
import static coder.aihui.base.Content.COMPANY_ID;
import static coder.aihui.base.Content.COMPANY_REQUEST_CODE;
import static coder.aihui.base.Content.COMPANY_SCCJ;
import static coder.aihui.base.Content.COMPANY_TYPE;
import static coder.aihui.base.Content.SYSUSER_REQUEST_CODE;


public class AssetDetailEditActivity extends AppActivity implements DeptView, DlwzView {


    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.tv_wzmc)
    TextView     mTvWzmc;
    @BindView(R.id.tv_brand)
    TextView     mTvBrand;
    @BindView(R.id.tv_ggxh)
    TextView     mTvGgxh;
    @BindView(R.id.tv_scbh)
    TextView     mTvScbh;
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
    private List<DialogBean> mBqlxList = new ArrayList<>();     //标签类型的集合
    private List<DialogBean> mScqrList = new ArrayList<>(); //首次确认
    private int              whichPic  = -1;                //哪张图片
    private List<ImageView>  PicList   = new ArrayList<>(); //ImageView的集合
    private DeptDecotor         mDeptDecotor;
    private DlwzDecotor         mDlwzDecotor;
    private DeptLocationManager mDeptLocationManager;
    private CORRECT_ASSET mCorrect;

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

        mCorrect = mDaoSession.getCORRECT_ASSETDao().
                queryBuilder().where(CORRECT_ASSETDao.Properties.ASSET_ID.eq(assetId))
                .unique();
        if (mCorrect != null) {
            //使用旧的
        } else {
            //创建新的
            mCorrect = createNew(assetId);
        }
        showView(mCorrect);
    }

    private CORRECT_ASSET createNew(long assetId) {
        IN_ASSET load = mDaoSession.getIN_ASSETDao().load(assetId);
        CORRECT_ASSET correct_asset = new CORRECT_ASSET();

        correct_asset.setID(-1L);

        correct_asset.setASSET_ID(load.getID());
        correct_asset.setDDID(load.getDDID());
        correct_asset.setDDMC(load.getDDMC());
        correct_asset.setGHDWID(load.getGHDWID());
        correct_asset.setGHDWMC(load.getGHDWMC());
        correct_asset.setPPID(load.getPPID());
        correct_asset.setPPMC(load.getPPMC());
        correct_asset.setSCCJID(load.getSCCJID());
        correct_asset.setSCCJMC(load.getSCCJMC());
        correct_asset.setGGXH(load.getGGXH());
        correct_asset.setSCBH(load.getSCBH());
    /*    correct_asset.setUSERID(load.getUSERID());
        correct_asset.setUSERNAME(load.getUSERNAME());
        correct_asset.setIS_UP(load.getIS_UP());*/
        correct_asset.setSCRQ(load.getSCRQ());
        correct_asset.setWZMC(load.getWZMC());
/*        correct_asset.setFRONT_PIC(load.getFRONT_PIC());
        correct_asset.setSIDE_PIC(load.getSIDE_PIC());
        correct_asset.setBIG_FRONT_PIC(load.getBIG_FRONT_PIC());
        correct_asset.setBIG_SIDE_PIC(load.getBIG_SIDE_PIC());
        correct_asset.setZMZ_FILE_ID(load.getZMZ_FILE_ID());
        correct_asset.setCMZ_FILE_ID(load.getCMZ_FILE_ID());
        correct_asset.setQD_FLAG(load.getQD_FLAG());*/
        correct_asset.setBGRID(load.getBGRID());
        correct_asset.setBGKSID(load.getBGKSID());
        correct_asset.setBGKSMC(load.getBGKSMC());
/*        correct_asset.setBIG_MP_PIC(load.getBIG_MP_PIC());
        correct_asset.setMP_PIC(load.getMP_PIC());
        correct_asset.setBQLX(load.getBQLX());
        correct_asset.setQRBZ(load.getQRBZ());
        correct_asset.setBQYCZT(load.getBQYCZT());
        correct_asset.setBQZT(load.getBQZT());*/


        return correct_asset;
    }

    /**
     * 使用旧的来渲染数据
     *
     * @param load 对象
     */
    private void showView(CORRECT_ASSET load) {

        mTvWzmc.setText(load.getWZMC());        //物资名称
        mTvBrand.setText(load.getPPMC());       //品牌名称
        mTvGgxh.setText(load.getGGXH());       //规格型号
        mTvScbh.setText(load.getSCBH());       //生产编号

        if (load.getSCRQ() != null) {
            mTvScrq.setText(TimeUtils.date2String(load.getSCRQ(), new SimpleDateFormat("yyyy-MM-dd")));//生产日期
        }
        mTvLocation.setText(load.getDDMC());      //地理位子
        mTvGhdw.setText(load.getGHDWMC());  //供货单位
        mTvSccj.setText(load.getSCCJMC());  //生产厂家
        mTvBgks.setText(load.getBGKSMC());  //保管科室
        mTvBgr.setText(load.getBGRXM());  //保管人员

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


    }


    @OnClick({R.id.iv_back, R.id.tv_save, R.id.ll_scrq, R.id.ll_location, R.id.ll_ghdw, R.id.ll_sccj, R.id.ll_bgks, R.id.ll_bgr, R.id.ll_bqlx, R.id.ll_scqr, R.id.rl_zmz, R.id.rl_cmz, R.id.rl_mpz})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_save:
                gotoSave();
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
        }
    }

    private void gotoGetBgr() {
        Intent intent = new Intent(this, SysUserActivity.class);
        intent.putExtra(Content.IS_MULTISELECT, false);      //是否多选
        startActivityForResult(intent, SYSUSER_REQUEST_CODE);

    }

    private void gotoChooseSccj(int companyType) {
        Intent intent = new Intent(this, ContactActivity.class);
        intent.putExtra(COMPANY_TYPE, companyType);                   //查询的是生产厂家还是保修单位之类的
        startActivityForResult(intent, COMPANY_REQUEST_CODE);

    }

    private void gotoChoosePic(int type) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //拍照回来的
                case Content.REQUEST_PIC_CHOOSE:
                    List<Uri> uris = Matisse.obtainResult(data);
                    if (whichPic != -1) {
                        Glide.with(this).load(uris.get(0)).into(PicList.get(whichPic));
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
                            mTvGhdw.setText(load.getMC());
                            break;
                        case COMPANY_SCCJ:
                            mTvSccj.setText(load.getMC());
                            break;
                    }
                    break;

                case SYSUSER_REQUEST_CODE:
                    long userId = data.getLongExtra("userId", -1L);
                    SYS_USER user = mDaoSession.getSYS_USERDao().load(userId);
                    if (user != null) {
                        mTvBgr.setText(user.getUSER_NAME());
                    }
            }
        }
    }

    //保存数据
    private void gotoSave() {
        if (TextViewUtils.isEmpty(mTvWzmc)) {
            ToastUtil.showToast("名称不能为空!");
        }
        if (TextViewUtils.isEmpty(mTvLocation)) {
            ToastUtil.showToast("地理位子不能为空!");
        }





        finish();
    }

/*
    private void gotoSave() {
        try {

            CORRECT_ASSET correct_asset = new CORRECT_ASSET();

            //标签类型
            String bqlx = (String) mSpBqlx.getSelectedItem();
            for (int i = 0; i < mBqlxList.size(); i++) {
                if (mBqlxList.get(i).getITEM_NAME().equals(bqlx)) {
                    correct_asset.setBQLX(mBqlxList.get(i).getITEM_ID() + "");
                }
            }
            int selectedItemPosition = mSpScqr.getSelectedItemPosition();

            if (selectedItemPosition == 1) {
                correct_asset.setQRBZ(1);

            } else if (selectedItemPosition == 2) {
                correct_asset.setQRBZ(0);
            }

            //标签有无
            int selectedItemPosition1 = mSpBqyw.getSelectedItemPosition();
            if (selectedItemPosition1 == 0) {
                correct_asset.setBQZT("1");

            } else if (selectedItemPosition1 == 1) {
                correct_asset.setBQZT("0");
            }
            correct_asset.setBQYCZT(mTvBqyczt.getText().toString());

            //生产日期
            correct_asset.setSCRQ(bean.getSCRQ());


            //图片
            correct_asset.setFRONT_PIC(frontUrl);
            correct_asset.setSIDE_PIC(sideUrl);
            correct_asset.setMP_PIC(mpUrl);

            correct_asset.setBGKSID(bean.getBGKSID());
            correct_asset.setBGRID(bean.getBGRID());
            correct_asset.setBGKSMC(bean.getBGKSMC());
            correct_asset.setBGRXM(bean.getBGRXM());


            //原图的地址存起来

            if (alfront.size() != 0) {
                correct_asset.setBIG_FRONT_PIC(alfront.get(0));
            }
            if (alside.size() != 0) {
                correct_asset.setBIG_SIDE_PIC(alside.get(0));
            }
            if (alMp.size() != 0) {
                correct_asset.setBIG_MP_PIC(alMp.get(0));
            }


            correct_asset.setDDMC(bean.getDDMC());
            correct_asset.setDDID(bean.getDDID());
            //要先变成空的 不然 没法判断是新的还是旧的
            correct_asset.setPPID("");
            correct_asset.setGHDWID("");
            correct_asset.setSCCJID("");


            if (!TextViewUtils.isEmpty(mAtBrand)) {
                correct_asset.setPPMC(mAtBrand.getText().toString()); //品牌
                //品牌Id
                for (int i = 0; i < mPpmcList.size(); i++) {
                    if (mAtBrand.getText().toString().equals(mPpmcList.get(i).getMC())) {
                        correct_asset.setPPID(mPpmcList.get(i).getID().toString());
                        break;
                    }
                }
            }

            try {
                if (!TextViewUtils.isEmpty(mAtBgr)) {
                    String string = mAtBgr.getText().toString().trim();
                    String[] split = string.split("\\(");
                    String name = split[0];
                    String aaId = split[1];

                    Log.d("HandCorrectDetailActivi", name);
                    String id = aaId.substring(0, aaId.length() - 1);
                    Log.d("HandCorrectDetailActivi", id);
                    correct_asset.setBGRXM(name);
                    correct_asset.setBGRID(id);
                }
            } catch (Exception e) {
                ToastUtil.showToast("保管人数据错误!");
            }

            if (!TextViewUtils.isEmpty(mEtXh)) {
                correct_asset.setGGXH(mEtXh.getText().toString()); //型号
            }

            if (!TextViewUtils.isEmpty(mEtSbmc)) {
                correct_asset.setWZMC(mEtSbmc.getText().toString()); //物资名称
            }
            if (!TextViewUtils.isEmpty(mEtScbh)) {
                correct_asset.setSCBH(mEtScbh.getText().toString());//生产编号
            }


            if (!TextViewUtils.isEmpty(mAtBxdw)) {
                correct_asset.setGHDWMC(mAtBxdw.getText().toString());//供货单位
                //供货单位Id
                for (int i = 0; i < mGhdwList.size(); i++) {
                    if (mAtBxdw.getText().toString().equals(mGhdwList.get(i).getMC())) {
                        correct_asset.setGHDWID(mGhdwList.get(i).getID().toString());
                        break;
                    }
                }
            }
            if (!TextViewUtils.isEmpty(mAtSccj)) {
                correct_asset.setSCCJMC(mAtSccj.getText().toString()); //生产厂家
                for (int i = 0; i < mSccjList.size(); i++) {
                    if (mAtSccj.getText().toString().equals(mSccjList.get(i).getMC())) {
                        correct_asset.setSCCJID(mSccjList.get(i).getID().toString());
                        break;
                    }
                }

            }




            //品牌Id
            if (TextUtils.isEmpty(correct_asset.getPPID()) && !TextUtils.isEmpty(correct_asset.getPPMC())) {
                //空的话 给标识
                PP_FLAG = 1;                //1是新的 0 或者没有就是旧的
                //同时将他存起来
                IN_MATERIALS_PPMC ppmc = new IN_MATERIALS_PPMC();
                ppmc.setMC(correct_asset.getPPMC());
                mDaoSession.insertOrReplace(ppmc);
            }
            //供货单位Id
            if (TextUtils.isEmpty(correct_asset.getGHDWID()) && !TextUtils.isEmpty(correct_asset.getGHDWMC())) {
                //空的话 给标识
                GHDW_FLAG = 1;                //1是新的 0 或者没有就是旧的
                PUB_COMPANY pub_company = new PUB_COMPANY();
                pub_company.setMC(correct_asset.getGHDWMC());
                pub_company.setLX(1);
                mDaoSession.insertOrReplace(pub_company);
            }

            //生产厂家
            if (TextUtils.isEmpty(correct_asset.getSCCJID()) && !TextUtils.isEmpty(correct_asset.getSCCJMC())) {
                //空的话 给标识
                SCCJ_FLAG = 1;                //1是新的 0 或者没有就是旧的
                PUB_COMPANY pub_company1 = new PUB_COMPANY();
                pub_company1.setMC(correct_asset.getSCCJMC());
                pub_company1.setLX(2);
                mDaoSession.insertOrReplace(pub_company1);
            }

            //更新


            String json = GsonUtil.parseObjectToJson(correct_asset);
            JSONObject jsonObject = new JSONObject(json);
            jsonObject.put("PP_FLAG", PP_FLAG);
            jsonObject.put("GHDW_FLAG", GHDW_FLAG);
            jsonObject.put("SCCJ_FLAG", SCCJ_FLAG);
            jsonObject.put("DD_FLAG", DDWZ_FLAG);        //这个因为涉及到使从另外一个activity过来的

            String userID = SpUtil.getString(this, "userID", "");
            String userName = SpUtil.getString(this, "userName", "");
            jsonObject.put("userId", userID);
            jsonObject.put("userName", userName);
            //网络上给过去


            Log.d("HandCorrectDetailActivi", "jsonObject:" + jsonObject);


            //   mDaoSession.update(mAsset);

            correct_asset.setPP_FLAG(PP_FLAG);
            correct_asset.setGHDW_FLAG(GHDW_FLAG);
            correct_asset.setSCCJ_FLAG(SCCJ_FLAG);
            correct_asset.setDD_FLAG(DDWZ_FLAG);

            correct_asset.setUSERID(userID);
            correct_asset.setUSERNAME(userName);


            //变化了 就修改状态为没有上传过
            correct_asset.setIS_UP(0);


            Log.d("HandCorrectDetailActivi", correct_asset.toString());
            mDaoSession.insertOrReplace(correct_asset);

            mDaoSession.clear();

            ToastUtil.showToast("保存成功!");

            finish();
        } catch (Exception e) {

            AndroidUtils.showErrorMsg("错误1", e.getMessage(), this);

        }

    }
*/



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
                .build();
        build.show(getSupportFragmentManager(), "1");
    }

    @Override
    public void onAddressSelected(ArrayList<ISelectAble> selectAbles) {

        mDeptLocationManager.solveDatas(selectAbles);
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
}
