package coder.aihui.util.viewutil;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.SizeUtils;

import java.util.Calendar;

import coder.aihui.R;
import coder.aihui.util.ScreenUtil;

import static coder.aihui.app.MyApplication.mContext;
import static coder.aihui.util.DateUtil.calendar;

/**
 * @ 创建者   zhou
 * @ 创建时间   2016/12/27 14:42
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2016/12/27$
 * @ 更新描述  ${TODO}
 */

public class TextViewUtils {


    //创建一个 小选择框
    public static TextView createText(Activity activity) {
        //我

        TextView textView = new TextView(activity);
        textView.setBackgroundResource(R.drawable.shape_uncheck_normal);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, SizeUtils.dp2px(activity, 30f));
        int FiveDp = SizeUtils.dp2px(activity, 5f);
        int TwoDp = SizeUtils.dp2px(activity, 2f);
        textView.setPadding(FiveDp, TwoDp, FiveDp, TwoDp);
        params.gravity = Gravity.CENTER_VERTICAL;
        int tenDp = SizeUtils.dp2px(activity, 10f);
        params.setMargins(FiveDp, tenDp, FiveDp, tenDp);
        textView.setLayoutParams(params);
        return textView;
    }


    //删除键
    public static TextView createDeleteTextView(Activity activity) {
        //跳出删除按钮
        TextView textView = new TextView(activity);
        textView.setText("删除该条目");
        textView.setTextSize(SizeUtils.dp2px(mContext
                ,9f));
        textView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(SizeUtils.dp2px(mContext, 60f), ScreenUtil.getScreenWidth(mContext) / 2);
        params.gravity = Gravity.CENTER;
        textView.setLayoutParams(params);

        return textView;
    }


    //判断edittext里的内容是否为空
    public static boolean isEmpty(TextView et){
        if(TextUtils.isEmpty(et.getText().toString())){
            return true;  //为空
        }else{

            return false;
        }
    }

    //将Textview变成部分红色
    public static void  changeTextColorRed(TextView textView,int start,int end){
        SpannableStringBuilder builder = new SpannableStringBuilder(textView.getText().toString());
        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);
        builder.setSpan(redSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
    }


   //弹出日期选择框
    public interface GetResult {
        void getResult(String result);
    }

    private DatePickerDialog dialog;
    public void showDatePick(final TextView textView, final Activity activity, final GetResult result) {

        textView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        calendar = Calendar.getInstance();
                        dialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                System.out.println("年-->" + year + "月-->"
                                        + monthOfYear + "日-->" + dayOfMonth);
                                if ((monthOfYear + 1) >= 10) {
                                    textView.setText(year + "-" + (monthOfYear + 1) + "-"
                                            + dayOfMonth);
                                    result.getResult(year + "-" + (monthOfYear + 1) + "-"
                                            + dayOfMonth);
                                } else if (monthOfYear + 1 < 10) {
                                    textView.setText(year + "-" + "0" + (monthOfYear + 1) + "-"
                                            + dayOfMonth);
                                    result.getResult(year + "-" + "0" + (monthOfYear + 1) + "-"
                                            + dayOfMonth);
                                }
                            }
                        }, calendar.get(Calendar.YEAR), calendar
                                .get(Calendar.MONTH), calendar
                                .get(Calendar.DAY_OF_MONTH));
                        dialog.show();
                    }
                }

        );
    }

}
