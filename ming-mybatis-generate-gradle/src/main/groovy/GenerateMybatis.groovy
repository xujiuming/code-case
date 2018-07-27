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
     static void main(String[] args) {

        String tableName = "contract_refund"
        String packageName = "com.onlyedu.sell.contract.refund"

        String className = StringUtils.capitalize(DbUtils.changeStyle(tableName, true))
        Map<String, String> map = DbUtils.pgsqlGetTableColumns(tableName)
        //生成pojo
        GeneratePojo.generate(packageName, className, map, null)
        //生成mapper
        GenerateMapper.generate(packageName, className, "Integer", null)
        //生成xml
        GenerateMapperXml.generate(packageName,className,map,null)

    }
}
