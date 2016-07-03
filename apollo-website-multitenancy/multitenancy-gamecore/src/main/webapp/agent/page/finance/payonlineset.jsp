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
							<label class="sr-only" for="account">操作员</label> <select class="form-control" id="account">
								<option value="0">选择操作员</option>
							</select>
						</div>
						<div class="input-group">
							<label class="sr-only" for="type">搜索类型</label> <select class="form-control" id="type">
								<option value="0">选择搜索类型</option>
							</select>
						</div>
						<div class="input-group">
							<input type="text" class="form-control" id="keyword">
						</div>

					</div>
					<div class="input-group">
						<input type="text" class="form-control" id="begin" placeholder="开始日期"> <span class="glyphicon glyphicon-th form-control-feedback" aria-hidden="true"></span>
					</div>
					<div class="input-group">
						<input type="text" id="end" class="form-control" placeholder="线束日期"> <span class="glyphicon glyphicon-th form-control-feedback" aria-hidden="true"></span>
					</div>
					<button class="btn btn-primary" onclick="search();">查询</button>
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
				url : '${base}/agent/system/oplog/list.do',
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
					title : '账号',
					align : 'center',
					width : '140px',
					valign : 'middle',
				}, {
					field : 'remark',
					title : '操作记录',
					align : 'center',
					valign : 'middle',
					formatter : remarkFormatter
				}, {
					field : 'createDatetime',
					title : '操作时间',
					align : 'center',
					width : '200px',
					valign : 'middle',
					formatter : dateFormatter
				} ]
			});
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