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
				<label class="sr-only" for="keyword">关键字</label> <label class="sr-only" for="methodType">查询类型</label>
				<div class="form-group">
					<div class="input-group">
						<select class="form-control" id="methodType">
							<option value="name">站点名称</option>
							<option value="account">租户账号</option>
						</select>
					</div>
					<div class="input-group">
						<input type="text" class="form-control" id="keyword" placeholder="关键字">
					</div>
				</div>
				<button class="btn btn-primary" onclick="search();">查询</button>
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
					<h4 class="modal-title" id="editLabel">编辑站点</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="stationId">
					<table class="table table-bordered table-striped" style="clear: both">
						<tbody>
							<tr>
								<td width="15%" class="text-right">站点名称：</td>
								<td width="35%" class="text-left"><input type="text" class="form-control" id="name" /></td>
							</tr>
							<tr>
								<td width="15%" class="text-right">别名：</td>
								<td width="35%" class="text-left"><input type="text" class="form-control" id="floder" /></td>
							</tr>
							<tr>
								<td width="15%" class="text-right">租户：</td>
								<td width="35%" class="text-left"><input type="text" class="form-control" id="account" /></td>
							</tr>
							<tr>
								<td width="15%" class="text-right">状态：</td>
								<td width="35%" class="text-left"><select id="status" class="form-control">
										<option value="1">禁用</option>
										<option value="2">启用</option>
								</select></td>
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
		function getTab() {
			window.table = new Game.Table({
				id : 'datagrid_tb',
				url : '${base}/admin/station/list.do',
				queryParams : queryParams,//参数
				toolbar : $('#toolbar'),
				columns : [ {
					field : 'name',
					title : '站点名称',
					align : 'center',
					width : '200',
					valign : 'middle',
				}, {
					field : 'account',
					title : '租户账号',
					align : 'center',
					width : '180',
					valign : 'bottom'
				}, {
					field : 'floder',
					title : '站点目录',
					align : 'center',
					width : '200',
					valign : 'middle'
				}, {
					field : 'status',
					title : '站点状态',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : statusFormatter
				}, {
					field : 'createDatetime',
					title : '创建时间',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : dateFormatter
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

		function statusFormatter(value, row, index) {

			var sn = GlobalTypeUtil.getTypeName(2, 1, value);
			if (value === 2) {
				return [ '<span class="text-success">', '</span>' ].join(sn);
			}
			return [ '<span class="text-danger">', '</span>' ].join(sn);
		}

		function dateFormatter(value, row, index) {
			return DateUtil.formatDatetime(value);
		}

		function operateFormatter(value, row, index) {
			return [ '<a class="eidt" href="javascript:void(0)" title="修改">',
					'<i class="glyphicon glyphicon-pencil"></i>', '</a>  ',
					'<a class="start" href="javascript:void(0)" title="启用">',
					'<i class="glyphicon glyphicon-ok-circle"></i>', '</a>  ',
					'<a class="stop" href="javascript:void(0)" title="停用">',
					'<i class="glyphicon glyphicon-ban-circle"></i>', '</a>' ]
					.join('');
		}

		window.operateEvents = {
			'click .eidt' : function(e, value, row, index) {

				$("#editmodel").modal('toggle');
				$("#account").val(row.account);
				$("#account").attr("readonly", "readonly")//将input元素设置为readonly
				$("#name").val(row.name);
				$("#stationId").val(row.id);
				$("#floder").val(row.floder);
				$("#status").val(row.status);
			},
			'click .start' : function(e, value, row, index) {
				row.stationId = row.id;
				open(row);
			},
			'click .stop' : function(e, value, row, index) {
				row.stationId = row.id;
				close(row);
			}
		};

		//设置传入参数
		function queryParams(params) {
			params[$("#methodType").val()] = $("#keyword").val();
			return params
		}
		$(function() {
			getTab();
		})

		function open(row) {
			$.ajax({
				url : "${base}/admin/station/open.do",
				data : row,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}
		function close(row) {
			$.ajax({
				url : "${base}/admin/station/close.do",
				data : row,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}

		function search() {
			$("#datagrid_tb").bootstrapTable('refresh');
		}

		function add() {
			$("#editmodel").modal('toggle');
			$("#account").val("");
			$("#account").removeAttr("readonly");//去除input元素的readonly属性
			$("#name").val("");
			$("#stationId").val("");
			$("#floder").val("");
			$("#status").val("");
		}

		function save() {
			$.ajax({
				url : "${base}/admin/station/save.do",
				data : {
					id : $("#stationId").val(),
					name : $("#name").val(),
					floder : $("#floder").val(),
					account : $("#account").val(),
					status : $("#status").val(),
				},
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}
	</script>
</body>
</html>