// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.main;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class MyFragment$$ViewBinder<T extends MyFragment> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends MyFragment> implements Unbinder {
    protected T target;

    private View view2131755431;

    private View view2131755428;

    private View view2131755429;

    private View view2131755430;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      view = finder.findRequiredView(source, 2131755431, "field 'mLlSet' and method 'onClick'");
      target.mLlSet = finder.castView(view, 2131755431, "field 'mLlSet'");
      view2131755431 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onClick();
        }
      });
      target.mTvSum = finder.findRequiredViewAsType(source, 2131755427, "field 'mTvSum'", TextView.class);
      view = finder.findRequiredView(source, 2131755428, "field 'mRlUse' and method 'onViewClicked'");
      target.mRlUse = finder.castView(view, 2131755428, "field 'mRlUse'");
      view2131755428 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755429, "field 'mRlVersion' and method 'onViewClicked'");
      target.mRlVersion = finder.castView(view, 2131755429, "field 'mRlVersion'");
      view2131755429 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755430, "field 'mTvExit' and method 'onViewClicked'");
      target.mTvExit = finder.castView(view, 2131755430, "field 'mTvExit'");
      view2131755430 = view;
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

      target.mLlSet = null;
      target.mTvSum = null;
      target.mRlUse = null;
      target.mRlVersion = null;
      target.mTvExit = null;

      view2131755431.setOnClickListener(null);
      view2131755431 = null;
      view2131755428.setOnClickListener(null);
      view2131755428 = null;
      view2131755429.setOnClickListener(null);
      view2131755429 = null;
      view2131755430.setOnClickListener(null);
      view2131755430 = null;

      this.target = null;
    }
  }
}
