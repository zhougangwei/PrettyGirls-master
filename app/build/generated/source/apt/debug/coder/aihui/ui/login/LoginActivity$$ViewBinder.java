// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.login;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class LoginActivity$$ViewBinder<T extends LoginActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends LoginActivity> implements Unbinder {
    protected T target;

    private View view2131755313;

    private View view2131755314;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      target.mEtUser = finder.findRequiredViewAsType(source, 2131755310, "field 'mEtUser'", EditText.class);
      target.mEtPassword = finder.findRequiredViewAsType(source, 2131755311, "field 'mEtPassword'", EditText.class);
      view = finder.findRequiredView(source, 2131755313, "field 'mTvConfig' and method 'onViewClicked'");
      target.mTvConfig = finder.castView(view, 2131755313, "field 'mTvConfig'");
      view2131755313 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755314, "field 'mButton' and method 'onViewClicked'");
      target.mButton = finder.castView(view, 2131755314, "field 'mButton'");
      view2131755314 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mProgressView = finder.findRequiredViewAsType(source, 2131755184, "field 'mProgressView'", ProgressBar.class);
      target.mLoginFormView = finder.findRequiredViewAsType(source, 2131755185, "field 'mLoginFormView'", LinearLayout.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mEtUser = null;
      target.mEtPassword = null;
      target.mTvConfig = null;
      target.mButton = null;
      target.mProgressView = null;
      target.mLoginFormView = null;

      view2131755313.setOnClickListener(null);
      view2131755313 = null;
      view2131755314.setOnClickListener(null);
      view2131755314 = null;

      this.target = null;
    }
  }
}
