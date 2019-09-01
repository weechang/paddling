package xyz.weechang.paddling.admin.model.domain.enums;

import cn.hutool.json.JSONArray;
import xyz.weechang.paddling.core.model.domain.enums.IPaddlingEnum;

/**
 * 资源类型
 *
 * @author zhangwei
 * date 2018/11/19
 * time 11:33
 */
public enum ResourceTypeEnum implements IPaddlingEnum<Integer, String> {

    SHOW_MENU(1, "显示菜单"),
    HIDE_MENU(2, "隐藏菜单"),
    COMPONENT(3, "页面组件"),
    PATH(4, "请求路径"),
    LINK(5, "外链菜单");

    private int value;
    private String desc;

    ResourceTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static JSONArray toJsonArray() {
        JSONArray array = new JSONArray();
        for (ResourceTypeEnum e : values()) {
            array.add(e.toJson());
        }
        return array;
    }

    public static String getDesc(ResourceTypeEnum e) {
        if (e == null) return null;
        return e.desc;
    }

    public static String getDesc(Integer v) {
        if (v == null) return null;
        for (ResourceTypeEnum e : values()) {
            if (e.value == v) {
                return e.desc;
            }
        }
       return null;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
