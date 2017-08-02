package coder.aihui.ui.assetcheck;

import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.data.bean.IN_ASSET;
import coder.aihui.data.normalbean.ExpandAssetBean;
import coder.aihui.data.normalbean.ExpandSonBean;
import coder.aihui.widget.ScrollViewWithExpandListView;

public class AssetDetailActivity extends AppActivity {



    @BindView(R.id.tv_kpbh)
    TextView     mTvKpbh;
    @BindView(R.id.ll_kpbh)
    LinearLayout mLlKpbh;
    @BindView(R.id.tv_wzmc)
    TextView     mTvWzmc;
    @BindView(R.id.ll_wzmc)
    LinearLayout mLlWzmc;
    @BindView(R.id.tv_ppmc)
    TextView     mTvPpmc;
    @BindView(R.id.ll_ppmc)
    LinearLayout mLlPpmc;
    @BindView(R.id.tv_ggxh)
    TextView     mTvGgxh;
    @BindView(R.id.ll_ggxh)
    LinearLayout mLlGgxh;
    @BindView(R.id.tv_dept)
    TextView     mTvDept;
    @BindView(R.id.ll_dept)
    LinearLayout mLlDept;
    @BindView(R.id.tv_location)
    TextView     mTvLocation;
    @BindView(R.id.ll_location)
    LinearLayout mLlLocation;
    @BindView(R.id.tv_bxdw)
    TextView     mTvBxdw;
    @BindView(R.id.ll_bxdw)
    LinearLayout mLlBxdw;
    @BindView(R.id.tv_bxjzrq)
    TextView     mTvBxjzrq;
    @BindView(R.id.ll_bxjzrq)
    LinearLayout mLlBxjzrq;
    @BindView(R.id.tv_bxlxr)
    TextView     mTvBxlxr;

    @BindView(R.id.tv_synx)
    TextView                     mTvSynx;
    @BindView(R.id.ll_bxlxr)
    LinearLayout                 mLlBxlxr;
    @BindView(R.id.tv_ccbh)
    TextView                     mTvCcbh;
    @BindView(R.id.ll_ccbh)
    LinearLayout                 mLlCcbh;
    @BindView(R.id.el)
    ScrollViewWithExpandListView mEl;

    @BindView(R.id.sv)
    ScrollView mSv;
    private List<ExpandAssetBean> mExpandList = new ArrayList<>();
    private AssetDetailAdapter mAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_asset_detail;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }



    @Override
    protected void initView() {

        mAdapter = new AssetDetailAdapter(this, mExpandList);
        mEl.setGroupIndicator(null);//去掉自带的箭头


        mEl.setAdapter(mAdapter);
        initGetIntent();


    }

    private void initGetIntent() {
        Long assetId = getIntent().getLongExtra("assetId", -1);
        initDatas(assetId);

    }

    private void initDatas(Long assetId) {
        IN_ASSET asset = mDaoSession.getIN_ASSETDao().load(assetId);
        if (asset != null) {

            mTvKpbh.setText(asset.getKPBH());//放入卡片编号
            asset.getRFID_CODE();
            mTvWzmc.setText(asset.getWZMC());
            mTvPpmc.setText(asset.getPPMC());
            mTvGgxh.setText(asset.getGGXH());

            Date syrq = asset.getSYRQ();
            int betYear = 0;
            if (syrq != null) {
                betYear = new Date().getYear() - syrq.getYear();

            }
            mTvSynx.setText("此设备已使用" + betYear + "年");

            mTvDept.setText(asset.getKSMC());
            mTvLocation.setText(asset.getDDMC());


            //这里可能有出入  就是 保修单位 我填的是供货单位
            //  mTvBxdw.setText(asset.getGHDWMC());
            mTvBxjzrq.setText(asset.getBXRQ() == null ? "" : TimeUtils.date2String(asset.getBXRQ()));
            mTvCcbh.setText(asset.getSCBH());

            //以上是 直接渲染 一下是expanList渲染



            ExpandAssetBean expandAssetBean = new <ExpandSonBean>ExpandAssetBean();
            expandAssetBean.setName("台账信息");
            List<ExpandSonBean> list = new ArrayList<>();

            list.add(new ExpandSonBean("旧编号",asset.getKPBH_OLD()));
            list.add(new ExpandSonBean("启用日期",asset.getSYRQ() == null ? "" : TimeUtils.date2String(asset.getSYRQ(),new SimpleDateFormat("yyyy-MM-dd"))));
            list.add(new ExpandSonBean("保管人",asset.getBGRXM()));
            expandAssetBean.setSonBean(list);


            ExpandAssetBean expandAssetBean2 = new ExpandAssetBean();
            expandAssetBean2.setName("台账图片及附件");
            ExpandAssetBean expandAssetBean3 = new ExpandAssetBean();
            expandAssetBean3.setName("维修信息");
            ExpandAssetBean expandAssetBean4 = new ExpandAssetBean();
            expandAssetBean4.setName("位置变动");
            ExpandAssetBean expandAssetBean5 = new ExpandAssetBean();
            expandAssetBean5.setName("转科信息");
            ExpandAssetBean expandAssetBean6 = new ExpandAssetBean();
            expandAssetBean6.setName("培训记录");
            ExpandAssetBean expandAssetBean7 = new ExpandAssetBean();

            expandAssetBean7.setName("配件信息");
            ExpandAssetBean expandAssetBean8 = new ExpandAssetBean();
            expandAssetBean8.setName("折旧信息");
            ExpandAssetBean expandAssetBean9 = new ExpandAssetBean();

            expandAssetBean9.setName("巡检PM");
            ExpandAssetBean expandAssetBean10 = new ExpandAssetBean();
            expandAssetBean10.setName("计量信息");
            ExpandAssetBean expandAssetBean11 = new ExpandAssetBean();
            expandAssetBean11.setName("使用记录");
            ExpandAssetBean expandAssetBean12 = new ExpandAssetBean();

            expandAssetBean12.setName("借用租赁");
            ExpandAssetBean expandAssetBean13 = new ExpandAssetBean();
            expandAssetBean13.setName("不良事件");



            mExpandList.add(expandAssetBean);
            mExpandList.add(expandAssetBean2);
            mExpandList.add(expandAssetBean3);
            mExpandList.add(expandAssetBean4);
            mExpandList.add(expandAssetBean5);
            mExpandList.add(expandAssetBean6);
            mExpandList.add(expandAssetBean7);
            mExpandList.add(expandAssetBean8);
            mExpandList.add(expandAssetBean9);
            mExpandList.add(expandAssetBean10);
            mExpandList.add(expandAssetBean11);
            mExpandList.add(expandAssetBean12);
            mExpandList.add(expandAssetBean13);


            mAdapter.notifyDataSetChanged();
            mEl.expandGroup(0);
            mSv.smoothScrollTo(0,0);

        }


    }
}
