package top.soft.classoa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 11448
 * @description: TODO
 * @date 2024/11/30 14:46
 */

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor

public class User {
    private Long userId;
    private String userName;
    private String Password;
    private Long employeeId;
    private Integer salt;
}
