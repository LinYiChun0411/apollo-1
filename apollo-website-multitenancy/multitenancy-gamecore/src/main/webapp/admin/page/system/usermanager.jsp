<%@ page language="java" import="java.util.*" isELIgnored="false"
	pageEncoding="utf-8"%>
<%@ include file="/common/include/base.jsp"%>
<html>
<head>
<title>${_title}</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@ include file="/common/include/easyui.jsp"%>
<script type="text/javascript" src="${base}/admin/js/core.js"></script>
<script type="text/javascript" src="${base}/common/js/md5.js"></script>
</head>
<body>

	<table id="dg" class="easyui-datagrid" url="${base}/admin/user/list.do"
		pagination="true" rownumbers="true" fitColumns="true"
		singleSelect="true" data-options="toolbar:'#tb',fit:true,border:false">
		<thead>
			<tr>
				<th field=account width="50">用户账号</th>
				<th field=creator width="50">创建者</th>
				<th field=modifyUser width="50">最后修改者</th>
				<th field="groupName" width="20">组别</th>
				<th field="statusName" width="20"  data-options=" 
					formatter: function(value, row, index){
					    if (value == 1) {
					        return '禁用';
					    }
					    else {
					        return '启用';
					    }
					}
				" >状态</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding: 2px 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newAccount()">新增</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editAccount()">修改</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroyAccount()">删除</a>
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="lot-icon-role" plain="true" onclick="confRole()">权限控制</a> -->
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 410px; height: 320px; padding: 20px 10px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post" novalidate>
			<input type="hidden" name="id">
			<div class="fitem">
				<label>用户账号:</label> <input id="account" name="account"
					class="easyui-textbox" data-options="required:true" />
			</div>
			<div class="fitem">
				<label>用户密码:</label> <input id="pwd" name="pwd" type="password"
					class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>确认密码:</label> <input id="rpwd" name="rpwd" type="password"
					class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>用户分组:</label> <input class="easyui-combobox" name="groupId"
					data-options="prompt:'组别',valueField:'id',textField:'name',url:'${base}/admin/dictionary/user/group.do',required:true">
			</div>
			<div class="fitem">
				<label>状态:</label> <input class="easyui-combobox" name="status"
					data-options="prompt:'状态',valueField:'id',textField:'name',url:'${base}/admin/dictionary/user/status.do',required:true">
			</div>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="saveAccount()" style="width: 90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"
			style="width: 90px">取消</a>
	</div>

	<script type="text/javascript">
		function newAccount() {
			$("#account").textbox("readonly", false);
			$('#dlg').dialog('open').dialog('setTitle', '新增');
			$('#fm').form('clear');
			$('#pwd').textbox({
				required : true
			});
			$('#rpwd').textbox({
				required : true
			});
		}

		function editAccount() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$("#account").textbox("readonly")
				$('#dlg').dialog('open').dialog('setTitle', '修改');
				$('#fm').form('load', row);
				$("#pwd").textbox("setValue", "");
				$("#rpwd").textbox("setValue", "")
				$('#pwd').textbox({
					required : false
				});
				$('#rpwd').textbox({
					required : false
				});
			}
		}

		function saveAccount() {
			var form = $('#fm');
			var data = form.form('getData');
			var url = '${base}/admin/user/save.do';

			if (data.id == "" && data.pwd == "") {
				Msg.info("密码不能为空!");
				return;
			}

			if (data.pwd != data.rpwd) {
				Msg.info("两次密码不一致!");
				return;
			}

			if (data.pwd != "") {
				data.pwd = hex_md5("~!@#$wc_" + data.pwd);
				data.rpwd = hex_md5("~!@#$wc_" + data.rpwd);
			}

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

		function destroyAccount() {
			var rows = $('#dg').datagrid('getSelections');
			var ids = [];
			for (var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			if (rows) {
				var url = '${base}/admin/user/delete.do';
				var data = {
					ids : ids.join(",")
				}
				ajaxData(url, data, function(data) {
					if (data.success) {
						$('#dg').datagrid('reload');
					}
					Msg.info(data.msg);
				});
			} else {
				Msg.info("请选择记录!")
			}
		}
	</script>
</body>
</html>
