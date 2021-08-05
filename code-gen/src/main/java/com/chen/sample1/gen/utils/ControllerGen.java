package com.chen.sample1.gen.utils;

import com.chen.sample1.gen.GenTool;
import com.chen.sample1.gen.dto.PrimaryKeyDto;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc 生成Dao类主体代码
 * @Author Chentian
 * @date 2021/7/21
 */
public class ControllerGen {

    public static final String template = "controller.ftl";

    public static String parse(String tableComment, String entityName, PrimaryKeyDto primaryKeyDto) {

        Map<String, Object> params = new HashMap<>();
        params.put("basePackageOutPath", GenTool.basePackageOutPath);
        params.put("entityPackageOutPath", GenTool.entityPackageOutPath);
        params.put("entityName", entityName);
        params.put("rootPackage", GenTool.rootPackage);
        params.put("svcPackageOutPath", GenTool.svcPackageOutPath);
        params.put("packageName", GenTool.modulePackage);
        params.put("classComment", tableComment);
        params.put("author", GenTool.authorName);
        params.put("primaryKeyDto", primaryKeyDto);

        String fileName = entityName + "Controller.java";
        GenUtil.genFile(template,params, GenTool.webPackageOutPath,fileName);

        return null;
    }

}
