<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>${_title}</title>
<meta charset="utf-8">
</head>
<body>
	<jsp:include page="/admin2/include/adminmenu.jsp"></jsp:include>
		<div class="container">
		<div id="toolbar">
			<input type="hidden" id="lottype">
		</div>
		<ul class="nav nav-tabs czGroup">
      		<li class="active">
     	 		<a href="#" data-toggle="tab" class="lotName" id="1">系统彩</a>
     	 	</li>
      		<li>
      			<a href="#" data-toggle="tab" class="lotName" id="2">时时彩</a>
      		</li>
      		<li>
      			<a href="#" data-toggle="tab" class="lotName" id="3">pk10</a>
      		</li>
      		<li>
      			<a href="#" data-toggle="tab" class="lotName" id="4">排列三</a>
      		</li>
      		<li>
      			<a href="#" data-toggle="tab" class="lotName" id="5">11选5</a>
      		</li>
      		<li>
      			<a href="#" data-toggle="tab" class="lotName" id="6">香港彩</a>
      		</li>
      		<li>
      			<a href="#" data-toggle="tab" class="lotName" id="7">PC蛋蛋</a>
      		</li>
    	</ul>
		<table id="datagrid_tb"></table>
	</div>
	
	
	<div class="modal fade" id="editmodel"
		tabindex="-1" role="dialog" aria-labelledby="editLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="editLabel">玩法大类修改</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="playId">
					<table class="table table-bordered table-striped"
						style="clear: both">
						<tbody>
							<tr>
								<td width="25%" class="text-right">玩法分组名称：</td>
								<td width="25%" class="text-left">
								<input id="fzName" class="form-control" type="text"/> 
								</td>
								<td width="25%" class="text-right">序号：</td>
								<td width="25%" class="text-left">
								<input id="fzSortNo" class="form-control" type="text"/> 
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal" 
						onclick="editor();">保存</button>
						
				</div>
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript">
		$(function() {
			getTab();
			$('.lotName').click(function(){
				$("#lottype").val(this.id);
				$("#datagrid_tb").bootstrapTable('refresh');
			});
		})
		function getTab() {
			window.table = new Game.Table({
				id : 'datagrid_tb',
				url : '${base}/admin/lotTypeGroup/list.do',
				queryParams : queryParams,//参数
				toolbar : $('#toolbar'),
				columns : [ {
					field : 'lotType',
					title : '彩票类型',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : cpType
				}, {
					field : 'name',
					title : '玩法分组名称',
					align : 'center',
					width : '180',
					valign : 'bottom'
				}, {
					field : 'sortNo',
					title : '序号',
					align : 'center',
					width : '200',
					valign : 'middle'
				}, {
					field : 'status',
					title : '状态',
					align : 'center',
					width : '200',
					valign : 'middle',
					events : operateEvents,
					formatter : statusFormatter
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
			case '6':
				return '香港彩';
				break;
			case '7':
				return 'PC蛋蛋';
				break;
			}
		}
		
		//状态
		function statusFormatter(value, row, index) {
			if (value === 2) {
				return '<span class="text-success">启用</span><a href="#"><span class="text-danger stateClose">(禁用)</span></a>';
			}
				return '<span class="text-danger">禁用</span><a href="#"><span class="text-success stateOpen">(启用)</span></a>';
		}
		
		window.operateEvents = {
				'click .eidt' : function(e, value, row, index) {

					$("#editmodel").modal('toggle');
					$("#fzName").val(row.name);
					$("#fzSortNo").val(row.sortNo);
					$("#playId").val(row.id);
				},
				'click .stateOpen' : function(e, value, row, index) {
					closeOrOpen(row);
				},
				'click .stateClose' : function(e, value, row, index) {
					closeOrOpen(row);
				}
			};
		
		function editor(){
			var bc = {
	 				"name" : $('#fzName').val(),
					"id" :$('#playId').val(),
					"sortNo" :$('#fzSortNo').val() 
	        	};
				$.ajax({
					url : "${base}/admin/lotTypeGroup/update.do",
					data : bc,
					success : function(result) {
						$("#datagrid_tb").bootstrapTable('refresh');
					}
				});
		}
		
		function closeOrOpen(row){
			$.ajax({
				url : "${base}/admin/lotTypeGroup/closeOrOpen.do",
				data : row,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}
		
		//设置传入参数
		function queryParams(params) {
			params['lotType'] = $("#lottype").val();
			return params
		}

		function search() {
			$("#datagrid_tb").bootstrapTable('refresh');
		}
	</script>
</body>
</html>
