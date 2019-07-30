package xyz.weechang.paddling.oss;


import xyz.weechang.paddling.oss.config.OssProperties;
import xyz.weechang.paddling.oss.enums.OSSEnum;

/**
 * 存储方案
 *
 * @author zhangwei
 * date 2018/10/27
 * time 14:27
 */
public abstract class StorageFactory {

    OssProperties config;

    public StorageFactory(OSSEnum type){

    }

}
