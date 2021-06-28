package com.jinshan.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jinshan.service.YiTuAPIService;
import com.jinshan.util.YiTuAPIEnum;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 小宇宙
 * @Date: 2021-05-18 23:36
 */
@Service
public class YiTuAPIServiceImpl implements YiTuAPIService {
    /**
     *  获取token
     * @return
     */
    @Override
    public String getToken() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", "test01");
        map.put("password", "0e698a8ffc1a0af622c7b4db3cb750cc");
        JSONObject param = JSONUtil.createObj();
        param.putAll(map);
        //获取到json字符串
        String json = HttpUtil.post(YiTuAPIEnum.getToken.getUrl(), param.toString());
        com.alibaba.fastjson.JSONObject pa = com.alibaba.fastjson.JSONObject.parseObject(json);
        return pa.getString("token");
    }

}
