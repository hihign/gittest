package com.powernode.spring6.dao;

import com.powernode.spring6.pojo.Account;

public interface AccountDao {
    Account selectByActno(String actno);
    int updateByAct(Account account);

    int insert(Account account);
}
