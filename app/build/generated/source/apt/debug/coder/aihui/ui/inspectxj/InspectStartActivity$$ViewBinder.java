// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.inspectxj;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class InspectStartActivity$$ViewBinder<T extends InspectStartActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends InspectStartActivity> implements Unbinder {
    protected T target;

    private View view2131755456;

    private View view2131755470;

    private View view2131755474;

    private View view2131755475;

    private View view2131755435;

    private View view2131755337;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      view = finder.findRequiredView(source, 2131755456, "field 'mIvBack' and method 'onViewClicked'");
      target.mIvBack = finder.castView(view, 2131755456, "field 'mIvBack'");
      view2131755456 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755470, "field 'mIvPeople' and method 'onViewClicked'");
      target.mIvPeople = finder.castView(view, 2131755470, "field 'mIvPeople'");
      view2131755470 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755474, "field 'mIvScan' and method 'onViewClicked'");
      target.mIvScan = finder.castView(view, 2131755474, "field 'mIvScan'");
      view2131755474 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755475, "field 'mIvSearch' and method 'onViewClicked'");
      target.mIvSearch = finder.castView(view, 2131755475, "field 'mIvSearch'");
      view2131755475 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mSpSearch = finder.findRequiredViewAsType(source, 2131755476, "field 'mSpSearch'", Spinner.class);
      target.mEtSearch = finder.findRequiredViewAsType(source, 2131755318, "field 'mEtSearch'", EditText.class);
      view = finder.findRequiredView(source, 2131755435, "field 'mTvSearch' and method 'onViewClicked'");
      target.mTvSearch = finder.castView(view, 2131755435, "field 'mTvSearch'");
      view2131755435 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTb = finder.findRequiredViewAsType(source, 2131755274, "field 'mTb'", TabLayout.class);
      target.mVp = finder.findRequiredViewAsType(source, 2131755276, "field 'mVp'", ViewPager.class);
      view = finder.findRequiredView(source, 2131755337, "field 'mFabBackToTop' and method 'onViewClicked'");
      target.mFabBackToTop = finder.castView(view, 2131755337, "field 'mFabBackToTop'");
      view2131755337 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755249, "field 'mTvTitle'", TextView.class);
      target.mTvStart = finder.findRequiredViewAsType(source, 2131755335, "field 'mTvStart'", TextView.class);
      target.mTvEnd = finder.findRequiredViewAsType(source, 2131755336, "field 'mTvEnd'", TextView.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mIvBack = null;
      target.mIvPeople = null;
      target.mIvScan = null;
      target.mIvSearch = null;
      target.mSpSearch = null;
      target.mEtSearch = null;
      target.mTvSearch = null;
      target.mTb = null;
      target.mVp = null;
      target.mFabBackToTop = null;
      target.mTvTitle = null;
      target.mTvStart = null;
      target.mTvEnd = null;

      view2131755456.setOnClickListener(null);
      view2131755456 = null;
      view2131755470.setOnClickListener(null);
      view2131755470 = null;
      view2131755474.setOnClickListener(null);
      view2131755474 = null;
      view2131755475.setOnClickListener(null);
      view2131755475 = null;
      view2131755435.setOnClickListener(null);
      view2131755435 = null;
      view2131755337.setOnClickListener(null);
      view2131755337 = null;

      this.target = null;
    }
  }
}
