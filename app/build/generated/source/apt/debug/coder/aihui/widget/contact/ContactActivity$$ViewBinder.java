// Generated code from Butter Knife. Do not modify!
package coder.aihui.widget.contact;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ContactActivity$$ViewBinder<T extends ContactActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends ContactActivity> implements Unbinder {
    protected T target;

    private View view2131755414;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      target.mRv = finder.findRequiredViewAsType(source, 2131755257, "field 'mRv'", RecyclerView.class);
      target.mIndexBar = finder.findRequiredViewAsType(source, 2131755297, "field 'mIndexBar'", IndexBar.class);
      target.mTvSideBarHint = finder.findRequiredViewAsType(source, 2131755298, "field 'mTvSideBarHint'", TextView.class);
      view = finder.findRequiredView(source, 2131755414, "field 'mIvBack' and method 'onViewClicked'");
      target.mIvBack = finder.castView(view, 2131755414, "field 'mIvBack'");
      view2131755414 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked();
        }
      });
      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755235, "field 'mTvTitle'", TextView.class);
      target.mSearch = finder.findRequiredViewAsType(source, 2131755295, "field 'mSearch'", RelativeLayout.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mRv = null;
      target.mIndexBar = null;
      target.mTvSideBarHint = null;
      target.mIvBack = null;
      target.mTvTitle = null;
      target.mSearch = null;

      view2131755414.setOnClickListener(null);
      view2131755414 = null;

      this.target = null;
    }
  }
}
