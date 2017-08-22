// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.inspectxj;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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

    private View view2131755438;

    private View view2131755446;

    private View view2131755447;

    private View view2131755315;

    private View view2131755268;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      view = finder.findRequiredView(source, 2131755438, "field 'mIvBack' and method 'onViewClicked'");
      target.mIvBack = finder.castView(view, 2131755438, "field 'mIvBack'");
      view2131755438 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755236, "field 'mTvTitle'", TextView.class);
      target.mTvName = finder.findRequiredViewAsType(source, 2131755440, "field 'mTvName'", TextView.class);
      target.mTvKpbh = finder.findRequiredViewAsType(source, 2131755225, "field 'mTvKpbh'", TextView.class);
      target.mTvType = finder.findRequiredViewAsType(source, 2131755212, "field 'mTvType'", TextView.class);
      target.mLlNames = finder.findRequiredViewAsType(source, 2131755441, "field 'mLlNames'", LinearLayout.class);
      target.mTvDlwz = finder.findRequiredViewAsType(source, 2131755442, "field 'mTvDlwz'", TextView.class);
      target.mTvPlanTime = finder.findRequiredViewAsType(source, 2131755316, "field 'mTvPlanTime'", TextView.class);
      target.mTvCurrentTime = finder.findRequiredViewAsType(source, 2131755317, "field 'mTvCurrentTime'", TextView.class);
      target.mRbAll = finder.findRequiredViewAsType(source, 2131755445, "field 'mRbAll'", RadioButton.class);
      view = finder.findRequiredView(source, 2131755446, "field 'mTvMr' and method 'onViewClicked'");
      target.mTvMr = finder.castView(view, 2131755446, "field 'mTvMr'");
      view2131755446 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755447, "field 'mTvZc' and method 'onViewClicked'");
      target.mTvZc = finder.castView(view, 2131755447, "field 'mTvZc'");
      view2131755447 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mLinearLayout = finder.findRequiredViewAsType(source, 2131755205, "field 'mLinearLayout'", LinearLayout.class);
      view = finder.findRequiredView(source, 2131755315, "field 'mTvAddPic' and method 'onViewClicked'");
      target.mTvAddPic = finder.castView(view, 2131755315, "field 'mTvAddPic'");
      view2131755315 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755268, "field 'mTvOk' and method 'onViewClicked'");
      target.mTvOk = finder.castView(view, 2131755268, "field 'mTvOk'");
      view2131755268 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mRv = finder.findRequiredViewAsType(source, 2131755292, "field 'mRv'", RecyclerView.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mIvBack = null;
      target.mTvTitle = null;
      target.mTvName = null;
      target.mTvKpbh = null;
      target.mTvType = null;
      target.mLlNames = null;
      target.mTvDlwz = null;
      target.mTvPlanTime = null;
      target.mTvCurrentTime = null;
      target.mRbAll = null;
      target.mTvMr = null;
      target.mTvZc = null;
      target.mLinearLayout = null;
      target.mTvAddPic = null;
      target.mTvOk = null;
      target.mRv = null;

      view2131755438.setOnClickListener(null);
      view2131755438 = null;
      view2131755446.setOnClickListener(null);
      view2131755446 = null;
      view2131755447.setOnClickListener(null);
      view2131755447 = null;
      view2131755315.setOnClickListener(null);
      view2131755315 = null;
      view2131755268.setOnClickListener(null);
      view2131755268 = null;

      this.target = null;
    }
  }
}
