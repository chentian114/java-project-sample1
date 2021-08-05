package ${packageName}service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${packageName}entity.${entityName};
import ${packageName}service.I${entityName}Service;
import ${daoPackageOutPath}.${entityName}Dao;
import ${rootPackage}tool.message.PageResult;
import ${rootPackage}tool.message.RequestMsg;
import ${rootPackage}tool.message.ResponseMsg;

/**
 * ${classComment}
 *
 * @author ${author}
 * @date ${.now?string('yyyy/MM/dd')}
 */
@Service
public class ${entityName}ServiceImpl extends ServiceImpl<${entityName}Dao, ${entityName}> implements I${entityName}Service {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private ${entityName}Dao ${entityName?uncap_first}Dao;


	@Override
	public ResponseMsg queryPage(RequestMsg requestMsg){
		String orderBy = "create_time desc";
		${entityName} ${entityName?uncap_first} = requestMsg.getParams().toBean(${entityName}.class);
		PageInfo<${entityName}> pageInfo = PageMethod.startPage(requestMsg.getPageNum(), requestMsg.getPageSize(),orderBy)
    									.doSelectPageInfo(() -> ${entityName?uncap_first}Dao.findAll(${entityName?uncap_first}));

    	PageResult<${entityName}> pageResult = new PageResult<>(pageInfo);
    	logger.info("pageResult:{}",pageResult);
        return ResponseMsg.createSuccessResponse(pageResult);
	}

}
