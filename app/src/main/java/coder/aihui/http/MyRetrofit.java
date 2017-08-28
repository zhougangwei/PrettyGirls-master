package coder.aihui.http;

import com.google.gson.Gson;

import coder.aihui.app.MyApplication;
import coder.aihui.base.Content;
import coder.aihui.util.GsonUtil;
import coder.aihui.util.SPUtil;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static coder.aihui.app.MyApplication.mContext;

/**
 * Created by gaohailong on 2016/5/17.
 */
public class MyRetrofit {

    private static Retrofit retrofit;
    private static Retrofit stringRetrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (MyRetrofit.class) {
                Gson gson = GsonUtil.getGson();
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(SPUtil.getString(mContext, Content.WS_ADDRESS, ""))
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            // .addConverterFactory(ToStringConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(MyApplication.defaultOkHttpClient())
                            .build();
                }
            }
        }
        return retrofit;
    }

    public static Retrofit getStringRetrofit() {
        if (stringRetrofit == null) {
            synchronized (MyRetrofit.class) {
                if (stringRetrofit == null) {
                    stringRetrofit = new Retrofit.Builder()
                            .baseUrl(SPUtil.getString(mContext, Content.WS_ADDRESS, ""))
                             .addConverterFactory(ToStringConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(MyApplication.defaultOkHttpClient())
                            .build();
                }
            }
        }
        return retrofit;
    }
}
