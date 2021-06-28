package com.jinshan.util.enumerate;

import org.springframework.http.HttpMethod;

/**
 *  金山远程接口信息的枚举类
 * @Auther: 小宇宙
 * @Date: 2021-05-18 23:25
 */
public enum JinShanAPIEnum {

    getCsYjGaData("获取城市预警_公安数据", "http://12.111.254.108:9000/js110/jsdata/timezone/causeAction", HttpMethod.GET),

    getCsYxKhDmData("获取城市运行考核断面水数据", "http://31.8.11.112:9251/api/screen/szlmb", HttpMethod.GET),

    getCsYxSyRkData("获取城市运行实有人口数据", "http://12.111.254.108:9000/web/shareApi/getJsPeopleDataCount", HttpMethod.POST),
    getCsYxCsJtData("获取城市运行城市交通数据--停车场", "http://116.228.125.202:8081/park/v1/parks", HttpMethod.GET),
    getCsYxCsHjDqData("获取城市运行城市环境大气数据(获取空气质量API实时数据)", "http://31.8.11.112:9251/api/screen/kqzlmb", HttpMethod.GET),

    getBaiDuToken("获取百度接口的令牌", "http://31.8.21.169:9106/auth/tokens", HttpMethod.GET),
    getZdDl_LdPh("获取重点道路路段排行接口", "http://31.8.21.169:9106/core/rest/XQ00005/JS00003ZYML00004/data?ak=IDUhLa5gDzuYNDRBBDbsowmPwCbZo9X0", HttpMethod.GET),
    getZdDl_DlPh("获取重点道路道路排行接口", "http://31.8.21.169:9106/core/rest/XQ00004/JS00003ZYML00005/data?ak=IDUhLa5gDzuYNDRBBDbsowmPwCbZo9X0", HttpMethod.GET),
    getZdDl_XX("获取重点道路信息接口", "http://31.8.21.169:9106/core/rest/XQ00001/JS00003ZYML00003/data?ak=IDUhLa5gDzuYNDRBBDbsowmPwCbZo9X0", HttpMethod.GET),
    getZdDl_DlYdZbXq("获取重点道路道路拥堵指标详情接口", "http://31.8.21.169:9106/core/rest/XQ00002/JS00003ZYML00007/data?ak=IDUhLa5gDzuYNDRBBDbsowmPwCbZo9X0", HttpMethod.GET),
    getZdDl_LDYdZbXq("获取重点道路路段拥堵指标详情接口", "http://31.8.21.169:9106/core/rest/XQ00003/JS00003ZYML00006/data?ak=IDUhLa5gDzuYNDRBBDbsowmPwCbZo9X0", HttpMethod.GET),
    getXFJQSj("消防警情事件接口", "http://31.8.11.98/sd/api/getJsFireEvents?", HttpMethod.GET),

    getQyToken("获取区域人流Token令牌", "http://172.16.33.111:8085/com/getToken4Corp", HttpMethod.GET),
    getQyRL("查询区域人流接口", "http://172.16.33.111:8085/query/queryHistoryCrowdCount", HttpMethod.POST),
    getZlSc("查询驻留时长", "http://172.16.33.111:8085/query/queryCrowdStationCount", HttpMethod.POST),
    getQyRlHxXX("查询区域人流画像基本信息","http://172.16.33.111:8085/query/queryCrowdPortraitBase", HttpMethod.POST),
    getXzQQjRL("获取行政区全局热力（订阅-热力图）", "http://172.16.33.111:8085/query/queryAllGridHeatNonSubscription", HttpMethod.POST),

    getQyXXZl("获取企业信息总览", "http://172.16.33.135:28081/device-ai/api/Row1Column1", HttpMethod.GET),
    getWXHXPZl("获取危险化学品总览", "http://172.16.33.135:28081/device-ai/api/Row2Col1", HttpMethod.GET),
    getLDLXQRLJ("获取流动流向-前日累计（辆）", "http://172.16.33.135:28081/device-ai/zhongan/getVehicleFlow", HttpMethod.GET),
    getLDLXSSSl("获取流动流向-获取实时数量（辆）", "http://172.16.33.135:28081/device-ai/zhongan/getVehicleStatistics", HttpMethod.GET),
    getLDLXWXCB("获取流动流向-获取危险船舶", "http://172.16.33.135:28081/device-ai/chemicalShip/ship", HttpMethod.GET),
    getLDLXDXHGGX("获取流动流向-获取地下化工管线", "http://172.16.33.135:28081/device-ai/chemicalChannel/pipeline", HttpMethod.GET),

    getJGDTZL("获取监管动态总览", "http://172.16.33.135:28081/device-ai/api/Row1Col2", HttpMethod.GET),

    getDPYCWR("大屏扬尘污染模块", "http://31.8.11.112:9251/api/interface/ycwr", HttpMethod.GET),

    getDPXWZ("大屏小微站模块数据", "http://31.8.11.112:9251/api/interface/xwz", HttpMethod.GET),

    getDPTZWR("大屏特征污染模块数据", "http://31.8.11.112:9251/api/interface/tzwr", HttpMethod.GET),

    getSTHJDWT("生态环境点位图", "http://31.8.11.112:9251/api/screen/getPoints", HttpMethod.GET),

    getXWZDWXX("获取小微站点位信息", "http://31.8.11.112:9251/api/screen/xwzList", HttpMethod.GET),

    getXWZDJCSJXSSJ("获取小微站点位监测数据小时数据", "http://31.8.11.112:9251/api/screen/getPointsItem", HttpMethod.GET),

    getDPZDPWDW("大屏重点排污单位模块数据", "http://31.8.11.112:9251/api/interface/zdpwdw", HttpMethod.GET),

    getCSHJKQZL("获取城市环境空气质量API实时数据", "http://31.8.11.112:9251/api/screen/kqzlmb", HttpMethod.GET),

    // 区网格化中心   疫苗相关
    getQWGHZXToken("获取区网格化中心接口token", "http://31.8.10.221/com/getToken4Corp", HttpMethod.GET),
    getXGYMJZTJ("新冠疫苗接种统计-街镇(疫苗类型)", "http://31.8.10.221/search/httpproxy?appid=05O6M247", HttpMethod.POST),
    getXGYMJZTJ_JZ("新冠疫苗接种统计-街镇", "http://31.8.10.221/search/httpproxy?appid=0w439373", HttpMethod.POST),

    getQRKNLDTJ_QQ("区人口-分年龄段统计_全区", "http://31.8.10.221/search/httpproxy?appid=69n91A9l", HttpMethod.POST),
    getQRKNLDTJ_JZ("区人口-分年龄段统计_街镇", "http://31.8.10.221/search/httpproxy?appid=1d0ih485", HttpMethod.POST),

    ;

    private String name;
    private String url;
    private HttpMethod method;

    JinShanAPIEnum(String name, String url, HttpMethod method) {
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
