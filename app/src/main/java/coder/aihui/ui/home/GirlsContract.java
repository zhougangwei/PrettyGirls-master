package coder.aihui.ui.home;

import java.util.List;

import coder.aihui.base.BasePresenter;
import coder.aihui.app.BaseView;
import coder.aihui.data.bean.GirlsBean;

/**
 * Created by oracleen on 2016/6/29.
 */
public interface GirlsContract {

    interface View extends BaseView {
        void refresh(List<GirlsBean.ResultsEntity> datas);

        void load(List<GirlsBean.ResultsEntity> datas);

        void showError();

        void showNormal();
    }

    interface Presenter extends BasePresenter {
        void getGirls(int page, int size, boolean isRefresh);
    }
}
