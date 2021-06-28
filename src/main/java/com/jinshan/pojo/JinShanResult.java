package com.jinshan.pojo;

import lombok.*;

/**
 *  结果返回类
 * @Auther: 小宇宙
 * @Date: 2021-05-19 8:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JinShanResult {
    private String code;   //返回码  code为200为成功，400为失败
    private String msg;     //返回信息
    private Object data;    //返回数据
}
