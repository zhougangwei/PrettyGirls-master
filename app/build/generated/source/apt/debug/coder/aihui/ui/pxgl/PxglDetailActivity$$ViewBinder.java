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

    private View view2131755414;

    private View view2131755433;

    private View view2131755330;

    private View view2131755332;

    private View view2131755337;

    private View view2131755339;

    private View view2131755341;

    private View view2131755305;

    private View view2131755345;

    private View view2131755346;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      view = finder.findRequiredView(source, 2131755414, "field 'mIvBack' and method 'onViewClicked'");
      target.mIvBack = finder.castView(view, 2131755414, "field 'mIvBack'");
      view2131755414 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755433, "field 'mIvUpdown' and method 'onViewClicked'");
      target.mIvUpdown = finder.castView(view, 2131755433, "field 'mIvUpdown'");
      view2131755433 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755330, "field 'mLlPxry' and method 'onViewClicked'");
      target.mLlPxry = finder.castView(view, 2131755330, "field 'mLlPxry'");
      view2131755330 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755332, "field 'mLlPxsb' and method 'onViewClicked'");
      target.mLlPxsb = finder.castView(view, 2131755332, "field 'mLlPxsb'");
      view2131755332 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mEtZt = finder.findRequiredViewAsType(source, 2131755334, "field 'mEtZt'", EditText.class);
      target.mEtZjr = finder.findRequiredViewAsType(source, 2131755335, "field 'mEtZjr'", EditText.class);
      target.mEtJlr = finder.findRequiredViewAsType(source, 2131755336, "field 'mEtJlr'", EditText.class);
      target.mTvPxlx = finder.findRequiredViewAsType(source, 2131755338, "field 'mTvPxlx'", TextView.class);
      view = finder.findRequiredView(source, 2131755337, "field 'mLlPxlx' and method 'onViewClicked'");
      target.mLlPxlx = finder.castView(view, 2131755337, "field 'mLlPxlx'");
      view2131755337 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvPxf = finder.findRequiredViewAsType(source, 2131755340, "field 'mTvPxf'", TextView.class);
      view = finder.findRequiredView(source, 2131755339, "field 'mLlPxf' and method 'onViewClicked'");
      target.mLlPxf = finder.castView(view, 2131755339, "field 'mLlPxf'");
      view2131755339 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTextView6 = finder.findRequiredViewAsType(source, 2131755342, "field 'mTextView6'", TextView.class);
      target.mTvPxrq = finder.findRequiredViewAsType(source, 2131755343, "field 'mTvPxrq'", TextView.class);
      view = finder.findRequiredView(source, 2131755341, "field 'mLlPxrq' and method 'onViewClicked'");
      target.mLlPxrq = finder.castView(view, 2131755341, "field 'mLlPxrq'");
      view2131755341 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755305, "field 'mLlTime' and method 'onViewClicked'");
      target.mLlTime = finder.castView(view, 2131755305, "field 'mLlTime'");
      view2131755305 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvScrq = finder.findRequiredViewAsType(source, 2131755239, "field 'mTvScrq'", TextView.class);
      target.mTvPxry = finder.findRequiredViewAsType(source, 2131755331, "field 'mTvPxry'", TextView.class);
      target.mTvPxsb = finder.findRequiredViewAsType(source, 2131755333, "field 'mTvPxsb'", TextView.class);
      view = finder.findRequiredView(source, 2131755345, "field 'mLlFj' and method 'onViewClicked'");
      target.mLlFj = finder.castView(view, 2131755345, "field 'mLlFj'");
      view2131755345 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755346, "field 'mLlJc' and method 'onViewClicked'");
      target.mLlJc = finder.castView(view, 2131755346, "field 'mLlJc'");
      view2131755346 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755143, "field 'mTvTitle'", TextView.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mIvBack = null;
      target.mIvUpdown = null;
      target.mLlPxry = null;
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
      target.mLlTime = null;
      target.mTvScrq = null;
      target.mTvPxry = null;
      target.mTvPxsb = null;
      target.mLlFj = null;
      target.mLlJc = null;
      target.mTvTitle = null;

      view2131755414.setOnClickListener(null);
      view2131755414 = null;
      view2131755433.setOnClickListener(null);
      view2131755433 = null;
      view2131755330.setOnClickListener(null);
      view2131755330 = null;
      view2131755332.setOnClickListener(null);
      view2131755332 = null;
      view2131755337.setOnClickListener(null);
      view2131755337 = null;
      view2131755339.setOnClickListener(null);
      view2131755339 = null;
      view2131755341.setOnClickListener(null);
      view2131755341 = null;
      view2131755305.setOnClickListener(null);
      view2131755305 = null;
      view2131755345.setOnClickListener(null);
      view2131755345 = null;
      view2131755346.setOnClickListener(null);
      view2131755346 = null;

      this.target = null;
    }
  }
}
