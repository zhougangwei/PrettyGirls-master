// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.assetcheck;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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

    private View view2131755456;

    private View view2131755255;

    private View view2131755221;

    private View view2131755257;

    private View view2131755259;

    private View view2131755261;

    private View view2131755263;

    private View view2131755265;

    private View view2131755267;

    private View view2131755472;

    private View view2131755271;

    private View view2131755272;

    private View view2131755273;

    private View view2131755235;

    private View view2131755269;

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
      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755249, "field 'mTvTitle'", TextView.class);
      target.mEtWzmc = finder.findRequiredViewAsType(source, 2131755250, "field 'mEtWzmc'", EditText.class);
      target.mTvBrand = finder.findRequiredViewAsType(source, 2131755251, "field 'mTvBrand'", TextView.class);
      target.mEtGgxh = finder.findRequiredViewAsType(source, 2131755252, "field 'mEtGgxh'", EditText.class);
      target.mEtScbh = finder.findRequiredViewAsType(source, 2131755254, "field 'mEtScbh'", EditText.class);
      target.mTvScrq = finder.findRequiredViewAsType(source, 2131755256, "field 'mTvScrq'", TextView.class);
      view = finder.findRequiredView(source, 2131755255, "field 'mLlScrq' and method 'onViewClicked'");
      target.mLlScrq = finder.castView(view, 2131755255, "field 'mLlScrq'");
      view2131755255 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvLocation = finder.findRequiredViewAsType(source, 2131755222, "field 'mTvLocation'", TextView.class);
      view = finder.findRequiredView(source, 2131755221, "field 'mLlLocation' and method 'onViewClicked'");
      target.mLlLocation = finder.castView(view, 2131755221, "field 'mLlLocation'");
      view2131755221 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvGhdw = finder.findRequiredViewAsType(source, 2131755258, "field 'mTvGhdw'", TextView.class);
      view = finder.findRequiredView(source, 2131755257, "field 'mLlGhdw' and method 'onViewClicked'");
      target.mLlGhdw = finder.castView(view, 2131755257, "field 'mLlGhdw'");
      view2131755257 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvSccj = finder.findRequiredViewAsType(source, 2131755260, "field 'mTvSccj'", TextView.class);
      view = finder.findRequiredView(source, 2131755259, "field 'mLlSccj' and method 'onViewClicked'");
      target.mLlSccj = finder.castView(view, 2131755259, "field 'mLlSccj'");
      view2131755259 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvBgks = finder.findRequiredViewAsType(source, 2131755262, "field 'mTvBgks'", TextView.class);
      view = finder.findRequiredView(source, 2131755261, "field 'mLlBgks' and method 'onViewClicked'");
      target.mLlBgks = finder.castView(view, 2131755261, "field 'mLlBgks'");
      view2131755261 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvBgr = finder.findRequiredViewAsType(source, 2131755264, "field 'mTvBgr'", TextView.class);
      view = finder.findRequiredView(source, 2131755263, "field 'mLlBgr' and method 'onViewClicked'");
      target.mLlBgr = finder.castView(view, 2131755263, "field 'mLlBgr'");
      view2131755263 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvBqlx = finder.findRequiredViewAsType(source, 2131755266, "field 'mTvBqlx'", TextView.class);
      view = finder.findRequiredView(source, 2131755265, "field 'mLlBqlx' and method 'onViewClicked'");
      target.mLlBqlx = finder.castView(view, 2131755265, "field 'mLlBqlx'");
      view2131755265 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvScqr = finder.findRequiredViewAsType(source, 2131755268, "field 'mTvScqr'", TextView.class);
      view = finder.findRequiredView(source, 2131755267, "field 'mLlScqr' and method 'onViewClicked'");
      target.mLlScqr = finder.castView(view, 2131755267, "field 'mLlScqr'");
      view2131755267 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755472, "field 'mTvSave' and method 'onViewClicked'");
      target.mTvSave = finder.castView(view, 2131755472, "field 'mTvSave'");
      view2131755472 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755271, "field 'mRlZmz' and method 'onViewClicked'");
      target.mRlZmz = finder.castView(view, 2131755271, "field 'mRlZmz'");
      view2131755271 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755272, "field 'mRlCmz' and method 'onViewClicked'");
      target.mRlCmz = finder.castView(view, 2131755272, "field 'mRlCmz'");
      view2131755272 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755273, "field 'mRlMpz' and method 'onViewClicked'");
      target.mRlMpz = finder.castView(view, 2131755273, "field 'mRlMpz'");
      view2131755273 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mLlWzmc = finder.findRequiredViewAsType(source, 2131755233, "field 'mLlWzmc'", LinearLayout.class);
      view = finder.findRequiredView(source, 2131755235, "field 'mLlPpmc' and method 'onViewClicked'");
      target.mLlPpmc = finder.castView(view, 2131755235, "field 'mLlPpmc'");
      view2131755235 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mLlGgxh = finder.findRequiredViewAsType(source, 2131755237, "field 'mLlGgxh'", LinearLayout.class);
      target.mLlScbh = finder.findRequiredViewAsType(source, 2131755253, "field 'mLlScbh'", LinearLayout.class);
      target.mTvBqyw = finder.findRequiredViewAsType(source, 2131755270, "field 'mTvBqyw'", TextView.class);
      view = finder.findRequiredView(source, 2131755269, "field 'mLlBqyw' and method 'onViewClicked'");
      target.mLlBqyw = finder.castView(view, 2131755269, "field 'mLlBqyw'");
      view2131755269 = view;
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
      target.mEtWzmc = null;
      target.mTvBrand = null;
      target.mEtGgxh = null;
      target.mEtScbh = null;
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
      target.mLlWzmc = null;
      target.mLlPpmc = null;
      target.mLlGgxh = null;
      target.mLlScbh = null;
      target.mTvBqyw = null;
      target.mLlBqyw = null;

      view2131755456.setOnClickListener(null);
      view2131755456 = null;
      view2131755255.setOnClickListener(null);
      view2131755255 = null;
      view2131755221.setOnClickListener(null);
      view2131755221 = null;
      view2131755257.setOnClickListener(null);
      view2131755257 = null;
      view2131755259.setOnClickListener(null);
      view2131755259 = null;
      view2131755261.setOnClickListener(null);
      view2131755261 = null;
      view2131755263.setOnClickListener(null);
      view2131755263 = null;
      view2131755265.setOnClickListener(null);
      view2131755265 = null;
      view2131755267.setOnClickListener(null);
      view2131755267 = null;
      view2131755472.setOnClickListener(null);
      view2131755472 = null;
      view2131755271.setOnClickListener(null);
      view2131755271 = null;
      view2131755272.setOnClickListener(null);
      view2131755272 = null;
      view2131755273.setOnClickListener(null);
      view2131755273 = null;
      view2131755235.setOnClickListener(null);
      view2131755235 = null;
      view2131755269.setOnClickListener(null);
      view2131755269 = null;

      this.target = null;
    }
  }
}
