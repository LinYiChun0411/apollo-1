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
		<input id="keyVal" type="hidden" value="-1"/> 
			<div id="search" class="form-inline">
				<label class="sr-only" for="keyword">关键字</label> <label class="sr-only" for="methodType">查询类型</label>
					<div class="input-group">
						<span class="btn btn-primary" onclick="addMode();">新增彩种</span>
					</div>
				<div class="form-group">
					<div class="input-group">
						<select class="form-control" id="methodType">
						</select>
					</div>
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
					<h4 class="modal-title" id="editLabel">彩种编辑</h4>
				</div>
				<div class="modal-body">
					<input id="bcId" class="form-control" type="hidden"/> 
					<table class="table table-bordered table-striped" style="clear: both">
						<tbody>
							<tr>
								<td width="15%" class="text-right">彩种名称：</td>
								<td width="35%" class="text-left">
								<input type="text" class="form-control" id="name" />
								</td>
							</tr>
							<tr>
								<td width="15%" class="text-right">开封盘时间差(秒)：</td>
								<td width="35%" class="text-left">
								<input type="text" class="form-control" id="ago" />
								</td>
							</tr>
							<tr>
								<td width="15%" class="text-right">显示时分组：</td>
								<td width="35%" class="text-left">
								<select id="viewGroup" class="form-control">
								<option value="1">时时彩</option>
								<option value="2">低频彩</option>
								<option value="3">快开</option>
								<option value="4">快三</option>
								<option value="5">11选5</option>
								<option value="6">香港彩</option>
								</select>
								</td>
							</tr>
							<tr>
								<td width="15%" class="text-right">序号：</td>
								<td width="35%" class="text-left"><input type="text" class="form-control" id="sortNo" /></td>
							</tr>
							<tr>
								<td width="15%" class="text-right">状态：</td>
								<td width="35%" class="text-left">
								<select id="status" class="form-control">
										<option value="1">禁用</option>
										<option value="2">启用</option>
								</select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="editor();">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 新增 -->
	<div class="modal fade" id="newModel" tabindex="-1" role="dialog" aria-labelledby="editLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="editLabel">新增站点彩种</h4>
				</div>
				<div class="modal-body">
					<input id="stationIdVal" class="form-control" value="1" type="hidden"/> 
					<table class="table table-bordered table-striped" style="clear: both">
						<tbody>
							<tr>
								<td width="15%" class="text-right">站点名称：</td>
								<td width="35%" class="text-left">
								<select id="stationName" class="form-control">
								</select>
							</td>
							</tr>
						</tbody>
					</table>
					<table class="table table-bordered table-striped" style="clear: both">
						<tbody class="czNameList">
						
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
	<script type="text/javascript">
	function getTab() {
		window.table = new Game.Table({
			id : 'datagrid_tb',
			url : '${base}/admin/lottery/list2.do',
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
				field : 'sortNo',
				title : '序号',
				align : 'center',
				width : '200',
				valign : 'middle'
			}, {
				field : 'status',
				title : '彩种状态',
				align : 'center',
				width : '200',
				valign : 'middle',
				events : operateEvents,
				formatter : statusFormatter
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

		function operateFormatter(value, row, index) {
			return [ '<a class="eidt" href="javascript:void(0)" title="修改">',
					'<i class="glyphicon glyphicon-pencil"></i>', '</a>  ',
					'<a class="del" href="javascript:void(0)" title="删除">',
					'<i class="glyphicon glyphicon-remove"></i>', '</a>' ]
					.join('');
		}

		window.operateEvents = {
			'click .eidt' : function(e, value, row, index) {

				$("#editmodel").modal('toggle');
				$("#name").val(row.name);
				$("#sortNo").val(row.sortNo);
				beSelect("viewGroup",showSFZSelect(showSFZ(row.viewGroup)));
				$("#ago").val(row.ago);
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
			params['stationId'] = $("#keyVal").val();
			return params
		}
		
		$(function() {
			getTab();
			stationList(null);
			
			$("#methodType").change(function() {
			var curVal=$(this).find("option:checked").attr("value");
			$("#keyVal").val(curVal);
			refresh();
			});
			
			//新增站点下拉框
			$('#stationName').change(function() {
				var curVal=$(this).find("option:checked").attr("value");
				$('#stationIdVal').val(curVal);
				intCheckBox();
				czStationList2(curVal);
				});
			
		})
		
		//点击新增站点彩种
		function addMode(){
			czStationList();
			$("#newModel").modal('toggle');
			czStationList2(1);
			stationList('stationName');
			
		}
		
		//站点列表
		function stationList(attrId) {
			var url = '${base}/admin/station/list.do';
			$.ajax({
				url : url,
				success : function(j) {
					var	col='';
					for(var i in j.rows){
						col+='<option value="'+j.rows[i].id+'">'+j.rows[i].name+'('+j.rows[i].floder+')</option>';
					}
					if(attrId==null){
						$('#methodType').html(col);
					}else{
						$('#'+attrId).html(col);
					}
					
				}
			});
		}
		
		//站点彩种列表
		function czStationList() {
			var url =  "${base}/admin/lottery/list2.do";
			$.ajax({
				url : url,
				success : function(j) {
					var col='';
					for(var i in j.rows){
							if(i%5==0){
								col+='<div class="checkbox">';
								col+='<label class="checkbox-inline">';
								col+='<input type="checkbox" id="'+j.rows[i].code+'" class="czFxk" value="'+j.rows[i].id+'">'+j.rows[i].name+'</label>';
							}else{
								col+='<label class="checkbox-inline">';
								col+='<input type="checkbox" id="'+j.rows[i].code+'" class="czFxk" value="'+j.rows[i].id+'">'+j.rows[i].name+'</label>';
							}
					}
					$('.czNameList').html(col);
				}
			
			});
		}
		
		function czStationList2(param) {
			var url =  "${base}/admin/lottery/list2.do";
			if(param!=null){
				url = url+'?stationId='+param;
			}
			$.ajax({
				url : url,
				success : function(j) {
					var col='';
					for(var i in j.rows){
					isOpen('czFxk',j.rows[i].code,j.rows[i].status);
					}
				}
			});
		}

		function intCheckBox(){
			var obj = $('.czFxk');
			for(var i=0;i<obj.length;i++){
				obj[i].checked = false;
			}
		}
		
		function search() {
			$("#datagrid_tb").bootstrapTable('refresh');
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
		function editor(){
			var bc = {
	 				"name" : $('#name').val(),
					"ago" :$('#ago').val(),
					"balls" :$('#balls').val(),
					"sortNo" :$('#sortNo').val(),
					"viewGroup" :$('#viewGroup').val(),
					"status" :$('#status').val(),
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
		
		//新增保存
		function save(){
			var obj = $('.czFxk');
			var param = '';
			var code = '';
			for(var i=0;i<obj.length;i++){
				if(obj[i].checked){
					param+=obj[i].value+',';
					code+=obj[i].id+',';
				}
			}
			var bc = {
	 				"bcId" : param,
	 				"code" : code,
					"stationId" :$('#stationName').val()
	        	};
			$.ajax({
				url : "${base}/admin/lottery/saveCheckBox.do",
				data : bc,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}
		
	</script>
</body>
</html>
