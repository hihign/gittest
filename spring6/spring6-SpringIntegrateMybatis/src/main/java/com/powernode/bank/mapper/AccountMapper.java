package com.powernode.bank.mapper;

import com.powernode.bank.pojo.Account;

import java.util.List;

public interface AccountMapper {
    /**
     * 添加用户
     * @param account
     * @return
     */
    int insert(Account account);

    /**
     * 根据账号删除用户
     * @param actno
     * @return
     */
    int deleteByActno(String actno);

    /**
     * 更新账户
     * @param account
     * @return
     */
    int update(Account account);

    /**
     * 根据账号查询账户
     * @param actno
     * @return
     */
    Account selectByActno(String actno);

    /**
     * 查询所有账户
     * @return
     */
    List<Account> selectAll();
}
