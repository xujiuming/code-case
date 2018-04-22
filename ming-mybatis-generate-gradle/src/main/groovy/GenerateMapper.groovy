import groovy.text.SimpleTemplateEngine
import org.apache.commons.lang3.StringUtils

import java.text.SimpleDateFormat

/**
 * 生成mapper
 * @author ming
 * @date 2018-04-22 14:12
 */
class GenerateMapper {

    /**
     * 生成 mapper
     * @author ming
     * @date 2018-04-22 14:42
     */
    static generateMapper(String packageName, String className, String idType, String outFilePath) {
        Map binding = [:]
        binding.put("packageName", generatePackageName(packageName))
        binding.put("importNameList", generateImport(packageName, className))
        binding.put("className", generateClassName("${className}Mapper"))
        binding.put("pojoClass", className)
        binding.put("pojoClassAll", "Collection<${className}>")
        binding.put("idClass", idType)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        binding.put("date", dateFormat.format(new Date()))
        File file = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "mapper.template")

        def engine = new SimpleTemplateEngine()
        def template = engine.createTemplate(file.text).make(binding)

        File outFile
        //输出文件
        if (null == outFilePath) {
            outFile = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "${className}Mapper.java")
        } else {
            outFile = new File(outFilePath + "${className}Mapper.java")
        }
        template.writeTo(outFile.newPrintWriter())
    }
    /**生成 包名
     * @param packageName
     * @author ming
     * @date 2018-04-22 14:00
     */
    static String generatePackageName(String packageName) {
        if (StringUtils.isNotEmpty(packageName)) {
            return "package ${packageName};"
        }
        return "package com.ming;\n"
    }

    /**生成导入信息
     * @param typeSet
     * @author ming
     * @date 2018-04-22 14:00
     */
    static String generateImport(String packageName, String className) {
        StringBuffer sb = new StringBuffer()
        //导入 collection
        sb.append("import java.util.Collection;\n")
        return sb
    }

    /**生成类名
     * @author ming
     * @date 2018-04-22 14:01
     */
    static String generateClassName(String className) {
        if (StringUtils.isNotEmpty(className)) {
            return className;
        }
        return "Ming"
    }

}
