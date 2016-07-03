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
	<input type="hidden" id="lottype" value="CQSSC">
		<div class="container">
		<ul class="nav nav-tabs" id="czGroup"></ul>
		<table id="datagrid_tb"></table>
	</div>
	
	<script type="text/javascript">
		$(function() {
			czGroup();
			getTab();
		})
		function getTab() {
			window.table = new Game.Table({
				id : 'datagrid_tb',
				url : '${base}/admin/lotteryTime/list.do',
				queryParams : queryParams,//参数
				toolbar : $('#toolbar'),
				columns : [ {
					field : 'lotCode',
					title : '彩票编码',
					align : 'center',
					width : '200',
					valign : 'middle',
				}, {
					field : 'actionNo',
					title : '期号',
					align : 'center',
					width : '180',
					valign : 'bottom'
				}, {
					field : 'actionTime',
					title : '开奖时间',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : dateFormatter
				}
				, {
					title : '操作',
					align : 'center',
					width : '50',
					valign : 'middle',
					events : operateEvents,
					formatter : operateFormatter
				} 
				]
			});
		}
		
		function operateFormatter(value, row, index) {
			return [ '<a class="edit" href="javascript:void(0)" id="'+row.id+'" title="修改">',
						'<i class="glyphicon glyphicon-pencil"></i>修改','</a>  '].join('');
		}
		
		window.operateEvents = {
				'click .edit' : function(e, value, row, index) {
					var timeVal = $('#date_'+this.id).val();
					var openId =this.id;
					openTime(timeVal,openId);
				}
			};
		
		
		function czGroup(){
			$.ajax({
				url:"${base}/admin/lottery/czGroup.do",
				data:"GET",
				DataType:"json",
				success:function(j){
					var col = '';
					for(var i in j){
						if(j[i].code=='CQSSC'){
		 					col+='<li class="active"><a href="#" data-toggle="tab" class="lotName" id="'+j[i].code+'">'+j[i].name+'</a></li>';
						}else{
							col+='<li><a href="#" data-toggle="tab" class="lotName" id="'+j[i].code+'">'+j[i].name+'</a></li>';
						}
					}
					$('#czGroup').html(col);
					$('.lotName').click(function(){
		 				$("#lottype").val(this.id);
		 				$("#datagrid_tb").bootstrapTable('refresh');
					});
				}
					
			});
		}
		
		function openTime(timeVal,openId){
			var bc = {
					"timeVal" :timeVal,
					"openId" :openId 
	        	};
			if(timeVal!=""&&openId!=""){
				$.ajax({
					url : "${base}/admin/lotteryTime/updateTime.do",
					data : bc,
					success : function(result) {
						alert('修改成功');
						$("#datagrid_tb").bootstrapTable('refresh');
					}
				});
			}
		}
		
		function statusFormatter(value, row, index) {

			var sn = GlobalTypeUtil.getTypeName(2, 1, value);
			if (value === 2) {
				return [ '<span class="text-success">', '</span>' ].join(sn);
			}
			return [ '<span class="text-danger">', '</span>' ].join(sn);
		}
		
		function dateFormatter(value, row, index) {
			var date = new Date(value);
// 			var col = '<a href="#" class="openTime showTime" data-type="combodate" data-template="HH:mm:ss" data-format="HH:mm:ss" data-viewformat="HH:mm:ss" data-pk="1" data-title="开奖时间设置" class="editable editable-click editable-empty editable-open" data-original-title="开奖时间设置" title="开奖时间设置" id="'+row.id+'">'+date.Format("hh:mm:ss")+'</a>';
			var col = '<input type="text" class="form-control" id="date_'+row.id+'" value="'+date.Format("hh:mm:ss")+'">';
			return col;
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