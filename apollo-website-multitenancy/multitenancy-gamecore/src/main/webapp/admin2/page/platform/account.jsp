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
<script type="text/javascript" src="${base}/common/js/md5.js"></script>
</head>
<body>
	<jsp:include page="/admin2/include/adminmenu.jsp"></jsp:include>
	<div class="container">
		<div id="toolbar">
			<div id="search" class="form-inline">
				<label class="sr-only" for="saccount">用户名</label>
				<div class="form-group">
					<div class="input-group">
						<input type="text" class="form-control" id="saccount" placeholder="用户名">
					</div>
					<div class="input-group">
						<select class="form-control" id="saccountType">
							<option value="0">全部类型</option>
						</select>
					</div>
				</div>
				<button class="btn btn-primary" onclick="search();">查询</button>
				<button class="btn btn-primary" onclick="add();">新增</button>
			</div>
		</div>
		<table id="datagrid_tb"></table>
	</div>
	<div class="modal fade bs-example-modal-lg" id="editmodel" tabindex="-1" role="dialog" aria-labelledby="editLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="editLabel">编辑账号</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="accountId"> <input type="hidden" id="agentId">
					<table class="table table-bordered table-striped" style="clear: both">
						<tbody>
							<tr>
								<td width="15%" class="text-right">用户类型：</td>
								<td width="35%" class="text-left"><select id="accountType" class="form-control">
								</select></td>
								<td width="15%" class="text-right">所属上级：</td>
								<td width="35%" class="text-left"><input type="text" class="form-control" id="agentName" /></td>
							</tr>
							<tr>
								<td width="15%" class="text-right">用户账号：</td>
								<td width="35%" class="text-left"><input type="text" class="form-control" id="account" /></td>
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
	<div class="modal fade" id="editpwd" tabindex="-1" role="dialog" aria-labelledby="editpwdLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="editpwdLabel">修改密码</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="pwdatId">
					<table class="table table-bordered table-striped" style="clear: both">
						<tbody>
							<tr>
								<td width="20%" class="text-right">用户名：</td>
								<td width="80%" class="text-left"><input type="text" class="form-control" id="pwdat" disabled="disabled" /></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">新密码：</td>
								<td width="80%" class="text-left"><input type="password" class="form-control" id="pwd" /></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">确认密码：</td>
								<td width="80%" class="text-left"><input type="password" class="form-control" id="rpwd" /></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="updpwd();">保存</button>
				</div>
			</div>
		</div>
	</div>
	<script id="accountType_tpl" type="text/html">
	{{each data as tl}}
			<option value="{{tl.id}}">{{tl.name}}</option>
	{{/each}}
	</script>
	<script type="text/javascript">
		function getTab() {
			window.table = new Game.Table({
				id : 'datagrid_tb',
				url : '${base}/admin/account/list.do',
				queryParams : queryParams,//参数
				toolbar : $('#toolbar'),
				columns : [ {
					field : 'account',
					title : '用户名',
					align : 'center',
					width : '200',
					valign : 'middle',
				}, {
					field : 'userName',
					title : '用户姓名',
					align : 'center',
					width : '200',
					valign : 'middle'
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
					field : 'accountType',
					title : '用户类型',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : typeFormatter

				}, {
					field : 'accountStatus',
					title : '用户状态',
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

		function typeFormatter(value, row, index) {

			return GlobalTypeUtil.getTypeName(2, 2, value);
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
			return [
					'<a class="eidt" href="javascript:void(0)" title="修改">',
					'<i class="glyphicon glyphicon-pencil"></i>',
					'</a>  ',
					'<a class="password" href="javascript:void(0)" title="修改密码">',
					'<i class="glyphicon glyphicon-asterisk"></i>', '</a>  ' ]
					.join('');
		}

		window.operateEvents = {
			'click .eidt' : function(e, value, row, index) {
				$("#editmodel").modal('toggle');
				$("#accountId").val(row.id);
				$("#account").val(row.account);
				$("#account").attr("disabled", "disabled");
				$("#accountType").val(row.accountType);
				$("#agentName").val(row.agentName);
				$("#agentName").attr("disabled", "disabled");
				$("#accountType").attr("disabled", "disabled");
				$("#accountStatus").val(row.accountStatus);
				$("#cardNo").val(row.cardNo);
				$("#bankName").val(row.bankName);
				$("#userName").val(row.userName);
				$("#phone").val(row.phone);
				$("#qq").val(row.qq);
				$("#email").val(row.email);
				$("#drawUser").val(row.drawUser);
				$("#bankAddress").val(row.bankAddress);
				$("#bankId").val(row.bankId);
				$("#agentId").val(row.agentId);
			},
			'click .password' : function(e, value, row, index) {
				$("#editpwd").modal('toggle');
				$("#pwdatId").val(row.id);
				$("#pwdat").val(row.account);
			}
		};

		//设置传入参数
		function queryParams(params) {
			params["accountType"] = $("#saccountType").val();
			params["account"] = $("#saccount").val();
			return params
		}
		$(function() {
			initCombo();
			getTab();
		})

		function initCombo() {
			var eachdata = {
				"data" : GlobalTypeUtil.getCombo(2, 2)
			};
			var html = template('accountType_tpl', eachdata);
			$("#accountType").append(html);
			$("#saccountType").append(html);
		}

		function search() {
			$("#datagrid_tb").bootstrapTable('refresh');
		}

		function updpwd() {
			var password = $("#pwd").val();
			var rpassword = $("#rpwd").val();
			if (password === "" || password === undefined) {
				Msg.info("新密码不能为空！");
				return;
			}
			if (rpassword === "" || rpassword === undefined) {
				Msg.info("确认不能为空！");
				return;
			}

			if (password !== rpassword) {
				Msg.info("两次密码不一致！");
				return;
			}
			password = hex_md5("~!@#$wc_" + password);
			rpassword = hex_md5("~!@#$wc_" + rpassword);
			$.ajax({
				url : "${base}/admin/account/updpwd.do",
				data : {
					id : $("#pwdatId").val(),
					pwd : password,
					rpwd : rpassword
				},
				success : function(result) {
					Msg.info("修改成功！");
				}
			});

		}

		function save() {
			$.ajax({
				url : "${base}/admin/account/save.do",
				data : {
					id : $("#accountId").val(),
					account : $("#account").val(),
					accountType : $("#accountType").val(),
					accountStatus : $("#accountStatus").val(),
					cardNo : $("#cardNo").val(),
					bankName : $("#bankName").val(),
					userName : $("#userName").val(),
					phone : $("#phone").val(),
					qq : $("#qq").val(),
					email : $("#email").val(),
					drawUser : $("#drawUser").val(),
					bankAddress : $("#bankAddress").val(),
					bankId : $("#bankId").val(),
					agentName : $("#agentName").val(),
					agentId : $("#agentId").val()
				},
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}
		function add() {
			$("#editmodel").modal('toggle');
			$("#accountId").val("");
			$("#account").val("");
			$("#account").removeAttr("disabled");
			$("#accountType").val("");
			$("#accountType").removeAttr("disabled");
			$("#agentName").removeAttr("disabled");
			$("#accountStatus").val("");
			$("#cardNo").val("");
			$("#bankName").val("");
			$("#userName").val("");
			$("#phone").val("");
			$("#qq").val("");
			$("#email").val("");
			$("#drawUser").val("");
			$("#bankAddress").val("");
			$("#bankId").val("");
			$("#agentName").val("");
			$("#agentId").val("");
		}
	</script>
</body>
</html>