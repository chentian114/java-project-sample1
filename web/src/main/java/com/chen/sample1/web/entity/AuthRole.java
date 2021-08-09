package com.chen.sample1.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;
import lombok.*;
import java.util.Date;

/**
 *  角色表
 *
 * @author Chentian
 * @date 2021/08/09
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
@TableName(value = "t_auth_role")
public class AuthRole implements Serializable{
    /**
     * 主键
     */
    @TableId(type=IdType.AUTO)
    private Integer id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色描述
     */
    private String memo;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
}
