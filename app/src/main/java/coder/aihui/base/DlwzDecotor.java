package coder.aihui.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import coder.aihui.data.bean.PUB_DICTIONARY_ITEM;
import coder.aihui.data.bean.gen.DaoSession;
import coder.aihui.data.bean.gen.PUB_DICTIONARY_ITEMDao;
import coder.aihui.data.normalbean.DeptBean;
import coder.aihui.util.ToastUtil;
import coder.aihui.widget.jdaddressselector.BottomDialog;
import coder.aihui.widget.jdaddressselector.DataProvider;
import coder.aihui.widget.jdaddressselector.ISelectAble;
import coder.aihui.widget.jdaddressselector.Selector;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/6/23 9:43
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/6/23$
 * @ 更新描述  ${TODO}
 */

public class DlwzDecotor implements DlwzView {

    Activity activity;

    private DaoSession   mDaoSession;
    private BottomDialog mLocationDialog;           //底部弹窗 科室或者地理位子的
    private DlwzView dlwzView;


    public DlwzDecotor(AppActivity activity,DlwzView dlwzView) {
        this.activity = activity;
        this.dlwzView = dlwzView;
        this.mDaoSession = activity.mDaoSession;
    }


    @Override
    //获得地理位子
    public void getDlwz() {

        if (mLocationDialog == null) {
            mLocationDialog = new BottomDialog(activity);
            Selector selector = new Selector(activity, 3);

            final List<ISelectAble> locationList = new ArrayList<ISelectAble>();       //地理位子

            List<PUB_DICTIONARY_ITEM> diclist = mDaoSession.getPUB_DICTIONARY_ITEMDao().queryBuilder()
                    .where(PUB_DICTIONARY_ITEMDao.Properties.DICT_ID.eq("007114"), PUB_DICTIONARY_ITEMDao.Properties.PARENT_ID.eq(0L)
                    ).list();

            if (diclist == null || diclist.size() == 0) {
                ToastUtil.showToast("请先进行数据初始化!");
                return;
            }

            for (int i = 0; i < diclist.size(); i++) {
                PUB_DICTIONARY_ITEM dlwz = diclist.get(i);
                DeptBean deptBean = new DeptBean();
                deptBean.id = Integer.parseInt(dlwz.getITEM_ID() + "");
                deptBean.name = dlwz.getITEM_NAME();
                deptBean.arg = "location";
                locationList.add(deptBean);
            }

            //输入自己的数据
            selector.setDataProvider(new DataProvider() {
                @Override
                public void provideData(int currentDeep, int preId, final DataReceiver receiver) {
                    if (currentDeep == 0) {
                        receiver.send(locationList);
                    } else if (currentDeep == 1 || currentDeep == 2) {
                        Observable.just(preId)
                                .subscribeOn(Schedulers.io())
                                .map(new Func1<Integer, List<PUB_DICTIONARY_ITEM>>() {
                                    @Override
                                    public List<PUB_DICTIONARY_ITEM> call(Integer integer) {
                                        return mDaoSession.getPUB_DICTIONARY_ITEMDao().queryBuilder()
                                                .where(PUB_DICTIONARY_ITEMDao.Properties.DICT_ID.eq("007114"), PUB_DICTIONARY_ITEMDao.Properties.PARENT_ID.eq(integer)
                                                ).list();
                                    }
                                }).map(new Func1<List<PUB_DICTIONARY_ITEM>, List<ISelectAble>>() {
                            @Override
                            public List<ISelectAble> call(List<PUB_DICTIONARY_ITEM> locationList) {
                                return changeLocationToCity(locationList);
                            }
                        }).observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Action1<List<ISelectAble>>() {
                                    @Override
                                    public void call(List<ISelectAble> counties) {
                                        receiver.send(counties);

                                    }
                                }, new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {
                                        //给一个空值 进去 触发提前结束
                                        receiver.send(new ArrayList<ISelectAble>());

                                    }
                                });
                    }
                }
            });

            mLocationDialog.init(activity, selector);
            mLocationDialog.setOnAddressSelectedListener(this);
        }

        mLocationDialog.show();
    }


    @Override
    public void onAddressSelected(ArrayList<ISelectAble> selectAbles) {
        dlwzView.onAddressSelected(selectAbles);
    }

    public void closeDiaLog() {
        if (mLocationDialog != null) {
            mLocationDialog.dismiss();
        }
    }

    //转换
    private List<ISelectAble> changeLocationToCity(List<PUB_DICTIONARY_ITEM> locatioList) {
        List<ISelectAble> list = new ArrayList<>();

        for (int i = 0; i < locatioList.size(); i++) {
            PUB_DICTIONARY_ITEM sys_dept = locatioList.get(i);
            DeptBean city = new DeptBean();
            city.arg = "location";
            city.id = Integer.parseInt(sys_dept.getITEM_ID() + "");
            city.name = sys_dept.getITEM_NAME();
            list.add(city);
        }
        return list;
    }
}
