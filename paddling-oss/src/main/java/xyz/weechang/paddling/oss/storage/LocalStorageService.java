package xyz.weechang.paddling.oss.storage;

import java.io.InputStream;

/**
 * 本地文件目录实现
 *
 * @author zhangwei
 * date 2019/5/23
 * time 13:28
 */
public class LocalStorageService extends StorageService{

    @Override
    public String upload(byte[] data, String path) {
        return null;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return null;
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        return null;
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return null;
    }
}
