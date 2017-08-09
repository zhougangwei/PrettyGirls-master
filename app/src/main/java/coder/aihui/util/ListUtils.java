package coder.aihui.util;

import java.lang.reflect.Method;
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


    //做一个转换 将集合中的每个个对象中的某一个字段取出来变成另一个集合
    public static List<String> ListFiled2list(List list, String methodName, Class clazz) {
        List<String> stringList = new ArrayList<>();
        try {

            for (int i = 0; i < list.size(); i++) {
                Method method = clazz.getDeclaredMethod(methodName);
                String invoke = (String) method.invoke(list.get(i));
                stringList.add(invoke);
            }
        } catch (Exception e) {
            return stringList;
        }

        return stringList;
    }

    //做一个转换 将集合中的每个个对象中的某一个字段取出来变成另一个集合
    public static String ListFiled2String(List list, String methodName, Class clazz) {
        List<String> stringList = new ArrayList<>();
        try {
            for (int i = 0; i < list.size(); i++) {
                Method method = clazz.getDeclaredMethod(methodName);
                String invoke = (String) method.invoke(list.get(i));
                stringList.add(invoke);
            }
        } catch (Exception e) {
            return "";
        }
        return listToStrings(stringList);
    }


}
