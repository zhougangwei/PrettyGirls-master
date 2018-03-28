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
import coder.aihui.widget.CircleImageView;
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

    private View view2131755456;

    private View view2131755466;

    private View view2131755207;

    private View view2131755477;

    private View view2131755435;

    private View view2131755338;

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
      view = finder.findRequiredView(source, 2131755466, "field 'mIvConfig' and method 'onViewClicked'");
      target.mIvConfig = finder.castView(view, 2131755466, "field 'mIvConfig'");
      view2131755466 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755207, "field 'mIvUpdown' and method 'onViewClicked'");
      target.mIvUpdown = finder.castView(view, 2131755207, "field 'mIvUpdown'");
      view2131755207 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755477, "field 'mIvPz' and method 'onViewClicked'");
      target.mIvPz = finder.castView(view, 2131755477, "field 'mIvPz'");
      view2131755477 = view;
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
      view = finder.findRequiredView(source, 2131755338, "field 'mFabXj' and method 'onViewClicked'");
      target.mFabXj = finder.castView(view, 2131755338, "field 'mFabXj'");
      view2131755338 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755249, "field 'mTvTitle'", TextView.class);
      target.mFabNum = finder.findRequiredViewAsType(source, 2131755548, "field 'mFabNum'", CircleImageView.class);
      target.mTvCurrentNum = finder.findRequiredViewAsType(source, 2131755549, "field 'mTvCurrentNum'", TextView.class);
      target.mTvAllNum = finder.findRequiredViewAsType(source, 2131755550, "field 'mTvAllNum'", TextView.class);
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
      target.mTvTitle = null;
      target.mFabNum = null;
      target.mTvCurrentNum = null;
      target.mTvAllNum = null;

      view2131755456.setOnClickListener(null);
      view2131755456 = null;
      view2131755466.setOnClickListener(null);
      view2131755466 = null;
      view2131755207.setOnClickListener(null);
      view2131755207 = null;
      view2131755477.setOnClickListener(null);
      view2131755477 = null;
      view2131755435.setOnClickListener(null);
      view2131755435 = null;
      view2131755338.setOnClickListener(null);
      view2131755338 = null;

      this.target = null;
    }
  }
}
