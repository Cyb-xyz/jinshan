package com.jinshan.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinshan.dao.WGHZXMapper;
import com.jinshan.pojo.RenKouFD;
import com.jinshan.service.WGHZXAPIService;
import com.jinshan.util.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *  区网格化中心指标服务层接口实现类
 * @Auther: 小宇宙
 * @Date: 2021-06-23 18:53
 */
@Service
public class WGHZXAPIServiceImpl implements WGHZXAPIService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private WGHZXMapper wghzxMapper;

    private static Logger logger = LoggerFactory.getLogger(WGHZXAPIServiceImpl.class);

    /**
     *  新冠疫苗接种统计-街镇(疫苗类型)入库
     */
    @Override
    public int saveXGYMJZTJ(String jsonStr) {
        logger.info("进入新冠疫苗接种统计-街镇(疫苗类型)入库服务层");

        int number = 0;
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);

        if (jsonObject != null) {

            JSONArray data = jsonObject.getJSONArray("data");
            Date date = new Date();//数据入库时间

            for (int i=0; i<data.size(); i++) {
                JSONObject object = data.getJSONObject(i);
                if (object != null) {
                    String jz_jzrs = object.getString("jz_jzrs") == null ? "" : object.getString("jz_jzrs");
                    String jz_jzrs_2 = object.getString("jz_jzrs_2") == null ? "" : object.getString("jz_jzrs_2");
                    String jz_jzrs_1 = object.getString("jz_jzrs_1") == null ? "" : object.getString("jz_jzrs_1");
                    String ymlx = object.getString("ymlx") == null ? "" : object.getString("ymlx");
                    String region_name = object.getString("region_name") == null ? "" : object.getString("region_name");
                    String region_code = object.getString("region_code") == null ? "" : object.getString("region_code");

                    Map<String, Object> map = new HashMap<>();

                    map.put("ID", idWorker.nextId()+"");
                    map.put("YMLX", ymlx);
                    map.put("REGION_CODE", region_code);    //街镇编码
                    map.put("REGION_NAME", region_name);    //街镇名称
                    map.put("JZ_JZRS_1", jz_jzrs_1);    //应接种人数-第一针
                    map.put("JZ_JZRS_2", jz_jzrs_2);    //应接种人数-第二针
                    map.put("JZ_JZRS", jz_jzrs);    //应接种人数
                    map.put("CREATE_TIME", date);

                    number += wghzxMapper.saveXGYMJZTJ(map);
                }
            }


        }
        return number;
    }


    /**
     *  新冠疫苗接种统计-街镇入库
     */
    @Override
    public int saveXGYMJZTJ_JZ(String jsonStr) {
        logger.info("进入新冠疫苗接种统计-街镇入库服务层");
        int number = 0;
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        if (jsonObject != null) {

            JSONArray data = jsonObject.getJSONArray("data");
            Date date = new Date();     //数据入库时间

            for (int i=0; i<data.size(); i++) {

                JSONObject object = data.getJSONObject(i);

                if (object != null) {

                    String yjjzrs = object.getString("yjjzrs") == null ? "" : object.getString("yjjzrs");
                    String cjbz_3 = object.getString("cjbz_3") == null ? "" : object.getString("cjbz_3");
                    String cjbz_2 = object.getString("cjbz_2") == null ? "" : object.getString("cjbz_2");
                    String cjbz_1 = object.getString("cjbz_1") == null ? "" : object.getString("cjbz_1");
                    String cjbz_0 = object.getString("cjbz_0") == null ? "" : object.getString("cjbz_0");
                    String cjbz_7 = object.getString("cjbz_7") == null ? "" : object.getString("cjbz_7");
                    String cjbz_6 = object.getString("cjbz_6") == null ? "" : object.getString("cjbz_6");
                    String cjbz_5 = object.getString("cjbz_5") == null ? "" : object.getString("cjbz_5");
                    String cjbz_4 = object.getString("cjbz_4") == null ? "" : object.getString("cjbz_4");
                    String yjjzrs_75 = object.getString("yjjzrs_75") == null ? "" : object.getString("yjjzrs_75");
                    String jz_jzrs = object.getString("jz_jzrs") == null ? "" : object.getString("jz_jzrs");
                    String jz_jzrs_2 = object.getString("jz_jzrs_2") == null ? "" : object.getString("jz_jzrs_2");
                    String jz_jzrs_1 = object.getString("jz_jzrs_1") == null ? "" : object.getString("jz_jzrs_1");
                    String jz_jzrs_One = object.getString("jz_jzrs_One") == null ? "" : object.getString("jz_jzrs_One");
                    String jz_cqwjz = object.getString("jz_cqwjz") == null ? "" : object.getString("jz_cqwjz");
                    String region_name = object.getString("region_name") == null ? "" : object.getString("region_name");
                    String jz_jzwc_610 = object.getString("jz_jzwc_610") == null ? "" : object.getString("jz_jzwc_610");
                    String jz_jzwc = object.getString("jz_jzwc") == null ? "" : object.getString("jz_jzwc");
                    String region_code = object.getString("region_code") == null ? "" : object.getString("region_code");

                    Map<String, Object> map = new HashMap<>();

                    map.put("ID", idWorker.nextId()+"");
                    map.put("REGION_CODE", region_code);    //街镇编码
                    map.put("REGION_NAME", region_name);    //街镇名称
                    map.put("YJJZRS", yjjzrs);              //应接种居住人数
                    map.put("JZ_JZRS", jz_jzrs);            //接种人数
                    map.put("JZ_JZRS_1", jz_jzrs_1);        //接种人数_第一针
                    map.put("JZ_JZRS_2", jz_jzrs_2);        //接种人数_第二针
                    map.put("JZ_JZRS_ONE", jz_jzrs_One);    //接种人数_只需接种一针（康希诺）
                    map.put("YJJZRS_75", yjjzrs_75);        //75岁以上应接种人数
                    map.put("JZ_JZWC", jz_jzwc);    //接种完成
                    map.put("CJBZ_0", cjbz_0);      //未排摸
                    map.put("CJBZ_1", cjbz_1);      //暂缓接种
                    map.put("CJBZ_2", cjbz_2);      //无接种意愿
                    map.put("CJBZ_3", cjbz_3);      //外省市接种
                    map.put("CJBZ_4", cjbz_4);      //有意愿接种还未接种（含第二针预约
                    map.put("CJBZ_5", cjbz_5);      //失联
                    map.put("CJBZ_6", cjbz_6);      //境外接种
                    map.put("CJBZ_7", cjbz_7);      //本市接种(平台无数据)
                    map.put("JZ_CQWJZ", jz_cqwjz);  //超期未接种
                    map.put("JZ_JZWC_610", jz_jzwc_610);    //6.10号以后累计完成数

                    map.put("CREATE_TIME", date);   //数据入库时间

                    number += wghzxMapper.saveXGYMJZTJ_JZ(map);

                }
            }

        }
        return number;
    }

    /**
     *  区人口-分年龄段统计全区数据入库
     */
    @Override
    public int saveQRKNLDTJ_QQ(String jsonStr) {
        logger.info("进入区人口-分年龄段统计(全区)数据入库服务层");
        int number = 0;
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        if (jsonObject != null) {
            JSONArray data = jsonObject.getJSONArray("data");
            for (int i=0; i<data.size(); i++) {
                String dataStr = data.getString(i);
                //将json字段字符串转换成Map集合
                Map<String, Object> map = JSONObject.parseObject(dataStr, Map.class);

                Date date = new Date();//数据入库时间

                logger.info("区人口-分年龄段统计(全区)数据入库");
                for (Map.Entry<String, Object> entry : map.entrySet()) {

                    Map<String, Object> paramMap = new HashMap<>();

                    paramMap.put("ID", idWorker.nextId()+"");
                    paramMap.put("REGIONNAME", "全区");      //街镇名称
                    paramMap.put("AGE", entry.getKey());    //年龄段
                    paramMap.put("NUM", entry.getValue());  //人数
                    paramMap.put("CREATE_TIME", date);      //数据入库时间

                    number += wghzxMapper.saveQRKNLDTJ(paramMap);
                }
            }
        }

        return number;
    }


    /**
     *  区人口-分年龄段统计(街镇)数据入库
     */
    @Override
    public int saveQRKNLDTJ_JZ(String jsonStr) {
        logger.info("进入区人口-分年龄段统计(街镇)数据入库服务层");
        int number = 0;
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        if (jsonObject != null) {

            JSONArray data = jsonObject.getJSONArray("data");

            Date date = new Date(); //数据入库时间

            for (int i=0; i<data.size(); i++) {

                String objectStr = data.getString(i);
                //将json字段字符串转换成Map集合
                Map<String, Object> map = JSONObject.parseObject(objectStr, Map.class);
                //获取街镇名称
                String RegionName = String.valueOf(map.get("RegionName") == null ? "" : map.get("RegionName"));
                // map集合中移除 RegionName 这个值
                Iterator<String> iter = map.keySet().iterator();
                while(iter.hasNext()){
                    if(iter.next().equals("RegionName")){
                        iter.remove();
                    }
                }
                logger.info("区人口-分年龄段统计(街镇)数据入库");
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    Map<String, Object> paramMap = new HashMap<>();

                    paramMap.put("ID", idWorker.nextId()+"");
                    paramMap.put("REGIONNAME", RegionName);     //街镇名称
                    paramMap.put("AGE", entry.getKey());        //年龄段
                    paramMap.put("NUM", entry.getValue());      //人数
                    paramMap.put("CREATE_TIME", date);          //数据入库时间

                    number += wghzxMapper.saveQRKNLDTJ(paramMap);
                }

            }

        }
        return number;
    }


    /**
     *  获取区人口-分年龄段统计（街镇）数据
     */
    @Override
    public List<Map<String, String>> getQRKNLDTJ_JZ() {
        logger.info("进入获取区人口-分年龄段统计（街镇）数据------>服务层");

        List<Map<String, String>> resultList = new ArrayList<>();

        // 获取道最新的街镇名称
        List<String> newREGIONNAME = wghzxMapper.getNewREGIONNAME();
        for (int i=0; i<newREGIONNAME.size(); i++) {

            String name = newREGIONNAME.get(i);

            List<RenKouFD> ryfdData = wghzxMapper.getJZRYFDSJ(name);

            Map<String,String> map = new HashMap<>();
            map.put("RegionName", name);    //添加街镇名称

            for (int j=0; j<ryfdData.size(); j++) {
                RenKouFD data = ryfdData.get(j);
                map.put(data.getAGE(), data.getNUM());
            }
            resultList.add(map);

        }
        return resultList;
    }

    /**
     *  根据街道名称获取街镇最新的人员分段数据
     */
    @Override
    public List<Map<String, String>> getJZRKFD(String name) {
        return wghzxMapper.getJZRKFD(name);
    }


}
