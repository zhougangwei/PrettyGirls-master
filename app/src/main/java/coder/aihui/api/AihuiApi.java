package coder.aihui.api;


import java.util.List;
import java.util.Map;

import coder.aihui.data.bean.DqxqOutBean;
import retrofit2.http.GET;
import rx.Observable;


/**
 * Created by long on 2016/8/22.
 * API 接口
 */
public interface AihuiApi {

    @GET("purEscRecord/getEscTempletJson.html")
    Observable<Map<String, List<DqxqOutBean>>> getIN_ASSETDatas();


}
