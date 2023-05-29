package com.powernode.spring6.dao.impl;

import com.powernode.spring6.dao.AccountDao;
import com.powernode.spring6.pojo.Account;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {
    @Resource(name="jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    @Override

    public Account selectByActno(String actno) {
        String sql = "select actno,balance from t_act where actno=?";
        Account account = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Account.class), actno);
        return account;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public int updateByAct(Account account) {
       String sql = "update t_act set balance=? where actno=?";
        int count = jdbcTemplate.update(sql,account.getBalance(),account.getActno());
        return count;
    }

    @Override
    public int insert(Account account) {
        String sql="insert into t_act(actno,balance) values(?,?)";
        int count = jdbcTemplate.update(sql, account.getActno(), account.getBalance());
        return count;
    }
}
