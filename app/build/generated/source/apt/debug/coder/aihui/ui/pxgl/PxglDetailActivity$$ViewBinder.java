// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.pxgl;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class PxglDetailActivity$$ViewBinder<T extends PxglDetailActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends PxglDetailActivity> implements Unbinder {
    protected T target;

    private View view2131755422;

    private View view2131755441;

    private View view2131755337;

    private View view2131755339;

    private View view2131755344;

    private View view2131755346;

    private View view2131755348;

    private View view2131755306;

    private View view2131755353;

    private View view2131755354;

    private View view2131755263;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755235, "field 'mTvTitle'", TextView.class);
      view = finder.findRequiredView(source, 2131755422, "field 'mIvBack' and method 'onViewClicked'");
      target.mIvBack = finder.castView(view, 2131755422, "field 'mIvBack'");
      view2131755422 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755441, "field 'mIvUpdown' and method 'onViewClicked'");
      target.mIvUpdown = finder.castView(view, 2131755441, "field 'mIvUpdown'");
      view2131755441 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvPxry = finder.findRequiredViewAsType(source, 2131755338, "field 'mTvPxry'", TextView.class);
      view = finder.findRequiredView(source, 2131755337, "field 'mLlPxry' and method 'onViewClicked'");
      target.mLlPxry = finder.castView(view, 2131755337, "field 'mLlPxry'");
      view2131755337 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvPxsb = finder.findRequiredViewAsType(source, 2131755340, "field 'mTvPxsb'", TextView.class);
      view = finder.findRequiredView(source, 2131755339, "field 'mLlPxsb' and method 'onViewClicked'");
      target.mLlPxsb = finder.castView(view, 2131755339, "field 'mLlPxsb'");
      view2131755339 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mEtZt = finder.findRequiredViewAsType(source, 2131755341, "field 'mEtZt'", EditText.class);
      target.mEtZjr = finder.findRequiredViewAsType(source, 2131755342, "field 'mEtZjr'", EditText.class);
      target.mEtJlr = finder.findRequiredViewAsType(source, 2131755343, "field 'mEtJlr'", EditText.class);
      target.mTvPxlx = finder.findRequiredViewAsType(source, 2131755345, "field 'mTvPxlx'", TextView.class);
      view = finder.findRequiredView(source, 2131755344, "field 'mLlPxlx' and method 'onViewClicked'");
      target.mLlPxlx = finder.castView(view, 2131755344, "field 'mLlPxlx'");
      view2131755344 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvPxf = finder.findRequiredViewAsType(source, 2131755347, "field 'mTvPxf'", TextView.class);
      view = finder.findRequiredView(source, 2131755346, "field 'mLlPxf' and method 'onViewClicked'");
      target.mLlPxf = finder.castView(view, 2131755346, "field 'mLlPxf'");
      view2131755346 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTextView6 = finder.findRequiredViewAsType(source, 2131755349, "field 'mTextView6'", TextView.class);
      target.mTvPxrq = finder.findRequiredViewAsType(source, 2131755350, "field 'mTvPxrq'", TextView.class);
      view = finder.findRequiredView(source, 2131755348, "field 'mLlPxrq' and method 'onViewClicked'");
      target.mLlPxrq = finder.castView(view, 2131755348, "field 'mLlPxrq'");
      view2131755348 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvPxsc = finder.findRequiredViewAsType(source, 2131755351, "field 'mTvPxsc'", TextView.class);
      view = finder.findRequiredView(source, 2131755306, "field 'mLlTime' and method 'onViewClicked'");
      target.mLlTime = finder.castView(view, 2131755306, "field 'mLlTime'");
      view2131755306 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mEtContent = finder.findRequiredViewAsType(source, 2131755352, "field 'mEtContent'", EditText.class);
      view = finder.findRequiredView(source, 2131755353, "field 'mRlFj' and method 'onViewClicked'");
      target.mRlFj = finder.castView(view, 2131755353, "field 'mRlFj'");
      view2131755353 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755354, "field 'mRlJc' and method 'onViewClicked'");
      target.mRlJc = finder.castView(view, 2131755354, "field 'mRlJc'");
      view2131755354 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755263, "method 'onViewClicked'");
      view2131755263 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mTvTitle = null;
      target.mIvBack = null;
      target.mIvUpdown = null;
      target.mTvPxry = null;
      target.mLlPxry = null;
      target.mTvPxsb = null;
      target.mLlPxsb = null;
      target.mEtZt = null;
      target.mEtZjr = null;
      target.mEtJlr = null;
      target.mTvPxlx = null;
      target.mLlPxlx = null;
      target.mTvPxf = null;
      target.mLlPxf = null;
      target.mTextView6 = null;
      target.mTvPxrq = null;
      target.mLlPxrq = null;
      target.mTvPxsc = null;
      target.mLlTime = null;
      target.mEtContent = null;
      target.mRlFj = null;
      target.mRlJc = null;

      view2131755422.setOnClickListener(null);
      view2131755422 = null;
      view2131755441.setOnClickListener(null);
      view2131755441 = null;
      view2131755337.setOnClickListener(null);
      view2131755337 = null;
      view2131755339.setOnClickListener(null);
      view2131755339 = null;
      view2131755344.setOnClickListener(null);
      view2131755344 = null;
      view2131755346.setOnClickListener(null);
      view2131755346 = null;
      view2131755348.setOnClickListener(null);
      view2131755348 = null;
      view2131755306.setOnClickListener(null);
      view2131755306 = null;
      view2131755353.setOnClickListener(null);
      view2131755353 = null;
      view2131755354.setOnClickListener(null);
      view2131755354 = null;
      view2131755263.setOnClickListener(null);
      view2131755263 = null;

      this.target = null;
    }
  }
}
