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
	<jsp:include page="/agent/include/agentmenu.jsp"></jsp:include>
	<div class="container">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#" data-toggle="tab" id="1">会员注册</a></li>
			<li><a href="#" data-toggle="tab" id="2">代理注册</a></li>
		</ul>
		<div id="toolbar">
			<button class="btn btn-primary" onclick="save();">保存</button>
		</div>
		<table id="datagrid_tb"></table>
	</div>
	<!--<button id="btn_on_{{key}}_{{index}}" class="btn btn-primary" onclick="btnclick({{index}},'{{key}}',2);" {{on_regex}}>是</button>&nbsp;
		<button id="btn_off_{{key}}_{{index}}" class="btn btn-primary" onclick="btnclick({{index}},'{{key}}',1);" {{off_regex}}>否</button>  -->
	<script id="btn_chk_tpl" type="text/html">
		<input type="checkbox" name="switch_chk" value="{{index}}_{{key}}" {{checked}}>
	</script>
	<script type="text/javascript">
		var platform = 1;
		var datas = {};
		function getTab() {
			$("#datagrid_tb").bootstrapTable({
				method : 'post',
				cache : false,
				striped : true,
				contentType : "application/x-www-form-urlencoded",
				queryParamsType : null,
				queryParams : queryParams,//参数
				url : '${base}/agent/system/registerconf/list.do',
				toolbar : "#toolbar",
				onLoadSuccess : bindSwitch,
				columns : [ {
					field : 'name',
					title : '属性名称',
					align : 'center',
					width : '200',
					valign : 'middle',
				}, {
					field : 'show',
					title : '显示',
					align : 'center',
					width : '180',
					valign : 'bottom',
					formatter : showFormatter
				}, {
					field : 'required',
					title : '必输',
					align : 'center',
					width : '180',
					valign : 'bottom',
					formatter : requiredFormatter
				}, {
					field : 'validate',
					title : '验证',
					align : 'center',
					width : '180',
					valign : 'bottom',
					formatter : validateFormatter
				}, {
					field : 'statusValidate',
					title : '状态验证',
					align : 'center',
					width : '180',
					valign : 'bottom',
					formatter : statusValidateFormatter
				} ]
			});
		}

		function platformFormatter(value, row, index) {

			return GlobalTypeUtil.getTypeName(7, 2, value);
		}

		function typeFormatter(value, row, index) {

			return GlobalTypeUtil.getTypeName(7, 3, value);
		}

		function showFormatter(value, row, index) {
			var key = "showVal";
			return premiumFormatter(value, row, index, key);
		}
		function requiredFormatter(value, row, index) {
			var key = "requiredVal";
			return premiumFormatter(value, row, index, key);
		}
		function validateFormatter(value, row, index) {
			var key = "validateVal";
			return premiumFormatter(value, row, index, key);
		}
		function statusValidateFormatter(value, row, index) {
			var key = "statusValidateVal";
			return premiumFormatter(value, row, index, key);
		}

		function premiumFormatter(value, row, index, key) {

			if (value == 1) {
				return "-";
			}
			var html = "";
			var data = {};
			data.index = index;
			data.key = key;
			if (row[key] == 2) {
				data.on_regex = "disabled=\"disabled\"";
				data.checked = "checked"
			} else {
				data.off_regex = "disabled=\"disabled\"";
				data.checked = ""
			}
			html = template('btn_chk_tpl', data);
			return html;
		}

		$(function() {
			getTab();
			bindNavTab();
			$("#datagrid_tb").bootstrapTable('hideColumn', 'statusValidate');
		})

		function bindNavTab() {
			$("a[data-toggle='tab']").click(function() {
				var table = $("#datagrid_tb");
				var id = this.id;
				platform = id;
				if (id == "1") {
					table.bootstrapTable('hideColumn', 'statusValidate');
					table.bootstrapTable('showColumn', 'show');
					table.bootstrapTable('showColumn', 'validate');
					table.bootstrapTable('showColumn', 'required');
				} else if (id == "2") {
					table.bootstrapTable('hideColumn', 'show');
					table.bootstrapTable('hideColumn', 'validate');
					table.bootstrapTable('hideColumn', 'required');
					table.bootstrapTable('showColumn', 'statusValidate');
				}
				table.bootstrapTable('refresh');
			});
		}
		function bindSwitch() {
			var options = {
				onText : "是",
				offText : "否",
				onSwitchChange : chkChange
			};
			$("[name='switch_chk']").bootstrapSwitch(options);
		}

		function chkChange() {
			var params = this.value.split("_");
			var val = 1;
			if (this.checked) {
				val = 2;
			}
			var index = params[0];
			var key = params[1];

			var table = $("#datagrid_tb");
			var data = table.bootstrapTable('getData');//获取表格的所有内容行  
			data[index][key] = val;
		}

		/* function btnclick(index, key, val) {
			var onbtn = $("#btn_on_" + key + "_" + index);
			var offbtn = $("#btn_off_" + key + "_" + index);
			if (val == 1) {
				onbtn.prop('disabled', false);
				offbtn.prop('disabled', true);
			} else {
				onbtn.prop('disabled', true);
				offbtn.prop('disabled', false);
			}

			var table = $("#datagrid_tb");
			var data = table.bootstrapTable('getData');//获取表格的所有内容行  
			data[index][key] = val;
			//table.bootstrapTable('load', data);//获取表格的所有内容行 

			// for (i = 0; i < data.length; i++) {
			//	if (i == index) {
			//		//var newRow = getNewRow(data[i], key, val); 
			//		var newRow = data[i];
			//		newRow[key] = val;
			//		table.bootstrapTable('updateRow', {
			//			index : i,
			//			row : newRow
			//		});
			//		break;
			//	}
			//}
		} */

		/* function getNewRow(row, key, val) {
			var newRow = {};
			if (!row.hasOwnProperty(key)) {
				row[key] = val;
				return row;
			}
			for ( var ok in row) {
				if (ok == key) {
					newRow[ok] = val;
				} else {
					newRow[ok] = row[ok];
				}
			}
			return newRow;
		} */

		//设置传入参数
		function queryParams(params) {
			params["platform"] = platform;
			return params
		}

		function save() {
			var table = $("#datagrid_tb");
			var data = table.bootstrapTable('getData');//获取表格的所有内容行  
			$.ajax({
				url : "${base}/agent/system/registerconf/save.do",
				data : {
					"data" : JSON.encode(data)
				},
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}
	</script>
</body>
</html>