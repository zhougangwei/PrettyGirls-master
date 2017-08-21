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

    private View view2131755434;

    private View view2131755239;

    private View view2131755208;

    private View view2131755241;

    private View view2131755243;

    private View view2131755245;

    private View view2131755247;

    private View view2131755249;

    private View view2131755251;

    private View view2131755450;

    private View view2131755253;

    private View view2131755254;

    private View view2131755255;

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
      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755236, "field 'mTvTitle'", TextView.class);
      target.mTvWzmc = finder.findRequiredViewAsType(source, 2131755221, "field 'mTvWzmc'", TextView.class);
      target.mTvBrand = finder.findRequiredViewAsType(source, 2131755237, "field 'mTvBrand'", TextView.class);
      target.mTvGgxh = finder.findRequiredViewAsType(source, 2131755225, "field 'mTvGgxh'", TextView.class);
      target.mTvScbh = finder.findRequiredViewAsType(source, 2131755238, "field 'mTvScbh'", TextView.class);
      target.mTvScrq = finder.findRequiredViewAsType(source, 2131755240, "field 'mTvScrq'", TextView.class);
      view = finder.findRequiredView(source, 2131755239, "field 'mLlScrq' and method 'onViewClicked'");
      target.mLlScrq = finder.castView(view, 2131755239, "field 'mLlScrq'");
      view2131755239 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvLocation = finder.findRequiredViewAsType(source, 2131755209, "field 'mTvLocation'", TextView.class);
      view = finder.findRequiredView(source, 2131755208, "field 'mLlLocation' and method 'onViewClicked'");
      target.mLlLocation = finder.castView(view, 2131755208, "field 'mLlLocation'");
      view2131755208 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvGhdw = finder.findRequiredViewAsType(source, 2131755242, "field 'mTvGhdw'", TextView.class);
      view = finder.findRequiredView(source, 2131755241, "field 'mLlGhdw' and method 'onViewClicked'");
      target.mLlGhdw = finder.castView(view, 2131755241, "field 'mLlGhdw'");
      view2131755241 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvSccj = finder.findRequiredViewAsType(source, 2131755244, "field 'mTvSccj'", TextView.class);
      view = finder.findRequiredView(source, 2131755243, "field 'mLlSccj' and method 'onViewClicked'");
      target.mLlSccj = finder.castView(view, 2131755243, "field 'mLlSccj'");
      view2131755243 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvBgks = finder.findRequiredViewAsType(source, 2131755246, "field 'mTvBgks'", TextView.class);
      view = finder.findRequiredView(source, 2131755245, "field 'mLlBgks' and method 'onViewClicked'");
      target.mLlBgks = finder.castView(view, 2131755245, "field 'mLlBgks'");
      view2131755245 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvBgr = finder.findRequiredViewAsType(source, 2131755248, "field 'mTvBgr'", TextView.class);
      view = finder.findRequiredView(source, 2131755247, "field 'mLlBgr' and method 'onViewClicked'");
      target.mLlBgr = finder.castView(view, 2131755247, "field 'mLlBgr'");
      view2131755247 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvBqlx = finder.findRequiredViewAsType(source, 2131755250, "field 'mTvBqlx'", TextView.class);
      view = finder.findRequiredView(source, 2131755249, "field 'mLlBqlx' and method 'onViewClicked'");
      target.mLlBqlx = finder.castView(view, 2131755249, "field 'mLlBqlx'");
      view2131755249 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvScqr = finder.findRequiredViewAsType(source, 2131755252, "field 'mTvScqr'", TextView.class);
      view = finder.findRequiredView(source, 2131755251, "field 'mLlScqr' and method 'onViewClicked'");
      target.mLlScqr = finder.castView(view, 2131755251, "field 'mLlScqr'");
      view2131755251 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755450, "field 'mTvSave' and method 'onViewClicked'");
      target.mTvSave = finder.castView(view, 2131755450, "field 'mTvSave'");
      view2131755450 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755253, "field 'mRlZmz' and method 'onViewClicked'");
      target.mRlZmz = finder.castView(view, 2131755253, "field 'mRlZmz'");
      view2131755253 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755254, "field 'mRlCmz' and method 'onViewClicked'");
      target.mRlCmz = finder.castView(view, 2131755254, "field 'mRlCmz'");
      view2131755254 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755255, "field 'mRlMpz' and method 'onViewClicked'");
      target.mRlMpz = finder.castView(view, 2131755255, "field 'mRlMpz'");
      view2131755255 = view;
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

      view2131755434.setOnClickListener(null);
      view2131755434 = null;
      view2131755239.setOnClickListener(null);
      view2131755239 = null;
      view2131755208.setOnClickListener(null);
      view2131755208 = null;
      view2131755241.setOnClickListener(null);
      view2131755241 = null;
      view2131755243.setOnClickListener(null);
      view2131755243 = null;
      view2131755245.setOnClickListener(null);
      view2131755245 = null;
      view2131755247.setOnClickListener(null);
      view2131755247 = null;
      view2131755249.setOnClickListener(null);
      view2131755249 = null;
      view2131755251.setOnClickListener(null);
      view2131755251 = null;
      view2131755450.setOnClickListener(null);
      view2131755450 = null;
      view2131755253.setOnClickListener(null);
      view2131755253 = null;
      view2131755254.setOnClickListener(null);
      view2131755254 = null;
      view2131755255.setOnClickListener(null);
      view2131755255 = null;

      this.target = null;
    }
  }
}
