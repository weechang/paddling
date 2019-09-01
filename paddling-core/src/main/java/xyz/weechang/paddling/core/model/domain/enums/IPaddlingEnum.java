package xyz.weechang.paddling.core.model.domain.enums;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.Serializable;

/**
 * @author zhangwei
 * date 2019/7/31
 * time 14:13
 */
public interface IPaddlingEnum<V extends Serializable, D extends Serializable> {

    V getValue();

    D getDesc();

    default JSONObject toJson() {
        JSONObject object = JSONUtil.createObj();
        object.put("value", getValue());
        object.put("desc", getDesc());
        return object;
    }

}
