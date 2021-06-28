package com.jinshan;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jinshan.service.CsYjAPIService;
import com.jinshan.service.CsYxAPIService;
import com.jinshan.service.GaoDeService;
import com.jinshan.util.TokenUtils;
import com.jinshan.util.date.DateUtils;
import com.jinshan.util.download.DownLoadFileUtil;
import com.jinshan.util.download.ReadTxtData;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//@SpringBootTest
class JinshaninterApplicationTests {

    @Autowired
    private CsYxAPIService csYxAPIService;
    @Autowired
    private CsYjAPIService csYjAPIService;
    @Autowired
    private GaoDeService gaoDeService;

    //获取令牌
    @Test
    void contextLoads() {
        String token = TokenUtils.getToken("12345", "abcde");
        System.out.println("获取到的令牌:"+token);
    }

    @Test
    void test1() {
//        csYxAPIService.getSyRkData();
//        csYjAPIService.getCsYjGaData();
//        csYxAPIService.getCsJtData();
        //csYxAPIService.getCshJdqData();
    }

    @Test
    void jsonTest() {
//        String name = "{\"status\":0,\"message\":\"成功\",\"result\":{\"list\":[{\"rank\":1,\"roadsegId\":\"6-2-体育场北环路-0-0\",\"roadname\":\"体育场北环路\",\"semantic\":\"从体育场东环路到杭州湾大道,南向西\",\"congestIndex\":\"1.519\",\"congestSpeed\":\"39.013\",\"congestLength\":\"0\",\"roadNetworkName\":\"重点道路\",\"sparseLoc\":\"121.337964,30.793452,121.33219,30.79352,121.331478,30.793498,121.330958,30.793413\",\"roadsegIdBase64\":\"Ni0yLeS9k+iCsuWcuuWMl+eOr+i3ry0wLTA=\"}," +
//                "{\"rank\":2,\"roadsegId\":\"6-2-龙堰路S63689177040-636891-0\",\"roadname\":\"龙堰路\",\"semantic\":\"从八树桥到卫山河三号桥,东向西\",\"congestIndex\":\"1.315\",\"congestSpeed\":\"28.067\",\"congestLength\":\"0\",\"roadNetworkName\":\"重点道路\",\"sparseLoc\":\"121.351557,30.769346,121.349224,30.769246,121.346927,30.76918,121.346312,30.769094,121.345812,30.76895,121.343241,30.768135,121.338687,30.766492,121.335961,30.765598,121.33259,30.764464,121.328835,30.763126\",\"roadsegIdBase64\":\"Ni0yLem+meWgsOi3r1M2MzY4OTE3NzA0MC02MzY4OTEtMA==\"}]}}";
//        JSONObject object = JSONUtil.parseObj(name);
//        String status = object.getStr("status");
//        System.out.println(status);
//        JSONObject object1 = object.getJSONObject("result");
//        System.out.println(object1.toString());
//        JSONArray jsonArray = object1.getJSONArray("list");
//
//        for (Object o : jsonArray) {
//            System.out.println(o);
//        }
    }

    /**
     *  高德1.2接口
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     */
    @Test
    void test2() throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String clientSecret = "ac707513d40ed8faf692d7fb931c4ca8";//该值为申请到的client Secret
        HashMap<String, Object> mapParam = new HashMap<>();
        mapParam.put("timestamp",System.currentTimeMillis() / 1000);
        mapParam.put("clientKey","737848e0faf317700c088b59d75dd68a");
        mapParam.put("pubname","737848e0faf317700c088b59d75dd68a");
//        mapParam.put("adcode","110000");

        Map<String, Object> map = sortByKey(mapParam);


        StringBuilder valueJoinStr = new StringBuilder("");
        StringBuilder paramJoinStr = new StringBuilder("");
        for (Map.Entry<String, Object> objectEntry : map.entrySet()) {
            valueJoinStr.append(objectEntry.getValue());
            paramJoinStr.append(String.format("%s=%s&",objectEntry.getKey(),objectEntry.getValue()));
        }
        paramJoinStr.substring(0, paramJoinStr.length() - 1);
        String url = "http://et-api.amap.com/state/areaLinkPub?";
        String apiPath = url + paramJoinStr;

        Mac mac = Mac.getInstance("HmacSHA256");
        byte[] secretByte = clientSecret.getBytes("UTF-8");
        byte[] dataBytes = valueJoinStr.toString().getBytes("UTF-8");
        SecretKey secretKey = new SecretKeySpec(secretByte, "HMACSHA256");
        mac.init(secretKey);
        byte[] doFinal = mac.doFinal(dataBytes);
        byte[] hexB = new Hex().encode(doFinal);
        String digest = new String(hexB, "utf-8");//计算好的签名  26f2042cde2e9ca01d4lecdb27a91fd9b84f0263c411bcb13b195ec589096885
        System.out.println(digest);
        String resultUrl = apiPath + "&digest=" + digest;
        System.out.println(resultUrl);

    }


    /**
     *  获取高德接口1.2请求地址
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     */
    public static String gd2() throws Exception   {
        String clientSecret = "ac707513d40ed8faf692d7fb931c4ca8";   //该值为申请到的client Secret
        HashMap<String, Object> mapParam = new HashMap<>();
        mapParam.put("timestamp",System.currentTimeMillis() / 1000);
        mapParam.put("clientKey","737848e0faf317700c088b59d75dd68a");
        mapParam.put("pubname","737848e0faf317700c088b59d75dd68a");
//        mapParam.put("adcode","110000");

        Map<String, Object> map = sortByKey(mapParam);


        StringBuilder valueJoinStr = new StringBuilder("");
        StringBuilder paramJoinStr = new StringBuilder("");
        for (Map.Entry<String, Object> objectEntry : map.entrySet()) {
            valueJoinStr.append(objectEntry.getValue());
            paramJoinStr.append(String.format("%s=%s&",objectEntry.getKey(),objectEntry.getValue()));
        }
        paramJoinStr.substring(0, paramJoinStr.length() - 1);
        String url = "http://et-api.amap.com/state/areaLinkPub?";
        String apiPath = url + paramJoinStr;

        Mac mac = Mac.getInstance("HmacSHA256");
        byte[] secretByte = clientSecret.getBytes("UTF-8");
        byte[] dataBytes = valueJoinStr.toString().getBytes("UTF-8");
        SecretKey secretKey = new SecretKeySpec(secretByte, "HMACSHA256");
        mac.init(secretKey);
        byte[] doFinal = mac.doFinal(dataBytes);
        byte[] hexB = new Hex().encode(doFinal);
        String digest = new String(hexB, "utf-8");//计算好的签名  26f2042cde2e9ca01d4lecdb27a91fd9b84f0263c411bcb13b195ec589096885
        System.out.println(digest);
        return apiPath + "&digest=" + digest;

    }
    /**
     * map 按 key 升序排序
     */
    public static Map<String, Object> sortByKey(Map<String, Object> map) {
        Map<String, Object> result = new LinkedHashMap<>(map.size());
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

    @Test
    void test4() throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException  {
        long timestamp = System.currentTimeMillis() / 1000;
        System.out.println(timestamp);
        String paramValuesStr = "737848e0faf317700c088b59d75dd68a1000"+timestamp; //该值为排好序的参数的参数值拼接结果
        String clientSecret = "ac707513d40ed8faf692d7fb931c4ca8";  //该值为申请到的client Secret
        Mac mac = Mac.getInstance("HmacSHA256");
        byte[] secretByte = clientSecret.getBytes("UTF-8");
        byte[] dataBytes = paramValuesStr.getBytes("UTF-8");
        SecretKey secretKey = new SecretKeySpec(secretByte, "HMACSHA256");
        mac.init(secretKey);
        byte[] doFinal = mac.doFinal(dataBytes);
        byte[] hexB = new Hex().encode(doFinal);
        String digest = new String(hexB, "utf-8");//计算好的签名  26f2042cde2e9ca01d4lecdb27a91fd9b84f0263c411bcb13b195ec589096885
        System.out.println(digest);
        String resultUrl = "http://et-api.amap.com/index/cityRanking?clientKey=737848e0faf317700c088b59d75dd68a&size=1000&timestamp="+timestamp+"&digest="+digest;
        System.out.println(resultUrl);
    }


    @Test
    void test5() throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException  {

        String clientSecret = "ac707513d40ed8faf692d7fb931c4ca8";   //该值为申请到的client Secret
        HashMap<String, Object> mapParam = new HashMap<>();
        mapParam.put("timestamp",System.currentTimeMillis() / 1000);
        mapParam.put("clientKey","737848e0faf317700c088b59d75dd68a");
        mapParam.put("pubname","737848e0faf317700c088b59d75dd68a");

        Map<String, Object> map = sortByKey(mapParam);
        StringBuilder valueJoinStr = new StringBuilder("");
        StringBuilder paramJoinStr = new StringBuilder("");
        for (Map.Entry<String, Object> objectEntry : map.entrySet()) {
            valueJoinStr.append(objectEntry.getValue());
            paramJoinStr.append(String.format("%s=%s&",objectEntry.getKey(),objectEntry.getValue()));
        }

        paramJoinStr.substring(0, paramJoinStr.length() - 1);
        String url = "http://et-api.amap.com/state/areaJSONPub?";
        String apiPath = url + paramJoinStr;

        Mac mac = Mac.getInstance("HmacSHA256");
        byte[] secretByte = clientSecret.getBytes("UTF-8");
        byte[] dataBytes = valueJoinStr.toString().getBytes("UTF-8");
        SecretKey secretKey = new SecretKeySpec(secretByte, "HMACSHA256");
        mac.init(secretKey);
        byte[] doFinal = mac.doFinal(dataBytes);
        byte[] hexB = new Hex().encode(doFinal);
        String digest = new String(hexB, "utf-8");//计算好的签名  26f2042cde2e9ca01d4lecdb27a91fd9b84f0263c411bcb13b195ec589096885
        System.out.println(digest);
        String resultUrl = apiPath + "&digest=" + digest;
        System.out.println(resultUrl);
    }


    /**
     *  获取高德接口1.1请求地址
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     */
    public static String gd1() throws Exception   {
        String clientSecret = "ac707513d40ed8faf692d7fb931c4ca8";   //该值为申请到的client Secret
        HashMap<String, Object> mapParam = new HashMap<>();
        mapParam.put("timestamp",System.currentTimeMillis() / 1000);
        mapParam.put("clientKey","737848e0faf317700c088b59d75dd68a");
        mapParam.put("pubname","737848e0faf317700c088b59d75dd68a");

        Map<String, Object> map = sortByKey(mapParam);
        StringBuilder valueJoinStr = new StringBuilder("");
        StringBuilder paramJoinStr = new StringBuilder("");
        for (Map.Entry<String, Object> objectEntry : map.entrySet()) {
            valueJoinStr.append(objectEntry.getValue());
            paramJoinStr.append(String.format("%s=%s&",objectEntry.getKey(),objectEntry.getValue()));
        }

        paramJoinStr.substring(0, paramJoinStr.length() - 1);
        String url = "http://et-api.amap.com/state/areaJSONPub?";
        String apiPath = url + paramJoinStr;

        Mac mac = Mac.getInstance("HmacSHA256");
        byte[] secretByte = clientSecret.getBytes("UTF-8");
        byte[] dataBytes = valueJoinStr.toString().getBytes("UTF-8");
        SecretKey secretKey = new SecretKeySpec(secretByte, "HMACSHA256");
        mac.init(secretKey);
        byte[] doFinal = mac.doFinal(dataBytes);
        byte[] hexB = new Hex().encode(doFinal);
        String digest = new String(hexB, "utf-8");//计算好的签名  26f2042cde2e9ca01d4lecdb27a91fd9b84f0263c411bcb13b195ec589096885
        System.out.println(digest);
        return apiPath + "&digest=" + digest;
    }

    // 高德接口1.2，下载文件并解压文件
    @Test
    void test6() throws Exception {
        String url = gd2();             //高德文件接口1.2请求地址
        System.out.println(url);
        String unZipPath = "D:/gaode/zip/";     //压缩文件存储的位置
        String zipFileName = "areaLinkPub";   //压缩文件的名称
        System.out.println(zipFileName);
        // 下载压缩包
        String s = DownLoadFileUtil.downloadFile(url, unZipPath, zipFileName+".zip");
        System.out.println(s);

        //解压压缩包
        String zipPath = unZipPath + zipFileName + ".zip";    //需要解压的压缩文件
        String outPath = "D:\\gaode\\data\\"+zipFileName+".txt";    //解压完成后保存路径
        DownLoadFileUtil.unpackFile(zipPath,  outPath);

        //读取解压文件中的数据
        String gaoDeData = ReadTxtData.readTxtToJson(outPath);  //接口返回的数据字符串

        JSONObject jsonObject = JSONObject.parseObject(gaoDeData);

        JSONArray jsonArray = jsonObject.getJSONArray("linkCoordList");
        System.out.println("数据条数："+jsonArray.size());
        System.out.println("第一个数据："+jsonArray.get(0).toString());

        String name = jsonObject.getString("name");
        System.out.println("name："+name);
        String utcSeconds = jsonObject.getString("utcSeconds");
        System.out.println("utcSeconds："+utcSeconds);

        int success = gaoDeService.saveJtLkIDXX(gaoDeData);
        System.out.println("入库数据条数:"+success);

    }

    // 高德接口1.1，下载文件并解压文件
    @Test
    void test7() throws Exception {
        String url = gd1();             //高德文件接口1.1请求地址
        System.out.println(url);
        String unZipPath = "D:/gaode/zip/";     //压缩文件存储的位置
        String zipFileName = "areaJSONPub";   //压缩文件的名称
        System.out.println(zipFileName);
        // 下载压缩包
        String s = DownLoadFileUtil.downloadFile(url, unZipPath, zipFileName+".zip");
        System.out.println(s);

        //解压压缩包
        String zipPath = unZipPath + zipFileName + ".zip";    //需要解压的压缩文件
        String outPath = "D:\\gaode\\data\\"+zipFileName+".txt";//解压完成后保存路径
        DownLoadFileUtil.unpackFile(zipPath,  outPath);


        //读取解压文件中的数据
        String gaoDeData = ReadTxtData.readTxtToJson(outPath);
        JSONObject jsonObject = JSONObject.parseObject(gaoDeData);
        String autolrDataVersion = jsonObject.getString("autolrDataVersion");
        System.out.println("autolrDataVersion："+autolrDataVersion);

        JSONArray jsonArray = jsonObject.getJSONArray("linkStates");
        System.out.println("数据条数："+jsonArray.size());

        System.out.println("第一个数据："+jsonArray.get(0).toString());
        System.out.println("第一个数据："+jsonArray.get(jsonArray.size()-1).toString());

        String name = jsonObject.getString("name");
        System.out.println("name："+name);
        String utcSeconds = jsonObject.getString("utcSeconds");
        System.out.println("utcSeconds："+utcSeconds);

        int success = gaoDeService.saveJtLkXX(gaoDeData);
        System.out.println("入库了数据:"+success);


    }

    /**
     *  获取 交通指数-实时-道路指数排行---接口  的签名
     */
    @Test
    void test8() throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String clientSecret = "ac707513d40ed8faf692d7fb931c4ca8";//该值为申请到的client Secret
        HashMap<String, Object> mapParam = new HashMap<>();
        mapParam.put("timestamp",System.currentTimeMillis() / 1000);
        mapParam.put("clientKey","737848e0faf317700c088b59d75dd68a");
        mapParam.put("adcode","310116");
        mapParam.put("type","2");

        Map<String, Object> map = sortByKey(mapParam);


        StringBuilder valueJoinStr = new StringBuilder("");
        StringBuilder paramJoinStr = new StringBuilder("");
        for (Map.Entry<String, Object> objectEntry : map.entrySet()) {
            valueJoinStr.append(objectEntry.getValue());
            paramJoinStr.append(String.format("%s=%s&",objectEntry.getKey(),objectEntry.getValue()));
        }
        paramJoinStr.substring(0, paramJoinStr.length() - 1);
        String url = "http://et-api.amap.com/state/areaLinkPub?";
        String apiPath = url + paramJoinStr;

        Mac mac = Mac.getInstance("HmacSHA256");
        byte[] secretByte = clientSecret.getBytes("UTF-8");
        byte[] dataBytes = valueJoinStr.toString().getBytes("UTF-8");
        SecretKey secretKey = new SecretKeySpec(secretByte, "HMACSHA256");
        mac.init(secretKey);
        byte[] doFinal = mac.doFinal(dataBytes);
        byte[] hexB = new Hex().encode(doFinal);
        String digest = new String(hexB, "utf-8");//计算好的签名  26f2042cde2e9ca01d4lecdb27a91fd9b84f0263c411bcb13b195ec589096885
        System.out.println(digest);
        String resultUrl = apiPath + "&digest=" + digest;
        System.out.println(resultUrl);

    }


    /**
     *  获取  高德 交通指数-历史-道路指数排行---接口  的签名
     */
    @Test
    void test9() throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String clientSecret = "ac707513d40ed8faf692d7fb931c4ca8";//该值为申请到的client Secret
        HashMap<String, Object> mapParam = new HashMap<>();


        mapParam.put("dateRangeList","2021-05-10@2021-05-17,2021-06-01@2021-06-07");
        mapParam.put("timePeriodList","mpeak");
        mapParam.put("dayType","workday");
        mapParam.put("timeGrading","day");

        mapParam.put("adcode","110000");
        mapParam.put("type","2");
        mapParam.put("clientKey","737848e0faf317700c088b59d75dd68a");
        mapParam.put("timestamp",System.currentTimeMillis() / 1000);

        Map<String, Object> map = sortByKey(mapParam);


        StringBuilder valueJoinStr = new StringBuilder("");
        StringBuilder paramJoinStr = new StringBuilder("");
        for (Map.Entry<String, Object> objectEntry : map.entrySet()) {
            valueJoinStr.append(objectEntry.getValue());
            paramJoinStr.append(String.format("%s=%s&",objectEntry.getKey(),objectEntry.getValue()));
        }
        paramJoinStr.substring(0, paramJoinStr.length() - 1);

        System.out.println(paramJoinStr);

        String url = "http://xxxxxx?";
        String apiPath = url + paramJoinStr;

        Mac mac = Mac.getInstance("HmacSHA256");
        byte[] secretByte = clientSecret.getBytes("UTF-8");
        byte[] dataBytes = valueJoinStr.toString().getBytes("UTF-8");
        SecretKey secretKey = new SecretKeySpec(secretByte, "HMACSHA256");
        mac.init(secretKey);
        byte[] doFinal = mac.doFinal(dataBytes);
        byte[] hexB = new Hex().encode(doFinal);
        String digest = new String(hexB, "utf-8");//计算好的签名  26f2042cde2e9ca01d4lecdb27a91fd9b84f0263c411bcb13b195ec589096885

        System.out.println(digest);

        String resultUrl = apiPath + "&digest=" + digest;
        System.out.println(resultUrl);

    }


    /**
     *  获取  交通指数-详情-道路指数详情--接口  的签名
     */
    @Test
    void test10() throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String clientSecret = "ac707513d40ed8faf692d7fb931c4ca8";//该值为申请到的client Secret
        HashMap<String, Object> mapParam = new HashMap<>();


        mapParam.put("dateRangeList","2021-05-10@2021-05-17,2021-06-01@2021-06-07");
        mapParam.put("timePeriodList","mpeak");
        mapParam.put("dayType","workday");
        mapParam.put("timeGrading","day");
        mapParam.put("ids","1393");

        mapParam.put("adcode","310116");
        mapParam.put("type","2");
        mapParam.put("clientKey","737848e0faf317700c088b59d75dd68a");
        mapParam.put("timestamp",System.currentTimeMillis() / 1000);

        Map<String, Object> map = sortByKey(mapParam);


        StringBuilder valueJoinStr = new StringBuilder("");
        StringBuilder paramJoinStr = new StringBuilder("");
        for (Map.Entry<String, Object> objectEntry : map.entrySet()) {
            valueJoinStr.append(objectEntry.getValue());
            paramJoinStr.append(String.format("%s=%s&",objectEntry.getKey(),objectEntry.getValue()));
        }
        paramJoinStr.substring(0, paramJoinStr.length() - 1);

        System.out.println(paramJoinStr);

        String url = "http://xxxxxx?";
        String apiPath = url + paramJoinStr;

        Mac mac = Mac.getInstance("HmacSHA256");
        byte[] secretByte = clientSecret.getBytes("UTF-8");
        byte[] dataBytes = valueJoinStr.toString().getBytes("UTF-8");
        SecretKey secretKey = new SecretKeySpec(secretByte, "HMACSHA256");
        mac.init(secretKey);
        byte[] doFinal = mac.doFinal(dataBytes);
        byte[] hexB = new Hex().encode(doFinal);
        String digest = new String(hexB, "utf-8");//计算好的签名  26f2042cde2e9ca01d4lecdb27a91fd9b84f0263c411bcb13b195ec589096885

        System.out.println(digest);

        String resultUrl = apiPath + "&digest=" + digest;
        System.out.println(resultUrl);

    }


    @Test
    void test11() {
        String coor = "121.33556663990021,30.764143466949463,121.33636057376862,30.76244294643402";
        String urlString = "http://restapi.amap.com/v3/geocode/regeo?key=8325164e247e15eea68b59e89200988b&s=rsv3&location="+coor+"&radius=2800&callback=jsonp_452865_&platform=JS&logversion=2.0&sdkversion=1.3&appname=http%3A%2F%2Flbs.amap.com%2Fconsole%2Fshow%2Fpicker&csid=49851531-2AE3-4A3B-A8C8-675A69BCA316";
        String res = "";
        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line+"\n";
            }
            in.close();
        } catch (Exception e) {
            System.out.println("error in wapaction,and e is " + e.getMessage());
        }
        System.out.println(res);
    }

    @Test
    void test12() {
        String queryDate = "2021062500";
        String startDate = DateUtils.addHourToDate(-1, queryDate, "yyyyMMddHH");
        System.out.println(startDate);
    }
}
