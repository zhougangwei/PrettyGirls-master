package coder.aihui.util;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

import coder.aihui.data.bean.DialogBean;
import coder.aihui.data.bean.IN_ASSET;

import static coder.aihui.app.MyApplication.mContext;

/**
 * @ 创建者   zhou
 * @ 创建时间   2016/12/27 14:51
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2016/12/27$
 * @ 更新描述  ${TODO}
 */

public class Utils {
    @SuppressLint("NewApi")
    public static String getPathByUri4kitkat(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {// ExternalStorageProvider
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {// DownloadsProvider
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(uri)) {// MediaProvider
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {// MediaStore
            // (and
            // general)
            return getDataColumn(context, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {// File
            return uri.getPath();
        }
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


    public static void writeObjectToFile(Object obj) {
        File file = new File(mContext.getFilesDir(), "dq.dat");
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(obj);
            objOut.flush();
            objOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Object readObjectFromFile() {
        Object temp = null;
        File file = new File(mContext.getFilesDir(), "dq.dat");
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn = new ObjectInputStream(in);
            temp = objIn.readObject();
            objIn.close();
        } catch (IOException e) {
            System.out.println("read object failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }

/*

    //将task转为rep 方便渲染
    public static INSPECT_REPS changeTask2Reps(INSPECT_TASKS tasks){
        INSPECT_REPS reps = new INSPECT_REPS();

        reps.setINSPR_PNAME(tasks.getINSTS_PNAME());
        reps.setINSPR_PCONTENT(tasks.getINSTS_PCONTENT());
        try{
            reps.setINSPR_CYCLE(tasks.getINSTS_CYCLE().toString());
        }catch (Exception e){

        }

        reps.setINSPR_RE_VALUE(tasks.getINSTS_RE_VALUE());

        try{
            reps.setINSPR_IS_FILL_IN(tasks.getINSTS_IS_FILL_IN().toString());
        }catch (Exception e){
        }
        reps.setINSPR_COMMENTS(tasks.getINSTS_COMMENTS());
        reps.setINSPR_VAL_TYPE(tasks.getINSTS_VAL_TYPE());
        reps.setINSPR_SEL_VAL(tasks.getINSTS_SEL_VAL());
        reps.setINSPR_UNIT(tasks.getINSTS_UNIT());
        reps.setINSPR_HG_VAL(tasks.getINSTS_HG_VAL());

        reps.setINSPR_BZ(tasks.getINSTS_COMMENTS());
        reps.set_List_id(tasks.get_List_id());
        reps.set_List_parentId(tasks.get_List_parentId());

        return reps;
    }



    //InspectTempletItem

    public static INSPECT_REPS changIti2Reps(InspectTempletItem bean){
        INSPECT_REPS reps = new INSPECT_REPS();

*//*
        jmodel.put("INSPR_ID", "");//放个空的主键进去
        jmodel.put("INSPR_EXAM_VALUE", "");
        jmodel.put("INSPR_EXAM_RESULT", "");
        jmodel.put("INSPR_WX_NEED", "");
        jmodel.put("INSPS_PLAN_ID", 0);
        jmodel.put("INSPS_PNAME", model.getITEM_NAME());
        //三级节点
        jmodel.put("INSPS_ID", subModel.getITEM_CODE());
        jmodel.put("INSPS_PCONTENT", subModel.getITEM_NAME());
        jmodel.put("INSPS_CYCLE", subModel.getITEM_EXT_NUM2());
        jmodel.put("INSPS_RE_VALUE", subModel.getITEM_EXT_STRING6());
        jmodel.put("INSPS_IS_FILL_IN", subModel.getITEM_EXT_NUM1());
        jmodel.put("INSPS_COMMENTS", "");
        jmodel.put("INSPS_VAL_TYPE", subModel.getITEM_EXT_STRING4());
        jmodel.put("INSPS_SEL_VAL", subModel.getITEM_EXT_STRING5());
        jmodel.put("INSPS_UNIT", subModel.getITEM_EXT_STRING3());*//*

        //reps.setINSPR_PNAME();

        try{
            reps.setINSPR_CYCLE(bean.getITEM_EXT_NUM2()+"");
        }catch (Exception e){
            reps.setINSPR_CYCLE("");
        }

        reps.setINSPR_WX_NEED(0);
        reps.setPLAN_ID(0L);
        reps.setINSPR_PCONTENT(bean.getITEM_NAME());


        reps.setINSPR_RE_VALUE(bean.getITEM_EXT_STRING6());

        try{
            reps.setINSPR_IS_FILL_IN(bean.getITEM_EXT_NUM1()+"");
        }catch (Exception e){
            reps.setINSPR_IS_FILL_IN("");
        }

        //todo  评论没有?  模板不需要  评论
       // reps.setINSPR_COMMENTS("");
        reps.setINSPR_VAL_TYPE(bean.getITEM_EXT_STRING4());
        reps.setINSPR_SEL_VAL(bean.getITEM_EXT_STRING5());
        reps.setINSPR_UNIT(bean.getITEM_EXT_STRING3());
        reps.setINSPR_HG_VAL(bean.getITEM_EXT_STRING2());
        //reps.setINSPR_CYCLE(bean.getITEM_EXT_NUM2());

        //也没有?

        //reps.setINSPR_BZ(bean.getINSTS_COMMENTS());

        //reps.set_List_id(Integer.parseInt(bean.getITEM_ID()+""));
       // reps.set_List_parentId(tasks.get_List_parentId());


        return reps;
    }*/


    //规定只有两位小数的Edittext
    public static void onlyTwoEditText(final EditText num_et) {
        num_et.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        num_et.setText(s);
                        num_et.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    num_et.setText(s);
                    num_et.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        num_et.setText(s.subSequence(0, 1));
                        num_et.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

        });
    }


    /**
     * @param path 文件的绝对路径
     * @return 文件
     */
    //压缩图片的 保证图片不超过 200k
    public static File scal(String path) {
        //String path = fileUri.getPath();
        File outputFile = new File(path);
        long fileSize = outputFile.length();
        final long fileMaxSize = 200 * 1024;
        if (fileSize >= fileMaxSize) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            int height = options.outHeight;
            int width = options.outWidth;

            double scale = Math.sqrt((float) fileSize / fileMaxSize);
            options.outHeight = (int) (height / scale);
            options.outWidth = (int) (width / scale);
            options.inSampleSize = (int) (scale + 0.5);
            options.inJustDecodeBounds = false;

            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            outputFile = new File(Utils.createImageFile().getPath());
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(outputFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
                return null;

            }
            Log.d("D2xxManager", "ssss+ok" + outputFile.length());
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            } else {
                File tempFile = outputFile;
                outputFile = new File(Utils.createImageFile().getPath());
                Utils.copyFileUsingFileChannels(tempFile, outputFile);
            }

        }
        return outputFile;

    }


    public static String getRealPathFromUri(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = mContext.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


    public static Uri createImageFile() {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName, /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Save a file: path for use with ACTION_VIEW intents
        return Uri.fromFile(image);
    }

    public static void copyFileUsingFileChannels(File source, File dest) {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            try {
                inputChannel = new FileInputStream(source).getChannel();
                outputChannel = new FileOutputStream(dest).getChannel();
                outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } finally {
            try {
                inputChannel.close();
                outputChannel.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public interface onBackResult {
        void backResult(IN_ASSET bean, boolean b);
    }


    /**
     * @param rbJlzmYes  视图对象
     * @param bean       台账对象
     * @param backResult 返回结果方便操作
     */
    //设置RadioButton 为 可取消
    public static void canCancelRadioButton(final RadioButton rbJlzmYes, final IN_ASSET bean, final onBackResult backResult) {
        rbJlzmYes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (bean.isFlagChoose()) {
                        rbJlzmYes.setChecked(false);
                        bean.setFlagChoose(false);
                        if (backResult != null) {
                            backResult.backResult(bean, false);
                        }

                    } else {
                        bean.setFlagChoose(true);
                        rbJlzmYes.setChecked(true);
                        if (backResult != null) {
                            backResult.backResult(bean, true);
                        }
                    }
                }
                return true;
            }
        });
    }


    public static DialogBean changeBean2Dialog(Object object) {

        return new DialogBean(object);

    }


}
