package top.soft.bookonline.dao;

import top.soft.bookonline.entity.User;

public interface UserDao {
    /**
     * 新增用户
     * @param user
     * @return
     */
    int insertUser(User user);
}
