package xyz.weechang.paddling.oss.storage;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;
import org.springframework.stereotype.Component;
import xyz.weechang.paddling.oss.config.TencentProperties;

import javax.annotation.Resource;
import java.io.InputStream;

/**
 * 腾讯云 OSS 实现
 * @author zhangwei
 * date 2018/10/27
 * time 14:31
 */
@Component
public class TencentStorageService extends StorageService{

    private COSClient client;

    @Resource
    private TencentProperties tencentProperties;

    @Override
    public String getUploadToken() {
        return null;
    }

    @Override
    public String upload(byte[] data, String path) {
        //腾讯云必需要以"/"开头
        if(!path.startsWith("/")) {
            path = "/" + path;
        }
        //上传到腾讯云
        UploadFileRequest request = new UploadFileRequest(tencentProperties.getBucketName(), path, data);
        String response = client.uploadFile(request);
        JSONObject jsonObject = JSONUtil.parseObj(response);
        if(jsonObject.getInt("code") != 0) {

        }
        return path;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(tencentProperties.getPrefix(), suffix));
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            byte[] data = IoUtil.readBytes(inputStream);
            return this.upload(data, path);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(tencentProperties.getPrefix(), suffix));
    }
}
