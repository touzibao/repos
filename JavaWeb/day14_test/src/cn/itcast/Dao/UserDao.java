package cn.itcast.Dao;

import cn.itcast.domain.User;
import cn.itcast.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;

/*
* 操作user表格的类
* */
public class UserDao {
    private static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDateSourse());

    public static User login(User loginUser) {
        //获取连接对象
        try {
            //Connection conn = JDBCUtils.getConnection();
            //获取sql
            String sql = "SELECT * FROM USER WHERE username=? AND password=?";
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());//???
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            //如果没有这个用户,返回空值
            return null;
        }
    }
}
