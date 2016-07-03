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
		style="width: 510px; height: 460px; padding: 20px 10px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<input type="hidden" name="id">
			<div class="fitem">
				<label>标题:</label> <input name="name" class="easyui-textbox"
					style="width: 250px;" data-options="required:true">
			</div>
			<div class="fitem">
				<label>开始时间:</label> <input name="beginDate"
					class="easyui-datetimebox" required style="width: 250px">
			</div>
			<div class="fitem">
				<label>结束时间:</label> <input name="endDate"
					class="easyui-datetimebox" required style="width: 250px">
			</div>
			<div class="fitem" id="modulePathDiv">
				<label>公告类别:</label> <input class="easyui-combobox" name="type"
					data-options="prompt:'公告类别',valueField:'id',textField:'name',
					data: [{
							id: '1',
							name: '维护'
						},{
							id: '2',
							name: '推广'
						}],required:true"
					style="width: 250px;">
			</div>
			<div class="fitem" id="modulePathDiv">
				<label>状态:</label> <input class="easyui-combobox" name="status"
					data-options="prompt:'状态',valueField:'id',textField:'name',
					data: [{
							id: '1',
							name: '未发布'
						},{
							id: '2',
							name: '已发布'
						}],required:true"
					style="width: 250px;">
			</div>
			<div class="fitem">
				<label>内容:</label> <input name="content" class="easyui-textbox"
					style="width: 250px; height: 200px;" data-options="multiline:true">
			</div>
		</form>
	</div>

	<div id="tb" style="padding: 2px 5px;">
		<form id="fm_search">
			<!-- <input name="name" class="easyui-textbox" data-options="prompt:'标题'"
				style="width: 200px;"> -->
			<input class="easyui-combobox" name="type"
				data-options="prompt:'公告类别',valueField:'id',textField:'name',data: [{
							id: '1',
							name: '维护'
						},{
							id: '2',
							name: '推广'
						}]">
			<input class="easyui-combobox" name="status"
				data-options="prompt:'公告状态',valueField:'id',textField:'name',data: [{
							id: '1',
							name: '未发布'
						},{
							id: '2',
							name: '已发布'
						}]">

			<input name="beginDatetime" class="easyui-datetimebox"
				data-options="prompt:'开始时间'"> <input name="endDatetime"
				class="easyui-datetimebox" data-options="prompt:'结束时间'"> <a
				href="#" class="easyui-linkbutton" iconCls="icon-search"
				plain="true" onclick="searchreload()">查询</a><a href="#"
				class="easyui-linkbutton" iconCls="lot-icon-undo" plain="true"
				onclick="resetform()">重置</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-add" plain="true"
				onclick="newAnnouncement()">新增</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-edit" plain="true"
				onclick="editAnnouncement()">修改</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-remove" plain="true"
				onclick="deleteAnnouncement()">删除</a>
			<!-- <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-edit" plain="true"
				onclick="editAnnouncement()">重置密码</a> -->
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="saveAnnouncement()" style="width: 90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"
			style="width: 90px">取消</a>
	</div>
</body>
</html>

<script language="javascript">
	function newAnnouncement() {
		$('#dlg').dialog('open').dialog('setTitle', '新增');
		$('#fm').form('clear');
	}

	function editAnnouncement() {
		var record = getSelected();
		if (record === false) {
			return;
		}
		record.beginDate = DateUtil.formatDatetime(record.beginDatetime);
		record.endDate = DateUtil.formatDatetime(record.endDatetime);
		$('#fm').form('clear');
		$('#fm').form('load', record);
		$('#dlg').dialog('open').dialog('setTitle', '编辑');
	}

	function deleteAnnouncement() {
		var record = getSelected();
		if (record === false) {
			return;
		}
		Msg.confirm("你是否确认要删除？", function(r) {
			if (!r) {
				return;
			}

			$.ajax({
				url : '${base}/admin/announcement/delete.do',
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

	function getSelected() {
		var rows = $('#dg').datagrid('getSelections');
		if (rows.length == 0) {
			Msg.info("请选择你要操作的记录");
			return false;
		}
		return rows[0];
	}

	function saveAnnouncement() {
		var form = $('#fm');
		var data = form.form('getData');
		var url = '${base}/admin/announcement/save.do';

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
		toolbar : '#tb',
		url : '${base}/admin/announcement/list.do',
		columns : [ [ {
			field : 'name',
			title : '标题',
			width : 100
		}, {
			field : 'creator',
			title : '创建人',
			width : 100
		}, {
			field : 'type',
			title : '公告类别',
			width : 100,
			formatter : function(value, row, index) {
				if (value == 1) {
					return "维护";
				} else if (value == 2) {
					return "推广";
				}
				return "其他";
			}
		}, {
			field : 'status',
			title : '状态',
			width : 100,
			formatter : function(value, row, index) {
				if (value == 1) {
					return "未发布";
				} else if (value == 2) {
					return "已发布";
				}
				return "异常";
			}
		}, {
			field : 'beginDatetime',
			title : '开始时间',
			width : 100,
			formatter : function(value, row, index) {
				return DateUtil.formatDatetime(value);
			}
		}, {
			field : 'endDatetime',
			title : '结束时间',
			width : 100,
			formatter : function(value, row, index) {
				return DateUtil.formatDatetime(value);
			}
		}, {
			field : 'createDatetime',
			title : '创建时间',
			width : 100,
			formatter : function(value, row, index) {
				return DateUtil.formatDatetime(value);
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

	function searchreload() {

		$('#dg').datagrid('load', $("#fm_search").form('getData'));
	}
	function resetform() {

		$("#fm_search").form('reset')
	}
</script>