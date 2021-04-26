package com.yc.tx.bean;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-14 20:26
 */
public class Accounts {
    private Integer accountId;
    private double balance;

    public Integer getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                '}';
    }
}
