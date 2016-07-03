<%@ page language="java" import="java.util.*" isELIgnored="false"  pageEncoding="utf-8"%>
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
	<table id="dg" class="easyui-datagrid" url="${base}/admin/group/list.do" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true" data-options="toolbar:'#tb',fit:true,border:false">
		<thead>
			<tr>
				<th field="name" width="50">组别名称</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding: 2px 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newGroup()">新增</a> <a
			href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editGroup()">修改</a> <a
			href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyGroup()">删除</a>
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="lot-icon-role" plain="true" onclick="confRole()">权限控制</a> -->
	</div>

	<div id="dlg" class="easyui-dialog" style="width: 400px; height: 160px; padding: 20px 10px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post" novalidate>
			<input type="hidden" name="id">
			<div class="fitem">
				<label>组别名称:</label> <input id="name" name="name" class="easyui-textbox" data-options="required:true" />
			</div>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveGroup()"
			style="width: 90px">保存</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#dlg').dialog('close')" style="width: 90px">取消</a>
	</div>

	<div id="dlg-buttons_conf">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveRole()" style="width: 90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#dlg_conf').dialog('close')" style="width: 90px">取消</a>
	</div>

	<div id="dlg_conf" class="easyui-dialog" style="width: 600px; height: 500px; padding: 20px 10px" closed="true"
		buttons="#dlg-buttons_conf">
		<form id="fm_conf" method="post" novalidate>
			<input type="hidden" name="id">
			<div class="fitem">
				<label>组别名称:</label> <input name="name" class="easyui-textbox" readonly="readonly">
			</div>
			<!-- <div class="fitem">
				<div style="float: left; width: 100px;">
					<label>权限设置:</label>
					<select id="ctr2" class="easyui-combotree" multiple style="width: 160px;">
				</div>
				<div style="float: left; width: 400px;">
					<ul id="ctr" class="easyui-tree" data-options="animate:true,checkbox:true"></ul>
				</div>
			</div> -->
		</form>
	</div>

	<script type="text/javascript">
		function confRole() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg_conf').dialog('open').dialog('setTitle', '权限控制');
				$('#fm_conf').form('load', row);
				$('#ctr').tree({
					url : '${base}/admin/account/tree.do?userId=' + row.userId,
					method : 'post'
				});
				/* $('#ctr2').combotree({
					url : '${base}/admin/account/tree.do?userId=' + row.userId,
					method : 'post'
				}); */
			}
		}

		function saveRole() {
			var nodes = $('#ctr').tree('getChecked',
					[ 'checked', 'indeterminate' ]);
			var ids = '';
			for (var i = 0; i < nodes.length; i++) {
				if (ids != '') {
					ids += ',';
				}
				ids += nodes[i].id;//权限ID
			}
			var data = $('#fm_conf').form('getData');
			data.roleIds = ids;
			var url = '${base}/admin/account/saveRole.do';
			ajaxData(url, data, function(data) {
				$('#dlg_conf').dialog('close'); // close the dialog
				$('#dg').datagrid('reload'); // reload the user data
				Msg.info(data.msg);
			});
		}

		function newGroup() {
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

		function editGroup() {
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

		function saveGroup() {
			var form = $('#fm');
			var data = form.form('getData');
			var url = '${base}/admin/group/save.do';
			
			if (!form.form('validate')) {
				return;
			}
			
			$.ajax({
				url:url,
				data:data,
				success:function(data){
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
					Msg.info("保存成功！");
				}
			});
		}

		function destroyGroup() {
			var rows = $('#dg').datagrid('getSelections');
			var ids = [];
			for (var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			if (rows) {
				var url = '${base}/admin/group/delete.do';
				var data = {
					ids : ids.join(",")
				}
				
				$.ajax({
					url:url,
					data:data,
					success:function(data){
						$('#dg').datagrid('reload');
						Msg.info("删除成功！");
					}
				});
				
			} else {
				Msg.info("请选择记录!")
			}
		}
	</script>
</body>
</html>
