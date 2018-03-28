// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.login;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
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

    private View view2131755349;

    private View view2131755350;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      target.mEtUser = finder.findRequiredViewAsType(source, 2131755347, "field 'mEtUser'", EditText.class);
      target.mEtPassword = finder.findRequiredViewAsType(source, 2131755348, "field 'mEtPassword'", EditText.class);
      view = finder.findRequiredView(source, 2131755349, "field 'mTvConfig' and method 'onViewClicked'");
      target.mTvConfig = finder.castView(view, 2131755349, "field 'mTvConfig'");
      view2131755349 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvHos = finder.findRequiredViewAsType(source, 2131755352, "field 'mTvHos'", TextView.class);
      view = finder.findRequiredView(source, 2131755350, "field 'mButton' and method 'onViewClicked'");
      target.mButton = finder.castView(view, 2131755350, "field 'mButton'");
      view2131755350 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mProgressView = finder.findRequiredViewAsType(source, 2131755200, "field 'mProgressView'", ProgressBar.class);
      target.mLoginFormView = finder.findRequiredViewAsType(source, 2131755201, "field 'mLoginFormView'", RelativeLayout.class);
      target.mRb = finder.findRequiredViewAsType(source, 2131755313, "field 'mRb'", RadioButton.class);
      target.mCircleImageView = finder.findRequiredViewAsType(source, 2131755351, "field 'mCircleImageView'", ImageView.class);
      target.mLlContent = finder.findRequiredViewAsType(source, 2131755345, "field 'mLlContent'", LinearLayout.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mEtUser = null;
      target.mEtPassword = null;
      target.mTvConfig = null;
      target.mTvHos = null;
      target.mButton = null;
      target.mProgressView = null;
      target.mLoginFormView = null;
      target.mRb = null;
      target.mCircleImageView = null;
      target.mLlContent = null;

      view2131755349.setOnClickListener(null);
      view2131755349 = null;
      view2131755350.setOnClickListener(null);
      view2131755350 = null;

      this.target = null;
    }
  }
}
