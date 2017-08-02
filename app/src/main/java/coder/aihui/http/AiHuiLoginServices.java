package coder.aihui.http;

import java.util.List;

import coder.aihui.data.bean.AZYS_MX;
import coder.aihui.data.bean.DqxqOutBean;
import coder.aihui.data.bean.IN_MATERIALS_PPMC;
import coder.aihui.data.bean.IN_MATERIALS_WZMC;
import coder.aihui.data.bean.PUB_COMPANY;
import coder.aihui.data.bean.PUR_CONTRACT_PLAN;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by gaohailong on 2016/5/17.
 */
public interface AiHuiLoginServices {

    @GET("purEscRecord/getEscTempletJson.html")
    Observable<DqxqOutBean> getDatas(
    );

    //http://192.168.1.105:8089/purContract/getPurContractDetail.html?dateTime=2010-10-25&userId=1
    @GET("purContract/getPurContractDetail.html?")
    Observable<List<PUR_CONTRACT_PLAN>> getAzysDatas(
            @Query("dateTime") String dateTime,
            @Query("userId") int userId

    );

    //下载公司类型
    @GET("inCompany/getCompanyJson.html?")
    Observable<List<PUB_COMPANY>> getComPanies(
            @Query("type") int type
    );

    //下载公司类型
    @GET("/purChecked/getWzmcJson.html?")
    Observable<List<IN_MATERIALS_WZMC>> getWzmc(
    ); //下载公司类型

    @GET("/purChecked/getPpmcJson.html?")
    Observable<List<IN_MATERIALS_PPMC>> getPpmc(
    );


    /**
     * @return 安装验收动态选择的下载
     */
    @GET("/pubDictionaryClass/getPurCheckItemJson.html?")
    Observable<List<AZYS_MX>> getAzysMx(
    );


}
