package com.powernode.spring6.test;

import com.powernode.spring6.dao.*;
import com.powernode.spring6.dao.Math;
import com.powernode.spring6.dao1.People;
import com.powernode.spring6.dao1.SpringBean;
import com.powernode.spring6.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DITest {
    @Test
    public void testSetDI() {
        ApplicationContext application = new ClassPathXmlApplicationContext("beans.xml");
        UserService userServiceBean = application.getBean("userServiceBean", UserService.class);
        userServiceBean.addUser();
    }

    @Test
    public void testConstructorDI() {
        ApplicationContext application = new ClassPathXmlApplicationContext("beans.xml");
        UserService userServiceBean = application.getBean("userServiceBean1", UserService.class);
        userServiceBean.addUser();
    }

    @Test
    public void testSimpleTypeInjectionWithSet() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        MyDataSource dataSourceBean = applicationContext.getBean("DataSourceBean", MyDataSource.class);
        System.out.println(dataSourceBean);
    }
    @Test
    public void testArrayWithSetInjection(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Person person = applicationContext.getBean("PersonBean", Person.class);
        System.out.println(person);

    }
    @Test
    public void testListAndSetWithSetInjection(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Student student = applicationContext.getBean("StudentBean", Student.class);
        System.out.println(student);

    }

    @Test
    public void testProperties(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        TeacherProperties teacherProperties = applicationContext.getBean("TeacherPropertiesBean", TeacherProperties.class);
        System.out.println(teacherProperties);
    }

    @Test
    public void testEmptyCharacter(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Friend friendBean4 = applicationContext.getBean("FriendBean4", Friend.class);
        System.out.println(friendBean4);
    }
    @Test
    public void testNull(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Friend friendBean5 = applicationContext.getBean("FriendBean5", Friend.class);
        System.out.println(friendBean5);
    }

    @Test
    public void testSpecialCharacter(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Math mathBean = applicationContext.getBean("MathBean", Math.class);
        System.out.println(mathBean);
    }

    @Test
    public void testP(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-namespace.xml");
        Drink drinkBean = applicationContext.getBean("DrinkBean", Drink.class);
        System.out.println(drinkBean);
    }

    @Test
    public void testC(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-namespace.xml");
        Drink drinkBean = applicationContext.getBean("DrinkBeanToConstructor", Drink.class);
        System.out.println(drinkBean);
    }

    @Test
    public void testUtil(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-namespace.xml");
        MyDataSource1 datasource1 = applicationContext.getBean("datasource1", MyDataSource1.class);
        MyDataSource2 datasource2 = applicationContext.getBean("datasource2", MyDataSource2.class);
        System.out.println(datasource1);
        System.out.println(datasource2);
    }

    @Test
    public void testAutoWireByName(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-autowire.xml");
        People peopleBean = applicationContext.getBean("PeopleBean", People.class);
        System.out.println(peopleBean);
    }
    @Test
    public void testAutoWireByType(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-autowire.xml");
        People peopleBean1 = applicationContext.getBean("PeopleBean1", People.class);
        System.out.println(peopleBean1);
    }

    @Test
    public void testContextName(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-outerProperties.xml");
        MyDataSource1 datasource1 = applicationContext.getBean("datasource1", MyDataSource1.class);
        System.out.println(datasource1);
    }

    //一个线程对应一个对象
    @Test
    public void testThreadScope(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("scope.xml");
        SpringBean springbean1 = applicationContext.getBean("springBean", SpringBean.class);
        SpringBean springbean2 = applicationContext.getBean("springBean", SpringBean.class);
        System.out.println(springbean1);
        System.out.println(springbean2);

        new Thread(()->{
            SpringBean springbean3 = applicationContext.getBean("springBean", SpringBean.class);
            SpringBean springbean4 = applicationContext.getBean("springBean", SpringBean.class);
            System.out.println(springbean3);
            System.out.println(springbean4);
        }).start();
    }
}
