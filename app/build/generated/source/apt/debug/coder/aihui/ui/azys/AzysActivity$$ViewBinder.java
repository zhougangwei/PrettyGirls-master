// Generated code from Butter Knife. Do not modify!
package coder.aihui.ui.azys;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class AzysActivity$$ViewBinder<T extends AzysActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends AzysActivity> implements Unbinder {
    protected T target;

    private View view2131755441;

    private View view2131755431;

    private View view2131755434;

    private View view2131755422;

    private View view2131755405;

    private View view2131755427;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      view = finder.findRequiredView(source, 2131755441, "field 'mIvUpdown' and method 'onViewClicked'");
      target.mIvUpdown = finder.castView(view, 2131755441, "field 'mIvUpdown'");
      view2131755441 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755431, "field 'mIvConfig' and method 'onViewClicked'");
      target.mIvConfig = finder.castView(view, 2131755431, "field 'mIvConfig'");
      view2131755431 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131755434, "field 'mIvPeople' and method 'onViewClicked'");
      target.mIvPeople = finder.castView(view, 2131755434, "field 'mIvPeople'");
      view2131755434 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
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
      target.mSpSearch = finder.findRequiredViewAsType(source, 2131755439, "field 'mSpSearch'", Spinner.class);
      target.mEtSearch = finder.findRequiredViewAsType(source, 2131755296, "field 'mEtSearch'", EditText.class);
      view = finder.findRequiredView(source, 2131755405, "field 'mTvSearch' and method 'onViewClicked'");
      target.mTvSearch = finder.castView(view, 2131755405, "field 'mTvSearch'");
      view2131755405 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      target.mTb = finder.findRequiredViewAsType(source, 2131755255, "field 'mTb'", TabLayout.class);
      target.mRv = finder.findRequiredViewAsType(source, 2131755258, "field 'mRv'", RecyclerView.class);
      view = finder.findRequiredView(source, 2131755427, "field 'mBackTop' and method 'onViewClicked'");
      target.mBackTop = finder.castView(view, 2131755427, "field 'mBackTop'");
      view2131755427 = view;
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

      target.mIvUpdown = null;
      target.mIvConfig = null;
      target.mIvPeople = null;
      target.mTvTitle = null;
      target.mIvBack = null;
      target.mSpSearch = null;
      target.mEtSearch = null;
      target.mTvSearch = null;
      target.mTb = null;
      target.mRv = null;
      target.mBackTop = null;

      view2131755441.setOnClickListener(null);
      view2131755441 = null;
      view2131755431.setOnClickListener(null);
      view2131755431 = null;
      view2131755434.setOnClickListener(null);
      view2131755434 = null;
      view2131755422.setOnClickListener(null);
      view2131755422 = null;
      view2131755405.setOnClickListener(null);
      view2131755405 = null;
      view2131755427.setOnClickListener(null);
      view2131755427 = null;

      this.target = null;
    }
  }
}
