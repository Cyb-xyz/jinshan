package com.jinshan.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jinshan.controller.DaPingController;
import com.jinshan.dao.DaPingMapper;
import com.jinshan.service.DaPingAPIService;
import com.jinshan.util.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  大屏指标模块服务层实现类
 * @Auther: 小宇宙
 * @Date: 2021-06-20 11:30
 */
@Service
public class DaPingAPIServiceImpl implements DaPingAPIService {

    @Autowired
    private DaPingMapper daPingMapper;

    @Autowired
    private IdWorker idWorker;

    private static Logger logger = LoggerFactory.getLogger(DaPingAPIServiceImpl.class);

    /**
     *  大屏-小微站-模块（小微站模块数据）
     * @param result    第三方接口返回的json字符串
     * @return
     */
    @Override
    public int saveXWZ(String result) {
        logger.info("进入大屏-小微站-模块指标服务层实现类");
        // 解析json字符串
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject != null) {
            JSONObject data = jsonObject.getJSONObject("data");
            Map<String, Object> map = new HashMap<>();
            Date date = new Date(); //数据插入时间
            int success = 0;    //入库数据条数
            if (data != null) {
                int bjNum = data.getInteger("bjNum") == null ? 0 : data.getInteger("bjNum");        //超标报警数
                int dwNum = data.getInteger("dwNum") == null ? 0 : data.getInteger("dwNum");        //定位数
                int wrsyNum = data.getInteger("wrsyNum") == null ? 0 : data.getInteger("wrsyNum");  //污染溯源数
                JSONArray xwzList = data.getJSONArray("xwzList");       //Echart雷达图数据
                for (int i=0; i<xwzList.size(); i++) {
                    JSONObject object = xwzList.getJSONObject(i);
                    int val = object.getInteger("val") == null ? 0 : object.getInteger("val");      //ECHART雷达图数据-数值
                    String name = object.getString("name") == null ? "" : object.getString("name"); //ECHART雷达图数据-名称

                    map.put("ID", idWorker.nextId()+"");
                    map.put("BJNUM", bjNum);
                    map.put("DWNUM", dwNum);
                    map.put("WRSYNUM", wrsyNum);
                    map.put("VAL", val);
                    map.put("NAME", name);
                    map.put("DATA_TIME", date);

                    logger.info("小微站模块数据入库");
                    try {
                        success += daPingMapper.saveXWZ(map);
                    } catch (Exception e) {
                        logger.info("小微站模块数据入库出现异常,{}", e.getMessage());
                        e.printStackTrace();
                    }

                }
                return success;
            }

        }
        return 0;
    }

    /**
     *  大屏-扬尘污染-模块（扬尘污染模块）
     * @param result    第三方接口返回的json字符串
     * @return
     */
    @Override
    public int saveYCWR(String result) {
        logger.info("进入大屏-扬尘污染-模块指标服务层实现类");
        // 解析json字符串
        JSONObject jsonObject = JSONObject.parseObject(result);

        if (jsonObject != null) {
            JSONObject data = jsonObject.getJSONObject("data");
            Map<String, Object> map = new HashMap<>();
            if (data != null) {
                String jzgdNum = data.getString("jzgdNum") == null ? "" : data.getString("jzgdNum");    // 建筑工地点位数
                String jzgdWTS = data.getString("jzgdWTS") == null ? "" : data.getString("jzgdWTS");    // 建筑工地问题数
                String dyxxwrjNum = data.getString("dyxxwrjNum") == null ? "" : data.getString("dyxxwrjNum");   // 无人机巡查次数
                String mtWTS = data.getString("mtWTS") == null ? "" : data.getString("mtWTS");                  // 码头问题数
                String dyxxwtNum = data.getString("dyxxwtNum") == null ? "" : data.getString("dyxxwtNum");      // 当月线下问题数
                String mtNum = data.getString("mtNum") == null ? "" : data.getString("mtNum");          // 码头点位数
                String jbzNum = data.getString("jbzNum") == null ? "" : data.getString("jbzNum");       // 搅拌站点位数
                String jbzWTS = data.getString("jbzWTS") == null ? "" : data.getString("jbzWTS");       // 搅拌站问题数
                String dyxxdwNum = data.getString("dyxxdwNum") == null ? "" : data.getString("dyxxdwNum");      // 当月线下点位数

                map.put("ID", idWorker.nextId()+"");
                map.put("JZGDNUM", jzgdNum);
                map.put("JZGDWTS", jzgdWTS);
                map.put("DYXXWRJNUM", dyxxwrjNum);
                map.put("MTWTS", mtWTS);
                map.put("DYXXWTNUM", dyxxwtNum);
                map.put("MTNUM", mtNum);
                map.put("JBZNUM", jbzNum);
                map.put("JBZWTS", jbzWTS);
                map.put("DYXXDWNUM", dyxxdwNum);
                map.put("DATA_TIME", new Date());


                logger.info("扬尘污染模块数据入库");
                try {
                    return daPingMapper.saveYCWR(map);
                } catch (Exception e) {
                    logger.info("扬尘污染模块数据入库出现异常,{}", e.getMessage());
                    e.printStackTrace();
                }

            }
        }

        return 0;
    }

    /**
     *  大屏-重点排污单位-模块（重点排污模块数据）
     * @param result    第三方接口返回的json字符串
     * @return
     */
    @Override
    public int saveZDPWDW(String result) {
        logger.info("进入大屏-重点排污单位-模块指标服务层实现类");
        // 解析json字符串
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject != null) {
            JSONObject data = jsonObject.getJSONObject("data");
            Map<String, Object> map = new HashMap<>();
            Date date = new Date(); //数据插入时间
            int success = 0;    //入库数据条数
            if (data != null) {
                String total = data.getString("total") == null ? "" : data.getString("total");  //重点排污单位总数
                String fqNum = data.getString("fqNum") == null ? "" : data.getString("fqNum");  //废气在线监测家数
                String fsNum = data.getString("fsNum") == null ? "" : data.getString("fsNum");  //废水在线监测家数
                String year = data.getString("year") == null ? "" : data.getString("year");     //年份
                JSONArray pwList = data.getJSONArray("pwList");
                for (int i=0; i<pwList.size(); i++) {
                    JSONObject object = pwList.getJSONObject(i);
                    String pwList_name = object.getString("name") == null ? "" : object.getString("name");      //排污地区名称
                    String pwList_fqNum = object.getString("fqNum") == null ? "" : object.getString("fqNum");   //排污地区废气单位数
                    String pwList_fsNum = object.getString("fsNum") == null ? "" : object.getString("fsNum");   //排污地区废水单位数
                    String pwList_year = object.getString("year") == null ? "" : object.getString("year");      //排污地区数据的年份

                    map.put("ID", idWorker.nextId()+"");
                    map.put("TOTAL", total);
                    map.put("FQNUM", fqNum);
                    map.put("PWLIST_NAME", pwList_name);
                    map.put("PWLIST_FQNUM", pwList_fqNum);
                    map.put("PWLIST_FSNUM", pwList_fsNum);
                    map.put("PWLIST_YEAR", pwList_year);
                    map.put("FSNUM", fsNum);
                    map.put("YEAR", year);
                    map.put("DATA_TIME", date);

                    logger.info("重点排污单位模块数据入库");
                    try {
                        success += daPingMapper.saveZDPWDW(map);
                    } catch (Exception e) {
                        logger.info("重点排污单位模块数据入库出现异常,{}", e.getMessage());
                        e.printStackTrace();
                    }
                }

                return success;

            }
        }
        return 0;
    }


    /*****************获取最新小微站数据*******************/
    //获取小微站最新Echart雷达图数据
    @Override
    public List<Map<String, String>> selectLDTSJ() {
        return daPingMapper.selectLDTSJ();
    }
    //获取小微站最新 超标报警数, 定位数, 污染溯源数 数据
    @Override
    public List<Map<String, String>> selectOther() {
        return daPingMapper.selectOther();
    }


    /*****************获取最新重点排污单位数据*******************/
    // 获取小重点排污单位总和数据
    @Override
    public List<Map<String, String>> selectPwSUM() {
        return daPingMapper.selectPwSUM();
    }
    // 获取小重点排污单位数据列表
    @Override
    public List<Map<String, String>> selectPwList() {
        return daPingMapper.selectPwList();
    }


}
