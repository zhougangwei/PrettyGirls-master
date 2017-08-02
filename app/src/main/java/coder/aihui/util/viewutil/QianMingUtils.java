package coder.aihui.util.viewutil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.blankj.utilcode.utils.TimeUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import coder.aihui.R;
import coder.aihui.util.BitmapDeleteNoUseSpaceUtil;
import coder.aihui.util.ThreadUtils;

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
    private Bitmap      mBitmap2;
    private String      mUrl;

    public QianMingUtils() {
    }


    public void showQianming(Activity activity, OnSure onSure) {
        mOnSure = onSure;
        View view = View.inflate(activity, R.layout.dialog_qm, null);

        mIv_image = (ImageView) view.findViewById(R.id.iv_image);
        final Button jiacu = (Button) view.findViewById(R.id.jiacu);
        final Button bianxi = (Button) view.findViewById(R.id.bianxi);
        //final Button save = (Button) view.findViewById(R.id.save);

        jiacu.setOnClickListener(this);
        bianxi.setOnClickListener(this);
        //save.setOnClickListener(this);

        // 1. 创建空白纸张
        bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);

        // 2. 创建画板
        final Canvas canvas = new Canvas(bitmap);

        // 3. 绘制内容
        canvas.drawColor(Color.WHITE);

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
                        canvas.drawLine(startX, startY, endX, endY, paint);
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

        mDialog = new AlertDialog.Builder(activity).setPositiveButton("保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goToSave();

            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mDialog.dismiss();
            }
        }).setView(view).create();
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
            case R.id.jiacu:
                goTojiacu();
                break;
            case R.id.bianxi:
                goTobianxi();
                break;
        }
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


        ThreadUtils.runOnSubThread(new Runnable() {
            @Override
            public void run() {


                try {
                    mBitmap2 = BitmapDeleteNoUseSpaceUtil.deleteNoUseWhiteSpace(bitmap);

                    SystemClock.currentThreadTimeMillis();

                    //用时间来命名
                    String time = "IMG_" + TimeUtils.getCurTimeString(new SimpleDateFormat("yyyyMMddhmmssSSS")) + ".jpg";
                    File f = new File(mContext.getFilesDir(), time);
                    if (f.exists()) {
                        f.delete();
                    }
                    f.createNewFile();

                    FileOutputStream fos = new FileOutputStream(f);
                    // 将图片写出到文件输出流中
                    mBitmap2.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.close();
                    mUrl = f.getAbsolutePath();

                   ThreadUtils.runOnMainThread(new Runnable() {
                       @Override
                       public void run() {
                           mOnSure.backResult(mUrl);
                       }
                   });
                    Log.d("QianMingUtils", mUrl);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


        //  this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + Environment.getExternalStorageDirectory())));

        // 为了图片保存之后, 立即在图库看到图片, 模拟一个系统sd卡挂载广播
    /*		Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED);
            // 设置文件路径
			intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
			sendBroadcast(intent);*/


    }


}
