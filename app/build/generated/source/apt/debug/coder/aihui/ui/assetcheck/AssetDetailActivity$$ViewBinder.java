// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.assetcheck;

import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import coder.aihui.widget.ScrollViewWithExpandListView;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class AssetDetailActivity$$ViewBinder<T extends AssetDetailActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends AssetDetailActivity> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.mTvKpbh = finder.findRequiredViewAsType(source, 2131755218, "field 'mTvKpbh'", TextView.class);
      target.mLlKpbh = finder.findRequiredViewAsType(source, 2131755217, "field 'mLlKpbh'", LinearLayout.class);
      target.mTvWzmc = finder.findRequiredViewAsType(source, 2131755220, "field 'mTvWzmc'", TextView.class);
      target.mLlWzmc = finder.findRequiredViewAsType(source, 2131755219, "field 'mLlWzmc'", LinearLayout.class);
      target.mTvPpmc = finder.findRequiredViewAsType(source, 2131755222, "field 'mTvPpmc'", TextView.class);
      target.mLlPpmc = finder.findRequiredViewAsType(source, 2131755221, "field 'mLlPpmc'", LinearLayout.class);
      target.mTvGgxh = finder.findRequiredViewAsType(source, 2131755224, "field 'mTvGgxh'", TextView.class);
      target.mLlGgxh = finder.findRequiredViewAsType(source, 2131755223, "field 'mLlGgxh'", LinearLayout.class);
      target.mTvDept = finder.findRequiredViewAsType(source, 2131755211, "field 'mTvDept'", TextView.class);
      target.mLlDept = finder.findRequiredViewAsType(source, 2131755210, "field 'mLlDept'", LinearLayout.class);
      target.mTvLocation = finder.findRequiredViewAsType(source, 2131755208, "field 'mTvLocation'", TextView.class);
      target.mLlLocation = finder.findRequiredViewAsType(source, 2131755207, "field 'mLlLocation'", LinearLayout.class);
      target.mTvBxdw = finder.findRequiredViewAsType(source, 2131755226, "field 'mTvBxdw'", TextView.class);
      target.mLlBxdw = finder.findRequiredViewAsType(source, 2131755225, "field 'mLlBxdw'", LinearLayout.class);
      target.mTvBxjzrq = finder.findRequiredViewAsType(source, 2131755228, "field 'mTvBxjzrq'", TextView.class);
      target.mLlBxjzrq = finder.findRequiredViewAsType(source, 2131755227, "field 'mLlBxjzrq'", LinearLayout.class);
      target.mTvBxlxr = finder.findRequiredViewAsType(source, 2131755230, "field 'mTvBxlxr'", TextView.class);
      target.mTvSynx = finder.findRequiredViewAsType(source, 2131755216, "field 'mTvSynx'", TextView.class);
      target.mLlBxlxr = finder.findRequiredViewAsType(source, 2131755229, "field 'mLlBxlxr'", LinearLayout.class);
      target.mTvCcbh = finder.findRequiredViewAsType(source, 2131755232, "field 'mTvCcbh'", TextView.class);
      target.mLlCcbh = finder.findRequiredViewAsType(source, 2131755231, "field 'mLlCcbh'", LinearLayout.class);
      target.mEl = finder.findRequiredViewAsType(source, 2131755233, "field 'mEl'", ScrollViewWithExpandListView.class);
      target.mSv = finder.findRequiredViewAsType(source, 2131755215, "field 'mSv'", ScrollView.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mTvKpbh = null;
      target.mLlKpbh = null;
      target.mTvWzmc = null;
      target.mLlWzmc = null;
      target.mTvPpmc = null;
      target.mLlPpmc = null;
      target.mTvGgxh = null;
      target.mLlGgxh = null;
      target.mTvDept = null;
      target.mLlDept = null;
      target.mTvLocation = null;
      target.mLlLocation = null;
      target.mTvBxdw = null;
      target.mLlBxdw = null;
      target.mTvBxjzrq = null;
      target.mLlBxjzrq = null;
      target.mTvBxlxr = null;
      target.mTvSynx = null;
      target.mLlBxlxr = null;
      target.mTvCcbh = null;
      target.mLlCcbh = null;
      target.mEl = null;
      target.mSv = null;

      this.target = null;
    }
  }
}
