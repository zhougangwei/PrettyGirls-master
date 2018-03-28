package coder.aihui.util.viewutil;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * @ 创建者   zhou
 * @ 创建时间   2016/12/19 13:53
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2016/12/19$
 * @ 更新描述  ${TODO}
 */

public class DatePickUtil {

    private Calendar         calendar;
    private DatePickerDialog dialog;

    private static DatePickUtil mDatePickUtil = new DatePickUtil();
    private DatePickUtil() {
    }

    public static DatePickUtil getInstance() {
        return mDatePickUtil;
    }


    public interface GetResult {
        void getResult(String result);
    }
    boolean isCancel = true;
    public void showDatePick(final TextView textView, final Activity activity, final GetResult result) {
        textView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        calendar = Calendar.getInstance();
                        final int year;
                        final int month;
                        final int day;


                        String string = textView.getText().toString();
                        if (!TextUtils.isEmpty(string)) {
                            String[] split = string.split("-");
                            year = Integer.parseInt(split[0]);

                            if ((Integer.parseInt(split[1]) + 1) >= 10) {
                                month = (0 + Integer.parseInt(split[1]) - 1);
                            } else {
                                month = (Integer.parseInt(split[1]) - 1);
                            }

                            day = Integer.parseInt(split[2]);
                        } else {
                            year = calendar.get(Calendar.YEAR);
                            month = calendar.get(Calendar.MONTH);
                            day = calendar.get(Calendar.DAY_OF_MONTH);
                        }

                        dialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                if(isCancel==false){
                                    isCancel=true;
                                    return;
                                }

                                System.out.println("年-->" + year + "月-->"
                                        + monthOfYear + "日-->" + dayOfMonth);
                                if ((monthOfYear + 1) >= 10) {
                                    textView.setText(year + "-" + (monthOfYear + 1) + "-"
                                            + (dayOfMonth >= 10 ? dayOfMonth : ("0" + dayOfMonth)));
                                    result.getResult(year + "-" + (monthOfYear + 1) + "-"
                                            + dayOfMonth);
                                } else if (monthOfYear + 1 < 10) {
                                    textView.setText(year + "-" + "0" + (monthOfYear + 1) + "-"
                                            + (dayOfMonth >= 10 ? dayOfMonth : ("0" + dayOfMonth)));
                                    result.getResult(year + "-" + "0" + (monthOfYear + 1) + "-"
                                            + dayOfMonth);
                                }

                            }
                        }, year, month, day);
                        dialog.setButton(DialogInterface.BUTTON_NEGATIVE,"取消",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                isCancel =false;
                                textView.setText("");
                            }
                        });
                        dialog.show();

                    }
                }
        );
    }

}
