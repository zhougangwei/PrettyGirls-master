// Generated code from Butter Knife. Do not modify!
package coder.aihui.widget.contact;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
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

public class SysUserActivity$$ViewBinder<T extends SysUserActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends SysUserActivity> implements Unbinder {
    protected T target;

    private View view2131755434;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      target.mRv = finder.findRequiredViewAsType(source, 2131755287, "field 'mRv'", RecyclerView.class);
      target.mIndexBar = finder.findRequiredViewAsType(source, 2131755299, "field 'mIndexBar'", IndexBar.class);
      target.mTvSideBarHint = finder.findRequiredViewAsType(source, 2131755300, "field 'mTvSideBarHint'", TextView.class);
      target.mTvOk = finder.findRequiredViewAsType(source, 2131755263, "field 'mTvOk'", TextView.class);
      view = finder.findRequiredView(source, 2131755434, "field 'mIvBack' and method 'onViewClicked'");
      target.mIvBack = finder.castView(view, 2131755434, "field 'mIvBack'");
      view2131755434 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755236, "field 'mTvTitle'", TextView.class);
      target.mEtSearch = finder.findRequiredViewAsType(source, 2131755298, "field 'mEtSearch'", EditText.class);
      target.mSearch = finder.findRequiredViewAsType(source, 2131755297, "field 'mSearch'", RelativeLayout.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mRv = null;
      target.mIndexBar = null;
      target.mTvSideBarHint = null;
      target.mTvOk = null;
      target.mIvBack = null;
      target.mTvTitle = null;
      target.mEtSearch = null;
      target.mSearch = null;

      view2131755434.setOnClickListener(null);
      view2131755434 = null;

      this.target = null;
    }
  }
}
