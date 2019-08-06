package xyz.weechang.paddling.oss.web;

import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.weechang.paddling.core.model.dto.R;
import xyz.weechang.paddling.oss.StorageFactory;
import xyz.weechang.paddling.oss.config.PaddlingOssProperties;
import xyz.weechang.paddling.oss.enums.OSSEnum;
import xyz.weechang.paddling.oss.storage.StorageService;

import javax.annotation.PostConstruct;

/**
 * @author zhangwei
 * date 2019/8/5
 * time 11:39
 */
@RestController
@RequestMapping("paddling/oss")
public class PaddlingOssController {

    @Autowired
    private StorageFactory storageFactory;

    @Autowired
    private PaddlingOssProperties paddlingOssProperties;

    private OSSEnum defaultOssType;

    @PostConstruct
    private void init() {
        String defaultOss = paddlingOssProperties.getDefaultOss();
        if (defaultOss == null) return;
        defaultOssType = OSSEnum.buildEnum(defaultOss);
    }


    @GetMapping("uploadToken")
    public R uploadToken(OSSEnum ossType) {
        if (ossType == null) {
            ossType = defaultOssType;
        }
        StorageService storageService = storageFactory.getStorage(ossType);
        if (storageService == null) return R.ok();
        String uploadToken = storageService.getUploadToken();
        JSONObject result = new JSONObject();
        result.put("uploadToken", uploadToken);
        return R.ok(result);
    }
}
