package com.jinshan.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jinshan.pojo.JinShanResult;
import com.jinshan.service.BaiDuAPIService;
import com.jinshan.util.enumerate.JinShanAPIEnum;
import com.jinshan.util.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *  对外请求远程获取道路信息的接口
 * @Auther: 小宇宙
 * @Date: 2021-05-31 17:21
 */
@RestController
@RequestMapping("baiDu")
public class BaiDuController {

    @Autowired
    private BaiDuAPIService baiDuAPIService;

    private static Logger logger = LoggerFactory.getLogger(BaiDuController.class);

    /**
     *  获取调用百度接口的token
     * @return
     */
    @GetMapping(value = "/getToken")
    public JSONObject getToken() {
        String param = "appKey=f188c007153022cac546bed4f49d8291&appSecret=5616f8d1345ac04ea451860277376659";
        String json = HttpUtils.sendGet(JinShanAPIEnum.getBaiDuToken.getUrl(), param);
        return JSONUtil.parseObj(json);
    }

    /**
     *  获取重点道路---路段排行数据接口
     * @return
     */
    @GetMapping(value = "/getLdPh")
    public JSONObject getZdDl_LdPh(HttpServletRequest request) {
        //获取请求令牌
        String token = request.getHeader("AUTHORIZATION_TOKEN") == null ? "sgcavdv-shbax" : request.getHeader("AUTHORIZATION_TOKEN");
        StringBuffer param = new StringBuffer();
        //拼接请求参数
        String nodeId = request.getParameter("nodeId");
        if (nodeId != null) {
            param.append("&nodeId="+nodeId);
        }
        String roadNetworkLevel = request.getParameter("roadNetworkLevel");
        if (roadNetworkLevel != null) {
            param.append("&roadNetworkLevel="+roadNetworkLevel);
        }
        String orderBy = request.getParameter("orderBy");
        if (orderBy != null) {
            param.append("&orderBy="+orderBy);
        }
        String searchType = request.getParameter("searchType");
        if (searchType != null) {
            param.append("&searchType="+searchType);
        }
        String datatime = request.getParameter("datatime");
        if (datatime != null) {
            param.append("&datatime="+datatime);
        }
        String datetype = request.getParameter("datetype");
        if (datetype != null) {
            param.append("&datetype="+datetype);
        }
        String timetype = request.getParameter("timetype");
        if (timetype != null) {
            param.append("&timetype="+timetype);
        }
        String hour = request.getParameter("hour");
        if (hour != null) {
            param.append("&hour="+hour);
        }
        String id = request.getParameter("id");
        if (id != null) {
            param.append("&id="+id);
        }
        String pageindex = request.getParameter("pageindex");
        if (pageindex != null) {
            param.append("&pageindex="+pageindex);
        }
        String pagecount = request.getParameter("pagecount");
        if (pagecount != null) {
            param.append("&pagecount="+pagecount);
        }
        //请求地址
        String url = JinShanAPIEnum.getZdDl_LdPh.getUrl()+param;
        HttpHeaders headers = new HttpHeaders();
        headers.add("AUTHORIZATION_TOKEN", token);//设置令牌
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = HttpUtils.sendGetheadersJSONObject(url, headers);//获取到远程接口返回的数据
            JSONObject data = jsonObject.getJSONObject("data");
            JSONObject result = data.getJSONObject("result");
            JSONArray list = result.getJSONArray("list");
            //判断是否有数据，有数据则数据入库。
            if (!list.isEmpty()) {
                System.out.println("list不为空");
                //数据入库
                baiDuAPIService.zDdlLdPhSave(result);
            }
        } catch (Exception e) {
            logger.info("获取重点道路---路段排行数据接口出现异常{}", e.getMessage());
            e.printStackTrace();
            jsonObject.set("message", "invalid token");
        }
        return jsonObject;
    }


    /**
     *  获取重点道路---道路排行数据接口
     * @return
     */
    @GetMapping(value = "/getDlPh")
    public JSONObject getZdDl_DlPh(HttpServletRequest request) {
        //获取请求令牌
        String token = request.getHeader("AUTHORIZATION_TOKEN") == null ? "sgcavdv-shbax" : request.getHeader("AUTHORIZATION_TOKEN");
        //拼接请求参数
        StringBuffer param = new StringBuffer();
        String nodeId = request.getParameter("nodeId");
        if (nodeId != null) {
            param.append("&nodeId="+nodeId);
        }
        String roadNetworkLevel = request.getParameter("roadNetworkLevel");
        if (roadNetworkLevel != null) {
            param.append("&roadNetworkLevel="+roadNetworkLevel);
        }
        String orderBy = request.getParameter("orderBy");
        if (orderBy != null) {
            param.append("&orderBy="+orderBy);
        }
        String searchType = request.getParameter("searchType");
        if (searchType != null) {
            param.append("&searchType="+searchType);
        }
        String datatime = request.getParameter("datatime");
        if (datatime != null) {
            param.append("&datatime="+datatime);
        }
        String datetype = request.getParameter("datetype");
        if (datetype != null) {
            param.append("&datetype="+datetype);
        }
        String timetype = request.getParameter("timetype");
        if (timetype != null) {
            param.append("&timetype="+timetype);
        }
        String hour = request.getParameter("hour");
        if (hour != null) {
            param.append("&hour="+hour);
        }
        String id = request.getParameter("id");
        if (id != null) {
            param.append("&id="+id);
        }
        String pageindex = request.getParameter("pageindex");
        if (pageindex != null) {
            param.append("&pageindex="+pageindex);
        }
        String pagecount = request.getParameter("pagecount");
        if (pagecount != null) {
            param.append("&pagecount="+pagecount);
        }
        //请求地址
        String url = JinShanAPIEnum.getZdDl_DlPh.getUrl()+param;
        HttpHeaders headers = new HttpHeaders();
        headers.add("AUTHORIZATION_TOKEN", token);//设置令牌
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = HttpUtils.sendGetheadersJSONObject(url, headers);//获取到远程接口返回的数据
            JSONObject data = jsonObject.getJSONObject("data");
            JSONObject result = data.getJSONObject("result");
            JSONArray list = result.getJSONArray("list");
            //判断是否有数据，有数据则数据入库。
            if (!list.isEmpty()) {
                System.out.println("list不为空");
                //数据入库
                baiDuAPIService.zddlDlPhSave(result);
            }
        } catch (Exception e) {
            logger.info("获取重点道路---道路排行数据接口出现异常{}", e.getMessage());
            e.printStackTrace();
            jsonObject.set("message", "invalid token");
        }
        return jsonObject;
    }


    /**
     *  获取重点道路---信息数据接口
     * @return
     */
    @GetMapping(value = "/getDlXX")
    public JSONObject getZdDl_XX(HttpServletRequest request) {
        //获取请求令牌
        String token = request.getHeader("AUTHORIZATION_TOKEN") == null ? "sgcavdv-shbax" : request.getHeader("AUTHORIZATION_TOKEN");
        //拼接请求参数
        StringBuffer param = new StringBuffer();
        String nodeId = request.getParameter("nodeId");
        if (nodeId != null) {
            param.append("&nodeId="+nodeId);
        }
        String roadNetworkLevel = request.getParameter("roadNetworkLevel");
        if (roadNetworkLevel != null) {
            param.append("&roadNetworkLevel="+roadNetworkLevel);
        }
        String roadId = request.getParameter("roadId");
        if (roadId != null) {
            param.append("&roadId="+roadId);
        }
        String roadsegId = request.getParameter("roadsegId");
        if (roadsegId != null) {
            param.append("&roadsegId="+roadsegId);
        }
        //请求地址
        String url = JinShanAPIEnum.getZdDl_XX.getUrl()+param;
        HttpHeaders headers = new HttpHeaders();
        headers.add("AUTHORIZATION_TOKEN", token);//设置令牌
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = HttpUtils.sendGetheadersJSONObject(url, headers);//获取到远程接口返回的数据
            JSONObject data = jsonObject.getJSONObject("data");
            JSONObject result = data.getJSONObject("result");
            JSONArray list = result.getJSONArray("list");
            //判断是否有数据，有数据则数据入库。
            if (!list.isEmpty()) {
                System.out.println("list不为空");
                //数据入库
                baiDuAPIService.zddlXxSave(result);
            }
        } catch (Exception e) {
            logger.info("获取重点道路---信息数据接口出现异常{}", e.getMessage());
            e.printStackTrace();
            jsonObject.set("message", "invalid token");
        }
        return jsonObject;
    }


    /**
     *  获取重点道路---道路拥堵指标详情接口
     * @return
     */
    @GetMapping(value = "/getDlYdZbXq")
    public JSONObject getZdDl_dlYdZbXq(HttpServletRequest request) {
        //获取请求令牌
        String token = request.getHeader("AUTHORIZATION_TOKEN") == null ? "sgcavdv-shbax" : request.getHeader("AUTHORIZATION_TOKEN");
        //拼接请求参数
        StringBuffer param = new StringBuffer();

        String nodeId = request.getParameter("nodeId");
        if (nodeId != null) {
            param.append("&nodeId="+nodeId);
        }
        String roadId = request.getParameter("roadId");
        if (roadId != null) {
            param.append("&roadId="+roadId);
        }
        String analyzeDate = request.getParameter("analyzeDate");
        if (analyzeDate != null) {
            param.append("&analyzeDate="+analyzeDate);
        }
        String analyzeDatetype = request.getParameter("analyzeDatetype");
        if (analyzeDatetype != null) {
            param.append("&analyzeDatetype="+analyzeDatetype);
        }
        String compareDate = request.getParameter("compareDate");
        if (compareDate != null) {
            param.append("&compareDate="+compareDate);
        }
        String compareDatetype = request.getParameter("compareDatetype");
        if (compareDatetype != null) {
            param.append("&compareDatetype="+compareDatetype);
        }
        String granularity = request.getParameter("granularity");
        if (granularity != null) {
            param.append("&granularity="+granularity);
        }
        String encode = request.getParameter("encode");
        if (encode != null) {
            param.append("&encode="+encode);
        }

        //请求地址
        String url = JinShanAPIEnum.getZdDl_DlYdZbXq.getUrl()+param;
        HttpHeaders headers = new HttpHeaders();
        headers.add("AUTHORIZATION_TOKEN", token);//设置令牌
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = HttpUtils.sendGetheadersJSONObject(url, headers);//获取到远程接口返回的数据
            JSONObject data = jsonObject.getJSONObject("data");
            JSONObject result = data.getJSONObject("result");
            JSONArray list = result.getJSONArray("list");
            //判断是否有数据，有数据则数据入库。
            if (!list.isEmpty()) {
                System.out.println("list不为空");
                //数据入库
                baiDuAPIService.zddlDlYdZbXqSave(result);
            }
        } catch (Exception e) {
            logger.info("获取重点道路---道路拥堵指标详情接口出现异常{}", e.getMessage());
            e.printStackTrace();
            jsonObject.set("message", "invalid token");
        }
        return jsonObject;
    }


    /**
     *  获取重点道路---路段拥堵指标详情数据接口
     * @return
     */
    @GetMapping(value = "/getLdYdZbXq")
    public JSONObject getZdDl_ldYdZbXq(HttpServletRequest request) {
        //获取请求令牌
        String token = request.getHeader("AUTHORIZATION_TOKEN") == null ? "sgcavdv-shbax" : request.getHeader("AUTHORIZATION_TOKEN");
        //拼接请求参数
        StringBuffer param = new StringBuffer();

        String nodeId = request.getParameter("nodeId");
        if (nodeId != null) {
            param.append("&nodeId="+nodeId);
        }
        String roadsegId = request.getParameter("roadsegId");
        if (roadsegId != null) {
            param.append("&roadsegId="+roadsegId);
        }
        String analyzeDate = request.getParameter("analyzeDate");
        if (analyzeDate != null) {
            param.append("&analyzeDate="+analyzeDate);
        }
        String analyzeDatetype = request.getParameter("analyzeDatetype");
        if (analyzeDatetype != null) {
            param.append("&analyzeDatetype="+analyzeDatetype);
        }
        String compareDate = request.getParameter("compareDate");
        if (compareDate != null) {
            param.append("&compareDate="+compareDate);
        }
        String compareDatetype = request.getParameter("compareDatetype");
        if (compareDatetype != null) {
            param.append("&compareDatetype="+compareDatetype);
        }
        String granularity = request.getParameter("granularity");
        if (granularity != null) {
            param.append("&granularity="+granularity);
        }
        String encode = request.getParameter("encode");
        if (encode != null) {
            param.append("&encode="+encode);
        }

        //请求地址
        String url = JinShanAPIEnum.getZdDl_LDYdZbXq.getUrl()+param;
        HttpHeaders headers = new HttpHeaders();
        headers.add("AUTHORIZATION_TOKEN", token);//设置令牌
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = HttpUtils.sendGetheadersJSONObject(url, headers);//获取到远程接口返回的数据
            JSONObject data = jsonObject.getJSONObject("data");
            JSONObject result = data.getJSONObject("result");
            JSONArray list = result.getJSONArray("list");
            //判断是否有数据，有数据则数据入库。
            if (!list.isEmpty()) {
                System.out.println("list不为空");
                //数据入库
                baiDuAPIService.zddlLdYdZbXqSave(result);
            }
        } catch (Exception e) {
            logger.info("获取重点道路---路段拥堵指标详情数据接口出现异常{}", e.getMessage());
            e.printStackTrace();
            jsonObject.set("message", "invalid token");
        }
        return jsonObject;
    }




    /**
     *  百度——路段拥堵指标详情入库   新的。
     */
    @GetMapping(value = "/ldydzbxq")
    public JinShanResult saveLDYDZBXQ() {
        String param = "appKey=f188c007153022cac546bed4f49d8291&appSecret=5616f8d1345ac04ea451860277376659";
        String json = HttpUtils.sendGet(JinShanAPIEnum.getBaiDuToken.getUrl(), param);
        JSONObject tokenObject = JSONUtil.parseObj(json);
        JSONObject data = tokenObject.getJSONObject("data");

        String token = data.getStr("token"); //获取到token

        List<String> idList = baiDuAPIService.selectLDID();
        int success = 0;
        Date date = new Date();
        for (int i=0; i<idList.size(); i++) {
            //拼接请求参数
            StringBuffer buffer = new StringBuffer();

            buffer.append("&nodeId="+"5062");      //辖区ID

            String roadsegId = idList.get(i);
            buffer.append("&roadsegId="+roadsegId);   //路段id  需要从数据库中查询

            buffer.append("&analyzeDate="+"20210621");      //分析日期

            //请求地址
            String url = JinShanAPIEnum.getZdDl_LDYdZbXq.getUrl()+buffer;
            HttpHeaders headers = new HttpHeaders();
            headers.add("AUTHORIZATION_TOKEN", token);  //设置令牌

            JSONObject jsonObject = HttpUtils.sendGetheadersJSONObject(url, headers);//获取到远程接口返回的数据

            JSONObject resultData = jsonObject.getJSONObject("data");
            JSONObject result = resultData.getJSONObject("result");

            JSONArray list = result.getJSONArray("list");
            if (!list.isEmpty()) {
                System.out.println("list不为空");
                //数据入库
                success += baiDuAPIService.saveLDYDZBXQ(result, date);
            }

        }

        return new JinShanResult("200", "百度——路段拥堵指标详情入库", "百度——路段拥堵指标详情入库了"+success);
    }












}
