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
			<button class="btn btn-primary" onclick="add();">新增</button>
		</div>
		<table id="datagrid_tb"></table>
	</div>
	<div class="modal fade" id="editmodel" tabindex="-1" role="dialog" aria-labelledby="editLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="editLabel">缓存详情</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="domainId">
					<table class="table table-bordered table-striped" style="clear: both">
						<tbody>
							<tr>
								<td width="20%" class="text-right">名称：</td>
								<td width="80%" class="text-left"><input type="text" name="id" class="form-control" /></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">键值：</td>
								<td width="80%" class="text-left"><input type="text" class="form-control" id="key" /></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">表达式：</td>
								<td width="80%" class="text-left"><input type="text" class="form-control" id="expression" /></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">数据类型：</td>
								<td width="80%" class="text-left"><input type="text" class="form-control" id="dataType" /></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">数据库：</td>
								<td width="80%" class="text-left"><input type="text" class="form-control" id="db" /></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">过期时间(秒)：</td>
								<td width="80%" class="text-left"><input type="text" class="form-control" id="timeout" /></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">描述：</td>
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
	<script id="stationlst_tpl" type="text/html">
	{{each data as st}}
			<option value="{{st.id}}">{{st.name}}</option>
	{{/each}}
	</script>
	<script type="text/javascript">
		function getTab() {
			window.table = new Game.Table({
				id : 'datagrid_tb',
				url : '${base}/admin/cache/list.do',
				queryParams : queryParams,//参数
				toolbar : $('#toolbar'),
				columns : [ {
					field : 'name',
					title : '名称',
					align : 'center',
					valign : 'middle',
				}, {
					field : 'key',
					title : '键值',
					align : 'center',

					valign : 'middle'
				}, {
					field : 'expression',
					title : '表达式',
					align : 'center',
					width : '180',
					valign : 'bottom'
				}, {
					field : 'dataType',
					title : '数据类型',
					align : 'center',

					valign : 'middle'
				}, {
					field : 'db',
					title : '数据库',
					align : 'center',
					valign : 'middle'
				}, {
					field : 'timeout',
					title : '过期时间(秒)',
					align : 'center',
					valign : 'middle'
				}, {
					title : '操作',
					align : 'center',
					valign : 'middle',
					events : operateEvents,
					formatter : operateFormatter
				} ]
			});
		}

		function operateFormatter(value, row, index) {
			return [ '<a class="detail" href="javascript:void(0)" title="查看">',
					'<i class="glyphicon glyphicon-eye-open"></i>', '</a>  ',
					'<a class="remove" href="javascript:void(0)" title="删除">',
					'<i class="glyphicon glyphicon-remove"></i>', '</a>' ]
					.join('');
		}

		window.operateEvents = {
			'click .detail' : function(e, value, row, index) {
			},
			'click .remove' : function(e, value, row, index) {
				remove(row);
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

		function remove(row) {
		/* 	$.ajax({
				url : "${base}/admin/domain/delete.do",
				data : row,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			}); */
		}

		function search() {
			$("#datagrid_tb").bootstrapTable('refresh');
		}

		function add() {
			$("#editmodel").modal('toggle');
		}

		function save() {
			
		/* $.ajax({
				url : "${base}/admin/domain/save.do",
				data : {
					id : $("#domainId").val(),
					stationId : $("#stationId").val(),
					domain : $("#domain").val(),
					status : $("#status").val(),
				},
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			}); */
		}
	</script>
</body>
</html>