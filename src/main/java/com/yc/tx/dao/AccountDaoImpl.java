package com.yc.tx.dao;

import com.yc.tx.bean.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-15 18:45
 */
@Repository  //dao层

public class AccountDaoImpl implements  AccountDao {
    private JdbcTemplate jdbcTemplate;//模板

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public int saveAccount(Accounts account) {
        String sql="insert into accounts(balance) values(?)";
        KeyHolder keyHolder = new GeneratedKeyHolder(); //生成键的保存器
        jdbcTemplate.update(connection -> {   //lambda  表达式
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
            ps.setDouble(1, account.getBalance());
            return ps;
        }, keyHolder);

//        jdbcTemplate.update(new PreparedStatementCreator() { //接口里只有一个方法 只有一个参数  匿名内部类
//            @Override
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                PreparedStatement pstmt=connection.prepareStatement(sql,new String[]{"accountId"}); //第二个参数的意思是返回这个字段生成的键
//                pstmt.setDouble(1,account.getBalance());
//                return pstmt;
//            }
//        },keyHolder);


        return ((BigInteger)keyHolder.getKey()).intValue(); //将BigInteger 类型转换为  Integer类型
    }

    @Override
    public Accounts updateAccount(Accounts account) {
        String sql="update accounts set balance=? where accountId = ?";
       this.jdbcTemplate.update(sql,account.getBalance(),account.getAccountId());
        return account;
    }

    @Override
    public Accounts findAccount(int accountId) {
        String sql="select * from accounts where accountId = ? ";
         Accounts a=null;
        try {
            a = this.jdbcTemplate.queryForObject(sql,

                    (resultSet, rowNum) -> {
                        Accounts accounts = new Accounts();
                        accounts.setAccountId(resultSet.getInt("accountId"));
                        accounts.setBalance(resultSet.getDouble("balance"));
                        return accounts;
                    },
                    accountId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return  a;
    }

    @Override
    public List<Accounts> findAll() {
        String sql="select * from accounts ";
        List<Accounts> list = this.jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> {
                    Accounts a=new Accounts(); //mapRow 底层帮你完成循环
                    System.out.println("当前取得是第"+rowNum+"行数据");
                    a.setAccountId(   resultSet.getInt("accountId"));
                    a.setBalance(   resultSet.getDouble("balance"));
                    return a;
                });
//        List<Accounts>list=this.jdbcTemplate.query(sql, new RowMapper<Accounts>() {
//            @Override
//            public Accounts mapRow(ResultSet resultSet, int i) throws SQLException {
//                Accounts a=new Accounts(); //mapRow 底层帮你完成循环
//                System.out.println("当前取得是第"+i+"行数据");
//             a.setAccountId(   resultSet.getInt("accountId"));
//             a.setBalance(   resultSet.getDouble("balance"));
//
//                return a;
//            }
//        });
        return list;
    }

    @Override
    public void delete(int accountId) {
        String sql ="delete from accounts where accountId=?";
        this.jdbcTemplate.update(sql,accountId);
    }
}
