import groovy.text.SimpleTemplateEngine
import org.apache.commons.lang3.StringUtils

import java.text.SimpleDateFormat

/**
 * 生成pojo工具类
 * @author ming
 * @date 2018-04-22 13:44
 */
class GeneratePojo {

    /**生成 pjoo
     * @param packageName
     * @param className
     * @params Map < columnsName , columnsType >
     * @params outFile
     * @author ming
     * @date 2018-04-22 12:06
     */
    static generate(String packageName, String className, Map<String, String> tableColumns, String outFilePath) {
        Map binding = [:]
        binding.put("packageName", generatePackageName(packageName))
        binding.put("importNameList", generateImport(tableColumns.values()))
        binding.put("className", generateClassName(className))
        binding.put("attribute", generateAttribute(tableColumns))
        binding.put("attributeSetterAndGettter", generateAttributeSetterAndGetter(tableColumns))
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        binding.put("date", dateFormat.format(new Date()))
        File file = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "pojo.template")

        def engine = new SimpleTemplateEngine()
        def template = engine.createTemplate(file.text).make(binding)

        File outFile
        //输出文件
        if (null == outFilePath) {
            outFile = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "${className}.java")
        } else {
            outFile = new File(outFilePath + "${className}.java")
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
    static String generateImport(Collection<String> typeSet) {
        StringBuffer sb = new StringBuffer()
        typeSet?.each {
            //date类型处理
            if ("Date".equals(it)) {
                sb.append("import java.util.Date;\n")
            }
        }
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
    /**生成 属性
     * @param map
     * @author ming
     * @date 2018-04-22 14:01
     */
    static String generateAttribute(Map<String, String> map) {
        StringBuffer sb = new StringBuffer()
        //处理attribute
        sb.append("\n")
        map.each() {
            k, v ->
                sb.append("  private ${v} ${k};\n")
        }
        return sb
    }

    /**生成属性的 setter 和getter
     * @author ming
     * @date 2018-04-22 14:02
     */
    static String generateAttributeSetterAndGetter(Map<String, String> map) {
        StringBuffer sb = new StringBuffer()

        sb.append("\n")
        map.each() {
            k, v ->
                sb.append("\n")
                sb.append("  public ${v} get${k.capitalize()}() {\n")
                sb.append("    return ${k};\n")
                sb.append("  }\n")
                sb.append("\n")
                sb.append("  public void set${k.capitalize()}(${v} ${k}) {\n")
                sb.append("    this.${k} = ${k};\n")
                sb.append("  }\n")
        }
        return sb
    }
}
