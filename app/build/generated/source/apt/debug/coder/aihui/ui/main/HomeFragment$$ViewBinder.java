// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.main;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class HomeFragment$$ViewBinder<T extends HomeFragment> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends HomeFragment> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.mRv = finder.findRequiredViewAsType(source, 2131755306, "field 'mRv'", RecyclerView.class);
      target.mTvNameHos = finder.findRequiredViewAsType(source, 2131755445, "field 'mTvNameHos'", TextView.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mRv = null;
      target.mTvNameHos = null;

      this.target = null;
    }
  }
}
