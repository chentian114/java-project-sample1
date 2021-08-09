package com.chen.sample1.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.sample1.web.entity.AuthRole;
import com.chen.sample1.tool.message.RequestMsg;
import com.chen.sample1.tool.message.ResponseMsg;

/**
 *  角色表 接口
 *
 * @author Chentian
 * @date 2021/08/09
 */
public interface IAuthRoleService extends IService<AuthRole>{

    /**
     * 分页查询
     */
	ResponseMsg queryPage(RequestMsg requestMsg);

}
