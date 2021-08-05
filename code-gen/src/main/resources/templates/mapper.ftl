<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}dao.${entityName}Dao">

    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${packageName}entity.${entityName}" >
    <#if fieldInfoList?exists && fieldInfoList?size gt 0>
    <#list fieldInfoList as fieldInfo >
        <result column="${fieldInfo.columnName}" property="${fieldInfo.fieldName}" />
    </#list>
    </#if>
    </resultMap>

    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
        <#if fieldInfoList?exists && fieldInfoList?size gt 0><#list fieldInfoList as fieldInfo >${fieldInfo.columnName}<#if fieldInfo_has_next>,</#if></#list></#if>
    </sql>

    <!-- 通用查询条件列 -->
    <sql id="Base_Condition_List">
        <where>
            <#list fieldInfoList as fieldInfo >
            <#if fieldInfo.columnName != "id" && fieldInfo.columnName != "uid" && fieldInfo.columnName != "create_time" && fieldInfo.columnName != "update_time" && fieldInfo.columnName != "modify_time" && fieldInfo.columnName != "create_by" && fieldInfo.columnName != "update_by">
            <if test="${fieldInfo.fieldName} != null <#if fieldInfo.fieldClass != "Integer"> and ${fieldInfo.fieldName} != ''</#if>">and ${fieldInfo.columnName} = ${r"#{"}${fieldInfo.fieldName}${r"}"}<#if fieldInfo_has_next> </#if></if>
            </#if>
            </#list>
        </where>
    </sql>

    <select id="findAll" parameterType="${packageName}entity.${entityName}" resultMap="BaseResultMap" >
        select
        <include refid="Base_Column_List"></include>
        from ${tableName}
        <include refid="Base_Condition_List"></include>
    </select>


</mapper>
