<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include/base.jsp"%>
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
	<jsp:include page="/admin2/include/adminmenu.jsp"></jsp:include>
		<div class="container">
		<div id="toolbar">
<!-- 			<div id="search" class="form-inline"> -->
<!-- 				<label class="sr-only" for="keyword">关键字</label> <label class="sr-only" for="methodType">查询类型</label> -->
<!-- 				<div class="form-group"> -->
<!-- 					<div class="input-group"> -->
<!-- 						<select class="form-control" id="methodType"> -->
<!-- 							<option value="qihao">期号</option> -->
<!-- 							<option value="account">彩票编码</option> -->
<!-- 						</select> -->
<!-- 					</div> -->
<!-- 					<div class="input-group"> -->
<!-- 					开奖时间: -->
<!-- 					<input type="text" id="openTime" data-date-format="yyyy-mm-dd hh:ii"> -->
<!-- 					</div> -->
<!-- 					<div class="input-group"> -->
<!-- 					开盘时间: -->
<!-- 					<input type="text" id="startTime" data-date-format="yyyy-mm-dd hh:ii"> -->
<!-- 					</div> -->
<!-- 					<div class="input-group"> -->
<!-- 						<input type="text" class="form-control" id="keyword" placeholder="关键字"> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<button class="btn btn-primary" onclick="search();">查询</button> -->
<!-- 			</div> -->
		</div>
		<table id="datagrid_tb"></table>
	</div>
	
		<script type="text/javascript">
		$(function() {
			getTab();
			$('#openTime').datetimepicker();
			$('#startTime').datetimepicker();
		})
		
		function getTab() {
			window.table = new Game.Table({
				id : 'datagrid_tb',
				url : '${base}/admin/lotteryData/list.do',
				queryParams : queryParams,//参数
				toolbar : $('#toolbar'),
				columns : [ {
					field : 'lotCode',
					title : '彩票编码',
					align : 'center',
					width : '200',
					valign : 'middle',
				}, {
					field : 'qiHao',
					title : '期号',
					align : 'center',
					width : '180',
					valign : 'bottom'
				}, {
					field : 'haoMa',
					title : '号码',
					align : 'center',
					width : '200',
					valign : 'middle'
				}, 
				{
					field : 'ommit',
					title : '遗漏',
					align : 'center',
					width : '200',
					valign : 'middle'
				}, {
					field : 'openStatus',
					title : '开盘状态',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : openState
				}, {
					field : 'openTime',
					title : '开奖时间',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : dateFormatter
				}, 
// 				{
// 					field : 'stationId',
// 					title : '站点',
// 					align : 'center',
// 					width : '200',
// 					valign : 'middle'
// 				}, 
				{
					title : '操作',
					align : 'center',
					width : '50',
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

		function dateFormatter(value, row, index) {
			return DateUtil.formatDatetime(value);
		}

		function operateFormatter(value, row, index) {
			return [ '<a class="eidt" href="javascript:void(0)" title="修改">',
					'<i class="glyphicon glyphicon-pencil"></i>', '</a>  ' ]
					.join('');
		}

		window.operateEvents = {
			'click .eidt' : function(e, value, row, index) {
// 								$("#editmodel").modal('toggle');
// 								$("#name").html(row.name);
// 								$$("#ago").html(row.ago);
// 								$("#code").html(row.code);
// 								$("#viewGroup").html(row.viewGroup);
// 								$("#balls").html(row.balls);
// 								$("#stationId").html(row.stationId);
// 								$("#type").html(row.type);
// 								$("#status").html(row.status);
// 								$("#sortNo").html(row.sortNo);

			},
			'click .start' : function(e, value, row, index) {
				row.stationId = row.id;
				open(row);
			},
			'click .stop' : function(e, value, row, index) {
				row.stationId = row.id;
				close(row);
			}
		};
		//开奖状态
		function openState(value){
			switch (value) {
			case 1:
				return '未开奖';
				break;
			case 2:
				return '已开奖';
				break;
			case 3:
				return '未开奖完';
				break;
			case 4:
				return '已经取消';
				break;
			case 5:
				return '已经回滚';
				break;
			}
		}
		
		//设置传入参数
		function queryParams(params) {
// 			params[$("#methodType").val()] = $("#keyword").val();
			return params
		}

		function search() {
			$("#datagrid_tb").bootstrapTable('refresh');
		}
	</script>
</body>
</html>
