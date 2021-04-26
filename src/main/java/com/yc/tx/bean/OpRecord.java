package com.yc.tx.bean;

import java.sql.Timestamp;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-15 19:51
 */
public class OpRecord {
    private  Integer id;
    private  Integer accounId;
    private  Double opmoney;
    private Timestamp optime;  //时间
    private  String optype;   //枚举
    private String transferid;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccounId(Integer accounId) {
        this.accounId = accounId;
    }

    public void setOpmoney(Double opmoney) {
        this.opmoney = opmoney;
    }

    public void setOptime(Timestamp optime) {
        this.optime = optime;
    }

    public void setOptype(String optype) {
        this.optype = optype;
    }

    public void setTransferid(String transferid) {
        this.transferid = transferid;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAccounId() {
        return accounId;
    }

    public Double getOpmoney() {
        return opmoney;
    }

    public Timestamp getOptime() {
        return optime;
    }

    public String getOptype() {
        return optype;
    }

    public String getTransferid() {
        return transferid;
    }

    @Override
    public String toString() {
        return "OpRecord{" +
                "id=" + id +
                ", accounId=" + accounId +
                ", opmoney=" + opmoney +
                ", optime=" + optime +
                ", optype='" + optype + '\'' +
                ", transferid='" + transferid + '\'' +
                '}';
    }
}
