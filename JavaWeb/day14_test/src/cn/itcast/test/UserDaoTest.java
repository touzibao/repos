package cn.itcast.test;

import cn.itcast.Dao.UserDao;
import cn.itcast.domain.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void testLogin(){
        User loginUser = new User();
        loginUser.setUsername("2");
        loginUser.setPassword("123456");
        User user = UserDao.login(loginUser);
        System.out.println(user);
    }
}
