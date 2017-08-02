// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.setting;

import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class SettingActivity$$ViewBinder<T extends SettingActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends SettingActivity> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.mIvBack = finder.findRequiredViewAsType(source, 2131755414, "field 'mIvBack'", ImageView.class);
      target.mTb = finder.findRequiredViewAsType(source, 2131755255, "field 'mTb'", LinearLayout.class);
      target.mLlSet = finder.findRequiredViewAsType(source, 2131755412, "field 'mLlSet'", LinearLayout.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mIvBack = null;
      target.mTb = null;
      target.mLlSet = null;

      this.target = null;
    }
  }
}
