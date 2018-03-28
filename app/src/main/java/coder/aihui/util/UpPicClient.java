package coder.aihui.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import coder.aihui.app.MyApplication;
import coder.aihui.data.normalbean.UpPicBean;
import coder.aihui.http.AiHuiServices;
import coder.aihui.http.MyRetrofit;
import coder.aihui.widget.MyProgressDialog;
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


    private OnBackResult     mOnBackResult;
    private List<String>     mImgUrls;
    private MyProgressDialog mProgressDialog;
    private String           message;


    private UpPicClient() {
    }

    private UpPicClient(Builder builder) {
        this.mImgUrls = builder.mImgUrls;
        this.message = builder.message;
        this.mOnBackResult = builder.mOnBackResult;
        this.mProgressDialog = builder.mProgressDialog;
    }

    public static class Builder {
        public Builder() {
        }

        private List<String> mImgUrls;

        private String           message;
        private MyProgressDialog mProgressDialog;
        private OnBackResult     mOnBackResult;


        /**
         * @param imgUrls 图片地址的集合 注意地址别错
         * @return
         */
        public Builder setImgUrls(List<String> imgUrls) {
            mImgUrls = imgUrls;
            return this;
        }


        /**
         * @param message 进度条显示的
         * @return
         */
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

        /**
         * @param mProgressDialog 显示进度条
         * @return
         */
        public Builder setProgressDialog(MyProgressDialog mProgressDialog) {
            this.mProgressDialog = mProgressDialog;
            return this;
        }

        public Builder setOnDefaultBackResult(OnDefaultResult onBackResult) {
            this.mOnBackResult = onBackResult;
            onBackResult.mProgressDialog = this.mProgressDialog;
            return this;
        }


    }


    /**
     * @param fileFolder 希望在服务器存的名字
     */
    public synchronized void uploadImg(final String fileFolder) {

        if (mProgressDialog != null) {

            mProgressDialog.show();
            mProgressDialog.setMyMessage(message);
        }
        final MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

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
                                .create(AiHuiServices.class)
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

    public abstract static class OnDefaultResult implements OnBackResult {

        MyProgressDialog mProgressDialog;

        @Override
        public void gotoSuccess(List<UpPicBean> recode) {
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
        }

        @Override
        public void gotoFinish() {
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
        }

        @Override
        public void goToFail(String str) {
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
        }

        @Override
        public void goToProgress(long totalBytes, long l) {
            if (mProgressDialog != null) {
                mProgressDialog.setProgress((int) (totalBytes / l));
            }
        }
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
