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
					<div class="input-group">
						<input type="text" class="form-control" id="begin" placeholder="开始日期"> <span class="glyphicon glyphicon-th form-control-feedback" aria-hidden="true"></span>
					</div>
					<button class="btn btn-default">今日</button>
					<button class="btn btn-default">昨日</button>
					<button class="btn btn-default">本周</button>
					<label class="sr-only" for="account">会员账号</label>
					<div class="form-group">
						<div class="input-group">
							<input type="text" class="form-control" id="account" placeholder="会员账号">
						</div>
						<div class="input-group">
							<label class="sr-only" for="type">类型</label> <select class="form-control" id="type">
								<option value="0">全部类型</option>
							</select>
						</div>
					</div>
					<button class="btn btn-primary" onclick="search();">查询</button>
				</div>
				<div class="form-inline" style="margin-top: 5px;">
					<div class="input-group">
						<input type="text" id="end" class="form-control" placeholder="线束日期"> <span class="glyphicon glyphicon-th form-control-feedback" aria-hidden="true"></span>
					</div>
					<button class="btn btn-default">上周</button>
					<button class="btn btn-default">本月</button>
					<button class="btn btn-default">上月</button>
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
				url : '${base}/agent/finance/memmnyrd/list.do',
				queryParams : queryParams,//参数
				toolbar : $('#toolbar'),
				columns : [ {
					field : 'id',
					title : '编号',
					align : 'center',
					width : '180',
					valign : 'bottom'
				}, {
					field : 'account',
					title : '会员账号',
					align : 'center',
					width : '200',
					valign : 'middle',
				}, {
					field : 'type',
					title : '变动类型',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : typeFormatter
				}, {
					field : 'beforeMoney',
					title : '变动前金额',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : moneyFormatter
				}, {
					field : 'money',
					title : '变动金额',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : moneyFormatter
				}, {
					field : 'afterMoney',
					title : '变动后会员余额',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : moneyFormatter
				}, {
					field : 'createDatetime',
					title : '变动时间(系统)',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : dateFormatter
				}, {
					field : 'remark',
					title : '备注',
					align : 'center',
					width : '200',
					valign : 'middle'
				} ]
			});
		}

		function typeFormatter(value, row, index) {

			return GlobalTypeUtil.getTypeName(1, 1, value);
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
			initCombo();
			bindbtn();
		})

		function search() {
			$("#datagrid_tb").bootstrapTable('refresh');
		}

		function initCombo() {
			$.ajax({
				url : "${base}/agent/dictionary/money/record/type.do",
				success : function(data) {
					var eachdata = {
						"data" : data
					};
					var html = template('recordtype_tpl', eachdata);
					$("#type").append(html);
				}
			});
		}

		function setDate(begin, end) {
			$('#begin').val(begin);
			$('#end').val(end);
		}

		function bindbtn() {
			$(".btn-default").click(function() {
				var type = $(this).html();
				var begin = "";
				var end = "";
				if ('今日' === type) {
					begin = DateUtil.getCurrentDate();
					end = begin;
				} else if ('昨日' === type) {
					begin = DateUtil.getLastDate();
					end = begin;
				} else if ('本周' === type) {
					begin = DateUtil.getWeekStartDate();
					end = DateUtil.getCurrentDate();
				} else if ('上周' === type) {
					begin = DateUtil.getLastWeekStartDate();
					end = DateUtil.getLastWeekEndDate();
				} else if ('本月' === type) {
					begin = DateUtil.getMonthDate();
					end = DateUtil.getCurrentDate();
				} else if ('上月' === type) {
					begin = DateUtil.getLastMonthStartDate();
					end = DateUtil.getLastMonthEndDate();
				}
				setDate(begin, end);
				search();
			});
		}
	</script>
	<script id="recordtype_tpl" type="text/html">
		{{each data as option}}
        	<option value="{{option.type}}">{{option.name}}</option>
		{{/each}}
	</script>
</body>
</html>