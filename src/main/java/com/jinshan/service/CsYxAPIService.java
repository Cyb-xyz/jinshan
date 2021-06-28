package com.jinshan.service;

/**
 *  城市运行服务层
 * @Auther: 小宇宙
 * @Date: 2021-05-26 10:36
 */
public interface CsYxAPIService {
    /**
     *  获取考核断面数据,并将数据入库
     */
    void getKhDmData();

    /**
     *  获取实有人口数据，并将数据入库
     */
    void getSyRkData();

    /**
     *  获取城市交通数据，并将数据入库
     */
    void getCsJtData();

    /**
     *  获取城市环境大气数据，并将数据入库
     */
    void getCshJdqData();
}
