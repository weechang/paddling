package xyz.weechang.paddling.oss.storage;

import cn.hutool.core.io.IoUtil;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.weechang.paddling.oss.config.QiniuProperties;

import javax.annotation.PostConstruct;
import java.io.InputStream;

/**
 * 七牛云实现
 *
 * @author zhangwei
 * date 2018/10/27
 * time 14:26
 */
@Component
public class QiniuStorageService extends StorageService{

    private UploadManager uploadManager;
    private String token;

    @Autowired
    private QiniuProperties qiniuProperties;

    @PostConstruct
    public void beforeInit() {
        if (qiniuProperties != null) {
            uploadManager = new UploadManager(new Configuration(Zone.autoZone()));
            token = Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey()).uploadToken(qiniuProperties.getBucketName());
        }
    }

    @Override
    public String getUploadToken() {
        return this.token;
    }

    @Override
    public String upload(byte[] data, String path) {
        try {
            Response res = uploadManager.put(data, path, token);
            if (!res.isOK()) {
                throw new RuntimeException("上传七牛出错：" + res.toString());
            }
        } catch (Exception e) {
        }
        return path;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return null;
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            byte[] data = IoUtil.readBytes(inputStream);
            return this.upload(data, path);
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(qiniuProperties.getPrefix(), suffix));
    }
}
