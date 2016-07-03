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
	<jsp:include page="/agent/include/agentmenu.jsp"></jsp:include>
	<div class="container">
		<div id="toolbar">
			<div id="search" class="form-inline">
				<div class="input-group">
					<input type="text" class="form-control" id="account" placeholder="输入用户名查询">
				</div>
				<button class="btn btn-primary" onclick="search();">查询</button>
			</div>
		</div>
		<table id="datagrid_tb"></table>
	</div>
	<div class="modal fade bs-example-modal-lg" id="editmodel" tabindex="-1" role="dialog" aria-labelledby="editLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="editLabel">编辑会员</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="accountId">
					<table class="table table-bordered table-striped" style="clear: both">
						<tbody>
							<tr>
								<td width="15%" class="text-right">登录账号：</td>
								<td width="35%" class="text-left"><span id="account"></span></td>
								<td width="15%" class="text-right">会员姓名：</td>
								<td width="35%" class="text-left"><input type="text" class="form-control" id="userName" /></td>
							</tr>
							<tr>
								<td width="15%" class="text-right">账号状态：</td>
								<td width="35%" class="text-left"><select id="accountStatus" class="form-control">
										<option value="1">禁用</option>
										<option value="2">启用</option>
								</select></td>
								<td width="15%" class="text-right">电话：</td>
								<td width="35%" class="text-left"><input type="text" class="form-control" id="phone" /></td>
							</tr>
							<tr>
								<td width="15%" class="text-right">邮箱：</td>
								<td width="35%" class="text-left"><input type="text" class="form-control" id="email" /></td>
								<td width="15%" class="text-right">QQ：</td>
								<td width="35%" class="text-left"><input type="text" class="form-control" id="qq" /></td>
							</tr>
							<tr>
								<td width="15%" class="text-right">取现人：</td>
								<td width="35%" class="text-left"><input type="text" class="form-control" id="drawUser" /></td>
								<td width="15%" class="text-right">取现银行：</td>
								<td width="35%" class="text-left"><select id="bankId" class="form-control">
										<option value="1">中国工商</option>
										<option value="2">中国农业</option>
										<option value="3">中国建设</option>
										<option value="4">中国招商</option>
										<option value="5">中国交通</option>
								</select></td>
							</tr>
							<tr>
								<td width="15%" class="text-right">银行地址：</td>
								<td width="35%" class="text-left"><input type="text" class="form-control" id="bankAddress" /></td>
								<td width="15%" class="text-right">银行账号：</td>
								<td width="35%" class="text-left"><input type="text" class="form-control" id="cardNo" /></td>
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
				url : '${base}/agent/member/search/list.do',
				queryParams : queryParams,//参数
				toolbar : $('#toolbar'),
				columns : [ {
					field : 'account',
					title : '会员账号',
					align : 'center',
					width : '200',
					valign : 'middle',
				}, {
					field : 'agentName',
					title : '所属上级',
					align : 'center',
					width : '180',
					valign : 'bottom'
				}, {
					field : 'money',
					title : '账号余额',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : moneyFormatter
				}, {
					field : 'userName',
					title : '会员姓名',
					align : 'center',
					width : '200',
					valign : 'middle'
				}, {
					field : 'createDatetime',
					title : '注册时间',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : dateFormatter
				}, {
					field : 'accountStatus',
					title : '状态',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : statusFormatter
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

		function moneyFormatter(value, row, index) {

			if (value === undefined) {
				return value;
			}

			if (value > 0) {
				return [ '<span class="text-danger">', '</span>' ].join(value);
			}
			return [ '<span class="text-primary">', '</span>' ].join(value);

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
				$("#account").html(row.account);
				$("#cardNo").val(row.cardNo);
				$("#accountId").val(row.id);
				$("#bankName").val(row.bankName);
				$("#userName").val(row.userName);
				$("#phone").val(row.phone);
				$("#qq").val(row.qq);
				$("#email").val(row.email);
				$("#drawUser").val(row.drawUser);
				$("#bankAddress").val(row.bankAddress);
				$("#bankId").val(row.bankId);
				$("#accountStatus").val(row.accountStatus);
			},
			'click .start' : function(e, value, row, index) {
				row.accountStatus = 2;
				updstatus(row);
			},
			'click .stop' : function(e, value, row, index) {
				row.accountStatus = 1;
				updstatus(row);
			}
		};

		//设置传入参数
		function queryParams(params) {
			params["account"] = $("#account").val();
			return params
		}
		$(function() {
			getTab();
		})

		function updstatus(row) {
			$.ajax({
				url : "${base}/agent/member/search/updStatus.do",
				data : row,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}

		function search() {
			$("#datagrid_tb").bootstrapTable('refresh');
		}

		function save() {
			$.ajax({
				url : "${base}/agent/member/search/save.do",
				data : {
					id : $("#accountId").val(),
					cardNo : $("#cardNo").val(),
					bankName : $("#bankName").val(),
					userName : $("#userName").val(),
					phone : $("#phone").val(),
					qq : $("#qq").val(),
					email : $("#email").val(),
					drawUser : $("#drawUser").val(),
					bankAddress : $("#bankAddress").val(),
					bankId : $("#bankId").val(),
					accountStatus : $("#accountStatus").val()
				},
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}
	</script>
</body>
</html>