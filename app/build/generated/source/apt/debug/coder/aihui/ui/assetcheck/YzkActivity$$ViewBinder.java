// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.assetcheck;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import coder.aihui.widget.ScrollViewWithExpandListView;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class YzkActivity$$ViewBinder<T extends YzkActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends YzkActivity> implements Unbinder {
    protected T target;

    private View view2131755456;

    private View view2131755282;

    private View view2131755224;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755249, "field 'mTvTitle'", TextView.class);
      view = finder.findRequiredView(source, 2131755456, "field 'mIvBack' and method 'onViewClicked'");
      target.mIvBack = finder.castView(view, 2131755456, "field 'mIvBack'");
      view2131755456 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvNum = finder.findRequiredViewAsType(source, 2131755387, "field 'mTvNum'", TextView.class);
      view = finder.findRequiredView(source, 2131755282, "field 'mTvOk' and method 'onViewClicked'");
      target.mTvOk = finder.castView(view, 2131755282, "field 'mTvOk'");
      view2131755282 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvChangeDept = finder.findRequiredViewAsType(source, 2131755388, "field 'mTvChangeDept'", TextView.class);
      target.mEl = finder.findRequiredViewAsType(source, 2131755247, "field 'mEl'", ScrollViewWithExpandListView.class);
      view = finder.findRequiredView(source, 2131755224, "field 'mLlDept' and method 'onViewClicked'");
      target.mLlDept = finder.castView(view, 2131755224, "field 'mLlDept'");
      view2131755224 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mTvTitle = null;
      target.mIvBack = null;
      target.mTvNum = null;
      target.mTvOk = null;
      target.mTvChangeDept = null;
      target.mEl = null;
      target.mLlDept = null;

      view2131755456.setOnClickListener(null);
      view2131755456 = null;
      view2131755282.setOnClickListener(null);
      view2131755282 = null;
      view2131755224.setOnClickListener(null);
      view2131755224 = null;

      this.target = null;
    }
  }
}
