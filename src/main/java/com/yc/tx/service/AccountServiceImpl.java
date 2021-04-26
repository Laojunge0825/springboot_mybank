package com.yc.tx.service;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpRecord;
import com.yc.tx.bean.OpTypes;
import com.yc.tx.dao.AccountDao;
import com.yc.tx.dao.OpRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-17 16:50
 */
@Service  //Spring 会自动将抛出的异常 转换为RuntimeException的子类
//事务处理 的注解
@Transactional(propagation = Propagation.REQUIRED,//传播机制
        isolation = Isolation.DEFAULT,//隔离级别
        readOnly = false,
        timeout = 100,//超时时间
        rollbackForClassName = {"RuntimeException"})//异常回滚
public class AccountServiceImpl implements  AccountService{
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private OpRecordDao opRecordDao;
    @Override
    public Integer openAccount(Accounts account, double money) {
        Accounts a=new Accounts();
            a.setBalance(money);
           a.setAccountId(accountDao.saveAccount(a)) ;
            OpRecord op=new OpRecord();
            op.setAccounId(a.getAccountId());
            op.setOptime(new Timestamp(System.currentTimeMillis()));
            op.setOptype(OpTypes.deposite.getName());
            op.setOpmoney(money);
            op.setTransferid(null);
            opRecordDao.saveOpRecord(op);
        return a.getAccountId();
    }

    @Override
    public Accounts deposite(Accounts account, double money, String optype, String transferid) {
            Accounts a=this.showBalance(account);
            OpRecord op=new OpRecord();
            op.setAccounId(a.getAccountId());
            op.setOpmoney(money);
            op.setOptype(optype);
            op.setOptime(new Timestamp(System.currentTimeMillis()));
            if(transferid==null){
                op.setTransferid(" ");
            }else{
                op.setTransferid(transferid);
            }
            opRecordDao.saveOpRecord(op);
            a.setBalance(a.getBalance()+money);
            accountDao.updateAccount(a);
        return a;
    }

    @Override
    public Accounts withdraw(Accounts account, double money, String optype, String transferid) {
        Accounts a=this.showBalance(account);
        OpRecord op=new OpRecord();
        op.setAccounId(a.getAccountId());
        op.setOpmoney(money);
        op.setOptype(optype);
        op.setOptime(new Timestamp(System.currentTimeMillis()));
        if(transferid==null){
            op.setTransferid(" ");
        }else{
            op.setTransferid(transferid);
        }
        opRecordDao.saveOpRecord(op);
        a.setBalance(a.getBalance()-money);
        accountDao.updateAccount(a);
        return a;

    }


    @Override
    public Accounts transfer(Accounts inaccount, Accounts outaccount, double money) {
        String uuid=UUID.randomUUID().toString();
        this.deposite(inaccount,money, OpTypes.deposite.getName(),uuid);
        Accounts a=this.withdraw(outaccount,money,OpTypes.withdraw.getName(),uuid);
        return a;
    }

    @Override
    @Transactional(readOnly =  true)
    public Accounts showBalance(Accounts account) {
        return accountDao.findAccount(account.getAccountId());
    }

    @Override
    @Transactional(readOnly =  true)
    public List<OpRecord> findByid(Accounts account) {
        return opRecordDao.findByAccountId(account.getAccountId());
    }

    @Override
    public List<Accounts> findAll() {
        return accountDao.findAll();
    }
}
