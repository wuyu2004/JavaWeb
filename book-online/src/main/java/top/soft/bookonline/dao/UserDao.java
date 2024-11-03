package top.soft.bookonline.dao;

import top.soft.bookonline.entity.User;

public interface UserDao {
    /**
     * 新增用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 根据账号密码查用户
     * @param user
     * @return
     */
    User findUser(User user);

    /**
     * 检查用户名是否已存在
     * @param account
     * @return true表示已存在，false表示不存在
     */
    boolean checkAccountExists(String account);
}
