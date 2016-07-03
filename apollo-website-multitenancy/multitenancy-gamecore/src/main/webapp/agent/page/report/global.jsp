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
			<div id="search">
				<div class="form-group">
					<div class="form-inline">
						<div class="input-group">
							<input type="text" class="form-control" id="begin" placeholder="开始日期">
							<span class="glyphicon glyphicon-th form-control-feedback" aria-hidden="true"></span>
						</div>
						<button class="btn btn-default">今日</button>
						<button class="btn btn-default">昨日</button>
						<button class="btn btn-default">本周</button>
						<div class="input-group">
							<label class="sr-only" for="agent">代理账号</label> <input type="text" class="form-control" id="agent" placeholder="代理账号">
						</div>
						<div class="input-group">
							<label class="sr-only" for="account">会员账号</label> <input type="text" class="form-control" id="account" placeholder="会员账号">
						</div>
						<button class="btn btn-primary" onclick="search();">查询</button>
						<button class="btn btn-primary" onclick="reset();">重置</button>
					</div>
					<div class="form-inline" style="margin-top: 5px;">
						<div class="input-group">
							<input type="text" id="end" class="form-control" placeholder="线束日期">
							<span class="glyphicon glyphicon-th form-control-feedback" aria-hidden="true"></span>
						</div>
						<button class="btn btn-default">上周</button>
						<button class="btn btn-default">本月</button>
						<button class="btn btn-default">上月</button>
					</div>
				</div>
			</div>
		</div>
		<br>
		<table id="datagrid_tb_base"></table>
		<br>
		<table id="datagrid_tb_self"></table>
		<br>
		<table id="datagrid_tb_other"></table>
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

			$("#datagrid_tb_base").bootstrapTable({
				striped : true,
				columns : [ {
					field : 'id',
					title : '新会员',
					align : 'center',
					width : '180',
					valign : 'bottom'
				}, {
					field : 'account',
					title : '存款总计',
					align : 'center',
					width : '200',
					valign : 'middle',
				}, {
					field : 'money',
					title : '提款总计',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : moneyFormatter
				}, {
					field : 'type',
					title : '反水总计',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : typeFormatter
				}, {
					field : 'afterMoney',
					title : '赠送总计',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : moneyFormatter
				}, {
					field : 'createDatetime',
					title : '手动加款',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : dateFormatter
				}, {
					field : 'remark',
					title : '手动扣款',
					align : 'center',
					width : '200',
					valign : 'middle'
				} ]
			});

			$("#datagrid_tb_self").bootstrapTable({
				striped : true,
				columns : [ {
					field : 'id',
					title : '体育下注总计',
					align : 'center',
					width : '180',
					valign : 'bottom'
				}, {
					field : 'account',
					title : '彩票下注总记',
					align : 'center',
					width : '200',
					valign : 'middle',
				}, {
					field : 'money',
					title : '真人下注总计',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : moneyFormatter
				} ]
			});

			$("#datagrid_tb_other").bootstrapTable({
				striped : true,
				columns : [ {
					field : 'id',
					title : '体育输赢',
					align : 'center',
					width : '180',
					valign : 'bottom'
				}, {
					field : 'account',
					title : '彩票输赢',
					align : 'center',
					width : '200',
					valign : 'middle',
				}, {
					field : 'money',
					title : '真人输赢',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : moneyFormatter
				}, {
					field : 'type',
					title : '全部输赢总计',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : typeFormatter
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
			params['agent'] = $("#agent").val();
			return params
		}
		
		$(function() {
			getTab();
			
			bindbtn();
		})

		function search() {
			$("#datagrid_tb").bootstrapTable('refresh');
		}
		
		function reset() {
			var cur =DateUtil.getCurrentDate();
			$("#account").val("");
			$("#agent").val("");
			setDate(cur,cur);
		}
		
		function setDate(begin,end){
			$('#begin').val(begin);
			$('#end').val(end);
		}
		
		function bindbtn(){
			$(".btn-default").click(function(){
				var type = $(this).html();
				var begin = "";
				var end = "";
				if('今日'===type){
					begin = DateUtil.getCurrentDate();
					end = begin;
				}else if('昨日'===type){
					begin = DateUtil.getLastDate();
					end = begin;
				}else if('本周'===type){
					begin = DateUtil.getWeekStartDate();
					end = DateUtil.getCurrentDate();
				}else if('上周'===type){
					begin = DateUtil.getLastWeekStartDate();
					end = DateUtil.getLastWeekEndDate();
				}else if('本月'===type){
					begin = DateUtil.getMonthDate();
					end = DateUtil.getCurrentDate();
				}else if('上月'===type){
					begin = DateUtil.getLastMonthStartDate();
					end = DateUtil.getLastMonthEndDate();
				}
				setDate(begin,end);
			});
		}
	</script>
</body>
</html>