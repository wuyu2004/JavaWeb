package top.soft.brandlist.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

/**
 * @author 11448
 * @description: 品牌实体类，用于存储品牌相关信息
 * @date 2024/11/9 16:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand {
    private Integer id;
    private String brandName;
    private String companyName;
    private Integer ordered;
    private String description;
}