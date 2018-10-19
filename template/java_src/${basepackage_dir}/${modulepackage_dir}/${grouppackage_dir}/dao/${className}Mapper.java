<#include "/java_copyright.include">
<#assign className = table.className>
package ${basepackage}${modulepackage}${table.ownerSynonymName}.dao;

import ${basepackage}${modulepackage}${table.ownerSynonymName}.entity.${className};
import com.globalegrow.brainkeeper.common.dao.BaseMapper;

<@classComment value="Dao 接口"/>
public interface ${className}Mapper extends BaseMapper<${className}, ${table.idColumn.javaType}> {

}
