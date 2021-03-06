// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.assetquery;

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

public class AssetQueryActivity$$ViewBinder<T extends AssetQueryActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends AssetQueryActivity> implements Unbinder {
    protected T target;

    private View view2131755474;

    private View view2131755475;

    private View view2131755466;

    private View view2131755456;

    private View view2131755435;

    private View view2131755462;

    private View view2131755275;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
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
      view = finder.findRequiredView(source, 2131755466, "field 'mIvConfig' and method 'onViewClicked'");
      target.mIvConfig = finder.castView(view, 2131755466, "field 'mIvConfig'");
      view2131755466 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755456, "field 'mIvBack' and method 'onViewClicked'");
      target.mIvBack = finder.castView(view, 2131755456, "field 'mIvBack'");
      view2131755456 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755249, "field 'mTvTitle'", TextView.class);
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
      view = finder.findRequiredView(source, 2131755462, "field 'mBackTop' and method 'onViewClicked'");
      target.mBackTop = finder.castView(view, 2131755462, "field 'mBackTop'");
      view2131755462 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755275, "method 'onViewClicked'");
      view2131755275 = view;
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

      target.mIvScan = null;
      target.mIvSearch = null;
      target.mIvConfig = null;
      target.mIvBack = null;
      target.mTvTitle = null;
      target.mSpSearch = null;
      target.mEtSearch = null;
      target.mTvSearch = null;
      target.mTb = null;
      target.mVp = null;
      target.mBackTop = null;

      view2131755474.setOnClickListener(null);
      view2131755474 = null;
      view2131755475.setOnClickListener(null);
      view2131755475 = null;
      view2131755466.setOnClickListener(null);
      view2131755466 = null;
      view2131755456.setOnClickListener(null);
      view2131755456 = null;
      view2131755435.setOnClickListener(null);
      view2131755435 = null;
      view2131755462.setOnClickListener(null);
      view2131755462 = null;
      view2131755275.setOnClickListener(null);
      view2131755275 = null;

      this.target = null;
    }
  }
}
