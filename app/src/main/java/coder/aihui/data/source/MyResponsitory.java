package coder.aihui.data.source;

import coder.aihui.data.source.local.LocalMyDataSource;
import coder.aihui.data.source.remote.RemoteMyDataSource;

/**
 * Created by oracleen on 2016/6/29.
 */
public class MyResponsitory implements MyDataSource {

    private LocalMyDataSource  mLocalMyDataSource;
    private RemoteMyDataSource mRemoteMyDataSource;


    public MyResponsitory() {
        mLocalMyDataSource= new LocalMyDataSource();
        //mRemoteMyDataSource = new RemoteMyDataSource();
    }
    public void getDatas(int page, int size, LoadDatasCallback callback) {
    //    mRemoteMyDataSource.getDatas(page, size, callback);
    }


    public void getData(LoadDatasCallback callback) {
        //mRemoteMyDataSource.getData(callback);
    }

}
