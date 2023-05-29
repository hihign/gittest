package com.powernode.spring6.test;

import com.powernode.spring6.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MVCTest {
    @Test
    public void testMVC(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        AccountService accountService = context.getBean("accountService", AccountService.class);
        try{
            accountService.transfer("act-01","act-02",10000);
            System.out.println("转账成功");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
