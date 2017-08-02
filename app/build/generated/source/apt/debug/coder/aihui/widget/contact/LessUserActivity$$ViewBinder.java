// Generated code from Butter Knife. Do not modify!
package coder.aihui.widget.contact;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class LessUserActivity$$ViewBinder<T extends LessUserActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends LessUserActivity> implements Unbinder {
    protected T target;

    private View view2131755414;

    private View view2131755263;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      view = finder.findRequiredView(source, 2131755414, "field 'mIvBack' and method 'onViewClicked'");
      target.mIvBack = finder.castView(view, 2131755414, "field 'mIvBack'");
      view2131755414 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755235, "field 'mTvTitle'", TextView.class);
      target.mRv = finder.findRequiredViewAsType(source, 2131755257, "field 'mRv'", RecyclerView.class);
      view = finder.findRequiredView(source, 2131755263, "field 'mTvOk' and method 'onViewClicked'");
      target.mTvOk = finder.castView(view, 2131755263, "field 'mTvOk'");
      view2131755263 = view;
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

      target.mIvBack = null;
      target.mTvTitle = null;
      target.mRv = null;
      target.mTvOk = null;

      view2131755414.setOnClickListener(null);
      view2131755414 = null;
      view2131755263.setOnClickListener(null);
      view2131755263 = null;

      this.target = null;
    }
  }
}
