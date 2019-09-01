package xyz.weechang.paddling.admin.model.domain.enums;

import cn.hutool.json.JSONArray;
import xyz.weechang.paddling.core.model.domain.enums.IPaddlingEnum;

/**
 * 用户状态
 *
 * @author zhangwei
 * date 2018/10/30
 * time 20:04
 */
public enum UserStatusEnum implements IPaddlingEnum<Integer, String> {

    FORBIDDEN(0, "禁用"),
    AVAILABLE(1, "可用"),
    LOCKED(2, "锁定");

    private int value;
    private String desc;

    UserStatusEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static JSONArray toJsonArray() {
        JSONArray array = new JSONArray();
        for (UserStatusEnum e : values()) {
            array.add(e.toJson());
        }
        return array;
    }

    public static String getDesc(UserStatusEnum e) {
        if (e == null) return null;
        return e.desc;
    }

    public static String getDesc(Integer v) {
        if (v == null) return null;
        for (UserStatusEnum e : values()) {
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
