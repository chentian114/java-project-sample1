package ${packageName}service;

import com.baomidou.mybatisplus.extension.service.IService;
import ${packageName}entity.${entityName};
import ${rootPackage}tool.message.RequestMsg;
import ${rootPackage}tool.message.ResponseMsg;

/**
 * ${classComment} 接口
 *
 * @author ${author}
 * @date ${.now?string('yyyy/MM/dd')}
 */
public interface I${entityName}Service extends IService<${entityName}>{

    /**
     * 分页查询
     */
	ResponseMsg queryPage(RequestMsg requestMsg);

}
