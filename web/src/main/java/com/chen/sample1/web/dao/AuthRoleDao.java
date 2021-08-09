package com.chen.sample1.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sample1.web.entity.AuthRole;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
 *  角色表 Dao
 *
 * @author Chentian
 * @date 2021/08/09
 */
@Repository
public interface AuthRoleDao extends BaseMapper<AuthRole> {

    /**
     * 通过参数查询 角色表列表
     *
     * @param authRole 查询对象
     * @return 查询列表
     */
    List<AuthRole> findAll(AuthRole authRole);


 }