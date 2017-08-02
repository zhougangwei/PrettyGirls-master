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

public class AssetDetailEditActivity$$ViewBinder<T extends AssetDetailEditActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends AssetDetailEditActivity> implements Unbinder {
    protected T target;

    private View view2131755414;

    private View view2131755238;

    private View view2131755207;

    private View view2131755240;

    private View view2131755242;

    private View view2131755244;

    private View view2131755246;

    private View view2131755248;

    private View view2131755250;

    private View view2131755428;

    private View view2131755252;

    private View view2131755253;

    private View view2131755254;

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
      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755235, "field 'mTvTitle'", TextView.class);
      target.mTvWzmc = finder.findRequiredViewAsType(source, 2131755220, "field 'mTvWzmc'", TextView.class);
      target.mTvBrand = finder.findRequiredViewAsType(source, 2131755236, "field 'mTvBrand'", TextView.class);
      target.mTvGgxh = finder.findRequiredViewAsType(source, 2131755224, "field 'mTvGgxh'", TextView.class);
      target.mTvScbh = finder.findRequiredViewAsType(source, 2131755237, "field 'mTvScbh'", TextView.class);
      target.mTvScrq = finder.findRequiredViewAsType(source, 2131755239, "field 'mTvScrq'", TextView.class);
      view = finder.findRequiredView(source, 2131755238, "field 'mLlScrq' and method 'onViewClicked'");
      target.mLlScrq = finder.castView(view, 2131755238, "field 'mLlScrq'");
      view2131755238 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvLocation = finder.findRequiredViewAsType(source, 2131755208, "field 'mTvLocation'", TextView.class);
      view = finder.findRequiredView(source, 2131755207, "field 'mLlLocation' and method 'onViewClicked'");
      target.mLlLocation = finder.castView(view, 2131755207, "field 'mLlLocation'");
      view2131755207 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvGhdw = finder.findRequiredViewAsType(source, 2131755241, "field 'mTvGhdw'", TextView.class);
      view = finder.findRequiredView(source, 2131755240, "field 'mLlGhdw' and method 'onViewClicked'");
      target.mLlGhdw = finder.castView(view, 2131755240, "field 'mLlGhdw'");
      view2131755240 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvSccj = finder.findRequiredViewAsType(source, 2131755243, "field 'mTvSccj'", TextView.class);
      view = finder.findRequiredView(source, 2131755242, "field 'mLlSccj' and method 'onViewClicked'");
      target.mLlSccj = finder.castView(view, 2131755242, "field 'mLlSccj'");
      view2131755242 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvBgks = finder.findRequiredViewAsType(source, 2131755245, "field 'mTvBgks'", TextView.class);
      view = finder.findRequiredView(source, 2131755244, "field 'mLlBgks' and method 'onViewClicked'");
      target.mLlBgks = finder.castView(view, 2131755244, "field 'mLlBgks'");
      view2131755244 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvBgr = finder.findRequiredViewAsType(source, 2131755247, "field 'mTvBgr'", TextView.class);
      view = finder.findRequiredView(source, 2131755246, "field 'mLlBgr' and method 'onViewClicked'");
      target.mLlBgr = finder.castView(view, 2131755246, "field 'mLlBgr'");
      view2131755246 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvBqlx = finder.findRequiredViewAsType(source, 2131755249, "field 'mTvBqlx'", TextView.class);
      view = finder.findRequiredView(source, 2131755248, "field 'mLlBqlx' and method 'onViewClicked'");
      target.mLlBqlx = finder.castView(view, 2131755248, "field 'mLlBqlx'");
      view2131755248 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvScqr = finder.findRequiredViewAsType(source, 2131755251, "field 'mTvScqr'", TextView.class);
      view = finder.findRequiredView(source, 2131755250, "field 'mLlScqr' and method 'onViewClicked'");
      target.mLlScqr = finder.castView(view, 2131755250, "field 'mLlScqr'");
      view2131755250 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755428, "field 'mTvSave' and method 'onViewClicked'");
      target.mTvSave = finder.castView(view, 2131755428, "field 'mTvSave'");
      view2131755428 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755252, "field 'mRlZmz' and method 'onViewClicked'");
      target.mRlZmz = finder.castView(view, 2131755252, "field 'mRlZmz'");
      view2131755252 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755253, "field 'mRlCmz' and method 'onViewClicked'");
      target.mRlCmz = finder.castView(view, 2131755253, "field 'mRlCmz'");
      view2131755253 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755254, "field 'mRlMpz' and method 'onViewClicked'");
      target.mRlMpz = finder.castView(view, 2131755254, "field 'mRlMpz'");
      view2131755254 = view;
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
      target.mTvWzmc = null;
      target.mTvBrand = null;
      target.mTvGgxh = null;
      target.mTvScbh = null;
      target.mTvScrq = null;
      target.mLlScrq = null;
      target.mTvLocation = null;
      target.mLlLocation = null;
      target.mTvGhdw = null;
      target.mLlGhdw = null;
      target.mTvSccj = null;
      target.mLlSccj = null;
      target.mTvBgks = null;
      target.mLlBgks = null;
      target.mTvBgr = null;
      target.mLlBgr = null;
      target.mTvBqlx = null;
      target.mLlBqlx = null;
      target.mTvScqr = null;
      target.mLlScqr = null;
      target.mTvSave = null;
      target.mRlZmz = null;
      target.mRlCmz = null;
      target.mRlMpz = null;

      view2131755414.setOnClickListener(null);
      view2131755414 = null;
      view2131755238.setOnClickListener(null);
      view2131755238 = null;
      view2131755207.setOnClickListener(null);
      view2131755207 = null;
      view2131755240.setOnClickListener(null);
      view2131755240 = null;
      view2131755242.setOnClickListener(null);
      view2131755242 = null;
      view2131755244.setOnClickListener(null);
      view2131755244 = null;
      view2131755246.setOnClickListener(null);
      view2131755246 = null;
      view2131755248.setOnClickListener(null);
      view2131755248 = null;
      view2131755250.setOnClickListener(null);
      view2131755250 = null;
      view2131755428.setOnClickListener(null);
      view2131755428 = null;
      view2131755252.setOnClickListener(null);
      view2131755252 = null;
      view2131755253.setOnClickListener(null);
      view2131755253 = null;
      view2131755254.setOnClickListener(null);
      view2131755254 = null;

      this.target = null;
    }
  }
}
