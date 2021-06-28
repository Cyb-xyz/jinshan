package com.jinshan.util.download;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 *  读取解压文件中的数据
 * @Auther: 小宇宙
 * @Date: 2021-06-18 9:58
 */
public class ReadTxtData {

    public static Logger logger = LoggerFactory.getLogger(ReadTxtData.class);

    /**
     *  将txt文件中的数据转换成json字符串
     * @param path  文件地址
     * @return json字符串
     */
    public static String readTxtToJson(String path) {
        try {
            File file = new File(path);
            String jsonStr = "";
            FileInputStream fis = null;
            InputStreamReader isr = null;
            BufferedReader br = null;
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                isr = new InputStreamReader(fis, "gbk");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                jsonStr += line;
            }
            logger.info("读取文件中的数据");
            return jsonStr;
        } catch (Exception e) {
            logger.info("读取文件中的数据出现异常，{}", e.getMessage());
            e.printStackTrace();
            return "";
        }
    }
}
