package coder.aihui.ui.main;

import java.util.List;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/4/10 15:18
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/4/10$
 * @ 更新描述  ${TODO}
 */

public class DownLoadBean {


        String[]       enties;    //对象
        String[]       methods;   //方法
        List<String[]> pars;        //参数
        Integer        type;       //下载的类别
        Integer        way;       //下载的方式 http还是webservice
        String         name;        //名称
        Integer        count;      //数目


        public String[] getEnties() {
            return enties;
        }

        public void setEnties(String[] enties) {
            this.enties = enties;
        }

        public String[] getMethods() {
            return methods;
        }

        public void setMethods(String[] methods) {
            this.methods = methods;
        }

        public List<String[]> getPars() {
            return pars;
        }

        public void setPars(List<String[]> pars) {
            this.pars = pars;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getWay() {
            return way;
        }

        public void setWay(Integer way) {
            this.way = way;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

}
