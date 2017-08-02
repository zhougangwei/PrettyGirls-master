// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.inspectxj;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class InspectDetailActivity$$ViewBinder<T extends InspectDetailActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends InspectDetailActivity> implements Unbinder {
    protected T target;

    private View view2131755414;

    private View view2131755420;

    private View view2131755421;

    private View view2131755422;

    private View view2131755302;

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
      target.mTvName = finder.findRequiredViewAsType(source, 2131755416, "field 'mTvName'", TextView.class);
      target.mTvGgxh = finder.findRequiredViewAsType(source, 2131755224, "field 'mTvGgxh'", TextView.class);
      target.mTvDept = finder.findRequiredViewAsType(source, 2131755211, "field 'mTvDept'", TextView.class);
      target.mLlNames = finder.findRequiredViewAsType(source, 2131755417, "field 'mLlNames'", LinearLayout.class);
      target.mTvPlanTime = finder.findRequiredViewAsType(source, 2131755303, "field 'mTvPlanTime'", TextView.class);
      target.mTvCurrentTime = finder.findRequiredViewAsType(source, 2131755304, "field 'mTvCurrentTime'", TextView.class);
      view = finder.findRequiredView(source, 2131755420, "field 'mRbAll' and method 'onViewClicked'");
      target.mRbAll = finder.castView(view, 2131755420, "field 'mRbAll'");
      view2131755420 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755421, "field 'mTvMr' and method 'onViewClicked'");
      target.mTvMr = finder.castView(view, 2131755421, "field 'mTvMr'");
      view2131755421 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755422, "field 'mTvZc' and method 'onViewClicked'");
      target.mTvZc = finder.castView(view, 2131755422, "field 'mTvZc'");
      view2131755422 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mLinearLayout = finder.findRequiredViewAsType(source, 2131755204, "field 'mLinearLayout'", LinearLayout.class);
      view = finder.findRequiredView(source, 2131755302, "field 'mTvAddPic' and method 'onViewClicked'");
      target.mTvAddPic = finder.castView(view, 2131755302, "field 'mTvAddPic'");
      view2131755302 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755263, "field 'mTvOk' and method 'onViewClicked'");
      target.mTvOk = finder.castView(view, 2131755263, "field 'mTvOk'");
      view2131755263 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mRv = finder.findRequiredViewAsType(source, 2131755257, "field 'mRv'", RecyclerView.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mIvBack = null;
      target.mTvTitle = null;
      target.mTvName = null;
      target.mTvGgxh = null;
      target.mTvDept = null;
      target.mLlNames = null;
      target.mTvPlanTime = null;
      target.mTvCurrentTime = null;
      target.mRbAll = null;
      target.mTvMr = null;
      target.mTvZc = null;
      target.mLinearLayout = null;
      target.mTvAddPic = null;
      target.mTvOk = null;
      target.mRv = null;

      view2131755414.setOnClickListener(null);
      view2131755414 = null;
      view2131755420.setOnClickListener(null);
      view2131755420 = null;
      view2131755421.setOnClickListener(null);
      view2131755421 = null;
      view2131755422.setOnClickListener(null);
      view2131755422 = null;
      view2131755302.setOnClickListener(null);
      view2131755302 = null;
      view2131755263.setOnClickListener(null);
      view2131755263 = null;

      this.target = null;
    }
  }
}
