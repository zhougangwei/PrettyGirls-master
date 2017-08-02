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
import coder.aihui.data.bean.DialogBean;
import coder.aihui.data.bean.IN_ASSET;
import coder.aihui.data.bean.PUB_COMPANY;
import coder.aihui.data.bean.PUB_DICTIONARY_ITEM;
import coder.aihui.data.bean.SYS_USER;
import coder.aihui.data.bean.gen.PUB_DICTIONARY_ITEMDao;
import coder.aihui.manager.DeptLocationManager;
import coder.aihui.util.viewutil.DatePickUtil;
import coder.aihui.widget.ListBottomDialog;
import coder.aihui.widget.contact.ContactActivity;
import coder.aihui.widget.contact.SysUserActivity;
import coder.aihui.widget.jdaddressselector.ISelectAble;
import coder.aihui.widget.jdaddressselector.SelectedListener;

import static coder.aihui.base.Content.COMPANY_GHDW;
import static coder.aihui.base.Content.COMPANY_ID;
import static coder.aihui.base.Content.COMPANY_REQUEST_CODE;
import static coder.aihui.base.Content.COMPANY_SCCJ;
import static coder.aihui.base.Content.COMPANY_TYPE;
import static coder.aihui.base.Content.SYSUSER_REQUEST_CODE;


public class AssetDetailEditActivity extends AppActivity implements SelectedListener {


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

        IN_ASSET load = mDaoSession.getIN_ASSETDao().load(assetId);
        if (load != null) {
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

            //   mTvBqlx.setText(load.get);          //标签类型

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
                getLocation(this);
                break;
            case R.id.ll_ghdw:              //供货单位
                gotoChooseSccj(COMPANY_GHDW);
                break;
            case R.id.ll_sccj:              //生产厂家
                gotoChooseSccj(COMPANY_SCCJ);
                break;
            case R.id.ll_bgks:              //保管科室
                getDept(this);
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
        intent.putExtra(Content.IS_MULTISELECT,false);      //是否多选
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

        finish();

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
        new ListBottomDialog(this).showDialog( mScqrList, new ListBottomDialog.onBackResult() {
            @Override
            public void backResult(DialogBean bean) {
                mTvScqr.setText(bean.getName());
            }
        });
    }

    //获取生产时间
    private void getScrq() {
        DatePickUtil.getInstance().showDatePick(mTvScrq, this, new DatePickUtil.GetResult() {
            @Override
            public void getResult(String result) {
            }
        });
    }

    @Override
    public void onAddressSelected(ArrayList<ISelectAble> selectAbles) {
        DeptLocationManager manager = new DeptLocationManager(selectAbles);



    }
}
