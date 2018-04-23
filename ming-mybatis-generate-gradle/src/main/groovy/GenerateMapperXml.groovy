import groovy.text.SimpleTemplateEngine
import org.apache.commons.lang3.StringUtils

/**
 * 生成 mapper xml
 * @author ming
 * @date 2018-04-22 18:38
 */
class GenerateMapperXml {

    public static void main(String[] args) {
        generate("com.ming", "Ming", [id: "Integer", loginName: "String"], null)
    }

    static generate(String packageName, String className, Map<String, String> map, String outFilePath) {
        Map binding = [:]
        binding.put("className", className)
        binding.put("nameSpace", "${packageName}.${className}Mapper")
        binding.put("resultMap", generateResultMap(packageName, className, map))
        binding.put("insert", generateInsert(packageName, className, map))
        binding.put("insertAll", generateInsertAll(packageName, className, map))
        binding.put("update", generateUpdate(packageName, className, map))
        binding.put("findById", generateFindById(packageName, className, map))
        binding.put("findAll", generateFindAll(packageName, className, map))
        binding.put("deleteById", generateDeleteById(packageName, className, map))
        File file = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "xml.template")

        def engine = new SimpleTemplateEngine()
        def template = engine.createTemplate(file.text).make(binding)

        File outFile
        //输出文件
        if (null == outFilePath) {
            outFile = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "${className}Mapper.xml")
        } else {
            outFile = new File(outFilePath + "${className}Mapper.xml")
        }
        template.writeTo(outFile.newPrintWriter())
    }

    static javaTypeTODbType(String dbType) {
        Map<String, String> map = [:]
        map.put("Integer", "INT")
        map.put("String", "VARCHAR")
        map.put("Date", "TIMESTAMP")
        //如果类型在map中能找到  那么返回对应类型 找不到 默认 VARCHAR
        if (map.containsKey(dbType)) {
            return map.get(dbType)
        } else {
            return "VARCHAR"
        }
    }

    /**
     * 驼峰和下划线互换格式
     * toCamel = true  下划线 转 驼峰
     * toCamel=false 驼峰转下划线
     * @author ming
     * @date 2018-04-22 12:11
     */
    static String changeStyle(String str, boolean toCamel) {
        if (!str || str.size() <= 1) {
            return str
        }
        if (toCamel) {
            String r = str.toLowerCase().split('_').collect { cc -> StringUtils.capitalize(cc) }.join('')
            return r[0].toLowerCase() + r[1..-1]
        } else {
            str = str[0].toLowerCase() + str[1..-1]
            return str.collect { cc -> ((char) cc).isUpperCase() ? '_' + cc.toLowerCase() : cc }.join('')
        }
    }
    /**生成 result map
     *
     * @author ming
     * @date 2018-04-22 18:49
     */
    static generateResultMap(String packageName, String className, Map<String, String> map) {
        StringBuffer sb = new StringBuffer()
        sb.append("\n")
        sb.append("<resultMap id=\"${className}ResultMap\" type=\"${packageName}.${className}\" >\n")
        int flag = 0
        map.each {
            k, v ->
                //当有id字段的情况
                if (flag == 0 && "id".equals(k)) {
                    sb.append("  <id column=\"id\" property=\"id\" jdbcType=\"${javaTypeTODbType(v)}\" />\n")
                    flag++
                } else if (flag == 0) {
                    sb.append("  <id column=\"${changeStyle(k, false)}\" property=\"${k}\" jdbcType=\"${javaTypeTODbType(v)}\" />\n")
                    flag++
                } else {
                    sb.append("  <result column=\"${changeStyle(k, false)}\" property=\"${k}\" jdbcType=\"${javaTypeTODbType(v)}\" />\n")
                }
        }
        sb.append("</resultMap>\n")
        return sb

    }

    /**
     * 生成insert 方法
     * @author ming
     * @date 2018-04-22 18:50
     */
    static generateInsert(String packageName, String className, Map<String, String> map) {
        StringBuffer sb = new StringBuffer()
        sb.append("\n")
        sb.append("<insert id=\"insert\" parameterType=\"${packageName}.${className}\"  >\n")
        sb.append("insert into ${changeStyle(className, false)}\n")
        sb.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >\n")
        sb.append("<set> \n")
        map.each {
            k, v ->
                sb.append(" <if test=\"${k} != null\" >\n")
                sb.append("${changeStyle(k, false)},\n")
                sb.append("</if>\n")
        }
        sb.append("</set> \n")
        sb.append("</trim>\n")
        sb.append("<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >")
        sb.append("<set> \n")
        map.each {
            k, v ->
                sb.append(" <if test=\"${k} != null\" >\n")
                sb.append(" #{${k},jdbcType=${javaTypeTODbType(v)}},\n")
                sb.append("</if>\n")
        }
        sb.append("</set> \n")
        sb.append("</trim>\n")
        sb.append("</insert>\n")
        return sb
    }

    /**
     * @author ming
     * @date 2018-04-22 18:51
     */
    static generateInsertAll(String packageName, String className, Map<String, String> map) {
        StringBuffer sb = new StringBuffer()
        sb.append("\n")
        sb.append("<insert id=\"insert\" parameterType=\"${packageName}.${className}\"  >\n")
        sb.append("insert into ${changeStyle(className, false)}\n")
        sb.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >\n")
        sb.append("<set> \n")
        map.each {
            k, v ->
                sb.append(" <if test=\"${k} != null\" >\n")
                sb.append("${changeStyle(k, false)},\n")
                sb.append("</if>\n")
        }
        sb.append("</set> \n")
        sb.append("</trim>\n")
        sb.append("values \n")
        /*<foreach collection="roleTemplateDTO.appliedDepartments" item="item" index="index" open="(" close=")" separator=",">
        #{item}
        </foreach>
        */
        sb.append("<trim prefix=\" (\" suffix=\")\" suffixOverrides=\",\" >")
        sb.append("<set> \n")
        map.each {
            k, v ->
                sb.append(" <if test=\"${k} != null\" >\n")
                sb.append(" #{${k},jdbcType=${javaTypeTODbType(v)}},\n")
                sb.append("</if>\n")
        }
        sb.append("</set> \n")
        sb.append("</trim>\n")
        sb.append("</insert>\n")
        return sb
    }

    /**
     *
     *  <update id="update" parameterType="com.onlyedu.user.authorization.role.BossRoleAuthorizedDataScope" >
     *     update boss_role_authorized_data_scope
     *     <set >
     *       <if test="departmentId != null" >
     *         department_id = #{departmentId,jdbcType=VARCHAR},
     *       </if>
     *       <if test="bossRoleId != null" >
     *         boss_role_id = #{bossRoleId,jdbcType=VARCHAR},
     *       </if>
     *       gmt_modified=CURRENT_TIMESTAMP
     *     </set>
     *     where id = #{id,jdbcType=VARCHAR}*
     * @author ming
     * @date 2018-04-22 18:51
     */
    static generateUpdate(String packageName, String className, Map<String, String> map) {
        StringBuffer sb = new StringBuffer()
        sb.append("\n")
        sb.append("<update id=\"update\" parameterType=\"${packageName}.${className}\" >\n")
        sb.append("update ${changeStyle(className, false)}\n")
        sb.append("<set>\n")
        map.each {
            k, v ->
                sb.append("<if test=\"${k} != null\" >\n")
                sb.append("${changeStyle(k, false)} = #{${k},jdbcType=${javaTypeTODbType(v)}} ,\n")
                sb.append("</if> \n")
        }
        sb.append("</set>\n")
        sb.append("where ")
        int flag = 0
        map.each {
            k, v ->
                if (flag == 0 && "id".equals(k)) {
                    sb.append("  id = #{id,jdbcType=${javaTypeTODbType(v)}}\n")
                    flag++
                } else if (flag == 0) {
                    sb.append("  ${k} = #{${k},jdbcType=${javaTypeTODbType(v)}}\n")
                    flag++
                } else {
                    return
                }
        }
        sb.append("</update>\n")
        return sb
    }

    /**
     * @author ming
     * @date 2018-04-22 18:51
     */
    static generateFindById(String packageName, String className, Map<String, String> map) {
        StringBuffer sb = new StringBuffer()
        String paramenterType
        String id
        int flag = 0
        map.each {
            k, v ->
                if (flag == 0 && "id".equals(k)) {
                    paramenterType = v
                    id = "id"
                    flag++
                } else if (flag == 0) {
                    paramenterType = v
                    id = k
                    flag++
                } else {
                    return
                }
        }
        sb.append("\n")
        sb.append("<select id=\"findAll\" resultMap=\"${className}ResultMap\" parameterType=\"${paramenterType}\" >\n")
        sb.append("select * from ${changeStyle(className, false)} \n")
        sb.append("where ${id} = #{${id},jdbcType=${javaTypeTODbType(paramenterType)}} \n")
        sb.append("</select>\n")
    }

    /**
     * <select id="selectById" resultMap="BaseResultMap" parameterType="java.lang.String" >
     *     select
     *     <include refid="Base_Column_List" />
     *     from boss_role_authorized_data_scope
     *     where id = #{id,jdbcType=VARCHAR}*       and is_deleted=false
     *   </select>
     * @author ming
     * @date 2018-04-22 18:51
     */
    static generateFindAll(String packageName, String className, Map<String, String> map) {
        StringBuffer sb = new StringBuffer()
        sb.append("<select id=\"findAll\" resultMap=\"${className}ResultMap\" >\n")
        sb.append("select * from ${changeStyle(className, false)} \n")
        sb.append("</select>\n")
        return sb
    }

    /**
     *  <delete id="deleteById" parameterType="java.lang.String" >
     *     update boss_role_authorized_data_scope set is_deleted=true ,gmt_modified=CURRENT_TIMESTAMP
     *     where id = #{id,jdbcType=VARCHAR}*   </delete>
     * @author ming
     * @date 2018-04-22 18:52
     */
    static generateDeleteById(String packageName, String className, Map<String, String> map) {
        StringBuffer sb = new StringBuffer()
        String paramenterType
        String id
        int flag = 0
        map.each {
            k, v ->
                if (flag == 0 && "id".equals(k)) {
                    paramenterType = v
                    id = "id"
                    flag++
                } else if (flag == 0) {
                    paramenterType = v
                    id = k
                    flag++
                } else {
                    return
                }
        }
        sb.append("\n")
        sb.append("<delete id=\"deleteById\"  parameterType=\"${paramenterType}\" >\n")
        sb.append("delete from ${changeStyle(className, false)} \n")
        sb.append("where ${id} = #{${id},jdbcType=${javaTypeTODbType(paramenterType)}} \n")
        sb.append("</delete>\n")
    }


}
