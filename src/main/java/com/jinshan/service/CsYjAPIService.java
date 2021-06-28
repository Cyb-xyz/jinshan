package com.jinshan.service;

/**
 *  城市预警服务层接口
 * @Auther: 小宇宙
 * @Date: 2021-05-18 23:33
 */
public interface CsYjAPIService {
    /**
     *  获取城市预警公安数据，并将数据存储。
     */
    int saveCsYjGa();

    /**
     *  城市预警--消防警情数据入库
     */
    int saveXFJQ();

}
