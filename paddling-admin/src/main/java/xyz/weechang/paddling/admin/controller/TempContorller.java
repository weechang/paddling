package xyz.weechang.paddling.admin.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.weechang.paddling.core.model.dto.R;

/**
 * @author zhangwei
 * date 2019/7/31
 * time 11:15
 */
@RequestMapping("paddling/temp")
@RestController
public class TempContorller {

    @GetMapping("info")
    public R info() {
        JSONObject token = new JSONObject();
        JSONArray roles = new JSONArray();
        roles.add("admin");
        token.put("roles", roles);
        token.put("introduction", "I am a super administrator");
        token.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        token.put("name", "Super Admin");
        return R.ok(token);
    }
}
