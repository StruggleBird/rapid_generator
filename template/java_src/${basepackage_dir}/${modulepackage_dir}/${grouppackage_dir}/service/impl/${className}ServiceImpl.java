<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign remarks = table.remarks>
package ${basepackage}${modulepackage}${table.ownerSynonymName}.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalegrow.brainkeeper.util.BeanUtils;
import com.globalegrow.brainkeeper.util.CollectionUtils;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import com.globalegrow.brainkeeper.common.dto.PageRequest;
import com.globalegrow.brainkeeper.common.dto.PageResult;
import ${basepackage}${modulepackage}${table.ownerSynonymName}.entity.${className};
import ${basepackage}${modulepackage}${table.ownerSynonymName}.dto.${className}Dto;
import ${basepackage}${modulepackage}${table.ownerSynonymName}.service.${className}Service;
import ${basepackage}${modulepackage}${table.ownerSynonymName}.dao.${className}Mapper;


<@classComment value="Service实现"/>
@Service
public class ${className}ServiceImpl implements ${className}Service {

  /**
   * ${remarks} Dao
   */
  @Autowired
  private ${className}Mapper ${classNameLower}Mapper;

  @Override
  public ${className}Dto get(Object condition)  
	{  
    ${className} ${classNameLower} = ${classNameLower}Mapper.get(condition);
    ${className}Dto ${classNameLower}Dto = BeanUtils.toBean(${classNameLower},${className}Dto.class);
	  return ${classNameLower}Dto;  
	}  

  @Override
  public long count(${className}Dto condition) {
    return ${classNameLower}Mapper.count(condition);
  }

  @Override
  public List<${className}Dto> list(${className}Dto condition) {
    List<${className}> list = ${classNameLower}Mapper.list(condition);
    List<${className}Dto> dtoList = CollectionUtils.transform(list, ${className}Dto.class);
    return dtoList;
  }

  @Override
  public List<${className}Dto> list(Map<String, Object> condition) {
    List<${className}> list = ${classNameLower}Mapper.list(condition);
    List<${className}Dto> dtoList = CollectionUtils.transform(list, ${className}Dto.class);
    return dtoList;
  }
  
  @Override
  public PageResult<${className}Dto> page(PageRequest pageRequest){
    PageHelper.startPage(pageRequest);
    List<${className}> list = ${classNameLower}Mapper.list(pageRequest.getCondition());
    PageInfo<${className}> pageInfo = PageInfo.of(list);
    List<${className}Dto> dtoList = CollectionUtils.transform(list, ${className}Dto.class);
    return PageResult.of(dtoList, pageInfo.getTotal(), pageInfo.getPageSize(),
      pageInfo.getPageNum());
  }

  @Override
  public ${className}Dto save(${className}Dto ${classNameLower}Dto) {
    ${className} ${classNameLower} = BeanUtils.toBean(${classNameLower}Dto,${className}.class);
    ${classNameLower}Mapper.save(${classNameLower});
    <#list table.pkColumns as column>
    ${classNameLower}Dto.set${column.columnName}(${classNameLower}.get${column.columnName}());
    </#list>
    return ${classNameLower}Dto;
  }

  @Override
  public int save(List<${className}Dto> ${classNameLower}Dtos) {
    if (${classNameLower}Dtos == null || ${classNameLower}Dtos.isEmpty()) {
      return 0;
    }
    List<${className}> ${classNameLower}s = CollectionUtils.transform(${classNameLower}Dtos, ${className}.class);
    return ${classNameLower}Mapper.saveBatch(${classNameLower}s);
  }

  @Override
  public int update(${className}Dto ${classNameLower}Dto) {
    ${className} ${classNameLower} = BeanUtils.toBean(${classNameLower}Dto,${className}.class);
    return ${classNameLower}Mapper.update(${classNameLower});
  }
  
  @Override
  public int deleteByPK(${table.pkColumn.simpleJavaType} ${table.pkColumn.columnNameFirstLower}) {
    return ${classNameLower}Mapper.deleteByPK(${table.pkColumn.columnNameFirstLower});
  }

  @Override
  public int delete(${className}Dto ${classNameLower}Dto) {
    ${className} ${classNameLower} = BeanUtils.toBean(${classNameLower}Dto,${className}.class);
    return ${classNameLower}Mapper.delete(${classNameLower});
  }
  
  @Override
  public int deleteBatchByPK(List<${table.pkColumn.simpleJavaType}> ${table.pkColumn.columnNameFirstLower}s) {
    return ${classNameLower}Mapper.deleteBatchByPK(${table.pkColumn.columnNameFirstLower}s);
  }
  
  @Override
  public int deleteBatch(List<${className}Dto> ${classNameLower}s) {
    return 0;
  }

}
