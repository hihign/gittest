package com.bjpowernode.test;

import com.bjpowernode.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest {
    @Test
    public void testGetAll() throws IOException {
        //使用文件流读取核心配置文件SqlMapConfig.xml
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessiionFactory 工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //取出sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //完成查询操作
        List<Student> list = sqlSession.selectList("asher.getAll");
        list.forEach(student->System.out.println(student));
        //关闭sqlSession
        sqlSession.close();
    }
    @Test
    public void testGetById() throws IOException {
        //读取核心配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //执行sql语句
        Student student = sqlSession.selectOne("asher.getById", 2);
        System.out.println(student);
        //关闭sqlSession
        sqlSession.close();
    }
    @Test
    public void testGetByName() throws IOException{
        //1、读取核心文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、创建SqlSessionFactory工厂对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3、获取sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //4、执行sql语句，完成需求功能
        List<Student> list = sqlSession.selectList("asher.getByName","王");
        list.forEach(student -> System.out.println(student));
        //5、关闭sqlSession
        sqlSession.close();
    }
    @Test
    public void testInsert() throws IOException {
        //1.读取核心文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //4.执行sql
        int num = sqlSession.insert("asher.insert",new Student("Jack","jack@126.com",25));
        System.out.println(num);
        //因为事务管理的方式为JDBC，需要我们程序员手动提交事务
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testDelete() throws IOException {
        //1.读取核心文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //4.执行sql
        int num = sqlSession.delete("asher.delete",1);
        System.out.println(num);
        //手动提交事务
        sqlSession.commit();
        //5.关闭sqlSession
        sqlSession.close();
    }
    @Test
    public void testUpdate() throws IOException{
        //1.读取核心文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //4.执行sql，完成功能
        int num = sqlSession.update("asher.update",new Student(2,"Rose","Rose@458.com",30));
        System.out.println(num);
        //手动提交事务
        sqlSession.commit();
        //5.关闭SqlSession对象
        sqlSession.close();
    }
}
