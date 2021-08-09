package ${packageName};

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;
import lombok.*;
<#if importDate?? && importDate>
import java.util.Date;
</#if>
<#if importSql?? && importSql>
import java.sql.*;
</#if>

/**
 * ${classComment}
 *
 * @author ${author}
 * @date ${.now?string('yyyy/MM/dd')}
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
@TableName(value = "${tableName}")
public class ${entityName} implements Serializable{
<#if fieldInfoList?exists && fieldInfoList?size gt 0>
 <#assign index=0 />
 <#list fieldInfoList as fieldInfo >
    /**
     * ${fieldInfo.fieldComment}
     */
    <#if fieldInfo.fieldKey=='PRI'>
    <#if fieldInfo.extra == 'auto_increment'>
    @TableId(type=IdType.AUTO)
    <#else >
    @TableId(type = IdType.UUID)
    </#if>
    </#if>
    private ${fieldInfo.fieldClass} ${fieldInfo.fieldName};
 </#list>
</#if>
}
