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

    private View view2131755449;

    private View view2131755450;

    private View view2131755452;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      target.mTvSum = finder.findRequiredViewAsType(source, 2131755446, "field 'mTvSum'", TextView.class);
      view = finder.findRequiredView(source, 2131755449, "field 'mRlUse' and method 'onViewClicked'");
      target.mRlUse = finder.castView(view, 2131755449, "field 'mRlUse'");
      view2131755449 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755450, "field 'mRlVersion' and method 'onViewClicked'");
      target.mRlVersion = finder.castView(view, 2131755450, "field 'mRlVersion'");
      view2131755450 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755452, "field 'mTvExit' and method 'onViewClicked'");
      target.mTvExit = finder.castView(view, 2131755452, "field 'mTvExit'");
      view2131755452 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvCheck = finder.findRequiredViewAsType(source, 2131755447, "field 'mTvCheck'", TextView.class);
      target.mTvUncheck = finder.findRequiredViewAsType(source, 2131755448, "field 'mTvUncheck'", TextView.class);
      target.mTvVersion = finder.findRequiredViewAsType(source, 2131755451, "field 'mTvVersion'", TextView.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mTvSum = null;
      target.mRlUse = null;
      target.mRlVersion = null;
      target.mTvExit = null;
      target.mTvCheck = null;
      target.mTvUncheck = null;
      target.mTvVersion = null;

      view2131755449.setOnClickListener(null);
      view2131755449 = null;
      view2131755450.setOnClickListener(null);
      view2131755450 = null;
      view2131755452.setOnClickListener(null);
      view2131755452 = null;

      this.target = null;
    }
  }
}
