<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>${_title}</title>
<meta charset="utf-8">
</head>
<body>
	<jsp:include page="/admin2/include/adminmenu.jsp"></jsp:include>
	<div class="container">
		<div id="toolbar">
			<div id="search" class="form-inline">
				<div class="form-group">
				<button class="btn btn-primary" onclick="newUser();">新增</button>
				</div>
			</div>
		</div>
		<table id="datagrid_tb"></table>
	</div>
	<!-- 新增用户 -->
	<div class="modal fade" id="addmodel"
		tabindex="-1" role="dialog" aria-labelledby="editLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="editLabel">新增用户</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="accountId">
					<table class="table table-bordered table-striped"
						style="clear: both">
						<tbody>
							<tr>
								<td width="15%" class="text-right">组别名称：</td>
								<td width="35%" class="text-left"><input name="name" id="name" class="form-control" type="text"></span></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="save();">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!--修改用户 -->
	<div class="modal fade" id="editmodel"
		tabindex="-1" role="dialog" aria-labelledby="editLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="editLabel">修改</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="accountId">
					<table class="table table-bordered table-striped"
						style="clear: both">
						<tbody>
							<tr>
								<td width="15%" class="text-right">用户名：</td>
								<td width="35%" class="text-left">
								<input name="id" id="idNew" class="form-control" type="hidden">
								<input name="name" id="nameNew" class="form-control" type="text"></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="edit();">保存</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			getTab();
		})
		function getTab() {
			window.table = new Game.Table({
				id : 'datagrid_tb',
				url : '${base}/admin/group/list.do',
				toolbar : $('#toolbar'),
				columns : [ {
					field : 'name',
					title : '组别名称',
					align : 'center',
					width : '30',
					valign : 'middle',
				}, {
					title : '操作',
					align : 'center',
					width : '30',
					valign : 'middle',
					events : operateEvents,
					formatter : operateFormatter
				} ]
			});
		}

		function newUser(){
			 $("#addmodel").modal('toggle');
		}
		

		function dateFormatter(value, row, index) {
			return DateUtil.formatDatetime(value);
		}

		function operateFormatter(value, row, index) {
			return [ '<a class="eidt" href="javascript:void(0)" title="修改">',
					'<i class="glyphicon glyphicon-pencil"></i>', '</a> ',
					'<a class="del" href="javascript:void(0)" title="删除">',
					'<i class="glyphicon glyphicon-remove"></i>', '</a> ']
					.join('');
		}

		window.operateEvents = {
			'click .eidt' : function(e, value, row, index) {
				 $("#editmodel").modal('toggle');
				 $("#nameNew").val(row.name);
				 $("#idNew").val(row.id);
			},
			'click .del' : function(e, value, row, index) {
				del(row);
			}
		};
		
		function del(value){
			
			   if(confirm("是否删除用户"+value.name+"?")){
				   
					$.ajax({
						url : "${base}/admin/group/del.do",
						data : value,
						success : function(result) {
							$("#datagrid_tb").bootstrapTable('refresh');
						}
					});
			   }
			   
		}
		
		//保存
		function save(){
			$.ajax({
				url : "${base}/admin/group/save.do",
				data : {
					name : $("#name").val()
				},
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}
		//编辑
		function edit(){
			$.ajax({
				url : "${base}/admin/group/save.do",
				data : {
					id : $("#idNew").val(),
					name : $("#nameNew").val()
				},
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}
		
		function search() {
			$("#datagrid_tb").bootstrapTable('refresh');
		}
	</script>
</body>
</html>
