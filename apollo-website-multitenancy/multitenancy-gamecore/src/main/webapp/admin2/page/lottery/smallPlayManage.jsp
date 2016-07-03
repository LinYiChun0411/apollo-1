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
			<div id="search" class="form-inline">
				<label class="sr-only" for="keyword">关键字</label> <label class="sr-only" for="methodType">查询类型</label>
				<div class="form-group">
					<div class="input-group">
						<select class="form-control" id="methodType">
							<option value="name">玩法小类名称</option>
						</select>
					</div>
					<div class="input-group">
						<select class="form-control" id="methodType2">
							<option value="0">状态</option>
							<option value="2">启用</option>
							<option value="1">停用</option>
						</select>
					</div>
					<div class="input-group">
						<input type="text" class="form-control" id="keyword" placeholder="关键字">
					</div>
				</div>
				<button class="btn btn-primary" onclick="search();">查询</button>
			</div>
		</div>
		<table id="datagrid_tb"></table>
	</div>
	
	
	<script type="text/javascript">
		$(function() {
			getTab();
		})
		function getTab() {
			window.table = new Game.Table({
				id : 'datagrid_tb',
				url : '${base}/admin/lotType/list.do',
				queryParams : queryParams,//参数
				status : $('#methodType2').val(),
				toolbar : $('#toolbar'),
				columns : [ {
					field : 'name',
					title : '玩法小类名称',
					align : 'center',
					width : '200',
					valign : 'middle'
				}, {
					field : 'maxBonusOdds',
					title : '最大中奖金额(元)',
					align : 'center',
					width : '200',
					valign : 'bottom'
				}, {
					field : 'minBonusOdds',
					title : '最小中奖金额(元)',
					align : 'center',
					width : '200',
					valign : 'middle'
				}, {
					field : 'rakeback',
					title : '返水',
					align : 'center',
					width : '200',
					valign : 'middle'
				}, {
					field : 'status',
					title : '状态',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : statusFormatter
				}, {
					field : 'maxNumber',
					title : '最高注数',
					align : 'center',
					width : '200',
					valign : 'middle'
				}, {
					field : 'sortNo',
					title : '序号',
					align : 'center',
					width : '200',
					valign : 'middle'
				},
// 				 {
// 					field : 'detailDesc',
// 					title : '详细介绍',
// 					align : 'center',
// 					width : '300',
// 					valign : 'middle'
// 				}, {
// 					field : 'winExample',
// 					title : '中奖范例',
// 					align : 'center',
// 					width : '300',
// 					valign : 'middle'
// 				}, {
// 					field : 'playMethod',
// 					title : '玩法介绍',
// 					align : 'center',
// 					width : '300',
// 					valign : 'middle'
// 				},
				{
					field : 'code',
					title : '玩法编码',
					align : 'center',
					width : '300',
					valign : 'middle'
				}, {
					title : '操作',
					align : 'center',
					width : '50',
					valign : 'middle',
					events : operateEvents,
					formatter : operateFormatter
				} ]
			});
		}

		//状态
		function statusFormatter(value, row, index) {

			var sn = GlobalTypeUtil.getTypeName(2, 1, value);
			if (value === 2) {
				return [ '<span class="text-success">', '</span>' ].join(sn);
			}
			return [ '<span class="text-danger">', '</span>' ].join(sn);
		}
		
		function statusFormatter(value, row, index) {

			var sn = GlobalTypeUtil.getTypeName(2, 1, value);
			if (value === 2) {
				return [ '<span class="text-success"></span>' ].join(sn);
			}
			return [ '<span class="text-danger"></span>' ].join(sn);
		}

		function dateFormatter(value, row, index) {
			return DateUtil.formatDatetime(value);
		}

		function operateFormatter(value, row, index) {
			return [ '<a class="eidt" href="javascript:void(0)" title="修改">',
					'<i class="glyphicon glyphicon-pencil"></i>', '</a>  ' ]
					.join('');
		}

		//格式化彩票类型
		function cpType(value) {
			switch (value) {
			case '1':
				return '系统彩';
				break;
			case '2':
				return '时时彩';
				break;
			case '3':
				return 'pk10';
				break;
			case '4':
				return '11选5';
				break;
			case '5':
				return '排列三';
				break;
			}
		}
		
		//状态
		function statusFormatter(value, row, index) {

			var sn = GlobalTypeUtil.getTypeName(2, 1, value);
			if (value === 2) {
				return [ '<span class="text-success">', '</span>' ].join(sn);
			}
			return [ '<span class="text-danger">', '</span>' ].join(sn);
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

		//设置传入参数
		function queryParams(params) {
			params[$("#methodType").val()] = $("#keyword").val();
			return params
// 			return $("#keyword").val()+"_"+$('#methodType2').val();
		}

		function search() {
			$("#datagrid_tb").bootstrapTable('refresh');
		}
	</script>
</body>
</html>
