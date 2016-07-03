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
					<label class="sr-only" for="name">公告名称</label>
					<div class="form-group">
						<div class="input-group">
							<input type="text" class="form-control" id="sname" placeholder="公告名称">
						</div>
						<div class="input-group">
							<label class="sr-only" for="stype">类型</label> <select class="form-control" id="stype">
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
	<div class="modal fade" id="editmodel" tabindex="-1" role="dialog" aria-labelledby="editLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="editLabel">编辑公告</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="ancId">
					<table class="table table-bordered table-striped" style="clear: both">
						<tbody>
							<tr>
								<td width="15%" class="text-right">标题：</td>
								<td width="35%" class="text-left"><input type="text" class="form-control" id="name" /></td>
							</tr>
							<tr>
								<td width="15%" class="text-right">开始时间：</td>
								<td width="35%" class="text-left"><input type="text" class="form-control" id="beginDatetime" /></td>
							</tr>
							<tr>
								<td width="15%" class="text-right">结束时间：</td>
								<td width="35%" class="text-left"><input type="text" class="form-control" id="endDatetime" /></td>
							</tr>
							<tr>
								<td width="15%" class="text-right">类型：</td>
								<td width="35%" class="text-left"><select id="type" class="form-control">
								</select></td>
							</tr>
							<tr>
								<td width="15%" class="text-right">状态：</td>
								<td width="35%" class="text-left"><select id="status" class="form-control">
										<option value="1">未发布</option>
										<option value="2">已发布</option>
								</select></td>
							</tr>
							<tr>
								<td width="15%" class="text-right">内容：</td>
								<td width="35%" class="text-left"><textarea id="content" class="form-control">
								</textarea></td>
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
	<script id="type_tpl" type="text/html">
	{{each data as tl}}
			<option value="{{tl.id}}">{{tl.name}}</option>
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
			var options_datetime = {
				language : 'zh-CN',
				autoclose : true,
				minView : 0,
				endDate : curDate,
				format : 'yyyy-mm-dd hh:mm:ss'
			};
			$('#beginDatetime').datetimepicker(options_datetime);
			$('#endDatetime').datetimepicker(options_datetime);

			window.table = new Game.Table({
				id : 'datagrid_tb',
				url : '${base}/admin/announcement/list.do',
				queryParams : queryParams,//参数
				toolbar : $('#toolbar'),
				columns : [ {
					field : 'name',
					title : '公告名称',
					align : 'center',
					width : '200',
					valign : 'middle',
				}, {
					field : 'creator',
					title : '创建人',
					align : 'center',
					width : '200',
					valign : 'middle'
				}, {
					field : 'type',
					title : '公告类别',
					align : 'center',
					width : '180',
					valign : 'bottom',
					formatter : typeFormatter
				}, {
					field : 'status',
					title : '公告状态',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : statusFormatter
				}, {
					field : 'beginDatetime',
					title : '开始时间',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : dateFormatter
				}, {
					field : 'endDatetime',
					title : '结束时间',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : dateFormatter
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
		function typeFormatter(value, row, index) {

			var sn = GlobalTypeUtil.getTypeName(4, 2, value);
			return sn;
		}

		function statusFormatter(value, row, index) {

			var sn = GlobalTypeUtil.getTypeName(4, 1, value);
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
					'<i class="glyphicon glyphicon-pencil"></i>', '</a>  ',
					'<a class="start" href="javascript:void(0)" title="发布">',
					'<i class="glyphicon glyphicon-ok-circle"></i>', '</a>  ',
					'<a class="stop" href="javascript:void(0)" title="取消发布">',
					'<i class="glyphicon glyphicon-ban-circle"></i>', '</a>',
					'<a class="remove" href="javascript:void(0)" title="删除">',
					'<i class="glyphicon glyphicon-remove"></i>', '</a>' ]
					.join('');
		}

		window.operateEvents = {
			'click .eidt' : function(e, value, row, index) {

				$("#editmodel").modal('toggle');
				$("#ancId").val(row.id);
				$("#name").val(row.name);
				$("#beginDatetime").val(
						DateUtil.formatDatetime(row.beginDatetime));
				$("#endDatetime").val(DateUtil.formatDatetime(row.endDatetime));
				$("#type").val(row.type);
				$("#status").val(row.status);
				$("#content").val(row.content);
			},
			'click .start' : function(e, value, row, index) {
				row.status = 2;
				updstatus(row);
			},
			'click .stop' : function(e, value, row, index) {
				row.status = 1;
				updstatus(row);
			},
			'click .remove' : function(e, value, row, index) {
				remove(row);
			}
		};

		//设置传入参数
		function queryParams(params) {
			params['name'] = $("#sname").val();
			params['type'] = $("#stype").val();
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
			var eachdata = {
				"data" : GlobalTypeUtil.getCombo(4, 2)
			};
			var html = template('type_tpl', eachdata);
			$("#type").append(html);
			$("#stype").append(html);
		}

		function updstatus(row) {
			$.ajax({
				url : "${base}/admin/announcement/updstatus.do",
				data : row,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}

		function remove(row) {
			$.ajax({
				url : "${base}/admin/announcement/delete.do",
				data : row,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}

		function search() {
			$("#datagrid_tb").bootstrapTable('refresh');
		}

		function add() {
			$("#editmodel").modal('toggle');
			$("#ancId").val("");
			$("#name").val("");
			$("#beginDatetime").val("");
			$("#endDatetime").val("");
			$("#type").val("");
			$("#status").val("");
			$("#content").val("");
		}

		function save() {
			var ele = {
				id : $("#ancId").val(),
				name : $("#name").val(),
				type : $("#type").val(),
				status : $("#status").val(),
				beginDatetime : $("#beginDatetime").val(),
				endDatetime : $("#endDatetime").val(),
				content : $("#content").val()
			};
			$.ajax({
				url : '${base}/admin/announcement/save.do',
				data : {
					data : JSON.encode(ele)
				},
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
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
</body>
</html>