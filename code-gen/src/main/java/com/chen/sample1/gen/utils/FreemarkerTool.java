package com.chen.sample1.gen.utils;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * desc:
 */
public class FreemarkerTool {

    /**
     * process Template Into String
     *
     */
    private void processTemplateIntoString(Template template, Object model, File file)
            throws IOException, TemplateException {

        FileOutputStream fos = new FileOutputStream(file);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8),10240);
        template.process(model,out);
    }

    /**
     * process String
     */
    public void processString(String templateName, Map<String, Object> params,File file)
            throws IOException, TemplateException {
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        processTemplateIntoString(template, params,file);
    }

}
