// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.assetcheck;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class AssetCheckActivity$$ViewBinder<T extends AssetCheckActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends AssetCheckActivity> implements Unbinder {
    protected T target;

    private View view2131755429;

    private View view2131755430;

    private View view2131755423;

    private View view2131755414;

    private View view2131755195;

    private View view2131755196;

    private View view2131755197;

    private View view2131755198;

    private View view2131755421;

    private View view2131755422;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      target.mLinearLayout = finder.findRequiredViewAsType(source, 2131755204, "field 'mLinearLayout'", LinearLayout.class);
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
      view = finder.findRequiredView(source, 2131755423, "field 'mIvConfig' and method 'onViewClicked'");
      target.mIvConfig = finder.castView(view, 2131755423, "field 'mIvConfig'");
      view2131755423 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
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
      target.mTvDlwz = finder.findRequiredViewAsType(source, 2131755199, "field 'mTvDlwz'", TextView.class);
      target.mRvList = finder.findRequiredViewAsType(source, 2131755205, "field 'mRvList'", RecyclerView.class);
      target.mRbAll = finder.findRequiredViewAsType(source, 2131755420, "field 'mRbAll'", RadioButton.class);
      target.mPb = finder.findRequiredViewAsType(source, 2131755206, "field 'mPb'", ProgressBar.class);
      view = finder.findRequiredView(source, 2131755195, "field 'mTvYd' and method 'onViewClicked'");
      target.mTvYd = finder.castView(view, 2131755195, "field 'mTvYd'");
      view2131755195 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755196, "field 'mTvWd' and method 'onViewClicked'");
      target.mTvWd = finder.castView(view, 2131755196, "field 'mTvWd'");
      view2131755196 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755197, "field 'mTvBd' and method 'onViewClicked'");
      target.mTvBd = finder.castView(view, 2131755197, "field 'mTvBd'");
      view2131755197 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755198, "field 'mTvZk' and method 'onViewClicked'");
      target.mTvZk = finder.castView(view, 2131755198, "field 'mTvZk'");
      view2131755198 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mLinearLayout2 = finder.findRequiredViewAsType(source, 2131755194, "field 'mLinearLayout2'", LinearLayout.class);
      target.mIvDevide = finder.findRequiredViewAsType(source, 2131755203, "field 'mIvDevide'", ImageView.class);
      target.mTvAll = finder.findRequiredViewAsType(source, 2131755200, "field 'mTvAll'", TextView.class);
      target.mTvYqd = finder.findRequiredViewAsType(source, 2131755201, "field 'mTvYqd'", TextView.class);
      target.mTvWqd = finder.findRequiredViewAsType(source, 2131755202, "field 'mTvWqd'", TextView.class);
      view = finder.findRequiredView(source, 2131755421, "field 'mTvYzk' and method 'onViewClicked'");
      target.mTvYzk = finder.castView(view, 2131755421, "field 'mTvYzk'");
      view2131755421 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755422, "field 'mTvLsyd' and method 'onViewClicked'");
      target.mTvLsyd = finder.castView(view, 2131755422, "field 'mTvLsyd'");
      view2131755422 = view;
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

      target.mLinearLayout = null;
      target.mIvScan = null;
      target.mIvSearch = null;
      target.mIvConfig = null;
      target.mIvBack = null;
      target.mTvTitle = null;
      target.mTvDlwz = null;
      target.mRvList = null;
      target.mRbAll = null;
      target.mPb = null;
      target.mTvYd = null;
      target.mTvWd = null;
      target.mTvBd = null;
      target.mTvZk = null;
      target.mLinearLayout2 = null;
      target.mIvDevide = null;
      target.mTvAll = null;
      target.mTvYqd = null;
      target.mTvWqd = null;
      target.mTvYzk = null;
      target.mTvLsyd = null;

      view2131755429.setOnClickListener(null);
      view2131755429 = null;
      view2131755430.setOnClickListener(null);
      view2131755430 = null;
      view2131755423.setOnClickListener(null);
      view2131755423 = null;
      view2131755414.setOnClickListener(null);
      view2131755414 = null;
      view2131755195.setOnClickListener(null);
      view2131755195 = null;
      view2131755196.setOnClickListener(null);
      view2131755196 = null;
      view2131755197.setOnClickListener(null);
      view2131755197 = null;
      view2131755198.setOnClickListener(null);
      view2131755198 = null;
      view2131755421.setOnClickListener(null);
      view2131755421 = null;
      view2131755422.setOnClickListener(null);
      view2131755422 = null;

      this.target = null;
    }
  }
}
