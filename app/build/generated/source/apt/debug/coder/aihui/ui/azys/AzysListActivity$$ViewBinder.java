// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.azys;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import coder.aihui.widget.autoview.AutoRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class AzysListActivity$$ViewBinder<T extends AzysListActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends AzysListActivity> implements Unbinder {
    protected T target;

    private View view2131755456;

    private View view2131755307;

    private View view2131755471;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      view = finder.findRequiredView(source, 2131755456, "field 'mIvBack' and method 'onViewClicked'");
      target.mIvBack = finder.castView(view, 2131755456, "field 'mIvBack'");
      view2131755456 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755249, "field 'mTvTitle'", TextView.class);
      target.mTvName = finder.findRequiredViewAsType(source, 2131755458, "field 'mTvName'", TextView.class);
      target.mTvGgxh = finder.findRequiredViewAsType(source, 2131755238, "field 'mTvGgxh'", TextView.class);
      target.mTvDept = finder.findRequiredViewAsType(source, 2131755225, "field 'mTvDept'", TextView.class);
      target.mLlNames = finder.findRequiredViewAsType(source, 2131755459, "field 'mLlNames'", LinearLayout.class);
      target.mRv = finder.findRequiredViewAsType(source, 2131755306, "field 'mRv'", AutoRecyclerView.class);
      view = finder.findRequiredView(source, 2131755307, "field 'mTvYs' and method 'onViewClicked'");
      target.mTvYs = finder.castView(view, 2131755307, "field 'mTvYs'");
      view2131755307 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755471, "field 'mIvPxgl' and method 'onViewClicked'");
      target.mIvPxgl = finder.castView(view, 2131755471, "field 'mIvPxgl'");
      view2131755471 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvRyname = finder.findRequiredViewAsType(source, 2131755460, "field 'mTvRyname'", TextView.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mIvBack = null;
      target.mTvTitle = null;
      target.mTvName = null;
      target.mTvGgxh = null;
      target.mTvDept = null;
      target.mLlNames = null;
      target.mRv = null;
      target.mTvYs = null;
      target.mIvPxgl = null;
      target.mTvRyname = null;

      view2131755456.setOnClickListener(null);
      view2131755456 = null;
      view2131755307.setOnClickListener(null);
      view2131755307 = null;
      view2131755471.setOnClickListener(null);
      view2131755471 = null;

      this.target = null;
    }
  }
}
