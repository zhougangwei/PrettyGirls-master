// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.inspectxj;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

    private View view2131755414;

    private View view2131755426;

    private View view2131755429;

    private View view2131755430;

    private View view2131755397;

    private View view2131755306;

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
      view = finder.findRequiredView(source, 2131755426, "field 'mIvPeople' and method 'onViewClicked'");
      target.mIvPeople = finder.castView(view, 2131755426, "field 'mIvPeople'");
      view2131755426 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755429, "field 'mIvScan' and method 'onViewClicked'");
      target.mIvScan = finder.castView(view, 2131755429, "field 'mIvScan'");
      view2131755429 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755430, "field 'mIvSearch' and method 'onViewClicked'");
      target.mIvSearch = finder.castView(view, 2131755430, "field 'mIvSearch'");
      view2131755430 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mSpSearch = finder.findRequiredViewAsType(source, 2131755431, "field 'mSpSearch'", Spinner.class);
      target.mEtSearch = finder.findRequiredViewAsType(source, 2131755296, "field 'mEtSearch'", EditText.class);
      view = finder.findRequiredView(source, 2131755397, "field 'mTvSearch' and method 'onViewClicked'");
      target.mTvSearch = finder.castView(view, 2131755397, "field 'mTvSearch'");
      view2131755397 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTb = finder.findRequiredViewAsType(source, 2131755255, "field 'mTb'", TabLayout.class);
      target.mVp = finder.findRequiredViewAsType(source, 2131755256, "field 'mVp'", ViewPager.class);
      target.mLine = finder.findRequiredViewAsType(source, 2131755258, "field 'mLine'", LinearLayout.class);
      view = finder.findRequiredView(source, 2131755306, "field 'mFabBackToTop' and method 'onViewClicked'");
      target.mFabBackToTop = finder.castView(view, 2131755306, "field 'mFabBackToTop'");
      view2131755306 = view;
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
      target.mIvPeople = null;
      target.mIvScan = null;
      target.mIvSearch = null;
      target.mSpSearch = null;
      target.mEtSearch = null;
      target.mTvSearch = null;
      target.mTb = null;
      target.mVp = null;
      target.mLine = null;
      target.mFabBackToTop = null;

      view2131755414.setOnClickListener(null);
      view2131755414 = null;
      view2131755426.setOnClickListener(null);
      view2131755426 = null;
      view2131755429.setOnClickListener(null);
      view2131755429 = null;
      view2131755430.setOnClickListener(null);
      view2131755430 = null;
      view2131755397.setOnClickListener(null);
      view2131755397 = null;
      view2131755306.setOnClickListener(null);
      view2131755306 = null;

      this.target = null;
    }
  }
}
