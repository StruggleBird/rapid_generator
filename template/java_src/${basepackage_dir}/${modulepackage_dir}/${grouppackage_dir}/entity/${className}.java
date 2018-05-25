<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign hasDateType = false> 
package ${basepackage}${modulepackage}${table.ownerSynonymName}.entity;

<#list table.columns as column>
<#if column.isDateTimeColumn>
  <#assign hasDateType = true>
</#if>
</#list>
<#if hasDateType>
import java.util.Date;
</#if>
import com.globalegrow.brainkeeper.admin.common.entity.BaseEntity;

<@classComment value="实体类"/>
public class ${className} extends BaseEntity {

  /**
   * 序列化版本号
   */
  private static final long serialVersionUID = 1L;

  <#list table.columns as column>
  /**
   * <#if (column.pk==true)>主键列</#if>
   * ${column.columnAlias!} 
   */
  private ${column.javaType} ${column.columnNameLower};

  </#list>
  <@generateJavaOneToManyFields/>

	<@generateJavaManyToOneFields/>
	
	public ${className}(){
	  // default constructor
	}
  
  public ${className}(<#list table.pkColumns as column>${column.javaType} ${column.columnNameLower}<#if column_has_next>, </#if></#list>){
    <#list table.pkColumns as column>
    this.${column.columnNameLower} = ${column.columnNameLower};
    </#list>
  }

<@generateJavaColumns/>

<@generateJavaOneToManyColumns/>

<@generateJavaManyToOneColumns/>
}
<#macro generateJavaColumns>
  <#list table.columns as column>
  public void set${column.columnName}(${column.javaType} ${column.columnName?uncap_first}) {
    this.${column.columnNameLower} = ${column.columnName?uncap_first};
  }
  
  public ${column.javaType} get${column.columnName}() {
    return this.${column.columnNameLower};
  }
  
  </#list>
</#macro>
<#macro generateJavaOneToManyFields>
  <#list table.exportedKeys.associatedTables?values as foreignKey>
  <#assign fkSqlTable = foreignKey.sqlTable>
  <#assign fkTable  = fkSqlTable.className>
  <#assign fkPojoClass = fkSqlTable.className>
  <#assign fkPojoClassVar = fkPojoClass?uncap_first>
  
  private List<${fkPojoClass}> ${fkPojoClassVar}s = new ArrayList<${fkPojoClass}>();
  </#list>
</#macro>
<#macro generateJavaOneToManyColumns>
  <#list table.exportedKeys.associatedTables?values as foreignKey>
  <#assign fkSqlTable = foreignKey.sqlTable>
  <#assign fkTable  = fkSqlTable.className>
  <#assign fkPojoClass = fkSqlTable.className>
  <#assign fkPojoClassVar = fkPojoClass?uncap_first>
  
  public void set${fkPojoClass}s(List<${fkPojoClass}> ${fkPojoClassVar}s){
    this.${fkPojoClassVar}s = ${fkPojoClassVar}s;
  }
  
  public List<${fkPojoClass}> get${fkPojoClass}s() {
    return ${fkPojoClassVar}s;
  }
  </#list>
</#macro>

<#macro generateJavaManyToOneFields>
  <#list table.importedKeys.associatedTables?values as foreignKey>
  <#assign fkSqlTable = foreignKey.sqlTable>
  <#assign fkTable  = fkSqlTable.className>
  <#assign fkPojoClass = fkSqlTable.className>
  <#assign fkPojoClassVar = fkPojoClass?uncap_first>
  
  private ${fkPojoClass} ${fkPojoClassVar};
   
  </#list>
</#macro>

<#macro generateJavaManyToOneColumns>
  <#list table.importedKeys.associatedTables?values as foreignKey>
  <#assign fkSqlTable = foreignKey.sqlTable>
  <#assign fkTable  = fkSqlTable.className>
  <#assign fkPojoClass = fkSqlTable.className>
  <#assign fkPojoClassVar = fkPojoClass?uncap_first>
  
  public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
    this.${fkPojoClassVar} = ${fkPojoClassVar};
  }
  
  public ${fkPojoClass} get${fkPojoClass}() {
    return ${fkPojoClassVar};
  }
  </#list>
</#macro>