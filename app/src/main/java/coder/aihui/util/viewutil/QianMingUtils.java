package coder.aihui.util.viewutil;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import coder.aihui.R;
import coder.aihui.util.BitmapDeleteNoUseSpaceUtil;
import coder.aihui.util.FileUtil;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static coder.aihui.app.MyApplication.mContext;

/**
 * @ 创建者   zhou
 * @ 创建时间   2016/12/24 9:48
 * @ 描述    ${可以进行签名}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2016/12/24$
 * @ 更新描述  ${TODO}
 */

public class QianMingUtils implements View.OnClickListener {
    private Paint  paint;
    private Bitmap bitmap;
    private ArrayList<Bitmap> al = new ArrayList();

    private float a = 6f;
    private AlertDialog mDialog;
    private ImageView   mIv_image;


    private Canvas mCanvas;

    private static QianMingUtils mLoadDataManager = new QianMingUtils();

    private QianMingUtils() {
    }

    public static QianMingUtils getInstance() {
        return mLoadDataManager;
    }


    public void showQianming(Activity activity, OnSure onSure) {
        mOnSure = onSure;
        View view = View.inflate(activity, R.layout.dialog_qm, null);

        mIv_image = (ImageView) view.findViewById(R.id.iv_image);
        final TextView jiacu = (TextView) view.findViewById(R.id.jiacu);            //加粗
        final TextView bianxi = (TextView) view.findViewById(R.id.bianxi);          //变细
        final TextView chongqian = (TextView) view.findViewById(R.id.chongqian);    //重签


        final TextView tvQuit = (TextView) view.findViewById(R.id.tv_quit);    //重签
        final TextView tvSave = (TextView) view.findViewById(R.id.tv_save);    //重签
        jiacu.setOnClickListener(this);
        bianxi.setOnClickListener(this);
        chongqian.setOnClickListener(this);
        tvQuit.setOnClickListener(this);
        tvSave.setOnClickListener(this);

        // 1. 创建空白纸张
        bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);

        // 2. 创建画板
        mCanvas = new Canvas(bitmap);

        // 3. 绘制内容
        mCanvas.drawColor(Color.WHITE);

        // 4. 设置给ImageView
        mIv_image.setImageBitmap(bitmap);

        paint = new Paint();
        paint.setColor(Color.BLACK);// 设置颜色
        // 画一条线
        //		canvas.drawLine(10f, 10f, 60f, 60f, paint);
        paint.setStrokeWidth(a);
        mIv_image.setOnTouchListener(new View.OnTouchListener() {

            float startX;
            float startY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) { // 区分事件类型
                    case MotionEvent.ACTION_DOWN: // 按下
                        //						bitmap.setPixel((int)event.getX(), (int)event.getY(), Color.BLACK);
                        //						iv_image.setImageBitmap(bitmap);
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:  // 移动
                        float endX = event.getX();
                        float endY = event.getY();

                        // 画一条直线
                        mCanvas.drawLine(startX, startY, endX, endY, paint);
                        // 更新图片
                        mIv_image.setImageBitmap(bitmap);

                        // 更新开始坐标
                        startX = endX;
                        startY = endY;
                        break;
                    case MotionEvent.ACTION_UP:     // 抬起
                        al.add(bitmap);
                        break;
                    default:
                        break;
                }

                return true; // 可以消费事件
            }
        });

        mDialog = new AlertDialog.Builder(activity).setView(view).create();
        mDialog.setCancelable(true);
        mDialog.show();
    }

    private OnSure mOnSure;

    public interface OnSure {
        public void backResult(String url);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.jiacu:        //加粗
                goTojiacu();
                break;
            case R.id.bianxi:       //变细
                goTobianxi();
                break;
            case R.id.tv_quit:      //退出
                gotoCancel();
                break;
            case R.id.tv_save:      //保存
                goToSave();
                break;
            case R.id.chongqian:       //清除
                mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                mIv_image.setImageBitmap(bitmap);
                break;


        }
    }


    //退出
    private void gotoCancel() {
        mDialog.dismiss();
    }


    private void goTojiacu() {
        a = a + 2f;
        if (a > 10f) {
            a = 10f;
        }
        paint.setStrokeWidth(a);
        paint.setStrokeCap(Paint.Cap.ROUND);// 设置线条样式
    }

    private void goTobianxi() {

        a = a - 2f;
        if (a < 4f) {
            a = 4f;
        }
        paint.setStrokeWidth(a);
        paint.setStrokeCap(Paint.Cap.ROUND);// 设置线条样式
    }


    private void goToSave() {


        Observable.just(bitmap)
                .map(new Func1<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap call(Bitmap bitmap) {
                        return BitmapDeleteNoUseSpaceUtil.deleteNoUseWhiteSpace(bitmap);
                    }
                }).subscribeOn(Schedulers.io())
                .map(new Func1<Bitmap, String>() {
                    @Override
                    public String call(Bitmap bitmap) {
                        //用时间来命名
                        try {
                            String time = "IMG_" + TimeUtils.getCurTimeString(new SimpleDateFormat("yyyyMMddhmmssSSS")) + ".jpg";
                            File f = new File(FileUtil.getDiskCacheDir(mContext), time);
                            if (f.exists()) {
                                f.delete();
                            }
                            f.createNewFile();
                            FileOutputStream fos = new FileOutputStream(f);
                            // 将图片写出到文件输出流中
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                            fos.close();
                            String mUrl = f.getAbsolutePath();
                            return mUrl;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return "错误1" + e.getMessage();
                        }
                    }
                }).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                if (s.startsWith("错误1")) {
                    mOnSure.backResult("" + s);
                    return false;
                } else if (TextUtils.isEmpty(s)) {
                    mOnSure.backResult("错误2");
                    return false;
                } else {
                    return true;
                }
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String mUrl) {
                        mOnSure.backResult(mUrl);
                    }
                });


    }

}
//  this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + Environment.getExternalStorageDirectory())));

// 为了图片保存之后, 立即在图库看到图片, 模拟一个系统sd卡挂载广播
    /*		Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED);
            // 设置文件路径
			intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
			sendBroadcast(intent);*/





