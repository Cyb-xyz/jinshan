package com.jinshan.controller;


import com.jinshan.pojo.JinShanResult;
import com.jinshan.service.GaoDeService;
import com.jinshan.util.download.DownLoadFileUtil;
import com.jinshan.util.download.ReadTxtData;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Auther: 小宇宙
 * @Date: 2021-06-18 17:29
 */
@RestController
@RequestMapping("gd")
public class GaoDeController {

    @Autowired
    private GaoDeService gaoDeService;

    @Value("${file.unZipPath}")
    private String zipPath;     //压缩包存储位置

    @Value("${file.outPath}")
    private String out_path;     //解压的数据文件存储位置

    private static Logger logger = LoggerFactory.getLogger(GaoDeController.class);

    /**
     *  高德_交通路况信息入库
     */
    @GetMapping(value = "/saveJtLkXX")
    public JinShanResult saveJtLkXX() throws Exception {
        //高德文件接口1.1请求地址
        String url = gd1();
        logger.info("高德_交通路况信息接口地址:{}", url);
        // /opt/jinshan/gaode/zip
//        String unZipPath = "/opt/jinshan/gaode/zip/";     //压缩文件存储的位置
        String unZipPath = zipPath;     //压缩文件存储的位置
        String zipFileName = "areaJSONPub";   //压缩文件的名称
        // 下载压缩包
        String s = DownLoadFileUtil.downloadFile(url, unZipPath, zipFileName+".zip");

        //解压压缩包
        String zipPath = unZipPath + zipFileName + ".zip";    //需要解压的压缩文件
        //   /opt/jinshan/gaode/data
//        String outPath = "/opt/jinshan/gaode/data/"+zipFileName+".txt";//解压完成后保存路径
        String outPath = out_path + zipFileName+".txt";//解压完成后保存路径
        DownLoadFileUtil.unpackFile(zipPath, outPath);

        //读取解压文件中的数据
        String gaoDeData = ReadTxtData.readTxtToJson(outPath);
        int success = gaoDeService.saveJtLkXX(gaoDeData);
        logger.info("入库了数据:{}条数据", success);
        return new JinShanResult("200", "高德_交通路况信息入库", "本次入库了"+success+"条数据");

    }

    /**
     *  高德_交通路况道路ID信息入库
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/saveJtLkIDXX")
    public JinShanResult saveJtLkIDXX() throws Exception {
        String url = gd2();             //高德文件接口1.2请求地址
        logger.info("高德_交通路况道路ID信息接口地址:{}", url);
//        String unZipPath = "/opt/jinshan/gaode/zip/";     //压缩文件存储的位置
        String unZipPath = zipPath;     //压缩文件存储的位置
        String zipFileName = "areaLinkPub";   //压缩文件的名称

        // 下载压缩包
        String s = DownLoadFileUtil.downloadFile(url, unZipPath, zipFileName+".zip");
        System.out.println(s);

        //解压压缩包
        String zipPath = unZipPath + zipFileName + ".zip";      //需要解压的压缩文件
//        String outPath = "/opt/jinshan/gaode/data/"+zipFileName+".txt";    //解压完成后保存路径
        String outPath = out_path + zipFileName+".txt";//解压完成后保存路径
        DownLoadFileUtil.unpackFile(zipPath,  outPath);

        //读取解压文件中的数据
        String gaoDeData = ReadTxtData.readTxtToJson(outPath);  //接口返回的数据字符串

        int success = gaoDeService.saveJtLkIDXX(gaoDeData);
        logger.info("入库了数据:{}条数据", success);
        return new JinShanResult("200", "高德_交通路况道路ID信息入库", "本次入库了"+success+"条数据");
    }







    /**
     *  获取高德接口1.1请求地址
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

    /**
     *  获取高德接口1.2请求地址
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



}
