package xyz.weechang.paddling.admin.model.domain.enums;

import cn.hutool.json.JSONArray;
import xyz.weechang.paddling.core.model.domain.enums.IPaddlingEnum;

/**
 * 资源显示
 *
 * @author zhangwei
 * date 2019/7/31
 * time 10:41
 */
public enum ResourceStatusEnum implements IPaddlingEnum<Integer, String> {

    UN_AVAILABLE(0, "禁用"),
    AVAILABLE(1, "启用");

    private int value;
    private String desc;

    ResourceStatusEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static JSONArray toJsonArray() {
        JSONArray array = new JSONArray();
        for (ResourceStatusEnum e : values()) {
            array.add(e.toJson());
        }
        return array;
    }

    public static String getDesc(ResourceStatusEnum e) {
        if (e == null) return null;
        return e.desc;
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
