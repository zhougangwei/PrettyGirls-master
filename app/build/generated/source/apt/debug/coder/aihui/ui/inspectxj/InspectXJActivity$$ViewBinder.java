// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.inspectxj;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class InspectXJActivity$$ViewBinder<T extends InspectXJActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends InspectXJActivity> implements Unbinder {
    protected T target;

    private View view2131755422;

    private View view2131755431;

    private View view2131755441;

    private View view2131755440;

    private View view2131755405;

    private View view2131755309;

    private View view2131755310;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      view = finder.findRequiredView(source, 2131755422, "field 'mIvBack' and method 'onViewClicked'");
      target.mIvBack = finder.castView(view, 2131755422, "field 'mIvBack'");
      view2131755422 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755431, "field 'mIvConfig' and method 'onViewClicked'");
      target.mIvConfig = finder.castView(view, 2131755431, "field 'mIvConfig'");
      view2131755431 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755441, "field 'mIvUpdown' and method 'onViewClicked'");
      target.mIvUpdown = finder.castView(view, 2131755441, "field 'mIvUpdown'");
      view2131755441 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755440, "field 'mIvPz' and method 'onViewClicked'");
      target.mIvPz = finder.castView(view, 2131755440, "field 'mIvPz'");
      view2131755440 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mSpSearch = finder.findRequiredViewAsType(source, 2131755439, "field 'mSpSearch'", Spinner.class);
      target.mEtSearch = finder.findRequiredViewAsType(source, 2131755296, "field 'mEtSearch'", EditText.class);
      view = finder.findRequiredView(source, 2131755405, "field 'mTvSearch' and method 'onViewClicked'");
      target.mTvSearch = finder.castView(view, 2131755405, "field 'mTvSearch'");
      view2131755405 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTb = finder.findRequiredViewAsType(source, 2131755255, "field 'mTb'", TabLayout.class);
      target.mVp = finder.findRequiredViewAsType(source, 2131755256, "field 'mVp'", ViewPager.class);
      view = finder.findRequiredView(source, 2131755309, "field 'mFabXj' and method 'onViewClicked'");
      target.mFabXj = finder.castView(view, 2131755309, "field 'mFabXj'");
      view2131755309 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755310, "field 'mFabNum' and method 'onViewClicked'");
      target.mFabNum = finder.castView(view, 2131755310, "field 'mFabNum'");
      view2131755310 = view;
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
      target.mIvConfig = null;
      target.mIvUpdown = null;
      target.mIvPz = null;
      target.mSpSearch = null;
      target.mEtSearch = null;
      target.mTvSearch = null;
      target.mTb = null;
      target.mVp = null;
      target.mFabXj = null;
      target.mFabNum = null;

      view2131755422.setOnClickListener(null);
      view2131755422 = null;
      view2131755431.setOnClickListener(null);
      view2131755431 = null;
      view2131755441.setOnClickListener(null);
      view2131755441 = null;
      view2131755440.setOnClickListener(null);
      view2131755440 = null;
      view2131755405.setOnClickListener(null);
      view2131755405 = null;
      view2131755309.setOnClickListener(null);
      view2131755309 = null;
      view2131755310.setOnClickListener(null);
      view2131755310 = null;

      this.target = null;
    }
  }
}
