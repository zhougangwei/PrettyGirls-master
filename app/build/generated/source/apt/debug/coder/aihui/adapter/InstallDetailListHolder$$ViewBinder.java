// Generated code from Butter Knife. Do not modify!
package coder.aihui.adapter;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class InstallDetailListHolder$$ViewBinder<T extends InstallDetailListHolder> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends InstallDetailListHolder> implements Unbinder {
    protected T target;

    private View view2131755495;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      target.mTvName = finder.findRequiredViewAsType(source, 2131755458, "field 'mTvName'", TextView.class);
      view = finder.findRequiredView(source, 2131755495, "field 'mLlChoose' and method 'onViewClicked'");
      target.mLlChoose = finder.castView(view, 2131755495, "field 'mLlChoose'");
      view2131755495 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked();
        }
      });
      target.mTvResult = finder.findRequiredViewAsType(source, 2131755496, "field 'mTvResult'", TextView.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mTvName = null;
      target.mLlChoose = null;
      target.mTvResult = null;

      view2131755495.setOnClickListener(null);
      view2131755495 = null;

      this.target = null;
    }
  }
}
