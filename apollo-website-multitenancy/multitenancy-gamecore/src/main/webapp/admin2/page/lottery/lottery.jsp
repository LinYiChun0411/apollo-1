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
			<div id="search" class="form-inline">
				<label class="sr-only" for="keyword">关键字</label> <label
					class="sr-only" for="methodType">查询类型</label>
				<div class="form-group">
					<div class="input-group">
						<select class="form-control" id="methodType">
							<option value="name">彩种名称</option>
						</select>
					</div>
					<div class="input-group">
						<input type="text" class="form-control" id="keyword"
							placeholder="关键字">
					</div>
				</div>
				<button class="btn btn-primary" onclick="search();">查询</button>
				<button class="btn btn-primary" onclick="newCz();">新增</button>
			</div>
		</div>
		<table id="datagrid_tb"></table>
	</div>
	<!-- 新增 -->
	<div class="modal fade bs-example-modal-lg" id="newModel"
		tabindex="-1" role="dialog" aria-labelledby="editLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="editLabel">新增彩种</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="accountId">
					<table class="table table-bordered table-striped"
						style="clear: both">
						<tbody>
							<tr>
								<td width="15%" class="text-right">彩种名称：</td>
								<td width="35%" class="text-left">
								<input id="nameNew" class="form-control" type="text"/> 
<!-- 								<span id="nameNew"></span> -->
								</td>
								<td width="15%" class="text-right">开盘时间差：</td>
								<td width="35%" class="text-left">
								<input id="agoNew" class="form-control" type="text"/> 
<!-- 								<span id="agoNew"></span> -->
								</td>
							</tr>
							<tr>
								<td width="15%" class="text-right">彩种编码：</td>
								<td width="35%" class="text-left">
								<input id="codeNew" class="form-control" type="text"/> 
<!-- 								<span id="codeNew"></span> -->
								</td>
								<td width="15%" class="text-right">显示时分组：</td>
								<td width="35%" class="text-left">
								<input id="viewGroupNew" class="form-control" type="text"/> 
<!-- 								<span id="viewGroupNew"> -->
								</span>
								</td>
							</tr>
							<tr>
								<td width="15%" class="text-right">球数：</td>
								<td width="35%" class="text-left">
								<input id="ballsNew" class="form-control" type="text"/> 
<!-- 								<span id="ballsNew"></span> -->
								</td>
								<td width="15%" class="text-right">序号：</td>
								<td width="35%" class="text-left">
								<input id="sortNoNew" class="form-control" type="text"/> 
<!-- 								<span id="sortNoNew"></span> -->
								</td>
							</tr>
							<tr>
								<td width="15%" class="text-right">彩种类型：</td>
								<td width="35%" class="text-left">
								<input id="typeNew" class="form-control" type="text"/> 
<!-- 								<span id="typeNew"></span> -->
								</td>
								<td width="15%" class="text-right">彩种状态：</td>
								<td width="35%" class="text-left"><select id="statusNew"
									class="form-control">
										<option value="2">启用</option>
										<option value="1">禁用</option>
								</select></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="save();">保存</button>
				</div>
			</div>
		</div>
	</div>
<!-- 	修改 -->
	<div class="modal fade" id="editmodel"
		tabindex="-1" role="dialog" aria-labelledby="editLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="editLabel">彩种修改</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="accountId">
					<table class="table table-bordered table-striped"
						style="clear: both">
						<tbody>
							<tr>
								<input id="bcId" class="form-control" type="hidden"/> 
								<td width="25%" class="text-right">彩种名称：</td>
								<td width="30%" class="text-left">
								<input id="name" class="form-control" type="text"/> 
<!-- 								<span id="name"></span> -->
								</td>
								<td width="25%" class="text-right">开盘时间差：</td>
								<td width="30%" class="text-left">
								<input id="ago" class="form-control" type="text"/> 
<!-- 								<span id="ago"></span> -->
								</td>
							</tr>
							<tr>
								<td width="25" class="text-right">球数：</td>
								<td width="30%" class="text-left">
								<input id="balls" class="form-control" type="text"/>
<!-- 								<span id="balls"> -->
								</span>
								</td>
								<td width="25" class="text-right">序号：</td>
								<td width="30" class="text-left">
								<input id="sortNo" class="form-control" type="text"/>
<!-- 								<span id="sortNo"> -->
								</span>
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
		})
		
		//新增
		function newCz(){
			$("#newModel").modal('toggle');
			stationList();
		}
		
		function getTab() {
			window.table = new Game.Table({
				id : 'datagrid_tb',
				url : '${base}/admin/lottery/list.do',
				queryParams : queryParams,//参数
				toolbar : $('#toolbar'),
				columns : [ {
					field : 'name',
					title : '彩种名称',
					align : 'center',
					width : '200',
					valign : 'middle',
				}, {
					field : 'ago',
					title : '开封盘时间差(秒)',
					align : 'center',
					width : '180',
					valign : 'bottom'
				}, {
					field : 'code',
					title : '彩种编码',
					align : 'center',
					width : '200',
					valign : 'middle'
				}, {
					field : 'viewGroup',
					title : '显示时分组',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : showSFZ
				}, {
					field : 'balls',
					title : '球数',
					align : 'center',
					width : '200',
					valign : 'middle'
				}, {
					field : 'type',
					title : '彩种类型',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : showCZ
				}, {
					field : 'status',
					title : '彩种状态',
					align : 'center',
					width : '200',
					valign : 'middle',
					events : operateEvents,
					formatter : statusFormatter
				}, {
					field : 'sortNo',
					title : '序号',
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
		//保存
		function save(){
 		var bc = {
 				"name" : $('#nameNew').val(),
				"ago" :$('#agoNew').val(),
				"code" :$('#codeNew').val(),
				"viewGroup" :$('#viewGroupNew').val(),
				"balls" :$('#ballsNew').val(),
				"type" :$('#typeNew').val(),
				"status" :$('#statusNew').val(),
				"sortNo" :$('#sortNoNew').val() 
        	};
			$.ajax({
				url : "${base}/admin/lottery/sava.do",
				data : bc,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
			
		}
		
		function editor(){
			var bc = {
	 				"name" : $('#name').val(),
					"ago" :$('#ago').val(),
					"balls" :$('#balls').val(),
					"sortNo" :$('#sortNo').val(),
					"id" :$('#bcId').val()
	        	};
			$.ajax({
				url : "${base}/admin/lottery/update.do",
				data : bc,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}
		
		//格式化时分组
		function showSFZ(value) {
			switch (value) {
			case 1:
				return '时时彩';
				break;
			case 2:
				return '低频彩';
				break;
			case 3:
				return '快开';
				break;
			case 4:
				return '快三';
				break;
			case 5:
				return '11选5';
				break;
			case 6:
				return '香港彩';
				break;
			}
		}
		//格式化彩种类型
		function showCZ(value) {
			switch (value) {
			case 1:
				return '系统彩';
				break;
			case 2:
				return '时时彩';
				break;
			case 3:
				return 'pk10';
				break;
			case 4:
				return '排列三';
				break;
			case 5:
				return '11选5';
				break;
			case 6:
				return '香港彩';
				break;
			case 7:
				return 'PC蛋蛋';
				break;
			}
		}

		function dateFormatter(value, row, index) {
			return DateUtil.formatDatetime(value);
		}

		function operateFormatter(value, row, index) {
			return [ '<a class="edit" href="javascript:void(0)" title="修改">',
						'<i class="glyphicon glyphicon-pencil"></i>', '</a>  ',
						'<a class="del" href="javascript:void(0)" title="删除">',
					'<i class="glyphicon glyphicon-remove"></i>', '</a>  ']
					.join('');
		}
		
		//状态
		function statusFormatter(value, row, index) {
			if (value === 2) {
				return '<span class="text-success">启用</span><a href="#"><span class="text-danger stateClose">(禁用)</span></a>';
			}
			return '<span class="text-danger">禁用</span><a href="#"><span class="text-success stateOpen">(启用)</span></a>';
		}
		
		window.operateEvents = {
			'click .edit' : function(e, value, row, index) {
								$("#editmodel").modal('toggle');
								$("#name").val(row.name);
								$("#sortNo").val(row.sortNo);
								$("#ago").val(row.ago);
								$("#balls").val(row.balls);
								$("#type").val(row.type);
								$("#status").val(row.status);
								$('#bcId').val(row.id);
			},
			'click .stateOpen' : function(e, value, row, index) {
				open(row);
			},
			'click .stateClose' : function(e, value, row, index) {
				close(row);
			},
			'click .del' : function(e, value, row, index) {
				del(row);
			}
		};

		//设置传入参数
		function queryParams(params) {
			params[$("#methodType").val()] = $("#keyword").val();
			return params
		}
		
		//站点列表
		function stationList() {
			var col='';
			$.ajax({
				url : "${base}/admin/station/list.do",
				success : function(result) {
					for(var j in result.rows){
						console.log(result.rows[j].id+"||"+result.rows[j].name);
						col+='<option value="'+result.rows[j].id+'">'+result.rows[j].name+'</option>';
					}
					$('#stationIdNew').html(col);
				}
			});
		}
		
		function open(row) {
			$.ajax({
				url : "${base}/admin/lottery/open.do",
				data : row,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}
		function close(row) {
			$.ajax({
				url : "${base}/admin/lottery/close.do",
				data : row,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}
		function del(row) {
			$.ajax({
				url : "${base}/admin/lottery/del.do",
				data : row,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}

		function search() {
			$("#datagrid_tb").bootstrapTable('refresh');
		}
	</script>
</body>
</html>
