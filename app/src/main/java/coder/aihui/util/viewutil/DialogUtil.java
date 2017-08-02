package coder.aihui.util.viewutil;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/3/16 15:45
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/3/16$
 * @ 更新描述  ${TODO}
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.view.View;


/**
 * 对话框封装类
 *
 * @author Z
 */
public class DialogUtil {

    public static final int NO_ICON = -1;  //无图标

    /**
     * 提示警告信息
     *
     * @param title
     * @param msg
     */
    public static void showErrorMsg(String title, String msg, Context ctx) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle(title)
                .setMessage(msg)
                .setPositiveButton("确定", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create().show();

    }

    /**
     * 创建消息对话框
     *
     * @param context  上下文 必填
     * @param iconId   图标，如：R.drawable.icon 或 DialogTool.NO_ICON 必填
     * @param title    标题 必填
     * @param message  显示内容 必填
     * @param btnName  按钮名称 必填
     * @param listener 监听器，需实现android.content.DialogInterface.OnClickListener接口 必填
     * @return
     */
    public static Dialog createMessageDialog(Context context, String title, String message,
                                             String btnName, OnClickListener listener, int iconId) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (iconId != NO_ICON) {
            //设置对话框图标
            builder.setIcon(iconId);
        }
        //设置对话框标题
        builder.setTitle(title);
        //设置对话框消息
        builder.setMessage(message);
        //设置按钮
        builder.setPositiveButton(btnName, listener);
        //创建一个消息对话框
        dialog = builder.create();

        return dialog;
    }


    /**
     * 创建警示（确认、取消）对话框
     *
     * @param context             上下文 必填
     * @param iconId              图标，如：R.drawable.icon 或 DialogTool.NO_ICON 必填
     * @param title               标题 必填
     * @param message             显示内容 必填
     * @param positiveBtnName     确定按钮名称 必填
     * @param negativeBtnName     取消按钮名称 必填
     * @param positiveBtnListener 监听器，需实现android.content.DialogInterface.OnClickListener接口 必填
     * @param negativeBtnListener 监听器，需实现android.content.DialogInterface.OnClickListener接口 必填
     * @return
     */
    public static Dialog createConfirmDialog(Context context, String title, String message,
                                             String positiveBtnName, String negativeBtnName, OnClickListener positiveBtnListener,
                                             OnClickListener negativeBtnListener, int iconId) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (iconId != NO_ICON) {
            //设置对话框图标
            builder.setIcon(iconId);
        }
        //设置对话框标题
        builder.setTitle(title);
        //设置对话框消息
        builder.setMessage(message);
        //设置确定按钮
        builder.setPositiveButton(positiveBtnName, positiveBtnListener);
        //设置取消按钮
        builder.setNegativeButton(negativeBtnName, negativeBtnListener);
        //创建一个消息对话框
        dialog = builder.create();

        return dialog;
    }


    /**
     * 创建单选对话框
     *
     * @param context             上下文 必填
     * @param iconId              图标，如：R.drawable.icon 或 DialogTool.NO_ICON 必填
     * @param title               标题 必填
     * @param itemsString         选择项 必填
     * @param positiveBtnName     确定按钮名称 必填
     * @param negativeBtnName     取消按钮名称 必填
     * @param positiveBtnListener 监听器，需实现android.content.DialogInterface.OnClickListener接口 必填
     * @param negativeBtnListener 监听器，需实现android.content.DialogInterface.OnClickListener接口 必填
     * @param itemClickListener   监听器，需实现android.content.DialogInterface.OnClickListener接口 必填
     * @return
     */
    public static Dialog createSingleChoiceDialog(Context context, String title, String[] itemsString,
                                                  String positiveBtnName, String negativeBtnName, OnClickListener positiveBtnListener,
                                                  OnClickListener negativeBtnListener, OnClickListener itemClickListener, int iconId) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (iconId != NO_ICON) {
            //设置对话框图标
            builder.setIcon(iconId);
        }
        //设置对话框标题
        builder.setTitle(title);
        //设置单选选项, 参数0: 默认第一个单选按钮被选中
        builder.setSingleChoiceItems(itemsString, 0, itemClickListener);
        //设置确定按钮
        builder.setPositiveButton(positiveBtnName, positiveBtnListener);
        //设置确定按钮
        builder.setNegativeButton(negativeBtnName, negativeBtnListener);
        //创建一个消息对话框
        dialog = builder.create();

        return dialog;
    }


    /**
     * 创建复选对话框
     *
     * @param context             上下文 必填
     * @param iconId              图标，如：R.drawable.icon 或 DialogTool.NO_ICON 必填
     * @param title               标题 必填
     * @param itemsString         选择项 必填
     * @param positiveBtnName     确定按钮名称 必填
     * @param negativeBtnName     取消按钮名称 必填
     * @param positiveBtnListener 监听器，需实现android.content.DialogInterface.OnClickListener接口 必填
     * @param negativeBtnListener 监听器，需实现android.content.DialogInterface.OnClickListener接口 必填
     * @param itemClickListener   监听器，需实现android.content.DialogInterface.OnMultiChoiceClickListener;接口 必填
     * @return
     */
    public static Dialog createMultiChoiceDialog(Context context, String title, String[] itemsString,
                                                 String positiveBtnName, String negativeBtnName, OnClickListener positiveBtnListener,
                                                 OnClickListener negativeBtnListener, OnMultiChoiceClickListener itemClickListener, int iconId) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (iconId != NO_ICON) {
            //设置对话框图标
            builder.setIcon(iconId);
        }
        //设置对话框标题
        builder.setTitle(title);
        //设置选项
        builder.setMultiChoiceItems(itemsString, null, itemClickListener);
        //设置确定按钮
        builder.setPositiveButton(positiveBtnName, positiveBtnListener);
        //设置确定按钮
        builder.setNegativeButton(negativeBtnName, negativeBtnListener);
        //创建一个消息对话框
        dialog = builder.create();

        return dialog;
    }


    /**
     * 创建列表对话框
     *
     * @param context             上下文 必填
     * @param iconId              图标，如：R.drawable.icon 或 DialogTool.NO_ICON 必填
     * @param title               标题 必填
     * @param itemsString         列表项 必填
     * @param negativeBtnName     取消按钮名称 必填
     * @param negativeBtnListener 监听器，需实现android.content.DialogInterface.OnClickListener接口 必填
     * @return
     */
    public static Dialog createListDialog(Context context, String title, String[] itemsString,
                                          String negativeBtnName, OnClickListener negativeBtnListener,
                                          OnClickListener itemClickListener, int iconId) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (iconId != NO_ICON) {
            //设置对话框图标
            builder.setIcon(iconId);
        }
        //设置对话框标题
        builder.setTitle(title);
        //设置列表选项
        builder.setItems(itemsString, itemClickListener);
        //设置确定按钮
        builder.setNegativeButton(negativeBtnName, negativeBtnListener);
        //创建一个消息对话框
        dialog = builder.create();

        return dialog;
    }


    /**
     * 创建自定义（含确认、取消）对话框
     *
     * @param context             上下文 必填
     * @param iconId              图标，如：R.drawable.icon 或 DialogTool.NO_ICON 必填
     * @param title               标题 必填
     * @param positiveBtnName     确定按钮名称 必填
     * @param negativeBtnName     取消按钮名称 必填
     * @param positiveBtnListener 监听器，需实现android.content.DialogInterface.OnClickListener接口 必填
     * @param negativeBtnListener 监听器，需实现android.content.DialogInterface.OnClickListener接口 必填
     * @param view                对话框中自定义视图 必填
     * @return
     */
    public static Dialog createRandomDialog(Context context, String title, String positiveBtnName,
                                            String negativeBtnName, OnClickListener positiveBtnListener,
                                            OnClickListener negativeBtnListener, View view, int iconId) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (iconId != NO_ICON) {
            //设置对话框图标
            builder.setIcon(iconId);
        }
        //设置对话框标题
        builder.setTitle(title);
        builder.setView(view);
        //设置确定按钮
        builder.setPositiveButton(positiveBtnName, positiveBtnListener);
        //设置确定按钮
        builder.setNegativeButton(negativeBtnName, negativeBtnListener);
        //创建一个消息对话框
        dialog = builder.create();

        return dialog;
    }


    public void getGreyBack(Activity activity) {
       /* Dialog dialog = new AlertDialog.Builder(activity)
                .setView(view)
                .create();

        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
        window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ERROR);

        dialog.setCancelable(false);
        dialog.showDialog();*/
    }
}
