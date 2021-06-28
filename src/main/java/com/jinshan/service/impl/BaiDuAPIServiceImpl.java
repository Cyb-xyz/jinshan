package com.jinshan.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jinshan.dao.BaiDuMapper;
import com.jinshan.service.BaiDuAPIService;
import com.jinshan.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 小宇宙
 * @Date: 2021-06-01 9:53
 */
@Service
public class BaiDuAPIServiceImpl implements BaiDuAPIService {

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private BaiDuMapper baiDuMapper;

    /**
     *  重点道路路段排行数据入库
     */
    @Override
    public void zDdlLdPhSave(JSONObject result) {

        int datatime = result.getInt("datatime");//数据时间（时间戳）
        JSONArray list = result.getJSONArray("list");
        Date date = new Date();     //数据入库时间
        //循环接口返回的数组数据，依次存入数据库
        for (Object object : list) {
            Map<String, Object> paramMap = new HashMap<>();
            JSONObject jsonObject = JSONUtil.parseObj(object);
            paramMap.put("id", idWorker.nextId()+"");
            paramMap.put("rank", jsonObject.getInt("rank"));
            paramMap.put("roadsegId", jsonObject.getStr("roadsegId"));
            paramMap.put("roadname", jsonObject.getStr("roadname"));
            paramMap.put("semantic", jsonObject.getStr("semantic"));
            paramMap.put("congestIndex", jsonObject.getStr("congestIndex"));
            paramMap.put("congestSpeed", jsonObject.getStr("congestSpeed"));
            paramMap.put("congestLength", jsonObject.getStr("congestLength"));
            paramMap.put("roadNetworkName", jsonObject.getStr("roadNetworkName"));
            paramMap.put("sparseLoc", jsonObject.getStr("sparseLoc"));
            paramMap.put("roadsegIdBase64", jsonObject.getStr("roadsegIdBase64"));
            paramMap.put("datatime", datatime);
            paramMap.put("dataInsertDate", date);

            try {
                int success = baiDuMapper.zDdlLdPhSave(paramMap);
                System.out.println("重点道路路段排行数据入库条数："+ success);
            } catch (Exception e) {
                System.out.println("重点道路路段排行数据入库出现异常"+e.getMessage());
                e.printStackTrace();
            }
        }

    }

    /**
     *  重点道路道路排行数据入库
     */
    @Override
    public void zddlDlPhSave(JSONObject result) {
        int datatime = result.getInt("datatime");//数据时间（时间戳）
        JSONArray list = result.getJSONArray("list");
        Date date = new Date();     //数据入库时间
        //循环接口返回的数组数据，依次存入数据库
        for (Object object : list) {
            Map<String, Object> paramMap = new HashMap<>();
            JSONObject jsonObject = JSONUtil.parseObj(object);
            paramMap.put("id", idWorker.nextId()+"");
            paramMap.put("rank", jsonObject.getInt("rank"));
            paramMap.put("roadId", jsonObject.getStr("roadId"));
            paramMap.put("roadname", jsonObject.getStr("roadname"));
            paramMap.put("roadnameEN", jsonObject.getStr("roadnameEN"));
            paramMap.put("congestIndex", jsonObject.getStr("congestIndex"));
            paramMap.put("congestSpeed", jsonObject.getStr("congestSpeed"));
            paramMap.put("congestLength", jsonObject.getStr("congestLength"));
            paramMap.put("roadNetworkName", jsonObject.getStr("roadNetworkName"));
            paramMap.put("sparseLoc", jsonObject.getStr("sparseLoc"));
            paramMap.put("roadIdBase64", jsonObject.getStr("roadIdBase64"));
            paramMap.put("datatime", datatime);
            paramMap.put("dataInsertDate", date);
            try {
                int success = baiDuMapper.zddl_dlPhSave(paramMap);
                System.out.println("重点道路道路排行数据入库条数："+ success);
            } catch (Exception e) {
                System.out.println("重点道路道路排行数据入库出现异常"+e.getMessage());
                e.printStackTrace();
            }

        }
    }

    /**
     *  重点道路信息数据入库
     */
    @Override
    public void zddlXxSave(JSONObject result) {
        JSONArray list = result.getJSONArray("list");
        Date date = new Date();     //数据入库时间
        //循环接口返回的数组数据，依次存入数据库
        for (Object object : list) {
            Map<String, Object> paramMap = new HashMap<>();
            JSONObject jsonObject = JSONUtil.parseObj(object);

            paramMap.put("id", idWorker.nextId()+"");
            paramMap.put("nodeName", jsonObject.getStr("nodeName"));
            paramMap.put("roadId", jsonObject.getStr("roadId"));
            paramMap.put("roadname", jsonObject.getStr("roadname"));
            paramMap.put("roadNetworkName", jsonObject.getStr("roadNetworkName"));
            paramMap.put("roadnameEN", jsonObject.getStr("roadnameEN"));
            paramMap.put("roadsegId", jsonObject.getStr("roadsegId"));
            paramMap.put("roadsegLength", jsonObject.getStr("roadsegLength"));
            paramMap.put("semantic", jsonObject.getStr("semantic"));
            paramMap.put("roadsegStartName", jsonObject.getStr("roadsegStartName"));
            paramMap.put("roadsegEndName", jsonObject.getStr("roadsegEndName"));
            paramMap.put("direction", jsonObject.getStr("direction"));
            paramMap.put("sparseLoc", jsonObject.getStr("sparseLoc"));
            paramMap.put("roadIdBase64", jsonObject.getStr("roadIdBase64"));
            paramMap.put("roadsegIdBase64", jsonObject.getStr("roadsegIdBase64"));
            paramMap.put("dataInsertDate", date);
            try {
                int success = baiDuMapper.zddlXxSave(paramMap);
                System.out.println("重点道路信息数据入库条数："+ success);
            } catch (Exception e) {
                System.out.println("重点道路信息数据入库出现异常"+e.getMessage());
                e.printStackTrace();
            }

        }
    }

    /**
     *  重点道路道路拥堵指标详情数据入库
     */
    @Override
    public void zddlDlYdZbXqSave(JSONObject result) {
        System.out.println("解析重点道路道路拥堵指标详情接口数据");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", idWorker.nextId()+"");

        JSONArray list = result.getJSONArray("list");
        paramMap.put("list", list.toString());

        JSONObject total = result.getJSONObject("total");
        if (total != null) {
            if (total.getStr("roadLength") != null) {
                paramMap.put("total_roadLength", total.getStr("roadLength"));
            }
            if (total.getStr("avgCongestIndex") != null) {
                paramMap.put("total_avgCongestIndex", total.getStr("avgCongestIndex"));
            }
            if (total.getStr("avgCongestSpeed") != null) {
                paramMap.put("total_avgCongestSpeed", total.getStr("avgCongestSpeed"));
            }
            if (total.getStr("avgCongestLength") != null) {
                paramMap.put("total_avgCongestLength", total.getStr("avgCongestLength"));
            }
        }
        JSONArray compareList = result.getJSONArray("compareList");
        if (compareList != null) {
            paramMap.put("compareList", compareList.toString());
            JSONObject compareTotal = result.getJSONObject("compareTotal");
            if (compareTotal != null) {
                if (compareTotal.getStr("roadLength") != null) {
                    paramMap.put("compareTotal_roadLength", compareTotal.getStr("roadLength"));
                }
                if (compareTotal.getStr("avgCongestIndex") != null) {
                    paramMap.put("compareTotal_avgCongestIndex", compareTotal.getStr("avgCongestIndex"));
                }
                if (compareTotal.getStr("avgCongestSpeed") != null) {
                    paramMap.put("compareTotal_avgCongestSpeed", compareTotal.getStr("avgCongestSpeed"));
                }
                if (compareTotal.getStr("avgCongestLength") != null) {
                    paramMap.put("compareTotal_avgCongestLength", compareTotal.getStr("avgCongestLength"));
                }
            }
        } else {
            paramMap.put("compareList", "");
            paramMap.put("compareTotal_roadLength", "");
            paramMap.put("compareTotal_avgCongestIndex", "");
            paramMap.put("compareTotal_avgCongestSpeed", "");
            paramMap.put("compareTotal_avgCongestLength", "");
        }
        paramMap.put("dataInsertDate", new Date());

        try {
            int success = baiDuMapper.zddlDlYdZbXq(paramMap);
            System.out.println("重点道路道路拥堵指标详情数据入库了："+success);
        } catch (Exception e) {
            System.out.println("重点道路道路拥堵指标详情数据入库出现异常"+e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     *  重点道路路段拥堵指标详情数据入库
     */
    @Override
    public void zddlLdYdZbXqSave(JSONObject result) {

        System.out.println("解析重点道路路段拥堵指标详情数据");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", idWorker.nextId()+"");

        JSONArray list = result.getJSONArray("list");
        paramMap.put("list", list.toString());

        JSONObject total = result.getJSONObject("total");
        if (total != null) {
            if (total.getStr("roadsegLength") != null) {
                paramMap.put("total_roadsegLength", total.getStr("roadsegLength"));
            }
            if (total.getStr("avgCongestIndex") != null) {
                paramMap.put("total_avgCongestIndex", total.getStr("avgCongestIndex"));
            }
            if (total.getStr("avgCongestSpeed") != null) {
                paramMap.put("total_avgCongestSpeed", total.getStr("avgCongestSpeed"));
            }
            if (total.getStr("avgCongestLength") != null) {
                paramMap.put("total_avgCongestLength", total.getStr("avgCongestLength"));
            } else {
                paramMap.put("total_avgCongestLength", "");
            }
        }

        JSONArray compareList = result.getJSONArray("compareList");
        if (compareList != null) {
            paramMap.put("compareList", compareList.toString());
            JSONObject compareTotal = result.getJSONObject("compareTotal");
            if (compareTotal != null) {
                if (compareTotal.getStr("roadsegLength") != null) {
                    paramMap.put("compareTotal_roadsegLength", compareTotal.getStr("roadsegLength"));
                }
                if (compareTotal.getStr("avgCongestIndex") != null) {
                    paramMap.put("compareTotal_avgCongestIndex", compareTotal.getStr("avgCongestIndex"));
                }
                if (compareTotal.getStr("avgCongestSpeed") != null) {
                    paramMap.put("compareTotal_avgCongestSpeed", compareTotal.getStr("avgCongestSpeed"));
                }
                if (compareTotal.getStr("avgCongestLength") != null) {
                    paramMap.put("compareTotal_avgCongestLength", compareTotal.getStr("avgCongestLength"));
                } else {
                    paramMap.put("compareTotal_avgCongestLength", "");
                }
            }
        } else {
            paramMap.put("compareList", "");
            paramMap.put("compareTotal_roadsegLength", "");
            paramMap.put("compareTotal_avgCongestIndex", "");
            paramMap.put("compareTotal_avgCongestSpeed", "");
            paramMap.put("compareTotal_avgCongestLength", "");
        }
        paramMap.put("dataInsertDate", new Date());
        try {
            int success = baiDuMapper.zddlLdYdZbXq(paramMap);
            System.out.println("重点道路路段拥堵指标详情数据入库了："+success);
        } catch (Exception e) {
            System.out.println("重点道路路段拥堵指标详情数据入库出现异常"+e.getMessage());
            e.printStackTrace();
        }


    }

    /**
     *  百度——路段拥堵指标详情入库   新的。
     */
    @Override
    public int saveLDYDZBXQ(JSONObject result, Date date) {

        JSONArray list = result.getJSONArray("list");

        JSONObject total = result.getJSONObject("total");
        String roadsegLength = total.getStr("roadsegLength") == null ? "" : total.getStr("roadsegLength");
        String avgCongestIndex = total.getStr("avgCongestIndex") == null ? "" : total.getStr("avgCongestIndex");
        String avgCongestSpeed = total.getStr("avgCongestSpeed") == null ? "" : total.getStr("avgCongestSpeed");
        String avgCongestLength = total.getStr("avgCongestLength") == null ? "" : total.getStr("avgCongestLength");

        int success = 0;

        for (int j=0; j<list.size(); j++) {
            Map<String, Object> paramMap = new HashMap<>();

            JSONObject object = list.getJSONObject(j);
            String datatime = object.getStr("datatime") == null ? "" : object.getStr("datatime");
            String roadname = object.getStr("roadname") == null ? "" : object.getStr("roadname");
            String semantic = object.getStr("semantic") == null ? "" : object.getStr("semantic");
            String congestIndex = object.getStr("congestIndex") == null ? "" : object.getStr("congestIndex");
            String congestSpeed = object.getStr("congestSpeed") == null ? "" : object.getStr("congestSpeed");
            String congestLength = object.getStr("congestLength") == null ? "" : object.getStr("congestLength");

            paramMap.put("ID", idWorker.nextId()+"");
            paramMap.put("SEMANTIC", semantic);
            paramMap.put("DATATIME", datatime);
            paramMap.put("CONGESTSPEED", congestSpeed);
            paramMap.put("CONGESTINDEX", congestIndex);
            paramMap.put("CONGESTLENGTH", congestLength);
            paramMap.put("ROADNAME", roadname);
            paramMap.put("ROADSEGLENGTH", roadsegLength);
            paramMap.put("AVGCONGESTINDEX", avgCongestIndex);
            paramMap.put("AVGCONGESTSPEED", avgCongestSpeed);
            paramMap.put("AVGCONGESTLENGTH", avgCongestLength);

            paramMap.put("TIME", "20210621");
            paramMap.put("DATA_TIME", date);


            try {
                success += baiDuMapper.saveLDYDZBXQ(paramMap);
            } catch (Exception e) {
                System.out.println("百度——路段拥堵指标详情入库新的。出现异常");
                e.printStackTrace();
            }

        }

        return success;
    }

    /**
     *  从重点道路信息表中查询所有路段的id
     */
    @Override
    public List<String> selectLDID() {
        return baiDuMapper.selectLDID();
    }


}
