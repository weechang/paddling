package xyz.weechang.paddling.oss.enums;

/**
 * 对象存储枚举
 */
public enum OSSEnum {
    LOCAL(0, "本机"),
    QINIU(1, "七牛"),
    ALIYUN(2, "阿里云"),
    TENCENT(3, "腾讯云"),
    ;

    private Integer key;
    private String value;

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    OSSEnum(Integer key, String value){
        this.key = key;
        this.value = value;
    }
}
