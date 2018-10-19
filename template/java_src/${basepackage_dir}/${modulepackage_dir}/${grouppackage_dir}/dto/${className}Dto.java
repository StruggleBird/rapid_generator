<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign hasDateType = false> 
package ${basepackage}${modulepackage}${table.ownerSynonymName}.dto;

<#list table.columns as column>
<#if column.isDateTimeColumn>
<#assign hasDateType = true>
</#if>
</#list>
<#if hasDateType>
import java.util.Date;
</#if>
import com.globalegrow.brainkeeper.common.dto.BaseDto;


<@classComment value="Dto 对象"/>
public class ${className}Dto extends BaseDto{
  
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
  
<@generateFields/>
  
<@generateJavaColumns/>

<@generateProperties/>

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
<#macro generateFields>
  <#list table.notPkColumns as column>
  <#if column.isDateTimeColumn>
  /**
   * ${column.columnAlias!}
   */
  private ${column.javaType} ${column.columnNameLower}Begin;

  /**
   * ${column.columnAlias!}
   */
  private ${column.javaType} ${column.columnNameLower}End;

  </#if>
  </#list>

</#macro>

<#macro generateProperties>
  <#list table.columns as column>
  <#if column.isDateTimeColumn>
  public ${column.javaType} get${column.columnName}Begin() {
    return this.${column.columnNameLower}Begin;
  }
  
  public void set${column.columnName}Begin(${column.javaType} ${column.columnNameLower}Begin) {
    this.${column.columnNameLower}Begin = ${column.columnNameLower}Begin;
  }  
  
  public ${column.javaType} get${column.columnName}End() {
    return this.${column.columnNameLower}End;
  }
  
  public void set${column.columnName}End(${column.javaType} ${column.columnNameLower}End) {
    this.${column.columnNameLower}End = ${column.columnNameLower}End;
  }
  
  </#if>
  </#list>
</#macro>