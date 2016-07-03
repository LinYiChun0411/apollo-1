<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/permission" prefix="p"%>
<%@ include file="/common/include/base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${_title}</title>
	<jsp:include page="/common/include/easyui.jsp"></jsp:include>
	<script type="text/javascript" src="${base}/admin/js/core.js"></script>
</head>
<body>
	<div id="dg"></div>
	
		<div id="dlg" class="easyui-dialog"
		style="width: 510px; height: 400px; padding: 20px 10px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<input id="idField" type="hidden" name="id">
			<div class="fitem">
				<label>名称:</label> 
				<input name="name" class="easyui-textbox" style="width: 250px;" data-options="required:true"/>
			</div>
			
			<div class="fitem">
				<label>键值:</label> 
				<input name="key" class="easyui-textbox" style="width: 250px;" data-options="required:true">
			</div>
	
			<div class="fitem" >
				<label>表达式:</label> 
				<input name="expression" class="easyui-textbox" style="width: 250px;" data-options="required:true">
			</div>
			<div class="fitem" >
				<label>数据类型:</label> 
				<input name="dataType" class="easyui-textbox" style="width: 250px;" >
			</div>
			<div class="fitem" id="modulePathDiv">
				<label>数据库:</label>
				<select id="typeField" class="easyui-combobox" name="db" style="width: 250px;" data-options="required:true">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="0">0</option>
				</select>
			</div>

			<div class="fitem">
				<label>过期时间(秒):</label> 
				<input name="timeout" class="easyui-numberbox" style="width: 250px;" data-options="min:0,required:true">
			</div>
			
			<div class="fitem">
				<label>描述:</label> 
				<input name="remark" class="easyui-textbox" style="width: 250px;height:60px;" data-options="multiline:true">
			</div>
			
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="saveCache()" style="width: 90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"
			style="width: 90px">取消</a>
	</div>
	
	<div id="cacheDlg" class="easyui-dialog" style="width: 400px; height: 300px; " closed="true">
		<div id="cacheGrid"></div>
	</div>
	
</body>
</html>

<script language="javascript">


var bar = [];

<p:permission url="/admin/cache/add.do">
	bar.push({
		iconCls: 'icon-add',
		text:'新增',
		handler: function(){
			$('#dlg').dialog('open').dialog('setTitle', '新增');
			$('#fm').form('clear');
			window.saveType = 2;
		}
	});
</p:permission>

<p:permission url="/admin/cache/update.do">
	bar.push({
		iconCls:'icon-edit',
		text:'修改',
		handler: function(){
			var record = getSelected();
			if(record === false){
				return;
			}
			$('#fm').form('clear');
			$('#fm').form('load', record);
			$('#dlg').dialog('open').dialog('setTitle', '编辑');
			window.saveType = 1;
		}
	});
</p:permission>

<p:permission url="/admin/cache/delete.do">
	bar.push({
		iconCls: 'icon-remove',
		text:'删除',
		handler: function(){
			var record = getSelected();
			if(record === false){
				return;
			}
			Msg.confirm("你是否确认要删除？",function(r){
				if(!r){
					return;
				}
				
				$.ajax({
					url:'${base}/admin/cache/delete.do',
					data:{id:record.id},
					success:function(data){
						$('#dg').datagrid('reload');
						Msg.info("删除成功");
					}
				});
			});
		}
	});
</p:permission>

bar.push('-');
bar.push({
	iconCls:'icon-search',
	text:'实时缓存',
	handler: function(){
		var record = getSelected();
		if(record === false){
			return;
		}
		$("#cacheGrid").datagrid({url:'${base}/admin/cache/getCacheList.do'});
		$('#cacheDlg').dialog('open').dialog('setTitle', '缓存实时监控');
	}
});

function getSelected(){
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length == 0){
		Msg.info("请选择你要操作的记录");
		return false;
	}
	return rows[0];
}

function saveCache(){
	var form = $('#fm');
	var data = form.form('getData');
	var url = null;
	if(window.saveType == 1){
		url = '${base}/admin/cache/update.do';
	}else if(window.saveType == 2){
		url = '${base}/admin/cache/add.do';
	}
	
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

$('#dg').datagrid({
	toolbar:bar,
    url:'${base}/admin/cache/list.do',
    columns:[[
        {field:'ck',checkbox:true},
        {field:'name',title:'名称',width:100},
        {field:'key',title:'键值',width:100},
        {field:'expression',title:'表达式',width:100},
        {field:'dataType',title:'数据类型',width:100,
        	formatter: function(value,row,index){
				return html_encode(value);
			}
        },
        {field:'db',title:'数据库',width:30},
        {field:'timeout',title:'过期时间(秒)',width:50},
        {field:'remark',title:'描述',width:150}
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


var current_db = -1;

$('#cacheGrid').datagrid({
	toolbar:[{
		iconCls: 'icon-remove',
		text:'删除',
		handler: function(){
			var rows = $('#cacheGrid').datagrid('getSelections');
			if(rows.length == 0){
				Msg.info("请选中要操作的记录！");
			}
			Msg.confirm("是否确认要删除选中的缓存？",function(r){
				if(!r){
					return;
				}
				var keys = [];
				for(var i=0; i<rows.length; i++ ){
					keys.push(rows[i].key);
				}
				var json = JSON.encode(keys);
				$.ajax({
					url : "${base}/admin/cache/delCache.do",
					data : {
						key:json,
						db:current_db
					},
					success : function(data) {
						if(data.success){
							$('#cacheGrid').datagrid('reload'); 
							Msg.info("删除成功！");
						}else{
							Msg.info(data.msg);
						}
					}
				});
			})
		}
	},{
		iconCls: 'icon-search',
		text:'查看数据',
		handler: function(){
			var rows = $('#cacheGrid').datagrid('getSelections');
			if(rows.length == 0){
				Msg.info("请选中要操作的记录！");
				return;
			}
			if(rows.length > 1){
				Msg.info("一次只能查看一条缓存数据！");
				return;
			}
			var r = rows[0];
			$.ajax({
				url : "${base}/admin/cache/getCacheContext.do",
				data : {
					key:r.key,
					db:current_db
				},
				success : function(data) {
					alert(data.content);
				}
			});
			
		}
	}],
    url:null,
    onBeforeLoad:function(param){
    	var rows = $('#dg').datagrid('getSelections');
    	if(rows.length == 0){
    		return;
    	}
    	current_db = rows[0].db;
    	param.db = rows[0].db;
    	param.key = rows[0].key;
    },
    columns:[[
        {field:'ck',checkbox:true},
        {field:'key',title:'键值',width:100},
    ]],
    border:false,
    singleSelect:false,
    fitColumns:true,
    fit:true,
    rownumbers: true,
    selectOnCheck: true,
    checkOnSelect: true
});


</script>