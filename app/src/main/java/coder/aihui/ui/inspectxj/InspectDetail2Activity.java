package coder.aihui.ui.inspectxj;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.text.method.TextKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.data.bean.DialogBean;
import coder.aihui.data.bean.INSPECT_REPS;
import coder.aihui.data.bean.gen.INSPECT_REPSDao;
import coder.aihui.widget.ListBottomDialog;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

public class InspectDetail2Activity extends AppActivity {

    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.rv)
    RecyclerView mRv;


    List<INSPECT_REPS> mDatas = new ArrayList();     //
    private CommonAdapter<INSPECT_REPS> mAdapter;
    private String                      mPName;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_inspect_detail2;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }


    @Override
    protected void initView() {


        initGetIntent();


    }

    private void initGetIntent() {
        Intent intent = getIntent();
        mPName = intent.getStringExtra(Content.INSPECT_PNAME);
        int repId = intent.getIntExtra("repId", -2);
        if (!TextUtils.isEmpty(mPName)) {
            mTvTitle.setText(mPName);  //标题设置为一级目录
            gotoInitRecyCleView();
            gotoInitData(mPName, repId);
        }


    }


    private void gotoInitRecyCleView() {

        mRv.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new CommonAdapter<INSPECT_REPS>(this, R.layout.item_inspect_detail2, mDatas) {
            @Override
            protected void convert(final ViewHolder holder, final INSPECT_REPS bean, int position) {
                holder.setText(R.id.tv_name, bean.getINSPR_PCONTENT());
                holder.setText(R.id.et_value, bean.getINSPR_EXAM_VALUE());       //实测值
                final TextView mTvName = (TextView)holder.getView(R.id.tv_name);


                if( "Select".equals(bean.getINSPR_VAL_TYPE())){
                    //"1-我的|2-你的"
                    String[] split = bean.getINSPR_SEL_VAL().split("\\|");
                    for (int i = 0; i < split.length; i++) {
                        if (split[i].split("-")[0].equals(bean.getINSPR_EXAM_VALUE())){
                            holder.setText(R.id.et_value,split[i].split("-")[1]);       //实测值
                        }
                    }
                }

                RadioGroup rg_result = (RadioGroup) holder.getView(R.id.rg_result);
                final RadioButton rb_hg = (RadioButton) holder.getView(R.id.rb_hg);
                final RadioButton rb_bhg = (RadioButton) holder.getView(R.id.rb_bhg);


                RadioGroup rg_bx = (RadioGroup) holder.getView(R.id.rg_bx);
                final RadioButton rb_bx_no = (RadioButton) holder.getView(R.id.rb_bx_no);
                final RadioButton rb_bx_yes = (RadioButton) holder.getView(R.id.rb_bx_yes);

                rg_result.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        if (rb_hg.getId() == checkedId) {       //合格
                            bean.setINSPR_HG_VAL("HG");
                        } else if (rb_bhg.getId() == checkedId) {    //不合格
                            bean.setINSPR_HG_VAL("BHG");
                        }

                        if ("BHG".equals(bean.getINSPR_HG_VAL())) {
                            Drawable drawable = getResources().getDrawable(R.mipmap.ic_bhg);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            mTvName.setCompoundDrawables(null, null, drawable, null);
                        } else {
                            mTvName.setCompoundDrawables(null, null, null, null);
                        }


                    }
                });
                rg_bx.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        if (rb_bx_no.getId() == checkedId) {       //不用修
                            bean.setINSPR_WX_NEED(0);
                        } else if (rb_bx_yes.getId() == checkedId) {    //用修
                            bean.setINSPR_WX_NEED(1);
                        }



                    }
                });

                if ("HG".equals(bean.getINSPR_HG_VAL())) {
                    rg_result.check(rb_hg.getId());
                } else if ("BHG".equals(bean.getINSPR_HG_VAL())) {
                    rg_result.check(rb_bhg.getId());
                }

                if (0 == bean.getINSPR_WX_NEED()) {
                    rg_bx.check(rb_bx_no.getId());
                } else if (1 == bean.getINSPR_WX_NEED()) {
                    rg_bx.check(rb_bx_yes.getId());
                }

                holder.setOnClickListener(R.id.et_value, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showType(bean, (TextView) holder.getView(R.id.et_value));         //渲染类型
                    }
                });
                holder.setOnClickListener(R.id.tv_add_bz, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View inflate = View.inflate(InspectDetail2Activity.this, R.layout.dialog_bz, null);
                        final AlertDialog alertDialog = new AlertDialog.Builder(InspectDetail2Activity.this)
                                .setView(inflate).create();
                        final EditText etBz = (EditText) inflate.findViewById(R.id.et_bz);
                        TextView bt_ok = (TextView) inflate.findViewById(R.id.tv_save);
                        TextView bt_quit = (TextView) inflate.findViewById(R.id.tv_quit);
                        bt_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                bean.setINSPR_BZ(etBz.getText().toString());
                                alertDialog.dismiss();
                            }
                        });
                        bt_quit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });
                        etBz.setText(bean.getINSPR_BZ());
                        alertDialog.show();
                    }
                });

            }
        };
        mRv.setAdapter(mAdapter);

    }

    private void showType(final INSPECT_REPS bean, final TextView tvValue) {
        String type = bean.getINSPR_VAL_TYPE();   //输入类型
        if (TextUtils.isEmpty(type) || type.equals("Text")) {
            type = "Number";
        }
        switch (type) {
            //如果是数字
            case "Number":

                final EditText et_act = (EditText) View.inflate(this, R.layout.include_edittext, null);
                et_act.setText(bean.getINSPR_EXAM_VALUE());
                final AlertDialog alertDialog = new AlertDialog.Builder(InspectDetail2Activity.this)
                        .setView(et_act).create();
                if ("Number".equals(bean.getINSPR_VAL_TYPE())) {
                    String digits = "0123456789.";//
                    et_act.setKeyListener(DigitsKeyListener.getInstance(digits));
                } else {
                    et_act.setKeyListener(TextKeyListener.getInstance());
                }
                if (!TextUtils.isEmpty(bean.getINSPR_EXAM_VALUE())) {
                    et_act.setText(bean.getINSPR_EXAM_VALUE());
                } else {
                    et_act.setText("");
                }
                //确定监听
                et_act.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                /*判断是否是“GO”键*/
                        if ((event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) || actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                            bean.setINSPR_EXAM_VALUE(v.getText().toString());
                            tvValue.setText(v.getText().toString());
                            showWc();
                            alertDialog.dismiss();
                            return true;
                        }
                        return false;
                    }
                });

                alertDialog.show();
                break;

            //选择框的
            case "Select":
                //"1-我的|2-你的"
                String[] split = bean.getINSPR_SEL_VAL().split("\\|");
                ArrayList<DialogBean> mSelectDatas = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    DialogBean dialogBean = new DialogBean(split[i].split("-")[1], split[i].split("-")[0]);
                    mSelectDatas.add(dialogBean);
                }
                new ListBottomDialog(this).showDialog(mSelectDatas, new ListBottomDialog.onBackResult() {
                    @Override
                    public void backResult(DialogBean dialogBean) {
                        bean.setINSPR_EXAM_VALUE(dialogBean.getObject() + "");
                        tvValue.setText(dialogBean.getName() + "");
                    }
                });
                break;
            case "Date":
                final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                TimePickerDialog build = new TimePickerDialog.Builder()
                        .setCurrentMillseconds(TimeUtils.string2Milliseconds(TextUtils.isEmpty(tvValue.getText().toString()) ? TimeUtils.getCurTimeString(format) : tvValue.getText().toString(), format))
                        .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg))
                        .setType(Type.YEAR_MONTH_DAY)
                        .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                        .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                        .setWheelItemTextSize(15)
                        .setCallBack(new OnDateSetListener() {
                            @Override
                            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                                tvValue.setText(TimeUtils.milliseconds2String(millseconds, format));
                            }
                        })
                        .build();
                build.show(getSupportFragmentManager(), "1");
                break;
            case "MultipleSelect":
                break;
            default:
                break;
        }


    }


    //显示误差
    private void showWc() {


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        gotoSave();
        finish();
    }

    private void gotoSave() {
        Intent intent = new Intent();

        String ISHG = "HG";
        for (INSPECT_REPS data : mDatas) {
            if ("BHG".equals(data.getINSPR_HG_VAL())) {
                ISHG = "BHG";
            }
        }
        intent.putExtra("ISHG", ISHG);
        intent.putExtra(Content.INSPECT_PNAME, mPName);
        setResult(RESULT_OK, intent);
        mDaoSession.getINSPECT_REPSDao().insertOrReplaceInTx(mDatas);
        finish();
    }


    /**
     * @param stringExtra pName
     * @param repId       rep主表的id -1为临时存储
     */
    private void gotoInitData(String stringExtra, long repId) {

        mDaoSession.getINSPECT_REPSDao().queryBuilder().where(INSPECT_REPSDao.Properties.INSPR_PNAME.eq(stringExtra))
                .where(INSPECT_REPSDao.Properties.INSPR_REP_ID.eq(repId))
                .rx().list().filter(new Func1<List<INSPECT_REPS>, Boolean>() {
            @Override
            public Boolean call(List<INSPECT_REPS> inspect_repses) {
                return inspect_repses != null;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<INSPECT_REPS>>() {
                    @Override
                    public void call(List<INSPECT_REPS> inspect_repses) {
                        mDatas.addAll(inspect_repses);
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        gotoSave();
    }
}
