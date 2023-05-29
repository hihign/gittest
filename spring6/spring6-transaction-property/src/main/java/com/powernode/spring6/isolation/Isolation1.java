package com.powernode.spring6.isolation;

import com.powernode.spring6.dao.AccountDao;
import com.powernode.spring6.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component("i1")
@Transactional(isolation= Isolation.READ_UNCOMMITTED)
public class Isolation1 {
    @Autowired
    @Qualifier("accountDao")
    private AccountDao accountDao;
    public Account select(String actno){
        Account account = accountDao.selectByActno(actno);
        return account;
    }
}
