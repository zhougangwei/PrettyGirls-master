package coder.aihui.widget;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/6/22 9:35
 * @ 描述    ${底部弹框 同时数据可操作 不是省市的数据}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/6/22$
 * @ 更新描述  ${TODO}
 */



public class MyBottomDialog /* extends Dialog*/{
  /*  private AddressSelector selector;

    public MyBottomDialog(Context context) {
        super(context, style.bottom_dialog);
        this.init(context);
    }

    public MyBottomDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.init(context);
    }

    public MyBottomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.init(context);
    }

    private void init(Context context) {
        this.selector = new AddressSelector(context);
        this.setContentView(this.selector.getView());
        Window window = this.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = -1;
        params.height = Dev.dp2px(context, 256.0F);
        window.setAttributes(params);
        window.setGravity(80);
    }

    public void setOnAddressSelectedListener(OnAddressSelectedListener listener) {
        this.selector.setOnAddressSelectedListener(listener);
    }

    public static MyBottomDialog showDialog(Context context) {
        return showDialog(context, (OnAddressSelectedListener)null);
    }

    public static MyBottomDialog showDialog(Context context, OnAddressSelectedListener listener) {
        MyBottomDialog dialog = new MyBottomDialog(context, style.bottom_dialog);
        dialog.selector.setOnAddressSelectedListener(listener);
        dialog.showDialog();
        return dialog;
    }


    public  void setAddressProvider(AddressProvider listener){
        this.selector.setAddressProvider(listener);
    }*/
}
