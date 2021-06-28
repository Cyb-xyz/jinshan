package com.jinshan.pojo;

/**
 *  人口年龄分段实体类
 * @Auther: 小宇宙
 * @Date: 2021-06-24 17:18
 */
public class RenKouFD {

    private String AGE;
    private String NUM;

    public String getAGE() {
        return AGE;
    }

    public void setAGE(String AGE) {
        this.AGE = AGE;
    }

    public String getNUM() {
        return NUM;
    }

    public void setNUM(String NUM) {
        this.NUM = NUM;
    }

    public RenKouFD(String AGE, String NUM) {
        this.AGE = AGE;
        this.NUM = NUM;
    }

    public RenKouFD() {
    }

    @Override
    public String toString() {
        return "RenKouFD{" +
                "AGE='" + AGE + '\'' +
                ", NUM='" + NUM + '\'' +
                '}';
    }
}
