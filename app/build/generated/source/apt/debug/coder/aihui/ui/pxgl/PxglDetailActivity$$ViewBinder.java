// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.pxgl;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class PxglDetailActivity$$ViewBinder<T extends PxglDetailActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends PxglDetailActivity> implements Unbinder {
    protected T target;

    private View view2131755456;

    private View view2131755207;

    private View view2131755366;

    private View view2131755368;

    private View view2131755373;

    private View view2131755375;

    private View view2131755377;

    private View view2131755334;

    private View view2131755382;

    private View view2131755383;

    private View view2131755282;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      target.mTvTitle = finder.findRequiredViewAsType(source, 2131755249, "field 'mTvTitle'", TextView.class);
      view = finder.findRequiredView(source, 2131755456, "field 'mIvBack' and method 'onViewClicked'");
      target.mIvBack = finder.castView(view, 2131755456, "field 'mIvBack'");
      view2131755456 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755207, "field 'mIvUpdown' and method 'onViewClicked'");
      target.mIvUpdown = finder.castView(view, 2131755207, "field 'mIvUpdown'");
      view2131755207 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvPxry = finder.findRequiredViewAsType(source, 2131755367, "field 'mTvPxry'", TextView.class);
      view = finder.findRequiredView(source, 2131755366, "field 'mLlPxry' and method 'onViewClicked'");
      target.mLlPxry = finder.castView(view, 2131755366, "field 'mLlPxry'");
      view2131755366 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvPxsb = finder.findRequiredViewAsType(source, 2131755369, "field 'mTvPxsb'", TextView.class);
      view = finder.findRequiredView(source, 2131755368, "field 'mLlPxsb' and method 'onViewClicked'");
      target.mLlPxsb = finder.castView(view, 2131755368, "field 'mLlPxsb'");
      view2131755368 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mEtZt = finder.findRequiredViewAsType(source, 2131755370, "field 'mEtZt'", EditText.class);
      target.mEtZjr = finder.findRequiredViewAsType(source, 2131755371, "field 'mEtZjr'", EditText.class);
      target.mEtJlr = finder.findRequiredViewAsType(source, 2131755372, "field 'mEtJlr'", EditText.class);
      target.mTvPxlx = finder.findRequiredViewAsType(source, 2131755374, "field 'mTvPxlx'", TextView.class);
      view = finder.findRequiredView(source, 2131755373, "field 'mLlPxlx' and method 'onViewClicked'");
      target.mLlPxlx = finder.castView(view, 2131755373, "field 'mLlPxlx'");
      view2131755373 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvPxf = finder.findRequiredViewAsType(source, 2131755376, "field 'mTvPxf'", TextView.class);
      view = finder.findRequiredView(source, 2131755375, "field 'mLlPxf' and method 'onViewClicked'");
      target.mLlPxf = finder.castView(view, 2131755375, "field 'mLlPxf'");
      view2131755375 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTextView6 = finder.findRequiredViewAsType(source, 2131755378, "field 'mTextView6'", TextView.class);
      target.mTvPxrq = finder.findRequiredViewAsType(source, 2131755379, "field 'mTvPxrq'", TextView.class);
      view = finder.findRequiredView(source, 2131755377, "field 'mLlPxrq' and method 'onViewClicked'");
      target.mLlPxrq = finder.castView(view, 2131755377, "field 'mLlPxrq'");
      view2131755377 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTvPxsc = finder.findRequiredViewAsType(source, 2131755380, "field 'mTvPxsc'", TextView.class);
      view = finder.findRequiredView(source, 2131755334, "field 'mLlTime' and method 'onViewClicked'");
      target.mLlTime = finder.castView(view, 2131755334, "field 'mLlTime'");
      view2131755334 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mEtContent = finder.findRequiredViewAsType(source, 2131755381, "field 'mEtContent'", EditText.class);
      view = finder.findRequiredView(source, 2131755382, "field 'mRlFj' and method 'onViewClicked'");
      target.mRlFj = finder.castView(view, 2131755382, "field 'mRlFj'");
      view2131755382 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755383, "field 'mRlJc' and method 'onViewClicked'");
      target.mRlJc = finder.castView(view, 2131755383, "field 'mRlJc'");
      view2131755383 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755282, "method 'onViewClicked'");
      view2131755282 = view;
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
      target.mIvUpdown = null;
      target.mTvPxry = null;
      target.mLlPxry = null;
      target.mTvPxsb = null;
      target.mLlPxsb = null;
      target.mEtZt = null;
      target.mEtZjr = null;
      target.mEtJlr = null;
      target.mTvPxlx = null;
      target.mLlPxlx = null;
      target.mTvPxf = null;
      target.mLlPxf = null;
      target.mTextView6 = null;
      target.mTvPxrq = null;
      target.mLlPxrq = null;
      target.mTvPxsc = null;
      target.mLlTime = null;
      target.mEtContent = null;
      target.mRlFj = null;
      target.mRlJc = null;

      view2131755456.setOnClickListener(null);
      view2131755456 = null;
      view2131755207.setOnClickListener(null);
      view2131755207 = null;
      view2131755366.setOnClickListener(null);
      view2131755366 = null;
      view2131755368.setOnClickListener(null);
      view2131755368 = null;
      view2131755373.setOnClickListener(null);
      view2131755373 = null;
      view2131755375.setOnClickListener(null);
      view2131755375 = null;
      view2131755377.setOnClickListener(null);
      view2131755377 = null;
      view2131755334.setOnClickListener(null);
      view2131755334 = null;
      view2131755382.setOnClickListener(null);
      view2131755382 = null;
      view2131755383.setOnClickListener(null);
      view2131755383 = null;
      view2131755282.setOnClickListener(null);
      view2131755282 = null;

      this.target = null;
    }
  }
}
