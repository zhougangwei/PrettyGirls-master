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

    private View view2131755419;

    private View view2131755416;

    private View view2131755417;

    private View view2131755418;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      view = finder.findRequiredView(source, 2131755419, "field 'mLlSet' and method 'onClick'");
      target.mLlSet = finder.castView(view, 2131755419, "field 'mLlSet'");
      view2131755419 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onClick();
        }
      });
      target.mTvSum = finder.findRequiredViewAsType(source, 2131755415, "field 'mTvSum'", TextView.class);
      view = finder.findRequiredView(source, 2131755416, "field 'mRlUse' and method 'onViewClicked'");
      target.mRlUse = finder.castView(view, 2131755416, "field 'mRlUse'");
      view2131755416 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755417, "field 'mRlVersion' and method 'onViewClicked'");
      target.mRlVersion = finder.castView(view, 2131755417, "field 'mRlVersion'");
      view2131755417 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755418, "field 'mTvExit' and method 'onViewClicked'");
      target.mTvExit = finder.castView(view, 2131755418, "field 'mTvExit'");
      view2131755418 = view;
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

      view2131755419.setOnClickListener(null);
      view2131755419 = null;
      view2131755416.setOnClickListener(null);
      view2131755416 = null;
      view2131755417.setOnClickListener(null);
      view2131755417 = null;
      view2131755418.setOnClickListener(null);
      view2131755418 = null;

      this.target = null;
    }
  }
}
