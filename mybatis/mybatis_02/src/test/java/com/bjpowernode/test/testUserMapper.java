package com.bjpowernode.test;

import com.bjpowernode.mapper.UsersMapper;
import com.bjpowernode.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class testUserMapper {
    private SqlSession sqlSession = null;
    //获取动态代理对象
    UsersMapper usersMapper = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Before
    public void openSqlSession() throws IOException {
        //1.读取核心文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        usersMapper = sqlSession.getMapper(UsersMapper.class);
    }
    @After
    public void closeSqlSession(){
        sqlSession.close();
    }
    @Test
    public void testGetAll(){

        List<User> userList = usersMapper.getAll();
        userList.forEach(user-> System.out.println(user));
    }
    @Test
    public void testInsert() throws ParseException {
        Date date = sdf.parse("2001-02-13");
        int num = usersMapper.insert(new User("Jack",date,"1","长沙望城区"));
        System.out.println(num);
        sqlSession.commit();
    }
    @Test
    public void testDelete(){
        int num = usersMapper.delete(7);
        System.out.println(num==1?"删除成功":"删除失败");
        sqlSession.commit();
    }
    @Test
    public void testUpdate() throws ParseException {
        int num = usersMapper.update(new User(27,"Rose",sdf.parse("2003-06-13"),"2","上海市"));
        System.out.println(num);
        sqlSession.commit();
    }
    @Test
    public void testGetById(){
        User user = usersMapper.getById(2);
        System.out.println(user);
    }
    @Test
    public void testGetByName(){
        List<User> userList = usersMapper.getByName("小");
        userList.forEach(user-> System.out.println(user));
    }
    @Test
    public void testGetByNamePlus(){
        List<User> userList = usersMapper.getByNamePlus("小");
        userList.forEach(user-> System.out.println(user));
    }
    @Test
    public void testGetByNameOrAge(){
        List<User> userList = usersMapper.getByNameOrAddress("address","市");
        userList.forEach(user-> System.out.println(user));
    }
    @Test
    //UUID 是全球唯一的字符串，由数字、字母、中划线组成，共36个
    public void testUUID(){
        //java中的UUID
        UUID uuid = UUID.randomUUID();
        //自动调用toString转成字符串为36个字符，是字符串就可以用字符串的所有方法
        //有些业务需要上传不同的文件名称，可以截取该字符串获得
        System.out.println(uuid);
    }
}
