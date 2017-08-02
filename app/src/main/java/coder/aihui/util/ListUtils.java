package coder.aihui.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/7/19 14:20
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/7/19$
 * @ 更新描述  ${TODO}
 */

public class ListUtils {

    public static String listToStrings(List datas) {
        if (datas == null || datas.size() == 0) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < datas.size(); i++) {
                if (i == datas.size() - 1) {
                    sb.append(datas.get(i));
                } else {
                    sb.append(datas.get(i)).append(",");
                }
            }
            return sb.toString();
        }
    }

    public static List<String> StringsTolist(String datas) {
        if (datas == null || datas.length() == 0) {
            return new ArrayList();
        } else {
            String[] split = datas.split(",");
            return Arrays.asList(split);
        }
    }



}
