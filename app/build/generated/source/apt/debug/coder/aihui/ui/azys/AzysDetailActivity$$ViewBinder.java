// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.azys;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import coder.aihui.widget.ScrollViewWithListView;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class AzysDetailActivity$$ViewBinder<T extends AzysDetailActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends AzysDetailActivity> implements Unbinder {
    protected T target;

    private View view2131755422;

    private View view2131755269;

    private View view2131755282;

    private View view2131755252;

    private View view2131755253;

    private View view2131755254;

    private View view2131755283;

    private View view2131755263;

    private View view2131755271;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755235, "field 'mTvTitle'", TextView.class);
      view = finder.findRequiredView(source, 2131755422, "field 'mIvBack' and method 'onViewClicked'");
      target.mIvBack = finder.castView(view, 2131755422, "field 'mIvBack'");
      view2131755422 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvWzmc = finder.findRequiredViewAsType(source, 2131755220, "field 'mTvWzmc'", TextView.class);
      target.mTextView2 = finder.findRequiredViewAsType(source, 2131755264, "field 'mTextView2'", TextView.class);
      target.mTvDept = finder.findRequiredViewAsType(source, 2131755211, "field 'mTvDept'", TextView.class);
      target.mTvGgxh = finder.findRequiredViewAsType(source, 2131755224, "field 'mTvGgxh'", TextView.class);
      target.mTvYsr = finder.findRequiredViewAsType(source, 2131755265, "field 'mTvYsr'", TextView.class);
      target.mEtCcbh = finder.findRequiredViewAsType(source, 2131755266, "field 'mEtCcbh'", EditText.class);
      target.mEtZczh = finder.findRequiredViewAsType(source, 2131755268, "field 'mEtZczh'", EditText.class);
      target.mLlZczh = finder.findRequiredViewAsType(source, 2131755267, "field 'mLlZczh'", LinearLayout.class);
      target.mTvZczyxq = finder.findRequiredViewAsType(source, 2131755270, "field 'mTvZczyxq'", TextView.class);
      view = finder.findRequiredView(source, 2131755269, "field 'mLlZczyxq' and method 'onViewClicked'");
      target.mLlZczyxq = finder.castView(view, 2131755269, "field 'mLlZczyxq'");
      view2131755269 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvJysynx = finder.findRequiredViewAsType(source, 2131755272, "field 'mTvJysynx'", TextView.class);
      target.mEtAzgcs = finder.findRequiredViewAsType(source, 2131755274, "field 'mEtAzgcs'", EditText.class);
      target.mLlAzgcs = finder.findRequiredViewAsType(source, 2131755273, "field 'mLlAzgcs'", LinearLayout.class);
      target.mEtGcsdh = finder.findRequiredViewAsType(source, 2131755276, "field 'mEtGcsdh'", EditText.class);
      target.mLlGcsdh = finder.findRequiredViewAsType(source, 2131755275, "field 'mLlGcsdh'", LinearLayout.class);
      target.mEtBxdw = finder.findRequiredViewAsType(source, 2131755277, "field 'mEtBxdw'", EditText.class);
      target.mLlBxdw = finder.findRequiredViewAsType(source, 2131755225, "field 'mLlBxdw'", LinearLayout.class);
      target.mEtBxlxr = finder.findRequiredViewAsType(source, 2131755278, "field 'mEtBxlxr'", EditText.class);
      target.mLlBxlxr = finder.findRequiredViewAsType(source, 2131755229, "field 'mLlBxlxr'", LinearLayout.class);
      target.mEtBxdh = finder.findRequiredViewAsType(source, 2131755280, "field 'mEtBxdh'", EditText.class);
      target.mLlTvBxdh = finder.findRequiredViewAsType(source, 2131755279, "field 'mLlTvBxdh'", LinearLayout.class);
      target.mSlDt = finder.findRequiredViewAsType(source, 2131755281, "field 'mSlDt'", ScrollViewWithListView.class);
      view = finder.findRequiredView(source, 2131755282, "field 'mLlBz' and method 'onViewClicked'");
      target.mLlBz = finder.castView(view, 2131755282, "field 'mLlBz'");
      view2131755282 = view;
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
      target.mIvPic = finder.findRequiredViewAsType(source, 2131755432, "field 'mIvPic'", ImageView.class);
      target.mTvWord = finder.findRequiredViewAsType(source, 2131755433, "field 'mTvWord'", TextView.class);
      target.mTvPjmx = finder.findRequiredViewAsType(source, 2131755284, "field 'mTvPjmx'", TextView.class);
      view = finder.findRequiredView(source, 2131755283, "field 'mLlPjmx' and method 'onViewClicked'");
      target.mLlPjmx = finder.castView(view, 2131755283, "field 'mLlPjmx'");
      view2131755283 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mEtInputId = finder.findRequiredViewAsType(source, 2131755285, "field 'mEtInputId'", EditText.class);
      view = finder.findRequiredView(source, 2131755263, "method 'onViewClicked'");
      view2131755263 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755271, "method 'onViewClicked'");
      view2131755271 = view;
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

      target.mTvTitle = null;
      target.mIvBack = null;
      target.mTvWzmc = null;
      target.mTextView2 = null;
      target.mTvDept = null;
      target.mTvGgxh = null;
      target.mTvYsr = null;
      target.mEtCcbh = null;
      target.mEtZczh = null;
      target.mLlZczh = null;
      target.mTvZczyxq = null;
      target.mLlZczyxq = null;
      target.mTvJysynx = null;
      target.mEtAzgcs = null;
      target.mLlAzgcs = null;
      target.mEtGcsdh = null;
      target.mLlGcsdh = null;
      target.mEtBxdw = null;
      target.mLlBxdw = null;
      target.mEtBxlxr = null;
      target.mLlBxlxr = null;
      target.mEtBxdh = null;
      target.mLlTvBxdh = null;
      target.mSlDt = null;
      target.mLlBz = null;
      target.mRlZmz = null;
      target.mRlCmz = null;
      target.mRlMpz = null;
      target.mIvPic = null;
      target.mTvWord = null;
      target.mTvPjmx = null;
      target.mLlPjmx = null;
      target.mEtInputId = null;

      view2131755422.setOnClickListener(null);
      view2131755422 = null;
      view2131755269.setOnClickListener(null);
      view2131755269 = null;
      view2131755282.setOnClickListener(null);
      view2131755282 = null;
      view2131755252.setOnClickListener(null);
      view2131755252 = null;
      view2131755253.setOnClickListener(null);
      view2131755253 = null;
      view2131755254.setOnClickListener(null);
      view2131755254 = null;
      view2131755283.setOnClickListener(null);
      view2131755283 = null;
      view2131755263.setOnClickListener(null);
      view2131755263 = null;
      view2131755271.setOnClickListener(null);
      view2131755271 = null;

      this.target = null;
    }
  }
}
