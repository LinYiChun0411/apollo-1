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
		<ul class="nav nav-tabs">
			<li class="active"><a href="#" data-toggle="tab" id="confnav">配置内容</a></li>
			<li><a href="#" data-toggle="tab" id="setnav">绑定站点</a></li>
		</ul>
		<div id="confnav_div">
			<div id="toolbar">
				<div id="search" class="form-inline">
					<label class="sr-only" for="splatform">配置名称</label>
					<div class="form-group">
						<div class="input-group">
							<input type="text" class="form-control" id="sname" placeholder="配置名称">
						</div>
					</div>
					<button class="btn btn-primary" onclick="search();">查询</button>
					<button class="btn btn-primary" onclick="add();">新增</button>
				</div>
			</div>
			<table id="datagrid_tb"></table>
		</div>
		<div id="setnav_div" class="hidden">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div id="toolbar">
						<div id="search" class="form-inline">
							<button id="saveConf" class="btn btn-primary" onclick="saveSet();" disabled="disabled">保存</button>
							<select id="stationId" class="form-control"></select>
						</div>
					</div>
				</div>
				<div class="panel-body" id="settings"></div>
			</div>
		</div>

	</div>
	<div class="modal fade" id="editmodel" tabindex="-1" role="dialog" aria-labelledby="editLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="editLabel">编辑配置</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="confId">
					<table class="table table-bordered table-striped" style="clear: both">
						<tbody>
							<tr>
								<td width="20%" class="text-right">配置名称：</td>
								<td width="80%" class="text-left"><input type="text" class="form-control" id="name" /></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">配置标题：</td>
								<td width="80%" class="text-left"><input type="text" class="form-control" id="title" /></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">键：</td>
								<td width="80%" class="text-left"><input type="text" class="form-control" id="key" /></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">配置类型：</td>
								<td width="80%" class="text-left"><select id="type" class="form-control">
								</select></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">状态：</td>
								<td width="80%" class="text-left"><select id="status" class="form-control">
										<option value="1">禁用</option>
										<option value="2">启用</option>
								</select></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">预设值：</td>
								<td width="80%" class="text-left"><input type="text" class="form-control" id="initValue" /></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">拓展属性：</td>
								<td width="80%" class="text-left"><textarea id="expand" class="form-control"></textarea></td>
							</tr>
							<tr>
								<td width="20%" class="text-right">绑定数据源：</td>
								<td width="80%" class="text-left"><textarea id=source class="form-control"></textarea></td>
							</tr>
							<tr class="agentpro hidden">
								<td width="20%" class="text-right">备注：</td>
								<td width="80%" class="text-left"><textarea id="remark" class="form-control"></textarea></td>
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
	<div class="modal fade bs-example-modal-lg" id="detailmodel" tabindex="-1" role="dialog" aria-labelledby="detailLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="detailLabel">预览配置</h4>
				</div>
				<div class="modal-body">
					<div id="detail_table"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<script id="combolst_tpl" type="text/html">
	<option value="0">请选择</option>
	{{each data as tl}}
			<option value="{{tl.id}}">{{tl.name}}</option>
	{{/each}}
	</script>
	<script id="config_tpl" type="text/html">
		<table class="table table-bordered table-striped" style="clear: both">
                <tbody> 
                    <tr>
						<td width="20%" class="text-right">{{conf.name}}</td>
                        <td width="80%"><a href="#" id="{{conf.key}}" data-type="{{conf.type}}" 
										data-value="{{conf.initValue}}" data-pk="{{conf.id}}" data-title="{{conf.title}}" 
										data-source="{{conf.source}}" {{conf.expand}}></a></td>
					</tr>
				</tbody>
		</table>
	</script>

	<script id="settings_tpl" type="text/html">
	{{each confs as conf}}
	<div class="checkbox">
		<label>
  			<input type="checkbox" id="{{conf.id}}" value="{{conf.id}}" {{checkLst[conf.id]}}> {{conf.name}}
		</label>
	</div>
	{{/each}}
	</script>
	<script type="text/javascript">
		function getTab() {
			window.table = new Game.Table({
				id : 'datagrid_tb',
				url : '${base}/admin/agentconfig/list.do',
				queryParams : queryParams,//参数
				toolbar : $('#toolbar'),
				columns : [ {
					field : 'name',
					title : '配置名称',
					align : 'center',
					width : '200',
					valign : 'middle',
				}, {
					field : 'title',
					title : '配置标题',
					align : 'center',
					width : '200',
					valign : 'middle'
				}, {
					field : 'key',
					title : '键',
					align : 'center',
					width : '200',
					valign : 'middle'
				}, {
					field : 'type',
					title : '配置类型',
					align : 'center',
					width : '180',
					valign : 'bottom',
					formatter : typeFormatter
				}, {
					field : 'initValue',
					title : '预设值',
					align : 'center',
					width : '180',
					valign : 'bottom'
				}, {
					field : 'expand',
					title : '拓展属性',
					align : 'center',
					width : '180',
					valign : 'bottom'
				}, {
					field : 'source',
					title : '绑定数据源',
					align : 'center',
					width : '180',
					valign : 'bottom'
				}, {
					field : 'remark',
					title : '备注',
					align : 'center',
					width : '180',
					valign : 'bottom'
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
		function typeFormatter(value, row, index) {

			return GlobalTypeUtil.getTypeName(8, 1, value);
		}

		function statusFormatter(value, row, index) {

			var sn = GlobalTypeUtil.getTypeName(7, 1, value);
			if (value === 2) {
				return [ '<span class="text-success">', '</span>' ].join(sn);
			}
			return [ '<span class="text-danger">', '</span>' ].join(sn);
		}

		function operateFormatter(value, row, index) {
			return [ '<a class="detail" href="javascript:void(0)" title="预览">',
					'<i class="glyphicon glyphicon-eye-open"></i>', '</a>',
					'<a class="eidt" href="javascript:void(0)" title="修改">',
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
			'click .detail' : function(e, value, row, index) {

				detail(row);
				$("#detailmodel").modal('toggle');
			},
			'click .eidt' : function(e, value, row, index) {

				$("#editmodel").modal('toggle');
				$("#confId").val(row.id);
				$("#name").val(row.name);
				$("#title").val(row.title);
				$("#type").val(row.type);
				$("#key").val(row.key);
				$("#source").val(row.source);
				$("#initValue").val(row.initValue);
				$("#expand").val(row.expand);
				$("#remark").val(row.remark);
				$("#status").val(row.status);
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
			initStationLst();
			//绑定下拉事件
			bindNavTab();
			bindStationLst();
		})

		function bindNavTab() {
			$("a[data-toggle='tab']").click(function() {
				var id = this.id;
				if (id === "confnav") {
					$("#confnav_div").removeClass("hidden");
					$("#setnav_div").addClass("hidden");
				} else if (id === "setnav") {
					$("#setnav_div").removeClass("hidden");
					$("#confnav_div").addClass("hidden");
				}
			});
		}

		function initStationLst() {
			$.ajax({
				url : "${base}/admin/station/combo.do",
				success : function(result) {
					var eachdata = {
						"data" : result
					};
					var html = template('combolst_tpl', eachdata);
					$("#stationId").append(html);
				}
			});
		}

		function bindStationLst() {
			$("#stationId").change(function() {
				$("#settings").html("");
				$("#saveConf").attr("disabled", "disabled")
				var selval = $(this).children('option:selected').val();
				if (selval > 0) {
					loadMenuByStationId(selval);
				}
			});
		}
		/**
		 *加载租户权限菜单
		 */
		function loadMenuByStationId(staId) {
			$.ajax({
				url : "${base}/admin/agentconfig/stationset.do",
				data : {
					stationId : staId
				},
				success : function(data) {

					var checklst = {};
					for (var i = 0; i < data.pm.length; i++) {
						var d = data.pm[i];
						checklst[d.configId] = "checked=\"checked\"";

					}
					data.checkLst = checklst;

					var html = template('settings_tpl', data);
					$("#settings").html(html);
					$("#saveConf").removeAttr("disabled");
				}
			});
		}

		function initCombo() {
			var typedata = {
				"data" : GlobalTypeUtil.getCombo(8, 1)
			};
			var typehtml = template('combolst_tpl', typedata);
			$("#type").append(typehtml);
		}

		function updStatus(row) {
			$.ajax({
				url : "${base}/admin/agentconfig/updStatus.do",
				data : row,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}

		function remove(row) {
			$.ajax({
				url : "${base}/admin/agentconfig/delete.do",
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
			$("#confId").val("");
			$("#name").val("");
			$("#title").val("");
			$("#type").val("");
			$("#key").val("");
			$("#source").val("");
			$("#initValue").val("");
			$("#expand").val("");
			$("#remark").val("");
			$("#status").val("");
		}

		function save() {
			var smitdata = {
				id : $("#confId").val(),
				name : $("#name").val(),
				title : $("#title").val(),
				type : $("#type").val(),
				key : $("#key").val(),
				source : $("#source").val(),
				initValue : $("#initValue").val(),
				expand : $("#expand").val(),
				status : $("#status").val(),
				remark : $("#remark").val()
			};
			$.ajax({
				url : "${base}/admin/agentconfig/save.do",
				data : smitdata,
				success : function(result) {
					$("#datagrid_tb").bootstrapTable('refresh');
				}
			});
		}

		function saveSet() {
			var ids = [];
			$("#setnav_div :checkbox:checked").each(function() {
				ids.push($(this).val());
			});

			$.ajax({
				url : "${base}/admin/agentconfig/saveset.do",
				data : {
					stationId : $('#stationId').val(),
					ids : ids.join(",")
				},
				success : function(result) {
					Msg.info("保存成功");
				}
			});
		}

		function detail(conf) {
			var data = {
				"conf" : conf
			};

			console.info(data);
			var html = template('config_tpl', data);
			$("#detail_table").html(html);

			var options = {
				emptytext : '无配置',
				params : setParams,
				placement : 'right'
			}

			$("#detail_table").find("a").each(function() {

				var type = $(this).attr("data-type");
				var source = $(this).attr("data-source");
				var val = $(this).attr("data-value");

				if (val !== undefined && val !== "") {
					$(this).text(val);
				}

				if (type === 'select') {
					options.source = source;
					var arr = eval(source);
					for (var i = 0; i < arr.length; i++) {
						var obj = arr[i];
						if (obj[val] !== undefined) {
							$(this).text(obj[val]);
							break;
						}
					}
				} else if (type === 'date') {
					options.template = 'yyyy-mm-dd';
					options.format = 'yyyy-mm-dd';
					options.viewformat = 'yyyy-mm-dd';
					options.language = 'zh-CN';
					options.datepicker = {
						todayBtn : 'linked',
						weekStart : 1,
						startView : 0,
						language : 'zh-CN',
						minViewMode : 0,
						autoclose : false
					}
				} else if (type === 'datetime') {
					options.template = 'yyyy-mm-dd hh:ii';
					options.format = 'yyyy-mm-dd hh:ii';
					options.viewformat = 'yyyy-mm-dd hh:ii';
					options.datetimepicker = {
						language : 'zh-CN',
						todayBtn : 'linked',
						weekStart : 1,
						minViewMode : 0,
						autoclose : false
					}
				} else if (type === 'combodate') {
					options.template = 'YYYY-MM-DD HH:mm:ss';
					options.format = 'YYYY-MM-DD HH:mm:ss';
					options.viewformat = 'YYYY-MM-DD HH:mm:ss';
					options.datetimepicker = {
						language : 'zh-CN',
						todayBtn : 'linked',
						weekStart : 1
					}
				} else if (type === 'textarea') {
					options.placement = 'bottom';
				} else if (type === 'checklist') {
					$(this).text("");
					options.source = source;
					var arr = eval(source);
					var checks = val.split(",");
					for (var i = 0; i < checks.length; i++) {
						var ck = checks[i];
						for (var j = 0; j < arr.length; j++) {
							var obj = arr[j];
							if (obj[ck] !== undefined) {
								$(this).append(obj[ck]);
								$(this).append("<br>");
								break;
							}
						}
					}
				}
				$(this).editable(options);
			});
		}

		function setParams(params) {
			if ($(this).attr("data-type") === 'checklist') {
				params.value = params.value.join(",");
			}
			params.configId = $(this).attr("id");
			return params;
		}
	</script>
</body>
</html>