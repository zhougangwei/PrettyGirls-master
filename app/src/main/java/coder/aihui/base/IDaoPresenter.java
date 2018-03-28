package coder.aihui.base;

import java.util.List;

/**
 * Created by long on 2016/9/2.
 * RxBus Presenter
 */
public interface IDaoPresenter<T> {

    /**
    * 插入数据
     * @param data  数据
     */
    void insert(T data);

    /**
     * 删除数据
     * @param data  数据
     */
    void delete(T data);

    /**
     * 更新数据
     * @param list   所有数据
     */
    void update(List<T> list);


    /*
    * 查数据
    * */
    /*List<T>  queryList();*/
}
