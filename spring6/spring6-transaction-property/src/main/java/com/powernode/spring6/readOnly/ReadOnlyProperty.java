package com.powernode.spring6.readOnly;

import com.powernode.spring6.dao.AccountDao;
import com.powernode.spring6.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("readOnlyProperty")
@Transactional(readOnly=true)
public class ReadOnlyProperty {
    @Autowired
    @Qualifier("accountDao")
    private AccountDao accountDao;
    public Account select(String actno){
        Account account = accountDao.selectByActno(actno);
        return account;
    }
}
