package com.yc.tx.service;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpRecord;

import java.util.List;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-17 16:45
 */
public interface AccountService {
    //开户
    public Integer openAccount(Accounts account,double money);
    //存钱
    public Accounts deposite(Accounts account ,double money,String optype ,String transferid);
    //取钱
    public Accounts withdraw(Accounts account ,double money,String optype ,String transferid);
    //转账
    public Accounts transfer(Accounts inaccount ,Accounts outaccount ,double money);
    //查询余额
    public Accounts showBalance(Accounts account);
    //根据id查询流水  日志记录
    public List<OpRecord> findByid(Accounts account);
    //查看所有
    public List<Accounts>findAll();
}
