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
		<table id="datagrid_tb"></table>
	</div>
	<div class="modal fade" id="editmodel" tabindex="-1" role="dialog" aria-labelledby="editLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="editLabel">修改</h4>
				</div>
				<div class="modal-body">
					<input id="tsgId" type="hidden"> <input id="thirdPlatformId" type="hidden"> <input id="status" type="hidden">
					<div class="form-group">
						<label for="custom-name" class="control-label">自定义名称:</label> <input type="text" class="form-control" id="custom-name" />
					</div>
					<div class="form-group">
						<label for="order-no" class="control-label">排序码:</label> <input type="text" class="form-control" id="order-no" />
					</div>
					<div class="form-group">
						<label for="remark-text" class="control-label">备注:</label>
						<textarea class="form-control" id="remark-text"></textarea>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="save();">保存</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function getTab() {
			window.table = new Game.Table({
				id : 'datagrid_tb',
				url : '${base}/agent/system/thirdplat/list.do',
				queryParams : queryParams,//参数
				columns : [ {
					field : 'thirdName',
					title : '第三方平台名称',
					align : 'center',
					width : '180',
					valign : 'bottom',
					editable : true
				}, {
					field : 'name',
					title : '自定义名称',
					align : 'center',
					width : '200',
					valign : 'middle',
				}, {
					field : 'status',
					title : '状态',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : statusFormatter
				}, {
					field : 'orderNo',
					title : '排序码',
					align : 'center',
					width : '200',
					valign : 'middle'
				}, {
					field : 'remark',
					title : '备注',
					align : 'center',
					width : '200',
					valign : 'middle'
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
			var sn = GlobalTypeUtil.getTypeName(2,1,value);
			if (value === 2) {
				return [ '<span class="text-success">', '</span>' ].join(sn);
			}
			return [ '<span class="text-danger">', '</span>' ].join(sn);
		}

		function operateFormatter(value, row, index) {
			return [ '<a class="eidt" href="javascript:void(0)" title="修改">',
					'<i class="glyphicon glyphicon-pencil"></i>', '</a>  ',
					'<a class="start" href="javascript:void(0)" title="启用">',
					'<i class="glyphicon glyphicon-ok-circle"></i>', '</a>  ',
					'<a class="stop" href="javascript:void(0)" title="停用">',
					'<i class="glyphicon glyphicon-ban-circle"></i>', '</a>' ]
					.join('');
		}

		window.operateEvents = {
			'click .eidt' : function(e, value, row, index) {

				$("#editmodel").modal('toggle');
				$("#custom-name").val(row.name);
				$("#remark-text").val(row.remark);
				$("#order-no").val(row.orderNo);
				$("#tsgId").val(row.id);
				$("#thirdPlatformId").val(row.thirdPlatformId);
				$("#status").val(row.status);
			},
			'click .start' : function(e, value, row, index) {
				row.status = 2;
				updstatus(row);
			},
			'click .stop' : function(e, value, row, index) {
				row.status = 1;
				updstatus(row);
			}
		};

		//设置传入参数
		function queryParams(params) {
			return params
		}
		$(function() {
			getTab();
		})

		function updstatus(row) {
			$.ajax({
				url : "${base}/agent/system/thirdplat/save.do",
				data : row,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}

		function save() {
			$.ajax({
				url : "${base}/agent/system/thirdplat/save.do",
				data : {
					id : $("#tsgId").val(),
					thirdPlatformId : $("#thirdPlatformId").val(),
					name : $("#custom-name").val(),
					remark : $("#remark-text").val(),
					orderNo : $("#order-no").val(),
					status : $("#status").val()
				},
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}
	</script>
</body>
</html>