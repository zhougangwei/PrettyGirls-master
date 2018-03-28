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

    private View view2131755474;

    private View view2131755475;

    private View view2131755466;

    private View view2131755207;

    private View view2131755456;

    private View view2131755209;

    private View view2131755210;

    private View view2131755211;

    private View view2131755212;

    private View view2131755464;

    private View view2131755465;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      target.mLinearLayout = finder.findRequiredViewAsType(source, 2131755218, "field 'mLinearLayout'", LinearLayout.class);
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
      view = finder.findRequiredView(source, 2131755207, "field 'mIvUpDown' and method 'onViewClicked'");
      target.mIvUpDown = finder.castView(view, 2131755207, "field 'mIvUpDown'");
      view2131755207 = view;
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
      target.mTvDlwz = finder.findRequiredViewAsType(source, 2131755213, "field 'mTvDlwz'", TextView.class);
      target.mRvList = finder.findRequiredViewAsType(source, 2131755219, "field 'mRvList'", RecyclerView.class);
      target.mRbAll = finder.findRequiredViewAsType(source, 2131755463, "field 'mRbAll'", RadioButton.class);
      target.mPb = finder.findRequiredViewAsType(source, 2131755220, "field 'mPb'", ProgressBar.class);
      view = finder.findRequiredView(source, 2131755209, "field 'mTvYd' and method 'onViewClicked'");
      target.mTvYd = finder.castView(view, 2131755209, "field 'mTvYd'");
      view2131755209 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755210, "field 'mTvWd' and method 'onViewClicked'");
      target.mTvWd = finder.castView(view, 2131755210, "field 'mTvWd'");
      view2131755210 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755211, "field 'mTvBd' and method 'onViewClicked'");
      target.mTvBd = finder.castView(view, 2131755211, "field 'mTvBd'");
      view2131755211 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755212, "field 'mTvZk' and method 'onViewClicked'");
      target.mTvZk = finder.castView(view, 2131755212, "field 'mTvZk'");
      view2131755212 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mLinearLayout2 = finder.findRequiredViewAsType(source, 2131755208, "field 'mLinearLayout2'", LinearLayout.class);
      target.mIvDevide = finder.findRequiredViewAsType(source, 2131755217, "field 'mIvDevide'", ImageView.class);
      target.mTvAll = finder.findRequiredViewAsType(source, 2131755214, "field 'mTvAll'", TextView.class);
      target.mTvYqd = finder.findRequiredViewAsType(source, 2131755215, "field 'mTvYqd'", TextView.class);
      target.mTvWqd = finder.findRequiredViewAsType(source, 2131755216, "field 'mTvWqd'", TextView.class);
      view = finder.findRequiredView(source, 2131755464, "field 'mTvYzk' and method 'onViewClicked'");
      target.mTvYzk = finder.castView(view, 2131755464, "field 'mTvYzk'");
      view2131755464 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755465, "field 'mTvLsyd' and method 'onViewClicked'");
      target.mTvLsyd = finder.castView(view, 2131755465, "field 'mTvLsyd'");
      view2131755465 = view;
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

      view2131755474.setOnClickListener(null);
      view2131755474 = null;
      view2131755475.setOnClickListener(null);
      view2131755475 = null;
      view2131755466.setOnClickListener(null);
      view2131755466 = null;
      view2131755207.setOnClickListener(null);
      view2131755207 = null;
      view2131755456.setOnClickListener(null);
      view2131755456 = null;
      view2131755209.setOnClickListener(null);
      view2131755209 = null;
      view2131755210.setOnClickListener(null);
      view2131755210 = null;
      view2131755211.setOnClickListener(null);
      view2131755211 = null;
      view2131755212.setOnClickListener(null);
      view2131755212 = null;
      view2131755464.setOnClickListener(null);
      view2131755464 = null;
      view2131755465.setOnClickListener(null);
      view2131755465 = null;

      this.target = null;
    }
  }
}
