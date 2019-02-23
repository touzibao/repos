package cn.itcast.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
/*
* JDBC工具类
* */
public class JDBCUtils {
    private static DataSource ds = null;

    static {
        try {
            //获取配置文件
            Properties properties = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(is);
            //使用druid中的工厂类创建JDBC的实现类对象
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
}
    //创建getConnection对象获取Connection对象
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    //创建连接池对象
    public static DataSource getDateSourse(){
        return ds;
    }

}
