package com.chen.sample1.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sample1.web.entity.AuthRole;
import com.chen.sample1.web.service.IAuthRoleService;
import com.chen.sample1.web.dao.AuthRoleDao;
import com.chen.sample1.tool.message.PageResult;
import com.chen.sample1.tool.message.RequestMsg;
import com.chen.sample1.tool.message.ResponseMsg;

/**
 *  角色表
 *
 * @author Chentian
 * @date 2021/08/09
 */
@Service
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleDao, AuthRole> implements IAuthRoleService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private AuthRoleDao authRoleDao;

	@Override
	public ResponseMsg queryPage(RequestMsg requestMsg){
		AuthRole authRole = requestMsg.getParams().toBean(AuthRole.class);
		PageInfo<AuthRole> pageInfo = PageMethod.startPage(requestMsg.getPageNum(), requestMsg.getPageSize(),RequestMsg.DEFAULT_SORT)
    									.doSelectPageInfo(() -> authRoleDao.findAll(authRole));

    	PageResult<AuthRole> pageResult = new PageResult<>(pageInfo);
    	logger.info("pageResult:{}", pageResult.toString());
        return ResponseMsg.createSuccessResponse(pageResult);
	}

}
