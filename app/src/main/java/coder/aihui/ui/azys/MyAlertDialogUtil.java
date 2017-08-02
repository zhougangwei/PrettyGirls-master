package coder.aihui.ui.azys;

import android.app.Activity;
import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import coder.aihui.R;
import coder.aihui.data.normalbean.PjmxBean;
import coder.aihui.util.ToastUtil;
import coder.aihui.util.viewutil.DatePickUtil;


/**
 * @ 创建者   zhou
 * @ 创建时间   2016/12/17 14:02
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2016/12/17$
 * @ 更新描述  ${TODO}
 */


public class MyAlertDialogUtil {

    private static MyAlertDialogUtil mAlertListDialogUtil = new MyAlertDialogUtil();
    private AlertDialog mDialog;
    private TextView    et_id;
    private EditText    et_pjmc;
    private EditText    et_code;
    private EditText    et_ggxh;
    private EditText    et_brand;
    private TextView    et_bxq;
    private EditText    et_num;
    private EditText    et_prize;
    private EditText    et_unit;
  //  private EditText    et_remark;


    private onGetResult mOnGetResult;
    private PjmxBean    mBean;

    private MyAlertDialogUtil() {
    }


    public static MyAlertDialogUtil getInstance() {
        return mAlertListDialogUtil;
    }


    public interface onGetResult {
        public void getResult(PjmxBean bean, Integer pxmxId);
    }

   /* public void setOnGetNum(onGetResult listener) {

    }*/


    public MyAlertDialogUtil showDialog(final Integer pxmxId, PjmxBean bean
            , Activity activity, onGetResult listener) {
        this.mBean = bean;
        View view = View.inflate(activity, R.layout.dialog_pjmx_view, null);

        et_id = (TextView) view.findViewById(R.id.et_id);
        et_pjmc = (EditText) view.findViewById(R.id.et_pjmc);
        et_code = (EditText) view.findViewById(R.id.et_code);
        et_ggxh = (EditText) view.findViewById(R.id.et_ggxh);
        et_brand = (EditText) view.findViewById(R.id.et_brand);
        et_bxq = (TextView) view.findViewById(R.id.et_bxq);
        et_num = (EditText) view.findViewById(R.id.et_num);
        et_prize = (EditText) view.findViewById(R.id.et_prize);
        et_unit = (EditText) view.findViewById(R.id.et_unit);
       TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView   tvOk = (TextView) view.findViewById(R.id.tv_add);
        //et_remark = (EditText) view.findViewById(R.id.et_remark);

        DatePickUtil.getInstance().showDatePick(et_bxq, activity, new DatePickUtil.GetResult() {
            @Override
            public void getResult(String result) {

            }
        });

        coder.aihui.util.Utils.onlyTwoEditText(et_prize);

        if (bean != null) {
            if (bean.getCheckDetailId() != null) {
                // mEtJysynx.setText(bean.getJYSYNX() + "");
                et_id.setText("PJ" + bean.getCheckDetailId());
            }
            et_pjmc.setText(bean.getPdPjmc());
            et_code.setText(bean.getPdCode());
            et_ggxh.setText(bean.getPdGgxh());
            et_brand.setText(bean.getPdBrand());


            if (bean.getBxjzrq2() != null) {
                et_bxq.setText(bean.getBxjzrq2() + "");
            }

            if (bean.getPdNum() != null) {
                et_num.setText(bean.getPdNum() + "");
            }
            if (bean.getPdPrice() != null) {
                et_prize.setText(bean.getPdPrice() + "");
            }
            et_unit.setText(bean.getPdUnit());
            //et_remark.setText(bean.getPdRemark());

        } else {
            et_id.setText("PJ" + pxmxId);
        }


        //取消
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_pjmc.getText().toString())) {
                    keepDialogOpen(mDialog);
                    ToastUtil.showToast("请填写配件名称");
                } else if (TextUtils.isEmpty(et_code.getText().toString())) {
                    keepDialogOpen(mDialog);
                    ToastUtil.showToast("请填写生产编号");
                } else {
                    closeDialog(mDialog);
                    gototrue(pxmxId);
                    mDialog.dismiss();
                }
            }
        });
        //取消
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDialog(mDialog);
                mOnGetResult.getResult(null, pxmxId);
                mDialog.dismiss();
            }
        });
        mOnGetResult = listener;
        mDialog = new AlertDialog.Builder(activity).setView(view).create();
        mDialog.setCancelable(true);
        mDialog.show();

        return this;
    }


    //保持dialog不关闭的方法
    private void keepDialogOpen(AlertDialog dialog) {
        try {
            java.lang.reflect.Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
            field.setAccessible(true);
            field.set(dialog, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //关闭dialog的方法
    private void closeDialog(AlertDialog dialog) {
        try {
            java.lang.reflect.Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
            field.setAccessible(true);
            field.set(dialog, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void gototrue(Integer pxmxId) {

        if (mBean == null) {
            mBean = new PjmxBean();
            mBean.setCheckDetailId(pxmxId);
        }


        mBean.setPdPjmc(et_pjmc.getText().toString());
        mBean.setPdCode(et_code.getText().toString());
        mBean.setPdGgxh(et_ggxh.getText().toString());
        mBean.setPdBrand(et_brand.getText().toString());

        String bxq = et_bxq.getText().toString();
        if (!TextUtils.isEmpty(bxq)) {
            mBean.setBxjzrq2(bxq);
        }

        String num = et_num.getText().toString();

        if (!TextUtils.isEmpty(num)) {
            mBean.setPdNum(Integer.parseInt(num));
        }

        String prize = et_prize.getText().toString();
        if (!TextUtils.isEmpty(prize)) {
            mBean.setPdPrice(Integer.parseInt(prize));
        }

        mBean.setPdUnit(et_unit.getText().toString());
       //mBean.setPdRemark(et_remark.getText().toString());

        // String json = GsonUtil.parseObjectToJson(mBean);
        pxmxId = pxmxId + 1;
        mOnGetResult.getResult(mBean, pxmxId);
        //Log.d("AlertListDialogUtil2", sb.toString());
    }


}
