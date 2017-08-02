// Generated code from Butter Knife. Do not modify!
package coder.aihui.widget.contact;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class SysUserActivity$$ViewBinder<T extends SysUserActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends SysUserActivity> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.mRv = finder.findRequiredViewAsType(source, 2131755257, "field 'mRv'", RecyclerView.class);
      target.mIndexBar = finder.findRequiredViewAsType(source, 2131755297, "field 'mIndexBar'", IndexBar.class);
      target.mTvSideBarHint = finder.findRequiredViewAsType(source, 2131755298, "field 'mTvSideBarHint'", TextView.class);
      target.mTvOk = finder.findRequiredViewAsType(source, 2131755263, "field 'mTvOk'", TextView.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mRv = null;
      target.mIndexBar = null;
      target.mTvSideBarHint = null;
      target.mTvOk = null;

      this.target = null;
    }
  }
}
