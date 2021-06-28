package com.jinshan.util;

import org.springframework.http.HttpMethod;

/**
 *  远程接口信息的枚举类
 * @Auther: 小宇宙
 * @Date: 2021-05-18 23:25
 */
public enum YiTuAPIEnum {

    getToken("获取token", "http://31.8.130.55:9800/yitu/login", HttpMethod.POST);  //获取token令牌


    private String name;
    private String url;
    private HttpMethod method;

    YiTuAPIEnum(String name, String url, HttpMethod method) {
        this.name = name;
        this.url = url;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }
}
