// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.assetcheck;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class AssetQueryConfigActivity$$ViewBinder<T extends AssetQueryConfigActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends AssetQueryConfigActivity> implements Unbinder {
    protected T target;

    private View view2131755434;

    private View view2131755208;

    private View view2131755211;

    private View view2131755214;

    private View view2131755450;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
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
      target.mTvLocation = finder.findRequiredViewAsType(source, 2131755209, "field 'mTvLocation'", TextView.class);
      view = finder.findRequiredView(source, 2131755208, "field 'mLlLocation' and method 'onViewClicked'");
      target.mLlLocation = finder.castView(view, 2131755208, "field 'mLlLocation'");
      view2131755208 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvDept = finder.findRequiredViewAsType(source, 2131755212, "field 'mTvDept'", TextView.class);
      view = finder.findRequiredView(source, 2131755211, "field 'mLlDept' and method 'onViewClicked'");
      target.mLlDept = finder.castView(view, 2131755211, "field 'mLlDept'");
      view2131755211 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvWay = finder.findRequiredViewAsType(source, 2131755215, "field 'mTvWay'", TextView.class);
      view = finder.findRequiredView(source, 2131755214, "field 'mLlWay' and method 'onViewClicked'");
      target.mLlWay = finder.castView(view, 2131755214, "field 'mLlWay'");
      view2131755214 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755450, "field 'mTvSave' and method 'onViewClicked'");
      target.mTvSave = finder.castView(view, 2131755450, "field 'mTvSave'");
      view2131755450 = view;
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
      target.mTvLocation = null;
      target.mLlLocation = null;
      target.mTvDept = null;
      target.mLlDept = null;
      target.mTvWay = null;
      target.mLlWay = null;
      target.mTvSave = null;

      view2131755434.setOnClickListener(null);
      view2131755434 = null;
      view2131755208.setOnClickListener(null);
      view2131755208 = null;
      view2131755211.setOnClickListener(null);
      view2131755211 = null;
      view2131755214.setOnClickListener(null);
      view2131755214 = null;
      view2131755450.setOnClickListener(null);
      view2131755450 = null;

      this.target = null;
    }
  }
}
