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

    private View view2131755434;

    private View view2131755194;

    private View view2131755346;

    private View view2131755348;

    private View view2131755353;

    private View view2131755355;

    private View view2131755357;

    private View view2131755314;

    private View view2131755362;

    private View view2131755363;

    private View view2131755263;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755236, "field 'mTvTitle'", TextView.class);
      view = finder.findRequiredView(source, 2131755434, "field 'mIvBack' and method 'onViewClicked'");
      target.mIvBack = finder.castView(view, 2131755434, "field 'mIvBack'");
      view2131755434 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755194, "field 'mIvUpdown' and method 'onViewClicked'");
      target.mIvUpdown = finder.castView(view, 2131755194, "field 'mIvUpdown'");
      view2131755194 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvPxry = finder.findRequiredViewAsType(source, 2131755347, "field 'mTvPxry'", TextView.class);
      view = finder.findRequiredView(source, 2131755346, "field 'mLlPxry' and method 'onViewClicked'");
      target.mLlPxry = finder.castView(view, 2131755346, "field 'mLlPxry'");
      view2131755346 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvPxsb = finder.findRequiredViewAsType(source, 2131755349, "field 'mTvPxsb'", TextView.class);
      view = finder.findRequiredView(source, 2131755348, "field 'mLlPxsb' and method 'onViewClicked'");
      target.mLlPxsb = finder.castView(view, 2131755348, "field 'mLlPxsb'");
      view2131755348 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mEtZt = finder.findRequiredViewAsType(source, 2131755350, "field 'mEtZt'", EditText.class);
      target.mEtZjr = finder.findRequiredViewAsType(source, 2131755351, "field 'mEtZjr'", EditText.class);
      target.mEtJlr = finder.findRequiredViewAsType(source, 2131755352, "field 'mEtJlr'", EditText.class);
      target.mTvPxlx = finder.findRequiredViewAsType(source, 2131755354, "field 'mTvPxlx'", TextView.class);
      view = finder.findRequiredView(source, 2131755353, "field 'mLlPxlx' and method 'onViewClicked'");
      target.mLlPxlx = finder.castView(view, 2131755353, "field 'mLlPxlx'");
      view2131755353 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvPxf = finder.findRequiredViewAsType(source, 2131755356, "field 'mTvPxf'", TextView.class);
      view = finder.findRequiredView(source, 2131755355, "field 'mLlPxf' and method 'onViewClicked'");
      target.mLlPxf = finder.castView(view, 2131755355, "field 'mLlPxf'");
      view2131755355 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTextView6 = finder.findRequiredViewAsType(source, 2131755358, "field 'mTextView6'", TextView.class);
      target.mTvPxrq = finder.findRequiredViewAsType(source, 2131755359, "field 'mTvPxrq'", TextView.class);
      view = finder.findRequiredView(source, 2131755357, "field 'mLlPxrq' and method 'onViewClicked'");
      target.mLlPxrq = finder.castView(view, 2131755357, "field 'mLlPxrq'");
      view2131755357 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvPxsc = finder.findRequiredViewAsType(source, 2131755360, "field 'mTvPxsc'", TextView.class);
      view = finder.findRequiredView(source, 2131755314, "field 'mLlTime' and method 'onViewClicked'");
      target.mLlTime = finder.castView(view, 2131755314, "field 'mLlTime'");
      view2131755314 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mEtContent = finder.findRequiredViewAsType(source, 2131755361, "field 'mEtContent'", EditText.class);
      view = finder.findRequiredView(source, 2131755362, "field 'mRlFj' and method 'onViewClicked'");
      target.mRlFj = finder.castView(view, 2131755362, "field 'mRlFj'");
      view2131755362 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755363, "field 'mRlJc' and method 'onViewClicked'");
      target.mRlJc = finder.castView(view, 2131755363, "field 'mRlJc'");
      view2131755363 = view;
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

      view2131755434.setOnClickListener(null);
      view2131755434 = null;
      view2131755194.setOnClickListener(null);
      view2131755194 = null;
      view2131755346.setOnClickListener(null);
      view2131755346 = null;
      view2131755348.setOnClickListener(null);
      view2131755348 = null;
      view2131755353.setOnClickListener(null);
      view2131755353 = null;
      view2131755355.setOnClickListener(null);
      view2131755355 = null;
      view2131755357.setOnClickListener(null);
      view2131755357 = null;
      view2131755314.setOnClickListener(null);
      view2131755314 = null;
      view2131755362.setOnClickListener(null);
      view2131755362 = null;
      view2131755363.setOnClickListener(null);
      view2131755363 = null;
      view2131755263.setOnClickListener(null);
      view2131755263 = null;

      this.target = null;
    }
  }
}
