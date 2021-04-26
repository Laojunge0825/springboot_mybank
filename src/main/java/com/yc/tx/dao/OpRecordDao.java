package com.yc.tx.dao;

import com.yc.tx.bean.OpRecord;

import java.util.List;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-15 19:49
 */
public interface OpRecordDao {

    public void saveOpRecord( OpRecord opRecord);

    public List<OpRecord>findAll();

    public List<OpRecord> findByAccountId( int accountId);
}
