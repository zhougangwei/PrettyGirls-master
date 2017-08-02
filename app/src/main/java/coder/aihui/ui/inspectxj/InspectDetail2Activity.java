package coder.aihui.ui.inspectxj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.aihui.R;
import coder.aihui.base.AppActivity;
import coder.aihui.base.BaseFragment;
import coder.aihui.base.Content;
import coder.aihui.data.bean.INSPECT_REPS;
import coder.aihui.data.bean.gen.INSPECT_REPSDao;
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
            protected void convert(ViewHolder holder, INSPECT_REPS inspect_reps, int position) {


                String type = inspect_reps.getINSPR_VAL_TYPE();   //输入类型
                if (TextUtils.isEmpty(type) || type.equals("Text")) {
                    type = "Number";
                }
          //     showType(type,inspect_reps,holder.getView(R.id.et_value));




            }
        };

        mRv.setAdapter(mAdapter);


    }



/*    public void showType(String type, final INSPECT_REPS bean, View view) {

        final String defValue = bean.getINSPR_RE_VALUE();
        switch (type) {
            //如果是数字
            case "Number":
                EditText et_act =(EditText)view;
                view.setVisibility(View.VISIBLE);
                //todo 有空处理你  现在没法inputType了
                //设置成只能数字   这破玩意儿中的singline 会和Listview冲突 搞得乱七八糟
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


                //实时保存 不然ListView不断的被复用数据会错误
                et_act.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            Editable text = et_act.getText();
                            if (!TextUtils.isEmpty(text)) {
                                inspect_reps.setINSPR_EXAM_VALUE(text.toString());
                            } else {
                                inspect_reps.setINSPR_EXAM_VALUE("");       //变空是为了防止ListView的复用错误
                            }
                            gotoShowWc(inspect_reps, holder.et, holder.wc);
                        }
                    }
                });

                gotoShowWc(inspect_reps, holder.et, holder.wc);






                break;

            //选择框的
            case "Select":
                //"1-我的|2-你的"
                String[] split = bean.getINSPR_SEL_VAL().split("\\|");

                ArrayList<DialogBean> mSelectDatas = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    new DialogBean(split[i].split("-")[1],split[i].split("-")[0]);
                }
                new ListBottomDialog(this).showDialog(mSelectDatas, new ListBottomDialog.backResult() {
                    @Override
                    public void backResult(DialogBean dialogBean) {
                        bean.setINSPR_EXAM_VALUE(dialogBean.getObject()+"");
                    }
                });


                break;
            case "Date":

                TextView tv_date = holder.date;
                holder.select.setVisibility(GONE);
                holder.date.setVisibility(VISIBLE);
                holder.et.setVisibility(GONE);


                if (!TextUtils.isEmpty(inspect_reps.getINSPR_EXAM_VALUE())) {
                    tv_date.setText(inspect_reps.getINSPR_EXAM_VALUE());
                }


                DatePickUtil.getInstance().showDatePick(tv_date, (BaseActivity) context, new DatePickUtil.GetResult() {
                    @Override
                    public void getResult(String result) {
                        inspect_reps.setINSPR_EXAM_VALUE(result);
                    }
                });

                if (!TextUtils.isEmpty(inspect_reps.getINSPR_EXAM_VALUE())) {
                    tv_date.setText(inspect_reps.getINSPR_EXAM_VALUE());
                } else {
                    tv_date.setText("");
                }

                break;

            case "MultipleSelect":
                break;
            default:
                break;

        }
    }*/












    private void gotoInitData(String stringExtra) {
        mDaoSession.getINSPECT_REPSDao().queryBuilder().where(INSPECT_REPSDao.Properties.INSPR_PNAME.like("%" + stringExtra + "%"))
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
    }
}
