package com.chen.sample1.gen.utils;

import com.chen.sample1.gen.GenTool;
import com.chen.sample1.gen.dto.PrimaryKeyDto;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc 生成Service主体代码
 * @Author Chentian
 * @date 2021/7/21
 */
public class ServiceGen {

    public static final String template = "service.ftl";
    public static final String template2 = "service_impl.ftl";

    public static String parse(String tableComment, String entityName, PrimaryKeyDto primaryKeyDto) {

        Map<String, Object> params = new HashMap<>();
        params.put("packageName", GenTool.modulePackage);
        params.put("classComment", tableComment);
        params.put("author", GenTool.authorName);
        params.put("entityName", entityName);
        params.put("daoPackageOutPath",GenTool.daoPackageOutPath);
        params.put("primaryKeyDto",primaryKeyDto);
        params.put("rootPackage",GenTool.rootPackage);

        String fileName = "I"+entityName + "Service.java";
        GenUtil.genFile(template,params, GenTool.svcPackageOutPath,fileName);

        fileName = entityName + "ServiceImpl.java";
        GenUtil.genFile(template2,params, GenTool.svcImplPackageOutPath,fileName);

        return null;
    }



}
