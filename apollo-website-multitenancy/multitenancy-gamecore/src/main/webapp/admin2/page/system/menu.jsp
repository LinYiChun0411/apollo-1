<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>${_title}</title>
<meta charset="utf-8">
<style type="text/css">
td {
	vertical-align: middle !important;
}
</style>
</head>
<body>
	<jsp:include page="/admin2/include/adminmenu.jsp"></jsp:include>
	<div class="container">
		<div id="toolbar">
			<div id="search" class="form-inline">
				<button class="btn btn-primary" onclick="back();">返回</button>
				<button class="btn btn-primary" onclick="add();">新增</button>
			</div>
		</div>
		<table id="datagrid_tb"></table>
	</div>
	<div class="modal fade" id="editmodel" tabindex="-1" role="dialog" aria-labelledby="editLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="editLabel">编辑菜单</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="parentId"> <input type="hidden" id="menuId"><input type="hidden" id="level">
					<table class="table table-bordered table-striped" style="clear: both">
						<tbody>
							<tr>
								<td width="20%" class="text-right">上级目录：</td>
								<td width="80%" class="text-left"><span id="parentName"></span></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">名称：</td>
								<td width="80%" class="text-left"><input type="text" class="form-control" id="name" placeholder="名称" /></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">页面URL：</td>
								<td width="80%" class="text-left"><input type="text" class="form-control" id="url" placeholder="页面URL" /></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">功能URL：</td>
								<td width="80%" class="text-left"><input type="text" class="form-control" id="modulePath" placeholder="功能URL" /></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">状态：</td>
								<td width="80%" class="text-left"><select id="status" class="form-control">
										<option value="1">禁用</option>
										<option value="2">启用</option>
										<option value="3">隐藏</option>
								</select></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">排序值：</td>
								<td width="80%" class="text-left"><input type="text" class="form-control" id="sort" placeholder="排序值" /></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">图标：</td>
								<td width="80%" class="text-left"><input type="text" class="form-control" id="icon" placeholder="图标" /></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">备注：</td>
								<td width="80%" class="text-left"><textarea class="form-control" id="remark"></textarea></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="save();">保存</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var ps = [ {
			"id" : 0,
			"name" : "根目录"
		} ]; //轨迹
		var parentId = 0; //当前父节点ID
		var parentName = "根目录"; //当前父节点名称
		var level = 1;

		function getTab() {
			window.table = new Game.Table({
				id : 'datagrid_tb',
				url : '${base}/admin/menu/getMenuTree.do',
				queryParams : queryParams,//参数
				toolbar : $('#toolbar'),
				columns : [ {
					field : 'name',
					title : '名称',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : nameFormatter
				}, {
					field : 'status',
					title : '状态',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : statusFormatter
				}, {
					field : 'level',
					title : '级别',
					align : 'center',
					width : '200',
					valign : 'middle'
				}, {
					field : 'sort',
					title : '排序',
					align : 'center',
					width : '200',
					valign : 'middle'
				}, {
					title : '操作',
					align : 'center',
					width : '200',
					valign : 'middle',
					events : operateEvents,
					formatter : operateFormatter
				} ]
			});
		}

		function nameFormatter(value, row, index) {

			return [
					"<a href='javascript:void(0);' class='link' onclick='down("
							+ row.id + ",\"" + row.name + "\"," + row.level
							+ ");'>", "</a>" ].join(value);
		}
		function statusFormatter(value, row, index) {

			var sn = GlobalTypeUtil.getTypeName(6, 1, value);
			if (value === 2) {
				return [ '<span class="text-success">', '</span>' ].join(sn);
			}
			return [ '<span class="text-danger">', '</span>' ].join(sn);
		}

		function operateFormatter(value, row, index) {
			return [ '<a class="down" href="javascript:void(0)" title="下级">',
					'<i class="glyphicon glyphicon-arrow-down"></i>', '</a>  ',
					'<a class="eidt" href="javascript:void(0)" title="修改">',
					'<i class="glyphicon glyphicon-pencil"></i>', '</a>  ',
					'<a class="start" href="javascript:void(0)" title="启用">',
					'<i class="glyphicon glyphicon-ok-circle"></i>', '</a>  ',
					'<a class="stop" href="javascript:void(0)" title="停用">',
					'<i class="glyphicon glyphicon-ban-circle"></i>', '</a>', ]
					.join('');
		}

		window.operateEvents = {
			'click .down' : function(e, value, row, index) {
				down(row.id, row.name, row.level);
			},
			'click .eidt' : function(e, value, row, index) {

				$("#editmodel").modal('toggle');
				$("#parentName").html(parentName);
				$("#menuId").val(row.id);
				$("#parentId").val(row.parentId);
				$("#name").val(row.name);
				$("#url").val(row.url);
				$("#modulePath").val(row.modulePath);
				$("#sort").val(row.sort);
				$("#icon").val(row.icon);
				$("#status").val(row.status);
				$("#remark").val(row.remark);
				$("#level").val(row.level);
			},
			'click .start' : function(e, value, row, index) {
				row.status = 2;
				upd(row);
			},
			'click .stop' : function(e, value, row, index) {
				row.status = 1;
				upd(row);
			}
		};

		//设置传入参数
		function queryParams(params) {
			params["parentId"] = parentId;
			return params;
		}
		$(function() {
			getTab();
		})

		function back() {
			var index = ps.length - 2;

			if (parentId == 0 || index < 0) {
				Msg.info("当前已经是最顶级了");
				return;
			}
			data = ps[index];
			parentId = data.id;
			parentName = data.name;
			level = data.level;
			var nps = [];
			for (var i = 0; i < ps.length; i++) {
				if (i == ps.length - 1) {
					break;
				}
				nps.push(ps[i]);
			}
			ps = nps;
			$("#datagrid_tb").bootstrapTable('refresh');
		}

		function down(id, name, lvl) {
			var data = {};
			data.id = id
			data.name = name;
			parentId = id;
			parentName = name;
			level = lvl+ 1;
			ps.push(data);
			$("#datagrid_tb").bootstrapTable('refresh');
		}

		function upd(row) {
			$.ajax({
				url : "${base}/admin/menu/updMenu.do",
				data : row,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}

		function remove(row) {
			$.ajax({
				url : "${base}/admin/domain/delete.do",
				data : row,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}

		function save() {
			$.ajax({
				url : "${base}/admin/menu/updMenu.do",
				data : {
					id : $("#menuId").val(),
					parentId : $("#parentId").val(),
					name : $("#name").val(),
					url : $("#url").val(),
					modulePath : $("#modulePath").val(),
					sort : $("#sort").val(),
					icon : $("#icon").val(),
					status : $("#status").val(),
					remark : $("#remark").val(),
					level : $("#level").val()
				},
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}
		function add() {
			$("#editmodel").modal('toggle');
			$("#parentName").html(parentName);
			$("#parentId").val(parentId);
			$("#menuId").val("");
			$("#name").val("");
			$("#url").val("");
			$("#modulePath").val("");
			$("#sort").val("");
			$("#icon").val("");
			$("#status").val("");
			$("#remark").val("");
			$("#level").val(level);
		}
	</script>
</body>
</html>