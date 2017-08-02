package coder.aihui.data.bean;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/7/6 16:03
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/7/6$
 * @ 更新描述  ${专门用于 Dialog 条目的 一个字段是String 一个是对象或者是Id}
 */

public class DialogBean {
    private String  Name;
    private Object  object;
    private Integer id;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DialogBean(String name, Object object, Integer id) {
        Name = name;
        this.object = object;
        this.id = id;
    }

    public DialogBean(String name, Object object) {
        Name = name;
        this.object = object;
    }

    public DialogBean() {

    }

    public DialogBean(Object object) {
        this.object = object;
    }


}
