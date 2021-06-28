package com.jinshan.util.download;

import org.codehaus.plexus.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 *  从网络上下载文件
 * @Auther: 小宇宙
 * @Date: 2021-06-17 17:32
 */
public class DownLoadFileUtil {

    public static Logger logger = LoggerFactory.getLogger(DownLoadFileUtil.class);

    /**
     *  解压
     * @param zipPath   压缩文件地址
     * @param filePath  解压后的文件地址
     * @throws Exception
     */
    public static void unpackFile(String zipPath, String filePath) {
        try {
            File inputFile = new File(zipPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(inputFile)), "utf-8"));
            File file = new File(filePath);//输出文件
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            String s;
            FileOutputStream fos = new FileOutputStream("liuName");
            while ((s = reader.readLine()) != null) {
                fos.write(s.getBytes());
                ps.append(s);// 在已有的基础上添加字符串
            }
            reader.close();
            fos.close();
            logger.info("解压完毕...");
        } catch (Exception e) {
            logger.info("高德压缩包解压出现异常，{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     *  下载文件
     * @param url   下载地址
     * @param dir   保存地址
     * @param fileName  保存文件名称
     * @return
     */
    public static String downloadFile(String url,String dir, String fileName) {
        try {
            URL httpurl = new URL(url);
            System.out.println(fileName);
            File f = new File(dir + fileName);
            FileUtils.copyURLToFile(httpurl, f);
        } catch(Exception e) {
            logger.info("高德下载压缩包出现异常，{}", e.getMessage());
            e.printStackTrace();
            return "Fault!";
        }
        return "Successful!";
    }
}
