package com.jinshan.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jinshan.dao.SYRKMapper;
import com.jinshan.service.SYRKAPIService;
import com.jinshan.util.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  实有人口入库服务层实现类
 * @Auther: 小宇宙
 * @Date: 2021-06-26 13:01
 */
@Service
public class SYRKAPIServiceImpl implements SYRKAPIService {

    @Autowired
    private SYRKMapper syrkMapper;

    @Autowired
    private IdWorker idWorker;

    private static Logger logger = LoggerFactory.getLogger(SYRKAPIServiceImpl.class);


    /**
     *  实有人口数据入库
     */
    @Override
    public int saveSYRK(String jsonStr) {
        logger.info("进入实有人口数据入库服务层");

        JSONObject result = JSONObject.parseObject(jsonStr);
        int number = 0;

        if (result != null) {
            JSONObject content = result.getJSONObject("content");
            if (content != null) {
                JSONObject jsPeopleCount = content.getJSONObject("jsPeopleCount");
                Date date = new Date();
                String sumPeople = jsPeopleCount.getString("sumPeople") == null ? "" : jsPeopleCount.getString("sumPeople");

                Map<String, Object> map1 = new HashMap<>();

                map1.put("ID", idWorker.nextId()+"");
                map1.put("PEOPLE_TYPE", "实有人口");
                map1.put("NUM", sumPeople);
                map1.put("CREATE_TIME", date);

                number += syrkMapper.saveSYRK(map1);

                JSONArray typePeople = jsPeopleCount.getJSONArray("typePeople");
                for (int i=0; i<typePeople.size(); i++) {
                    JSONObject object = typePeople.getJSONObject(i);
                    String syrklbdm = object.getString("syrklbdm") == null ? "" : object.getString("syrklbdm"); //人口类型
                    String rknum = object.getString("rknum") == null ? "" : object.getString("rknum");  //人口数量

                    Map<String, Object> map2 = new HashMap<>();
                    map2.put("ID", idWorker.nextId()+"");
                    //户籍人员
                    if (syrklbdm.equals("01")) {
                        map2.put("PEOPLE_TYPE", "户籍人员");
                    }
                    //来沪人员
                    if (syrklbdm.equals("02")) {
                        map2.put("PEOPLE_TYPE", "来沪人员");
                    }
                    //境外人员
                    if (syrklbdm.equals("03")) {
                        map2.put("PEOPLE_TYPE", "境外人员");
                    }
                    map2.put("NUM", rknum);
                    map2.put("CREATE_TIME", date);
                    number += syrkMapper.saveSYRK(map2);
                }
            }
        }
        return number;
    }












}
