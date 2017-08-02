package coder.aihui.ui.main;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import coder.aihui.R;
import coder.aihui.app.BaseView;
import coder.aihui.base.BaseFragment;
import coder.aihui.ui.assetcheck.AssetCheckActivity;
import coder.aihui.ui.assetquery.AssetQueryActivity;
import coder.aihui.ui.azys.AzysActivity;
import coder.aihui.ui.inspectxj.InspectXJActivity;

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


    @Override
    protected void initView() {

        mPresenter = new HomePresenter(this);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<ActivityBean> mDatas = new ArrayList();
        mDatas.add(new ActivityBean("资产清点", AssetCheckActivity.class));
        mDatas.add(new ActivityBean("台账查询", AssetQueryActivity.class));
        mDatas.add(new ActivityBean("巡检", InspectXJActivity.class));
        mDatas.add(new ActivityBean("资产清点", AssetCheckActivity.class));
        mDatas.add(new ActivityBean("安装验收", AzysActivity.class));

        CommonAdapter commonAdapter = new CommonAdapter<ActivityBean>(getActivity(), R.layout.item_homefragment_list, mDatas) {
            @Override
            protected void convert(ViewHolder holder, final ActivityBean o, int position) {

                //设置名字
                holder.setText(R.id.tv_name, o.name);
                //点击事件
                holder.setOnClickListener(R.id.tv_name, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), o.className));
                    }
                });
            }
        };
        mRv.setAdapter(commonAdapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.recycleview;
    }



    class ActivityBean {
        String name;
        Class  className;

        public ActivityBean(String name, Class className) {
            this.name = name;
            this.className = className;
        }
    }


}
