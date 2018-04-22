import groovy.sql.Sql
import org.apache.commons.lang3.StringUtils

/**
 * db工具类  提供 操作db的功能
 * @author ming
 * @date 2018-04-22 13:44
 */

class DbUtils {

    /**获取 数据库链接
     * @author ming
     * @date 2018-04-22 11:53
     */
    static def sql = Sql.newInstance('jdbc:postgresql://localhost:5432/postgres',
            'postgres', 'ming', 'org.postgresql.Driver')

    /**pgsql 获取 表格字段信息
     * @author ming
     * @date 2018-04-22 12:04
     */

    static def pgsqlGetTableColumns(String tableName) {

        Map<String, String> map = [:]
        sql.eachRow("select  * from information_schema.columns as info   where info.table_name = ${tableName}") {
            it ->
                map.put(it.column_name, it.data_type)
        }
        //处理 命名规则 和类型
        Map<String, String> result = [:]
        map?.each {
            k, v ->
                result.put(changeStyle(k, true), pgsqlDbTypeTOJavaType(v))
        }
        return result
    }

    static pgsqlDbTypeTOJavaType(String dbTypeStr) {
        Map<String, String> map = [:]
        map.put("char.*", "String")
        map.put("boolean.*", "Boolean")
        map.put("timestamp.*", "Date")
        map.put("integer.*", "Integer")
        map.put("date.*", "Date")
        //默认类型为String
        String result = "String"
        map.each {
            k, v ->
                if (dbTypeStr?.matches(k)) {
                    result = v
                }
        }
        return result
    }

    /**mysql 获取表格字段信息
     * @author ming
     * @date 2018-04-22 12:05
     */
    static mysqlGetTableColumns(String tableName) {

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
}
