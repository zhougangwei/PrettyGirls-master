package coder.aihui.ui.assetquery;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import coder.aihui.R;
import coder.aihui.base.BaseFragment;
import coder.aihui.data.bean.IN_ASSET;
import coder.aihui.manager.DataUtil;
import coder.aihui.ui.assetcheck.AssetDetailActivity;
import coder.aihui.util.AndroidUtils;
import coder.aihui.util.Utils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/7/10 17:16
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/7/10$
 * @ 更新描述  ${TODO}
 */

public class AssetQueryFragment extends BaseFragment {


    @BindView(R.id.rv)
    RecyclerView   mRv;



    private LinearLayoutManager mLayoutManager;
    private boolean loadFlag = true;       //加载数据的时候不刷新界面
    ArrayList<IN_ASSET> mDatas       = new ArrayList<IN_ASSET>() {
    };
    ArrayList<IN_ASSET> mLinshiDatas = new ArrayList<IN_ASSET>() {
    };
    private int    searchType = 9;//"1">名称 "2">出厂 "3">规格 "4">RFID "5">二维码设备扫描 "6">二维码摄像头扫描"7">老资产编号"8">新资产编号 9是品牌名称规格的后搜索
    private String searchText = "";
    private CommonAdapter mMainAdapter;
    private boolean       isLoadingMore;

    @Override
    protected void initView() {


        //  holder.setText(R.id.iv_asset)
        //科室
        //  holder.setChecked(R.id.rb, bean.getIsCheck());
        //radiobutton 的状态保持
        //跳转到编辑界面
        //  holder.setText(R.id.tv_location_later)


        initRecycleView();


    }

    private void initRecycleView() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(mLayoutManager);
        mMainAdapter = new CommonAdapter<IN_ASSET>(getActivity(), R.layout.item_assetquery, mDatas) {
            @Override
            protected void convert(ViewHolder holder, final IN_ASSET bean, int position) {
                //  holder.setText(R.id.iv_asset)
                holder.setText(R.id.tv_name, bean.getWZMC());               //物资名称
                holder.setText(R.id.tv_brand, bean.getPPMC());              //品牌名称
                holder.setText(R.id.tv_code, bean.getKPBH());               //卡片编号
                holder.setText(R.id.tv_old_code, bean.getKPBH_OLD());       //旧编号

                holder.setText(R.id.rb, bean.getKSMC());                    //科室
                //  holder.setChecked(R.id.rb, bean.getIsCheck());

                RadioButton mRb = (RadioButton) holder.getView(R.id.rb);
                mRb.setChecked(bean.isFlagChoose());

                Utils.canCancelRadioButton(mRb, bean,null);
                //radiobutton 的状态保持


                //跳转到编辑界面
                holder.setOnClickListener(R.id.ll_main, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), AssetDetailActivity.class);
                        intent.putExtra("assetId", bean.getID());
                        startActivity(intent);
                    }
                });
                holder.setText(R.id.tv_location_before, bean.getDDMC());
                holder.setVisible(R.id.tv_hand, false);

            }
        };
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
                int totalItemCount = mLayoutManager.getItemCount();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                // dy>0 表示向下滑动
                if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
                    if (isLoadingMore) {
                        //不做操作
                    } else {
                        isLoadingMore = true;
                        loadMeinv(mDatas.size(), 10);//这里多线程也要手动控制isLoadingMore
                    }
                }
            }
        });


        mRv.setAdapter(mMainAdapter);
        loadMeinv(0, 10);


    }

    private void querySummaryInAsset(String sql, String[] par) {

        Log.d("AssetQueryFragment", sql);


        /*select a.ID as ID, a.WZMC as WZMC, a.GGXH as GGXH,
         a.PPMC as PPMC, a.SCBH as SCBH, a.KPBH as KPBH,a.KPBH__OLD as KPBH_OLD, a.BAR__CODE as BAR, a.KSMC as KSMC, a.DDMC as DDMC,ic_selected.DQDDMC as DQDDMC,case when ic_selected.ASSET__ID is not null and (ic_selected.IS__CHANGE__DD != 1 or ic_selected.IS__CHANGE__DD is null) and ic_selected.IS__CHANGE=1 then 4 when ic_selected.ASSET__ID is not null and (ic_selected.IS__CHANGE != 1 or ic_selected.IS__CHANGE is null) and ic_selected.IS__CHANGE__DD=1 then 3 when ic_selected.ASSET__ID is not null and ic_selected.IS__CHANGE=1 and ic_selected.IS__CHANGE__DD=1 then 5 when ic_selected.ASSET__ID is not null then 1 else 2 end as PDAID from IN__ASSET a  left join PDA__ASSET__CHECK ic_selected
        on a.ID=ic_selected.ASSET__ID and ic_selected.QCPC=20170523
        order by PDAID desc,ID limit ? offset ?*/

        DataUtil.getDatas(mDaoSession, sql, par)
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<IN_ASSET>() {
            @Override
            public void onCompleted() {
                Log.d("AssetQueryActivity2", "mLinshiDatas.size():" + mLinshiDatas.toString());
                mDatas.addAll(mLinshiDatas);
                mMainAdapter.notifyDataSetChanged();
                mLinshiDatas.clear();
                loadFlag = true;
                isLoadingMore = false;
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(IN_ASSET in_asset) {

                mLinshiDatas.add(in_asset);
                request(1);
            }

            @Override
            public void onStart() {
                request(1);
            }
        });
    }


    public void loadMeinv(final int start, final int count) {

        int i_start = start;
        int i_count = count;

        //增加侧边条件判断
        String qcid = AndroidUtils.getMaxPcid(mDaoSession);// 取最大的盘点期次，这个只要取一次就好，不用每次数据库。
        List<String> parList = new ArrayList<String>();

        String where = "";


        StringBuffer sql = new StringBuffer();
        sql.append("select  a.ID as ID, a.WZMC as WZMC, a.GGXH as GGXH,a.RFID__CODE as RFID, a.PPMC as PPMC, a.SCBH as SCBH, a.KPBH as KPBH,a.KPBH__OLD as KPBH_OLD, a.BAR__CODE as BAR, a.KSMC as KSMC, a.DDMC as DDMC,ic_selected.DQDDMC as DQDDMC,ic_selected.IS__CHANGE as IS__CHANGE ,ic_selected.CHANGE__DEPT as CHANGE__DEPT");
        sql.append(",case when ic_selected.ASSET__ID is not null and (ic_selected.IS__CHANGE__DD != 1 or ic_selected.IS__CHANGE__DD is null) and ic_selected.IS__CHANGE=1 then 4 " +
                "when ic_selected.ASSET__ID is not null and (ic_selected.IS__CHANGE != 1 or ic_selected.IS__CHANGE is null) and ic_selected.IS__CHANGE__DD=1 then 3 " +
                "when ic_selected.ASSET__ID is not null and ic_selected.IS__CHANGE=1 and ic_selected.IS__CHANGE__DD=1 then 5 " +
                "when ic_selected.ASSET__ID is not null then 1 " +
                "else 2 " +
                "end as PDAID");


        String aTab = "IN__ASSET a ";           //a表
        String bTab = "PDA__ASSET__CHECK ic_selected ";           //b表

        StringBuffer sql_count = new StringBuffer();


        sql.append(" from " + aTab + " left join " + bTab + "  on a.ID=ic_selected.ASSET__ID and ic_selected.QCPC=" + qcid);
        sql_count.append("select count(*) from " + aTab + " left join " + bTab + " on a.ID=ic_selected.ASSET__ID and ic_selected.QCPC=" + qcid);


        //如果是RFID扫描，则跳过科室地理位置条件
        if (searchType == 4 || searchType == 5 || searchType == 6) {
            where = "";
        } else {
            sql.append(where);
            sql_count.append(where);
        }

        if (searchText != null && !"".equals(searchText)) {
            if (!"".equals(where)) {
                sql.append(" and ");
                sql_count.append(" and ");
            } else {
                sql.append(" where ");
                sql_count.append(" where ");
            }
            //"1">名称 "2">出厂 "3">规格 "4">RFID
            if (searchType == 1) {
                sql.append(" a.WZMC like ?");
                sql_count.append(" a.WZMC like ?");
                parList.add("%" + searchText + "%");
            } else if (searchType == 2) {
                sql.append(" a.SCBH like ?");
                sql_count.append(" a.SCBH like ?");
                parList.add("%" + searchText + "%");
            } else if (searchType == 7) {        //老资产编号
                sql.append(" a.KPBH__OLD like ?");
                sql_count.append(" a.KPBH__OLD like ?");
                parList.add("%" + searchText + "%");
            } else if (searchType == 8) {    //新编号
                sql.append(" a.RFID__CODE like ?");
                sql_count.append(" a.RFID__CODE like ?");
                parList.add("%" + searchText + "%");
            } else if (searchType == 3) {
                sql.append(" a.GGXH like ?");
                sql_count.append(" a.GGXH like ?");
                parList.add("%" + searchText + "%");
            } else if (searchType == 4) {
                sql.append(" a.RFID__CODE in( " + searchText + " )");
                sql_count.append(" a.RFID__CODE in( " + searchText + " )");
            } else if (searchType == 5) {
                sql.append(" a.BAR__CODE in( " + searchText + " )");
                sql_count.append(" a.BAR__CODE in( " + searchText + " )");
            } else if (searchType == 6) {
                sql.append(" a.BAR__CODE in( " + searchText + " )");
                sql_count.append(" a.BAR__CODE in( " + searchText + " )");
            } else if (searchType == 9) {
                sql.append(" a.WZMC like ? or a.GGXH like ? or a.PPMC like ? ");
                sql_count.append(" a.WZMC like ? or a.GGXH like ? or a.PPMC like ? ");
                parList.add("%" + searchText + "%");
                parList.add("%" + searchText + "%");
                parList.add("%" + searchText + "%");

            }
        }


        sql.append(" order by PDAID desc,ID");
        sql.append(" limit ? offset ?");
        parList.add(i_count + "");
        parList.add(i_start + "");
        querySummaryInAsset(sql.toString(), (String[]) parList.toArray(new String[parList.size()]));
    }

    void setSeachText(String text) {
        mDatas.clear();
        searchText = text;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.recycleview;
    }

}
