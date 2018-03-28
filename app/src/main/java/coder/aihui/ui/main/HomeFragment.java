package coder.aihui.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import coder.aihui.R;
import coder.aihui.app.BaseView;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.ui.assetcheck.AssetCheckActivity;
import coder.aihui.ui.assetquery.AssetQueryActivity;
import coder.aihui.ui.azys.AzysActivity;
import coder.aihui.ui.inspectxj.InspectXJActivity;
import coder.aihui.util.SPUtil;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/3/17 15:31
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/3/17$
 * @ 更新描述  ${TODO}
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements BaseView {



    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.tv_name_hos)
    TextView     mTvNameHos;
    Unbinder unbinder;


    @Override
    protected void initView() {

        mPresenter = new HomePresenter(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRv.setLayoutManager(gridLayoutManager);

        ArrayList<ActivityBean> mDatas = new ArrayList();

        mDatas.add(new ActivityBean("台账查询", AssetQueryActivity.class, "TZ", R.mipmap.tzgl));
        mDatas.add(new ActivityBean("资产清点", AssetCheckActivity.class, "QD", R.mipmap.zcqd));
        mDatas.add(new ActivityBean("出入库", AssetCheckActivity.class, "CRK", R.mipmap.crk));
        mDatas.add(new ActivityBean("维修质控", AssetCheckActivity.class, "WXZK", R.mipmap.wxzk));
        mDatas.add(new ActivityBean("设备巡检", InspectXJActivity.class, "XJ", R.mipmap.sbxj));
        mDatas.add(new ActivityBean("PM保养", InspectXJActivity.class, "PM", R.mipmap.pmby));
        mDatas.add(new ActivityBean("安装验收", AzysActivity.class, "AZ", R.mipmap.azys));

        mTvNameHos.setText(SPUtil.getHospitalName(getActivity()) + "(" + (SPUtil.getUserName(getActivity()) + ")"));

        CommonAdapter commonAdapter = new CommonAdapter<ActivityBean>(getActivity(), R.layout.item_homefragment_list, mDatas) {
            @Override
            protected void convert(ViewHolder holder, final ActivityBean o, int position) {
                //设置名字
                holder.setText(R.id.tv_name, o.name);
                //设置图片
                holder.setImageResource(R.id.iv_pic, o.idRes);
                //点击事件
                holder.setOnClickListener(R.id.rl, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), o.className);
                        intent.putExtra("type", o.type);
                        startActivityForResult(intent, Content.HOME_REQUEST_CODE);
                    }
                });
            }
        };
        mRv.setAdapter(commonAdapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    class ActivityBean {
        String  name;
        Class   className;
        String  type;
        Integer idRes;

        public ActivityBean(String name, Class className, String type, Integer idRes) {
            this.name = name;
            this.className = className;
            this.type = type;
            this.idRes = idRes;
        }
    }


}
