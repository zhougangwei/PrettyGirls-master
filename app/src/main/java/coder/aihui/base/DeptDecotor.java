package coder.aihui.base;

import android.app.Activity;
import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.List;

import coder.aihui.data.bean.SYS_DEPT;
import coder.aihui.data.bean.gen.DaoSession;
import coder.aihui.data.bean.gen.SYS_DEPTDao;
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

public class DeptDecotor implements DeptView {

    Activity activity;
    private BottomDialog   mDeptDialog;           //底部弹窗 科室或者地理位子的
    private DaoSession   mDaoSession;
    private DeptView   mDeptView;



    public DeptDecotor(AppActivity activity, DeptView mDeptView) {
        this.activity = activity;
        this.mDaoSession = activity.mDaoSession;
        this.mDeptView = mDeptView;
    }



    @Override
    public void getDept() {
            mDeptDialog = new BottomDialog(activity);
            Selector selector = new Selector(activity, 3);
            final List<ISelectAble> Dept1 = new ArrayList<ISelectAble>();       //科室
            final List<SYS_DEPT> list = mDaoSession.getSYS_DEPTDao().queryBuilder().where(SYS_DEPTDao.Properties.DEPT_PARENT_ID.eq(0L)).list();

            if (list == null && list.size() == 0) {
                ToastUtil.showToast("请先进行数据初始化!");
                return;
            }

            for (int i = 0; i < list.size(); i++) {
                SYS_DEPT sys_dept = list.get(i);
                DeptBean deptBean = new DeptBean();
                deptBean.id = Integer.parseInt(sys_dept.getDEPT_ID() + "");
                deptBean.name = sys_dept.getDEPT_NAME();
                deptBean.arg = "dept";
                Dept1.add(deptBean);
            }

            if (Dept1.size() == 0) {
                ToastUtil.showToast("当前无数据!");
                return;
            }

            //输入自己的数据
            selector.setDataProvider(new DataProvider() {
                @Override
                public void provideData(int currentDeep, int preId, final DataReceiver receiver) {
                    if (currentDeep == 0) {
                        receiver.send(Dept1);
                    } else if (currentDeep == 1 || currentDeep == 2) {

                        Observable.just(preId)
                                .subscribeOn(Schedulers.io())
                                .map(new Func1<Integer, List<SYS_DEPT>>() {
                                    @Override
                                    public List<SYS_DEPT> call(Integer integer) {
                                        return mDaoSession.getSYS_DEPTDao().queryBuilder().where(SYS_DEPTDao.Properties.DEPT_PARENT_ID.eq(integer)).list();
                                    }
                                }).map(new Func1<List<SYS_DEPT>, List<ISelectAble>>() {
                            @Override
                            public List<ISelectAble> call(List<SYS_DEPT> sys_depts) {
                                return changeDeptToCity(sys_depts);
                            }
                        })
                                .observeOn(AndroidSchedulers.mainThread())
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
            selector.setSelectedListener(this);
            mDeptDialog.init(activity, selector);
            mDeptDialog.setOnAddressSelectedListener(this);
            mDeptDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {

                }
            });

        mDeptDialog.show();
    }
    //转换
    private List<ISelectAble> changeDeptToCity(List<SYS_DEPT> sys_depts) {
        List<ISelectAble> list = new ArrayList<>();
        for (int i = 0; i < sys_depts.size(); i++) {
            SYS_DEPT sys_dept = sys_depts.get(i);
            DeptBean city = new DeptBean();
            city.arg = "dept";
            city.id = Integer.parseInt(sys_dept.getDEPT_ID() + "");
            city.name = sys_dept.getDEPT_NAME();
            list.add(city);
        }
        return list;
    }

    @Override
    public void onAddressSelected(ArrayList<ISelectAble> selectAbles) {
        mDeptView.onAddressSelected(selectAbles);
    }

    @Override
    public void closeDiaLog() {
        if (mDeptDialog != null) {
            mDeptDialog.dismiss();
        }
    }
}
