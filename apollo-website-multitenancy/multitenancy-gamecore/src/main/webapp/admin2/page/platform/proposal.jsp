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
	<jsp:include page="/admin2/include/adminmenu.jsp"></jsp:include>
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
					<label class="sr-only" for="name">标题</label>
					<div class="form-group">
						<div class="input-group">
							<input type="text" class="form-control" id="sname" placeholder="标题">
						</div>
						<div class="input-group">
							<label class="sr-only" for="stype">类型</label> <select class="form-control" id="sstationId">
								<option value="0">全部平台</option>
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
	<div class="modal fade" id="editmodel" tabindex="-1" role="dialog" aria-labelledby="editLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="editLabel">投诉建议详情</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="ancId">
					<table class="table table-bordered table-striped" style="clear: both">
						<tbody>
							<tr>
								<td width="15%" class="text-right">标题：</td>
								<td width="35%" class="text-left"><input type="text" class="form-control" id="name" disabled="disabled" /></td>
							</tr>
							<tr>
								<td width="15%" class="text-right">所属平台：</td>
								<td width="35%" class="text-left"><select id="stationId" class="form-control" disabled="disabled">
								</select></td>
							</tr>
							<tr>
								<td width="15%" class="text-right">QQ：</td>
								<td width="35%" class="text-left"><input type="text" class="form-control" id="qq" disabled="disabled" /></td>
							</tr>
							<tr>
								<td width="15%" class="text-right">手机：</td>
								<td width="35%" class="text-left"><input type="text" class="form-control" id="phone" disabled="disabled" /></td>
							</tr>
							<tr>
								<td width="15%" class="text-right">内容：</td>
								<td width="35%" class="text-left"><textarea id="content" class="form-control" disabled="disabled">
								</textarea></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<script id="station_tpl" type="text/html">
	{{each data as st}}
			<option value="{{st.id}}">{{st.name}}</option>
	{{/each}}
	</script>
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
				url : '${base}/admin/proposal/list.do',
				queryParams : queryParams,//参数
				toolbar : $('#toolbar'),
				columns : [ {
					field : 'name',
					title : '标题',
					align : 'center',
					width : '200',
					valign : 'middle',
				}, {
					field : 'creator',
					title : '账号',
					align : 'center',
					width : '200',
					valign : 'middle'
				}, {
					field : 'stationName',
					title : '所属平台',
					align : 'center',
					width : '180',
					valign : 'bottom'
				}, {
					field : 'qq',
					title : 'QQ',
					align : 'center',
					width : '180',
					valign : 'bottom'
				}, {
					field : 'phone',
					title : '手机',
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
					field : 'createDatetime',
					title : '投诉/建议时间',
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

			var sn = GlobalTypeUtil.getTypeName(5, 1, value);
			if (value === 2) {
				return [ '<span class="text-success">', '</span>' ].join(sn);
			}
			return [ '<span class="text-danger">', '</span>' ].join(sn);
		}

		function dateFormatter(value, row, index) {
			return DateUtil.formatDatetime(value);
		}

		function operateFormatter(value, row, index) {
			return [ '<a class="detail" href="javascript:void(0)" title="查看">',
					'<i class="glyphicon glyphicon-eye-open"></i>', '</a>',
					'<a class="remove" href="javascript:void(0)" title="删除">',
					'<i class="glyphicon glyphicon-remove"></i>', '</a>' ]
					.join('');
		}

		window.operateEvents = {
			'click .detail' : function(e, value, row, index) {

				$("#editmodel").modal('toggle');
				$("#name").val(row.name);
				$("#stationId").val(row.stationId);
				$("#qq").val(row.qq);
				$("#phone").val(row.phone);
				$("#content").val(row.content);
				read(row);
			},
			'click .remove' : function(e, value, row, index) {
				remove(row);
			}
		};

		//设置传入参数
		function queryParams(params) {
			params['name'] = $("#sname").val();
			params['stationId'] = $("#sstationId").val();
			params['beginDatetime'] = $("#begin").val();
			params['endDatetime'] = $("#end").val();
			return params
		}
		$(function() {
			getTab();
			bindbtn();
			initCombo();
		})

		function initCombo() {
			$.ajax({
				url : "${base}/admin/station/combo.do",
				success : function(result) {
					var eachdata = {
						"data" : result
					};
					var html = template('station_tpl', eachdata);
					$("#stationId").append(html);
					$("#sstationId").append(html);
				}
			});

		}
		function read(row) {
			$.ajax({
				url : "${base}/admin/proposal/read.do",
				data : {
					proposalId : row.id
				},
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}

		function remove(row) {
			$.ajax({
				url : "${base}/admin/proposal/delete.do",
				data : {
					proposalId : row.id
				},
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}

		function search() {
			$("#datagrid_tb").bootstrapTable('refresh');
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
</body>
</html>