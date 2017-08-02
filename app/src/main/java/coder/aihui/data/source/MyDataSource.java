package coder.aihui.data.source;

/**
 * Created by oracleen on 2016/6/29.
 */
public interface MyDataSource {

    interface LoadDatasCallback {

        void onDatasLoadedProgress(int index);  //正在保存数据

        void onDataNotAvailable(String i);      //没拿到数据

        void  onDataFinished();         //保存数据完毕

        void onGetData();               //如果是webservice请求 那么数据会有一个延迟获取数据

        void onLoadingData();           //正在下载数据

    }

}
