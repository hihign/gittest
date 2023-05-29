package com.powernode.spring6.test;

import com.powernode.spring6.config.Spring6Config;
import com.powernode.spring6.rollbackFor.RollbackForProperty;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class NoXmlTest {
    @Test
    public void testNoXml(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Spring6Config.class);
        RollbackForProperty rollbackForProperty = applicationContext.getBean("rollbackForProperty", RollbackForProperty.class);
        rollbackForProperty.insert1();
    }
}
