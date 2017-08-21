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

    private View view2131755434;

    private View view2131755448;

    private View view2131755452;

    private View view2131755453;

    private View view2131755416;

    private View view2131755317;

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
      view = finder.findRequiredView(source, 2131755448, "field 'mIvPeople' and method 'onViewClicked'");
      target.mIvPeople = finder.castView(view, 2131755448, "field 'mIvPeople'");
      view2131755448 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755452, "field 'mIvScan' and method 'onViewClicked'");
      target.mIvScan = finder.castView(view, 2131755452, "field 'mIvScan'");
      view2131755452 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755453, "field 'mIvSearch' and method 'onViewClicked'");
      target.mIvSearch = finder.castView(view, 2131755453, "field 'mIvSearch'");
      view2131755453 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mSpSearch = finder.findRequiredViewAsType(source, 2131755454, "field 'mSpSearch'", Spinner.class);
      target.mEtSearch = finder.findRequiredViewAsType(source, 2131755298, "field 'mEtSearch'", EditText.class);
      view = finder.findRequiredView(source, 2131755416, "field 'mTvSearch' and method 'onViewClicked'");
      target.mTvSearch = finder.castView(view, 2131755416, "field 'mTvSearch'");
      view2131755416 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTb = finder.findRequiredViewAsType(source, 2131755256, "field 'mTb'", TabLayout.class);
      target.mVp = finder.findRequiredViewAsType(source, 2131755257, "field 'mVp'", ViewPager.class);
      view = finder.findRequiredView(source, 2131755317, "field 'mFabBackToTop' and method 'onViewClicked'");
      target.mFabBackToTop = finder.castView(view, 2131755317, "field 'mFabBackToTop'");
      view2131755317 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755236, "field 'mTvTitle'", TextView.class);
      target.mTvStart = finder.findRequiredViewAsType(source, 2131755315, "field 'mTvStart'", TextView.class);
      target.mTvEnd = finder.findRequiredViewAsType(source, 2131755316, "field 'mTvEnd'", TextView.class);
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

      view2131755434.setOnClickListener(null);
      view2131755434 = null;
      view2131755448.setOnClickListener(null);
      view2131755448 = null;
      view2131755452.setOnClickListener(null);
      view2131755452 = null;
      view2131755453.setOnClickListener(null);
      view2131755453 = null;
      view2131755416.setOnClickListener(null);
      view2131755416 = null;
      view2131755317.setOnClickListener(null);
      view2131755317 = null;

      this.target = null;
    }
  }
}
