package com.yc.tx.service;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest {

    @Autowired
    private AccountService as;


    @Test
    @Transactional   // 在junit中 测试完后自动回滚 回复现场   不会影响数据库
    public void testOpenAccount(){
        Integer AccountId=as.openAccount(new Accounts(),1);
        System.out.println(AccountId);
    }
    @Test
    public void testDeposite(){
        Accounts a =new Accounts();
        a.setAccountId(3);
        Accounts aa=as.deposite(a,100.0, OpTypes.deposite.getName(),null);
        System.out.println(aa);
    }
    @Test
    public void testWithdraw(){
        Accounts a =new Accounts();
        a.setAccountId(3);
        Accounts aa= as.withdraw(a,100.0, OpTypes.deposite.getName(),null);
        System.out.println(aa);
    }

    @Test
    public void testTarnsfer(){
        Accounts ina =new Accounts();
        ina.setAccountId(20);
        Accounts out =new Accounts();
        out.setAccountId(2);
        Accounts aa=as.transfer(ina,out,10);
        System.out.println(aa);
    }
}