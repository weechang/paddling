package xyz.weechang.paddling.admin.model.domain.enums;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * 资源类型
 *
 * @author zhangwei
 * date 2018/11/19
 * time 11:33
 */
public enum ResourceTypeEnum {

    MENU(1, "菜单"),
    COMPONENT(2, "页面组件"),
    PATH(3, "请求路径"),
    LINK(4, "外链菜单");

    @EnumValue
    private Integer key;
    private String name;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    ResourceTypeEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    public static String getNameByKey(Integer key) {
        for (ResourceTypeEnum item : values()) {
            if (item.key == key) {
                return item.name;
            }
        }
        return null;
    }

    public static JSONArray toJsonArray() {
        JSONArray array = new JSONArray();
        for (ResourceTypeEnum e : values()) {
            JSONObject item = new JSONObject();
            item.put("key", e.getKey());
            item.put("name", e.getName());
            array.add(item);
        }
        return array;
    }
}
