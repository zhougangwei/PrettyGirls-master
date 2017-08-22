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

    private View view2131755438;

    private View view2131755242;

    private View view2131755208;

    private View view2131755244;

    private View view2131755246;

    private View view2131755248;

    private View view2131755250;

    private View view2131755252;

    private View view2131755254;

    private View view2131755454;

    private View view2131755258;

    private View view2131755259;

    private View view2131755260;

    private View view2131755222;

    private View view2131755256;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
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
      target.mEtWzmc = finder.findRequiredViewAsType(source, 2131755237, "field 'mEtWzmc'", EditText.class);
      target.mTvBrand = finder.findRequiredViewAsType(source, 2131755238, "field 'mTvBrand'", TextView.class);
      target.mEtGgxh = finder.findRequiredViewAsType(source, 2131755239, "field 'mEtGgxh'", EditText.class);
      target.mEtScbh = finder.findRequiredViewAsType(source, 2131755241, "field 'mEtScbh'", EditText.class);
      target.mTvScrq = finder.findRequiredViewAsType(source, 2131755243, "field 'mTvScrq'", TextView.class);
      view = finder.findRequiredView(source, 2131755242, "field 'mLlScrq' and method 'onViewClicked'");
      target.mLlScrq = finder.castView(view, 2131755242, "field 'mLlScrq'");
      view2131755242 = view;
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
      target.mTvGhdw = finder.findRequiredViewAsType(source, 2131755245, "field 'mTvGhdw'", TextView.class);
      view = finder.findRequiredView(source, 2131755244, "field 'mLlGhdw' and method 'onViewClicked'");
      target.mLlGhdw = finder.castView(view, 2131755244, "field 'mLlGhdw'");
      view2131755244 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvSccj = finder.findRequiredViewAsType(source, 2131755247, "field 'mTvSccj'", TextView.class);
      view = finder.findRequiredView(source, 2131755246, "field 'mLlSccj' and method 'onViewClicked'");
      target.mLlSccj = finder.castView(view, 2131755246, "field 'mLlSccj'");
      view2131755246 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvBgks = finder.findRequiredViewAsType(source, 2131755249, "field 'mTvBgks'", TextView.class);
      view = finder.findRequiredView(source, 2131755248, "field 'mLlBgks' and method 'onViewClicked'");
      target.mLlBgks = finder.castView(view, 2131755248, "field 'mLlBgks'");
      view2131755248 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvBgr = finder.findRequiredViewAsType(source, 2131755251, "field 'mTvBgr'", TextView.class);
      view = finder.findRequiredView(source, 2131755250, "field 'mLlBgr' and method 'onViewClicked'");
      target.mLlBgr = finder.castView(view, 2131755250, "field 'mLlBgr'");
      view2131755250 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvBqlx = finder.findRequiredViewAsType(source, 2131755253, "field 'mTvBqlx'", TextView.class);
      view = finder.findRequiredView(source, 2131755252, "field 'mLlBqlx' and method 'onViewClicked'");
      target.mLlBqlx = finder.castView(view, 2131755252, "field 'mLlBqlx'");
      view2131755252 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvScqr = finder.findRequiredViewAsType(source, 2131755255, "field 'mTvScqr'", TextView.class);
      view = finder.findRequiredView(source, 2131755254, "field 'mLlScqr' and method 'onViewClicked'");
      target.mLlScqr = finder.castView(view, 2131755254, "field 'mLlScqr'");
      view2131755254 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755454, "field 'mTvSave' and method 'onViewClicked'");
      target.mTvSave = finder.castView(view, 2131755454, "field 'mTvSave'");
      view2131755454 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755258, "field 'mRlZmz' and method 'onViewClicked'");
      target.mRlZmz = finder.castView(view, 2131755258, "field 'mRlZmz'");
      view2131755258 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755259, "field 'mRlCmz' and method 'onViewClicked'");
      target.mRlCmz = finder.castView(view, 2131755259, "field 'mRlCmz'");
      view2131755259 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755260, "field 'mRlMpz' and method 'onViewClicked'");
      target.mRlMpz = finder.castView(view, 2131755260, "field 'mRlMpz'");
      view2131755260 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mLlWzmc = finder.findRequiredViewAsType(source, 2131755220, "field 'mLlWzmc'", LinearLayout.class);
      view = finder.findRequiredView(source, 2131755222, "field 'mLlPpmc' and method 'onViewClicked'");
      target.mLlPpmc = finder.castView(view, 2131755222, "field 'mLlPpmc'");
      view2131755222 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mLlGgxh = finder.findRequiredViewAsType(source, 2131755224, "field 'mLlGgxh'", LinearLayout.class);
      target.mLlScbh = finder.findRequiredViewAsType(source, 2131755240, "field 'mLlScbh'", LinearLayout.class);
      target.mTvBqyw = finder.findRequiredViewAsType(source, 2131755257, "field 'mTvBqyw'", TextView.class);
      view = finder.findRequiredView(source, 2131755256, "field 'mLlBqyw' and method 'onViewClicked'");
      target.mLlBqyw = finder.castView(view, 2131755256, "field 'mLlBqyw'");
      view2131755256 = view;
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

      view2131755438.setOnClickListener(null);
      view2131755438 = null;
      view2131755242.setOnClickListener(null);
      view2131755242 = null;
      view2131755208.setOnClickListener(null);
      view2131755208 = null;
      view2131755244.setOnClickListener(null);
      view2131755244 = null;
      view2131755246.setOnClickListener(null);
      view2131755246 = null;
      view2131755248.setOnClickListener(null);
      view2131755248 = null;
      view2131755250.setOnClickListener(null);
      view2131755250 = null;
      view2131755252.setOnClickListener(null);
      view2131755252 = null;
      view2131755254.setOnClickListener(null);
      view2131755254 = null;
      view2131755454.setOnClickListener(null);
      view2131755454 = null;
      view2131755258.setOnClickListener(null);
      view2131755258 = null;
      view2131755259.setOnClickListener(null);
      view2131755259 = null;
      view2131755260.setOnClickListener(null);
      view2131755260 = null;
      view2131755222.setOnClickListener(null);
      view2131755222 = null;
      view2131755256.setOnClickListener(null);
      view2131755256 = null;

      this.target = null;
    }
  }
}
