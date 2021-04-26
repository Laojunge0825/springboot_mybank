package com.yc.tx.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: mybank
 * @author: 作者
 * @create: 2021-04-24 20:43
 */
@Data
public class ResultVO<T> implements Serializable {
    private Integer code;
    private T data;
    private String msg;
}
