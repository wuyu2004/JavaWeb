package top.soft.classoa.mapper;

import top.soft.classoa.entity.User;
import top.soft.classoa.utils.MyBatisUtils;

public class UserMapper {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    public User selectByUsername(String username){
        return (User) MyBatisUtils.executeQuery(
                sqlSession -> sqlSession.selectOne("top.soft.classoa.mapper.UserMapper.selectByUsername",username)
        );
    }

}
