package com.chen.sample1.gen.utils;

import com.chen.sample1.gen.GenTool;

import java.io.File;
import java.util.Map;

/**
 * @desc
 * @Author Chentian
 * @date 2021/7/20
 */
public class GenUtil {

    private static FreemarkerTool freemarkerTool = new FreemarkerTool();

    public static void genFile(String template, Map<String, Object> params, String packagePath, String fileName){
        try {
            String path = System.getProperty("user.dir")+"/";
            String outputPath = path + GenTool.moduleName;

            if(!fileName.endsWith(".xml")){
                outputPath += "/src/main/java/" + packagePath.replace(".", "/");
            }else {
                outputPath += "/src/main/resources/"+packagePath;
            }

            System.out.println(outputPath);
            File outPutFile = new File(outputPath);
            if (!outPutFile.exists()) {
                outPutFile.mkdirs();
            }
            String filePath = outputPath + "/" + fileName;

            File mapperFileDO = new File(filePath);

            System.out.println("----------生成-----------"+template);
            freemarkerTool.processString(template, params, mapperFileDO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 功能：获得列的数据类型
     *
     * @param sqlType
     * @return
     */
    public static String sqlType2JavaType(String sqlType) {

        if (sqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "Byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "Short";
        } else if (sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("INT UNSIGNED")) {
            //INT UNSIGNED无符号整形
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "Float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("double") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("timestamp")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        }
        return null;
    }


    /**
     * 功能：将输入字符串的首字母改成大写
     */
    public static String initcap(String str) {
        String[] arr = str.split("_");
        String result = "";
        if (arr.length == 1) {
            return initcap0(str.toLowerCase());
        }
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                char[] ch = initcap0(arr[i].toLowerCase()).toCharArray();
                if (ch[0] >= 'a' && ch[0] <= 'z') {
                    ch[0] = (char) (ch[0] - 32);
                }
                result += new String(ch);
            } else {
                result += initcap0(arr[i].toLowerCase());
            }
        }
        return result;
    }


    /**
     * 功能：将输入字符串的首字母改成小写
     *
     * @param str
     * @return
     */
    public static String initcap2(String str) {
        String[] arr = str.split("_");
        String result = "";
        if (arr.length == 1) {
            return initcap1(str.toLowerCase());
        }
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                char[] ch = initcap0(arr[i].toLowerCase()).toCharArray();
                if (ch[0] >= 'A' && ch[0] <= 'Z') {
                    ch[0] = (char) (ch[0] + 32);
                }
                result += new String(ch);
            } else {
                result += initcap0(arr[i].toLowerCase());
            }
        }
        return result;
    }

    public static String initcap1(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'A' && ch[0] <= 'Z') {
            ch[0] = (char) (ch[0] + 32);
        }
        return new String(ch);
    }

    public static String initcap0(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
}
