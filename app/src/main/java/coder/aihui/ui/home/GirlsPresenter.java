package coder.aihui.ui.home;

import android.util.Log;

import coder.aihui.data.source.MyResponsitory;

/**
 * Created by oracleen on 2016/6/29.
 */
public class GirlsPresenter implements GirlsContract.Presenter {

    public static final String TAG = "GirlsPresenter";

    private GirlsContract.View mView;
    private MyResponsitory     mMyResponsitory;

    public GirlsPresenter(GirlsContract.View view) {
        mView = view;
        mMyResponsitory = new MyResponsitory();
    }




    @Override
    public void getGirls(int page, int size, final boolean isRefresh) {
        Log.d(TAG, "getDatas");
        /*mMyResponsitory.getDatas(page, size, new MyDataSource.LoadDatasCallback(){
            @Override
            public void onDatasLoadedProgress(int index) {
            }
            @Override
            public void onDataNotAvailable() {
                if (isRefresh) {
                    mView.showError();
                }
            }
            @Override
            public void onDataFinished() {

            }


        });*/
    }

    @Override
    public void onStart() {
        getGirls(1, 20, true);
    }

    @Override
    public void onDestroy() {

    }


}
