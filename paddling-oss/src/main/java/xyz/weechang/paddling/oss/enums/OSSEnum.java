package xyz.weechang.paddling.oss.enums;

/**
 * 对象存储枚举
 */
public enum OSSEnum {
    LOCAL(0, "本机", "LOCAL"),
    QINIU(1, "七牛", "QINIU"),
    ALIYUN(2, "阿里云", "ALIYUN"),
    TENCENT(3, "腾讯云", "TENCENT"),
    ;

    private Integer value;
    private String desc;
    private String name;

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    OSSEnum(Integer value, String desc, String name){
        this.value = value;
        this.desc = desc;
        this.name = name;
    }

    public static OSSEnum buildEnum(String enumName) {
        if (enumName == null) return null;
        for (OSSEnum item : values()) {
            if (item.getName().equals(enumName)) {
                return item;
            }
        }
        return null;
    }
}
