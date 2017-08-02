package coder.aihui.util;


import coder.aihui.base.BaseFragment;
import coder.aihui.ui.main.DownFragment;
import coder.aihui.ui.main.HomeFragment;
import coder.aihui.ui.main.MyFragment;

/**
 * 作者： itheima
 * 时间：2016-10-17 10:42
 * 网址：http://www.itheima.com
 */

public class FragmentFactory {

    static DownFragment mDownFragment;
    static HomeFragment sMHomeFragment;
    static MyFragment   sMMyFragment;


    public static BaseFragment getFragment(int position){
        BaseFragment baseFragment = null;
        switch (position) {
            case 0:
              if (sMHomeFragment ==null){
                sMHomeFragment = new HomeFragment();
            }
                baseFragment = sMHomeFragment;
                break;
            case 1:
                if (mDownFragment ==null){
                    mDownFragment = new DownFragment();
                }
                baseFragment = mDownFragment;
                break;
            case 2:
                if (sMMyFragment ==null){
                    sMMyFragment = new MyFragment();
                }
                baseFragment = sMMyFragment;
                break;
        }
        return baseFragment;

    }

}
