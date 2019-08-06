package xyz.weechang.paddling.oss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.weechang.paddling.oss.enums.OSSEnum;
import xyz.weechang.paddling.oss.storage.*;

/**
 * 存储方案
 *
 * @author zhangwei
 * date 2018/10/27
 * time 14:27
 */
@Component
public class StorageFactory {

    @Autowired
    private LocalStorageService localStorageService;
    @Autowired
    private QiniuStorageService qiniuStorageService;
    @Autowired
    private AliyunStorageService aliyunStorageService;
    @Autowired
    private TencentStorageService tencentStorageService;

    public StorageService getStorage(OSSEnum type) {
        StorageService storageService = null;
        switch (type) {
            case LOCAL:
                storageService = localStorageService;
                break;
            case QINIU:
                storageService = qiniuStorageService;
                break;
            case ALIYUN:
                storageService = aliyunStorageService;
                break;
            case TENCENT:
                storageService = tencentStorageService;
                break;
        }
        return storageService;
    }

}
