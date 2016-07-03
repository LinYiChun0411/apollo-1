<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/include/base.jsp"%>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${_title}</title>
<%@ include file="/common/include/easyui.jsp"%>
<script type="text/javascript" src="${base}/admin/js/core.js"></script>
</head>
<body>
	<table id="dg"></table>

	<div id="dlg" class="easyui-dialog"
		style="width: 510px; height: 400px; padding: 20px 10px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<input id="idField" type="hidden" name="id">
			<input id="parentIdField" type="hidden" name="parentId">
			<input id="levelField" type="hidden" name="level"/>
			<div class="fitem">
				<label>父级目录:</label> <input id="parentNameField" name="parentName"
					class="easyui-textbox" readonly style="width: 250px;" />
			</div>
			
			<div class="fitem">
				<label>名称:</label> <input id="nameField" name="name"
					class="easyui-textbox" style="width: 250px;" data-options="required:true">
			</div>
			
			<div class="fitem" id="urlDiv">
				<label>页面URL:</label> <input id="urlField" name="url"
					class="easyui-textbox" style="width: 250px;">
			</div>
			
			<div class="fitem" id="modulePathDiv">
				<label>功能URL:</label> <input id="modulePathField" name="modulePath"
					class="easyui-textbox" style="width: 250px;" >
			</div>
		<!--
			<div class="fitem">
				<label>目录类别:</label> <select id="typeField" class="easyui-combobox"
					name="type" style="width: 400px;">
					<option value="2">模块页面</option>
					<option value="1">模块分类</option>
					<option value="3">功能点</option>
				</select>
			</div>  -->
			
			<div class="fitem">
				<label>状态:</label> <select id="statusField" class="easyui-combobox"
					name="status" style="width: 250px;" data-options="required:true">
					<option value="2">正常</option>
					<option value="1">禁用</option>
					<option value="3">隐藏</option>
				</select>
			</div>
			
			<div class="fitem">
				<label>排序值:</label> <input name="sort"
					class="easyui-numberbox" style="width: 250px;" data-options="required:true">
			</div>
			
			<div class="fitem">
				<label>图标:</label> <input name="icon"
					class="easyui-textbox" style="width: 250px;" >
			</div>
			
			<div class="fitem">
				<label>备注:</label> <input name="remark"
					class="easyui-textbox" style="width: 250px;height:60px;" data-options="multiline:true">
			</div>
			
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="saveMenu()" style="width: 90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"
			style="width: 90px">取消</a>
	</div>

</body>
</html>

<script language="javascript">
	
	var ways = [0]; //轨迹
	var names = ["根目录"];//目录
	var opt = 0; //1：上一级  2：下一级
	
	var parentId = 0; //当前父节点ID
	var parentName;//根木名称
	
	function getSelected(){
		var rows = $('#dg').datagrid('getSelections');
		if(rows.length == 0){
			Msg.info("请选择你要操作的记录");
			return false;
		}
		return rows[0];
	}
	
	function setTextboxStatus(){
		if(ways.length == 1){ //层级
			$('#urlDiv').css("display", "none");
			$('#modulePathDiv').css("display", "none");
			
			$('#urlField').textbox({
				required : false
			});
		}else if(ways.length == 2){
			$('#urlDiv').css("display", "");
			$('#modulePathDiv').css("display", "");
			$('#urlField').textbox({
				required : true
			});
	
		}else if(ways.length == 3){
			$('#urlDiv').css("display", "");
			$('#modulePathDiv').css("display", "none");
			$('#urlField').textbox({
				required : true
			});
		}
	}
	
	$('#dg').datagrid({
		toolbar:[{
			iconCls: 'icon-add',
			text:'新增',
			handler: function(){
				$('#dlg').dialog('open').dialog('setTitle', '新增目录');
				$('#fm').form('clear');
				$('#parentIdField').val(ways[ways.length-1]);
				$('#parentNameField').textbox('setValue',names[names.length - 1]);
				$('#levelField').val(names.length);
				setTextboxStatus();
				
			}
		},{
			iconCls:'icon-edit',
			text:'修改',
			handler: function(){
				var record = getSelected();
				if(record === false){
					return;
				}
				$('#fm').form('clear');
				setTextboxStatus();
				$('#fm').form('load', record);
				$('#parentNameField').textbox('setValue',names[names.length - 1]);
				$('#dlg').dialog('open').dialog('setTitle', '修改目录');
			}
		},'-',{
			iconCls:'icon-pre',
			text:'上一级',
			handler: function(){
				if(parentId == 0 || ways.length < 2 ){
					Msg.info("当前已经是最顶级了");
					return;
				}
				parentId = ways[ways.length - 2];
				opt = 1;
				$('#dg').datagrid("reload");
			}
		},{
			iconCls:'icon-next',
			text:'下一级',
			handler: function(){
				var record = getSelected();
				if(record === false){
					return;
				}
				if(record.type == 3){
					Msg.info("功能点不能有下一级");
					return;
				}
				parentId = record.id;
				parentName = record.name;
				opt = 2;
				$('#dg').datagrid("reload");
			}
		}],
	    url:'${base}/admin/agentmenu/getMenuTree.do',
	    onBeforeLoad:function(param){
	    	param["parentId"] = parentId;
	    },
	    onLoadSuccess:function(data){
	    	if(opt == 1){
	    		ways.pop();
	    		names.pop();
	    	}else if(opt == 2){
	    		ways.push(parentId);
	    		names.push(parentName);
	    	}
	    	opt = 0;
	    },
	    onLoadError:function(){
	    	opt = 0;
	    },
	    columns:[[
	        {field:'ck',checkbox:true},
	        {field:'name',title:'名称',width:100},
	      /**  {field:'type',title:'类别',width:100,
	           	formatter:function(value,row,index){
		        	if(value == 1){
		        		return "模块分类";
		        	}else if(value == 2){
		        		return "模块页面";
		        	}else if(value == 3){
		        		return "功能点";
		        	}
		        	return "异常";
		        }
	       	},**/
	        {field:'status',title:'状态',width:100,
	        	formatter:function(value,row,index){
		        	if(value == 1){
		        		return "禁用";
		        	}else if(value == 2){
		        		return "正常";
		        	}else if(value == 3){
		        		return "隐藏";
		        	}
		        	return "异常";
		        }
	        },
	        {field:'level',title:'级别',width:100},
	        {field:'sort',title:'排序',width:100}
	    ]],
	    border:false,
	    singleSelect:true,
	    pagination:true,
	    fitColumns:true,
	    fit:true,
	    rownumbers: true,
	    selectOnCheck: true,
	    checkOnSelect: true
	});
	
	function saveMenu() {
		var form = $('#fm');
		var data = form.form('getData');
		var url = '${base}/admin/agentmenu/saveMenu.do';
		
		if (!form.form('validate')) {
			return;
		}

		$.ajax({
			url : url,
			data : {
				data:JSON.encode(data)
			},
			success : function(data) {
				$('#dlg').dialog('close'); // close the dialog
				$('#dg').datagrid('reload'); // reload the user data
				Msg.info("保存成功！");
			}
		});
	}
</script>