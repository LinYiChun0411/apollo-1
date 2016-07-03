<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/include/base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${_title}</title>
<jsp:include page="/common/include/easyui.jsp"></jsp:include>
<script type="text/javascript" src="${base}/common/js/md5.js"></script>
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
				<label>用户名:</label> <input name="account" class="easyui-textbox"
					style="width: 250px;" data-options="required:true">
			</div>
			<div class="fitem" id="modulePathDiv">
				<label>状态:</label> <input class="easyui-combobox" name="accountType"
					data-options="prompt:'账号类别',valueField:'id',textField:'name',
					data: [{
							id: '1',
							name: '大大股东'
						},{
							id: '2',
							name: '大股东'
						}],required:true"
					style="width: 250px;">
			</div>
			<div class="fitem" id="modulePathDiv">
				<label>状态:</label> <input class="easyui-combobox"
					name="accountStatus"
					data-options="prompt:'状态',valueField:'id',textField:'name',
					data: [{
							id: '1',
							name: '停用'
						},{
							id: '2',
							name: '正常'
						}],required:true"
					style="width: 250px;">
			</div>
		</form>
	</div>

	<div id="dlg_pwd" class="easyui-dialog"
		style="width: 510px; height: 260px; padding: 20px 10px" closed="true"
		buttons="#dlg-buttons_pwd">
		<form id="fm_pwd" method="post">
			<input type="hidden" name="id">
			<div class="fitem">
				<label>用户名:</label> <input name="account" class="easyui-textbox"
					style="width: 250px;" readonly="readonly">
			</div>
			<div class="fitem">
				<label>新密码:</label> <input name="pwd" type="password"
					class="easyui-textbox" style="width: 250px;"
					data-options="required:true">
			</div>
			<div class="fitem">
				<label>确认密码:</label> <input name="rpwd" type="password"
					class="easyui-textbox" style="width: 250px;"
					data-options="required:true">
			</div>
		</form>
	</div>

	<div id="tb" style="padding: 2px 5px;">
		<form id="fm_search">
			<input name="account" class="easyui-textbox"
				data-options="prompt:'用户名'" style="width: 200px;"> <input
				class="easyui-combobox" name="accountType"
				data-options="prompt:'账号类别',valueField:'id',textField:'name',data: [{
							id: '1',
							name: '大大股东'
						},{
							id: '2',
							name: '大股东'
						}]">
			<input class="easyui-combobox" name="accountStatus"
				data-options="prompt:'状态',valueField:'id',textField:'name',data: [{
							id: '1',
							name: '停用'
						},{
							id: '2',
							name: '正常'
						}]">
			<a href="#" class="easyui-linkbutton" iconCls="icon-search"
				plain="true" onclick="searchreload()">查询</a><a href="#"
				class="easyui-linkbutton" iconCls="lot-icon-undo" plain="true"
				onclick="resetform()">重置</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-add" plain="true"
				onclick="newAccount()">新增</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-edit" plain="true"
				onclick="editAccount()">修改</a><a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-edit" plain="true"
				onclick="resetpwd()">重置密码</a><a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-remove" plain="true"
				onclick="deleteAccount()">删除</a>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="saveAccount()" style="width: 90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"
			style="width: 90px">取消</a>
	</div>

	<div id="dlg-buttons_pwd">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="updpwd()" style="width: 90px">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#dlg_pwd').dialog('close')"
			style="width: 90px">取消</a>
	</div>
</body>
</html>

<script language="javascript">
	function newAccount() {
		$('#dlg').dialog('open').dialog('setTitle', '新增');
		$('#fm').form('clear');
	}

	function editAccount() {
		var record = getSelected();
		if (record === false) {
			return;
		}
		$('#fm').form('clear');
		$('#fm').form('load', record);
		$('#dlg').dialog('open').dialog('setTitle', '编辑');
	}

	function resetpwd() {
		var record = getSelected();
		if (record === false) {
			return;
		}
		$('#fm_pwd').form('clear');
		$('#fm_pwd').form('load', record);
		$('#dlg_pwd').dialog('open').dialog('setTitle', '重置密码');
	}

	function deleteAccount() {
		var record = getSelected();
		if (record === false) {
			return;
		}
		Msg.confirm("你是否确认要删除？", function(r) {
			if (!r) {
				return;
			}

			$.ajax({
				url : '${base}/admin/account/delete.do',
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

	function saveAccount() {
		var form = $('#fm');
		var data = form.form('getData');
		var url = '${base}/admin/account/save.do';

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
	function updpwd() {
		var form = $('#fm_pwd');
		var data = form.form('getData');
		var url = '${base}/admin/account/updpwd.do';

		if (!form.form('validate')) {
			return;
		}
		data.pwd = hex_md5("~!@#$wc_" + data.pwd);
		data.rpwd = hex_md5("~!@#$wc_" + data.rpwd);
		$.ajax({
			url : url,
			data : data,
			success : function(data) {
				$('#dlg_pwd').dialog('close'); // close the dialog
				Msg.info("保存成功！");
			}
		});
	}

	$('#dg').datagrid({
		toolbar : '#tb',
		url : '${base}/admin/account/list.do',
		columns : [ [ {
			field : 'account',
			title : '用户名',
			width : 100
		}, {
			field : 'accountType',
			title : '账号类别',
			width : 100,
			formatter : function(value, row, index) {
				if (value == 1) {
					return "大大股东";
				} else if (value == 2) {
					return "大股东";
				}
				return "会员";
			}
		}, {
			field : 'accountStatus',
			title : '状态',
			width : 100,
			formatter : function(value, row, index) {
				if (value == 1) {
					return "停用";
				} else if (value == 2) {
					return "正常";
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

	function searchreload() {

		$('#dg').datagrid('load', $("#fm_search").form('getData'));
	}
	function resetform() {

		$("#fm_search").form('reset')
	}
</script>