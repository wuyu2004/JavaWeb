package top.soft.classoa.mapper;


import top.soft.classoa.entity.User;
import top.soft.classoa.utils.MybatisUtils;

/**
 * @author 11448
 * @description: TODO
 * @date 2024/11/30 14:49
 */

public class UserMapper {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户
     */
    public User selectByUsername(String username){
        return (User) MybatisUtils.executeQuery(
                sqlSession -> sqlSession.selectOne("top.soft.classoa.mapper.UserMapper.selectByUsername", username)
        );
    }
}