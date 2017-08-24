package coder.aihui.util;

import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import coder.aihui.app.MyApplication;
import coder.aihui.http.AiHuiLoginServices;
import coder.aihui.http.MyRetrofit;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * @ 创建者   zhou
 * @ 创建时间   2016/12/10 16:15
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2016/12/10$
 * @ 更新描述  ${TODO}
 */

public class UpFileManager {
    //POST参数还没传
    // private final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/jpg");


    private OnBackResult mOnBackResult;
    private List<String> mImgUrls;

    private String message;


    private UpFileManager() {
    }

    private UpFileManager(Builder builder) {

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

        public UpFileManager build() {
            return new UpFileManager(this);
        }
    }


    public synchronized void uploadImg() {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (int i = 0; i < mImgUrls.size(); i++) {
            File f = new File(mImgUrls.get(i));
            if (f != null) {
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
        }
        MultipartBody requestBody = builder.build();
        MyRetrofit.getRetrofit()
                .create(AiHuiLoginServices.class)
                .uploadFiles(requestBody)
                .map(new Func1<Response, String>() {
                    @Override
                    public String call(Response response) {
                        try {
                            return response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return "";
                    }
                }).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return !TextUtils.isEmpty(s);
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                mOnBackResult.gotoSuccess();
            }
            @Override
            public void onError(Throwable e) {
                mOnBackResult.goToFail(e.getMessage());
            }

            @Override
            public void onNext(String s) {

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
                    //sink.writeAll(source);
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
        void gotoSuccess();

        void goToFail(String str);

        void goToProgress(long totalBytes, long l);
    }


}
