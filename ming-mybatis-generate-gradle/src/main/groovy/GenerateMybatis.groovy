import org.apache.commons.lang3.StringUtils

/**生成 mybatis 的 pojo   xml  mapper 基础方法的生成器
 *
 * 因为官方的太麻烦  大部分时候 只需要很简单的快速生成即可   通过 groovy的模板引擎 可以快速构建这些功能
 *
 * 使用前提 :
 * pojo命名规则 驼峰命名
 * 暂时只支持 pgsql  后续考虑接入mysql.oracle 之类常用数据库
 * 依赖管理 暂时使用 groovy 自带的 garpe
 * @author ming
 * @date 2018-04-21 22:16
 */
class GenerateMybatis {
    public static void main(String[] args) {

        String tableName = "my_user"
        String packageName = "com.ming"

        String className = StringUtils.capitalize(DbUtils.changeStyle(tableName, true))
        Map<String, String> map = DbUtils.pgsqlGetTableColumns(tableName)
        GeneratePojo.generatePojo(packageName, className, map, null)

        GenerateMapper.generateMapper(packageName, className, "Integer", null)

    }
}
