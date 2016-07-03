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
	<div id="dg"></div>
<!-- 新增 -->
	<div id="dlg" class="easyui-dialog"
		style="width: 510px; height: 440px; padding: 20px 10px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<input type="hidden" name="id">
			<div class="fitem">
				<label>站点:</label> <input class="easyui-combobox" name="groupId"
					data-options="prompt:'站点名称',valueField:'id',textField:'name',editable:false,url:'${base}/admin/lottery/stationList.do',required:true">
			</div>
<!-- 			<div class="fitem"> -->
<!-- 				<label>彩种名称:</label> <input class="easyui-combobox" name="name" -->
<%-- 					data-options="prompt:'彩种名称',valueField:'id',textField:'name',editable:false,url:'${base}/admin/lottery/list.do',required:true"> --%>
<!-- 			</div> -->
				
				<c:foreach items="${list}" var="li">
				${li}
				</c:foreach>
				
				
<!-- 			<div class="fitem"> -->
<!-- 				<label>彩种名称:</label> <input name="name" class="easyui-textbox" -->
<!-- 					style="width: 250px;" data-options="required:true" /> -->
<!-- 			</div> -->
			<div class="fitem" id="modulePathDiv">
				<label>状态:</label> <input class="easyui-combobox" name="status"
					data-options="prompt:'状态',valueField:'id',editable:false,textField:'name',
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
	
	
<!-- 	修改 -->
	<div id="dlg2" class="easyui-dialog"
		style="width: 510px; height: 440px; padding: 20px 10px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm2" method="post">
			<input type="hidden" name="id">
			<div class="fitem">
				<label>站点:</label> <input class="easyui-combobox" name="groupId"
					data-options="prompt:'站点名称',valueField:'id',textField:'name',editable:false,url:'${base}/admin/lottery/stationList.do',required:true">
			</div>
			<div class="fitem">
				<label>彩种名称:</label> <input name="name" class="easyui-textbox"
					style="width: 250px;" data-options="required:true" />
			</div>

			<div class="fitem">
				<label>球数:</label> <input name="balls" class="easyui-textbox"
					style="width: 250px;"  data-options="required:true,editable:false">
			</div>
			<div class="fitem">
				<label>站点名称:</label> <input name="stationId" class="easyui-textbox"
					style="width: 250px;" data-options="required:true,editable:false">
			</div>
			<div class="fitem">
				<label>彩种类型:</label> <input name="type" class="easyui-combobox"
					style="width: 250px;" data-options="prompt:'彩种类型',editable:false,valueField:'id',editable:false,textField:'name',
					data: [{
							id: '1',
							name: '系统彩'
						},{
							id: '2',
							name: '时时彩'
						},{
							id: '3',
							name: 'pk10'
						},{
							id: '4',
							name: '排列三'
						},{
							id: '5',
							name: '11选5'
						},{
							id: '6',
							name: '香港彩'
						},{
							id: '7',
							name: 'PC蛋蛋'
						}],required:true">
			</div>

			<div class="fitem">
				<label> 彩票编码:</label> <input name="code" class="easyui-textbox"
					style="width: 250px;" data-options="required:true,editable:false">
			</div>
			<div class="fitem">
				<label> 序号:</label> <input name="sortNo" class="easyui-textbox"
					style="width: 250px;" data-options="required:true">
			</div>
			<div class="fitem">
				<label>开封盘时间差(秒):</label> <input name="ago" class="easyui-textbox"
					style="width: 250px;" data-options="required:true,editable:false">
			</div>
			<div class="fitem">
				<label> 显示时分组:</label> <input name="viewGroup" class="easyui-combobox"
					style="width: 250px;" data-options="prompt:'显示时分组',valueField:'id',editable:false,textField:'name',
					data: [{
							id: '1',
							name: '时时彩'
						},{
							id: '2',
							name: '低频彩'
						},{
							id: '3',
							name: '快开'
						},{
							id: '4',
							name: '快三'
						},{
							id: '5',
							name: '11选5'
						},{
							id: '6',
							name: '香港彩'
						}],required:true">
			</div>
			
			<div class="fitem" id="modulePathDiv">
				<label>状态:</label> <input class="easyui-combobox" name="status"
					data-options="prompt:'状态',valueField:'id',editable:false,textField:'name',
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
			iconCls="icon-ok" onclick="saveStation2()" style="width: 90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg2').dialog('close')"
			style="width: 90px">取消</a>
	</div>
	
 <script type="text/javascript">
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
			$('#fm2').form('clear');
			$('#fm2').form('load', record);
			$('#dlg2').dialog('open').dialog('setTitle', '编辑');
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
					url : '${base}/admin/lottery/openOrClose.do',
					data : {
						bid : record.id,
						status : 2
					},
					success : function(data) {
						$('#dg').datagrid('reload');
						Msg.info("启用成功");
					}
				});
				
			});
		}
	});
// 	http://localhost:8080/game/admin/station/list.do    //站点名称
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
					url : '${base}/admin/lottery/openOrClose.do',
					data : {
						bid : record.id,
						status : 1
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
					url : '${base}/admin/lottery/del.do',
					data : {
						bid : record.id
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

	$('#dg').datagrid({
		toolbar : bar,
		url : '${base}/admin/lottery/listByLottery.do',
		columns : [ [ {
			field : 'name',
			title : '彩种名称',
			width : 100
		}, {
			field : 'ago',
			title : '开封盘时间差(秒)',
			width : 100
		},{
			field : 'code',
			title : '彩种编码',
			width : 100
		}, {
			field : 'viewGroup',
			title : '显示时分组',
			width : 100,
			formatter : function(value, row, index) {
				if (value == 1) {
					return "时时彩";
				} else if (value == 2) {
					return "低频彩";
				}else if (value == 3) {
					return "快开";
				}else if (value == 4) {
					return "快三";
				}else if (value == 5) {
					return "11选5";
				}else if (value == 6) {
					return "香港彩";
				}
				return "异常";
			}
		}, {
			field : 'balls',
			title : '球数',
			width : 100
		}, {
			field : 'stationId',
			title : '站点',
			width : 100
		}, {
			field : 'type',
			title : '彩种类型',
			width : 100,
			formatter : function(value, row, index) {
				if (value == 1) {
					return "系统彩";
				} else if (value == 2) {
					return "时时彩";
				}else if (value == 3) {
					return "pk10";
				}else if (value == 4) {
					return "排列三";
				}else if (value == 5) {
					return "11选5";
				}else if (value == 6) {
					return "香港彩";
				}else if (value == 7) {
					return "PC蛋蛋";
				}
				return "异常";
			}
			},{
			field : 'status',
			title : '彩种状态',
			width : 100,
			formatter : function(value, row, index) {
				if (value == 1) {
					return "关闭";
				} else if (value == 2) {
					return "开启";
				}
				return "异常";
			}
		},
		{
			field : 'sortNo',
			title : '序号',
			width : 150
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
	

	function saveStation() {//新增
		var form = $('#fm');
		saveOrUpdate(form);
	}
	
	function saveStation2() {//修改
		var form = $('#fm2');
		saveOrUpdate(form);
	}
	
	function saveOrUpdate(forms){
		var form = forms;
		var data = form.form('getData');
		var url = '${base}/admin/lottery/savaOrUpdate.do';

		if (!form.form('validate')) {
			return;
		}
// 		$.ajax({
// 			url : url,
// 			data : data,
// 			success : function(data) {
// 				$('#dlg').dialog('close'); // close the dialog
// 				$('#dg').datagrid('reload'); // reload the user data
// 				Msg.info("保存成功！");
// 			}
// 		});
	}
	
 </script>
</body>
</html>
