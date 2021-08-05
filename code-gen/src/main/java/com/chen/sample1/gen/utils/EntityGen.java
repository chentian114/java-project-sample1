package com.chen.sample1.gen.utils;

import com.chen.sample1.gen.GenTool;
import com.chen.sample1.gen.dto.ColumnInfo;
import com.chen.sample1.gen.dto.FieldInfo;
import com.chen.sample1.gen.dto.PrimaryKeyDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc 生成实体类主体代码
 * @Author Chentian
 * @date 2021/7/20
 */
public class EntityGen {

    public static final String template = "entity.ftl";
    public static final String template2 = "mapper.ftl";

    public static PrimaryKeyDto parse(String tableName, String tableComment, String entityName, List<ColumnInfo> columnInfoList) {

        PrimaryKeyDto primaryKeyDto = new PrimaryKeyDto();
        for (ColumnInfo columnInfo : columnInfoList) {
            String fieldClass = GenUtil.sqlType2JavaType(columnInfo.getDataType());
            String fieldName = GenUtil.initcap2(columnInfo.getColumnName());
            if("PRI".equals(columnInfo.getColumnKey())){
                primaryKeyDto.setPrimaryName(fieldName);
                primaryKeyDto.setPrimaryType(fieldClass);
                if (!columnInfo.getExtra().equals("auto_increment")) {
                    primaryKeyDto.setUUID(true);
                }
            }
        }

        boolean hasDate = false;
        boolean hasSql = false;

        boolean isPrimary = false;
        List<FieldInfo> fieldInfoList = new ArrayList<>();
        for (ColumnInfo columnInfo : columnInfoList) {
            if (columnInfo.getDataType().equalsIgnoreCase("datetime")
                    || columnInfo.getDataType().equalsIgnoreCase("timestamp")) {
                hasDate = true;
            }
            if (columnInfo.getDataType().equalsIgnoreCase("image")
                    || columnInfo.getDataType().equalsIgnoreCase("text")) {
                hasSql = true;
            }
            if("PRI".equals(columnInfo.getColumnKey())){
                isPrimary = true;
            }
            String fieldClass = GenUtil.sqlType2JavaType(columnInfo.getDataType());
            String fieldName = GenUtil.initcap2(columnInfo.getColumnName());

            FieldInfo fieldInfo = FieldInfo.builder()
                    .fieldComment(columnInfo.getColumnComment())
                    .fieldClass(fieldClass)
                    .fieldName(fieldName)
                    .fieldKey(columnInfo.getColumnKey())
                    .columnName(columnInfo.getColumnName())
                    .extra(columnInfo.getExtra())
                    .isPrimary(isPrimary)
                    .build();
            fieldInfoList.add(fieldInfo);
        }

        Map<String, Object> params = new HashMap<>();
        params.put("packageName", GenTool.entityPackageOutPath);
        params.put("classComment", tableComment);
        params.put("tableName", tableName);
        params.put("author", GenTool.authorName);
        params.put("entityName", entityName);
        params.put("importDate",hasDate);
        params.put("importSql",hasSql);
        params.put("fieldInfoList",fieldInfoList);
        params.put("primaryKeyDto",primaryKeyDto);

        String fileName = entityName + ".java";

        GenUtil.genFile(template,params, GenTool.entityPackageOutPath,fileName);


        params.put("packageName", GenTool.modulePackage);
        fileName = entityName + "Mapper.xml";
        GenUtil.genFile(template2,params,"mapper",fileName);

        return primaryKeyDto;
    }


}
