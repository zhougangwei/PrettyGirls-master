

package coder.aihui.ui.main;

import coder.aihui.app.BaseView;

public interface DownView extends BaseView {


    void showSuccess(int type);                     //下载成功

    void showFault(int type, String wrong);                       //显示错误的

    void showProgress(int num, int type);   //显示进度


/*    void showLoading(int type);         //显示数据下载时候的加载

    void closeLoading(int type);       //数据加载结束 关闭显示*/
}
