<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>${_title}</title>
<meta charset="utf-8">
</head>
<body>
	<jsp:include page="/agent/include/agentmenu.jsp"></jsp:include>
	<div class="container">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#" data-toggle="tab" id="setnav">公司入款明细</a></li>
			<li><a href="#" data-toggle="tab" id="opnav">公司入款操作</a></li>
		</ul>
		<div id="toolbar">
			<div class="form-group">
				<div class="form-inline">

					<div class="form-group">
						<div class="input-group">
							<label class="sr-only" for="account">会员</label> <select class="form-control" id="account">
								<option value="0">全部会员</option>
							</select>
						</div>
						<div class="input-group">
							<label class="sr-only" for="status">状态</label> <select class="form-control" id="status">
								<option value="0" class="text-warning">未处理</option>
								<option value="0" class="text-danger">提款失败</option>
								<option value="0" class="text-success">存款成功</option>
								<option value="0">全部存款</option>
							</select>
						</div>
						<div class="input-group">
							<label class="sr-only" for="type">类型</label> <select class="form-control" id="type">
								<option value="0">会员存款</option>
								<option value="0">福利赠送</option>
								<option value="0">反水</option>
								<option value="0">真人补钱</option>
							</select>
						</div>
						<div class="input-group">
							<input type="text" class="form-control" id="keyword" placeholder="会员名称">
						</div>

					</div>
					<div class="input-group">
						<input type="text" class="form-control" id="begin" placeholder="开始日期"> <span class="glyphicon glyphicon-th form-control-feedback" aria-hidden="true"></span>
					</div>
					<div class="input-group">
						<input type="text" id="end" class="form-control" placeholder="线束日期"> <span class="glyphicon glyphicon-th form-control-feedback" aria-hidden="true"></span>
					</div>
					<button class="btn btn-success" onclick="search();">查找</button>
				</div>
			</div>
		</div>
		<table id="datagrid_tb"></table>
	</div>
	<script type="text/javascript">
		function getTab() {
			var curDate = new Date();
			var options = {
				language : 'zh-CN',
				autoclose : true,
				minView : 2,
				endDate : curDate,
				format : 'yyyy-mm-dd'
			};
			$('#begin').datetimepicker(options);
			$('#end').datetimepicker(options);

			window.table = new Game.Table({
				id : 'datagrid_tb',
				url : '${base}/agent/finance/depositset/list.do',
				queryParams : queryParams,//参数
				toolbar : $('#toolbar'),
				columns : [ {
					field : 'bank',
					title : '银行名称',
					align : 'center',
					width : '140px',
					valign : 'middle',
				}, {
					field : 'cardNo',
					title : '银行卡号',
					align : 'center',
					valign : 'middle'
				}, {
					field : 'userName',
					title : '开户名',
					align : 'center',
					width : '200px',
					valign : 'middle'
				}, {
					field : 'address',
					title : '开户行地址',
					align : 'center',
					valign : 'middle',
					formatter : moneyFormatter
				}, {
					field : 'userGroup',
					title : '所属会员组',
					align : 'center',
					valign : 'middle',
					formatter : moneyFormatter
				}, {
					title : '操作',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : operateFormatter
				} ]
			});
		}

		function operateFormatter(value, row, index) {
			return [
					'<a class="eidt" href="javascript:void(0)" title="编辑">编辑 </a>  ',
					'<a class="remove" href="javascript:void(0)" title="删除">删除</a>  ' ]
					.join('|');
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
		function remarkFormatter(value, row, index) {
			return [ "<p class='text-danger'>", "</p>" ].join(value);
		}

		//设置传入参数
		function queryParams(params) {
			params['account'] = $("#account").val();
			params['type'] = $("#type").val();
			params['begin'] = $("#begin").val();
			params['end'] = $("#end").val();
			return params
		}
		$(function() {
			getTab();
			bindNavTab();
			/* $("#datagrid_tb").bootstrapTable('hideColumn', 'giveMoney');
			$("#datagrid_tb").bootstrapTable('hideColumn', 'bank');
			$("#datagrid_tb").bootstrapTable('hideColumn', 'actionAndAddr'); */
		});

		function bindNavTab() {
			$("a[data-toggle='tab']").click(function() {
				var table = $("#datagrid_tb");
				var id = this.id;
				platform = id;
				/* if (id == "setnav") {
					table.bootstrapTable('hideColumn', 'giveMoney');
					table.bootstrapTable('hideColumn', 'bank');
					table.bootstrapTable('hideColumn', 'actionAndAddr');
					table.bootstrapTable('showColumn', 'fee');
					table.bootstrapTable('showColumn', 'remark');
				} else if (id == "opnav") {
					table.bootstrapTable('showColumn', 'giveMoney');
					table.bootstrapTable('showColumn', 'bank');
					table.bootstrapTable('showColumn', 'actionAndAddr');
					table.bootstrapTable('hideColumn', 'fee');
					table.bootstrapTable('hideColumn', 'remark');
				} */
				table.bootstrapTable('refresh');
			});
		}

		function search() {
			$("#datagrid_tb").bootstrapTable('refresh');
		}

		function setDate(begin, end) {
			$('#begin').val(begin);
			$('#end').val(end);
		}
	</script>
	<script id="recordtype_tpl" type="text/html">
		{{each data as option}}
        	<option value="{{option.type}}">{{option.name}}</option>
		{{/each}}
	</script>
</body>
</html>