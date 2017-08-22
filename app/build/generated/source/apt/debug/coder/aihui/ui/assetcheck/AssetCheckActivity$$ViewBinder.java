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

    private View view2131755456;

    private View view2131755457;

    private View view2131755448;

    private View view2131755194;

    private View view2131755438;

    private View view2131755196;

    private View view2131755197;

    private View view2131755198;

    private View view2131755199;

    private View view2131755446;

    private View view2131755447;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      target.mLinearLayout = finder.findRequiredViewAsType(source, 2131755205, "field 'mLinearLayout'", LinearLayout.class);
      view = finder.findRequiredView(source, 2131755456, "field 'mIvScan' and method 'onViewClicked'");
      target.mIvScan = finder.castView(view, 2131755456, "field 'mIvScan'");
      view2131755456 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755457, "field 'mIvSearch' and method 'onViewClicked'");
      target.mIvSearch = finder.castView(view, 2131755457, "field 'mIvSearch'");
      view2131755457 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755448, "field 'mIvConfig' and method 'onViewClicked'");
      target.mIvConfig = finder.castView(view, 2131755448, "field 'mIvConfig'");
      view2131755448 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755194, "field 'mIvUpDown' and method 'onViewClicked'");
      target.mIvUpDown = finder.castView(view, 2131755194, "field 'mIvUpDown'");
      view2131755194 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
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
      target.mTvDlwz = finder.findRequiredViewAsType(source, 2131755200, "field 'mTvDlwz'", TextView.class);
      target.mRvList = finder.findRequiredViewAsType(source, 2131755206, "field 'mRvList'", RecyclerView.class);
      target.mRbAll = finder.findRequiredViewAsType(source, 2131755445, "field 'mRbAll'", RadioButton.class);
      target.mPb = finder.findRequiredViewAsType(source, 2131755207, "field 'mPb'", ProgressBar.class);
      view = finder.findRequiredView(source, 2131755196, "field 'mTvYd' and method 'onViewClicked'");
      target.mTvYd = finder.castView(view, 2131755196, "field 'mTvYd'");
      view2131755196 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755197, "field 'mTvWd' and method 'onViewClicked'");
      target.mTvWd = finder.castView(view, 2131755197, "field 'mTvWd'");
      view2131755197 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755198, "field 'mTvBd' and method 'onViewClicked'");
      target.mTvBd = finder.castView(view, 2131755198, "field 'mTvBd'");
      view2131755198 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755199, "field 'mTvZk' and method 'onViewClicked'");
      target.mTvZk = finder.castView(view, 2131755199, "field 'mTvZk'");
      view2131755199 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mLinearLayout2 = finder.findRequiredViewAsType(source, 2131755195, "field 'mLinearLayout2'", LinearLayout.class);
      target.mIvDevide = finder.findRequiredViewAsType(source, 2131755204, "field 'mIvDevide'", ImageView.class);
      target.mTvAll = finder.findRequiredViewAsType(source, 2131755201, "field 'mTvAll'", TextView.class);
      target.mTvYqd = finder.findRequiredViewAsType(source, 2131755202, "field 'mTvYqd'", TextView.class);
      target.mTvWqd = finder.findRequiredViewAsType(source, 2131755203, "field 'mTvWqd'", TextView.class);
      view = finder.findRequiredView(source, 2131755446, "field 'mTvYzk' and method 'onViewClicked'");
      target.mTvYzk = finder.castView(view, 2131755446, "field 'mTvYzk'");
      view2131755446 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755447, "field 'mTvLsyd' and method 'onViewClicked'");
      target.mTvLsyd = finder.castView(view, 2131755447, "field 'mTvLsyd'");
      view2131755447 = view;
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
      target.mIvUpDown = null;
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

      view2131755456.setOnClickListener(null);
      view2131755456 = null;
      view2131755457.setOnClickListener(null);
      view2131755457 = null;
      view2131755448.setOnClickListener(null);
      view2131755448 = null;
      view2131755194.setOnClickListener(null);
      view2131755194 = null;
      view2131755438.setOnClickListener(null);
      view2131755438 = null;
      view2131755196.setOnClickListener(null);
      view2131755196 = null;
      view2131755197.setOnClickListener(null);
      view2131755197 = null;
      view2131755198.setOnClickListener(null);
      view2131755198 = null;
      view2131755199.setOnClickListener(null);
      view2131755199 = null;
      view2131755446.setOnClickListener(null);
      view2131755446 = null;
      view2131755447.setOnClickListener(null);
      view2131755447 = null;

      this.target = null;
    }
  }
}
