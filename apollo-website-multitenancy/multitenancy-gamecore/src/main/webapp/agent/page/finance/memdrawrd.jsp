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
								<option value="0">会员提款</option>
								<option value="0">后台扣款</option>
								<option value="0">全部类型</option>
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
				url : '${base}/agent/finance/memdrawrd/list.do',
				queryParams : queryParams,//参数
				toolbar : $('#toolbar'),
				columns : [ {
					field : 'id',
					title : '编号',
					align : 'center',
					width : '80px',
					valign : 'bottom'
				}, {
					field : 'account',
					title : '提款会员',
					align : 'center',
					width : '140px',
					valign : 'middle',
				}, {
					field : 'userName',
					title : '真实姓名',
					align : 'center',
					valign : 'middle'
				}, {
					field : 'orderNo',
					title : '订单号',
					align : 'center',
					valign : 'middle'
				}, {
					field : 'money',
					title : '提款金额',
					align : 'center',
					valign : 'middle',
					formatter : moneyFormatter
				}, {
					field : 'fee',
					title : '手续费',
					align : 'center',
					valign : 'middle',
					formatter : moneyFormatter
				}, {
					field : 'createDatetime',
					title : '申请时间',
					align : 'center',
					width : '200px',
					valign : 'middle',
					formatter : dateFormatter
				}, {
					field : 'opStatus',
					title : '流水查询',
					align : 'center',
					width : '200px',
					valign : 'middle'
				}, {
					field : 'remark',
					title : '说明',
					align : 'center',
					width : '200px',
					valign : 'middle'
				}, {
					field : 'status',
					title : '操作',
					align : 'center',
					width : '200px',
					valign : 'middle'
				} ]
			});
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
		})

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