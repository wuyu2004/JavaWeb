package top.soft.classoa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 1144
 * @description: TODO
 * @date 2024/12/7 13:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {
    private Long departmentId;
    private String departmentName;
}
