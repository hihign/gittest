package com.powernode.spring6.test;

import com.powernode.spring6.bean.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JdbcTemplateTest {
    @Test
    public void testInsert(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql = "insert into t_user(real_name,age) values(?,?)";
        int count = jdbcTemplate.update(sql, "王五", 60);
        System.out.println(count);
    }
    @Test
    public void testUpdate(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql = "update t_user set real_name=?,age=? where id=?";
        int count = jdbcTemplate.update(sql, "老王", 52, 1);
        System.out.println(count);
    }
    @Test
    public void testDelete(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql="delete from t_user where id = ?";
        int count = jdbcTemplate.update(sql, 1);
        System.out.println(count);
    }
    @Test
    public void testSelectOne(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql = "select id,real_name,age from t_user where id=?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), 2);
        System.out.println(user);
    }

    @Test
    public void testSelectAll(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql = "select * from t_user";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        users.forEach(user -> System.out.println(user));
    }

    @Test
    public void testTotal(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql = "select count(1) from t_user";
        Integer total = jdbcTemplate.queryForObject(sql, int.class);
        System.out.println(total);
    }

    @Test
    public void testBatchInsert(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql = "insert into t_user(real_name,age) values (?,?)";
        Object[] objects1 = {"小明",22};
        Object[] objects2 = {"小花",23};
        Object[] objects3 = {"小亮",24};
        List<Object[]> list = new ArrayList<>();
        list.add(objects1);
        list.add(objects2);
        list.add(objects3);
        int[] count = jdbcTemplate.batchUpdate(sql, list);
        System.out.println(Arrays.toString(count));
    }
    @Test
    public void testBatchDelete(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql="delete from t_user where id =?";
        Object[] objects1 = {4};
        Object[] objects2 = {5};
        Object[] objects3 = {6};
        List<Object[]> list = new ArrayList<>();
        list.add(objects1);
        list.add(objects2);
        list.add(objects3);
        int[] count = jdbcTemplate.batchUpdate(sql, list);
        System.out.println(Arrays.toString(count));
    }

    @Test
    public void testBatchUpdate(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql="update t_user set real_name=?,age=? where id=?";
        Object[] objects1 = {"锐雯",18,2};
        Object[] objects2={"EZ",17,3};
        List<Object[]> list = new ArrayList<>();
        list.add(objects1);
        list.add(objects2);
        int[] count = jdbcTemplate.batchUpdate(sql, list);
        System.out.println(Arrays.toString(count));
    }

    @Test
    public void testCallback(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql = "select id,real_name,age from t_user where id = ?";
        User user = jdbcTemplate.execute(sql,new PreparedStatementCallback<User>(){
                    @Override
                    public User doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                        User user = null;
                        ps.setInt(1,2);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            user = new User();
                            user.setId(rs.getLong("id"));
                            user.setRealName(rs.getString("real_name"));
                            user.setAge(rs.getInt("age"));
                        }
                        return user;
                    }
                });
        System.out.println(user);
    }
    @Test
    public void testDruid(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql = "select id,real_name,age from t_user where id = ?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), 2);
        System.out.println(user);
    }
}
