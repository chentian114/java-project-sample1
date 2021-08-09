package ${packageName}controller;

import ${basePackageOutPath}.BaseController;
import ${entityPackageOutPath}.${entityName};
import ${rootPackage}tool.message.RequestMsg;
import ${rootPackage}tool.message.ResponseMsg;
import ${svcPackageOutPath}.I${entityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ${classComment} Controller
 *
 * @author ${author}
 * @date ${.now?string('yyyy/MM/dd')}
 */
@RestController
@RequestMapping("/${entityName?uncap_first}")
public class ${entityName}Controller extends BaseController {

    @Autowired
    private I${entityName}Service ${entityName?uncap_first}Service;

	@GetMapping("/getById/{${primaryKeyDto.primaryName}}")
	public ResponseMsg getById(@PathVariable ${primaryKeyDto.primaryType} ${primaryKeyDto.primaryName}) {
		logger.info("params:{}", ${primaryKeyDto.primaryName});
		${entityName} result = ${entityName?uncap_first}Service.getById(${primaryKeyDto.primaryName});
		ResponseMsg responseMsg = new ResponseMsg();
		responseMsg.setData(result);
		return responseMsg;
	}

	@GetMapping("/queryPage")
	public ResponseMsg queryPage(${entityName} model,Integer pageNum,Integer pageSize) {
		RequestMsg requestMsg = new RequestMsg(model,pageNum,pageSize);
		logger.info("pageNum:{} pageSize:{} model:{}", pageNum,pageSize,model.toString());
		return ${entityName?uncap_first}Service.queryPage(requestMsg);
	}

	@PostMapping("/save")
	public ResponseMsg save(@RequestBody ${entityName} model) {
		logger.info("params:{}", model.toString());
		boolean result = ${entityName?uncap_first}Service.save(model);
		ResponseMsg responseMsg = new ResponseMsg();
		responseMsg.setData(result);
		return responseMsg;
	}

	@DeleteMapping("/deleteById/{${primaryKeyDto.primaryName}}")
	public ResponseMsg deleteById(@PathVariable ${primaryKeyDto.primaryType} ${primaryKeyDto.primaryName}) {
		logger.info("params:{}", ${primaryKeyDto.primaryName});
		boolean result = ${entityName?uncap_first}Service.removeById(${primaryKeyDto.primaryName});
		ResponseMsg responseMsg = new ResponseMsg();
		responseMsg.setData(result);
		return responseMsg;
	}

}