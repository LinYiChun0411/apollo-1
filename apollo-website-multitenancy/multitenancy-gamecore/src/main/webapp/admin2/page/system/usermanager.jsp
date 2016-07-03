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
	<div class="modal fade bs-example-modal-lg" id="addmodel"
		tabindex="-1" role="dialog" aria-labelledby="editLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
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
								<td width="15%" class="text-right">用户账号：</td>
								<td width="35%" class="text-left"><input name="account" id="accountNew" class="form-control" type="text"></span></td>
								<td width="15%" class="text-right">用户分组：</td>
								<td width="35%" class="text-left">
								<select name="groupName" id="groupIdNew" class="form-control"></select>
<!-- 								<input name="groupId" id="groupIdNew" class="form-control" type="text"/> -->
								</td>
							</tr>
							<tr>
								<td width="15%" class="text-right">密码：</td>
								<td width="35%" class="text-left"><input name="pwd" id="pwdNew" class="form-control" type="password"/></td>
								<td width="15%" class="text-right">确认密码：</td>
								<td width="35%" class="text-left"><input name="rpwd" id="rpwdNew" class="form-control" type="password"/></td>
							</tr>
							
							<tr>
								<td width="15%" class="text-right">状态：</td>
								<td width="35%" class="text-left"><select id="statusNew"
									class="form-control" name="status">
										<option value="1">禁用</option>
										<option value="2">启用</option>
								</select></td>
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
								<td width="35%" class="text-left"><input name="account" id="account" class="form-control" type="text"></td>
							</tr>
							<tr>
								<td width="15%" class="text-right">组别：</td>
								<td width="35%" class="text-left">
<!-- 								<input name="groupName" id="groupName" class="form-control" type="text"/> -->
								<select name="groupName" id="groupName" class="form-control"></select>
								</td>
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
				url : '${base}/admin/user/list.do',
				toolbar : $('#toolbar'),
				columns : [ {
					field : 'account',
					title : '用户账号',
					align : 'center',
					width : '200',
					valign : 'middle',
				}, {
					field : 'creator',
					title : '创建者',
					align : 'center',
					width : '180',
					valign : 'bottom'
				}, {
					field : 'modifyUser',
					title : '最后修改者',
					align : 'center',
					width : '200',
					valign : 'middle'
				}, {
					field : 'groupName',
					title : '组别',
					align : 'center',
					width : '200',
					valign : 'middle'
				}, {
					field : 'status',
					title : '状态',
					align : 'center',
					width : '200',
					valign : 'middle',
					events : operateEvents,
					formatter : statusFormatter
				}, {
					title : '操作',
					align : 'center',
					width : '80',
					valign : 'middle',
					events : operateEvents,
					formatter : operateFormatter
				} ]
			});
		}
			
		//组别
		function zubie(param,value){
			var col = '';
			$.ajax({
				url : "${base}/admin/group/list.do",
				success : function(result) {
					  for ( var j in result.rows ){ 
						  if(value==result.rows[j].name){
							  col+='<option value='+result.rows[j].id+' selected="selected">'+result.rows[j].name+'</option>';
						  }else{
							  col+='<option value='+result.rows[j].id+'>'+result.rows[j].name+'</option>';
						  }
						 
					  }						
				$('#'+param).html(col);	
				}
			});
		}
		
		function newUser(){
			 $("#addmodel").modal('toggle');
			 zubie("groupIdNew","-1");
		}
		
		function statusFormatter(value, row, index) {
			if (value === 2) {
				return '<span class="text-success">启用</span><a href="#"><span class="text-danger stateClose">(禁用)</span></a>';
			}
				return '<span class="text-danger">禁用</span><a href="#"><span class="text-success stateOpen">(启用)</span></a>';
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
								zubie("groupName",$("#groupName").val(row.groupName));
								$("#account").val(row.account);
								$("#creator").val(row.creator);
								$("#modifyUser").val(row.modifyUser);
// 								$("#groupName").val(row.groupName);
								$("#status").val(row.status);

			},
			'click .del' : function(e, value, row, index) {
				del(row);
			},
			'click .stateOpen' : function(e, value, row, index) {
				closeOrOpen(row);
			},
			'click .stateClose' : function(e, value, row, index) {
				closeOrOpen(row);
			}
		};
		
		function closeOrOpen(row){
			$.ajax({
				url : "${base}/admin/user/closeOrOpen.do",
				data : row,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}
		
		function del(value){
			
			   if(confirm("是否删除用户"+value.account+"?")){
				   
					$.ajax({
						url : "${base}/admin/user/del.do",
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
				url : "${base}/admin/user/save.do",
				data : {
					account : $("#accountNew").val(),
					groupId : $("#groupNameNew").val(),
					status : $("#statusNew").val(),
					pwd : $("#pwdNew").val(),
					rpwd : $("#rpwdNew").val()
				},
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}
		//编辑
		function edit(){
			$.ajax({
				url : "${base}/admin/user/edit.do",
				data : {
					account : $("#account").val(),
					creator : $("#creator").val(),
					modifyUser : $("#modifyUser").val(),
					groupId : $("#groupName").val()
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
