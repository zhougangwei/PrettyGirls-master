package coder.aihui.ui.inspectxj;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
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


    ArrayList mDatas = new ArrayList();     //
    private CommonAdapter<INSPECT_REPS> mAdapter;

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
        String stringExtra = intent.getStringExtra(Content.INSPECT_PNAME);
        if (!TextUtils.isEmpty(stringExtra)) {
            mTvTitle.setText(stringExtra);  //标题设置为一级目录
            gotoInitRecyCleView();
            gotoInitData(stringExtra);
        }
    }


    private void gotoInitRecyCleView() {

        mRv.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new CommonAdapter<INSPECT_REPS>(this, R.layout.item_inspect_detail2, mDatas) {
            @Override
            protected void convert(final ViewHolder holder, final INSPECT_REPS inspect_reps, int position) {

                holder.setOnClickListener(R.id.et_value, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showType(inspect_reps, (TextView) holder.getView(R.id.et_value));         //渲染类型
                    }
                });
            }
        };
        mRv.setAdapter(mAdapter);


    }

    private void showType(final INSPECT_REPS bean, TextView tvValue) {
        String type = bean.getINSPR_VAL_TYPE();   //输入类型
        if (TextUtils.isEmpty(type) || type.equals("Text")) {
            type = "Number";
        }
        switch (type) {
            //如果是数字
            case "Number":
                EditText et_act = (EditText) View.inflate(this, R.layout.include_edittext, null);
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
                            return true;
                        }
                        return false;
                    }
                });
                AlertDialog alertDialog = new AlertDialog.Builder(InspectDetail2Activity.this)
                        .setView(et_act).create();
                alertDialog.show();
                break;

            //选择框的
            case "Select":
                //"1-我的|2-你的"
                String[] split = bean.getINSPR_SEL_VAL().split("\\|");
                ArrayList<DialogBean> mSelectDatas = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    new DialogBean(split[i].split("-")[1], split[i].split("-")[0]);
                }
                new ListBottomDialog(this).showDialog(mSelectDatas, new ListBottomDialog.onBackResult() {
                    @Override
                    public void backResult(DialogBean dialogBean) {
                        bean.setINSPR_EXAM_VALUE(dialogBean.getObject() + "");
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
                        .build();
                build.show(getSupportFragmentManager(), "1");
                break;
            case "MultipleSelect":
                break;
            default:
                break;
        }


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        gotoSave();
        finish();
    }

    private void gotoSave() {

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);

        mDaoSession.insertOrReplace(mDatas);


    }




    private void gotoInitData(String stringExtra) {
        mDaoSession.getINSPECT_REPSDao().queryBuilder().where(INSPECT_REPSDao.Properties.INSPR_PNAME.like(stringExtra))
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
