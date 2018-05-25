<#include "/java_copyright.include">
<#assign className = table.className>
<#assign remarks = table.remarks>
<#assign classNameLower = className?uncap_first> 
package ${basepackage}${modulepackage}${table.ownerSynonymName}.service;

import java.util.List;
import java.util.Map;
import com.globalegrow.brainkeeper.admin.common.dto.PageResult;
import com.globalegrow.brainkeeper.admin.common.dto.PageRequest;

import ${basepackage}${modulepackage}${table.ownerSynonymName}.dto.${className}Dto;

<@classComment value="Service"/>
public interface ${className}Service {

  /**
   * 查询单个记录${remarks}
   * 主键：<#list table.compositeIdColumns as column>${column.columnNameLower} <#if column_has_next> 、 </#if></#list>
   * @param condition 主键/Map/查询对象
   * @return ${remarks}
   */
  ${className}Dto get(Object condition);

  /**
   * 统计${remarks}数量
   * 
   * @param condition ${remarks}查询条件对象
   * @return 统计数量
   */
  long count(${className}Dto condition);

  /**
   * 根据组合条件查询${remarks}
   * 
   * @param condition ${remarks}查询对象
   * @return ${remarks}集合,如果不存在,返回Empty List
   */
  List<${className}Dto> list(${className}Dto condition);

  /**
   * 根据组合条件查询,不建议用该方法进行分页  ${remarks}
   * 
   * @param condition ${remarks}查询对象
   * @return ${remarks}集合,如果不存在,返回Empty List
   */
  List<${className}Dto> list(Map<String,Object> condition);
  
  /**
   * 根据组合条件做分页查询,需要condition中包含分页对象page  ${remarks}
   * 
   * @param pageRequest ${remarks}查询对象
   * @return ${remarks}集合,如果不存在,返回Empty List
   */
  PageResult<${className}Dto> page(PageRequest pageRequest);
  

  /**
   * 保存${remarks}
   * 
   * @param ${classNameLower} ${remarks}
   * @return 带主键的DTO
   */
  ${className}Dto save(${className}Dto ${classNameLower}Dto);

  /**
   * 批量保存${remarks}
   * 
   * @param ${classNameLower}s ${remarks}
   * @return 带主键的DTO
   */
  int save(List<${className}Dto> ${classNameLower}Dtos);

  /**
   * 更新${remarks}
   * 
   * @param ${classNameLower} ${remarks}
   * @return 操作影响行数
   */
  int update(${className}Dto ${classNameLower}Dto);
  
  /**
   * 根据主键删除${remarks}
   * 不建议，建议使用delete(${className} ${classNameLower})
   * @param ${table.pkColumn.columnNameFirstLower} 主键
   * @return 操作影响行数
   */
  int deleteByPK(${table.pkColumn.simpleJavaType} ${table.pkColumn.columnNameFirstLower});
  
  /**
   * 删除${remarks}
   * 
   * @param ${table.pkColumn.columnNameFirstLower} 主键
   * @return 操作影响行数
   */
  int delete(${className}Dto ${classNameLower}Dto);
  
  /**
   * 根据主键删除${remarks}
   * 不建议，建议使用delete(${className}Dto ${classNameLower})
   * @param ${table.pkColumn.columnNameFirstLower} 主键
   * @return 操作影响行数
   */
  public int deleteBatchByPK(List<${table.pkColumn.simpleJavaType}> ${table.pkColumn.columnNameFirstLower}s);
  
  
  /**
   * 批量删除${remarks}
   * 
   * @param ${table.pkColumn.columnNameFirstLower} 主键
   * @return 操作影响行数
   */
  int deleteBatch(List<${className}Dto> ${classNameLower}s);
}
