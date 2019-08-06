package xyz.weechang.paddling.oss.storage;

import com.aliyun.oss.OSSClient;
import org.springframework.stereotype.Component;
import xyz.weechang.paddling.oss.config.AliyunProperties;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 阿里云实现
 *
 * @author zhangwei
 * date 2018/10/27
 * time 14:26
 */
@Component
public class AliyunStorageService extends StorageService {

    private OSSClient client;

    @Resource
    private AliyunProperties aliyunProperties;

    @Override
    public String getUploadToken() {
        return null;
    }

    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(aliyunProperties.getPrefix(), suffix));
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            client.putObject(aliyunProperties.getBucketName(), path, inputStream);
        } catch (Exception e){

        }
        return path;
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(aliyunProperties.getPrefix(), suffix));
    }
}
