
import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;
<#if fullParentClass??>import ${fullParentClass};</#if>
<#if hasDate>import java.util.Date;</#if>

@Table(name="${tableName}")
public class ${className} <#if parentClass??>extends ${parentClass}</#if> {
<#list list as item>
	<#if item.colComment??>
	/**
		${item.colComment}
	*/
	</#if>
	@Column(name="${item.dbCloumn}"<#if item.isPrimary>,primarykey=true</#if><#if item.maxLength??>,length=${item.maxLength}</#if>)
	private ${item.type} ${item.property};
	
</#list>

<#list list as item> 

	public ${item.type} ${item.getMethodName}(){
		return this.${item.property};
	}
	
	public void ${item.setMethodName}(${item.type} ${item.property}){
		this.${item.property} = ${item.property};
	}
</#list>
}
