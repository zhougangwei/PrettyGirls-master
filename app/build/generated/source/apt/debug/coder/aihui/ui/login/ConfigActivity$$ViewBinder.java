// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.login;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import com.github.lzyzsd.circleprogress.DonutProgress;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ConfigActivity$$ViewBinder<T extends ConfigActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends ConfigActivity> implements Unbinder {
    protected T target;

    private View view2131755292;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755235, "field 'mTvTitle'", TextView.class);
      view = finder.findRequiredView(source, 2131755292, "field 'mBtSure' and method 'onViewClicked'");
      target.mBtSure = finder.castView(view, 2131755292, "field 'mBtSure'");
      view2131755292 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked();
        }
      });
      target.mEtAddress = finder.findRequiredViewAsType(source, 2131755291, "field 'mEtAddress'", EditText.class);
      target.mDonutProgress = finder.findRequiredViewAsType(source, 2131755294, "field 'mDonutProgress'", DonutProgress.class);
      target.mRlProgress = finder.findRequiredViewAsType(source, 2131755293, "field 'mRlProgress'", RelativeLayout.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mTvTitle = null;
      target.mBtSure = null;
      target.mEtAddress = null;
      target.mDonutProgress = null;
      target.mRlProgress = null;

      view2131755292.setOnClickListener(null);
      view2131755292 = null;

      this.target = null;
    }
  }
}
