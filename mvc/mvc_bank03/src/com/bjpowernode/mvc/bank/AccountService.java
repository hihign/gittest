package com.bjpowernode.mvc.bank;

import com.bjpowernode.mvc.exception.AppException;
import com.bjpowernode.mvc.exception.MoneyNotEnoughException;
import com.bjpowernode.utils.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 专门处理业务逻辑的类
 * 只负责编写业务逻辑代码
 * 对version 1.0的优化：增加了事务机制。
 * @author 小老虎
 * @version 2.0
 * @since 1.0
 */
public class AccountService {
    private AccountDao accountDao = new AccountDao();


    /**
     * 专门处理转账的业务
     * @param fromActno 转出账号
     * @param toActno 转入账号
     * @param money 转入金额
     */
    public void transfer(String fromActno,String toActno,double money) throws MoneyNotEnoughException, AppException {
        try (Connection conn = DBUtil.getConnection()){
            conn.setAutoCommit(false);
            Account fromAct = accountDao.selectByActno(fromActno,conn);
            double balance = fromAct.getBalance();
            //余额不足
            if (balance<money) {
                //转账失败
                throw new MoneyNotEnoughException("余额不足，不能完成转账！");
            }

            //余额充足，开始转帐
            Account toAct = accountDao.selectByActno(toActno,conn);
            fromAct.setBalance(balance - money);
            toAct.setBalance(toAct.getBalance()+money);
            int count = accountDao.update(fromAct,conn);

            //模拟异常
            /*String s = null;
            s.toString();
             */

            count += accountDao.update(toAct,conn);

            conn.commit();
            //转账失败
            if (count != 2){
                throw new AppException("账号异常,转账失败");
            }
            //转账成功

        }catch (SQLException e){
            e.printStackTrace();
        }



    }
}
