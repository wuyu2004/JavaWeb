package top.soft.bookonline.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import top.soft.bookonline.dao.UserDao;
import top.soft.bookonline.entity.User;
import top.soft.bookonline.util.JdbcUtil;
import top.soft.bookonline.util.Md5Util;

/**
 * @author 11448
 * @description: UserDao 实现层
 * @date 2024/10/19 18:35
 */

public class UserDaoImpl implements UserDao {

    // 声名JDBC对象
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtil.getDataSource());

    @Override
    public int insertUser(User user) {
        // 插入用户sql
        String sql = "insert into t_user(account,password,nickname,avatar,address,create_time) values(?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, user.getAccount(), Md5Util.crypt(user.getPassword()), user.getNickname(), user.getAvatar(), user.getAddress(), user.getCreateTime());
    }

    @Override
    public User findUser(User userDto) {
        try {
            String sql = "SELECT * FROM t_user WHERE account =? AND password =?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), userDto.getAccount(), userDto.getPassword());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean checkAccountExists(String account) {
        String sql = "SELECT COUNT(*) FROM t_user WHERE account =?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, account);
        return count!= null && count > 0;
    }
}