package com.powernode.spring6.rollbackFor;

import com.powernode.spring6.dao.AccountDao;
import com.powernode.spring6.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("rollbackForProperty")
@Transactional(rollbackFor = RuntimeException.class)
public class RollbackForProperty {
    @Autowired
    @Qualifier("accountDao")
    private AccountDao accountDao;
    Account account = new Account("act-06",8000);
    public void insert1(){
        accountDao.insert(account);
        /*throw new RuntimeException("运行异常");*/
    }

}
