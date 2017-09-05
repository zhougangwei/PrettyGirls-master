package coder.aihui.http;

import java.util.List;

import coder.aihui.data.bean.AZYS_MX;
import coder.aihui.data.bean.DqxqOutBean;
import coder.aihui.data.bean.IN_MATERIALS_PPMC;
import coder.aihui.data.bean.IN_MATERIALS_WZMC;
import coder.aihui.data.bean.PUB_COMPANY;
import coder.aihui.data.bean.PUR_CONTRACT_PLAN;
import coder.aihui.data.bean.YsrBean;
import coder.aihui.data.normalbean.UpPicBean;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
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


    @GET("/purContract/getPurYsrPda.html?")
    Observable<List<YsrBean>> getAzysYsr(
    );


    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("/purContract/sendPurContractDetail.html")
    Observable<String> upLoadPurPlan(@Body RequestBody body);

    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("/purPxjl/savePxjlJson.html")
    Observable<String> upPxjl(@Body RequestBody body);


    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("{url}")
    Observable<String> upJson(@Path("url") String url, @Body RequestBody body);

    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("/inAsset/saveInAssetMsgJson.html")
    Observable<String> upAssetCorrect(@Body RequestBody body);


    @POST("/fileup/upLoadFile_getId_pda.html?folderName=azys/")
    Observable<String> uploadFiles(@Body MultipartBody imgs);


    @POST("/fileup/upLoadFile_getId_pda.html?/")
    Observable<List<UpPicBean>> uploadFiles(@Body RequestBody body, @Query("folderName") String filename);


}
