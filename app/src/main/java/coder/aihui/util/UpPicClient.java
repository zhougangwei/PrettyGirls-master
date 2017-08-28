package coder.aihui.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import coder.aihui.app.MyApplication;
import coder.aihui.data.normalbean.UpPicBean;
import coder.aihui.http.AiHuiLoginServices;
import coder.aihui.http.MyRetrofit;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @ 创建者   zhou
 * @ 创建时间   2016/12/10 16:15
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2016/12/10$
 * @ 更新描述  ${TODO}
 */

public class UpPicClient {
    //POST参数还没传
    // private final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/jpg");


    private OnBackResult mOnBackResult;
    private List<String> mImgUrls;

    private String message;


    private UpPicClient() {
    }

    private UpPicClient(Builder builder) {

        this.mImgUrls = builder.mImgUrls;
        this.message = builder.message;
        this.mOnBackResult = builder.mOnBackResult;
    }

    public static class Builder {
        public Builder() {
        }

        private List<String> mImgUrls;

        private String       message;
        private OnBackResult mOnBackResult;


        public Builder setImgUrls(List<String> imgUrls) {
            mImgUrls = imgUrls;
            return this;
        }


        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setOnBackResult(OnBackResult onBackResult) {
            this.mOnBackResult = onBackResult;
            return this;
        }

        public UpPicClient build() {
            return new UpPicClient(this);
        }
    }


    /**
     * @param fileFolder 希望在服务器存的名字
     */
    public synchronized void uploadImg(final String fileFolder) {

        final MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        final Map<String, RequestBody> map = new HashMap<>();
        Observable.from(mImgUrls)
                .map(new Func1<String, File>() {
                    @Override
                    public File call(String s) {
                        return Utils.scal(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<File>() {
                    @Override
                    public void call(File f) {
                        builder.addFormDataPart("fileList", f.getName(),
                                createCustomRequestBody(MultipartBody.FORM, f, new ProgressListener() {
                                    @Override
                                    public void onProgress(long totalBytes, long remainingBytes, boolean done) {
                                        mOnBackResult.goToProgress(totalBytes, totalBytes - remainingBytes);
                                    }
                                }));
                        builder.addFormDataPart("fileListFileName", f.getAbsolutePath());
                        builder.addFormDataPart("userId", SPUtil.getUserId(MyApplication.mContext));
                    }
                })
                .last().
                observeOn(Schedulers.io()).
                flatMap(new Func1<File, Observable<List<UpPicBean>>>() {
                    @Override
                    public Observable<List<UpPicBean>> call(File file) {
                        MultipartBody build = builder.build();

                        return MyRetrofit.getRetrofit()
                                .create(AiHuiLoginServices.class)
                                .uploadFiles(build, fileFolder);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<UpPicBean>>() {
                    @Override
                    public void onCompleted() {
                        mOnBackResult.gotoFinish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mOnBackResult.goToFail(e.getMessage());
                    }

                    @Override
                    public void onNext(List<UpPicBean> upPicBeen) {
                        try {
                            //图片只用这个 保证返回码一致
                            mOnBackResult.gotoSuccess(upPicBeen);
                        } catch (Exception e) {
                            onError(e);
                        }
                    }
                });


    }

    interface ProgressListener {
        void onProgress(long totalBytes, long remainingBytes, boolean done);
    }

    public static RequestBody createCustomRequestBody(
            final MediaType contentType,
            final File file, final ProgressListener listener) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return contentType;
            }

            @Override
            public long contentLength() {
                return file.length();
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                Source source = null;
                try {

                    source = Okio.source(file);
                    Buffer buf = new Buffer();
                    Long remaining = contentLength();
                    for (
                            long readCount;
                            (readCount = source.read(buf, 1024)) != -1; ) {
                        sink.write(buf, readCount);
                        listener.onProgress(contentLength(), remaining -= readCount, remaining == 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
    }


    public interface OnBackResult {

        //服务器返回码
        void gotoSuccess(List<UpPicBean> recode);

        //下载结束
        void gotoFinish();

        //下载失败报错
        void goToFail(String str);


        //渲染进度条 监听的文件的字节
        void goToProgress(long totalBytes, long l);
    }


}
