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

    private View view2131755438;

    private View view2131755274;

    private View view2131755287;

    private View view2131755258;

    private View view2131755259;

    private View view2131755260;

    private View view2131755288;

    private View view2131755291;

    private View view2131755268;

    private View view2131755276;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755236, "field 'mTvTitle'", TextView.class);
      view = finder.findRequiredView(source, 2131755438, "field 'mIvBack' and method 'onViewClicked'");
      target.mIvBack = finder.castView(view, 2131755438, "field 'mIvBack'");
      view2131755438 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvWzmc = finder.findRequiredViewAsType(source, 2131755221, "field 'mTvWzmc'", TextView.class);
      target.mTextView2 = finder.findRequiredViewAsType(source, 2131755269, "field 'mTextView2'", TextView.class);
      target.mTvDept = finder.findRequiredViewAsType(source, 2131755212, "field 'mTvDept'", TextView.class);
      target.mTvGgxh = finder.findRequiredViewAsType(source, 2131755225, "field 'mTvGgxh'", TextView.class);
      target.mTvYsr = finder.findRequiredViewAsType(source, 2131755270, "field 'mTvYsr'", TextView.class);
      target.mEtCcbh = finder.findRequiredViewAsType(source, 2131755271, "field 'mEtCcbh'", EditText.class);
      target.mEtZczh = finder.findRequiredViewAsType(source, 2131755273, "field 'mEtZczh'", EditText.class);
      target.mLlZczh = finder.findRequiredViewAsType(source, 2131755272, "field 'mLlZczh'", LinearLayout.class);
      target.mTvZczyxq = finder.findRequiredViewAsType(source, 2131755275, "field 'mTvZczyxq'", TextView.class);
      view = finder.findRequiredView(source, 2131755274, "field 'mLlZczyxq' and method 'onViewClicked'");
      target.mLlZczyxq = finder.castView(view, 2131755274, "field 'mLlZczyxq'");
      view2131755274 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvJysynx = finder.findRequiredViewAsType(source, 2131755277, "field 'mTvJysynx'", TextView.class);
      target.mEtAzgcs = finder.findRequiredViewAsType(source, 2131755279, "field 'mEtAzgcs'", EditText.class);
      target.mLlAzgcs = finder.findRequiredViewAsType(source, 2131755278, "field 'mLlAzgcs'", LinearLayout.class);
      target.mEtGcsdh = finder.findRequiredViewAsType(source, 2131755281, "field 'mEtGcsdh'", EditText.class);
      target.mLlGcsdh = finder.findRequiredViewAsType(source, 2131755280, "field 'mLlGcsdh'", LinearLayout.class);
      target.mEtBxdw = finder.findRequiredViewAsType(source, 2131755282, "field 'mEtBxdw'", EditText.class);
      target.mLlBxdw = finder.findRequiredViewAsType(source, 2131755226, "field 'mLlBxdw'", LinearLayout.class);
      target.mEtBxlxr = finder.findRequiredViewAsType(source, 2131755283, "field 'mEtBxlxr'", EditText.class);
      target.mLlBxlxr = finder.findRequiredViewAsType(source, 2131755230, "field 'mLlBxlxr'", LinearLayout.class);
      target.mEtBxdh = finder.findRequiredViewAsType(source, 2131755285, "field 'mEtBxdh'", EditText.class);
      target.mLlTvBxdh = finder.findRequiredViewAsType(source, 2131755284, "field 'mLlTvBxdh'", LinearLayout.class);
      target.mSlDt = finder.findRequiredViewAsType(source, 2131755286, "field 'mSlDt'", ScrollViewWithListView.class);
      view = finder.findRequiredView(source, 2131755287, "field 'mLlBz' and method 'onViewClicked'");
      target.mLlBz = finder.castView(view, 2131755287, "field 'mLlBz'");
      view2131755287 = view;
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
      target.mIvPic = finder.findRequiredViewAsType(source, 2131755450, "field 'mIvPic'", ImageView.class);
      target.mTvWord = finder.findRequiredViewAsType(source, 2131755451, "field 'mTvWord'", TextView.class);
      target.mTvPjmx = finder.findRequiredViewAsType(source, 2131755289, "field 'mTvPjmx'", TextView.class);
      view = finder.findRequiredView(source, 2131755288, "field 'mLlPjmx' and method 'onViewClicked'");
      target.mLlPjmx = finder.castView(view, 2131755288, "field 'mLlPjmx'");
      view2131755288 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mEtInputId = finder.findRequiredViewAsType(source, 2131755290, "field 'mEtInputId'", EditText.class);
      view = finder.findRequiredView(source, 2131755291, "field 'mIvQianming' and method 'onViewClicked'");
      target.mIvQianming = finder.castView(view, 2131755291, "field 'mIvQianming'");
      view2131755291 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755268, "method 'onViewClicked'");
      view2131755268 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755276, "method 'onViewClicked'");
      view2131755276 = view;
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
      target.mIvQianming = null;

      view2131755438.setOnClickListener(null);
      view2131755438 = null;
      view2131755274.setOnClickListener(null);
      view2131755274 = null;
      view2131755287.setOnClickListener(null);
      view2131755287 = null;
      view2131755258.setOnClickListener(null);
      view2131755258 = null;
      view2131755259.setOnClickListener(null);
      view2131755259 = null;
      view2131755260.setOnClickListener(null);
      view2131755260 = null;
      view2131755288.setOnClickListener(null);
      view2131755288 = null;
      view2131755291.setOnClickListener(null);
      view2131755291 = null;
      view2131755268.setOnClickListener(null);
      view2131755268 = null;
      view2131755276.setOnClickListener(null);
      view2131755276 = null;

      this.target = null;
    }
  }
}
