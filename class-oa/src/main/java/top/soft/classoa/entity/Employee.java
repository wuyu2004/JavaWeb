package top.soft.classoa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 1144
 * @description: TODO
 * @date 2024/12/7 13:33
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    /**
     * 员工编号
     */
    private Long employeeId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 部门编号
     */
    private Long departmentId;
    /**
     * 头衔/职务
     */
    private String title;
    /**
     * 岗位级别
     */
    private Integer level;
    /**
     * 头像
     */
    private String avatar;
}
