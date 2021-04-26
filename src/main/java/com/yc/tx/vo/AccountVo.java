package com.yc.tx.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: mybank
 * @author: 作者
 * @create: 2021-04-24 20:38
 */
@Data
public class AccountVo implements Serializable {


        private Integer accountId;
        private Double money;
        private Integer inAccountId;
}
