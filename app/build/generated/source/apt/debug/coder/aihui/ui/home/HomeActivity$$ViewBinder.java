// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.home;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class HomeActivity$$ViewBinder<T extends HomeActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends HomeActivity> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.mToolbar = finder.findRequiredViewAsType(source, 2131755296, "field 'mToolbar'", Toolbar.class);
      target.mFab = finder.findRequiredViewAsType(source, 2131755308, "field 'mFab'", FloatingActionButton.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mToolbar = null;
      target.mFab = null;

      this.target = null;
    }
  }
}
