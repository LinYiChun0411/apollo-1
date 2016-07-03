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
			<div class="fitem" id="modulePathDiv">
				<label>所属平台:</label> <input class="easyui-combobox" name="stationId"
					data-options="prompt:'所属平台',valueField:'id',textField:'name',
					url:'${base}/admin/station/combo.do',required:true"
					style="width: 250px;">
			</div>
			<div class="fitem" id="modulePathDiv">
				<label>状态:</label> <input class="easyui-combobox" name="status"
					data-options="prompt:'状态',valueField:'id',textField:'name',
					data: [{
							id: '1',
							name: '未读'
						},{
							id: '2',
							name: '已读'
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
			<input class="easyui-combobox" name="stationId"
				data-options="prompt:'所属平台',valueField:'id',textField:'name',url:'${base}/admin/station/combo.do'">
			<input class="easyui-combobox" name="status"
				data-options="prompt:'状态',valueField:'id',textField:'name',data: [{
							id: '1',
							name: '未读'
						},{
							id: '2',
							name: '已读'
						}]">

			<input name="beginDatetime" class="easyui-datetimebox"
				data-options="prompt:'起始时间'"> <input name="endDatetime"
				class="easyui-datetimebox" data-options="prompt:'终止时间'"> <a
				href="#" class="easyui-linkbutton" iconCls="icon-search"
				plain="true" onclick="searchreload()">查询</a><a href="#"
				class="easyui-linkbutton" iconCls="lot-icon-undo" plain="true"
				onclick="resetform()">重置</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-edit" plain="true"
				onclick="readProposal()">查看</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-remove" plain="true"
				onclick="deleteProposal()">删除</a>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="readsuccess()" style="width: 90px">确认</a>
	</div>
</body>
</html>

<script language="javascript">
	var readed = 2;

	function readProposal() {

		var record = getSelected();
		if (record === false) {
			return;
		}
		if (record.status == readed) {
			readHandler(record);
			return;
		}
		$.ajax({
			url : '${base}/admin/proposal/read.do',
			data : {
				proposalId : record.id
			},
			success : function(data) {
				readHandler(record);
			}
		});
	}

	function readHandler(record) {
		$('#fm').form('clear');
		$('#fm').form('load', record);
		$('#dlg').dialog('open').dialog('setTitle', '查看');
	}

	function deleteProposal() {
		var record = getSelected();
		if (record === false) {
			return;
		}
		Msg.confirm("你是否确认要删除？", function(r) {
			if (!r) {
				return;
			}

			$.ajax({
				url : '${base}/admin/proposal/delete.do',
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

	$('#dg').datagrid({
		toolbar : '#tb',
		url : '${base}/admin/proposal/list.do',
		columns : [ [ {
			field : 'name',
			title : '标题',
			width : 100
		}, {
			field : 'creator',
			title : '账号',
			width : 100
		}, {
			field : 'stationName',
			title : '所属平台',
			width : 100
		}, {
			field : 'qq',
			title : 'QQ',
			width : 100
		}, {
			field : 'phone',
			title : '手机号',
			width : 100
		}, {
			field : 'status',
			title : '状态',
			width : 100,
			formatter : function(value, row, index) {
				if (value == 1) {
					return "未读";
				} else if (value == 2) {
					return "已读";
				}
				return "其他";
			}
		}, {
			field : 'createDatetime',
			title : '投诉/建议时间',
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

	function readsuccess() {
		var form = $('#fm');
		var data = form.form('getData');
		if (data.status != readed) {
			$('#dg').datagrid('reload');
		}
		$('#dlg').dialog('close');
	}
</script>