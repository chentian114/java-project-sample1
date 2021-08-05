package ${packageName}dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${packageName}entity.${entityName};
import org.springframework.stereotype.Repository;
import java.util.List;


/**
 * ${classComment} Dao
 *
 * @author ${author}
 * @date ${.now?string('yyyy/MM/dd')}
 */
@Repository
public interface ${entityName}Dao extends BaseMapper<${entityName}> {

    /**
     * 通过参数查询${classComment}列表
     *
     * @param ${entityName?uncap_first} 查询对象
     * @return 查询列表
     */
    List<${entityName}> findAll(${entityName} ${entityName?uncap_first});


 }