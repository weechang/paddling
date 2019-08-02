package xyz.weechang.paddling.core.model.domain.enums;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

/**
 * @author zhangwei
 * date 2019/7/31
 * time 14:13
 */
public interface IPaddlingEnum<V extends Serializable, D extends Serializable> extends IEnum<V> {

    D getDesc();

    default JSONObject toJson() {
        JSONObject object = JSONUtil.createObj();
        object.put("obj", this);
        object.put("value", getValue());
        object.put("desc", getDesc());
        return object;
    }

    default boolean equalValue(int outVal) {
        return getValue().equals(outVal);
    }

}
