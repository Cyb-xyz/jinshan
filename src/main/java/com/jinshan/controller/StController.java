package com.jinshan.controller;

import com.jinshan.pojo.JinShanResult;
import com.jinshan.service.StService;
import com.jinshan.util.IdWorker;
import com.jinshan.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 *  对外接口(商汤调用)
 * @Auther: 小宇宙
 * @Date: 2021-05-19 8:14
 */
@RestController
@RequestMapping("/st")
public class StController {

    @Autowired
    private StService stService;

    @Autowired
    private IdWorker idWorker;

    /**
     *  对外获取token令牌的接口
     * @param appid 应用id
     * @param secret    登录密钥
     * @return  返回token
     */
    @GetMapping(value = "/getToken")
    public JinShanResult getToken(@RequestParam("appid") String appid,
                                  @RequestParam("secret") String secret) {
        //appId: 224_F28184DK
        //secretId: 2758b811046f4e7eae3552a45a78d580
        Map<String, Object> map = new HashMap<>();
        if (!"".equals(appid) && "224_F28184DK".equals(appid) &&
                !"".equals(secret) && "2758b811046f4e7eae3552a45a78d580".equals(secret)) {

            String token = TokenUtils.getToken(appid, secret);
            map.put("x-access-token", token);

            return new JinShanResult("0", "操作成功", map);
        }

        return new JinShanResult("-1", "应用id或登录密钥不正确", map);
    }

    /**
     *  对外事件信息上报接口，接收推送的商汤数据
     * @param token     令牌
     * @param jsonStr   上传的参数列表
     * @return
     */
    @PostMapping(value = "/messageUpload")
    public JinShanResult messageUpload(@RequestHeader("x-access-token") String token,
                                       @RequestBody Map<String, Map<String, Object>> jsonStr) {
        //校验token令牌
        if (!"".equals(token) && TokenUtils.verify(token)) {
            //存放json字符串解析中的数据
            Map<String, Object> paramMap = new HashMap<>();
            //解析传递过来的json字符串
            for (String key:jsonStr.keySet()) {
                Map<String, Object> map = jsonStr.get(key);
                for (String key1:map.keySet()) {
                    paramMap.put(key1, map.get(key1));
                }
            }
            //设置上传数据的id
            paramMap.put("id", idWorker.nextId()+"");
//            打印json字符串中的数据
//            for (String key:paramMap.keySet()) {
//                System.out.print(key);
//                System.out.println(" : "+paramMap.get(key));
//            }
            String code = "-1";
            String msg = "推送失败";
            try {
                int success = stService.save(paramMap);
                System.out.println("影响条数:"+success);
                if (success > 0) {
                    code = "0";
                    msg = "推送成功";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return new JinShanResult(code, msg, null);

        }
        return new JinShanResult("-1", "token令牌过期或令牌不正确", null);
    }



}
