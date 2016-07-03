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
							<label class="sr-only" for="platfrom">平台</label> <select class="form-control" id=""platfrom"">
								<option value="0">全部平台</option>
							</select>
						</div>
						<div class="input-group">
							<label class="sr-only" for="type">类型</label> <select class="form-control" id="type">
								<option value="0">转入</option>
								<option value="0">转出</option>
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
				url : '${base}/agent/finance/realchangerd/list.do',
				queryParams : queryParams,//参数
				toolbar : $('#toolbar'),
				columns : [ {
					field : 'account',
					title : '会员账号',
					align : 'center',
					width : '140px',
					valign : 'middle',
				}, {
					field : 'orderNo',
					title : '订单号',
					align : 'center',
					valign : 'middle'
				}, {
					field : 'outPlatform',
					title : '转出账户',
					align : 'center',
					valign : 'middle'
				}, {
					field : 'money',
					title : '转换金额',
					align : 'center',
					width : '200px',
					valign : 'middle',
					formatter : moneyFormatter
				}, {
					field : 'inPlatform',
					title : '转入账户',
					align : 'center',
					valign : 'middle'
				}, {
					field : 'createDatetime',
					title : '转换时间',
					align : 'center',
					width : '200px',
					valign : 'middle',
					formatter : dateFormatter
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