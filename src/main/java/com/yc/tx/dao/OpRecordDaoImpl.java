package com.yc.tx.dao;

import com.yc.tx.bean.OpRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-15 20:00
 */
@Repository
public class OpRecordDaoImpl implements  OpRecordDao{
    private JdbcTemplate jdbcTemplate;//模板

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveOpRecord(OpRecord opRecord) {
        String sql="insert into oprecord(accountid,opmoney,optime,optype,transferid) values(?,?,?,?,?)";
      //  UUID uuid = UUID.randomUUID();
      //  String str=uuid.toString();
        this.jdbcTemplate.update(
                sql,
                opRecord.getAccounId(), opRecord.getOpmoney(),opRecord.getOptime(),opRecord.getOptype(), opRecord.getTransferid());
    }

    @Override
    public List<OpRecord> findAll() {
        String sql="select * from oprecord ";
        List<OpRecord> list = this.jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> {
                    OpRecord op = new OpRecord();
                    op.setId(resultSet.getInt("id"));
                    op.setAccounId(resultSet.getInt("accountid"));
                    op.setOpmoney(resultSet.getDouble("opmoney"));
                    op.setOptime(resultSet.getTimestamp("optime"));
                    op.setOptype(resultSet.getString("optype"));
                    op.setTransferid(resultSet.getString("transferid"));
                    return op;
                });
        return list;
    }

    @Override
    public List<OpRecord> findByAccountId(int accountId) {
        String sql="select * from oprecord where accountid= ?";
        List<OpRecord>list=this.jdbcTemplate.query(sql,(resultSet, i) -> {
            OpRecord op=new OpRecord();
            op.setId(resultSet.getInt("id"));
            op.setAccounId(resultSet.getInt("accountid"));
            op.setOpmoney(resultSet.getDouble("opmoney"));
            op.setOptime(resultSet.getTimestamp("optime"));
            op.setOptype(resultSet.getString("optype"));
            op.setTransferid(resultSet.getString("transferid"));
            return op;
        },accountId);
//        OpRecord o=null;
//        try{
//        o=jdbcTemplate.queryForObject( 只能存一个对象
//                sql,
//                (resultSet, rowNum) -> {
//                    OpRecord op = new OpRecord();
//                    op.setId(resultSet.getInt("id"));
//                   op.setAccounId(accountId);
//                   op.setOpmoney(resultSet.getDouble("opmoney"));
//                   op.setOptime(resultSet.getTimestamp("optime"));
//                   op.setOptype(resultSet.getString("optype"));
//                   op.setTransferid(resultSet.getString("transferid"));
//                    return op;
//                },
//                accountId);
//        } catch (
//        EmptyResultDataAccessException e) {
//            return null;
//        }
//        list.add(o);
        return list;
    }
}
