// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.main;

import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class MainActivity$$ViewBinder<T extends MainActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends MainActivity> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755236, "field 'mTvTitle'", TextView.class);
      target.mToolbar = finder.findRequiredViewAsType(source, 2131755296, "field 'mToolbar'", Toolbar.class);
      target.mFlContent = finder.findRequiredViewAsType(source, 2131755338, "field 'mFlContent'", FrameLayout.class);
      target.mBottomNavigationBar = finder.findRequiredViewAsType(source, 2131755339, "field 'mBottomNavigationBar'", BottomNavigationBar.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mTvTitle = null;
      target.mToolbar = null;
      target.mFlContent = null;
      target.mBottomNavigationBar = null;

      this.target = null;
    }
  }
}
