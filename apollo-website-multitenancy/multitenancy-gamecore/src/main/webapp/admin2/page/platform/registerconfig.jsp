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
			<div id="search" class="form-inline">
				<label class="sr-only" for="splatform">属性名称</label> <label class="sr-only" for="stype">查询类型</label>
				<div class="form-group">
					<div class="input-group">
						<select class="form-control" id="splatform">
							<option value="0">全部类型</option>
						</select>
					</div>
					<div class="input-group">
						<input type="text" class="form-control" id="sname" placeholder="属性名称">
					</div>
				</div>
				<button class="btn btn-primary" onclick="search();">查询</button>
				<button class="btn btn-primary" onclick="add();">新增</button>
			</div>
		</div>
		<table id="datagrid_tb"></table>
	</div>
	<div class="modal fade" id="editmodel" tabindex="-1" role="dialog" aria-labelledby="editLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="editLabel">编辑域名</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="srcId">
					<table class="table table-bordered table-striped" style="clear: both">
						<tbody>
							<tr>
								<td width="20%" class="text-right">属性名称：</td>
								<td width="80%" class="text-left"><input type="text" class="form-control" id="name" /></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">注册入口：</td>
								<td width="80%" class="text-left"><select id="platform" class="form-control">
										<option value="0">选择注册入口</option>
								</select></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">属性类型：</td>
								<td width="80%" class="text-left"><select id="type" class="form-control">
										<option value="0">选择属性类型</option>
								</select></td>
							</tr>
							<tr class="mempro hidden">
								<td width="20%" class="text-right">显示：</td>
								<td width="80%" class="text-left"><select id="show" class="form-control">
										<option value="1">隐藏</option>
										<option value="2">可见</option>
								</select></td>
							</tr>
							<tr class="mempro hidden">
								<td width="20%" class="text-right">验证：</td>
								<td width="80%" class="text-left"><select id="validate" class="form-control">
										<option value="1">隐藏</option>
										<option value="2">可见</option>
								</select></td>
							</tr>
							<tr class="mempro hidden">
								<td width="20%" class="text-right">验证表达式：</td>
								<td width="80%" class="text-left"><input type="text" id="regex" class="form-control"></td>
							</tr>
							<tr class="mempro hidden">
								<td width="20%" class="text-right">必输：</td>
								<td width="80%" class="text-left"><select id="required" class="form-control">
										<option value="1">隐藏</option>
										<option value="2">可见</option>
								</select></td>
							</tr>
							<tr class="agentpro hidden">
								<td width="20%" class="text-right">状态验证：</td>
								<td width="80%" class="text-left"><select id="statusValidate" class="form-control">
										<option value="1">隐藏</option>
										<option value="2">可见</option>
								</select></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">状态：</td>
								<td width="80%" class="text-left"><select id="status" class="form-control">
										<option value="1">禁用</option>
										<option value="2">启用</option>
								</select></td>
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
	<script id="combolst_tpl" type="text/html">
	{{each data as tl}}
			<option value="{{tl.id}}">{{tl.name}}</option>
	{{/each}}
	</script>
	<script type="text/javascript">
		function getTab() {
			window.table = new Game.Table({
				id : 'datagrid_tb',
				url : '${base}/admin/registerconf/list.do',
				queryParams : queryParams,//参数
				toolbar : $('#toolbar'),
				columns : [ {
					field : 'name',
					title : '属性名称',
					align : 'center',
					width : '200',
					valign : 'middle',
				}, {
					field : 'platform',
					title : '注册入口',
					align : 'center',
					width : '200',
					valign : 'middle',
					formatter : platformFormatter
				}, {
					field : 'type',
					title : '属性类型',
					align : 'center',
					width : '180',
					valign : 'bottom',
					formatter : typeFormatter
				}, {
					field : 'show',
					title : '显示',
					align : 'center',
					width : '180',
					valign : 'bottom',
					formatter : showFormatter
				}, {
					field : 'validate',
					title : '验证',
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
					formatter : showFormatter
				}, {
					field : 'statusValidate',
					title : '状态验证',
					align : 'center',
					width : '180',
					valign : 'bottom',
					formatter : showFormatter
				}, {
					field : 'status',
					title : '状态',
					align : 'center',
					width : '200',
					valign : 'middle',
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

		function statusFormatter(value, row, index) {

			var sn = GlobalTypeUtil.getTypeName(7, 1, value);
			if (value === 2) {
				return [ '<span class="text-success">', '</span>' ].join(sn);
			}
			return [ '<span class="text-danger">', '</span>' ].join(sn);
		}

		function platformFormatter(value, row, index) {

			return GlobalTypeUtil.getTypeName(7, 2, value);
		}

		function typeFormatter(value, row, index) {

			return GlobalTypeUtil.getTypeName(7, 3, value);
		}

		function showFormatter(value, row, index) {
			if (value === undefined || value === "") {
				return "-";
			}

			var sn = GlobalTypeUtil.getTypeName(7, 4, value);
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
					'<a class="start" href="javascript:void(0)" title="启用">',
					'<i class="glyphicon glyphicon-ok-circle"></i>', '</a>  ',
					'<a class="stop" href="javascript:void(0)" title="停用">',
					'<i class="glyphicon glyphicon-ban-circle"></i>', '</a>',
					'<a class="remove" href="javascript:void(0)" title="删除">',
					'<i class="glyphicon glyphicon-remove"></i>', '</a>' ]
					.join('');
		}

		window.operateEvents = {
			'click .eidt' : function(e, value, row, index) {

				$("#editmodel").modal('toggle');
				$("#srcId").val(row.id);
				$("#name").val(row.name);
				$("#platform").val(row.platform);
				$("#platform").attr("disabled", "disabled");
				$("#type").val(row.type);
				$("#show").val(row.show);
				$("#required").val(row.required);
				$("#statusValidate").val(row.statusValidate);
				$("#status").val(row.status);
				$("#validate").val(row.validate);
				$("#regex").val(row.regex);
				$("#platform").change();
			},
			'click .start' : function(e, value, row, index) {
				row.status = 2;
				updStatus(row);
			},
			'click .stop' : function(e, value, row, index) {
				row.status = 1;
				updStatus(row);
			},
			'click .remove' : function(e, value, row, index) {
				remove(row);
			}
		};

		//设置传入参数
		function queryParams(params) {
			params["name"] = $("#sname").val();
			params["platform"] = $("#splatform").val();
			return params
		}
		$(function() {
			getTab();
			initCombo();
			bindCombo();
		})

		function initCombo() {
			var platdata = {
				"data" : GlobalTypeUtil.getCombo(7, 2)
			};
			var typedata = {
				"data" : GlobalTypeUtil.getCombo(7, 3)
			};
			var plathtml = template('combolst_tpl', platdata);
			var typehtml = template('combolst_tpl', typedata);
			$("#splatform").append(plathtml);
			$("#platform").append(plathtml);
			$("#type").append(typehtml);
		}

		function bindCombo() {
			$("#platform").change(function() {
				var val = this.value;
				if (val == 1) {
					$(".agentpro").addClass('hidden');
					$(".mempro").removeClass('hidden');
				} else if (val == 2) {
					$(".mempro").addClass('hidden');
					$(".agentpro").removeClass('hidden');
				} else {
					$(".agentpro").addClass('hidden');
					$(".mempro").addClass('hidden');
				}
			});
		}

		function updStatus(row) {
			$.ajax({
				url : "${base}/admin/registerconf/updStatus.do",
				data : row,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}

		function remove(row) {
			$.ajax({
				url : "${base}/admin/registerconf/delete.do",
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
			$("#srcId").val("");
			$("#name").val("");
			$("#platform").val("");
			$("#platform").removeAttr("disabled");
			$("#type").val("");
			$("#show").val("");
			$("#required").val("");
			$("#statusValidate").val("");
			$("#validate").val("");
			$("#status").val("");
			$("#regex").val("");
			$("#platform").change();
		}

		function save() {
			var smitdata = {
				id : $("#srcId").val(),
				name : $("#name").val(),
				platform : $("#platform").val(),
				type : $("#type").val(),
				show : $("#show").val(),
				required : $("#required").val(),
				statusValidate : $("#statusValidate").val(),
				validate : $("#validate").val(),
				status : $("#status").val(),
				regex : $("#regex").val()
			};
			$.ajax({
				url : "${base}/admin/registerconf/save.do",
				data : smitdata,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}
	</script>
</body>
</html>