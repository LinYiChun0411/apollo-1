<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		style="width: 510px; height: 260px; padding: 20px 10px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<input type="hidden" name="id">
			<div class="fitem">
				<label>站点:</label> <input class="easyui-combobox" name="stationId"
					style="width: 250px;"
					data-options="prompt:'站点名称',valueField:'id',textField:'name',url:'${base}/admin/station/combo.do',required:true">
			</div>

			<div class="fitem">
				<label>域名:</label> <input name="domain" class="easyui-textbox"
					style="width: 250px;" data-options="required:true">
			</div>
			<div class="fitem" id="modulePathDiv">
				<label>状态:</label> <input class="easyui-combobox" name="status"
					data-options="prompt:'状态',valueField:'id',textField:'name',
					data: [{
							id: '1',
							name: '停用'
						},{
							id: '2',
							name: '启用'
						}],required:true"
					style="width: 250px;">
			</div>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="saveStation()" style="width: 90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"
			style="width: 90px">取消</a>
	</div>
</body>
</html>

<script language="javascript">
	var bar = [];

	bar.push({
		iconCls : 'icon-add',
		text : '新增',
		handler : function() {
			$('#dlg').dialog('open').dialog('setTitle', '新增');
			$('#fm').form('clear');
		}
	});

	bar.push({
		iconCls : 'icon-edit',
		text : '修改',
		handler : function() {
			var record = getSelected();
			if (record === false) {
				return;
			}
			$('#fm').form('clear');
			$('#fm').form('load', record);
			$('#dlg').dialog('open').dialog('setTitle', '编辑');
		}
	});

	bar.push({
		iconCls : 'icon-remove',
		text : '启用',
		handler : function() {
			var record = getSelected();
			if (record === false) {
				return;
			}
			Msg.confirm("你是否确认要启用？", function(r) {
				if (!r) {
					return;
				}

				$.ajax({
					url : '${base}/admin/domain/open.do',
					data : {
						domainId : record.id
					},
					success : function(data) {
						$('#dg').datagrid('reload');
						Msg.info("启用成功");
					}
				});
			});
		}
	});

	bar.push({
		iconCls : 'icon-remove',
		text : '停用',
		handler : function() {
			var record = getSelected();
			if (record === false) {
				return;
			}
			Msg.confirm("你是否确认要停用？", function(r) {
				if (!r) {
					return;
				}

				$.ajax({
					url : '${base}/admin/domain/close.do',
					data : {
						domainId : record.id
					},
					success : function(data) {
						$('#dg').datagrid('reload');
						Msg.info("停用成功");
					}
				});
			});
		}
	});
	
	bar.push({
		iconCls : 'icon-remove',
		text : '删除',
		handler : function() {
			var record = getSelected();
			if (record === false) {
				return;
			}
			Msg.confirm("你是否确认要删除？", function(r) {
				if (!r) {
					return;
				}

				$.ajax({
					url : '${base}/admin/domain/delete.do',
					data : {
						domainId : record.id
					},
					success : function(data) {
						$('#dg').datagrid('reload');
						Msg.info("删除成功");
					}
				});
			});
		}
	});

	bar.push('-');

	function getSelected() {
		var rows = $('#dg').datagrid('getSelections');
		if (rows.length == 0) {
			Msg.info("请选择你要操作的记录");
			return false;
		}
		return rows[0];
	}

	function saveStation() {
		var form = $('#fm');
		var data = form.form('getData');
		var url = '${base}/admin/domain/save.do';

		if (!form.form('validate')) {
			return;
		}

		$.ajax({
			url : url,
			data : data,
			success : function(data) {
				$('#dlg').dialog('close'); // close the dialog
				$('#dg').datagrid('reload'); // reload the user data
				Msg.info("保存成功！");
			}
		});
	}

	$('#dg').datagrid({
		toolbar : bar,
		url : '${base}/admin/domain/list.do',
		columns : [ [ {
			field : 'stationName',
			title : '站点名称',
			width : 100
		}, {
			field : 'floder',
			title : '站点别名',
			width : 100
		}, {
			field : 'domain',
			title : '域名',
			width : 100
		}, {
			field : 'status',
			title : '状态',
			width : 100,
			formatter : function(value, row, index) {
				if (value == 1) {
					return "停用";
				} else if (value == 2) {
					return "启用";
				}
				return "异常";
			}
		} ] ],
		border : false,
		singleSelect : true,
		pagination : true,
		fitColumns : true,
		fit : true,
		rownumbers : true,
		selectOnCheck : true,
		checkOnSelect : true
	});
</script>