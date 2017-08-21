// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.girl;

import android.support.v7.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class GirlActivity$$ViewBinder<T extends GirlActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends GirlActivity> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.mToolbar = finder.findRequiredViewAsType(source, 2131755291, "field 'mToolbar'", Toolbar.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mToolbar = null;

      this.target = null;
    }
  }
}
