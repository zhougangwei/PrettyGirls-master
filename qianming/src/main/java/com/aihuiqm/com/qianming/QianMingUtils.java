package com.aihuiqm.com.qianming;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @ 创建者   zhou
 * @ 创建时间   2016/12/24 9:48
 * @ 描述    ${TODO}
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


    private Context mContext;

    public QianMingUtils(Context context) {
        mContext = context;
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


        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        // 1. 创建空白纸张
      //  bitmap = Bitmap.createBitmap(width, 800, Bitmap.Config.ARGB_8888);
        bitmap = Bitmap.createBitmap(width, 800, Bitmap.Config.ARGB_8888);

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
                Toast.makeText(mContext,"正在保存...",Toast.LENGTH_SHORT).show();
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
          /*  case R.id.save:
                goToSave();
                break;*/
      /*      case R.id.cexiao:
                //goToCeXiao();
                break;*/
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
                  //  mBitmap2 = BitmapDeleteNoUseSpaceUtil.deleteNoUseWhiteSpace(bitmap);

                    mBitmap2 = bitmap;

                   /* int width = bitmap.getWidth();
                    int height = bitmap.getHeight();
                    // 设置想要的大小
                    int newWidth = 100;
                    int newHeight = 40;
                    // 计算缩放比例
                    float scaleWidth = ((float) newWidth) / width;
                    float scaleHeight = ((float) newHeight) / height;
                    // 取得想要缩放的matrix参数
                    Matrix matrix = new Matrix();
                    matrix.postScale(scaleWidth, scaleHeight);
                    // 得到新的图片
                   mBitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,
                            true);*/

                    //mBitmap2 = Bitmap.createScaledBitmap(bitmap, 100, 40, true);


                    SystemClock.currentThreadTimeMillis();

                    //用时间来命名
                    String time = "IMG_" +new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date()) + ".png";

                 //   String cachePath;


                    String path = Environment.getExternalStorageDirectory().getPath();
                 /*   if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                            || !Environment.isExternalStorageRemovable()) {


                        cachePath = mContext.getExternalStorageDirectory().getPath();
                    } else {
                        cachePath = mContext.getCacheDir().getPath();
                    }*/
                    File f = new File(path, time);

                    if (f.exists()) {
                        f.delete();
                    }
                    f.createNewFile();

                    FileOutputStream fos = new FileOutputStream(f);
                    // 将图片写出到文件输出流中
                    mBitmap2.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.close();
                    mUrl = f.getAbsolutePath();


                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri uri = Uri.fromFile(f);
                    intent.setData(uri);
                    mContext.sendBroadcast(intent);

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




        // 为了图片保存之后, 立即在图库看到图片, 模拟一个系统sd卡挂载广播
    /*		Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED);
            // 设置文件路径
			intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
             mContext.sendBroadcast(intent);*/


    }


}
