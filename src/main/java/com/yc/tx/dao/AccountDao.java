package com.yc.tx.dao;


import com.yc.tx.bean.Accounts;

import java.util.List;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-14 21:14
 */
public interface AccountDao  {
    public  int saveAccount(Accounts account);
    public Accounts updateAccount(Accounts account);
    public Accounts findAccount(int accountId);
    public List<Accounts> findAll();
    public void delete(int accountId);
}
