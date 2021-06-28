package com.jinshan.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jinshan.dao.WeiHuaMapper;
import com.jinshan.service.WeiHuaAPIService;
import com.jinshan.util.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  危化指标服务层实现类
 * @Auther: 小宇宙
 * @Date: 2021-06-17 8:55
 */
@Service
public class WeiHuaAPIServiceImpl implements WeiHuaAPIService {

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private WeiHuaMapper weiHuaMapper;

    private static Logger logger = LoggerFactory.getLogger(WeiHuaAPIServiceImpl.class);

    /**
     *  企业信息总览信息入库
     * @param result    第三方接口返回的json字符串
     * @return
     */
    @Override
    public int save_qyxxzl(String result) {
        logger.info("进入企业信息总览信息入库服务层实现类");
        //解析JSON字符串
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject data = jsonObject.getJSONObject("data");
        try {
            if (data != null) {
                Map<String, Object> paramMap = new HashMap<>();

                paramMap.put("ID", idWorker.nextId()+"");
                paramMap.put("DATA3", data.getInteger("data3") == null ? 0 : data.getInteger("data3"));
                paramMap.put("DATA2", data.getInteger("data2") == null ? 0 : data.getInteger("data2"));
                paramMap.put("DATA4", data.getInteger("data4") == null ? 0 : data.getInteger("data4"));
                paramMap.put("ENT1", data.getInteger("ent1") == null ? 0 : data.getInteger("ent1"));
                paramMap.put("ENT2", data.getInteger("ent2") == null ? 0 : data.getInteger("ent2"));
                paramMap.put("ENT3", data.getInteger("ent3") == null ? 0 : data.getInteger("ent3"));
                paramMap.put("ENT4", data.getInteger("ent4") == null ? 0 : data.getInteger("ent4"));
                paramMap.put("ENT5", data.getInteger("ent5") == null ? 0 : data.getInteger("ent5"));
                paramMap.put("ENT6", data.getInteger("ent6") == null ? 0 : data.getInteger("ent6"));
                paramMap.put("PEOPLE1", data.getInteger("people1") == null ? 0 : data.getInteger("people1"));
                paramMap.put("PEOPLE2", data.getInteger("people2") == null ? 0 : data.getInteger("people2"));
                paramMap.put("PEOPLE3", data.getInteger("people3") == null ? 0 : data.getInteger("people3"));
                paramMap.put("PEOPLE4", data.getInteger("people4") == null ? 0 : data.getInteger("people4"));
                paramMap.put("PEOPLE5", data.getInteger("people5") == null ? 0 : data.getInteger("people5"));
                paramMap.put("PEOPLE6", data.getInteger("people6") == null ? 0 : data.getInteger("people6"));
                paramMap.put("DATA_TIME", new Date());

                logger.info("企业信息总览信息入库");
                return weiHuaMapper.save_qyxxzl(paramMap);

            }
        } catch (Exception e) {
            logger.info("企业信息总览信息入库出错，{}", e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }


    /**
     *  危险化学品总览数据入库
     * @param result    第三方接口返回的json字符串
     * @return
     */
    @Override
    public int saveWXHXPZL(String result) {
        logger.info("进入危险化学品总览数据入库服务层实现类");
        //解析JSON字符串
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject != null) {
            JSONObject data = jsonObject.getJSONObject("data");
            if (data != null) {
                String kindNum = data.getString("kindNum") == null ? "" : data.getString("kindNum");
                String entNum = data.getString("entNum") == null ? "" : data.getString("entNum");
                String allType = data.getString("allType") == null ? "" : data.getString("allType");
                String allEnt = data.getString("allEnt") == null ? "" : data.getString("allEnt");
                String allStorage = data.getString("allStorage") == null ? "" : data.getString("allStorage");
                String toxicType = data.getString("toxicType") == null ? "" : data.getString("toxicType");
                String toxicEnt = data.getString("toxicEnt") == null ? "" : data.getString("toxicEnt");
                String toxicStorage = data.getString("toxicStorage") == null ? "" : data.getString("toxicStorage");
                String explosionType = data.getString("explosionType") == null ? "" : data.getString("explosionType");
                String explosionEnt = data.getString("explosionEnt") == null ? "" : data.getString("explosionEnt");
                String explosionStorage = data.getString("explosionStorage") == null ? "" : data.getString("explosionStorage");
                String numDangsrc = data.getString("numDangsrc") == null ? "" : data.getString("numDangsrc");
                String entNumDangsrc = data.getString("entNumDangsrc") == null ? "" : data.getString("entNumDangsrc");
                String typeNum = data.getString("typeNum") == null ? "" : data.getString("typeNum");
                String equipNum = data.getString("equipNum") == null ? "" : data.getString("equipNum");

                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("ID", idWorker.nextId()+"");
                paramMap.put("KINDNUM", kindNum);
                paramMap.put("ENTNUM", entNum);
                paramMap.put("ALLTYPE", allType);
                paramMap.put("ALLENT", allEnt);
                paramMap.put("ALLSTORAGE", allStorage);
                paramMap.put("TOXICTYPE", toxicType);
                paramMap.put("TOXICENT", toxicEnt);
                paramMap.put("TOXICSTORAGE", toxicStorage);
                paramMap.put("EXPLOSIONTYPE", explosionType);
                paramMap.put("EXPLOSIONENT", explosionEnt);
                paramMap.put("EXPLOSIONSTORAGE", explosionStorage);
                paramMap.put("NUMDANGSRC", numDangsrc);
                paramMap.put("ENTNUMDANGSRC", entNumDangsrc);
                paramMap.put("TYPENUM", typeNum);
                paramMap.put("EQUIPNUM", equipNum);
                paramMap.put("DATA_TIME", new Date());

                logger.info("危险化学品总览数据入库");
                try {
                    return weiHuaMapper.saveWXHXPZL(paramMap);
                } catch (Exception e) {
                    logger.info("危险化学品总览数据入库出错，{}", e.getMessage());
                    e.printStackTrace();
                }

            }
        }

        return 0;
    }


    /**
     *  危化车辆，前日累计（辆）数据入库
     * @param result 第三方接口返回的json字符串
     * @return
     */
    @Override
    public int saveQRLJCL(String result) {
        logger.info("进入危化车辆，前日累计（辆）数据入库服务层实现类");
        //解析JSON字符串
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject != null) {
            JSONObject jsonResult = jsonObject.getJSONObject("result");
            if (jsonResult != null) {

                String total = jsonResult.getString("total") == null ? "" : jsonResult.getString("total");   //危化车辆前日累计（辆）
                JSONArray data = jsonResult.getJSONArray("data");   //获取车辆信息
                int success = 0;    //入库条数
                Date date = new Date(); //入库时间

                for (int i = 0; i < data.size(); i++) {
                    JSONObject object = data.getJSONObject(i);

                    String flowTime = object.getString("flowTime") == null ? "" : object.getString("flowTime");    //流动时间
                    String license = object.getString("license") == null ? "" : object.getString("license");    //牌照
                    String licenseColor = object.getString("licenseColor") == null ? "" : object.getString("licenseColor"); //牌照颜色

                    Map<String, Object> paramMap = new HashMap<>();

                    paramMap.put("ID", idWorker.nextId()+"");
                    paramMap.put("TOTAL", total);
                    paramMap.put("FLOWTIME", flowTime);
                    paramMap.put("LICENSE", license);
                    paramMap.put("LICENSECOLOR", licenseColor);
                    paramMap.put("DATA_TIME", date);
                    try {
                        logger.info("危化车辆，前日累计（辆）数据入库");
                        success += weiHuaMapper.saveQRLJCL(paramMap);
                    } catch (Exception e) {
                        logger.info("危化车辆，前日累计（辆）数据入库出错，{}", e.getMessage());
                        e.printStackTrace();
                    }
                }

                return success;
            }
        }

        return 0;
    }

    /**
     *  危化车辆，实时数量（辆）数据入库
     * @param jsonStr 第三方接口返回的json字符串
     * @return
     */
    @Override
    public int saveSSCSL(String jsonStr) {
        logger.info("进入危化车辆，实时数量（辆）数据入库服务层实现类");
        //解析JSON字符串
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        if (jsonObject != null) {
            JSONObject result = jsonObject.getJSONObject("result");
            if (result != null) {

                String total = result.getString("total") == null ? "" : result.getString("total");  //【危化车辆 | 实时数量（辆）】
                String highWayCars = result.getString("highWayCars") == null ? "" : result.getString("highWayCars");
                String dangerousGoodsCars = result.getString("dangerousGoodsCars") == null ? "" : result.getString("dangerousGoodsCars");
                String localCars = result.getString("localCars") == null ? "" : result.getString("localCars");
                String nonLocalCars = result.getString("nonLocalCars") == null ? "" : result.getString("nonLocalCars");

                int success = 0;    //入库条数
                Date date = new Date(); //入库时间

                JSONArray data = result.getJSONArray("data");   //车辆信息

                for (int i=0; i<data.size(); i++) {
                    JSONObject object = data.getJSONObject(i);

                    String license = object.getString("license") == null ? "" : object.getString("license");
                    String licenseColor = object.getString("licenseColor") == null ? "" : object.getString("licenseColor");
                    String gaosu = object.getString("gaosu") == null ? "" : object.getString("gaosu");
                    String local = object.getString("local") == null ? "" : object.getString("local");

                    Map<String, Object> paramMap = new HashMap<>();

                    paramMap.put("ID", idWorker.nextId() + "");
                    paramMap.put("TOTAL", total);
                    paramMap.put("HIGHWAYCARS", highWayCars);

                    paramMap.put("LICENSE", license);
                    paramMap.put("LICENSECOLOR", licenseColor);
                    paramMap.put("GAOSU", gaosu);
                    paramMap.put("LOCAL", local);

                    paramMap.put("DANGEROUSGOODSCARS", dangerousGoodsCars);
                    paramMap.put("LOCALCARS", localCars);
                    paramMap.put("NONLOCALCARS", nonLocalCars);

                    paramMap.put("DATA_TIME", date);

                    try {
                        logger.info("危化车辆，实时数量（辆）数据入库");
                        success += weiHuaMapper.saveSSCSL(paramMap);
                    } catch (Exception e) {
                        logger.info("危化车辆，实时数量（辆）数据入库出错，{}", e.getMessage());
                        e.printStackTrace();
                    }
                }
                return success;

            }
        }

        return 0;
    }

    /**
     *  危险船舶数据入库
     * @param jsonStr 第三方接口返回的json字符串
     * @return
     */
    @Override
    public int saveWXCB(String jsonStr) {
        logger.info("进入危险船舶数据入库服务层实现类");
        //解析JSON字符串
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        if (jsonObject != null) {
            JSONObject data = jsonObject.getJSONObject("data");

            String num = data.getString("num") == null ? "" : data.getString("num");    //在线数量（艘）
            String ship = data.getString("ship") == null ? "" : data.getString("ship");  //危化数量（艘）

            Map<String, Object> paramMap = new HashMap<>();

            paramMap.put("ID", idWorker.nextId() + "");
            paramMap.put("NUM", num);
            paramMap.put("SHIP", ship);
            paramMap.put("DATA_TIME", new Date());

            try {
                logger.info("危险船舶数据入库");
                return weiHuaMapper.saveWXCB(paramMap);
            } catch (Exception e) {
                logger.info("危险船舶数据入库出错，{}", e.getMessage());
                e.printStackTrace();
            }

        }
        return 0;
    }

    /**
     *  地下化工管线数据入库
     * @param jsonStr 第三方接口返回的json字符串
     */
    @Override
    public int saveDXHGGX(String jsonStr) {
        logger.info("进入地下化工管线数据入库服务层实现类");
        //解析JSON字符串
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        if (jsonObject != null) {

            JSONObject data = jsonObject.getJSONObject("data");

            String species = data.getString("species") == null ? "" : data.getString("species");    //【地下化工管线 | 种类数量（种）】
            String mileage = data.getString("mileage") == null ? "" : data.getString("mileage");  //【地下化工管线  | 总里程（公里）】

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("ID", idWorker.nextId() + "");
            paramMap.put("SPECIES", species);
            paramMap.put("MILEAGE", mileage);
            paramMap.put("DATA_TIME", new Date());

            try {
                logger.info("地下化工管线数据入库");
                return weiHuaMapper.saveDXHGGX(paramMap);
            } catch (Exception e) {
                logger.info("地下化工管线数据入库出错，{}", e.getMessage());
                e.printStackTrace();
            }

        }
        return 0;
    }

    /**
     *  监管动态总览数据入库
     * @param jsonStr 第三方接口返回的json字符串
     * @return
     */
    @Override
    public int saveJGDTZL(String jsonStr) {
        logger.info("进入监管动态总览数据入库服务层实现类");
        //解析JSON字符串
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        if (jsonObject != null) {
            JSONObject data = jsonObject.getJSONObject("data");
            if (data != null) {
                String people = data.getString("people") == null ? "" : data.getString("people");
                String companies = data.getString("companies") == null ? "" : data.getString("companies");
                String punish = data.getString("punish") == null ? "" : data.getString("punish");
                String amount = data.getString("amount") == null ? "" : data.getString("amount");
                String devices = data.getString("devices") == null ? "" : data.getString("devices");
                String lawOnline = data.getString("lawOnline") == null ? "" : data.getString("lawOnline");
                String inspectionRate = data.getString("inspectionRate") == null ? "" : data.getString("inspectionRate");
                String rectificationRate = data.getString("rectificationRate") == null ? "" : data.getString("rectificationRate");
                String punishRate = data.getString("punishRate") == null ? "" : data.getString("punishRate");

                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("ID", idWorker.nextId()+"");
                paramMap.put("PEOPLE", people);     //【部门监管 | 执法人次（次）】
                paramMap.put("COMPANIES", companies);   //【部门监管 | 检查家次（家）】
                paramMap.put("PUNISH", punish);     //【部门监管 | 处罚数量（次）】
                paramMap.put("AMOUNT", amount);     //【部门监管 | 处罚金额（万元）】
                paramMap.put("DEVICES", devices);   //【部门监管 | 设备总数（台）】
                paramMap.put("LAWONLINE", lawOnline);   //【部门监管 | 视频总数（台）】
                paramMap.put("INSPECTIONRATE", inspectionRate); //【部门监管 | 检查率】
                paramMap.put("RECTIFICATIONRATE", rectificationRate);   //【部门监管 | 整改率】
                paramMap.put("PUNISHRATE", punishRate);     //【部门监管 | 处罚率】
                paramMap.put("DATA_TIME", new Date());      //数据入库时间

                try {
                    logger.info("监管动态总览数据入库");
                    return weiHuaMapper.saveJGDTZL(paramMap);
                } catch (Exception e) {
                    logger.info("监管动态总览数据入库出错，{}", e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }


}
